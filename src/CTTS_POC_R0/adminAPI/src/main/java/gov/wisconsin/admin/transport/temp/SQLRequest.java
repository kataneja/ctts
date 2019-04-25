package gov.wisconsin.admin.transport.temp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import gov.wisconsin.framework.transport.FwRequest;

@Data @NoArgsConstructor @AllArgsConstructor
public class SQLRequest extends FwRequest {

	private String category;
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
}
