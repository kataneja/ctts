package gov.wisconsin.admin.data.pojo;

public class T019_T023_Security_Profile_Response_Cargo {
	private String prfl_id;
	private Boolean showSubmitTimesheet;
	private Boolean showDashboard;
	private Boolean showApproveTimesheet;
	private Boolean showSubmitTimeoff;
	private Boolean showReports;
	private Boolean showAdminPanel;
	private Boolean showUserManagement;
	private String access_cd;
	public T019_T023_Security_Profile_Response_Cargo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public T019_T023_Security_Profile_Response_Cargo(String prfl_id,
			Boolean showSubmitTimesheet, Boolean showDasboard,
			Boolean showApproveTimesheet, Boolean showSubmitTimeoff,
			Boolean showReports, Boolean showAdminPanel,
			Boolean showUserManagement, String access_cd) {
		super();
		this.prfl_id = prfl_id;
		this.showSubmitTimesheet = showSubmitTimesheet;
		this.showDashboard = showDasboard;
		this.showApproveTimesheet = showApproveTimesheet;
		this.showSubmitTimeoff = showSubmitTimeoff;
		this.showReports = showReports;
		this.showAdminPanel = showAdminPanel;
		this.showUserManagement = showUserManagement;
		this.access_cd = access_cd;
	}
	public String getPrfl_id() {
		return prfl_id;
	}
	public void setPrfl_id(String prfl_id) {
		this.prfl_id = prfl_id;
	}
	public Boolean getShowSubmitTimesheet() {
		return showSubmitTimesheet;
	}
	public void setShowSubmitTimesheet(Boolean showSubmitTimesheet) {
		this.showSubmitTimesheet = showSubmitTimesheet;
	}
	public Boolean getShowDashboard() {
		return showDashboard;
	}
	public void setShowDashboard(Boolean showDashboard) {
		this.showDashboard = showDashboard;
	}
	public Boolean getShowApproveTimesheet() {
		return showApproveTimesheet;
	}
	public void setShowApproveTimesheet(Boolean showApproveTimesheet) {
		this.showApproveTimesheet = showApproveTimesheet;
	}
	public Boolean getShowSubmitTimeoff() {
		return showSubmitTimeoff;
	}
	public void setShowSubmitTimeoff(Boolean showSubmitTimeoff) {
		this.showSubmitTimeoff = showSubmitTimeoff;
	}
	public Boolean getShowReports() {
		return showReports;
	}
	public void setShowReports(Boolean showReports) {
		this.showReports = showReports;
	}
	public Boolean getShowAdminPanel() {
		return showAdminPanel;
	}
	public void setShowAdminPanel(Boolean showAdminPanel) {
		this.showAdminPanel = showAdminPanel;
	}
	public Boolean getShowUserManagement() {
		return showUserManagement;
	}
	public void setShowUserManagement(Boolean showUserManagement) {
		this.showUserManagement = showUserManagement;
	}
	public String getAccess_cd() {
		return access_cd;
	}
	public void setAccess_cd(String access_cd) {
		this.access_cd = access_cd;
	}
	
}
