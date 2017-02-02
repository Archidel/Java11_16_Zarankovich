package by.epam.analyzer.dao;

import java.util.List;

import by.epam.analyzer.bean.Element;
import by.epam.analyzer.dao.exception.DAOException;

public interface ReadPartOfFileDAO {
	List<Element> getListElement(String path) throws DAOException;
}
