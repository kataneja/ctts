package gov.wisconsin.framework.data.base;

import java.io.Serializable;

public interface ICargo extends Serializable {

	public String ROWACTION_RETRO  = "R";
	public String ROWACTION_INSERT = "I";
	public String ROWACTION_UPDATE = "U";
	public String ROWACTION_DELETE = "D";
	public String ROWACTION_SELECT = "S";
	
	public String getUser();
	
	public void setUser(String newUser);
	
	public Object clone() throws CloneNotSupportedException;
	
	public IPrimaryKey getPrimaryKey();
	
	public String inspectCargo();
	
	public boolean isDirty();

	public void setDirty(boolean dirty);
	
	public String getRowAction();
}
