package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import models.JobApplication;
import utils.JobApplicationSorter;
import utils.Utils;
import views.StartView;

/**
 * a controller class to control the startview, the functionality
 * within the main-application view
 * @author nilsma
 */
public class StartController implements ActionListener {
	@SuppressWarnings("unused")
	private RegisterNewAppController regNewAppController;
	private ApplicationController applicationController;
	private StartView startView;
	
	/**
	 * Constructor
	 * @param applicationController an instance of the ApplicationController class
	 */
	public StartController(ApplicationController applicationController) {
		
		this.applicationController = applicationController;
		this.startView = new StartView(applicationController.getApplications());
		
		startView.getRegAppBtn().addActionListener(this);
		startView.getQuitBtn().addActionListener(this);
		startView.getRemoveApp().addActionListener(this);
		startView.getAppDet().addActionListener(this);
		startView.getSortCategories().addActionListener(this);
		startView.getActiveBox().addActionListener(this);
		startView.getInactiveBox().addActionListener(this);
	}
	
	/**
	 * listens to actions performed on the startview 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startView.getRegAppBtn()) {
			initiateRegNewAppController();
		} else if(e.getSource() == startView.getRemoveApp()) {
			if(applicationController.getApplications() != null) {
				if((applicationController.getApplications().size() > 0)) {
					if (startView.getTable().getSelectedRow() < applicationController.getApplications().size()) {
						applicationController.removeRow(startView.getTable().getSelectedRow());
						applicationController.setStartView();
					} else {
						JOptionPane.showMessageDialog(null, "You must choose a row first");
					}
				} else {
					JOptionPane.showMessageDialog(null, "The list of applications is already empty");
				}
			}
		} else if(e.getSource() == startView.getAppDet()) {
			if(applicationController.getApplications() != null) {
				if(applicationController.getApplications().size() > 0) {
					if(startView.getTable().getSelectedRow() >= 0 && (
							startView.getTable().getSelectedRow() < applicationController.getApplications().size())) {
						applicationController.initiateAppDetailsController(startView.getTable().getSelectedRow());
					} else {
						JOptionPane.showMessageDialog(null, "You must select an application in the list first.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "The list of applications is empty");
				}
			} 
		} else if(e.getSource() == startView.getActiveBox()) {
			if(applicationController.getApplications() != null) {
				if(startView.getActiveBox().isSelected() == false) {
					removeActiveApps();
					updateAppCtrlApplications();
					applicationController.setStartView();
				} else if(startView.getActiveBox().isSelected() == true) {
					addActiveApps();
					updateAppCtrlApplications();
					applicationController.setStartView();
				}
			} else {
				startView.getActiveBox().setSelected(true);
			}
		} else if(e.getSource() == startView.getInactiveBox()) {
			if(applicationController.getApplications() != null) {
				if(startView.getInactiveBox().isSelected() == false) {
					removeInactiveApps();
					updateAppCtrlApplications();
					applicationController.setStartView();
				} else {
					addInactiveApps();
					updateAppCtrlApplications();
					applicationController.setStartView();
				}
			} else {
				startView.getInactiveBox().setSelected(true);
			}
		} else if(e.getSource() == startView.getSortCategories()) {
			if(applicationController.getApplications() != null) {
				updateAppCtrlApplications();
				applicationController.setStartView();
			} else {
				startView.getSortCategories().setSelectedIndex(0);
			}
		} else if(e.getSource() == startView.getQuitBtn()) {
			applicationController.promptForQuit();
		}
	}
	
	/**
	 * a method to create an instance of RegisterNewAppController
	 */
	public void initiateRegNewAppController() {
		regNewAppController = new RegisterNewAppController(
				applicationController, this, applicationController.getApplicationView(),
				applicationController.getApplications()
				);
	}
	
	/**
	 * a method to sort and update the job-applications list
	 * in the main-application
	 */
	public void updateAppCtrlApplications() {
		applicationController.setApplications(
				JobApplicationSorter.sortApplications(
						applicationController.getApplications(), 
						startView.getSortCategories().getSelectedItem().toString()
						)
				);
	}
	
	/**
	 * a method to update the table in the startview
	 */
	public void updateTable() {
		startView.setApplications(applicationController.getApplications());
		startView.getAppTableModel().updateAppTable(applicationController.getApplications());
	}
	
	/**
	 * a method to add active job-applications back to the startview,
	 * does so by fetching active job-applications through iterating
	 * over the vanilla job-applications list in the Utils-class
	 */
	public void addActiveApps() {
		Utils.setOriginalList(applicationController.getFilename());
		List<JobApplication> list = new ArrayList<JobApplication>();
		list = Utils.getOriginalList();
		for(JobApplication app : list) {
			if(app.isActive()) {
				applicationController.getApplications().add(app);
			}
		}
	}
	
	/**
	 * a method to add inactive job-applications back to the startview,
	 * does so by fetching active job-applications through iterating
	 * over the vanilla job-applications list in the Utils-class
	 */
	public void addInactiveApps() {
		Utils.setOriginalList(applicationController.getFilename());
		List<JobApplication> list = new ArrayList<JobApplication>();
		list = Utils.getOriginalList();
		for(JobApplication app : list) {
			if(!app.isActive()) {
				applicationController.getApplications().add(app);
			}
		}
	}
	
	/**
	 * a method to remove inactive job-applications from the startview
	 */
	public void removeInactiveApps() {
		Iterator<JobApplication> iterator = applicationController.getApplications().iterator();
		while(iterator.hasNext()) {
			JobApplication app = iterator.next();
			if(!app.isActive()) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * a method to remove active job-applications the startview
	 */
	public void removeActiveApps() {
		Iterator<JobApplication> iterator = applicationController.getApplications().iterator();
		while(iterator.hasNext()) {
			JobApplication app = iterator.next();
			if(app.isActive()) {
				iterator.remove();
			}
		}
	}

	/**
	 * @return the startView
	 */
	public StartView getStartView() {
		return startView;
	}
}
