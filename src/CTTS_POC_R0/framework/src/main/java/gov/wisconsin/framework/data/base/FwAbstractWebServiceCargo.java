package gov.wisconsin.framework.data.base;

import gov.wisconsin.framework.exception.FwException;

import java.io.Serializable;

public abstract class FwAbstractWebServiceCargo implements Serializable, Cloneable, ICargoWS {
	private static final long serialVersionUID = -3138192182806837067L;
	
	private String user;
	private String cargoName;
	private FwException feException;

	public FwAbstractWebServiceCargo() {
		super();
	}
	
	public abstract String getPackage();

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

	public FwException getFeException() {
		return feException;
	}

	public void setFeException(FwException exception) {
		feException = exception;
	}

	public boolean isDirty() {
		return false;
	}

	public void setDirty(boolean dirty) {
		
	}

	public IPrimaryKey getPrimaryKey() {
		return null;
	}

	public void setSimulation(char aSimulation) {
		
	}

	public char getSimulation() {
		return 0;
	}		

	public String getRowAction(){
		return null;
	}
}