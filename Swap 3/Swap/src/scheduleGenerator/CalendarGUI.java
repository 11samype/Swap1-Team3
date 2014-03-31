package scheduleGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;

import darrylbu.util.MenuScroller;

/**
 * 
 * @author schneimd
 */
// SWAP 1, TEAM 7 SMELL Shotgun Surgery - Any time a change has to be made to the GUI, 
// Many different changes need to be made throughout this file to properly implement them.
// By fixing this we could implement a data driven GUI that would allow changes based on
// user needs.
public class CalendarGUI extends javax.swing.JFrame {

	// SWAP 1, TEAM 07
	// QUALITY CHANGES

	// Used Remove Field to get rid of unused fields following removal of
	// duplicated code.

	// Original Code
	/*
	 * private Schedule schedule; private GregorianCalendar cal; private
	 * TreeMap<String, TreeMap<String, Worker>> scheduleMap; private int
	 * currentMonth; private String monthName;
	 * 
	 * @SuppressWarnings("unused") private int earliestYear, earliestMonth,
	 * earliestDay; private int monthsAhead = 0; private int yearsAhead = 0;
	 */

	// New Code
	private Schedule schedule;
	private GregorianCalendar cal;
	private TreeMap<String, TreeMap<String, Worker>> scheduleMap;
	private int earliestYear, earliestMonth;

	// SWAP 1, TEAM 07
	// Additional Feature

	// This field was added to allow the CalendarGUI to be internationalized
	// through generation based on Locale.
	private Locale locale;

	/**
	 * Creates new form Calendar
	 * 
	 * @param schd
	 * @param locale 
	 */
	// SWAP 2 TEAM 01 FURTHER ELABORATION
	// CalendarGUI now takes a (possibly null for new user) locale for month names. This
	// allows for loading a previously set locale.
	public CalendarGUI(Schedule schd, Locale locale) {
		this.schedule = schd;
		this.scheduleMap = this.schedule.getSchedule();
		String[] earliest = this.scheduleMap.firstKey().split("/");
		this.earliestYear = Integer.parseInt(earliest[0]);
		this.earliestMonth = Integer.parseInt(earliest[1]);
		// this.earliestDay = Integer.parseInt(earliest[2]);
		this.cal = new GregorianCalendar();
		this.locale = locale == null ? Locale.getDefault() : locale;
		initComponents();

		// SWAP 1, TEAM 07
		// QUALITY CHANGES

		// Replaced the original method with a new method that removes very
		// similar code.

		// Original Code
		// this.fillTableForThisMonth();

		// New Code
		this.fillTableForMonth();
	}

	// SWAP 1, TEAM 07
	// QUALITY CHANGES

	// Removed the duplicated code by using Replace Temp with Query in
	// fillTableForMonth and then using Inline Method. Doing so eliminates the
	// duplicated code while allowing the use of calendars other than Gregorian.

	/*
	 * private void setTitleMonth(int n, int year) { switch (n) { case (1):
	 * this.monthTitle.setText("January " + year); this.monthName = "January " +
	 * year; break; case (2): this.monthTitle.setText("February " + year);
	 * this.monthName = "February " + year; break; case (3):
	 * this.monthTitle.setText("March " + year); this.monthName = "March " +
	 * year; break; case (4): this.monthTitle.setText("April " + year);
	 * this.monthName = "April " + year; break; case (5):
	 * this.monthTitle.setText("May " + year); this.monthName = "May " + year;
	 * break; case (6): this.monthTitle.setText("June " + year); this.monthName
	 * = "June " + year; break; case (7): this.monthTitle.setText("July " +
	 * year); this.monthName = "July " + year; break; case (8):
	 * this.monthTitle.setText("August " + year); this.monthName = "August " +
	 * year; break; case (9): this.monthTitle.setText("September " + year);
	 * this.monthName = "September " + year; break; case (10):
	 * this.monthTitle.setText("October " + year); this.monthName = "October " +
	 * year; break; case (11): this.monthTitle.setText("November " + year);
	 * this.monthName = "November " + year; break; case (12):
	 * this.monthTitle.setText("December " + year); this.monthName = "December "
	 * + year; break;
	 * 
	 * } }
	 */

