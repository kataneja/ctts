package gov.wisconsin.framework.data.base;

import javax.jms.Message;

public interface IMessageQueue {
	
	public void send(ICargoMSG cargo);
	
	public Message readMessage(String connectionFactory, String queueName);
}