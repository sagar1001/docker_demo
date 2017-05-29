package com.demo.config;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.demo.messaging.DemoMessageListener;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnectionFactory;

@Configuration
public class DemoConfiguration {

	@Value("${jms.hostname}")
	private String jmsHostName;

	@Value("${jms.port}")
	private int jmsPort;

	@Value("${jms.queuemanager}")
	private String jmsQueueManager;

	@Value("${jms.transporttype}")
	private int jmsTransportType;

	@Value("${jms.destinationqueue}")
	private String jmsDestinationQueue;

	@Bean
	public JmsTemplate npdfJmsTemplate() throws JMSException {
		JmsTemplate jmsTemplate = new JmsTemplate();
		MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
		connectionFactory.setHostName(jmsHostName);
		connectionFactory.setPort(jmsPort);
		connectionFactory.setQueueManager(jmsQueueManager);
		connectionFactory.setTransportType(jmsTransportType);
		jmsTemplate.setConnectionFactory(connectionFactory);

		MQQueue destination = new MQQueue(jmsDestinationQueue);
		jmsTemplate.setDefaultDestination(destination);
		return jmsTemplate;
	}

	@Bean
	public MQQueueConnectionFactory queueConnectionFactory() throws JMSException {

		MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
		connectionFactory.setHostName(jmsHostName);
		connectionFactory.setPort(jmsPort);
		connectionFactory.setQueueManager(jmsQueueManager);
		connectionFactory.setTransportType(jmsTransportType);

		QueueConnection connection = connectionFactory.createQueueConnection();
		MQQueue queue = new MQQueue(jmsDestinationQueue);
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueReceiver receiver = session.createReceiver(queue);
		receiver.setMessageListener(new DemoMessageListener());

		connection.start();
		return connectionFactory;
	}
}