	// SWAP 1, TEAM 07
	// QUALITY CHANGES

	// Removed very similar code for fillTableForThisMonth, fillTableMonthAhead,
	// and fillTableMonthBack by using Extract Method on
	// generateCalendarForMonth and populateCalendar. Then used Replace Data
	// Value with Object to make more use of this.cal.

	// Original Code
	/**
	 * Displays the calendar for the current month based on the computers month.
	 * 
	 **/
	/*
	 * public void fillTableForThisMonth() { int currentYear = new
	 * GregorianCalendar().get(Calendar.YEAR); this.currentMonth = new
	 * GregorianCalendar().get(Calendar.MONTH) + 1;
	 * this.setTitleMonth(this.currentMonth, currentYear); this.monthsAhead = 0;
	 * this.yearsAhead = 0;
	 * 
	 * String keyStart = currentYear + "/" + String.format("%02d",
	 * this.currentMonth); String currentKey = "";
	 * 
	 * // Generates calendar for current month if none exists while
	 * (currentKey.equals("")) { Set<String> keys = this.scheduleMap.keySet();
	 * for (String key : keys) { if (key.startsWith(keyStart)) { currentKey =
	 * key; break; } } if (currentKey.equals("")) { Thread t = new
	 * Thread(this.schedule); t.start(); // this.schedule.calculateNextMonth();
	 * } }
	 * 
	 * DefaultTableModel table = new DefaultTableModel(new Object[0][0], new
	 * String[0][0]);
	 * 
	 * this.cal = new GregorianCalendar(currentYear, this.currentMonth - 1, 1);
	 * 
	 * while (this.currentMonth == this.cal.get(Calendar.MONTH) + 1) { String
	 * tempKey = this.cal.get(Calendar.YEAR) + "/" + String.format("%02d",
	 * (this.cal.get(Calendar.MONTH) + 1)) + "/" + String.format("%02d",
	 * this.cal.get(Calendar.DAY_OF_MONTH)); if
	 * (this.scheduleMap.containsKey(tempKey)) {
	 * 
	 * int numOfJobs = this.scheduleMap.get(tempKey).size(); String[] colData =
	 * new String[numOfJobs]; int i = 0;
	 * 
	 * for (String key : this.scheduleMap.get(tempKey).keySet()) { colData[i] =
	 * key + ": " + this.scheduleMap.get(tempKey).get(key).getName(); i++; }
	 * 
	 * String numDate = (this.cal.get(Calendar.MONTH) + 1) + "/" +
	 * String.format("%02d", this.cal.get(Calendar.DAY_OF_MONTH)) + "/" +
	 * String.format("%02d", this.cal.get(Calendar.YEAR)); String colTitle =
	 * this.getNameforNum(this.cal .get(Calendar.DAY_OF_WEEK)) + " (" + numDate
	 * + ")"; table.addColumn(colTitle, colData);
	 * 
	 * } this.cal.add(Calendar.DATE, 1); }
	 * 
	 * HTMLGenerator.addMonth(this.monthName, table);
	 * this.scheduleTable.setModel(table); }
	 */

