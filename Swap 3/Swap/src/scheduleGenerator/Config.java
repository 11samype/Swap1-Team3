/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.awt.Dimension;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

/**
 * 
 * @author schneimd
 */

// SWAP 1 TEAM 7 SMELL Large Class - This class is trying to do everything
// relating to
// the configuration GUI, instead this class should be split up into more
// classes to better
// deal with different GUI variations.

// SWAP 2 Team 01
// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
// 1.There were several refactoring methods used here. First, I used the Extract
// Class method to take out a method that was testing configurations and put it
// into its own class, where that is its sole responsibility.Then I used the
// Extract Method to minimize the size of initComponents and the
// nextButtonActionPerformed methods. This got rid of a lot of the duplicate
// code and helped with the large class smell desribed above, so this class is
// now shorter and its purpose is to set up the configuration GUI.
// 2.By removing unnecessary responsibilities from this class and making
// configuring the configuration GUI its sole purpose, it is now easier to make
// changes to the GUI and administer when and where those changes should occur.
// Getting rid of a lot of the duplicate code also makes it easier to
// extend/change the GUI components and functionality, since those changes would
// only need to be made from one place now.
// 3. I would say the refactoring was successful. This class now has only one
// responsibility, which is to set up the Configuration GUI and its controls.
// However, this is still a large class. There are still places in here where
// duplicate code could probably be removed and it could possibly be refactored
// even further to seperate the GUI from the actions that should be performed on
// button clicks and check box checks.

public class Config extends javax.swing.JFrame {

	private boolean firstSelection = true;
	private int numSelected = 0;
	private DefaultListModel[] models;

	/**
	 * Used to edit days.
	 * 
	 * @param days
	 */
	public Config(ArrayList<Day> days) {
		this.models = new DefaultListModel[7];
		initDyn();
		initComponents();

		// SWAP 1, TEAM 7 - QUALITY CHANGES - Removed duplication
		// By using the arrays, we can avoid having to have separate
		// code for each item.
		for (Day day : days) {
			String weekdayName = day.getNameOfDay();
			int dayIndex = WEEK_INDICIES.valueOf(weekdayName).ordinal();
			this.weekCheck[dayIndex].doClick();
			List<String> jobs = day.getJobs();
			for (String job : jobs) {
				this.models[dayIndex].addElement(job);
				this.weekJobList[dayIndex].setModel(this.models[dayIndex]);
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

	private void setUpDayChecks() {
		for (int i = 0; i < weekCheck.length; i++) {
			this.weekCheck[i] = new javax.swing.JCheckBox();
			this.weekCheck[i]
					.setText(new DateFormatSymbols().getWeekdays()[i + 1]);
			this.weekCheck[i].setName("" + i);
			this.weekCheck[i]
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							onDayActionPerformed(evt);
						}
					});
		}
	}

	// SWAP 1, TEAM 7 - QUALITY CHANGES - Removed duplication
	// By using the arrays, rather than individual indexes, we
	// can avoid having a ton of duplicated code
	private void initDyn() {
		for (int i = 0; i < this.weekTab.length; i++) {
			this.weekScrollPane[i] = new JScrollPane();
			this.weekScrollPane[i].setPreferredSize(new Dimension(185, 150));
			this.weekJobList[i] = new JList();
			this.weekJobName[i] = new JTextField();
			this.weekLabel[i] = new JLabel();
			this.weekAddJob[i] = new JButton();
			this.weekDeleteJob[i] = new JButton();
			this.weekTab[i] = new javax.swing.JPanel();
		}
	}

