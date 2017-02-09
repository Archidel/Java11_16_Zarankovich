package by.epam.library.service.factory;

import by.epam.library.service.LibraryService;
import by.epam.library.service.impl.LibraryServiceImpl;

public final class ServiceFactory {
	private static ServiceFactory instance = null;
	private final LibraryService libraryService = new LibraryServiceImpl();
	
	private ServiceFactory() {
		// TODO Auto-generated constructor stub
	}

	public static ServiceFactory getInstance() {
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}

	
	
}
