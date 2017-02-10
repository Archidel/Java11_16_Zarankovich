package by.epam.analyzer.service.validation;

public final class ValidationData {
	
	private ValidationData() {}
	
	public static boolean validationString(String line){		
		if((line == null) || (line.isEmpty())){
			return false;
		}else{
			return true;
		}
	}
	
}
