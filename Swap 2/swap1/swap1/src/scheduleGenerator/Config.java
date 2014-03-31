/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * 
 * @author schneimd
 */
@SuppressWarnings("serial")
public class Config extends JFrame {

	/*
	 * SWAP 1, TEAM2 (JORDON/FRANCIS) SMELL: Large Class - This class is over
	 * 1000 lines and attempts to perform a large variety of tasks FIX: Delegate
	 * tasks to other classes. In particular, there is a lot of code to manage
	 * the GUI which should really be moved to a GUI dedicated class.
	 */

	private boolean firstSelection = true;
	private int numSelected = 0;
	@SuppressWarnings("rawtypes")
	private DefaultListModel[] models;
	// SWAP 2, TEAM 03
	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
	// Feature Envy - add CalendarGUI object for change
	private CalendarGUI gui;

	/**
	 * Used to edit days.
	 * 
	 * @param days
	 */
	/*
	 * SMELL: Shot Gun Surgery if we want to change the way of configuration of
	 * each day. we have to make small changes at the configuration of each day
	 * in this class. FIX: create new class that consist of part that may have
	 * potential changes.
	 */
	@SuppressWarnings("unchecked")
	public Config(ArrayList<Day> days) {
		this.models = new DefaultListModel[7];
		initDyn();
		initComponents();

		for (Day day : days) {
			int dayNum = DayConversion.numForName(day.getNameOfDay()) - 1;
			if (dayNum > -1) {
				this.dayCheckboxes[dayNum].doClick();
				ArrayList<String> jobs = day.getJobs();
				for (String job : jobs) {
					this.dayTabsList[dayNum].addJobElement(job);
					this.dayJobLists[0].setModel(this.dayTabsList[dayNum]
							.getModel());
				}
			} else {
				System.out.println("Day not found");
			}
		}
	}

	/**
	 * Creates new form.
	 */
	public Config() {
		this.models = new DefaultListModel[7];
		initDyn();

		initComponents();
	}

