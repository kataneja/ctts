package gov.wisconsin.admin.data.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Embedded;

import gov.wisconsin.framework.data.FwCargo;

@Entity @Data
@Table(name = "T024_Category")
public class T024_Category_Cargo implements FwCargo {
	
	@Id
	@Embedded
    private T024_Category_PK PK;

	private Character billing_cd;
	private Character billable_sw;
	private Character category_group_cd;
	private Character service_request_sw;
	
	private String project_nam;
	private String category_nam;
	@Column(name="blling_category_typ")
	private String billing_category_type;
	
	public T024_Category_Cargo() {}
	
	

	public T024_Category_Cargo(T024_Category_PK pK, Character billing_cd,
			Character billable_sw, Character category_group_cd,
			Character service_request_sw, String project_nam,
			String category_nam, String billing_category_type) {
		super();
		PK = pK;
		this.billing_cd = billing_cd;
		this.billable_sw = billable_sw;
		this.category_group_cd = category_group_cd;
		this.service_request_sw = service_request_sw;
		this.project_nam = project_nam;
		this.category_nam = category_nam;
		this.billing_category_type = billing_category_type;
	}



	public T024_Category_PK getPK() {
		return PK;
	}
	
	public void setPK(T024_Category_PK pK) {
		PK = pK;
	}
	
	
	public Character getBilling_cd() {
		return billing_cd;
	}



	public void setBilling_cd(Character billing_cd) {
		this.billing_cd = billing_cd;
	}



	public Character getBillable_sw() {
		return billable_sw;
	}



	public void setBillable_sw(Character billable_sw) {
		this.billable_sw = billable_sw;
	}



	public Character getCategory_group_cd() {
		return category_group_cd;
	}



	public void setCategory_group_cd(Character category_group_cd) {
		this.category_group_cd = category_group_cd;
	}



	public Character getService_request_sw() {
		return service_request_sw;
	}



	public void setService_request_sw(Character service_request_sw) {
		this.service_request_sw = service_request_sw;
	}



	public String getProject_nam() {
		return project_nam;
	}
	
	public void setProject_nam(String project_nam) {
		this.project_nam = project_nam;
	}
	
	public String getCategory_nam() {
		return category_nam;
	}
	
	public void setCategory_nam(String category_nam) {
		this.category_nam = category_nam;
	}
	
	public String getBilling_category_type() {
		return billing_category_type;
	}
	
	public void setBilling_category_type(String billing_category_type) {
		this.billing_category_type = billing_category_type;
	}
}
