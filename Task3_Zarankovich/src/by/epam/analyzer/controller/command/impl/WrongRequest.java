package by.epam.analyzer.controller.command.impl;

import by.epam.analyzer.controller.command.Command;

public class WrongRequest implements Command {

	@Override
	public String execute(String request) {
		String responce = "Request failed";
		return responce;
	}

}
