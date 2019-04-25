package gov.wisconsin.framework.data.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.IConnection;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class FwConnectionManager extends FwBaseClass implements IConnection {

	protected String dbSchema;
	
	protected String source;
	
	protected DataSource dataSource;
	
	private static IConnection instance;
	
	private FwConnectionManager() {}
	
	public void init() {
		try {
			InitialContext initialContext = new InitialContext();
			dataSource = (DataSource) initialContext.lookup(source);
			initialContext.close();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK, "Failed to get data source initial context or connection. Check the Data Source Name: " + source);
		}
	}

	public Connection getConnection() throws FwException {
		String errorMessage = "Error while getting connection from data source. Data source is null.";
		
		try {
			if(dataSource != null) {
				return dataSource.getConnection();
			}
			throw new NullPointerException(errorMessage);
		} catch(SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, errorMessage);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return null;
	}
	
	public String getDBSchema(){
		return dbSchema;	
	}
	
    public static void setInstance(IConnection connectionManager) {
    	instance = connectionManager;
    }
    
    public static IConnection getInstance() {
    	return instance;
    }
}