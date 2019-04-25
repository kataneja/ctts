package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwCargo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Data
@Table(name = "T050_REF_TABLE")
public class T050_RefTable_Cargo implements FwCargo {
	
	@Id
	@Embedded
    private T050_RefTable_PK PK;
	
	private long holiday_hr;

	private String holiday_desc_txt;

	public T050_RefTable_Cargo() {}
			
	public T050_RefTable_Cargo(T050_RefTable_PK pK, long holiday_hr, String holiday_desc_txt) {
		super();
		PK = pK;
		this.holiday_hr = holiday_hr;
		this.holiday_desc_txt = holiday_desc_txt;
	}

	public T050_RefTable_PK getPK() {
		return PK;
	}

	public void setPK(T050_RefTable_PK pK) {
		PK = pK;
	}

	public long getHoliday_hr() {
		return holiday_hr;
	}

	public void setHoliday_hr(long holiday_hr) {
		this.holiday_hr = holiday_hr;
	}

	public String getHoliday_desc_txt() {
		return holiday_desc_txt;
	}

	public void setHoliday_desc_txt(String holiday_desc_txt) {
		this.holiday_desc_txt = holiday_desc_txt;
	}
}
