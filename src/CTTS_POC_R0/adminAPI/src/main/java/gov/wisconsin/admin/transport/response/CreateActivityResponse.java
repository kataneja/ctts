package gov.wisconsin.admin.transport.response;

import gov.wisconsin.admin.data.pojo.T025_Activity_Cargo;
import gov.wisconsin.framework.transport.FwResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateActivityResponse extends FwResponse {
	private T025_Activity_Cargo createdCargo;

	public T025_Activity_Cargo getCreatedCargo() {
		return createdCargo;
	}

	public void setCreatedCargo(T025_Activity_Cargo createdCargo) {
		this.createdCargo = createdCargo;
	}
}