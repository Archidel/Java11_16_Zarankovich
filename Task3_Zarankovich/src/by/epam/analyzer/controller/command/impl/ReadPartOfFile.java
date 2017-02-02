package by.epam.analyzer.controller.command.impl;

import by.epam.analyzer.controller.command.Command;
import by.epam.analyzer.service.ReadPartOfFileService;
import by.epam.analyzer.service.exception.ServiceException;
import by.epam.analyzer.service.factory.ServiceFactory;

public class ReadPartOfFile implements Command {

	@Override
	public String execute(String request){
		String [] parameters = request.split(" ");
		String filename = parameters[1];
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ReadPartOfFileService readPartOfFileService = factory.getReadPartOfFileService();
		
		String response;
		try {
			readPartOfFileService.readPartOfFile(filename);
			response = new String("File successfully parsed (Read part of file)");
		} catch (ServiceException e) {
			response = new String("File reading failed (Read part of file)");
			//logger.log(Level.ERROR, e);
			e.printStackTrace();
		}
		
		return response;
	}

}
