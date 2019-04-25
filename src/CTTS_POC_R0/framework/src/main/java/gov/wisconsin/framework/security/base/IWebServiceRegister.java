package gov.wisconsin.framework.security.base;

import javax.xml.rpc.Service;

public interface IWebServiceRegister {
	public void registerHandler(Service service, String webServicePortName, String webServiceEndpoint, String userName, String password, String passwordType, Class<?> handlerClass);
}
