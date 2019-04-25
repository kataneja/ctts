package gov.wisconsin.framework.data;

import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class JPAConfiguration {
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = null;
		
		try {
			InitialContext initialContext = new InitialContext();
			dataSource = (DataSource) initialContext.lookup("jdbc/ctts");
			initialContext.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return dataSource;
	}
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", Oracle10gDialect.class.getName());
        properties.put("hibernate.show_sql", "false");
        return properties;
    }
	
	@Bean
    LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setHibernateProperties(hibernateProperties());
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("gov.wisconsin.admin.data");
        return factoryBean;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}