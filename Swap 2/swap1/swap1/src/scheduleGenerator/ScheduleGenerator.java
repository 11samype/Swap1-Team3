package scheduleGenerator;

/*
 * SWAP 1, TEAM 2 (JORDON/FRANCIS)
 * BONUS FEATURE
 */
public abstract class ScheduleGenerator {
	protected Schedule schedule;
	
	public ScheduleGenerator(Schedule schedule) {
		this.setSchedule(schedule);
	}
	
	/**
	 * Writes the schedule.
	 * @return true if the write was successful, otherwise false.
	 */
	public abstract boolean write();

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
