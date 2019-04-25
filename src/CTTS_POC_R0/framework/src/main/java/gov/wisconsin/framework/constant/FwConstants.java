package gov.wisconsin.framework.constant;

public final class FwConstants {
	
	/*
	 * 
	 *	Environments
	 * 
	 */
	public static final String ENV_DEV = "DEVELOPMENT";
	public static final String ENV_INT = "INTEGRATION";
	public static final String ENV_SYS = "SYSTEMS";
	public static final String ENV_UAT = "ACCEPTANCE";
	public static final String ENV_TRN = "TRAINING";
	public static final String ENV_PRD = "PRODUCTION";
	
	/*
	 * 
	 *	Process Switches 
	 * 
	 */
	public static final String ON  = "on";
	public static final String OFF = "off";
	
	/*
	 * 
	 *	Annotation Values 
	 * 
	 */
	public static final String PROTOTYPE_SCOPE  = "prototype";
	
	/*
	 * 
	 *	HTTP Methods 
	 * 
	 */
	public static final String HTTP_GET    = "GET";
	public static final String HTTP_PUT    = "PUT";
	public static final String HTTP_POST   = "POST";
	public static final String HTTP_DELETE = "DELETE";
	
	/*
	 * 
	 *	APIPayload Keys 
	 * 
	 */
	public static final String API_STATUS        = "status";
	public static final String PAYLOAD_MESSAGES  = "messages";
	public static final String PAYLOAD_REQUESTID = "requestID";
	public static final String PAYLOAD_TIMESTAMP = "timestamp";
	
	public static final String PAYLOAD_HEADER_WID             = "WID";
	public static final String PAYLOAD_HEADER_USER            = "WI_USER";
	public static final String PAYLOAD_HEADER_CACHE           = "mw-cache";
	public static final String PAYLOAD_HEADER_USER_AGENT      = "User-Agent";
	public static final String PAYLOAD_HEADER_DEVICE_LANGUAGE = "deviceLang";
	
	public static final String PAYLOAD_EXCEPTION         = "exception";
	public static final String PAYLOAD_EXCEPTION_ID      = "exceptionID";
	public static final String PAYLOAD_EXCEPTION_TEXT    = "exceptionText";
	public static final String PAYLOAD_EXCEPTION_CLASS   = "exceptionClass";
	public static final String PAYLOAD_EXCEPTION_STATUS  = "httpStatus";
	public static final String PAYLOAD_EXCEPTION_METHOD  = "exceptionMethod";
	public static final String PAYLOAD_EXCEPTION_MESSAGE = "exceptionMessage";
	
	/*
	 * 
	 *	TEXT 
	 * 
	 */
	public static final String NEWLINE = "\n";
	public static final String ENGLISH = "EN";
	public static final String SPANISH = "ES";
	
	public static final String DOT          = ".";
	public static final String COMMA        = ",";
	public static final String SPACE        = " ";
	public static final String COLON        = ":";
	public static final String HYPHEN       = "-";
	public static final String UNDERSCORE   = "_";
	public static final String EMPTY_STRING = "";
	
	/*
	 * 
	 *	SUBSYSTEM PREFIXES
	 * 
	 */
	public static final String PREFIX_DSM       = "DS";
	public static final String PREFIX_CMB       = "CM";
	public static final String PREFIX_RMC       = "RM";
	public static final String PREFIX_ACCESS    = "AM";
	public static final String PREFIX_FRAMEWORK = "FW";
	
	/*
	 * 
	 *	APPLICATION URLS 
	 * 
	 */
	public static final String API_VERSION = "/v1";
	
	public static final String URL_ALL         = "/";
	public static final String URL_CATCHALL    = "/**";
	public static final String URL_APPLICATION = "/api";

	/*
	 * 
	 *	CONTROLLER URLS 
	 * 
	 */
	public static final String URL_CONTROLLER_EXAMPLE = "/example";
	
