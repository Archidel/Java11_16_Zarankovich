package by.epam.library.controller.command.impl;

import by.epam.library.controller.command.Command;
import by.epam.library.service.LibraryService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;

public class RenameBook implements Command {

	@Override
	public String execute(String request) {
		String [] parameters = request.split(" ");
		String newName = parameters[1];
		int idBook = Integer.parseInt(parameters[2]);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService libraryService = factory.getLibraryService();
		
		try {
			libraryService.RenameBook(idBook, newName);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
