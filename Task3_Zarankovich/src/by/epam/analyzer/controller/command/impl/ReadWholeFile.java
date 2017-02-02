package by.epam.analyzer.controller.command.impl;

import by.epam.analyzer.controller.command.Command;
import by.epam.analyzer.service.ReadWholeFileSerivce;
import by.epam.analyzer.service.exception.ServiceException;
import by.epam.analyzer.service.factory.ServiceFactory;

public class ReadWholeFile implements Command {

	@Override
	public String execute(String request){
		String [] parameters = request.split(" ");
		String filename = parameters[1];
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ReadWholeFileSerivce readWholeFileSerivce = factory.getWholeFileSerivce();
	
		String response;
		try {
			readWholeFileSerivce.readWholeFile(filename);	
			response = new String("File successfully parsed (Read whole file)");
		} catch (ServiceException e) {
			response = new String("File reading failed (Read whole file)");
			//logger.log(Level.ERROR, e);
			e.printStackTrace();
		}
		
		return response;
	}

}
