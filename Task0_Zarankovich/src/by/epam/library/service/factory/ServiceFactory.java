package by.epam.library.service.factory;

import by.epam.library.service.LibraryService;
import by.epam.library.service.impl.LibraryServiceImpl;

public final class ServiceFactory {
	private static ServiceFactory instance = null;
	private static final LibraryService LIBRARY_SERVICE = new LibraryServiceImpl();
	
	private ServiceFactory() {
		// TODO Auto-generated constructor stub
	}

	public static ServiceFactory getInstance() {
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public static LibraryService getLibraryService() {
		return LIBRARY_SERVICE;
	}

	
	
}
