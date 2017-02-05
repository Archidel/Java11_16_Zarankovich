package by.epam.parser.controller.command;

import by.epam.parser.bean.Response;

public interface Command {
	Response execute(String request);
}
