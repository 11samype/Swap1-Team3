package scheduleGenerator;

// SWAP 2, TEAM 03
// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
// 1. Created this class to have access to the static day manipulation methods
// 2. more day manipulations such as date to sentence or something like that
// 3. This was a successful refactoring because it gives access to the static methods
//	  for date manipulation
public class DayConversion {

	public static int numForName(String nameOfDay) {
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
	
	public static String getNameforNum(int n) {
		switch (n) {
		case (1):
			return "Sunday";
		case (2):
			return "Monday";
		case (3):
			return "Tuesday";
		case (4):
			return "Wednesday";
		case (5):
			return "Thursday";
		case (6):
			return "Friday";
		case (7):
			return "Saturday";
		}
		return null;
	}
}
