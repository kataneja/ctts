package gov.wisconsin.admin.data.pojo;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import gov.wisconsin.framework.data.FwPK;

@Embeddable @Data
public class T019_Staff_Profile_PK implements FwPK {
	private static final long serialVersionUID = -3083925208042538340L;

	@NotNull
	private String staff_id;
	
	public T019_Staff_Profile_PK() {}

	public T019_Staff_Profile_PK(String staff_id) {
		super();
		this.staff_id = staff_id;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
}