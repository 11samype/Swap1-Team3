package scheduleGenerator;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

//	SWAP 2 TEAM 1 REFACTORING FOR ENHANCEMENT FROM BAD SMELL
//	See Main for details. Note that the main method has been renamed
//	1. Extract Class was used
//	2. By pulling out the Main method, this allows for an enhancement to
//		allow the user to select which configuration to use. For example,
//		the main currently loads schedule_data.ser.  However, the enhancement
//		may allow them to select from a list of ser files.
//	3. The refactoring was a success, though its important to note that all of
//		the references to the previous Main class were updated to ConfigManager.

// SWAP 3, TEAM 03
// ENHANCEMENT FROM REFACTORING
// 1. The previous team claimed that the ability to choose different .ser files could be added and opened
//	  easily from the main method.
// 2. The refactoring was successful towards building this enhancement because the initializeConfig method
//	  just takes a file name so we were able to add a JFileChooser to select the file before the call is made.
// 3. This enhancement adds value to the system because a user can now load many different files using the
//	  system so a minister from one church might be able to send their schedule to another church and they
//	  would be able to open it without changing the code.
public class Main {

	public static void main(String[] args) {
		//Create a file chooser
		
		ExtendedGregCal.DaysUntilNextHoliday();
//		File file = new File("schedule_data.ser");
//		final JFileChooser fc = new JFileChooser(file);
//		fc.setMultiSelectionEnabled(false);
//
//		//In response to a button click:
//		int returnVal = fc.showOpenDialog(new JFrame());
//
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            file = fc.getSelectedFile();
//            while (!SerFileFilter.accept(file)){
//            	JOptionPane.showMessageDialog(new JFrame(), "Please Choose a file with a \".ser\" extension.");
//            	returnVal = fc.showOpenDialog(new JFrame());
//                if (returnVal == JFileChooser.APPROVE_OPTION) {
//                    file = fc.getSelectedFile();
//                    System.out.println(file.getName());
//                }else
//                {
//                	System.out.println("Open command cancelled by user.");
//                	break;
//                }
//            }
//        } else {
//            System.out.println("Open command cancelled by user.");
//        }
//		ConfigManager.initializeConfig(file.getName());
	}
}
