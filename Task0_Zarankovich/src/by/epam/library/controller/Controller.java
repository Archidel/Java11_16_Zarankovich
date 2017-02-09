package by.epam.library.controller;

import by.epam.library.controller.command.Command;
import by.epam.library.controller.command.CommandProvider;

public final class Controller {
	private final char paramDelimeter = ' ';
	
	public String executeAction(String request){
		String commandName;
		Command command;
		
		commandName = request.substring(0, request.indexOf(paramDelimeter));
		System.out.println("COMMAND NAME" + commandName);
		
		CommandProvider provider = CommandProvider.getInstance();
		command = provider.getCommand(commandName);
		
		String response;
		response = command.execute(request);
		return response;
	}
	
}
