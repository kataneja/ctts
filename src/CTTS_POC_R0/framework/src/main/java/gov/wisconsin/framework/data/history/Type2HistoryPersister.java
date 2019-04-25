package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.data.management.FwDAOHelper;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;

public class Type2HistoryPersister implements IHistoryPersisterType2 {

	Class<?>[] params = {IHistory.class, Connection.class};
	private static final IHistoryPersisterType2 persister = new Type2HistoryPersister();

	private Type2HistoryPersister() {}
	
	public static IHistoryPersisterType2 getInstance(){
		//METHOD DOES NOT NEED TO BE SYNCHRONIZED AS INSTANCE IS OBTAINED STATICALLY IN CLASS DECLARATION
		//DO NOT MODIFY CODE TO MAKE METHOD SYNCHRONIZED OR USE DOUBLE-LOCKING AS DOUBLE-LOCKING IS NOT THREAD-SAFE CURRENTLY
		return persister;	
	}
	
	public ICargo update(IDAO aDao, IHistory aCargo, Connection aConn) {
		short hsn = 1;
		Class<? extends IDAO> thedao = aDao.getClass();

		if(aCargo.getHistory_seq_num() >  0) {
			Object[] updateparam = {aCargo, aConn};
			try {
				FwDAOHelper.executeMethod(thedao, IDAO.UPDATE_T2_HISTORY, params, updateparam);
			} catch(FwException fe) {
				if(fe.getServiceMessage().indexOf(IDAO.NO_HISTORY_REC_INSERTED) != -1){
					fe.setMessageCode("FW051");
				}
				FwExceptionManager.handleException(fe);;
			}				
			hsn = (short) (aCargo.getHistory_seq_num()+1);
		}
		aCargo.setHistory_seq_num(hsn);
		Object[] insert_delete_parms = {aCargo, aConn};
		if(hsn != 1) {
			FwDAOHelper.executeMethod(thedao, IDAO.UPDATE, params, insert_delete_parms);												
		} else {
			try {
				FwDAOHelper.executeMethod(thedao, IDAO.INSERT, params, insert_delete_parms);
			} catch(FwException fe) {
				if(fe.getExceptionText().indexOf("DuplicateKeyException") != -1) {
					fe.setMessageCode("FW051");
				}
				FwExceptionManager.handleException(fe);					
			}															
		}
		return aCargo;	
	}	

	public ICargo update(IDAO aDao, IHistory aCargo, String aMethodName, Connection aConn) {
		short hsn = 1;
		Class<? extends IDAO> thedao = aDao.getClass();

		if(aCargo.getHistory_seq_num() >  0) {
			Object[] updateparam = {aCargo, aConn};
			try {
				FwDAOHelper.executeMethod(thedao, IDAO.UPDATE_T2_HISTORY, params, updateparam);
			} catch(FwException fe) {
				if(fe.getServiceMessage().indexOf(IDAO.NO_HISTORY_REC_INSERTED) != -1){
					fe.setMessageCode("FW051");
				}
				FwExceptionManager.handleException(fe);
			}
			hsn = (short)(aCargo.getHistory_seq_num()+1);
			aCargo.setHistory_seq_num(hsn);
			Object[] insert_update_delete_parms = {aCargo, aConn};
			if(hsn != 1) {
				FwDAOHelper.executeMethod(thedao, aMethodName, params, insert_update_delete_parms);
				return aCargo;												
			}
		}
		FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
		fe.setServiceName(aDao.getClass().getName());
		fe.setServiceMethod("update");
		fe.setMessageCode("FW008");
		FwExceptionManager.handleException(fe);
		return null;
	}	

	public boolean update(IDAO aDao, ICargo aCargo, boolean aDelete, Connection aConn) {
		FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
		fe.setServiceName(aDao.getClass().getName());
		fe.setServiceMethod("update");
		fe.setMessageCode("FW009");
		FwExceptionManager.handleException(fe);
		return false;
	}	
	
	public boolean update(IDAO aDao, ICargo aCargo, Connection aConn) {
		return update(aDao,aCargo,false,aConn);
	}	
}