package de.homedev.rest.client;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;



@Component
public class SenderV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenderV1.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.restPath}")
    private String restPath;

    @Value("${app.restProtocol}")
    private String restProtocol;

    @Value("${app.restHost}")
    private String restHost;

    @Value("${app.restPort}")
    private int restPort;

    public void send(final String msg)  throws URISyntaxException{
        final String restUrl = restProtocol + "://" + restHost + ":" + restPort + restPath;
        final java.net.URI uri = new java.net.URI(restUrl);
        System.err.println("restUrl="+restUrl);

        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<String> requestEntity = new HttpEntity<>(msg,requestHeaders);


        final ResponseEntity<Void> response = restTemplate.exchange(
            uri,
            HttpMethod.POST,
            requestEntity,
            Void.class
    );


        //final ResponseEntity<Void> response=restTemplate.postForEntity(restUrl, msg, Void.class);

        LOGGER.info("send message: " + msg + " to " + restUrl);
        LOGGER.info("response: " + response);
    }

    public void sendV2() {
        final String restUrl = restProtocol + "://" + restHost + ":" + restPort + "/hello";
        System.err.println("restUrl="+restUrl);
        //ResponseEntity<?> response = restTemplate.postForEntity(restUrl, msg, String.class);
        final String result=restTemplate.getForObject(restUrl, String.class);
        LOGGER.info("result: " + result);

    }
}
