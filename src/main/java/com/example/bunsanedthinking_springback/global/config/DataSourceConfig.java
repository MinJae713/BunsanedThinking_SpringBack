package com.example.bunsanedthinking_springback.global.config;

import com.example.bunsanedthinking_springback.global.common.RoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class DataSourceConfig {
	@Bean(name = "writeDataSource")
	// @ConfigurationProperties(prefix = "spring.datasource.write")
	public DataSource writeDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		// dataSource.setJdbcUrl(
		// 	"jdbc:mysql://bunsaned-thinking-rds-cluster-test.cluster-cpkqc6so4dk4.ap-northeast-2.rds.amazonaws.com/bunsanedthinking");
		// dataSource.setUsername("admin");
		// dataSource.setPassword("qnstkstodrkr");

		dataSource.setJdbcUrl(
			"jdbc:mysql://localhost:3306/bunsaninsurance");
		dataSource.setUsername("root");
		dataSource.setPassword("Godyell713!");

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}

	@Bean(name = "readDataSource")
	public DataSource readDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		// dataSource.setJdbcUrl(
		// 	"jdbc:mysql://bunsaned-thinking-rds-cluster-test.cluster-ro-cpkqc6so4dk4.ap-northeast-2.rds.amazonaws.com/bunsanedthinking");
		// dataSource.setUsername("admin");
		// dataSource.setPassword("qnstkstodrkr");

		dataSource.setJdbcUrl(
			"jdbc:mysql://localhost:3306/bunsaninsurance");
		dataSource.setUsername("root");
		dataSource.setPassword("Godyell713!");
		
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}

	@Primary
	@Bean(name = "routingDataSource")
	public DataSource routingDataSource(
		@Qualifier("writeDataSource") DataSource writeDataSource,
		@Qualifier("readDataSource") DataSource readDataSource) {
		RoutingDataSource routingDataSource = new RoutingDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("WRITE", writeDataSource);
		dataSourceMap.put("READ", readDataSource);
		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(writeDataSource);
		return routingDataSource;
	}
}
