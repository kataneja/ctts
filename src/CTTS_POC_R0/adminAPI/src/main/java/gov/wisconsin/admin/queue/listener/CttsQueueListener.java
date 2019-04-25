package gov.wisconsin.admin.queue.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CttsQueueListener {
	
	@JmsListener(destination = "ctts_Dest", containerFactory="cttsLCF")
	public void handleCTTSMessage(String message) {
		System.out.println("------  CTTS RECEIVED | " + message);
	}
}
