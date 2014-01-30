package xml;

import java.io.IOException;

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

public class PreferencesXMLWriter {
	private static PreferencesXMLWriter preferencesWriter;
	
	public PreferencesXMLWriter() {
		
	}
	
	public static PreferencesXMLWriter getInstance() {
		if(preferencesWriter == null) {
			preferencesWriter = new PreferencesXMLWriter();
		}
		return preferencesWriter;
	}
	
	public void writeLastUsedProfile(String lastUsedProfile, String path) throws IOException, 
			ParserConfigurationException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		Element rootElement = document.createElement("preferences");
		document.appendChild(rootElement);
		
		Element preference = document.createElement("preference");
		preference.appendChild(document.createTextNode(lastUsedProfile));
		rootElement.appendChild(preference);
		
		DOMSource source = new DOMSource(document);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		StreamResult result = new StreamResult(path);
		transformer.transform(source, result);
	}

}
