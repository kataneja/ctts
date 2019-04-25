package gov.wisconsin.framework.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.base.IValidation;
import gov.wisconsin.framework.constant.AppConstants;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.IReferenceTableData;
import gov.wisconsin.framework.impl.FwDateRoutine;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FwValidator extends FwBaseClass implements IValidation {
	private final static int beginMonthLength = 6;
	
	private FwDateRoutine dateRoutine;	//@Autowired

	public boolean isNumeric(String phrase) {
		char[] phraseChrs = phrase.toCharArray();
		for(int i = 0; i < phraseChrs.length; i++) {	
			if(Character.isDigit(phraseChrs[i])) {
				continue;	
			}
			return false;
		}
		return true;
	}
	
	public boolean isDateBeforeDate(Date inputDate, Date whenDate) {
		if(inputDate != null && whenDate != null) {
			return (inputDate.before(whenDate));
		}
		return false;
	}

	public boolean isDateBeforeDate(String inputDate, Date whenDate) {
		if(inputDate != null && whenDate != null) {
			Date date = changeDateFormat(inputDate);
			if(date == null) {
				return false;
			}
			return (date.before(whenDate));
		}
		return false;
	}

	public boolean isDateAfterDate(Date inputDate, Date whenDate) {
		if(inputDate != null && whenDate != null) {
			return inputDate.after(whenDate);
		}
		return false;
	}

	public boolean isDateAfterDate(String inputDate, Date whenDate) {
		if(inputDate != null && whenDate != null) {
			Date date = changeDateFormat(inputDate);
			if(date == null){
				return false;
			}
			return date.after(whenDate);
		}
		return false;
	}

	public boolean pastDate(Date inputDate){
		Date today = new Date();
		return inputDate.before(today) ? false : true;
	}

	public boolean pastDateInclusive(Date inputDate) {
		Date today = new Date();
		return (inputDate.before(today) || inputDate.compareTo(today) == 0) ? false : true;
	}

	public boolean futureDateInclusive(Date inputDate) {
		Date today = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = dateFormatter.format(today);
		String inDate = dateFormatter.format(inputDate);
		Date todayDt = java.sql.Date.valueOf(nowDate);
		Date inputDt = java.sql.Date.valueOf(inDate);
		
		return (inputDt.after(todayDt)|| inputDt.compareTo(todayDt) == 0) ? false : true;
	}

	public boolean futureDate(Date inputDate) {
		Date today = new Date();
		return inputDate.after(today) ? false : true;
	}

	public boolean futureDate(String inputDate) {
		Date date = changeDateFormat(inputDate);
		if (date == null){
			return false;
		}
		Date today = new Date();
		if (date.after(today))
			return false;
		else 
			return true;
	}

	public boolean rangeForDate(Date inputDate, Date upperDate, Date lowerDate) {
		if(inputDate.after(upperDate) || inputDate.before(lowerDate))
			return false;
		else
			return true;
	}

	public boolean validateDate(Date inputDate) {
		if(inputDate == null)
			return false;
		Calendar cDate = Calendar.getInstance();
		cDate.setTime(inputDate);
		
		if((cDate.get(Calendar.DAY_OF_MONTH)>=1 && cDate.get(Calendar.DAY_OF_MONTH)<=31)&&
		(cDate.get(Calendar.MONTH) >=0 && cDate.get(Calendar.MONTH)<=11)&&
		(cDate.get(Calendar.YEAR)>=1001 && cDate.get(Calendar.YEAR)<=5000))
			return true;
		else
			return false;
	}
	
	public boolean validateDate(long inputDate) {
		String inputValue = String.valueOf(inputDate);

		if (inputValue.length() != beginMonthLength)
			return false;
		inputValue = dateRoutine.convertBeginMonthToDate(inputValue);

		return (validateDate(java.sql.Date.valueOf(inputValue)));
	}

	public boolean validateDate(String aDate) {	
		if(aDate!=null & aDate.length()==10){
			Date date = changeDateFormat(aDate);
			if(date == null){
				return false;
			}
			else{
				Calendar cDate = Calendar.getInstance();
				cDate.setTime(date);
				if((cDate.get(Calendar.DAY_OF_MONTH)>=1 && cDate.get(Calendar.DAY_OF_MONTH)<=31)&&
					(cDate.get(Calendar.MONTH) >=0 && cDate.get(Calendar.MONTH)<=11)&&
					(cDate.get(Calendar.YEAR)>=1000 && cDate.get(Calendar.YEAR)<=9999))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public boolean isComplete(String requiredValue) {
		if(requiredValue == null)
			return false;
		if(!requiredValue.trim().equals(FwConstants.EMPTY_STRING))
			return true;
		else
			return false;
	}

	public boolean isAtleastOneComplete(String [] requiredValues) {
		if(requiredValues == null)
			return false;
		for(int i=0; i<requiredValues.length; i++) {
			if(!requiredValues[i].equals(FwConstants.EMPTY_STRING))
				return true;
		}
		return false;
	}

	public boolean isAlpha(String inputValue) {
   	   	if(inputValue == null)
   	   		return false;
   	   	for(int i=0; i<inputValue.length(); i++) {
		   if (!isLetter(inputValue.charAt(i)))
			   return false;
   	   	}
   	   	return true;
	}

	public boolean isAlphaWithSpace(String inputValue) {
		if(inputValue == null)
			return false;
		for(int i=0; i<inputValue.length(); i++)  {
			if (!isLetter(inputValue.charAt(i)) && !Character.isWhitespace(inputValue.charAt(i)))
				return false;
		 }
		 return true;
	 }

	public boolean isInteger(String inputValue) {
		if(inputValue == null)
			return false;
		for(int i=0; i<inputValue.length(); i++) {
			if (!isDigit(inputValue.charAt(i)))
				return false;
		}
		return true;
	}

	public boolean isDecimal(String inputValue) {
		if(inputValue == null)
			return false;
	 	boolean dotChar = false;
		for(int i=0; i<inputValue.length(); i++) {
			if(!dotChar) {
				if(inputValue.charAt(i) == '.')
					dotChar = true;
				if(!isDigit(inputValue.charAt(i)) && !dotChar)
					return false;
			}
			else {
				if(!isDigit(inputValue.charAt(i)))
					return false;
			}
		}
		if(dotChar)
			return true;
		else
			return false;
	 }

	public boolean isAlphaNumeric(String inputValue) {
		if(inputValue == null)
			return false;
		for(int i=0; i<inputValue.length(); i++) {
			if (!isLetterOrDigit(inputValue.charAt(i)) && !Character.isWhitespace(inputValue.charAt(i)))
				return false;
		}
		return true;
	}

	public boolean isAlphaNumericForAIE(String inputValue) {
		if(inputValue == null)
			return false;
		for(int i=0; i<inputValue.length(); i++) {
			if (!Character.isLetterOrDigit(inputValue.charAt(i)) && !Character.isWhitespace(inputValue.charAt(i)))
				return false;
		}
		return true;
	}

	public boolean isStrictlyAlphaNumeric(String inputValue) {
		if(inputValue == null)
			return false;
		for(int i=0; i<inputValue.length(); i++){
			if (!isLetterOrDigit(inputValue.charAt(i)))
				return false;
		}
		return true;
	}

	public boolean isSpecialAlphaNumeric(String inputValue, char [] specialChars) {
		if(inputValue == null)
			return false;
		boolean alphaNum = true;
		int charCount =0;
		for(int i=0; i<inputValue.length(); i++){
			if (!isLetterOrDigit(inputValue.charAt(i)))
				alphaNum=false;

			for(int j=0; j<specialChars.length; j++){
				if(inputValue.charAt(i) != specialChars[j] && !alphaNum)
					charCount++;
			}
			if(charCount == specialChars.length)
				return false;
			alphaNum = true;
			charCount=0;
		}
		return true;
	}

	public boolean isSpecialAlpha(String inputValue, char [] specialChars) {
		if(inputValue == null)
			return false;
		boolean alphaNum = true;
		int charCount =0;
		for(int i=0; i<inputValue.length(); i++){
			//System.out.println(inputValue.charAt(i));
			//isDefined //isUnicodeIdentifierStart  //isLetterOrDigit
			if (!isLetter(inputValue.charAt(i)))
				alphaNum=false;
			//System.out.println(alphaNum);
			for(int j=0; j<specialChars.length; j++){
				if(inputValue.charAt(i) != specialChars[j] && !alphaNum)
					charCount++;
			}
			if(charCount == specialChars.length)
				return false;
			alphaNum = true;
			charCount=0;
		}
		return true;
	}
	
	private boolean isLetter(char c){
		if(((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))){
			return true;
		}
		return false;
	}
	
	private boolean isDigit(char c){
		if(Character.isDigit(c)){
			return true;
		}
		return false;
	}
	
	private boolean isLetterOrDigit(char c){
		if(!isLetter(c) && !isDigit(c)){
			return false;
		}
		return true;
	}

	public boolean preventSpecialChars(String inputValue, char [] specialChars){
		if(inputValue == null || specialChars == null)
			return false;
		for(int i=0; i<inputValue.length(); i++){
			for(int j=0; j<specialChars.length; j++){
				if(inputValue.charAt(i) == specialChars[j])
					return false;
			}
		}
		return true;
	}

	public boolean noSpace(String inputValue){
		if(inputValue == null)
			return false;
		for(int i=0; i<inputValue.length(); i++){
			if (Character.isWhitespace(inputValue.charAt(i)))
				return false;
		}
		return true;
	}

	public boolean isFieldEmpty(String inputValue){
		if(inputValue == null)
			return true;
			
		if(inputValue.trim().length()== 0)
			return true;
				
		return false;
	}

	public boolean isZero(String inputValue){
		if(inputValue == null || inputValue.trim().equals(FwConstants.EMPTY_STRING))
			return false;
		else if(Float.valueOf(inputValue).floatValue() == 0)
			return false;
		else
			return true;
	}

	public boolean isMaxSet(String inputValue, int maxFieldLength){
		if(inputValue == null)
			return false;
		
		if(inputValue.length() == maxFieldLength)
			return true;
		else
			return false;
	}
	
	public boolean checkMaxAllowed(String inputValue,int maxValue){
		if(inputValue == null)
			return false;
		if(Integer.valueOf(inputValue).intValue() > maxValue)
			return false;
		else 
			return true;
	}

	public boolean checkMaxLength(String inputValue, int maxLimit){
		if(inputValue == null)
			return false;
		if(inputValue.length() > maxLimit)
			return false;
		else
			return true;
	}

	public boolean isCurrency(double amount){
		String amountValue = String.valueOf(amount);
		if(amountValue == null)
			return false;
		int precIndex = amountValue.indexOf(".");
		if(amountValue.substring(precIndex).length() > 3)
			return false;
		else 
			return true;
	}
	
	public boolean isCurrency(String amountValue){
		boolean precIndex = false;
		if(amountValue == null)
			return false;
		
		if(isInteger(amountValue))
		return true;
		else
		{
			for(int i=0; i<amountValue.length(); i++)
			{
				if(amountValue.charAt(i) != '.' && !Character.isDigit(amountValue.charAt(i)))
					return false;
				else if(amountValue.charAt(i) == '.' && precIndex)
					return false;
				else if(amountValue.charAt(i) == '.' && !precIndex)
				{
					precIndex = true;
					if(amountValue.substring(i).length() > 3 || amountValue.substring(i).length() == 1 )
						return false;
				}
			}
		}
		return true;
	}
	
	public boolean isHour(String hourValue) {
		boolean precIndex = false;
		if(hourValue == null)
			return false;
	
		if(isInteger(hourValue))
			return true;
		else {
			for(int i=0; i<hourValue.length(); i++) {
				if(hourValue.charAt(i) != '.' && !Character.isDigit(hourValue.charAt(i)))
					return false;
				else if(hourValue.charAt(i) == '.' && precIndex)
					return false;
				else if(hourValue.charAt(i) == '.' && !precIndex) {
					precIndex = true;
					if(hourValue.substring(i).length() > 3 || hourValue.substring(i).length() == 1 )
						return false;
				}
			}
		}
		return true;
	}

	public boolean isEmail(String eMail) {
		if (eMail.equals(FwConstants.EMPTY_STRING)) {
			return true;
		}
		if (eMail.length() > 127) {
			return false;
		}
		int i = eMail.indexOf(' ');
		if (i != -1) {
			return false;
		}
		i = eMail.indexOf('@');

		if (i == -1) {
			return false;
		}
		if (i == 0) {
			return false;
		}
		if(i == eMail.length()-1 ){ //@ cannot be at the end of the string (ADDED)
			return false;
		}
	
		if (eMail.indexOf('@', i + 1) != -1) { // there cannot be more than one @ symbol in an email
			return false;
		}
	
		int j = eMail.indexOf('.', i + 1);
	
		if (j == i + 1) {
			return false;
		}
		if (j == eMail.length() - 1) {
			return false;
		}	
		if (eMail.charAt(eMail.length() - 1) == '.') {
			return false;
		}
		
		if(eMail.indexOf("@.") != -1) { //@ and period cannot be consecutive
			return false;
		}
		
		if(eMail.indexOf("@-") != -1) { //@ and hyphen cannot be consecutive
			return false;
		}
		
		j = eMail.indexOf("@");
		String beforeAT = eMail.substring(0, j);
		String afterAT = eMail.substring(j + 1);
		
		if (!isSpecialAlphaNumeric(beforeAT, new char[] { '.', '!', '#', '$', '%', '&', '\'' ,'*' ,'+', '/', '=', '?', '^', '_', '`', '{', '|', '}', '~', '-' })) {
			return false;
		}
		
		if (!isSpecialAlphaNumeric(afterAT, new char[] { '.' ,'-' })) {
			return false;
		}
		if (afterAT.indexOf("..") != -1) { //Don't allow consecutive periods after the @ sign
			return false;
		}
		if (afterAT.indexOf(".-") != -1) { //Don't allow consecutive period and hyphen after the @ sign
			return false;
		}
		if (afterAT.indexOf("-.") != -1) { //Don't allow consecutive hyphen and periods after the @ sign
			return false;
		}
		
		int len = afterAT.length();
		if((afterAT.charAt(len-1) == '-') || (afterAT.charAt(len-1) == '.')){ //period or hyphen cannot be the last character of an email
			return false;
		}
		
		return true;
	}

	public boolean validateSSN(String inputValue){
		if(inputValue == null || inputValue.length() < 9)
			return false;
		String firstGroup = inputValue.substring(0,3);
		String secondGroup = inputValue.substring(3,5);
		String thirdGroup = inputValue.substring(5,9);

		if(firstGroup.equals("000") || firstGroup.equals("666") || firstGroup.equals("999"))
			return false;
		if(secondGroup.equals("00"))
			return false;
		if(thirdGroup.equals("0000"))
			return false;
	
		IReferenceTableData ssnDetails = referenceTableManager.getReferenceTableData("TSSN",FwConstants.ENGLISH);

		String desc = null;
		
		int ssnDetailsSize = ssnDetails.getNumberOfRows();
		for(int i=0; i<ssnDetailsSize; i++){
			desc = ssnDetails.getDesc(i,228);
			if(desc.equals(inputValue)){
				return false;
			}
		}	
		return true;
	}

	public boolean validatePhone(String inputValue){
		if(inputValue == null)
			return false;
		if(!isAlphaNumeric(inputValue) || inputValue.trim().length() < 10)
			return false;
		else {
			String firstGroup = inputValue.substring(0,3).trim();
			String secondGroup = inputValue.substring(3,6).trim();
			String thirdGroup = inputValue.substring(6).trim();
			if(firstGroup.trim().length() < 3 || secondGroup.trim().length() < 3 || thirdGroup.trim().length() < 4 )
				return false; 	
		}
		return true;
	}

	public boolean validateWorkerId(String inputValue){
		boolean numeric = false;
		boolean alpha = false;
		if(inputValue == null)
			return false;
		for(int i=0;i<inputValue.length();i++){
			if(Character.isWhitespace(inputValue.charAt(i))){
				return false;
			}
			if(isDigit(inputValue.charAt(i))){
				numeric = true;
			}
			if(isLetter(inputValue.charAt(i))){
				alpha = true;
			}
		}
		if(alpha && numeric)
			return true;
		
		return false;
	}

	public boolean validateAddress(String address){
		if(!isSpecialAlphaNumeric(address,new char[]{'.','/','-','#',' ','\''})){
			return(false);
		}
		return true;
	}

	public boolean validateAddress(String stNumber,String unit,String dir,String stAddress,String suffix, String quad,String apt, String addAddress,String city,String state,String zip4,String zip5,String census,String region){
		if((isComplete(stNumber)||isComplete(unit)||isComplete(dir)||isComplete(suffix)||isComplete(quad)||isComplete(apt)||
		isComplete(addAddress)||isComplete(census)||isComplete(region)) && (!isComplete(stAddress)||!isComplete(city)||
		!isComplete(state)||!isComplete(zip4))) {
			return false;
		}
		if((isComplete(stNumber)&&!isAlphaNumeric(stNumber))||(isComplete(unit)&&!isAlphaNumeric(unit))
		||(isComplete(stAddress)&&!isAlphaNumeric(stAddress))||(isComplete(apt)&&!isAlphaNumeric(apt))
		||(isComplete(addAddress)&&!isAlphaNumeric(addAddress))||(isComplete(city)&&!isAlpha(city))
		||(isComplete(zip4)&&!isInteger(zip4))||(isComplete(zip5)&&!isInteger(zip5))) {
			return false;
		}
		if(isComplete(stNumber) && isComplete(unit)) {
			if(stNumber.length() < 9) {
				return false;
			}
		}
		if(isComplete(zip4) && zip4.length() < 5) {
			return false;
		}
		if(isComplete(zip5) && zip5.length() < 4) {
			return false;
		}
		return true;
	}

	public boolean validateAddress(String address1, String address2, String city, String zip){
		if((isComplete(address1)&&!isAlphaNumeric(address1))||(isComplete(address2)&&!isAlphaNumeric(address2))
		||(isComplete(city)&&!isAlpha(city))||(isComplete(zip)&&!isInteger(zip))) {
			return false;
		}
		
		if(isComplete(zip) && zip.length() < 5) {
			return false;
		}
		return true;
	}

	public boolean isSameAs(String inputValue, String compareValue){
		if(inputValue != null && compareValue != null)
			return(inputValue.trim().equals(compareValue.trim()));
		else
			return false;	
	}

	public boolean requiredIfEntered(String enteredValue, String requiredValue){
		if(enteredValue == null || enteredValue.trim().equals(FwConstants.EMPTY_STRING) || requiredValue == null)
			return false;
		if(requiredValue.trim().equals(FwConstants.EMPTY_STRING))
			return false;
		else
			return true;
	}

	public boolean requiredOnSelect(String selectedValue, String requiredValue, String selectedType){
		if(selectedValue == null || requiredValue == null)
			return false;
		selectedValue = selectedValue.trim();
		selectedType = selectedType.trim();
		requiredValue = requiredValue.trim();
		
		if(selectedType.equalsIgnoreCase("Yes")) {
			if((selectedValue.equalsIgnoreCase("Yes") || selectedValue.equals("Y"))&& requiredValue.equals(FwConstants.EMPTY_STRING))
				return false;
		}
		else if(selectedType.equalsIgnoreCase("No")) {
			if((selectedValue.equalsIgnoreCase("No") || selectedValue.equals("N"))&& requiredValue.equals(FwConstants.EMPTY_STRING))
				return false;
		}
		else if(selectedType.equalsIgnoreCase("Other")) {
			if(selectedValue.equalsIgnoreCase("Other") && requiredValue.equals(FwConstants.EMPTY_STRING))
				return false;
		}
		return true;	
	}

	public boolean invalidOnSelect(String selectedValue, String requiredValue, String selectedType) {
		if(selectedValue == null || requiredValue == null)
			return false;
		selectedValue = selectedValue.trim();
		selectedType = selectedType.trim();
		requiredValue = requiredValue.trim();
		
		if(selectedType.equalsIgnoreCase("Yes")) {
			if((selectedValue.equalsIgnoreCase("Yes") || selectedValue.equals("Y"))&& !requiredValue.equals(FwConstants.EMPTY_STRING))
				return false;
		}
		else if(selectedType.equalsIgnoreCase("No")) {
			if((selectedValue.equalsIgnoreCase("No") || selectedValue.equals("N"))&& !requiredValue.equals(FwConstants.EMPTY_STRING))
				return false;
		}
		return true;	
	}

	public boolean invalidYesOnNo(String selectedValue, String affectedValue) {
		if(selectedValue == null || affectedValue == null)
			return false;
		selectedValue = selectedValue.trim();
		affectedValue = affectedValue.trim();
			
		if((selectedValue.equalsIgnoreCase("No") || selectedValue.equals("N"))&& 
			(affectedValue.equalsIgnoreCase("Yes") || affectedValue.equalsIgnoreCase("Y")))
			return false;
		
		return true;	
	}

	public boolean isInvalidFirstChar(String inputValue) {
		if (Character.isLetter(inputValue.charAt(0)))
			return true;
		else
			return false;
	}

	public boolean isAmountLessOrGreaterThan(double amount, double compare, String compareType){
		if(!isCurrency(amount)) {
			return false;
		}
		if(compareType.trim().equalsIgnoreCase("less")) {
			if (amount < compare)
				return true;
		}
		else if(compareType.trim().equalsIgnoreCase("greater")) {
			if (amount > compare)
				return true;
		}
		else if(compareType.trim().equalsIgnoreCase("lessEqual")) {
			if (amount <= compare)
				return true;
		}
		else if(compareType.trim().equalsIgnoreCase("greaterEqual")) {
			if (amount >= compare)
				return true;
		}
		return false;
	}

	public boolean isValidAmountLimit(String amount) {
		if(isDecimal(amount)){
			StringTokenizer st = new StringTokenizer(amount,".");
			String first = st.nextToken();
			if(checkMaxLength(first,7))
				return true;	
		}
		else{
			if(checkMaxLength(amount,7))
				return true;
		}
		return false;
	}
	
	public boolean isValidAmountLimit(String amount, int maxFieldLength) {
		if(isDecimal(amount)){
			StringTokenizer st = new StringTokenizer(amount,".");
			String first = st.nextToken();
			if(checkMaxLength(first,maxFieldLength))
				return true;	
		}
		else{
			if(checkMaxLength(amount,maxFieldLength))
				return true;
		}
		return false;
	}

	private Date changeDateFormat(String inputDate){
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.setLenient(false);
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = df.parse(inputDate, pos);

		if (date == null || pos.getErrorIndex() != -1) {
			return null;
		}
		else{
			return date;
		}
	}
	
	public boolean validateSQLDate(String aDate) {	
		if(aDate!=null & aDate.length()==10){
			Date date = checkDateFormate(aDate);
			char[] allowedChar = {'-'};
			if(date == null || !isValidDate(aDate) || !isSpecialAlphaNumeric(aDate, allowedChar)) {
				return false;
			} else {
				Calendar cDate = Calendar.getInstance();
				cDate.setTime(date);
				if((cDate.get(Calendar.DAY_OF_MONTH) >= 1 && cDate.get(Calendar.DAY_OF_MONTH) <= 31) &&
					(cDate.get(Calendar.MONTH) >= 0 && cDate.get(Calendar.MONTH) <= 11) &&
					(cDate.get(Calendar.YEAR) >= 1000 && cDate.get(Calendar.YEAR) <= 9999))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	private Date checkDateFormate(String inputDate){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = df.parse(inputDate, pos);

		if (date == null || pos.getErrorIndex() != -1) {
			return null;
		}
		else{
			return date;
		}
	}
	
	private boolean isValidDate(String date) {
		if(date != null) {
			int length = date.length();
			for(int i = 0; i < length; i++) {
				if(isLetter(date.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isEmailSpl(String eMail){
		if (eMail.equals(FwConstants.EMPTY_STRING)) {
			return (true);
		}
		if (eMail.length() > 127) {
			return (false);
		}
		int i = eMail.indexOf(' ');
		if (i != -1) {
			return (false);
		}
		i = eMail.indexOf('@');

		if (i == -1) {
			return (false);
		}
		if (i == 0) {
			return (false);
		}
		if (i > eMail.length() - 4) {
			return (false);
		}
		if (eMail.indexOf('@', i + 1) != -1) {
			return (false);
		}
		int j = eMail.indexOf('\'',i);
		if ( j != -1 ) {
			return (false);
		}
		j = eMail.indexOf('.', i + 1);
		if (j == -1) {
			return (false);
		}
		if (j == i + 1) {
			return (false);
		}
		if (j == eMail.length() - 1) {
			return (false);
		}

		if ( eMail.charAt( i-1 ) == '.' ) {	
			return (false);
		}
		if ( eMail.charAt( eMail.length()-1 ) == '.' ) {	
			return (false);
		}
		j = eMail.indexOf('.');
		while( j > -1 && j+1 < eMail.length() )  {	
			if ( eMail.charAt(j+1) == '.' ) {	
				return (false);
			}
			j = eMail.indexOf('.', j+1);
		}
		if ( eMail.indexOf(',') != -1 ) {
			return (false);
		}
		j = eMail.indexOf('"');
		if ( j == ( eMail.length()-1 ) || j == 0 ) {
			return (false);
		}
		if(!isSpecialAlphaNumeric(eMail,new char[]{'.','-','_','@'})){
			return(false);
		}
		j = eMail.indexOf("@");
		
		String tempDomainAdd = eMail.substring(j+1);
		j =  tempDomainAdd.lastIndexOf(".");
		
		if(!isSpecialAlphaNumeric(tempDomainAdd.substring(0,j),new char[]{'-'})){
			return(false);
		}
			
		if(!isSpecialAlphaNumeric(tempDomainAdd.substring(j+1),new char[]{'.'})){
			return(false);
		}
		return (true);
	}

	public boolean validateWorker(String inputValue) {
		return false;
	}
	
	public boolean checkEmailFormat(String eMail) {
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile(AppConstants.EMAIL_PATTERN);
		if (eMail != null && eMail.trim().length() > 0) {
			matcher = pattern.matcher(eMail.trim());
			if (!matcher.matches()) {
				return false;
			}
		}
		return true;
	}
	
	public FwDateRoutine getDateRoutine() {
		return dateRoutine;
	}

	@Autowired
	public void setDateRoutine(FwDateRoutine dateRoutine) {
		this.dateRoutine = dateRoutine;
	}

}
