package neoonki.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private final static String DEFUALT_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	public final static String getCurrentStringDate(String format) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	public final static String getCurrentStringDate() {
		return getCurrentStringDate(DEFUALT_DATE_FORMAT);
	}
}
