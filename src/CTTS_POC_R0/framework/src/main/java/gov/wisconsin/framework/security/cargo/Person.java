package gov.wisconsin.framework.security.cargo;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.AppConstants;
import gov.wisconsin.framework.constant.FwConstants;

import javax.naming.Name;

public class Person extends FwBaseClass {		
	
	// Valid characters for user names.
	static char[] trt = new char[256];
	static char[] special = new char[27];
	
	// The souce of wiUid is a threaded getWiUid() method.
	private String wiUid = null;

	// newPassword is not an attribute!  Is second copy of new or changed password. 
	private String newPassword = null;
	
	private String eMail = null;
	private String suffix = null;
	private String answer = null;
	private String answer2 = null;
	private String logonId = null;
	private String password = null;
	private String question = null;
	private String lastName = null;
	private String question2 = null;
	private String firstName = null;
	private String policyAccept = null;
	private String middleInitial = null;
	private String challengeResponse = null;	
	private String lockedByIntruder = null;
	
	private Name dn = null;
	
	/**
	 * @return the dn
	 */
	public Name getDn() {
		return dn;
	}

	/**
	 * @param dn the dn to set
	 */
	public void setDn(Name dn) {
		this.dn = dn;
	}

	/**
	 * @return Returns the answer2.
	 */
	public String getAnswer2() {
		return answer2;
	}
	
	/**
	 * @param answer2 The answer2 to set.
	 */
	public void setAnswer2(String answer2) {
		this.answer2 = (answer2 == null) ? FwConstants.EMPTY_STRING : answer2.trim();
	}
	
	/**
	 * @return Returns the question2.
	 */
	public String getQuestion2() {
		return question2;
	}
	
	/**
	 * @param question2 The question2 to set.
	 */
	public void setQuestion2(String question2) {
		this.question2 = (question2 == null) ? FwConstants.EMPTY_STRING : question2.trim();
	}
	
	/**
	 * Extract answer from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return answer java.lang.String
	 */
	public String getAnswer() {
		return this.answer;
	}
	
	/**
	 * Extract eMail address from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return eMail java.lang.String
	 */
	public String getEMail() {
		return this.eMail;
	}
	
	/**
	 * Extract firstName from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return firstName java.lang.String
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Extract lastName from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return lastName java.lang.String
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Extract logon Id from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return logonId java.lang.String
	 */
	public String getLogonId() {
		return this.logonId;
	}
	
	/**
	 * Extract middle initial from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return middleInitial java.lang.String
	 */
	public String getMiddleInitial() {
		return this.middleInitial;
	}
	
	/**
	 * Extract the new Password value from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return newPassword java.lang.String
	 */
	public String getNewPassword() {
		return this.newPassword;
	}
	
	/**
	 * Extract password from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return password java.lang.String
	 */
	public String getPassword() {
		return this.password;
	}
	
	public String getChallengeResponse() {
		return this.challengeResponse;
	}
	
	/**
	 * Extract policy acceptance indicator from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return generational java.lang.String
	 */
	public String getPolicyAccept() {
		return this.policyAccept;
	}

	/**
	 * Extract question from People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return question java.lang.String
	 */
	public String getQuestion() {
		return this.question;
	}
	
	/**
	 * Extract Wisconsin User Id (WiUid) from People class.
	 *
	 * Creation date: (06/05/2001 3:38:13 PM)
	 * @return WiUid java.lang.String
	 */
	public String getWiUid() {
		return this.wiUid;
	}
	
	/**
	 * Place answer attribute into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return answer java.lang.String
	 * @param  answer java.lang.String
	 */
	public String setAnswer(String answer) {
		this.answer = (answer == null) ? FwConstants.EMPTY_STRING : answer.trim();
		return this.answer;
	}
	
	/**
	 * Place e-Mail address into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return eMail java.lang.String 
	 * @param  eMail java.lang.String
	 */
	public String setEMail(String eMail) {
		this.eMail = (eMail == null) ? FwConstants.EMPTY_STRING : eMail.trim();
		return this.eMail;
	}
	
