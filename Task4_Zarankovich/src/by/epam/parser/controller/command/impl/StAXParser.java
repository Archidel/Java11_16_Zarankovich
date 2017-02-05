package by.epam.parser.controller.command.impl;

import by.epam.parser.bean.Response;
import by.epam.parser.controller.command.Command;
import by.epam.parser.service.StAXParserService;
import by.epam.parser.service.exception.ServiceException;
import by.epam.parser.service.factory.ServiceFactory;

public class StAXParser implements Command {

	@Override
	public Response execute(String request){	
		String [] parameters = request.split(" ");
		String filename = parameters[1];
		
		ServiceFactory factory = ServiceFactory.getInstance();
		StAXParserService staxParserService = factory.getStaxParserService();
		
		Response response;
		
		try {
			response = staxParserService.doParsing(filename);
		} catch (ServiceException e) {
			response = new Response();
			response.setErrorMessage("Attempt to parse fails (StAX)");
			response.setErrorStatus(true);
			//Logger.log(Level.ERROR, e);
			e.printStackTrace();
		}
		
		return response;
	}

}
