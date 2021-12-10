package kr.co.coinis.webserver.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

	private static DateTimeFormatter fmtMicro
	= DateTimeFormatter.ofPattern( "yyyyMMddHHmmssSSSSSS" )
						.withLocale( Locale.KOREA )
						.withZone( ZoneId.systemDefault() );

	private static DateTimeFormatter fmtDateTime
	= DateTimeFormatter.ofPattern( "yyyyMMddHHmmss" )
						.withLocale( Locale.KOREA )
						.withZone( ZoneId.systemDefault() );

	public static String getNow() {
		return fmtMicro.format(Instant.now());
	}

	public static String getDateTimeNow() {
		return fmtDateTime.format(Instant.now());
	}

	/**
	 *
	 * <pre>
	 * 1.  기능		: 입력 받은 문자열과 포맷으로 날짜 포맷 변경하여 반환
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  Seongcheol/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param strDate
	 * @param bFormat : 전달 받은 문자열이 갖고 있는 date format
	 * @param aFormat : 전달 받은 문자열을 변환할 date format
	 * @return
	 * @throws ParseException
	 * </pre>
	 */
	public static String setDateFormat(String strDate, String bFormat, String aFormat) throws ParseException {
		if("0".equals(strDate)) {
			return "";
		} else if(!StringUtils.isEmpty(strDate)) {
			Date date = new SimpleDateFormat(bFormat).parse(strDate);

			return new SimpleDateFormat(aFormat).format(date);
		} else {
			return "";
		}
	}

	/**
	 * <pre>
	 * 1.  기능		: yyyy-MM-dd HH:mm:ss.SSS 형식으로 문자열 반환
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  민성철/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param date
	 * @return
	 * @throws ParseException
	 * </pre>
	*/
	public static String yyyyMMddHHmmssSSSFormat(String date) throws ParseException {
		return setDateFormat(date, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * <pre>
	 * 1.  기능		: yyyy-MM-dd HH:mm:ss 형식으로 문자열 반환
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  민성철/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param date
	 * @return
	 * @throws ParseException
	 * </pre>
	*/
	public static String yyyyMMddHHmmssFormat(String date) throws ParseException {
		return setDateFormat(date, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * <pre>
	 * 1.  기능		: yyyy-MM-dd HH:mm 형식으로 문자열 반환
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  민성철/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param date
	 * @return
	 * @throws ParseException
	 * </pre>
	*/
	public static String yyyyMMddHHmmFormat(String date) throws ParseException {
		return setDateFormat(date, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm");
	}

	/**
	 *
	 * <pre>
	 * 1.  기능		: 입력받은 문자열을 yyyy-MM-dd 형식으로 문자열 반환
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  Seongcheol/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param date
	 * @return
	 * @throws ParseException
	 * </pre>
	 */
	public static String yyyyMMddFormat(String date) throws ParseException {
		return setDateFormat(date, "yyyyMMddHHmmssSSS", "yyyy-MM-dd");
	}

	/**
	 *
	 * <pre>
	 * 1.  기능		: 입력받은 문자열을 yyyy-MM-dd 형식으로 문자열 반환
	 * 2.  처리개요	:
	 * 3.  주의사항	:
	 * ====================================
	 * 4.  작성자/작성일	:  Seongcheol/2018. 4. 10.
	 * ====================================
	 * 5.  수정사항
	 * 5.1 요구사항 ID	:
	 * - 수정자/수정일 	:
	 * - 수정사유/내역 	:
	 * ====================================
	 * @param date
	 * @return
	 * @throws ParseException
	 * </pre>
	 */
	public static String getDate(String day) throws ParseException {
		String result = "";

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

		result = cal.get(Calendar.YEAR) + "-";
		result += String.format("%02d",(cal.get(Calendar.MONTH) + 1)) + "-";
		result += String.format("%02d",cal.get(Calendar.DATE));

		return result;
	}
	public static String getCustomDate(int day) throws ParseException {
		String result = "";

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, day);

		result = cal.get(Calendar.YEAR) + "";
		result += String.format("%02d",(cal.get(Calendar.MONTH) + 1)) + "";
		result += String.format("%02d",cal.get(Calendar.DATE));

		return result;
	}
	public static String getCustomDate(int day, String format) throws ParseException {
		String result = format;

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, day);

		if(result.indexOf("yyyy") != -1) { result = result.replace("yyyy", cal.get(Calendar.YEAR) + "");        }
		if(result.indexOf("MM") != -1)   { result = result.replace("MM",   String.format("%02d", (cal.get(Calendar.MONTH) + 1))); }
		if(result.indexOf("dd") != -1)   { result = result.replace("dd",   String.format("%02d", cal.get(Calendar.DATE)));        }
		if(result.indexOf("HH") != -1)   { result = result.replace("HH",   String.format("%02d", cal.get(Calendar.HOUR_OF_DAY))); }
		if(result.indexOf("mm") != -1)   { result = result.replace("mm",   String.format("%02d", cal.get(Calendar.MINUTE)));      }
		if(result.indexOf("ss") != -1)   { result = result.replace("ss",   String.format("%02d", cal.get(Calendar.SECOND)));      }
		if(result.indexOf("SSS") != -1)  { result = result.replace("SSS",  String.format("%03d", cal.get(Calendar.MILLISECOND))); }

		return result;
	}
	public static String getCustomFormatDate(String format) {
		String result = format;
		Calendar cal = Calendar.getInstance();

		if(format.indexOf("yyyy") != -1) { result = result.replace("yyyy", cal.get(Calendar.YEAR) + "");        }
		if(format.indexOf("MM") != -1)   { result = result.replace("MM",   String.format("%02d", (cal.get(Calendar.MONTH) + 1))); }
		if(format.indexOf("dd") != -1)   { result = result.replace("dd",   String.format("%02d", cal.get(Calendar.DATE)));        }
		if(format.indexOf("HH") != -1)   { result = result.replace("HH",   String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)));        }
		if(format.indexOf("mm") != -1)   { result = result.replace("mm",   String.format("%02d", cal.get(Calendar.MINUTE)));      }
		if(format.indexOf("ss") != -1)   { result = result.replace("ss",   String.format("%02d", cal.get(Calendar.SECOND)));      }
		if(format.indexOf("SSS") != -1)  { result = result.replace("SSS",  String.format("%03d", cal.get(Calendar.MILLISECOND))); }

		return result;
	}

	/**
	 * 입력받은 이전 데이터 포맷에서 custom 포맷의 형태로 반환,
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String customizeFormat(String date, String format, String custom) throws ParseException {
		return setDateFormat(date, format, custom);
	}

	/**
	 * 입력받은 이전 데이터 포맷에서 시간까찌 표현,
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String getCustomDateTime() throws ParseException {
		String result = "";

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, 0);

		result = cal.get(Calendar.YEAR) + "";
		result += String.format("%02d",(cal.get(Calendar.MONTH) + 1));
		result += String.format("%02d",cal.get(Calendar.DATE));
		result += String.format("%02d",cal.get(Calendar.HOUR_OF_DAY));
		result += String.format("%02d",cal.get(Calendar.MINUTE));
		result += String.format("%02d",cal.get(Calendar.SECOND));
		result += String.format("%02d",cal.get(Calendar.MILLISECOND));

		return result;
	}

	/**
	 * 입력 받을 날짜 포맷 변경
	 * <pre>
	 * 1. 개요 : 입력 받은 문자열 형태의 날짜를 aFormat 형태로 반환
	 * 2. 처리내용 : yyyy, MM, dd, HH, mm, ss, SSS, SSSSSS 형태의 날짜 형식에만 대응한다
	 * </pre>
	 * @Method Name : getConvertFormat
	 * @date : 2019. 4. 10.
	 * @author : Seongcheol
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 4. 10.		Seongcheol				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param date
	 * @param bFormat
	 * @param aFormat
	 * @return
	 * @throws ParseException
	 */
	public static String getConvertFormat(String date, String bFormat, String aFormat) throws ParseException {
		String result = aFormat;
		int index = 0;

		if(result.indexOf("yyyy") != -1) {
			index = bFormat.indexOf("yyyy");
			result = result.replace("yyyy", date.substring(index, index + 4));
		}
		if(result.indexOf("MM") != -1)   {
			index = bFormat.indexOf("MM");
			result = result.replace("MM", date.substring(index, index + 2));
		}
		if(result.indexOf("dd") != -1)   {
			index = bFormat.indexOf("dd");
			result = result.replace("dd", date.substring(index, index + 2));
		}
		if(result.indexOf("HH") != -1)   {
			index = bFormat.indexOf("HH");
			result = result.replace("HH", date.substring(index, index + 2));
		}
		if(result.indexOf("mm") != -1)   {
			index = bFormat.indexOf("mm");
			result = result.replace("mm", date.substring(index, index + 2));
		}
		if(result.indexOf("ss") != -1)   {
			index = bFormat.indexOf("ss");
			result = result.replace("ss", date.substring(index, index + 2));
		}
		if(result.indexOf("SSSSSS") != -1)
			index = bFormat.indexOf("SSSSSS");{
			result = result.replace("SSSSSS", date.substring(index, index + 6));
		}
		if(result.indexOf("SSS") != -1)  {
			index = bFormat.indexOf("SSS");
			result = result.replace("SSS", date.substring(index, index + 3));
		}

		return result;
	}
	public static String getSystemDate(int fType)
	{

		Locale lc = new Locale("Locale.KOREAN","Locale.KOREA");
		TimeZone mySTZ = (TimeZone)TimeZone.getTimeZone ("JST");

		Calendar today = Calendar.getInstance(mySTZ, lc);
		int year = today.get(Calendar.YEAR);
		int mon = today.get(Calendar.MONTH)+1;
		int day = today.get(Calendar.DAY_OF_MONTH);
		int hour = today.get(Calendar.HOUR_OF_DAY);
		int min = today.get(Calendar.MINUTE);
		int sec = today.get(Calendar.SECOND);

		String str = "";

		str += String.valueOf(year);

		if(fType == 1) {
			str += "-";
		}

		if(mon < 10) str += "0";
			str += String.valueOf(mon);

		if(fType == 1) {
			str += "-";
		}

		if(day < 10) str += "0";
			str += String.valueOf(day);

		if(fType == 1) {
			str += " ";
		}

		if(hour < 10) str += "0";
			str += String.valueOf(hour);

		if(fType == 1) {
			str += ":";
		}

		if(min < 10) str += "0";
			str += String.valueOf(min);

		if(fType == 1) {
			str += ":";
		}

		if(sec < 10) str += "0";
			str += String.valueOf(sec);

		return str;

	}

	public static boolean checkAdult19(String date) throws ParseException {
		String standard = DateUtils.getCustomDate(-6935);

		if( Integer.parseInt(standard) > Integer.parseInt(date) ) {
			return false;
		} else {
			return true;
		}
	}
}
