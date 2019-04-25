package gov.wisconsin.timesheet.transport.response;

import gov.wisconsin.framework.transport.FwResponse;
import gov.wisconsin.timesheet.data.pojo.T052_Timesheet_Cargo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetAllTimesheetsResponse extends FwResponse {
	private List<T052_Timesheet_Cargo> resultList = new ArrayList<T052_Timesheet_Cargo>();

	public List<T052_Timesheet_Cargo> getResultList() {
		return resultList;
	}

	public void setResultList(List<T052_Timesheet_Cargo> resultList) {
		this.resultList = resultList;
	}
}