	/**
	 * Place first name into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return firstName java.lang.String
	 * @param  firstName java.lang.String
	 */
	public String setFirstName(String firstName) {
		this.firstName = (firstName == null) ? FwConstants.EMPTY_STRING : firstName.trim();
		return this.firstName;
	}

	/**
	 * Place last name into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return lastName java.lang.String
	 * @param  lastName java.lang.String
	 */
	public String setLastName(String lastName) {
		this.lastName = (lastName == null) ? FwConstants.EMPTY_STRING : lastName.trim();
		return this.lastName;
	}
	
	/**
	 * Place logon Id into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return answer java.lang.String
	 * @param  answer java.lang.String
	 */
	public String setLogonId(String logonId) {
		this.logonId = (logonId == null) ? FwConstants.EMPTY_STRING : logonId.trim();
		return this.logonId;
	}
	
	/**
	 * Place middle initial into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return middleInitial java.lang.String
	 * @param  middleInitial java.lang.String
	 */
	public String setMiddleInitial(String middleInitial) {
		this.middleInitial = (middleInitial == null) ? FwConstants.EMPTY_STRING : middleInitial.trim();
		return this.middleInitial;
	}
	
	/**
	 * Place second copy of a new password into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return password java.lang.String
	 * @param  password java.lang.String 
	 */
	public String setNewPassword(String newPassword) {
		this.newPassword = (newPassword == null) ? FwConstants.EMPTY_STRING : newPassword.trim();
		return this.newPassword;
	}

	/**
	 * Place password into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return password java.lang.String
	 * @param  password java.lang.String
	 */
	public String setPassword(String password) {
		this.password = (password == null) ? FwConstants.EMPTY_STRING : password.trim();
		return this.password;
	}
	
	public String setChallengeResponse(String challengeResponse) {
		this.challengeResponse = (challengeResponse == null) ? FwConstants.EMPTY_STRING : challengeResponse.trim();
		return this.challengeResponse;
	}

	/**
	 * Place policy acceptence indicator into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return policyAccept java.lang.String
	 * @param  policyAccept java.lang.String
	 */
	public String setPolicyAccept(String policyAccept) {
		this.policyAccept = (policyAccept == null) ? FwConstants.EMPTY_STRING : policyAccept.trim();
		return this.policyAccept;
	}
	
	/**
	 * Place question into People class.
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return question java.lang.String
	 * @param  question java.lang.String
	 */
	public String setQuestion(String question) {
		this.question = (question == null) ? FwConstants.EMPTY_STRING : question.trim();
		return this.question;
	}
	
	/**
	 * Place wiUid attribute into People class.
	 * The souce of wiUid is a threaded getWiUid() method.
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return wiUid java.lang.String
	 * @param  wiUid java.lang.String
	 */
	public String setWiUid(String wiUid) {
		this.wiUid = (wiUid == null) ? FwConstants.EMPTY_STRING : wiUid.trim();
		return this.wiUid;
	}
	
	/**
	 * Place Self Registraion elements into name=value pairs. 
	 * Creation date: (06/05/2001 1:06:37 PM)
	 * @return java.lang.String
	 */
	public String toString() { 
		//  thirteen elements, each constant 18 long, each element 40 long. 
		StringBuffer buffer = new StringBuffer(13 * (18 + 40));
		
		buffer.append("First Name='" + this.getFirstName());
		buffer.append("', Middle Initial='" + this.getMiddleInitial());
		buffer.append("', Last Name='" + this.getFirstName());
		buffer.append("', eMail='" + this.getEMail());
		buffer.append("', WiUid='" + this.getWiUid());
		buffer.append("', User Id='" + this.getLogonId());
		buffer.append("', Password='" + this.getPassword());
		buffer.append("', Question='" + this.getQuestion());
		buffer.append("', Answer='" + this.getAnswer());
		buffer.append("', Policy Accept='" + this.getPolicyAccept());

		return buffer.toString();
	}
	
