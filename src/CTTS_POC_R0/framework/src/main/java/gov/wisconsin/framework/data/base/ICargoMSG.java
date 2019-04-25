package gov.wisconsin.framework.data.base;

public interface ICargoMSG extends ICargo {
	
	public String getConnectionFactoryAliasName();
	
	public String getQueueAliasName();
	
	public int getMessageType();
	
	public String generateTextMessage();
	
	public int getMsg_priority();
	
	public boolean isTransaction();
}
