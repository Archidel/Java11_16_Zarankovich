package by.epam.analyzer.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.epam.analyzer.bean.Element;
import by.epam.analyzer.dao.XMLFileDAO;
import by.epam.analyzer.dao.exception.DAOException;

public class XMLFileDAOIMpl implements XMLFileDAO {

	@Override
	public ArrayList<String> getDataFromFile(String path) throws DAOException {
		ArrayList<String> list = new ArrayList<String>();
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)));){
			while(reader.ready()){
				list.add(reader.readLine().trim());
			}		
		} catch (FileNotFoundException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		
		return list;
	}

	@Override
	public void setDataToFileStringList(List<String> list, String pathForReport) throws DAOException {	
		try(FileWriter writer = new FileWriter(pathForReport, false)){
			for(int i = 0; i < list.size(); i++){
				writer.write(list.get(i) + "\n");
			}
			writer.flush();	
		}catch(IOException e){
			throw new DAOException(e);
        } 
	}

	@Override
	public void setDataToFileElementList(List<Element> listElement, String pathForResult) throws DAOException {
		try(FileWriter writer = new FileWriter(pathForResult, false)){
			for(int i = 1; i < listElement.size(); i++){
				writer.write(listElement.get(i).getTypeElement() + " " + listElement.get(i).getNameElement() + "\n");
			}
			writer.flush();
		}catch(IOException e){
			throw new DAOException(e);
        } 
	}
	
}
