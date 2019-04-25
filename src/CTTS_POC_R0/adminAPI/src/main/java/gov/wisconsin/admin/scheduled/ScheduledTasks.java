package gov.wisconsin.admin.scheduled;

import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Scheduled(fixedRate = (10 * 60000))
	public void scheduleFixedDelayTask() {
		System.out.println("Admin API Scheduled Task - " + LocalTime.now());
	}
	
}
