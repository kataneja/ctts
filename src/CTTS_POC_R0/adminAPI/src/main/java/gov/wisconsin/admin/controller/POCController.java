package gov.wisconsin.admin.controller;

import gov.wisconsin.admin.data.dao.T001_Subsystem_DAO;
import gov.wisconsin.admin.data.dao.T003_CustomerArea_DAO;
import gov.wisconsin.admin.data.dao.T024_Category_DAO;
import gov.wisconsin.admin.data.dao.T025_Activity_DAO;
import gov.wisconsin.admin.data.dao.T035_HolidayTime_DAO;
import gov.wisconsin.admin.data.dao.T049_RefData_DAO;
import gov.wisconsin.admin.data.dao.T050_RefTable_DAO;
import gov.wisconsin.admin.data.dao.T051_ExtraHours_DAO;
import gov.wisconsin.admin.data.pojo.T024_Category_Cargo;
import gov.wisconsin.admin.transport.temp.CCDRequest;
import gov.wisconsin.admin.transport.temp.CCDResponse;
import gov.wisconsin.admin.transport.temp.EmailRequest;
import gov.wisconsin.admin.transport.temp.EmailResponse;
import gov.wisconsin.admin.transport.temp.JMSRequest;
import gov.wisconsin.admin.transport.temp.JMSResponse;
import gov.wisconsin.admin.transport.temp.LogRequest;
import gov.wisconsin.admin.transport.temp.LogResponse;
import gov.wisconsin.admin.transport.temp.SQLRequest;
import gov.wisconsin.admin.transport.temp.SQLResponse;
import gov.wisconsin.framework.base.FwAbstractController;
import gov.wisconsin.framework.data.FwCargo;
import gov.wisconsin.framework.data.FwPK;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.exception.FwWrappedException;
import gov.wisconsin.framework.generate.JPAGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poc")
public class POCController extends FwAbstractController {
	
	private @Autowired JavaMailSender emailSender;

	private @Autowired T049_RefData_DAO refDataDAO;
	private @Autowired T050_RefTable_DAO refTableDAO;
	private @Autowired T024_Category_DAO categoryDAO;
	private @Autowired T025_Activity_DAO activityDAO;
	private @Autowired T001_Subsystem_DAO subsystemDAO;
	private @Autowired T051_ExtraHours_DAO extraHoursDAO;
	private @Autowired T035_HolidayTime_DAO holidayTimeDAO;
	private @Autowired T003_CustomerArea_DAO customerAreaDAO;

	private @Resource(name="cttsQueue") JmsTemplate cttsQueue;
	private @Resource(name="auditQueue") JmsTemplate auditQueue;
	
	private @Resource(name="testTemplate") MimeMessage testEmailTemplate;
	
	@PostMapping(value = "email", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailResponse> EMAIL(@RequestBody EmailRequest apiRequest, @ModelAttribute("username") String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		EmailResponse apiResponse = new EmailResponse();
		
		try {
			apiResponse.setStatus(false);
			
			try {
				List<String> toEmails = apiRequest.getToEmails();
				List<String> ccEmails = apiRequest.getCcEmails();
				
				try {
					MimeMessage message = emailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message, true);
				    
					helper.setFrom("noreply@wisconsin.gov");
					helper.setText(apiRequest.getMessage());
					helper.setSubject(apiRequest.getSubject()); 
					helper.setTo(toEmails.toArray(new String[toEmails.size()]));
					helper.setCc(ccEmails.toArray(new String[ccEmails.size()]));
					//helper.addAttachment("Invoice", file);
					
			        emailSender.send(message);
			        apiResponse.setRegularMessage("Success");
				} catch (Exception e) {
					apiResponse.setRegularMessage(e.getMessage());
				}
				
				try {
					testEmailTemplate.setText(String.format(testEmailTemplate.getContent().toString(), "<INSERTED>"));
			        emailSender.send(testEmailTemplate);
			        apiResponse.setTemplateMessage("Success");
				} catch (Exception e) {
					apiResponse.setTemplateMessage(e.getMessage());
				}
				
				apiResponse.setStatus(true);
			} catch (Exception e) {
				FwExceptionManager.handleException(this.getClass(), e);
			}
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}

		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PostMapping(value = "log", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogResponse> LOG(@RequestBody LogRequest apiRequest, @ModelAttribute("username") String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		LogResponse apiResponse = new LogResponse();
		
		try {
			apiResponse.setStatus(false);
			
			String messageToLog = apiRequest.getLogMessage();
			apiLogger.info(messageToLog);
			activityLogger.info(messageToLog);

			apiResponse.setMessage("Success");
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}

		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PostMapping(value = "sql", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SQLResponse> SQL(@RequestBody SQLRequest apiRequest, @ModelAttribute("username") String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		SQLResponse apiResponse = new SQLResponse();
		
		try {
			apiResponse.setStatus(false);
			
			try {
				if (apiRequest.getCategory().equals("category0")) {
					throw new Exception("A test exception");
				}
				
				List<T024_Category_Cargo> results = categoryDAO.findCatgoryByProjectAndCategory(apiRequest.getProjectName(), apiRequest.getCategory());
				apiResponse.setResultList(results);
				apiResponse.setStatus(true);
			} catch (Exception e) {
				FwExceptionManager.handleException(this.getClass(), e);
			}
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}

		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PostMapping(value = "jms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JMSResponse> JMS(@RequestBody JMSRequest apiRequest, @ModelAttribute("username") String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		JMSResponse apiResponse = new JMSResponse();
		
		try {
			apiResponse.setStatus(false);
			
			try {
				cttsQueue.convertAndSend(apiRequest.getCttsMessage());
				apiResponse.setCttsResponse("Success");
			} catch (Exception e) {
				apiResponse.setCttsResponse(e.getCause().toString());
			}
			
			try {
				auditQueue.convertAndSend(apiRequest.getAuditMessage());
				apiResponse.setAuditResponse("Success");
			} catch (Exception e) {
				apiResponse.setAuditResponse(e.getCause().toString());
			}

			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}

		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PostMapping(value = "ccd", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CCDResponse> CCD(@RequestBody CCDRequest apiRequest, @ModelAttribute("username") String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		CCDResponse apiResponse = new CCDResponse();
		
		try {
			apiResponse.setStatus(false);
			JPAGenerator.generate(apiRequest.getProject(), apiRequest.getTable());
			apiResponse.setMessage("Successfully generated CCDS for " + apiRequest.getTable());
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@GetMapping(value = "jpa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> JPA(@ModelAttribute("username") String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		try {
			@SuppressWarnings("unchecked")
			JpaRepository<FwCargo, FwPK>[] daoList = new JpaRepository[] { categoryDAO };
			//JpaRepository<FwCargo, FwPK>[] daoList = new JpaRepository[] { categoryDAO, subsystemDAO, refDataDAO, customerAreaDAO, activityDAO, holidayTimeDAO, extraHoursDAO, refTableDAO };
			
			for (int i = 0; i < daoList.length; i++) {
				JpaRepository<FwCargo, FwPK> currentDAO = daoList[i];
				String interfaceName = ((Advised) currentDAO).getProxiedInterfaces()[0].getCanonicalName();
				interfaceName = interfaceName.substring(interfaceName.lastIndexOf(".") + 1, interfaceName.length());
				
				try {
					responseMap.put(interfaceName, currentDAO.findAll());
				} catch (Exception e) {
					responseMap.put(interfaceName, e.getCause().toString());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			responseMap.put("error", e.getCause().toString());
		}

		return ResponseEntity.status(200).body(responseMap);
	}
}