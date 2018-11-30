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

/**
 * Created by AnGo on 08.06.2017.
 */
public class DateUtils {
	//    private static final String DATE_PATTERN = "dd.MM.yyyy hh:mm:ss";
	//"(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)\d\d ([0-9]|1[0-1]):[0-5][0-9](:[0-5][0-9])? ?[APap][mM]$"
	private static final String DATE_PATTERN = "dd.MM.yyyy";

	public static final String DATE_PROZORRO_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
	public static final String DATE_PROZORRO_SHORT_FORMAT = "yyyy-MM-dd";
	public static final String DATE_PROZORRO_MAX_DATE_TILL = "3000-01-01";


	private static final DateTimeFormatter DATE_TIME_FORMATTER =
			DateTimeFormatter.ofPattern(DATE_PATTERN);
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);


	/**
	 * Возвращает полученную дату в виде хорошо отформатированной строки.
	 * Используется определённый выше {@link DateUtils#DATE_PATTERN}.
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
	 * Используется определённый выше {@link DateUtils#DATE_PATTERN}.
	 *
	 * @param date         - дата, которая будет возвращена в виде строки ,
	 * @param defaultValue - значение, которое вернуть,
	 *                     если @dateString не дата.
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
	 * Используется определённый выше {@link DateUtils#DATE_PATTERN}.
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
	 * шаблона {@link DateUtils#DATE_PATTERN} в объект {@link LocalDate}.
	 * <p>
	 * Возвращает null, если строка не может быть преобразована.
	 *
	 * @param dateString - дата в виде String
	 * @return объект даты или null, если строка не может быть преобразована
	 */
	public static LocalDate parseToLocalDate(String dateString) {
		try {
			return DATE_TIME_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}


	/**
	 * Преобразует строку, которая отформатирована по правилам
	 * шаблона {@link DateUtils#DATE_PATTERN} в объект {@link LocalDateTime}.
	 * <p>
	 * Возвращает null, если строка не может быть преобразована.
	 *
	 * @param dateString - дата в виде String
	 * @return объект даты или null, если строка не может быть преобразована
	 */
	public static LocalDateTime parseToLocalDateTime(String dateString) {
		try {
			return DATE_TIME_FORMATTER.parse(dateString, LocalDateTime::from);
		} catch (DateTimeParseException e) {
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
	 * @param dateString   - дата в виде String ,
	 * @param defaultValue - значение, которое вернуть,
	 *                     если @dateString не дата.
	 * @return true, если строка является корректной датой
	 */
	public static String getValidStringDate(String dateString, String defaultValue) {
		return (DateUtils.parseToLocalDate(dateString) != null ? dateString : defaultValue);
	}

	public static LocalDateTime getLocalDateTime(Date date) {
		//Date date = new Date();
		if (date == null) {
			date = new Date();
		}
		Instant instant = Instant.ofEpochMilli(date.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

	}

	public static LocalDate getLocalDate(Date date) {
		//Date date = new Date();
		if (date == null) {
			date = new Date();
		}
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		return localDate;

	}

	public static String getDatePattern() {
		return DATE_PATTERN;
	}

	public static DateTimeFormatter getDateTimeFormatter() {
		return DATE_TIME_FORMATTER;
	}

	public static Date addToDate(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		Date newDate = calendar.getTime();
		//System.out.println("1 min later: " + newDate);
		return newDate;
	}
	public static Date addDaysToDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		Date newDate = calendar.getTime();
		//System.out.println("1 min later: " + newDate);
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

	public static Date parseDateFromString(String stringDate, String dateFormat) throws java.text.ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);


		Date date = simpleDateFormat.parse(stringDate);
		return date;
	}

	public static String parseDateToString(Date date, String dateFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}

	public static Date parseDateToFormate(Date date, String dateFormat) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.parse(simpleDateFormat.format(date));
	}


	public static String checkDatesForPeriod(Date dateFrom, Date dateTill) {
		if (dateFrom == null) {
			return "Дата \"С\" не может быть пустой!";
		}
		if (dateTill == null) {
			return "Дата \"ПО\" не может быть пустой!";

		}
		if (dateFrom.compareTo(dateTill) > 0) {
			return "Дата \"С\" не может быть больше даты \"ПО\"!";
		}
		return null;
	}

	public static String checkDatesForPeriod(LocalDate dateFrom, LocalDate dateTill) {
		if (dateFrom == null) {
			return "Дата \"С\" не может быть пустой!";
		}
		if (dateTill == null) {
			return "Дата \"ПО\" не может быть пустой!";

		}
		if (dateFrom.compareTo(dateTill) > 0) {
			return "Дата \"С\" не может быть больше даты \"ПО\"!";
		}
		return null;
	}
}
