package com.example.bunsanedthinking_springback.global.common;

public class RoutingDataSourceContext {

	private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

	public static void setDataSourceType(String dataSourceType) {
		CONTEXT.set(dataSourceType);
	}

	public static String getDataSourceType() {
		return CONTEXT.get();
	}

	public static void clear() {
		CONTEXT.remove();
	}
}

