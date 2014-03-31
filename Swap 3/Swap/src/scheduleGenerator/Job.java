package scheduleGenerator;

import java.io.Serializable;

public class Job implements Serializable{
	//	SWAP 2 TEAM 1 REFACTORING FOR ENHANCEMENT FROM BAD SMELL
	//	1. Replace data value with object was used
	//	2. The changes allow Job to have additional properties.  This allows for jobs to have
	//		more properties such as hours worked on, pay, etc.
	//	3. The refactoring was marginally successful - the refactoring in the Day class dealt only with job descriptions.
	//		No actual enhancements were added with Job as its own object.
	
	private String jobDescription;
	
	public Job( String description ) {
		this.jobDescription = description;
	}
	
	public String getDescription() {
		return this.jobDescription;
	}

}