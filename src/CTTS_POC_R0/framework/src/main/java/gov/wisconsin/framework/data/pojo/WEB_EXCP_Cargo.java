package gov.wisconsin.framework.data.pojo;

import gov.wisconsin.framework.data.base.FwAbstractCargo;
import gov.wisconsin.framework.data.base.IPrimaryKey;

import java.io.Serializable;
import java.sql.Timestamp;

public class WEB_EXCP_Cargo extends FwAbstractCargo implements Serializable {
	private static final long serialVersionUID = -7339735506253956383L;

	private static final String PACKAGE = "gov.wisconsin.framework.data.impl.WEB_EXCP_DAO";

	boolean isDirty = false;

	private int excp_id;
	private int excp_typ;
	private char full_excp_sw;
	private Timestamp excp_tms;
	private String call_cls_id;
	private String call_mthd_id;
	private String cls_id;
	private String cur_page_id;
	private String ip_adr;
	private String msg_id;
	private String mthd_id;
	private String prev_page_id;
	private String srvc_mthd_typ;
	private String srvc_nam;
	private String user_id;
	private String wams_logon_id;
	private String excp_txt;
	private String srvc_dsc;
	private String srvc_msg_txt;
	private String srvr_nam;
	private String stak_trc_txt;
	private String prim_key_txt;
	private String parm_txt;

	public String getPackage() {
		return PACKAGE;
	}

	public IPrimaryKey getPrimaryKey() {
		WEB_EXCP_PrimaryKey key = new WEB_EXCP_PrimaryKey();
		key.setExcp_id(this.getExcp_id());
		return key;
	}

