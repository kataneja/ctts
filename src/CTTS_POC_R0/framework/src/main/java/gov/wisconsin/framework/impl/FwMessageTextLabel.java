package gov.wisconsin.framework.impl;

import java.io.Serializable;

public class FwMessageTextLabel implements Serializable {
	private static final long serialVersionUID = 6542216793249087008L;
	
	private String textId = null;

    public FwMessageTextLabel(String textId) {
    	this.textId = textId; 
    }

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}

}
