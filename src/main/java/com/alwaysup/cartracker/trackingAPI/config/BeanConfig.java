package com.alwaysup.cartracker.trackingAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
public class BeanConfig {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

}
