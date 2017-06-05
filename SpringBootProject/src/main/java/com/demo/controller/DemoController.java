package com.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.dao.DemoDao;
import com.demo.process.DemoProcess;

@Controller
public class DemoController {
	
	public static Logger LOGGER = Logger.getLogger(DemoController.class);

	@Autowired
	private DemoProcess demoProcess;

	@RequestMapping(value = "checkStatus", method = RequestMethod.GET)
	public ResponseEntity<String> statusCheck() {

		return new ResponseEntity<String>("Web service is up and running", HttpStatus.OK);
	}

	@RequestMapping(value = "getDataFromDB", method = RequestMethod.GET)
	public ResponseEntity<String> getDataFromDB() {

		try {
			Long data = demoProcess.getDataFromDB();
			return new ResponseEntity<String>("Data returned from DB: " + data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Exception occurred while getting data from DB" + e.getMessage(),
					HttpStatus.NO_CONTENT);
		}

	}

	@RequestMapping(value = "postMessageToQueue", method = RequestMethod.GET)
	public ResponseEntity<String> postMessageToQueue(@RequestParam String message) {

		try {
			demoProcess.postMessageToQueue(message);
			return new ResponseEntity<String>("Message posted to queue: " + message, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Exception occurred while posting message to queue" + e.getMessage(),
					HttpStatus.NO_CONTENT);
		}

	}
}
