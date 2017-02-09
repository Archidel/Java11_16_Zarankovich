package by.epam.library.service;

import by.epam.library.service.exception.ServiceException;

public interface LibraryService {
	void RenameBook() throws ServiceException;
	void ShowthanOneBook() throws ServiceException;
	void ShowThanTwoOrEqualseBook() throws ServiceException;
	
}
