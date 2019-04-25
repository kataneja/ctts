package gov.wisconsin.framework.constant;

import java.sql.Date;

public final class AppConstants {

	/*
	 * 
	 *	Numbers as Strings
	 * 
	 */
	public static final String ZERO   = "0";
	public static final String ONE    = "1";
	public static final String TWO    = "2";
	public static final String THREE  = "3";
	public static final String FOUR   = "4";
	public static final String FIVE   = "5";
	public static final String SIX    = "6";
	public static final String SEVEN  = "7";
	public static final String EIGHT  = "8";
	public static final String NINE   = "9";
	
	/*
	 * 
	 *	Dates
	 * 
	 */
	public static final String LOW_DATE               = "0001-01-01";
	public final static String HIGH_DATE              = "9999-12-31";
	public final static String IC_HIGH_DATE           = "2299-12-31";
	public final static String HIGH_MONTH             = "999912";
	public final static String HIGH_YEAR              = "9999";
	public final static String HIGH_MONTH_YEAR        = "129999";
	public static final String HIGH_PIN_NUM           = "9999999999";
	public static final String HIGH_CASE_NUM          = "9999999999";	
	public final static String HIGH_MONTH_YEAR_FORMAT = "12/9999";
	public static final Date HIGH_DATE_OBJ = Date.valueOf(AppConstants.HIGH_DATE);
	public static final Date LOW_DATE_OBJ               = Date.valueOf(AppConstants.LOW_DATE);
	
	/*
	 * 
	 *	Display Text
	 * 
	 */
	public static final String AT            = "at";
	public static final String AT_SPANISH    = "a las";
	public static final String POST_MERIDIAN = "P.M.";
	public static final String ANTE_MERIDIAN = "A.M.";
	
	/*
	 * 
	 *	Webservice Status
	 * 
	 */
	public static final String WS_READY     = "RE";
	public static final String WS_PENDING   = "PE";
	public static final String WS_EXCEPTION = "EX";
	public static final String WS_PROCESSED = "PR";
	
	/*
	 * 
	 *	Joint Owner
	 * 
	 */
	public static final String JOINT_OWNER_TYPE_LIQUID_ASSET      = "LA";
	public static final String JOINT_OWNER_TYPE_REAL_PROPERTY     = "RP";
	public static final String JOINT_OWNER_TYPE_VEHICLE_ASSET     = "VA";
	public static final String JOINT_OWNER_TYPE_LIFE_INSURANCE    = "LI";	
	public static final String JOINT_OWNER_TYPE_PERSONAL_PROPERTY = "PP";
	
	/*
	 * 
	 *	URLs
	 * 
	 */
	public static final String FS_WHAT_TO_BRING    = "FSW";
	public static final String WHEAP_WHAT_TO_BRING = "WHW";
	public static final String FS_ONE_PAGE_FORM    = "FSA";
	public static final String FS_LEARN_MORE       = "FSL";
	public static final String WHEAP_LEARN_MORE    = "WHL";
	public static final String LTC_LEARN_MORE      = "LTL";
	public static final String FMA_APPLICATION     = "FMA";
	public static final String FAM_LEARN_MORE      = "FML";
	public static final String FAM_LEARN_MORE_CORE = "FMC";
	public static final String BC_LEARN_MORE       = "BCL";
	public static final String SC_APPLICATION      = "SCA";
	public static final String SC_LEARN_MORE       = "SCL";
	public static final String CC_LEARN_MORE       = "CCL";
	public static final String CC_LOCAL_AGENCY     = "CAG";
	public static final String SLIF_APPLICATION    = "SLA";
	public static final String SLIF_LEARN_MORE     = "SLL";
	public static final String WW_LEARN_MORE       = "WWL";
	public static final String WW_LOCAL_AGENCY     = "WAG";
	public static final String IM_AGENCY           = "IAG";
	public static final String MPD_LEARN_MORE      = "MPD";
	public static final String MPD_APPLICATION     = "MPA";
	public static final String GET_ACROBAT_READER  = "ACR";
	public static final String TAX_LEARN_MORE      = "TXL";
	public static final String EBD_APPLICATION     = "EBA";
	public static final String EBD_LEARN_MORE      = "EBL";
	public static final String AUT_APPLICATION     = "AUT";
	public static final String TEF_LEARN_MORE      = "TFL";
	public static final String SBL_LEARN_MORE      = "SML";
	public static final String SFS_LEARN_MORE      = "SFL";
	public static final String WIC_LEARN_MORE      = "WCL";
	public static final String EBS_LEARN_MORE      = "EBS";
	public static final String MEDICAID_ADDENDUM   = "MAA";
	public static final String DIS_RES_CENTER      = "DRC";
	public static final String COM_OPT_COORDINATOR = "COM";
	
