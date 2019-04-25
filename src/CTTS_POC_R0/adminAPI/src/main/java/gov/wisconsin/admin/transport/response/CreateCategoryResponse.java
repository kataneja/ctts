package gov.wisconsin.admin.transport.response;

import gov.wisconsin.admin.data.pojo.T024_Category_Cargo;
import gov.wisconsin.framework.transport.FwResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateCategoryResponse extends FwResponse {
	private T024_Category_Cargo createdCargo;

	public T024_Category_Cargo getCreatedCargo() {
		return createdCargo;
	}

	public void setCreatedCargo(T024_Category_Cargo createdCargo) {
		this.createdCargo = createdCargo;
	}
}