package gov.wisconsin.framework.exception;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.impl.FwMessageList;

public class FwException extends RuntimeException {
	private static final long serialVersionUID = 9190982399125799573L;

	private int statusCode = 500;
	private int exceptionType = -1;
	
	private boolean isLoggable = true;
	private boolean showFullExp = true;
	private boolean isLoggedToDB = false;

	private FwMessageList messageList = null;
	private Exception originalException = null;

	private String classID = FwConstants.EMPTY_STRING;
	private String methodID = FwConstants.EMPTY_STRING;
	private String messageCode = FwConstants.EMPTY_STRING;
	private String messageText = FwConstants.EMPTY_STRING;
	private String serviceName = FwConstants.EMPTY_STRING;
	private String customMessage = FwConstants.EMPTY_STRING;
	private String serviceMethod = FwConstants.EMPTY_STRING;
	private String exceptionText = FwConstants.EMPTY_STRING;
	private String parameterText = FwConstants.EMPTY_STRING;
	private String serviceMessage = FwConstants.EMPTY_STRING;
	private String stackTraceValue = FwConstants.EMPTY_STRING;
	private String serviceDescription = FwConstants.EMPTY_STRING;

	public FwException() {}
	
	public FwException(String customMessage) {
		super(customMessage);
		this.parameterText = customMessage;
	}
	
	public FwException(Class<?> clazz, Exception e) {
		this(clazz, e, FwConstants.EXP_TYP_APPLICATION, FwConstants.EMPTY_STRING);
	}
	
	public FwException(Class<?> clazz, Exception e, int exceptionType) {
		this(clazz, e, exceptionType, FwConstants.EMPTY_STRING);
	}
	
	public FwException(Class<?> clazz, Exception e, String customMessage) {
		this(clazz, e, FwConstants.EXP_TYP_APPLICATION, customMessage);
	}

	public FwException(Class<?> clazz, String customMmessage) {
		this(clazz, new Exception(customMmessage), FwConstants.EXP_TYP_APPLICATION, customMmessage);
	}
	
	public FwException(Class<?> clazz, FwMessageList messageList, String message) {
		this(clazz, new Exception(message), FwConstants.EXP_TYP_APPLICATION, message);
		messageList.performMessageSubstitution();
		this.messageList = messageList;
	}
	
	public FwException(Class<?> clazz, Exception e, int exceptionType, String customMessage) {
		super(customMessage);
		this.originalException = e;
		this.classID = clazz.getName();
		this.customMessage = customMessage;
		this.parameterText = customMessage;
		this.exceptionType = exceptionType;
		this.exceptionText = e.getMessage() == null ? customMessage : e.getMessage();
		this.methodID = getCARESMethodFromException(e);
    	this.stackTraceValue = FwExceptionManager.getStackTraceValue(e);
	}
	
	public FwException(Exception e) {
		StackTraceElement exceptionSource = e.getStackTrace()[0];
		
		this.originalException = e;
		this.exceptionType = FwConstants.EXP_TYP_APPLICATION;
		this.exceptionText = e.getMessage() == null ? FwConstants.EMPTY_STRING : e.getMessage();
		this.classID = exceptionSource.getClassName();
		this.methodID = exceptionSource.getMethodName();
    	this.stackTraceValue = FwExceptionManager.getStackTraceValue(e);
	}

	public String toXMLString() {
		StringBuffer temp = new StringBuffer();
		
		temp.append("\n\t<classID>").append(classID).append("</classID>");
		temp.append("\n\t<methodID>").append(methodID).append("</methodID>");
		temp.append("\n\t<exceptionText>").append(exceptionText).append("</exceptionText>");
		temp.append("\n\t<exceptionType>").append(exceptionType).append("</exceptionType>");
		temp.append("\n\t<messageCode>").append(messageCode).append("</messageCode>");
		temp.append("\n\t<messageText>").append(messageText).append("</messageText>");
		temp.append("\n\t<serviceName>").append(serviceName).append("</serviceName>");
		temp.append("\n\t<serviceMethod>").append(serviceMethod).append("</serviceMethod>");
		temp.append("\n\t<serviceDescription>").append(serviceDescription).append("</serviceDescription>");
		temp.append("\n\t<serviceMessage>").append(serviceMessage).append("</serviceMessage>");
		temp.append("\n\t<parameterText>").append(parameterText).append("</parameterText>");
		temp.append("\n\t<stackTraceValue>").append(repMsgChars(stackTraceValue)).append("</stackTraceValue>");
		
		return temp.toString(); 
	}	
	
