package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IDAO;
import gov.wisconsin.framework.exception.FwException;

import java.sql.Connection;

public interface IHistoryPersisterType1 extends IHistoryPersister {
	public ICargo update(IDAO aDao, IHistory aCargo, Connection aConn) throws FwException;
}
