package gov.wisconsin.timeoff.scheduled;

import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Scheduled(fixedRate = 60000)
	public void scheduleFixedDelayTask() {
		System.out.println("Timeoff API Scheduled Task - " + LocalTime.now());
	}
	
}
