package gov.wisconsin.framework.base;

import gov.wisconsin.framework.data.pojo.FwEmailDistribution;
import gov.wisconsin.framework.data.pojo.FwEmailMessage;

import java.util.Map;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;

import org.springframework.core.io.Resource;

public interface IConfiguration {
	int PAGE_ACTION_SUCCESSTYPE_NONE             = 0;
	int PAGE_ACTION_SUCCESSTYPE_PAGE_ID          = 1;
	int PAGE_ACTION_SUCCESSTYPE_PAGE_ACTION      = 2;
	int PAGE_ACTION_SUCCESSTYPE_DRIVER_DIRECTION = 3;
	int PAGE_ACTION_SUCCESSTYPE_CLASS_METHOD     = 4;

	public void init();
	
	public String getCodeForURL(String url);
	
	public void resetActivityMQSwitch();
	
	public void turnOnActivityMQSwitch();
	
	public void turnOffActivityMQSwitch();

	public String getSpringProperty(String property);

	public Map<String, Map<String, Object>> getDriverConfiguration();
	
	public Map<String, String> getXMLSQLConfiguration(String sqlIndicator);

	public Templates getXSLTTemplate(String key);
	
	public FwEmailMessage getXMLEmailId(String emailId);

	public FwEmailDistribution getXMLdistributionList(String distributionId);
	
	public Resource getClasspathResource(String fileName);
	
	public Resource[] getResources(String folderLocation, String fileExtension);
	
	public Resource[] getXMLResources(String folderLocation);
	
	public Resource[] getXSDResources(String folderLocation);

	public Resource[] getSCHResources(String folderLocation);
	
	public Resource[] getXSLResources(String folderLocation);
	
	public Resource[] getXSLTResources(String folderLocation);
	
	public Transformer getAccountTransferSchematronTransformer();

}