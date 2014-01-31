package models;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A model class to represent a JobApplication
 * @author nilsma
 *
 */
public class JobApplication {
	private String id;
	private String company;
	private String position;
	private Date applied;
	private Date duedate;
	private Date followup;
	private String status;
	private String notes;
	private boolean active;
	private List<File> files;
	
	/**
	 * No-param constructor
	 */
	public JobApplication() {
		this.id = "";
		this.company = "";
		this.position = "";
		this.applied = null;
		this.duedate = null;
		this.followup = null;
		this.status = "";
		this.notes = "";
		this.files = new ArrayList<File>();
		this.active = defineActive();
	}
	
	/**
	 * A JobApplication constructor
	 * @param id int number of the id
	 * @param company String name of the company the application concerns 
	 * @param position String description of the position the application concerns
	 * @param applied Date the date the application was sent
	 * @param duedate Date the applicants' due-date for the position, the last day the company accepts new applications
	 * @param followup Date for following up the application
	 * @param status String description of the status, or the phase, of the application 
	 * @param notes String of notes in regards to the application
	 * @param files ArrayList<String> holding the path of the applications associated files
	 */
	public JobApplication(String id, String company, String position, String applied, 
			String duedate, String followup, String status, String notes, List<File> files) {
		this.id = id;
		this.company = company;
		this.position = position;
		this.applied = setDate(applied);
		this.duedate = setDate(duedate);
		this.followup = setDate(followup);
		this.status = defineStatus(status);
		this.status = status;
		this.notes = notes;
		this.active = defineActive();
		this.files = files;
	}
	
	/**
	 * A method to add a given file to the files ArrayList<File>
	 * @param file the file to add
	 */
	public void addFile(File file) {
		files.add(file);
	}
	
	/**
	 * a method that sets the application status according to the dates in the application
	 * if the currentStatus is that the application is dead then nothing is done
	 * @return the status of the application
	 */
	public String defineStatus(String currentStatus) {
		Date today = new Date();
		String status = "";
		if(currentStatus.trim().toLowerCase().equals("application dead")) {
			status = currentStatus;
		} else if(today.getTime() >= this.getFollowup().getTime()) {
			status = "Follow-up!";
		} else if((today.getTime() >= getDuedate().getTime()) && (today.getTime() < getFollowup().getTime())) {
			status = "Duedate reached";
		} else if((today.getTime() >= getApplied().getTime()) && (today.getTime() < getDuedate().getTime())) {
			status = "Application sent";
		}
		return status;
	}
	
	/**
	 * A method to set the variable 'active' describing whether the application is still active or not  
	 * @return boolean true if the application is still active, false otherwise
	 */
	public boolean defineActive() {
		if(status.trim().toLowerCase().equals("application dead")) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * A method which all Dates are run through to format the date according to the given pattern
	 * @param string the Date as a String
	 * @return the date
	 */
	public Date setDate(String string) {
		Date date = null;
		try {
			date = new SimpleDateFormat("ddMMyy").parse(string);
		} catch (ParseException e) {
			System.out.println("Something went wrong while setting the date!");
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * A method to get the title of each column 
	 * @param column int index of the column to get the title for
	 * @return the title of the given column, returns "Unspecified" if the index is not covered as a case 
	 */
	@SuppressWarnings("unused")
	public String getColumnData(int column) {
		DateFormat sdf = new SimpleDateFormat("dd.MM.yy");
		String date = "";
		switch (column) {
		case 0: 
			return getCompany();
		case 1: 
			return getPosition();
		case 2:
			return date = sdf.format(getApplied());
		case 3:
			return date = sdf.format(getDuedate());
		case 4:
			return date = sdf.format(getFollowup());
		case 5:
			return getStatus();
		case 6:
			return getNotes();
		}
		return "Unspecified";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param string the id to set
	 */
	public void setId(String string) {
		this.id = string;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the applied
	 */
	public Date getApplied() {
		return applied;
	}

	/**
	 * @param applied the applied to set
	 */
	public void setApplied(String applied) {
		this.applied = setDate(applied);
	}

	/**
	 * @return the duedate
	 */
	public Date getDuedate() {
		return duedate;
	}

	/**
	 * @param duedate the duedate to set
	 */
	public void setDuedate(String duedate) {
		this.duedate = setDate(duedate);
	}

	/**
	 * @return the followup
	 */
	public Date getFollowup() {
		return followup;
	}

	/**
	 * @param followup the followup to set
	 */
	public void setFollowup(String followup) {
		this.followup = setDate(followup);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * sets the description of the application status, also
	 * calls the defineActive method to see whether the application should
	 * be set to inactive based on the new application status
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
		active = defineActive();
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the files
	 */
	public List<File> getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(List<File> files) {
		this.files = files;
	}
}
