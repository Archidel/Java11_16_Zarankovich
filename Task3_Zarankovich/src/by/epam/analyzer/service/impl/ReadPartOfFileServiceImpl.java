package by.epam.analyzer.service.impl;

import java.util.List;

import by.epam.analyzer.bean.Element;
import by.epam.analyzer.dao.ReadPartOfFileDAO;
import by.epam.analyzer.dao.XMLFileDAO;
import by.epam.analyzer.dao.exception.DAOException;
import by.epam.analyzer.dao.factory.DAOFactory;
import by.epam.analyzer.service.FileParameters;
import by.epam.analyzer.service.ReadPartOfFileService;
import by.epam.analyzer.service.exception.ServiceException;
import by.epam.analyzer.service.validation.ValidationData;

public class ReadPartOfFileServiceImpl implements ReadPartOfFileService {

	private static final String FILENAME_RESULT = "PartOfFile(Result).txt";
	
	@Override
	public void readPartOfFile(String filename) throws ServiceException {
		
		if(!ValidationData.validationString(filename)){
			throw new ServiceException("Incorrect filename");
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		ReadPartOfFileDAO readPartOfFileDAO = factory.getReadPartOfFileDAO();
		XMLFileDAO xmlFileDAO = factory.getXmlFileDAO();
		
		try {
			List<Element> listElement;
			listElement = readPartOfFileDAO.getListElement(FileParameters.FILE_PATH_SOURCE + filename);
			listElement = setTagType(listElement);
			xmlFileDAO.setDataToFileElementList(listElement, FileParameters.FILE_PATH_RESULT + FILENAME_RESULT);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	private List<Element> setTagType(List<Element> listElement){
		for(int i = 0; i < listElement.size(); i++){
			String nameElement = listElement.get(i).getNameElement();
			char [] nameCharArray = nameElement.toCharArray();
			if(nameCharArray[0] == '/'){
				listElement.get(i).setTypeElement(FileParameters.TAG_CLOSE);
			}else{
				listElement.get(i).setTypeElement(FileParameters.TAG_OPEN);
			}
		}
		
		return listElement;
	}

}

