package gov.wisconsin.timesheet.transport.response;

import gov.wisconsin.framework.transport.FwResponse;
import gov.wisconsin.timesheet.transport.helper.TimesheetSummaryData;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TimesheetSummaryResponse extends FwResponse {
	private List<TimesheetSummaryData> resultList = new ArrayList<TimesheetSummaryData>();

	public List<TimesheetSummaryData> getResultList() {
		return resultList;
	}

	public void setResultList(List<TimesheetSummaryData> resultList) {
		this.resultList = resultList;
	}
}
