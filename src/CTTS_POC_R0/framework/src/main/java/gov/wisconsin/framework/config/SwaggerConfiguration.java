package gov.wisconsin.framework.config;

import gov.wisconsin.framework.constant.FwConstants;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({ "dev", "int", "sys", "uat", "xte" })
public class SwaggerConfiguration {

	@Value(FwConstants.SWAGGER_HOST_INJECT)
	private String host;
	
	@Value(FwConstants.SWAGGER_PROTOCOL_INJECT)
	private String protocol;
	
	@Value(FwConstants.SWAGGER_TITLE_INJECT)
	private String title;
	
	@Value(FwConstants.SWAGGER_CONTROLLER_INJECT)
	private String controllerPackage;
	
	@Bean
    public Docket api() {
		Set<String> protocols = new HashSet<String>();
		protocols.add(protocol);
		
		//TODO Get the following from property file since this class is shared between applications:
		//	1. Title
		//	2. Description
		//	3. Controller package
		ApiInfo info = new ApiInfo(
			title, 												// Title
			"Wisconsin CARES CTTS Application", 				// Description
			"1.0", 												// Version
			"", 												// Terms of Service URL
			new Contact("the Wisconsin CARES Project", "", ""),	// Contact
			"",  												// License
			""													// License URL
		);
		
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          
          //The below package name allows us to hide the default Spring ErrorController
          .apis(RequestHandlerSelectors.basePackage(controllerPackage))
          
          //The below regex allows us to hide all endpoints that end with '/test'
          //.paths(PathSelectors.regex("^((?!/test).)*$"))
          
          .build()
          .apiInfo(info)
          .protocols(protocols)
          .host(host);
    }

}