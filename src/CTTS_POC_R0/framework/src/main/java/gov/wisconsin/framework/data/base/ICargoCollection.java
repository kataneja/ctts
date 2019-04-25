package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.exception.FwException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface ICargoCollection extends Collection<Object>, Serializable {

	public String getPACKAGE();        
	
	public void setGenericResults(Object obj)  throws java.rmi.RemoteException; 
	
	public boolean add(java.lang.Object o);	

	public boolean addAll(Collection<?> c);
	
	public void clear();

	public boolean contains(java.lang.Object o);
	
	public boolean containsAll(Collection<?> c);
	
	public boolean isEmpty();

	public Iterator<Object> iterator();

	public boolean remove(java.lang.Object o);

	public boolean removeAll(Collection<?> c);

	public boolean retainAll(Collection<?> c);

	public int size();

	public Object[] toArray();

	public Object[] toArray(Object[] a);

	public int getCurrentIndex();

	public void setCurrentIndex(int currentIndex);

	public Object get(int idx);

	public Collection<Object> getKeys();

	public int getSelectSize();

	public void release() throws Throwable ;

	public Object remove(int index);

	public void set(int idx, Object obj);

	public void setSelectSize(int size);

	public Object find(Object o);

	public Collection<Object> persist(String aPersist_type) throws FwException;

	public Collection<Object> persist(String aPersist_type, String aUpdateMethod) throws FwException;

	public Collection<Object> delete(String aPersist_type) throws FwException ;

	public ICargo[] select(String aPersist_type, Map<String, Object> aData) throws FwException ;

	public ICargo[] select(String aPersist_type, String aFinderMethod) throws FwException;

	public List<Object> execute(String aPersistType, String aMethodName) throws FwException ;

	public List<Object> getNext(int count);

	public List<Object> getPrevious(int count) ;

	public List<Object> getFirst(int count);

	public List<Object> getLast(int count);
}
