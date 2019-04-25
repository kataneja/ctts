package gov.wisconsin.framework.data.management;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FwDAOHelper {

	public static Method getMethod(Class<?> aClass, String aMethodName, Class<?>[] aParams) throws FwException {
		try {
			return aClass.getMethod(aMethodName, aParams);
		} catch(Exception e) {
			if (e instanceof InvocationTargetException) {
				Throwable t = ((InvocationTargetException) e).getTargetException();
				if (t instanceof FwException) {
					FwExceptionManager.handleException((FwException) t);
				} else {
					FwException fe = new FwException(FwDAOHelper.class, e, FwConstants.EXP_TYP_DAO);
					fe.setServiceName(aClass.getName());
					fe.setServiceMethod(aMethodName);
					FwExceptionManager.handleException(fe);	
				}	
			}  else {
				FwException fe = new FwException(FwDAOHelper.class, e, FwConstants.EXP_TYP_DAO);
				fe.setServiceName(aClass.getName());
				fe.setServiceMethod(aMethodName);
				FwExceptionManager.handleException(fe);	
			}					
		}
		
		return null;
	}

	public static Object invokeMethod(Class<?> aClass, Method aMethod, Object[] aParams) throws FwException {
		try {
			return aMethod.invoke(aClass.newInstance(), aParams);
		} catch(Exception e) {
			if (e instanceof InvocationTargetException) {
				Throwable t = ((InvocationTargetException) e).getTargetException();
				if (t instanceof FwException) {
					FwExceptionManager.handleException((FwException) t);
				} else {
					FwException fe = new FwException(FwDAOHelper.class, e, FwConstants.EXP_TYP_DAO);
					fe.setServiceName(aClass.getName());
					fe.setServiceMethod(aMethod.getName());
					FwExceptionManager.handleException(fe);	
				}	
			}  else {
				FwException fe = new FwException(FwDAOHelper.class, e, FwConstants.EXP_TYP_DAO);
				fe.setServiceName(aClass.getName());
				fe.setServiceMethod(aMethod.getName());
				FwExceptionManager.handleException(fe);	
			}					
		}
		
		return null;
	}
	
	public static Object executeMethod(Class<?> aClass, String aMethodName, Class<?>[] aParams, Object[] aParamValues) throws FwException {
		return invokeMethod(aClass, getMethod(aClass, aMethodName, aParams), aParamValues);		
	}
}
