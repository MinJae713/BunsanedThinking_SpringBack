package com.example.bunsanedthinking_springback.global.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return RoutingDataSourceContext.getDataSourceType(); // "WRITE" 또는 "READ"
	}
}

