package gov.wisconsin.framework.data.impl;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractDAO;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IPrimaryKey;
import gov.wisconsin.framework.data.pojo.FwDataCriteria;
import gov.wisconsin.framework.data.pojo.FwDataSortOrder;
import gov.wisconsin.framework.data.pojo.WEB_EXCP_Cargo;
import gov.wisconsin.framework.data.pojo.WEB_EXCP_PrimaryKey;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WEB_EXCP_DAO extends FwAbstractDAO {
	
	private static final short HISTORY_TYPE = 0;
	private static final String SELECT_SQL         = "SELECT EXCP_ID,CALL_CLS_ID,CALL_MTHD_ID,CLS_ID,CUR_PAGE_ID,EXCP_TMS,EXCP_TYP,FULL_EXCP_SW,IP_ADR,MSG_ID,MTHD_ID,PREV_PAGE_ID,SRVC_MTHD_TYP,SRVC_NAM,USER_ID,WAMS_LOGON_ID,EXCP_TXT,SRVC_DSC,SRVC_MSG_TXT,SRVR_NAM,STAK_TRC_TXT,PRIM_KEY_TXT,PARM_TXT FROM "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"WEB_EXCP";
	private static final String INSERT_SQL         = "INSERT INTO "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"WEB_EXCP(CALL_CLS_ID,CALL_MTHD_ID,CLS_ID,CUR_PAGE_ID,EXCP_TMS,EXCP_TYP,FULL_EXCP_SW,IP_ADR,MSG_ID,MTHD_ID,PREV_PAGE_ID,SRVC_MTHD_TYP,SRVC_NAM,USER_ID,WAMS_LOGON_ID,EXCP_TXT,SRVC_DSC,SRVC_MSG_TXT,SRVR_NAM,STAK_TRC_TXT,PRIM_KEY_TXT,PARM_TXT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String PK_SELECT_SQL      = "SELECT EXCP_ID FROM "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"WEB_EXCP";
	private static final String UPDATE_SQL         = "UPDATE "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"WEB_EXCP SET CALL_CLS_ID=?,CALL_MTHD_ID=?,CLS_ID=?,CUR_PAGE_ID=?,EXCP_TMS=?,EXCP_TYP=?,FULL_EXCP_SW=?,IP_ADR=?,MSG_ID=?,MTHD_ID=?,PREV_PAGE_ID=?,SRVC_MTHD_TYP=?,SRVC_NAM=?,USER_ID=?,WAMS_LOGON_ID=?,EXCP_TXT=?,SRVC_DSC=?,SRVC_MSG_TXT=?,SRVR_NAM=?,STAK_TRC_TXT=?,PRIM_KEY_TXT=?,PARM_TXT=? WHERE  EXCP_ID=?";
	private static final String DELETE_SQL         = "DELETE FROM "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"WEB_EXCP WHERE  EXCP_ID=?";
	private static final String SELECT_EXCP_ID_SQL = "SELECT EXCP_ID FROM "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"WEB_EXCP WHERE EXCP_ID = IDENTITY_VAL_LOCAL()";

	public short getHistoryType() {
		return HISTORY_TYPE;
	}
	
	private void getRowFromResultSet(WEB_EXCP_Cargo row, ResultSet rs) {
		try {
			row.setExcp_id(rs.getInt("EXCP_ID"));
			row.setCall_cls_id(rs.getString("CALL_CLS_ID"));
			row.setCall_mthd_id(rs.getString("CALL_MTHD_ID"));
			row.setCls_id(rs.getString("CLS_ID"));
			row.setCur_page_id(rs.getString("CUR_PAGE_ID"));
			row.setExcp_tms(rs.getTimestamp("EXCP_TMS"));
			row.setExcp_typ(rs.getInt("EXCP_TYP"));
			row.setFull_excp_sw(rs.getString("FULL_EXCP_SW").charAt(0));
			row.setIp_adr(rs.getString("IP_ADR"));
			row.setMsg_id(rs.getString("MSG_ID"));
			row.setMthd_id(rs.getString("MTHD_ID"));
			row.setPrev_page_id(rs.getString("PREV_PAGE_ID"));
			row.setSrvc_mthd_typ(rs.getString("SRVC_MTHD_TYP"));
			row.setSrvc_nam(rs.getString("SRVC_NAM"));
			row.setUser_id(rs.getString("USER_ID"));
			row.setWams_logon_id(rs.getString("WAMS_LOGON_ID"));
			row.setExcp_txt(rs.getString("EXCP_TXT"));
			row.setSrvc_dsc(rs.getString("SRVC_DSC"));
			row.setSrvc_msg_txt(rs.getString("SRVC_MSG_TXT"));
			row.setSrvr_nam(rs.getString("SRVR_NAM"));
			row.setStak_trc_txt(rs.getString("STAK_TRC_TXT"));
			row.setPrim_key_txt(rs.getString("PRIM_KEY_TXT"));
			row.setParm_txt(rs.getString("PARM_TXT"));
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		}
	}

	public ICargo[] select(FwDataCriteria[] aCriteria, FwDataSortOrder[] aSort, Connection aConn) { 
		String sqlString = null;
		List<ICargo> values = new ArrayList<ICargo>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		WEB_EXCP_Cargo rows[] = null;
		StringBuffer whereClause = new StringBuffer();
		try{
			conn = aConn;
			if(aCriteria.length == 0) { 
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_SEARCH_CRITERIA), FwConstants.EXP_TYP_DAO, NO_SEARCH_CRITERIA);
			}
			whereClause.append(FwConstants.WHERE);
			boolean where = false;
			boolean and = false;
			for(int i=0; i<aCriteria.length; i++){
				if(aCriteria[i].getColumn_name().equals("EXCP_ID")){
					where = true;
					if(and){
						whereClause.append(FwConstants.AND);
					}
					whereClause.append(" EXCP_ID=?");
					and=true;
				}
			}
			if(!where) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_PK_SEARCH_CRITERIA), FwConstants.EXP_TYP_DAO, NO_PK_SEARCH_CRITERIA);
			}
			 //add sort order
			if(aSort!=null && aSort.length>0){
				whereClause.append(FwConstants.ORDER_BY);
				for(int i=0; i<aSort.length; i++){
					if(i>0)whereClause.append(FwConstants.COMMA);
					whereClause.append(aSort[i].getColumnName());
					if(aSort[i].getSortOrder() == FwDataSortOrder.asc) {
						whereClause.append(" ASC");
					} else if (aSort[i].getSortOrder() == FwDataSortOrder.desc) {
						 whereClause.append(" DESC");
					}
				}
			}
			
			sqlString = prepareSQLString(SELECT_SQL + whereClause);
			statement = conn.prepareStatement(sqlString);
			int pos=0;
			for(int j=0; j<aCriteria.length; j++) {
				if(aCriteria[j].getColumn_name().equals("EXCP_ID")){
					int value =Integer.parseInt((String) aCriteria[j].getColumn_value());
					statement.setInt(++pos, value);
				}
			}
			rs=statement.executeQuery();
			while(rs.next()){
				 WEB_EXCP_Cargo cargo = new WEB_EXCP_Cargo();
				 getRowFromResultSet(cargo,rs);
				 values.add(cargo);
			}
			rows = new WEB_EXCP_Cargo[values.size()];
			values.toArray(rows);
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, sqlString);
		} catch(Exception e) {
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally{
			closeResultSetAndStatement(rs, statement);
		}
		return rows;
	}

	public ICargo findByPrimaryKey(IPrimaryKey aKey, char aSimul, Connection aConn) {
		String sqlString = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		WEB_EXCP_Cargo rescargo = null;
		WEB_EXCP_PrimaryKey key = (WEB_EXCP_PrimaryKey) aKey;
		String whereClause=null;
		try{
			int count=1;
			conn = aConn;
			whereClause= " WHERE EXCP_ID=?";
			sqlString = prepareSQLString(PK_SELECT_SQL + whereClause);
			statement = conn.prepareStatement(sqlString);
			statement.setInt(count++,key.getExcp_id());
			rs=statement.executeQuery();
			if(rs.next()){
				rescargo = new WEB_EXCP_Cargo();
			}
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, sqlString);
		} catch(Exception e){
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally{
			closeResultSetAndStatement(rs, statement);
		}
		return rescargo;
	}

	public boolean insert(ICargo aCargo, Connection aConn) {
		String sqlString = null;
		WEB_EXCP_Cargo cargo = (WEB_EXCP_Cargo) aCargo;
		Connection conn = null;
		PreparedStatement statement=null;
		try{
			int count=1;
			conn=aConn;
			sqlString = prepareSQLString(INSERT_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setInt(count++,cargo.getExcp_id());
			statement.setString(count++,cargo.getCall_cls_id());
			statement.setString(count++,cargo.getCall_mthd_id());
			statement.setString(count++,cargo.getCls_id());
			statement.setString(count++,cargo.getCur_page_id());
			statement.setTimestamp(count++,cargo.getExcp_tms());
			statement.setInt(count++,cargo.getExcp_typ());
			statement.setString(count++,String.valueOf(cargo.getFull_excp_sw()));
			statement.setString(count++,cargo.getIp_adr());
			statement.setString(count++,cargo.getMsg_id());
			statement.setString(count++,cargo.getMthd_id());
			statement.setString(count++,cargo.getPrev_page_id());
			statement.setString(count++,cargo.getSrvc_mthd_typ());
			statement.setString(count++,cargo.getSrvc_nam());
			statement.setString(count++,cargo.getUser_id());
			statement.setString(count++,cargo.getWams_logon_id());
			statement.setString(count++,cargo.getExcp_txt());
			statement.setString(count++,cargo.getSrvc_dsc());
			statement.setString(count++,cargo.getSrvc_msg_txt());
			statement.setString(count++,cargo.getSrvr_nam());
			statement.setString(count++,cargo.getStak_trc_txt());
			statement.setString(count++,cargo.getPrim_key_txt());
			statement.setString(count++,cargo.getParm_txt());
			int res = statement.executeUpdate();
			if(res==0) { 
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_REC_INSERTED), FwConstants.EXP_TYP_DAO, NO_REC_INSERTED);
			}
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, sqlString);
		} catch(Exception e){
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally {
			closeStatement(statement);
		}
		return true;
	}

	public boolean update(ICargo aCargo, Connection aConn) {
		String sqlString = null;
		WEB_EXCP_Cargo cargo = (WEB_EXCP_Cargo) aCargo;
		Connection conn = null;
		PreparedStatement statement=null;
		try{
			int count=1;
			conn=aConn;
			sqlString = prepareSQLString(UPDATE_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setString(count++ ,cargo.getCall_cls_id());
			statement.setString(count++ ,cargo.getCall_mthd_id());
			statement.setString(count++ ,cargo.getCls_id());
			statement.setString(count++ ,cargo.getCur_page_id());
			statement.setTimestamp(count++ ,cargo.getExcp_tms());
			statement.setInt(count++ ,cargo.getExcp_typ());
			statement.setString(count++ ,String.valueOf(cargo.getFull_excp_sw()));
			statement.setString(count++ ,cargo.getIp_adr());
			statement.setString(count++ ,cargo.getMsg_id());
			statement.setString(count++ ,cargo.getMthd_id());
			statement.setString(count++ ,cargo.getPrev_page_id());
			statement.setString(count++ ,cargo.getSrvc_mthd_typ());
			statement.setString(count++ ,cargo.getSrvc_nam());
			statement.setString(count++ ,cargo.getUser_id());
			statement.setString(count++ ,cargo.getWams_logon_id());
			statement.setString(count++ ,cargo.getExcp_txt());
			statement.setString(count++ ,cargo.getSrvc_dsc());
			statement.setString(count++ ,cargo.getSrvc_msg_txt());
			statement.setString(count++ ,cargo.getSrvr_nam());
			statement.setString(count++ ,cargo.getStak_trc_txt());
			statement.setString(count++ ,cargo.getPrim_key_txt());
			statement.setString(count++ ,cargo.getParm_txt());
			statement.setInt(count++,cargo.getExcp_id());
			int res = statement.executeUpdate();
			if(res==0) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_REC_UPDATED), FwConstants.EXP_TYP_DAO, NO_REC_UPDATED);
			}
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, sqlString);
		} catch(Exception e) {
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally {
			closeStatement(statement);
		}
		return true;
	}

	public boolean delete(ICargo aCargo, Connection aConn) {
		String sqlString = null;
		WEB_EXCP_Cargo cargo = (WEB_EXCP_Cargo) aCargo;
		Connection conn = null;
		PreparedStatement statement=null;
		try{
			int count=1;
			conn=aConn;
			sqlString = prepareSQLString(DELETE_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setInt(count++,cargo.getExcp_id());
			int res = statement.executeUpdate();
			if(res==0) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_REC_DELETED), FwConstants.EXP_TYP_DAO, NO_REC_DELETED);
			}
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, sqlString);
		} catch(Exception e) {
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally{
			closeStatement(statement);
		}
		return true;
	}

	public ICargo[] insertAndSelectExcpID(ICargo aCargo, Connection aConn) { 
		String sqlString = null;
		WEB_EXCP_Cargo cargo = (WEB_EXCP_Cargo) aCargo;
		WEB_EXCP_Cargo[] rescargo = new WEB_EXCP_Cargo[1];
		Connection conn = null;
		PreparedStatement statement=null;
		ResultSet rs = null;
		try{
			int count=1;
			conn=aConn;
			conn.setAutoCommit(false);
			sqlString = prepareSQLString(INSERT_SQL);
			statement = conn.prepareStatement(sqlString);

			truncateExcessChars(cargo);
			
			statement.setString(count++,cargo.getCall_cls_id()); 
			statement.setString(count++,cargo.getCall_mthd_id());
			statement.setString(count++,cargo.getCls_id());
			statement.setString(count++,cargo.getCur_page_id());
			statement.setTimestamp(count++,cargo.getExcp_tms());
			statement.setInt(count++,cargo.getExcp_typ());
			statement.setString(count++,String.valueOf(cargo.getFull_excp_sw()));
			statement.setString(count++,cargo.getIp_adr());
			statement.setString(count++,cargo.getMsg_id());
			statement.setString(count++,cargo.getMthd_id());
			statement.setString(count++,cargo.getPrev_page_id());
			statement.setString(count++,cargo.getSrvc_mthd_typ());
			statement.setString(count++,cargo.getSrvc_nam());
			statement.setString(count++,cargo.getUser_id());
			statement.setString(count++,cargo.getWams_logon_id());
			statement.setString(count++,cargo.getExcp_txt());
			statement.setString(count++,cargo.getSrvc_dsc());
			statement.setString(count++,cargo.getSrvc_msg_txt());
			statement.setString(count++,cargo.getSrvr_nam());
			statement.setString(count++,cargo.getStak_trc_txt());
			statement.setString(count++,cargo.getPrim_key_txt());
			statement.setString(count++,cargo.getParm_txt());
			
			int res = statement.executeUpdate();
			if(res==0) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_REC_INSERTED), FwConstants.EXP_TYP_DAO, NO_REC_INSERTED);
			}

			closeResultSetAndStatement(rs, statement);
			sqlString = prepareSQLString(SELECT_EXCP_ID_SQL);
			statement = conn.prepareStatement(sqlString);
			rs = statement.executeQuery();
			if(rs.next()) {
				rescargo[0] = new WEB_EXCP_Cargo();
				rescargo[0].setExcp_id(rs.getInt(1));
			}
			conn.commit();
			return rescargo;
		} catch(SQLException sqle) {
			try { 
				conn.rollback();
			} catch(Exception e) {
				FwExceptionManager.handleException(this.getClass(), e);
			}
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		} catch(Exception e) {
			try {
				conn.rollback();
			} 
			catch(Exception ee) {
				FwExceptionManager.handleException(this.getClass(), ee);
			}
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally {
			closeResultSetAndStatement(rs, statement);
		}
		return null;
	}

	public ICargo[] insertAndSelectExcpIDInTransaction(ICargo aCargo, Connection aConn) { 
		String sqlString = null;
		WEB_EXCP_Cargo cargo = (WEB_EXCP_Cargo) aCargo;
		WEB_EXCP_Cargo[] rescargo = new WEB_EXCP_Cargo[1];
		Connection conn = null;
		PreparedStatement statement=null;
		ResultSet rs = null;
		try{
			int count=1;
			conn=aConn;
			sqlString = prepareSQLString(INSERT_SQL);
			statement = conn.prepareStatement(sqlString);
			truncateExcessChars(cargo);
			
			statement.setString(count++,cargo.getCall_cls_id());
			statement.setString(count++,cargo.getCall_mthd_id());
			statement.setString(count++,cargo.getCls_id());
			statement.setString(count++,cargo.getCur_page_id());
			statement.setTimestamp(count++,cargo.getExcp_tms());
			statement.setInt(count++,cargo.getExcp_typ());
			statement.setString(count++,String.valueOf(cargo.getFull_excp_sw()));			
			statement.setString(count++,cargo.getIp_adr());
			statement.setString(count++,cargo.getMsg_id());
			statement.setString(count++,cargo.getMthd_id());
			statement.setString(count++,cargo.getPrev_page_id());
			statement.setString(count++,cargo.getSrvc_mthd_typ());
			statement.setString(count++,cargo.getSrvc_nam());
			statement.setString(count++,cargo.getUser_id());
			statement.setString(count++,cargo.getWams_logon_id());
			statement.setString(count++,cargo.getExcp_txt());
			statement.setString(count++,cargo.getSrvc_dsc());
			statement.setString(count++,cargo.getSrvc_msg_txt());
			statement.setString(count++,cargo.getSrvr_nam());
			statement.setString(count++,cargo.getStak_trc_txt());
			statement.setString(count++,cargo.getPrim_key_txt());
			statement.setString(count++,cargo.getParm_txt());

			int res = statement.executeUpdate();
			if(res==0) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_REC_INSERTED), FwConstants.EXP_TYP_DAO, NO_REC_INSERTED);
			}
			
			closeResultSetAndStatement(rs, statement);
			sqlString = prepareSQLString(SELECT_EXCP_ID_SQL);
			statement = conn.prepareStatement(sqlString);
			rs = statement.executeQuery();
			if(rs.next()){
				rescargo[0] = new WEB_EXCP_Cargo();
				rescargo[0].setExcp_id(rs.getInt(1));
			}
			return rescargo;
		} catch(SQLException sqle) {
			try { 
				conn.rollback();
			} catch(Exception e) {
				FwExceptionManager.handleException(this.getClass(), e);
			}
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		} catch(Exception e){
			try {
				conn.rollback();
			} 
			catch(Exception ee){
				FwExceptionManager.handleException(this.getClass(), ee);
			}
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally{
			closeResultSetAndStatement(rs, statement);
		}
		return null;
	}

	private void truncateExcessChars(WEB_EXCP_Cargo aCargo){
		if(aCargo.getCall_cls_id() != null && aCargo.getCall_cls_id().length() > 100){
			aCargo.setCall_cls_id(aCargo.getCall_cls_id().substring(0,100));
		}
		if(aCargo.getCall_mthd_id() != null && aCargo.getCall_mthd_id().length() > 50){
			aCargo.setCall_mthd_id(aCargo.getCall_mthd_id().substring(0,50));
		}
		if(aCargo.getCls_id() != null && aCargo.getCls_id().length() > 100){
			aCargo.setCls_id(aCargo.getCls_id().substring(0,100));
		}
		if(aCargo.getCur_page_id() != null && aCargo.getCur_page_id().length() > 5){
			aCargo.setCur_page_id(aCargo.getCur_page_id().substring(0,5));
		}
		if(aCargo.getIp_adr() != null && aCargo.getIp_adr().length() > 15){
			aCargo.setIp_adr(aCargo.getIp_adr().substring(0,15));
		}
		if(aCargo.getMsg_id() != null && aCargo.getMsg_id().length() > 5){
			aCargo.setMsg_id(aCargo.getMsg_id().substring(0,5));
		}		
		if(aCargo.getPrev_page_id() != null && aCargo.getPrev_page_id().length() > 5){
			aCargo.setPrev_page_id(aCargo.getPrev_page_id().substring(0,5));
		}		
		if(aCargo.getSrvc_mthd_typ() != null && aCargo.getSrvc_mthd_typ().length() > 50){
			aCargo.setSrvc_mthd_typ(aCargo.getSrvc_mthd_typ().substring(0,50));
		}		
		if(aCargo.getSrvc_nam() != null && aCargo.getSrvc_nam().length() > 100){
			aCargo.setSrvc_nam(aCargo.getSrvc_nam().substring(0,100));
		}		
		if(aCargo.getUser_id() != null && aCargo.getUser_id().length() > 6){
			aCargo.setUser_id(aCargo.getUser_id().substring(0,6));
		}		
		if(aCargo.getWams_logon_id() != null && aCargo.getWams_logon_id().length() > 36){
			aCargo.setWams_logon_id(aCargo.getWams_logon_id().substring(0,36));
		}		
		if(aCargo.getExcp_txt() != null && aCargo.getExcp_txt().length() > 300){
			aCargo.setExcp_txt(aCargo.getExcp_txt().substring(0,300));
		}		
		if(aCargo.getSrvc_dsc() != null && aCargo.getSrvc_dsc().length() > 2000){
			aCargo.setSrvc_dsc(aCargo.getSrvc_dsc().substring(0,2000));
		}		
		if(aCargo.getSrvc_msg_txt() != null && aCargo.getSrvc_msg_txt().length() > 4500){
			aCargo.setSrvc_msg_txt(aCargo.getSrvc_msg_txt().substring(0,4500));
		}		
		if(aCargo.getSrvr_nam() != null && aCargo.getSrvr_nam().length() > 50){
			aCargo.setSrvr_nam(aCargo.getSrvr_nam().substring(0,50));
		}		
		if(aCargo.getStak_trc_txt() != null && aCargo.getStak_trc_txt().length() > 10000){
			aCargo.setStak_trc_txt(aCargo.getStak_trc_txt().substring(0,10000));
		}		
		if(aCargo.getPrim_key_txt() != null && aCargo.getPrim_key_txt().length() > 500){
			aCargo.setPrim_key_txt(aCargo.getPrim_key_txt().substring(0,500));
		}		
		if(aCargo.getParm_txt() != null && aCargo.getParm_txt().length() > 10000){
			aCargo.setParm_txt(aCargo.getParm_txt().substring(0,10000));
		}		
	}
}
