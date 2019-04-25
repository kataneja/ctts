package gov.wisconsin.timesheet.transport.response;

import gov.wisconsin.timesheet.data.pojo.T052_Timesheet_Cargo;
import gov.wisconsin.framework.transport.FwResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdateTimesheetResponse extends FwResponse {
	private T052_Timesheet_Cargo updatedCargo;

	public T052_Timesheet_Cargo getUpdatedCargo() {
		return updatedCargo;
	}

	public void setUpdatedCargo(T052_Timesheet_Cargo updatedCargo) {
		this.updatedCargo = updatedCargo;
	}
}