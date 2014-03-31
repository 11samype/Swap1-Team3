package scheduleGenerator;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

// SWAP 2, TEAM 03
// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
// 1. Created this class to remove the duplicate code of creation and use of 
//	  the the different day tabs in the Config class
// 2. Different functionalities could be added to these tabs as well as 
//	  new buttons and components
// 3. This was a very successful refactoring and helped cut down on a lot of 
//	  duplicate code in the config class
@SuppressWarnings("serial")
public class DayTab extends JPanel {

    private javax.swing.JScrollPane sundayScrollPane = new JScrollPane();
    private javax.swing.JButton sundayAddJob = new JButton();
    private javax.swing.JButton sundayDeleteJob = new JButton();
    @SuppressWarnings("rawtypes")
	private javax.swing.JList sundayJobList = new JList();
    private javax.swing.JTextField sundayJobName = new JTextField();
    private javax.swing.JLabel sundayLabel = new JLabel();
    @SuppressWarnings("rawtypes")
	private DefaultListModel model = new DefaultListModel();
    //Sunday 0
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public DayTab(DefaultListModel m){
        this.model = m;
        this.sundayJobList.setModel(this.model);
        
        this.sundayScrollPane.setPreferredSize(new Dimension(185,150));        
        this.sundayScrollPane.setViewportView(this.sundayJobList);
    	
    	this.sundayJobName.setColumns(20);
    	this.sundayLabel.setText("Job Name:");
    	this.sundayAddJob.setText("Add Job");
    	
        this.sundayAddJob.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(!DayTab.this.sundayJobName.getText().isEmpty()) {
                    DayTab.this.model.addElement(DayTab.this.sundayJobName.getText());
                    DayTab.this.sundayJobList.setModel(DayTab.this.model);
                    DayTab.this.sundayJobName.setText("");
                }
            }
        });
        
        this.sundayDeleteJob.setText("Delete Job");
        this.sundayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                while(!DayTab.this.sundayJobList.isSelectionEmpty()) {
                    int n = DayTab.this.sundayJobList.getSelectedIndex();
                    DayTab.this.model.remove(n);
                }
                
            }
        });
        
        this.setTabLayout();
    	
    }
    
    private void setTabLayout() {
    	GroupLayout layout = new GroupLayout(this);
    	this.setLayout(layout);
    	layout.setHorizontalGroup(
    			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.sundayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.sundayLabel)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(this.sundayAddJob))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.sundayJobName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(this.sundayDeleteJob))
                .addContainerGap(431, Short.MAX_VALUE))
        );
    	layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(this.sundayJobName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(this.sundayLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.sundayAddJob)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.sundayDeleteJob))
                    .addComponent(this.sundayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }

	@SuppressWarnings("unchecked")
	public void addJobElement(String job) {
		this.model.addElement(job);
		
	}
	
	@SuppressWarnings("rawtypes")
	public DefaultListModel getModel(){
		return this.model;
	}
}
