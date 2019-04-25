package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.data.management.FwDAOHelper;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;

public class Type1HistoryPersister implements IHistoryPersisterType1 {

	Class<?>[] params = {IHistory.class, Connection.class};
	private static final IHistoryPersisterType1 persister = new Type1HistoryPersister();

	private Type1HistoryPersister() {}
	
	public static IHistoryPersisterType1 getInstance() {
		return persister;	
	}

	public ICargo update(IDAO aDao, IHistory aCargo, Connection aConn) {
		Class<? extends IDAO> thedao = aDao.getClass();
		short hsn = 1;
		Object[] update_delete_parms = {aCargo, aConn};
		if(aCargo.getHistory_seq_num() > 0) {
			hsn = (short) (aCargo.getHistory_seq_num()+1);
			try {
				FwDAOHelper.executeMethod(thedao, IDAO.UPDATE, params, update_delete_parms);
			} catch(FwException fe) {
				if(fe.getServiceMessage().indexOf(IDAO.NO_REC_UPDATED) != -1) {
					fe.setMessageCode("FW051");
				}
				FwExceptionManager.handleException(fe);
			}
		}
		aCargo.setHistory_seq_num(hsn);
		Object[] insert_parms = {aCargo, aConn};
		try {
			FwDAOHelper.executeMethod(thedao, IDAO.INSERT, params, insert_parms);
		} catch(FwException fe) {
			if(fe.getExceptionText().indexOf("DuplicateKeyException") != -1) {
				fe.setMessageCode("FW051");
			}
			FwExceptionManager.handleException(fe);				
		}
		return aCargo;														
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