package gov.wisconsin.admin.transport.response;

import gov.wisconsin.admin.data.pojo.T019_T023_Security_Profile_Response_Cargo;
import gov.wisconsin.admin.data.pojo.T019_T023_Staff_Security_Profile_Cargo;
import gov.wisconsin.framework.transport.FwResponse;

import java.util.List;

public class GetStaffSecurityProfileResponse extends FwResponse{
	private List<T019_T023_Security_Profile_Response_Cargo> resultList;

	public List<T019_T023_Security_Profile_Response_Cargo> getResultList() {
		return resultList;
	}

	public void setResultList(List<T019_T023_Security_Profile_Response_Cargo> resultList) {
		this.resultList = resultList;
	}
	
}
