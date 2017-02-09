package by.epam.library.view;

import by.epam.library.controller.Controller;
import by.epam.library.controller.command.CommandName;

public class View {
	public static final Controller CONTROLLER = new Controller();
	public static void main(String [] args){
		String response = null;
		
		
		response = CONTROLLER.executeAction(CommandName.SHOW_THAN_1_book.name());

		response = CONTROLLER.executeAction(CommandName.SHOW_THAN_2_OR_EQUALSE_BOOK.name());
		
		response = CONTROLLER.executeAction(CommandName.RENAME_BOOK.name());
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