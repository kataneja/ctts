package gov.wisconsin.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@Lazy
public class FwLdapConfiguration {
	
    private Environment environment;
	
	@Bean
	public LdapContextSource contextSource () {
	    LdapContextSource contextSource = new LdapContextSource();
	    contextSource.setUrl(environment.getRequiredProperty("LDAP_URL"));
	    contextSource.setBase(environment.getRequiredProperty("LDAP_PEOPLE_SEARCH_BASE"));
	     
	    // Admin user name and password with READ only rights
	    // Is used in ACCESS for authentication purposes.
//	    contextSource.setUserDn(env.getRequiredProperty("LDAP.USERDN"));
//	    contextSource.setPassword(env.getRequiredProperty("LDAP.PASSWORD"));
	     
	    // Self Registration user name and password with READ and WRITE rights.
	    contextSource.setUserDn(environment.getRequiredProperty("SRALL_LDAP_ADMIN_DN"));
	    contextSource.setPassword(environment.getRequiredProperty("SRALL_LDAP_ADMIN_PSSWD"));
	    return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
	    return new LdapTemplate(contextSource());        
	}
	
    @Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
