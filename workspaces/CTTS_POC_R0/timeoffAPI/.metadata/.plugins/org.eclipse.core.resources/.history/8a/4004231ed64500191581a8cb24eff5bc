package gov.wisconsin.framework.config;

import gov.wisconsin.framework.impl.FwRequestInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FwRequestInterceptorConfiguration {

	@Bean
	public WebMvcConfigurerAdapter adapter() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addInterceptors(InterceptorRegistry registry) {
	            registry.addInterceptor(new FwRequestInterceptor()).addPathPatterns("/**");
	            super.addInterceptors(registry);
	        }
	    };
	}
}