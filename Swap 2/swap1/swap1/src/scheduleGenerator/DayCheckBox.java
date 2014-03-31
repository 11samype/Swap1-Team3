package scheduleGenerator;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class DayCheckBox extends JCheckBox {
	
	public DayCheckBox(String dayName){
		this.setText(dayName);
		this.setName(dayName.toLowerCase() + "Check"); // NOI18N
	}

}
