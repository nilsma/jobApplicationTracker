package controllers;

import java.awt.Color;
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
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
//import javax.swing.text.html.HTMLDocument.Iterator;

import models.JobApplication;

import utils.JobApplicationSorter;
import utils.Utils;
import views.ApplicationView;
import views.RegisterNewAppView;

public class RegisterNewAppController implements ActionListener {
	private ApplicationController applicationController;
	private RegisterNewAppView regNewAppView;
	private List<JTextComponent> formFields;
	private StartController startController;
	private List<File> files;
	private File fileToAdd;

	public RegisterNewAppController(ApplicationController applicationController,
			StartController startController,
			ApplicationView applicationView,
			List<JobApplication> applications) {
		this.applicationController = applicationController;
		this.startController = startController;
		this.regNewAppView = new RegisterNewAppView();
		this.files = new ArrayList<File>();

		formFields = new ArrayList<JTextComponent>();
		formFields.add(regNewAppView.getCompanyTF());
		formFields.add(regNewAppView.getPositionTF());
		formFields.add(regNewAppView.getAppliedTF());
		formFields.add(regNewAppView.getDuedateTF());
		formFields.add(regNewAppView.getFollowupTF());
		formFields.add(regNewAppView.getNotesArea());

		regNewAppView.getAddFileButton().addActionListener(this);
		regNewAppView.getRemoveFileButton().addActionListener(this);
		regNewAppView.getSaveButton().addActionListener(this);
		regNewAppView.getClearButton().addActionListener(this);
		regNewAppView.getCancelButton().addActionListener(this);
		regNewAppView.getCompanyTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				regNewAppView.getCancelButton().setText("Cancel");
				regNewAppView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		regNewAppView.getPositionTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) { 
				regNewAppView.getCancelButton().setText("Cancel");
				regNewAppView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		regNewAppView.getAppliedTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) { 
				regNewAppView.getCancelButton().setText("Cancel");
				regNewAppView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		regNewAppView.getDuedateTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) { 
				regNewAppView.getCancelButton().setText("Cancel");
				regNewAppView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		regNewAppView.getFollowupTF().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) { 
				regNewAppView.getCancelButton().setText("Cancel");
				regNewAppView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		regNewAppView.getNotesArea().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) { 
				regNewAppView.getCancelButton().setText("Cancel");
				regNewAppView.getVerifyLabel().setVisible(false);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		regNewAppView.getCompanyTF().requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == regNewAppView.getSaveButton()) {
			if(checkForm()) {
				checkFilename();
				JobApplication application = new JobApplication(
						regNewAppView.getIdTF().getText(),
						regNewAppView.getCompanyTF().getText(),
						regNewAppView.getPositionTF().getText(),
						regNewAppView.getAppliedTF().getText(),
						regNewAppView.getDuedateTF().getText(),
						regNewAppView.getFollowupTF().getText(),
						regNewAppView.getStatusList().getSelectedItem().toString(),
						regNewAppView.getNotesArea().getText(),
						files
						);
				if(applicationController.getApplications() == null) {
					List<JobApplication> tempApps = new ArrayList<JobApplication>();
					applicationController.setApplications(tempApps);
					startController.updateTable();
				}
				applicationController.getApplications().add(application);
				applicationController.updateListOfApplications();
				applicationController.setStartView();
				Utils.setLastUsedId(applicationController.getApplications());
				files = new ArrayList<File>();
				regNewAppView.getIdTF().setText(Utils.getNextId());
				clearForm();
				regNewAppView.getVerifyLabel().setVisible(true);
				regNewAppView.getCompanyTF().requestFocus();
				regNewAppView.getCancelButton().setText("Back");
			} else {
				if(regNewAppView.getVerifyLabel().isVisible()) {
					regNewAppView.getVerifyLabel().setVisible(false);
				}
			}
		} else if(e.getSource() == regNewAppView.getAddFileButton()) {
			checkFilename();
			File temp = new File(applicationController.getFilename());
			File applicationFilesFolder = new File(temp.getParent() + "/" + regNewAppView.getIdTF().getText());
			if(!applicationFilesFolder.exists()) {
				new File(applicationFilesFolder.getAbsolutePath()).mkdirs();
			}	
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				regNewAppView.getListModel().addElement(fileToAdd.getAbsolutePath());
			}
		} else if(e.getSource() == regNewAppView.getRemoveFileButton()) {
			if(!regNewAppView.getFilesList().isSelectionEmpty()) {
				File fileToRemove = new File((String) regNewAppView.getFilesList().getSelectedValue());
				if(fileToRemove.exists()) {
					fileToRemove.delete();
				}
				removeFileFromList(fileToRemove, files);
				regNewAppView.getListModel().removeElementAt(regNewAppView.getFilesList().getSelectedIndex());
			} else {
				if(!regNewAppView.getListModel().isEmpty()) {
					JOptionPane.showMessageDialog(null, "You must first select a file to be removed");
				} else {
					JOptionPane.showMessageDialog(null, "The file list is already empty");
				}
			}
		} else if(e.getSource() == regNewAppView.getClearButton()) {
			clearForm();
			regNewAppView.getCompanyTF().requestFocus();
			if(regNewAppView.getVerifyLabel().isVisible()) {
				regNewAppView.getVerifyLabel().setVisible(false);
			}
		} else if(e.getSource() == regNewAppView.getCompanyTF()) {
			if(regNewAppView.getCompanyTF().isFocusOwner()) {
				regNewAppView.getCancelButton().setText("Cancel");
			}
		} else if(e.getSource() == regNewAppView.getCancelButton()) {
			applicationController.setApplications(JobApplicationSorter.sortApplications(
					applicationController.getApplications(),
					applicationController.getStartView().getSortCategories().getSelectedItem().toString())
					);
			applicationController.setStartView();
			regNewAppView.getMainFrame().dispose();
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

	public void checkFilename() {
		if(applicationController.getFilename() == null) {
			JOptionPane.showMessageDialog(null, "checkFilename: " + applicationController.getFilename());
			String tempFolderPath = "/tmp/JAT/";
			new File(tempFolderPath).mkdirs();
			File tempFile = new File(tempFolderPath + "/applications.xml");
			applicationController.setFilename(tempFile.getAbsolutePath());
//			applicationController.setFolder(tempFile.getParent());
		} else {
			File tempFile = new File(applicationController.getFilename());
			if(!tempFile.exists()) {
				new File(tempFile.getParent()).mkdirs();	
//				applicationController.setFolder(tempFile.getParent());
			}
		}
	}

	/**
	 * a method to convert the objects in the RegNewAppViews JList of files associated
	 * to the application to a List<String>
	 * @return files as the list of files
	 */
	public List<File> getListOfFiles() {
		files = new ArrayList<File>();
		ListModel listModel = regNewAppView.getFilesList().getModel();
		for(int i = 0; i < listModel.getSize(); i++) {
			files.add((File) listModel.getElementAt(i));
		}
		return files;
	}

	/**
	 * a method to check that the form is valid before saving the new application to the
	 * list of applications. The for-loop is -1 to leave out the notes area of the form
	 * so not to force notes on an application. Same is true for list of files in application, and
	 * the status which can be any of the given values.
	 * The method also colors any fields that do not pass with a red background-color.
	 * @return boolean true if the form is valid, false otherwise
	 */
	public boolean checkForm() {
		boolean valid = true;
		for(int i = 0; i < formFields.size() - 1; i++) {
			if(formFields.get(i).getText().trim().isEmpty()) {
				valid = false;
				regNewAppView.setBackgroundColor(formFields.get(i), Color.RED);
			}
		}
		return valid;
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
			regNewAppView.setBackgroundColor(component, Color.WHITE);
		}
	}

	/**
	 * sets the selected status in the status combobox back to index(0) ("application sent")
	 */
	public void clearStatus() {
		regNewAppView.getStatusList().setSelectedIndex(0);
	}

	/**
	 * clears the selection of files associated with the application
	 */
	public void clearFiles() {
		regNewAppView.getListModel().clear();
	}

	/**
	 * sets the forms fields text value to an empty string
	 */
	public void clearFields() {
		for(JTextComponent component: formFields) {
			component.setText("");
		}
	}

}
