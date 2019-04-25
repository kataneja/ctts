package gov.wisconsin.framework.data.pojo;

import gov.wisconsin.framework.data.base.FwAbstractCollection;

public class FwEmailCollection extends FwAbstractCollection {
	private static final long serialVersionUID = -6135694328226482594L;
	
	private static final String PACKAGE ="gov.wisconsin.framework.data.pojo";

	public String getPACKAGE() {
		return PACKAGE;
	}

	public void addCargo(FwEmailCargo aNewCargo) {
		add(aNewCargo);
	}

	public void setResults(FwEmailCargo[] cbArray) {
		clear();
		for (int i = 0; i < cbArray.length; i++) {
			add(cbArray[i]);
		}
	}

	public void setCargo(int idx, FwEmailCargo aCargo) {
		set(idx, aCargo);
	}

	public FwEmailCargo[] getResults() {
		FwEmailCargo[] cbArray = new FwEmailCargo[size()];
		toArray(cbArray);
		return cbArray;
	}

	public FwEmailCargo getCargo(int idx) {
		return (FwEmailCargo) get(idx);
	}

	public FwEmailCargo[] cloneResults(){
		FwEmailCargo[] clonedResults = new FwEmailCargo[size()];
		
		for(int i = 0; i < size(); i++) {
			FwEmailCargo originalCargo = getCargo(i);
			FwEmailCargo newCargo = new FwEmailCargo();
			
			newCargo.setDirty(originalCargo.isDirty());
			newCargo.setDistributionId(originalCargo.getDistributionId());
			newCargo.setEmailMessageSubstitution(originalCargo.getEmailMessageSubstitution());
			newCargo.setMessageId(originalCargo.getMessageId());
			newCargo.setRowAction(originalCargo.getRowAction());
			newCargo.setUser(originalCargo.getUser());
			
			clonedResults[i] = newCargo;
		}
		
		return clonedResults;
	}

	public void setGenericResults(Object obj) {
		if (obj instanceof FwEmailCargo[]) {
			FwEmailCargo[] cbArray = (FwEmailCargo[]) obj;
			setResults(cbArray);
		}
	}
}