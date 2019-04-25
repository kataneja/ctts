package gov.wisconsin.framework.data.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractCargo;
import gov.wisconsin.framework.data.base.FwAbstractCollection;

@Component
@Scope(value = FwConstants.PROTOTYPE_SCOPE)
public class WEB_EXCP_Collection extends FwAbstractCollection {
	private static final long serialVersionUID = -508942292587980112L;
	
	private static final String PACKAGE = "gov.wisconsin.framework.data.impl.WEB_EXCP_DAO";

	public void addCargo(WEB_EXCP_Cargo newCargo) {
		add(newCargo); 
	}

	public String getPACKAGE() {
		return PACKAGE;
	}

	public WEB_EXCP_Cargo getCargo() {
		if (size() == 0) {
			add(new WEB_EXCP_Cargo());
		}
		return (WEB_EXCP_Cargo) get(0);
	}

	public void setCargo(WEB_EXCP_Cargo newCargo) {
		if (size() == 0) { add(newCargo); }
		else { set(0, newCargo); }
	}

	public FwAbstractCargo getAbstractCargo() {
		return getCargo();
	}

	public void setAbstractCargo(FwAbstractCargo cargo) {
		setCargo((WEB_EXCP_Cargo) cargo);
	}

	public void setResults(WEB_EXCP_Cargo[]cbArray) {
		clear();
		for (int i = 0; i < cbArray.length; i++) {
			add(cbArray[i]);
		}
	}

	public void setResults(int idx, WEB_EXCP_Cargo cb) {
		set(idx, cb);
	}

	public void setGenericResults(Object obj) {
		if (obj instanceof WEB_EXCP_Cargo[]) {
			WEB_EXCP_Cargo[] cbArray = (WEB_EXCP_Cargo[]) obj;
			for (int i = 0; i < cbArray.length; i++) {
				add(cbArray[i]);
			}
		}
	}

	public WEB_EXCP_Cargo[] getResults() {
		WEB_EXCP_Cargo[] cbArray = new WEB_EXCP_Cargo[size()];
		toArray(cbArray);
		return cbArray;
	}

	public WEB_EXCP_Cargo getResult(int idx) {
		return (WEB_EXCP_Cargo) get(idx);
	}

	public int getResultsSize() {
		return size();
	}

	public WEB_EXCP_Cargo[] cloneResults(){
		WEB_EXCP_Cargo[] rescargo = new WEB_EXCP_Cargo[size()];
		for(int i = 0; i < size(); i++) {
			WEB_EXCP_Cargo cargo = getResult(i);
			rescargo[i] = new WEB_EXCP_Cargo();
			rescargo[i].setExcp_id(cargo.getExcp_id());
			rescargo[i].setCall_cls_id(cargo.getCall_cls_id());
			rescargo[i].setCall_mthd_id(cargo.getCall_mthd_id());
			rescargo[i].setCls_id(cargo.getCls_id());
			rescargo[i].setCur_page_id(cargo.getCur_page_id());
			rescargo[i].setExcp_tms(cargo.getExcp_tms());
			rescargo[i].setExcp_typ(cargo.getExcp_typ());
			rescargo[i].setFull_excp_sw(cargo.getFull_excp_sw());
			rescargo[i].setIp_adr(cargo.getIp_adr());
			rescargo[i].setMsg_id(cargo.getMsg_id());
			rescargo[i].setMthd_id(cargo.getMthd_id());
			rescargo[i].setPrev_page_id(cargo.getPrev_page_id());
			rescargo[i].setSrvc_mthd_typ(cargo.getSrvc_mthd_typ());
			rescargo[i].setSrvc_nam(cargo.getSrvc_nam());
			rescargo[i].setUser_id(cargo.getUser_id());
			rescargo[i].setWams_logon_id(cargo.getWams_logon_id());
			rescargo[i].setExcp_txt(cargo.getExcp_txt());
			rescargo[i].setSrvc_dsc(cargo.getSrvc_dsc());
			rescargo[i].setSrvc_msg_txt(cargo.getSrvc_msg_txt());
			rescargo[i].setSrvr_nam(cargo.getSrvr_nam());
			rescargo[i].setStak_trc_txt(cargo.getStak_trc_txt());
			rescargo[i].setPrim_key_txt(cargo.getPrim_key_txt());
			rescargo[i].setParm_txt(cargo.getParm_txt());
			rescargo[i].setRowAction(cargo.getRowAction());
			rescargo[i].setUser(cargo.getUser());
			rescargo[i].setDirty(cargo.isDirty());
		}
		return rescargo;
	}
}