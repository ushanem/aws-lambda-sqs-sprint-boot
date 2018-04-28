package com.pega.aws.lambda.sqs.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class AppConfig {

    @Value("${aws.sqs.queue.url}")
    private String queueUrl;

    @Value("${aws.sqs.dlq.url}")
    private String dlqueueUrl;
    
    @Value("${aaws.sqs.def.url}")
    private String defenderQueueUrl; 
    
	public String getQueueUrl() {
		// TODO Auto-generated method stub
		return queueUrl;
	}

	public String getDLQueueUrl() {
		// TODO Auto-generated method stub
		return dlqueueUrl;
	}

	public String getDefenderQueueURL() {
		// TODO Auto-generated method stub
		return defenderQueueUrl;
	}

}
