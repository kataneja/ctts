package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.data.pojo.FwEmailMessage;
import gov.wisconsin.framework.exception.FwException;

import java.util.List;

public interface IEmail {

	public String performSubstitution(String message, String[] substitute);
	
	public void handleEmail(FwAbstractCollection coll);
	
	public void sendEmailMessage(List<FwEmailMessage> emailMessageList) throws FwException;
}
