package gov.wisconsin.framework.security.core;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.pojo.FwDataCriteria;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwExceptionManager;
import gov.wisconsin.framework.security.cargo.UserLogin_Cargo;
import gov.wisconsin.framework.util.WiUid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FwSecurityBO extends FwBaseClass {
	
	public String getNewWiUid() {	
		String resultStr = FwConstants.EMPTY_STRING;
		
		try {
			String newWid = WiUid.getNewWiUid();
			String nextCntlNum = getNextCntlNum("WI");
			resultStr = newWid.substring(0, newWid.length()-2) + nextCntlNum.substring(nextCntlNum.length()-2);
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY, "Error: Failed to get a new WID number");
		}
		
		return resultStr;
	}
	
	public UserLogin_Cargo getUserIDforWIDfromAPP(String wid) {
		UserLogin_Cargo userLoginCargo = null;		
		
		try {	
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(FwConstants.SQL_IND, "sql-APS1");
			FwDataCriteria[] criteria = new FwDataCriteria[1];
			criteria[0] = new FwDataCriteria();
			criteria[0].setColumn_value(wid);
			criteria[0].setData_type(FwConstants.LONG);
			dataMap.put(FwConstants.CRITERIA, criteria);
			List<Map<String, Object>> results = dataManager.execute(FwConstants.SQL, dataMap);
			int size = results.size();
			if(size > 0) {
				userLoginCargo = new UserLogin_Cargo();
				for (int i = 0; i < size; i++) {
					Map<String, Object> resultMap = results.get(i);
					userLoginCargo.setUserId((String) resultMap.get("USER_ID"));
					userLoginCargo.setWamsLognId((String) resultMap.get("WAMS_LOGN_ID"));
					String fName = (String) resultMap.get("FST_NAM");
					String lName = (String) resultMap.get("LAST_NAM");
					String midInit = (String) resultMap.get("MID_INIT");
					
					if(fName != null) {
						userLoginCargo.setUserFirstName(fName.trim());
					}
					
					if(fName != null && lName != null) {
						StringBuffer sb = new StringBuffer();
						if(midInit != null) {
							midInit = midInit.trim().length() > 0 ? midInit + FwConstants.SPACE : FwConstants.EMPTY_STRING;
						}
						else {
							midInit = FwConstants.EMPTY_STRING;
						}
						sb.append(fName.trim()).append(FwConstants.SPACE).append((midInit)).append(lName.trim());
						userLoginCargo.setSesnWkrShortNam(sb.toString());
					}
					
					userLoginCargo.setUserType((String)resultMap.get("USER_TYP"));
				}
			}
		} catch(Exception e) {
			FwException fe = new FwException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY, "Error: Failed to get Profile ID's from WISA");
			fe.setMessageCode("GL110");
			FwExceptionManager.handleException(fe);
		}
		
		return userLoginCargo;
	}
	
	public String getNextCntlNum(String cntlCd) {
		String nextCntlNum = null; 	
		
		try {
			Map<String, Object> sqlMap = new HashMap<String, Object>();

			sqlMap.put(FwConstants.SQL_IND, "sql-36");
			FwDataCriteria[] criteria = new FwDataCriteria[1];
			criteria[0] = new FwDataCriteria();
			criteria[0].setColumn_name("CNTL_CD");
			criteria[0].setColumn_value(cntlCd);
			criteria[0].setData_type(FwConstants.STRING);
			sqlMap.put(FwConstants.CRITERIA, criteria);
			dataManager.execute(FwConstants.SQL, sqlMap);
			
			sqlMap = new HashMap<String, Object>();
			sqlMap.put(FwConstants.SQL_IND, "sql-1");
			criteria = new FwDataCriteria[1];
			criteria[0] = new FwDataCriteria();
			criteria[0].setColumn_name("CNTL_CD");
			criteria[0].setColumn_value(cntlCd);
			criteria[0].setData_type(FwConstants.STRING);
			sqlMap.put(FwConstants.CRITERIA, criteria);
			List<Map<String, Object>> res =  dataManager.execute(FwConstants.SQL, sqlMap);
		
			if (res.size() > 0) {
				Map<String, Object> temp = res.get(0);
				nextCntlNum = (String) temp.get("NEXT_CNTL_NUM");
			}
		} catch(Exception e) {
			FwExceptionManager.handleException(this.getClass(), e, FwConstants.EXP_TYP_SECURITY, "Error: Failed to get a new Control Number");
		}
		
		return nextCntlNum;
	}
}
