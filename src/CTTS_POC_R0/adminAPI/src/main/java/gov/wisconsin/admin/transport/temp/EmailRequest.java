package gov.wisconsin.admin.transport.temp;

import gov.wisconsin.framework.transport.FwRequest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class EmailRequest extends FwRequest {
	
	private String message;
	private String subject;
	private List<String> toEmails;
	private List<String> ccEmails;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getToEmails() {
		return toEmails;
	}

	public void setToEmails(List<String> toEmails) {
		this.toEmails = toEmails;
	}

	public List<String> getCcEmails() {
		return ccEmails;
	}

	public void setCcEmails(List<String> ccEmails) {
		this.ccEmails = ccEmails;
	}
}
