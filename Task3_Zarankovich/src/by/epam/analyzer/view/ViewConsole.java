package by.epam.analyzer.view;

import by.epam.analyzer.controller.Controller;

public final class ViewConsole {
	private static final Controller CONTROLLER = new Controller();
	
	public static void main(String [] args){
		String response = null;
		
		// controller.exceuteActioon(тип_анализа, имя файла);
		//Чтение с полной загрузкой файла в коллекцию
		response = CONTROLLER.executeAction("read_whole_file breakfaseMenu.xml");
		PrintData.printResponse(response);
		
		//Чтение файла по элементам файла
		response = CONTROLLER.executeAction("read_part_of_file notes.xml");
		PrintData.printResponse(response);
		
	}
	
}
