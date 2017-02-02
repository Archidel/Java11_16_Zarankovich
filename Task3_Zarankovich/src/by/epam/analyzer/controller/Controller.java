package by.epam.analyzer.controller;

import by.epam.analyzer.controller.command.Command;
import by.epam.analyzer.controller.command.CommandProvider;

public final class Controller {
	private final char paramDelimeter = ' ';
	
	public String executeAction(String request){
		String commandName;
		Command command;
		
		commandName = request.substring(0, request.indexOf(paramDelimeter));
		CommandProvider provider = CommandProvider.getInstance();
		command = provider.getCommand(commandName);
		
		String response;
		response = command.execute(request);
		return response;
	}
	
}
