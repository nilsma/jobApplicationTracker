package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import models.Profile;
import views.CreateProfileView;

/**
 * a controller class to control the CreateProfileView
 * @author nilsma
 */
public class CreateProfileController implements ActionListener {
	private CreateProfileView createProfileView;
	private ProfilesController profilesController;
	private ApplicationController applicationController;
	
	/**
	 * CreateProfileController constructor
	 * @param profilesController the profilesController
	 * @param applicationController the applicationController
	 */
	public CreateProfileController(ProfilesController profilesController, ApplicationController applicationController) {
		createProfileView = new CreateProfileView();
		this.applicationController = applicationController;
		this.profilesController = profilesController;
		
		createProfileView.getConfirmButton().addActionListener(this);
		createProfileView.getCancelButton().addActionListener(this);
		createProfileView.getNameField().requestFocus();
		
		createProfileView.getNameField().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				createProfileView.getNameField().setBackground(Color.WHITE);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				createProfileView.getNameField().setBackground(Color.WHITE);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				//do nothing
			}

		});
	}
	
	/**
	 * listens to actions performed on the createProfileController
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createProfileView.getConfirmButton()) {
			if(!createProfileView.getNameField().getText().trim().isEmpty()) {
				Profile profile = new Profile(createProfileView.getNameField().getText(), createProfilePath());
				if(validateProfile(profile)) {
					profilesController.addProfile(profile);
					profilesController.declareProfile(profile);
					createProfileView.getFrame().dispose();
					profilesController.getProfilesView().getFrame().dispose();
				} else {
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "Name already exists! \n Please choose a different name", 
							"Create Profile ...", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
					createProfileView.getNameField().setText("");
					createProfileView.getNameField().requestFocus();
				}
			} else {
				createProfileView.getNameField().setBackground(Color.RED);
			}
		} else if(e.getSource() == createProfileView.getCancelButton()) {
			createProfileView.getFrame().dispose();
		}
	}
	
	public String createProfilePath() {
		String path = applicationController.getRootFolder();
		path += "/profiles";
		path += "/";
		path += createProfileView.getNameField().getText();
		path += "/";
		path += "applications.xml";
		return path;
	}
	
	public boolean validateProfile(Profile profile) {
		boolean validation = true;
		for(Profile p : applicationController.getProfilesList()) {
			if(p.getName().trim().toLowerCase().equals(profile.getName())) {
				validation = false;
			}
		}
		return validation;
	}
	
	public boolean checkForm() {
		boolean valid = true;
		List<JTextComponent> fields = new ArrayList<JTextComponent>();
		fields.add(createProfileView.getNameField());
		fields.add(createProfileView.getPathField());
		for(JTextComponent component : fields) {
			if(component.getText().trim().isEmpty()) {
				component.setBackground(Color.RED);
				valid = false;
			}
		}
		return valid;
	}
	
}
