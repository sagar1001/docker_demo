package com.demo.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class DemoMessageSender {

	//@Autowired
	private JmsTemplate jmsTemplate;

	public void postMessageToQueue(String message) {
		jmsTemplate.convertAndSend(message);
	}
}
