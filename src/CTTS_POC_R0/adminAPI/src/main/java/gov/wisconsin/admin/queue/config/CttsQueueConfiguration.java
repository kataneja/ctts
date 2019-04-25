package gov.wisconsin.admin.queue.config;

import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.JndiDestinationResolver;

@Configuration
public class CttsQueueConfiguration {

	@Bean(name="cttsQueue")
	public JmsTemplate cttsJMSTemplate() {
		try {
			JmsTemplate template = new JmsTemplate();
			template.setConnectionFactory(cttsQCF());
			template.setDestinationResolver(new JndiDestinationResolver());
			template.setDefaultDestinationName("jms/ctts_Q");
			return template;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean(name="cttsQCF") @Primary
	public QueueConnectionFactory cttsQCF() {
		try {
			return (QueueConnectionFactory) PortableRemoteObject.narrow(new InitialContext().lookup("jms/ctts_F"), QueueConnectionFactory.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** Required if listening for queue **/
	@Bean(name="cttsLCF")
	public JmsListenerContainerFactory<DefaultMessageListenerContainer> cttsLCF() {
		try {
			DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
			factory.setConnectionFactory(cttsQCF());
			return factory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

