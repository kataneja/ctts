package gov.wisconsin.framework.security.core;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserInfoLDAPAttr extends FwBaseClass {

	@Value("${LDAP_ACCT_ACTVN_LOGIN_EXPRN_DURN_YRS}")
	public String strLoginExprnTimeInYrs; 
	
	@Value("${LDAP_ACCT_ACTVN_PSSWD_EXPRN_DURN_YRS}")
	public String strPasswdExprnTimeInYrs;
	
	@Value("${LDAP_TEMP_ACCT_ACTVN_LOGIN_EXPRN_DURN_MTS}")
	public String strTimeDurn;
	
	@Value("${LDAP_MNFRM_TIME_GMT}")
	public String blnIsGMT;
	
	// These values exist in the ldap properties file, but is all set to zero. If changes are needed
	// then we possibly need to inject these values from the property file.
	public String strExprnTimeInMons = "0"; 
	public String strExprnTimeInDys  = "0"; 
	public String strExprnTimeInHrs  = "0";
	public String strExprnTimeInMts  = "0";
	public String strExprnTimeInSecs = "0";
	
	/**
	 * This method takes Input String and reads the Expiration time interval
	 * from the property file for the experation Id provided.<br>
	 * Creation date: (07/16/2001 11:11:40 AM)
	 * @param experationId java.lang.String Value of "PASSWD_EXPRN" or "LOGIN_EXPRN"
	 * @return java.lang.String
	 */
	public String getExprnTime(String experationId) {
		String strExprnTimeInYrs = strLoginExprnTimeInYrs;
		try {	
			if(FwConstants.PSSWD_EXPRN.equals(experationId)) {
				strExprnTimeInYrs = strPasswdExprnTimeInYrs;
			}
			
			String exprirationTime = getExpirationTime(strExprnTimeInYrs,
				strExprnTimeInMons,
				strExprnTimeInDys,
				strExprnTimeInHrs,
				strExprnTimeInMts,
				strExprnTimeInSecs);
			
			return exprirationTime;
		} catch(NumberFormatException nfe) {
			FwExceptionManager.handleException(this.getClass(), nfe, FwConstants.EXP_TYP_SECURITY, "NumberFormatException: Msg = " + nfe.getMessage());
		}
		return null;
	}
	
	/**
	 * This method returns adds the expiration time interval values to the 
	 * current time creating a new expiration time value.
	 * Creation date: (02/28/2003 10:01:24 AM)
	 * @return java.lang.String
	 */
	private String getExpirationTime(String strExprnTimeInYrs, String strExprnTimeInMons, String strExprnTimeInDys, String strExprnTimeInHrs, String strExprnTimeInMts, String strExprnTimeInSecs) throws NumberFormatException {
		int intExprnTimeInYrs  = 0;
		int intExprnTimeInMons = 0;
		int intExprnTimeInDys  = 0;
		int intExprnTimeInHrs  = 0;
		int intExprnTimeInMts  = 0;
		int intExprnTimeInSecs = 0;
		
		try {
			intExprnTimeInYrs  = new Integer(strExprnTimeInYrs).intValue();
			intExprnTimeInMons = new Integer(strExprnTimeInMons).intValue();
			intExprnTimeInDys  = new Integer(strExprnTimeInDys).intValue();
			intExprnTimeInHrs  = new Integer(strExprnTimeInHrs).intValue();
			intExprnTimeInMts  = new Integer(strExprnTimeInMts).intValue();
			intExprnTimeInSecs = new Integer(strExprnTimeInSecs).intValue();
		} catch(NumberFormatException nfe) {
			FwExceptionManager.handleException(this.getClass(), nfe, FwConstants.EXP_TYP_SECURITY, "NumberFormatException: Msg = " + nfe.getMessage());
		}
	
		//Adding the values to the current calendar 
		GregorianCalendar calendar  = new GregorianCalendar();
		calendar.add(Calendar.YEAR,   intExprnTimeInYrs);
		calendar.add(Calendar.MONTH,  intExprnTimeInMons);
		calendar.add(Calendar.DATE,   intExprnTimeInDys);
		calendar.add(Calendar.HOUR,   intExprnTimeInHrs);
		calendar.add(Calendar.MINUTE, intExprnTimeInMts);	
		calendar.add(Calendar.SECOND, intExprnTimeInSecs);	
		String year  = Integer.toString(calendar.get(Calendar.YEAR));
		
		//Since the Grecorian calandar Month,is indexed 0 to 11, 1 is added
		int monthValue = calendar.get(Calendar.MONTH) + 1; 
		String month = Integer.toString( monthValue );
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
		
		//Adding in the format
		String strExprnTime = year + month + day + hour + min + sec;
		
		return strExprnTime;
	}
	
	/**
	 * This method reads from Proerty File 
	 * and returns the Time required for Temporary Activation.
	 * Creation date: (07/16/2001 10:01:24 AM)
	 * @return java.lang.String
	 */
	public String getTempActvnTime() throws NumberFormatException {
		//getting the time Durn for Activation
		int	intTimeDurn = 0;
		
		try {	
			intTimeDurn = new Integer(strTimeDurn).intValue();
		} catch(NumberFormatException nfe) {
			FwExceptionManager.handleException(this.getClass(), nfe, FwConstants.EXP_TYP_SECURITY, "NumberFormatException: Msg = " + nfe.getMessage());
		}
	
		int intGMTOffSetInHrs = 0;
		GregorianCalendar calendar = new GregorianCalendar();
		TimeZone objTimeZone = TimeZone.getTimeZone("America/Chicago");
	
		//Getting the whether Mainifrm Running on Correct GMT Time
		boolean IsGMT = Boolean.parseBoolean(blnIsGMT);
		
		//If the Minframe Running on wrong GMT the offset beeing added
		if(!IsGMT) {	
			//Getting the Time offset from GMT in  MilliSecond for Central Time
			int intCTOffsetInMs = objTimeZone.getOffset(
									GregorianCalendar.AD,
									calendar.get(Calendar.YEAR),
									calendar.get(Calendar.MONTH),
									calendar.get(Calendar.DATE),
									calendar.get(Calendar.DAY_OF_WEEK),
									calendar.get(Calendar.MILLISECOND)
									);
			intCTOffsetInMs = Math.abs(intCTOffsetInMs);
			//Converting Milisecond to Hr
			intGMTOffSetInHrs = (intCTOffsetInMs) / (60*60*1000);
		}
	
		//Adding the values read from the property file and GMT offset time to the current calendar 
		calendar.add(Calendar.MINUTE,intTimeDurn);
		calendar.add(Calendar.HOUR,intGMTOffSetInHrs);
		String year  = Integer.toString( calendar.get(Calendar.YEAR) );
		
		//Since the Gregorian calandar Month,is indexed 0 to 11, 1 is added
		int monthValue = calendar.get(Calendar.MONTH) + 1; 
		String month = Integer.toString( monthValue );
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
		
		String strExprnTime = year + month + day + hour + min + sec;
		return strExprnTime;
	}
}
