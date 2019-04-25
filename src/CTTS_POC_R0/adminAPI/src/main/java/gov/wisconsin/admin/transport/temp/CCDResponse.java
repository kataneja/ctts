package gov.wisconsin.admin.transport.temp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import gov.wisconsin.framework.transport.FwResponse;

@Data @NoArgsConstructor @AllArgsConstructor
public class CCDResponse extends FwResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