	/**
	 * Displays the next month from current month.
	 * 
	 */
	/*
	 * public void fillTableMonthAhead() { int currentYear = new
	 * GregorianCalendar().get(Calendar.YEAR); this.monthsAhead++; int showMonth
	 * = new GregorianCalendar().get(Calendar.MONTH) + this.monthsAhead + 1;
	 * this.yearsAhead = 0; while (showMonth > 12) { currentYear++; showMonth -=
	 * 12; this.yearsAhead++; } this.setTitleMonth(showMonth, currentYear);
	 * 
	 * String keyStart = currentYear + "/" + String.format("%02d", showMonth);
	 * String currentKey = "";
	 * 
	 * // Generates calendar for current month if none exists while
	 * (currentKey.equals("")) { Set<String> keys = this.scheduleMap.keySet();
	 * for (String key : keys) { if (key.startsWith(keyStart)) { currentKey =
	 * key; break; } } if (currentKey.equals("")) { Thread t = new
	 * Thread(this.schedule); t.start(); // this.schedule.calculateNextMonth();
	 * } }
	 * 
	 * DefaultTableModel table = new DefaultTableModel(new Object[0][0], new
	 * String[0][0]); this.cal = new GregorianCalendar(currentYear, showMonth -
	 * 1, 1);
	 * 
	 * while (showMonth == this.cal.get(Calendar.MONTH) + 1) { String tempKey =
	 * this.cal.get(Calendar.YEAR) + "/" + String.format("%02d",
	 * (this.cal.get(Calendar.MONTH) + 1)) + "/" + String.format("%02d",
	 * this.cal.get(Calendar.DAY_OF_MONTH)); if
	 * (this.scheduleMap.containsKey(tempKey)) {
	 * 
	 * int numOfJobs = this.scheduleMap.get(tempKey).size(); String[] colData =
	 * new String[numOfJobs]; int i = 0;
	 * 
	 * for (String key : this.scheduleMap.get(tempKey).keySet()) { colData[i] =
	 * key + ": " + this.scheduleMap.get(tempKey).get(key).getName(); i++; }
	 * 
	 * String numDate = String.format("%02d", (this.cal.get(Calendar.MONTH) +
	 * 1)) + "/" + String.format("%02d", this.cal.get(Calendar.DAY_OF_MONTH)) +
	 * "/" + this.cal.get(Calendar.YEAR); String colTitle =
	 * this.getNameforNum(this.cal .get(Calendar.DAY_OF_WEEK)) + " (" + numDate
	 * + ")"; table.addColumn(colTitle, colData);
	 * 
	 * } this.cal.add(Calendar.DATE, 1); }
	 * 
	 * HTMLGenerator.addMonth(this.monthName, table);
	 * this.scheduleTable.setModel(table); }
	 */

	/**
	 * Displays the last months from current month.
	 * 
	 */
	/*
	 * public void fillTableMonthBack() { int tempMonths = this.monthsAhead; if
	 * ((new GregorianCalendar().get(Calendar.MONTH) + tempMonths) % 12 == 0) {
	 * this.yearsAhead--; } int currentYear = new
	 * GregorianCalendar().get(Calendar.YEAR) + this.yearsAhead;
	 * this.monthsAhead--; int monthsToAdd = this.monthsAhead; while
	 * (monthsToAdd < -11) { monthsToAdd += 12; currentYear--;
	 * this.yearsAhead--; } int showMonth = new
	 * GregorianCalendar().get(Calendar.MONTH) + monthsToAdd + 1;
	 * 
	 * while (showMonth > 12) { showMonth -= 12; }
	 * 
	 * if (currentYear < this.earliestYear || (currentYear == this.earliestYear
	 * && showMonth < this.earliestMonth)) { this.monthsAhead++;
	 * 
	 * } else { this.setTitleMonth(showMonth, currentYear);
	 * 
	 * String keyStart = currentYear + "/" + String.format("%02d", showMonth);
	 * String currentKey = "";
	 * 
	 * // Generates calendar for current month if none exists while
	 * (currentKey.equals("")) { Set<String> keys = this.scheduleMap.keySet();
	 * for (String key : keys) { if (key.startsWith(keyStart)) { currentKey =
	 * key; break; } } if (currentKey.equals("")) { Thread t = new
	 * Thread(this.schedule); t.start(); // this.schedule.calculateNextMonth();
	 * } }
	 * 
	 * DefaultTableModel table = new DefaultTableModel(new Object[0][0], new
	 * String[0][0]); this.cal = new GregorianCalendar(currentYear, showMonth -
	 * 1, 1);
	 * 
	 * while (showMonth == this.cal.get(Calendar.MONTH) + 1) { String tempKey =
	 * this.cal.get(Calendar.YEAR) + "/" + String.format("%02d",
	 * (this.cal.get(Calendar.MONTH) + 1)) + "/" + String.format("%02d",
	 * this.cal.get(Calendar.DAY_OF_MONTH)); if
	 * (this.scheduleMap.containsKey(tempKey)) {
	 * 
	 * int numOfJobs = this.scheduleMap.get(tempKey).size(); String[] colData =
	 * new String[numOfJobs]; int i = 0;
	 * 
	 * for (String key : this.scheduleMap.get(tempKey).keySet()) { colData[i] =
	 * key + ": " + this.scheduleMap.get(tempKey).get(key) .getName(); i++; }
	 * 
	 * String numDate = String.format("%02d", (this.cal.get(Calendar.MONTH) +
	 * 1)) + "/" + String.format("%02d", this.cal.get(Calendar.DAY_OF_MONTH)) +
	 * "/" + this.cal.get(Calendar.YEAR); String colTitle =
	 * this.getNameforNum(this.cal .get(Calendar.DAY_OF_WEEK)) + " (" + numDate
	 * + ")"; table.addColumn(colTitle, colData);
	 * 
	 * } this.cal.add(Calendar.DATE, 1); }
	 * 
	 * this.scheduleTable.setModel(table);
	 * HTMLGenerator.addMonth(this.monthName, table); }
	 * 
	 * }
	 */

