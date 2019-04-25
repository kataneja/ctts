package gov.wisconsin.framework.util;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.AppConstants;
import gov.wisconsin.framework.constant.FwConstants;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisplayFormatter extends FwBaseClass {

	private FwMessageFormatter messageFormatter;  //@Autowired
	
	public static String printRatingStars(int rate, int max){
		StringBuffer imgHTML = new StringBuffer();		
		rate = rate > max? max:rate;
		rate = rate < 0? 0:rate;
		for(int i=0;i<rate;i++){
			imgHTML.append("<IMG BORDER=\"0\" SRC=\"images\\"+AppConstants.STAR_FILLED_IMG+"\">&nbsp;");		
		}
		for(int i=rate;i<max;i++){
			imgHTML.append("<IMG BORDER=\"0\" SRC=\"images\\"+AppConstants.STAR_EMPTY_IMG+"\">&nbsp;");
		}
		return imgHTML.toString();
	}

	public String getExpandedDate(String date, String language_indicator) {
		return (getExpandedDate(Date.valueOf(date), language_indicator));
	}

	public String getExpandDate(Date date, String language_indicator) {
		int textID = 0;
		String month = FwConstants.EMPTY_STRING;
		String result = FwConstants.EMPTY_STRING;

		//Get the values for the date, month and year from the given sql date
		Calendar parsingCalendar = Calendar.getInstance();
		parsingCalendar.setTime(date);
		int parsedMonth = parsingCalendar.get(Calendar.MONTH);
		int parsedDate = parsingCalendar.get(Calendar.DAY_OF_MONTH);
		int parsedYear = parsingCalendar.get(Calendar.YEAR);

		Calendar cal = new GregorianCalendar(parsedYear, parsedMonth, parsedDate);
		int m = cal.get(Calendar.MONTH);
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

		if (m == jan) {
			textID = 15034;
		} else if (m == feb) {
			textID = 15035;
		} else if (m == mar) {
			textID = 15036;
		} else if (m == apr) {
			textID = 15037;
		} else if (m == may) {
			textID = 15038;
		} else if (m == jun) {
			textID = 15039;
		} else if (m == jul) {
			textID = 15040;
		} else if (m == aug) {
			textID = 15041;
		} else if (m == sep) {
			textID = 15042;
		} else if (m == oct) {
			textID = 15043;
		} else if (m == nov) {
			textID = 15044;
		} else if (m == dec) {
			textID = 15045;
		}

		month = pageManager.getDisplayText(textID, language_indicator);
		result = new StringBuffer(month).append(FwConstants.SPACE).append(parsedDate).append(",").append(FwConstants.SPACE).append(parsedYear).toString();
		return result;
	}

	public String getExpandedDate(Date date, String language_indicator) {
		String result = FwConstants.EMPTY_STRING;
		int textID = 0;
		String day = FwConstants.EMPTY_STRING;
		String month = FwConstants.EMPTY_STRING;

		//Get the values for the date, month and year from the given sql date
		Calendar parsingCalendar = Calendar.getInstance();
		parsingCalendar.setTime(date);
		int parsedMonth = parsingCalendar.get(Calendar.MONTH);
		int parsedDate = parsingCalendar.get(Calendar.DAY_OF_MONTH);
		int parsedYear = parsingCalendar.get(Calendar.YEAR);

		//Get the constant values for the days of the week.
		Calendar cal = new GregorianCalendar(parsedYear, parsedMonth, parsedDate);
		int d = cal.get(Calendar.DAY_OF_WEEK);
		int sun = Calendar.SUNDAY;
		int mon = Calendar.MONDAY;
		int tue = Calendar.TUESDAY;
		int wed = Calendar.WEDNESDAY;
		int thu = Calendar.THURSDAY;
		int fri = Calendar.FRIDAY;
		int sat = Calendar.SATURDAY;

		if (d == sun) {
			textID = 15027;
		} else if (d == mon) {
			textID = 15028;
		} else if (d == tue) {
			textID = 15029;
		} else if (d == wed) {
			textID = 15030;
		} else if (d == thu) {
			textID = 15031;
		} else if (d == fri) {
			textID = 15032;
		} else if (d == sat) {
			textID = 15033;
		}

		day = pageManager.getDisplayText(textID, language_indicator);

		//Get the constant values for the months of the year.
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

		if (m == jan) {
			textID = 15034;
		} else if (m == feb) {
			textID = 15035;
		} else if (m == mar) {
			textID = 15036;
		} else if (m == apr) {
			textID = 15037;
		} else if (m == may) {
			textID = 15038;
		} else if (m == jun) {
			textID = 15039;
		} else if (m == jul) {
			textID = 15040;
		} else if (m == aug) {
			textID = 15041;
		} else if (m == sep) {
			textID = 15042;
		} else if (m == oct) {
			textID = 15043;
		} else if (m == nov) {
			textID = 15044;
		} else if (m == dec) {
			textID = 15045;
		}

		month = pageManager.getDisplayText(textID, language_indicator);
		result = new StringBuffer(day).append(FwConstants.SPACE).append(month).append(FwConstants.SPACE).append(parsedDate).append(",").append(FwConstants.SPACE).append(parsedYear).toString();
		return result;
	}

	public String getYYYYMMDDDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public String getMMDDYYYYDate(Date date) {
		String result = FwConstants.EMPTY_STRING;
		
		Calendar parsingCalendar = Calendar.getInstance();
		parsingCalendar.setTime(date);
		int parsedMonth = parsingCalendar.get(Calendar.MONTH);
		int parsedDate = parsingCalendar.get(Calendar.DAY_OF_MONTH);
		int parsedYear = parsingCalendar.get(Calendar.YEAR);

		result = new StringBuffer().append(parsedMonth + 1).append("/").append(parsedDate).append("/").append(parsedYear).toString();
		return result;
	}

	public String getMMDDYYYYDate(String yyyy_mm_dd) {
		String result = FwConstants.EMPTY_STRING;
		String yyyy = yyyy_mm_dd.substring(0, 4);
		String mm = yyyy_mm_dd.substring(5, 7);
		String dd = yyyy_mm_dd.substring(8);

		result = new StringBuffer(mm).append("/").append(dd).append("/").append(yyyy).toString();
		return result;
	}

	public String getMMYYYYDate(String yyyy_mm) {
		String result = FwConstants.EMPTY_STRING;
		String yyyy = yyyy_mm.substring(0, 4);
		String mm = yyyy_mm.substring(4);
		result = new StringBuffer(mm).append("/").append(yyyy).toString();
		return result;
	}

	public String getYYYYMMDate(String mm_yyyy) {
		String result = FwConstants.EMPTY_STRING;
		String yyyy = mm_yyyy.substring(3, 7);
		String mm = mm_yyyy.substring(0, 2);
		result = new StringBuffer(yyyy).append(mm).toString();
		return result;
	}

	public String getYYYYMMDDDate(String mm_dd_yyyy) {
		String result = FwConstants.EMPTY_STRING;
		String yyyy = mm_dd_yyyy.substring(6, 10);
		String mm = mm_dd_yyyy.substring(0, 2);
		String dd = mm_dd_yyyy.substring(3, 5);
		result = new StringBuffer(yyyy).append("-").append(mm).append("-").append(dd).toString();
		return result;
	}

	public String getMonthName(Date date, String language) {
		int textID = 0;
		String month = FwConstants.EMPTY_STRING;
		
		Calendar parsingCalendar = Calendar.getInstance();
		parsingCalendar.setTime(date);
		int parsedMonth = parsingCalendar.get(Calendar.MONTH);
		int parsedDate = parsingCalendar.get(Calendar.DAY_OF_MONTH);
		int parsedYear = parsingCalendar.get(Calendar.YEAR);

		//Get the constant values for the months of the year.
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
		if (m == jan) {
			textID = 15034;
		} else if (m == feb) {
			textID = 15035;
		} else if (m == mar) {
			textID = 15036;
		} else if (m == apr) {
			textID = 15037;
		} else if (m == may) {
			textID = 15038;
		} else if (m == jun) {
			textID = 15039;
		} else if (m == jul) {
			textID = 15040;
		} else if (m == aug) {
			textID = 15041;
		} else if (m == sep) {
			textID = 15042;
		} else if (m == oct) {
			textID = 15043;
		} else if (m == nov) {
			textID = 15044;
		} else if (m == dec) {
			textID = 15045;
		}
		
		month = pageManager.getDisplayText(textID, language);
		return month;
	}

	public String getExpandedMonthYear(
		Date date,
		String language_indicator) {
		String result = FwConstants.EMPTY_STRING;
		int textID = 0;
		String month = FwConstants.EMPTY_STRING;

		//Get the values for the date, month and year from the given sql date
		Calendar parsingCalendar = Calendar.getInstance();
		parsingCalendar.setTime(date);
		int parsedMonth = parsingCalendar.get(Calendar.MONTH);
		int parsedDate = parsingCalendar.get(Calendar.DAY_OF_MONTH);
		int parsedYear = parsingCalendar.get(Calendar.YEAR);

		//Get the constant values for the months of the year.
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

		if (m == jan) {
			textID = 15034;
		} else if (m == feb) {
			textID = 15035;
		} else if (m == mar) {
			textID = 15036;
		} else if (m == apr) {
			textID = 15037;
		} else if (m == may) {
			textID = 15038;
		} else if (m == jun) {
			textID = 15039;
		} else if (m == jul) {
			textID = 15040;
		} else if (m == aug) {
			textID = 15041;
		} else if (m == sep) {
			textID = 15042;
		} else if (m == oct) {
			textID = 15043;
		} else if (m == nov) {
			textID = 15044;
		} else if (m == dec) {
			textID = 15045;
		}

		month = pageManager.getDisplayText(textID, language_indicator);
		result = new StringBuffer(month).append(FwConstants.SPACE).append(parsedYear).toString();
		return result;
	}

	public String getFormatedPhNumber(String phoneNumber) {
		StringBuffer pNumber = new StringBuffer();
		if(phoneNumber != null && phoneNumber.trim().length() == 10){
			String phNum = phoneNumber.trim();
			if (phNum.startsWith("800") || phNum.startsWith("866") || phNum.startsWith("877") || phNum.startsWith("888")) { 
				pNumber.append("1-");
				pNumber.append(phNum.substring(0, 3));
				pNumber.append("-");
				pNumber.append(phNum.substring(3, 6));
				pNumber.append("-");
				pNumber.append(phNum.substring(6));
			}
			 else if(phNum.equals("0000000000")) {
				return FwConstants.SPACE;
			}
			else {
				pNumber.append("(");
				pNumber.append(phNum.substring(0, 3));
				pNumber.append(") ");
				pNumber.append(phNum.substring(3, 6));
				pNumber.append("-");
				pNumber.append(phNum.substring(6));
			}
		}
		return pNumber.toString();
	}

	public String getCurrencyFormat(String amount) {
		return (java.text.NumberFormat.getCurrencyInstance().format(Double.parseDouble(amount)).substring(1));
	}

	public String getStringWithPaddedZeros(String number, int maxLength){
		int numberLength = number.length();
		StringBuffer paddedCase = null;
		if(numberLength < maxLength) {
			paddedCase = new StringBuffer();
			int counter = maxLength - numberLength;
			for (int m = 0; m < counter; m++) 
			{
				paddedCase.append("0");
			}
			number=paddedCase.append(number).toString();
		}
		return number;
	}

	public String getFormattedTimeStamp(String timeStampString, String langInd) {
		Timestamp timestamp = Timestamp.valueOf(timeStampString);
		StringBuffer finalDateStamp = new StringBuffer();
		
		Calendar parsingCalendar = Calendar.getInstance();
		parsingCalendar.setTime(timestamp);
		int parsedHours = parsingCalendar.get(Calendar.HOUR);
		int parsedDate = parsingCalendar.get(Calendar.DAY_OF_MONTH);
		int parsedYear = parsingCalendar.get(Calendar.YEAR);
		int parsedMinutes = parsingCalendar.get(Calendar.MINUTE);
		
		Date date = new Date(timestamp.getTime());
		finalDateStamp.append(getMonthName(date, langInd))
		.append(FwConstants.SPACE)
		.append(parsedDate)
		.append(FwConstants.COMMA)
		.append(FwConstants.SPACE)
		.append(parsedYear)
		.append(FwConstants.SPACE);

		if(langInd.equals(FwConstants.SPANISH)){
			finalDateStamp.append(AppConstants.AT_SPANISH);
		} else {
			finalDateStamp.append(AppConstants.AT);
		}
		finalDateStamp.append(FwConstants.SPACE);
					
		String strHour = null;
		String strAMPM = AppConstants.ANTE_MERIDIAN;
		
		if(parsedHours >= 12) {
			strHour = (parsedHours == 12) ? "12" : String.valueOf(parsedHours - 12);
			strAMPM = AppConstants.POST_MERIDIAN;
		} 
		else {
			strHour = (parsedHours == 0) ? "12" : String.valueOf(parsedHours);
		}
	
		if(strHour.length() == 1) {
			finalDateStamp.append(AppConstants.ZERO);
		}
		
		finalDateStamp.append(strHour).append(":");
		
		if(parsedMinutes < 10)	{	
			finalDateStamp.append(AppConstants.ZERO);
		}
		
		finalDateStamp.append(parsedMinutes).append(FwConstants.SPACE).append(strAMPM);
		return finalDateStamp.toString();
	}
	
	public StringBuffer convertListToStringbuffer(List<Object> list, String and) {
		int listSize = list.size();
		StringBuffer aa = new StringBuffer();
		
		for(int i = 0; i < listSize; i++) {
			if(list.size() > 2 && i == list.size()-1) {
				aa.append(FwConstants.SPACE);
				aa.append(and);
				aa.append(FwConstants.SPACE);				
			} else if(i != 0 && list.size() > 2) {	
				aa.append(", ");
			} else if(list.size() == 2 && i != 0) {
				aa.append(FwConstants.SPACE);
				aa.append(and);		
				aa.append(FwConstants.SPACE);
			}
			aa.append(list.get(i));
		}
		
		return aa;
	}

	public String getMMDDYYDate(String aInputDate) {
		StringBuffer outputDateBuffer = new StringBuffer(6);
		// Get the value for the year,month and day from the given string date
		String year_of_date = aInputDate.substring(2,4);
		String month_of_date = aInputDate.substring(5,7);
		String day_of_date = aInputDate.substring(8,10);
		outputDateBuffer.append(month_of_date).append(day_of_date).append(year_of_date);
		return outputDateBuffer.toString();
	}

	public String getMMDDYYYY(String aInputDate) {
		StringBuffer outputDateBuffer = new StringBuffer(8);
		//Get the values for the year, month and day from the given string date
		String year = aInputDate.substring(0, 4);
		String month = aInputDate.substring(5, 7);
		String day = aInputDate.substring(8);
		outputDateBuffer.append(month).append(day).append(year);
		return outputDateBuffer.toString();
	}

	public String getStringWithPaddedSpaces(String inputValue, int numberOfChars) {
		StringBuffer outputStrVal = new StringBuffer(numberOfChars);
		if(inputValue == null || inputValue.equals(FwConstants.EMPTY_STRING)){
			for(int i=0;i < numberOfChars;i++){
				outputStrVal.append(FwConstants.SPACE);
			}
		}
		else if(inputValue != null){
			int inputValLenght = inputValue.length();
			outputStrVal.append(inputValue);
			for(int i=inputValLenght;i < numberOfChars;i++){
				outputStrVal.append(FwConstants.SPACE);
			}
		}
		return outputStrVal.toString();
	}

	public String convertToHiddenAccountNum(String input){
		if(input == null)
			return FwConstants.EMPTY_STRING;
		
		int inputSize = input.trim().length();
		
		//Size is less than 4 so we should display all the digits
		if(inputSize <= 4)
			return input; 
		
		//Find how many characters will need to be X's
		int protectedLength = inputSize - 4;
		
		StringBuffer newString = new StringBuffer();
		
		//Fill in X's for all characters but the last four
		for(int i = 0; i < protectedLength; i++){
			newString = newString.append("X");
		}
		
		newString = newString.append(input.substring(protectedLength));
		return newString.toString();
	}

	public java.util.Date addDays(Date date, int days){
		Calendar clndr = Calendar.getInstance();
		clndr.setTime(date);
		clndr.add(Calendar.DATE, days); 
		java.util.Date before30DaysDate =   clndr.getTime();
        return before30DaysDate;
	}
	
	public String[] getExpandedMonthsYear(Date currentDate,Date futureDate,String language_indicator){
		ArrayList<String> result=new ArrayList<String>();
		Calendar clndr = Calendar.getInstance();		
		clndr.setTime(currentDate);
		int currentMonth=clndr.get(Calendar.MONTH);
		clndr.add(Calendar.MONTH, 1);
		int nextMonth=clndr.get(Calendar.MONTH);
		Date nextMonthDate=new Date(clndr.getTime().getTime());
		clndr.setTime(futureDate);
		int futureMonth=clndr.get(Calendar.MONTH);
		
		if(currentMonth==futureMonth){
			result.add(getExpandedMonthYear(currentDate, language_indicator));
		}else if(nextMonth==futureMonth){
			result.add(getExpandedMonthYear(currentDate, language_indicator));
			result.add(getExpandedMonthYear(futureDate, language_indicator));
		}else{
			result.add(getExpandedMonthYear(currentDate, language_indicator));
			result.add(getExpandedMonthYear(nextMonthDate, language_indicator));
			result.add(getExpandedMonthYear(futureDate, language_indicator));
		}
		return result.toArray(new String[result.size()]);
	}
	
	public FwMessageFormatter getMessageFormatter() {
		return messageFormatter;
	}

	@Autowired
	public void setMessageFormatter(FwMessageFormatter messageFormatter) {
		this.messageFormatter = messageFormatter;
	}
}