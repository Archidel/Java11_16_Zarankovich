package by.epam.parser.controller.command.impl;

import by.epam.parser.bean.Response;
import by.epam.parser.controller.command.Command;

public class WrongRequest implements Command {

	@Override
	public Response execute(String request) {
		Response response = new Response();
		response.setErrorStatus(true);
		response.setErrorMessage("Request failed");
		return response;
	}

}
