package gov.wisconsin.timesheet.data.pojo;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import gov.wisconsin.framework.data.FwPK;

import java.sql.Date;

@Embeddable @Data
public class T052_Timesheet_PK implements FwPK {
	private static final long serialVersionUID = -8810331171292859929L;
	
	@NotNull
	private char staff_id;

//	@NotNull
//	private char customer_area_id;

	@NotNull
	private char category_cd;

//	@NotNull
//	private char subsystem_id;

	@NotNull
	private char activity_cd;

	@NotNull
	private Date activity_date;

	public T052_Timesheet_PK() {}
	
	public T052_Timesheet_PK(char staff_id, char customer_area_id, char category_cd, char subsystem_id, char activity_cd, Date activity_date) {
		super();
		this.staff_id = staff_id;
//		this.customer_area_id = customer_area_id;
		this.category_cd = category_cd;
//		this.subsystem_id = subsystem_id;
		this.activity_cd = activity_cd;
		this.activity_date = activity_date;
	}

	public char getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(char staff_id) {
		this.staff_id = staff_id;
	}

//	public char getCustomer_area_id() {
//		return customer_area_id;
//	}

//	public void setCustomer_area_id(char customer_area_id) {
//		this.customer_area_id = customer_area_id;
//	}

	public char getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(char category_cd) {
		this.category_cd = category_cd;
	}

//	public char getSubsystem_id() {
//		return subsystem_id;
//	}
//
//	public void setSubsystem_id(char subsystem_id) {
//		this.subsystem_id = subsystem_id;
//	}

	public char getActivity_cd() {
		return activity_cd;
	}

	public void setActivity_cd(char activity_cd) {
		this.activity_cd = activity_cd;
	}

	public Date getActivity_date() {
		return activity_date;
	}

	public void setActivity_date(Date activity_date) {
		this.activity_date = activity_date;
	}
}
