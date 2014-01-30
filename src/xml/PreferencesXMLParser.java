package xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class PreferencesXMLParser {
	private static PreferencesXMLParser preferencesParser;
	static final String PREFERENCES = "preferences";
	static final String PREFERENCE = "preference";
	
	private PreferencesXMLParser() {
		
	}
	
	public static PreferencesXMLParser getInstance() {
		if(preferencesParser == null) {
			preferencesParser = new PreferencesXMLParser();
		}
		return preferencesParser;
	}
	
	public String getLastUsedProfile(String path) throws FileNotFoundException, XMLStreamException {
		String lastUsedProfile = "";
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream inputStream = new FileInputStream(path);
		XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
		while(eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			if(event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if(startElement.getName().getLocalPart().equals(PREFERENCE)) {
					event = eventReader.nextEvent();
					lastUsedProfile = event.asCharacters().getData();
				}
			}
		}
		return lastUsedProfile;
	}
	
}
