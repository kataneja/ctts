package gov.wisconsin.timeoff.transport.response;

import gov.wisconsin.framework.transport.FwResponse;
import gov.wisconsin.timeoff.transport.helper.TimeoffSummaryData;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TimeoffSummaryResponse extends FwResponse {
	private List<TimeoffSummaryData> resultList = new ArrayList<TimeoffSummaryData>();

	public List<TimeoffSummaryData> getResultList() {
		return resultList;
	}

	public void setResultList(List<TimeoffSummaryData> resultList) {
		this.resultList = resultList;
	}
}
