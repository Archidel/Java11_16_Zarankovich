package by.epam.analyzer.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.analyzer.controller.command.impl.ReadPartOfFile;
import by.epam.analyzer.controller.command.impl.ReadWholeFile;
import by.epam.analyzer.controller.command.impl.WrongRequest;

public final class CommandProvider {
	 
	private static CommandProvider instance = null;
	private final Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	private CommandProvider() {
		commands.put(CommandName.READ_WHOLE_FILE, new ReadWholeFile());
		commands.put(CommandName.READ_PART_OF_FILE, new ReadPartOfFile());
		commands.put(CommandName.WROG_REQUEST, new WrongRequest());
	}
	
	public Command getCommand(String key){
		CommandName commandName;
		Command command;
		
		try{
			commandName = CommandName.valueOf(key.toUpperCase());	
			command = commands.get(commandName);
		}catch(IllegalArgumentException | NullPointerException e){
			command = commands.get(CommandName.WROG_REQUEST);
			//logger.log(Level.ERROR, e);
			e.printStackTrace();
		}
		
		return command;
	}

	public static CommandProvider getInstance() {
		if(instance == null){
			instance = new CommandProvider();
		}
		return instance;
	}

}
