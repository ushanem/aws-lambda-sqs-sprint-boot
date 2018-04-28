package com.pega.aws.lambda.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.pega.aws.lambda.sqs.config.AppConfig;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
            isSuccessful = this.ingestAlert(message);
            if(!isSuccessful) {
            	this.sendMessageToDLQueue(dlqUrl,message);
            }
        }
        this.deleteMessagesByQueueUrl(messageList,queueUrl);
        return empty;
    }

    private boolean ingestAlert(Message message) {
    	boolean success = false;

    	//add steps to parse the JSON
    	//replace below code with logic checking for value of the defenderAlertType key and setting it into the temp string

    	String strMessage = message.toString();
    	int intStart = strMessage.indexOf("\"defenderAlertType\":\"");
    	int intEnd = strMessage.indexOf(",",intStart+21);
    	String strDefenderAlertType = strMessage.substring(intStart+21,intEnd-2);

    	switch(strDefenderAlertType) {
    	   case "CUSTOMERALERT" :
    		   log.info("-----------Pega URL invoked----------");
    		   success = true;
    		   //Add Logic to invoke AcctOpen URL
     	      break; 
    	   default : // Optional
    	      // Add logic to invoke the transactionAlert URL
    		   log.info("********Alert type NOT Defined:",strDefenderAlertType," - Pega URL Not Found*******");
    	}
    	return success;
    }

    private void deleteMessagesByQueueUrl(List<Message> messageList, String queueUrl) {

        if(messageList == null || messageList.isEmpty()){
            log.info("Empty list for removal");
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

        ReceiveMessageRequest messageRequest = new ReceiveMessageRequest(queueUrl).
                withWaitTimeSeconds(180);

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