	/*
	 * 
	 *	SWAGGER
	 * 
	 */
	public static final String SWAGGER_HOST_INJECT       = "${swagger.host}";
	public static final String SWAGGER_PROTOCOL_INJECT   = "${swagger.protocol}";
	public static final String SWAGGER_TITLE_INJECT      = "${swagger.title}";
	public static final String SWAGGER_CONTROLLER_INJECT = "${swagger.controllerPackage}";
	
	/*
	 * 
	 *	Database Property Names
	 * 
	 */
	public static final String DATABASE_SCHEMA               = "dbms.schema";
	public static final String DATABASE_SCHEMA_INJECT        = "${" + DATABASE_SCHEMA + "}";
	public static final String DATABASE_NAME                 = "dbms.name";
	public static final String DATABASE_NAME_INJECT          = "${" + DATABASE_NAME + "}";
	public static final String DATABASE_SOURCE               = "dbms.source";
	public static final String DATABASE_SOURCE_INJECT        = "${" + DATABASE_SOURCE + "}";
	
	/*
	 * 
	 *	Environment Property Names 
	 * 
	 */
	public static final String ENVIRONMENT_NAME        = "env.name";
	public static final String ENVIRONMENT_NAME_INJECT = "${" + ENVIRONMENT_NAME + "}";
	
	public static final String PROPERTY_FOLDER        = "propertyFolder";
	public static final String PROPERTY_FOLDER_INJECT = "${" + PROPERTY_FOLDER + "}";
	
	public static final String XSLT_FOLDER = "xsltFolder";

	public static final String ACCESS_ONLINE_ENDPOINT_INJECT = "${accessOnlineURL}";
	
	/*
	 * 
	 *  Caching
	 *  
	 */
	public static final int DYNA_CACHE_TTL = 60;
	public static final String CLIENT_DEVICE_CACHE = "deviceCache";

	public static final String PROPERTY_MW_RESPONSE_CACHE        = "mwResponseCache";
	public static final String PROPERTY_MW_RESPONSE_CACHE_INJECT = "${" + PROPERTY_MW_RESPONSE_CACHE + "}";
	
	public static final String PROPERTY_BROADCAST_CACHE        = "broadcastNotificationCache";
	public static final String PROPERTY_BROADCAST_CACHE_INJECT = "${" + PROPERTY_BROADCAST_CACHE + "}";
	
	/*
	 * 
	 * Middleware Meta-Data
	 * 
	 */
	public static final String IS_MOBILE     = "mobile";
	public static final String IS_DESKTOP    = "desktop";
	public static final String CLIENT_DEVICE = "clientDevice";
	
	public static final String MW_USERNAME = "mw.username";
	public static final String MW_USERNAME_INJECT = "${" + MW_USERNAME + "}";
	public static final String MW_PASSWORD = "mw.password";
	public static final String MW_PASSWORD_INJECT = "${" + MW_PASSWORD + "}";

	/*
	 * 
	 *	Logger Names 
	 * 
	 */
	public static final String LOGGER_API       = "apiLogger";
	public static final String LOGGER_ACTIVITY  = "activityLogger";
	public static final String LOGGER_EXCEPTION = "exceptionLogger";
	
	/*
	 * 
	 *	Exception Types 
	 * 
	 */
	public static final int EXP_TYP_FRAMEWORK       = 0; 
	public static final int EXP_TYP_APPLICATION     = 1;
	public static final int EXP_TYP_DAO             = 2;
	public static final int EXP_TYP_XML             = 3;
	public static final int EXP_TYP_STOREDPROCEDURE = 4;
	public static final int EXP_TYP_LDAP            = 5;
	public static final int EXP_TYP_WEBSERVICE      = 6;
	public static final int EXP_TYP_MESSAGING       = 7;
	public static final int EXP_TYP_SECURITY        = 8;
	public static final int EXP_TYP_CUSTOM_TAG      = 9;
	public static final int EXP_TYP_TRANSACTION     = 10;
	public static final int EXP_TYP_BATCH           = 11; 
	public static final int EXP_TYP_DIST_BATCH      = 11;
	public static final int EXP_TYP_TRANSFER        = 13;
	
