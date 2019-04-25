package gov.wisconsin.framework.exception;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.impl.FwMessageList;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FwExceptionManager extends FwBaseClass {

	/**
	 * The STACKTRACE_COUNT variable is used to programmatically retrieve the currently executing method from the stacktrace.
	 * Some methods in this class modify this number based upon where we are in the stacktrace when the method is called.
	 * 						***	DO NOT MODIFY THIS VARIABLE UNLESS ABSOLUTELY NEEDED ***
	 */
	private static final int STACKTRACE_COUNT = 4;
	
	private static final String STACKTRACE_SEPARATOR = " :: ";
	private static final Logger exceptionLogger = LoggerFactory.getLogger(FwConstants.LOGGER_EXCEPTION);
	private static final String API_LOG_MESSAGE = "FwExceptionManager Logging Exception - check exception.log for more details";

	private static Map<Integer, String> exceptionTypeAndNameMapping = new HashMap<>();
	
	public static void log(Exception exception) {
		apiLogger.info(API_LOG_MESSAGE);
		
		try {
			FwWrappedException wrappedExceptionToLog = null;
			if (exception instanceof FwWrappedException) {
				wrappedExceptionToLog = (FwWrappedException) exception;
			} else if (exception instanceof FwException) {
				wrappedExceptionToLog = new FwWrappedException((FwException) exception);
			} else {
				wrappedExceptionToLog = new FwWrappedException(new FwException(exception));
			}
		
    		FwException tmp = wrappedExceptionToLog.getFwException();
    		if(tmp != null && tmp.isLoggable()) {
    			exceptionLogger.info(wrappedExceptionToLog.toString());
    		}
    	} catch(Exception e) {
    		apiLogger.info("An error occured while logging an exception - " + e.toString());
    	}
	}
	
	public static void handleValidationException(Class<?> clazz, FwMessageList messageList) {
		FwException fe = new FwException(clazz, messageList, "Server Side Validation Failed");
		fe.setStatusCode(400);
		fe.setLoggable(false);
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
    	throw fe;
    }
	
	public static void handleAuthorizationException(Class<?> clazz) {
		FwException fe = new FwException(clazz, "Logged-In User Not Authorized For Resource");
		fe.setStatusCode(401);
		fe.setLoggable(false);
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
    	throw fe;
    }
	
	public static void handleUnknownResourceException(Class<?> clazz) {
		FwException fe = new FwException(clazz, "Unknown Resource");
		fe.setStatusCode(404);
		fe.setLoggable(false);
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
    	throw fe;
    }
	
	public static void handleUnsupportedMediaException(Class<?> clazz) {
		FwException fe = new FwException(clazz, "Unsupported Media Type");
		fe.setStatusCode(415);
		fe.setLoggable(false);
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
    	throw fe;
    }
	
	public static void handleUnprocessableEntityException(Class<?> clazz) {
		FwException fe = new FwException(clazz, "Unprocessable Entity");
		fe.setStatusCode(422);
		fe.setLoggable(false);
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
    	throw fe;
    }
	
	public static void handleSQLException(Class<?> clazz, SQLException sqle, String serviceMessage) {
		FwException fe = new FwException(clazz, sqle, FwConstants.EXP_TYP_DAO);
		fe.setClassID(clazz.getName());
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
		fe.setExceptionText(sqle.getMessage());
		fe.setStackTraceValue(getStackTraceValue(fe));

		fe.setServiceName(clazz.getName());
		fe.setServiceMethod(getCurrentMethodName(STACKTRACE_COUNT));
		fe.setServiceDescription(serviceMessage);
		
    	throw fe;
	}
	
	public static void handleSQLException(Class<?> clazz, SQLException sqle, String serviceMessage, int exceptionType) {
		FwException fe = new FwException(clazz, sqle, exceptionType);
		fe.setClassID(clazz.getName());
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
		fe.setExceptionText(sqle.getMessage());
		fe.setStackTraceValue(getStackTraceValue(fe));

		fe.setServiceName(clazz.getName());
		fe.setServiceMethod(getCurrentMethodName(STACKTRACE_COUNT));
		fe.setServiceDescription(serviceMessage);
		
    	throw fe;
	}
	
	public static void handleServiceException(Class<?> clazz, Exception e, String serviceDescription) {
		FwException fe = new FwException(clazz, e, FwConstants.EXP_TYP_DAO);
		fe.setClassID(clazz.getName());
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
		fe.setExceptionText(e.getMessage());
		fe.setStackTraceValue(getStackTraceValue(fe));

		fe.setServiceName(clazz.getName());
		fe.setServiceMethod(getCurrentMethodName(STACKTRACE_COUNT));
		fe.setServiceDescription(serviceDescription);

    	throw fe;
	}
	
	public static void handleException(FwException fe) {
		if (FwConstants.EMPTY_STRING.equals(fe.getMethodID())) { fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT + 1)); }
    	if (FwConstants.EMPTY_STRING.equals(fe.getStackTraceValue())) { fe.setStackTraceValue(getStackTraceValue(fe)); }
    	
    	throw fe;
    }
    
    public static void handleException(Class<?> clazz, Exception e) {
    	String message = e.getMessage() != null ? e.getMessage() : FwConstants.EMPTY_STRING;
    	handleException(clazz, e, FwConstants.EXP_TYP_APPLICATION, message);
    }
    
    public static void handleException(Class<?> clazz, Exception e, int exceptionType) {
    	String message = e.getMessage() != null ? e.getMessage() : FwConstants.EMPTY_STRING;
    	handleException(clazz, e, exceptionType, message);
    }
    
    public static void handleException(Class<?> clazz, Exception e, String customMessage) {
    	handleException(clazz, e, FwConstants.EXP_TYP_FRAMEWORK, customMessage);
    }
    
    public static void handleException(Class<?> clazz, Exception e, int exceptionType, String customMessage) {
    	handleException(clazz, e, exceptionType, customMessage, true);
    }

    /**
	 * This method is private so that when it is called we know where that call came from and we know exactly where we are in the stacktrace
	 * and that allows us to programmatically get the currently executing method name from the stacktrace.
	 */
    private static void handleException(Class<?> clazz, Exception e, int exceptionType, String customMessage, boolean calledFromExceptionManager) {
    	FwException fe;
    	
    	if (e instanceof FwException) {
    		fe = (FwException) e;
    	} else {
    		fe = new FwException(clazz, e, exceptionType, customMessage);
    	}

    	throw fe;
    }
    
	public static String getStackTraceValue(Exception e) {
		return getStackTraceValue(e.getStackTrace());
	}
	
	public static String getStackTraceValue(StackTraceElement[] elements) {
		StringBuffer traceValue = new StringBuffer("Stack Trace: ");
		Arrays.stream(elements).forEach(ele -> traceValue.append(ele).append(STACKTRACE_SEPARATOR));
		return traceValue.toString();
	}
	
	public static String getExceptionNameByType(int exceptionType){
		String result = exceptionTypeAndNameMapping.get(exceptionType);
		return result != null ? result : "Unknown";
	}
	
	public static FwException createFwException(String className, Exception e) {
		FwException fe = new FwException();
		fe.setClassID(className);
		fe.setMethodID(getCurrentMethodName(STACKTRACE_COUNT));
		fe.setStackTraceValue(getStackTraceValue(e));
		fe.setExceptionText(String.valueOf(e));
		fe.setExceptionType(FwConstants.EXP_TYP_APPLICATION);
		return fe;
	}
	
	public static FwException createFwException(String className, String methodName, Exception e) {
		FwException fe = new FwException();
		fe.setClassID(className);
		fe.setMethodID(methodName);
		fe.setStackTraceValue(getStackTraceValue(e));
		fe.setExceptionText(String.valueOf(e));
		fe.setExceptionType(FwConstants.EXP_TYP_APPLICATION);
		return fe;
	}
	
	private static String getCurrentMethodName(int stacktraceLevel) {
		return Thread.currentThread().getStackTrace()[stacktraceLevel].getMethodName();
    }
	
	static {
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_DAO,             "DAO");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_XML,             "XML");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_LDAP,            "LDAP");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_BATCH,           "Batch");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_SECURITY,        "Security");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_TRANSFER,        "Transfer");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_FRAMEWORK,       "Framework");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_MESSAGING,       "Messaging");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_CUSTOM_TAG,      "Custom Tag");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_TRANSACTION,     "Transaction");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_APPLICATION,     "Application");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_WEBSERVICE,      "Web Service");
		exceptionTypeAndNameMapping.put(FwConstants.EXP_TYP_STOREDPROCEDURE, "Stored Procedure");
	}
}
