package scheduleGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Used to store predicted days and generate new days.
 * 
 * @author schneimd. Created Oct 18, 2012.
 */
public class Schedule extends Thread implements Serializable {

	private ArrayList<Worker> workers;
	private ArrayList<Day> days;
	private TreeMap<String, TreeMap<String, Worker>> schedule;
	private GregorianCalendar cal;
	private HashMap<Integer, ArrayList<Worker>> workerIndices;
	private boolean workerForEveryJob = true;

	/**
	 * Used to construct an initial schedule, used if one does not exist.
	 * 
	 * @param daySlots
	 * @param wrks
	 */
	public Schedule(ArrayList<Day> daySlots, ArrayList<Worker> wrks) {
		this.workers = wrks;
		this.days = daySlots;
		this.workerIndices = new HashMap<Integer, ArrayList<Worker>>();
		for (int i = 1; i <= 7; i++) {
			this.workerIndices.put(i, new ArrayList<Worker>());
		}
		this.generateIndices();

		// Key is year/month/day format and item is a hashmap with key nameOfJob
		// and item Worker
		this.schedule = new TreeMap<String, TreeMap<String, Worker>>();

		this.cal = new GregorianCalendar();

		this.calculateNextMonth();
	}

	@Override
	public void run() {
		this.calculateNextMonth();
	}

	/**
	 * returns workers in schedule.
	 * 
	 * @return workers
	 */
	public ArrayList<Worker> getWorkers() {
		return this.workers;
	}

	private void generateIndices() {
		for (int i = 0; i < this.workers.size(); i++) {
			for (Day day : this.workers.get(i).getDays()) {
				int numDay = this.numForName(day.getNameOfDay());
				this.workerIndices.get(numDay).add(this.workers.get(i));
			}
		}
	}

	/**
	 * Calculates another month of schedule based on workers availability.
	 * 
	 */
	private synchronized void calculateNextMonth() {

		int initialSize = this.schedule.size();

		// SWAP 1, TEAM 03
		
		// QUALITY CHANGES
		// moved setup for months when schedule previously generated to new function
		
		// If the schedule has already been generated
		if (this.schedule.size() > 0) {
			setUpNextMonthWithGeneratedSchedule();
		}

		// Used to see if month changes
		int currentMonth = this.cal.get(Calendar.MONTH);

		int daysInMonth = 0;
		ArrayList<Integer> numOfJobs = new ArrayList<Integer>();

		// While still in the current month generate a schedule for each day
		while (currentMonth == this.cal.get(Calendar.MONTH)) {

			for (Day day : this.days) {

				if (this.cal.get(Calendar.DAY_OF_WEEK) == this.numForName(day
						.getNameOfDay())) {

					TreeMap<String, Worker> jobsWithWorker = new TreeMap<String, Worker>();
					ArrayList<String> workersWorking = new ArrayList<String>();

					ArrayList<String> jobsInOrder = day.getJobs();

					// Used for html later

					daysInMonth++;
					numOfJobs.add(jobsInOrder.size());

					for (String job : jobsInOrder) {

						ArrayList<Worker> workersForJob = new ArrayList<Worker>();

						for (Worker worker : this.workerIndices.get(this
								.numForName(day.getNameOfDay()))) {
							
							// SWAP 1, TEAM 03
							
							// QUALITY CHANGES
							// moved all steps for finding all workers for a job to separate function
							
							workersForJob = findWorkersForJob(worker, day, job, workersWorking, workersForJob);
							
						}
						
						if (workersForJob.size() > 0) {
							
							// SWAP 1, TEAM 03
							
							// QUALITY CHANGES
							// moved all steps for finding a single worker for a job to a separate function
							
							Worker workerForJob = findWorkerForJob(workersForJob, job);
							
							// SWAP 1, TEAM 03
							
							// BAD SMELLS
							// Data Clumps
							// These seem to get modified together often, they could be made into their own object
							
							jobsWithWorker.put(job, workerForJob);
							workersWorking.add(workerForJob.getName());
							workerForJob.addWorkedJob(job);
						} else {
							
							// SWAP 1, TEAM 03
							
							// QUALITY CHANGES
							// moved handling case where there are not any workers for a job to a new function
							
							jobsWithWorker = noWorkersForJob(jobsWithWorker, job, day);
							break;
						}

					}
					String date = computeDate();
					this.schedule.put(date, jobsWithWorker);
					break; // Breaks so it doesn't check the other days
				}
			}
			this.cal.add(Calendar.DATE, 1);
		}
		HTMLGenerator.makeTable(daysInMonth, numOfJobs);
		// Calls itself if there aren't many days generated
		// For instance if the date it was created is the last day of the
		// month it would only makes one day of schedule.
		if (this.schedule.size() - initialSize < 2 && !this.workerForEveryJob) {
			this.calculateNextMonth();
		}

		Main.dumpConfigFile();
	}
	
