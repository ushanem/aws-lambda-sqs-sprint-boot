package com.pega.aws.lambda.sqs.map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class OriginalAlertTransInfo {
	private String checkNumber;
	private int checkScore;
	private DateTime clearingTimestamp;
	private String payeeName;
	private DateTime presentmentTimestamp;
	private String routingNumber;
	private String signatureScore;
	private String transactionAmount;
	private String transactionChannel;
	private String transactionSequenceNumber;
	private String transactionType;
	/**
	 * @return the strCheckNumber
	 */
	public String getCheckNumber() {
		return checkNumber;
	}
	/**
	 * @param strCheckNumber the strCheckNumber to set
	 */
	public void setCheckNumber(String strCheckNumber) {
		this.checkNumber = strCheckNumber;
	}
	/**
	 * @return the strCheckScore
	 */
	public int getCheckScore() {
		return checkScore;
	}
	/**
	 * @param strCheckScore the strCheckScore to set
	 */
	public void setCheckScore(int strCheckScore) {
		this.checkScore = strCheckScore;
	}
	/**
	 * @return the dtClearingTimestam
	 */
	public DateTime getDtClearingTimestam() {
		return clearingTimestamp;
	}
	/**
	 * @param dtClearingTimestam the dtClearingTimestam to set
	 */
	public void setDtClearingTimestam(String strClearingTimestamp) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-ddTHH:mm:ss.ZZZ");
		this.clearingTimestamp = formatter.parseDateTime(strClearingTimestamp);
	}
	/**
	 * @return the strPayeeName
	 */
	public String getPayeeName() {
		return payeeName;
	}
	/**
	 * @param strPayeeName the strPayeeName to set
	 */
	public void setPayeeName(String strPayeeName) {
		this.payeeName = strPayeeName;
	}
	/**
	 * @return the strPresentmentTimestamp
	 */
	public DateTime getPresentmentTimestamp() {
		return presentmentTimestamp;
	}
	/**
	 * @param strPresentmentTimestamp the strPresentmentTimestamp to set
	 */
	public void setPresentmentTimestamp(String strPresentmentTimestamp) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-ddTHH:mm:ss.ZZZ");
		this.presentmentTimestamp = formatter.parseDateTime(strPresentmentTimestamp);
	}
	/**
	 * @return the strRoutingNumber
	 */
	public String getRoutingNumber() {
		return routingNumber;
	}
	/**
	 * @param strRoutingNumber the strRoutingNumber to set
	 */
	public void setRoutingNumber(String strRoutingNumber) {
		this.routingNumber = strRoutingNumber;
	}
	/**
	 * @return the strSignatureScore
	 */
	public String getSignatureScore() {
		return signatureScore;
	}
	/**
	 * @param strSignatureScore the strSignatureScore to set
	 */
	public void setSignatureScore(String strSignatureScore) {
		this.signatureScore = strSignatureScore;
	}
	/**
	 * @return the strTransactionAmount
	 */
	public String getTransactionAmount() {
		return transactionAmount;
	}
	/**
	 * @param strTransactionAmount the strTransactionAmount to set
	 */
	public void setTransactionAmount(String strTransactionAmount) {
		this.transactionAmount = strTransactionAmount;
	}
	/**
	 * @return the strTransactionChannel
	 */
	public String getTransactionChannel() {
		return transactionChannel;
	}
	/**
	 * @param strTransactionChannel the strTransactionChannel to set
	 */
	public void setTransactionChannel(String strTransactionChannel) {
		this.transactionChannel = strTransactionChannel;
	}
	/**
	 * @return the strTransactionSequenceNumber
	 */
	public String getTransactionSequenceNumber() {
		return transactionSequenceNumber;
	}
	/**
	 * @param strTransactionSequenceNumber the strTransactionSequenceNumber to set
	 */
	public void setTransactionSequenceNumber(String strTransactionSequenceNumber) {
		this.transactionSequenceNumber = strTransactionSequenceNumber;
	}
	/**
	 * @return the strTransactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param strTransactionType the strTransactionType to set
	 */
	public void setTransactionType(String strTransactionType) {
		this.transactionType = strTransactionType;
	}

	
}
