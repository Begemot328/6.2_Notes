package by.module6.notebook.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private String theme;
	private String text;
	private String mail;
	private Date date;
	
	public Note(String theme, String mail, Date date, String text) {
		super();
		this.theme = theme;
		this.text = text;
		this.mail = mail;
		this.date = date;
	}
	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}
	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Note [theme=" + theme + ", text=" + text + ", mail=" + mail + ", date=" + new SimpleDateFormat(DATE_FORMAT).format(date) + "]";
	}
	
	
	
}
