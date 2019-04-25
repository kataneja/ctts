package gov.wisconsin.framework.data.base;

import java.io.Serializable;

public abstract class FwAbstractMessageCargo implements Serializable, ICargoMSG {
	private static final long serialVersionUID = -7050412824988492513L;
	
	private String user;
	private String cargoName;
	private String queueAliasName;
	private String connectionFactoryAliasName; 

	private int messageType; //TEXT_MESSAGE = 1 | OBJECT_MESSAGE = 2
	private int msg_priority = 4;
	private boolean isTransaction = false;
	
	public abstract String getPackage(); 
	
	public abstract String generateTextMessage();
	
	public String getConnectionFactoryAliasName() {
		return connectionFactoryAliasName;
	}

	public String getQueueAliasName() {
		return queueAliasName;
	}

	public void setConnectionFactoryAliasName(String connectionFactoryAliasName) {
		this.connectionFactoryAliasName = connectionFactoryAliasName;
	}

	public void setQueueAliasName(String queueAliasName) {
		this.queueAliasName = queueAliasName;
	}
	
	public java.lang.String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public boolean isDirty() {
		return false;
	}

	public void setDirty(boolean dirty) {
		
	}

	public IPrimaryKey getPrimaryKey() {
		return null;
	}

	public int getMsg_priority() {
		return msg_priority;
	}

	public void setMsg_priority(int msg_priority) {
		this.msg_priority = msg_priority;
	}

	public boolean isTransaction() {
		return isTransaction;
	}

	public void setTransaction(boolean isTransaction) {
		this.isTransaction = isTransaction;
	}
	
	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
}
