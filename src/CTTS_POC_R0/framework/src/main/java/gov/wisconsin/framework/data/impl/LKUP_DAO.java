package gov.wisconsin.framework.data.impl;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractDAO;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IPrimaryKey;
import gov.wisconsin.framework.data.pojo.FwDataCriteria;
import gov.wisconsin.framework.data.pojo.FwDataSortOrder;
import gov.wisconsin.framework.data.pojo.LKUP_Cargo;
import gov.wisconsin.framework.data.pojo.LKUP_PrimaryKey;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LKUP_DAO extends FwAbstractDAO {
	
	private static final short HISTORY_TYPE = 0;
	private static final String SELECT_SQL  = "SELECT LKUP_GRP_FLD_ID,LKUP_CD,CD_ACTV_FLG,LKUP_DSC,SORT_ORD,UPDT_DT FROM "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"LKUP";
	private static final String INSERT_SQL  = "INSERT INTO "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"LKUP(LKUP_GRP_FLD_ID,LKUP_CD,CD_ACTV_FLG,LKUP_DSC,SORT_ORD,UPDT_DT) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_SQL  = "UPDATE "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"LKUP SET CD_ACTV_FLG=?,LKUP_DSC=?,SORT_ORD=?,UPDT_DT=? WHERE LKUP_GRP_FLD_ID=? AND  LKUP_CD=?";
	private static final String DELETE_SQL  = "DELETE FROM "+FwConstants.SCHEMA_TOKEN+FwConstants.DOT+"LKUP WHERE LKUP_GRP_FLD_ID=? AND  LKUP_CD=?";

	private void getRowFromResultSet(LKUP_Cargo row, ResultSet rs) {
		try {
			row.setLkup_grp_fld_id(rs.getString("LKUP_GRP_FLD_ID"));
			row.setLkup_cd(rs.getString("LKUP_CD"));
			row.setCd_actv_flg(rs.getString("CD_ACTV_FLG"));
			row.setLkup_dsc(rs.getString("LKUP_DSC"));
			row.setSort_ord(rs.getString("SORT_ORD"));
			row.setUpdt_dt(rs.getString("UPDT_DT"));
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		}
	}

	public ICargo[] select(FwDataCriteria[] aCriteria, FwDataSortOrder[] aSort, Connection aConn) {
		ResultSet rs = null;
		Connection conn = null;
		String sqlString = null;
		LKUP_Cargo rows[] = null;
		PreparedStatement statement = null;
		StringBuffer whereClause = new StringBuffer();
		List<LKUP_Cargo> values = new ArrayList<LKUP_Cargo>();

		try {
			conn = aConn;
			
			if(aCriteria.length == 0) { 
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_SEARCH_CRITERIA), FwConstants.EXP_TYP_DAO, NO_SEARCH_CRITERIA );
			}
			
			whereClause.append(FwConstants.WHERE);
			boolean where = false;
			boolean and = false;
			for(int i = 0; i < aCriteria.length; i++) {
				if(aCriteria[i].getColumn_name().equals("LKUP_GRP_FLD_ID")) {
					where = true;
					if(and) { whereClause.append(FwConstants.AND); }
					whereClause.append(" LKUP_GRP_FLD_ID=?");
					and = true;
				}
				else {
					if(aCriteria[i].getColumn_name().equals("LKUP_CD")) {
						where = true;
						if(and) { whereClause.append(FwConstants.AND); }
						whereClause.append(" LKUP_CD=?");
						and = true;
					}
				}
			}
			
			if(!where) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_PK_SEARCH_CRITERIA), FwConstants.EXP_TYP_DAO, NO_PK_SEARCH_CRITERIA );
			}

			if(aSort != null && aSort.length > 0) {
				whereClause.append(FwConstants.ORDER_BY);
				for(int i = 0; i < aSort.length; i++) {
					if(i > 0) { whereClause.append(FwConstants.COMMA); }
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
			int pos = 0;
			for(int j = 0; j < aCriteria.length; j++) {
				if(aCriteria[j].getColumn_name().equals("LKUP_GRP_FLD_ID")) {
					String value = (String) aCriteria[j].getColumn_value();
					statement.setShort(++pos, Short.parseShort(value));
				}
				else {
					if(aCriteria[j].getColumn_name().equals("LKUP_CD")) {
						String value = (String) aCriteria[j].getColumn_value();
						statement.setString(++pos, value);
					}
				}
			}
			
			rs = statement.executeQuery();
			while(rs.next()){
				LKUP_Cargo cargo = new LKUP_Cargo();
				getRowFromResultSet(cargo,rs);
				values.add(cargo);
			}
			rows = new LKUP_Cargo[values.size()];
			values.toArray(rows);
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, sqlString);
		} catch(Exception e) {
			FwExceptionManager.handleServiceException(this.getClass(), e, FwConstants.EMPTY_STRING);
		} finally {
			closeResultSetAndStatement(rs, statement);
		}
		return rows;
	}

	public ICargo findByPrimaryKey(IPrimaryKey aKey, Connection aConn) {
		ResultSet rs = null;
		Connection conn = null;
		String sqlString = null;
		String whereClause = null;
		LKUP_Cargo rescargo = null;
		PreparedStatement statement = null;
		LKUP_PrimaryKey key = (LKUP_PrimaryKey) aKey;

		try {
			conn = aConn;
			int count = 1;
			whereClause = " WHERE LKUP_GRP_FLD_ID=? AND LKUP_CD=?";
			sqlString = prepareSQLString(SELECT_SQL + whereClause);
			statement = conn.prepareStatement(sqlString);
			statement.setShort(count++, Short.parseShort(key.getLkup_grp_fld_id()));
			statement.setString(count++, key.getLkup_cd());
			rs = statement.executeQuery();
			
			if(rs.next()) {
				rescargo = new LKUP_Cargo();
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
		Connection conn = null;
		String sqlString = null;
		PreparedStatement statement = null;
		LKUP_Cargo cargo = (LKUP_Cargo) aCargo;
		
		try {
			conn = aConn;
			int count = 1;
			sqlString = prepareSQLString(INSERT_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setShort(count++, Short.parseShort(cargo.getLkup_grp_fld_id()));
			statement.setString(count++, cargo.getLkup_cd());
			statement.setString(count++, String.valueOf(cargo.getCd_actv_flg()));
			statement.setString(count++, cargo.getLkup_dsc());
			statement.setShort(count++, Short.parseShort(cargo.getSort_ord()));
			statement.setDate(count++, Date.valueOf(cargo.getUpdt_dt()));
			int res = statement.executeUpdate();
			if(res == 0) { 
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
		Connection conn = null;
		String sqlString = null;
		PreparedStatement statement = null;
		LKUP_Cargo cargo = (LKUP_Cargo) aCargo;
		try{
			conn = aConn;
			int count = 1;
			sqlString = prepareSQLString(UPDATE_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setString(count++,String.valueOf(cargo.getCd_actv_flg()));
			statement.setString(count++,cargo.getLkup_dsc());
			statement.setShort(count++,Short.parseShort(cargo.getSort_ord()));
			statement.setDate(count++,Date.valueOf(cargo.getUpdt_dt()));
			statement.setShort(count++,Short.parseShort(cargo.getLkup_grp_fld_id()));
			statement.setString(count++,cargo.getLkup_cd());
			int res = statement.executeUpdate();
			if(res == 0) {
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
		Connection conn = null;
		String sqlString = null;
		PreparedStatement statement = null;
		LKUP_Cargo cargo = (LKUP_Cargo) aCargo;
		
		try {
			conn = aConn;
			int count = 1;
			sqlString = prepareSQLString(DELETE_SQL);
			statement = conn.prepareStatement(sqlString);
			statement.setShort(count++,Short.parseShort(cargo.getLkup_grp_fld_id()));
			statement.setString(count++,cargo.getLkup_cd());
			int res = statement.executeUpdate();
			if(res == 0) {
				FwExceptionManager.handleException(this.getClass(), new Exception(NO_REC_DELETED), FwConstants.EXP_TYP_DAO, NO_REC_DELETED);
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

	public short getHistoryType()throws FwException{
		return HISTORY_TYPE;
	}
}
