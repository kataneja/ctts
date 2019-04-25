package gov.wisconsin.framework.data.impl;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractDAO;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IPrimaryKey;
import gov.wisconsin.framework.data.pojo.FwDataCriteria;
import gov.wisconsin.framework.data.pojo.FwDataSortOrder;
import gov.wisconsin.framework.data.pojo.LKUP_GRP_Cargo;
import gov.wisconsin.framework.data.pojo.LKUP_GRP_PrimaryKey;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LKUP_GRP_DAO extends FwAbstractDAO {
	
	private static final short HISTORY_TYPE = 0;
	private static final String SELECT_SQL  = "SELECT LKUP_GRP_CD,LKUP_GRP_DSC FROM "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"LKUP_GRP";
	private static final String INSERT_SQL  = "INSERT INTO "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"LKUP_GRP(LKUP_GRP_CD,LKUP_GRP_DSC) VALUES(?,?)";
	private static final String UPDATE_SQL  = "UPDATE "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"LKUP_GRP SET LKUP_GRP_DSC=? WHERE  LKUP_GRP_CD=?";
	private static final String DELETE_SQL  = "DELETE FROM "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"LKUP_GRP WHERE  LKUP_GRP_CD=?";

	private void getRowFromResultSet(LKUP_GRP_Cargo row, ResultSet rs) {
		try{
			row.setLkup_grp_cd(rs.getString("LKUP_GRP_CD"));
			row.setLkup_grp_dsc(rs.getString("LKUP_GRP_DSC"));
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		}
	}

	public ICargo[] select(FwDataCriteria[] aCriteria, FwDataSortOrder[] aSort, Connection aConn){
		String sqlString = null;
		List<ICargo> values = new ArrayList<ICargo>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		LKUP_GRP_Cargo rows[] = null;
		StringBuffer whereClause = new StringBuffer();
		try{
			conn = aConn;
			if(aCriteria.length == 0) { 
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_SEARCH_CRITERIA), FwConstants.EXP_TYP_DAO, NO_SEARCH_CRITERIA );
			}
			whereClause.append(FwConstants.WHERE);
			boolean where = false;
			boolean and = false;
			for(int i=0; i<aCriteria.length; i++){
				if(aCriteria[i].getColumn_name().equals("LKUP_GRP_CD")){
					where = true;
					if(and){
						whereClause.append(FwConstants.AND);
					}
					whereClause.append(" LKUP_GRP_CD=?");
					and=true;
				}
			}
			if(!where) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_PK_SEARCH_CRITERIA), FwConstants.EXP_TYP_DAO, NO_PK_SEARCH_CRITERIA );
			}

			if(aSort!=null && aSort.length>0){
				whereClause.append(FwConstants.ORDER_BY);
				for(int i=0; i<aSort.length; i++){
					if(i>0)whereClause.append(FwConstants.COMMA);
					whereClause.append(aSort[i].getColumnName());
					if(aSort[i].getSortOrder() == FwDataSortOrder.asc) {
						whereClause.append(" ASC");
					} else if (aSort[i].getSortOrder() == FwDataSortOrder.desc) {
						 whereClause.append(" DESC");						}
				}
			}

			sqlString = prepareSQLString(SELECT_SQL + whereClause);
			statement = conn.prepareStatement(sqlString);
			int pos=0;
			for(int j=0; j<aCriteria.length; j++) {
				if(aCriteria[j].getColumn_name().equals("LKUP_GRP_CD")){
					String value = (String) aCriteria[j].getColumn_value();
					statement.setString(++pos, value);
				}
			}
			rs=statement.executeQuery();
			while(rs.next()){
				LKUP_GRP_Cargo cargo = new LKUP_GRP_Cargo();
				getRowFromResultSet(cargo,rs);
				values.add(cargo);
			}
			rows = new LKUP_GRP_Cargo[values.size()];
			values.toArray(rows);
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, sqlString);
		} catch(Exception e){
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally {
			closeResultSetAndStatement(rs, statement);
		}
		return rows;
	}

	public ICargo findByPrimaryKey(IPrimaryKey aKey, Connection aConn) {
		String sqlString = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		LKUP_GRP_Cargo rescargo = null;
		LKUP_GRP_PrimaryKey key = (LKUP_GRP_PrimaryKey) aKey;
		String whereClause=null;
		try{
			int count=1;
			conn = aConn;
			whereClause= " WHERE LKUP_GRP_CD=?";
			sqlString = prepareSQLString(SELECT_SQL + whereClause);
			statement = conn.prepareStatement(sqlString);
			statement.setString(count++,key.getLkup_grp_cd());
			rs=statement.executeQuery();
			if(rs.next()){
				rescargo = new LKUP_GRP_Cargo();
			}
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, sqlString);
		} catch(Exception e) {
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally {
			closeResultSetAndStatement(rs,statement);
		}
		return rescargo;
	}

	public boolean insert(ICargo aCargo, Connection aConn) {
		String sqlString = null;
		LKUP_GRP_Cargo cargo = (LKUP_GRP_Cargo) aCargo;
		Connection conn = null;
		PreparedStatement statement=null;
		try{
			int count=1;
			conn=aConn;
			sqlString = prepareSQLString(INSERT_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setString(count++,cargo.getLkup_grp_cd());
			statement.setString(count++,cargo.getLkup_grp_dsc());
			int res = statement.executeUpdate();
			if(res==0) { 
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_REC_INSERTED), FwConstants.EXP_TYP_DAO, NO_REC_INSERTED);
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

	public boolean update(ICargo aCargo, Connection aConn) {
		String sqlString = null;
		LKUP_GRP_Cargo cargo = (LKUP_GRP_Cargo) aCargo;
		Connection conn = null;
		PreparedStatement statement=null;
		try{
			int count=1;
			conn=aConn;
			sqlString = prepareSQLString(UPDATE_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setString(count++ ,cargo.getLkup_grp_dsc());
			statement.setString(count++,cargo.getLkup_grp_cd());
			int res = statement.executeUpdate();
			if(res==0) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_REC_UPDATED), FwConstants.EXP_TYP_DAO, NO_REC_UPDATED);
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

	public boolean delete(ICargo aCargo, Connection aConn) {
		String sqlString = null;
		LKUP_GRP_Cargo cargo = (LKUP_GRP_Cargo) aCargo;
		Connection conn = null;
		PreparedStatement statement=null;
		try{
			int count=1;
			conn=aConn;
			sqlString = prepareSQLString(DELETE_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setString(count++,cargo.getLkup_grp_cd());
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

	public short getHistoryType() throws FwException {
		return HISTORY_TYPE;
	}
}
