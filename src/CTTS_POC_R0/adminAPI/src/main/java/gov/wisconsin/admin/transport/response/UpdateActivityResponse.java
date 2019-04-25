package gov.wisconsin.admin.transport.response;

import gov.wisconsin.admin.data.pojo.T025_Activity_Cargo;
import gov.wisconsin.framework.transport.FwResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdateActivityResponse extends FwResponse {
	private T025_Activity_Cargo updatedCargo;

	public T025_Activity_Cargo getUpdatedCargo() {
		return updatedCargo;
	}

	public void setUpdatedCargo(T025_Activity_Cargo updatedCargo) {
		this.updatedCargo = updatedCargo;
	}
}