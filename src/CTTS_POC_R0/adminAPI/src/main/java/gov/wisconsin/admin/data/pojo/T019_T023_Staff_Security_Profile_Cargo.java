package gov.wisconsin.admin.data.pojo;

public class T019_T023_Staff_Security_Profile_Cargo {
	private String prfl_id;
	private String window_id;
	private String access_cd;
	
	public T019_T023_Staff_Security_Profile_Cargo() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public T019_T023_Staff_Security_Profile_Cargo(String prfl_id,
			String window_id, String access_cd) {
		super();
		this.prfl_id = prfl_id;
		this.window_id = window_id;
		this.access_cd = access_cd;
	}
	
	public String getAccess_cd() {
		return access_cd;
	}


	public void setAccess_cd(String access_cd) {
		this.access_cd = access_cd;
	}
	
	public String getPrfl_id() {
		return prfl_id;
	}
	public void setPrfl_id(String prfl_id) {
		this.prfl_id = prfl_id;
	}
	public String getWindow_id() {
		return window_id;
	}
	public void setWindow_id(String window_id) {
		this.window_id = window_id;
	}
	
}