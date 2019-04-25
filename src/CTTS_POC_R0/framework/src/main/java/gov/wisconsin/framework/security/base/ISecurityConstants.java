package gov.wisconsin.framework.security.base;

public interface ISecurityConstants {
	
	public static final int NONE      = 0;
	public static final int READ_ONLY = 1;
	public static final int FULL      = 2;

	public static final String WID                      = "wid";
	public static final String USER_PHONE               = "phone";
	public static final String USER_E_MAIL              = "eMail";
	public static final String USER_ID                  = "userId";
	public static final String REPORT_ID                = "reportId";
	public static final String PROFILE_LIST             = "profiles";
	public static final String USER_LAST_NAME           = "lastName";
	public static final String USER_FIRST_NAME          = "firstName";
	public static final String LOCATION_ID              = "locationId";
	public static final String REPORT_NAME	            = "reportName";
	public static final String UID_WID_MAP              = "UID_WID_MAP";
	public static final String WAMS_LOGON_ID            = "wamslogonid";
	public static final String LOCATION_NAME            = "locationName";
	public static final String PROFILE_NAME_LIST        = "profileNames";
	public static final String PROFILE_LIST_MF_ID       = "profilesMfIds";
	public static final String UID_PRFLID_MAP           = "UID_PRFLID_MAP";
	public static final String USER_END_DATE            = "effectiveEndDate";
	public static final String USER_DEFAULT_LOCATION_ID = "defaultLocationId";
	public static final String USER_BEGIN_DATE          = "effectiveBeginDate";

	public static final String DEFAULT_APPLICATION_API = "gov.wisconsin.framework.security.ldap.FwApplication";
	
	//SECURITY MANAGER CONSTANTS
	public static final String PROFILE_ID             = "PROFILE_ID";
	public static final String PROFILE_MAP            = "PROFILE_MAP";
	public static final String PROFILE_NAME           = "PROFILE_NAME";
	public static final String PROFILE_FUNC_MAP       = "PROFILE_FUNC_MAP";
	public static final String RESOURCE_ATTRIBUTE_MAP = "RESOURCE_ATTRIBUTE_MAP";
	
	public static final String NO_ACCESS         = "N";
	public static final String READ_ONLY_ACCESS  = "R";
	public static final String READ_WRITE_ACCESS = "U";
	
	//	for Application profile name lookup
	public static final String APP_PROFILE_DATA = "APP_PROFILE_DATA";
	
	public static final String PRFL_MAP = "PRFL_MAP";
}