	@SuppressWarnings("rawtypes")
	private void initDyn() {

		this.dayJobLists = new JList[7];
		this.dayTabsList = new DayTab[7];
		this.dayCheckboxes = new DayCheckBox[7];

		for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
			this.dayJobLists[dayIndex] = new JList();
			this.dayTabsList[dayIndex] = new DayTab(new DefaultListModel());
			this.dayCheckboxes[dayIndex] = new DayCheckBox(DayConversion.getNameforNum(dayIndex + 1));
		}

	}

	private void initComponents() {

		/*
		 * SWAP 1, TEAM2 (JORDON/FRANCIS) SMELL: Long Method - This method is
		 * insanely long and does a large number of things. FIX: Abstract some
		 * of these out into different methods.
		 */

		this.configPanel = new JPanel();		
		this.dayTabs = new JTabbedPane();
		
		
		for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
			final int dayInt = dayIndex;
			this.dayCheckboxes[dayIndex].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					dayCheckActionPerformed(evt, DayConversion.getNameforNum(dayInt + 1), dayInt);
				}
			});
		}

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Configuration");
		setPreferredSize(new java.awt.Dimension(801, 87));
		setResizable(false);

		this.daysHeaderLabel = new JLabel("Days:");
		this.daysHeaderLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

		this.nextButton = new JButton("Next");
		this.nextButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});

		this.InitializeLayout();

	}
	
	private void InitializeLayout(){
		ConfigGroupLayout jPanel1Layout = new ConfigGroupLayout(this.configPanel, this.dayCheckboxes, this.nextButton, this.daysHeaderLabel);		
		this.configPanel.setLayout(jPanel1Layout);
		
		ConfigGroupLayout layout = new ConfigGroupLayout(getContentPane(), this.configPanel, this.dayTabs);
		this.getContentPane().setLayout(layout);


		this.dayTabs.getAccessibleContext().setAccessibleName("Days Tab");

		this.pack();
	}// </editor-fold>

	// SWAP 2, TEAM 03
	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
	// Feature Envy
	// Moved to CalendarGUI, made some changes later in this file to account for
	// it

	/*
	 * SWAP 1, TEAM2 (JORDON/FRANCIS)
	 */
	/*
	 * QUALITY CHANGES Though the entire functions will need changing, the
	 * setting of the tab layout was one of the largest portions of the code in
	 * each, and it made sense for it to be its own function.
	 * 
	 * This does add a smell, or rather expand upon one that is already present:
	 * Long Parameter List. The GUI for each tab really needs to be put into a
	 * single object.
	 */
	// private void setTabLayout(JPanel tab, JScrollPane s, JLabel l, JButton
	// addJob, JButton deleteJob, JTextField jobName) {
	// /*
	// * SMELL: Feature Envy: this part of the code is highly related to the
	// CalendarGUI it should be in the CalendarGUI
	// * instead of here
	// * Fix: move the function into the CalendarGUI.
	// *
	// */
	// GroupLayout layout = new GroupLayout(tab);
	// tab.setLayout(layout);
	// layout.setHorizontalGroup(
	// layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	// .addGroup(layout.createSequentialGroup()
	// .addContainerGap()
	// .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 182,
	// javax.swing.GroupLayout.PREFERRED_SIZE)
	// .addGap(18, 18, 18)
	// .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	// .addGroup(layout.createSequentialGroup()
	// .addComponent(l)
	// .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	// .addGroup(layout.createSequentialGroup()
	// .addGap(14, 14, 14)
	// .addComponent(addJob))
	// .addGroup(layout.createSequentialGroup()
	// .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	// .addComponent(jobName, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
	// javax.swing.GroupLayout.PREFERRED_SIZE))))
	// .addComponent(deleteJob))
	// .addContainerGap(431, Short.MAX_VALUE))
	// );
	// layout.setVerticalGroup(
	// layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	// .addGroup(layout.createSequentialGroup()
	// .addContainerGap()
	// .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
	// false)
	// .addGroup(layout.createSequentialGroup()
	// .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	// .addComponent(jobName, javax.swing.GroupLayout.PREFERRED_SIZE,
	// javax.swing.GroupLayout.DEFAULT_SIZE,
	// javax.swing.GroupLayout.PREFERRED_SIZE)
	// .addComponent(l))
	// .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	// .addComponent(addJob)
	// .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
	// javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	// .addComponent(deleteJob))
	// .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE,
	// javax.swing.GroupLayout.DEFAULT_SIZE,
	// javax.swing.GroupLayout.PREFERRED_SIZE))
	// .addContainerGap(25, Short.MAX_VALUE))
	// );
	// }

	/*
	 * SMELL: Duplicated Code: it has a check action performed method, which
	 * involve a lot duplicated code. Fix: make the config class to a abstract
	 * class and have a subclass for every day. if there are difference on the
	 * implementation, the subclass can simply override this method.
	 */

	/**
	 * @param evt
	 * @param dayName
	 * @param dayNum
	 */
	@SuppressWarnings("unchecked")
	private void dayCheckActionPerformed(java.awt.event.ActionEvent evt,
			String dayName, int dayNum) {
		if (((JCheckBox) evt.getSource()).isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			this.models[dayNum] = new DefaultListModel<Object>();
			this.dayJobLists[dayNum].setModel(this.models[dayNum]);
			this.dayTabsList[dayNum] = new DayTab(this.models[dayNum]);
			this.dayTabs.addTab(dayName, this.dayTabsList[dayNum]);
			
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.dayTabsList[dayNum]);
		}

	}

	/**
	 * @param evt
	 */
	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		/*
		 * SWAP 1, TEAM2 (JORDON/FRANCIS) QUALITY CHANGES Again, this would be
		 * even better served by encapsulating the day GUI elements into their
		 * own class. It could also be improved by adding
		 */
		ArrayList<Day> days = new ArrayList<Day>();
		for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
			if (this.dayCheckboxes[dayIndex].isSelected()) {
				days.add(getJobs(this.dayCheckboxes[dayIndex].getText(), dayIndex));
			}
			
		}
		
		if (days.size() > 0) {
			boolean hasJobs = true;
			int i = 0;
			while (hasJobs && i < days.size()) {
				if (days.get(i).getJobs().size() == 0) {
					hasJobs = false;
				}
				i++;
			}
			if (hasJobs) {
				Main.setDays(days);
				Main.wSet = new WorkerSetup();
				Main.toggleWorkerSetup();
				Main.config = this;
				Main.toggleConfig();
			} else {
				JOptionPane.showMessageDialog(this,
						"You must have at least one job each day.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "You have not added any days.");
		}
	}

	private Day getJobs(String name, int num) {
		ArrayList<Object> day = new ArrayList<Object>();
		List<Object> jobs = null;
		jobs = Arrays.asList(this.dayTabsList[num].getModel().toArray());
		day.addAll(jobs);
		return new Day(name, day);
	}

	private void stretch() {
		if (this.numSelected > 0) {
			this.setSize(801, 290);
			this.firstSelection = false;
		} else {
			this.setSize(801, 87);
			this.firstSelection = true;
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Config().setVisible(true);
			}
		});
	}

	public CalendarGUI getGui() {
		return gui;
	}

	public void setGui(CalendarGUI gui) {
		this.gui = gui;
	}

	/*
	 * SWAP 1, TEAM2 (JORDON/FRANCIS) SMELL: Data Clumps - These bunches of data
	 * appear together practically every time any one of them is referenced.
	 * FIX: Make them into their own object.
	 */

	private DayTab[] dayTabsList;
	@SuppressWarnings("rawtypes")
	private JList[] dayJobLists;
	private DayCheckBox[] dayCheckboxes;

	private JTabbedPane dayTabs;
	private JLabel daysHeaderLabel;
	private JPanel configPanel;
	private JButton nextButton;
}
