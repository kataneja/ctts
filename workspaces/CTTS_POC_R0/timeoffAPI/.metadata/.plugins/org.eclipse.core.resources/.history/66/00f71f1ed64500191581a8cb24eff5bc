package gov.wisconsin.framework.config;

import gov.wisconsin.framework.impl.FwRequestFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FwRequestFilterConfiguration {

    public FilterRegistrationBean frameworkFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("framework");
        FwRequestFilter frameworkRequestFilter = new FwRequestFilter();
        registrationBean.setFilter(frameworkRequestFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}