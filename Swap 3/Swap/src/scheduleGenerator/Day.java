package scheduleGenerator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Day is used to store jobs for a given day.
 *
 * @author schneimd.
 *         Created Oct 15, 2012.
 */
//SWAP 1 TEAM 7 SMELL: Data class - Day really is only a container for a list of jobs
//Removing this class would simplify many of the classes that use it and allow for 
public class Day implements Serializable{
	
	//SWAP 1 TEAM 7 SMELL: Primitive Obsession - 
	//There is probably a better way to represent a day of the week
	//And a Job besides encoding it into a string.
	//Changing this could allow for special days, such as holidays,
	//Or allowing jobs to have more properties, such as how long they take to complete.
	
	//	SWAP 2 TEAM 1 REFACTORING FOR ENHANCEMENT FROM BAD SMELL
	//	See Job class for description.
	private String dayOfWeek;
	private ArrayList<Job> jobs = new ArrayList<Job>();
	
	/**
	 * Construct a day with a name and jobs.
	 * 
	 * @param name 
	 *
	 * @param jobs
	 */
	public Day(String name, ArrayList<Object> jobs)
	{
		this.dayOfWeek = name;
		for(Object i:jobs)
		{
			this.jobs.add(new Job((String) i));
		}
	}
	
	/**
	 * Add one jobName.
	 *
	 * @param jobName
	 */
	public void addJob(String jobName) {
		this.jobs.add(new Job(jobName));
	}
	
	/**
	 * Set jobs to new jobs.
	 *
	 * @param jobNames
	 */
	public void setJobs(ArrayList<String> jobNames) {
		this.jobs = new ArrayList<Job>();
		for(String j:jobNames)
		{
			this.jobs.add(new Job(j));
		}
	}
	
	/**
	 * return current jobs.
	 *
	 * @return jobs
	 */
	public ArrayList<String> getJobs() {
		ArrayList<String> jobDescriptions = new ArrayList<String>();
		for(Job j: this.jobs)
		{
			jobDescriptions.add(j.getDescription());
		}
		return jobDescriptions;
	}
	
	/**
	 * Gives the name of this day.
	 *
	 * @return day of week
	 */
	public String getNameOfDay() {
		return this.dayOfWeek;
	}
}
