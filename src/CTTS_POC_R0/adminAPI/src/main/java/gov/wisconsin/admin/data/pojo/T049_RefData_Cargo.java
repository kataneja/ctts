package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwCargo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Data
@Table(name = "T049_REF_DATA")
public class T049_RefData_Cargo implements FwCargo {
	
	@Id
	@Embedded
    private T049_RefData_PK PK;
	
	private String ref_data_desc;

	public T049_RefData_Cargo() {}
	
	public T049_RefData_Cargo(T049_RefData_PK pK, String ref_data_desc) {
		super();
		PK = pK;
		this.ref_data_desc = ref_data_desc;
	}

	public T049_RefData_PK getPK() {
		return PK;
	}

	public void setPK(T049_RefData_PK pK) {
		PK = pK;
	}

	public String getRef_data_desc() {
		return ref_data_desc;
	}

	public void setRef_data_desc(String ref_data_desc) {
		this.ref_data_desc = ref_data_desc;
	}	
}
