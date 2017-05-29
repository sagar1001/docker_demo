package com.demo.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class DemoMessageListener implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(DemoMessageListener.class);

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			String textMessage = "";
			try {
				textMessage = ((TextMessage) message).getText();
			} catch (JMSException e) {
				LOGGER.error("Error while receiving message", e);
				System.out.println("Error while receiving message" + e.getMessage());
			}
			LOGGER.info("Received message: " + textMessage);
			System.out.println("Received message: " + textMessage);
		}

	}

}