	/*
	 * 
	 *	Activity Timer 
	 * 
	 */
	public static final int ACTIVITY_INCOMING_MODE  = 0;
	public static final int ACTIVITY_OUTGOING_MODE  = 1;
	public static final int ACT_TIMER_OUTGOING_MODE = 1;
	public static final int ACT_TIMER_INCOMING_MODE = 0;
	public static final String ACTIVITY_DELIMITER   = ", "; 
	public static final String ACTIVITY_DATE_FORMAT = "yyyy-MM-dd HH.mm.ss.SSS000";
	
	public static final String ACTIVITY_TIMER             = "activityTimer";
	public static final String ACTIVITY_LOG_PROCESS_TIME  = "activityLogProcessTime";
	public static final String ACTIVITY_TIMER_WRITE_TO_MQ = "activityTimerWriteToMQ";
	
	public static final String SERVICE_ACTIVITY_TIMER             = "serviceActivityTimer";
	public static final String SERVICE_ACTIVITY_LOG_PROCESS_TIME  = "serviceActivityLogProcessTime";
	public static final String SERVICE_ACTIVITY_TIMER_WRITE_TO_MQ = "serviceActivityTimerWriteToMQ";
	
	/*
	 * 
	 *	Driver
	 * 
	 */
	public static final String RMC_DRIVER        = "RMC";
	public static final String APW_DRIVER        = "APW";
	public static final String APC_DRIVER        = "APC";
	public static final String APP_DRIVER        = "APP";
	public static final String AHN_DRIVER        = "AHN";
	public static final String HCE_DRIVER        = "HCE";
	public static final String HNA_DRIVER        = "HNA";
	public static final String DSM_DRIVER        = "DSM";
	public static final String AFB_DRIVER        = "AFB";
	public static final String RMB_DRIVER 		 = "RMB";
	public static final String APP_PW_DRIVER     = "APW";
	public static final String APP_CH_DRIVER     = "APC";
	public static final String APP_HN_DRIVER     = "AHN";
	public static final String ACCESS_DRIVER     = "AC";
	public static final String NEXT_STEPS_DRIVER = "NXT";
	
	public static final String id             = "id";
	public static final String ID             = "id";
	public static final String DRIVER         = "driver";
	public static final String DATALIST       = "datalist";
	public static final String DRIVERXML      = "driverxml";
	public final static String PAGEACTION     = "pageaction";
	public static final String INIT_STATUS    = "initStatus";
	public static final String BUSINESSDRIVER = "businessDriver";
	
	/*
	 * 
	 *	File And Folder Paths 
	 * 
	 */
	public static final String CONFIG_FILE_JHOVE    = "jhove.conf";
	public static final String ISO_SVRL_FOR_XSLT2   = "iso_svrl_for_xslt2.xsl";
	public static final String ISO_ABSTRACT_EXPAND  = "iso_abstract_expand.xsl";
	public static final String CONFIG_FILE_TRANSFER = "/xml/transfer/transferactions.xml";
	
	public static final String SQL_FOLDER               = "sqlFolder";
	public static final String SHARED_SQL               = "sharedSQL";
	public static final String FRAMEWORK_SQL            = "frameworkSQL";
	public static final String DOCUMENT_FOLDER          = "documentFolder";
	public static final String SCHEMATRON_FOLDER        = "schematronFolder";
	public static final String SCHEMATRON_SCHEMA_FOLDER = "schematronSchemaFolder";
	
	/*
	 * 
	 *	Email
	 * 
	 */
	public static final String EMAIL_ID             = "ID";
	public static final String EMAIL_LIST           = "VALUE";
	public static final String EMAIL_ROOT           = "EMAILS";
	public static final String EMAIL_BODY           = "BODY";
	public static final String EMAIL_HEADER         = "HEADER"; 
	public static final String EMAIL_FOOTER         = "FOOTER";
	public static final String EMAIL_SUBJECT        = "SUBJECT";
	public static final String EMAIL_FOLDER         = "emailFolder";
	public static final String EMAIL_PROCESS_SWITCH = "emailProcess";
	public static final String DISTRIBUTION_ROOT    = "DISTRIBUTIONS";
	
