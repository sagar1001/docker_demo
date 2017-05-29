package com.demo.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.DemoDao;
import com.demo.messaging.DemoMessageSender;

@Component
public class DemoProcess {

	@Autowired
	private DemoDao demoDao;

	@Autowired
	private DemoMessageSender demoMessaging;

	public Long getDataFromDB() {
		return demoDao.getDataFromDB();
	}

	public void postMessageToQueue(String message) {
		demoMessaging.postMessageToQueue(message);
	}

}
