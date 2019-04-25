package gov.wisconsin.admin.transport.temp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import gov.wisconsin.framework.transport.FwRequest;

@Data @NoArgsConstructor @AllArgsConstructor
public class JMSRequest extends FwRequest {
	private String cttsMessage;
	private String auditMessage;
	
	public String getCttsMessage() {
		return cttsMessage;
	}
	
	public void setCttsMessage(String cttsMessage) {
		this.cttsMessage = cttsMessage;
	}
	
	public String getAuditMessage() {
		return auditMessage;
	}
	
	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}
}
