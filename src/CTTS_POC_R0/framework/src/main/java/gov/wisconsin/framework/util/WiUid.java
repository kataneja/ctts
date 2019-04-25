package gov.wisconsin.framework.util;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
  
/**
 * Create a new unique Wisconsing User Id, WiUid
 * Creation date: (06/04/2001 5:08:08 PM)
 * @author       John M. Kendall
 * @version      1.0.01
 */
public class WiUid {	
	
	private static int wiUidLen  = 16;
	
	public static final int FIRST  = 1;
	public static final int SECOND = 2;
	
	private static long lastTime = 0;
	private static char [] code = new char[256];
	private static int [] twister = new int[wiUidLen*2];
	private static int [] untwister = new int[wiUidLen*2];
	
	public static String preLoadValue = "ODD";
	
	private static final Object wiUIDlock = new Object();
	
	static { 	
		for (char charI = 0; charI<code.length; charI++) { 
			code[charI] = charI;
		}
		
		code['0'] = 'M';
		code['1'] = 'G';
		code['2'] = 'z';
		code['3'] = 'i';
		code['4'] = 'n';
		code['5'] = 'u';
		code['6'] = 'V';
		code['7'] = 'Q';
		code['8'] = 'J';
		code['9'] = 'E';
		code['a'] = 'h';
		code['b'] = 'B';
		code['c'] = 'l';
		code['d'] = 'P';
		code['e'] = 'o';
		code['f'] = 'K';
		
		code['M'] = '0';
		code['G'] = '1';
		code['z'] = '2';
		code['i'] = '3';
		code['n'] = '4';
		code['u'] = '5';
		code['V'] = '6';
		code['Q'] = '7';
		code['J'] = '8';
		code['E'] = '9';
		code['h'] = 'a';
		code['B'] = 'b';
		code['l'] = 'c';
		code['P'] = 'd';
		code['o'] = 'e';
		code['K'] = 'f';

		twister[0]  =  0;
		twister[1]  =  1;
		twister[2]  = 31;
		twister[3]  = 30;
		twister[4]  =  2;
		twister[5]  = 29;
		twister[6]  = 28;
		twister[7]  = 27;
		twister[8]  =  3;
		twister[9]  =  4;
		twister[10] =  5;
		twister[11] = 26;
		twister[12] = 25;
		twister[13] = 24;
		twister[14] = 23;
		twister[15] =  6;
		twister[16] =  7;
		twister[17] = 22;
		twister[18] =  8;
		twister[19] =  9;
		twister[20] = 21;
		twister[21] = 20;
		twister[22] = 19;
		twister[23] = 10;
		twister[24] = 11;
		twister[25] = 12;
		twister[26] = 18;
		twister[27] = 13;
		twister[28] = 17;
		twister[29] = 14;
		twister[30] = 16;
		twister[31] = 15;

		untwister[0]  =  0;
		untwister[1]  =  1;
		untwister[31] =  2;
		untwister[30] =  3;
		untwister[2]  =  4;
		untwister[29] =  5;
		untwister[28] =  6;
		untwister[27] =  7;
		untwister[3]  =  8;
		untwister[4]  =  9;
		untwister[5]  = 10;
		untwister[26] = 11;
		untwister[25] = 12;
		untwister[24] = 13;
		untwister[23] = 14;
		untwister[6]  = 15;
		untwister[7]  = 16;
		untwister[22] = 17;
		untwister[8]  = 18;
		untwister[9]  = 19;
		untwister[21] = 20;
		untwister[20] = 21;
		untwister[19] = 22;
		untwister[10] = 23;
		untwister[11] = 24;
		untwister[12] = 25;
		untwister[18] = 26;
		untwister[13] = 27;
		untwister[17] = 28;
		untwister[14] = 29;
		untwister[16] = 30;
		untwister[15] = 31;	
	}
		 
	/**
	 * Decode WiUid from e-mail transfer.
	 * Creation date: (07/17/2001 4:19:34 PM)
	 * @return java.lang.String
	 * @param wiUid java.lang.String
	 */
	public static String decodeWiUid(String wiUidCode) {   
		String wiUid = FwConstants.EMPTY_STRING;
		
		if (wiUidCode != null) {
			for (int i = 0; i < wiUidCode.length(); i++) {	
				wiUid = wiUid + code[wiUidCode.charAt(i)];
			}
		}
		
		return wiUid;
	}
	
