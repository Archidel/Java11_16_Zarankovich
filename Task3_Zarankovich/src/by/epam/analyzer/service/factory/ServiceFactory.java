package by.epam.analyzer.service.factory;

import by.epam.analyzer.service.ReadPartOfFileService;
import by.epam.analyzer.service.ReadWholeFileSerivce;
import by.epam.analyzer.service.impl.ReadPartOfFileServiceImpl;
import by.epam.analyzer.service.impl.ReadWholeFileSerivceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance = null;
	private final ReadWholeFileSerivce readWholeFileSerivce = new ReadWholeFileSerivceImpl();
	private final ReadPartOfFileService readPartOfFileService = new ReadPartOfFileServiceImpl();
	
	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public ReadWholeFileSerivce getWholeFileSerivce() {
		return readWholeFileSerivce;
	}

	public ReadPartOfFileService getReadPartOfFileService() {
		return readPartOfFileService;
	}
	
}
