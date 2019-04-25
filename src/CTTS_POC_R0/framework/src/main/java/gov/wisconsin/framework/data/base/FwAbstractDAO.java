package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FwAbstractDAO implements IDAO {

	protected static String dbSchema;
	
	public FwAbstractDAO() {
		if (dbSchema == null) {
			//dbSchema = FwConfigurationManager.getInstance().getSpringProperty(FwConstants.DATABASE_SCHEMA);
			dbSchema = "";
		}
	}
	
	protected void closeResultSetAndStatement(ResultSet rs, PreparedStatement statement) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);
		}
		closeStatement(statement);	
	}

	protected void closeStatement(PreparedStatement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException sqle) {
			FwExceptionManager.handleSQLException(this.getClass(), sqle, FwConstants.EMPTY_STRING);	
		}
	}
	
	protected String prepareSQLString(String sqlString) {
		if (sqlString != null) {
			if (sqlString.contains(FwConstants.SCHEMA_TOKEN)) {
				return sqlString.replace(FwConstants.SCHEMA_TOKEN, dbSchema);
			}
		}
		return sqlString;
	}
	
	protected static final String getDBSchema(){
		return dbSchema;
	}
}
