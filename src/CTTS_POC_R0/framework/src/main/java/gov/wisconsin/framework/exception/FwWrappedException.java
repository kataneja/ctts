package gov.wisconsin.framework.exception;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.management.FwConfigurationManager;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.HandlerMapping;

public class FwWrappedException extends RuntimeException {
	private static final long serialVersionUID = -4528538031173679423L;

	private static final Random rand = new Random();
	
	private long exceptionID;
	private Timestamp exceptionTime;
	private FwException fwException;
	private boolean inTransaction = false;

	private int statusCode = 500;
	private String previousPageID = "MOBLE";
	private String userID = FwConstants.EMPTY_STRING;
	private String ipAddress = FwConstants.EMPTY_STRING;
	private String serverName = FwConstants.EMPTY_STRING;
	private String wamsLoginID = FwConstants.EMPTY_STRING;
	private String currentPageID = FwConstants.EMPTY_STRING;
	private String primaryKeyText = FwConstants.EMPTY_STRING;
	private String callingClassID = FwConstants.EMPTY_STRING;
	private String callingMethodID = FwConstants.EMPTY_STRING;

	public FwWrappedException() {
		super();
		setServerStats();
	}

	public FwWrappedException(String exceptionText) {
		super(exceptionText);
		setServerStats();
	}
	
	public FwWrappedException(FwException fe) {
		super();
		this.fwException = fe;
		this.exceptionID = rand.nextLong();
		this.statusCode = fe.getStatusCode();
		this.callingClassID = fe.getClassID();
		this.callingMethodID = fe.getMethodID();
		this.primaryKeyText = fe.getCustomMessage();
		setServerStats();
	}
	
	public FwWrappedException(Class<?> clazz, FwException fe) {
		super();
		this.fwException = fe;
		this.exceptionID = rand.nextLong();
		this.statusCode = fe.getStatusCode();
		this.callingClassID = clazz.getName();
		this.callingMethodID = getCurrentMethodName();
		this.primaryKeyText = fe.getCustomMessage();
		setServerStats();
	}
	
	public FwWrappedException(Class<?> clazz, FwException fe, HttpServletRequest httpRequest) {
		super();
		this.fwException = fe;
		this.exceptionID = rand.nextLong();
		this.statusCode = fe.getStatusCode();
		this.callingClassID = clazz.getName();
		this.callingMethodID = getCurrentMethodName();

		Object requestIDAttributeValue = httpRequest.getAttribute(FwConstants.PAYLOAD_REQUESTID);
		if (requestIDAttributeValue != null) {
			this.primaryKeyText = "Request ID [" + requestIDAttributeValue + "]";
		}
		
		Object urlTemplateAttribute = httpRequest.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		if (urlTemplateAttribute != null) {
	    	String urlTemplateAttributeString = ((String) urlTemplateAttribute).substring(1);
	    	String code = FwConfigurationManager.getInstance().getCodeForURL(urlTemplateAttributeString);
	    	if (code != null) {
	    		this.currentPageID = code;
	    	}
		}
		
		setServerStats();
	}
	
	private void setServerStats() {
		this.exceptionTime = new Timestamp(System.currentTimeMillis());
		
		try {
			String server = String.valueOf(InetAddress.getLocalHost());
			this.serverName = server;
			this.ipAddress = server.substring(server.lastIndexOf("/") + 1);
		} catch(Exception e) {
			this.serverName = "UNKNOWN";
			this.ipAddress = "0.0.0.0";
		}
	}
	
	public String toXMLString() {
		StringBuffer temp = new StringBuffer();
		
		temp.append("\n\t<exceptionID>").append(exceptionID).append("</exceptionID>");
		temp.append("\n\t<exceptionTime>").append(exceptionTime).append("</exceptionTime>");
		temp.append("\n\t<serverName>").append(serverName).append("</serverName>");
		temp.append("\n\t<primaryKeyText>").append(primaryKeyText).append("</primaryKeyText>");
		temp.append("\n\t<callingClassID>").append(fwException.getClassID()).append("</callingClassID>");
		temp.append("\n\t<callingMethodID>").append(fwException.getMethodID()).append("</callingMethodID>");
		temp.append("\n\t<currentPageID>").append(currentPageID).append("</currentPageID>");
		temp.append("\n\t<previousPageID>").append(previousPageID).append("</previousPageID>");
		temp.append("\n\t<ipAddress>").append(ipAddress).append("</ipAddress>");
		temp.append("\n\t<wamsLoginID>").append(wamsLoginID).append("</wamsLoginID>");
		temp.append("\n\t<userID>").append(userID).append("</userID>");
		
		return temp.toString();
	}	
	
