package by.epam.library.dao;

import by.epam.library.dao.exception.DAOException;

public interface LibraryDAO {
	void renameBook(int idBook, String newName) throws DAOException;
	void showthanOneBook() throws DAOException;
	void showThanTwoOrEqualseBook() throws DAOException;
}
