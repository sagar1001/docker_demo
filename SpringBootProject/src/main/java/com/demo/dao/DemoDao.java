package com.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DemoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Long getDataFromDB() {
		Long latestId = (long) jdbcTemplate.queryForObject(
				"SELECT id FROM apptable", new Object[] {},
				Long.class);
		return latestId;
	}
}
