package by.module6.notebook.DOM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.module6.notebook.entity.Note;
import by.module6.notebook.entity.NoteBook;
import by.module6.notebook.exception.InputException;

public class NoteBookBuilder {

	public final static String ROOT = "notebook";
	public static final String ELEMENT_NAME = "note";
	public static final String DATE = "date";
	public static final String THEME = "theme";
	public static final String EMAIL = "email";
	public static final String TEXT = "text"; 
	public static final String EXTENSION = ".xml"; 
	public static final String DEFAULT_FILENAME = "default"; 
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	
	private ArrayList<Note> list;
	private DocumentBuilder docBuilder;
	private NoteBook noteBook;
	
	public NoteBookBuilder() {
		noteBook = new NoteBook();
		this.list = new ArrayList<Note>();
		// создание DOM-анализатора
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
		docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("Ошибка конфигурации парсера: " + e);
		}
	}
	
	public NoteBook getNoteBook() {
		return getNoteBook(DEFAULT_FILENAME);
	}
	
	public NoteBook getNoteBook(String fileName) {
		buildtNoteBook(fileName);
		return noteBook;
	}
	
	private void buildtNoteBook (String fileName) {
		Document doc = null;
		try {
			// parsing XML-документа и создание древовидной структуры
			doc = docBuilder.parse(fileName + EXTENSION);
			Element root = doc.getDocumentElement();
			// получение списка дочерних элементов <note>
			NodeList notesList = root.getElementsByTagName(ELEMENT_NAME);
			for (int i = 0; i < notesList.getLength(); i++) {
				Element noteElement = (Element) notesList.item(i);
				buildNote(noteElement);
			}
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);
		} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);
		}
	}

	private void buildNote(Element noteElement) {
		try {
			this.noteBook.addNote(getElementTextContent(noteElement, THEME),
								getElementTextContent(noteElement, EMAIL),
								getElementTextContent(noteElement, DATE),
								getElementTextContent(noteElement, TEXT));
		} catch (InputException e) {
			e.printStackTrace();
		}
	}	
	
	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
