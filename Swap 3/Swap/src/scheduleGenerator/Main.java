package scheduleGenerator;

//	SWAP 2 TEAM 1 REFACTORING FOR ENHANCEMENT FROM BAD SMELL
//	See Main for details. Note that the main method has been renamed
//	1. Extract Class was used
//	2. By pulling out the Main method, this allows for an enhancement to
//		allow the user to select which configuration to use. For example,
//		the main currently loads schedule_data.ser.  However, the enhancement
//		may allow them to select from a list of ser files.
//	3. The refactoring was a success, though its important to note that all of
//		the references to the previous Main class were updated to ConfigManager.
public class Main {

	public static void main(String[] args) {
		ConfigManager.initializeConfig("schedule_data.ser");
	}
}
