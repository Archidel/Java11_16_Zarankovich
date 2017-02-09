package by.epam.library.dao;

import by.epam.library.dao.exception.DAOException;

public interface LibraryDAO {
	void RenameBook() throws DAOException;
	void ShowthanOneBook() throws DAOException;
	void ShowThanTwoOrEqualseBook() throws DAOException;
}
