package com.example.bunsanedthinking_springback.global.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String toString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
}