	/**
	 * Determines if the answer attribute is acceptable for directory storage.
	 * The answer field must not be null or blanks.
	 * Any alpha section of the answer, as delimited by non-alpha characters, 
	 *must not match a "Blue List" word. 
	 * Creation date: (05/29/2001 10:09 AM)
	 * @return boolean
	 */
	public boolean validateAnswer() {
		if (this.answer.equals(FwConstants.EMPTY_STRING)) {
			return false;
		}

		if (this.answer.equalsIgnoreCase(this.question)) {
			return false;
		}			
		
		if (this.answer.indexOf("  ") != -1) {
			return false;
		}

		return true;
	}
	
	/**
	 * Determines if the answer attribute is acceptable for directory storage.
	 * The answer field must not be null or blanks.
	 * Any alpha section of the answer, as delimited by non-alpha characters, 
	 *must not match a "Blue List" word. 
	 * Creation date: (05/29/2001 10:09 AM)
	 * @return boolean
	 */
	public boolean validateAnswer2() {
		if (this.answer2.equals(FwConstants.EMPTY_STRING)) {
			return false;
		}

		if (this.answer2.equalsIgnoreCase(this.question)) {
			return false;
		}			
		
		if (this.answer2.indexOf("  ") != -1) {
			return false;
		}

		return true;
	}
	
