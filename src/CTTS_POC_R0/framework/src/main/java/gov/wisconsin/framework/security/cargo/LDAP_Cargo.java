package gov.wisconsin.framework.security.cargo;

import gov.wisconsin.framework.data.base.FwAbstractCargo;
import gov.wisconsin.framework.data.base.IPrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class LDAP_Cargo extends FwAbstractCargo {
	private static final long serialVersionUID = -5960474734691315166L;
	
	private long wid;
	private char middleInitial;
	
	private String email = null;
	private String action = null;
	private String logonId = null;
	private String answer1 = null;
	private String answer2 = null;
	private String password = null;
	private String lastName = null;
	private String question1 = null;
	private String question2 = null;
	private String firstName = null;
	private String suffixName = null;

	private List<String> widsFromAccess;
	private List<String> resultCodes = new ArrayList<>();
	
	/**
	 * @return Returns the widsFromAccess.
	 */
	public List<String> getWidsFromAccess() {
		return widsFromAccess;
	}
	
	/**
	 * @param widsFromAccess The widsFromAccess to set.
	 */
	public void setWidsFromAccess(List<String> widsFromAccess) {
		this.widsFromAccess = widsFromAccess;
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * @return Returns the answer1.
	 */
	public String getAnswer1() {
		return answer1;
	}
	
	/**
	 * @param answer1 The answer1 to set.
	 */
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
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
		this.answer2 = answer2;
	}
	
	/**
	 * @return Returns the eMail.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param mail The eMail to set.
	 */
	public void setEmail(String mail) {
		email = mail;
	}
	
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	
	/**
	 * @return Returns the middleInitial.
	 */
	public char getMiddleInitial() {
		return middleInitial;
	}
	
	/**
	 * @param middleInitial The middleInitial to set.
	 */
	public void setMiddleInitial(char middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return Returns the question1.
	 */
	public String getQuestion1() {
		return question1;
	}
	
	/**
	 * @param question1 The question1 to set.
	 */
	public void setQuestion1(String question1) {
		this.question1 = question1;
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
		this.question2 = question2;
	}
	
	/**
	 * @return Returns the suffixName.
	 */
	public String getSuffixName() {
		return suffixName;
	}
	
	/**
	 * @param suffixName The suffixName to set.
	 */
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}
	
	/**
	 * @return Returns the wid.
	 */
	public long getWid() {
		return wid;
	}
	
	/**
	 * @param wid The wid to set.
	 */
	public void setWid(long wid) {
		this.wid = wid;
	}
	
	/** (non-Javadoc)
	 * @see gov.wisconsin.framework.business.entities.ICargo#inspectCargo()
	 */
	public String inspectCargo() {
		return new StringBuffer().append("LDAP_Cargo: ").append("firstName=").append(firstName).append("lastName=").append(lastName).append("middleInitial=").append(middleInitial).append("suffixName=").append(suffixName).append("logonId=").append(logonId).append("password=").append(password).append("question1=").append(question1).append("question2=").append(question2).append("answer1=").append(answer1).append("answer2=").append(answer2).append("wid=").append(wid).append("action=").append(action).append("email=").append(email).toString();
	}

	/**
	 * @return Returns the resultCodes.
	 */
	public List<String> getResultCodes() {
		return resultCodes;
	}
	
	/**
	 * @param resultCodes The resultCodes to set.
	 */
	public void setResultCodes(List<String> resultCodes) {
		this.resultCodes = resultCodes;
	}
	
	@Override
	public IPrimaryKey getPrimaryKey() {
		return null;
	}
}