	public String inspectCargo() {
		return "WEB_EXCP: " + "excp_id=" + excp_id + "," + "call_cls_id=" + call_cls_id + "," + "call_mthd_id=" + call_mthd_id + "," + "cls_id=" + cls_id + "," + "cur_page_id=" + cur_page_id + "," + "excp_tms=" + excp_tms + "," + "excp_typ=" + excp_typ + "," + "full_excp_sw=" + full_excp_sw + "," + "ip_adr=" + ip_adr + "," + "msg_id=" + msg_id + "," + "mthd_id=" + mthd_id + "," + "prev_page_id=" + prev_page_id + "," + "srvc_mthd_typ=" + srvc_mthd_typ + "," + "srvc_nam=" + srvc_nam + "," + "user_id=" + user_id + "," + "wams_logon_id=" + wams_logon_id + "," + "excp_txt=" + excp_txt + "," + "srvc_dsc=" + srvc_dsc + "," + "srvc_msg_txt=" + srvc_msg_txt + "," + "srvr_nam=" + srvr_nam + "," + "stak_trc_txt=" + stak_trc_txt + "," + "prim_key_txt=" + prim_key_txt + "," + "parm_txt=" + parm_txt;
	}

	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (int) excp_id;
		hash = 31 * hash + (null == call_cls_id ? 0 : call_cls_id.trim().hashCode());
		hash = 31 * hash + (null == call_mthd_id ? 0 : call_mthd_id.trim().hashCode());
		hash = 31 * hash + (null == cls_id ? 0 : cls_id.trim().hashCode());
		hash = 31 * hash + (null == cur_page_id ? 0 : cur_page_id.trim().hashCode());
		hash = 31 * hash + (null == excp_tms ? 0 : excp_tms.hashCode());
		hash = 31 * hash + (int) excp_typ;
		hash = 31 * hash + (int) full_excp_sw;
		hash = 31 * hash + (null == ip_adr ? 0 : ip_adr.trim().hashCode());
		hash = 31 * hash + (null == msg_id ? 0 : msg_id.trim().hashCode());
		hash = 31 * hash + (null == mthd_id ? 0 : mthd_id.trim().hashCode());
		hash = 31 * hash + (null == prev_page_id ? 0 : prev_page_id.trim().hashCode());
		hash = 31 * hash + (null == srvc_mthd_typ ? 0 : srvc_mthd_typ.trim().hashCode());
		hash = 31 * hash + (null == srvc_nam ? 0 : srvc_nam.trim().hashCode());
		hash = 31 * hash + (null == user_id ? 0 : user_id.trim().hashCode());
		hash = 31 * hash + (null == wams_logon_id ? 0 : wams_logon_id.trim().hashCode());
		hash = 31 * hash + (null == excp_txt ? 0 : excp_txt.trim().hashCode());
		hash = 31 * hash + (null == srvc_dsc ? 0 : srvc_dsc.trim().hashCode());
		hash = 31 * hash + (null == srvc_msg_txt ? 0 : srvc_msg_txt.trim().hashCode());
		hash = 31 * hash + (null == srvr_nam ? 0 : srvr_nam.trim().hashCode());
		hash = 31 * hash + (null == stak_trc_txt ? 0 : stak_trc_txt.trim().hashCode());
		hash = 31 * hash + (null == prim_key_txt ? 0 : prim_key_txt.trim().hashCode());
		hash = 31 * hash + (null == parm_txt ? 0 : parm_txt.trim().hashCode());
		return hash;
	}
	
	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public int getExcp_id() {
		return excp_id;
	}

	public void setExcp_id(int excp_id) {
		this.excp_id = excp_id;
	}

	public Timestamp getExcp_tms() {
		return excp_tms;
	}

	public void setExcp_tms(Timestamp excp_tms) {
		this.excp_tms = excp_tms;
	}

	public int getExcp_typ() {
		return excp_typ;
	}

	public void setExcp_typ(int excp_typ) {
		this.excp_typ = excp_typ;
	}

	public char getFull_excp_sw() {
		return full_excp_sw;
	}

	public void setFull_excp_sw(char full_excp_sw) {
		this.full_excp_sw = full_excp_sw;
	}

	public String getCall_cls_id() {
		return call_cls_id;
	}

	public void setCall_cls_id(String call_cls_id) {
		this.call_cls_id = call_cls_id;
	}

	public String getCall_mthd_id() {
		return call_mthd_id;
	}

	public void setCall_mthd_id(String call_mthd_id) {
		this.call_mthd_id = call_mthd_id;
	}

	public String getCls_id() {
		return cls_id;
	}

	public void setCls_id(String cls_id) {
		this.cls_id = cls_id;
	}

	public String getCur_page_id() {
		return cur_page_id;
	}

	public void setCur_page_id(String cur_page_id) {
		this.cur_page_id = cur_page_id;
	}

	public String getIp_adr() {
		return ip_adr;
	}

	public void setIp_adr(String ip_adr) {
		this.ip_adr = ip_adr;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMthd_id() {
		return mthd_id;
	}

	public void setMthd_id(String mthd_id) {
		this.mthd_id = mthd_id;
	}

	public String getPrev_page_id() {
		return prev_page_id;
	}

	public void setPrev_page_id(String prev_page_id) {
		this.prev_page_id = prev_page_id;
	}

	public String getSrvc_mthd_typ() {
		return srvc_mthd_typ;
	}

	public void setSrvc_mthd_typ(String srvc_mthd_typ) {
		this.srvc_mthd_typ = srvc_mthd_typ;
	}

	public String getSrvc_nam() {
		return srvc_nam;
	}

	public void setSrvc_nam(String srvc_nam) {
		this.srvc_nam = srvc_nam;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getWams_logon_id() {
		return wams_logon_id;
	}

	public void setWams_logon_id(String wams_logon_id) {
		this.wams_logon_id = wams_logon_id;
	}

	public String getExcp_txt() {
		return excp_txt;
	}

	public void setExcp_txt(String excp_txt) {
		this.excp_txt = excp_txt;
	}

	public String getSrvc_dsc() {
		return srvc_dsc;
	}

	public void setSrvc_dsc(String srvc_dsc) {
		this.srvc_dsc = srvc_dsc;
	}

	public String getSrvc_msg_txt() {
		return srvc_msg_txt;
	}

	public void setSrvc_msg_txt(String srvc_msg_txt) {
		this.srvc_msg_txt = srvc_msg_txt;
	}

	public String getSrvr_nam() {
		return srvr_nam;
	}

	public void setSrvr_nam(String srvr_nam) {
		this.srvr_nam = srvr_nam;
	}

	public String getStak_trc_txt() {
		return stak_trc_txt;
	}

	public void setStak_trc_txt(String stak_trc_txt) {
		this.stak_trc_txt = stak_trc_txt;
	}

	public String getPrim_key_txt() {
		return prim_key_txt;
	}

	public void setPrim_key_txt(String prim_key_txt) {
		this.prim_key_txt = prim_key_txt;
	}

	public String getParm_txt() {
		return parm_txt;
	}

	public void setParm_txt(String parm_txt) {
		this.parm_txt = parm_txt;
	}

}