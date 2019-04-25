package gov.wisconsin.framework.security.core;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.Handler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import com.ibm.ws.webservices.engine.GuardedMessageContext;

/**
 * **** Example usernameToken *******
 * <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">
         <wsse:UsernameToken>
            <wsse:Username>userName</wsse:Username>
            <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">password</wsse:Password>
            <wsse:Nonce EncodingType="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary">LqtiatEllLRo+iudKjMwtw==</wsse:Nonce>
            <wsu:Created xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">2017-06-21T20:11:03.536Z</wsu:Created>
         </wsse:UsernameToken>
      </wsse:Security>
 */

/**
 * This will generate security token whose type is [http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#UsernameToken]
 * This type will be passed as a headed value with a web-service request that requires this UsernameToken for authentication.
 */
public class WSClientContextHandler extends FwBaseClass implements Handler {
	
	// Constants for the security token
	private final String OASIS_UTILITY_XSD 	   = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	private final String OASIS_WEBSECURITY_XSD = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private final String BASE64_BINARY  	   = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary";
	private final String PASSWORD_TEXT         = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText";
	private final String PASSWORD_DIGEST       = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest";
	
	private QName qn[] = null;
	private String userName = null;
	private String password = null;
	private String passwordType = null;

	@Override
	public void init(HandlerInfo info) {
		Map<String, String> map = info.getHandlerConfig();
		userName = map.get(FwConstants.USER_NAME);
		password = map.get(FwConstants.PASSWORD);
		passwordType = map.get(FwConstants.PASSWORD_TYPE);
	}
	
	@Override
	public boolean handleRequest(MessageContext ctx) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();){
			if(userName != null && password != null) {
				//From the spec: Password_Digest = Base64 ( SHA-1 ( nonce + created + password ) )
		        //Make the nonce
		        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
		        rand.setSeed(System.currentTimeMillis());
		        byte[] nonceBytes = new byte[16];
		        rand.nextBytes(nonceBytes);
		        
		        //Make the created date
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        df.setTimeZone(TimeZone.getTimeZone("UTC"));
		        String createdDate = df.format(Calendar.getInstance().getTime());
		        byte[] createdDateBytes = createdDate.getBytes("UTF-8");
		        
		        //Make the password
		        byte[] passwordBytes = password.getBytes("UTF-8");
		        
		        //SHA-1 hash the bunch of it. 
		        baos.write(nonceBytes);
		        baos.write(createdDateBytes);
		        baos.write(passwordBytes);
		        MessageDigest md = MessageDigest.getInstance("SHA-1");
		        byte[] digestedPassword = md.digest(baos.toByteArray());
		        
		        // Base64 encode the password and nonce for sending                   
		        String passwordB64 = Base64.getEncoder().encodeToString(digestedPassword);
		        String nonceB64 = Base64.getEncoder().encodeToString(nonceBytes);
				
		        
		        // Build the token
				GuardedMessageContext soapMessageContext = (GuardedMessageContext)ctx;
				SOAPMessage sm = soapMessageContext.getMessage();
				SOAPHeader header = sm.getSOAPPart().getEnvelope().getHeader();
				SOAPElement securityElm = header.addChildElement("Security", "wsse", OASIS_WEBSECURITY_XSD);
	            SOAPElement usernameTokenElm = securityElm.addChildElement("UsernameToken", "wsse");
	            SOAPElement usernameElm = usernameTokenElm.addChildElement("Username", "wsse");
	            usernameElm.addTextNode(userName);
	            SOAPElement passwordElm = usernameTokenElm.addChildElement("Password", "wsse");
	            
	            if(FwConstants.PASSWORD_DIGEST.equals(passwordType)) {
	            	// Add encrypted password
	            	passwordElm.addAttribute(new QName("Type"), PASSWORD_DIGEST);
	            	passwordElm.addTextNode(passwordB64);	
	            } else {
	            	// Add plain text password
	            	passwordElm.addAttribute(new QName("Type"), PASSWORD_TEXT);
	            	passwordElm.addTextNode(password);
	            }      
	                    
	            SOAPElement nonceElm = usernameTokenElm.addChildElement("Nonce", "wsse");
	            nonceElm.addAttribute(new QName("EncodingType"), BASE64_BINARY);
	            nonceElm.addTextNode(nonceB64);
	            
	            SOAPElement created = usernameTokenElm.addChildElement("Created", "wsu", OASIS_UTILITY_XSD);
	            created.addTextNode(createdDate);
	            
				sm.saveChanges();
			} else {
				throw new NullPointerException("userName or password is null");
			}
		} catch (Exception e) {
			apiLogger.debug("Web service security token could not be created in class " + this.getClassName());
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY, "Web service security token could not be created.");
		}
		return true;
	}

	@Override
	public void destroy() {}
	
	@Override
	public QName[] getHeaders() { return qn; }
	
	@Override
	public boolean handleFault(MessageContext arg0) { return false; }

	@Override
	public boolean handleResponse(MessageContext arg0) { return false; }

}