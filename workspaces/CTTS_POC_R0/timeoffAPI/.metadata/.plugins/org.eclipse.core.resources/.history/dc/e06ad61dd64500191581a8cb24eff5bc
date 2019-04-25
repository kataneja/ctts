package gov.wisconsin.framework.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.base.IPage;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.constant.IReferenceConstants;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FwPageManager extends FwBaseClass implements IPage {
    
	private static IPage instance;
	
	private static Hashtable pageMap = new Hashtable();

	private FwPageManager() {}

	@Override
	public void init() {
		try {	
			getDisplayTextMap();
			getPageDetails();
			getSuppressReasonCodesMap();
			loadMessageTable();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	public void getPageDetails() {
		try {
			Map criteria = new HashMap();
			Map mapDataMap = new HashMap();
			criteria.put(FwConstants.SQL_IND, "framework-01");
			List result = dataManager.execute(FwConstants.SQL, criteria);
			
			for(int i = 0; i < result.size(); i++) {
				Map dataMap = new HashMap();
				Map row = (Map) result.get(i);
				dataMap.put(FwConstants.URL, row.get("PAGE_URL_ADR"));
				dataMap.put(FwConstants.PAGE_DSC, row.get("PAGE_DSC"));
				dataMap.put(FwConstants.HELP_PAGE, row.get("HELP_PAGE_URL_ADR"));
				dataMap.put(FwConstants.PERCENTAGE, row.get("PROG_PCT"));
				mapDataMap.put(row.get("PAGE_ID"), dataMap);
			}
			
			pageMap.put(FwConstants.PAGE_DETAILS, mapDataMap);
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}

	public void getDisplayTextMap() {
		try {
			Map criteria1 = new HashMap();
			Map returnMap = new HashMap();
			criteria1.put(FwConstants.SQL_IND, "framework-02");
			List result1 = dataManager.execute(FwConstants.SQL, criteria1);
			
			for(int i = 0; i < result1.size(); i++) {
				Map dataMap = new HashMap();
				Map row = (Map) result1.get(i);
				dataMap.put(FwConstants.CMT_TXT,row.get("CMT_TXT"));
				dataMap.put(row.get("LANG_CD").toString().trim(), row.get("TXT_DSC"));
				returnMap.put(row.get("TXT_ID").toString().trim() + row.get("LANG_CD").toString().trim().toUpperCase(), dataMap);
			}
			
			String msgType = null;
			Map criteria2 = new HashMap();
			Map messageMap = new HashMap();
			StringBuffer sbf1 = new StringBuffer();
			StringBuffer sbf2 = new StringBuffer();
			criteria2.put(FwConstants.SQL_IND, "framework-03");
			List result2 = dataManager.execute(FwConstants.SQL, criteria2);

			for(int j = 0; j < result2.size(); j++) {
				sbf1.delete(0, sbf1.length());
				sbf2.delete(0, sbf2.length());
				Map row = (Map) result2.get(j);
				msgType = row.get("MSG_TYP").toString();
				sbf1.append(row.get("VFCN_RSN_CD")).append(FwConstants.UNDERSCORE).append(msgType);
				if(msgType.equals(FwConstants.VERIFICATION_FAILURE_RSN)) {
					sbf1.append(FwConstants.UNDERSCORE).append(row.get("DEPT_CD"));
				}
				sbf2.append(row.get("TXT_ID")).append(FwConstants.UNDERSCORE).append(row.get("HIST_FLG"));
				messageMap.put(sbf1.toString(),sbf2.toString());
			}
			pageMap.put(FwConstants.BNFT_MSG, messageMap);
			pageMap.put(FwConstants.DISPLAY_TEXT, returnMap);
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}

	public String getURL(String page_id) {
		Map details = (Map) pageMap.get(FwConstants.PAGE_DETAILS);
		Map data = (Map) details.get(page_id);
		return (String) data.get(FwConstants.URL);
	}

	public String getProgress(String page_id) {
		Map details = (Map) pageMap.get(FwConstants.PAGE_DETAILS);
		Map data = (Map) details.get(page_id);
		return (String) data.get(FwConstants.PERCENTAGE);
	}

	public String getDisplayText(int textID, String language_indicator) {
		Map innerMap = null;	
		Map data = (Map) pageMap.get(FwConstants.DISPLAY_TEXT);
		
		String chkValue = textID+language_indicator;
		if (data.containsKey(chkValue)) {
			innerMap = (Map) data.get(chkValue);
			return (String) innerMap.get(language_indicator.toUpperCase());
		}
		else {
		    StringBuffer msg = new StringBuffer(100);
		    msg.append("The Data for the text id (");
		    msg.append(textID);
		    msg.append(") requested is not available for the language type of ");
		    msg.append(language_indicator);
		    msg.append(" using default instead.");
		    
		    apiLogger.info(msg.toString());			

			chkValue = textID + FwConstants.ENGLISH;
			if (data.containsKey(chkValue)) {
				innerMap = (Map) data.get(chkValue);
				String textVal = (String) innerMap.get(FwConstants.ENGLISH.toUpperCase());
				StringBuffer font = new StringBuffer(textVal);
				font.append(" [").append(textID).append("] ");
				return font.toString();
			}
			else {
				FwExceptionManager.handleException(this.getClass(), new FwException("The Data for the text id " + chkValue + " is not available"), FwConstants.EXP_TYP_FRAMEWORK);
			}
		}
		return null;
	}

	public String getMessageHistory(String vfcnRsnCd, String msgType, String aCatCode) {
		String chkValueStr = null;
		StringBuffer chkValueBuffer = new StringBuffer();
		Map data = (Map) pageMap.get(FwConstants.BNFT_MSG);
		chkValueBuffer.append(vfcnRsnCd).append(FwConstants.UNDERSCORE).append(msgType.trim());
		if(msgType.equals(FwConstants.VERIFICATION_FAILURE_RSN)){
			String deptCode = referenceTableManager.getColumnValue("TBMC", 226, aCatCode, FwConstants.ENGLISH);
			chkValueBuffer.append(FwConstants.UNDERSCORE).append(deptCode);
		}
		chkValueStr = chkValueBuffer.toString();
		if (data.containsKey(chkValueStr)) {
			String messageCode =(String)data.get(chkValueStr);
			return messageCode.substring(messageCode.indexOf(FwConstants.UNDERSCORE)+1);
		}
		else {
			FwExceptionManager.handleException(this.getClass(), new FwException("The Data for the message type " + chkValueStr + " is not available"), FwConstants.EXP_TYP_FRAMEWORK);
		}
		return null;
	}

	public String getMessageCode(String vfcnRsnCd,String msgType, String aCatCode) {
		String chkValueStr = null;
		StringBuffer chkValueBuffer = new StringBuffer();
		Map data = (Map) pageMap.get(FwConstants.BNFT_MSG);
		chkValueBuffer.append(vfcnRsnCd).append(FwConstants.UNDERSCORE).append(msgType.trim());
		if(msgType.equals(FwConstants.VERIFICATION_FAILURE_RSN)){
			String deptCode = referenceTableManager.getColumnValue("TBMC", 226, aCatCode, FwConstants.ENGLISH);
			chkValueBuffer.append(FwConstants.UNDERSCORE).append(deptCode);
		} 
		chkValueStr = chkValueBuffer.toString();
		if (data.containsKey(chkValueStr)) {
			String messageCode =(String)data.get(chkValueStr);
			return messageCode.substring(0,messageCode.indexOf(FwConstants.UNDERSCORE));
		}
		else {
			FwExceptionManager.handleException(this.getClass(), new FwException("The Data for the message type " + chkValueStr + " is not available"), FwConstants.EXP_TYP_FRAMEWORK);
		}
		return null;
	}

	public Map getAllData() {
		return (Map) pageMap;
	}

	public void loadMessageTable() {
		try {
			Map criteria = new HashMap();
			Map mapMsgTable = new HashMap();
			criteria.put(FwConstants.SQL_IND, "framework-04");
			List result = dataManager.execute(FwConstants.SQL, criteria);
			
			for(int i = 0; i < result.size(); i++) {
				Map dataMap = new HashMap();
				Map row = (Map) result.get(i);
				dataMap.put(IReferenceConstants.SVR_ID, row.get("SVR_CD"));
				dataMap.put(IReferenceConstants.MSG_DSC, row.get("MSG_DSC"));
				mapMsgTable.put(row.get("MSG_ID").toString().trim() + row.get("LANG_CD").toString().trim().toUpperCase(), dataMap);
			}
			
			pageMap.put(FwConstants.MESSAGES, (Object) mapMsgTable);
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}

	public Map getMessages() {
		return (HashMap) pageMap.get(FwConstants.MESSAGES);
	}
	
	public String[] getMessageByCode(String code, String language) {
		Map allMsg = getMessages();
		String[] retStr = new String[2];
		Map specificMsg = (Map)allMsg.get(code.trim()+language.trim());
		if(specificMsg != null) {
			retStr[0] = (String) specificMsg.get(IReferenceConstants.SVR_ID).toString();
			retStr[1] = (String) specificMsg.get(IReferenceConstants.MSG_DSC).toString();
		}			
		return retStr;
	}

	public void getSuppressReasonCodesMap() {
		try {
			Map criteria = new HashMap();
			Map returnMap = new HashMap();
			criteria.put(FwConstants.SQL_IND, "framework-05");
			List result = dataManager.execute(FwConstants.SQL, criteria);

			for(int i = 0; i < result.size(); i++) {
				Map dataMap = new HashMap();
				Map row = (Map) result.get(i);
				dataMap.put("ACTION", row.get("ACTN_IND"));
				dataMap.put("SUBST_RSN_CD", row.get("SUB_RSN_CD"));
				dataMap.put("CATEGORY", row.get("CAT_CD"));
				returnMap.put(row.get("RSN_CD"), dataMap);
			}
			
			pageMap.put(FwConstants.SUPPRESS_REASON_CODES, returnMap);
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	public String[] getRefTableMessage(String msgCode, String language) {
		try {
			return pageManager.getMessageByCode(msgCode, language);
		} catch (FwException fe) {
			String[] sa = new String[2];
			sa[0] = "1";
			sa[1] = new StringBuffer("Error: ").append(msgCode).toString();
			return sa;
		}
	}
	
    public static void setInstance(IPage pageManager) {
    	instance = pageManager;
    }
    
    public static IPage getInstance() {
    	return instance;
    }
}
