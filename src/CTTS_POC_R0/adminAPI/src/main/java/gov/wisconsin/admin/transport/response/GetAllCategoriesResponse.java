package gov.wisconsin.admin.transport.response;

import gov.wisconsin.admin.data.pojo.T024_Category_Cargo;
import gov.wisconsin.framework.transport.FwResponse;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetAllCategoriesResponse extends FwResponse {
	private List<T024_Category_Cargo> resultList = new ArrayList<T024_Category_Cargo>();

	public List<T024_Category_Cargo> getResultList() {
		return resultList;
	}

	public void setResultList(List<T024_Category_Cargo> resultList) {
		this.resultList = resultList;
	}
}