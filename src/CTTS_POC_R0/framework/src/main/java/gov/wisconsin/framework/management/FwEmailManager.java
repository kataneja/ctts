package gov.wisconsin.framework.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractCollection;
import gov.wisconsin.framework.data.base.ICargoEmail;
import gov.wisconsin.framework.data.base.IEmail;
import gov.wisconsin.framework.data.pojo.FwEmailDistribution;
import gov.wisconsin.framework.data.pojo.FwEmailMessage;
import gov.wisconsin.framework.data.pojo.FwEmailMessageSubstitution;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class FwEmailManager extends FwBaseClass implements IEmail {

	private String fromEmailID = "smtpout.state.wi.us";
	
	private String smtpServerHost = "smtpout.state.wi.us";
	
	private final String PARSER = "~";
	
	private FwEmailManager() {}
	
	private FwEmailMessage getMessage(String messageId) {
		try {
			FwEmailMessage emailMessage = null;
			if (messageId != null) {
				emailMessage = (FwEmailMessage) configurationManager.getXMLEmailId(messageId);
			}
			return emailMessage;
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		return null;
	}
	
	private FwEmailDistribution getDistributionIds(String distributionId) {
		try {
			FwEmailDistribution distributionIds = null;
			if(distributionId != null) {
				distributionIds = (FwEmailDistribution) configurationManager.getXMLdistributionList(distributionId);
			}
			return distributionIds;
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		return null;
	}

	@Override
	public String performSubstitution(String message, String[] substitute) {
		try {
			for (int i = 0; i < substitute.length; i++) {
				message = message.replaceFirst(PARSER, substitute[i]);
			}
		} catch(Exception e){
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		return message;
	}

	@Override
	public void handleEmail(FwAbstractCollection coll) {
		List<FwEmailMessage> emailMessageList = new ArrayList<FwEmailMessage>();
		Iterator<?> itr = coll.iterator();
		while(itr.hasNext()) {
			ICargoEmail cargo = (ICargoEmail) itr.next();
			if (cargo.getDistributionId() != null) {
				emailMessageList.add(createEmailMessage(cargo.getMessageId(), cargo.getDistributionId(), cargo.getEmailMessageSubstitution()));
			} else {
				emailMessageList.add(createEmailMessage(cargo.getMessageId(), cargo.getEmailMessageSubstitution()));
			}
		}
		sendEmailMessage(emailMessageList);
	}

	@Override
	public void sendEmailMessage(List<FwEmailMessage> emailMessageList) throws FwException {
		Properties props = new Properties();
		props.put("mail.debug", "true");
		props.put("mail.smtp.host", smtpServerHost);

		try {
			Session session = Session.getInstance(props, null);
			if(emailMessageList != null) {
				FwEmailMessage sendMessage = null;
				StringBuffer text = new StringBuffer();
				MimeMessage msg = new MimeMessage(session);
				int emailMessageListSize = emailMessageList.size(); 
				for(int i = 0; i < emailMessageListSize; i++) {
					sendMessage = emailMessageList.get(i);
					String header = sendMessage.getHeader();
					String bodyText = sendMessage.getBody();
					String footer = sendMessage.getFooter();
					String[] toAddress = sendMessage.getToAddresses();
					String[] ccAddress = sendMessage.getCcAddresses();
					String[] bccAddress = sendMessage.getBccAddresses();
					
					if(sendMessage.getHeader() != null) { text.append(header); }
					if(bodyText != null) { text.append("\n").append(bodyText); }
					if(footer != null) { text.append("\n").append(footer); }
					
					Address[] toAdr = null;
					if(toAddress != null) {
						toAdr = new InternetAddress[toAddress.length];
						for (int j = 0; j < toAdr.length; j++) {
							if(toAddress[j] != null) {
								toAdr[j] = new InternetAddress(toAddress[j]);
							}
						}
					}
					
					Address[] ccAdr = null;
					if(ccAddress != null) {
						ccAdr = new InternetAddress[ccAddress.length];
						for(int j = 0; j < ccAdr.length; j++) {
							if(ccAddress[j] != null) {
								ccAdr[j] = new InternetAddress(ccAddress[j]);
							}
						}
					}
					
					Address[] bccAdr = null;
					if(bccAddress != null) {
						bccAdr = new InternetAddress[bccAddress.length];
						for(int j = 0; j < bccAdr.length; j++) {
							if(bccAddress[j] != null) {
								bccAdr[j] = new InternetAddress(bccAddress[j]);
							}
						}
					}
					
					msg.setFrom();
					if(sendMessage.getFromAddress() != null) { msg.setFrom(new InternetAddress(sendMessage.getFromAddress())); }
					else { msg.setFrom(new InternetAddress(fromEmailID)); }
					
					if(toAdr != null) { msg.setRecipients(Message.RecipientType.TO, toAdr); }
			        if(ccAdr != null) { msg.setRecipients(Message.RecipientType.CC, ccAdr); }
			        if(bccAdr != null) { msg.setRecipients(Message.RecipientType.BCC, bccAdr); }
			        
			        msg.setSubject(sendMessage.getSubject());
			        msg.setSentDate(new Date());
			        msg.setText(text.toString());
			        Transport.send(msg);
			        text = new StringBuffer();
				}
				emailMessageList.clear();
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
	}

	public FwEmailMessage createEmailMessage(String messageId, FwEmailMessageSubstitution emailMessageSubstitution) {
		try {
			String[] bodySub = emailMessageSubstitution.getBodySubst();
			String[] footerSub = emailMessageSubstitution.getFooterSubst();
			String[] headerSub = emailMessageSubstitution.getHeaderSubst();
			String[] subjectSub = emailMessageSubstitution.getSubjectSubst();
			
			String footer = null;
			String header = null;
			String subject = null;
			String bodyText = null;
			String fromAddress = null;
			String[] toAddress = null;
			String[] ccAddress = null;
			String[] bccAddress = null;
			
			if (messageId != null) {
				FwEmailMessage xmlEmailMessage = null;
				xmlEmailMessage = getMessage(messageId);
				header = xmlEmailMessage.getHeader();
				bodyText = xmlEmailMessage.getBody();
				footer = xmlEmailMessage.getFooter();
				subject = xmlEmailMessage.getSubject();
				
				if(subject == null || subject.trim().length() == 0) {
					if(subjectSub != null && subjectSub.length > 0) {
						subject = subjectSub[0];
					}
				} else if(subjectSub != null && subjectSub.length > 0) {
					subject = performSubstitution(subject, subjectSub);
				}
				
				if(footer == null || footer.trim().length() == 0) {
					if(footerSub != null && footerSub.length > 0) {
						footer = footerSub[0];
					}
				} else if(footerSub != null && footerSub.length > 0) {
					footer = performSubstitution(footer, footerSub);
				}
				
				if(bodyText == null || bodyText.trim().length() == 0) {
					if(bodySub != null && bodySub.length > 0) {
						bodyText = bodySub[0];
					}
				}else if(bodySub != null && bodySub.length > 0) {
					bodyText = performSubstitution(bodyText, bodySub);
				}
				
				if(header == null || header.trim().length() == 0) {
					if(headerSub != null && headerSub.length > 0) {
						header = headerSub[0];
					}
				}else if(headerSub != null && headerSub.length > 0) {
					header = performSubstitution(header, headerSub);
				}
			} else {
				header = headerSub[0];
				footer = footerSub[0];
				bodyText = bodySub[0];
				subject = subjectSub[0];
			}
			
			ccAddress = emailMessageSubstitution.getCcAddress();
			toAddress = emailMessageSubstitution.getToAddress();
			bccAddress = emailMessageSubstitution.getBccAddress();
			fromAddress = emailMessageSubstitution.getFromAddress();
			
			FwEmailMessage sendEmailMessage = new FwEmailMessage(subject, header, bodyText, footer, fromAddress, toAddress, ccAddress, bccAddress);
			return sendEmailMessage;
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		return null;
	}	

	public FwEmailMessage createEmailMessage(String messageId, String distributionId, FwEmailMessageSubstitution emailMessageSubstitution) {
		try {
			String[] bodySub = emailMessageSubstitution.getBodySubst();
			String[] footerSub = emailMessageSubstitution.getFooterSubst();
			String[] headerSub = emailMessageSubstitution.getHeaderSubst();
			String[] subjectSub = emailMessageSubstitution.getSubjectSubst();
			
			String footer = null;
			String header = null;
			String subject = null;
			String bodyText = null;
			String fromAddress = null;
			String[] toAddress = null;
			String[] ccAddress = null;
			String[] bccAddress = null;
			
			if (messageId != null) {
				FwEmailMessage xmlEmailMessage = null;
				xmlEmailMessage = getMessage(messageId);
				header = xmlEmailMessage.getHeader();
				bodyText = xmlEmailMessage.getBody();
				footer = xmlEmailMessage.getFooter();
				subject = xmlEmailMessage.getSubject();
				
				if(subject == null || subject.trim().length() == 0) {
					if(subjectSub != null && subjectSub.length > 0) {
						subject = subjectSub[0];
					}
				}else if(subjectSub != null && subjectSub.length > 0) {
					subject = performSubstitution(subject, subjectSub);
				}
				
				if(footer == null || footer.trim().length() == 0) {
					if(footerSub != null && footerSub.length > 0) {
						footer = footerSub[0];
					}
				}else if(footerSub != null && footerSub.length > 0) {
					footer = performSubstitution(footer, footerSub);
				}
				
				if(bodyText == null || bodyText.trim().length() == 0) {
					if(bodySub != null && bodySub.length > 0) {
						bodyText = bodySub[0];
					}
				}else if(bodySub != null && bodySub.length > 0) {
					bodyText = performSubstitution(bodyText, bodySub);
				}
				
				if(header == null || header.trim().length() == 0) {
					if(headerSub != null && headerSub.length > 0) {
						header = headerSub[0];
					}
				}else if(headerSub != null && headerSub.length > 0) {
					header = performSubstitution(header, headerSub);
				}
			} else {
				header = headerSub[0];
				footer = footerSub[0];
				bodyText = bodySub[0];
				subject = subjectSub[0];
			}
			
			if(distributionId != null) {
				FwEmailDistribution distribution = getDistributionIds(distributionId);
				String toAddr = distribution.getValue();
				toAddress = toAddr.split(";");
			} else {
				toAddress = null;
				toAddress = emailMessageSubstitution.getToAddress();
			}
			
			ccAddress = emailMessageSubstitution.getCcAddress();
			bccAddress = emailMessageSubstitution.getBccAddress();
			fromAddress = emailMessageSubstitution.getFromAddress();
			
			FwEmailMessage sendEmailMessage = new FwEmailMessage(subject,header,bodyText,footer,fromAddress, toAddress, ccAddress, bccAddress);
			return sendEmailMessage;			
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		return null;
	}
}