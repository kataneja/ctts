package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwPK;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable @Data
public class T049_RefData_PK implements FwPK {
	private static final long serialVersionUID = 997752912719040490L;

	@NotNull
	private char ref_table_id;
	
	@NotNull
	private String ref_data_key;

	public T049_RefData_PK() {}
	
	public T049_RefData_PK(char ref_table_id, String ref_data_key) {
		super();
		this.ref_table_id = ref_table_id;
		this.ref_data_key = ref_data_key;
	}

	public char getRef_table_id() {
		return ref_table_id;
	}

	public void setRef_table_id(char ref_table_id) {
		this.ref_table_id = ref_table_id;
	}

	public String getRef_data_key() {
		return ref_data_key;
	}

	public void setRef_data_key(String ref_data_key) {
		this.ref_data_key = ref_data_key;
	}
}
