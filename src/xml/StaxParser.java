package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import models.JobApplication;

/**
 * a class to parse a job application xml-file
 * employing the singleton pattern
 * @author nilsma
 *
 */
public class StaxParser {
	static final String APPLICATION = "application";
	static final String ID = "id";
	static final String COMPANY = "company";
	static final String POSITION = "position";
	static final String APPLIED = "applied";
	static final String DUEDATE = "duedate";
	static final String FOLLOWUP = "followup";
	static final String STATUS = "status";
	static final String NOTES = "notes";
	static final String FILES = "files";
	static final String FILE = "file";
	private List<File> files;
	private static StaxParser staxParser;
	
	/**
	 * private, empty constructor in accordance with the singleton pattern
	 */
	private StaxParser() {
		
	}
	
	/**
	 * a method to get an instance of the StaxParser
	 * @return a new instance of the StaxParser or, if one already exists, returns
	 * the existing instance of the StaxParser
	 */
	public static StaxParser getInstance() {
		if(staxParser == null) {
			staxParser = new StaxParser();
		}
		return staxParser;
	}

	/**
	 * a method to parse a job application xml-file. Iterates over the file and creates a new
	 * JobApplication instance and puts it in an ArrayList<JobApplication>
	 * @param filename the job application xml-file to be parsed
	 * @return an ArrayList containing instances of job applications in the xml-file
	 * @throws IOException if the given filename is not found
	 * @throws XMLStreamException if the given XML-file does not match the pattern of the parser
	 */
	public List<JobApplication> readFile(String filename) throws IOException, XMLStreamException {
		List<JobApplication> applications = new ArrayList<JobApplication>();
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(filename);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			JobApplication application = null;
			
			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if(event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					if(startElement.getName().getLocalPart() == (APPLICATION)) {
						application = new JobApplication();
						files = new ArrayList<File>();
						@SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
						while(attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if(attribute.getName().getLocalPart().equals(ID)) {
								application.setId(attribute.getValue());	
							}
						}
						
					}
				}
				
				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(COMPANY)) {
						event = eventReader.nextEvent();
						application.setCompany(event.asCharacters().getData());
						continue;
					}
				}
				
				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(POSITION)) {
						event = eventReader.nextEvent();
						application.setPosition(event.asCharacters().getData());
						continue;
					}
				}
				
				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(APPLIED)) {
						event = eventReader.nextEvent();
						application.setApplied(event.asCharacters().getData());
						continue;
					}
				}
				
				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(DUEDATE)) {
						event = eventReader.nextEvent();
						application.setDuedate(event.asCharacters().getData());
						continue;
					}
				}
				
				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(FOLLOWUP)) {
						event = eventReader.nextEvent();
						application.setFollowup(event.asCharacters().getData());
						continue;
					}
				}
				
				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(STATUS)) {
						event = eventReader.nextEvent();
						application.setStatus(event.asCharacters().getData());
						continue;
					}
				}
				
				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(NOTES)) {
						event = eventReader.nextEvent();
						if(!event.isEndElement()) {
							application.setNotes(event.asCharacters().getData());
						}
						continue;
					}
				}

				if(event.isStartElement()) {
					if(event.asStartElement().getName().getLocalPart().equals(FILES)) {
						event = eventReader.nextTag();
						while(event.isStartElement()) {
							if(event.asStartElement().getName().getLocalPart().equals(FILE)) {
								event = eventReader.nextEvent();
								files.add(new File(event.asCharacters().getData()));
								event = eventReader.nextEvent();
								event = eventReader.nextTag();
							}
							
							continue;
						}
						application.setFiles(files);
						event = eventReader.nextEvent();
						continue;
					} 
				}
				
				if(event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if(endElement.getName().getLocalPart().equals(APPLICATION)) {
						applications.add(application);
					}
				}
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("Something went wrong while finding file: " + filename + "!");
			e.printStackTrace();
		} catch(XMLStreamException e) {
			System.out.println("Something went wrong while trying to parse the XML!");
		}
		
		return applications;
	}
	
}














