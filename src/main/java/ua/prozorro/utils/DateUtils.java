package ua.prozorro.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by AnGo on 08.06.2017.
 */
public class DateUtils {
	public static final String DATE_PROZORRO_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
	public static final String DATE_PROZORRO_SHORT_FORMAT = "yyyy-MM-dd";
	public static final String DATE_PROZORRO_MAX_DATE_TILL = "3000-01-01";
	public static final String DATE_PATTERN_DD_MM_YYYY = "dd.MM.yyyy";
	public static final String PROZORRO_DATE_FORMATE_WITHOUT_SSS = "yyyy-MM-dd'T'HH:mm:ssXXX";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN_DD_MM_YYYY);
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN_DD_MM_YYYY);
	
	/**
	 * Возвращает полученную дату в виде хорошо отформатированной строки.
	 * Используется определённый выше {@link DateUtils#DATE_PATTERN_DD_MM_YYYY}.
	 *
	 * @param date - дата, которая будет возвращена в виде строки
	 * @return отформатированную строку
	 */
	public static String format(LocalDateTime date) {
		if (date == null) {
			return null;
		}
		return DATE_TIME_FORMATTER.format(date);
	}
	
	/**
	 * Возвращает полученную дату в виде хорошо отформатированной строки.
	 * Используется определённый выше {@link DateUtils#DATE_PATTERN_DD_MM_YYYY}.
	 *
	 * @param date - дата, которая будет возвращена в виде строки ,
	 * @param defaultValue - значение, которое вернуть,
	 * если @dateString не дата.
	 * @return отформатированную строку
	 */
	public static String format(LocalDateTime date, String defaultValue) {
		if (date == null) {
			return defaultValue;
		}
		return DATE_TIME_FORMATTER.format(date);
	}
	
	
	/**
	 * Возвращает полученную дату в виде хорошо отформатированной строки.
	 * Используется определённый выше {@link DateUtils#DATE_PATTERN_DD_MM_YYYY}.
	 *
	 * @param date - дата, которая будет возвращена в виде строки
	 * @return отформатированную строку
	 */
	public static String format(LocalDate date) {
		if (date == null) {
			return null;
		}
		return DATE_TIME_FORMATTER.format(date);
	}
	
	/**
	 * Преобразует строку, которая отформатирована по правилам
	 * шаблона {@link DateUtils#DATE_PATTERN_DD_MM_YYYY} в объект {@link LocalDate}.
	 * <p>
	 * Возвращает null, если строка не может быть преобразована.
	 *
	 * @param dateString - дата в виде String
	 * @return объект даты или null, если строка не может быть преобразована
	 */
	public static LocalDate parseToLocalDate(String dateString) {
		try {
			return DATE_TIME_FORMATTER.parse(dateString, LocalDate::from);
		}
		catch (DateTimeParseException e) {
			return null;
		}
	}
	
	
	/**
	 * Преобразует строку, которая отформатирована по правилам
	 * шаблона {@link DateUtils#DATE_PATTERN_DD_MM_YYYY} в объект {@link LocalDateTime}.
	 * <p>
	 * Возвращает null, если строка не может быть преобразована.
	 *
	 * @param dateString - дата в виде String
	 * @return объект даты или null, если строка не может быть преобразована
	 */
	public static LocalDateTime parseToLocalDateTime(String dateString) {
		try {
			return DATE_TIME_FORMATTER.parse(dateString, LocalDateTime::from);
		}
		catch (DateTimeParseException e) {
			return null;
		}
	}
	
	/**
	 * Проверяет, является ли строка корректной датой.
	 *
	 * @param dateString - дата в виде String
	 * @return true, если строка является корректной датой
	 */
	public static boolean validDate(String dateString) {
		// Пытаемся разобрать строку.
		return DateUtils.parseToLocalDate(dateString) != null;
	}
	
	/**
	 * Проверяет, является ли строка корректной датой,
	 * и возвращает строку или же .
	 *
	 * @param dateString - дата в виде String ,
	 * @param defaultValue - значение, которое вернуть,
	 * если @dateString не дата.
	 * @return true, если строка является корректной датой
	 */
	public static String getValidStringDate(String dateString, String defaultValue) {
		return (DateUtils.parseToLocalDate(dateString) != null ? dateString : defaultValue);
	}
	
	public static LocalDateTime getLocalDateTime(Date date) {
		if (date == null) {
			date = new Date();
		}
		Instant instant = Instant.ofEpochMilli(date.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		
	}
	
	public static LocalDate getLocalDate(Date date) {
		if (date == null) {
			date = new Date();
		}
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		return localDate;
		
	}
	
	public static String getDatePatternDdMmYyyy() {
		return DATE_PATTERN_DD_MM_YYYY;
	}
	
	public static DateTimeFormatter getDateTimeFormatter() {
		return DATE_TIME_FORMATTER;
	}
	
	public static Date addToDate(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		Date newDate = calendar.getTime();
		return newDate;
	}
	
	public static Date addDaysToDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		Date newDate = calendar.getTime();
		return newDate;
	}
	
	public static Date getDateFromLocalDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static String dateToString(Date date) {
		if (date == null) {
			return "";
		}
		return DATE_FORMAT.format(date);
	}
	
	public static Date parseProzorroPageDateFromString(String stringDate, String dateFormat)
	throws java.text.ParseException {
		Date date;
		try {
			date = parseDateFromString(stringDate, dateFormat);
		}
		catch (ParseException e) {
			date = parseDateFromString(stringDate, PROZORRO_DATE_FORMATE_WITHOUT_SSS);
		}
		return date;
	}
	
	public static String parseProzorroPageDateToString(Date date, String dateFormat) throws java.text.ParseException {
		String stringDate = parseDateToString(date, dateFormat);
		return stringDate;
	}
	
	public static Date parseDateFromString(String stringDate, String dateFormat) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		Date date = simpleDateFormat.parse(stringDate);
		return date;
	}
	
	public static String parseDateToString(Date date, String dateFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}
	
	public static Date parseDateToFormat(Date date, String dateFormat) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.parse(simpleDateFormat.format(date));
	}
	
	public static boolean isValidForPeriod(LocalDate dateFrom, LocalDate dateTill) {
		return dateFrom != null && dateTill != null && dateFrom.compareTo(dateTill) <= 0;
	}
	
	public static String getTextTime(long milliseconds) {
		long timeDifference = milliseconds / 1000;
		long millisec = milliseconds % 1000;
		long hour = (int) (timeDifference / (3600));
		long minute = (int) ((timeDifference - (hour * 3600)) / 60);
		long second = (int) (timeDifference - (hour * 3600) - minute * 60);
		
		return String.format("%02dh:%02dm:%02ds:%dms", hour, minute, second, millisec);
	}
	
	public static long daysBetween(Date dateFrom, Date dateTill) {
		long diffInMillies = Math.abs(dateTill.getTime() - dateFrom.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}
	
	public static long getHoursFromDate(Date date) {
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
}
