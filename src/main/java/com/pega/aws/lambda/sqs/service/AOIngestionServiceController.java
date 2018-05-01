package com.pega.aws.lambda.sqs.service;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.pega.aws.lambda.sqs.map.FinalAlertInfo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RestController
@RequestMapping("/")
public class AOIngestionServiceController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${ingestion.dp.restURL}")
	String serviceURL;
	
	String strFinalIngestJson;
	
	public AOIngestionServiceController(String strJson) {
		strFinalIngestJson = strJson;
	}

	/**
	 Consuming a service by postForEntity method, this method is exposed as a post operation if user 
	 post a request object(JSON) it will be automatically mapped to Request parameter.
	 */
	@PostMapping("/prweb/PRRestService/FraudAlertServicePackage/Services/IngestAlert")
	public String postAvailability(@RequestBody FinalAlertInfo ingestServiceRequest) {
		ResponseEntity<String> response = restTemplate.postForEntity(serviceURL, ingestServiceRequest,
				String.class);
		return response.getBody();
	}
/*
	private Object createIngestRequest() {
		FinalAlertInfo reqFinalAlertInfo = new FinalAlertInfo();
		//Add lines to set values of attributes of the object
		
		return reqFinalAlertInfoß;
	}
*/
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

}