	/*
	 * 
	 *	User Types
	 * 
	 */
	public static final String USER_TYPE_IVR              = "I";
	public static final String USER_TYPE_WORKER           = "W";
	public static final String USER_TYPE_CUSTOMER         = "C";
	public static final String USER_TYPE_CHOICE_FORM      = "F";
	public static final String USER_TYPE_WORKER_WITH_CASE = "WC";	
	public static final String NATIVE_AMERICAN            = "E74";
	public static final String MIGRANT_WORKER             = "E75";
	
	/*
	 * 
	 *	Images
	 * 
	 */
	public static final String STAR_FILLED = "STAR_FILLED";
	public static final String STAR_FILLED_IMG = "/star_filled.gif";
	public static final String STAR_FILLED_ALT = "";
	public static final String STAR_EMPTY = "STAR_EMPTY";
	public static final String STAR_EMPTY_IMG = "/star_empty.gif";
	public static final String STAR_EMPTY_ALT = "";
	
	/*
	 * 
	 *	Database
	 * 
	 */
	public static final short CURRENT_HISTORY_CODE = 0;
	
	/*
	 * 
	 *	Validation
	 * 
	 */
	public static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	
	/*
	 * 
	 *	Queues
	 * 
	 */
	public static final String ACCESSTOCWWINVOKEACP_QCF      = "accessToCwwInvokeACP_qcf";
	public static final String ACCESSTOCWWINVOKEACP_QUEUE    = "accessToCwwInvokeACP_queue";
	public static final String ACCESSAPPLICATIONSUBMIT_QCF   = "accessApplicationSubmit_qcf";
	public static final String ACCESSAPPLICATIONSUBMIT_QUEUE = "accessApplicationSubmit_queue";

	/*
	 * 
	 *	Access 3.0
	 * 
	 */
	public static final String NOVIEW     = "0";
	public static final String FULLVIEW   = "20";
	public static final String TARGETVIEW = "10";
	
	public static final String ELIGIBLE       = "E";
	public static final String INELIGIBLE     = "I";
	public static final String INDVINELIGIBLE = "X";
	
	public static final String TYPEOFVIEW        = "TYPEOFVIEW";
	public static final String ACCESS_LEVEL_COLL = "ACCESS_LEVEL_COLL";
	
	/*
	 * 
	 * 
	 * 
	 * constants related to Access2CWW RealTime Process
	 * 
	 * 
	 * 
	 * 
	 */
	public static final String ACCESS2CWWREALTIME_QUEUE = "accessToCwwRealTime_queue";
	public static final String ACCESS2CWWREALTIME_QCF = "accessToCwwRealTime_qcf";
	
	
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String EMPTY_STRING = "";
	
	public static final String SEX_IND_MALE = "M";
	public static final String SEX_IND_FEMALE = "F";
	
	public static final char PE_APP_TYPE = '1';
	public static final char CH_APP_TYPE = '2';
	public static final char AFB_APP_TYPE = '3';
	public static final char HCE_APP_TYPE = '4';
	public static final char CMB_APP_TYPE = '5';
	public static final char RMC_APP_TYPE = '6';
	public static final char ACC_APP_TYPE = '7';
	public static final char APP_APP_TYPE = '8';
	public static final char AIE_APP_TYPE = '9';
	public static final char RMB_APP_TYPE = 'A';
	public static final char SMRF_APP_TYPE = 'B';
	public static final char FEE_APP_TYPE = 'F';
	public static final char DSM_APP_TYPE = 'C';
	public static final char ADD_A_PROGRAM_APP_TYPE = 'D';
	public static final String NOTICE_PROOF_NEEDED = "P";
	
	public static final String CMB = "CMB";
	public static final String AFB = "AFB";
	public static final String RMC = "RMC";
	public static final String HCE = "HCE";
	public static final String RMB = "RMB";
	public static final String SMF = "SMF";
	public static final String ADD_A_PROGRAM = "APA";
	
	public static final short FMA_INDEX = 0;
	public static final short FPW_INDEX = 1; 
	public static final short FS_INDEX = 2;
	public static final short HC_INDEX = 3;
	public static final short EBD_INDEX = 4;
	public static final short CLA_INDEX = 5;
	public static final short CC_INDEX = 8;
	
