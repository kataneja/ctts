package gov.wisconsin.framework.base;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.IConnection;
import gov.wisconsin.framework.data.base.IData;
import gov.wisconsin.framework.data.base.IEmail;
import gov.wisconsin.framework.data.base.IMessageQueue;
import gov.wisconsin.framework.data.base.IReferenceTableManager;
import gov.wisconsin.framework.impl.FwDate;
import gov.wisconsin.framework.impl.FwDateRoutine;
import gov.wisconsin.framework.management.FwDistributedCacheManager;
import gov.wisconsin.framework.security.base.ILdap;
import gov.wisconsin.framework.security.base.IWebServiceRegister;
import gov.wisconsin.framework.util.FwMessageFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class FwBaseClass {
	
	@JsonIgnore protected static ApplicationContext staticApplicationContext;
	
	@JsonIgnore protected ApplicationContext applicationContext; 			 //@Autowired

	@JsonIgnore protected FwDate fwDate;			                    	 //@Autowired
	@JsonIgnore protected FwDateRoutine dateRoutine;	                     //@Autowired
	@JsonIgnore protected FwMessageFormatter messageFormatter;  			 //@Autowired
	@JsonIgnore protected IWebServiceRegister clientWebServiceRegister;		 //@Autowired

	@JsonIgnore protected IPage pageManager;  								 //@Autowired
	@JsonIgnore protected ILdap ldapManager;  								 //@Autowired
	@JsonIgnore protected IData dataManager;								 //@Autowired
	@JsonIgnore protected IEmail emailManager;						 		 //@Autowired
	@JsonIgnore protected IValidation fwValidator;					         //@Autowired

	@JsonIgnore protected IConnection connectionManager;  					 //@Autowired
	@JsonIgnore protected IMessageQueue messageQueueManager;  				 //@Autowired
	@JsonIgnore protected IConfiguration configurationManager;		 		 //@Autowired
	@JsonIgnore protected IReferenceTableManager referenceTableManager;		 //@Autowired
	@JsonIgnore protected FwDistributedCacheManager distributedCacheManager; //@Autowired

	@JsonIgnore protected static final Logger apiLogger      = LoggerFactory.getLogger(FwConstants.LOGGER_API);
	@JsonIgnore protected static final Logger activityLogger = LoggerFactory.getLogger(FwConstants.LOGGER_ACTIVITY);
	
	public FwBaseClass() {}
	
	protected final String getClassName() {
		String classString = this.getClass().toString();
		return classString.substring(classString.lastIndexOf(".") + 1);
	}
	
    public static void setStaticApplicationContext(ApplicationContext ctx) {
    	staticApplicationContext = ctx;
    }
    
    public static ApplicationContext getStaticApplicationContext() {
    	return staticApplicationContext;
    }
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
    
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public FwDate getFwDate() {
		return fwDate;
	}
	
	@Autowired
	public void setFwDate(FwDate fwDate) {
		this.fwDate = fwDate;
	}
	
	public IReferenceTableManager getReferenceTableManager() {
		return referenceTableManager;
	}
	
	@Autowired
	public void setReferenceTableManager(IReferenceTableManager referenceTableManager) {
		this.referenceTableManager = referenceTableManager;
	}
	
	public IConfiguration getConfigurationManager() {
		return configurationManager;
	}

	@Autowired
	public void setConfigurationManager(IConfiguration configurationManager) {
		this.configurationManager = configurationManager;
	}

	public ILdap getLdapManager() {
		return ldapManager;
	}

	@Autowired
	public void setLdapManager(ILdap ldapManager) {
		this.ldapManager = ldapManager;
	}
	
	public IConnection getConnectionManager() {
		return connectionManager;
	}

	@Autowired
	public void setConnectionManager(IConnection connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	public IPage getPageManager() {
		return pageManager;
	}

	@Autowired
	public void setPageManager(IPage pageManager) {
		this.pageManager = pageManager;
	}
	
	public IMessageQueue getMessageQueueManager() {
		return messageQueueManager;
	}

	@Autowired
	public void setMessageQueueManager(IMessageQueue messageQueueManager) {
		this.messageQueueManager = messageQueueManager;
	}
	
	public IWebServiceRegister getClientWebServiceRegister() {
		return clientWebServiceRegister;
	}

	@Autowired
	public void setClientWebServiceRegister(IWebServiceRegister clientWebServiceRegister) {
		this.clientWebServiceRegister = clientWebServiceRegister;
	}
	
    public IEmail getEmailManager() {
		return emailManager;
	}

    @Autowired
	public void setEmailManager(IEmail emailManager) {
		this.emailManager = emailManager;
	}
    
    public FwDateRoutine getDateRoutine() {
		return dateRoutine;
	}

	@Autowired
	public void setDateRoutine(FwDateRoutine dateRoutine) {
		this.dateRoutine = dateRoutine;
	}
	
	public IData getDataManager() {
		return dataManager;
	}

	@Autowired
	public void setDataManager(IData dataManager) {
		this.dataManager = dataManager;
	}
    
	public IValidation getFwValidator() {
		return fwValidator;
	}

	@Autowired
	public void setFwValidator(IValidation fwValidator) {
		this.fwValidator = fwValidator;
	}
	
    public FwMessageFormatter getMessageFormatter() {
		return messageFormatter;
	}

    @Autowired
	public void setMessageFormatter(FwMessageFormatter messageFormatter) {
		this.messageFormatter = messageFormatter;
	}
    
    public FwDistributedCacheManager getDistributedCacheManager() {
		return distributedCacheManager;
	}

    @Autowired
	public void setDistributedCacheManager(FwDistributedCacheManager distributedCacheManager) {
		this.distributedCacheManager = distributedCacheManager;
	}
}