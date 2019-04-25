package gov.wisconsin.framework.data.management;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FwDAOFactory {

	private static final String DAO_SUFFIX       = "_DAO";
	private static final String DAO_PACKAGE      = "impl";
	private static final String POJO_PACKAGE     = "pojo";
	private Map<String, Object> map = Collections.synchronizedMap(new HashMap<String, Object>());

	private static FwDAOFactory factory = new FwDAOFactory();

	public static FwDAOFactory getFactory() { 
		return factory;
	}

	public static IDAO getDAO(String persisterName) throws FwException {
		return getFactory().getDAOClass(persisterName);
	}

	private IDAO getDAOClass(String persister) throws FwException {
		IDAO theDAO = null;
		try {
			Class<?> theClass = (Class<?>) map.get(persister);
			if (theClass == null) {
				if (!persister.endsWith(DAO_SUFFIX)) {
					persister = persister + DAO_SUFFIX;
				}
				String className = substituteDB2PackageName(persister);
				theClass = Class.forName(className);
				map.put(persister, theClass);
			}
			theDAO = (IDAO) theClass.newInstance();
			return theDAO;
		} catch (ClassNotFoundException cnfe) {
			FwException fe = new FwException(this.getClass(), cnfe, FwConstants.EXP_TYP_DAO);
			fe.setServiceName(persister);
			FwExceptionManager.handleException(fe);
		} catch (InstantiationException ie) {
			FwException fe = new FwException(this.getClass(), ie, FwConstants.EXP_TYP_DAO);
			fe.setServiceName(persister);
			FwExceptionManager.handleException(fe);			
		} catch(IllegalAccessException iae){
			FwException fe = new FwException(this.getClass(), iae, FwConstants.EXP_TYP_DAO);
			fe.setServiceName(persister);
			FwExceptionManager.handleException(fe);				
		}
		return null;
	}

	private String substituteDB2PackageName(String aName) {
		int startPosition = aName.indexOf(POJO_PACKAGE);
		if (startPosition != -1) {
			StringBuffer daoName = new StringBuffer(aName.substring(0, startPosition));
			daoName.append(DAO_PACKAGE);
			daoName.append(aName.substring(startPosition + POJO_PACKAGE.length()));	
			return daoName.toString();
		}
		else {
			return aName;
		}
	}	
}