package gov.wisconsin.framework.transport;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwWrappedException;

public class FwErrorResponse extends FwResponse {
	
	public FwErrorResponse(String message) {
		this(new FwException(message));
	}
	
	public FwErrorResponse(FwWrappedException fwWrappedException) {
		FwException internalException = fwWrappedException.getFwException();
		this.exceptionClass = internalException.getClassID();
		this.exceptionMethod = internalException.getMethodID();
		this.exceptionText = internalException.getOriginalException().getClass().getCanonicalName();
		
		if (internalException.getCustomMessage().equals(FwConstants.EMPTY_STRING)) {
			this.errorMessage = internalException.getExceptionText();
		} else {
			this.errorMessage = internalException.getCustomMessage();			
		}
	}
	
	public FwErrorResponse(FwException fwException) {
		this.exceptionClass = fwException.getClassID();
		this.exceptionMethod = fwException.getMethodID();
		this.exceptionText = fwException.getOriginalException().getClass().getCanonicalName();
		
		if (fwException.getCustomMessage().equals(FwConstants.EMPTY_STRING)) {
			this.errorMessage = fwException.getExceptionText();
		} else {
			this.errorMessage = fwException.getCustomMessage();			
		}
	}
}
