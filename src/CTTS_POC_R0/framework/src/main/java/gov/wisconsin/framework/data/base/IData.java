package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.exception.FwException;

import java.util.List;
import java.util.Map;

public interface IData {

	public FwAbstractCollection persist(String aPersist_type, Map<String, Object> aData) throws FwException;

	public FwAbstractCollection persist(String aPersist_type, Map<String, Object> aData, String aUpdateMethod) throws FwException;

	public ICargo[] select(String aPersist_type, Map<String, Object> aData) throws FwException;

	public ICargo[] select(String aPersist_type, String aFinder, FwAbstractCollection aCollection) throws FwException;

	public List<Map<String, Object>> execute(String aPersist_type, Map<String, Object> aData) throws FwException;

	public void sendMessage(FwAbstractCollection aCollection) throws FwException;

	public void sendEmailMessage(FwAbstractCollection aCollection) throws FwException;		
}
