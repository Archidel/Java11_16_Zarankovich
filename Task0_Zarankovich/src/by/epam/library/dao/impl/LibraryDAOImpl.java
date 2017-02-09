package by.epam.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.library.dao.LibraryDAO;
import by.epam.library.dao.SQLCommand;
import by.epam.library.dao.connection.ConnectionPool;
import by.epam.library.dao.exception.DAOException;

public class LibraryDAOImpl implements LibraryDAO {

	@Override
	public void RenameBook(int idBook, String newName) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;
		Connection con = null;
		
		try {
			con = pool.take();
			
			preparedStatement = con.prepareStatement(SQLCommand.RENAME_BOOK);
			
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, idBook);
			
			preparedStatement.executeUpdate();
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			try {
				pool.free(con);
			} catch (InterruptedException e) {
				throw new DAOException(e);
		
			}
		}
	}

	@Override
	public void ShowthanOneBook() throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
				
		try {
			con = pool.take();
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(SQLCommand.SHOW_ONE);
			
			while(rs.next()){
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
		
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			try {
				pool.free(con);
			} catch (InterruptedException e) {
				throw new DAOException(e);
			}	//Освобождение очереди
		}
		
	}

	@Override
	public void ShowThanTwoOrEqualseBook() throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
				
		try {
			con = pool.take();
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(SQLCommand.SHOW_TWO);
			
			while(rs.next()){
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			} 
		
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			try {
				pool.free(con);
			} catch (InterruptedException e) {
				throw new DAOException(e);
			}
		}
		
	}
	
}
