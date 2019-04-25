package gov.wisconsin.framework.data.pojo;

import java.io.Serializable;

public class FwEmailMessageSubstitution implements Serializable {
	private static final long serialVersionUID = -3279857891932820288L;
	
	private String fromAddress;
	private String[] bodySubst;
	private String[] toAddress;
	private String[] ccAddress;
	private String[] bccAddress;
	private String[] headerSubst;
	private String[] footerSubst;
	private String[] subjectSubst;
	
	public String getFromAddress() {
		return fromAddress;
	}
	
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	public String[] getBodySubst() {
		return bodySubst;
	}
	
	public void setBodySubst(String[] bodySubst) {
		this.bodySubst = bodySubst;
	}
	
	public String[] getToAddress() {
		return toAddress;
	}
	
	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}
	
	public String[] getCcAddress() {
		return ccAddress;
	}
	
	public void setCcAddress(String[] ccAddress) {
		this.ccAddress = ccAddress;
	}
	
	public String[] getBccAddress() {
		return bccAddress;
	}
	
	public void setBccAddress(String[] bccAddress) {
		this.bccAddress = bccAddress;
	}
	
	public String[] getHeaderSubst() {
		return headerSubst;
	}
	
	public void setHeaderSubst(String[] headerSubst) {
		this.headerSubst = headerSubst;
	}
	
	public String[] getFooterSubst() {
		return footerSubst;
	}
	
	public void setFooterSubst(String[] footerSubst) {
		this.footerSubst = footerSubst;
	}
	
	public String[] getSubjectSubst() {
		return subjectSubst;
	}
	
	public void setSubjectSubst(String[] subjectSubst) {
		this.subjectSubst = subjectSubst;
	}

}
