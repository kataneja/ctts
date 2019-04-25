package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.data.pojo.FwEmailMessageSubstitution;

public interface ICargoEmail extends ICargo {
	
	public String getMessageId();
	
	public String getDistributionId();
	
	public FwEmailMessageSubstitution getEmailMessageSubstitution();
	
}
