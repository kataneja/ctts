package gov.wisconsin.framework.data.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.ISQL;
import gov.wisconsin.framework.data.pojo.FwDataCriteria;
import gov.wisconsin.framework.data.pojo.FwDataSortOrder;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FwSQLManager extends FwBaseClass implements ISQL {

	public List<Map<String, Object>> select(Map<String, Object> aData, Connection aConn) throws FwException {
		String sql_ind = (String) aData.get(FwConstants.SQL_IND);
		Map<String, String> row = configurationManager.getXMLSQLConfiguration(sql_ind);
		FwDataCriteria[] criteria = (FwDataCriteria[]) aData.get(FwConstants.CRITERIA);
		FwDataSortOrder[] sort = (FwDataSortOrder[]) aData.get(FwConstants.ORDER_BY);
		
		String sql_st = row.get(FwConstants.SQL_VALUE);
		
		if(row.get(TYPE).equals(DYNAMIC)) { 
			sql_st = setWhereClause(sql_st, criteria); 
		}
		
		if(sort != null) { 
			sql_st = setOrderByClause(sql_st, sort); 
		}
		
		String isolation = String.valueOf(aData.get(FwConstants.DB_ISOLATION_LEVEL));
		if(isolation != null) { 
			sql_st = setIsolationLevel(isolation, sql_st); 
		}
		
		String kw = row.get(KEYWORD) != null ? row.get(KEYWORD) : null;
		String sqlType = row.get(SQLTYPE);
		
		if(kw != null) { 
			return selectForSql(sql_st, criteria, kw, sqlType, aConn); 
		}
		
		return selectForSql(sql_st, criteria, sqlType, aConn);
	}	

	private String setWhereClause(String aSql, FwDataCriteria[] aCriteria) {
		StringBuffer tempSql = new StringBuffer(aSql);
		tempSql.append(FwConstants.WHERE);
		
		for(int i = 0; i < aCriteria.length; i++) {
			if(i > 0) { tempSql.append(FwConstants.AND); }
			tempSql.append(aCriteria[i].getColumn_name());
			tempSql.append(FwConstants.SPACE);
			tempSql.append(aCriteria[i].getOperator());
			tempSql.append(FwConstants.SPACE);
			tempSql.append(INPUT_PARAM);
		}
		return tempSql.toString();
	}

	private String setOrderByClause(String aSql, FwDataSortOrder[] aSort) {
		StringBuffer sql = new StringBuffer(aSql);
		
		if(aSort != null && aSort.length > 0) {
			sql.append(FwConstants.ORDER_BY);
			
			for(int i = 0; i < aSort.length; i++) {
				if(i > 0) { sql.append(FwConstants.COMMA); }
				sql.append(aSort[i].getColumnName());
				
				if(aSort[i].getSortOrder() == FwDataSortOrder.asc) { sql.append(ASC); } 
				else if (aSort[i].getSortOrder() == FwDataSortOrder.desc) { sql.append(DESC); }										
			}
		}
		return sql.toString();	
	}

	private String setIsolationLevel(String aIsolation, String aSql) {
		if(aIsolation.equals(FwConstants.DB_UNCOMMITTED_READ)) { aSql = aSql + FwConstants.DB_UNCOMMITTED_READ; } 
		else if(aIsolation.equals(FwConstants.DB_READ_COMMITTED)) { aSql = aSql + FwConstants.DB_READ_COMMITTED; } 
		else if(aIsolation.equals(FwConstants.DB_REPEATABLE_READ)) { aSql = aSql + FwConstants.DB_READ_COMMITTED; } 
		else if(aIsolation.equals(FwConstants.DB_SERIALIZABLE)) { aSql = aSql + FwConstants.DB_SERIALIZABLE; }
		
		return aSql;
	}

	private List<Map<String, Object>> selectForSql(String aSql, FwDataCriteria[] aCriteria, String aSqlType, Connection aConn) throws FwException {
		List<Map<String, Object>> result = null;
		ResultSet rs = null;
		Connection conn = null;	
		PreparedStatement statement = null;
		
		try {
			conn = aConn;
			statement = conn.prepareStatement(aSql);
			if(aCriteria != null) {
				int pos = 1;
				for(int i = 0; i < aCriteria.length; i++, pos++) {
					String value = aCriteria[i].getColumn_value();
					String type = aCriteria[i].getData_type();
					
					if(type.equals(FwConstants.STRING) || type.equals(FwConstants.CHAR)) { statement.setString(pos, value); } 
					else if(type.equals(FwConstants.SHORT))     { statement.setShort(pos, Short.parseShort(value)); }
					else if(type.equals(FwConstants.INT))       { statement.setInt(pos, Integer.parseInt(value)); }
					else if(type.equals(FwConstants.LONG))      { statement.setLong(pos, Long.parseLong(value)); }
					else if(type.equals(FwConstants.DATE))      { statement.setDate(pos, Date.valueOf(value)); } 
					else if(type.equals(FwConstants.DOUBLE))    { statement.setDouble(pos, Double.parseDouble(value)); } 
					else if(type.equals(FwConstants.FLOAT))     { statement.setFloat(pos, Float.parseFloat(value)); } 
					else if(type.equals(FwConstants.TIMESTAMP)) { statement.setTimestamp(pos, Timestamp.valueOf(value)); } 
					else {
						FwException fe = new FwException(this.getClass(), new Exception("Type: " + type), FwConstants.EXP_TYP_XML, "Type: " + type);
						fe.setMessageCode("FW018");
						FwExceptionManager.handleException(fe);
					}
				}
			}
			
			if(PERSIST.equals(aSqlType)) {
				int res = statement.executeUpdate();
				result = new ArrayList<Map<String, Object>>();
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("count", res);
				result.add(resultMap);
				return result;
			} else {
				rs = statement.executeQuery();
				return getResultAsList(rs);
			}
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING, FwConstants.EXP_TYP_XML);
		}
		finally {
			closeStatmentResultSet(rs, statement);
		}
		return null;
	}

	private List<Map<String, Object>> selectForSql(String aSql, FwDataCriteria[] aCriteria, String aKeyword, String aSqlType, Connection aConn) throws FwException {	
		if(!aKeyword.equals(IN) && !aKeyword.equals(NOT_IN)) {
			FwException fe = new FwException(this.getClass(), new Exception("Keyword: " + aKeyword), FwConstants.EXP_TYP_XML, "Keyword: " + aKeyword);
			fe.setMessageCode("FW050");
			FwExceptionManager.handleException(fe);
		} 
		
		List<Map<String, Object>> result = null;
		ResultSet rs = null;
		Statement statement = null;
		
		try {
			statement = aConn.createStatement();
			StringBuffer tempSql = new StringBuffer();
			if(aCriteria != null) {
				int pos = 0;
				for(int i = 0; i < aCriteria.length; i++) {
					String value = aCriteria[i].getColumn_value();
					tempSql.append(aSql.substring(pos, pos=aSql.indexOf(INPUT_PARAM, pos)));
					tempSql.append(value);
					pos++;
				}
				tempSql.append(aSql.substring(pos));
			}
			if(aSqlType.equals(PERSIST)){
				int res = statement.executeUpdate(tempSql.toString());
				result = new ArrayList<Map<String, Object>>();
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("count", res);
				result.add(resultMap);
				return result;
			} else {
				rs = statement.executeQuery(tempSql.toString());
				return getResultAsList(rs);
			}
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING, FwConstants.EXP_TYP_XML);
		}
		finally {
			closeStatmentResultSet(rs, statement);				
		}
		return null;
	}

	private List<Map<String, Object>> getResultAsList(ResultSet aRs) {
		try {	
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			ResultSetMetaData rsmd = aRs.getMetaData();	
			int column_count = rsmd.getColumnCount();
			String[] columnName = new String[column_count];
			for(int i = 0; i < column_count; i++) { 
				columnName[i] = rsmd.getColumnName(i+1);
			}
			while(aRs.next()) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				for(int j = 0; j < column_count; j++) {
					resultMap.put(columnName[j], aRs.getString(j+1));
				}
				result.add(resultMap);
			}	
			return result;
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING, FwConstants.EXP_TYP_XML);
		}
		
		return null;
	}

	private void closeStatmentResultSet(ResultSet rs, Statement statement) {
		try {
			if (rs != null) { rs.close(); }
		} catch (SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		}
		closeStatement(statement);	
	}

	private void closeStatement(Statement statement) {
		try {
			if (statement != null) { statement.close(); }
		} catch (SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		}
	}
}