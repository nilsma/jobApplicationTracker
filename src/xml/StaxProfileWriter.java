package xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import models.Profile;

public class StaxProfileWriter {

	public static StaxProfileWriter staxProfileWriter;

	/**
	 * private and empty constructor
	 * in compliance with singleton pattern
	 */
	private StaxProfileWriter() {

	}

	public static StaxProfileWriter getInstance() {
		if(staxProfileWriter == null) {
			staxProfileWriter = new StaxProfileWriter();
		}
		return staxProfileWriter;
	}

	public void updateProfilesXML(List<Profile> list, String filename) throws IOException, 
	ParserConfigurationException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		Element rootElement = document.createElement("profiles");
		document.appendChild(rootElement);
		
		for(Profile p : list) {
			Element profile = document.createElement("profile");
			rootElement.appendChild(profile);
			
			Element name = document.createElement("name");
			name.appendChild(document.createTextNode(p.getName()));
			profile.appendChild(name);
			
			Element location = document.createElement("location");
			location.appendChild(document.createTextNode(p.getPath()));
			profile.appendChild(location);
		}

		DOMSource source = new DOMSource(document);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		StreamResult result = new StreamResult(filename);
		transformer.transform(source, result);
	}

	public void writeEmptyProfiles(File profiles) throws IOException, 
	ParserConfigurationException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		Element rootElement = document.createElement("profiles");
		document.appendChild(rootElement);

		DOMSource source = new DOMSource(document);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		StreamResult result = new StreamResult(profiles.getAbsolutePath());
		transformer.transform(source, result);

	}

}
