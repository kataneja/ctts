package gov.wisconsin.framework.log;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.impl.FwRequestWrapper;

import java.util.Enumeration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FwAspectLogger extends FwBaseClass {

	private static final String LOG_SEPARATOR = " :: ";
	
    @Before("execution(* gov.wisconsin.framework.controller..*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
    	logMethodAction("Start", joinPoint);
    }
    
    @AfterReturning("execution(* gov.wisconsin.framework.controller..*.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
    	logMethodAction("End  ", joinPoint);
    }
    
    private int logMethodAction(String action, JoinPoint joinPoint) {
    	StringBuilder logMessage = new StringBuilder("MethodAction" + LOG_SEPARATOR);
    	
    	logMessage.append(getExecutingClassAndMethod(joinPoint) + LOG_SEPARATOR + action + LOG_SEPARATOR);
    	
    	getRequestSpecificData(joinPoint, logMessage);
    	
    	apiLogger.info(logMessage.toString());
    	return 1;
    }
    
    private void getRequestSpecificData(JoinPoint joinPoint, StringBuilder logMessage) {
    	FwRequestWrapper wrapper = null;
    	Object[] methodArguments = joinPoint.getArgs();
    	
    	if (methodArguments.length > 1) {
    		try {
    			wrapper = (FwRequestWrapper) methodArguments[0];
    		} catch (Exception e1) {
    			try {
    				wrapper = (FwRequestWrapper) methodArguments[1];
    			} catch (Exception e2) {
    				try {
        				wrapper = (FwRequestWrapper) methodArguments[2];
        			} catch (Exception e3) {}
    			}
    		}
    		
    		if (wrapper != null) {
    			try {
    		    	Enumeration<String> headerNames = wrapper.getHeaderNames();
    		    	while (headerNames.hasMoreElements()) {
    		    		String name = headerNames.nextElement();
    		    		if (name.equals("WID")) {
    		    			logMessage.append("WID=" + wrapper.getHeader(name) + LOG_SEPARATOR);
    		    		}
    		    		if (name.equals("userID")) {
    		    			logMessage.append("userID=" + wrapper.getHeader(name) + LOG_SEPARATOR);
    		    		}
    		    	}
    		    	
    		    	Object requestID = wrapper.getAttribute(FwConstants.PAYLOAD_REQUESTID);
    		    	if (requestID != null) {
    		    		logMessage.append(FwConstants.PAYLOAD_REQUESTID + "=" + requestID);
    		    	}
        		} catch (Exception e) {}
    		}
    	}
    }
    
    private String getExecutingClassAndMethod(JoinPoint joinPoint) {
    	String longClassString = joinPoint.getTarget().getClass().toString();
    	return longClassString.substring(longClassString.lastIndexOf(".") + 1) + LOG_SEPARATOR + joinPoint.getSignature().getName();
    }
}