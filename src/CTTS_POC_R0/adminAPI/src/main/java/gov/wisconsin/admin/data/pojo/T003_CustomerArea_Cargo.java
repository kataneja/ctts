package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwCargo;

import java.sql.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Data
@Table(name = "T003_CUSTOMER_AREA")
public class T003_CustomerArea_Cargo implements FwCargo {
	
	@Id
	@Embedded
    private T003_CustomerArea_PK PK;
	
	private Date end_date;
	private Date begin_date;
	
	private String customer_area_name;

	public T003_CustomerArea_Cargo() {}
			
	public T003_CustomerArea_Cargo(T003_CustomerArea_PK pK, Date end_date, Date begin_date, String customer_area_name) {
		super();
		PK = pK;
		this.end_date = end_date;
		this.begin_date = begin_date;
		this.customer_area_name = customer_area_name;
	}

	public T003_CustomerArea_PK getPK() {
		return PK;
	}

	public void setPK(T003_CustomerArea_PK pK) {
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

	public String getCustomer_area_name() {
		return customer_area_name;
	}

	public void setCustomer_area_name(String customer_area_name) {
		this.customer_area_name = customer_area_name;
	}
}
