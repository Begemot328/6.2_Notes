package by.module6.notebook.DOM;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import by.module6.notebook.entity.Note;
import by.module6.notebook.entity.NoteBook;

public class XMLDOMWriter {
	public final static String ROOT = "notebook";
	public static final String ELEMENT_NAME = "note";
	public static final String DATE = "date";
	public static final String THEME = "theme";
	public static final String EMAIL = "email";
	public static final String TEXT = "text"; 
	public static final String EXTENSION = ".xml"; 
	public static final String DEFAULT_FILENAME = "default"; 
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	
	private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder documentBuilder;
	private NoteBook notebook;
	
	public XMLDOMWriter(NoteBook notebook) {
		this.notebook = notebook;
		documentBuilder = null;
		try {
			documentBuilder =	documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
				e.printStackTrace();
		}
	}	
	
	public XMLDOMWriter() {
		this(new NoteBook());
	}
	
	public void createXML() {
		createXML(DEFAULT_FILENAME);
	}
	
	public void createXML(String filename) {
		if(filename.contains(EXTENSION)) {
			createXML(new File(filename));
		}
		createXML(new File(filename + EXTENSION));
	}
	
	
	public NoteBook parseXML(File file) {
		
		return new NoteBook();
	}
	
	public void createXML(File file) {
		Document document = documentBuilder.newDocument();
		Element rootElement = document.createElement(ROOT);
		document.appendChild(rootElement);
		
		for (Note note: notebook.getNotes()) {
			Element element = document.createElement(ELEMENT_NAME);
			
			Element elementTheme = document.createElement(THEME);
			elementTheme.appendChild(document.createTextNode(note.getTheme()));
			element.appendChild(elementTheme);
			
			Element elementDate = document.createElement(DATE);
			elementDate.appendChild(document.createTextNode(new SimpleDateFormat(DATE_FORMAT).format(note.getDate())));
			element.appendChild(elementDate);
			
			Element elementMail = document.createElement(EMAIL);
			elementMail.appendChild(document.createTextNode(note.getMail()));
			element.appendChild(elementMail);
			
			Element elementText = document.createElement(TEXT);
			elementText.appendChild(document.createTextNode(note.getText()));
			element.appendChild(elementText);
			
			rootElement.appendChild(element);
		}
				
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new FileWriter(file));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