	public static final String INDV_SEQUENCE_NUMBERS = "INDV_SEQUENCE_NUMBERS";
	public static final String INDV_DESCRIPTIONS = "INDV_DESCRIPTIONS";
	
	public static final String DSM_STATUS_PURGED = "PU";
	public static final String DSM_STATUS_PENDING = "PE";
	public static final String DSM_STATUS_PROCESSED = "PR";
	public static final String DSM_STATUS_INPROGRESS ="IP";
	public static final String DSM_STATUS_SKIPPED ="SK";
	public static final String DSM_STATUS_ABANDONED  = "AB";
	public static final String DSM_STATUS_CLEANED_UP = "PX";
	public static final String DSM_STATUS_SKIP_PRUGE = "SP";
	public static final String DSM_STATUS_NOT_SENT = "NS";
	public static final String DSM_STATUS_DELETED = "D";
	public static final String DSM_STATUS_COMPLETE = "C";
	public final static String DSM_STATUS_NOT_REQUIRED = "N";
	public final static String DSM_STATUS_REQUIRED = "R";
	public final static String DSMZEROS = "0000000000";
	public final static String INTIAL_DOC_SEQ_NUMBER = "1";
	public static final int PEND_CODE_PARTITION_SIZE = 3;
	public static final int DOC_CODE_PARTITION_SIZE = 2;
	public static final String DSM_PEND_TYPE_GROUPED ="G" ;
	public static final String DSM_PEND_TYPE_INDIVIDUAL ="I" ;
	public static final String CHILD_CARE_SMRF = "CC";
	public static final String BURIAL_ASSESTS= "AB";
	public static final String LIFE_INSURANCE_ASSESTS= "AI"; 
	public static final String LIQUID_ASSESTS = "AL";
	public static final String EMPLOYMENT_SELF = "SE";
	public static final String OTHER_INCOME = "UI";
	public static final String REAL_PROPERTY_ASSESTS= "AR";
	public static final String SHELTER_COST_ABR = "SC";
	public static final String TAX_DEDC = "TD";
	public static final String UTILITY_COSTS_ABR = "UC";
	public static final String VEHICLE_ASSESTS= "AV";
	public static final long DOC_SIZE_LIMIT= 10485760L;
	public static final String OTHER_DOCUMENT_CODE = "ZZ";
	public static final String DSM_NEEDED_DOCUMENT_DAYS = "DN";
	public static final String DSM_SUBMITTED_DOCUMENT_DAYS = "DS";
	public static final String DOCUMENT_STATUS_CODE_RECEIVED ="RC";
	public static final String DOCUMENT_STATUS_CODE_WAITING ="WT";
	public static final String DOCUMENT_STATUS_CODE_MOVE_TO_CASE ="MV";
	public static final String DOCUMENT_STATUS_CODE_PROCESSED ="PR";
	public static final String DOCUMENT_STATUS_CODE_DELETED = "DS";
	public static final String ACCESS_DOC_SCAN = "A";
	public static final String MOBILE_DOC_SCAN = "M";
	public static final String MOBILE_PROOF_DESC_EN = "Proof of";
	public static final String MOBILE_PROOF_DESC_ES = "Prueba de";
	public static final String PROOF_NEEDED_LIST = "caseProofList";
	public static final String BAT_NUM = "batNum";
	public static final String DOC_ID = "docID";
	public static final String CASE_LIST = "caseList";
	public static final String SUBMITTED_DOC_LIST = "docList";
	public static final String SUBMITTED_DOC_IMAGE = "submittedImage";
	public static final String OTHER_CHANNEL = "O";
	public static final String SCAN_CHANNEL = "S";
	public static final String FAX_CHANNEL = "F";
	public static final String SUPPORT_OBLIGATION_PAYMENT = "SP"; 
	public static final String MEDICAL_EXPENSE = "ME";
	public static final String TRANSFER_ASSETS = "AT";
	public static final String ELG_DATA = "ELG_DATA";
	public static final String FULL_VIEW_ACCESS = "F";
	public static final String TARGET_VIEW_ACCESS = "T";
	public static final String OTHER_PROOF_TYPE = "OT";
	public static final String UNKNOWN_DOCUMENT = "Unknown Document";
	public static final String UNKNOWN_DOCUMENT_CODE = "UN";
	
	
	public static final String ON_WAITLIST = "OW";
	
