package gov.wisconsin.framework.security.base;

import gov.wisconsin.framework.security.cargo.LDAP_Cargo;
import gov.wisconsin.framework.security.cargo.Person;

public interface ILdap {
	
	/**
	 * Search for a user by userId or wid.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 * @return The People object containing the user's information
	 */
	public Person findUser(LDAP_Cargo cargo);
	
	/**
	 * Helper method that will search for a user by userId.
	 * @param userId - The userId of the account you want to retrieve
	 * @return A people object populated with the cargo values if the user is found, else returns null.
	 */
	public Person findUser(String userId);
	
	/**
	 * Create a new user account in the LDAP directory.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 * @return True if the account is successfully created, else returns false.
	 */
	public boolean createAccount(LDAP_Cargo cargo);
	
	/**
	 * This will update account information of the user.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 */
	public void updateAccount(LDAP_Cargo cargo);
	
	/**
	 * This will check if a user exist in the directory.
	 * If the user is not found in the directory, then an indicator,
	 * NOT_ABLE_TO_CREATE_ACCOUNT will be set in the result codes field of
	 * the LDAP cargo instance.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 */
	public boolean isValidUser(LDAP_Cargo cargo);
	
	/**
	 * This will search for user in the LDAP directory with an email address
	 * that matches the one from the cargo argument. Depending on the result of the
	 * search, different indicators will be set in the result code field of the cargo
	 * instance.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 */
	public boolean searchUserByEmail(LDAP_Cargo cargo);
	
	
	/**
	 * This will retrieve the secret questions from the LDAP directory,
	 * and add them to the cargo instance.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 */
	public void getSecretQuestions(LDAP_Cargo cargo);
	
	/**
	 * This will update the secret questions of this user in the LDAP
	 * directory.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 */
	public void updateQuestions(LDAP_Cargo cargo);
	
	/**
	 * Will return the WID for a specific user.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 * @return - The WID value based on the user id from the cargo instance.
	 */
	public String getWIDforUserId(LDAP_Cargo cargo);
	
	/**
	 * This will retrieve user details from the LDAP directory based on 
	 * the WID value from the cargo argument.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 * @return - A String array of user details based on the WID value. 
	 */
	public String[] getUserDetailsForWIDs(LDAP_Cargo cargo);
	
	/**
	 * This will convert the cargo instance into a People object.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 * @return - A People object that consist of all the values from the cargo instance
	 */
	public Person getPerson(LDAP_Cargo cargo);
	
	/**
	 * Will match a wid with the LDAP directory.
	 * @param cargo - The LDAP cargo that holds all the user information.
	 */
	public void matchWiUid(LDAP_Cargo cargo);
	
	/**
	 * Determine if the Wisconsin User ID account has been locked by due to
	 * password violations.
	 */
	boolean isLockedByIntruder(String logonId); 
}

