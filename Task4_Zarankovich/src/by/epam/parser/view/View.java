package by.epam.parser.view;

import by.epam.parser.bean.Response;
import by.epam.parser.controller.Controller;

public final class View {
	public static final Controller CONTROLLER = new Controller();
	
	public static void main(String [] args){		
		Response response;
		// controller.exceuteActioon(тип_парсера, имя файла);
		response = CONTROLLER.executeAction("sax_parser web.xml");
		PrintData.PrintResponse(response);
		
//		response = CONTROLLER.executeAction("stax_parser web.xml");
//		PrintData.PrintResponse(response);

//		response = CONTROLLER.executeAction("dom_parser web.xml");
//		PrintData.PrintResponse(response);
	}
	
}