	/**
	 * Encode WiUid for e-mail transfer.
	 * Creation date: (07/17/2001 4:19:34 PM)
	 * @return java.lang.String
	 * @param wiUid java.lang.String
	 */
	public static String encodeWiUid(String wiUid) {   
		String wiUidCode = FwConstants.EMPTY_STRING;
		
		if (wiUid != null) {
			for (int i = 0; i < wiUid.length(); i++) {	
				wiUidCode = wiUidCode + code[wiUid.charAt(i)];
			} 
		}
		return wiUidCode;
	}
	
	/**
	 * Returns the length of the Wisonsin User Id string, wiUid.
	 * Creation date: (06/21/2002 12:45:12 PM)
	 * @return int
	 */
	public static int getLength() {
		return wiUidLen;
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (06/05/2001 9:37:07 AM)
	 * @return java.lang.String
	 */
	public static synchronized String getNewWiUid() {	
		// get a new date greater than the last one by at least 10 milliseconds.	
		
		GregorianCalendar calendar  = new GregorianCalendar();
		// Milliseconds since January 1, 1970, 00:00:00 GMT / 10 
		long newTime = ((calendar.getTime()).getTime()) / 10;  
		
		synchronized(wiUIDlock) {
			while (lastTime >= newTime) {	
				//  Fall back.  
				//  Could go to sleep for an hour if system not on GMT.
				//  Expect (lastTime - newTime ) to equal zero normally	
				try {
					wiUIDlock.wait(((lastTime - newTime) * 10) + 5);
				}
				catch (InterruptedException ie ) {}
				
				calendar  = new GregorianCalendar();
				newTime = ((calendar.getTime()).getTime()) / 10;
			}
			lastTime = newTime;
		}
		
		// Fall back.  
		// Could get overlap because of local time change if system is running GMT. 
		String year = Integer.toString(calendar.get(Calendar.YEAR));
		int monthValue = calendar.get(Calendar.MONTH) + 1;
		
		String month = Integer.toString(monthValue);
		if (month.length() == 1) {
			month = '0' + month;
		}
		
		String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		if (day.length() == 1) {
			day = '0' + day;
		}
		
		String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		if (hour.length() == 1) {
			hour = '0' + hour;
		}
		
		String min = Integer.toString(calendar.get(Calendar.MINUTE));
		if (min.length() == 1) {
			min = '0' + min;
		}
		
		String sec = Integer.toString(calendar.get(Calendar.SECOND));
		if (sec.length() == 1) {
			sec = '0' + sec;
		}
		
		String milli = Integer.toString(calendar.get(Calendar.MILLISECOND));
		while (milli.length() < 3) {	
			milli = '0' + milli;
		}
		
		String newWiUid = year + month + day + hour + min + sec + milli.substring(0,2);
		return newWiUid;
	}
	
	/**
	 * Twist and encode two strings into one using proprietary method
	 * The lenght of string one plus the lenght of string two must be 32.
	 * Creation date: (09/21/2001 8:26:07 AM)
	 * @return java.lang.String
	 * @param first java.lang.String
	 * @param second java.lang.String
	 */
	public static String twist(String first, String second) throws Exception {	
		String work = FwConstants.EMPTY_STRING;
		
		try {
			// 1. Twist to wiUid values
			// 2. Convert to Hex
			// 3. Encode result
			
			long firstLong   = 0;
			long secondLong  = 0;
			String firstHex  = null;
			String secondHex = null;
			work = first + second;
		
			if (work.length() != wiUidLen*2) {	
				throw new Exception("WiUid.twist: Logic: Parameter length error." + " first=" + first + " second=" + second );
			}
			 
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < work.length(); i++) {	
				result.append(work.charAt(twister[i]));
			}
		
			try {	
				firstLong = Long.parseLong(result.substring(0,wiUidLen));
				firstHex  = Long.toHexString(firstLong);
				 
				secondLong = Long.parseLong(result.substring(wiUidLen));
				secondHex  = Long.toHexString(secondLong);
			} catch (NumberFormatException nfe) {	
				throw new Exception("WiUid.twist: NumberFormatException:" + " first=" + first + " second=" + second + " Msg="+nfe.getMessage() );
			}
		
			while (firstHex.length() > secondHex.length()) {	
				secondHex = '0' + secondHex;
			}
			 
			while (secondHex.length() > firstHex.length()) {	
				firstHex  = '0' + firstHex;
			}
		
			work = WiUid.encodeWiUid(firstHex + secondHex);
		} catch (Exception e) {
			FwExceptionManager.handleException(WiUid.class, e);
		}
		
		return work;
	}
	
