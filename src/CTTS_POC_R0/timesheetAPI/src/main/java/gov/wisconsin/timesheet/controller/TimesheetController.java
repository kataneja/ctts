package gov.wisconsin.timesheet.controller;

import gov.wisconsin.framework.base.FwAbstractController;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwWrappedException;
import gov.wisconsin.timesheet.business.TimesheetBO;
import gov.wisconsin.timesheet.data.pojo.T052_Timesheet_Cargo;
import gov.wisconsin.timesheet.transport.request.SendTimesheetEmailRequest;
import gov.wisconsin.timesheet.transport.response.CreateTimesheetResponse;
import gov.wisconsin.timesheet.transport.response.GetAllTimesheetsResponse;
import gov.wisconsin.timesheet.transport.response.SendTimesheetEmailResponse;
import gov.wisconsin.timesheet.transport.response.TimesheetSummaryResponse;
import gov.wisconsin.timesheet.transport.response.UpdateTimesheetResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FwConstants.EMPTY_STRING)
public class TimesheetController extends FwAbstractController {

	@GetMapping(value = "/timesheet", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetAllTimesheetsResponse> getAllTimesheets(@ModelAttribute String authenticatedUsername, HttpServletRequest httpRequest, HttpSession httpSession) {
		GetAllTimesheetsResponse apiResponse = new GetAllTimesheetsResponse();
		
		try {
			apiResponse.setStatus(false);

			TimesheetBO timesheetBO = applicationContext.getBean(TimesheetBO.class);
			apiResponse.setResultList(timesheetBO.getAllTimesheets(authenticatedUsername));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PostMapping(value = "/timesheet", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateTimesheetResponse> createTimesheet(@RequestBody T052_Timesheet_Cargo apiRequest, @ModelAttribute String authenticatedUsername, HttpServletRequest httpRequest, HttpSession httpSession) {
		CreateTimesheetResponse apiResponse = new CreateTimesheetResponse();
		
		try {
			apiResponse.setStatus(false);
			
			TimesheetBO timesheetBO = applicationContext.getBean(TimesheetBO.class);
			apiResponse.setCreatedCargo(timesheetBO.createOrUpdateTimesheet(apiRequest));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PutMapping(value = "/timesheet", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UpdateTimesheetResponse> updateCategory(@RequestBody T052_Timesheet_Cargo apiRequest, @ModelAttribute String authenticatedUsername, HttpServletRequest httpRequest, HttpSession httpSession) {
		UpdateTimesheetResponse apiResponse = new UpdateTimesheetResponse();
		
		try {
			apiResponse.setStatus(false);

			TimesheetBO timesheetBO = applicationContext.getBean(TimesheetBO.class);
			apiResponse.setUpdatedCargo(timesheetBO.createOrUpdateTimesheet(apiRequest));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@GetMapping(value = "/timesheet/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetAllTimesheetsResponse> getAllTimesheetsForUser(@PathVariable("userId") String userId, @ModelAttribute String authenticatedUsername, HttpServletRequest httpRequest, HttpSession httpSession) {
		GetAllTimesheetsResponse apiResponse = new GetAllTimesheetsResponse();
		
		try {
			apiResponse.setStatus(false);

			TimesheetBO timesheetBO = applicationContext.getBean(TimesheetBO.class);
			apiResponse.setResultList(timesheetBO.getAllTimesheets(userId));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PostMapping(value = "/timesheet/email", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendTimesheetEmailResponse> sendTimesheetEmail(@RequestBody SendTimesheetEmailRequest apiRequest, @ModelAttribute String authenticatedUsername, HttpServletRequest httpRequest, HttpSession httpSession) {
		SendTimesheetEmailResponse apiResponse = new SendTimesheetEmailResponse();
		
		try {
			apiResponse.setStatus(false);
			
			TimesheetBO timesheetBO = applicationContext.getBean(TimesheetBO.class);
			apiResponse.setMessage(timesheetBO.sendTimesheetEmail(apiRequest));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	/** CSM
	For use on the home page to get a summary of a user's timesheets
	Need to determine how to find timesheets for a particular user - by username, WID, etc...
	Need to determine which tables holds which information to determine what DAOs I need
	**/
	@GetMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TimesheetSummaryResponse> getTimesheetSummary(@ModelAttribute String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		TimesheetSummaryResponse apiResponse = new TimesheetSummaryResponse();		
		
		try {
			apiResponse.setStatus(false);
			
			TimesheetBO timesheetBO = applicationContext.getBean(TimesheetBO.class);
			apiResponse.setResultList(timesheetBO.getTimesheetSummary(username));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}

}