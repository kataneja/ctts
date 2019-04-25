package gov.wisconsin.framework.data.pojo;

import gov.wisconsin.framework.data.base.IPrimaryKey;

import java.io.Serializable;

public class LKUP_PrimaryKey implements Serializable, IPrimaryKey {
	private static final long serialVersionUID = 4319190485735517344L;

	private String lkup_cd;
	private String lkup_grp_fld_id;
	
	public LKUP_PrimaryKey() {}

	public String getLkup_cd() {
		return lkup_cd;
	}

	public void setLkup_cd(String lkup_cd) {
		this.lkup_cd = lkup_cd;
	}

	public String getLkup_grp_fld_id() {
		return lkup_grp_fld_id;
	}

	public void setLkup_grp_fld_id(String lkup_grp_fld_id) {
		this.lkup_grp_fld_id = lkup_grp_fld_id;
	}

	public String inspectPrimaryKey(){
		return "LKUP: " + "lkup_grp_fld_id=" + lkup_grp_fld_id + "," + "lkup_cd=" + lkup_cd;
	}

}