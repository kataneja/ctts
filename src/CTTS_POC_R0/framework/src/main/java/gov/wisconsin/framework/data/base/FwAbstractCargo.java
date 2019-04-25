package gov.wisconsin.framework.data.base;

import java.io.Serializable;

public abstract class FwAbstractCargo implements Serializable, Cloneable, ICargo {
	private static final long serialVersionUID = -7580016741172303007L;
	
	private boolean dirty;
	private String user;
	private String cargoName;
	private String rowAction;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String newUser) {
		user = newUser;
	}

	public String getCargoName() {
		return cargoName;
	}	

	public void setCargoName(String cName) {
		cargoName = cName;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public String getDelete_reason_cd() {
		return null;
	}

	public String getRowAction() {
		return rowAction;
	}

	public void setRowAction(String string) {
		rowAction = string;
	}
}
