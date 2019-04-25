package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.exception.FwException;

import java.sql.Connection;

public interface IHistoryPersister {
	public boolean update(IDAO aDao, ICargo aCargo, Connection aConn) throws FwException;
}
