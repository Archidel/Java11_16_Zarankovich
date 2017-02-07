package by.epam.parser.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import by.epam.parser.bean.ErrorPage;
import by.epam.parser.bean.Filter;
import by.epam.parser.bean.FilterMapping;
import by.epam.parser.bean.InitParam;
import by.epam.parser.bean.Listener;
import by.epam.parser.bean.Response;
import by.epam.parser.bean.Servlet;
import by.epam.parser.bean.ServletMapping;
import by.epam.parser.bean.WebApp;
import by.epam.parser.service.StAXParserService;
import by.epam.parser.service.exception.ServiceException;
import by.epam.parser.service.name_tag.WebNameTag;
import by.epam.parser.service.validation.ValidationData;

public class StAXParserServiceImpl implements StAXParserService {
	private static final String FILE_PATH = "src/resource/web.xml";
	
	private String currentElement;
	private String previousElement;
	
	/* убрать всё и сотавить ссылку webapp
	 * кидайть её пометодам 
	 * ВЫНЕСТИ ИНИЦИАЛИЗАЦИЮ листов в отдельный меотдов
	 */ 
	
	@Override
	public Response doParsing(String filename) throws ServiceException {
		
		if(!ValidationData.validationString(filename)){
			throw new ServiceException("Incorrect filename");
		}
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		Response response = null;
		WebApp webApp = new WebApp();

		try {
			
			FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
			XMLEventReader xmlEventReader = factory.createXMLEventReader(fileInputStream);
			
			while(xmlEventReader.hasNext()){
				XMLEvent xmlEvent = xmlEventReader.nextEvent();
				
				if(xmlEvent.isStartElement()){
					startElement(xmlEvent.asStartElement(), webApp);
				}
				
				if(xmlEvent.isCharacters()){
					characters(xmlEvent.asCharacters(), webApp);
				}
				
				if(xmlEvent.isEndElement()){
					endElement(xmlEvent.asEndElement(), webApp);	
				}	
			}

		} catch (XMLStreamException e) {
			throw new ServiceException(e);
		} catch (FileNotFoundException e) {
			throw new ServiceException(e);
		}
		
		response = new Response();
		response.setWebApp(webApp);
		return response;
	}

	private void startElement(StartElement startElement, WebApp webApp){
		QName qName = startElement.getName();
		String nameElement = qName.toString();
		this.currentElement = nameElement;
	
		if(nameElement.equals("web-app")){
			setAttribute(startElement, webApp);
		}
	}

	private void characters(Characters characters, WebApp webApp) {
		String textContent = characters.getData();
		
		if(!currentElement.isEmpty()){
			WebNameTag tagName = WebNameTag.valueOf(currentElement.toUpperCase().replace("-", "_"));

			switch (tagName) {			
			case DISPLAY_NAME:
				if(webApp.getDisplayNameList() == null){
					webApp.setDisplayNameList(new ArrayList<String>());
				}
				webApp.addDisplayName(textContent);	
				break;
			case WELCOME_FILE_LIST:
				if(webApp.getWelcomeFileList() == null){
					webApp.setWelcomeFileList(new ArrayList<String>());
				}
				break;
			case WELCOME_FILE:
				webApp.addWelcomeFile(textContent);
				break;
			case FILTER:
				if(webApp.getFilterList() == null){
					webApp.setFilterList(new ArrayList<Filter>());
				}
				webApp.addFilter(new Filter());			
				this.previousElement = this.currentElement;
				break;
			case FILTER_MAPPING:
				if(webApp.getFilterMappingList() == null){
					webApp.setFilterMappingList(new ArrayList<FilterMapping>());
				}
				webApp.addFilterMapping(new FilterMapping());
				this.previousElement = this.currentElement;
				break;
			case FILTER_NAME:
				switch (previousElement) {
				case "filter":
					webApp.getLastFilter().setFilterName(textContent);
					break;
				case "filter-mapping":
					webApp.getLastFilterMapping().setFilterName(textContent);
					break;
				}
				break;
			case FILTER_CLASS:
				webApp.getLastFilter().setFilterClass(textContent);
				break;		
			case DISPATCHER:
				webApp.getLastFilterMapping().setDispatcher(textContent);
				break;
			case INIT_PARAM:
				webApp.getLastFilter().setInitParam(new InitParam());
			case PARAM_NAME:
				webApp.getLastFilter().getInitParam().setParamName(textContent);
				break;
			case PARAM_VALUE:
				webApp.getLastFilter().getInitParam().setParamValue(textContent);
				break;
			case LISTENER:
				if(webApp.getListenerList() == null){
					webApp.setListenerList(new ArrayList<Listener>());
				}
				webApp.addListener(new Listener());
				break;
			case LISTENER_CLASS:
				webApp.getLastListener().setListnerClass(textContent);
				break;
			case SERVLET:
				if(webApp.getServletList() == null){
					webApp.setServletList(new ArrayList<Servlet>());
				}
				webApp.addServlet(new Servlet());
				this.previousElement = this.currentElement;
				break;
			case SERVLET_NAME:
				switch (previousElement) {
				case "servlet":
					webApp.getLastServlet().setServletName(textContent);
					break;
				
				case "servlet-mapping":
					webApp.getLastServletMapping().setServletName(textContent);
					break;
				}
				break;
			case SERVLET_CLASS:
				webApp.getLastServlet().setServletClass(textContent);
				break;
			case SERVLET_MAPPING:
				if(webApp.getServletMappingList() == null){
					webApp.setServletMappingList(new ArrayList<ServletMapping>());
				}
				webApp.addServletMapping(new ServletMapping());
				this.previousElement = this.currentElement;
				break;
			case URL_PATTERN:
				switch (previousElement) {
				case "filter-mapping":
					webApp.getLastFilterMapping().setUrlPattern(textContent);
					break;
				case "servlet-mapping":
					webApp.getLastServletMapping().setUrlPattern(textContent);
					break;
				}
				break;
			case ERROR_PAGE:
				if(webApp.getErrorPageList() == null){
					webApp.setErrorPageList(new ArrayList<ErrorPage>());
				}
				webApp.addErrorPage(new ErrorPage());
				break;
			case EXCEPTION_TYPE:
				webApp.getLastErrorPage().setExceptionType(textContent);
				break;
			case LOCATION:
				webApp.getLastErrorPage().setLocation(textContent);
				break;
			case ERROR_CODE:
				webApp.getLastErrorPage().setErrorCode(textContent);
				break;
			default:
				break;
			}
		}
	}
	
	private void endElement(EndElement endElement, WebApp webApp) {
		this.currentElement = "";
	}
	
	@SuppressWarnings("rawtypes")
	private void setAttribute(StartElement startElement, WebApp webApp){
		Iterator iterator = startElement.getAttributes();
		String attributeValue;
		String attributeName;
		
		while(iterator.hasNext()){
			Attribute attribute = (javax.xml.stream.events.Attribute) (iterator.next());
			attributeName = attribute.getName().toString();
			attributeValue = attribute.getValue();
			
			switch (attributeName) {
			case "id":
				webApp.setId(attributeValue);
				break;
			case "version":
				webApp.setVersion(attributeValue);
				break;
			}
		}
	}

}
