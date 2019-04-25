package gov.wisconsin.framework.data.pojo;

import gov.wisconsin.framework.data.base.FwAbstractCargo;
import gov.wisconsin.framework.data.base.IPrimaryKey;

import java.io.Serializable;

public class LKUP_GRP_Cargo extends FwAbstractCargo implements Serializable {
	private static final long serialVersionUID = -1918817408083087230L;

	private static final String PACKAGE = "gov.wisconsin.framework.data.impl.LKUP_GRP";

	boolean isDirty = false;
	private String lkup_grp_cd;
	private String lkup_grp_dsc;

	public String getPackage() {
		return PACKAGE;
	}

	public IPrimaryKey getPrimaryKey() {
		LKUP_GRP_PrimaryKey key = new LKUP_GRP_PrimaryKey();
		key.setLkup_grp_cd(this.getLkup_grp_cd());
		return key;
	}

	public String inspectCargo() {
		return "LKUP_GRP: " + "lkup_grp_cd=" + lkup_grp_cd + "," + "lkup_grp_dsc=" + lkup_grp_dsc;
	}

	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (null == lkup_grp_cd ? 0 : lkup_grp_cd.hashCode());
		hash = 31 * hash + (null == lkup_grp_dsc ? 0 : lkup_grp_dsc.hashCode());
		return hash;
	}

	public String getLkup_grp_cd() {
		return lkup_grp_cd;
	}

	public void setLkup_grp_cd(String lkup_grp_cd) {
		this.lkup_grp_cd = lkup_grp_cd;
	}

	public String getLkup_grp_dsc() {
		return lkup_grp_dsc;
	}

	public void setLkup_grp_dsc(String lkup_grp_dsc){
		this.lkup_grp_dsc= lkup_grp_dsc;
	}
}