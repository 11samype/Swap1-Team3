package scheduleGenerator;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ExtendedGregCal extends GregorianCalendar {
	
	public ExtendedGregCal() {
		super();
	}
	
	public ExtendedGregCal(int year, int month, int day) {
		super(year,month,day);
	}
	
	public static String DayString(int day) {
		return new DateFormatSymbols().getWeekdays()[day];
	}
	
	public static int DayInt(String nameOfDay) {
		int dayNum = 0;
		if (nameOfDay.equals("Sunday")) {
			dayNum = 1;
		} else if (nameOfDay.equals("Monday")) {
			dayNum = 2;
		} else if (nameOfDay.equals("Tuesday")) {
			dayNum = 3;
		} else if (nameOfDay.equals("Wednesday")) {
			dayNum = 4;
		} else if (nameOfDay.equals("Thursday")) {
			dayNum = 5;
		} else if (nameOfDay.equals("Friday")) {
			dayNum = 6;
		} else if (nameOfDay.equals("Saturday")) {
			dayNum = 7;
		}
		return dayNum;
	}

}
