package gov.wisconsin.framework.security.cargo;

import java.util.Arrays;

import gov.wisconsin.framework.data.base.FwAbstractWebServiceCargo;

public class UserLogin_Cargo extends FwAbstractWebServiceCargo {
	private static final long serialVersionUID = -947087196832881542L;
	
	private String[] prflID;

	private String wid;
	private String appID;
	private String userID;
	private String userType;
	private String wamsLognID;
	private String userFirstName;
	private String sesnWkrShortNam;

	public String inspectCargo() {
		StringBuffer sb = new StringBuffer();
		sb.append("AppID ").append(appID).append("UserID ").append(userID).append("WID ").append(wid)
		.append("Wams_logn_ID ").append(wamsLognID).append("SesnWkrShortNam ").append(sesnWkrShortNam)
		.append("PrflID ").append(Arrays.toString(prflID)).append("User Type ").append(userType).append(" userFirstName ").append(userFirstName);
		return sb.toString();
	}

	public String getSesnWkrShortNam() {
		return sesnWkrShortNam;
	}

	public String getUserID() {
		return userID;
	}

	public String getWamsLognID() {
		return wamsLognID;
	}

	public void setSesnWkrShortNam(String sesnWkrShortNam) {
		this.sesnWkrShortNam = sesnWkrShortNam;
	}

	public void setUserId(String userID) {
		this.userID = userID;
	}

	public void setWamsLognId(String wamsLognID) {
		this.wamsLognID = wamsLognID;
	}
	
	public String[] getPrflId() {
		return prflID;
	}

	public void setPrflId(String[] prflID) {
		this.prflID = prflID;
	}

	public String getAppID() {
		return appID;
	}

	public String getWid() {
		return wid;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPackage() {
		return null;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
}