	private void initComponents() {

		this.jPanel1 = new javax.swing.JPanel();
		this.jLabel1 = new javax.swing.JLabel();
		this.nextButton = new javax.swing.JButton();
		this.dayTabs = new javax.swing.JTabbedPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Configuration");
		setPreferredSize(new java.awt.Dimension(801, 87));
		setResizable(false);
		this.jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		this.jLabel1.setText("Days:");
		setUpDayChecks();

		this.nextButton.setText("Next");
		this.nextButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(this.jLabel1)
										.addGap(18, 18, 18)
										.addComponent(this.weekCheck[0])
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.weekCheck[1],
												javax.swing.GroupLayout.PREFERRED_SIZE,
												71,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(this.weekCheck[2])
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.weekCheck[3],
												javax.swing.GroupLayout.PREFERRED_SIZE,
												100,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(this.weekCheck[4])
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.weekCheck[5],
												javax.swing.GroupLayout.PREFERRED_SIZE,
												65,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.weekCheck[6],
												javax.swing.GroupLayout.PREFERRED_SIZE,
												100,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(this.nextButton)
										.addGap(78, 78, 78)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																this.weekCheck[0],
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				this.weekCheck[5],
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				this.weekCheck[6],
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				33,
																				Short.MAX_VALUE)
																		.addComponent(
																				this.nextButton))
														.addComponent(
																this.weekCheck[3],
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																this.weekCheck[2],
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				this.jLabel1)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE))
														.addComponent(
																this.weekCheck[4],
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																this.weekCheck[1],
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(this.jPanel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 18, Short.MAX_VALUE))
				.addComponent(this.dayTabs));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addComponent(this.jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(this.dayTabs,
								javax.swing.GroupLayout.DEFAULT_SIZE, 196,
								Short.MAX_VALUE)));

		this.dayTabs.getAccessibleContext().setAccessibleName("Days Tab");

		pack();
	}// </editor-fold>

	private void onDayActionPerformed(java.awt.event.ActionEvent evt) {
		int i = Integer.parseInt(((JCheckBox) evt.getSource()).getName());
		if (this.weekCheck[i].isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			this.models[i] = new DefaultListModel<Object>();
			this.weekJobList[i].setModel(this.models[i]);
			this.weekScrollPane[i].setViewportView(this.weekJobList[i]);

			this.weekJobName[i].setColumns(20);

			this.weekLabel[i].setText("Job Name:");

			this.weekAddJob[i].setText("Add Job");
			this.weekAddJob[i].setName("" + i);
			this.weekAddJob[i]
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							int i = Integer.parseInt(((JButton) evt.getSource())
									.getName());
							if (!Config.this.weekJobName[i].getText().isEmpty()) {
								Config.this.models[i]
										.addElement(Config.this.weekJobName[i]
												.getText());
								Config.this.weekJobList[i]
										.setModel(Config.this.models[i]);
								Config.this.weekJobName[i].setText("");
							}
						}
					});

			this.weekDeleteJob[i].setText("Delete Job");
			this.weekDeleteJob[i]
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							int i = Integer.parseInt(((JButton) evt.getSource())
									.getName());
							while (!Config.this.weekJobList[i]
									.isSelectionEmpty()) {
								int n = Config.this.weekJobList[i]
										.getSelectedIndex();
								Config.this.models[i].remove(n);
							}

						}
					});

			javax.swing.GroupLayout weekTab0Layout = new javax.swing.GroupLayout(
					this.weekTab[i]);
			this.weekTab[i].setLayout(weekTab0Layout);
			weekTab0Layout
					.setHorizontalGroup(weekTab0Layout
							.createParallelGroup(
									javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
									weekTab0Layout
											.createSequentialGroup()
											.addContainerGap()
											.addComponent(
													this.weekScrollPane[i],
													javax.swing.GroupLayout.PREFERRED_SIZE,
													182,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(18, 18, 18)
											.addGroup(
													weekTab0Layout
															.createParallelGroup(
																	javax.swing.GroupLayout.Alignment.LEADING)
															.addGroup(
																	weekTab0Layout
																			.createSequentialGroup()
																			.addComponent(
																					this.weekLabel[i])
																			.addGroup(
																					weekTab0Layout
																							.createParallelGroup(
																									javax.swing.GroupLayout.Alignment.LEADING)
																							.addGroup(
																									weekTab0Layout
																											.createSequentialGroup()
																											.addGap(14,
																													14,
																													14)
																											.addComponent(
																													this.weekAddJob[i]))
																							.addGroup(
																									weekTab0Layout
																											.createSequentialGroup()
																											.addPreferredGap(
																													javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																											.addComponent(
																													this.weekJobName[i],
																													javax.swing.GroupLayout.PREFERRED_SIZE,
																													100,
																													javax.swing.GroupLayout.PREFERRED_SIZE))))
															.addComponent(
																	this.weekDeleteJob[i]))
											.addContainerGap(431,
													Short.MAX_VALUE)));
			weekTab0Layout
					.setVerticalGroup(weekTab0Layout
							.createParallelGroup(
									javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
									weekTab0Layout
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													weekTab0Layout
															.createParallelGroup(
																	javax.swing.GroupLayout.Alignment.LEADING,
																	false)
															.addGroup(
																	weekTab0Layout
																			.createSequentialGroup()
																			.addGroup(
																					weekTab0Layout
																							.createParallelGroup(
																									javax.swing.GroupLayout.Alignment.BASELINE)
																							.addComponent(
																									this.weekJobName[i],
																									javax.swing.GroupLayout.PREFERRED_SIZE,
																									javax.swing.GroupLayout.DEFAULT_SIZE,
																									javax.swing.GroupLayout.PREFERRED_SIZE)
																							.addComponent(
																									this.weekLabel[i]))
																			.addPreferredGap(
																					javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																			.addComponent(
																					this.weekAddJob[i])
																			.addPreferredGap(
																					javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																					javax.swing.GroupLayout.DEFAULT_SIZE,
																					Short.MAX_VALUE)
																			.addComponent(
																					this.weekDeleteJob[i]))
															.addComponent(
																	this.weekScrollPane[i],
																	javax.swing.GroupLayout.PREFERRED_SIZE,
																	javax.swing.GroupLayout.DEFAULT_SIZE,
																	javax.swing.GroupLayout.PREFERRED_SIZE))
											.addContainerGap(25,
													Short.MAX_VALUE)));
			this.dayTabs.addTab(new DateFormatSymbols().getWeekdays()[i + 1],
					this.weekTab[i]);
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.weekTab[i]);
		}

	}

	/**
	 * @param evt
	 */
	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		ArrayList<Day> days = new ArrayList<Day>();
		for (int i = 0; i < weekTab.length; i++) {
			if (this.weekCheck[i].isSelected()) {
				ArrayList<Object> sun = new ArrayList<Object>();
				List<Object> jobs = Arrays.asList(this.models[i].toArray());
				sun.addAll(jobs);
				days.add(new Day(new DateFormatSymbols().getWeekdays()[i + 1],
						sun));
			}
		}
		if (days.size() > 0) {
			boolean hasJobs = true;
			int k = 0;
			while (hasJobs && k < days.size()) {
				if (days.get(k).getJobs().size() == 0) {
					hasJobs = false;
				}
				k++;
			}
			if (hasJobs) {
				ConfigManager.setDays(days);
				ConfigManager.wSet = new WorkerSetup();
				ConfigManager.toggleWorkerSetup();
				ConfigManager.config = this;
				ConfigManager.toggleConfig();
			} else {
				JOptionPane.showMessageDialog(this,
						"You must have at least one job each day.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "You have not added any days.");
		}
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

	// SWAP 1, TEAM 7 - QUALITY CHANGES
	// Each of these arrays used to be 7 seperate variables, we removed all of
	// the
	// duplicated code and can now use arrays to store the variables instead
	private JScrollPane[] weekScrollPane = new JScrollPane[7];
	private JButton[] weekAddJob = new JButton[7];
	private JButton[] weekDeleteJob = new JButton[7];
	private JList[] weekJobList = new JList[7];
	private JTextField[] weekJobName = new JTextField[7];
	private JLabel[] weekLabel = new JLabel[7];
	private JPanel[] weekTab = new JPanel[7];
	private JCheckBox[] weekCheck = new JCheckBox[7];

	// Static Mapping of weekday names to the integers of the arrays
	// This is needed as we still need be able to figure out what day
	// goes with what index of the array
	private static enum WEEK_INDICIES {
		Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Satuday
	};

	private JTabbedPane dayTabs;
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JButton nextButton;

}
