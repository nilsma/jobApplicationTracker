package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import models.JobApplication;
import models.Profile;

import utils.JobApplicationSorter;
import utils.Utils;
import views.ApplicationView;
import views.StartView;
import xml.PreferencesXMLWriter;
import xml.StaxParser;
import xml.StaxWriterRev;

/**
 * a controller class to control the main-application mainframe
 * @author Nils Martinussen
 * @version 0.1-13.08.2013
 */
public class ApplicationController implements ActionListener {
	@SuppressWarnings("unused")
	private AppDetailsController appDetailsController;
	@SuppressWarnings("unused")
	private PreferencesController preferencesController;
	private String preferencesPath;
	private String rootFolder = "/home/nilsma/JobApplicationTracker";
	private StartView startView;
	private ApplicationView applicationView;
	private StartController startController;
	private RegisterNewAppController regNewAppController;
	private String filename;
	private List<JobApplication> applications;
	private ProfilesController profilesController;
	private List<Profile> profilesList;
	private String lastUsedProfile;

	/**
	 * ApplicationController constructor which employs a run()-method
	 * to instantiate the main-application view, adds actionlisteners
	 */
	public ApplicationController() {
		setApplicationDimensions();
		startController = new StartController(this);
		startView = startController.getStartView();
		
		setPreferencesPath(rootFolder + "/preferences.xml");
		preferencesController = new PreferencesController(this);

		run();

		applicationView.getChangeUser().addActionListener(this);
		applicationView.getCloseapplication().addActionListener(this);
		
		this.profilesController = new ProfilesController(this);
	}

	/**
	 * listens to actions performed on the main-application file-menu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == applicationView.getChangeUser()) {
			lastUsedProfile = "";
			profilesController.checkLastUsedProfile();
		} else if(e.getSource() == applicationView.getCloseapplication()) {
			promptForQuit();
		}
	}
	
	/**
	 * a method to start the main-application main view
	 */
	public void run() {
		applicationView = new ApplicationView(startController.getStartView());
		applicationView.setVisible(true);
	}
	
