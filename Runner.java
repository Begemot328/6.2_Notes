package by.module6.notebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Locale;

import by.module6.notebook.DOM.NoteBookBuilder;
import by.module6.notebook.DOM.XMLDOMWriter;
import by.module6.notebook.controller.Controller;
import by.module6.notebook.entity.NoteBook;

/*  @author Yury Zmushko

* Задание 2. Блокнот.  Разработать консольное приложение, работающее с Заметками 
* в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).  
* Общие пояснения к практическому заданию. 
* •  В начале работы приложения данные должны считываться из файла, в конце 
* работы – сохраняться в файл. 
* •  У пользователя должна быть возможность найти запись по любому параметру 
* или  по  группе  параметров  (группу  параметров  можно  определить 
* самостоятельно), получить требуемые записи в отсортированном виде, найти 
* записи,  текстовое  поле  которой  содержит  определенное  слово,  а  также 
* добавить новую запись. 
* •  Особое  условие:  поиск,  сравнение  и  валидацию  вводимой  информации 
* осуществлять с использованием регулярных выражений. 
* •  Особое  условие:  проверку  введенной  информации  на  валидность  должен 
* осуществлять код, непосредственно добавляющий информацию.  
 */

public class Runner {
	
	public static void main(String[] args) {
		
		Controller controller = new Controller();
		controller.run();
		
		/*
		NoteBookBuilder builder = new NoteBookBuilder();
		NoteBook noteBook =  builder.getNoteBook(("notebook"));
		
		System.out.println(noteBook);

		XMLDOMWriter dom = new XMLDOMWriter(noteBook);
		dom.createXML();

		Controller controller = Controller.getInstance();
		controller.run();
		*/
	}	
}


