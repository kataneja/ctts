package gov.wisconsin.framework.security.cargo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.data.base.FwAbstractCollection;

@Component
@Scope(value = FwConstants.PROTOTYPE_SCOPE)
public class LDAP_Collection extends FwAbstractCollection {
	private static final long serialVersionUID = -8252897488507616118L;
	
	private static final String PACKAGE = "gov.wisconsin.framework.security.core.FwLdapManager";

	public String getPACKAGE() {
		return PACKAGE;
	}

	public void addCargo(LDAP_Cargo aNewCargo) {
		add(aNewCargo);
	}

	public void setResults(LDAP_Cargo[]cbArray) {
		clear();
		for (int i = 0; i < cbArray.length; i++) {
			add(cbArray[i]);
		}
	}

	public void setCargo(int idx, LDAP_Cargo aCargo) {
		set(idx, aCargo);
	}

	public LDAP_Cargo[] getResults() {
		LDAP_Cargo[] cbArray = new LDAP_Cargo[size()];
		toArray(cbArray);
		return cbArray;
	}

	public LDAP_Cargo getCargo(int idx) {
		return (LDAP_Cargo) get(idx);
	}

	public LDAP_Cargo[] cloneResults(){
		LDAP_Cargo[] rescargo = new LDAP_Cargo[size()];
		for(int i = 0; i < size(); i++) {
			LDAP_Cargo cargo = getCargo(i);
			rescargo[i] = new LDAP_Cargo();
			rescargo[i].setAction(cargo.getAction());
			rescargo[i].setAnswer1(cargo.getAnswer1());
			rescargo[i].setAnswer2(cargo.getAnswer2());
			rescargo[i].setEmail(cargo.getEmail());
			rescargo[i].setFirstName(cargo.getFirstName());
			rescargo[i].setLastName(cargo.getLastName());
			rescargo[i].setLogonId(cargo.getLogonId());
			rescargo[i].setPassword(cargo.getPassword());
			rescargo[i].setQuestion1(cargo.getQuestion1());
			rescargo[i].setQuestion2(cargo.getQuestion2());
			rescargo[i].setRowAction(cargo.getRowAction());
			rescargo[i].setSuffixName(cargo.getSuffixName());
			rescargo[i].setMiddleInitial(cargo.getMiddleInitial());
			rescargo[i].setWid(cargo.getWid());
		}
		return rescargo;
	}

	public void setGenericResults(Object obj) {
		if (obj instanceof LDAP_Cargo[]) {
			LDAP_Cargo[] cbArray = (LDAP_Cargo[]) obj;
			setResults(cbArray);
		}
	}
}
