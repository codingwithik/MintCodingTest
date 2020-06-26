package com.mint.customerservice.services;


import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);
	
	 public CountDownLatch getCountDownLatch1() {
	        return countDownLatch1;
	    }

	 private CountDownLatch countDownLatch1 = new CountDownLatch(1);

	
	@KafkaListener(topics = "com.mintfintech.card_verified", groupId = "Card")
	public void listen(String message) {
	    System.out.println("Received Messasge in group foo: " + message);
	    LOGGER.info("Reciever payload..."+ message);
	    countDownLatch1.countDown();
	}
}
