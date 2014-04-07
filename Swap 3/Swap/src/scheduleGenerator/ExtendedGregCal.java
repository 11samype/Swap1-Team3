package scheduleGenerator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ExtendedGregCal extends GregorianCalendar {
	private static final long serialVersionUID = 6276733575936162057L;
	
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

	// SWAP 3, TEAM 03
	// ENHANCEMENT FROM REFACTORING
	// 1. The previous team claimed that the ability to add different methods to the calendar class such as
	//	  days until next Holiday because the made this extended class.
	// 2. The refactoring was successful towards building this enhancement because the class existed so the method 
	//	  just needed to be added here but the feature was somewhat difficult to make becuase everything was done
	//	  from scratch.
	// 3. This enhancement adds some value to the system because the user could calculate how many days until the 
	//	  the next holiday but in most cases that is not very helpful.
	public static int DaysUntilNextHoliday(){
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int minDays = 365;
		for (Method method : Holidays.class.getMethods()){
			Boolean okay = true;
			Object o = null;
				try {
					o = method.invoke(null, year);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block 
					okay = false;
					//e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block 
					okay = false;
					//e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block 
					okay = false;
					//e.printStackTrace();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block 
					okay = false;
					//e.printStackTrace();
				} 
				
				if(okay){
					System.out.println(method.getName());
					System.out.println(((Date) o).toString());
					
					int days = daysBetween(GregorianCalendar.getInstance().getTime(), ((Date) o)) + 1;
					int temp = Math.min(minDays, days);
					if (temp > -1){
						minDays = temp;
					}
				}
		}
		return minDays;
	}
	
	private static int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
}
}
