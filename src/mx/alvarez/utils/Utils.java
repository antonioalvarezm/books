package mx.alvarez.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import mx.alvarez.commons.Constants;

public class Utils {

	private static Logger logger = Logger.getLogger(Utils.class);

	/**
	 * 
	 * @param calendar
	 * @return
	 */
	public static java.sql.Date gregorianCalendarToDate(XMLGregorianCalendar calendar) {
		java.sql.Date d = null;
		try {
			d = new java.sql.Date(calendar.toGregorianCalendar().getTimeInMillis());
		} catch (Exception e) {
			logger.error(e);
		}
		return d;
	}

	/**
	 * 
	 * @param xmlCalendar
	 * @return
	 */
	public static Calendar getCalendar(XMLGregorianCalendar xmlCalendar) {
		TimeZone timeZone = xmlCalendar.getTimeZone(xmlCalendar.getTimezone());
		Calendar calendar = Calendar.getInstance(timeZone);
		calendar.set(Calendar.YEAR, xmlCalendar.getYear());
		calendar.set(Calendar.MONTH, xmlCalendar.getMonth() - 1);
		calendar.set(Calendar.DATE, xmlCalendar.getDay());
		if (xmlCalendar.getHour() > 0) {
			calendar.set(Calendar.HOUR_OF_DAY, xmlCalendar.getHour());
			calendar.set(Calendar.MINUTE, xmlCalendar.getMinute());
			calendar.set(Calendar.SECOND, xmlCalendar.getSecond());
		}
		return calendar;
	}

	public static XMLGregorianCalendar getXMLGregorianCalendar(String d) {
		XMLGregorianCalendar xmlGregCal = null;
		try {
			xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(d);
		} catch (Exception e) {
			logger.error(e);
		}
		return xmlGregCal;
	}
	
	public static Calendar stringToCalendar(String f) {
		return getCalendar(getXMLGregorianCalendar(f));
	}

	public static String calendarToString(Calendar c) {
		return calendarToString(c, Constants.DATE_YYYYMMDD);
	}

	/**
	 * 
	 * @param c
	 * @param format
	 * @return
	 */
	public static String calendarToString(Calendar c, String format) {
		String s = null;
		try {
			s = dateToString(c.getTime(), format);
		} catch (Exception e) {
			logger.error(e);
			s = null;
		}
		return s;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		try {
			SimpleDateFormat f = new SimpleDateFormat(format);
			return f.format(date);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static int stringToInt(String number) {
		int i = 0;
		try {
			if (number != null) {
				if (!number.isEmpty()) {
					i = new Integer(stripCommasGetString(number));
				}
			}
		} catch (Exception e) {
			logger.warn(e);
			i = 0;
		}
		return i;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static BigDecimal stringToBigDecimal(String number) {
		BigDecimal bd = null;
		try {
			bd = new BigDecimal(stripCommasGetString(number));
		} catch (Exception e) {
			logger.error(e);
			bd = null;
		}
		return bd;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static String stripCommasGetString(String number) {
		StringBuffer buffer = new StringBuffer();
		try {
			if (!number.isEmpty()) {
				for (int u = 0; u < number.length(); u++) {
					char c = number.charAt(u);
					switch (c) {
					case ',':
						break;
					case ' ':
						break;
					case ';':
						break;
					case '$':
						break;
					case 'M':
						break;
					case 'N':
						break;
					default:
						buffer.append(c);
						break;
					}
				}
			} else {
				buffer.append("0.0");
			}
		} catch (Exception e) {
			logger.warn(e);
		}
		return buffer.toString();
	}

}