	/*
	 * 
	 *	Database 
	 * 
	 */
	public static final String IN           = " IN ";
	public static final String AND          = " AND ";
	public static final String WHERE        = " WHERE ";
	public static final String NOT_IN       = " NOT IN ";
	public static final String ORDER_BY     = " ORDER BY ";
	public static final String SCHEMA_TOKEN = "~SCHEMA~";

	public static final String CARGO      = "cargo";
	public static final String COLLECTION = "collection";
	
	public static final String OPERATOR  = "operator";
	public static final String DATA_TYPE = "data_type";
	public static final String SQL_IND   = "Sql_Indicator";

	public static final String CRITERIA     = "select_criteria";
	public static final String COLUMN_VALUE = "coulmn_value";
	public static final String COLUMN_NAME  = "coulmn_name";
	
	public static final String SQL_ID    = "id";
	public static final String SQL_VALUE = "value";
	public static final String SQL_TAG   = "sql";
	public static final String SQL_FILE  = "selectsql";	
	
	public static final String INT       = "int";
	public static final String CHAR      = "char";
	public static final String LONG      = "long";
	public static final String SHORT     = "short";	
	public static final String FLOAT     = "float";
	public static final String DOUBLE    = "double";
	public static final String DATE      = "java.sql.Date";
	public static final String STRING    = "java.lang.String";
	public static final String TIMESTAMP = "java.sql.Timestamp";
	
	public static final String DAO         = "dao";
	public static final String SQL         = "sql";
	public static final String LDAP        = "ldap";
	public static final String MSG         = "message";
	public static final String TRANSFER    = "transfer";
	public static final String METHOD_NAME = "methodName";
	public static final String WS          = "web_service";
	public static final String SP          = "stored_procedure";
	public static final String DS          = "document_service";
	
	public static final String LDAP_WID        = "wid";
	public static final String LDAP_EMAIL      = "email";
	public static final String LDAP_LOGON_ID   = "logonId";
	public static final String LDAP_LAST_NAME  = "lastName";
	public static final String LDAP_FIRST_NAME = "firstName";
	
	public static final String DB_SERIALIZABLE     = " WITH RR";
	public static final String DB_READ_COMMITTED   = " WITH CS";
	public static final String DB_REPEATABLE_READ  = " WITH RS";
	public static final String DB_UNCOMMITTED_READ = " WITH UR";
	public static final String DB_ISOLATION_LEVEL  = "ISOLATION_LEVEL";
	
	public static final String ROWACTION_RETRO  = "R";
	public static final String ROWACTION_INSERT = "I";
	public static final String ROWACTION_UPDATE = "U";
	public static final String ROWACTION_DELETE = "D";
	public static final String ROWACTION_SELECT = "S";
	
	public static final int TEXT_MESSAGE   = 1;
	public static final int OBJECT_MESSAGE = 2;
	
	public static final String SMTP_SERVER          = "smtpServer";
	public static final String SMTP_SERVER_INJECT   = "${" + SMTP_SERVER + "}";
	public static final String FROM_EMAIL_ID        = "fromEmailID";
	public static final String FROM_EMAIL_ID_INJECT = "${" + FROM_EMAIL_ID + "}";
	public static final String EMAIL_DETAILS_PATH   = "emailDetailsPath";
	
	/*
	 * 
	 *	Holiday Reference Table 
	 * 
	 */
	public static final int holidaysColumnID        = 1137;
	public static final int caresHolidaysColumnID   = 1138;
	public static final int federalHolidaysColumnID = 1139;
	public static final String HOLIDAYS_REF_TBL     = "TRHY";
	
	/*
	 * 
	 *	Queues
	 * 
	 */
	public static final String ACTIVITY_QCF   = "accessActivity_qcf";
	public static final String ACTIVITY_QUEUE = "accessActivity_queue";
	
