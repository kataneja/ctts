package gov.wisconsin.timesheet.transport.request;

import gov.wisconsin.framework.transport.FwRequest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SendTimesheetEmailRequest extends FwRequest {
	private List<String> emailAddresses;

	public List<String> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
}
