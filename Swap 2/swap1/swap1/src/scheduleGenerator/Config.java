/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author schneimd
 */
public class Config extends javax.swing.JFrame {
	
	/*
     * SWAP 1, TEAM2 (JORDON/FRANCIS)
     * SMELL: Large Class - This class is over 1000 lines and attempts to perform a large variety of tasks
     * FIX: Delegate tasks to other classes. In particular, there is a lot of code to  manage the GUI which
     * 	should really be moved to a GUI dedicated class.
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
     * SMELL: Shot Gun Surgery if we want to change the way of configuration of each day.
     * we have to make small changes at the configuration of each day in this class.
     * FIX: create new class that consist of part that may have potential changes. 
     */
    @SuppressWarnings("unchecked")
	public Config(ArrayList<Day> days) {
    	this.models = new DefaultListModel[7];
        initDyn();
        initComponents();
        
    	for(Day day: days) {
    		if(day.getNameOfDay().equals("Sunday")) {
    			this.sundayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.dayTabsList[0].addJobElement(job);
    				this.sundayJobList.setModel(this.dayTabsList[0].getModel());
    			}
    		} else if(day.getNameOfDay().equals("Monday")) {
    			this.mondayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.mondayTab.addJobElement(job);
    				this.sundayJobList.setModel(this.mondayTab.getModel());
    			}
    		} else if(day.getNameOfDay().equals("Tuesday")) {
    			this.tuesdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.tuesdayTab.addJobElement(job);
    				this.sundayJobList.setModel(this.tuesdayTab.getModel());
    			}
    		} else if(day.getNameOfDay().equals("Wednesday")) {
    			this.wednesdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.wednesdayTab.addJobElement(job);
    				this.sundayJobList.setModel(this.wednesdayTab.getModel());
    			}
    		} else if(day.getNameOfDay().equals("Thursday")) {
    			this.thursdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.thursdayTab.addJobElement(job);
    				this.sundayJobList.setModel(this.thursdayTab.getModel());
    			}
    		} else if(day.getNameOfDay().equals("Friday")) {
    			this.fridayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.fridayTab.addJobElement(job);
    				this.sundayJobList.setModel(this.fridayTab.getModel());
    			}
    		} else if(day.getNameOfDay().equals("Saturday")) {
    			this.saturdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.saturdayTab.addJobElement(job);
    				this.sundayJobList.setModel(this.saturdayTab.getModel());
    			}
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
        this.sundayJobList = new javax.swing.JList();
        this.mondayJobList = new javax.swing.JList();
        this.tuesdayJobList = new javax.swing.JList();
        this.wednesdayJobList = new javax.swing.JList();
        this.thursdayJobList = new javax.swing.JList();
        this.fridayJobList = new javax.swing.JList();
        this.saturdayJobList = new javax.swing.JList();
        
        this.mondayTab = new DayTab(new DefaultListModel());
        this.tuesdayTab = new DayTab(new DefaultListModel());
        this.wednesdayTab = new DayTab(new DefaultListModel());
        this.thursdayTab = new DayTab(new DefaultListModel());
        this.fridayTab = new DayTab(new DefaultListModel());
        this.saturdayTab = new DayTab(new DefaultListModel());
        //this.sundayTab = new DayTab(new DefaultListModel());
        
        this.dayTabsList = new DayTab[7];
        this.dayTabsList[0] = new DayTab(new DefaultListModel());
        this.dayTabsList[1] = new DayTab(new DefaultListModel());
        this.dayTabsList[2] = new DayTab(new DefaultListModel());
        this.dayTabsList[3] = new DayTab(new DefaultListModel());
        this.dayTabsList[4] = new DayTab(new DefaultListModel());
        this.dayTabsList[5] = new DayTab(new DefaultListModel());
        this.dayTabsList[6] = new DayTab(new DefaultListModel());
        
    }

    private void initComponents() {
    	
    	/*
         * SWAP 1, TEAM2 (JORDON/FRANCIS)
         * SMELL: Long Method - This method is insanely long and does a large number of things.
         * FIX: Abstract some of these out into different methods.
         */

    	this.jPanel1 = new javax.swing.JPanel();
        this.sundayCheck = new javax.swing.JCheckBox();
        this.wednesdayCheck = new javax.swing.JCheckBox();
        this.mondayCheck = new javax.swing.JCheckBox();
        this.tuesdayCheck = new javax.swing.JCheckBox();
        this.jLabel1 = new javax.swing.JLabel();
        this.thursdayCheck = new javax.swing.JCheckBox();
        this.fridayCheck = new javax.swing.JCheckBox();
        this.saturdayCheck = new javax.swing.JCheckBox();
        this.nextButton = new javax.swing.JButton();
        this.dayTabs = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuration");
        setPreferredSize(new java.awt.Dimension(801, 87));
        setResizable(false);

        this.sundayCheck.setText("Sunday");
        this.sundayCheck.setName("sundayCheck"); // NOI18N
        this.sundayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayCheckActionPerformed(evt, "Sunday", 0);
            }
        });

        this.wednesdayCheck.setText("Wednesday");
        this.wednesdayCheck.setName("wednesdayCheck"); // NOI18N
        this.wednesdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dayCheckActionPerformed(evt, "Wednesday", 3);
            }
        });

        this.mondayCheck.setText("Monday");
        this.mondayCheck.setName("mondayCheck"); // NOI18N
        this.mondayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dayCheckActionPerformed(evt, "Monday", 1);
            }
        });

        this.tuesdayCheck.setText("Tuesday");
        this.tuesdayCheck.setName("tuesdayCheck"); // NOI18N
        this.tuesdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dayCheckActionPerformed(evt, "Tuesday", 2);
            }
        });

        this.jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        this.jLabel1.setText("Days:");

        this.thursdayCheck.setText("Thursday");
        this.thursdayCheck.setName("thursdayCheck"); // NOI18N
        this.thursdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dayCheckActionPerformed(evt, "Thursday", 4);
            }
        });

        this.fridayCheck.setText("Friday");
        this.fridayCheck.setName("fridayCheck"); // NOI18N
        this.fridayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dayCheckActionPerformed(evt, "Friday", 5);
            }
        });

        this.saturdayCheck.setText("Saturday");
        this.saturdayCheck.setName("saturdayCheck"); // NOI18N
        this.saturdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dayCheckActionPerformed(evt, "Saturday", 6);
            }
        });

        this.nextButton.setText("Next");
        this.nextButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.jLabel1)
                .addGap(18, 18, 18)
                .addComponent(this.sundayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.mondayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.tuesdayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.wednesdayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.thursdayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.fridayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.saturdayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.nextButton)
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.sundayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(this.fridayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.saturdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(this.nextButton))
                    .addComponent(this.wednesdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.tuesdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(this.jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(this.thursdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.mondayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
            .addComponent(this.dayTabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.dayTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        this.dayTabs.getAccessibleContext().setAccessibleName("Days Tab");

        pack();
    }// </editor-fold>
    
    // SWAP 2, TEAM 03
	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
	// Feature Envy
	// Moved to CalendarGUI, made some changes later in this file to account for it
    
    /*
     * SWAP 1, TEAM2 (JORDON/FRANCIS)
     * 
     */
    /* QUALITY CHANGES
     * Though the entire functions will need changing, the setting of the tab layout was one
     * of the largest portions of the code in each, and it made sense for it to be its own
     * function.
     * 
     * This does add a smell, or rather expand upon one that is already present: Long Parameter List.
     * The GUI for each tab really needs to be put into a single object.
     */
//    private void setTabLayout(JPanel tab, JScrollPane s, JLabel l, JButton addJob, JButton deleteJob, JTextField jobName) {
//    	/*
//    	 * SMELL: Feature Envy: this part of the code is highly related to the CalendarGUI it should be in the CalendarGUI 
//    	 * instead of here
//    	 * Fix: move the function into the CalendarGUI.
//    	 * 
//    	 */
//    	GroupLayout layout = new GroupLayout(tab);
//    	tab.setLayout(layout);
//    	layout.setHorizontalGroup(
//    			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(18, 18, 18)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addComponent(l)
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addGroup(layout.createSequentialGroup()
//                                .addGap(14, 14, 14)
//                                .addComponent(addJob))
//                            .addGroup(layout.createSequentialGroup()
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(jobName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
//                    .addComponent(deleteJob))
//                .addContainerGap(431, Short.MAX_VALUE))
//        );
//    	layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jobName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(l))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(addJob)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(deleteJob))
//                    .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addContainerGap(25, Short.MAX_VALUE))
//        );
//    }

    /*
     * SMELL: Duplicated Code: it has a check action performed method, which involve a lot duplicated code.
     * Fix: make the config class to a abstract class and have a subclass for every day. 
     * if there are difference on the implementation, the subclass can simply override this method.
     */
    
    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void dayCheckActionPerformed(java.awt.event.ActionEvent evt, String name, int modelNum) { 
        if(((JCheckBox)evt.getSource()).isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            
    		if(name.equals("Sunday")) {
                this.models[modelNum] = new DefaultListModel<Object>();
                this.sundayJobList.setModel(this.models[modelNum]);
                this.dayTabsList[0] = new DayTab(this.models[modelNum]);
                this.dayTabs.addTab(name, this.dayTabsList[0]);
               // this.sundayTab = new DayTab(this.models[modelNum]);
                //this.dayTabs.addTab(name, this.sundayTab);
    		} else if(name.equals("Monday")) {
                this.models[modelNum] = new DefaultListModel<Object>();
                this.mondayJobList.setModel(this.models[modelNum]);
                this.mondayTab = new DayTab(this.models[modelNum]);
                this.dayTabs.addTab(name, this.mondayTab);
    		} else if(name.equals("Tuesday")) {
                this.models[modelNum] = new DefaultListModel<Object>();
                this.tuesdayJobList.setModel(this.models[modelNum]);
                this.tuesdayTab = new DayTab(this.models[modelNum]);
                this.dayTabs.addTab(name, this.tuesdayTab);
    		} else if(name.equals("Wednesday")) {
                this.models[modelNum] = new DefaultListModel<Object>();
                this.wednesdayJobList.setModel(this.models[modelNum]);
                this.wednesdayTab = new DayTab(this.models[modelNum]);
                this.dayTabs.addTab(name, this.wednesdayTab);
    		} else if(name.equals("Thursday")) {
                this.models[modelNum] = new DefaultListModel<Object>();
                this.thursdayJobList.setModel(this.models[modelNum]);
                this.thursdayTab = new DayTab(this.models[modelNum]);
                this.dayTabs.addTab(name, this.thursdayTab);
    		} else if(name.equals("Friday")) {
    			this.models[modelNum] = new DefaultListModel<Object>();
                this.fridayJobList.setModel(this.models[modelNum]);
                this.fridayTab = new DayTab(this.models[modelNum]);
                this.dayTabs.addTab(name, this.fridayTab);
    		} else if(name.equals("Saturday")) {
    			this.models[modelNum] = new DefaultListModel<Object>();
                this.saturdayJobList.setModel(this.models[modelNum]);
                this.saturdayTab = new DayTab(this.models[modelNum]);
                this.dayTabs.addTab(name, this.saturdayTab);
    		}
        } else {
            this.numSelected--;
            stretch();
    		if(name.equals("Sunday")) {
    			this.dayTabs.remove(this.dayTabsList[0]);
    		} else if(name.equals("Monday")) {
    			this.dayTabs.remove(this.mondayTab);
    		} else if(name.equals("Tuesday")) {
    			this.dayTabs.remove(this.tuesdayTab);
    		} else if(name.equals("Wednesday")) {
    			this.dayTabs.remove(this.wednesdayTab);
    		} else if(name.equals("Thursday")) {
    			this.dayTabs.remove(this.thursdayTab);
    		} else if(name.equals("Friday")) {
    			this.dayTabs.remove(this.fridayTab);
    		} else if(name.equals("Saturday")) {
    			this.dayTabs.remove(this.saturdayTab);
    		}
        }
        
    }                                           

    /**
	 * @param evt  
	 */
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	/*
    	 * SWAP 1, TEAM2 (JORDON/FRANCIS)
    	 * QUALITY CHANGES
    	 * Again, this would be even better served by encapsulating the day GUI
    	 * elements into their own class. It could also be improved by adding
    	 */
    	ArrayList<Day> days = new ArrayList<Day>();
    	if(this.sundayCheck.isSelected())
        {
        	days.add(getJobs("Sunday", 0));
        }
    	if(this.mondayCheck.isSelected())
        {
    		days.add(getJobs("Monday", 1));
        }
    	if(this.tuesdayCheck.isSelected())
        {
    		days.add(getJobs("Tueday", 2));
        }
    	if(this.wednesdayCheck.isSelected())
        {
    		days.add(getJobs("Wednesday", 3));
        }
    	if(this.thursdayCheck.isSelected())
        {
    		days.add(getJobs("Thursday", 4));
        }
    	if(this.fridayCheck.isSelected())
        {
    		days.add(getJobs("Friday", 5));
        }
    	if(this.saturdayCheck.isSelected())
        {
    		days.add(getJobs("Saturday", 6));
        }
    	if(days.size() > 0) {
    		boolean hasJobs = true;
    		int i = 0;
    		while(hasJobs && i<days.size()) {
    			if(days.get(i).getJobs().size() == 0) {
    				hasJobs = false;
    			}
    			i++;
    		}
    		if(hasJobs) {
		    	Main.setDays(days);
		    	Main.wSet = new WorkerSetup();
		    	Main.toggleWorkerSetup();
		    	Main.config = this;
		    	Main.toggleConfig();
    		} else {
    			JOptionPane.showMessageDialog(this, "You must have at least one job each day.");
    		}
    	} else {
    		JOptionPane.showMessageDialog(this, "You have not added any days.");
    	}
    }
    
    private Day getJobs(String name, int num) {
    	ArrayList<Object> day = new ArrayList<Object>();
    	List<Object> jobs = null;
		if(num == 0) {
			jobs = Arrays.asList(this.dayTabsList[num].getModel().toArray());
		} else if(num == 1) {
			jobs = Arrays.asList(this.mondayTab.getModel().toArray());
		} else if(num == 2) {
			jobs = Arrays.asList(this.tuesdayTab.getModel().toArray());
		} else if(num == 3) {
			jobs = Arrays.asList(this.wednesdayTab.getModel().toArray());
		} else if(num == 4) {
			jobs = Arrays.asList(this.thursdayTab.getModel().toArray());
		} else if(num == 5) {
			jobs = Arrays.asList(this.fridayTab.getModel().toArray());
		} else if(num == 6) {
			jobs = Arrays.asList(this.saturdayTab.getModel().toArray());
		}
    	day.addAll(jobs);
    	return new Day(name, day);    	
    }
    
    private void stretch() {
        if(this.numSelected > 0) {
            this.setSize(801, 290);
            this.firstSelection = false;
        } else {
            this.setSize(801, 87);
            this.firstSelection = true;
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
                new Config().setVisible(true);
            }
        });
    }
    
    private DayTab[] dayTabsList;
    
    
    public CalendarGUI getGui() {
		return gui;
	}

	public void setGui(CalendarGUI gui) {
		this.gui = gui;
	}


	/*
     * SWAP 1, TEAM2 (JORDON/FRANCIS)
     * SMELL: Data Clumps - These bunches of data appear together practically
     * 	every time any one of them is referenced.
     * FIX: Make them into their own object.
     */
    @SuppressWarnings("rawtypes")
	private javax.swing.JList sundayJobList;
//    /private DayTab sundayTab;
    
    @SuppressWarnings("rawtypes")
	private javax.swing.JList mondayJobList;
    private DayTab mondayTab;
    
    @SuppressWarnings("rawtypes")
	private javax.swing.JList tuesdayJobList;
    private DayTab tuesdayTab;
    
    @SuppressWarnings("rawtypes")
	private javax.swing.JList wednesdayJobList;
    private DayTab wednesdayTab;
    
    @SuppressWarnings("rawtypes")
	private javax.swing.JList thursdayJobList;
    private DayTab thursdayTab;
    
    @SuppressWarnings("rawtypes")
	private javax.swing.JList fridayJobList;
    private DayTab fridayTab;
    
    @SuppressWarnings("rawtypes")
	private javax.swing.JList saturdayJobList;
    private DayTab saturdayTab;
    
    private javax.swing.JTabbedPane dayTabs;
    private javax.swing.JCheckBox fridayCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox mondayCheck;
    private javax.swing.JButton nextButton;
    private javax.swing.JCheckBox saturdayCheck;
    private javax.swing.JCheckBox sundayCheck;
    private javax.swing.JCheckBox thursdayCheck;
    private javax.swing.JCheckBox tuesdayCheck;
    private javax.swing.JCheckBox wednesdayCheck;
}
