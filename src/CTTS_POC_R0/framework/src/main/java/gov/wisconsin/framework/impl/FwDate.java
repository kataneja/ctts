package gov.wisconsin.framework.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

@Component
public class FwDate {
	
	public int getDay(Date aDate) {
		Calendar cal = getCalendar(aDate);
		return cal.get(Calendar.DAY_OF_MONTH);		
	}

	public Calendar getCalendar(java.sql.Date aDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(aDate.getTime());
		return calendar;
	}
	
	public Timestamp getTimestamp() {
		return new Timestamp(getDate().getTime());
	}

	public String getFormattedDateTime() {
		return getTimestamp().toString();
	}

	private Date formatDate(String aDate) {
		return Date.valueOf(aDate);	
	}
	
	public Date getDate(String aDate) {
		return (aDate != null ? formatDate(aDate) : new Date(new java.util.Date().getTime()));
	}

	public Date getDate() {
		return getDate(null);
	}	
	
	public String getToday() {
		return getDate().toString();
	}

	public int getYear(Date aDate) {
		return getCalendar(aDate).get(Calendar.YEAR);
	}

	public int getMonth(Date aDate){
		Calendar cal = getCalendar(aDate);
		switch(cal.get(Calendar.MONTH)){
			case Calendar.JANUARY:
				return 1;
			case Calendar.FEBRUARY:
				return 2;
			case Calendar.MARCH:
				return 3;
			case Calendar.APRIL:
				return 4;
			case Calendar.MAY:
				return 5;
			case Calendar.JUNE:
				return 6;
			case Calendar.JULY:
				return 7;
			case Calendar.AUGUST:
				return 8;
			case Calendar.SEPTEMBER:
				return 9;
			case Calendar.OCTOBER:
				return 10;
			case Calendar.NOVEMBER:
				return 11;
			case Calendar.DECEMBER:
				return 12;
			default:
				return -1;									
		}
	}
}