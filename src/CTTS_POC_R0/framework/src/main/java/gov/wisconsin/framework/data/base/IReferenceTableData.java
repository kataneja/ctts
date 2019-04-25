package gov.wisconsin.framework.data.base;

import java.util.List;

public interface IReferenceTableData {
	
	public boolean containsCode(String code);
	
	public String getColumnValue(String code, int columnId);
	
	public String[] getCodeValues();
	
	public String[] getDescriptionValues(int columnId);
	
	public List<String> getDescriptionValuesAsList(int columnId);
	
	public int getNumberOfRows();
		
	public String getDesc(int index, int columnId);
	
	public IReferenceTableData filterDataOnSingleColumn(int columnId, int filterMethod, String[] filterValues);
	
	public IReferenceTableData filterDataOnMultipleColumns(int[] columnId, int filterMethod, String[] filterValues);
}
