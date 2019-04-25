package gov.wisconsin.framework.bean.impl;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.base.IPreload;
import gov.wisconsin.framework.data.management.FwConnectionManager;
import gov.wisconsin.framework.data.management.FwDataManager;
import gov.wisconsin.framework.data.management.FwMessageQueueManager;
import gov.wisconsin.framework.management.FwConfigurationManager;
import gov.wisconsin.framework.management.FwPageManager;
import gov.wisconsin.framework.management.FwReferenceTableManager;
import gov.wisconsin.framework.security.core.ClientWebServiceRegister;
import gov.wisconsin.framework.security.core.FwLdapManager;
import gov.wisconsin.framework.util.FwMessageFormatter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class FwPreloadBean extends FwBaseClass implements IPreload, ApplicationListener<ContextRefreshedEvent> {

	private static boolean initialized = false;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (!initialized) {
			init();
			initialized = true;
		}
	}

	@Override
	public void init() {
		/**
		 *	This makes certain singleton beans and spring objects available to non spring managed classes through static instances
		 */
		FwBaseClass.setStaticApplicationContext(applicationContext);
		
		FwPageManager.setInstance(pageManager);
		FwDataManager.setInstance(dataManager);
		FwLdapManager.setInstance(ldapManager);
		FwMessageFormatter.setInstance(messageFormatter);
		FwConnectionManager.setInstance(connectionManager);
		FwMessageQueueManager.setInstance(messageQueueManager);
		FwConfigurationManager.setInstance(configurationManager);
		FwReferenceTableManager.setInstance(referenceTableManager);
		ClientWebServiceRegister.setInstance(clientWebServiceRegister);
		
		/**
		 *	Initialize core framework management classes - Don't modify the order of execution unless you are VERY sure you need to
		 */
		//connectionManager.init();
		configurationManager.init();
	}
}