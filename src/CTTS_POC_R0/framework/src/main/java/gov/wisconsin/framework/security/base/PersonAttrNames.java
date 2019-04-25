package gov.wisconsin.framework.security.base;

public interface PersonAttrNames {	

	public static final String attrLastName		              = "sn";
	public static final String attrLogonId		              = "cn";
	public static final String attrACL			              = "ACL";
	public static final String attrUserId		              = "uid";
	public static final String attrEMail			          = "mail";
	public static final String attrWiUid	                  = "wiUid";
	public static final String attrMiddleInitial	          = "initials";
	public static final String attrFirstName		          = "givenName";
	public static final String attrHonorific		          = "honorific";
	public static final String attrLastLoginTime	          = "loginTime";
	public static final String attrObjectClass	              = "objectClass";
	public static final String attrPassword		              = "userPassword";
	public static final String attrLoginDisabled		      = "loginDisabled";
	public static final String attrWiXmlTransfer              = "wiXmlTransfer";
	public static final String attrDisabled		              = "loginDisabled";
	public static final String attrSecurityEquals             = "securityEquals";
	public static final String attrTemplateAttr               = "wiTemplateAttr";
	public static final String attrResidenceZip	              = "wiResidenceZip";
	public static final String attrPhoneNumber	              = "telephoneNumber";
	public static final String attrPolicyHistory	          = "wiPolicyHistory";
	public static final String attrResidenceUnit	          = "wiResidenceUnit";
	public static final String attrResidenceCity	          = "wiResidenceCity";
	public static final String attrLoginGraceLimit            = "loginGraceLimit";
	public static final String attrTemplateAction             = "wiTemplateAction";
	public static final String attrLockedByIntruder	          = "lockedByIntruder";
	public static final String attrResidenceState             = "wiResidenceState";
	public static final String attrPasswordRequired	          = "passwordRequired";
	public static final String attrResidenceStreet            = "wiResidenceStreet";
	public static final String attrAnswer 		              = "wiUserSecretAnswer";
	public static final String attrCommunityLink	          = "ichainCommunityLink";
	public static final String attrGenerational	              = "generationQualifier";
	public static final String attrMailingZip	              = "wiMailingAddressZip";
	public static final String attrIChainCommunityLink	      = "iChainCommunityLink";
	public static final String attrLoginExpirationTime	      = "loginExpirationTime";
	public static final String attrLoginGraceRemaining	      = "loginGraceRemaining";
	public static final String attrChallengeResponse          = "wiChallengeResponse";
	public static final String attrMailingCity	              = "wiMailingAddressCity";
	public static final String attrQuestion		              = "wiUserSecretQuestion";
	public static final String attrPasswordMinimumLength	  = "passwordMinimumLength";
	public static final String attrMailingAddressLine1	      = "wiMailingAddressLine1";
	public static final String attrMailingAddressLine2	      = "wiMailingAddressLine2";
	public static final String attrMailingState	              = "wiMailingAddressState";
	public static final String attrLoginIntruderAttempts	  = "loginIntruderAttempts";
	public static final String attrPasswordUniqueRequired	  = "passwordUniqueRequired";
	public static final String attrPasswordExpirationTime     = "passwordExpirationTime";
	public static final String attrPasswordExpirationInterval = "passwordExpirationInterval";
	
	public static final String LDAP_USR_CRTN_LOGINGRACELIMIT       = "LDAP_USR_CRTN_LOGINGRACELIMIT";
	public static final String LDAP_USR_CRTN_LOCKEDBYINTRUDER      = "LDAP_USR_CRTN_LOCKEDBYINTRUDER";
	public static final String LDAP_USR_CRTN_LOGINGRACEREMAINING   = "LDAP_USR_CRTN_LOGINGRACEREMAINING";
	public static final String LDAP_USR_CRTN_LOGININTRUDERATTEMPTS = "LDAP_USR_CRTN_LOGININTRUDERATTEMPTS";
	public static final String LDAP_USR_CRTN_PASSEXPINTERVAL       = "LDAP_USR_CRTN_PASSWORDEXPIRATIONINTERVAL";
}

