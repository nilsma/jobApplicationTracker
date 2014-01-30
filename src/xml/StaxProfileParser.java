package xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import models.Profile;

public class StaxProfileParser {
	static final String PROFILE = "profile";
	static final String NAME = "name";
	static final String LOCATION = "location";
	private static StaxProfileParser staxProfileParser;

	private StaxProfileParser() {

	}

	public static StaxProfileParser getInstance() {
		if(staxProfileParser == null) {
			staxProfileParser = new StaxProfileParser();
		}
		return staxProfileParser;
	}

	public List<Profile> parse(String path) throws IOException, XMLStreamException {
		List<Profile> profiles = new ArrayList<Profile>();
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(path);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			Profile profile = null;

			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if(event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					if(startElement.getName().getLocalPart() == (PROFILE)) {
						profile = new Profile();
					}
				}

				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(NAME)) {
						event = eventReader.nextEvent();
						profile.setName(event.asCharacters().getData());
						continue;
					}
				}

				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(LOCATION)) {
						event = eventReader.nextEvent();
						profile.setPath(event.asCharacters().getData());
						continue;
					}
				}
				
				if(event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if(endElement.getName().getLocalPart().equals(PROFILE)) {
						if(profile != null) {
							profiles.add(profile);
						}
					}
				}
			}
			return profiles;
		} catch(IOException e) {
			e.printStackTrace();
		} catch(XMLStreamException e) {
			e.printStackTrace();
		}
		return null;
	}



}
