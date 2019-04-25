package gov.wisconsin.framework.bean.impl;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.impl.FwRequestWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.servlet.HandlerMapping;

public final class FwActivityBean extends FwBaseClass {

	private String wid;
	private String sessionID;
	private String subSystem;
  	private String requestID;
  	private String urlTemplate;
	private String incomingTime;
  	private String outgoingTime;
	private String requestedResource;
	
	public FwActivityBean() {}
	
	public void handleIncomingActivity(FwRequestWrapper request) {
		try {
			incomingTime = formatDate(System.currentTimeMillis());
			requestedResource = request.getRequestURL().toString();
			handleOtherData(request);
		}
		catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
	}

	public void handleOutgoingActivity(FwRequestWrapper request) {
		try {
			Object urlTemplateAttribute = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
			if (urlTemplateAttribute != null) {
				urlTemplate = ((String) urlTemplateAttribute).substring(1);
			}
			
			outgoingTime = formatDate(System.currentTimeMillis());
			handleOtherData(request);
			activityLogger.info(this.toString());
		}
		catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
	}

	private void handleOtherData(FwRequestWrapper request) {
		handleRequestID(request);
		handleSubSystem();
		handleWID(request);
		
		if (sessionID == null) {
			sessionID = request.getSession().getId();
		}
	}
	
	private void handleWID(FwRequestWrapper request) {
		String widHeaderValue = request.getHeader(FwConstants.PAYLOAD_HEADER_WID);
		
		if (widHeaderValue != null) {
			wid = widHeaderValue;
		}
		
		if (wid == null) {
			wid = FwConstants.EMPTY_STRING;
		}
	}
	
	private void handleSubSystem() {
		subSystem = "???";

		if (requestedResource.contains(FwConstants.URL_APPLICATION)) {
			String urlSuffix = requestedResource.substring(requestedResource.indexOf(FwConstants.URL_APPLICATION) + 5);
			
			int slashIndex = urlSuffix.indexOf("/");
			String controller = (slashIndex != -1) ? "/" + urlSuffix.substring(0, slashIndex) : urlSuffix;
			
			switch (controller) {
				case FwConstants.URL_CONTROLLER_EXAMPLE:
					subSystem = "XXX";
					break;
			}
		}
	}
	
	private void handleRequestID(FwRequestWrapper request) {
		if (this.requestID == null) {
			this.requestID = UUID.randomUUID().toString();
			request.setAttribute(FwConstants.PAYLOAD_REQUESTID, this.requestID);
		}
	}
	
	private String formatDate(long time) {
		return new SimpleDateFormat(FwConstants.ACTIVITY_DATE_FORMAT).format(new Date(time));
	}

	public String getSubSystem() {
		return subSystem;
	}

	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
	}
	
	public String getIncomingTime() {
		return incomingTime;
	}

	public void setIncomingTime(String incomingTime) {
		this.incomingTime = incomingTime;
	}

	public String getOutgoingTime() {
		return outgoingTime;
	}

	public void setOutgoingTime(String outgoingTime) {
		this.outgoingTime = outgoingTime;
	}

	public String getRequestedResource() {
		return requestedResource;
	}

	public void setRequestedResource(String requestedResource) {
		this.requestedResource = requestedResource;
	}
	
  	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	
	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}
	
  	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public String getUrlTemplate() {
		return urlTemplate;
	}

	public void setUrlTemplate(String urlTemplate) {
		this.urlTemplate = urlTemplate;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Activity | ");
		sb.append("requestID: ").append(requestID == null ? FwConstants.EMPTY_STRING : requestID).append(FwConstants.ACTIVITY_DELIMITER);
		sb.append("sessionID: ").append(sessionID == null ? FwConstants.EMPTY_STRING : sessionID).append(FwConstants.ACTIVITY_DELIMITER);
		sb.append("requestedResource: ").append(requestedResource == null ? FwConstants.EMPTY_STRING : requestedResource).append(FwConstants.ACTIVITY_DELIMITER);
		sb.append("urlTemplate: ").append(urlTemplate == null ? FwConstants.EMPTY_STRING : urlTemplate).append(FwConstants.ACTIVITY_DELIMITER);
		sb.append("subSystem: ").append(subSystem == null ? FwConstants.EMPTY_STRING : subSystem).append(FwConstants.ACTIVITY_DELIMITER);
		sb.append("wid: ").append(wid == null ? FwConstants.EMPTY_STRING : wid).append(FwConstants.ACTIVITY_DELIMITER);
		sb.append("incomingTime: ").append(incomingTime == null ? FwConstants.EMPTY_STRING : incomingTime).append(FwConstants.ACTIVITY_DELIMITER);
		sb.append("outgoingTime: ").append(outgoingTime == null ? FwConstants.EMPTY_STRING : outgoingTime);
		return sb.toString();
	}
}
