package com.carworld.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;




@Configuration
public class DbConfiguration {

	/*
	 * The preceding configuration is exactly equivalent to the following Spring XML:
	 * <beans>
		  <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"/>
		  <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
		  <property name="url" value="dbc:mysql://localhost/cardb"/>
		  <property name="username" value="root"/>
		  <property name="password" value="root"/>
		</beans> 
	 */
	@Bean
	@Scope("singleton")
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost/cardb");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}

	
	@Bean
	@Scope("singleton")
	public CarRepository carRepository() {
		CarRepository carRepository = new JdbcCarRepository(dataSource());
		((JdbcCarRepository)carRepository).setEngineRepository(engineRepository());
		((JdbcCarRepository)carRepository).setManufacturerRepository(manufacturerRepository());
		return carRepository;
	}
	
	@Bean
	@Scope("singleton")
	public EngineRepository engineRepository() {
		EngineRepository engineRepository = new JdbcEngineRepository(dataSource());
		((JdbcEngineRepository)engineRepository).setManufacturerRepository(manufacturerRepository());
		return engineRepository;
	}
	
	@Bean
	@Scope("singleton")
	public ManufacturerRepository manufacturerRepository() {
		return new JdbcManufacturerRepository(dataSource());
	}
	
	
}
