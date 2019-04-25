package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.data.management.FwDAOHelper;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;

public class Type6HistoryPersister implements IHistoryPersisterType6 {

	private static final Class<?>[] params = {ICargo.class, Connection.class};
	private static final IHistoryPersisterType6 persister = new Type6HistoryPersister();

	private Type6HistoryPersister() {}
	
	public static IHistoryPersisterType6 getInstance(){
		//METHOD DOES NOT NEED TO BE SYNCHRONIZED AS INSTANCE IS OBTAINED STATICALLY IN CLASS DECLARATION
		//DO NOT MODIFY CODE TO MAKE METHOD SYNCHRONIZED OR USE DOUBLE-LOCKING AS DOUBLE-LOCKING IS NOT THREAD-SAFE CURRENTLY
		return persister;	
	}
	
	public ICargo update(IDAO aDao, IHistoryType6 aCargo, Connection aConn) {
		Class<? extends IDAO> thedao = aDao.getClass();
		Object[] values = {aCargo, aConn};
		if(aCargo.getRowAction().equals(FwConstants.ROWACTION_DELETE)) {
			FwDAOHelper.executeMethod(thedao, IDAO.DELETE,params, values);
		} else if(aCargo.getRowAction().equals(FwConstants.ROWACTION_INSERT)) {
			short seq_num=1;
			IHistoryType6 result=null;
			Object obj = FwDAOHelper.executeMethod(thedao, IDAO.FINDBY_MAXSEQ, params, values);
			if(obj instanceof IHistoryType6) {
				result=(IHistoryType6)obj;
				seq_num = Short.parseShort(result.getSeq_num());
				seq_num++;
			}
			aCargo.setSeq_num(String.valueOf(seq_num));
			FwDAOHelper.executeMethod(thedao, IDAO.INSERT, params, values);
		} else if (aCargo.getRowAction().equals(FwConstants.ROWACTION_UPDATE)) {
			FwDAOHelper.executeMethod(thedao, IDAO.UPDATE, params, values);
		} else {
			FwExceptionManager.handleServiceException(this.getClass(), new Exception("Row Action Not Supported"), "Row Action Not Supported");
		}
		return aCargo;
	}

	public boolean update(IDAO aDao, ICargo aCargo, Connection aConn) {
		FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
		fe.setServiceName(aDao.getClass().getName());
		fe.setServiceMethod("update");
		fe.setMessageCode("FW009");
		FwExceptionManager.handleException(fe);
		return false;
	}
}