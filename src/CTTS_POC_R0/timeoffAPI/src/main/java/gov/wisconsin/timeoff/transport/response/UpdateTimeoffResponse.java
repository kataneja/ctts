package gov.wisconsin.timeoff.transport.response;

import gov.wisconsin.framework.transport.FwResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdateTimeoffResponse extends FwResponse {
	private TXXX_Timeoff_Cargo updatedCargo;

	public TXXX_Timeoff_Cargo getUpdatedCargo() {
		return updatedCargo;
	}

	public void setUpdatedCargo(TXXX_Timeoff_Cargo updatedCargo) {
		this.updatedCargo = updatedCargo;
	}
}