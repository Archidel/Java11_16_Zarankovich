package by.epam.parser.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.parser.controller.command.impl.DOMParser;
import by.epam.parser.controller.command.impl.SAXParser;
import by.epam.parser.controller.command.impl.StAXParser;
import by.epam.parser.controller.command.impl.WrongRequest;

public final class CommandProvider {
	private static CommandProvider instance = null;
	public static final Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	private CommandProvider() {
		commands.put(CommandName.SAX_PARSER, new SAXParser());
		commands.put(CommandName.STAX_PARSER, new StAXParser());
		commands.put(CommandName.DOM_PARSER, new DOMParser());
		commands.put(CommandName.WRONG_REQUEST, new WrongRequest());
	}

	public static CommandProvider getInstance() {
		if(instance == null){
			instance = new CommandProvider();
		}
		return instance;
	}

	public Command getCommand(String key){
		Command command;
		CommandName commandName;
		
		try{
			commandName = CommandName.valueOf(key.toUpperCase());	
			command = commands.get(commandName);
		}catch (IllegalArgumentException | NullPointerException e) {
			command = commands.get(CommandName.WRONG_REQUEST);
			//logger.log(Level.ERROR, e);
			e.printStackTrace();
		}
		
		return command;
	}
	
}
