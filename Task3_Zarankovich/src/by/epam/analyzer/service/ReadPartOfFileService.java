package by.epam.analyzer.service;

import by.epam.analyzer.service.exception.ServiceException;

public interface ReadPartOfFileService {
	void readPartOfFile(String filename) throws ServiceException;
	
}
