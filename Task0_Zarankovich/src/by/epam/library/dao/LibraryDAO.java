package by.epam.library.dao;

import by.epam.library.dao.exception.DAOException;

public interface LibraryDAO {
	void RenameBook(int idBook, String newName) throws DAOException;
	void ShowthanOneBook() throws DAOException;
	void ShowThanTwoOrEqualseBook() throws DAOException;
}
