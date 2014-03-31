package scheduleGenerator;

import javax.swing.JCheckBox;

// SWAP 2, TEAM 03
// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
// 1. Created this class for the checkboxes in the Config class
// 2. More default functionality could be added to this checkbox such as an on click method
// 3. Pretty successful except this class does not do much.
@SuppressWarnings("serial")
public class DayCheckBox extends JCheckBox {
	
	public DayCheckBox(String dayName){
		this.setText(dayName);
		this.setName(dayName.toLowerCase() + "Check"); // NOI18N
	}

}
