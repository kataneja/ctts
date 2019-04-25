package gov.wisconsin.admin.transport.temp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import gov.wisconsin.framework.transport.FwRequest;

@Data @NoArgsConstructor @AllArgsConstructor
public class CCDRequest extends FwRequest {
	private String table;
	private String project;
	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
}
