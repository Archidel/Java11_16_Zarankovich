package by.epam.library.service.impl;

import by.epam.library.dao.LibraryDAO;
import by.epam.library.dao.exception.DAOException;
import by.epam.library.dao.factory.DAOFactory;
import by.epam.library.service.LibraryService;
import by.epam.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService {

	@Override
	public void renameBook(int idBook, String newName) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		LibraryDAO libraryDAO = daoFactory.getLibraryDao();
		
		try {
			libraryDAO.renameBook(idBook,newName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void showthanOneBook() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		LibraryDAO libraryDAO = daoFactory.getLibraryDao();
		
		try {
			libraryDAO.showthanOneBook();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void showThanTwoOrEqualseBook() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		LibraryDAO libraryDAO = daoFactory.getLibraryDao();
	
		try {
			libraryDAO.showThanTwoOrEqualseBook();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

}
