package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwPK;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable @Data
public class T001_Subsystem_PK implements FwPK {
	private static final long serialVersionUID = -8810331170292859929L;
	
	@NotNull
	private char subsystem_id;

	public T001_Subsystem_PK() {}
	
	public T001_Subsystem_PK(char subsystem_id) {
		super();
		this.subsystem_id = subsystem_id;
	}

	public char getSubsystem_id() {
		return subsystem_id;
	}

	public void setSubsystem_id(char subsystem_id) {
		this.subsystem_id = subsystem_id;
	}
}
