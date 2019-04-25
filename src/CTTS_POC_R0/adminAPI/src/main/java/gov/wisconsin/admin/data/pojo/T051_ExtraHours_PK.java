package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwPK;

import java.sql.Date;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable @Data
public class T051_ExtraHours_PK implements FwPK {
	private static final long serialVersionUID = -8810331171292859929L;
	
	@NotNull
	private char staff_id;
	
	@NotNull
	private char cr_dbt_cd;

	@NotNull
	private Date end_date;
	
	@NotNull
	private Date begin_date;
	
	public T051_ExtraHours_PK() {}
	
	public T051_ExtraHours_PK(char staff_id, char cr_dbt_cd, Date end_date, Date begin_date) {
		super();
		this.staff_id = staff_id;
		this.cr_dbt_cd = cr_dbt_cd;
		this.end_date = end_date;
		this.begin_date = begin_date;
	}

	public char getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(char staff_id) {
		this.staff_id = staff_id;
	}

	public char getCr_dbt_cd() {
		return cr_dbt_cd;
	}

	public void setCr_dbt_cd(char cr_dbt_cd) {
		this.cr_dbt_cd = cr_dbt_cd;
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
}
