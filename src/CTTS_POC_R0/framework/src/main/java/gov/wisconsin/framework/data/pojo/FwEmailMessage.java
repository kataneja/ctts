package gov.wisconsin.framework.data.pojo;

public class FwEmailMessage {
	
	private String body;
	private String header;
	private String footer;
	private String subject;
	private String fromAddress;
	private String[] toAddresses;
	private String[] ccAddresses;
	private String[] bccAddresses;

	public FwEmailMessage(String subject, String header, String body, String footer, String to) {
		this.body = body;
		this.header = header;
		this.footer = footer;
		this.subject = subject;
	}
	
	public FwEmailMessage(String subject, String header, String body, String footer) {
		this.body = body;
		this.header = header;
		this.footer = footer;
		this.subject = subject;
	}
	
	public FwEmailMessage(String subject, String header, String body, String footer, String[] toAddress) {
		this.body = body;
		this.header = header;
		this.footer = footer;
		this.subject = subject;
		this.toAddresses = toAddress;
	}
	
	public FwEmailMessage(String subject, String header, String body, String footer, String fromAddress, String[] toAddress) {
		this.body = body;
		this.header = header;
		this.footer = footer;
		this.subject = subject;
		this.toAddresses = toAddress;
		this.fromAddress = fromAddress;
	}
	
	public FwEmailMessage(String subject, String header, String body, String footer, String fromAddress, String[] toAddress, String[] ccAddress) {
		this.body = body;
		this.header = header;
		this.footer = footer;
		this.subject = subject;
		this.toAddresses = toAddress;
		this.ccAddresses = ccAddress;
		this.fromAddress = fromAddress;
	}

	public FwEmailMessage(String subject, String header, String body, String footer, String fromAddress, String[] toAddress, String[] ccAddress, String[] bccAddress) {
		this.body = body;
		this.header = header;
		this.footer = footer;
		this.subject = subject;
		this.toAddresses = toAddress;
		this.ccAddresses = ccAddress;
		this.fromAddress = fromAddress;
		this.bccAddresses = bccAddress;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String[] getToAddresses() {
		return toAddresses;
	}

	public void setToAddresses(String[] toAddresses) {
		this.toAddresses = toAddresses;
	}

	public String[] getCcAddresses() {
		return ccAddresses;
	}

	public void setCcAddresses(String[] ccAddresses) {
		this.ccAddresses = ccAddresses;
	}

	public String[] getBccAddresses() {
		return bccAddresses;
	}

	public void setBccAddresses(String[] bccAddresses) {
		this.bccAddresses = bccAddresses;
	}
}
