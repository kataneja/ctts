package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.exception.FwException;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface ISQL {

	public String INPUT_PARAM  = "?";
	public String YES          = "Y";
	public String IN           = "IN";
	public String ASC          = " ASC";
	public String DESC         = " DESC";
	public String TYPE         = "type";
	public String TOKEN        = "token";
	public String NOT_IN       = "NOT IN";
	public String DYNAMIC      = "dynamic";
	public String KEYWORD      = "keyword";
	public String SQLTYPE      = "sqlType";
	public String PERSIST      = "persist";
	public String SCHEMA_TOKEN = "~SCHEMA~";

	public List<Map<String, Object>> select(Map<String, Object> aData, Connection aConn) throws FwException;
}
