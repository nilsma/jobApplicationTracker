package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import xml.PreferencesXMLParser;
import xml.PreferencesXMLWriter;

public class PreferencesController {
	private ApplicationController applicationController;

	public PreferencesController(ApplicationController applicationController) {
		this.applicationController = applicationController;
		applicationController.setLastUsedProfile(getLastUsedProfile());
	}

	public String getLastUsedProfile() {
		String lastUsedProfile = "";
		if(checkPreferencesFile()) {
			PreferencesXMLParser preferencesParser = PreferencesXMLParser.getInstance();
			try {
				lastUsedProfile = preferencesParser.getLastUsedProfile(applicationController.getPreferencesPath());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong while getting last used profile (IO)");
				e.printStackTrace();
			} catch (XMLStreamException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong while getting last used profile (XML)");
				e.printStackTrace();
			}
		} 
		return lastUsedProfile;
	}

	public void writeLastUsedProfile() {
		PreferencesXMLWriter preferencesWriter = PreferencesXMLWriter.getInstance();
		try {
			preferencesWriter.writeLastUsedProfile((String) applicationController.getLastUsedProfile(), 
					applicationController.getPreferencesPath());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to write last used profile(IO)");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to write last used profile(Parser)");
			e.printStackTrace();
		} catch (TransformerException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while trying to write last used profile(Transformer)");
			e.printStackTrace();
		}
	} 

	public boolean checkPreferencesFile() {
		File file = new File(applicationController.getPreferencesPath());
		return file.exists();
	}

}
