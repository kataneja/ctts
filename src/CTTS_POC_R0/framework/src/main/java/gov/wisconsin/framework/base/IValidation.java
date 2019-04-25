package gov.wisconsin.framework.base;

import java.util.Date;

public interface IValidation {

	public boolean isNumeric(String phrase);
	
	public boolean isDateBeforeDate(Date inputDate, Date whenDate);
	
	public boolean isDateBeforeDate(String inputDate, Date whenDate);
	
	public boolean isDateAfterDate(Date inputDate, Date whenDate);
	
	public boolean isDateAfterDate(String inputDate, Date whenDate);
	
	public boolean pastDate(Date inputDate);
	
	public boolean pastDateInclusive(Date inputDate);
	
	public boolean futureDate(Date inputDate);
	
	public boolean futureDate(String inputDate);
	
	public boolean rangeForDate(Date inputDate, Date upperDate, Date lowerDate);
	
	public boolean validateDate(Date inputDate);
	
	public boolean isComplete(String requiredValue);
	
	public boolean isAtleastOneComplete(String [] requiredValues);
	
	public boolean isAlpha(String inputValue);
	
	public boolean isAlphaWithSpace(String inputValue);
	
	public boolean isInteger(String inputValue);
	
	public boolean isDecimal(String inputValue);
	
	public boolean isAlphaNumeric(String inputValue);
	
	public boolean isAlphaNumericForAIE(String inputValue);
	
	public boolean isStrictlyAlphaNumeric(String inputValue);
	
	public boolean isSpecialAlphaNumeric(String inputValue, char [] specialChars);
	
	public boolean preventSpecialChars(String inputValue, char [] specialChars);
	
	public boolean noSpace(String inputValue);
	
	public boolean isZero(String inputValue);
	
	public boolean isMaxSet(String inputValue, int maxFieldLength);
	
	public boolean checkMaxAllowed(String inputValue,int maxValue);
	
	public boolean checkMaxLength(String inputValue, int maxLimit);
	
	public boolean isCurrency(double amount);
	
	public boolean isEmail(String field);
	
	public boolean validateSSN(String inputValue);
	
	public boolean validatePhone(String inputValue);
	
	public boolean validateAddress(String stNumber,String unit,String dir,String stAddress,String suffix, String quad,String apt, String addAddress,String city,String state,String zip4,String zip5,String census,String region);
	
	public boolean validateAddress(String address1, String address2, String city, String zip);
	
	public boolean validateAddress(String address);
	
	public boolean isSameAs(String inputValue, String compareValue);
	
	public boolean requiredIfEntered(String enteredValue, String requiredValue);
	
	public boolean requiredOnSelect(String selectedValue, String requiredValue, String selectedType);
	
	public boolean invalidOnSelect(String selectedValue, String requiredValue, String selectedType);
	
	public boolean invalidYesOnNo(String selectedValue, String affectedValue);
	
	public boolean isInvalidFirstChar(String inputValue);
	
	public boolean isAmountLessOrGreaterThan(double amount, double compare, String compareType);
	
	public boolean isValidAmountLimit(String amount);
	
	public boolean validateDate(String aDate);
	
	public boolean validateDate(long inputDate);
	
	public boolean isCurrency(String amount);
	
	public boolean isHour(String Hour);
	
	public boolean isFieldEmpty(String inputValue);
	
	public boolean isValidAmountLimit(String inputValue, int maxFieldLength);
	
	public boolean validateSQLDate(String inputDate);
	
	public boolean isSpecialAlpha(String inputValue, char [] specialChars); 
	
	public boolean isEmailSpl(String field);
	
	public boolean validateWorkerId(String inputValue);

	public boolean checkEmailFormat(String email_adr);
	
}


