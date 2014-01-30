package xml;

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
import utils.Utils;

import models.JobApplication;

/**
 * a class to add a job application to an already existing list of applications
 * and write the new list of applications to a xml-file
 * @author nilsma
 *
 */
public class StaxWriterRev {

	private static StaxWriterRev staxWriter;

	/**
	 * private and empty constructor
	 * in compliance with singleton pattern
	 */
	private StaxWriterRev() {

	}

	/**
	 * a method to get an instance of stax writer rev
	 * @return a new instance of the StaxWriterRev or, if one already exists, returns
	 * the existing instance of the StaxWriter
	 */
	public static StaxWriterRev getInstance() {
		if(staxWriter == null) {
			staxWriter = new StaxWriterRev();
		}
		return staxWriter;
	}

	/**
	 * a method to add a job application to an already existing list of applications
	 * and write the new list of applications to the given xml-file
	 * @param apps a list of applications of which to add the new application
	 * @param filename the filename of the xml-file to store the applications
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public void addApplication(List<JobApplication> apps, String filename) throws IOException, 
	ParserConfigurationException, TransformerException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		Element rootElement = document.createElement("applications");
		document.appendChild(rootElement);

		for(JobApplication app : apps) {
			Element application = document.createElement("application");
			rootElement.appendChild(application);

			application.setAttribute("id", app.getId());

			Element company = document.createElement("company");
			company.appendChild(document.createTextNode(app.getCompany()));
			application.appendChild(company);

			Element position = document.createElement("position");
			position.appendChild(document.createTextNode(app.getPosition()));
			application.appendChild(position);

			Element applied = document.createElement("applied");
			applied.appendChild(document.createTextNode(Utils.dateToString(app.getApplied())));
			application.appendChild(applied);

			Element duedate = document.createElement("duedate");
			duedate.appendChild(document.createTextNode(Utils.dateToString(app.getDuedate())));
			application.appendChild(duedate);

			Element followup = document.createElement("followup");
			followup.appendChild(document.createTextNode(Utils.dateToString(app.getFollowup())));
			application.appendChild(followup);

			Element status = document.createElement("status");
			status.appendChild(document.createTextNode(app.getStatus()));
			application.appendChild(status);

			Element notes = document.createElement("notes");
			notes.appendChild(document.createTextNode(app.getNotes()));
			application.appendChild(notes);
			
			Element files = document.createElement("files");
			application.appendChild(files);
			
			for(int i = 0; i < app.getFiles().size(); i++) {
				Element file = document.createElement("file");
				file.appendChild(document.createTextNode(app.getFiles().get(i).getAbsolutePath()));
				files.appendChild(file);
			}
		}

		DOMSource source = new DOMSource(document);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		StreamResult result = new StreamResult(filename);
		transformer.transform(source, result);

	}
	
	public void writeEmptyApplicationFile(String path) throws IOException, 
	ParserConfigurationException, TransformerException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		Element rootElement = document.createElement("applications");
		document.appendChild(rootElement);
		
		DOMSource source = new DOMSource(document);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		StreamResult result = new StreamResult(path);
		transformer.transform(source, result);
	}
}


