package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.exception.FwException;

import java.sql.Connection;

public interface IHistoryPersisterType4 extends IHistoryPersister {
	
	public String INCORRECT_METHOD             = "Incorrect Method Call";
	public String NO_SEQ_NUM                   = "History Sequence Number with out Sequence Number";
	public String HIST_REC_NOT_ALLOWED_FURTURE = "History (9) records can't be inserted in future";
	
	public ICargo update(IDAO aDao, IHistoryType4 aCargo, Connection aConn) throws FwException;
}
