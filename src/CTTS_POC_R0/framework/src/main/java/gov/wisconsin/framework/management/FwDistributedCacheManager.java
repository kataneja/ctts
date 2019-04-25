package gov.wisconsin.framework.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.stereotype.Component;

import com.ibm.websphere.cache.DistributedMap;
import com.ibm.websphere.cache.EntryInfo;

@Component
public class FwDistributedCacheManager extends FwBaseClass {
	
	private boolean dynamicCache = true;
	private Map<String, Map<String, Map<String, Object>>> distributedMapCache = new HashMap<String, Map<String, Map<String, Object>>>();
	
	public Map<String, Map<String, Object>> getDistributedMap(String name) {
		Map<String, Map<String, Object>> distributedMap = null;

		try {
			if(distributedMapCache.get(name) == null) {
				if(dynamicCache) {
					InitialContext ic = new InitialContext();
					distributedMap = (DistributedMap) ic.lookup(name);
					((DistributedMap) distributedMap).setSharingPolicy(EntryInfo.SHARED_PUSH);
					((DistributedMap) distributedMap).setTimeToLive(FwConstants.DYNA_CACHE_TTL);
				} else {
					distributedMap = new HashMap<String, Map<String, Object>>();
				}
				distributedMapCache.put(name, distributedMap);
			} else {
				distributedMap = distributedMapCache.get(name);
			}
		} catch (NamingException e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return distributedMap;
	}
	
	public void clearDistributedMap(String name) {
		getDistributedMap(name).clear();
	}
	
	public void putObject(String name, String key, Map<String, Object> map) {
		getDistributedMap(name).put(key, map);
	}
	
	public Map<String, Object> getObject(String distributedMapName, String objectId) {
		return getDistributedMap(distributedMapName).get(objectId);
	}
	
	public void removeObject(String distributedMapName, String objectId) {
		getDistributedMap(distributedMapName).remove(objectId);
	}

}
