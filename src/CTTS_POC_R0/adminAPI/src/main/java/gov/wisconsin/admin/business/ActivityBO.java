package gov.wisconsin.admin.business;

import gov.wisconsin.admin.data.dao.T025_Activity_DAO;
import gov.wisconsin.admin.data.pojo.T025_Activity_Cargo;
import gov.wisconsin.framework.base.FwAbstractBO;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = FwConstants.PROTOTYPE_SCOPE)
public class ActivityBO extends FwAbstractBO {

	private @Autowired T025_Activity_DAO activityDAO;
	
	public List<T025_Activity_Cargo> getAllActivities(String username) {
		try {
			return activityDAO.findAll();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public T025_Activity_Cargo createOrUpdateActivity(T025_Activity_Cargo cargoToCreateOrUpdate) {
		try {
			return activityDAO.save(cargoToCreateOrUpdate);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
}
