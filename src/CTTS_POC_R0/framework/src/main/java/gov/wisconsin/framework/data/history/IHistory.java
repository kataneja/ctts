package gov.wisconsin.framework.data.history;

import gov.wisconsin.framework.data.base.ICargo;

public interface IHistory extends ICargo {
	public short getHistory_cd();		
	public short getHistory_seq_num();
	public String getDelete_reason_cd();	
	public void setHistory_seq_num(short history_seq_num);
}
