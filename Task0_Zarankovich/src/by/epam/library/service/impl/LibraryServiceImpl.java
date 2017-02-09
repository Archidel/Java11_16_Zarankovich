package by.epam.library.service.impl;

import by.epam.library.dao.LibraryDAO;
import by.epam.library.dao.exception.DAOException;
import by.epam.library.dao.factory.DAOFactory;
import by.epam.library.service.LibraryService;
import by.epam.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService {

	@Override
	public void RenameBook(int idBook, String newName) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		LibraryDAO libraryDAO = daoFactory.getLibraryDao();
		
		try {
			libraryDAO.RenameBook(idBook,newName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void ShowthanOneBook() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		LibraryDAO libraryDAO = daoFactory.getLibraryDao();
		
		try {
			libraryDAO.ShowthanOneBook();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void ShowThanTwoOrEqualseBook() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		LibraryDAO libraryDAO = daoFactory.getLibraryDao();
	
		try {
			libraryDAO.ShowThanTwoOrEqualseBook();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

}
