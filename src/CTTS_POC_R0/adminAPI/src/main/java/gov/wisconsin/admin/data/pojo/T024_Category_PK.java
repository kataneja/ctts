package gov.wisconsin.admin.data.pojo;

import java.util.Date;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import gov.wisconsin.framework.data.FwPK;

@Embeddable @Data
public class T024_Category_PK implements FwPK {
	private static final long serialVersionUID = 3238281223509669029L;

	@NotNull
	private String category_cd;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT-6")
	private Date cat_beg_dt;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT-6")
	private Date cat_end_dt;

	public T024_Category_PK() {}
	
	

	public T024_Category_PK(String category_cd, Date cat_beg_dt, Date cat_end_dt) {
		super();
		this.category_cd = category_cd;
		this.cat_beg_dt = cat_beg_dt;
		this.cat_end_dt = cat_end_dt;
	}



	public String getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(String category_cd) {
		this.category_cd = category_cd;
	}



	public Date getCat_beg_dt() {
		return cat_beg_dt;
	}



	public void setCat_beg_dt(Date cat_beg_dt) {
		this.cat_beg_dt = cat_beg_dt;
	}



	public Date getCat_end_dt() {
		return cat_end_dt;
	}



	public void setCat_end_dt(Date cat_end_dt) {
		this.cat_end_dt = cat_end_dt;
	}

	
}
