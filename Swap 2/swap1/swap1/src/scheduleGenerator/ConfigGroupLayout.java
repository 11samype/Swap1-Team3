package scheduleGenerator;

import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
/**
 * 
 * @author sternetj
 * 		// SWAP 2, TEAM 03
		// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
		// 1. Created this class to remove some unecessary functionality out of the Config class
		// 2. Different layouts could be implemented
		// 3. pretty successful except the code was basically just dumped in this class
		 * 	  and the code could probably be refactored itself.
 *
 */
public class ConfigGroupLayout extends GroupLayout {

	public ConfigGroupLayout(Container container, JPanel configPanel,
			JTabbedPane dayTabs) {
		super(container);
		this.setHorizontalGroup(this
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						this.createSequentialGroup()
								.addComponent(configPanel,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(0, 18, Short.MAX_VALUE))
				.addComponent(dayTabs));
		this.setVerticalGroup(this.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				this.createSequentialGroup()
						.addComponent(configPanel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(dayTabs, GroupLayout.DEFAULT_SIZE, 196,
								Short.MAX_VALUE)));
	}

	public ConfigGroupLayout(JPanel configPanel, DayCheckBox[] dayCheckboxes,
			JButton nextButton, JLabel daysHeaderLabel) {
		super(configPanel);
		this.setupHorizontalGroup(dayCheckboxes, nextButton, daysHeaderLabel);
		this.setupVerticalGroup(dayCheckboxes, nextButton, daysHeaderLabel);

	}

	private void setupVerticalGroup(DayCheckBox[] dayCheckboxes,
			JButton nextButton, JLabel daysHeaderLabel) {
		this.setVerticalGroup(this
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						this.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										this.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(
														dayCheckboxes[0],
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														this.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
																.addComponent(
																		dayCheckboxes[5],
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		dayCheckboxes[6],
																		GroupLayout.DEFAULT_SIZE,
																		33,
																		Short.MAX_VALUE)
																.addComponent(
																		nextButton))
												.addComponent(
														dayCheckboxes[3],
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														dayCheckboxes[2],
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														this.createSequentialGroup()
																.addComponent(
																		daysHeaderLabel)
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addComponent(
														dayCheckboxes[4],
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														dayCheckboxes[1],
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap()));

	}

	private void setupHorizontalGroup(DayCheckBox[] dayCheckboxes,
			JButton nextButton, JLabel daysHeaderLabel) {
		this.setHorizontalGroup(this
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						this.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(daysHeaderLabel)
								.addGap(18, 18, 18)
								.addComponent(dayCheckboxes[0])
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dayCheckboxes[1],
										GroupLayout.PREFERRED_SIZE, 71,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(dayCheckboxes[2])
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dayCheckboxes[3],
										GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(dayCheckboxes[4])
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dayCheckboxes[5],
										GroupLayout.PREFERRED_SIZE, 65,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dayCheckboxes[6],
										GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(nextButton)
								.addGap(78, 78, 78)));

	}
}