	/*
	 * 
	 *	JHove Module Names
	 * 
	 */
	public static final String JHOVE_AIFF       = "AIFF-hul";
	public static final String JHOVE_WAVE       = "WAVE-hul";
	public static final String JHOVE_PDF        = "PDF-hul";
	public static final String JHOVE_PNG        = "PNG-gdm";
	public static final String JHOVE_GIF        = "GIF-hul";
	public static final String JHOVE_TIFF       = "TIFF-hul";
	public static final String JHOVE_XML        = "XML-hul";
	public static final String JHOVE_HTML       = "HTML-hul";
	public static final String JHOVE_UTF8       = "UTF8-hul";
	public static final String JHOVE_ASCII      = "ASCII-hul";
	public static final String JHOVE_JPEG       = "JPEG-hul";
	public static final String JHOVE_JPEG2000   = "JPEG2000-hul";
	public static final String JHOVE_BYTESTREAM = "BYTESTREAM";
	
	/*
	 * 
	 *	Page Manager 
	 * 
	 */
	public static final String URL                   = "URL"; 
	public static final String TITLE                 = "TITLE"; 
	public static final String CMT_TXT               = "CMT_TXT";
	public static final String PAGE_DSC              = "PAGE_DSC";
	public static final String ELEMENTS              = "ELEMENTS"; 
	public static final String BNFT_MSG              = "BNFT_MSG";
	public static final String MESSAGES              = "MESSAGES";
	public static final String HELP_PAGE             = "HELP_PAGE"; 
	public static final String ELEMENT_ID            = "ELEMENT_ID"; 
	public static final String SIMULATION            = "SIMULATION";
	public static final String PERCENTAGE            = "PERCENTAGE";
	public static final String DISPLAY_KEY           = "DISPLAY_KEY"; 
	public static final String DISPLAY_TEXT          = "displaytext";
	public static final String PAGE_DETAILS          = "PAGE_DETAILS";
	public static final String SUPPRESS_REASON_CODES = "SUPPRESS_REASON_CODES";
	public static final String RETURN_URL            = "RETURN_URL";
	
	/*
	 * 
	 *	Verification Reason Codes 
	 * 
	 */
	public static final String VERIFICATION_FAILURE_RSN = "R";
	
	/*
	 * 
	 *  Constants for LDAP Service
	 *  
	 */
	public final static String SUFFIX              = "suffix";
	public final static String USER_ID             = "userID";
	public final static String PASSWORD            = "password";
	public final static String FULL_NAME           = "fullName";
	public final static String LAST_NAME           = "lastName";
	public final static String FIRST_NAME          = "firstName";
	public final static String MIDDLE_INITIAL      = "middleInitial";
	public final static String EMAIL               = "emailAddress";
	public final static String SECRET_ANSWER       = "secretAnswer";
	public final static String SECRET_QUESTION     = "secretQuestion";
	public final static String SECRET_QUESTION1    = "secretQuestion1";
	public final static String SECRET_QUESTION2    = "secretQuestion2";
	public final static String RE_ENTERED_PASSWORD = "reEnteredPassword";	
	public final static String SECRET_ANSWER1      = "secretQuestionAnswer1";	
	public final static String SECRET_ANSWER2      = "secretQuestionAnswer2";	
	
	public final static String USER_ID_ALREADY_EXISTS              = "1";
	public final static String NOT_ABLE_TO_CREATE_ACCOUNT          = "2";
	public final static String PASSWORD_ALREADY_USED               = "3";
	public final static String USER_ID_DOES_NOT_MATCH_WITH_SSN     = "4";
	public final static String ANSWER_DOES_NOT_MATCH               = "5";
	public final static String UNABLE_TO_PROCESS                   = "6";
	public final static String USER_ID_DOES_NOT_MATCH              = "7";
	public final static String EMAIL_ID_DOES_NOT_MATCH             = "8";
	public final static String ACCOUNT_ALREADY_EXISTS_FOR_EMAIL_ID = "9";
	
