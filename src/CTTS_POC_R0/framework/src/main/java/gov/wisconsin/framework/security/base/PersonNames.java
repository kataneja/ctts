package gov.wisconsin.framework.security.base;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;

public interface PersonNames {	

	public static final String reqGUserId      = "ReqGUserId";
	public static final String reqDirInfo      = "ReqDirInfo";  
	public static final String reqErrorMsgs    = "ReqErrorMsgs";
	public static final String reqStatusMsgs   = "ReqStatusMsgs";
	public static final String reqDirInfoSet   = "ReqDirInfoSet";
	public static final String reqPolicyAccept = "ReqPolicyAccept";
	
	public static final String reqWiUid                = "ReqWiUid";
	public static final String reqEMail                = "ReqEMail";
	public static final String reqAnswer               = "ReqAnswer";
	public static final String reqLogonId              = "ReqLogonId";
	public static final String reqLastName             = "ReqLastName";
	public static final String reqQuestion             = "ReqQuestion";
	public static final String reqPassword             = "ReqPassword";
	public static final String reqFirstName            = "ReqFirstName";
	public static final String reqOldPassword          = "ReqOldPassword";
	public static final String reqNewPassword          = "ReqNewPassword";
	public static final String reqPhoneNumber          = "ReqPhoneNumber";
	public static final String reqMailingCity          = "ReqMailingCity";
	public static final String reqMailingZip5          = "ReqMailingZip5";
	public static final String reqMailingZip4          = "ReqMailingZip4";
	public static final String reqMailingState         = "ReqMailingState";
	public static final String reqGenerational         = "ReqGenerational";
	public static final String reqMiddleInitial        = "ReqMiddleInitial";
	public static final String reqResidenceUnit        = "ReqResidenceUnit";
	public static final String reqResidenceCity        = "ReqResidenceCity";
	public static final String reqResidenceZip5        = "ReqResidenceZip5";
	public static final String reqResidenceZip4        = "ReqResidenceZip4";
	public static final String reqResidenceState       = "ReqResidenceState";
	public static final String reqResidenceStreet      = "ReqResidenceStreet";
	public static final String reqMailingAddressLine1  = "ReqMailingAddressLine1";
	public static final String reqMailingAddressLine2  = "ReqMailingAddressLine2";

	public static final Attribute dirACL		         = new BasicAttribute(PersonAttrNames.attrACL);
	public static final Attribute dirEMail    	         = new BasicAttribute(PersonAttrNames.attrEMail);
	public static final Attribute dirAnswer		         = new BasicAttribute(PersonAttrNames.attrAnswer);
	public static final Attribute dirLastName		     = new BasicAttribute(PersonAttrNames.attrLastName);
	public static final Attribute dirDisabled	         = new BasicAttribute(PersonAttrNames.attrDisabled);
	public static final Attribute dirFirstName	         = new BasicAttribute(PersonAttrNames.attrFirstName);
	public static final Attribute dirHonorific		     = new BasicAttribute(PersonAttrNames.attrHonorific);
	public static final Attribute dirMailingZip		     = new BasicAttribute(PersonAttrNames.attrMailingZip);
	public static final Attribute dirMailingCity		 = new BasicAttribute(PersonAttrNames.attrMailingCity);
	public static final Attribute dirMailingState	     = new BasicAttribute(PersonAttrNames.attrMailingState);
	public static final Attribute dirResidenceZip	     = new BasicAttribute(PersonAttrNames.attrResidenceZip);
	public static final Attribute dirGenerational        = new BasicAttribute(PersonAttrNames.attrGenerational);
	public static final Attribute dirCommunityLink       = new BasicAttribute(PersonAttrNames.attrCommunityLink);
	public static final Attribute dirResidenceUnit	     = new BasicAttribute(PersonAttrNames.attrResidenceUnit);
	public static final Attribute dirResidenceCity	     = new BasicAttribute(PersonAttrNames.attrResidenceCity);
	public static final Attribute dirLastLoginTime	     = new BasicAttribute(PersonAttrNames.attrLastLoginTime);
	public static final Attribute dirResidenceState	     = new BasicAttribute(PersonAttrNames.attrResidenceState);
	public static final Attribute dirResidenceStreet	 = new BasicAttribute(PersonAttrNames.attrResidenceStreet);
	public static final Attribute dirMailingAddressLine1 = new BasicAttribute(PersonAttrNames.attrMailingAddressLine1);
	public static final Attribute dirMailingAddressLine2 = new BasicAttribute(PersonAttrNames.attrMailingAddressLine2);
	public static final Attribute dirIChainCommunityLink = new BasicAttribute(PersonAttrNames.attrIChainCommunityLink);
	
	public static final Attribute dirWiUid		                = new BasicAttribute(PersonAttrNames.attrWiUid);
	public static final Attribute dirUserId		                = new BasicAttribute(PersonAttrNames.attrUserId);
	public static final Attribute dirLogonId			        = new BasicAttribute(PersonAttrNames.attrLogonId);
	public static final Attribute dirPassword     	            = new BasicAttribute(PersonAttrNames.attrPassword);
	public static final Attribute dirQuestion	                = new BasicAttribute(PersonAttrNames.attrQuestion);
	public static final Attribute dirObjectClass  	            = new BasicAttribute(PersonAttrNames.attrObjectClass);
	public static final Attribute dirPhoneNumber                = new BasicAttribute(PersonAttrNames.attrPhoneNumber);
	public static final Attribute dirMiddleIntial 	            = new BasicAttribute(PersonAttrNames.attrMiddleInitial);
	public static final Attribute dirTemplateAttr               = new BasicAttribute(PersonAttrNames.attrTemplateAttr);
	public static final Attribute dirWiXmlTransfer              = new BasicAttribute(PersonAttrNames.attrWiXmlTransfer);
	public static final Attribute dirPolicyHistory              = new BasicAttribute(PersonAttrNames.attrPolicyHistory);
	public static final Attribute dirLoginDisabled  	        = new BasicAttribute(PersonAttrNames.attrLoginDisabled);
	public static final Attribute dirTemplateAction             = new BasicAttribute(PersonAttrNames.attrTemplateAction);
	public static final Attribute dirLockedByIntruder           = new BasicAttribute(PersonAttrNames.attrLockedByIntruder);
	public static final Attribute dirLoginExpirationTime        = new BasicAttribute(PersonAttrNames.attrLoginExpirationTime);
	public static final Attribute dirLoginGraceRemaining        = new BasicAttribute(PersonAttrNames.attrLoginGraceRemaining);
	public static final Attribute dirSecurityEquals             = new BasicAttribute(PersonAttrNames.attrSecurityEquals, true);
	public static final Attribute dirLoginIntruderAttempts      = new BasicAttribute(PersonAttrNames.attrLoginIntruderAttempts);
	public static final Attribute dirPasswordExpirationTime     = new BasicAttribute(PersonAttrNames.attrPasswordExpirationTime);
	public static final Attribute dirPasswordExpirationInterval = new BasicAttribute(PersonAttrNames.attrPasswordExpirationInterval);

}
