package by.epam.parser.service;

import by.epam.parser.bean.Response;
import by.epam.parser.service.exception.ServiceException;

public interface StAXParserService {
	Response doParsing(String filename) throws ServiceException;
}
