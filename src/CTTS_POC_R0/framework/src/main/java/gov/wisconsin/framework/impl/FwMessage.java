package gov.wisconsin.framework.impl;

import gov.wisconsin.framework.base.IPage;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.management.FwPageManager;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;

public class FwMessage implements Serializable {
	private static final long serialVersionUID = 3792883025488714673L;
	
	protected static ApplicationContext applicationContext;
	
	private String message = null;	
	private String messageCode = null;
	private String[] elementId = null;
	private String messageSeverity = null;
	private String messageDescription = null;
	private Object[] messageFieldValue = null;
	
	private String finalizedMessage = null;

	public FwMessage() {}
	
	public void addMessage(String message) {
		this.message = message;
		this.setSeverityCode();
	}

	public void addMessageCode(String messageCode) {
		this.messageCode = messageCode;
		this.setSeverityCode();
	}

	public void addMsgCdElemIdMsgFieldVal(String messageCode, String[] elementId, Object[] messageFieldValue) {
		this.messageCode = messageCode;
		this.elementId = elementId;
		this.messageFieldValue = messageFieldValue;
		this.setSeverityCode();
	}

	public void addMessageWithElementIds(String messageCode, String[] elementId) {
		this.messageCode = messageCode;
		this.elementId = elementId;
		this.setSeverityCode();
	}
	
	public void addMessageWithFieldValues(String messageCode, Object[] messageFieldValue) {
		this.messageCode = messageCode;
		this.messageFieldValue = messageFieldValue;
		this.setSeverityCode();
	}

	private void setSeverityCode() {
		try {
			IPage pageManager = FwPageManager.getInstance();
			String[] errorCodeDesc = pageManager.getRefTableMessage(this.messageCode, FwConstants.ENGLISH);
			this.setMessageSeverity(errorCodeDesc[0]);
			if (errorCodeDesc.length == 2) {
				this.setMessageDescription(errorCodeDesc[1]);
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
	}
	
	public String getMessageSeverity() {
		return messageSeverity;
	}

	public void setMessageSeverity(String messageSeverity) {
		this.messageSeverity = messageSeverity;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getElementId() {
		return elementId;
	}

	public void setElementId(String[] elementId) {
		this.elementId = elementId;
	}

	public Object[] getMessageFieldValue() {
		return messageFieldValue;
	}

	public void setMessageFieldValue(Object[] messageFieldValue) {
		this.messageFieldValue = messageFieldValue;
	}
	
	public String getMessageDescription() {
		return messageDescription;
	}

	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	
	public String getFinalizedMessage() {
		return finalizedMessage;
	}

	public void setFinalizedMessage(String finalizedMessage) {
		this.finalizedMessage = finalizedMessage;
	}
}
