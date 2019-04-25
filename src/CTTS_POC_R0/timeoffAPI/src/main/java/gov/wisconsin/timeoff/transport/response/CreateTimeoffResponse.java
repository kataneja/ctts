package gov.wisconsin.timeoff.transport.response;

import gov.wisconsin.framework.transport.FwResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateTimeoffResponse extends FwResponse {
	private TXXX_Timeoff_Cargo createdCargo;

	public TXXX_Timeoff_Cargo getCreatedCargo() {
		return createdCargo;
	}

	public void setCreatedCargo(TXXX_Timeoff_Cargo createdCargo) {
		this.createdCargo = createdCargo;
	}
}