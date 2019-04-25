package gov.wisconsin.framework.data.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractCargo;
import gov.wisconsin.framework.data.base.FwAbstractCollection;

@Component
@Scope(value = FwConstants.PROTOTYPE_SCOPE)
public class LKUP_Collection extends FwAbstractCollection  {
	private static final long serialVersionUID = 7901966166560561289L;
	
	private static final String PACKAGE = "gov.wisconsin.framework.data.impl.LKUP_DAO";

	public void addCargo(LKUP_Cargo newCargo) {
		add(newCargo); 
	}

	public String getPACKAGE() {
		return PACKAGE;
	}

	public LKUP_Cargo getCargo() {
	  if (size() == 0)
	    add(new LKUP_Cargo());
	  return (LKUP_Cargo) get(0);
	}

	public void setCargo(LKUP_Cargo newCargo) {
	  if (size() == 0)
	    add(newCargo);
	  else
	    set(0, newCargo);
	}

	public FwAbstractCargo getAbstractCargo() {
		return getCargo();
	}

	public void setAbstractCargo(FwAbstractCargo cargo) {
		setCargo((LKUP_Cargo) cargo);
	}

	public void setResults(LKUP_Cargo[]cbArray) {
		clear();
		for (int i = 0; i < cbArray.length; i++) {
			add(cbArray[i]);
		}
	}

	public void setResults(int idx, LKUP_Cargo cb) {
		set(idx, cb);
	}

	public void setGenericResults(Object obj) {
		if (obj instanceof LKUP_Cargo[]){
			LKUP_Cargo[] cbArray = (LKUP_Cargo[]) obj;
			for (int i = 0; i < cbArray.length; i++) {
				add(cbArray[i]);
			}
		}
	}

	public LKUP_Cargo[] getResults() {
		LKUP_Cargo[] cbArray = new LKUP_Cargo[size()];
		toArray(cbArray);
		return cbArray;
	}

	public LKUP_Cargo getResult(int idx) {
		return (LKUP_Cargo) get(idx);
	}

	public int getResultsSize() {
		return size();
	}
}