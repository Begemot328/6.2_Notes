package by.module6.notebook.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.module6.notebook.exception.InputException;

public class NoteBook {
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String MAIL_REGEXP = "([.[^@\n]]+)@([.[^@\n]]+)\\.([a-z]+)";
	private static final String EMPTY_DATA = "Enter non-empty data!";
	private static final String INCORRECT_MAIL = "Enter valid e-mail adress!";
	private static final String INCORRECT_DATE = "Enter valid date";
	
	private ArrayList<Note> list = new ArrayList<Note>();
	
	public NoteBook() {
	}
	
	public ArrayList<Note> getNotes() {
		return (ArrayList<Note>) list.clone();
	}
	
	public Note getNote(int i) {
		return list.get(i);
	}
	
	public void addNote(String theme, String mail, Date date, String text) throws InputException {
		addNote(createNote(theme, mail, date, text));
	}
	
	public void addNote(String theme, String mail, String date, String text) throws InputException {
		addNote(createNote(theme, mail, date, text));
	}
	
	private void addNote(Note note) {
		if (note != null) {
			list.add(note);
		}
	}
	
	private Note createNote(String theme, String mail, Date date, String text) throws InputException {
		if (!isOk(mail, MAIL_REGEXP)) {
			throw new InputException(INCORRECT_MAIL);
			
		}
		if (theme.isEmpty() || text.isEmpty()) {
			throw new InputException(EMPTY_DATA);
		}
		return new Note(theme, mail, date, text);
	}
	
	
	
	private Note createNote(String theme, String mail, String dateString, String text) throws InputException {
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
		} catch (ParseException e) {
			throw new InputException(INCORRECT_DATE);
		}
		return createNote(theme, mail, date, text);
	}
	
	private boolean isOk(String text, String regexp) {
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(text);
		if (text == null || text.isEmpty()) {
			return false;
		}
		return matcher.find();
	}
	
	private boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public String toString() {
		String result = "NoteBook, list=\n";
		for (Note note: list) {
			result += note + "\n";
		}
		return result;
	}
	
	
}
