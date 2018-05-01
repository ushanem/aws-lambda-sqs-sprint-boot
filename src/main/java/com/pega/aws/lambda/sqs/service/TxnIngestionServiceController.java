package com.pega.aws.lambda.sqs.service;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.pega.aws.lambda.sqs.map.FinalAlertInfo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/")
public class TxnIngestionServiceController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${ingestion.dp.restURL}")
	String serviceURL;

	/**
	 Consuming a service by postForEntity method, this method is exposed as a post operation if user 
	 post a request object(JSON) it will be automatically mapped to Request parameter.
	 */
	@PostMapping("/prweb/PRRestService/FraudAlertServicePackage/Services/IngestAlertWithTxns")
	public String postAvailability(@RequestBody FinalAlertInfo ingestServiceRequest) {
		
		ResponseEntity<String> response = restTemplate.postForEntity(serviceURL, ingestServiceRequest,
				String.class);
		return response.getBody();
	}

	private Object createIngestRequest() {
		FinalAlertInfo objFinalAlertInfo = new FinalAlertInfo();
		//Ad lines to set values of attributes of the object
		return objFinalAlertInfo;
	}

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

}


