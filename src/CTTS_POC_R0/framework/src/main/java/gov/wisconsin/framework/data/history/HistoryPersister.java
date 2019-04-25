package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractCollection;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.data.management.FwDAOFactory;
import gov.wisconsin.framework.data.management.FwDAOHelper;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;

public class HistoryPersister {
	
	private static final HistoryPersister persister = new HistoryPersister();

	private HistoryPersister() {}
	
	public static HistoryPersister getInstance() {
		return persister;
	}

	public FwAbstractCollection update(FwAbstractCollection aColl, Connection aConn) throws FwException {
		IDAO dao = FwDAOFactory.getDAO(aColl.getPACKAGE());
		short type = dao.getHistoryType();
		FwAbstractCollection returnColl = null;
		
		switch(type) {
			case 0:
				IHistoryPersister type0 = Type0HistoryPersister.getInstance();
				returnColl = updateType0Records(aColl, dao, type0, aConn);
				break;
			case 1: 
				IHistoryPersisterType1 type1 = Type1HistoryPersister.getInstance();
				returnColl = updateType1Records(aColl, dao, type1, aConn);
				break;				
			case 2:
				IHistoryPersisterType2 type2 = Type2HistoryPersister.getInstance();
				returnColl = updateType2Records(aColl, dao, type2, aConn);
				break;
			case 4:
				IHistoryPersisterType4 type4 = Type4HistoryPersister.getInstance();
				returnColl = updateType4Records(aColl, dao, type4, aConn);
				break;	
			case 6:
				IHistoryPersisterType6 type6 = Type6HistoryPersister.getInstance();
				returnColl = updateType6Records(aColl, dao, type6, aConn);
				break;
			default:
				FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
				fe.setServiceName(dao.getClass().getName());
				fe.setServiceMethod("update");
				fe.setMessageCode("FW010");
				FwExceptionManager.handleException(fe);
		}
		return returnColl;
	}
	
	public FwAbstractCollection update(FwAbstractCollection aColl, String aUpdateMethod, Connection aConn) {
		IDAO dao = FwDAOFactory.getDAO(aColl.getPACKAGE());
		short type = dao.getHistoryType();
		FwAbstractCollection returnColl = null;
		
		switch(type) {
			case 0:
				returnColl = updateType0Type6Records(aColl, dao, aUpdateMethod, aConn);
				break;
			case 6:
				returnColl = updateType0Type6Records(aColl, dao, aUpdateMethod, aConn);
				break;				
			default:
				FwException fe = new FwException(this.getClass(), new Exception(), FwConstants.EXP_TYP_DAO);
				fe.setServiceName(dao.getClass().getName());
				fe.setServiceMethod("update");
				fe.setMessageCode("FW006");
				FwExceptionManager.handleException(fe);
		}
		return returnColl;
	}

	private FwAbstractCollection updateType0Records(FwAbstractCollection aColl, IDAO aDao, IHistoryPersister iHistoryPersister, Connection aConn) throws FwException  {
		ICargo cargo;
		int coll_size = aColl.size();
		for(int i = 0; i < coll_size; i++) {
			cargo = (ICargo) aColl.get(i);
			iHistoryPersister.update(aDao, cargo, aConn);					
		}				
		return aColl;	
	}

	private FwAbstractCollection updateType1Records(FwAbstractCollection aColl, IDAO aDao, IHistoryPersisterType1 iHistory, Connection aConn) throws FwException  {
		IHistory history = null;
		int coll_size = aColl.size();
		for(int i = 0; i < coll_size; i++) {
			history = (IHistory) aColl.get(i);
			aColl.set(i, iHistory.update(aDao, history, aConn));			
		}				
		return aColl;	
		
	
	}

	private FwAbstractCollection updateType2Records(FwAbstractCollection aColl, IDAO aDao, IHistoryPersisterType2 iHistory, Connection aConn) {
		IHistory history = null;
		try {
			int coll_size = aColl.size();
			for(int i = 0; i < coll_size; i++) {
				history = (IHistory) aColl.get(i);
				aColl.set(i, iHistory.update(aDao, history, aConn));
			}				
			return aColl;	
		} catch(FwException fe) {
			if(history != null && history.getPrimaryKey() != null) {
				fe.setParameterText(history.getPrimaryKey().inspectPrimaryKey());
			}
			FwExceptionManager.handleException(fe);
		}
		return null;
	}

	private FwAbstractCollection updateType4Records(FwAbstractCollection aColl, IDAO aDao, IHistoryPersisterType4 iHistory, Connection aConn) {
		IHistoryType4 history = null;
		try {
			int coll_size = aColl.size();
			for(int i = 0; i < coll_size; i++) {
				history = (IHistoryType4) aColl.get(i);
				aColl.set(i, iHistory.update(aDao, history, aConn));
			}				
			return aColl;	
		}catch(FwException fe){
			if(history != null && history.getPrimaryKey() != null){
				fe.setParameterText(history.getPrimaryKey().inspectPrimaryKey());
			}
			FwExceptionManager.handleException(fe);
		}
		return null;
	}

	private FwAbstractCollection updateType0Type6Records(FwAbstractCollection aColl, IDAO aDao, String aUpdateMethod, Connection aConn) {
		ICargo cargo;
		int coll_size = aColl.size();
		for(int i = 0; i < coll_size; i++) {
			cargo = (ICargo) aColl.get(i);
			Class<?>[] params = {ICargo.class, Connection.class};
			Object[] values = {cargo, aConn};
			FwDAOHelper.executeMethod(aDao.getClass(), aUpdateMethod, params, values);
		}
		return aColl;	
	}
	
	private FwAbstractCollection updateType6Records(FwAbstractCollection aColl, IDAO aDao, IHistoryPersisterType6 iHistoryPersister, Connection aConn) {
		IHistoryType6 history = null;
		try {
			int coll_size = aColl.size();
			for(int i = 0; i < coll_size; i++) {
				history = (IHistoryType6) aColl.get(i);
				aColl.set(i, iHistoryPersister.update(aDao, history, aConn));					
			}				
			return aColl;	
		} catch(FwException fe) {
			FwExceptionManager.handleException(fe);
		}
		return null;
	}
}