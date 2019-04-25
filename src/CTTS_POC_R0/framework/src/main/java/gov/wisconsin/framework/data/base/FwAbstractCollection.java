package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class FwAbstractCollection extends FwBaseClass implements Serializable {
	private static final long serialVersionUID = -6236108189692736743L;
	
	private ArrayList<Object> list = new ArrayList<Object>();
    private ArrayList<Object> keys = new ArrayList<Object>();
	
	private int currentIndex;
	private int selectSize = 100;

	public abstract String getPACKAGE();

    public abstract void setGenericResults(Object obj) throws java.rmi.RemoteException;

    public boolean add(java.lang.Object o) {
        return list.add(o);
    }

    public boolean addAll(Collection<Object> c) {
        return list.addAll(c);
    }
    
    public void addCollection(FwAbstractCollection coll) {
    	Iterator<Object> iterator = coll.iterator();
    	while (iterator.hasNext()) {
    		list.add(iterator.next());
    	}
    }
    
    public void addCollection(FwAbstractCollection[] collArray) {
    	for (int i = 0; i < collArray.length; i++) {
    		FwAbstractCollection coll = collArray[i];
    		Iterator<Object> iterator = coll.iterator();
        	while (iterator.hasNext()) {
        		list.add(iterator.next());
        	}
    	}
    }

    public void clear() {
        list.clear();
    }

    public boolean contains(java.lang.Object o) {
        return list.contains(o);
    }

    public boolean containsAll(Collection<Object> c) {
        return list.containsAll(c);
    }
	
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Iterator<Object> iterator() {
        return list.iterator();
    }    

    public boolean remove(java.lang.Object o) {
        return list.remove(o);
    }

    public boolean removeAll(Collection<Object> c) {
        return list.removeAll(c);
    }

    public boolean retainAll(Collection<Object> c) {
        return list.retainAll(c);
    }

    public int size() {
        return list.size();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public Object[] toArray(Object[] a) {
        return list.toArray(a);
    }    

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}    

    public Object get(int idx) {
        return list.get(idx);
    }

    public Collection<Object> getKeys() {
        return keys;
    }

    public int getSelectSize() {
        return selectSize;
    }

    public void release() throws Throwable {
        finalize();
    }    	

    public Object remove(int index) {
        return list.remove(index);
    }

    public void set(int idx, Object obj) {
        list.set(idx, obj);
    }

    public void setSelectSize(int size) {
        selectSize = size;
    }

    public Object find(Object o) {
        int idx = list.indexOf(o);
        return idx == -1 ? null : list.get(idx);
    }

	public FwAbstractCollection persist(String aPersist_type) throws FwException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(FwConstants.COLLECTION, this);
		return dataManager.persist(aPersist_type,data);
	}

	public FwAbstractCollection persist(String aPersist_type, String aUpdateMethod) throws FwException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(FwConstants.COLLECTION, this);
		return dataManager.persist(aPersist_type,data, aUpdateMethod);
	}

	public FwAbstractCollection delete(String aPersist_type) throws FwException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(FwConstants.COLLECTION, this);		
		return dataManager.persist(aPersist_type,data);	
	}            
  
    public ICargo[] select(String aPersist_type, Map<String, Object> aData) throws FwException {
    	aData.put(FwConstants.COLLECTION, this);
    	return dataManager.select(aPersist_type, aData);
    }
    
	public ICargo[] select(String aPersist_type, String aFinderMethod) throws FwException {
		return dataManager.select(aPersist_type, aFinderMethod, this);
	}

	public List<Map<String, Object>> execute(String aPersistType, String aMethodName) throws FwException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(FwConstants.METHOD_NAME, aMethodName);
		data.put(FwConstants.COLLECTION, this);
		return dataManager.execute(aPersistType, data);		
	}
	
	public void sendMessage() {
		dataManager.sendMessage(this);
	}
	
	public void sendEmail() throws FwException {
		dataManager.sendEmailMessage((FwAbstractCollection) this);
	}

	public List<Object> getNext(int count) throws FwException {		
		int toIndex = 0;
		int fromIndex = 0;
		List<Object> subList = new ArrayList<Object>();	
		
		try {
			if(null != list) {
			int size = list.size();
			if (list != null && list.size() == 0) { return subList; }

			if (size < count) {
				toIndex = size;			
			} else {
				if (currentIndex >= size) { return subList; }					
				
				fromIndex = currentIndex + count;
				if (count > size) { toIndex = size; } 
				else { toIndex = fromIndex + count; }
				
				if (fromIndex >= size) { fromIndex  = currentIndex; }
				if (toIndex >= size) { toIndex = size; }
			}
			subList = list.subList(fromIndex, toIndex);
			currentIndex =  fromIndex;
			}
		}
		catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		return subList;		
	}

	public List<Object> getPrevious(int count) throws FwException {
		int toIndex = 0;
		int fromIndex = 0;
		List<Object> subList = new ArrayList<Object>();
	
		try {	
			if(null != list) {
	        int size = list.size();
			if (list != null && size == 0) { return subList; }

			if (size < count) {
				toIndex = size;
			} else {
				if (currentIndex <= 0) { fromIndex = 0; }
				else { fromIndex = currentIndex - count; }
				
				toIndex = fromIndex + count;			
				if (toIndex < 0) { toIndex = 0; }			
				if (fromIndex < 0) 	{ fromIndex = 0; }
				if (fromIndex == 0 && toIndex == 0) { return subList; }
				currentIndex = fromIndex;
			}
			subList = list.subList(fromIndex, toIndex);	
		    }
			 }
			
			
		catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		return subList;		
	}

	public List<Object> getFirst(int count) throws FwException {
		int toIndex = 0;
		int fromIndex = 0;
		List<Object> subList = new ArrayList<Object>();
		
		try {
			if(null != list) {
			int size = list.size();
			if (list != null && size == 0) { return subList; }			

			if (size < count) { toIndex = size; }
			else { toIndex = count; }
			subList = list.subList(fromIndex, toIndex);
			currentIndex = 0;
			}
		}
		catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		return subList;
	}

	public List<Object> getLast(int count) throws FwException {
		int toIndex = 0;
		int fromIndex  = 0;
		List<Object> subList = new ArrayList<Object>();

		try {
			if(null != list) {
			int size = list.size();
			if (list != null && size == 0) { return subList; }
			
			toIndex = size;
			if (size < count) { fromIndex = 0; }
			else { fromIndex = size - count; }
			
			subList = list.subList(fromIndex, toIndex);
			currentIndex = size - count;
			}
		}
		catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		return  subList;
	}
}