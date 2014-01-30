package utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.JobApplication;

public class JobApplicationSorter {
	
	private static Comparator<JobApplication> COMPANY = new Comparator<JobApplication>() {
		@Override
		public int compare(JobApplication app1, JobApplication app2) {
			return app1.getCompany().trim().toLowerCase().compareTo(app2.getCompany().trim().toLowerCase());
		}
	};
	private static Comparator<JobApplication> POSITION = new Comparator<JobApplication>() {
		@Override
		public int compare(JobApplication app1, JobApplication app2) {
			return app1.getPosition().trim().toLowerCase().compareTo(app2.getPosition().trim().toLowerCase());
		}
	};
	private static Comparator<JobApplication> APPLIED = new Comparator<JobApplication>() {
		@Override
		public int compare(JobApplication app1, JobApplication app2) {
			return app1.getApplied().compareTo(app2.getApplied());
		}
	};
	private static Comparator<JobApplication> DUEDATE = new Comparator<JobApplication>() {
		@Override
		public int compare(JobApplication app1, JobApplication app2) {
			return app1.getDuedate().compareTo(app2.getDuedate());
		}
	};
	private static Comparator<JobApplication> FOLLOWUP = new Comparator<JobApplication>() {
		@Override
		public int compare(JobApplication app1, JobApplication app2) {
			return app1.getFollowup().compareTo(app2.getFollowup());
		}
	};
	private static Comparator<JobApplication> STATUS = new Comparator<JobApplication>() {
		@Override
		public int compare(JobApplication app1, JobApplication app2) {
			return app1.getStatus().compareTo(app2.getStatus());
		}
	};
	
	/**
	 * A method to sort the table of job-applications on the given attribute
	 * @param list the list of job-applications to be sorted
	 * @return a list of sorted job-applications based on the parameter given
	 */
	public static List<JobApplication> sortApplications(List<JobApplication> applications, String theCategory) {
		String category = theCategory;
		if(category.trim().toLowerCase().equals("company")) {
			Collections.sort(applications, COMPANY);
			return applications;
		} else if(category.trim().toLowerCase().equals("position")) {
			Collections.sort(applications, POSITION);
			return applications;
		} else if(category.trim().toLowerCase().equals("applied")) {
			Collections.sort(applications, APPLIED);
			return applications;
		} else if(category.trim().toLowerCase().equals("duedate")) {
			Collections.sort(applications, DUEDATE);
			return applications;
		} else if(category.trim().toLowerCase().equals("followup")) {
			Collections.sort(applications, FOLLOWUP);
			return applications;
		} else if(category.trim().toLowerCase().equals("status")) {
			Collections.sort(applications, STATUS);
			return applications;
		} else {
			return applications;
		}
	}
}
