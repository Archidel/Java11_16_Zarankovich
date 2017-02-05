package by.epam.parser.view;

import by.epam.parser.bean.Response;

public final class PrintData {
	
	public static void PrintResponse(Response response){	
		if(response.isErrorStatus()){
			System.out.println(response.getErrorMessage());
		}else{
			System.out.println(response.getResponseMessage());
			System.out.println();
			// Строка исключительно для демнстрации праивльности работы приложения
			System.out.println(response.getWebApp().toString());
		}
	}
	
}
