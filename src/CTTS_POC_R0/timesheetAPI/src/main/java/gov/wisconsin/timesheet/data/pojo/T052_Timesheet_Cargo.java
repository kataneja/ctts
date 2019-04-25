package gov.wisconsin.timesheet.data.pojo;

import gov.wisconsin.framework.data.FwCargo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Data
@Table(name = "T052_Timesheet")
public class T052_Timesheet_Cargo implements FwCargo {
	
	@Id
	@Embedded
    private T052_Timesheet_PK PK;
	
	private long actual_hours;
	
	private String description;
	private String approved_flag;
	
	public T052_Timesheet_Cargo() {}
	
	public T052_Timesheet_Cargo(T052_Timesheet_PK pK, long actual_hours, String description, String approved_flag) {
		super();
		PK = pK;
		this.actual_hours = actual_hours;
		this.description = description;
		this.approved_flag = approved_flag;
	}

	public T052_Timesheet_PK getPK() {
		return PK;
	}

	public void setPK(T052_Timesheet_PK pK) {
		PK = pK;
	}

	public long getActual_hours() {
		return actual_hours;
	}

	public void setActual_hours(long actual_hours) {
		this.actual_hours = actual_hours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApproved_flag() {
		return approved_flag;
	}

	public void setApproved_flag(String approved_flag) {
		this.approved_flag = approved_flag;
	}
}
