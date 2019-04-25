package gov.wisconsin.framework.data.pojo;

import gov.wisconsin.framework.data.base.ICargoEmail;
import gov.wisconsin.framework.data.base.IPrimaryKey;
import gov.wisconsin.framework.data.pojo.FwEmailMessageSubstitution;

import java.io.Serializable;

public class FwEmailCargo implements Serializable, ICargoEmail,Cloneable {
	private static final long serialVersionUID = -7372000085115250842L;
	
	private String messageId = null;
	private String rowAction = null;
	private String distributionId = null;
	private FwEmailMessageSubstitution emailMessageSubstitution; 

	@Override
	public String getUser() {
		return null; 
	}

	@Override
	public IPrimaryKey getPrimaryKey() {
		return null;
	}

	@Override
	public String inspectCargo() {
		return null;
	}
	
	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void setDirty(boolean dirty) {}
	
	@Override
	public void setUser(String newUser) {}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getRowAction() {
		return rowAction;
	}

	public void setRowAction(String rowAction) {
		this.rowAction = rowAction;
	}

	public String getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(String distributionId) {
		this.distributionId = distributionId;
	}

	public FwEmailMessageSubstitution getEmailMessageSubstitution() {
		return emailMessageSubstitution;
	}

	public void setEmailMessageSubstitution(FwEmailMessageSubstitution emailMessageSubstitution) {
		this.emailMessageSubstitution = emailMessageSubstitution;
	}

}
