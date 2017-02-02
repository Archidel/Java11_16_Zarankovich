package by.epam.analyzer.dao.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.epam.analyzer.bean.Element;
import by.epam.analyzer.dao.ReadPartOfFileDAO;
import by.epam.analyzer.dao.exception.DAOException;

public class ReadPartOfFileDAOImpl implements ReadPartOfFileDAO {
	
	@Override 
	public List<Element> getListElement(String path) throws DAOException {
		List<Element> listElement = new ArrayList<>();
		char symbol = 0;
		
		try(FileReader reader = new FileReader(path)){ 
			 
			StringBuilder builder = null;
			Element element = null;
			int c = 0;
			boolean isWriting = false;
			 
			while ((c = reader.read()) != -1) {
				symbol = (char) c; 
	
				if(symbol == '<'){
					element = new Element();
					builder = new StringBuilder();
					symbol =(char) reader.read();
					isWriting = true;
				}
				 
				if(symbol == ' ' || symbol == '>'){
					if(isWriting){					 
						isWriting = false;
						symbol =(char) reader.read();
						element.setNameElement(builder.toString());
						listElement.add(element);
					}	 
				}
				
				if(isWriting){
					builder.append(symbol);
				}
			
			}
		}catch (IOException  e) {
			throw new DAOException(e);
		}
		
		return listElement;
	}

}
