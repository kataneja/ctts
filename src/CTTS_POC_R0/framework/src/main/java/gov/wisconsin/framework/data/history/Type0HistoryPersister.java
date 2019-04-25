package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.data.management.FwDAOHelper;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;

public class Type0HistoryPersister implements IHistoryPersister {
	
	Class<?>[] params = {ICargo.class, Connection.class};
	private static final IHistoryPersister persister = new Type0HistoryPersister();
	
	private Type0HistoryPersister(){}

	public static IHistoryPersister getInstance(){
		//METHOD DOES NOT NEED TO BE SYNCHRONIZED AS INSTANCE IS OBTAINED STATICALLY IN CLASS DECLARATION
		//DO NOT MODIFY CODE TO MAKE METHOD SYNCHRONIZED OR USE DOUBLE-LOCKING AS DOUBLE-LOCKING IS NOT THREAD-SAFE CURRENTLY
		return persister;	
	}

	public boolean update(IDAO aDao, ICargo aCargo, Connection aConn) throws FwException {
	
		Class<? extends IDAO> thedao = aDao.getClass();
		Object[] values = {aCargo, aConn};
		if(aCargo.getRowAction().equals(FwConstants.ROWACTION_DELETE)) {
			FwDAOHelper.executeMethod(thedao, IDAO.DELETE, params, values);
		} else if(aCargo.getRowAction().equals(FwConstants.ROWACTION_UPDATE)) {	
			FwDAOHelper.executeMethod(thedao, IDAO.UPDATE, params, values);							
		} else if(aCargo.getRowAction().equals(FwConstants.ROWACTION_INSERT)){
			FwDAOHelper.executeMethod(thedao, IDAO.INSERT, params, values);
		} else {
			FwExceptionManager.handleServiceException(this.getClass(), new Exception("Row Action Not Supported"), "Row Action Not Supported");
		}
		return true;	
	}
}