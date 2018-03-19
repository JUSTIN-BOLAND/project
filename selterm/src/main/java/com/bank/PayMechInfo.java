package com.bank;

public class PayMechInfo {

	/**
	 * 序号
	 */
	private String seqno;
	/**
	 * 账号
	 */
	private String AcctNo;
	/**
	 * 账号名称
	 */
	private String AcctNme;
	/**
	 * 付款金额
	 */
	private String PmtAmt;
	/**
	 * 备注信息
	 */
	private String Sumry;
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getAcctNo() {
		return AcctNo;
	}
	public void setAcctNo(String acctNo) {
		AcctNo = acctNo;
	}
	public String getAcctNme() {
		return AcctNme;
	}
	public void setAcctNme(String acctNme) {
		AcctNme = acctNme;
	}
	public String getPmtAmt() {
		return PmtAmt;
	}
	public void setPmtAmt(String pmtAmt) {
		PmtAmt = pmtAmt;
	}
	public String getSumry() {
		return Sumry;
	}
	public void setSumry(String sumry) {
		Sumry = sumry;
	} 
	
	
}
