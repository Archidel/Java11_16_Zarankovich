package by.epam.library.dao.factory;

import by.epam.library.dao.LibraryDAO;
import by.epam.library.dao.impl.LibraryDAOImpl;

public final class DAOFactory {
	private static DAOFactory instance = null;
	private final LibraryDAO libraryDAO = new LibraryDAOImpl();

	private DAOFactory() {}

	public static DAOFactory getInstance() {
		if(instance == null){
			instance = new DAOFactory();
		}
		return instance;
	}

	public LibraryDAO getLibraryDao() {
		return libraryDAO;
	}

	
}