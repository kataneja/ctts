package gov.wisconsin.admin.transport.temp;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import gov.wisconsin.admin.data.pojo.T024_Category_Cargo;
import gov.wisconsin.framework.transport.FwResponse;

@Data @NoArgsConstructor @AllArgsConstructor
public class SQLResponse extends FwResponse {
	
	private List<T024_Category_Cargo> resultList;

	public List<T024_Category_Cargo> getResultList() {
		return resultList;
	}

	public void setResultList(List<T024_Category_Cargo> resultList) {
		this.resultList = resultList;
	}
}
