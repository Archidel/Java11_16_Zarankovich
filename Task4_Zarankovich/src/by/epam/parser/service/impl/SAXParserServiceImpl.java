package by.epam.parser.service.impl;

import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.parser.bean.Response;
import by.epam.parser.service.SAXParserService;
import by.epam.parser.service.exception.ServiceException;
import by.epam.parser.service.validation.ValidationData;

public class SAXParserServiceImpl implements SAXParserService {
	public static final String FILE_PATH = "src/resource/web.xml";
	
	@Override
	public Response doParsing(String filename) throws ServiceException {
		
		if(!ValidationData.validationString(filename)){
			throw new ServiceException("Incorrect filename");
		}
		
		XMLReader reader = null;
		SAXHandler handler = null;
		Response response = null;
		
		try {
			reader = XMLReaderFactory.createXMLReader();
			handler = new SAXHandler();
			
			reader.setContentHandler(handler);
			reader.parse(new InputSource(FILE_PATH));
		} catch (SAXException e) {
			response = new Response();
			response.setErrorMessage("Xml file not found");
			response.setErrorStatus(true);
			throw new ServiceException(e);
		} catch (IOException e) {
			response = new Response();
			response.setErrorMessage("Xml file not found");
			response.setErrorStatus(true);
			throw new ServiceException(e);
		}
		
		response = new Response();
		response.setResponseMessage("File has been successfully parsed");
		response.setWebApp(handler.getWebApp());
		
		return response;
	}

}
