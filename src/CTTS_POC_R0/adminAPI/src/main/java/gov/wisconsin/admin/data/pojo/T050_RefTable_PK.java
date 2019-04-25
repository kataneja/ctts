package gov.wisconsin.admin.data.pojo;

import lombok.Data;

import java.sql.Date;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import gov.wisconsin.framework.data.FwPK;

@Embeddable @Data
public class T050_RefTable_PK implements FwPK {
	private static final long serialVersionUID = -8810331171292859929L;
	
	@NotNull
	private Date holiday_dt;

	public T050_RefTable_PK() {}
	
	public T050_RefTable_PK(Date holiday_dt) {
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
