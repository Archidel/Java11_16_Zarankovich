package by.epam.library.view;

import by.epam.library.controller.Controller;
import by.epam.library.controller.command.CommandName;

public class View {
	public static final Controller CONTROLLER = new Controller();
	public static void main(String [] args){
		String response = null;
		
		
		//CONTROLLER.executeAction (commandName + idBook + newNameBook);
		response = CONTROLLER.executeAction(CommandName.SHOW_THAN_1_BOOK.name() + " 1 mybook");
		System.out.println(response);
		
		response = CONTROLLER.executeAction(CommandName.SHOW_THAN_2_OR_EQUALSE_BOOK.name());
		System.out.println(response);
		
		response = CONTROLLER.executeAction(CommandName.RENAME_BOOK.name());
		System.out.println(response);
	}
}

/*
 * 
 * у которых больше чем 1 книга
 * больше или равно 2 книги
 * em name: emp date o f birstad: number of books
 */

// rename book name
//name : number of book

/*


*/