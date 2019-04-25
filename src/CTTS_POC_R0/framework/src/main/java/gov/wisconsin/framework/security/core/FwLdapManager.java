package gov.wisconsin.framework.security.core;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.security.base.ILdap;
import gov.wisconsin.framework.security.base.PersonAttrNames;
import gov.wisconsin.framework.security.cargo.LDAP_Cargo;
import gov.wisconsin.framework.security.cargo.Person;
import gov.wisconsin.framework.util.SecurityUtils;

import java.util.Base64;
import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.InvalidAttributeValueException;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

@Component
public class FwLdapManager extends FwBaseClass implements ILdap {
	
	private static ILdap instance;
	
	@Value("${LDAP_USR_CRTN_PERSON_DISPLAY_ACL}")
	public String ldapUsrCrtnPersonDisplayAcl;
	
	@Value("${LDAP_USR_CRTN_LOGINDISABLED}")
	public String ldapUsrCrtnloginDisabled;
	
	@Value("${LDAP_USR_CRTN_LOGINGRACEREMAINING}")
	public String ldapUsrCrtnloginGraceRemaining;
	
	@Value("${LDAP_USR_CRTN_PASSWORDMINIMUMLENGTH}")
	public String ldapUsrCrtnPasswordMinimumlength;
	
	@Value("${LDAP_USR_CRTN_PASSWORDUNIQUEREQUIRED}")
	public String ldapUsrCrtnPasswordUniqueRequired;
	
	@Value("${LDAP_USR_CRTN_PASSWORDREQUIRED}")
	public String ldapUsrCrtnPasswordRequired;
	
	@Value("${LDAP_USR_CRTN_PASSWORDEXPIRATIONINTERVAL}")
	public String ldapUsrCrtnPasswordExpirationInterval;
	
	@Value("${LDAP_USR_CRTN_LOGINGRACELIMIT}")
	public String ldapUsrCrtnLoginGraceLimit;
	
	@Value("${LDAP_USR_CRTN_LOCKEDBYINTRUDER}")
	public String ldapUsrCrtnLockedByIntruder;
	
	@Value("${LDAP_USR_CRTN_LOGININTRUDERATTEMPTS}")
	public String ldapUsrCrtnLoginIntruderAttempts;
	
	@Value("${LDAP_USRINFO_STORE_LOCTN}")
	public String strTmplocation;
	
	private SecurityUtils securityUtils;				//@Autowired
	private FwSecurityBO securityBO;		    //@Autowired
	private LdapTemplate ldapTemplate;			//@Autowired
	private UserInfoLDAPAttr userInfoLdapAttr;	//@Autowired
	
	private static final String NULL_CARGO_MSG = "cargo is null";
	
	private FwLdapManager() {}
	
    /**
     * This will create a new user account in ACCESS with the values passed in through the LDAP_Cargo argument.
     * @param - cargo is the LDAP_Cargo that holds all the user information needed to create an account.
     * @return - Returns true if the user has been successfully created, else returns false.
     */
	@Override
	public boolean createAccount(LDAP_Cargo cargo) {
		Person person = null;
		String newWiUid = FwConstants.EMPTY_STRING;
		
		try {
			if(cargo != null) {
				// Convert to person object
				person = this.getPerson(cargo);
				
				// Create a new WiUid
				newWiUid = securityBO.getNewWiUid();
				newWiUid = newWiUid.replaceFirst(newWiUid.substring(0, 1), "6");
				person.setWiUid(newWiUid);
				cargo.setWid(Long.parseLong(newWiUid));
				
				//set wiPolicyHistory
				String policyAccept = "SelfReg#"+newWiUid+"#Ver 4.0#20030606";
				person.setPolicyAccept(policyAccept);
				
				// Search the directory for this user. if it is found
				// that means that a user already exist with this user name.
				List<Person> pers = this.findUserByAttr(PersonAttrNames.attrLogonId, person.getLogonId());
				
				// Create a new user if the user doesn't exist already.
				if(pers == null || pers.isEmpty()) {
					try {
						this.createUser(person);
						return true;
					} catch(Exception ex) {
						cargo.getResultCodes().add(FwConstants.NOT_ABLE_TO_CREATE_ACCOUNT);
					}
				} else {
					cargo.getResultCodes().add(FwConstants.USER_ID_ALREADY_EXISTS);
				}
			}
		} catch(Exception e) {
			String message = "Account creation failed";
			if (person != null) { message += " for " + person.getFirstName() + FwConstants.SPACE + person.getLastName(); }
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK, message);
		}
		
