package by.epam.analyzer.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.analyzer.dao.XMLFileDAO;
import by.epam.analyzer.dao.exception.DAOException;
import by.epam.analyzer.dao.factory.DAOFactory;
import by.epam.analyzer.service.FileParameters;
import by.epam.analyzer.service.ReadWholeFileSerivce;
import by.epam.analyzer.service.exception.ServiceException;
import by.epam.analyzer.service.validation.ValidationData;

public class ReadWholeFileSerivceImpl implements ReadWholeFileSerivce{
	private static final String FILENAME_RESULT = "WholeFile(Result).txt";
	
	@Override
	public void readWholeFile(String filename) throws ServiceException {
	
		if(!ValidationData.validationString(filename)){
			throw new ServiceException("Incorrect filename");
		}
				
		DAOFactory daoFactory = DAOFactory.getInstance();
		XMLFileDAO xmlFileDAO = daoFactory.getXmlFileDAO();
		List<String> dataList;
			
		try {
			dataList = xmlFileDAO.getDataFromFile(FileParameters.FILE_PATH_SOURCE + filename);
			xmlFileDAO.setDataToFileStringList(dataAnalysis(dataList), FileParameters.FILE_PATH_RESULT + FILENAME_RESULT);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	
	private List<String> dataAnalysis(List<String> dataList) throws ServiceException{
		List<String> analyzerList = new ArrayList<String>();
		//Цикл который запускает всю эту карусель
		for(int i = 1; i < dataList.size(); i++){
			char [] tempCharArray = dataList.get(i).toCharArray(); // Строку в массив символов
			int amountTagsInline = tagCounterInLine(tempCharArray); // Подсчёт количество тегов в строке
			String xmlLine = tagDispatcher(amountTagsInline, tempCharArray); // Распределение по полученным данным
			analyzerList.add(xmlLine);
		}
		
		return analyzerList;
	}
	
	//Подсчёт коилчество тегов в строке и возвращение значения
	private int tagCounterInLine(char [] array){
		int tagCounter = 0;
		for(char count : array){
			if(count == '<'){
				tagCounter++;
			}
		}
		
		return tagCounter;
	}
	
	//Распределяет скроки для обработки в зависимости количества тегов в строке
	private String tagDispatcher(int tagCouner, char [] array) throws ServiceException{
		String line;
		switch (tagCouner) {
		case 0:
			line = noTag(array);
			break;
		case 1:
			line = singleTag(array);
			break;
		case 2:
			line = doubleTag(array);
			break;
		default:
			throw new ServiceException("Sorry, this method of reading the file will not be able to read the file, choose another.");
		}
		
		return line;
	}
	
	//В строке не присутвуют теги
	private String noTag(char [] array){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Содержит в себе: ");
		for(int i = 0; i < array.length; i++){
			stringBuffer.append(array[i]);
		}
		
		return stringBuffer.toString();
	}
	
	//В строке присутствует 1 тега example: <note> or </note>
	private String singleTag(char[] array) throws ServiceException {
		int numberOpeningTag = -1;
		int numberClosingTag = -1;
		boolean isClosedTag = false;
		
		//Запись индексов открывающегося, закрывающегося и определение закрывается тег или нет
		for(int j = 0; j < array.length; j++){
			if(array[j] == '<'){
				numberOpeningTag = j;
			}
			if(array[j] == '>'){
				numberClosingTag = j;
			}
			if(array[j] == '<' && array[j+1] == '/'){
				isClosedTag = true;
			}
		}
		
		String tagName = getTagName(numberOpeningTag, numberClosingTag, array);
		String tagAttribute = getTagAttrubite(numberOpeningTag, numberClosingTag, array);		
		return responseFormatterSingleTag(tagName, tagAttribute, isClosedTag);
	}
	
	//В строке присутствует 2 тега example: <note> ...content... </note>
	private String doubleTag(char[] array) throws ServiceException {
		int firstNumberOpeningTag = -1;
		int secondNumberClosingTag = -1;
		int thirdNumberOpeningTag = -1;
		
		for(int j = 0; j < array.length; j++){
			
			if(array[j] == '<'){
				if(firstNumberOpeningTag == -1){
					firstNumberOpeningTag = j;
				}else{
					thirdNumberOpeningTag = j;
				}
			}	
			
			if(array[j] == '>'){
				if(secondNumberClosingTag == -1){
					secondNumberClosingTag = j;
				}
			}
		}
		
		String tagName = getTagName(firstNumberOpeningTag, secondNumberClosingTag, array).trim();		
		String tagAttribute = getTagAttrubite(firstNumberOpeningTag, secondNumberClosingTag, array);			
		String tagContent = getTagContent(secondNumberClosingTag, thirdNumberOpeningTag, array);
		
		return responseFormatterDoubleTag(tagName, tagAttribute, tagContent);
	}

	//Читаем из массива символов название тега и возвращае строки
	private String getTagName(int numberOpeningTag,int numberClosingTag,char [] array){
		StringBuffer tagName = new StringBuffer();
		if(numberOpeningTag != -1 && numberClosingTag != -1){		
			for(int i = numberOpeningTag + 1; i < numberClosingTag; i++){
				if(array[i] == ' '){
					break;
				}else{
					tagName.append(array[i]);
				}
			}
		}
		
		return tagName.toString();
	}
	
	//Читаем из массива символов атрибут тега и возвращае строки
	private String getTagAttrubite(int numberOpeningTag,int numberClosingTag,char [] array){
		boolean isAttribute = false;
		int numberTagAttribute = 0;
		StringBuffer attribute = new StringBuffer();
		
		if(numberOpeningTag != -1 && numberClosingTag != -1){	
			for(int i = numberOpeningTag + 1; i < numberClosingTag; i++){
				if(array[i] == ' '){
					isAttribute = true;
					break;
				}else{
					numberTagAttribute = i + 2;
				}
			}
		
			//Читаем атрибут тега
			if(isAttribute){
				for(int i = numberTagAttribute; i < numberClosingTag; i++){
					attribute.append(array[i]);
				}
			}
		}
		
		return attribute.toString();
	}

	//Читает из массива символов содержимое тега и возвращает строку
	private String getTagContent(int secondNumberClosingTag, int thirdNumberOpeningTag, char[] array){
		StringBuffer stringBuffer = new StringBuffer();
		for(int k = secondNumberClosingTag + 1; k < thirdNumberOpeningTag; k++){
			stringBuffer.append(array[k]);
		}
	
		return stringBuffer.toString();
	}
	
	//Формирует на основании полученных данных полноценную строку для вывода и возвращаем строку
	private String responseFormatterSingleTag(String tagName, String tagAttribute, boolean isClosedTag){
		StringBuffer stringBuffer = new StringBuffer();
		
		if(isClosedTag){
			stringBuffer.append("Закрывается тег ").append(tagName.toUpperCase()); 
		}else{
			stringBuffer.append("Открывается тег ").append(tagName.toUpperCase()).append(" " + tagAttribute);
		}
		
		return stringBuffer.toString();
	}
	
	//Формирует на основании полученных данных полноценную строку для вывода и возвращаем строку
	private String responseFormatterDoubleTag(String tagName, String tagAttribute, String tagContent){
		StringBuffer stringBuffer = new StringBuffer();
		
		if(ValidationData.validationString(tagAttribute)){
			stringBuffer.append("Открывается тег ").append(tagName.toUpperCase()).append(" который имеет атрибуты: ").append(tagAttribute.toUpperCase()).append("и содержит в себе: ").append(tagContent.toUpperCase()).append(". Тег ").append(tagName.toUpperCase()).append(" закрывается.");
		}else{
			stringBuffer.append("Открывается тег ").append(tagName.toUpperCase()).append(" который содержит в себе: ").append(tagContent.toUpperCase()).append(". Тег ").append(tagName.toUpperCase()).append(" закрывается.");
		}
		
		return stringBuffer.toString();
	}
	
}
