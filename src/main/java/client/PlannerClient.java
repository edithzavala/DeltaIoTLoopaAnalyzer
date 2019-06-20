package client;

import org.loopa.comm.message.IMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PlannerClient {
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	
	public void requestPlanning(String url, IMessage mssg) {
		LOGGER.info("Post analysis data to plan");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, mssg.getMessageBody().get("content"),String.class);
		LOGGER.info("Receive response (" + response.getStatusCode()+")");
//		LOGGER.info("Send mssg: " + mssg.getMessageBody().toString() + " to: " + url);
	}
}
