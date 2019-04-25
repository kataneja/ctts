package gov.wisconsin.framework.data.base;

public interface IReferenceTableManager {

	public IReferenceTableData getReferenceTableData(String aRefTableName, String languageCode);

	public IReferenceTableData filterDataOnSingleColumn(String refTableName, String language, int columnId, int filterMethod, String[] filterValues);

	public IReferenceTableData filterDataOnMultipleColumns(String refTableName, String language, int[] columnId, int filterMethod, String[] filterValues);

	public String getColumnValue(String refTableName, int columnId, String code, String language);

	public void cacheRTData();
	
	public boolean reloadRefTableOnDemand(String aRefTableName);
}

