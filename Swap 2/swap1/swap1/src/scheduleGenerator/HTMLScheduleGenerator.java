package scheduleGenerator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

/**
 * Used to generate HTML for a schedule.
 * 
 * @author schneimd. Created Oct 26, 2012.
 */
public class HTMLScheduleGenerator extends ScheduleGenerator {
	
	public HTMLScheduleGenerator(Schedule schedule) {
		super(schedule);
	}


	/**
	 * Used to write.
	 */
	PrintWriter writer;
	/**
	 * Stores path to file.
	 */
	File path;
	
	
	/**
	 * finalizes and writes to file.
	 *
	 */
	public boolean write() {
		/*
		 * SWAP 1, TEAM 2 (JORDON/FRANCIS)
		 * BONUS FEATURE - Editable Output
		 */
		generate();
		String all = top+insert.subSequence(0,insert.length()-1)+"];"+(table.subSequence(0,table.length()-1)+"];")+bottom+foot;
		path = new File("index.html");
		if(path.exists()) {
			path.delete();
		}
		try {
			path.createNewFile();
			writer = new PrintWriter(path);
			writer.println(all);
			writer.close();
		} catch (IOException exception) {
			
			exception.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Populates all of the schedule data.
	 */
	private void generate() {
		/*
		 * SWAP 1, TEAM 2 (JORDON/FRANCIS)
		 * BONUS FEATURE - Editable Output
		 */
		TreeMap<Date, TreeMap<String, Worker>> sch = this.schedule.getDatedSchedule();
		ArrayList<Date> days = new ArrayList<Date>(sch.navigableKeySet());
		java.util.Collections.sort(days);
		Calendar current = Calendar.getInstance();
		Calendar next = Calendar.getInstance();
		TreeMap<Date, TreeMap<String, Worker>> month = new TreeMap<Date, TreeMap<String, Worker>>();
		
		for (int i = 0; i < days.size() - 1; i++) {
			current.setTime(days.get(i));
			next.setTime(days.get(i+1));
			
			month.put(current.getTime(), sch.get(current.getTime()));
			if (current.get(Calendar.MONTH) != next.get(Calendar.MONTH)) {
				addMonthTable(month);
				addMonthData(month);
				month = new TreeMap<Date, TreeMap<String, Worker>>();
			}
		}
		month.put(days.get(days.size()-1), sch.get(days.get(days.size()-1)));
		addMonthTable(month);
		addMonthData(month);
	}
	
	/**
	 * Adds a month to the Javascript data array in the format:
	 * 		[['Month Name',['job name: worker name']]]
	 * @param duties
	 */
	private void addMonthData(TreeMap<Date, TreeMap<String, Worker>> duties) {
		/*
		 * SWAP 1, TEAM 2 (JORDON/FRANCIS)
		 * BONUS FEATURE - Editable Output
		 */
		String name = new SimpleDateFormat("MMM yyyy").format(duties.firstKey());
		String tmp = "['"+name+"',[";
		
		SimpleDateFormat fmt = new SimpleDateFormat("EE (MM/dd/yyyy)");
		
		ArrayList<Date> dates = new ArrayList<Date>(duties.navigableKeySet());
		java.util.Collections.sort(dates);
		NavigableSet<String> jobs;
		
		for (Date d : dates) {
			tmp += "['"+fmt.format(d)+"'";
			jobs = duties.get(d).navigableKeySet();
			for (String job : jobs) {
				tmp += ",'"+job+": "+duties.get(d).get(job).getName()+"'";
			}
			tmp += "],";
		}
		tmp = tmp.substring(0, tmp.length() - 1);
		tmp += "]],";
		this.insert += tmp;
	}
	
	/**
	 * Adds in the HTML for a month's table based on the number of jobs for a day and
	 * the number of days.
	 * @param duties
	 */
	public void addMonthTable(TreeMap<Date, TreeMap<String, Worker>> duties) {
		/*
		 * SWAP 1, TEAM 2 (JORDON/FRANCIS)
		 * BONUS FEATURE - Editable Output
		 */
		ArrayList<Date> dates = new ArrayList<Date>(duties.navigableKeySet());
		java.util.Collections.sort(dates);
		String tempTable = "\"<table width='100%' height='44%' border='1'><tr>";
		for(int c = 0; c<duties.size(); c++) {
			tempTable += "<td class='day'>" +
					"<table width='100%' border='1'>" +
					"<tr>" +
					"<th id='"+c+",0' scope='col'></th></tr>";
			
			int size = duties.get(dates.get(c)).size();
			for(int r = 1; r<=size; r++) {
				tempTable +=  "<tr><td id='"+c+","+r+"' class='day'></td></tr>";
			}
			tempTable += "</table></td>";
		}
		tempTable += "</tr></table>\",";
		table+=tempTable;
	}
	
	/**
	 * Returns the generated tables for javascript.
	 *
	 * @return table
	 */
	public String getTables() {
		return table;
	}
	
	/**
	 * sets the tables for javascript to use.
	 *
	 * @param tables
	 */
	public void setTables(String tables) {
		table = tables;
	}
	
	
	/**
	 * Used to store array for javascipt.
	 */
	private String insert = "\nvar months = [";

	/**
	 * Used to store tables for each month.
	 */
	private String table = "\nvar tables = ["; // This needs to remove the last
											// comma and add a bracket.

	/**
	 * Used to put header.
	 */
	private String top = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>Rose-Hulman Schedule Generator</title><link href='css/main.css' rel='stylesheet' type='text/css' />\n" +
			"<script type='text/javascript'>window.onload = function() {\n	" +
			"		document.getElementById('monthName').innerHTML = '<input onclick=\\\'monthBack()\\\' type=\\\'submit\\\' name=\\\'monthBack\\\' id=\\\'monthBack\\\' value=\\\'&lt;\\\' /> ' + months[monthIndex][0] + 		' <input type=\\\'submit\\\' name=\\\'monthAhead\\\' id=\\\'monthAhead\\\' value=\\\'&gt;\\\' onclick=\\\'monthAhead()\\\' />';\n" +
			"		document.getElementById('middle').innerHTML = tables[monthIndex];\n" +
			"		var numCols = months[monthIndex][1].length;\n" +
			"		for(var c = 0; c<numCols; c++) {\n			" +
			"var numRows = months[monthIndex][1][c].length;\n" +
			"			for(var r = 0; r<numRows; r++) {\n" +
			"				document.getElementById(c + ',' + r).innerHTML = months[monthIndex][1][c][r];\n" +
			"			}\n" +
			"		}\n}\n" +
			"var col = 0;\n" +
			"var row = 0;\n" +
			"var monthIndex = 0;\n";
	// Insert JavaScript
	/**
	 * Used to fill all of body.
	 */
	private String bottom = "function monthBack() {	if(monthIndex > 0) {\n	" +
			"	monthIndex--;\n" +
			"	document.getElementById('middle').innerHTML = tables[monthIndex];\n" +
			"	document.getElementById('monthName').innerHTML = '<input onclick=\\'monthBack()\\' type=\\'submit\\' name=\\'monthBack\\' id=\\'monthBack\\' value=\\'&lt;\\' /> ' + months[monthIndex][0] + 		' <input type=\\'submit\\' name=\\'monthAhead\\' id=\\'monthAhead\\' value=\\'&gt;\\' onclick=\\'monthAhead()\\' />';\n" +
			"		var numCols = months[monthIndex][1].length;\n" +
			"		for(var c = 0; c<numCols; c++) {\n" +
			"			var numRows = months[monthIndex][1][c].length;\n" +
			"			for(var r = 0; r<numRows; r++) {\n" +
			"				document.getElementById(c + ',' + r).innerHTML = months[monthIndex][1][c][r];\n" +
			"			}\n" +
			"		}\n" +
			"	}\n" +
			"}\n" +
			"function monthAhead() {\n" +
			"	if(monthIndex < months.length - 1) {\n" +
			"		monthIndex++;\n" +
			"		document.getElementById('middle').innerHTML = tables[monthIndex];\n" +
			"		document.getElementById('monthName').innerHTML = '<input onclick=\\'monthBack()\\' type=\\'submit\\' name=\\'monthBack\\' id=\\'monthBack\\' value=\\'&lt;\\' /> ' + months[monthIndex][0] + 		' <input type=\\'submit\\' name=\\'monthAhead\\' id=\\'monthAhead\\' value=\\'&gt;\\' onclick=\\'monthAhead()\\' />';\n" +
			"		var numCols = months[monthIndex][1].length;\n" +
			"		for(var c = 0; c<numCols; c++) {\n" +
			"			var numRows = months[monthIndex][1][c].length;\n" +
			"			for(var r = 0; r<numRows; r++) {\n" +
			"				document.getElementById(c + ',' + r).innerHTML = months[monthIndex][1][c][r];\n" +
			"			}\n" +
			"		}\n" +
			"	}\n" +
			"}\n" +
			"</script>\n" +
			"</head><body><table width='100%' border='0' id='container'>  <tr>   <td colspan='3' id='head'><img src='images/head.gif' width='500' height='150' alt='header' /></td> </tr> <tr>   <td width='15%' rowspan='2' id='left'>&nbsp;</td>   <td height='84' id='monthName'></td>   <td width='19%' rowspan='2' id='right'>&nbsp;</td> </tr> <tr>   <td width='66%' height='606' id='middle'>";
	// Insert Table
	/**
	 * Used as footer
	 */
	private String foot = "</td></tr></table></td></tr><tr><td colspan='3' id='foot'>&nbsp;</td></tr></table></body></html>";
}