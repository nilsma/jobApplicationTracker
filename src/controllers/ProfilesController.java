package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import models.Profile;

import views.ProfilesView;
import xml.StaxProfileParser;
import xml.StaxProfileWriter;
import xml.StaxWriterRev;

/**
 * a class to control the profiles view
 * @author nilsma
 *
 */
public class ProfilesController implements ActionListener {
	private ApplicationController applicationController;
	private ProfilesView profilesView;
	private String[] profilesArray;
	
	/**
	 * ProfilesController constructor
	 * @param applicationController the applications applicationController
	 */
	public ProfilesController(ApplicationController applicationController) {
		this.applicationController = applicationController;
		applicationController.setProfilesList(populateProfilesList());
		this.profilesArray = generateProfilesArray(generateProfilesPath());
		checkLastUsedProfile();
	}

	/**
	 * a method that listens to action performed on the profile view instance
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == profilesView.getConfirmButton()) {
			if(profilesView.getComboBox().getSelectedItem().toString().trim().toLowerCase().equals("create new profile ...")) {
				createNewProfile();
			} else {
				String chosen = (String) profilesView.getComboBox().getSelectedItem();
				Iterator<Profile> iterator = applicationController.getProfilesList().iterator();
				Profile profile = null;
				profile = iterator.next();
				while(profile != null) {
					if(profile.getName().equals(chosen)) {
						declareProfile(profile);
						break;
					}
					profile = iterator.next();
				}
				profilesView.getFrame().dispose();
			}
		} else if(e.getSource() == profilesView.getCancelButton()) {
			profilesView.getFrame().dispose();
		}
	}
	
	/**
	 * a method that checks for the last used profile 
	 */
	public void checkLastUsedProfile() {
		if(applicationController.getLastUsedProfile().equals("")) {
			this.profilesView = new ProfilesView(profilesArray);
			profilesView.getConfirmButton().addActionListener(this);
			profilesView.getCancelButton().addActionListener(this);
		} else {
			String chosen = applicationController.getLastUsedProfile();
			Iterator<Profile> iterator = applicationController.getProfilesList().iterator();
			Profile profile = null;
			profile = iterator.next();
			while(profile != null) {
				if(profile.getName().equals(chosen)) {
					declareProfile(profile);
					break;
				}
				profile = iterator.next();
			}
		}
	}

	public void declareProfile(Profile profile) {
		applicationController.updateLastUsedProfile(profile.getName());
		applicationController.setFilename(profile.getPath());
		applicationController.updateApplication();
	}
	
	public void addProfile(Profile profile) {
		applicationController.getProfilesList().add(profile);
		updateProfilesList();
		generateProfileFolder(profile);
		StaxProfileWriter staxProfileWriter = StaxProfileWriter.getInstance();
		try {
			staxProfileWriter.updateProfilesXML(applicationController.getProfilesList(), generateProfilesPath());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to updateProfilesXML (IO)");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to updateProfilesXML (PCE)");
			e.printStackTrace();
		} catch (TransformerException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to updateProfilesXML (TE)");
			e.printStackTrace();
		}
	}
	
	public void generateProfileFolder(Profile profile) {
		String path = applicationController.getRootFolder();
		path += "/profiles";
		path += "/";
		path += profile.getName();
		new File(path).mkdirs();
		path += "/";
		path += "applications.xml";
		StaxWriterRev staxWriter = StaxWriterRev.getInstance();
		try {
			staxWriter.writeEmptyApplicationFile(path);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to add profile (IO)");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to add profile (ParserConfigurationException)");
			e.printStackTrace();
		} catch (TransformerException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to add profile (TransformerException)");
			e.printStackTrace();
		}
	}
	
	public void updateProfilesList() {
		profilesView.getComboBox().setModel(new DefaultComboBoxModel(generateProfilesArray(generateProfilesPath())));
	}
	
	public List<Profile> populateProfilesList() {
		List<Profile> list = new ArrayList<Profile>();
		String path = generateProfilesPath();
		list = parseProfiles(path);
		return list; 
	}
	
	@SuppressWarnings("unused")
	public void createNewProfile() {
		CreateProfileController createProfileController = new CreateProfileController(this, applicationController);
	}
	
	public String generateProfilesPath() {
		String path = applicationController.getRootFolder() + "/profiles.xml";
		checkForProfilesFile(path);
		return path;
	}
	
	public void checkForProfilesFile(String path) {
		File profiles = new File(path);
		if(!profiles.exists()) {
			StaxProfileWriter staxProfileWriter = StaxProfileWriter.getInstance();
			try {
				staxProfileWriter.writeEmptyProfiles(profiles);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong while trying to write empty profiles (IO)");
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong while trying to write empty profiles (ParserConfigurationException)");
				e.printStackTrace();
			} catch (TransformerException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong while trying to write empty profiles (TransformerException)");
				e.printStackTrace();
			}
		}
	}
	
	public String[] generateProfilesArray(String path) {
		List<String> profiles = new ArrayList<String>();
		profiles.add("Create new profile ...");
		for(Profile profile : applicationController.getProfilesList()) {
			profiles.add(profile.getName());
		}
		String[] profilesArray = profiles.toArray(new String[profiles.size()]);
		return profilesArray;
	}
	
	public List<Profile> parseProfiles(String path) {
		StaxProfileParser staxProfileParser = StaxProfileParser.getInstance();
		List<Profile> parsedProfiles = new ArrayList<Profile>();				
		try {
			parsedProfiles = staxProfileParser.parse(path);
			return parsedProfiles;
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to get/parse profiles (IO)");
			e.printStackTrace();
		} catch(XMLStreamException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to get/parse profiles (XML)");
			e.printStackTrace();
		}
		return parsedProfiles;
	}

	/**
	 * @return the profilesView
	 */
	public ProfilesView getProfilesView() {
		return profilesView;
	}	
	
}
