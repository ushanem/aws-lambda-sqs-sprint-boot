package com.pega.aws.lambda.sqs.map;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlertMapper {

	private void remapAlertInfo(Map originalAlertMap) {
		ObjectMapper objMapper = new ObjectMapper();
		try {
			String strFinalAlertJson = objMapper.writeValueAsString(originalAlertMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        /*for (Map.Entry<?, ?> entry : empMap.entrySet())
	        {
	            logger.info("\n----------------------------\n"+entry.getKey() + "=" + entry.getValue()+"\n");
	        }*/
	}
	
}
