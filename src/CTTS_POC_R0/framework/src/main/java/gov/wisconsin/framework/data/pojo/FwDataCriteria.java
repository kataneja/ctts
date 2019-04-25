package gov.wisconsin.framework.data.pojo;

import java.io.Serializable;

public class FwDataCriteria implements Serializable {
	private static final long serialVersionUID = -3117520609834213655L;
	
	private String operator;
	private String data_type;
	private String column_name;
	private String column_value;

	public FwDataCriteria() {}
	
	public FwDataCriteria(String dataType, String columnValue) {
		this.data_type = dataType;
		this.column_value = columnValue;
	}
	
	public String getColumn_name() {
		return column_name;
	}

	public String getColumn_value() {
		return column_value;
	}

	public String getData_type() {
		return data_type;
	}

	public String getOperator() {
		return operator;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public void setColumn_value(String column_value) {
		this.column_value = column_value;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
