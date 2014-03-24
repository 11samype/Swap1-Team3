package scheduleGenerator;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.NavigableSet;

/*
 * SWAP 1, TEAM 2 (JORDON/FRANCIS)
 * BONUS FEATURE - Editable Output
 * 
 * The base code did come with a text output, but it wasn't exactly editable.
 * It printed everything all in a few lines, and so it was difficult to add
 * or remove items. The formatting here is much simpler, and since everything
 * is on its own line, much easier to edit. (The code is way simpler too.)
 * 
 * SMELL: Shotgun Surgery. There were only a few classes that didn't have their
 * 	hands buried in the static version of HTMLScheduleGenerator. Little updates
 * 	here and there to the static version were all over the place.
 * 
 * FIX: Make the generator fully able to generate its output given the current
 * 	Schedule object. While doing this, we created the ScheduleGenerator abstract
 * 	class to enable the addition off the text output in a more sensible way. For
 * 	the sake of our own sanity, we also included a method in Schedule that gives
 * 	us the schedule map with a Date object as the key, making arranging things
 * 	much easier.
 */
public class TextScheduleGenerator extends ScheduleGenerator {
	private String outputFileName = "schedule.txt";

	public TextScheduleGenerator(Schedule schedule) {
		super(schedule);
	}
	
	public TextScheduleGenerator(Schedule schedule, String outputFileName) {
		super(schedule);
		this.outputFileName = outputFileName;
	}

	@Override
	public boolean write() {
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(this.outputFileName), "utf-8"));
			writer.write(generate());
		} catch (IOException e) {
			System.out.println("Writing exception: " + e);
			e.printStackTrace();
			return false;
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	private String generate() {
		String s = "";
		NavigableSet<String> days = this.schedule.getSchedule().navigableKeySet();
		NavigableSet<String> duties = null;
		
		for (String date : days) {
			duties = this.schedule.getSchedule().get(date).navigableKeySet();
			s += date + ":\n";
			
			for (String duty : duties) {
				s += duty + " - " + this.schedule.getSchedule().get(date).get(duty).getName() + "\n";
			}
			s += "\n";
		}
		return s;
	}
}
