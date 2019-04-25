package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwPK;

import java.sql.Date;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Embeddable @Data
public class T035_HolidayTime_PK implements FwPK {
	private static final long serialVersionUID = -8810331171292859929L;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT-5")
	private Date holiday_dt;

	public T035_HolidayTime_PK() {}
	
	public T035_HolidayTime_PK(Date holiday_dt) {
		super();
		this.holiday_dt = holiday_dt;
	}

	public Date getHoliday_dt() {
		return holiday_dt;
	}

	public void setHoliday_dt(Date holiday_dt) {
		this.holiday_dt = holiday_dt;
	}
}
