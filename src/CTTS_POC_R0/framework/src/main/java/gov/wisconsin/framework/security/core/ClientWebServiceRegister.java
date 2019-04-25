package gov.wisconsin.framework.security.core;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.security.base.IWebServiceRegister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import org.springframework.stereotype.Component;

/**
 * This class is used to register a handler to a web service client that is connecting to a 
 * secure web service provider. the handler will add a SOAP security token the the SOAP 
 * request header. The security token includes user name and password needed to connect
 * to the web service.<BR>
 * The call to register the handler should be done in the stub class constructor of the web service.
 * The <code>javax.xml.rpc.Service</code> object will need to be passed in from the stub to the register methods, in order
 * for the handler to be registered to a specific web service.<BR>
 * The ProfileSecurity web service client can be used as an example.
 */
@Component
public class ClientWebServiceRegister extends FwBaseClass implements IWebServiceRegister {
	
	private static IWebServiceRegister instance;
	
	private ClientWebServiceRegister() {}
	
	/**
	 * This method can be used to register a handler to a web service call. The handler 
	 * will add a security token to the SOAP web service request when trying to connect to 
	 * a web service that requires a security token with user name and password.
	 * @param service - this is the javax.xml.rpc.Service object that the handler will be registered to.
	 * @param webServicePortName - The web service port name from the WSDL. 
	 * @param webServiceEndpoint - The web service end point URL.
	 * @param userName - The user name required to connect to the web service.
	 * @param password - Password required to connect to the web service.
	 * @param passwordType - This can either be passwordText or passwordDigest. passwordText is plain text, passwordDigest is encrypted.
	 * @param handlerClass - The class that builds the SOAP security token that will be attached to the request.
	 */
	public void registerHandler(Service service, String webServicePortName, String webServiceEndpoint, String userName, String password, String passwordType, Class<?> handlerClass) {
		try {
			String endpoint = configurationManager.getSpringProperty(webServiceEndpoint);

			// Get the handler chain from the Handler registry
			HandlerRegistry registry = service.getHandlerRegistry();
			QName servicePort = new QName(endpoint, webServicePortName);

			List<HandlerInfo> handlerChain = registry.getHandlerChain(servicePort);

			// Add user name and password details
			Map<String, String> configDetailsMap = new HashMap<>();
			configDetailsMap.put(FwConstants.USER_NAME, configurationManager.getSpringProperty(userName));
			configDetailsMap.put(FwConstants.PASSWORD, configurationManager.getSpringProperty(password));
			configDetailsMap.put(FwConstants.PASSWORD_TYPE, passwordType);
			HandlerInfo handlerInfo = new HandlerInfo(handlerClass, configDetailsMap, null);
			handlerChain.add(handlerInfo);

			apiLogger.info("Registered Web Service security token handler on port " + servicePort);
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_WEBSERVICE);
		}
	}
	
	public static void setInstance(IWebServiceRegister clientWebServiceRegister) {
    	instance = clientWebServiceRegister;
    }
    
    public static IWebServiceRegister getInstance() {
    	return instance;
    }
}
