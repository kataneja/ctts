package gov.wisconsin.framework.data.pojo;

public class FwDataSortOrder {

	private char sortOrder = 'A';
	private String columnName = null;
	public static final char asc = 'A';
	public static final char desc = 'D';
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public char getSortOrder() {
		return sortOrder;
	}

	public void setAscOrder() {
		this.sortOrder = asc;
	}

	public void setDescOrder() {
		this.sortOrder = desc;
	}
}
