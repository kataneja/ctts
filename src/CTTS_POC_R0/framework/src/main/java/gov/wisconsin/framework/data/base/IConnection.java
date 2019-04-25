package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.exception.FwException;

import java.sql.Connection;

public interface IConnection {
	public void init();
	
	public String getDBSchema();

	public Connection getConnection() throws FwException;
}