	public static final String HEALTH_CARE_M01 = "M01";
	public static final String HEALTH_CARE_M02 = "M02";
	public static final String HEALTH_CARE_M03 = "M03";
	public static final String HEALTH_CARE_M04 = "M04";
	public static final String HEALTH_CARE_M05 = "M05";
	public static final String HEALTH_CARE_M06 = "M06";
	public static final String HEALTH_CARE_M07 = "M07";
	public static final String HEALTH_CARE_M08 = "M08";
	public static final String HEALTH_CARE_M09 = "M09";
	public static final String HEALTH_CARE_M10 = "M10";
	public static final String HEALTH_CARE_M11 = "M11";
	public static final String HEALTH_CARE_M12 = "M12";
	public static final String HEALTH_CARE_M13 = "M13";
	public static final String HEALTH_CARE_M14 = "M14";
	public static final String HEALTH_CARE_M15 = "M15";
	public static final String HEALTH_CARE_M16 = "M16";
	public static final String HEALTH_CARE_M17 = "M17";
	public static final String HEALTH_CARE_M18 = "M18";
	public static final String HEALTH_CARE_M19 = "M19";
	public static final String HEALTH_CARE_M20 = "M20";
	public static final String HEALTH_CARE_M21 = "M21";
	public static final String HEALTH_CARE_M22 = "M22";
	public static final String HEALTH_CARE_M23 = "M23";
	public static final String HEALTH_CARE_M24 = "M24";
	public static final String HEALTH_CARE_M25 = "M25";
	public static final String HEALTH_CARE_M26 = "M26";
	public static final String HEALTH_CARE_M50 = "M50";
	public static final String HEALTH_CARE_MAJ_CAT = "HC";
	public static final String HEALTH_CARE_BCLATOHC_M50 = "M50HC";

	public static final String BADGERCARE_PLUS = "BCP";
	public static final String BADGERCAREPLUS_CORE_PLAN = "BCLA";
	public static final String HEALTH_CARE = "HC";
	public static final String FOOD_SHARE = "FS";
	public static final String CHILD_CARE = "CC";
	public static final String CARETAKER_SUPPLEMENT = "CTS";
	public static final String SENIOR_CARE = "SC";
	public static final String WISCONSIN_WORKS = "WW";
	
	public static final String ADULT = "A";
	public static final String CHILD = "C";

	public static final int INDIVIDUAL_AGE_EIGHTEEN = 18;
	public static final String PERSON_ADD ="PERSON ADD";
	
	public static final String CURRENT_MODE  = "CURRENT_MODE";
	public static final String HISTORY_MODE  = "HISTORY_MODE";
	public static final String HIST_MONTH_LIST  = "HIST_MONTH_LIST";
	
	public final static String ELIGIBILITY_CONFIRM = "1";
	public final static String ELIGIBILITY_UN_CONFIRM = "0";
	public final static String ELIGIBILITY_HISTORY = "9";
	
	public static final int CTS_MAX_SEQ =2;
	public static final int M01_MAX_SEQ =1; 
	public static final int M02_MAX_SEQ =1;
	public static final int M03_MAX_SEQ =15;
	public static final int M04_MAX_SEQ =1;
	public static final int M05_MAX_SEQ =2;
	public static final int M06_MAX_SEQ =5;
	public static final int M07_MAX_SEQ =2;
	public static final int M08_MAX_SEQ =2;
	public static final int M09_MAX_SEQ =2;
	public static final int M10_MAX_SEQ =2;
	public static final int M11_MAX_SEQ =1;
	public static final int M12_MAX_SEQ =15;
	public static final int M50_MAX_SEQ =2;
	public static final int FS_MAX_SEQ =1;
	public static final int CC_MAX_SEQ =1;
	public static final int SC_MAX_SEQ =2;
	public static final int WW_MAX_SEQ =10;
	public static final int ENROLLMENT_SERVICE_CENTER_COUNTY = 74;
	
	public static final String REVIEW_CASE_NUM = "reviewCaseNum";
	public static final String MESSAGE_BEN_OPEN = "O";
	public static final String MESSAGE_MED_BEN_OPEN = "M";
	public static final String MESSAGE_BEN_CLOSED = "C";
	public static final String MESSAGE_BEN_DENIED = "D";
	public final static String BNFT_RGST_IND = "BNFT_RGST_IND";
	
