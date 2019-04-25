package gov.wisconsin.admin.transport.response;

import gov.wisconsin.admin.data.pojo.T035_HolidayTime_Cargo;
import gov.wisconsin.framework.transport.FwResponse;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetAllHolidaysResponse extends FwResponse {
	private List<T035_HolidayTime_Cargo> resultList = new ArrayList<T035_HolidayTime_Cargo>();

	public List<T035_HolidayTime_Cargo> getResultList() {
		return resultList;
	}

	public void setResultList(List<T035_HolidayTime_Cargo> resultList) {
		this.resultList = resultList;
	}
}