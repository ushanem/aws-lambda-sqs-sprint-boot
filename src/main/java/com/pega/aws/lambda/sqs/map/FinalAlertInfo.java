package com.pega.aws.lambda.sqs.map;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class FinalAlertInfo {

	private String accountNumber;
	private String[] alertReason;
	private String alertScore;
	private String alertType;
	private String alertTypeDescription;
	private String applicationId;
	private String[] documentId;
	private String channel;
	private String CIFID;
	private String customerReferenceID;
	private String prospectID;
	private String defenderAlertType;
	private String ipaddress;
	private String jointApplicantId;
	private String jointApplicantIdType;
	private String primaryApplicantId;
	private String primaryApplicantIdType;
	private String productTypeCode;
	private String ssn;
	private String UUID;
	private FinalAlertTransInfo TxnData;
	
	
	
	/**
	 * @return the AcctNo
	 */
	public String getAcctNo() {
		return accountNumber;
	}
	/**
	 * @param AcctNo the AcctNo to set
	 */
	public void setAcctNo(String AcctNo) {
		this.accountNumber = AcctNo;
	}
	/**
	 * @return the ArrAlertReason
	 */
	public String[] getArrAlertReason() {
		return alertReason;
	}
	/**
	 * @param ArrAlertReason the ArrAlertReason to set
	 */
	public void setArrAlertReason(String[] ArrAlertReason) {
		this.alertReason = ArrAlertReason;
	}
	/**
	 * @return the AlertScore
	 */
	public String getAlertScore() {
		return alertScore;
	}
	/**
	 * @param AlertScore the AlertScore to set
	 */
	public void setAlertScore(String AlertScore) {
		this.alertScore = AlertScore;
	}
	/**
	 * @return the AlertType
	 */
	public String getAlertType() {
		return alertType;
	}
	/**
	 * @param AlertType the AlertType to set
	 */
	public void setAlertType(String AlertType) {
		this.alertType = AlertType;
	}
	/**
	 * @return the AlertTypeDescription
	 */
	public String getAlertTypeDescription() {
		return alertTypeDescription;
	}
	/**
	 * @param AlertTypeDescription the AlertTypeDescription to set
	 */
	public void setAlertTypeDescription(String AlertTypeDescription) {
		this.alertTypeDescription = AlertTypeDescription;
	}
	/**
	 * @return the ApplicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param ApplicationId the ApplicationId to set
	 */
	public void setApplicationId(String ApplicationId) {
		this.applicationId = ApplicationId;
	}
	/**
	 * @return the ArrDocumentId
	 */
	public String[] getArrDocumentId() {
		return documentId;
	}
	/**
	 * @param ArrDocumentId the ArrDocumentId to set
	 */
	public void setArrDocumentId(String[] ArrDocumentId) {
		this.documentId = ArrDocumentId;
	}
	/**
	 * @return the Channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param Channel the Channel to set
	 */
	public void setChannel(String Channel) {
		this.channel = Channel;
	}

	/**
	 * @return the CustomerReferenceId
	 */
	public String getCustomerReferenceID() {
		return customerReferenceID;
	}
	/**
	 * @param CustomerReferenceId the CustomerReferenceId to set
	 */
	public void setCustomerReferenceID(String CustomerReferenceId) {
		this.customerReferenceID = CustomerReferenceId;
	}
	/**
	 * @return the DefenderAlertType
	 */
	public String getDefenderAlertType() {
		return defenderAlertType;
	}
	/**
	 * @param DefenderAlertType the DefenderAlertType to set
	 */
	public void setDefenderAlertType(String DefenderAlertType) {
		this.defenderAlertType = DefenderAlertType;
	}

	/**
	 * @return the JointApplId
	 */
	public String getJointApplicantId() {
		return jointApplicantId;
	}
	/**
	 * @param JointApplId the JointApplId to set
	 */
	public void setJointApplicantId(String JointApplId) {
		this.jointApplicantId = JointApplId;
	}
	/**
	 * @return the JointApplIdType
	 */
	public String getJointApplicantIdType() {
		return jointApplicantIdType;
	}
	/**
	 * @param JointApplIdType the JointApplIdType to set
	 */
	public void setJointApplicantIdType(String JointApplIdType) {
		this.jointApplicantIdType = JointApplIdType;
	}
	/**
	 * @return the PrimaryApplId
	 */
	public String getPrimaryApplicantId() {
		return primaryApplicantId;
	}
	/**
	 * @param PrimaryApplId the PrimaryApplId to set
	 */
	public void setPrimaryApplicantId(String PrimaryApplId) {
		this.primaryApplicantId = PrimaryApplId;
	}
	/**
	 * @return the PrimaryApplIdType
	 */
	public String getPrimaryApplicantIdType() {
		return primaryApplicantIdType;
	}
	/**
	 * @param PrimaryApplIdType the PrimaryApplIdType to set
	 */
	public void setPrimaryApplicantIdType(String PrimaryApplIdType) {
		this.primaryApplicantIdType = PrimaryApplIdType;
	}
	/**
	 * @return the ProductTypeCode
	 */
	public String getProductTypeCode() {
		return productTypeCode;
	}
	/**
	 * @param ProductTypeCode the ProductTypeCode to set
	 */
	public void setProductTypeCode(String ProductTypeCode) {
		this.productTypeCode = ProductTypeCode;
	}
	/**
	 * @return the TaxId
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param TaxId the TaxId to set
	 */
	public void setSsn(String TaxId) {
		this.ssn = TaxId;
	}
	/**
	 * @return the UUID
	 */
	public String getUUID() {
		return UUID;
	}
	/**
	 * @param UUID the UUID to set
	 */
	public void setUUID(String UUID) {
		this.UUID = UUID;
	}
	/**
	 * @return the cIFID
	 */
	public String getCIFID() {
		return CIFID;
	}
	/**
	 * @param cIFID the cIFID to set
	 */
	public void setCIFID(String cIFID) {
		CIFID = cIFID;
	}
	/**
	 * @return the prospectID
	 */
	public String getProspectID() {
		return prospectID;
	}
	/**
	 * @param prospectID the prospectID to set
	 */
	public void setProspectID(String prospectID) {
		this.prospectID = prospectID;
	}
	/**
	 * @return the ipaddress
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	/**
	 * @param ipaddress the ipaddress to set
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	/**
	 * @return the txnData
	 */
	public FinalAlertTransInfo getTxnData() {
		return TxnData;
	}
	/**
	 * @param txnData the txnData to set
	 */
	public void setTxnData(Map<String,String> txnData) {
		for (Map.Entry<?, ?> entry : txnData.entrySet()) {
			String key = entry.getKey().toString();
        	switch(key) {
			case "transactionChannel":
				TxnData.setTxnChannel(entry.getValue().toString());
				break;
			case "transactionType":
				TxnData.setTxnType(entry.getValue().toString());
				break;
			case "transactionAmount":
				TxnData.setTxnAmount(entry.getValue().toString());
				break;
			case "presentmentTimestamp":
				TxnData.setPresentmentDate(entry.getValue().toString());
				break;
			case "checkNumber":
				TxnData.setCheckNumber(entry.getValue().toString());
				break;
			case "checkScore":
				TxnData.setCheckScore(Integer.parseInt(entry.getValue().toString()));
				break;
			case "clearingTimestamp":
				TxnData.setClearingDate(entry.getValue().toString());
				break;
			case "payeeName":
				TxnData.setPayeeName(entry.getValue().toString());
				break;
			case "transactionSequenceNumber":
				TxnData.setSequenceNumber(entry.getValue().toString());
				break;
			case "routingNumber":
				TxnData.setRoutingNumber(entry.getValue().toString());
				break;
			case "signatureScore":
				TxnData.setSignatureScore(entry.getValue().toString());
				break;
			default:
				break;
			}
		}
	}
	
}
