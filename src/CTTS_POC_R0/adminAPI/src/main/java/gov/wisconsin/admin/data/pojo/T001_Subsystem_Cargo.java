package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwCargo;

import java.sql.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Data
@Table(name = "T001_Subsystem")
public class T001_Subsystem_Cargo implements FwCargo {
	
	@Id
	@Embedded
    private T001_Subsystem_PK PK;
	
	private Date end_date;
	private Date begin_date;
	
	private char customer_area_id;
	
	private String subsystem_name;

	public T001_Subsystem_Cargo() {}
	
	public T001_Subsystem_Cargo(T001_Subsystem_PK pK, Date end_date, Date begin_date, char customer_area_id, String subsystem_name) {
		super();
		PK = pK;
		this.end_date = end_date;
		this.begin_date = begin_date;
		this.customer_area_id = customer_area_id;
		this.subsystem_name = subsystem_name;
	}

	public T001_Subsystem_PK getPK() {
		return PK;
	}

	public void setPK(T001_Subsystem_PK pK) {
		PK = pK;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public char getCustomer_area_id() {
		return customer_area_id;
	}

	public void setCustomer_area_id(char customer_area_id) {
		this.customer_area_id = customer_area_id;
	}

	public String getSubsystem_name() {
		return subsystem_name;
	}

	public void setSubsystem_name(String subsystem_name) {
		this.subsystem_name = subsystem_name;
	}
}
