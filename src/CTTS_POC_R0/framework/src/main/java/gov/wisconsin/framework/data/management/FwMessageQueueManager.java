package gov.wisconsin.framework.data.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.ICargoMSG;
import gov.wisconsin.framework.data.base.IMessageQueue;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.springframework.stereotype.Component;

@Component
public class FwMessageQueueManager extends FwBaseClass implements IMessageQueue {
	private static Map<String, Queue> qcMap = new HashMap<String, Queue>();
	private static Map<String, QueueConnectionFactory> qcfMap = new HashMap<String, QueueConnectionFactory>();

	private static IMessageQueue instance;
	
	private FwMessageQueueManager() {}
	
	public void send(ICargoMSG cargo) {
		boolean transaction = false;
		QueueConnection qc = null;	 				            
		QueueSender	queueSender = null;
		QueueSession queueSession = null;
		
		try {
			Queue queue	= (Queue) getQCFromCache(cargo.getQueueAliasName());
			QueueConnectionFactory qcf = (QueueConnectionFactory) getQCFFromCache(cargo.getConnectionFactoryAliasName());
			
			qc = qcf.createQueueConnection();
			transaction = cargo.isTransaction();
			
			if (transaction) { queueSession = qc.createQueueSession(true, 0); }
			else { queueSession = qc.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE); }

			int msgPriority = cargo.getMsg_priority();
			queueSender	= queueSession.createSender(queue);

			if(msgPriority >= 0 && msgPriority <= 9) {
				if(msgPriority != 4) {
					queueSender.setPriority(msgPriority);
				}
			} else {
				FwException fe = new FwException(this.getClass(), new Exception("Message Priority is out of range (0-9)"), FwConstants.EXP_TYP_MESSAGING);
				fe.setParameterText("Message Priority is out of range (0-9)");
				FwExceptionManager.handleException(fe);
			}
			
			Message message = null;
			if(cargo.getMessageType() == FwConstants.TEXT_MESSAGE) {
				message = queueSession.createTextMessage(cargo.generateTextMessage());				
			} else if(cargo.getMessageType() == FwConstants.OBJECT_MESSAGE) {
				message = queueSession.createObjectMessage(cargo);				
				queueSender.setTimeToLive(150000000);
			}
			queueSender.send(message);

			if(transaction){
				queueSession.commit();
			}
		} 
		catch(FwException fe) {
			if(transaction){
				try {
					queueSession.rollback();
				} catch(JMSException jx) {
					FwExceptionManager.handleException(this.getClass(), jx, FwConstants.EXP_TYP_MESSAGING);
				}
			}
		} catch(JMSException jx) {
			if(transaction) {
				try {
					queueSession.rollback();
				} catch(JMSException jmsx){
					FwExceptionManager.handleException(this.getClass(), jmsx, FwConstants.EXP_TYP_MESSAGING);
				}
			}
			FwExceptionManager.handleException(this.getClass(), jx, FwConstants.EXP_TYP_MESSAGING);
		} 
		catch (Exception e) {
			if(transaction) {
				try {
					queueSession.rollback();
				} catch(JMSException jx){
					FwExceptionManager.handleException(this.getClass(), jx, FwConstants.EXP_TYP_MESSAGING);
				}
			}
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_MESSAGING);
		} finally {
			if(qc != null) {
				try {
					queueSender.close();
					queueSession.close();
					qc.close();
				} catch(Exception e) {
					FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_MESSAGING);
				}
			}
		}
	}

	
	public Message readMessage(String connectionFactory, String queueName) {
		boolean transaction = false;
		QueueConnection qc = null;
		QueueSession queueSession = null;
		QueueReceiver queueReceiver = null;

		try {
			Queue queue = getQCFromCache(queueName);
			QueueConnectionFactory qcf = getQCFFromCache(connectionFactory);
			qc = qcf.createQueueConnection();
			queueSession = qc.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			queueReceiver = queueSession.createReceiver(queue);
			qc.start();
			Message ret = queueReceiver.receive(1000);
			return ret;
		} catch(FwException fe) {
			if(transaction) {
				try {
					queueSession.rollback();
				} catch(JMSException jx) {
					FwExceptionManager.handleException(this.getClass(), jx, FwConstants.EXP_TYP_MESSAGING);
				}
			}
		} catch(JMSException jx) {
			if(transaction) {
				try {
					queueSession.rollback();
				} catch(JMSException jmsx) {
					FwExceptionManager.handleException(this.getClass(), jmsx, FwConstants.EXP_TYP_MESSAGING);
				}
			}
			FwExceptionManager.handleException(this.getClass(), jx, FwConstants.EXP_TYP_MESSAGING);
		} catch (Exception e) {
			if(transaction) {
				try {
					queueSession.rollback();
				} catch(JMSException jx) {
					FwExceptionManager.handleException(this.getClass(), jx, FwConstants.EXP_TYP_MESSAGING);
				}
			}
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_MESSAGING);
		} finally {
			if(qc != null) {
				try {
					queueReceiver.close();
					queueSession.close();
					qc.close();
				} catch(Exception e) {
					FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_MESSAGING);
				}
			}
		}
		return null;
	}

	private QueueConnectionFactory getQCFFromCache(String key) {
		QueueConnectionFactory result = null;
		if((result = qcfMap.get(key)) == null) {
			result = createQCF(key);
		}			
		return result;
	}	
	
	private Queue getQCFromCache(String key) {
		Queue result;
		if((result = qcMap.get(key)) == null) {
			result = createQC(key);
		}
		return result;
	}

	private synchronized Queue createQC(String key) {
		Queue result;
		if((result = qcMap.get(key)) == null) {
			try {
				Context ctx = new InitialContext();
				result = (Queue) PortableRemoteObject.narrow(ctx.lookup(configurationManager.getSpringProperty(key)), Queue.class);
				qcMap.put(key, result);
			} catch( NamingException ne) {
				FwExceptionManager.handleException(this.getClass(), ne, FwConstants.EXP_TYP_MESSAGING);
			}
		}
		return result;			
	}
	
	private synchronized QueueConnectionFactory createQCF(String key) {
		QueueConnectionFactory result = null;
		if((result = qcfMap.get(key)) == null) {
			try {
				Context ctx = new InitialContext();
				result = (QueueConnectionFactory) PortableRemoteObject.narrow(ctx.lookup(configurationManager.getSpringProperty(key)), QueueConnectionFactory.class);
				qcfMap.put(key, result);
			} catch(NamingException ne) {
				FwExceptionManager.handleException(this.getClass(), ne, FwConstants.EXP_TYP_MESSAGING);
			}
		}			
		return result;
	}
	
	public static void setInstance(IMessageQueue messageQueueManager) {
    	instance = messageQueueManager;
    }
    
    public static IMessageQueue getInstance() {
    	return instance;
    }
}
