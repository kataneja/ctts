package gov.wisconsin.framework.data.pojo;

import gov.wisconsin.framework.data.base.IPrimaryKey;

import java.io.Serializable;

public class LKUP_GRP_PrimaryKey implements Serializable, IPrimaryKey {
	private static final long serialVersionUID = 7519398772420597751L;
	
	private String lkup_grp_cd;

	public LKUP_GRP_PrimaryKey() {}

	public String inspectPrimaryKey() {
		return "LKUP_GRP: " + "lkup_grp_cd=" + lkup_grp_cd;
	}

	public java.lang.String getLkup_grp_cd() {
		return lkup_grp_cd;
	}

	public void setLkup_grp_cd(java.lang.String lkup_grp_cd) {
		this.lkup_grp_cd = lkup_grp_cd;
	}
}