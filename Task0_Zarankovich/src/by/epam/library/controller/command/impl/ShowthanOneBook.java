package by.epam.library.controller.command.impl;

import by.epam.library.controller.command.Command;
import by.epam.library.service.LibraryService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;

public class ShowthanOneBook implements Command {

	@Override
	public String execute(String request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService libraryService = factory.getLibraryService();
		
		try {
			libraryService.ShowthanOneBook();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
