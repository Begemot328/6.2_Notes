package by.module6.notebook.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import by.module6.notebook.controller.SearchIndex;
import by.module6.notebook.entity.Note;
import by.module6.notebook.entity.NoteBook;
import by.module6.notebook.exception.InputException;

public class NoteBookLogic {
	public static final String WRONG_PASSWORD = "Wrong password!";
	public static final String USER_NOT_FOUND = "User not found!";
	public static final String WRONG_DATA = "Enter correct data!";
	private NoteBook notebook;
	
	public NoteBookLogic(NoteBook notebook) {
		super();
		this.notebook = notebook;
	}
	
	/**
	 * @return the notebook
	 */
	public NoteBook getNotebook() {
		return notebook;
	}

	/**
	 * @param notebook the notebook to set
	 */
	public void setNotebook(NoteBook notebook) {
		this.notebook = notebook;
	}
	
	public void addNote(String theme, String mail, Date date, String text) throws InputException {
		notebook.addNote(theme, mail, date, text);
	}
	
	public void addNote(String theme, String mail, String date, String text) throws InputException {
		notebook.addNote(theme, mail, date, text);
	}
	
	public class NoteSearch {
		private ArrayList<Note> list;
		
		public NoteSearch search(String name, SearchIndex index) {
			NoteSearch result = new NoteSearch();
			result.list = new ArrayList<Note>();
			if (list.isEmpty()) {
				return result;
			}
			for (Note note: this.list) {
				if(isOK(index, note, name)) {
					result.list.add(note);
				}
			}
			return result;
		}
		
		private boolean isOK(SearchIndex index, Note note, String name) {
			switch (index) {
			case THEME:
				return note.getTheme().contains(name) 
						|| name.contains(note.getTheme());
			case TEXT:
				return note.getText().contains(name) 
						|| name.contains(note.getText());
			case DATE:
				try {
					return new SimpleDateFormat(notebook.DATE_FORMAT).parse(name).equals(note.getDate());
				} catch (ParseException e) {
					return false;
				}
			case MAIL:
				return note.getMail().contains(name) 
						|| name.contains(note.getMail());
				
			}
			return true;
		}
		
		public ArrayList<Note> getResult() {
			return list;
		}
	}	
	
	public NoteSearch searchAll() {
		NoteSearch result = new NoteSearch();
		result.list = NoteBookLogic.this.notebook.getNotes();
		return result;
	}
}
