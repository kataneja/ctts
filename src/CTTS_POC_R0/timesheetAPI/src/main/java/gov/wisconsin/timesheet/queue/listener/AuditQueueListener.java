package gov.wisconsin.timesheet.queue.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AuditQueueListener {
	
	@JmsListener(destination = "audit_Dest", containerFactory="auditLCF")
	public void handleAuditMessage(String message) {
		System.out.println("------ AUDIT RECEIVED | " + message);
	}
	
}