	public final static String LOGIN_EXPRN = "LOGIN_EXPRN";
	public final static String PSSWD_EXPRN = "PSSWD_EXPRN";	
	
	/*
	 * 
	 * Web Service
	 * 
	 */
	public final static String USER_NAME              = "userName";
	public final static String PASSWORD_TEXT 		  = "passwordText";
	public final static String PASSWORD_TYPE          = "passwordType";
	public final static String PASSWORD_DIGEST        = "passwordDigest";
	public final static String WISA_PROFILE_ENDPOINT  = "wisaProfileURL";
	public final static String WISA_PROFILE_PORT_NAME = "ProfileSecurityV2EJB";
	public final static String WISA_PROFILE_USER_NAME = "WisaProfile_UserName";
	public final static String WISA_PROFILE_PASSWORD  = "WisaProfile_Password";
	
	/*
	 * 
	 * Individual service WS
	 * 
	 */
	public final static String INDIVIDULAL_SERVICE_ENDPOINT  = "individualURL";
	public final static String INDIVIDULAL_SERVICE_PORT_NAME = "IndividualServicesEJB";
	public final static String INDIVIDULAL_SERVICE_USER_NAME = "IndividualService_UserName";
	public final static String INDIVIDULAL_SERVICE_PASSWORD  = "IndividualService_Password";
	
	/*
	 * 
	 * Recipient Transfer service WS
	 * 
	 */
	public final static String RECIPIENT_TRANSFER_ENDPOINT  = "interChangeURL";
	public final static String RECIPIENT_TRANSFER_PORT_NAME = "RecipientTransferSoap";
	public final static String RECIPIENT_TRANSFER_USER_NAME = "RecipientTransfer_UserName";
	public final static String RECIPIENT_TRANSFER_PASSWORD  = "RecipientTransfer_Password";
	
	/*
	 * 
	 * Encryption 
	 * 
	 */
	public static final int	KEY_SIZE_56        = 56;
	public static final String HASHING_MD5     = "MD5";
	public static final String ALGO_BLOWFISH   = "Blowfish";
    public static final String MODE_ECB        = "ECB";
    public static final String PADDING_PKCS5   = "PKCS5Padding";
    public static final String PROVIDER_SUNJCE = "SunJCE";
	public static final String PROVIDER_IBMJCE = "IBMJCE";
    public static final String ENCODING_UTF8   = "UTF-8";
    
    public final static String FACTORY_MODE_CORE     = "core";
    public final static String FACTORY_METHOD_CREATE = "getInstance";
     
    /*
     * 
     * 
     * Constants For User Info
     * 
     * 
     * 
     */
    public final static String USER_SSN         = "ssn";
    public final static String USER_PIN         = "pin";
    public final static String ACS_ID           = "acsUserId";
    public final static String USER_MCI_ID      = "userMciId";
    public final static String USER_PIN_NUM     = "pinNumber";
    public final static String USER_EBT_NUMBER  = "ebtNumber";
    public final static String WARN_MSG         = "warningMsg";
    public final static String CASE_ID_TYPE     = "caseIdType";	
    public final static String USER_CASE_NUMBER = "caseNumber";
    public final static String USER_MAID_NUMBER = "maidNumber";
    public final static String DATE_OF_BIRTH    = "dateOfBirth";
    public final static String CASE_ID_NUM      = "caseIdNumber";
    
    /*
     * 
     * Constants For AFB Security
     * 
     */
     public static final String AFB_APPLICATION = "afbApplication";
     
     /*
      * 
      * General Constants
      * 
      * 
      */
 	public static final String NO = "N";
 	public static final String YES = "Y";
 	
    /*
     * 
     * Push Notification Device Data
     * 
     * 
     */
	public final static String DEVICE_TOKEN                  = "deviceToken";
	public final static String TOKEN_INDICATOR               = "tokenIndicator";
	public final static String LANGUAGE_INDICATOR            = "languageIndicator";
	public final static String DEVICE_LOGIN_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
}

