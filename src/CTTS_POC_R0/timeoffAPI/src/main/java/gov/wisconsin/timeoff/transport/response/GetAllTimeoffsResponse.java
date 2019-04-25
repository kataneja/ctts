package gov.wisconsin.timeoff.transport.response;

import gov.wisconsin.framework.transport.FwResponse;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetAllTimeoffsResponse extends FwResponse {
	private List<TXXX_Timeoff_Cargo> resultList = new ArrayList<TXXX_Timeoff_Cargo>();

	public List<TXXX_Timeoff_Cargo> getResultList() {
		return resultList;
	}

	public void setResultList(List<TXXX_Timeoff_Cargo> resultList) {
		this.resultList = resultList;
	}
}