package by.epam.analyzer.dao.factory;

import by.epam.analyzer.dao.ReadPartOfFileDAO;
import by.epam.analyzer.dao.XMLFileDAO;
import by.epam.analyzer.dao.impl.ReadPartOfFileDAOImpl;
import by.epam.analyzer.dao.impl.XMLFileDAOIMpl;

public class DAOFactory {
	
	private static DAOFactory instance = null;
	private final XMLFileDAO xmlFileDAO = new XMLFileDAOIMpl();
	private final ReadPartOfFileDAO readPartOfFileDAO = new ReadPartOfFileDAOImpl();

	private DAOFactory() {}

	public static DAOFactory getInstance() {
		if(instance == null){
			instance = new DAOFactory();
		}
		return instance;
	}

	public XMLFileDAO getXmlFileDAO() {
		return xmlFileDAO;
	}

	public ReadPartOfFileDAO getReadPartOfFileDAO() {
		return readPartOfFileDAO;
	}
		
}

