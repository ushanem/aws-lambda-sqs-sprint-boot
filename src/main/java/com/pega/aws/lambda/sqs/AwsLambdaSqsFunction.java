package com.pega.aws.lambda.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.pega.aws.lambda.sqs.config.AppConfig;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;


@Slf4j
@Component("awsLambdaSqsFunction")
public class AwsLambdaSqsFunction implements Function<Void,Void>{

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private AmazonSQS amazonSqs;

    private static final Logger log = LoggerFactory.getLogger(AwsLambdaSqsFunction.class);
    @Override
    public Void apply(Void empty) {

        String queueUrl = appConfig.getQueueUrl();
        String dlqUrl = appConfig.getDLQueueUrl();
        //String defenderQueueURL = appConfig.getDefenderQueueURL();
        log.info("Retrieved queues: {}",amazonSqs.listQueues().getQueueUrls().toString());

        List<Message> messageList = this.getQueueMessageByQueueUrl(queueUrl);
        for (Message message : messageList) {
        	boolean isSuccessful=false;
            log.info("~~~~~~~~~~Read message#", messageList.indexOf(message)," - ",message.toString());
            isSuccessful = this.initiateIngestAlert(message);
            if(!isSuccessful) {
            	this.sendMessageToDLQueue(dlqUrl,message);
            }
        }
        this.deleteMessagesByQueueUrl(messageList,queueUrl);
        return empty;
    }
    
    private boolean initiateIngestAlert(Message message) {
    	boolean success = false;

    	//Parse JSON to map
    	
    	Map<String, Object> mapOriginalMessage;
		try {
			mapOriginalMessage = jsonToMap(new JSONObject(message.getBody()));
	    	String strDefenderAlertType = mapOriginalMessage.get("defenderAlertType").toString();
	    	String strFinalRequest = null;
			//Interpret map to final JSON
			strFinalRequest = this.remapJSONRequest(mapOriginalMessage);
	
			if(strFinalRequest.length()>0) {
		    	switch(strDefenderAlertType) {
		    	   case "CustomerAlert" :
		    		   success = this.ingestFinalJson(mapOriginalMessage);
		    		   log.info("-----------Pega URL invoked----------");
		    		   break; 
		    	   case "TransactionAlert":
		    	   case "WithdrawalAlert":
		    	   case "WireAlert":
		    	   case "CheckInclearingAlert":
		    		   log.info("-----------Pega URL invoked----------");
		    		   success = true;
		    		   //Add Logic to invoke AcctOpen URL
		     	      break; 
		    	   default : // Optional
		    	      // Add logic to invoke the transactionAlert URL
		    		   log.info("********Alert type NOT Defined:",strDefenderAlertType," - Pega URL Not Found*******");
		    	}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return success;
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject objJson) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = objJson.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = objJson.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static String[] toList(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < array.length(); i++) {
            String value = (String) array.get(i);
            list.add(value);
        }
        String[] strArrValues = (String[]) list.toArray();
        return strArrValues;
    }
    
    private boolean ingestFinalJson(Map<String, Object> mapOriginalMessage) {
    	boolean isIngestionSuccess = true;
    	
    	
		return isIngestionSuccess;
	}

	private String remapJSONRequest(Map<String, Object> mapOriginalMessage) {
		String strFinalJson = "";
		Map<String, Object> mapFinalMessage = new HashMap<String,Object>();
		ObjectMapper objAlertMapper = new ObjectMapper();
		for (Map.Entry<?, ?> entry : mapOriginalMessage.entrySet()) {
			String key = entry.getKey().toString();
			switch(key) {
			case "customerReferenceId":
				mapFinalMessage.put("customerReferenceID", entry.getValue().toString());
				break;
			case "prospectId":
				mapFinalMessage.put("prospectID", entry.getValue().toString());
				break;
			case "taxId":
				mapFinalMessage.put("ssn", entry.getValue().toString());
				break;
			case "ipAddress":
				mapFinalMessage.put("ipaddress", entry.getValue().toString());
				break;
			case "uuid":
				mapFinalMessage.put("UUID", entry.getValue().toString());
				break;
			case "customerId":
				mapFinalMessage.put("CIFID", entry.getValue().toString());
				break;
			case "transactionData":
				mapFinalMessage.put("TxnData", entry.getValue());
				break;
			default:
				mapFinalMessage.put(key, entry.getValue());
				break;
			}
		}
        try {
            strFinalJson = objAlertMapper.writeValueAsString(mapFinalMessage);
            log.info("~~~~~~~~~~~Final JSON before ingestion",strFinalJson);
        } catch (IOException e) {
        	log.info("**********Conversion of the Message map to JSON failed:",e.getMessage());
        }
		return strFinalJson;
	}

	private void deleteMessagesByQueueUrl(List<Message> messageList, String queueUrl) {

        if(messageList == null || messageList.isEmpty()){
            log.info("No message in the list of messages to br deleted");
            return;
        }

        List<DeleteMessageBatchRequestEntry> deleteMessageEntries = new ArrayList<>();

        messageList.forEach(message ->
            deleteMessageEntries.add(
                    new DeleteMessageBatchRequestEntry(message.getMessageId(),message.getReceiptHandle())
        ));

        DeleteMessageBatchRequest deleteRequest = new DeleteMessageBatchRequest();
        deleteRequest.setQueueUrl(queueUrl);
        deleteRequest.setEntries(deleteMessageEntries);

        DeleteMessageBatchResult result = amazonSqs.deleteMessageBatch(deleteRequest);

        log.info("Successfully removed size: {}",result.getSuccessful().size());
        log.error("Failed to removed size: {}",result.getFailed().size());
    }

    private List<Message> getQueueMessageByQueueUrl(String queueUrl) {
        log.info("Getting messages from queue url: {}", queueUrl);

        ReceiveMessageRequest messageRequest = new ReceiveMessageRequest(queueUrl).withWaitTimeSeconds(180);

        List<Message> messages = amazonSqs.receiveMessage(messageRequest).getMessages();

        log.info("Received total messages size: {}", messages.size());

        return messages;
    }
    
    private void sendMessageToDLQueue(String dlqueueUrl, Message message) {

    	MessageAttributeValue value = new MessageAttributeValue();
    	value.setStringValue("0");
    	message.addMessageAttributesEntry("retryCount", value);
    	SendMessageRequest messageRequest = new SendMessageRequest();
    	messageRequest.setMessageBody(message.getBody());
    	messageRequest.setQueueUrl(dlqueueUrl);

        log.info("************Message written to DLQ:", message.toString());
    }
}
