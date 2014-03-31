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
    				this.models[0].addElement(job);
    				this.sundayJobList.setModel(this.models[0]);
    			}
    		} else if(day.getNameOfDay().equals("Monday")) {
    			this.mondayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[1].addElement(job);
    				this.sundayJobList.setModel(this.models[1]);
    			}
    		} else if(day.getNameOfDay().equals("Tuesday")) {
    			this.tuesdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[2].addElement(job);
    				this.sundayJobList.setModel(this.models[2]);
    			}
    		} else if(day.getNameOfDay().equals("Wednesday")) {
    			this.wednesdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[3].addElement(job);
    				this.sundayJobList.setModel(this.models[3]);
    			}
    		} else if(day.getNameOfDay().equals("Thursday")) {
    			this.thursdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[4].addElement(job);
    				this.sundayJobList.setModel(this.models[4]);
    			}
    		} else if(day.getNameOfDay().equals("Friday")) {
    			this.fridayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[5].addElement(job);
    				this.sundayJobList.setModel(this.models[5]);
    			}
    		} else if(day.getNameOfDay().equals("Saturday")) {
    			this.saturdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[6].addElement(job);
    				this.sundayJobList.setModel(this.models[6]);
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
        this.sundayScrollPane = new javax.swing.JScrollPane();
        this.sundayScrollPane.setPreferredSize(new Dimension(185,150));
        this.sundayJobList = new javax.swing.JList();
        this.sundayJobName = new javax.swing.JTextField();
        this.sundayLabel = new javax.swing.JLabel();
        this.sundayAddJob = new javax.swing.JButton();
        this.sundayDeleteJob = new javax.swing.JButton();
        
        this.mondayScrollPane = new javax.swing.JScrollPane();
        this.mondayScrollPane.setPreferredSize(new Dimension(185,150));
        this.mondayJobList = new javax.swing.JList();
        this.mondayJobName = new javax.swing.JTextField();
        this.mondayLabel = new javax.swing.JLabel();
        this.mondayAddJob = new javax.swing.JButton();
        this.mondayDeleteJob = new javax.swing.JButton();
        
        this.tuesdayScrollPane = new javax.swing.JScrollPane();
        this.tuesdayScrollPane.setPreferredSize(new Dimension(185,150));
        this.tuesdayJobList = new javax.swing.JList();
        this.tuesdayJobName = new javax.swing.JTextField();
        this.tuesdayLabel = new javax.swing.JLabel();
        this.tuesdayAddJob = new javax.swing.JButton();
        this.tuesdayDeleteJob = new javax.swing.JButton();
        
        this.wednesdayScrollPane = new javax.swing.JScrollPane();
        this.wednesdayScrollPane.setPreferredSize(new Dimension(185,150));
        this.wednesdayJobList = new javax.swing.JList();
        this.wednesdayJobName = new javax.swing.JTextField();
        this.wednesdayLabel = new javax.swing.JLabel();
        this.wednesdayAddJob = new javax.swing.JButton();
        this.wednesdayDeleteJob = new javax.swing.JButton();
        
        this.thursdayScrollPane = new javax.swing.JScrollPane();
        this.thursdayScrollPane.setPreferredSize(new Dimension(185,150));
        this.thursdayJobList = new javax.swing.JList();
        this.thursdayJobName = new javax.swing.JTextField();
        this.thursdayLabel = new javax.swing.JLabel();
        this.thursdayAddJob = new javax.swing.JButton();
        this.thursdayDeleteJob = new javax.swing.JButton();
        
        this.fridayScrollPane = new javax.swing.JScrollPane();
        this.fridayScrollPane.setPreferredSize(new Dimension(185,150));
        this.fridayJobList = new javax.swing.JList();
        this.fridayJobName = new javax.swing.JTextField();
        this.fridayLabel = new javax.swing.JLabel();
        this.fridayAddJob = new javax.swing.JButton();
        this.fridayDeleteJob = new javax.swing.JButton();
        
        this.saturdayScrollPane = new javax.swing.JScrollPane();
        this.saturdayScrollPane.setPreferredSize(new Dimension(185,150));
        this.saturdayJobList = new javax.swing.JList();
        this.saturdayJobName = new javax.swing.JTextField();
        this.saturdayLabel = new javax.swing.JLabel();
        this.saturdayAddJob = new javax.swing.JButton();
        this.saturdayDeleteJob = new javax.swing.JButton();
        
        this.mondayTab = new javax.swing.JPanel();
        this.tuesdayTab = new javax.swing.JPanel();
        this.wednesdayTab = new javax.swing.JPanel();
        this.thursdayTab = new javax.swing.JPanel();
        this.fridayTab = new javax.swing.JPanel();
        this.saturdayTab = new javax.swing.JPanel();
        this.sundayTab = new javax.swing.JPanel();
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
                sundayCheckActionPerformed(evt);
            }
        });

        this.wednesdayCheck.setText("Wednesday");
        this.wednesdayCheck.setName("wednesdayCheck"); // NOI18N
        this.wednesdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                wednesdayCheckActionPerformed(evt);
            }
        });

        this.mondayCheck.setText("Monday");
        this.mondayCheck.setName("mondayCheck"); // NOI18N
        this.mondayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                mondayCheckActionPerformed(evt);
            }
        });

        this.tuesdayCheck.setText("Tuesday");
        this.tuesdayCheck.setName("tuesdayCheck"); // NOI18N
        this.tuesdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuesdayCheckActionPerformed(evt);
            }
        });

        this.jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        this.jLabel1.setText("Days:");

        this.thursdayCheck.setText("Thursday");
        this.thursdayCheck.setName("thursdayCheck"); // NOI18N
        this.thursdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                thursdayCheckActionPerformed(evt);
            }
        });

        this.fridayCheck.setText("Friday");
        this.fridayCheck.setName("fridayCheck"); // NOI18N
        this.fridayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                fridayCheckActionPerformed(evt);
            }
        });

        this.saturdayCheck.setText("Saturday");
        this.saturdayCheck.setName("saturdayCheck"); // NOI18N
        this.saturdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                saturdayCheckActionPerformed(evt);
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
	private void sundayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(this.sundayCheck.isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[0] = new DefaultListModel<Object>();
            this.sundayJobList.setModel(this.models[0]);
            this.sundayScrollPane.setViewportView(this.sundayJobList);

            this.sundayJobName.setColumns(20);

            this.sundayLabel.setText("Job Name:");

            this.sundayAddJob.setText("Add Job");
            this.sundayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.sundayJobName.getText().isEmpty()) {
                        Config.this.models[0].addElement(Config.this.sundayJobName.getText());
                        Config.this.sundayJobList.setModel(Config.this.models[0]);
                        Config.this.sundayJobName.setText("");
                    }
                }
            });

            this.sundayDeleteJob.setText("Delete Job");
            this.sundayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.sundayJobList.isSelectionEmpty()) {
                        int n = Config.this.sundayJobList.getSelectedIndex();
                        Config.this.models[0].remove(n);
                    }
                    
                }
            });

            // SWAP 2, TEAM 03
        	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
        	// Feature Envy - account for move to CalendarGUI
            this.gui.setTabLayout(this.sundayTab, this.sundayScrollPane, this.sundayLabel, this.sundayAddJob, this.sundayDeleteJob, this.sundayJobName);
            this.dayTabs.addTab("Sunday", this.sundayTab);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.sundayTab);
        }
        
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void mondayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(this.mondayCheck.isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[1] = new DefaultListModel<Object>();
            this.mondayJobList.setModel(this.models[1]);
            this.mondayScrollPane.setViewportView(this.mondayJobList);

            this.mondayJobName.setColumns(20);

            this.mondayLabel.setText("Job Name:");

            this.mondayAddJob.setText("Add Job");
            this.mondayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.mondayJobName.getText().isEmpty()) {
                        Config.this.models[1].addElement(Config.this.mondayJobName.getText());
                        Config.this.mondayJobList.setModel(Config.this.models[1]);
                        Config.this.mondayJobName.setText("");
                    }
                }
            });

            this.mondayDeleteJob.setText("Delete Job");
            this.mondayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.mondayJobList.isSelectionEmpty()) {
                        int n = Config.this.mondayJobList.getSelectedIndex();
                        Config.this.models[1].remove(n);
                    }
                    
                }
            });
            
            // SWAP 2, TEAM 03
        	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
        	// Feature Envy - account for move to CalendarGUI
            this.gui.setTabLayout(this.mondayTab, this.mondayScrollPane, this.mondayLabel, this.mondayAddJob, this.mondayDeleteJob, this.mondayJobName);
            dayTabs.addTab("Monday", this.mondayTab);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.mondayTab);
        }
                
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void tuesdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(this.tuesdayCheck.isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[2] = new DefaultListModel<Object>();
            this.tuesdayJobList.setModel(this.models[2]);
            this.tuesdayScrollPane.setViewportView(this.tuesdayJobList);

            this.tuesdayJobName.setColumns(20);

            this.tuesdayLabel.setText("Job Name:");

            this.tuesdayAddJob.setText("Add Job");
            this.tuesdayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.tuesdayJobName.getText().isEmpty()) {
                        Config.this.models[2].addElement(Config.this.tuesdayJobName.getText());
                        Config.this.tuesdayJobList.setModel(Config.this.models[2]);
                        Config.this.tuesdayJobName.setText("");
                    }
                }
            });

            this.tuesdayDeleteJob.setText("Delete Job");
            this.tuesdayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.tuesdayJobList.isSelectionEmpty()) {
                        int n = Config.this.tuesdayJobList.getSelectedIndex();
                        Config.this.models[2].remove(n);
                    }
                    
                }
            });

            // SWAP 2, TEAM 03
        	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
        	// Feature Envy - account for move to CalendarGUI
            this.gui.setTabLayout(this.tuesdayTab, this.tuesdayScrollPane, this.tuesdayLabel, this.tuesdayAddJob, this.tuesdayDeleteJob, this.tuesdayJobName);
            this.dayTabs.addTab("Tuesday", this.tuesdayTab);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.tuesdayTab);
        }
    }                                            

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void wednesdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                               
        if(this.wednesdayCheck.isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[3] = new DefaultListModel<Object>();
            this.wednesdayJobList.setModel(this.models[3]);
            this.wednesdayScrollPane.setViewportView(this.wednesdayJobList);

            this.wednesdayJobName.setColumns(20);

            this.wednesdayLabel.setText("Job Name:");

            this.wednesdayAddJob.setText("Add Job");
            this.wednesdayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.wednesdayJobName.getText().isEmpty()) {
                        Config.this.models[3].addElement(Config.this.wednesdayJobName.getText());
                        Config.this.wednesdayJobList.setModel(Config.this.models[3]);
                        Config.this.wednesdayJobName.setText("");
                    }
                }
            });

            this.wednesdayDeleteJob.setText("Delete Job");
            this.wednesdayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.wednesdayJobList.isSelectionEmpty()) {
                        int n = Config.this.wednesdayJobList.getSelectedIndex();
                        Config.this.models[3].remove(n);
                    }
                    
                }
            });

            // SWAP 2, TEAM 03
        	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
        	// Feature Envy - account for move to CalendarGUI
            this.gui.setTabLayout(this.wednesdayTab, this.wednesdayScrollPane, this.wednesdayLabel, this.wednesdayAddJob, this.wednesdayDeleteJob, this.wednesdayJobName);
            this.dayTabs.addTab("Wednesday", this.wednesdayTab);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.wednesdayTab);
        }
        
    }                                              

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void thursdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(this.thursdayCheck.isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[4] = new DefaultListModel<Object>();
            this.thursdayJobList.setModel(this.models[4]);
            this.thursdayScrollPane.setViewportView(this.thursdayJobList);

            this.thursdayJobName.setColumns(20);

            this.thursdayLabel.setText("Job Name:");

            this.thursdayAddJob.setText("Add Job");
            this.thursdayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.thursdayJobName.getText().isEmpty()) {
                        Config.this.models[4].addElement(Config.this.thursdayJobName.getText());
                        Config.this.thursdayJobList.setModel(Config.this.models[4]);
                        Config.this.thursdayJobName.setText("");
                    }
                }
            });

            this.thursdayDeleteJob.setText("Delete Job");
            this.thursdayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.thursdayJobList.isSelectionEmpty()) {
                        int n = Config.this.thursdayJobList.getSelectedIndex();
                        Config.this.models[4].remove(n);
                    }
                    
                }
            });
            
            // SWAP 2, TEAM 03
        	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
        	// Feature Envy - account for move to CalendarGUI
            this.gui.setTabLayout(this.thursdayTab, this.thursdayScrollPane, this.thursdayLabel, this.thursdayAddJob, this.thursdayDeleteJob, this.thursdayJobName);
            
            this.dayTabs.addTab("Thursday", this.thursdayTab);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.thursdayTab);
        }
        
    }

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void fridayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                            
       if(this.fridayCheck.isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[5] = new DefaultListModel<Object>();
            this.fridayJobList.setModel(this.models[5]);
            this.fridayScrollPane.setViewportView(this.fridayJobList);

            this.fridayJobName.setColumns(20);

            this.fridayLabel.setText("Job Name:");

            this.fridayAddJob.setText("Add Job");
            this.fridayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.fridayJobName.getText().isEmpty()) {
                        Config.this.models[5].addElement(Config.this.fridayJobName.getText());
                        Config.this.fridayJobList.setModel(Config.this.models[5]);
                        Config.this.fridayJobName.setText("");
                    }
                }
            });

            this.fridayDeleteJob.setText("Delete Job");
            this.fridayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.fridayJobList.isSelectionEmpty()) {
                        int n = Config.this.fridayJobList.getSelectedIndex();
                        Config.this.models[5].remove(n);
                    }
                    
                }
            });

            // SWAP 2, TEAM 03
        	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
        	// Feature Envy - account for move to CalendarGUI
            this.gui.setTabLayout(this.fridayTab, this.fridayScrollPane, this.fridayLabel, this.fridayAddJob, this.fridayDeleteJob, this.fridayJobName);
            this.dayTabs.addTab("Friday", this.fridayTab);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.fridayTab);
        }
        
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void saturdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(this.saturdayCheck.isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[6] = new DefaultListModel<Object>();
            this.saturdayJobList.setModel(this.models[6]);
            this.saturdayScrollPane.setViewportView(this.saturdayJobList);

            this.saturdayJobName.setColumns(20);

            this.saturdayLabel.setText("Job Name:");

            this.saturdayAddJob.setText("Add Job");
            this.saturdayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.saturdayJobName.getText().isEmpty()) {
                        Config.this.models[6].addElement(Config.this.saturdayJobName.getText());
                        Config.this.saturdayJobList.setModel(Config.this.models[6]);
                        Config.this.saturdayJobName.setText("");
                    }
                }
            });

            this.saturdayDeleteJob.setText("Delete Job");
            this.saturdayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.saturdayJobList.isSelectionEmpty()) {
                        int n = Config.this.saturdayJobList.getSelectedIndex();
                        Config.this.models[6].remove(n);
                    }
                    
                }
            });

            // SWAP 2, TEAM 03
        	// REFACTORING FOR ENHANCMENT FROM BAD SMELL
        	// Feature Envy - account for move to CalendarGUI
            this.gui.setTabLayout(this.saturdayTab, this.saturdayScrollPane, this.saturdayLabel, this.saturdayAddJob, this.saturdayDeleteJob, this.saturdayJobName);
            this.dayTabs.addTab("Saturday", this.saturdayTab);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(this.saturdayTab);
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
    	List<Object> jobs = Arrays.asList(this.models[num].toArray());
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
    private javax.swing.JScrollPane sundayScrollPane;
    private javax.swing.JButton sundayAddJob;
    private javax.swing.JButton sundayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList sundayJobList;
    private javax.swing.JTextField sundayJobName;
    private javax.swing.JLabel sundayLabel;
    private javax.swing.JPanel sundayTab;
    
    private javax.swing.JScrollPane mondayScrollPane;
    private javax.swing.JButton mondayAddJob;
    private javax.swing.JButton mondayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList mondayJobList;
    private javax.swing.JTextField mondayJobName;
    private javax.swing.JLabel mondayLabel;
    private javax.swing.JPanel mondayTab;
    
    private javax.swing.JScrollPane tuesdayScrollPane;
    private javax.swing.JButton tuesdayAddJob;
    private javax.swing.JButton tuesdayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList tuesdayJobList;
    private javax.swing.JTextField tuesdayJobName;
    private javax.swing.JLabel tuesdayLabel;
    private javax.swing.JPanel tuesdayTab;
    
    private javax.swing.JScrollPane wednesdayScrollPane;
    private javax.swing.JButton wednesdayAddJob;
    private javax.swing.JButton wednesdayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList wednesdayJobList;
    private javax.swing.JTextField wednesdayJobName;
    private javax.swing.JLabel wednesdayLabel;
    private javax.swing.JPanel wednesdayTab;
    
    private javax.swing.JScrollPane thursdayScrollPane;
    private javax.swing.JButton thursdayAddJob;
    private javax.swing.JButton thursdayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList thursdayJobList;
    private javax.swing.JTextField thursdayJobName;
    private javax.swing.JLabel thursdayLabel;
    private javax.swing.JPanel thursdayTab;
    
    private javax.swing.JScrollPane fridayScrollPane;
    private javax.swing.JButton fridayAddJob;
    private javax.swing.JButton fridayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList fridayJobList;
    private javax.swing.JTextField fridayJobName;
    private javax.swing.JLabel fridayLabel;
    private javax.swing.JPanel fridayTab;
    
    private javax.swing.JScrollPane saturdayScrollPane;
    private javax.swing.JButton saturdayAddJob;
    private javax.swing.JButton saturdayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList saturdayJobList;
    private javax.swing.JTextField saturdayJobName;
    private javax.swing.JLabel saturdayLabel;
    private javax.swing.JPanel saturdayTab;
    
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
