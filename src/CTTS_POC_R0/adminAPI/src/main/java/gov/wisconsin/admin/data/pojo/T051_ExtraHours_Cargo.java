package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwCargo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Data
@Table(name = "T051_Extra_Hours")
public class T051_ExtraHours_Cargo implements FwCargo {
	
	@Id
	@Embedded
    private T051_ExtraHours_PK PK;
	
	private long extra_hrs;
	
	private char adjustment_cd;

	public T051_ExtraHours_Cargo() {}
	
	public T051_ExtraHours_Cargo(T051_ExtraHours_PK pK, long extra_hrs, char adjustment_cd) {
		super();
		PK = pK;
		this.extra_hrs = extra_hrs;
		this.adjustment_cd = adjustment_cd;
	}

	public T051_ExtraHours_PK getPK() {
		return PK;
	}

	public void setPK(T051_ExtraHours_PK pK) {
		PK = pK;
	}

	public long getExtra_hrs() {
		return extra_hrs;
	}

	public void setExtra_hrs(long extra_hrs) {
		this.extra_hrs = extra_hrs;
	}

	public char getAdjustment_cd() {
		return adjustment_cd;
	}

	public void setAdjustment_cd(char adjustment_cd) {
		this.adjustment_cd = adjustment_cd;
	}
}