package by.epam.library.service;

import by.epam.library.service.exception.ServiceException;

public interface LibraryService {
	void renameBook(int idBook, String newName) throws ServiceException;
	void showthanOneBook() throws ServiceException;
	void showThanTwoOrEqualseBook() throws ServiceException;
	
}
