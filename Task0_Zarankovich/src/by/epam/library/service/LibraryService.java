package by.epam.library.service;

import by.epam.library.service.exception.ServiceException;

public interface LibraryService {
	void RenameBook(int idBook, String newName) throws ServiceException;
	void ShowthanOneBook() throws ServiceException;
	void ShowThanTwoOrEqualseBook() throws ServiceException;
	
}
