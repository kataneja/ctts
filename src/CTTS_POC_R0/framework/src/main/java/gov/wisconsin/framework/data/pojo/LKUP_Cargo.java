package gov.wisconsin.framework.data.pojo;

import gov.wisconsin.framework.data.base.FwAbstractCargo;
import gov.wisconsin.framework.data.base.ICargo;
import gov.wisconsin.framework.data.base.IPrimaryKey;

import java.io.Serializable;

public class LKUP_Cargo extends FwAbstractCargo implements Serializable, ICargo {
	private static final long serialVersionUID = 5306203411453930782L;

	private static final String PACKAGE = "gov.wisconsin.framework.data.impl.LKUP";

	boolean isDirty = false;
	private String lkup_grp_fld_id;
	private String lkup_cd;
	private String cd_actv_flg;
	private String lkup_dsc;
	private String sort_ord;
	private String updt_dt;

	public String getPackage() {
		return PACKAGE;
	}

	public IPrimaryKey getPrimaryKey(){
		LKUP_PrimaryKey key = new LKUP_PrimaryKey();
		key.setLkup_grp_fld_id(this.getLkup_grp_fld_id());
		key.setLkup_cd(this.getLkup_cd());
		return key;
	}

	public String inspectCargo(){
		return "LKUP: " + "lkup_grp_fld_id=" + lkup_grp_fld_id + "," + "lkup_cd=" + lkup_cd + "," + "cd_actv_flg=" + cd_actv_flg + "," + "lkup_dsc=" + lkup_dsc + "," + "sort_ord=" + sort_ord + "," + "updt_dt=" + updt_dt;
	}

	public int hashCode(){
		int hash = 7;
		hash = 31 * hash + (null == lkup_grp_fld_id ? 0 : lkup_grp_fld_id.hashCode());
		hash = 31 * hash + (null == lkup_cd ? 0 : lkup_cd.hashCode());
		hash = 31 * hash + (null == cd_actv_flg ? 0 : cd_actv_flg.hashCode());
		hash = 31 * hash + (null == lkup_dsc ? 0 : lkup_dsc.hashCode());
		hash = 31 * hash + (null == sort_ord ? 0 : sort_ord.hashCode());
		hash = 31 * hash + (null == updt_dt ? 0 : updt_dt.hashCode());
		return hash;
	}
	
	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public String getLkup_grp_fld_id() {
		return lkup_grp_fld_id;
	}

	public void setLkup_grp_fld_id(String lkup_grp_fld_id) {
		this.lkup_grp_fld_id = lkup_grp_fld_id;
	}

	public String getLkup_cd() {
		return lkup_cd;
	}

	public void setLkup_cd(String lkup_cd) {
		this.lkup_cd = lkup_cd;
	}

	public String getCd_actv_flg() {
		return cd_actv_flg;
	}

	public void setCd_actv_flg(String cd_actv_flg) {
		this.cd_actv_flg = cd_actv_flg;
	}

	public String getLkup_dsc() {
		return lkup_dsc;
	}

	public void setLkup_dsc(String lkup_dsc) {
		this.lkup_dsc = lkup_dsc;
	}

	public String getSort_ord() {
		return sort_ord;
	}

	public void setSort_ord(String sort_ord) {
		this.sort_ord = sort_ord;
	}

	public String getUpdt_dt() {
		return updt_dt;
	}

	public void setUpdt_dt(String updt_dt) {
		this.updt_dt = updt_dt;
	}
}