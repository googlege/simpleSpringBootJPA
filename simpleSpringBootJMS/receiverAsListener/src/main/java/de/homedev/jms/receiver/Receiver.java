package de.homedev.jms.receiver;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@JmsListener(destination = "${app.jms.mqname}")
	public void receive(String message) {
		LOGGER.info("received message='{}'", message);
		latch.countDown();
	}

}
