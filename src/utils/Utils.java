package utils;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;

import xml.StaxParser;

import models.JobApplication;

/**
 * Utility class for the application
 * @author nilsma
 *
 */
public class Utils {
	private static Dimension screenSize;
	private static int applicationFrameWidth;
	private static int applicationFrameHeight;
	private static String lastUsedId = "0";
	private static List<JobApplication> originalList;
	
	public static void setOriginalList(String filename) {
		StaxParser staxParser = StaxParser.getInstance();
		List<JobApplication> list = new ArrayList<JobApplication>();
		try {
			list = staxParser.readFile(filename);
		} catch (IOException e) {
			System.out.println("Something went wrong while trying to parse the file: " + filename + " (IOException)");
			e.printStackTrace();
		} catch (XMLStreamException e) {
			System.out.println("Something went wrong while trying to parse the file: " + filename + " (XMLStreamException)");
			e.printStackTrace();
		}
		originalList = list;
	}
	
	/**
	 * a method to show a generic error message
	 */
	public static void errorMessage() {
		JOptionPane.showMessageDialog(null, "You must choose a file to work on first, \n " +
				"you can do so in the File menu \n");
	}
	
	/**
	 * takes a file and checks its filename and appends the xml extension if it does not already
	 * use that extension in the filename
	 * @param file the file of the filename to be checked
	 * @return newFile with the new, verified extension
	 */
	public static File checkPath(File file) {
		String filename = file.getAbsolutePath();
		if(filename.contains(".")) {
			filename = filename.substring(0, filename.indexOf(".") + 1);
			filename += "xml";
			File newFile = new File(filename);
			return newFile;
		} else {
			filename += ".xml";
			File newFile = new File(filename);
			return newFile;
		}
	}
	
	/**
	 * a method to format a date to string
	 * @param date the date to be formatted
	 * @return the date as a string
	 */
	public static String dateToString(Date date) {
		DateFormat sdf = new SimpleDateFormat("ddMMyy");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	/**
	 * a method to convert a date from String to Date
	 * @param convert the date to be converted
	 * @return Date the converted date
	 */
	public static Date stringToDate(String convert) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yy").parse(convert);
		return date;
	}
	
	/**
	 * a method to decrease the lastUsedId by 1, unless
	 * the lastUsedId already is at 0, in which case
	 * the lastUsedId remains at 0
	 */
	public static void decreaseLastUsedId() {
		Integer temp = new Integer(0);
		temp = Integer.parseInt(lastUsedId);
		if(temp <= 0) {
			lastUsedId = temp.toString();
		} else {
			temp = temp + 1;
			lastUsedId = temp.toString();
		}
	}
	
	/**
	 * a method to increase the lastUsedId by 1
	 */
	public static void increaseLastUsedId() {
		Integer temp = new Integer(0);
		temp = Integer.parseInt(lastUsedId);
		temp = temp + 1;
		lastUsedId = temp + "";
	}

	/**
	 * @return the lastUsedId
	 */
	public static String getLastUsedId() {
		return lastUsedId;
	}
	
	/**
	 * a method to get the next application id
	 * @return the next application id to be used
	 */
	public static String getNextId() {
		Integer temp = new Integer(0);
		temp = Integer.parseInt(lastUsedId);
		Integer nextId = temp + 1;
		return nextId.toString();
	}
	
	/**
	 * a method that sets the last id used in the xml-file in question
	 * @param applications a List of JobApplications 
	 */
	public static void setLastUsedId(List<JobApplication> applications) {
		if(applications.size() == 0) {
			lastUsedId = "0";
		} else {
			Integer temp = new Integer(0);
			Integer current = new Integer(0);
			for(JobApplication app : applications) {
				temp = Integer.parseInt(app.getId());
				if(temp > current) {
					current = temp;
				}
			}
			lastUsedId = current + "";
		}
	}

	/**
	 * @param screenSize the screenSize to set
	 */
	public static void setScreenSize(Dimension screenSize) {
		Utils.screenSize = screenSize;
	}
	
	/**
	 * a method to get the screensizes
	 * @return Dimension screensize the dimensions of the screen
	 */
	public static Dimension getScreenSize() {
		screenSize = new Dimension(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, 
				java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		return screenSize; 
	}
	
	/**
	 * sets the applicationFrameWidth variable
	 * @param width
	 */
	public static void setApplicationFrameWidth(int width) {
		Utils.applicationFrameWidth = width;
	}
	
	/**
	 * sets the applicationFrameHeight variable
	 * @param height
	 */
	public static void setApplicationFrameHeight(int height) {
		Utils.applicationFrameHeight = height;
	}
	
	/**
	 * @return applicationFrameWidth 
	 */
	public static int getApplicationFrameWidth() {
		return applicationFrameWidth;
	}
	
	/**
	 * @return applicationFrameHeight
	 */
	public static int getApplicationFrameHeight() {
		return applicationFrameHeight;
	}

	/**
	 * @return the originalList
	 */
	public static List<JobApplication> getOriginalList() {
		return originalList;
	}
	
}
