package gov.wisconsin.timeoff.queue.config;

import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.JndiDestinationResolver;

@Configuration
public class AuditQueueConfiguration {

	@Bean(name="auditQueue")
	public JmsTemplate auditJMSTemplate() {
		try {
			JmsTemplate template = new JmsTemplate();
			template.setConnectionFactory(auditQCF());
			template.setDestinationResolver(new JndiDestinationResolver());
			template.setDefaultDestinationName("jms/audit_Q");
			return template;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean(name="auditQCF")
	public QueueConnectionFactory auditQCF() {
		try {
			return (QueueConnectionFactory) PortableRemoteObject.narrow(new InitialContext().lookup("jms/audit_F"), QueueConnectionFactory.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** Required if listening for queue **/
	@Bean(name="auditLCF")
	public JmsListenerContainerFactory<DefaultMessageListenerContainer> auditLCF() {
		try {
			DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
			factory.setConnectionFactory(auditQCF());
			return factory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
