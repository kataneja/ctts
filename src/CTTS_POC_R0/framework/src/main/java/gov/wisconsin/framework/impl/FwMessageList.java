package gov.wisconsin.framework.impl;

import gov.wisconsin.framework.base.IPage;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.management.FwPageManager;
import gov.wisconsin.framework.util.FwMessageFormatter;

import java.io.Serializable;
import java.util.ArrayList;

public class FwMessageList implements Serializable {
	private static final long serialVersionUID = -8038593091912591194L;
	
	private boolean errFlag = true;
	private boolean browserFlag = false;
	private ArrayList<FwMessage> messageList = null;
	
	private IPage pageManager = FwPageManager.getInstance();
	private FwMessageFormatter messageFormatter = FwMessageFormatter.getInstance();
	
	public FwMessageList() {
		messageList = new ArrayList<FwMessage>();
	}

	public void addMessageToList(FwMessage message) {
		messageList.add(message);
	}

	public void addMessageToList(FwMessageList msgList) {
		if (msgList != null) {
			ArrayList<FwMessage> fwMsgList = msgList.getMessageList();
			for (int i = 0; i < fwMsgList.size(); i++) {
				this.messageList.add(fwMsgList.get(i));
			}
		}
	}

	public boolean containsMessageCode(String messageCode) {
		for (FwMessage msg : messageList) {
			if (msg.getMessageCode().equals(messageCode)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasMessages() {
		if (this.messageList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public FwMessage getMessageAtIndex(int index) {
		try {
			return this.messageList.get(index);
		} catch(Exception e){
			FwExceptionManager.handleException(this.getClass(), e);
		}
		return null;
	}
	
	public void performMessageSubstitution() {
		FwMessage tmp = null;
		String language = "EN";
		int size = this.messageList.size();
		
		for (int i = 0; i < size; i++) {
			tmp = messageList.get(i);
			String[] _refMsg = pageManager.getRefTableMessage(tmp.getMessageCode(), language);
			
			if (_refMsg.length == 2) {
				tmp.setMessageDescription(_refMsg[1]);
			}
			
			if (tmp.getMessageFieldValue() != null) {
				Object[] messageFiledValueArray = tmp.getMessageFieldValue();
				String[] value = new String[messageFiledValueArray.length];
				
				for (int j = 0; j < messageFiledValueArray.length; j++) {
					Object newObj = messageFiledValueArray[j];
					
					if (newObj instanceof FwMessageTextLabel) {
						String textIDString = ((FwMessageTextLabel) newObj).getTextId();
						value[j] = messageFormatter.getCachedText(Integer.parseInt(textIDString), language);
					} else {
						value[j] = newObj.toString();
					}
				}
				
				this.messageList.get(i).setFinalizedMessage(messageFormatter.getFormattedMessage(_refMsg[1], value));
			} else {
				this.messageList.get(i).setFinalizedMessage(_refMsg[1]);
			}
		}
	}
	
	public int getMessageListSize() {
		return this.messageList.size();
	}
	
	public ArrayList<FwMessage> getMessageList() {
		this.performMessageSubstitution();
		return messageList;
	}

	public void setMessageList(ArrayList<FwMessage> messageList) {
		this.messageList = messageList;
	}

	public boolean isBrowserFlag() {
		return browserFlag;
	}

	public void setBrowserFlag(boolean b) {
		browserFlag = b;
	}
	
	public void setErrFlagToMsgsOnly() {
		this.errFlag = false;
	}

	public boolean isErrors() {
		return this.errFlag;
	}
}
