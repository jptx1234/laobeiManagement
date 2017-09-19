package com.laobei.utils;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

	private CommonUtils() {}
	
	public static int parseInt(String num, int defaultNum) {
		if (StringUtils.isEmpty(num)) {
			return defaultNum;
		}
		try {
			return Integer.parseInt(num);
		} catch (Exception e) {
			return defaultNum;
		}
	}
	
}
