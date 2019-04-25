package gov.wisconsin.framework.data.management;

import gov.wisconsin.framework.data.base.IData;
import gov.wisconsin.framework.exception.FwException;

import java.sql.Connection;

import org.springframework.stereotype.Component;

@Component
public class FwDataManager extends FwDataDirector implements IData {

	private static IData instance;
	
	private FwDataManager() {}

	protected Connection getConnection() throws FwException {
		return connectionManager.getConnection();
	}
	
    public static void setInstance(IData dataManager) {
    	instance = dataManager;
    }
    
    public static IData getInstance() {
    	return instance;
    }

}