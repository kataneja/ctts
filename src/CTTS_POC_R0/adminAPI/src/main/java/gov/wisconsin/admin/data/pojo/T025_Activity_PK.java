package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwPK;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable @Data
public class T025_Activity_PK implements FwPK {
	private static final long serialVersionUID = -8810331171292859929L;
	
	@NotNull
	private String activity_cd;

	public T025_Activity_PK() {}

	public T025_Activity_PK(String activity_cd) {
		super();
		this.activity_cd = activity_cd;
	}

	public String getActivity_cd() {
		return activity_cd;
	}

	public void setActivity_cd(String activity_cd) {
		this.activity_cd = activity_cd;
	}
	
	

	


}
