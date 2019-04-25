package gov.wisconsin.admin.email;

import gov.wisconsin.framework.exception.FwExceptionManager;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class EmailTemplates {
	
	private @Autowired JavaMailSender emailSender;
	
	@Bean(name = "testTemplate")
	public MimeMessage getTestTemplate() {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
		    
			helper.setFrom("noreply@wisconsin.gov");
			helper.setText("This is the test email template for your email:\n%s\n");
			helper.setSubject("Some specific error message"); 
			helper.setTo(new String[] { "Charles.McKelvey@dhs.wisconsin.gov", "chmckelvey@deloitte.com"});
			helper.setCc("Charles.McKelvey@dhs.wisconsin.gov");
			return message;
		} catch (Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}

	    return null;
	}
}
