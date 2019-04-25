package gov.wisconsin.framework.base;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.constant.IReferenceConstants;
import gov.wisconsin.framework.data.base.FwAbstractCargo;
import gov.wisconsin.framework.data.base.FwAbstractCollection;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IPrimaryKey;
import gov.wisconsin.framework.data.base.IReferenceTableData;
import gov.wisconsin.framework.data.pojo.FwDataCriteria;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.impl.FwMessage;
import gov.wisconsin.framework.impl.FwMessageList;
import gov.wisconsin.framework.security.cargo.LDAP_Cargo;
import gov.wisconsin.framework.security.cargo.LDAP_Collection;
import gov.wisconsin.framework.security.cargo.Person;
import gov.wisconsin.framework.util.DisplayFormatter;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class FwAbstractBO extends FwBaseClass {

	private String batchID = null;
	private String currentPageID = null;
	private FwMessageList messageList = null;

	protected DisplayFormatter displayFormatter; 	//@Autowired
	
	private void createMessageList() {
		if(this.messageList == null) {
			this.messageList = new FwMessageList();
		}
	}
	
	protected StringBuffer createXMLMessage() {
		StringBuffer xmlMessage = new StringBuffer();
		xmlMessage.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"); 
		return xmlMessage;
	}

	public void removeMessageCode(String messageCode) {
		List<FwMessage> currentMessageList = messageList.getMessageList();
		for (Iterator<FwMessage> iterator = currentMessageList.iterator(); iterator.hasNext();) {
		    FwMessage currentMessage = iterator.next();
		    if (currentMessage.getMessageCode().equals(messageCode)) {
		        iterator.remove();
		    }
		}
	}
	
	public void addMessage(String messageCode, String[] elementId, Object[] messageFieldValue) {
		try {
			createMessageList();
			FwMessage message = new FwMessage();
			message.addMsgCdElemIdMsgFieldVal(messageCode,elementId,messageFieldValue);
			messageList.addMessageToList(message);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}		

	public void addMessageWithElementIds(String messageCode, String[] elementId) {
		try {
			createMessageList();
			FwMessage message = new FwMessage();
			message.addMessageWithElementIds(messageCode,elementId);
			messageList.addMessageToList(message);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}		

	public void addMessageWithFieldValues(String messageCode, Object[] messageFieldValue) {
		try {
			createMessageList();
			FwMessage message = new FwMessage();
			message.addMessageWithFieldValues(messageCode, messageFieldValue);
			messageList.addMessageToList(message);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}		
	
	public void addMessage(String message) {
		try {
			createMessageList();
			FwMessage msg = new FwMessage();
			msg.addMessage(message);
			messageList.addMessageToList(msg);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}		
	
	public void addMessageCode(String messageCode) {
		try {
			createMessageList();
			FwMessage message = new FwMessage();
			message.addMessageCode(messageCode);
			messageList.addMessageToList(message);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}		

	public void addMessage(boolean validationResult, String messageCode, String[] elementId, Object[] messageFieldValue) {
		if(!validationResult) {
			addMessage(messageCode, elementId, messageFieldValue);
		}
	}

	public void addMessageWithFieldValues(boolean validationResult, String messageCode, Object[] messageFieldValue) {
		if(!validationResult) {
			addMessageWithFieldValues(messageCode, messageFieldValue);
		}
	}	

	public void addMessageWithElementIds(boolean validationResult, String messageCode, String[] elementId) {
		if(!validationResult) {
			addMessageWithElementIds(messageCode, elementId);
		}
	}	

	public void addMessage(boolean validationResult, String messageCode) {
		if(!validationResult) {
			addMessage(messageCode);
		}
	}

	public FwMessageList getMessageList() {
		if (messageList != null) {
			this.messageList.performMessageSubstitution();
		}
		return this.messageList;
	}	

	public boolean hasMessages() {
		return messageList == null ? false : this.messageList.hasMessages();
	}			

	public List<Map<String,Object>> executeSQL(Map<String, Object> aData) throws FwException {
		try {
			return dataManager.execute(FwConstants.SQL, aData);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return null;
	}

	protected ICargo isChanged(ICargo aBeforeCargo, ICargo aAfterCargo) throws FwException {
		try {
			if(aBeforeCargo == aAfterCargo) { 
				aAfterCargo.setDirty(false);
			} else if(aAfterCargo!= null) {
				aAfterCargo.setDirty((aBeforeCargo == null) ? ((aAfterCargo == null) ? false : true) : (aAfterCargo == null) ? true : (aBeforeCargo.hashCode() != aAfterCargo.hashCode()));
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return aAfterCargo;
	}

	protected FwAbstractCollection isChanged(FwAbstractCollection aBeforeCollection, FwAbstractCollection aAfterCollection) {
		try {
			if(aAfterCollection != null) {
				ICargo[] afterArray = new FwAbstractCargo[aAfterCollection.size()];
				aAfterCollection.toArray(afterArray);
				
				if(aBeforeCollection != null) {
					ICargo[] beforeArray = new FwAbstractCargo[aBeforeCollection.size()];
					aBeforeCollection.toArray(beforeArray);
					
					outer:
					for(int i = 0; i < afterArray.length; i++) {
						IPrimaryKey key = afterArray[i].getPrimaryKey();
						for(int j = 0; j < beforeArray.length; j++) {
							IPrimaryKey beforeKey = beforeArray[j].getPrimaryKey();						
							if(key.hashCode() == beforeKey.hashCode()) {
								afterArray[i] = isChanged(beforeArray[j], afterArray[i]);
								continue outer;					
							}
						}
						afterArray[i].setDirty(true);
					}
				} else {
					for(int k = 0; k < afterArray.length; k++) {
						afterArray[k].setDirty(true);
					}
				}
				
				aAfterCollection.clear();
				for(int p = 0; p < afterArray.length; p++) {
					aAfterCollection.add(afterArray[p]);
				}
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return aAfterCollection;
	}	

	protected String performXSLTTransformation(String aInputXMLStr, String aXSLTFileName) {
		InputStream is = null;
		
		try {
			Transformer transformer = configurationManager.getXSLTTemplate(aXSLTFileName).newTransformer();
			StringWriter strW = new StringWriter();
			byte[] bArray = aInputXMLStr.getBytes();	
			is = new ByteArrayInputStream(bArray);
			Source src = new StreamSource(is);
			transformer.transform(src, new StreamResult(strW));
			return strW.toString();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		} finally {
			try {
				if(is != null) { is.close(); }
			} catch(Exception e) {
				FwExceptionManager.handleException(this.getClass(), e);
			}
		}
		
		return null;
	}

	public long computeCheckDigit(String sNum){
		int qty = 0;
		int rem = 0;
		int qty1 = 0;
		long aNum1 = 1L;
		String lastDigit;
		
		try {
			int size = sNum.length();

			for(int i = 0; i < size; i++) {
				qty = qty + (Short.parseShort(sNum.substring(i, i+1)) * size);
			}
	
			rem = qty % 11;
			qty1 = 11 - rem;
			
			//Remove the first digit
			if(qty1 == 10) {
				qty1 = 0;	
			} else if(qty1 == 11) {
				qty1 = 1;
			}
			
			lastDigit = String.valueOf(qty1);
			if(lastDigit.length() > 9) {
				FwException fe = new FwException(this.getClass(), new Exception("Last Digit is: " + lastDigit), FwConstants.EXP_TYP_APPLICATION, "Last Digit is: " + lastDigit);
				fe.setMessageCode("FW047");
				FwExceptionManager.handleException(fe);
			}
			aNum1 = Long.parseLong(sNum + lastDigit);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return aNum1;
	}

	public boolean writeDataToFile(String fullFilePath, String data, boolean appendStatus) {
		boolean recStatus = true;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		
		try {
			fileWriter = new FileWriter(fullFilePath, appendStatus);
			buffWriter = new BufferedWriter(fileWriter);
			buffWriter.write(data);
			fileWriter.flush();
			buffWriter.flush();
		} catch (IOException ioe) {
			recStatus = false;
			FwExceptionManager.handleException(this.getClass(), ioe);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		} finally {
			try {
				if(fileWriter != null) { fileWriter.close(); }
				if(buffWriter != null) { buffWriter.close(); }
			} catch (Exception e) {
				FwExceptionManager.handleException(this.getClass(), e);
			}
		}
		
		return recStatus;
	}
	
	public String padZero(String id, int length) {
		try {
			StringBuffer padZeroBuff = new StringBuffer();
			if (id == null) {
				id = "";
			}
			for (int i = 0; i < (length - id.length()); i++) {
				padZeroBuff.append("0");
			}
			return padZeroBuff.append(id).toString();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return null;
	}
	
	public String unPadZero(String id) {
		try {
			if (id != null) {
				for (int i = 0; i < id.length(); i++) {
					if (id.charAt(i) != '0') {
						return id.substring(i, id.length());
					}
				}
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}

		return null;
	}
	
	public String getWidForTheUser(String user_id) {
		String wid = null;

		try {
			LDAP_Cargo cargo = new LDAP_Cargo();
			LDAP_Collection collection = applicationContext.getBean(LDAP_Collection.class);
			cargo.setLogonId(user_id);
			collection.addCargo(cargo);
			wid = ldapManager.getWIDforUserId(cargo);
			
			if (cargo.getResultCodes() != null && cargo.getResultCodes().size() > 0) {
				convertLDAPResultsToErrorMessages(cargo.getResultCodes());
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return wid;
	}
	
	public FwMessageList convertLDAPResultsToErrorMessages(List<String> resultCodes) {
		String current = null;
		
		try {
			int resultCodesLength = resultCodes.size();
	
			for (int i = 0; i < resultCodesLength; i++) {
				current = resultCodes.get(i);
				if (current.equals(FwConstants.USER_ID_ALREADY_EXISTS)) {
					this.addMessageCode("AM008");
				}
				else if (current.equals(FwConstants.NOT_ABLE_TO_CREATE_ACCOUNT)) {
					this.addMessageCode("AM009");
				}
				else if (current.equals(FwConstants.PASSWORD_ALREADY_USED)) {
					this.addMessageCode("AM033");
				}
				else if (current.equals(FwConstants.USER_ID_DOES_NOT_MATCH_WITH_SSN)) {
					this.addMessageCode("10071");
				}
				else if (current.equals(FwConstants.ANSWER_DOES_NOT_MATCH)) {
					this.addMessageCode("AM032");
				}
				else if (current.equals(FwConstants.UNABLE_TO_PROCESS)) {
					this.addMessageCode("AM010");
				}
				else if (current.equals(FwConstants.ACCOUNT_ALREADY_EXISTS_FOR_EMAIL_ID)) {
					this.addMessageCode("AM007");
				}
				else if (current.equals(FwConstants.USER_ID_DOES_NOT_MATCH)) {
					this.addMessageCode("AM040");
				}
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return this.getMessageList();
	}
	
	public String getWidForTrackingNumber(String aTrackingNum) {
		String wid = null;

		try {
			Map<String, Object> sqlMap = new HashMap<String, Object>();
			sqlMap.put(FwConstants.SQL_IND, "sql-AS18");
			FwDataCriteria[] dataCriteria = new FwDataCriteria[1];
			dataCriteria[0] = new FwDataCriteria();
			dataCriteria[0].setColumn_name("APP_NUM");
			dataCriteria[0].setColumn_value(aTrackingNum);
			dataCriteria[0].setData_type(FwConstants.LONG);

			sqlMap.put(FwConstants.CRITERIA, dataCriteria);
			List<Map<String, Object>> result = executeSQL(sqlMap);
			if (result != null && result.size() > 0) {
				Map<String, Object> cargo = result.get(0);
				wid = (String) cargo.get("WAMS_INTL_USER_ID");
			} else {
				this.addMessageCode("AM030");
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return wid;
	}
	
	public String getUserIdForWid(String aWid) {
		try {
			LDAP_Cargo cargo = new LDAP_Cargo();
			cargo.setWid(Long.valueOf(aWid).longValue());
			ldapManager.matchWiUid(cargo);
			
			if (cargo.getLogonId() != null) {
				return cargo.getLogonId();
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public boolean checkFileExists(String fileLocation, String fileName) {
		boolean fileExists = false;
        
        try {
        	File f = new File(fileLocation + fileName);
        	if (f.exists()) {
        		fileExists = true;
        	}
        } catch (Exception e) {
        	FwExceptionManager.handleException(this.getClass(), e);
        }

        return fileExists;
	}
	
	public boolean isDateAHoliday(Date theDate) {
		try {
			IReferenceTableData caresHolidayData = referenceTableManager.filterDataOnSingleColumn(FwConstants.HOLIDAYS_REF_TBL, FwConstants.ENGLISH, FwConstants.caresHolidaysColumnID, IReferenceConstants.FILTER_INCLUDE_MATCH_ONE, new String[] {"Y"});
			IReferenceTableData federalHolidayData = referenceTableManager.filterDataOnSingleColumn(FwConstants.HOLIDAYS_REF_TBL, FwConstants.ENGLISH, FwConstants.federalHolidaysColumnID, IReferenceConstants.FILTER_INCLUDE_MATCH_ONE, new String[] {"Y"});
			
			List<String> allHolidayDates = new ArrayList<String>();
			allHolidayDates.addAll(caresHolidayData.getDescriptionValuesAsList(FwConstants.holidaysColumnID));
			allHolidayDates.addAll(federalHolidayData.getDescriptionValuesAsList(FwConstants.holidaysColumnID));
			
			return allHolidayDates.contains(theDate.toString());
		} catch (Exception e) {
        	FwExceptionManager.handleException(this.getClass(), e);
        }
		
		return false;
	}
	
	public boolean accountHasChallengeResponse(String userId) {
		try {
			Person person = ldapManager.findUser(userId);
			
			String question = person.getQuestion2();
			String answer = person.getAnswer2();
			
			if (question != null && !FwConstants.EMPTY_STRING.equals(question)) {
				if (answer != null && !FwConstants.EMPTY_STRING.equals(answer)) {
					return true;
				}
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return false;
	}

	public String getBatchID() {
		return batchID;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}
	
	public String getCurrentPageID() {
		return currentPageID;
	}

	public void setCurrentPageID(String currentPageID) {
		this.currentPageID = currentPageID;
	}
	
	public DisplayFormatter getDisplayFormatter() {
		return displayFormatter;
	}

	@Autowired
	public void setDisplayFormatter(DisplayFormatter displayFormatter) {
		this.displayFormatter = displayFormatter;
	}
}