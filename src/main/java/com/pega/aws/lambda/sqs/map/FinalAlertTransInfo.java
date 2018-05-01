package com.pega.aws.lambda.sqs.map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class FinalAlertTransInfo {
	private String CheckNumber;
	private int CheckScore;
	private DateTime ClearingDate;
	private String PayeeName;
	private DateTime PresentmentDate;
	private String RoutingNumber;
	private String signatureScore;
	private String TxnAmount;
	private String TxnChannel;
	private String SequenceNumber;
	private String TxnType;

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
	/**
	 * @return the checkNumber
	 */
	public String getCheckNumber() {
		return CheckNumber;
	}
	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		CheckNumber = checkNumber;
	}
	/**
	 * @return the checkScore
	 */
	public int getCheckScore() {
		return CheckScore;
	}
	/**
	 * @param checkScore the checkScore to set
	 */
	public void setCheckScore(int checkScore) {
		CheckScore = checkScore;
	}
	/**
	 * @return the clearingDate
	 */
	public DateTime getClearingDate() {
		return ClearingDate;
	}
	/**
	 * @param clearingDate the clearingDate to set
	 */
	public void setClearingDate(String clearingDate) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-ddTHH:mm:ss.ZZZ");
		ClearingDate = formatter.parseDateTime(clearingDate);
	}
	/**
	 * @return the presentmentDate
	 */
	public DateTime getPresentmentDate() {
		return PresentmentDate;
	}
	/**
	 * @param presentmentDate the presentmentDate to set
	 */
	public void setPresentmentDate(String presentmentDate) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-ddTHH:mm:ss.ZZZ");
		PresentmentDate = formatter.parseDateTime(presentmentDate);
	}
	/**
	 * @return the payeeName
	 */
	public String getPayeeName() {
		return PayeeName;
	}
	/**
	 * @param payeeName the payeeName to set
	 */
	public void setPayeeName(String payeeName) {
		PayeeName = payeeName;
	}
	/**
	 * @return the routingNumber
	 */
	public String getRoutingNumber() {
		return RoutingNumber;
	}
	/**
	 * @param routingNumber the routingNumber to set
	 */
	public void setRoutingNumber(String routingNumber) {
		RoutingNumber = routingNumber;
	}
	/**
	 * @return the txnAmount
	 */
	public String getTxnAmount() {
		return TxnAmount;
	}
	/**
	 * @param txnAmount the txnAmount to set
	 */
	public void setTxnAmount(String txnAmount) {
		TxnAmount = txnAmount;
	}
	/**
	 * @return the txnChannel
	 */
	public String getTxnChannel() {
		return TxnChannel;
	}
	/**
	 * @param txnChannel the txnChannel to set
	 */
	public void setTxnChannel(String txnChannel) {
		TxnChannel = txnChannel;
	}
	/**
	 * @return the sequenceNumber
	 */
	public String getSequenceNumber() {
		return SequenceNumber;
	}
	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(String sequenceNumber) {
		SequenceNumber = sequenceNumber;
	}
	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return TxnType;
	}
	/**
	 * @param txnType the txnType to set
	 */
	public void setTxnType(String txnType) {
		TxnType = txnType;
	}

}
