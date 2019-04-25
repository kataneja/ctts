package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.data.management.FwDAOHelper;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;

public class Type4HistoryPersister implements IHistoryPersisterType4 {
	
	private Class<?>[] params = {IHistory.class, Connection.class};
	private static final IHistoryPersisterType4 persister = new Type4HistoryPersister();

	private Type4HistoryPersister() {}
	
	public static IHistoryPersisterType4 getInstance(){
		//METHOD DOES NOT NEED TO BE SYNCHRONIZED AS INSTANCE IS OBTAINED STATICALLY IN CLASS DECLARATION
		//DO NOT MODIFY CODE TO MAKE METHOD SYNCHRONIZED OR USE DOUBLE-LOCKING AS DOUBLE-LOCKING IS NOT THREAD-SAFE CURRENTLY
		return persister;	
	}

	public ICargo update(IDAO aDao, IHistoryType4 aCargo, Connection aConn) {
		Class<? extends IDAO> thedao = aDao.getClass();
		short seq_num=1;			
		short hsn=1; 
		if(aCargo.getSeq_num() == 0) {
			if(aCargo.getHistory_seq_num() > 0) {
				FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
				fe.setServiceName(aDao.getClass().getName());
				fe.setServiceMethod("update");
				fe.setMessageCode("FW048");
				FwExceptionManager.handleException(fe);
			} 
			if(aCargo.getHistory_cd() != 0) {
				FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
				fe.setServiceName(aDao.getClass().getName());
				fe.setServiceMethod("update");
				fe.setMessageCode("FW053");
				FwExceptionManager.handleException(fe);
			}	
			
			Object[] parms = {aCargo, aConn};
			Object obj = FwDAOHelper.executeMethod(thedao, IDAO.FINDBY_MAXSEQ,params, parms);	
					
			IHistoryType4 result=null;			
			if(obj instanceof IHistoryType4) {
				result=(IHistoryType4)obj;
				seq_num=result.getSeq_num();
				seq_num++;
			}
			aCargo.setHistory_seq_num(hsn);
			aCargo.setSeq_num(seq_num);
			Object[] insert_parms = {aCargo, aConn};
			FwDAOHelper.executeMethod(thedao, IDAO.INSERT,params, insert_parms);
			return aCargo;	
                                                                                                             					
		} else if(aCargo.getHistory_seq_num() > 0){
			if(aCargo.getHistory_cd() == 0){
				// update logic
				Object[] update_parms = {aCargo, aConn};
				try {
					FwDAOHelper.executeMethod(thedao, IDAO.UPDATE,params, update_parms);
				} catch(FwException fe){
					if(fe.getServiceMessage().indexOf(IDAO.NO_REC_UPDATED) != -1){
						fe.setMessageCode("FW051");
					}
					FwExceptionManager.handleException(fe);					
				}						
				hsn=(short)(aCargo.getHistory_seq_num()+1);

				// insert for update logic
				aCargo.setHistory_seq_num(hsn);
				Object[] insert_parms = {aCargo,aConn};
				try {
					FwDAOHelper.executeMethod(thedao, IDAO.INSERT,params, insert_parms);
				} catch(FwException fe){
					if(fe.getExceptionText().indexOf("DuplicateKeyException") != -1){
						fe.setMessageCode("FW051");
					}
					FwExceptionManager.handleException(fe);
				}						
				return aCargo;
			}
		}
		FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
		fe.setServiceName(aDao.getClass().getName());
		fe.setServiceMethod("update");
		fe.setMessageCode("FW005");
		FwExceptionManager.handleException(fe);
		return null;
	}	

	public ICargo update(IDAO aDao, IHistory aCargo, Connection aConn) {
		FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
		fe.setServiceName(aDao.getClass().getName());
		fe.setServiceMethod("update");
		fe.setMessageCode("FW049");
		FwExceptionManager.handleException(fe);
		return null;
	}

	public ICargo update(IDAO aDao, IHistory aCargo, String aMethodName, Connection aConn) {
		FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
		fe.setServiceName(aDao.getClass().getName());
		fe.setServiceMethod("update");
		fe.setMessageCode("FW006");
		FwExceptionManager.handleException(fe);
		return null;
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