	public String toQueueMsgString() {
		StringBuffer temp = new StringBuffer();
		
		temp.append("\n\t<classID>").append(repMsgChars(classID)).append("</classID>");
		temp.append("\n\t<methodID>").append(repMsgChars(methodID)).append("</methodID>");
		temp.append("\n\t<execeptionText>").append(repMsgChars(exceptionText)).append("</execeptionText>");
		temp.append("\n\t<exceptionType>").append(exceptionType).append("</exceptionType>");
		temp.append("\n\t<messageCode>").append(repMsgChars(messageCode)).append("</messageCode>");
		
		return temp.toString(); 
	}	
	
	private String repMsgChars(String string) {
		if (string != null) {
			string = string.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;").replace("\"", "&quot;");
		}
		return string;
	}
	
	private String getCARESMethodFromException(Exception e) {
		StackTraceElement[] stacktraceElements = e.getStackTrace();
		
		for (StackTraceElement element : stacktraceElements) {
			if (element.getClassName().startsWith("gov.wisconsin")) {
				return element.getMethodName();
			}
		}
		
		return FwConstants.EMPTY_STRING;
	}
	
	public int getExceptionType() {
		return exceptionType;
	}
	
	public void setExceptionType(int exceptionType) {
		this.exceptionType = exceptionType;
	}
	
	public boolean isShowFullExp() {
		return showFullExp;
	}
	
	public void setShowFullExp(boolean showFullExp) {
		this.showFullExp = showFullExp;
	}
	
	public boolean isLoggable() {
		return isLoggable;
	}
	
	public void setLoggable(boolean isLoggable) {
		this.isLoggable = isLoggable;
	}
	
	public String getClassID() {
		return classID;
	}
	
	public void setClassID(String classID) {
		this.classID = classID;
	}
	
	public String getMethodID() {
		return methodID;
	}
	
	public void setMethodID(String methodID) {
		this.methodID = methodID;
	}
	
	public String getMessageCode() {
		return messageCode;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
	public String getMessageText() {
		return messageText;
	}
	
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceMethod() {
		return serviceMethod;
	}
	
	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}
	
	public String getExceptionText() {
		return exceptionText;
	}
	
	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}
	
	public String getParameterText() {
		return parameterText;
	}
	
	public void setParameterText(String parameterText) {
		this.parameterText = parameterText;
	}
	
	public String getServiceMessage() {
		return serviceMessage;
	}
	
	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}
	
	public String getStackTraceValue() {
		return stackTraceValue;
	}
	
	public void setStackTraceValue(String stackTraceValue) {
		this.stackTraceValue = stackTraceValue;
	}
	
	public String getServiceDescription() {
		return serviceDescription;
	}
	
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	
	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	public Exception getOriginalException() {
		return originalException;
	}

	public void setOriginalException(Exception originalException) {
		this.originalException = originalException;
	}
	
	public FwMessageList getMessageList() {
		return messageList;
	}

	public void setMessageList(FwMessageList messageList) {
		this.messageList = messageList;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public boolean isLoggedToDB() {
		return isLoggedToDB;
	}

	public void setLoggedToDB(boolean isLoggedToDB) {
		this.isLoggedToDB = isLoggedToDB;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("FwException [ ");
		result.append("classID = ").append(classID).append(", ");
		result.append("methodID = ").append(methodID).append(", ");
		
		if (originalException != null) {
			result.append("cause = ").append(originalException.getClass().getCanonicalName()).append(", ");
		}
		
		result.append("exceptionText = ").append(exceptionText).append(", ");
		result.append("customMessage = ").append(customMessage).append(", ");
		result.append("statusCode = ").append(statusCode).append(", ");
		result.append("exceptionType = ").append(FwExceptionManager.getExceptionNameByType(exceptionType)).append(", ");
		result.append("isLoggable = ").append(isLoggable).append(", ");
		result.append("showFullExp = ").append(showFullExp).append(", ");
		result.append("messageCode = ").append(messageCode).append(", ");
		result.append("messageText = ").append(messageText).append(", ");
		result.append("serviceName = ").append(serviceName).append(", ");
		result.append("serviceMethod = ").append(serviceMethod).append(", ");
		result.append("parameterText = ").append(parameterText).append(", ");
		result.append("serviceMessage = ").append(serviceMessage).append(", ");
		result.append("serviceDescription = ").append(serviceDescription).append(", ");
		result.append("stackTraceValue = ").append(stackTraceValue).append("]");
		
		return result.toString();
	}
}
