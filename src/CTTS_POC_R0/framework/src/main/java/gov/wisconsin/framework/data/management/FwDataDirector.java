package gov.wisconsin.framework.data.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractCollection;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.ICargoMSG;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.data.base.ISQL;
import gov.wisconsin.framework.data.history.HistoryPersister;
import gov.wisconsin.framework.data.pojo.FwDataCriteria;
import gov.wisconsin.framework.data.pojo.FwDataSortOrder;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class FwDataDirector extends FwBaseClass {

	protected ISQL sqlManager; //@Autowired

	protected abstract Connection getConnection() throws FwException;

	private void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		}			
	}

	public FwAbstractCollection persist(String aPersist_type, Map<String, Object> aData) {
		if(aPersist_type.equals(FwConstants.DAO)) {
			FwAbstractCollection coll = (FwAbstractCollection) aData.get(FwConstants.COLLECTION);
			return persist(coll);
		}
		FwException fe = new FwException(this.getClass(), new Exception());
		fe.setMessageCode("FW012");
		FwExceptionManager.handleException(fe);
		
		return null;
	}

	public FwAbstractCollection persist(String aPersistType, Map<String, Object> aData, String aUpdateMethod) {
		if(aPersistType.equals(FwConstants.DAO)) {
			FwAbstractCollection coll = (FwAbstractCollection) aData.get(FwConstants.COLLECTION);
			return persist(coll, aUpdateMethod);
		}
		FwException fe = new FwException(this.getClass(), new Exception());
		fe.setMessageCode("FW012");
		FwExceptionManager.handleException(fe);
		
		return null;		
	}

	public ICargo[] select(String aPersist_type, Map<String, Object> aData) throws FwException {
		if(aPersist_type.equals(FwConstants.DAO)) {
			return daoSelect(aData);
		}
		FwException fe = new FwException(this.getClass(), new Exception());
		fe.setMessageCode("FW013");
		FwExceptionManager.handleException(fe);
		
		return null;
	}

	public ICargo[] select(String aPersistType, String aFinder, FwAbstractCollection aCollection) {
		if(aPersistType.equals(FwConstants.DAO)) {
			return daoSelect(aFinder, aCollection);
		}
		FwException fe = new FwException(this.getClass(), new Exception());
		fe.setMessageCode("FW013");
		FwExceptionManager.handleException(fe);
		
		return null;
	}

	public List<Map<String, Object>> execute(String aPersistType, Map<String, Object> aData) throws FwException {
		if(aPersistType.equals(FwConstants.SQL)) {
			return sqlSelect(aData);
		}
		
		FwException fe = new FwException(this.getClass(), new Exception());
		fe.setMessageCode("FW014");
		FwExceptionManager.handleException(fe);
		
		return null;
	}

	private List<Map<String, Object>> sqlSelect(Map<String, Object> aData){
		Connection conn = null;
		try {
			conn = getConnection();
			return sqlManager.select(aData, conn);
		} finally {
			closeConnection(conn);
		}
	}

	private FwAbstractCollection persist(FwAbstractCollection aColl) throws FwException {
		Connection conn = null;
		try {
			conn = getConnection();	
			HistoryPersister hp = HistoryPersister.getInstance();
			return hp.update(aColl, conn);
		} finally {
			closeConnection(conn);
		}
	}

	private FwAbstractCollection persist(FwAbstractCollection aColl, String aUpdateMethod) {
		Connection conn = null;
		try {
			HistoryPersister hp = HistoryPersister.getInstance();
			conn = getConnection();	
			return hp.update(aColl, aUpdateMethod, conn);
		} finally {
			closeConnection(conn);
		}			
	}	

	private ICargo[] daoSelect(Map<String, Object> aData) throws FwException {
		Connection conn = null;
		try {
			conn = getConnection();
			FwAbstractCollection collection = (FwAbstractCollection) aData.remove(FwConstants.COLLECTION);
			FwDataSortOrder[] sort = (FwDataSortOrder[]) aData.get(FwConstants.ORDER_BY);
			FwDataCriteria[] criteria = (FwDataCriteria[]) aData.get(FwConstants.CRITERIA);
			Class<? extends IDAO> thedao = FwDAOFactory.getDAO(collection.getPACKAGE()).getClass();
			Class<?>[] params = {FwDataCriteria[].class, FwDataSortOrder[].class, Connection.class};
			Object[] values = {criteria, sort, conn};
			Object obj = FwDAOHelper.executeMethod(thedao, IDAO.SELECT, params, values);
			
			if(obj instanceof ICargo[]) {
				return (ICargo[]) obj;
			}
			
			FwException fe = new FwException(this.getClass(), new Exception());
			fe.setMessageCode("FW011");
			fe.setServiceName(thedao.getName());
			fe.setServiceMethod("select");
			FwExceptionManager.handleException(fe);
		} finally {
			closeConnection(conn);
		}
		return null;
	}

	private ICargo[] daoSelect(String aFinder, FwAbstractCollection aCollection) {
		Connection conn = null;
		try {
			conn = getConnection();
			Class<? extends IDAO> thedao = FwDAOFactory.getDAO(aCollection.getPACKAGE()).getClass();
			Class<?>[] params = {ICargo.class, java.sql.Connection.class};
			Object[] param = {(ICargo) aCollection.get(0), conn};
			Object obj = FwDAOHelper.executeMethod(thedao,aFinder, params, param);
			if(obj instanceof ICargo[]) {
				return (ICargo[]) obj;	
			}
			FwException fe = new FwException(this.getClass(), new Exception());
			fe.setMessageCode("FW011");
			fe.setServiceName(thedao.getName());
			fe.setServiceMethod(aFinder);
			FwExceptionManager.handleException(fe);	
		} finally {
			closeConnection(conn);
		}
		return null;
	}
	
	public void sendMessage(FwAbstractCollection aCollection) {
		Iterator<Object> iter = aCollection.iterator();
		while(iter.hasNext()) {
			messageQueueManager.send((ICargoMSG) iter.next());
		}
	}

	public void sendEmailMessage(FwAbstractCollection aCollection) {
		emailManager.handleEmail(aCollection);
	}
	
	public ISQL getSqlManager() {
		return sqlManager;
	}

	@Autowired
	public void setSqlManager(ISQL sqlManager) {
		this.sqlManager = sqlManager;
	}

}