package gov.wisconsin.admin.data.pojo;

import gov.wisconsin.framework.data.FwPK;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable @Data
public class T003_CustomerArea_PK implements FwPK {
	private static final long serialVersionUID = -3075067235721858380L;
	
	@NotNull
	private char customer_area_id;

	public T003_CustomerArea_PK() {}
	
	public T003_CustomerArea_PK(char customer_area_id) {
		super();
		this.customer_area_id = customer_area_id;
	}

	public char getCustomer_area_id() {
		return customer_area_id;
	}

	public void setCustomer_area_id(char customer_area_id) {
		this.customer_area_id = customer_area_id;
	}
}
