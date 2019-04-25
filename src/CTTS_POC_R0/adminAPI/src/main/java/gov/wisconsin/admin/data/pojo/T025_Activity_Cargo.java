package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwCargo;

import java.sql.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Entity @Data
@Table(name = "T025_ACTIVITY")
public class T025_Activity_Cargo implements FwCargo {
	
	@Id
	@Embedded
    private T025_Activity_PK PK;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT-6")
//	@JsonDeserialize
	private Date end_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT-6")
	private Date begin_date;
	
	private String activity_nam;
	
	private Character excl_for_extra_hrs;

	public T025_Activity_Cargo() {}
	
	

	public T025_Activity_Cargo(T025_Activity_PK pK, Date end_date,
			Date begin_date, String activity_nam, Character excl_for_extra_hrs) {
		super();
		PK = pK;
		this.end_date = end_date;
		this.begin_date = begin_date;
		this.activity_nam = activity_nam;
		this.excl_for_extra_hrs = excl_for_extra_hrs;
	}



	public T025_Activity_PK getPK() {
		return PK;
	}

	public void setPK(T025_Activity_PK pK) {
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

	public String getActivity_nam() {
		return activity_nam;
	}

	public void setActivity_nam(String activity_nam) {
		this.activity_nam = activity_nam;
	}



	public Character getExcl_for_extra_hrs() {
		return excl_for_extra_hrs;
	}



	public void setExcl_for_extra_hrs(Character excl_for_extra_hrs) {
		this.excl_for_extra_hrs = excl_for_extra_hrs;
	}

	
}
