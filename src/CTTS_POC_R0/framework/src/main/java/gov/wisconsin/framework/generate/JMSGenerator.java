package gov.wisconsin.framework.generate;

public class JMSGenerator {
	
	public static void main(String args[]) {
		generate("admin", "audit", true, true);
	}
	
	public static void generate(String projectName, String queueName, boolean producer, boolean consumer) {
		StringBuilder builder = new StringBuilder();

		queueName = queueName.toLowerCase();
		String capitalizedQueueName = queueName.substring(0,1).toUpperCase() + queueName.substring(1);

		builder.append("\npackage gov.wisconsin." + projectName.toLowerCase() + ".queue;");
		builder.append("\n");
		builder.append("\nimport javax.naming.InitialContext;");
		builder.append("\nimport javax.rmi.PortableRemoteObject;");
		builder.append("\nimport javax.jms.QueueConnectionFactory;");
		builder.append("\n");
		
		if (producer) {
			builder.append("\nimport org.springframework.jms.core.JmsTemplate;");
		}
		
		builder.append("\nimport org.springframework.context.annotation.Bean;");
		builder.append("\nimport org.springframework.context.annotation.Configuration;");
		
		if (consumer) {
			builder.append("\nimport org.springframework.jms.config.JmsListenerContainerFactory;");
			builder.append("\nimport org.springframework.jms.listener.DefaultMessageListenerContainer;");
			builder.append("\nimport org.springframework.jms.config.DefaultJmsListenerContainerFactory;");
		}
		
		if (producer) {
			builder.append("\nimport org.springframework.jms.support.destination.JndiDestinationResolver;");
		}
		
		builder.append("\n");
		builder.append("\n@Configuration");
		builder.append("\npublic class " + capitalizedQueueName + "QueueConfiguration {");
		builder.append("\n");
		
		if (producer) {
			builder.append("\n\t@Bean(name = \"" + queueName + "Queue\")");
			builder.append("\n\tpublic JmsTemplate " + queueName + "JMSTemplate() {");
			builder.append("\n\t\ttry {");
			builder.append("\n\t\t\tJmsTemplate template = new JmsTemplate();");
			builder.append("\n\t\t\ttemplate.setConnectionFactory(" + queueName + "QCF());");
			builder.append("\n\t\t\ttemplate.setDestinationResolver(new JndiDestinationResolver());");
			builder.append("\n\t\t\ttemplate.setDefaultDestinationName(\"jms/" + queueName + "_Q\");");
			builder.append("\n\t\t\treturn template;");
			builder.append("\n\t\t} catch (Exception e) {");
			builder.append("\n\t\t\te.printStackTrace();");
			builder.append("\n\t\t}");
			builder.append("\n\t\treturn null;");
			builder.append("\n\t}");
			builder.append("\n");
		}
		
		builder.append("\n\t@Bean(name = \"" + queueName + "QCF\")");
		builder.append("\n\tpublic QueueConnectionFactory " + queueName + "QCF() {");
		builder.append("\n\t\ttry {");
		builder.append("\n\t\t\treturn (QueueConnectionFactory) PortableRemoteObject.narrow(new InitialContext().lookup(\"jms/" + queueName + "_F\"), QueueConnectionFactory.class);");
		builder.append("\n\t\t} catch (Exception e) {");
		builder.append("\n\t\t\te.printStackTrace();");
		builder.append("\n\t\t}");
		builder.append("\n\t\treturn null;");
		builder.append("\n\t}");
		builder.append("\n");
		
		if (consumer) {
			builder.append("\n\t/** Required if listening for queue **/");
			builder.append("\n\t@Bean(name = \"" + queueName + "LCF\")");
			builder.append("\n\tpublic JmsListenerContainerFactory<DefaultMessageListenerContainer> " + queueName + "LCF() {");
			builder.append("\n\t\ttry {");
			builder.append("\n\t\t\tDefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();");
			builder.append("\n\t\t\tfactory.setConnectionFactory(" + queueName + "QCF());");
			builder.append("\n\t\t\treturn factory;");
			builder.append("\n\t\t} catch (Exception e) {");
			builder.append("\n\t\t\te.printStackTrace();");
			builder.append("\n\t\t}");
			builder.append("\n\t\treturn null;");
			builder.append("\n\t}");
		}
		
		builder.append("\n}");
		
		System.out.println(builder.toString());
	}
}
