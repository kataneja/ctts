package gov.wisconsin.timeoff.controller;

import gov.wisconsin.framework.base.FwAbstractController;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwWrappedException;
import gov.wisconsin.timeoff.business.TimeoffBO;
import gov.wisconsin.timeoff.transport.helper.TimeoffSummaryData;
import gov.wisconsin.timeoff.transport.response.TimeoffSummaryResponse;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FwConstants.EMPTY_STRING)
public class TimeoffController extends FwAbstractController {
	
	@GetMapping(value = "/timeoff", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetAllTimeoffsResponse> getAllTimeoffs(@ModelAttribute String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		GetAllTimeoffsResponse apiResponse = new GetAllTimeoffsResponse();
		
		try {
			apiResponse.setStatus(false);

			TimeoffBO timeoffBO = applicationContext.getBean(TimeoffBO.class);
			apiResponse.setResultList(timeoffBO.getAllTimeoffs(username));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PostMapping(value = "/timeoff", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateTimeoffResponse> createTimeoff(@RequestBody T052_Timesheet_Cargo apiRequest, @ModelAttribute String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		CreateTimeoffResponse apiResponse = new CreateTimeoffResponse();
		
		try {
			apiResponse.setStatus(false);
			
			TimeoffBO timeoffBO = applicationContext.getBean(TimeoffBO.class);
			apiResponse.setCreatedCargo(timeoffBO.createOrUpdateTimeoff(apiRequest));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	@PutMapping(value = "/timeoff", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UpdateTimeoffResponse> updateTimeoff(@RequestBody T052_Timesheet_Cargo apiRequest, @ModelAttribute String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		UpdateTimeoffResponse apiResponse = new UpdateTimeoffResponse();
		
		try {
			apiResponse.setStatus(false);

			TimeoffBO timeoffBO = applicationContext.getBean(TimeoffBO.class);
			apiResponse.setUpdatedCargo(timeoffBO.createOrUpdateTimeoff(apiRequest));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
	
	/** CSM
	For use on the home page to get a summary of a user's timeoff requests
	Need to determine how to find timeoff requests for a particular user - by username, WID, etc...
	Need to determine which tables holds which information to determine what DAOs I need
	**/
	@GetMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TimeoffSummaryResponse> getTimeoffSummary(@ModelAttribute String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		TimeoffSummaryResponse apiResponse = new TimeoffSummaryResponse();		
		
		try {
			apiResponse.setStatus(false);
			
			TimeoffBO timeoffBO = applicationContext.getBean(TimeoffBO.class);
			apiResponse.setResultList(timeoffBO.getTimeoffSummary(username));
			
			apiResponse.setStatus(true);
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(apiResponse);
	}
}