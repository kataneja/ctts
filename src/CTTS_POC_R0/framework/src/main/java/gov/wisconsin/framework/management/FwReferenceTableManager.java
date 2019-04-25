package gov.wisconsin.framework.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.constant.IReferenceConstants;
import gov.wisconsin.framework.data.base.IReferenceTableData;
import gov.wisconsin.framework.data.base.IReferenceTableManager;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.impl.FwReferenceTableData;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public final class FwReferenceTableManager extends FwBaseClass implements IReferenceTableManager, Serializable {
	private static final long serialVersionUID = -8197449510469878794L;
	
	private static IReferenceTableManager instance;
	
	private String SCHEMA;
	
	private String LOAD_TABLE_SQL;
	private Map<String, Map<String, FwReferenceTableData>> masterTable = new HashMap<String, Map<String, FwReferenceTableData>>();
	
	private FwReferenceTableManager() {}
	
	public IReferenceTableData getReferenceTableData(String aRefTableName, String languageCode) {
		try {
			Map<String, FwReferenceTableData> data = masterTable.get(aRefTableName);
			if (data != null) {
				return (IReferenceTableData) data.get(languageCode);
			} else {
				loadRefTableOnDemand(aRefTableName);
				return masterTable.get(aRefTableName).get(languageCode);
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		return null;
	}

	private void loadReferenceTableData(String aRefTableName) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		Map<String, Object> codeDescMap = null;
		ArrayList<Map<String, Object>> esData = null;
		ArrayList<Map<String, Object>> enData =  null;
		Map<String, FwReferenceTableData> langMap = new HashMap<String, FwReferenceTableData>();

		try {
		  	con = connectionManager.getConnection();
			ps = con.prepareStatement(LOAD_TABLE_SQL);
			ps.setString(1, aRefTableName);				
			rs = ps.executeQuery();

			List<Map<String, Object>> data = null;
			enData = new ArrayList<Map<String, Object>>();
			esData = new ArrayList<Map<String, Object>>();
			while(rs.next()) {
				Integer fieldId = new Integer(rs.getInt("LKUP_FLD_ID"));
				String rowLookupCode = rs.getString("LKUP_CD").trim();
				
				if(rs.getString("LANG_CD").trim().equals(FwConstants.ENGLISH)) {
					data = enData;
				} else {
					data = esData;
				}
				
				int listSize = data.size();
				boolean codeExists = false;
				for (int i = 0;i < listSize; i++) {
					codeDescMap = data.get(i);
					String code = (String) codeDescMap.get(IReferenceConstants.CODE_COLUMN_KEY);
					if (code.equals(rowLookupCode)) {
						codeExists = true;
						codeDescMap.put(String.valueOf(fieldId), rs.getString("LKUP_DSC").trim());
						break;
					}
				}
				if (!codeExists) {
					codeDescMap = new HashMap<String, Object>();
					codeDescMap.put(String.valueOf(IReferenceConstants.CODE_COLUMN_KEY), rowLookupCode);
					codeDescMap.put(String.valueOf(fieldId), rs.getString("LKUP_DSC").trim());
					data.add(codeDescMap);
				}
			}

			enData.trimToSize();
			esData.trimToSize();
			langMap.put(FwConstants.ENGLISH, new FwReferenceTableData(enData));			
			langMap.put(FwConstants.SPANISH, new FwReferenceTableData(esData));
			masterTable.put(aRefTableName, langMap);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		} finally {
			try {
				if(rs != null) { rs.close(); }
				if(ps != null) { ps.close(); }
				if(con != null) { con.close(); }
			} catch(SQLException sqle) {
				FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
			} catch(Exception e) {
				FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
			}
		}	
	}

	public void cacheRTData() {
		setLoadQueryString();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
		 	con = connectionManager.getConnection();
			ps = con.prepareStatement("SELECT LKUP_GRP_CD FROM " + SCHEMA + ".LKUP_GRP");
			rs = ps.executeQuery();
			List<String> tableNameList = new ArrayList<String>();
			
			while(rs.next()) {
				tableNameList.add(rs.getString(1).trim());							 
			}
			
			rs.close();
			ps.close();
			con.close();
			
			for(int i = 0; i < tableNameList.size(); i++) {
				loadReferenceTableData(tableNameList.get(i));
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		} finally {
			try {
				if(rs != null) { rs.close(); }
				if(ps != null) { ps.close(); }
				if(con != null && !con.isClosed()) { con.close(); }
			} catch(SQLException sqle) {
				FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
			} catch(Exception e) {
				FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
			}
		}       
	}
	
	private synchronized void loadRefTableOnDemand(String aRefTableName) {
		if (!masterTable.containsKey(aRefTableName)) {
			loadReferenceTableData(aRefTableName);
		}
	}

	public IReferenceTableData filterDataOnMultipleColumns(String refTableName, String language, int[] columnId, int filterMethod, String[] filterValues) {
		IReferenceTableData refTable = getReferenceTableData(refTableName, language);
		return refTable.filterDataOnMultipleColumns(columnId, filterMethod, filterValues);
	}

	public IReferenceTableData filterDataOnSingleColumn(String refTableName, String language, int columnId, int filterMethod, String[] filterValues) {
		IReferenceTableData refTable = getReferenceTableData(refTableName, language);
		return refTable.filterDataOnSingleColumn(columnId, filterMethod, filterValues);	
	}

	public String getColumnValue(String refTableName, int columnId, String code, String language) {
		IReferenceTableData refTable = getReferenceTableData(refTableName, language);
		return refTable.getColumnValue(code, columnId);
	}

	public boolean reloadRefTableOnDemand(String aRefTableName) {
		boolean reloadStatus = false;
		
		if (!masterTable.containsKey(aRefTableName)) {
			loadReferenceTableData(aRefTableName);
			reloadStatus = true;
		} else {
			masterTable.remove(aRefTableName);
			loadReferenceTableData(aRefTableName);
			reloadStatus = true;
		}
		
		return reloadStatus;
	}
	
	private void setLoadQueryString() {
		LOAD_TABLE_SQL = 
			"SELECT LKUP.LKUP_GRP_FLD_ID, LKUP.LKUP_CD, LKUP.LKUP_DSC, LKUP.SORT_ORD, LKUP_GRP_FLD.LANG_CD, LKUP_GRP_FLD.LKUP_GRP_CD, LKUP_GRP_FLD.LKUP_FLD_ID "
			+ "FROM " + SCHEMA + ".LKUP AS LKUP "
			+ "LEFT OUTER JOIN " + SCHEMA + ".LKUP_GRP_FLD AS LKUP_GRP_FLD "
			+ "ON LKUP.LKUP_GRP_FLD_ID = LKUP_GRP_FLD.LKUP_GRP_FLD_ID "
			+ "WHERE LKUP_GRP_FLD.LKUP_GRP_CD = ? AND LKUP.CD_ACTV_FLG = 'Y' ORDER BY lang_cd, sort_ord";
	}

    public static void setInstance(IReferenceTableManager referenceTableManager) {
    	instance = referenceTableManager;
    }
    
    public static IReferenceTableManager getInstance() {
    	return instance;
    }
}
