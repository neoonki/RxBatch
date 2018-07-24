package neoonki.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private final static String DEFUALT_DATE_FORMAT = "yyyyMMddHHmmssSSS";
	public final static String getCurrentStringDate(String format) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	public final static Long getCurrentLongDate(String format) {
		return Long.parseLong(getCurrentStringDate(format));
	}
	
	public final static Long getCurrentLongDate() {
		return Long.parseLong(getCurrentStringDate(DEFUALT_DATE_FORMAT));
	}
	
	public final static String getCurrentStringDate() {
		return getCurrentStringDate(DEFUALT_DATE_FORMAT);
	}
}
