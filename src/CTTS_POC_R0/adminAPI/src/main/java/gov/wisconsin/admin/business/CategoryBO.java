package gov.wisconsin.admin.business;

import gov.wisconsin.admin.data.dao.T024_Category_DAO;
import gov.wisconsin.admin.data.pojo.T024_Category_Cargo;
import gov.wisconsin.framework.base.FwAbstractBO;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = FwConstants.PROTOTYPE_SCOPE)
public class CategoryBO extends FwAbstractBO {

	private @Autowired T024_Category_DAO categoryDAO;
	
	public List<T024_Category_Cargo> getAllCategories(String username) {
		try {
			return categoryDAO.findAll();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public T024_Category_Cargo createOrUpdateCategory(T024_Category_Cargo cargoToCreateOrUpdate) {
		try {
			return categoryDAO.save(cargoToCreateOrUpdate);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
}
