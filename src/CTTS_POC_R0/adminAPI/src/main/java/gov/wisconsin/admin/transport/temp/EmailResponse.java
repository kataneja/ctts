package gov.wisconsin.admin.transport.temp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import gov.wisconsin.framework.transport.FwResponse;

@Data @NoArgsConstructor @AllArgsConstructor
public class EmailResponse extends FwResponse {
	private String regularMessage;
	private String templateMessage;
	
	public String getRegularMessage() {
		return regularMessage;
	}
	
	public void setRegularMessage(String regularMessage) {
		this.regularMessage = regularMessage;
	}
	
	public String getTemplateMessage() {
		return templateMessage;
	}
	
	public void setTemplateMessage(String templateMessage) {
		this.templateMessage = templateMessage;
	}
}
