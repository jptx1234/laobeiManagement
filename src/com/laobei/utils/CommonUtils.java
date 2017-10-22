package com.laobei.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
	
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

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
	
	/**
	 * 整理开始时间和结束时间
	 * @param beginDate
	 * @param endDate
	 * @return 数组下标0的是开始时间，下标为1的是结束时间
	 */
	public static String[] dealDateRange(String beginDate, String endDate) {
		Calendar calendar = Calendar.getInstance();
		
		Date begin = null;
		Date end = null;
		try {
			if (StringUtils.isNotBlank(beginDate)) {
				begin = SDF.parse(beginDate);
			}
		} catch (Exception e) {
		}
		try {
			if (StringUtils.isNotBlank(endDate)) {
				end = SDF.parse(endDate);
			}
		} catch (Exception e) {
		}
		
		if (end == null) {
			calendar.add(Calendar.DATE, 1);
			end = calendar.getTime();
			endDate = SDF.format(end);
		}
		if (begin == null || begin.after(end)) {
			calendar.add(Calendar.DATE, -1);
			begin = calendar.getTime();
			beginDate = SDF.format(begin);
		}
		
		return new String[] {beginDate, endDate};
	}
	
}
