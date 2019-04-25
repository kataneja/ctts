package gov.wisconsin.framework.base;

import java.util.Map;

public interface IPage {

	public void init();
	
	public void getPageDetails();

    public String getURL(String page_id);

	public String getProgress(String page_id);

    public String getDisplayText(int textID,String language_indicator);

	public void getDisplayTextMap();

	public void loadMessageTable();

	public Map<String, Object> getMessages();

    public Map<String, Object> getAllData();

	public String[] getMessageByCode(String code, String language);

	public String getMessageHistory(String vfcnRsnCd, String msgType, String aCatCode);

	public String getMessageCode(String vfcnRsnCd,String msgType, String aCatCode);

	public void getSuppressReasonCodesMap();
	
	public String[] getRefTableMessage(String msgCode, String language);
}
