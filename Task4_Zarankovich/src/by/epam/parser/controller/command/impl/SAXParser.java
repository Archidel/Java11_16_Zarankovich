package by.epam.parser.controller.command.impl;

import by.epam.parser.bean.Response;
import by.epam.parser.controller.command.Command;
import by.epam.parser.service.SAXParserService;
import by.epam.parser.service.exception.ServiceException;
import by.epam.parser.service.factory.ServiceFactory;

public class SAXParser implements Command {

	@Override
	public Response execute(String request){
		String [] parameters = request.split(" ");
		String filename = parameters[1];

		ServiceFactory factory = ServiceFactory.getInstance();
		SAXParserService saxParserService = factory.getSaxParserService();
		
		Response response;
		
		try {
			response = saxParserService.doParsing(filename);
		} catch (ServiceException e) {
			response = new Response();
			response.setErrorMessage("Attempt to parse fails (SAX)");
			response.setErrorStatus(true);
			//Logger.log(Level.ERROR, e);
			e.printStackTrace();
		}
		
		return response;
	}

}
