package gov.wisconsin.framework.impl;

import gov.wisconsin.framework.constant.IReferenceConstants;
import gov.wisconsin.framework.data.base.IReferenceTableData;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class FwReferenceTableData implements IReferenceTableData, Serializable {
	private static final long serialVersionUID = 9155757630365491119L;
	
	private int numberOfRows;
	private List<Map<String, Object>> data = null;
	
	public FwReferenceTableData(List<Map<String, Object>> data){
		this.data = data;
		this.numberOfRows = data.size();
	}

	public boolean containsCode(String code){
		code = code.trim();
		int size = numberOfRows;
		Map<String, Object> codeDescMap = null;
		String rowCode = null;
		
		for (int i = 0; i < size; i++) {
			codeDescMap = data.get(i);
			rowCode = (String) codeDescMap.get(IReferenceConstants.CODE_COLUMN_KEY);
			if (code.equals(rowCode)) {
				return true;
			}
		}
		return false;
	}
	
	public int getNumberOfRows(){	
		return numberOfRows;
	}

	public String getColumnValue(String code, int columnId){
		code = code.trim();
		int size = numberOfRows;
		Map<String, Object> codeDescMap = null;
		String rowCode = null;
		
		for (int i = 0; i < size; i++) {
			codeDescMap = data.get(i);
			rowCode = (String) codeDescMap.get(IReferenceConstants.CODE_COLUMN_KEY);
			if (code.equals(rowCode)) {
				return (String) codeDescMap.get(new Integer(columnId));
			}
		}
		return null;
	}

	public String[] getCodeValues() {
		int length = numberOfRows;
		String[] codeList = new String[length];
		Map<String, Object> codeDescMap  = null;
		
		for (int i = 0; i < length; i++) {
			codeDescMap = data.get(i);
			codeList[i] = (String) codeDescMap.get(IReferenceConstants.CODE_COLUMN_KEY);
		}
		return codeList;
	}

	public String[] getDescriptionValues(int columnId) {
		int length = numberOfRows;
		String[] descList = new String[length];
		Map<String, Object> codeDescMap  = null;
		Integer columnKey = new Integer(columnId);
		
		for (int i = 0; i < length; i++) {
			codeDescMap = data.get(i);
			descList[i] = (String) codeDescMap.get(columnKey);
		}
		return descList;
	}

	public List<String> getDescriptionValuesAsList(int columnId) {
		List<String> descValuesList = new ArrayList<String>();
		Map<String, Object> codeDescMap  = null;
		Integer columnKey = new Integer(columnId);
		
		for (int i = 0; i < numberOfRows; i++) {
			codeDescMap = data.get(i);
			descValuesList.add((String) codeDescMap.get(columnKey));
		}
		return descValuesList;
	}

	public String getDesc(int index, int columnId) {
		Map<String, Object> rowMap = this.data.get(index);
		return (String) rowMap.get(new Integer(columnId));
	}

	public IReferenceTableData filterDataOnSingleColumn(int columnId, int filterMethod, String[] filterValues){
		try {
			int filterValuesLength = filterValues.length;
			Integer columnKey = new Integer(columnId);
			List<Map<String, Object>> filteredList = new ArrayList<Map<String, Object>>();
			
			for (int i = 0; i < filterValuesLength; i++) {
				filterValues[i] = filterValues[i].trim();
			}
			
			if (filterMethod == IReferenceConstants.FILTER_INCLUDE_MATCH_ONE) {
				String columnValue = null;
				Map<String, Object> rowData = null;
				
				for (int i = 0; i < numberOfRows; i++) {
					rowData = data.get(i);
					columnValue = (String) rowData.get(columnKey);
					
					for (int j = 0; j < filterValuesLength; j++) {
						if (columnValue.equals(filterValues[j])) {
							filteredList.add(rowData);
							break;
						}
					}
				}
			} else if (filterMethod == IReferenceConstants.FILTER_EXCLUDE_MATCH_ONE){
				String columnValue = null;
				Map<String, Object> rowData = null;
				boolean matchFound = false;
				
				for (int i = 0; i < numberOfRows; i++) {
					matchFound = false;
					rowData = data.get(i);
					columnValue = (String) rowData.get(columnKey);
					
					for (int j = 0; j < filterValuesLength; j++) {
						if (columnValue.equals(filterValues[j])) {
							matchFound = true;
							break;
						}
					}
					if (!matchFound){
						filteredList.add(rowData);
					}
				}
			} else {
				FwExceptionManager.handleException(new FwException(this.getClass(), new Exception("Unknown Filter method")));
			}
			
			return new FwReferenceTableData(filteredList);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}

	public IReferenceTableData filterDataOnMultipleColumns(int[] columnId, int filterMethod, String[] filterValues){
		try {
			int filterValuesLength = filterValues.length;
			Integer[] columnKeys = new Integer[filterValuesLength];
			
			for (int i = 0; i < filterValuesLength; i++) {
				columnKeys[i] = new Integer(columnId[i]);
			}
			
			List<Map<String, Object>> filteredList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < filterValuesLength; i++) {
				filterValues[i] = filterValues[i].trim();
			}
			
			if (filterMethod == IReferenceConstants.FILTER_INCLUDE_MATCH_ONE) {
				String columnValue = null;
				Map<String, Object> rowData = null;
				
				for (int i = 0 ; i < numberOfRows; i++) {
					rowData = data.get(i);
					for (int j = 0; j < filterValuesLength; j++) {
						columnValue = (String) rowData.get(columnKeys[j]);
						if (columnValue.equals(filterValues[j])){
							filteredList.add(rowData);
							break;
						}
					}
				}
			} else if (filterMethod == IReferenceConstants.FILTER_INCLUDE_MATCH_ALL) {
				String columnValue = null;
				Map<String, Object> rowData = null;
				boolean matchFound = true;
				
				for (int i = 0; i < numberOfRows; i++) {
					matchFound = true;
					rowData = data.get(i);
					
					for (int j = 0; j < filterValuesLength; j++) {
						columnValue = (String) rowData.get(columnKeys[j]);
						if (!columnValue.equals(filterValues[j])) {
							matchFound = false;
							break;
						}
					}
					if (matchFound){
						filteredList.add(rowData);
					}
				}
			} else if (filterMethod == IReferenceConstants.FILTER_EXCLUDE_MATCH_ALL){
				String columnValue = null;
				Map<String, Object> rowData = null;
				boolean matchFound = false;
				
				for (int i = 0; i < numberOfRows; i++) {
					matchFound = true;
					rowData = data.get(i);
					
					for (int j = 0; j < filterValuesLength; j++) {
						columnValue = (String) rowData.get(columnKeys[j]);
						if (!columnValue.equals(filterValues[j])) {
							matchFound = false;
							break;
						}
					}
					if (matchFound) {
						filteredList.add(rowData);
					}
				}
			} else if (filterMethod == IReferenceConstants.FILTER_EXCLUDE_MATCH_ONE){
				String columnValue = null;
				Map<String, Object> rowData = null;
				boolean matchFound = false;
				
				for (int i = 0; i < numberOfRows; i++) {
					matchFound = false;
					rowData = data.get(i);
					
					for (int j = 0; j < filterValuesLength; j++) {
						columnValue = (String) rowData.get(columnKeys[j]);
						if (columnValue.equals(filterValues[j])) {
							matchFound = true;
							break;
						}
					}
					if (!matchFound) {
						filteredList.add(rowData);
					}
				}
			}
			else {
				FwExceptionManager.handleException(new FwException(this.getClass(), new Exception("Unknown Filter method")));
			}
			return new FwReferenceTableData(filteredList);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
}
