package com.example.bunsanedthinking_springback.global.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoutingDataSourceAspect {

	@Before("@annotation(ReadOnly)")
	public void setReadDataSource() {
		RoutingDataSourceContext.setDataSourceType("READ");
	}

	@After("@annotation(ReadOnly)")
	public void clearDataSource() {
		RoutingDataSourceContext.clear();
	}
}

