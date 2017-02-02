package by.epam.analyzer.service;

import by.epam.analyzer.service.exception.ServiceException;

public interface ReadWholeFileSerivce {
	void readWholeFile(String filename) throws ServiceException;
}
