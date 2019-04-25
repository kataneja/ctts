package gov.wisconsin.framework.data.pojo;

import gov.wisconsin.framework.data.base.IPrimaryKey;

import java.io.Serializable;

public class WEB_EXCP_PrimaryKey implements Serializable, IPrimaryKey {
	private static final long serialVersionUID = 2347318239166171063L;
	
	private int excp_id;

	public String inspectPrimaryKey() {
		return "WEB_EXCP: " + "excp_id=" + excp_id;
	}

	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (int) excp_id;
		return hash;
	}

	public int getExcp_id() {
		return excp_id;
	}

	public void setExcp_id(int excp_id) {
		this.excp_id = excp_id;
	}
}