	/**
	 * a method to update the main-applications last used profile field
	 * and to write the name of the profile to the preferences.xml file
	 * @param name of the last used profile
	 */
	public void updateLastUsedProfile(String name) {
		lastUsedProfile = name;
		PreferencesXMLWriter preferencesWriter = PreferencesXMLWriter.getInstance();
		try {
			preferencesWriter.writeLastUsedProfile(name, preferencesPath);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to update last used profile (IO)");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to update last used profile (Parser)");
			e.printStackTrace();
		} catch (TransformerException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to update last used profile (Transformer)");
			e.printStackTrace();
		}
	}
	
	/**
	 * a method that prompts the user for verification, and
	 * closes the main-application if the user chooses to
	 */
	public void promptForQuit() {
		Object[] options = {"Yes", "No"};
		int returnVal = JOptionPane.showOptionDialog(
				null, "Do you wish to to quit?", "Quit Program",
				JOptionPane.OK_OPTION, JOptionPane.CANCEL_OPTION,
				null, options, options[1]);
		if(returnVal == JOptionPane.OK_OPTION) {
			killProgram();
		} 
	}
	
	/**
	 * a method to update and sort the main-applications job-application-list
	 * and update the table of job-applications  
	 */
	public void updateApplication() {
		Utils.setOriginalList(filename);
		applications = Utils.getOriginalList();
		Utils.setLastUsedId(applications);
		applications = JobApplicationSorter.sortApplications(
				applications, startView.getSortCategories().getSelectedItem().toString()
				);
		startController.updateTable();
	}
	
	/**
	 * a method to kill the main-application
	 */
	public void killProgram() {
		System.exit(0);
	}

	/**
	 * a method to generate the list of job-applications
	 * @param filename of the xml-file to be parsed
	 * @return a list of job-applications
	 */
	public List<JobApplication> loadApplications(String filename) {
		StaxParser staxParser = StaxParser.getInstance();
		List<JobApplication> list = new ArrayList<JobApplication>();
		try {
			list = staxParser.readFile(filename);
			return list;
		} catch (IOException e) {
			System.out.println("Something went wrong while trying to parse the file: " + filename + " (IOException)");
			e.printStackTrace();
		} catch (XMLStreamException e) {
			System.out.println("Something went wrong while trying to parse the file: " + filename + " (XMLStreamException)");
			e.printStackTrace();
		}
		return applications;
	}

	/**
	 * a method to set the main-application view
	 * by removing components from the content pane
	 * and adding the general startView before repainting the view 
	 */
	public void setStartView() {
		applicationView.getContentPane().removeAll();
		applicationView.getContentPane().add(startController.getStartView());
		applicationView.validate();
		applicationView.repaint();
	}

	/**
	 * a method that removes a job-application from the list of job-applications,
	 * and subsequently calls the updateListOfApplications() method to refresh the list on display
	 * @param index of the job-application to be removed
	 */
	public void removeRow(int index) {
		applications.remove(index);
		updateListOfApplications();
	}

	/**
	 * a method to update the list of job-applications xml-file 
	 * by rewriting the xml-file of job-applications
	 */
	public void updateListOfApplications() {
		StaxWriterRev staxWriter = StaxWriterRev.getInstance();
		try {
			staxWriter.addApplication(applications, filename);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to add an application to the list of applications");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to add an application to the list of applications");			
			e.printStackTrace();
		} catch (TransformerException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to add an application to the list of applications");
			e.printStackTrace();
		}
	}

	/**
	 * a method to create an instance of AppDetailsController to
	 * display the details of the given job-application specified
	 * through the given param index 
	 * @param index of the job-application of which to display the details
	 */
	public void initiateAppDetailsController(int index) {
		this.appDetailsController = new AppDetailsController(this, index);
	}

	/**
	 * a method to create an instance of RegisterNewAppController
	 */
	public void initiateRegNewAppController() {
		this.regNewAppController = new RegisterNewAppController(this, startController, applicationView, applications);
	}
	
	/**
	 * sets the screen dimensions of the application in the Utils-class
	 */
	public void setApplicationDimensions() {
		Utils.setApplicationFrameWidth((int) java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width);
		Utils.setApplicationFrameHeight((int) java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @paramupdateApps filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
		applicationView.updateTitle(filename);
	}

	/**
	 * @return the applications
	 */
	public List<JobApplication> getApplications() {
		return applications;
	}

	/**
	 * @param applications the applications to set
	 */
	public void setApplications(List<JobApplication> applications) {
		this.applications = applications;
	}

	/**
	 * @return the applicationController
	 */
	public ApplicationController getApplicationController() {
		return this;
	}

	/**
	 * @return the regNewAppController
	 */
	public RegisterNewAppController getRegNewAppController() {
		return regNewAppController;
	}

	/**
	 * @param regNewAppController the regNewAppController to set
	 */
	public void setRegNewAppController(RegisterNewAppController regNewAppController) {
		this.regNewAppController = regNewAppController;
	}

	/**
	 * @return the rootFolder
	 */
	public String getRootFolder() {
		return rootFolder;
	}

	/**
	 * @param rootFolder the rootFolder to set
	 */
	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}

	/**
	 * @return the profilesList
	 */
	public List<Profile> getProfilesList() {
		return profilesList;
	}

	/**
	 * @param profilesList the profilesList to set
	 */
	public void setProfilesList(List<Profile> profilesList) {
		this.profilesList = profilesList;
	}

	/**
	 * @return the preferencesPath
	 */
	public String getPreferencesPath() {
		return preferencesPath;
	}

	/**
	 * @param preferencesPath the preferencesPath to set
	 */
	public void setPreferencesPath(String preferencesPath) {
		this.preferencesPath = preferencesPath;
	}

	/**
	 * @return the lastUsedProfile
	 */
	public String getLastUsedProfile() {
		return lastUsedProfile;
	}

	/**
	 * @param lastUsedProfile the lastUsedProfile to set
	 */
	public void setLastUsedProfile(String lastUsedProfile) {
		this.lastUsedProfile = lastUsedProfile;
	}

	/**
	 * @return the startView
	 */
	public StartView getStartView() {
		return startView;
	}

	/**
	 * @return the applicationView
	 */
	public ApplicationView getApplicationView() {
		return applicationView;
	}
	
}
