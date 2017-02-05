package by.epam.parser.controller;

import by.epam.parser.bean.Response;
import by.epam.parser.controller.command.Command;
import by.epam.parser.controller.command.CommandProvider;

public final class Controller {
	private final char paramDelimeter = ' ';
	
	public Response executeAction(String request){
		String commandName;
		Command command;
		
		commandName = request.substring(0, request.indexOf(paramDelimeter));
		CommandProvider provider = CommandProvider.getInstance();
		command = provider.getCommand(commandName);
		
		Response response;
		response = command.execute(request);
		return response;
	}
	
}
