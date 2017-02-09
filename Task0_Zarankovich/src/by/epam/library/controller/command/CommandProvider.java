package by.epam.library.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.library.controller.command.impl.RenameBook;
import by.epam.library.controller.command.impl.ShowThanTwoOrEqualseBook;
import by.epam.library.controller.command.impl.ShowthanOneBook;
import by.epam.library.controller.command.impl.WrongRequest;

public class CommandProvider {
	private static CommandProvider instance = null;
	public static final Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	private CommandProvider() {
		commands.put(CommandName.SHOW_THAN_1_BOOK, new ShowthanOneBook());
		commands.put(CommandName.SHOW_THAN_2_OR_EQUALSE_BOOK, new ShowThanTwoOrEqualseBook());
		commands.put(CommandName.RENAME_BOOK, new RenameBook());
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
