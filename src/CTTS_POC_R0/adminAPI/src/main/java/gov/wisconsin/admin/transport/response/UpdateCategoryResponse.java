package gov.wisconsin.admin.transport.response;

import gov.wisconsin.admin.data.pojo.T024_Category_Cargo;
import gov.wisconsin.framework.transport.FwResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdateCategoryResponse extends FwResponse {
	private T024_Category_Cargo updatedCargo;

	public T024_Category_Cargo getUpdatedCargo() {
		return updatedCargo;
	}

	public void setUpdatedCargo(T024_Category_Cargo updatedCargo) {
		this.updatedCargo = updatedCargo;
	}
}