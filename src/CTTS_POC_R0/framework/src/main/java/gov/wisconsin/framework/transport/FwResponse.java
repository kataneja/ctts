package gov.wisconsin.framework.transport;

import gov.wisconsin.framework.exception.FwException;

public abstract class FwResponse {
	protected boolean status;
	protected String errorMessage;
	protected String exceptionText;
	protected String exceptionClass;
	protected String exceptionMethod;
	protected FwException fwException;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getExceptionText() {
		return exceptionText;
	}

	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}

	public String getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

	public String getExceptionMethod() {
		return exceptionMethod;
	}

	public void setExceptionMethod(String exceptionMethod) {
		this.exceptionMethod = exceptionMethod;
	}

	public FwException getFwException() {
		return fwException;
	}

	public void setFwException(FwException fwException) {
		this.fwException = fwException;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
