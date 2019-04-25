package gov.wisconsin.timesheet.business;

import gov.wisconsin.framework.base.FwAbstractBO;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.timesheet.data.dao.T052_Timesheet_DAO;
import gov.wisconsin.timesheet.data.pojo.T052_Timesheet_Cargo;
import gov.wisconsin.timesheet.transport.helper.TimesheetSummaryData;
import gov.wisconsin.timesheet.transport.request.SendTimesheetEmailRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.openjpa.jdbc.kernel.exps.Substring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Scope(value = FwConstants.PROTOTYPE_SCOPE)
public class TimesheetBO extends FwAbstractBO {

	private @Autowired JavaMailSender emailSender;
	private @Autowired T052_Timesheet_DAO timesheetDAO;
	
	public List<T052_Timesheet_Cargo> getAllTimesheets(String username) {
		try {
			return timesheetDAO.findAll();
//			return timesheetDAO.findByStaffId(username);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public T052_Timesheet_Cargo createOrUpdateTimesheet(T052_Timesheet_Cargo cargoToCreateOrUpdate) {
		try {
			return timesheetDAO.save(cargoToCreateOrUpdate);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public List<TimesheetSummaryData> getTimesheetSummary(String username) {
		List<TimesheetSummaryData> resultList = new ArrayList<TimesheetSummaryData>();
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:000");
			Date begDate = new Date();
			Calendar cal = Calendar.getInstance();
			int numDays = cal.get(Calendar.DAY_OF_WEEK);
			cal.setTime(begDate);
			if(numDays != 1)
				{
					cal.add(Calendar.DATE, -(numDays+1));
				};
			begDate = sdf.parse(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 6);
			Date endDate = sdf.parse(sdf.format(cal.getTime()));
			System.out.println("Begin Date"+begDate);
			System.out.println("End Date"+endDate);
			List<T052_Timesheet_Cargo> timesheetRecords = timesheetDAO.findAll();
			
			for (T052_Timesheet_Cargo cargo : timesheetRecords) {
				TimesheetSummaryData data = new TimesheetSummaryData();
				data.setStatus("someStatus");
				data.setTotalBillableHours(35);
				data.setTotalNonBillableHours(5);
				data.setTotalOverallHours(cargo.getActual_hours());
				data.setWeekNumber("44");
				resultList.add(data);
			}

			return resultList;
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public String sendTimesheetEmail(SendTimesheetEmailRequest apiRequest) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
		    
			helper.setFrom("noreply@wisconsin.gov");
			helper.setSubject("Timesheet - insertWeekNumberOrSomething"); 
			
			List<String> emails = apiRequest.getEmailAddresses();
			helper.setTo(emails.toArray(new String[emails.size()]));

			/** CSM Can add an attachment (PDF, .txt, etc...) or put text in the email body **/
			//helper.setText("callMethodToBuildTimesheetDataForDisplay");
			//helper.addAttachment("Invoice", file);
			
	        emailSender.send(message);
	        
	        return "Success";
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	
}
