package com.airlines.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;


@Configuration
@ComponentScan({"com.airlines.controllers","com.airlines.pojos","com.airlines.dao","com.airlines.filters"})
@EnableSpringConfigured
public class JPAConfig {
	@Bean
	public Properties getProperties() {
		final Properties props = new Properties();
		props.put("javax.persistence.jdbc.url" ,"jdbc:mysql://localhost:3306/airlines");
		props.put("javax.persistence.jdbc.user","root");
		props.put("javax.persistence.jdbc.password" ,"sqs786");
		props.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
		props.put("hibernate.show_sql" ,"true");
		props.put("hibernate.format_sql" ,"true");
		return props;
	}
	@Bean
	public EntityManager getEMF() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		return emf.createEntityManager();
	}
}
