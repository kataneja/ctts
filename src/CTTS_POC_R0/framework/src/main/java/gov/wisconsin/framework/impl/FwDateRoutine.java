package gov.wisconsin.framework.impl;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.IReferenceTableData;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class FwDateRoutine extends FwBaseClass {
	
	private static List<String> MONTHS = new ArrayList<String>();
	private static final String JANUARY   = "JAN";
	private static final String FEBBRUARY = "FEB";
	private static final String MARCH     = "MAR";
	private static final String APRIL     = "APR";
	private static final String MAY       = "MAY";
	private static final String JUNE      = "JUN";
	private static final String JULY      = "JUL";
	private static final String AUGUST    = "AUG";
	private static final String SEPTEMBER = "SEP";
	private static final String OCTOBER   = "OCT";
	private static final String NOVEMBER  = "NOV";
	private static final String DECEMBER  = "DEC";
	public final static String defaultDay = "-01";
	private static final int HOUR_MILLIES = 60 * 60 * 1000;
	private static final long DAY_MILLIES = 24 * HOUR_MILLIES;

	static {
		//ArrayList<String> MONTHS = new ArrayList<String>();
		//MONTHS = new ArrayList<String>();
		MONTHS.add(OCTOBER);		
		MONTHS.add(NOVEMBER);
		MONTHS.add(DECEMBER);
		MONTHS.add(JANUARY);
		MONTHS.add(FEBBRUARY);
		MONTHS.add(MARCH);
		MONTHS.add(APRIL);
		MONTHS.add(MAY);
		MONTHS.add(JUNE);
		MONTHS.add(JULY);
		MONTHS.add(AUGUST);
		MONTHS.add(SEPTEMBER);
		MONTHS.add(OCTOBER);
		MONTHS.add(NOVEMBER);
		MONTHS.add(DECEMBER);
	}

	public int diffMonths(Date fromDate, Date toDate) {
		int months = 0;
		int sec_year = fwDate.getYear(toDate);
		int sec_month = fwDate.getMonth(toDate);
		int first_year = fwDate.getYear(fromDate);
		int first_month = fwDate.getMonth(fromDate);
		
		months = (sec_year - first_year) * 12;
		months +=  (sec_month - first_month);
		return months;
	}

	public int diffFullMonths(Date fromDate, Date toDate) {
		int full_months = 0;
		int first_day = fwDate.getDay(fromDate);
		int sec_day = fwDate.getDay(toDate);
		full_months = diffMonths(fromDate, toDate);				
		if ((sec_day < first_day) && full_months > 0) { full_months--; }
		return full_months;
	}

	public String[] getBackDatedMonths(Date aDate) {
		String[] backDatedMonths = new String[3];
		int i = MONTHS.lastIndexOf(getMonthFromDate(aDate));
		backDatedMonths[2] = (String) MONTHS.get(--i); 
		backDatedMonths[1] = (String) MONTHS.get(--i);
		backDatedMonths[0] = (String) MONTHS.get(--i);
		return backDatedMonths;
	}

	public String[] getBackDatedMonths(Date aDate, int months) {
		String[] backDatedMonths = new String[months];
		int i = MONTHS.lastIndexOf(getMonthFromDate(aDate));
		
		if(months == 1) {
			backDatedMonths[0] = (String) MONTHS.get(--i);
		}else if(months == 2) {
			backDatedMonths[1] = (String) MONTHS.get(--i);
			backDatedMonths[0] = (String) MONTHS.get(--i);
		}else if(months == 3) {
			backDatedMonths[2] = (String) MONTHS.get(--i); 
			backDatedMonths[1] = (String) MONTHS.get(--i);
			backDatedMonths[0] = (String) MONTHS.get(--i);
		}
		return backDatedMonths;
	}

	public String getMonthFromDate(Date aDate) {
		Calendar parsingCalendar = Calendar.getInstance();
		parsingCalendar.setTime(aDate);

		switch (parsingCalendar.get(Calendar.MONTH)){
			case Calendar.JANUARY:
				return JANUARY;
			case Calendar.FEBRUARY:
				return FEBBRUARY;
			case Calendar.MARCH:
				return MARCH;
			case Calendar.APRIL:
				return APRIL;
			case Calendar.MAY:
				return MAY;
			case Calendar.JUNE:
				return JUNE;
			case Calendar.JULY:
				return JULY;
			case Calendar.AUGUST:
				return AUGUST;
			case Calendar.SEPTEMBER:
				return SEPTEMBER;
			case Calendar.OCTOBER:
				return OCTOBER;
			case Calendar.NOVEMBER:
				return NOVEMBER;
			case Calendar.DECEMBER:
				return DECEMBER;
			default:
				return "-1";
		}
	}

	public Date getEndOfMonth(Date aDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(aDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new Date(calendar.getTimeInMillis());
	}

	public Date getEndOfNextMonth(Date aDate) {
		Calendar c = Calendar.getInstance();
	    c.setTime(aDate);
	    c.add(Calendar.MONTH, 1);
	    c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	    Date nextDate = new Date(c.getTimeInMillis());
	    return nextDate;
	}

	public Date getStartMonth(Date aDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(aDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return new Date(calendar.getTimeInMillis());
	}

	public Date getStartYear(Date aDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(aDate);
		int year =this.getYear(aDate);
		calendar.set(year, 0,1);
		return new Date(calendar.getTimeInMillis());
	}

	public Date addMonthToDate(Date fromDate, int no_of_months) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fromDate);
		calendar.add(Calendar.MONTH, no_of_months);
		return new Date(calendar.getTimeInMillis());
	}

	public Date subtractMonthToDate(Date fromDate, int no_of_months) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fromDate);
		calendar.add(Calendar.MONTH, -no_of_months);
		return new Date(calendar.getTimeInMillis());
	}

	public Date addDaysToDate(Date fromDate, int no_of_days) {
		long datemillies = fromDate.getTime();
		datemillies += (DAY_MILLIES*no_of_days);
		Date resultDate = new Date(datemillies); 
		return adjustDSTDifference(resultDate, fromDate);
	}

	public  Date subtractDaysFromDate(Date fromDate, int no_of_days) {
		long datemillies = fromDate.getTime();
		datemillies -= (DAY_MILLIES*no_of_days);
		Date resultDate = new Date(datemillies); 
		return adjustDSTDifference(resultDate, fromDate);
	}

	public int getTotalDays(Date fromDate, Date toDate) {
		return (int) ((toDate.getTime() - fromDate.getTime()) / DAY_MILLIES);
	} 	
	
	private  Date adjustDSTDifference(Date aNewDate, Date aOldDate){
		long datemillies = aNewDate.getTime();
		
		if(getDSTOffset(aOldDate) > getDSTOffset(aNewDate)) { datemillies += HOUR_MILLIES; } 
		else if(getDSTOffset(aOldDate) < getDSTOffset(aNewDate)) { datemillies -= HOUR_MILLIES; }
		
		return new Date(datemillies);			
	}

	private int getDSTOffset(Date aDate){
		// create a Pacific Standard Time time zone
		String[] ids = TimeZone.getAvailableIDs(-6 * 60 * 60 * 1000);
		SimpleTimeZone cst = new SimpleTimeZone(-6 * 60 * 60 * 1000, ids[0]);

		// set up rules for daylight savings time
		cst.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		cst.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

		// create a GregorianCalendar with the Central Daylight time zone
		// and the current date and time
		Calendar calendar = new GregorianCalendar(cst);
		calendar.setTime(aDate);
		return calendar.get(Calendar.DST_OFFSET)/(HOUR_MILLIES);
	}

	public Date getDate(long aYYYYMM) {
		String year = String.valueOf(aYYYYMM).substring(0, 4);
		String month = String.valueOf(aYYYYMM).substring(4, 6);
		StringBuffer sbf = new StringBuffer(10);
		sbf.append(year).append('-').append(month).append('-').append("01");
		return Date.valueOf(sbf.toString());
	}

	public String getYYYYMMDDDate(java.util.Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		return dateFormat.format(date);
	}

	public String getSqlFormatDate(String aYYYYMMDD) {
		String year = String.valueOf(aYYYYMMDD).substring(0, 4);
		String month = String.valueOf(aYYYYMMDD).substring(4, 6);
		String date = String.valueOf(aYYYYMMDD).substring(6, 8);
		StringBuffer sbf = new StringBuffer(10);
		sbf.append(year).append('-').append(month).append('-').append(date);
		return sbf.toString();
	}

	public String getYYYYMMDDDate(String aMMDDYYY) {
		String month = String.valueOf(aMMDDYYY).substring(0, 2);
		String date = String.valueOf(aMMDDYYY).substring(3, 5);
		String year = String.valueOf(aMMDDYYY).substring(6, 10);
		StringBuffer sbf = new StringBuffer(10);
		sbf.append(year).append('-').append(month).append('-').append(date);
		return sbf.toString();
	}

	public String getExpandDate(String date, String language_indicator) {
		return (getExpandDate(Date.valueOf(date), language_indicator));
	}
	
	public String convertBeginMonthToDate(String beginMonth) {		
		String replaceValue = beginMonth.substring(0,4) + "-";
		beginMonth = replaceValue.concat(beginMonth.substring(4));
		beginMonth = beginMonth.concat(defaultDay);	
		return beginMonth;
	}

	public String getExpandDate(Date date, String language_indicator) {
		int textID = 0;
		String month = FwConstants.EMPTY_STRING;
		String result = FwConstants.EMPTY_STRING;

		Calendar parsingCalendar = Calendar.getInstance();
		parsingCalendar.setTime(date);
		int parsedMonth = parsingCalendar.get(Calendar.MONTH);
		int parsedDate = parsingCalendar.get(Calendar.DAY_OF_MONTH);
		int parsedYear = parsingCalendar.get(Calendar.YEAR);

		Calendar cal1 = new GregorianCalendar(parsedYear, parsedMonth, parsedDate);
		int m = cal1.get(Calendar.MONTH);
		int jan = Calendar.JANUARY;
		int feb = Calendar.FEBRUARY;
		int mar = Calendar.MARCH;
		int apr = Calendar.APRIL;
		int may = Calendar.MAY;
		int jun = Calendar.JUNE;
		int jul = Calendar.JULY;
		int aug = Calendar.AUGUST;
		int sep = Calendar.SEPTEMBER;
		int oct = Calendar.OCTOBER;
		int nov = Calendar.NOVEMBER;
		int dec = Calendar.DECEMBER;

		if (m == jan) 	   { textID = 15034; } 
		else if (m == feb) { textID = 15035; } 
		else if (m == mar) { textID = 15036; } 
		else if (m == apr) { textID = 15037; } 
		else if (m == may) { textID = 15038; } 
		else if (m == jun) { textID = 15039; } 
		else if (m == jul) { textID = 15040; } 
		else if (m == aug) { textID = 15041; } 
		else if (m == sep) { textID = 15042; } 
		else if (m == oct) { textID = 15043; } 
		else if (m == nov) { textID = 15044; } 
		else if (m == dec) { textID = 15045; }

		month = pageManager.getDisplayText(textID, language_indicator);
		result = new StringBuffer(month).append(FwConstants.SPACE).append(parsedDate).append(",").append(FwConstants.SPACE).append(parsedYear).toString();
		return result;
	}
	
	private Calendar getCalendar(Date aDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(aDate.getTime());
		return calendar;
	}
	
	public int getYear(Date aDate) {
		Calendar cal = getCalendar(aDate);
		return cal.get(Calendar.YEAR);	 
	}

	public int getMonth(Date aDate){
		Calendar cal = getCalendar(aDate);
		switch(cal.get(Calendar.MONTH)) {
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

	public long getEffMMEquivalentLong(Date aDate) {
		int year = getYear(aDate);
		int month = getMonth(aDate);
		StringBuffer sbf = new StringBuffer(6);
		sbf.append(year);
		
		if(month < 10) { sbf.append("0"); }
		
		sbf.append(month);
		return Long.parseLong(String.valueOf(sbf));
	}
	
	public String getGivenDayMonthDate(Date aInputDate, int aInputDay) {
		int year = getYear(aInputDate);
		int month = getMonth(aInputDate);
		StringBuffer tempBuffer = new StringBuffer(2);
		StringBuffer dateBuffer = new StringBuffer(10);
		
		if(month < 10) { tempBuffer.append("0").append(month); } 
		else { tempBuffer.append(month); }
		
		dateBuffer.append(tempBuffer).append('/');
		tempBuffer = new StringBuffer();
		
		if(aInputDay < 10) { tempBuffer.append("0").append(aInputDay); } 
		else { tempBuffer.append(aInputDay); }
		
		dateBuffer.append(tempBuffer).append('/').append(year);
		return dateBuffer.toString();
	}

	public Date addYeartoDate(Date fromDate, int no_of_years) {
		return updateNewDate(fromDate, no_of_years);	
	}

	public Date getLastDayOfMonth(Date aDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(aDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new Date(calendar.getTimeInMillis());
	}
	
	public Date getFisrtDateOfYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new Date(cal.getTime().getTime());
	}

	public Date getLastDateOfYear(int year) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new Date(cal.getTime().getTime());
	}

	private Date updateNewDate(Date date, int no_of_years) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, no_of_years);
		return new Date(calendar.getTimeInMillis());		
	}

	public int[] getBackDatedMonthsForPDF(Date aDate) {
		int[] backDatedMonths = new int[3];
		int i = MONTHS.lastIndexOf(getMonthFromDate(aDate));
		backDatedMonths[2] = getTextIdForMonth(MONTHS.get(--i)); 
		backDatedMonths[1] = getTextIdForMonth(MONTHS.get(--i));
		backDatedMonths[0] = getTextIdForMonth(MONTHS.get(--i));
		return backDatedMonths;
	}
	
	public int getTextIdForMonth(String month){
		int textID = 15034;
		
		if(JANUARY.equals(month))         { textID = 15034; } 
		else if (FEBBRUARY.equals(month)) { textID = 15035; } 
		else if (MARCH.equals(month))     { textID = 15036; } 
		else if (APRIL.equals(month))     { textID = 15037; } 
		else if (MAY.equals(month))       { textID = 15038; } 
		else if (JUNE.equals(month))      { textID = 15039; } 
		else if (JULY.equals(month))      { textID = 15040; } 
		else if (AUGUST.equals(month))    { textID = 15041; } 
		else if (SEPTEMBER.equals(month)) { textID = 15042; } 
		else if (OCTOBER.equals(month))   { textID = 15043; } 
		else if (NOVEMBER.equals(month))  { textID = 15044; } 
		else if (DECEMBER.equals(month))  { textID = 15045; }
		
		return textID;
	}

	public boolean isCARESHoliday(Date aDate) {
		IReferenceTableData data = referenceTableManager.filterDataOnSingleColumn(FwConstants.HOLIDAYS_REF_TBL, FwConstants.ENGLISH, FwConstants.caresHolidaysColumnID, 1, new String[]{"Y"});
		List<String> codeList = data.getDescriptionValuesAsList(FwConstants.holidaysColumnID);
		return codeList.contains(aDate.toString());
	}

	public boolean isFederalHoliday(Date aDate) {
		IReferenceTableData data = referenceTableManager.filterDataOnSingleColumn(FwConstants.HOLIDAYS_REF_TBL, FwConstants.ENGLISH, FwConstants.federalHolidaysColumnID, 1, new String[]{"Y"});
		List<String> codeList = data.getDescriptionValuesAsList(FwConstants.holidaysColumnID);
		return codeList.contains(aDate.toString());
	}

	public boolean isHoliday(Date aDate) {
		return (!isCARESHoliday(aDate) || !isFederalHoliday(aDate));
	}

	public boolean isWeekEnd(Date aDate) {
		Calendar cal = getCalendar(aDate);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return (Calendar.SATURDAY == day || Calendar.SUNDAY == day);
	}

	public boolean isWorkingDay(Date aDate) {
		return (!isWeekEnd(aDate) || !isHoliday(aDate));
	}

	public boolean isCARESWorkingDay(Date aDate) {
		return (!isWeekEnd(aDate) || !isCARESHoliday(aDate));
	}

	public boolean isFederalWorkingDay(Date aDate) {
		return (!isWeekEnd(aDate) || !isFederalHoliday(aDate));
	}

	public Date getLastWorkingDayOfTheMonth(Date aDate) {
		Date date = getLastDayOfMonth(aDate);
		boolean isLastWorkingDayOfMonth = isWorkingDay(date);
		
		while(isLastWorkingDayOfMonth) {
			Calendar cal = getCalendar(date);
			cal.add(Calendar.DATE, -1);
			date = new Date(cal.getTimeInMillis());
			isLastWorkingDayOfMonth = isWorkingDay(date);
		}
		return date;
	}

	public Date getLastWorkingDayOfTheMonth() {
		return getLastWorkingDayOfTheMonth(fwDate.getDate());
	}

}
