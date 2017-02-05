package by.epam.parser.controller.command.impl;

import by.epam.parser.bean.Response;
import by.epam.parser.controller.command.Command;
import by.epam.parser.service.DOMParserService;
import by.epam.parser.service.exception.ServiceException;
import by.epam.parser.service.factory.ServiceFactory;

public class DOMParser implements Command {

	@Override
	public Response execute(String request){
		String [] parameters = request.split(" ");
		String filename = parameters[1];

		ServiceFactory factory = ServiceFactory.getInstance();
		DOMParserService domParserService = factory.getDomParserService();
		
		Response response;
		
		try {
			response = domParserService.doParsing(filename);
		} catch (ServiceException e) {
			response = new Response();
			response.setErrorMessage("Attempt to parse fails (DOM)");
			response.setErrorStatus(true);
			//Logger.log(Level.ERROR, e);
			e.printStackTrace();
		}
		
		return response;
	}

}
