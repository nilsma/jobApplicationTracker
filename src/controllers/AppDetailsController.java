package controllers;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import models.JobApplication;
import utils.Utils;
import views.AppDetailsView;

/**
 * a controller-class to controll the AppDetailsController which presents
 * the application details
 * @author nilsma
 *
 */
public class AppDetailsController implements ActionListener {
	private ApplicationController applicationController;
	private AppDetailsView appDetailsView;
	private int applicationIndex;
	private List<JTextComponent> formFields;
	private List<File> files;
	private File fileToAdd; 
	
	public AppDetailsController(ApplicationController applicationController, int index) {
		this.applicationController = applicationController;
		this.applicationIndex = index;
		this.appDetailsView = new AppDetailsView();
		
		formFields = new ArrayList<JTextComponent>();
		formFields.add(appDetailsView.getCompanyTF());
		formFields.add(appDetailsView.getPositionTF());
		formFields.add(appDetailsView.getAppliedTF());
		formFields.add(appDetailsView.getDuedateTF());
		formFields.add(appDetailsView.getFollowupTF());
		formFields.add(appDetailsView.getNotesArea());
		
		appDetailsView.getAddFileButton().addActionListener(this);
		appDetailsView.getRemoveFileButton().addActionListener(this);
		appDetailsView.getOpenFileButton().addActionListener(this);
		appDetailsView.getSaveButton().addActionListener(this);
		appDetailsView.getClearButton().addActionListener(this);
		appDetailsView.getCancelButton().addActionListener(this);
		appDetailsView.getCompanyTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				appDetailsView.getCancelButton().setText("Cancel");
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}
		});
		
		appDetailsView.getPositionTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				appDetailsView.getCancelButton().setText("Cancel");
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}
		});
		
		appDetailsView.getAppliedTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				appDetailsView.getCancelButton().setText("Cancel");
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}
		});
		
		appDetailsView.getDuedateTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				appDetailsView.getCancelButton().setText("Cancel");
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}
		});
		
		appDetailsView.getFollowupTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				appDetailsView.getCancelButton().setText("Cancel");
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}
		});
		
		appDetailsView.getNotesArea().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				appDetailsView.getCancelButton().setText("Cancel");
				appDetailsView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				appDetailsView.getVerifyLabel().setVisible(false);
			}
		});
		
		populateForm(applicationIndex);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == appDetailsView.getSaveButton()) {
			JobApplication application = null;
			application = applicationController.getApplications().get(applicationIndex);
			application.setCompany(appDetailsView.getCompanyTF().getText());
			application.setPosition(appDetailsView.getPositionTF().getText());
			application.setApplied(appDetailsView.getAppliedTF().getText());
			application.setDuedate(appDetailsView.getDuedateTF().getText());
			application.setFollowup(appDetailsView.getFollowupTF().getText());
			application.setStatus(appDetailsView.getStatusList().getSelectedItem().toString());
			application.setNotes(appDetailsView.getNotesArea().getText());
			application.setFiles(files);
			applicationController.getApplications().remove(applicationIndex);
			applicationController.getApplications().add(applicationIndex, application);
			applicationController.updateListOfApplications();
			applicationController.setStartView();
			appDetailsView.getVerifyLabel().setVisible(true);
			appDetailsView.getCompanyTF().requestFocus();
		} else if(e.getSource() == appDetailsView.getClearButton()) {
			if(!applicationController.getApplications().isEmpty()) {
				int returnVal = JOptionPane.showConfirmDialog(appDetailsView, 
					"This will delete the current application!\nDo you wish to continue?", 
					"Warning!", JOptionPane.YES_NO_OPTION);
				if(returnVal == JOptionPane.OK_OPTION) {
					applicationController.removeRow(applicationIndex);
					applicationController.setStartView();
					appDetailsView.getCompanyTF().requestFocus();
					setApplicationIndex(applicationIndex -= 1);
					populateForm(applicationIndex);
				}
			} else {
				JOptionPane.showMessageDialog(appDetailsView, "There are no more applications to delete!");
				clearForm();
			}
		} else if(e.getSource() == appDetailsView.getCancelButton()) {
//			applicationController.sortApplications();
//			applications = JobApplicationSorter.sortApplications(
//					applications, startView.getSortCategories().getSelectedItem().toString()
//					);
			applicationController.setStartView();
			appDetailsView.getMainFrame().dispose();
		} else if(e.getSource() == appDetailsView.getOpenFileButton()) {
			if(!appDetailsView.getFilesList().isSelectionEmpty()) {
				File file = new File(appDetailsView.getFilesList().getSelectedValue().toString());
				if(file.exists()) {
					try {
						Desktop.getDesktop().open(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "file (" + file.getName() + ") does not exists!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "nada nada");
			}
		} else if(e.getSource() == appDetailsView.getRemoveFileButton()) {
			appDetailsView.getVerifyLabel().setVisible(false);
			if(!appDetailsView.getFilesList().isSelectionEmpty()) {
				File fileToRemove = new File(appDetailsView.getFilesList().getSelectedValue().toString());
				if(fileToRemove.exists()) {
					fileToRemove.delete();
				}
				removeFileFromList(fileToRemove, files);
				appDetailsView.getListModel().removeElementAt(appDetailsView.getFilesList().getSelectedIndex());
			} else {
				if(!appDetailsView.getListModel().isEmpty()) {
					JOptionPane.showMessageDialog(null, "You must first select a file to be removed");
				} else {
					JOptionPane.showMessageDialog(null, "The file list is already empty");
				}
			}
		} else if(e.getSource() == appDetailsView.getAddFileButton()) {	
			appDetailsView.getVerifyLabel().setVisible(false);
//			File applicationFilesFolder = new File(
//					applicationController.getFolder() + "/" + appDetailsView.getIdTF().getText()
//					);
			File applicationFilesFolder = new File(
					applicationController.getRootFolder() + "/profiles/" + applicationController.getLastUsedProfile() +
					"/" + appDetailsView.getIdTF().getText()
					);
			JFileChooser filechooser = new JFileChooser();
			File dir = new File("/home/nilsma/");
			filechooser.setCurrentDirectory(dir);
			int returnVal = filechooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					fileToAdd = new File(applicationFilesFolder + "/" + filechooser.getSelectedFile().getName());
					FileUtils.copyFile(filechooser.getSelectedFile(), fileToAdd);
					files.add(fileToAdd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				appDetailsView.getListModel().addElement(fileToAdd.getAbsolutePath());		
			}
		} else {
			JOptionPane.showMessageDialog(null, "message for you!");
		}
	}
	
	public void removeFileFromList(File file, List<File> list) {
		Iterator<File> iterator = list.iterator();
		while(iterator.hasNext()) {
			File nextFile = iterator.next();
			if(nextFile.getName().equals(file.getName())) {
				iterator.remove();
			}
		}
	}

	/**
	 * clears the text values in the forms fields, sets the selected status in the form
	 * to index(0) ("application sent"), clears the selection of files, and colors all
	 * fields back to white
	 */
	public void clearForm() {
		clearFields();
		clearStatus();
		clearFiles();
		colorFieldsWhite();
	}
	
	/**
	 * sets the background color of all the forms fields back to white
	 */
	public void colorFieldsWhite() {
		for(JTextComponent component : formFields) {
			appDetailsView.setBackgroundColor(component, Color.WHITE);
		}
	}
	
	/**
	 * sets the selected status in the status combobox back to index(0) ("application sent")
	 */
	public void clearStatus() {
		appDetailsView.getStatusList().setSelectedIndex(0);
	}
	
	/**
	 * clears the selection of files associated with the application
	 */
	public void clearFiles() {
		appDetailsView.getListModel().clear();
	}
	
	/**
	 * sets the forms fields text value to an empty string
	 */
	public void clearFields() {
		for(JTextComponent component: formFields) {
			component.setText("");
		}
	}
	
	public void populateForm(int appIndex) {
		if(!applicationController.getApplications().isEmpty()) {
			JobApplication application = null;
			application = applicationController.getApplications().get(appIndex);
			appDetailsView.getIdTF().setText(application.getId());
			appDetailsView.getCompanyTF().setText(application.getCompany());
			appDetailsView.getPositionTF().setText(application.getPosition());
			appDetailsView.getAppliedTF().setText(Utils.dateToString(application.getApplied()));
			appDetailsView.getDuedateTF().setText(Utils.dateToString(application.getDuedate()));
			appDetailsView.getFollowupTF().setText(Utils.dateToString(application.getFollowup()));
			setApplicationViewStatus(application.getStatus());
			appDetailsView.getNotesArea().setText(application.getNotes());
			files = application.getFiles();
			populateApplicationFiles(files);
		} else {
			clearForm();
		}	
	}
	
	public void setApplicationViewStatus(String status) {
		if(status.trim().toLowerCase().equals("application sent")) {
			appDetailsView.getStatusList().setSelectedIndex(0);
		} else if(status.trim().toLowerCase().equals("duedate reached")) {
			appDetailsView.getStatusList().setSelectedIndex(1);
		} else if(status.trim().toLowerCase().equals("follow-up!")) {
			appDetailsView.getStatusList().setSelectedIndex(2);
		} else if(status.trim().toLowerCase().equals("advanced interviews")) {
			appDetailsView.getStatusList().setSelectedIndex(3);
		} else if(status.trim().toLowerCase().equals("application dead")) {
			appDetailsView.getStatusList().setSelectedIndex(4);
		}
	}
	
	/**
	 * a method to iterate through the given applications associated files and add them to the
	 * appDetails list model
	 * @param files list of strings to be added to list model
	 */
	public void populateApplicationFiles(List<File> files) {
		for(int i = 0; i < files.size(); i++) {
			appDetailsView.getListModel().addElement(files.get(i));
		}
	}
	
	/**
	 * @return the applicationIndex
	 */
	public int getApplicationIndex() {
		return applicationIndex;
	}

	/**
	 * @param applicationIndex the applicationIndex to set
	 */
	public void setApplicationIndex(int applicationIndex) {
		if(applicationIndex < 0) {
			this.applicationIndex = 0;
		} else {
			this.applicationIndex = applicationIndex;
		}
	}
	
}