	// SWAP 1, TEAM 07
	// SMELL: Switch Statements - should probably look into libraries to see if
	// this functionality is already implemented; could probably use Replace
	// Type Code with Subclasses - not too sure how useful that would be here.
	
	// SWAP 2, TEAM 01
	// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
	// 1. Extract Method was used for refactoring
	// 2. With the switch statment eliminated, the method is only one line long.  While no new features arise from
	//		change, code can be cleaned up by replacing the getNameforNum with the inline method
	// 3. While the refactoring was successful in eliminating the switch statement, it can be taken a step further by
	//		eliminating the method completely and just using the DateFormatSymbols inline.

	private String getNameforNum(int n) {
		return ExtendedGregCal.DayString(n);
	}

	// SWAP 1, TEAM 07
	// SMELL: Long Method - could be broken up into a method for setting up each
	// component individually and one method for putting them all on the display
	// using Extract Method. This would allow each individual GUI component to
	// be reused elsewhere if necessary, as well as allowing selective creation
	// of components if desired.

	// SWAP 2, Team 01
		// REFACTORING FOR ENHANCEMENT FROM BAD SMELL
		// 1. Extract Method was used here to refactor the code in initComponents so
		// that everything that was being setup had its own method instead of being
		// clumped into one long method.
		// 2. With all of the components and the layout being in their own method,
		// individual changes can be made to each (such as size, color, function)
		// within their own methods, and new buttons or file options can be added by
		// creating a method for them and calling it from initComponents.
		// 3. While the refactoring was successful in making initComponents a
		// smaller method, it can be taken a step further by refactoring each method
		// that was extracted to make sure they aren't doing more than they need to.
		// For now all that was done was for the code to be pulled out of this one
		// method.
		
		private void initComponents() {

			this.monthTitle = new javax.swing.JLabel();
			this.previousMonthButton = new javax.swing.JButton();
			this.nextMonthButton = new javax.swing.JButton();
			this.jScrollPane1 = new javax.swing.JScrollPane();
			this.scheduleTable = new javax.swing.JTable();
			this.popup = new javax.swing.JPopupMenu();
			this.menuBar = new javax.swing.JMenuBar();
			this.fileMenu = new javax.swing.JMenu();
			this.saveChanges = new javax.swing.JMenuItem();
			this.undoChanges = new javax.swing.JMenuItem();
			this.editMenu = new javax.swing.JMenu();
			this.editWorkers = new javax.swing.JMenuItem();
			this.editDays = new javax.swing.JMenuItem();
			this.generateMenu = new javax.swing.JMenu();
			this.genHtml = new javax.swing.JMenuItem();
			this.generateText = new javax.swing.JMenuItem();
			this.preferencesMenu = new javax.swing.JMenu();
			this.localeSubMenu = new javax.swing.JMenu();
			// this.localesPane = new javax.swing.JScrollPane();
			// this.localesPaneMenu = new javax.swing.JMenu();
			setUpScheduleTable();
			setUpButtons();
			setUpEditMenu();
			setUpFileMenu();
			setUpGenerateMenu();
			setUpPreferencesMenu();

			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
			setTitle("Calendar");

			this.monthTitle.setFont(new java.awt.Font("Tahoma", 1, 24));
			this.monthTitle.setText("Month Name Here");

			setJMenuBar(this.menuBar);
			setLayout();

		}

