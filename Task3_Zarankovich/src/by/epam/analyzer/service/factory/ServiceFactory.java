package by.epam.analyzer.service.factory;

import by.epam.analyzer.service.ReadPartOfFileService;
import by.epam.analyzer.service.ReadWholeFileSerivce;
import by.epam.analyzer.service.impl.ReadPartOfFileServiceImpl;
import by.epam.analyzer.service.impl.ReadWholeFileSerivceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance = null;
	private final ReadWholeFileSerivce WHOLE_FILE_SERIVCE = new ReadWholeFileSerivceImpl();
	private final ReadPartOfFileService READ_PART_OF_FILE_SERVICE = new ReadPartOfFileServiceImpl();
	
	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public ReadWholeFileSerivce getWholeFileSerivce() {
		return WHOLE_FILE_SERIVCE;
	}

	public ReadPartOfFileService getReadPartOfFileService() {
		return READ_PART_OF_FILE_SERVICE;
	}
	
}
