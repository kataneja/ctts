package gov.wisconsin.admin.transport.temp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import gov.wisconsin.framework.transport.FwResponse;

@Data @NoArgsConstructor @AllArgsConstructor
public class JMSResponse extends FwResponse {
	private String cttsResponse;
	private String auditResponse;
	
	public String getCttsResponse() {
		return cttsResponse;
	}
	
	public void setCttsResponse(String cttsResponse) {
		this.cttsResponse = cttsResponse;
	}
	
	public String getAuditResponse() {
		return auditResponse;
	}
	
	public void setAuditResponse(String auditResponse) {
		this.auditResponse = auditResponse;
	}
}
