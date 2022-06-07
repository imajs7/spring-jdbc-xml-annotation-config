package com.springjdbc.util;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.springjdbc.service.StudentDaoImplementation;

@Configuration
public class SpringConfig {
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc");
		dataSource.setUsername("root");
		dataSource.setPassword("12345678");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate getTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource( getDataSource() );
		return jdbcTemplate;
	}
	
	@Bean(name = "studentDao")
	public StudentDao getStudentDao() {
		StudentDaoImplementation studentDao = new StudentDaoImplementation();
		studentDao.setJdbcTemplate( getTemplate() );
		return studentDao;
	}
}
