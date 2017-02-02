package by.epam.analyzer.dao;

import java.util.List;

import by.epam.analyzer.bean.Element;
import by.epam.analyzer.dao.exception.DAOException;

public interface XMLFileDAO {
	List<String> getDataFromFile(String path) throws DAOException;
	void setDataToFileStringList(List<String> listData, String pathForResult) throws DAOException;
	void setDataToFileElementList(List<Element> listElement, String pathForResult) throws DAOException;
}
