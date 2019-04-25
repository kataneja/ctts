package gov.wisconsin.timeoff.business;

import gov.wisconsin.framework.base.FwAbstractBO;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.timeoff.transport.helper.TimeoffSummaryData;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = FwConstants.PROTOTYPE_SCOPE)
public class TimeoffBO extends FwAbstractBO {

	private @Autowired TXXX_Timeoff_DAO timeoffDAO;
	
	public List<TXXX_Timeoff_Cargo> getAllTimeoffs(String username) {
		try {
			return timeoffDAO.findAll();
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public TXXX_Timeoff_Cargo createOrUpdateTimeoff(TXXX_Timeoff_Cargo cargoToCreateOrUpdate) {
		try {
			return timeoffDAO.save(cargoToCreateOrUpdate);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
	
	public List<TimeoffSummaryData> getTimeoffSummary(String username) {
		List<TimeoffSummaryData> resultList = new ArrayList<TimeoffSummaryData>();
		
		try {
			List<TXXX_Timeoff_Cargo> timeoffRecords = timeoffDAO.findAll();
			
			for (TXXX_Timeoff_Cargo cargo : timeoffRecords) {
				TimeoffSummaryData data = new TimeoffSummaryData();
				data.setStatus("someStatus");
				data.setFirstDayOff("01/01/2019");
				data.setLastDayOff("01/05/2019");
				data.setNumberOfWorkingDays(5);
				cargo.getHoliday_hr();
				resultList.add(data);
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e);
		}
		
		return null;
	}
}