	private TreeMap<String, Worker> noWorkersForJob(TreeMap<String, Worker> jobsWithWorker, String job, Day day) {
		jobsWithWorker.put(job, new Worker("Empty",
				new ArrayList<Day>()));
		JOptionPane
				.showMessageDialog(
						new JFrame(),
						"No workers are able to work as a(n) "
								+ job + " on "
								+ day.getNameOfDay());
		this.workerForEveryJob = false;
		
		return jobsWithWorker;
		
	}

	private Worker findWorkerForJob(ArrayList<Worker> workersForJob, String job) {
		Worker workerForJob = workersForJob
				.get(new Random().nextInt(workersForJob
						.size()));
		for (Worker w : workersForJob) {
			if (w.numWorkedForJob(job) < workerForJob
					.numWorkedForJob(job)) {
				workerForJob = w;
			}
		}
		
		return workerForJob;
		
	}

	private ArrayList<Worker> findWorkersForJob(Worker worker, Day day, String job, ArrayList<String> workersWorking, ArrayList<Worker> workersForJob) {
		Day workerDay = worker.getDayWithName(day
				.getNameOfDay());
		// SWAP 1, TEAM 03
		//Removed some logic to implement the bonus feature so that it adds all works to the job so it can select the one that has worked last.
		if (!workersWorking.contains(worker
						.getName())) {
			workersForJob.add(worker);

		}
		return workersForJob;
		
	}

	private String computeDate() {
		
		String date = this.cal.get(Calendar.YEAR)
				+ "/"
				+ String.format("%02d",
						(this.cal.get(Calendar.MONTH) + 1))
				+ "/"
				+ String.format("%02d",
						this.cal.get(Calendar.DAY_OF_MONTH));
		
		return date;
	}
	
	private void setUpNextMonthWithGeneratedSchedule() {
		
		String lastDateMade = this.schedule.lastKey();
		String[] parts = lastDateMade.split("/");
		int year = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]) - 1;
		int day = Integer.parseInt(parts[2]);
		this.cal = new GregorianCalendar(year, month, day);
		int tempNum = this.cal.get(Calendar.MONTH);
		while (tempNum == this.cal.get(Calendar.MONTH)) {
			this.cal.add(Calendar.DATE, 1);
		}
		
	}

	private int numForName(String nameOfDay) {
		
		// SWAP 1, TEAM 03
		
		// BAD SMELLS
		// Switch Statements
		// Using a built in method or polymorphism would fix this.
		
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

	// /**
	// * Returns the month/day/year of next date with the name of day.
	// *
	// * @param nameOfDay
	// * @return string of year/month/day format
	// */
	// private String getNextDate(String nameOfDay) {
	// int dayNum = numForName(nameOfDay);
	// GregorianCalendar tempCal = (GregorianCalendar) this.cal.clone();
	//
	// tempCal.add(Calendar.DATE, 1);
	// while (tempCal.get(Calendar.DAY_OF_WEEK) != dayNum) {
	// tempCal.add(Calendar.DATE, 1);
	// }
	// return String.valueOf(tempCal.get(Calendar.YEAR)) + "/" +
	// String.valueOf(tempCal.get(Calendar.MONTH)) + "/"
	// + String.valueOf(tempCal.get(Calendar.DAY_OF_MONTH));
	// }

	/**
	 * Returns the schedule.
	 * 
	 * @return HashMap schedule
	 */
	public TreeMap<String, TreeMap<String, Worker>> getSchedule() {
		return this.schedule;
	}

}