	/**
	 * Determines if an eMail address is acceptable for directory storage.
	 * Creation date: (05/29/2001 10:09 AM)
	 * @return boolean
	 */
	public boolean validateEMail() {
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
		
		if(i == eMail.length()-1 ) { //@ cannot be at the end of the string (ADDED)
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
		if((afterAT.charAt(len-1) == '-') || (afterAT.charAt(len-1) == '.')) { //period or hyphen cannot be the last character of an email
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks characters in the value to allow only letter, digits and special chars sepecified in the array
	 * Method isSpecialAlphaNumeric.
	 * @param inputValue
	 * @param specialChars
	 * @return boolean
	 */
	private boolean isSpecialAlphaNumeric(String inputValue, char [] specialChars) {
		if(inputValue == null) {
			return false;
		}
		
		int charCount = 0;
		boolean alphaNum = true;
		for(int i = 0; i < inputValue.length(); i++) {
			if (!isLetterOrDigit(inputValue.charAt(i))) {
				alphaNum = false;
			}

			for(int j = 0; j < specialChars.length; j++) {
				if(inputValue.charAt(i) != specialChars[j] && !alphaNum) {
					charCount++;
				}
			}
			
			if(charCount == specialChars.length) {
				return false;
			}
			alphaNum = true;
			charCount=0;
		}
		return true;
	}
	
	/**
	 * Returns true if the character provided is a letter or a digit
	 * @param c
	 * @return
	 */
	private boolean isLetterOrDigit(char c) {
		if(!isLetter(c) && !isDigit(c)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns true if the character provided is a letter
	 * @param c
	 * @return
	 */
	private boolean isLetter(char c) {
		if(((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the character provided is a digit
	 * @param c
	 * @return
	 */
	private boolean isDigit(char c) {
		if(Character.isDigit(c)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if the first name is acceptable for directory storage.
	 * First name is a required attribute.<br>
	 * Determine if all characters are valid: a-z,A-Z,( )(')(-)<br>
	 * Check special characters ( )(')(-)<br>
	 *	 1. Not the first character.<br>
	 *	 2. Not the last character.<br>
	 *	 3. Only (-) can occur multiple times.<br>
	 *	 4. Does not allow consecutive special characters, (xx -xx)(xx'-xx)(xx--xx)<br>
	 *	 5. Does not allow ( o'xxx-mc'xxx). See rule three.<br>
	 *
	 * Creation date: (05/29/2001 10:09 AM)
	 * @return boolean
	 */
	public boolean validateFirstName() {
		if (this.firstName.equals(FwConstants.EMPTY_STRING)) {
			return false;
		}

		if (this.firstName.length() > 63) {
			return false;
		}

		// Determine if all characters are valid: a-z,A-Z,( )(')(-)
		int i = 0;
		char[] nameChr = this.firstName.toCharArray();
		for (; i < this.firstName.length() && trt[nameChr[i]] == 0; i++) {}
		
		if (i < this.firstName.length()) {
			return false;
		}
		
/** The criteria below does not match the criteria from CWW so we are removing as we must use names from CWW in some scenarios **/
		// Check special characters ( )(')(-)
		// 1. Not the first character.
		// 2. Not the last character.
		// 3. Only (-) can occur multiple times.
		// 4. Does not allow consecutive special characters, (xx -xx)(xx'-xx)(xx--xx)
		// 5. Does not allow ( o'xxx-mc'xxx). See rule three.
		
//		int j = 0;
//		for (int k = 0; k < special.length; k++) {
//			i = this.firstName.indexOf(special[k]);
//			if (i == -1) {  // Does not have this special character.
//				continue;
//			}
//			
//			if (i == 0) {// Is first character
//				return false;
//			}
//		
//			if (i == this.firstName.length() - 1) { // Is last character.
//				return false;
//			}
//			
//			i++;
//			for (int m = 0; m < special.length; m++) {
//				if (special[m] == this.firstName.charAt(i)) {
//					return false;
//				}
//			}
//			
//			j = this.firstName.indexOf(special[k], i); // Is there another.
//			
//			while (j != -1) {
//				if (special[k] != '-') { // Only (-) allowed multiple times.
//					return false;
//				}
//				
//				if (j == this.firstName.length() - 1) { // Is last character.
//					return false;
//				}
//				
//				i = ++j;
//				for (int m = 0; m < special.length; m++) {
//					if (special[m] == this.firstName.charAt(i)) {
//						return false;
//					}
//				}
//				j = this.firstName.indexOf(special[k], i); // Find next (-).
//			}
//		}
		
		return true;
	}

	/**
	 * Determines if the last name is acceptable for directory storage.
	 * First name is a required attribute.<br>
	 * Determine if all characters are valid: a-z,A-Z,( )(')(-)<br>
	 * Check special characters ( )(')(-)<br>
	 *	 1. Not the first character.<br>
	 *	 2. Not the last character.<br>
	 *	 3. Only (-) can occur multiple times.<br>
	 *	 4. Does not allow consecutive special characters, (xx -xx)(xx'-xx)(xx--xx)<br>
	 *	 5. Does not allow ( o'xxx-mc'xxx). See rule three.<br>
	 *
	 * Creation date: (05/29/2001 10:09 AM)
	 * @return boolean
	 */
	public boolean validateLastName() {
		if (this.lastName.equals(FwConstants.EMPTY_STRING)) {
			return false;
		}

		if (this.lastName.length() > 63) {
			return false;
		}

		// Determine if all characters are valid: a-z,A-Z,( )(')(-)
		int i = 0;
		char[] nameChr = this.lastName.toCharArray();
		for (; i < this.lastName.length() && trt[nameChr[i]] == 0; i++) {}
		
		if (i < this.lastName.length()) {
			return false;
		}

		return true;
	}
	
	/**
	 * Determines if a Logon Id is acceptable for directory storage.
	 * 01010801="User Id: Required field." 
	 * 01010802="User Id: Contains a blank within the name." 
	 * 01010803="User Id: Characters other than A through Z and 1 through 9 are present." 
	 * 01010804="User Id: "Blue List" validation failed.  Please select another User Id." 
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return boolean
	 * @param logonId java.lang.String
	 */
	public boolean validateLogonId() {
		if (this.logonId.equals(FwConstants.EMPTY_STRING)) {
			return false;
		}
		
		if (this.logonId.indexOf(' ') != -1) {
			return false;
		}
		
		if (logonId.length() < 5 || logonId.length() > 20) {
			return false;
		}

		if (!fwValidator.isAlphaNumeric(this.logonId)) {
			return false;
		}

		return true;
	}
	
	/**
	 * Determines if the middle initial is acceptable for directory storage.
	 * The middle initial may be null or blanks.
	 * Single apha character
	 * If there is a value, it must be an alpha character. 
	 * Creation date: (05/29/2001 10:09 AM)
	 * @return boolean
	 */
	public boolean validateMiddleInitial() {
		if (this.middleInitial.equals(FwConstants.EMPTY_STRING)) {
			return true;
		}
		
		if (this.middleInitial.length() != 1) {
			return false;
		}
		
		if (!fwValidator.isAlpha(this.middleInitial)) {
			return false;
		}

		return true;
	}

	/**
	 * Determines if a new password is acceptable for directory storage.
	 * The elements password and newPassword are used.
	 * 01011401="Password & Confirm Password: Both fields are required." 
	 * 01011402="Password & Confirm Password: The two values are unequal." 
	 *
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return boolean
	 */
	public boolean validateNewPassword() {
		if (this.password.equals(FwConstants.EMPTY_STRING) || this.newPassword.equals(FwConstants.EMPTY_STRING)) {
			return false;
		}
		
		if (!this.password.equals(this.newPassword)) {
			return false;
		}

		if (!this.validatePassword()) {
			return false;
		}

		return true;

	}
	
	/**
	 * Determines if a WiUid is acceptable for directory storage.
	 * Must pass validateWiUid() check.
	 * Must not already be in use or currently assigned to a pending account. 
	 * 
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return boolean
	 */
	public boolean validateNewWiUid() {
		if (!this.validateWiUid()) {
			return false;
		}
		return true;
	}

	/**
	 * Determines if a password is acceptable for directory storage.
	 * @return boolean
	 */
	public boolean validatePassword() {
		boolean valid = true;
		char[] passwordArray = password.toCharArray();

		/* Verify all characters are in the acceptable range */
		for (int i = 0; i < passwordArray.length && valid; i++) {
			if (passwordArray[i] < 33 || passwordArray[i] > 126) {
				valid = false;
			}
		}
		
		if (valid) {
			/* Verify password meets length requirements */
			if (password.length() < 8 || password.length() > 20) {
				valid = false;
			}
		}
		
		if (valid) {
			/* Verify if password contains at least 1 digit */
			valid = false;
			for (int i = 0; i < passwordArray.length && !valid; i++) {
				if (fwValidator.isNumeric(String.valueOf(passwordArray[i]))) {
					valid = true;
				}
			}
		}
		
		if (valid) {
			/* Verify if password contains at least 1 letter */
			valid = false;
			for (int i = 0; i < passwordArray.length && !valid; i++) {
				if (fwValidator.isAlpha(String.valueOf(passwordArray[i]))) {
					valid = true;
				}
			}
		}

		if (valid) {
			/* Verify if password contains at least 1 allowed special character */
			valid = false;
			for (int i = 0; i < AppConstants.ALLOWED_SPECIAL_CHARACTERS.length && !valid; i++) {
				if (password.contains(String.valueOf(AppConstants.ALLOWED_SPECIAL_CHARACTERS[i]))) {
					valid = true;
				}
			}
		}
		
		if (valid) {
			/* Verify if password contains spaces */
			if (password.contains(" ")) {
				valid = false;
			}
		}
		
		if (valid) {
			/* Verify if password contains first name, last name, or user ID */
			String lowercasePassword = password.toLowerCase();
			if (lowercasePassword.contains(this.getFirstName().toLowerCase())) {
				valid = false;
			} else if (lowercasePassword.contains(this.getLastName().toLowerCase())) {
				valid = false;
			} else if (lowercasePassword.contains(this.getLogonId().toLowerCase())) {
				valid = false;
			}
		}
		
		return valid;
	}
	
	/**
	 * Determines if the policy was accepted and can be place in directory storage.
	 * The only acceptable value is 'A'.
	 * Creation date: (05/29/2001 10:09 AM)
	 * @return boolean
	 */
	public boolean validatePolicyAccept() {
		if (this.policyAccept.equals(FwConstants.EMPTY_STRING)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Determines if the question attribute is acceptable for directory storage.
	 * The quesition field must not be null or blanks.
	 * Any alpha section of the question, as delimited by non-alpha characters, 
	 * must not match a "Blue List" word. 
	 * Creation date: (05/29/2001 10:09 AM)
	 * @return boolean
	 */
	public boolean validateQuestion() {
		if (this.question.equals(FwConstants.EMPTY_STRING) || this.question.equals("SEL")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Determines if the question attribute is acceptable for directory storage.
	 * The quesition field must not be null or blanks.
	 * Any alpha section of the question, as delimited by non-alpha characters, 
	 * must not match a "Blue List" word. 
	 * Creation date: (05/29/2001 10:09 AM)
	 * added by vasantha
	 * @return boolean
	 */
	public boolean validateQuestion2() {
		if (this.question2.equals(FwConstants.EMPTY_STRING) || this.question2.equals("SEL")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Determines if a WiUid is acceptable for directory storage.
	 * Year >= 2001 and <= current year.
	 * Month >= 01 and <= 12.
	 * Day valid for the given month.
	 * Hour >= 0 and <= 23.
	 * Minute >= 0 and <= 59.
	 * Second >= 0 and <= 59.
	 * 1/100 second >=0 and <= 99.
	 * 
	 * Creation date: (05/25/2001 3:38:13 PM)
	 * @return boolean
	 */
	public boolean validateWiUid() {
		if (this.wiUid.equals(FwConstants.EMPTY_STRING)) {
			return true;
		}

		if (wiUid.length() != 16) {
			return false;
		}
		
		if (!fwValidator.isNumeric(wiUid)) {
			return false;
		}

		return true;
	}
	
	/**
	 * @return
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param string
	 */
	public void setSuffix(String string) {
		suffix = string;
	}
	/**
	 * 
	 * @return
	 */
	public String getLockedByIntruder() {
		return lockedByIntruder;
	}
	/**
	 * 
	 * @param lockedByIntruder
	 */
	public void setLockedByIntruder(String lockedByIntruder) {
		this.lockedByIntruder = lockedByIntruder;
	}

	static {
		for (int i = 0; i < trt.length; i++) {
			trt[i] = (char) i;
		}
		
		// hex 00 invalid
		trt[0] = 255;

		// Valid characters.
		trt['a'] =
			trt['b'] =
				trt['c'] =
					trt['d'] = trt['e'] = trt['f'] = trt['g'] = trt['h'] = 0;
		trt['i'] =
			trt['j'] =
				trt['k'] =
					trt['l'] = trt['m'] = trt['n'] = trt['o'] = trt['p'] = 0;
		trt['q'] =
			trt['r'] =
				trt['s'] =
					trt['t'] = trt['u'] = trt['v'] = trt['w'] = trt['x'] = 0;
		trt['y'] = trt['z'] = 0;
		trt['A'] =
			trt['B'] =
				trt['C'] =
					trt['D'] = trt['E'] = trt['F'] = trt['G'] = trt['H'] = 0;
		trt['I'] =
			trt['J'] =
				trt['K'] =
					trt['L'] = trt['M'] = trt['N'] = trt['O'] = trt['P'] = 0;
		trt['Q'] =
			trt['R'] =
				trt['S'] =
					trt['T'] = trt['U'] = trt['V'] = trt['W'] = trt['X'] = 0;
		trt['Y'] = trt['Z'] = 0;
		trt['1'] = trt['2'] = trt['3'] = trt['4']= trt['5'] = trt['6'] =
				trt['7'] = trt['8'] = trt['9'] = trt['0'] = 0;
		
		special[0] = ' ';
		special[1] = '\'';
		special[2] = '-';
		
		// The below special characters are being allowed in mobile because in some cases we use names that come from CWW where these characters are allowed
		special[3]  = '!';
		special[4]  = '#';
		special[5]  = '$';
		special[6]  = '`';
		special[7]  = '(';
		special[8]  = ')';
		special[9]  = '[';
		special[10] = ']';
		special[11] = '{';
		special[12] = '}';
		special[13] = '*';
		special[14] = '+';
		special[15] = ',';
		special[16] = ':';
		special[17] = ';';
		special[18] = '=';
		special[19] = '?';
		special[20] = '@';
		special[21] = '^';
		special[22] = '_';
		special[23] = '|';
		special[24] = '~';
		special[25] = '.';
		special[26] = '/';

		for (int i = 0; i < special.length; i++) {
			trt[special[i]] = 0;
		}
	}	
}