		return false;
	}

	/**
	 * Sets indicator in the LDAP_Cargo resultCodes if the user is not found.
	 * This will use the logonId to find a user.
	 * @param cargo - The cargo that holds all user account information
	 */
	@Override
	public boolean isValidUser(LDAP_Cargo cargo) {
		try {
			if(cargo != null) {
				Name ldapName = this.getDn(cargo.getLogonId());
				if (ldapName != null) {
					cargo.getResultCodes().add(FwConstants.USER_ID_ALREADY_EXISTS);
					return false;
				}
			} else {
				throw new NullPointerException(NULL_CARGO_MSG);
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return true;
	}

	/**
	 * This will search for a user by email address. An indicator will be set in the
	 * LDAP_Cargo resultCodes if the email is found.
	 * This method can be used to find out if there are existing entries with this
	 * email address.
	 * @param cargo - The cargo that holds all user account information
	 */
	@Override
	public boolean searchUserByEmail(LDAP_Cargo cargo) {
		try {
			if(cargo != null) {
				List<Person> people = this.findUserByAttr(PersonAttrNames.attrEMail, cargo.getEmail());
				if(people != null && !people.isEmpty()) {
					cargo.getResultCodes().add(FwConstants.ACCOUNT_ALREADY_EXISTS_FOR_EMAIL_ID);
					return true;
				}
			} else {
				throw new NullPointerException(NULL_CARGO_MSG);
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return false;
	}

	/**
	 * This method will find the security questions for a user. If the user is found
	 * then the cargo question 1 and 2 will be populated with the 2 security questions.
	 * @param cargo - This holds values about a user, such as login id and wid number.
	 */
	@Override
	public void getSecretQuestions(LDAP_Cargo cargo) {
		Person person = null;
		
		try {
			if(cargo != null) {
				person = this.findUser(cargo);
				if(person != null) {
					String question = person.getQuestion();
					String challengeResponse = person.getChallengeResponse();
					if(question != null && challengeResponse != null) {
						// Get the question part of the challenge response
						if(challengeResponse.indexOf("~") != -1){
							challengeResponse = challengeResponse.split("~")[0];
						}
						cargo.setQuestion1(question);
						cargo.setQuestion2(challengeResponse);
					}
				} else {
					cargo.getResultCodes().add(FwConstants.USER_ID_DOES_NOT_MATCH);
				}
			} else {
				throw new NullPointerException(NULL_CARGO_MSG);
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	/**
	 * This will update account password information, and related values, for a user.
	 * @param - cargo holds user information
	 */
	@Override
	public void updateAccount(LDAP_Cargo cargo) {
		Person person = null;
		
		try {
			if(cargo != null) {
				List<String> widsFromAccess = cargo.getWidsFromAccess();
				String secQuestion = cargo.getQuestion1();
				String secAnswer = cargo.getAnswer1();
				String secAnswer2 = cargo.getAnswer2();
				String password = cargo.getPassword();
				
				person = this.findUser(cargo);
				if(person != null) {
					// If the user account is found then get the required attributes from the account				
					String secQn = null;
					String secAns = null;
					String secAns2 = null;
					String wiUid = null;
					
					secQn = person.getQuestion();
					secAns = person.getAnswer();
					secAns2 = person.getChallengeResponse();
					if(secAns2 != null && secAns2.indexOf("~") != -1) {
						secAns2 = secAns2.split("~")[1];
					}
					wiUid = person.getWiUid();
					
					// Check the information in the account matches with the user's input
					if(!widsFromAccess.contains(wiUid)) {
						cargo.getResultCodes().add(FwConstants.USER_ID_DOES_NOT_MATCH);		
						return;
					} else if(!secQuestion.equals(secQn)) {
						cargo.getResultCodes().add(FwConstants.ANSWER_DOES_NOT_MATCH);	
						return;
					} else if(!secAnswer.equalsIgnoreCase(secAns)) {
						cargo.getResultCodes().add(FwConstants.ANSWER_DOES_NOT_MATCH);		
						return;
					} else if (secAnswer2 != null && !secAnswer2.equalsIgnoreCase(secAns2)) {
						cargo.getResultCodes().add(FwConstants.ANSWER_DOES_NOT_MATCH); 	
						return;
					} else {
						try {
							// Change the password
							Name dn = this.getDn(person.getLogonId());
						    Attribute attr = new BasicAttribute(PersonAttrNames.attrPassword, password);
						    ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
						    ldapTemplate.modifyAttributes(dn, new ModificationItem[] {item});
							
						    // Change other attributes
						    String passwordExpirationTime = securityUtils.getTimeSyntax(userInfoLdapAttr.getExprnTime(FwConstants.PSSWD_EXPRN));
						    
						    ModificationItem [] items = new ModificationItem[8];
						    
						    Attribute objPasswdExpirn = new BasicAttribute(PersonAttrNames.attrPasswordExpirationTime, passwordExpirationTime);				
							items[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objPasswdExpirn);
						
							Attribute objLoginDisabled = new BasicAttribute(PersonAttrNames.attrLoginDisabled, ldapUsrCrtnloginDisabled);				
							items[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objLoginDisabled);
									
							Attribute objWiXMLTransfer = new BasicAttribute(PersonAttrNames.attrWiXmlTransfer, this.encryptPassword(password));
							items[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objWiXMLTransfer);
							
							Attribute objPassExpInterval = new BasicAttribute(PersonAttrNames.attrPasswordExpirationInterval, ldapUsrCrtnPasswordExpirationInterval);
							items[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objPassExpInterval);
							
							Attribute objLoginGraceLimit = new BasicAttribute(PersonAttrNames.attrLoginGraceLimit, ldapUsrCrtnLoginGraceLimit);
							items[4] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objLoginGraceLimit);
							
							Attribute objLoginGraceRemaining = new BasicAttribute(PersonAttrNames.attrLoginGraceRemaining, ldapUsrCrtnloginGraceRemaining);
							items[5] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objLoginGraceRemaining);
							
							Attribute objLockedByIntruder = new BasicAttribute(PersonAttrNames.attrLockedByIntruder, null);
							items[6] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objLockedByIntruder);

							Attribute objLoginIntruderAttempts = new BasicAttribute(PersonAttrNames.attrLoginIntruderAttempts, ldapUsrCrtnLoginIntruderAttempts);
							items[7] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objLoginIntruderAttempts);
							
							ldapTemplate.modifyAttributes(dn, items);
						} catch(InvalidAttributeValueException e) {
							cargo.getResultCodes().add(FwConstants.PASSWORD_ALREADY_USED);
						} 
					}
				} else {
					cargo.getResultCodes().add(FwConstants.NOT_ABLE_TO_CREATE_ACCOUNT);
					return;
				}
			} else {
				throw new NullPointerException(NULL_CARGO_MSG);
			}
		} catch(Exception e) {
			if (cargo == null) { cargo = new LDAP_Cargo(); }
			cargo.getResultCodes().add(FwConstants.UNABLE_TO_PROCESS);
		}
	}

	/**
	 * This will update security questions for a user.
	 * @param cargo - this hold user information that is needed to update security questions
	 */
	@Override
	public void updateQuestions(LDAP_Cargo cargo) {
		Person person = null;
		
		try {
			if(cargo != null) {
				String secQuestion = cargo.getQuestion1();
				String secAnswer = cargo.getAnswer1();
				String secQuestion2 = cargo.getQuestion2();
				String secAnswer2 = cargo.getAnswer2();
				
				person = this.findUser(cargo);
				if(person != null) {
					Name dn = this.getDn(person.getLogonId());
					
					ModificationItem [] items = new ModificationItem[3];
					
					Attribute objNewQuestion1 = new BasicAttribute(PersonAttrNames.attrQuestion, secQuestion);
					items[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objNewQuestion1);
					
					Attribute objNewResponseChallenge = new BasicAttribute(PersonAttrNames.attrChallengeResponse, secQuestion2 + "~" + secAnswer2);
					//Attribute objNewResponseChallenge = new BasicAttribute(PeopleAttrNames.attrChallengeResponse, null);
					items[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objNewResponseChallenge);
					
					Attribute objNewAnswer1 = new BasicAttribute(PersonAttrNames.attrAnswer, secAnswer);
					items[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objNewAnswer1);
					
					ldapTemplate.modifyAttributes(dn, items);
				} else {
					cargo.getResultCodes().add(FwConstants.UNABLE_TO_PROCESS);	
				}
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}

	/**
	 * This method will find and return a user wid number for a login id.
	 * @return The wid number for a user if found, else returns null.
	 */
	@Override
	public String getWIDforUserId(LDAP_Cargo cargo) {
		String wid = null;
		
		try {
			if(cargo != null) {
				Person person = this.findUserByLogonId(cargo.getLogonId());
				
				if(person == null) {
					cargo.getResultCodes().add(FwConstants.USER_ID_DOES_NOT_MATCH);
				} else {
					wid = person.getWiUid();
				}
			} else {
				throw new NullPointerException(NULL_CARGO_MSG);
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return wid;
	}

	/**
	 * This method will get user details for a matching wid number.
	 * @return A string array of [0] first name, [1] last name, [2] and login id.
	 */
	@Override
	public String[] getUserDetailsForWIDs(LDAP_Cargo cargo) {
		String[] values = new String[3];
		
		try {
			if(cargo != null) {
				//We should only get a one person match for a wid
				Person person = this.getPersonForWid(String.valueOf(cargo.getWid()));
				if(person != null) {
					values[0] = person.getFirstName();
					values[1] = person.getLastName(); 
					values[2] = person.getLogonId();
				}
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return values;
	}

	@Override
	public Person getPerson(LDAP_Cargo cargo) {
		Person person = new Person();
		
		try {
			if(cargo != null) {
				person.setAnswer(cargo.getAnswer1());
				person.setEMail(cargo.getEmail());
				person.setFirstName(cargo.getFirstName());
				person.setLastName(cargo.getLastName());
				person.setLogonId(cargo.getLogonId());
				person.setMiddleInitial(String.valueOf(cargo.getMiddleInitial()));
				person.setNewPassword(cargo.getPassword());
				person.setPassword(cargo.getPassword());
				person.setQuestion(cargo.getQuestion1());
				person.setQuestion2(cargo.getQuestion2());
				person.setSuffix(cargo.getSuffixName());
				person.setWiUid(String.valueOf(cargo.getWid()));
				person.setChallengeResponse(cargo.getQuestion2()+"~"+cargo.getAnswer2());
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return person;
	}

	/**
	 * Sets user information in the cargo that is related to a wid.
	 */
	@Override
	public void matchWiUid(LDAP_Cargo cargo) {
		try {
			if(cargo != null) {
				//We should only get a one person match for a wid
				Person person = this.getPersonForWid(String.valueOf(cargo.getWid()));
				if(person != null) {
					cargo.setFirstName(person.getFirstName());
					cargo.setLastName(person.getLastName());
					cargo.setLogonId(person.getLogonId());
					cargo.setEmail(person.getEMail());
				} else {
					cargo.getResultCodes().add(FwConstants.USER_ID_DOES_NOT_MATCH);
				}
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	/**
	 * Helper method that will search for a user by userId or wid.
	 * @param cargo - Holds user information
	 * @return A people object populated with the cargo values if the user is found, else returns null.
	 */
	public Person findUser(LDAP_Cargo cargo) {
		List<Person> people = null;
		Person person = null;
		
		try {
			if(cargo != null) {
				String userId = cargo.getUser();
				if(userId != null) {
					person = this.findUserByLogonId(userId);
				} else {
					people = this.findUserByAttr(PersonAttrNames.attrWiUid, String.valueOf(cargo.getWid()));
					if(people != null && !people.isEmpty()) {
						// There should be only one person return from a WID.
						person = people.get(0);
					}
				}
				
				if(person == null) {
					cargo.getResultCodes().add(FwConstants.USER_ID_DOES_NOT_MATCH);
				}
			}
		
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return person;
	}
	
	/**
	 * Helper method that will search for a user by userId.
	 * @param userId - The userId of the account you want to retrieve
	 * @return A people object populated with the cargo values if the user is found, else returns null.
	 */
	public Person findUser(String userId) {
		try {
			LDAP_Cargo cargo = new LDAP_Cargo();
			cargo.setUser(userId);
			cargo.setLogonId(userId);
			return findUser(cargo);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return null;
	}
	
	/**
	 * find a people object for the wid number
	 * @param wid - The wid number for a user
	 * @return A people object if a match is found, else will return null.
	 */
	private Person getPersonForWid(String wid) {
		List<Person> people = null;
		
		try {
			if(wid != null) {
				people = this.findUserByAttr(PersonAttrNames.attrWiUid, wid);
				if(people != null && !people.isEmpty()) {
					//We should only get a one person match for a wid
					return people.get(0);
				}
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return null;
	}
	
	/**
	 * Create a new person in the LDAP directory
	 * @param person - The person who is creating an ACCESS account
	 * @param dn - The Distinguished Name for this person
	 * @throws Exception  If person or dn is null;
	 */
	private void createUser(Person person) throws Exception {
		if(person != null) {
			try {
			    DirContextAdapter context = new DirContextAdapter();
			    
			    // Set all the required context values
			    context.setDn(this.buildDn(person.getLogonId()));
			    this.mapPersonToContext(person, context);
			    
			    apiLogger.info("createUser inside");
			    
			    context.setAttributeValue(PersonAttrNames.attrPolicyHistory, "Y");
			    context.setAttributeValue(PersonAttrNames.attrACL, ldapUsrCrtnPersonDisplayAcl);
			    context.setAttributeValue(PersonAttrNames.attrLoginDisabled, ldapUsrCrtnloginDisabled);
			    context.setAttributeValue(PersonAttrNames.attrLoginGraceLimit, ldapUsrCrtnLoginGraceLimit);
			    context.setAttributeValue(PersonAttrNames.attrPasswordRequired, ldapUsrCrtnPasswordRequired);
			    context.setAttributeValue(PersonAttrNames.attrLoginGraceRemaining, ldapUsrCrtnloginGraceRemaining);
			    context.setAttributeValue(PersonAttrNames.attrPasswordMinimumLength, ldapUsrCrtnPasswordMinimumlength);
			    context.setAttributeValue(PersonAttrNames.attrPasswordUniqueRequired, ldapUsrCrtnPasswordUniqueRequired);
			    context.setAttributeValue(PersonAttrNames.attrPasswordExpirationInterval, ldapUsrCrtnPasswordExpirationInterval);
			    
				// Store the newly created person in the directory.
			    this.createEntry(context, person);
			} catch(Exception e) {
				FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK, "User account entry failed for: " + person.getFirstName() + FwConstants.SPACE + person.getLastName());
			}
		}
	}
	
	/**
	 * This method will create a user account in the LDAP directory with the values 
	 * provided by the context argument. 
	 * If the user is successfully created, then the password expiration time
	 * will be set.
	 * @param context - This is the DirContext that holds all the values for a
	 *  new user who's account is getting created.
	 * @param person - The person who is creating an ACCESS account.
	 */
	private void createEntry(DirContextOperations context, Person person) {
		try {
			 // Create the entry in the directory
			 ldapTemplate.bind(context);
			 
			 // Don't look to soon after the create. You will get a null.
			 try {  Thread.sleep( 5000 ); }
			 catch (InterruptedException ie ) {	
				 apiLogger.warn("CreateUser.execute: InterruptedException: Msg= " + ie.getMessage());
			 }
			 	
			 if(person != null) {
				 Name dn = this.getDn(person.getLogonId());
				 // Set the password expiration time
				 if(dn != null) {
					 String passwordExpirationTime = securityUtils.getTimeSyntax(userInfoLdapAttr.getExprnTime(FwConstants.PSSWD_EXPRN));
					 ModificationItem [] items = new ModificationItem[1];
					 Attribute objPasswdExpirn = new BasicAttribute(PersonAttrNames.attrPasswordExpirationTime, passwordExpirationTime);				
					 items[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objPasswdExpirn);
					 ldapTemplate.modifyAttributes(dn, items);
				 }
			 }
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	/**
	 * This will search the directory for users with this logon ID / user name, and return any
	 * matches found. This should usually always be one.
	 * @param logonId - A users logon ID
	 * @return A list of people matches from the directory. This should usually always be one.
	 */
	private List<Person> findUserByAttr(String attr, String value) {
		List<Person> people = null;
		
		try {
			// Query in the people base directory
			LdapQuery queryBase = query()
					.attributes(PersonAttrNames.attrLogonId, 
							PersonAttrNames.attrLastName,
							PersonAttrNames.attrFirstName,
							PersonAttrNames.attrEMail,
							PersonAttrNames.attrQuestion,
							PersonAttrNames.attrAnswer, 
							PersonAttrNames.attrChallengeResponse,
							PersonAttrNames.attrWiUid,
							PersonAttrNames.attrMiddleInitial, 
							PersonAttrNames.attrPolicyHistory,
							PersonAttrNames.attrLockedByIntruder)
					.where("objectclass").is("person")
					.and(attr).is(value);
			
			// Search the base location
			people = ldapTemplate.search(queryBase, CONTEXT_TO_PERSON_MAPPER);
		} catch(Exception e) {
			// Return null if no person is found
			return null;
		}
		
		return people;
	}
	
	/**
	 * Build a Distinguished Name from a People object
	 * @param person - The People object 
	 * @return An LdapName instance of the Distinguished Name
	 */
	private LdapName buildDn(String logonId) {
		if(logonId == null) { return null; }
		
		return LdapNameBuilder.newInstance()
				.add(strTmplocation)
                .add(PersonAttrNames.attrLogonId, logonId)
                .build();
	}
	
	/**
	 * Look up a person by the Distinguished name.
	 * @param dn - The unique Distinguished name.
	 * @return - A person if found, else returns null;
	 */
	private Person findUserByLogonId(String logonId) {
		try {
			Name dn = this.getDn(logonId);
			if(dn == null) { return null; }
			return ldapTemplate.lookup(dn, CONTEXT_TO_PERSON_MAPPER);
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * will return the distinguished name for a logon ID. It will first look in the 
	 * base location, if not found there, then search the temporary location.
	 * @param dn - The unique Distinguished name.
	 * @return - A distinguished name if found, else returns null;
	 */
	private Name getDn(String logonId) {
		try {
			List<Person> peopleList = this.findUserByAttr(PersonAttrNames.attrLogonId, logonId);
			if (peopleList.size() > 0) {
				Person person = peopleList.get(0);
				return person.getDn();
			}
		} catch(Exception e) {
			apiLogger.debug("unable to get DN for [" + logonId + "]");
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	/**
	 * This method will set all person value as attributes in the DirContextAdapter object
	 * @param person - The person who should be mapped to a context
	 * @param context - The DirContextAdapter that will set attributes with all person values.
	 */
	private void mapPersonToContext(Person person, DirContextAdapter context) {
		try {
			if(person != null && context != null) {
				context.setAttributeValues(PersonAttrNames.attrObjectClass, new String[] { "top", "ndsLoginProperties", "person", "organizationalPerson", "inetOrgPerson", "wiNetUser", "wiXmlSync", "ichainProfiles", "brdsrvsRuleObjects", "ichainCommunityLinks", "wiHomeInfo"});
				context.setAttributeValue(PersonAttrNames.attrLogonId, person.getLogonId());
			    context.setAttributeValue(PersonAttrNames.attrLastName, person.getLastName());
			    context.setAttributeValue(PersonAttrNames.attrFirstName, person.getFirstName());
			    context.setAttributeValue(PersonAttrNames.attrUserId, person.getLogonId());
			    
			    if(person.getPassword() != null && person.getPassword().length() > 0) {
				    context.setAttributeValue(PersonAttrNames.attrPassword, person.getPassword());
					context.setAttributeValue(PersonAttrNames.attrWiXmlTransfer, this.encryptPassword(person.getPassword()));
					apiLogger.debug("createUser(): End of the encryption");
			    }
			    
			    context.setAttributeValue(PersonAttrNames.attrQuestion, person.getQuestion());
			    context.setAttributeValue(PersonAttrNames.attrAnswer, person.getAnswer());
			    context.setAttributeValue(PersonAttrNames.attrChallengeResponse, person.getChallengeResponse());
			    context.setAttributeValue(PersonAttrNames.attrWiUid, person.getWiUid());
			    
			    //Since these are optional fields they are being checked for null and empty String
			    if( person.getEMail() != null && person.getEMail().length() > 0) {
			    	context.setAttributeValue(PersonAttrNames.attrEMail, person.getEMail());
			    }
			    
				if( person.getMiddleInitial() != null && person.getMiddleInitial().length() > 0) {
					context.setAttributeValue(PersonAttrNames.attrMiddleInitial, person.getMiddleInitial());
				}
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK, "User account entry failed for: " + person.getFirstName() + FwConstants.SPACE + person.getLastName());
		}
	}
	
	/**
	 * Maps to Person objects. 
	 */
	private final static ContextMapper<Person> CONTEXT_TO_PERSON_MAPPER = new AbstractContextMapper<Person>() {
        @Override
		public Person doMapFromContext(DirContextOperations context) {
        	Person person = new Person();
            
        	try {
	            // Map to People object
	        	person.setDn(context.getDn());
	            person.setLogonId(context.getStringAttribute(PersonAttrNames.attrLogonId));
	            person.setLastName(context.getStringAttribute(PersonAttrNames.attrLastName));
	            person.setFirstName(context.getStringAttribute(PersonAttrNames.attrFirstName));
	            person.setEMail(context.getStringAttribute(PersonAttrNames.attrEMail));
	            person.setQuestion(context.getStringAttribute(PersonAttrNames.attrQuestion));
	            person.setAnswer(context.getStringAttribute(PersonAttrNames.attrAnswer));
	            person.setChallengeResponse(context.getStringAttribute(PersonAttrNames.attrChallengeResponse));
	            person.setWiUid(context.getStringAttribute(PersonAttrNames.attrWiUid));
	            person.setMiddleInitial(context.getStringAttribute(PersonAttrNames.attrMiddleInitial));
	            person.setPolicyAccept(context.getStringAttribute(PersonAttrNames.attrPolicyHistory));
	            person.setPassword(context.getStringAttribute(PersonAttrNames.attrPassword));
	            person.setLockedByIntruder(context.getStringAttribute(PersonAttrNames.attrLockedByIntruder));
	            
	            /** CAR-74405 | Handling accounts with only 1 secret Q/A | Defaulting 2nd secret Q/A to empty strings **/
	            String challengeResponse = context.getStringAttribute(PersonAttrNames.attrChallengeResponse);
	            String[] questionAndAnswer2 = challengeResponse != null && !challengeResponse.equals("~") ? challengeResponse.split("~") : new String[] {FwConstants.EMPTY_STRING, FwConstants.EMPTY_STRING};
	
	            person.setQuestion2(questionAndAnswer2[0]);
	            person.setAnswer2(questionAndAnswer2[1]);
        	} catch(Exception e) {
    			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
    		}
            
			return person;
		}
	};
	
	/**
	 * This will encrypt the password and then base64 encode it.
	 * @param password - password coming from the user
	 * @return The encrypted and base64 encoded password.
	 */
	private String encryptPassword(String password) {
		String wiXmlTransfer = FwConstants.EMPTY_STRING;
		
		try {
			apiLogger.debug("createUser(): Start of the encryption");
			byte[]	encryptedPassword = securityUtils.encrypt( password );
			wiXmlTransfer = Base64.getEncoder().encodeToString(encryptedPassword);
			apiLogger.debug("createUser(): End of the encryption");
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return wiXmlTransfer;
	}
	
	/**
	 * Determine if the Wisconsin User ID account has been locked by due to
	 * password violations.
	 *
	 * @return boolean TRUE if the account has been locked due to password 
	 * violations.
	 */
	@Override
	public boolean isLockedByIntruder(String logonId) {	
		try {
			apiLogger.info(" isLockedByIntruder :: starts :: logonId is " + logonId);
			List<Person> personLst = this.findUserByAttr(PersonAttrNames.attrLogonId, logonId);
			
			if(personLst.size() > 1) {
				FwExceptionManager.handleException(this.getClass(), new Exception("Duplicate User Account Found for logonId: " + logonId));
			}
			
			Person person = personLst.get(0);
			String lockedByIntruder = person.getLockedByIntruder();
	
			apiLogger.info(" isLockedByIntruder :: ends :: lockedByIntruder Status is :: " + lockedByIntruder);
			
			if (lockedByIntruder != null) {
				return lockedByIntruder.equalsIgnoreCase("TRUE");
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return false;
	}
	
	@Autowired
	public void setUserInfoLDAPAttr(UserInfoLDAPAttr userInfoLDAPAttr) {
		this.userInfoLdapAttr = userInfoLDAPAttr;
	}
	
	@Autowired
	public void setsecurityUtils(SecurityUtils securityUtils) {
		this.securityUtils = securityUtils;
	}
	
	@Autowired
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	
	public FwSecurityBO getSecurityBO() {
		return securityBO;
	}

	@Autowired
	public void setSecurityBO(FwSecurityBO securityBO) {
		this.securityBO = securityBO;
	}
	
    public static void setInstance(ILdap ldapManager) {
    	instance = ldapManager;
    }
    
    public static ILdap getInstance() {
    	return instance;
    } 
}
