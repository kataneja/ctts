package gov.wisconsin.framework.management;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.base.IConfiguration;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.pojo.FwEmailDistribution;
import gov.wisconsin.framework.data.pojo.FwEmailMessage;
import gov.wisconsin.framework.exception.FwExceptionManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public final class FwConfigurationManager extends FwBaseClass implements IConfiguration {

	private static IConfiguration instance;
	
	@Value(FwConstants.PROPERTY_FOLDER_INJECT)
	private String propertyFolder;
	
	private ResourcePatternResolver resourceResolver;	//@Autowired
	
	private Properties springProperties = new Properties();
	
	private Map<String, String> urlMap = null;
	private Map<String, Templates> xsltTemplates = null;
	private Map<String, FwEmailMessage> xmlEmailMessage = null;
	private Map<String, Map<String, String>> xmlSQLConfiguration = null;
	private Map<String, Map<String, Object>> driverConfiguration = null;
	private Map<String, FwEmailDistribution> xmlEmailDistribution = null;
	
	private String activityMQSwitchInitial = null;
	private Transformer accountTransferSchematronTransformer = null;
	
	private FwConfigurationManager() {}
	
	@Override
	public void init() {
		try {
			loadSpringProperties();
			loadURLMapping();
			loadSQLConfiguration();
			loadEmailConfiguration();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	private void loadURLMapping() {
		Scanner scanner = null;
		Resource urlFile = null;
		urlMap = new HashMap<String, String>();
		
		try {
			String tmpURL = "";
			String tmpCode = "";
			urlFile = resourceResolver.getResources("classpath:/url.properties")[0];
			scanner = new Scanner(urlFile.getFile());

			while(scanner.hasNextLine()) {
	            String split[] = scanner.nextLine().trim().split("=");
	            if(split.length == 2 && !split[0].startsWith("#")) {
	            	tmpCode = split[0].trim();
	            	tmpURL = split[1].trim();

	            	if (!urlMap.containsValue(tmpCode)) {
	            		urlMap.put(tmpURL, tmpCode);
	            	} else {
						String errorMessage = "Duplicate entry of " + tmpCode + " found in " + urlFile.getFilename() + ".";
						System.err.println(errorMessage);
						FwExceptionManager.handleException(this.getClass(), new Exception(errorMessage), FwConstants.EXP_TYP_FRAMEWORK, errorMessage);
	            	}
	            }
	        }
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
	
	private void loadSpringProperties() {
		Resource[] propertyFiles = null;

		try {
			propertyFiles = resourceResolver.getResources("classpath:" + propertyFolder + "/*.properties");
			for (int i = 0; i < propertyFiles.length; i++) {
				springProperties.putAll(PropertiesLoaderUtils.loadAllProperties(propertyFiles[i].getFilename()));
			}
			
			activityMQSwitchInitial = springProperties.getProperty(FwConstants.ACTIVITY_TIMER_WRITE_TO_MQ);
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	private void loadSQLConfiguration() {
		String fileName = null;
		Resource current = null;
		Resource[] xmlFiles = getXMLResources(FwConstants.SQL_FOLDER);
		xmlSQLConfiguration = new HashMap<String, Map<String, String>>();
		
		try {
			for (int i = 0; i < xmlFiles.length; i++) {
				current = xmlFiles[i];
				fileName = current.getFilename();
				if(fileName.toLowerCase().contains(".xml")) {
					cacheSql(current.getInputStream(), fileName);
				}
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK, "Check the following parameters :: folderLocation = " + getSpringProperty(FwConstants.SQL_FOLDER) + " :: fileName = " + fileName);
		}
	}
	
	private void loadEmailConfiguration() {
		String fileName = null;
		Resource currentFile = null;
		
		try {
			String emailProcess = getSpringProperty(FwConstants.EMAIL_PROCESS_SWITCH);
			
			if("on".equalsIgnoreCase(emailProcess)) {
				Resource[] xmlFiles = getXMLResources(FwConstants.EMAIL_FOLDER);
				
				xmlEmailMessage = new HashMap<String, FwEmailMessage>();
				xmlEmailDistribution = new HashMap<String, FwEmailDistribution>();
				
				for (int i = 0; i < xmlFiles.length; i++) {
					currentFile = xmlFiles[i];
					fileName = currentFile.getFilename();
					if(fileName.toLowerCase().contains(".xml")) {
						cacheEmail(currentFile.getInputStream(), fileName);
					}
				}
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK, "Check the following parameters :: folderLocation = " + getSpringProperty(FwConstants.EMAIL_FOLDER) + " :: fileName = " + fileName);
		}
	}
	
	private void cacheSql(InputStream is, String s) {
		try {
			Document document = this.unmarshall(new InputStreamReader(is));
			String schemaName = "";
			
			Element root = document.getRootElement();
			if (!root.getChildren().isEmpty()) {
				List<?> children = root.getChildren("sql");
				
				for (Iterator<?> i = children.iterator(); i.hasNext();) {
					Element element = (Element) i.next();
					String key = element.getAttribute(FwConstants.SQL_ID).getValue();
					List<?> attrib = element.getAttributes();
					Map<String, String> attributeMap = new HashMap<String, String>();
					
					for (Iterator<?> j = attrib.iterator(); j.hasNext();) {
						Attribute attribute = (Attribute) j.next();
						
						if(attribute.getName().equals(FwConstants.SQL_VALUE)) {
							String value = attribute.getValue();
							String prd_value = value.replaceAll(FwConstants.SCHEMA_TOKEN, schemaName);
							attributeMap.put(FwConstants.SQL_VALUE, prd_value);
						} else {
							attributeMap.put(attribute.getName(), attribute.getValue());
						}
					}
					
					if(xmlSQLConfiguration.get(key) != null) {
						String errorMessage = "Duplicate entry of " + key + " found in " + s;
						System.err.println(errorMessage);
						FwExceptionManager.handleException(this.getClass(), new Exception(errorMessage), FwConstants.EXP_TYP_FRAMEWORK, errorMessage);
					}
					
					xmlSQLConfiguration.put(key, attributeMap);
				}
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);			
		}
	}
	
	private void cacheEmail(InputStream is, String s) {
		try {
			Document document = this.unmarshall(new InputStreamReader(is));
			Element rootElement = document.getRootElement();
			String rootName = rootElement.getName();
			
			if(rootName.equals(FwConstants.EMAIL_ROOT)) {
				cacheEmailMessageXML(rootElement, s);
			} else if (rootName.equals(FwConstants.DISTRIBUTION_ROOT)) {
				cacheEmailDistributionXML(rootElement, s);
			} else {
				String errorMessage = "Unknown root element found in " + s;
				System.err.println(errorMessage);
				FwExceptionManager.handleException(this.getClass(), new Exception(errorMessage), FwConstants.EXP_TYP_FRAMEWORK, errorMessage);
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	private void cacheEmailMessageXML(Element emailRootElement, String s) {
		try {
			if(emailRootElement != null && !emailRootElement.getChildren().isEmpty()) {
				String body = FwConstants.EMPTY_STRING;
				String footer = FwConstants.EMPTY_STRING;
				String subject = FwConstants.EMPTY_STRING;
				String header =  FwConstants.EMPTY_STRING;
				FwEmailMessage fwEmailMessage = null;
				List<?> emailChildElements = emailRootElement.getChildren();
				int emailChildElementsSize = emailChildElements.size();

				for(int i = 0; i < emailChildElementsSize; i++) {
					Element emailChildElement = (Element) emailChildElements.get(i);
					if(null != emailChildElement ){
					String emailId = emailChildElement.getAttributeValue(FwConstants.EMAIL_ID);
					
					if(this.xmlEmailMessage.get(emailId) != null) {
						String errorMessage = "Duplicate entry of " + emailId + " found in " + s;
						System.err.println(errorMessage);
						FwExceptionManager.handleException(this.getClass(), new Exception(errorMessage), FwConstants.EXP_TYP_FRAMEWORK, errorMessage);
					}
					
					if(!emailChildElement.getChildren().isEmpty()) {
						body = emailChildElement.getChildText(FwConstants.EMAIL_BODY);
						header = emailChildElement.getChildText(FwConstants.EMAIL_HEADER);
						footer = emailChildElement.getChildText(FwConstants.EMAIL_FOOTER);
						subject = emailChildElement.getChildText(FwConstants.EMAIL_SUBJECT);
					}
					
					fwEmailMessage = new FwEmailMessage(subject, header, body, footer);
					xmlEmailMessage.put(emailId, fwEmailMessage);
					}
				}
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
	}
	
	private void cacheEmailDistributionXML(Element emailDistributionRootElement, String s) {
		try {
			if(emailDistributionRootElement != null && !emailDistributionRootElement.getChildren().isEmpty()) {
				String value =  FwConstants.EMPTY_STRING;
				String distributionId = FwConstants.EMPTY_STRING;
				FwEmailDistribution fwEmailDistribution = null;
				List<?> emailDistributionChildElements = emailDistributionRootElement.getChildren();
				int emailDistributionChildElementsSize = emailDistributionChildElements.size();

				for(int i = 0; i < emailDistributionChildElementsSize; i++) {
					Element emailDistributionChildElement = (Element) emailDistributionChildElements.get(i);
					distributionId = emailDistributionChildElement.getAttributeValue(FwConstants.EMAIL_ID);
					
					if(this.xmlEmailDistribution.get(distributionId) != null) {
						String errorMessage = "Duplicate entry of " + distributionId + " found in " + s;
						System.err.println(errorMessage);
						FwExceptionManager.handleException(this.getClass(), new Exception(errorMessage), FwConstants.EXP_TYP_FRAMEWORK, errorMessage);	
					}
					
					value = emailDistributionChildElement.getAttributeValue(FwConstants.EMAIL_LIST);
					fwEmailDistribution = new FwEmailDistribution(value);
					xmlEmailDistribution.put(distributionId, fwEmailDistribution);
				}
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);			
		}
	}

	private Document unmarshall(Reader reader) {
		Document document = null;
		
		try {
			SAXBuilder builder = new SAXBuilder(false);
			document = builder.build(reader);
		} catch (JDOMException jdome) {
			FwExceptionManager.handleException(this.getClass(), jdome, FwConstants.EXP_TYP_FRAMEWORK);
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return document;
	}
	
	@Override
	public Resource[] getResources(String folderLocation, String fileExtension) {
		Resource[] resources = null;
		
		try {
			resources = resourceResolver.getResources("classpath:" + getSpringProperty(folderLocation) + "/*." + fileExtension);
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return resources;
	}
	
	@Override
	public Resource getClasspathResource(String fileName) {
		Resource result = null;
		Resource[] resources = null;
		
		try {
			resources = resourceResolver.getResources("classpath:/" + fileName);
			if (resources.length > 0) {
				result = resources[0];
			}
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_FRAMEWORK);
		}
		
		return result;
	}

	public String getCodeForURL(String url) {
		return urlMap.get(url);
	}
	
	@Override
	public void turnOnActivityMQSwitch() {
		springProperties.replace(FwConstants.ACTIVITY_TIMER_WRITE_TO_MQ, FwConstants.ON);
	}

	@Override
	public void turnOffActivityMQSwitch() {
		springProperties.replace(FwConstants.ACTIVITY_TIMER_WRITE_TO_MQ, FwConstants.OFF);
	}

	@Override
	public void resetActivityMQSwitch() {
		springProperties.replace(FwConstants.ACTIVITY_TIMER_WRITE_TO_MQ, activityMQSwitchInitial);
	}
	
	@Override
	public Resource[] getXSLTResources(String folderLocation) {
		return getResources(folderLocation, "xslt");
	}
	
	@Override
	public Resource[] getSCHResources(String folderLocation) {
		return getResources(folderLocation, "sch");
	}
	
	@Override
	public Resource[] getXSLResources(String folderLocation) {
		return getResources(folderLocation, "xsl");
	}
	
	@Override
	public Resource[] getXMLResources(String folderLocation) {
		return getResources(folderLocation, "xml");
	}
	
	@Override
	public Resource[] getXSDResources(String folderLocation) {
		return getResources(folderLocation, "xsd");
	}
	
	@Override
	public FwEmailMessage getXMLEmailId(String emailId) {
		return xmlEmailMessage.get(emailId);
	}
	
	@Override
	public Templates getXSLTTemplate(String key) {
		return xsltTemplates.get(key);
	}
	
	@Override
	public Map<String, Map<String, Object>> getDriverConfiguration() {
		return driverConfiguration;
	}

	@Override
	public Map<String, String> getXMLSQLConfiguration(String sqlIndicator) {
		return xmlSQLConfiguration.get(sqlIndicator);
	}
	
	@Override
	public FwEmailDistribution getXMLdistributionList(String distributionId) {
		return (FwEmailDistribution) xmlEmailDistribution.get(distributionId);
	}
	
	@Override
	public String getSpringProperty(String property) {
		return this.springProperties.getProperty(property);
	}

	public Transformer getAccountTransferSchematronTransformer() {
		return accountTransferSchematronTransformer;
	}

	public void setAccountTransferSchematronTransformer(Transformer accountTransferSchematronTransformer) {
		this.accountTransferSchematronTransformer = accountTransferSchematronTransformer;
	}
	
	public ResourcePatternResolver getResourceResolver() {
		return resourceResolver;
	}

	@Autowired
	public void setResourceResolver(ResourcePatternResolver resourceResolver) {
		this.resourceResolver = resourceResolver;
	}
    
    public static void setInstance(IConfiguration configurationManager) {
    	instance = configurationManager;
    }
    
    public static IConfiguration getInstance() {
    	return instance;
    }
}