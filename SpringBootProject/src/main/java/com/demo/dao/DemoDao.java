package com.demo.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DemoDao {

	public static Logger LOGGER = Logger.getLogger(DemoDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Long getDataFromDB() {
		jdbcTemplate.setQueryTimeout(1);
		LOGGER.info("Querying DB for data");
		Long latestId = (long) jdbcTemplate.queryForObject("SELECT id FROM apptable", new Object[] {}, Long.class);
		LOGGER.info("Obtained data from DB: " + latestId);
		return latestId;
	}
}
