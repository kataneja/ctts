package gov.wisconsin.admin.business;

import gov.wisconsin.admin.data.dao.T035_HolidayTime_DAO;
import gov.wisconsin.admin.data.pojo.T035_HolidayTime_Cargo;
import gov.wisconsin.framework.base.FwAbstractBO;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = FwConstants.PROTOTYPE_SCOPE)
public class HolidayBO extends FwAbstractBO {

	private @Autowired T035_HolidayTime_DAO holidayDAO;
	
	public List<T035_HolidayTime_Cargo> getAllHolidays(String username) {
		try {
			return holidayDAO.findAll();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public T035_HolidayTime_Cargo createOrUpdateHoliday(T035_HolidayTime_Cargo cargoToCreateOrUpdate) {
		try {
			return holidayDAO.save(cargoToCreateOrUpdate);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
}
