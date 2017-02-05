package by.epam.parser.service.factory;

import by.epam.parser.service.DOMParserService;
import by.epam.parser.service.SAXParserService;
import by.epam.parser.service.StAXParserService;
import by.epam.parser.service.impl.DOMParserServiceImpl;
import by.epam.parser.service.impl.SAXParserServiceImpl;
import by.epam.parser.service.impl.StAXParserServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance = null;
	private final DOMParserService domParserService = new DOMParserServiceImpl();
	private final SAXParserService saxParserService = new SAXParserServiceImpl();
	private final StAXParserService staxParserService = new StAXParserServiceImpl();
	
	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public DOMParserService getDomParserService() {
		return domParserService;
	}

	public SAXParserService getSaxParserService() {
		return saxParserService;
	}

	public StAXParserService getStaxParserService() {
		return staxParserService;
	}

}
