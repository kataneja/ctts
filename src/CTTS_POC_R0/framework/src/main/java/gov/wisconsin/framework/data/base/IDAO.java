package gov.wisconsin.framework.data.base;

public interface IDAO {

	public String SELECT = "select";
	public String INSERT = "insert";
	public String UPDATE = "update";
	public String DELETE = "delete";
	public String UPDATE_T2_HISTORY = "updateHistory";
	
	public String FINDBY_MAXSEQ ="findByMaxSeqNum";
	public String FINDBY_PRIMARYKEY ="findByPrimaryKey";

	public String NO_REC_UPDATED = "Update action performed and it did not update any record in to the database";
	public String NO_REC_DELETED = "Delete action performed and it did not delete any record in to the database";
	public String NO_REC_INSERTED = "Insert action performed and it did not insert any record in to the database";
	public String NO_SEARCH_CRITERIA = "Select method in DAO requires at least one search criteria to execute the search";
	public String NO_HISTORY_REC_INSERTED = "Insert on select action perfomed and it did not insert any record in History Table";
	public String NO_DB_SCHEMA = "Database action could not be performed because the database switch is not simulation or production";
	public String NO_PK_SEARCH_CRITERIA = "Select method in DAO requires at least one primary key in search criteria to execute the search";
		
	public short getHistoryType();
	
}
