package scheduleGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NavigableSet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * This class handles the interaction of one frame to another as well as
 * handling initialization.
 * 
 * @author Mason Schneider and Orion Martin. Created Oct 8, 2012.
 */
public class Main {

	private static ArrayList<Day> days;
	private static ArrayList<Worker> workers;
	private static File path = new File("schedule_data.ser");
	
	/**
	 * Configures days.
	 */
	static Config config;
	/**
	 * Configures workers.
	 */
	static WorkerSetup wSet;
	/**
	 * Displays schedule.
	 */
	static CalendarGUI cal;
	private static Schedule schedule;

	/**
	 * Program starts here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		path = new File("schedule_data.ser");
		config = new Config();
		
		//Code to open the config file.
		
		try {
			recallConfigFile();
			if(getSchedule() != (null)){
				cal = new CalendarGUI(getSchedule());
				//config.setVisible(true);
				cal.setVisible(true);
			} else{
				// SWAP 2, TEAM 03
				// REFACTORING FOR ENHANCMENT FROM BAD SMELL
				// Feature Envy
				// See Config.java
				
				config.setGui(new CalendarGUI(getSchedule()));
				config.setVisible(true);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			
		}
	}

	/**
	 * Changes visible of config.
	 * 
	 */
	public static void toggleConfig() {
		config.setVisible(!config.isVisible());
	}

	/**
	 * Changes visible of calendar.
	 * 
	 */
	public static void toggleCalendar() {
		cal.setVisible(!cal.isVisible());
	}

	/**
	 * Changes visible of worker setup.
	 * 
	 */
	public static void toggleWorkerSetup() {
		if (wSet != null) {
			wSet.setVisible(!wSet.isVisible());
		}
	}

	/**
	 * Returns the value of the field called 'schedule'.
	 * 
	 * @return Returns the schedule.
	 */
	public static Schedule getSchedule() {
		return Main.schedule;
	}

	/**
	 * Sets the field called 'schedule' to the given value.
	 * 
	 * @param schedule
	 *            The schedule to set.
	 */
	public static void setSchedule(Schedule schedule) {
		Main.schedule = schedule;
	}

	/**
	 * Sets the value of workers.
	 * 
	 * @return workers
	 */
	public static ArrayList<Worker> getWorkers() {
		return workers;
	}

	/**
	 * Sets workers.
	 * 
	 * @param w
	 */
	public static void setWorkers(ArrayList<Worker> w) {
		workers = w;
	}

	/**
	 * Returns the value of the field called 'days'.
	 * 
	 * @return Returns the days.
	 */
	public static ArrayList<Day> getDays() {
		return days;
	}

	/**
	 * Sets the field called 'days' to the given value.
	 * 
	 * @param d
	 */
	public static void setDays(ArrayList<Day> d) {
		days = d;
	}
	
	/**
	 * Dumps data to the file schedule_data.ser.
	 *
	 */
	public static void dumpConfigFile(){
		
		try {
			path.delete();
			path.createNewFile();
			FileOutputStream dumpConfig = new FileOutputStream(path);
			ObjectOutputStream fileStore = new ObjectOutputStream(dumpConfig);
			fileStore.writeObject(days);
			fileStore.writeObject(workers);
			fileStore.writeObject(schedule);
			fileStore.close();
			dumpConfig.close();
			
			System.out.println("Stored");

		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/*
	 * ADDITIONAL FEATURE: we enable the system to save the schedule to the excel speadsheet
	 * 
	 */
	public static void generateExcel(){
		WritableWorkbook wworkbook;
	      try {
			wworkbook = Workbook.createWorkbook(new File("schedule.xls"));
			WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
			NavigableSet<String> days = schedule.getSchedule().navigableKeySet();
			NavigableSet<String> duties = null;
			int rowCount = 0;
			for (String date : days) {
				duties = schedule.getSchedule().get(date).navigableKeySet();
				for (String duty : duties) {
					Label label = new Label( 0, rowCount, date);
					Label label2 = new Label( 1, rowCount,duty);
					Label label3 = new Label( 2, rowCount,schedule.getSchedule().get(date).get(duty).getName());
					wsheet.addCell(label);
					wsheet.addCell(label2);
					wsheet.addCell(label3);
					rowCount++;
				}
			}
		      wworkbook.write();
		      wworkbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	
	// SWAP 2, TEAM 03
	// FURTHER ELABORATION
	// Added option to save as XML, this is in addition to saving as Excel
	public static void generateXML(){
		FileOutputStream fos = null;
		try {
			XStream xstream = new XStream(new DomDriver());
			
			NavigableSet<String> days = schedule.getSchedule().navigableKeySet();
			NavigableSet<String> duties = null;
			
			String xmlDoc =  "";
			
			for (String date : days) {
				duties = schedule.getSchedule().get(date).navigableKeySet();
				for (String duty : duties) {
									
					String xml = xstream.toXML(date).concat("\n");
					
					xmlDoc = xmlDoc.concat(xml);
					
					String xml2 = xstream.toXML(duty).concat("\n");
					
					xmlDoc = xmlDoc.concat(xml2);
					
					String xml3 = xstream.toXML(schedule.getSchedule().get(date).get(duty).getName()).concat("\n");
					
					xmlDoc = xmlDoc.concat(xml3);
	
				}
			}
			
			fos = new FileOutputStream("calendar.xml");
			fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
			byte[] bytes = xmlDoc.getBytes("UTF-8");
			fos.write(bytes);
			
		} catch (Exception e) {
			System.err.println("Error in XML Write: " + e.getMessage());
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	      
	}
	
	
	/**
	 * Recalls data from schedule_data.ser.
	 *
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void recallConfigFile() throws ClassNotFoundException, IOException{
		if(path.exists()) {
			FileInputStream recallConfig = new FileInputStream(path);
			ObjectInputStream fileRecall = new ObjectInputStream(recallConfig);
			days = (ArrayList<Day>) fileRecall.readObject();
			workers = (ArrayList<Worker>) fileRecall.readObject();
			schedule = (Schedule) fileRecall.readObject();
			
			fileRecall.close();
			recallConfig.close();
		}
	}
}
