/*
 * Copyright WaveString,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of wavestring.com.,LTD. ("Confidential Information").
 */
package kr.co.coinis.webserver.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * kr.co.coinis.webserver.common.utils 
 *    |_ HolidayUtil.java
 * 
 * </pre>
 * 
 * @date : 2019. 7. 24. 오전 10:49:51
 * @version :
 * @author : kangn
 */
public class HolidayUtil {

	private static String[] solarArr = new String[] { "0101", "0301", "0505", "0606", "0815", "1225" };

	/**
	 * 해당일자가 법정공휴일, 대체공휴일, 토요일, 일요일인지 확인
	 * 
	 * @param date
	 *            양력날짜 (yyyyMMdd)
	 * @return 법정공휴일, 대체공휴일, 일요일이면 true, 오류시 false
	 */
	private static boolean isHoliday(String date) {
		try {
			return isHolidaySolar(date) || isHolidayAlternate(date) || isWeekend(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 토요일 또는 일요일이면 true를 리턴한다.
	 * 
	 * @param date
	 *            양력날짜 (yyyyMMdd)
	 * @return 일요일인지 여부
	 * @throws ParseException
	 */
	private static boolean isWeekend(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(date));
		return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	/**
	 * 해당일자가 대체공휴일에 해당하는 지 확인
	 * 
	 * @param 양력날짜
	 *            (yyyyMMdd)
	 * @return 대체 공휴일이면 true
	 */
	private static boolean isHolidayAlternate(String date) {

		String[] altHoliday = new String[] { "20150929", "20160210", "20170130", "20180926", "20180507", "20190506",
				"20200127", "20220912", "20230124", "20240212", "20240506", "20251008", "20270209", "20290924",
				"20290507" };

		return Arrays.asList(altHoliday).contains(date);
	}

	/**
	 * 해당일자가 양력 법정공휴일에 해당하는 지 확인
	 * 
	 * @param date
	 *            양력날짜 (yyyyMMdd)
	 * @return 양력 공휴일이면 true
	 */
	private static boolean isHolidaySolar(String date) {
		try {
			// 공휴일에 포함 여부 리턴
			return Arrays.asList(solarArr).contains(date.substring(4));
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
			return false;
		}
	}

	/**
	 * 양력날짜의 요일을 리턴
	 * 
	 * @param 양력날짜
	 *            (yyyyMMdd)
	 * @return 요일(int)
	 */
	private static int getDayOfWeek(String day) {
		int y = Integer.parseInt(day.substring(0, 4));
		int m = Integer.parseInt(day.substring(4, 6)) - 1;
		int d = Integer.parseInt(day.substring(6));
		Calendar c = Calendar.getInstance();
		c.set(y, m, d);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 공휴일을 제외한 Working Day 기준으로 시작~종료 시간까지의 경과시간을 구한다.
	 * 2. 처리내용 : 공휴일 및 대체휴일 제외한 순수 Working day 기준, 법정공휴일은 제외함
	 * </pre>
	 * @Method Name : getAccumulatedWorkingTime
	 * @date : 2019. 7. 24.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 24.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param start_date_hour	시작 시간 (yyyyMMddHH)
	 * @param end_date_hour		종료 시간 (yyyyMMddHH)
	 * @return
	 * @throws Exception
	 */
	public static int getAccumulatedWorkingTime(String start_date_hour, String end_date_hour) {
		try {
			String start 		= start_date_hour;
			String end 			= end_date_hour;
			
			String start_day	= start.substring(0, 8);
			int    start_hour	= Integer.valueOf(start.substring(8));
			String end_day		= end.substring(0, 8);
			int    end_hour		= Integer.valueOf(end.substring(8));
			
			// Start day = End day일때는 시간차이만 return
			if(start_day.equalsIgnoreCase(end_day)) {
				return (end_hour - start_hour);
			}
			
			Date startDt = new SimpleDateFormat("yyyyMMdd").parse(start_day);
	
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDt);
			
			int iAccumulatedDay = 0;
			iAccumulatedDay += isHoliday(start_day) ? 0 : 1;			// 시작일이 휴일이면 누적일 +0, 아니면 +1
			
			while(true) {
				cal.add(Calendar.DATE, 1);
				Date nextDt = cal.getTime();
				String next_day_str = new SimpleDateFormat("yyyyMMdd").format(nextDt);
				
				iAccumulatedDay += isHoliday(next_day_str) ? 0 : 1;		// 휴일이면 누적일 +0, 아니면 +1
				// 마지막날까지 도달... break
				if(next_day_str.equalsIgnoreCase(end_day)) break;
			}
			
			// 양편빼기 -1
			iAccumulatedDay -= 1;
			
			int iTimeDiff = (end_hour + 24) - start_hour;
			
			System.out.println("Working Day 누적일 :: " + iAccumulatedDay + ", 시작~끝시간 차이 :: " + iTimeDiff );
			
			int iAccumTime = (iAccumulatedDay * 24) + iTimeDiff;
			
			System.out.println("Total 경과시간 (공휴일제외) :: " + iAccumTime );
			
			return iAccumTime;
			
		} catch(Exception e) {
			return 0;
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 공휴일을 제외한 Working Day 기준으로 시작시간부터 현재 시간까지의 경과시간을 구한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getAccumulatedWorkingTime
	 * @date : 2019. 7. 24.
	 * @author : kangn
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 7. 24.		kangn				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param start_date_hour	시작 시간 (yyyyMMddHH)
	 * @return
	 */
	public static int getAccumulatedWorkingTime(String start_date_hour) {
		try {
			String end 	= new SimpleDateFormat("yyyyMMddHH").format(new Date());
			return getAccumulatedWorkingTime(start_date_hour, end);
		} catch(Exception e) {
			return 0;
		}
	}
	
}
