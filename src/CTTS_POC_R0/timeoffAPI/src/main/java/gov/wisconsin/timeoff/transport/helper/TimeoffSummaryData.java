package gov.wisconsin.timeoff.transport.helper;

public class TimeoffSummaryData {
	private String status = "";
	private String lastDayOff = "";
	private String firstDayOff = "";
	private int numberOfWorkingDays = 0;
	
	public TimeoffSummaryData() {}
	
	public TimeoffSummaryData(String status, String lastDayOff, String firstDayOff, int numberOfWorkingDays) {
		super();
		this.status = status;
		this.lastDayOff = lastDayOff;
		this.firstDayOff = firstDayOff;
		this.numberOfWorkingDays = numberOfWorkingDays;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getLastDayOff() {
		return lastDayOff;
	}
	
	public void setLastDayOff(String lastDayOff) {
		this.lastDayOff = lastDayOff;
	}
	
	public String getFirstDayOff() {
		return firstDayOff;
	}
	
	public void setFirstDayOff(String firstDayOff) {
		this.firstDayOff = firstDayOff;
	}
	
	public int getNumberOfWorkingDays() {
		return numberOfWorkingDays;
	}
	
	public void setNumberOfWorkingDays(int numberOfWorkingDays) {
		this.numberOfWorkingDays = numberOfWorkingDays;
	}
}