	//Constants for Load Dashboard
	public final static String OPEN = "O";
	public final static String PENDING = "P";
	public final static String CLOSED = "C";
	public final static String DENIED = "D";
	public final static String DEDUCTIBLE = "M";
	public final static String TAPPABLE = "T";
	public final static String UNTAPPABLE = "U";
	public final static String CASETYPECARES = "C";
	public final static String CASETYPEIC = "M";
	public final static String BENEFITS_AVAILABLE = "Y";
	public final static String APP_IN_PROGRESS_NO_AVAILABLE_BENEFITS = "P";
	public final static String NO_ACTIVE_BENEFITS_FOR_LAST_3_MONTHS = "N";
	public final static String LOCKED_ACCOUNT_ASSOCIATED = "L";
	public final static String CONFIDENTIAL_CASE_ASSOCIATED_WITH_ACCOUNT = "C";
	public final static String NO_BENEFIT_ASSOCIATED_WITH_ACCOUNT = "N";
	public final static String TRACKING_NUMBER = "trackingNumber";
	public final static String EMAIL_ADDRESS = "emailAddress";
	public final static String SSN_OR_PIN = "ssnOrPin";
	public final static String CMB_ACCOUNT = "cmbAccount";
	public final static String AFB_ACCOUNT = "afbAccount";
	public final static String ACCOUNT_TYPE = "accountType";
	public final static String CASE_MODE_REVIEW = "R";
	public final static String CASE_MODE_ONGOING = "O";
	public final static String TRUE_FLAG = "true";
	public final static String FALSE_FLAG = "false";
	//AG Codes
	public static final String VALID_AG_STAT_RSN_CD_077 = "077";
	//Reminder types
	public final static String ACTION_NEEDED = "A";
	public final static String INFORMATION_NEEDED = "I";
	public final static String RENEWAL = "R";
	//Reminder codes
	public final static String REMINDER_CD_R01 = "R01";
	public final static String REMINDER_CD_R02 = "R02";
	public final static String REMINDER_CD_R03 = "R03";
	public final static String REMINDER_CD_R04 = "R04";
	public final static String REMINDER_CD_R05 = "R05";
	public final static String REMINDER_CD_R06 = "R06";
	public final static String REMINDER_CD_R07 = "R07";
	public final static String REMINDER_CD_R08 = "R08";
	public final static String REMINDER_CD_R09 = "R09";
	public final static String REMINDER_CD_R10 = "R10";
	public final static String REMINDER_CD_R11 = "R11";
	public final static String REMINDER_CD_R12 = "R12";
	public final static String REMINDER_CD_R13 = "R13";
	public final static String REMINDER_CD_R14 = "R14";
	public final static String REMINDER_CD_R15 = "R15";
	public final static String REMINDER_CD_R16 = "R16";
	//Reference table code values
	public final static String REFERENCE_CODE_VALUE_001 = "001";
	public final static String REFERENCE_CODE_VALUE_002 = "002";
	public final static String REFERENCE_CODE_VALUE_003 = "003";
	//ACCOUNT TYPE
	public final static String AFB_ACNT = "A";
	public final static String CMB_ACNT = "C";
	//Upgrade switch service
	public final static String TRUE_FLAG_STRING = "T";
	
	/*
	 * 
	 *	Broadcast Messages
	 * 
	 */
	public final static String BROADCAST_TIME_FORMAT = "HH:mm:ss";
	public final static String BROADCAST_DATE_FORMAT = "yyyy-MM-dd";
	
	public final static String BROADCAST_MESSAGE           = "message";
	public final static String BROADCAST_MESSAGE_ID        = "messageID";
	public final static String BROADCAST_MESSAGE_ENDDATE   = "endDate";
	public final static String BROADCAST_MESSAGE_ENDTIME   = "endTime";
	public final static String BROADCAST_MESSAGE_STARTDATE = "startDate";
	public final static String BROADCAST_MESSAGE_STARTTIME = "startTime";
	public final static String BROADCAST_MESSAGE_LANGUAGE  = "language";
	public final static String BROADCAST_MESSAGE_KEY       = "broadcastMessagesList";
	
	public final static String DB_HEALTH_STATUS            = "dbHealthStatus";
	public final static String ONLINE_HEALTH_STATUS        = "onlineHealthStatus";

	/*
	 * 
	 *	Password allowed special characters
	 * 
	 */
	public final static char[] ALLOWED_SPECIAL_CHARACTERS = {'@', '%', '+', '/', '\'', '!', '#', '$', '^', '?', ':', ',', '(', ')', '{', '}', '[', ']', '~', '`', '-', '_', '.'};
}