	/**
	 * Untwist, decode and return one pair of the twisted string pair.
	 * Input string must be 32 bytes long.
	 * Each pair returned is 16 bytes long.
	 * Creation date: (09/21/2001 9:19:39 AM)
	 * @return java.lang.String
	 * @param which int
	 * @param twisted java.lang.String
	 */
	public static String untwist(int which, String twisted) throws Exception {	
		String result = null;
		
		try {
			// 3. UnTwist to wiUid values
			// 2. Convert from Hex
			// 1. decode result
		
			long firstLong    = 0;
			long secondLong   = 0;
			String firstHex   = null;
			String secondHex  = null;
			String firstHalf  = null;
			String secondHalf = null;
			int halfLength    = twisted.length() / 2;
		
			if (twisted.length() != halfLength*2) {	
				throw new Exception("03060204");
			}
			
			String work = WiUid.decodeWiUid(twisted);
		
			firstHex = work.substring(0, halfLength);
			secondHex = work.substring(halfLength);
		
			try {	
				firstLong  = Long.parseLong(firstHex, 16);
				secondLong = Long.parseLong(secondHex, 16);
			} catch( NumberFormatException nfe ) {	
				throw new Exception("03060205");
			}
			
			firstHalf = Long.toString(firstLong);
			secondHalf = Long.toString(secondLong);
		
			if (firstHalf.length() > wiUidLen) {	
				firstHalf = firstHalf.substring(0, wiUidLen);
			} 
			else {	
				while(firstHalf.length() < wiUidLen) { 
					firstHalf = '0' + firstHalf;
				}
			}
		
			if (firstHalf.length() > wiUidLen) {	
				secondHalf = secondHalf.substring(0, wiUidLen);
			} 
			else {	
				while(secondHalf.length() < wiUidLen) { 
					secondHalf = '0' + secondHalf;
				}
			}
		
			work = firstHalf + secondHalf;
			StringBuffer untwisted = new StringBuffer();
			for (int i = 0; i < work.length(); i++) {	
				untwisted.append( work.charAt(untwister[i]));
			}
			
			switch (which) {	
				case  1: result = untwisted.substring(0,wiUidLen); break; 
				case  2: result = untwisted.substring(wiUidLen);   break; 
				default: throw new Exception("03060203");
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(WiUid.class, e);
		}
	
		return result;	
	}
	
	/**
	 * This method returns the current GMT time.  
	 * Creation date: (10/31/2003 10:01:24 AM)
	 * @return java.lang.String
	 */
	public static String getGMT() {
		String newGMT = null;
		
		try {
//			Getting the Current Local Time
			int timeOffsetFromGmtInHouts = 0;
			GregorianCalendar calendar = new GregorianCalendar();
			TimeZone objTimeZone       = TimeZone.getTimeZone("America/Chicago");
		
			//Getting the Time offset from GMT in  MilliSecond for Central Time
			int timeOffsetFromGmtInMilliseconds = objTimeZone.getOffset(
									GregorianCalendar.AD,
									calendar.get(Calendar.YEAR),
									calendar.get(Calendar.MONTH),
									calendar.get(Calendar.DATE),
									calendar.get(Calendar.DAY_OF_WEEK),
									calendar.get(Calendar.MILLISECOND)
									);
			timeOffsetFromGmtInMilliseconds = Math.abs(timeOffsetFromGmtInMilliseconds);
			//Converting Milisecond to Hr
			timeOffsetFromGmtInHouts = (timeOffsetFromGmtInMilliseconds) / (60*60*1000);
			//System.out.println("The  Offset Time in Hrs"+intGMTOffSetInHrs);
			
		
			//Adding GMT offset time to the current calendar 
			calendar.add(Calendar.HOUR,timeOffsetFromGmtInHouts);
			String year  = Integer.toString(calendar.get(Calendar.YEAR));
			
			//Since the Grecorian calandar Month,is indexed 0 to 11, 1 is added
			int monthValue = calendar.get(Calendar.MONTH) + 1; 
			String month = Integer.toString(monthValue);
			if (month.length() == 1) {
				month = '0' + month;
			}
			
			String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
			if (day.length() == 1) {
				day = '0' + day;	
			}
		
			String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
			if (hour.length() == 1) {
				hour = '0' + hour;
			}
			
			String min = Integer.toString(calendar.get(Calendar.MINUTE));
			if (min.length() == 1) {
				min = '0' + min;
			}
			
			String sec = Integer.toString(calendar.get(Calendar.SECOND));
			if (sec.length() == 1) {
				sec = '0' + sec;
			}
			
			String milli = Integer.toString(calendar.get(Calendar.MILLISECOND));
			while (milli.length() < 3) {	
				milli = '0' + milli;
			}
			
			newGMT = year + month + day + hour + min + sec + milli.substring(0,2);
		} catch (Exception e) {
			FwExceptionManager.handleException(WiUid.class, e);
		}

		return newGMT;
	}
}
