package gov.wisconsin.admin.transport.response;

import gov.wisconsin.admin.data.pojo.T035_HolidayTime_Cargo;
import gov.wisconsin.framework.transport.FwResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateHolidayResponse extends FwResponse {
	private T035_HolidayTime_Cargo createdCargo;

	public T035_HolidayTime_Cargo getCreatedCargo() {
		return createdCargo;
	}

	public void setCreatedCargo(T035_HolidayTime_Cargo createdCargo) {
		this.createdCargo = createdCargo;
	}
}