	public String toQueueMsgString() {
		StringBuffer temp = new StringBuffer();
		
		temp.append("\n\t<exceptionID>").append(exceptionID).append("</exceptionID>");
		temp.append("\n\t<exceptionTime>").append(exceptionTime).append("</exceptionTime>");
		temp.append("\n\t<serverName>").append(serverName).append("</serverName>");
		temp.append("\n\t<primaryKeyText>").append(repMsgChars(primaryKeyText)).append("</primaryKeyText>");
		temp.append("\n\t<currentPageID>").append(currentPageID).append("</currentPageID>");
		temp.append("\n\t<previousPageID>").append(previousPageID).append("</previousPageID>");
		temp.append("\n\t<wamsLoginID>").append(wamsLoginID).append("</wamsLoginID>");
		temp.append("\n\t<userID>").append(userID).append("</userID>");
		
		return temp.toString();
	}
	
	private String repMsgChars(String string) {
		if (string != null) {
			string = string.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;").replace("\"", "&quot;");
		}
		return string;
	}
	
	private static String getCurrentMethodName() {
		return Thread.currentThread().getStackTrace()[4].getMethodName();
    }
	
	public Timestamp getExceptionTime() {
		return exceptionTime;
	}
	
	public void setExceptionTime(Timestamp exceptionTime) {
		this.exceptionTime = exceptionTime;
	}
	
	public FwException getFwException() {
		return fwException;
	}
	
	public void setFwException(FwException fwException) {
		this.fwException = fwException;
	}
	
	public boolean isInTransaction() {
		return inTransaction;
	}
	
	public void setInTransaction(boolean inTransaction) {
		this.inTransaction = inTransaction;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getServerName() {
		return serverName;
	}
	
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	public String getWamsLoginID() {
		return wamsLoginID;
	}
	
	public void setWamsLoginID(String wamsLoginID) {
		this.wamsLoginID = wamsLoginID;
	}
	
	public String getCurrentPageID() {
		return currentPageID;
	}
	
	public void setCurrentPageID(String currentPageID) {
		this.currentPageID = currentPageID;
	}
	
	public String getPreviousPageID() {
		return previousPageID;
	}
	
	public void setPreviousPageID(String previousPageID) {
		this.previousPageID = previousPageID;
	}
	
	public String getPrimaryKeyText() {
		return primaryKeyText;
	}
	
	public void setPrimaryKeyText(String primaryKeyText) {
		this.primaryKeyText = primaryKeyText;
	}
	
	public long getExceptionID() {
		return exceptionID;
	}

	public void setExceptionID(long exceptionID) {
		this.exceptionID = exceptionID;
	}
	
	public String getCallingMethodID() {
		return callingMethodID;
	}

	public void setCallingMethodID(String callingMethodID) {
		this.callingMethodID = callingMethodID;
	}

	public String getCallingClassID() {
		return callingClassID;
	}

	public void setCallingClassID(String callingClassID) {
		this.callingClassID = callingClassID;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "FwWrappedException [ "
				+ "exceptionTime = " + exceptionTime + ", "
				+ "callingClassID = " + callingClassID + ", "
				+ "callingMethodID = " + callingMethodID + ", "
				+ "primaryKeyText = " + primaryKeyText + ", "
				+ "currentPageID = " + currentPageID + ", "
				+ "previousPageID = " + previousPageID + ", "
				+ "statusCode = " + statusCode + ", "
				+ "inTransaction = " + inTransaction + ", "
				+ "ipAddress = " + ipAddress + ", "
				+ "serverName = " + serverName + ", "
				+ "exceptionID = " + exceptionID + ", "
				+ "userID = " + userID + ", "
				+ "wamsLoginID = " + wamsLoginID + ", "
				+ "fwException = " + fwException
				+ "]";
	}
}