		private void setUpScheduleTable() {
			this.scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
					new Object[][] { { null, null, null }, { null, null, null },
							{ null, null, null }, { null, null, null } },
					new String[] { "Monday (10/22/2012)", "Wednesday (10/24/12)",
							"Thursday (10/26/12)" }));
			this.scheduleTable.setColumnSelectionAllowed(true);
			this.scheduleTable.getTableHeader().setReorderingAllowed(false);

			for (Worker i : this.schedule.getWorkers()) {
				final Worker input = i;
				this.popup.add(new JMenuItem(input.getName())).addActionListener(
						new java.awt.event.ActionListener() {
							@Override
							public void actionPerformed(
									java.awt.event.ActionEvent evt) {
								editCell(input);
							}
						});
			}
			this.scheduleTable.setComponentPopupMenu(this.popup);

			this.jScrollPane1.setViewportView(this.scheduleTable);
		}

		private void setUpButtons() {
			this.previousMonthButton.setText("<");
			this.previousMonthButton
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							previousMonthActionPerformed(evt);
						}
					});

			this.nextMonthButton.setText(">");
			this.nextMonthButton
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							nextMonthActionPerformed(evt);
						}
					});

		}

		private void setUpEditMenu() {
			this.editMenu.setText("Edit");

			this.editWorkers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_W,
					java.awt.event.InputEvent.CTRL_MASK));
			this.editWorkers.setText("Edit Workers");
			this.editWorkers.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					editWorkersActionPerformed(evt);
				}
			});
			this.editMenu.add(this.editWorkers);

			this.editDays.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_D,
					java.awt.event.InputEvent.CTRL_MASK));
			this.editDays.setText("Edit Days");
			this.editDays.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					editDaysActionPerformed(evt);
				}
			});
			this.editMenu.add(this.editDays);

			this.menuBar.add(this.editMenu);
		}

		private void setUpFileMenu() {

			this.fileMenu.setText("File");

			this.saveChanges.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_S,
					java.awt.event.InputEvent.CTRL_MASK));
			this.saveChanges.setText("Save Changes");
			this.saveChanges.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					saveChangesActionPerformed(evt);
				}
			});
			this.fileMenu.add(this.saveChanges);

			this.undoChanges.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_Z,
					java.awt.event.InputEvent.CTRL_MASK));
			this.undoChanges.setText("Undo Changes");
			this.undoChanges.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					undoChangesActionPerformed(evt);
				}
			});
			// this.fileMenu.add(this.undoChanges);

			this.menuBar.add(this.fileMenu);
		}

		private void setUpGenerateMenu() {

			this.generateMenu.setText("Generate");

			this.genHtml.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_H,
					java.awt.event.InputEvent.CTRL_MASK));
			this.genHtml.setText("Generate Web Page");
			this.genHtml.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					genHtmlActionPerformed(evt);
				}
			});
			this.generateMenu.add(this.genHtml);

			this.generateText.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_T,
					java.awt.event.InputEvent.CTRL_MASK));
			this.generateText.setText("Generate Text");
			this.generateText
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							generateTextActionPerformed(evt);
						}
					});
			this.generateMenu.add(this.generateText);

			this.menuBar.add(this.generateMenu);
		}
		
		// SWAP 2 TEAM 01 FURTHER ELABORATION
		// Refactoring included extracting out creating the localSubMenu
		// 1. SMELL: Divergent Changes - the method was dealing with changes to both submenus
		//			and the menu containing it. I used extract method so that the submenu added
		//			had its own method. Now any change to the submenu will not effect the method
		//			setting up the menu.
		// 2. This change enables additional "Preference" submenus to be added without bulking up the
		//		setUpPreferencesMenu. It also helped our understanding of the additional feature.

		private void setUpPreferencesMenu() {
			// SWAP 1, TEAM 07
			// Additional Feature

			// The code below for adding a Preferences menu was added to support
			// localization options.
			this.preferencesMenu.setText("Preferences");
			
			this.preferencesMenu.add(createLocaleSubMenu());

			this.menuBar.add(this.preferencesMenu);
		}
		
		// SWAP 2 TEAM 01 FURTHER ELABORATION
		// The method below was extracted from setUpPreferencesMenu.
		
		private JMenu createLocaleSubMenu()
		{
			this.localeSubMenu.setText("Locale");

			for (Locale l : Locale.getAvailableLocales()) {
				JMenuItem item = new JMenuItem(l.getDisplayName(l));
				item.setLocale(l);
				item.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						changeLocaleActionPerformed(evt);
					}
				});
				this.localeSubMenu.add(item);
			}
			MenuScroller.setScrollerFor(this.localeSubMenu, 10);
			return this.localeSubMenu;
		}

		private void setLayout() {
			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
					getContentPane());
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addComponent(this.jScrollPane1,
							javax.swing.GroupLayout.DEFAULT_SIZE, 1002,
							Short.MAX_VALUE)
					.addGroup(
							layout.createSequentialGroup()
									.addContainerGap()
									.addComponent(this.previousMonthButton)
									.addPreferredGap(
											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(this.monthTitle)
									.addPreferredGap(
											javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(this.nextMonthButton)
									.addGap(0, 0, Short.MAX_VALUE)));
			layout.setVerticalGroup(layout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(
							layout.createSequentialGroup()
									.addContainerGap(18, Short.MAX_VALUE)
									.addGroup(
											layout.createParallelGroup(
													javax.swing.GroupLayout.Alignment.LEADING,
													false)
													.addComponent(
															this.monthTitle,
															javax.swing.GroupLayout.DEFAULT_SIZE,
															javax.swing.GroupLayout.DEFAULT_SIZE,
															Short.MAX_VALUE)
													.addComponent(
															this.previousMonthButton,
															javax.swing.GroupLayout.DEFAULT_SIZE,
															javax.swing.GroupLayout.DEFAULT_SIZE,
															Short.MAX_VALUE)
													.addComponent(
															this.nextMonthButton,
															javax.swing.GroupLayout.PREFERRED_SIZE,
															29,
															javax.swing.GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
											javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(this.jScrollPane1,
											javax.swing.GroupLayout.PREFERRED_SIZE,
											265,
											javax.swing.GroupLayout.PREFERRED_SIZE)));

			pack();
		}


	/**
	 * @param evt
	 */
	private void editWorkersActionPerformed(java.awt.event.ActionEvent evt) {
		ConfigManager.wSet = new WorkerSetup(this.schedule.getWorkers());
		ConfigManager.toggleWorkerSetup();
		ConfigManager.toggleCalendar();
	}

	/**
	 * @param evt
	 */
	private void editDaysActionPerformed(java.awt.event.ActionEvent evt) {
		ConfigManager.config = new Config(ConfigManager.getDays());
		ConfigManager.toggleConfig();
		ConfigManager.toggleCalendar();
	}

	/**
	 * @param evt
	 */
	private void previousMonthActionPerformed(java.awt.event.ActionEvent evt) {
		// SWAP 1, TEAM 07
		// QUALITY CHANGES

		// Added logic here for determining whether or not the previous month
		// can be displayed. Also used Replace Temp with Query to eliminate
		// temporary variables and clear up what values are being used for
		// method calls.

		// Original Code
		// this.fillTableMonthBack();

		// New Code
		this.cal.add(Calendar.MONTH, -1);
		if (this.cal.get(Calendar.YEAR) < this.earliestYear
				|| (this.cal.get(Calendar.YEAR) == this.earliestYear && this.cal
						.get(Calendar.MONTH) + 1 < this.earliestMonth)) {
			this.cal.add(Calendar.MONTH, 1);
		} else {
			this.fillTableForMonth();
		}
	}

	/**
	 * @param evt
	 */
	private void nextMonthActionPerformed(java.awt.event.ActionEvent evt) {
		// SWAP 1, TEAM 07
		// QUALITY CHANGES

		// Utilized this.cal for month and year changes, removed duplicate code
		// from fillTableForCurrentMonth, fillTableMonthAhead, and
		// fillTableMonthBack by creating fillTableForMonth.

		// Original Code
		// this.fillTableMonthAhead();

		// New Code
		this.cal.add(Calendar.MONTH, 1);
		this.fillTableForMonth();
	}

	/**
	 * @param evt
	 */
	private void genHtmlActionPerformed(java.awt.event.ActionEvent evt) {
		HTMLGenerator.writeHtml();
	}

	// SWAP 1, TEAM 07
	// SMELL: Not sure, but I think the algorithm could be improved...

	/**
	 * @param evt
	 */
	private void generateTextActionPerformed(java.awt.event.ActionEvent evt) {
		NavigableSet<String> keySet = this.scheduleMap.navigableKeySet();
		String textOutput = new String();
		File readout = new File("Calendar.txt");
		ArrayList<String> dutyRows = new ArrayList<String>();

		int column = 1;
		for (String i : keySet) {
			textOutput += String.format("%-30s", "|" + i);
			NavigableSet<String> valueSet = this.scheduleMap.get(i)
					.navigableKeySet();
			int row = 0;
			for (String j : valueSet) {
				if (dutyRows.size() <= row)
					dutyRows.add("");
				String newCol = dutyRows.get(row) + "|" + j + ": "
						+ this.scheduleMap.get(i).get(j).getName();

				dutyRows.set(row,
						String.format("%-" + 30 * column + "s", newCol));
				row += 1;
			}
			column += 1;
		}

		for (String i : dutyRows) {
			textOutput += "\n" + i;
		}

		char[] letterOutput = textOutput.toCharArray();

		try {
			readout.createNewFile();

			FileWriter outFile = new FileWriter(readout);
			for (char i : letterOutput)
				outFile.write(i);
			outFile.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @param evt
	 */
	private void saveChangesActionPerformed(java.awt.event.ActionEvent evt) {
		ConfigManager.dumpConfigFile();
	}

	/**
	 * @param evt
	 */
	private void undoChangesActionPerformed(java.awt.event.ActionEvent evt) {
		// removed
	}

	// SWAP 1, TEAM 07
	// Additional Feature

	// The method below was added to allow for localization of the application,
	// enabling schedules to be displayed in different languages. This would
	// have been more difficult if the code had not been refactored to remove
	// duplication first because not only would changes need to be made in
	// multiple locations, but the switch statement that was removed would have
	// had to have been expanded to account for each possible locale.
	

	// SWAP 2 TEAM 01 FURTHER ELABORATION
	// The method now sets the locale on the config manager so that it can be saved for
	// later use.
	/**
	 * @param evt
	 */
	private void changeLocaleActionPerformed(java.awt.event.ActionEvent evt) {
		JMenuItem source = (JMenuItem) evt.getSource();
		this.locale = source.getLocale();
		this.monthTitle.setText(this.cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
						this.locale) + " " + this.cal.get(Calendar.YEAR));
		this.monthTitle.repaint();
		ConfigManager.setLocale(this.locale);
		ConfigManager.dumpConfigFile();
	}

	private void editCell(Worker input) {
		int i = this.scheduleTable.getSelectedRow();
		int j = this.scheduleTable.getSelectedColumn();
		if (this.scheduleTable.getValueAt(i, j) != null) {
			System.out.println(this.scheduleTable.getColumnName(j));
			String job = this.scheduleTable.getValueAt(i, j).toString()
					.split(":")[0];
			String date = this.scheduleTable.getColumnName(j).split(" ")[1];
			date = date.substring(1, date.length() - 1);
			String[] dateNums = date.split("/");
			date = dateNums[2] + "/" + dateNums[0] + "/" + dateNums[1];
			System.out.println(date);
			this.scheduleMap.get(date).put(job, input);
			this.scheduleTable.setValueAt(job + ": " + input.getName(), i, j);
		}
	}

	// SWAP 1, TEAM 07
	// QUALITY CHANGES

	// Used Extract Method on code below to reduce the length of the
	// fillTableForMonth method and to eliminate the comment explaining what
	// this code is supposed to do.

	private void generateCalendarForMonth(String keyStart) {
		String currentKey = "";
		while (currentKey.equals("")) {
			Set<String> keys = this.scheduleMap.keySet();
			for (String key : keys) {
				if (key.startsWith(keyStart)) {
					currentKey = key;
					break;
				}
			}
			if (currentKey.equals("")) {
				//Thread t = new Thread(this.schedule);
				this.schedule.run();
			}
		}
	}

	// SWAP 1, TEAM 07
	// QUALITY CHANGES

	// Used Extract Method on code below to reduce Duplicated Code. Making this
	// a method will allow, with slight modification, the population of any
	// month of any year.

	private DefaultTableModel populateCalendar(int month) {
		DefaultTableModel table = new DefaultTableModel(new Object[0][0],
				new String[0][0]);
		this.cal.set(Calendar.DAY_OF_MONTH, 1);

		while (month == this.cal.get(Calendar.MONTH)) {
			String tempKey = this.cal.get(Calendar.YEAR)
					+ "/"
					+ String.format("%02d", (this.cal.get(Calendar.MONTH) + 1))
					+ "/"
					+ String.format("%02d", this.cal.get(Calendar.DAY_OF_MONTH));

			if (this.scheduleMap.containsKey(tempKey)) {
				int numOfJobs = this.scheduleMap.get(tempKey).size();
				String[] colData = new String[numOfJobs];
				int i = 0;

				for (String key : this.scheduleMap.get(tempKey).keySet()) {
					colData[i] = key + ": "
							+ this.scheduleMap.get(tempKey).get(key).getName();
					i++;
				}

				String numDate = String.format("%02d",
						(this.cal.get(Calendar.MONTH) + 1))
						+ "/"
						+ String.format("%02d",
								this.cal.get(Calendar.DAY_OF_MONTH))
						+ "/"
						+ String.format("%02d", this.cal.get(Calendar.YEAR));
				String colTitle = this.getNameforNum(this.cal.get(Calendar.DAY_OF_WEEK)) + " (" + numDate + ")";
				table.addColumn(colTitle, colData);
			}
			this.cal.add(Calendar.DATE, 1);
		}
		this.cal.add(Calendar.MONTH, -1);
		return table;
	}

	// SWAP 1, TEAM 07
	// QUALITY CHANGES

	// New Code
	/**
	 * Displays the calendar for the current month based on this.cal
	 */
	public void fillTableForMonth() {
		this.monthTitle
				.setText(this.cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
						this.locale) + " " + this.cal.get(Calendar.YEAR));

		String keyStart = this.cal.get(Calendar.YEAR) + "/"
				+ String.format("%02d", this.cal.get(Calendar.MONTH) + 1);

		generateCalendarForMonth(keyStart);

		DefaultTableModel table = populateCalendar(this.cal.get(Calendar.MONTH));

		HTMLGenerator.addMonth(this.monthTitle.getText(), table);
		this.scheduleTable.setModel(table);
	}

	private javax.swing.JMenuItem editDays;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenuItem editWorkers;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenuItem genHtml;
	private javax.swing.JMenu generateMenu;
	private javax.swing.JMenuItem generateText;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JLabel monthTitle;
	private javax.swing.JButton nextMonthButton;
	private javax.swing.JPopupMenu popup;
	private javax.swing.JButton previousMonthButton;
	private javax.swing.JMenuItem saveChanges;
	private javax.swing.JTable scheduleTable;
	private javax.swing.JMenuItem undoChanges;

	// SWAP 1, TEAM 07
	// Additional Feature

	// The two fields below were added to enable switching between Locales using
	// a menu on the GUI.
	private javax.swing.JMenu preferencesMenu;
	private javax.swing.JMenu localeSubMenu;
}