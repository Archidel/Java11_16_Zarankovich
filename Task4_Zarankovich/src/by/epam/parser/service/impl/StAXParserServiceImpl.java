package by.epam.parser.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
			setAttribute(startElement);
		}
	}

	private void characters(Characters characters, WebApp webApp) {
		String textContent = characters.getData().trim();
		
		if(!currentElement.isEmpty()){
			WebNameTag tagName = WebNameTag.valueOf(this.currentElement.toUpperCase().replace("-", "_"));

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
				webApp.addFilter(new Filter());
				this.previousElement = this.currentElement;
				break;
			case FILTER_MAPPING:
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
				switch (previousElement) {
				case "filter":
					//баг 
					webApp.getLastFilter().setInitParamList(new ArrayList<InitParam>());
					webApp.getLastFilter().addInitParam(new InitParam());
					break;
				case "servlet":
					webApp.getLastServlet().setInitParamList(new ArrayList<InitParam>());
					webApp.getLastServlet().addInitParam(new InitParam());
					break;
				default:
					break;
				}
				break;
			case PARAM_NAME:
				
				///initParam.setParamName(textContent);
				break;
			
			case PARAM_VALUE:
				
				//initParam.setParamValue(textContent);
				break;
			
			case LISTENER:
				if(webApp.getListenerList() == null){
					webApp.setListenerList(new ArrayList<Listener>());
					webApp.addListener(new Listener());
				}
				break;
			case LISTENER_CLASS:
				webApp.getLastListener().setListnerClass(textContent);
				break;
			case SERVLET:
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
	
	private void endElement(EndElement endElement) {
		QName qName = endElement.getName();
		String nameElement = qName.toString();
		
		if(!nameElement.isEmpty()){
			WebNameTag tagName = WebNameTag.valueOf(nameElement.toUpperCase().replace("-", "_"));
			switch (tagName) {
			
			case FILTER:
				
				if(webApp.getFilterList() == null){
					List<Filter> filterList = new ArrayList<Filter>();
					webApp.setFilterList(filterList);
				}
				
				webApp.addFilter(filter);
				break;
			
			case FILTER_MAPPING:
				
				if(webApp.getFilterMappingList() == null){
					List<FilterMapping> filterMappingList = new ArrayList<FilterMapping>();
					webApp.setFilterMappingList(filterMappingList);
				}
				
				webApp.addFilterMapping(filterMapping);
				break;
			
			case SERVLET:
				
				if(webApp.getServletList() == null){
					List<Servlet> servletList = new ArrayList<Servlet>();
					webApp.setServletList(servletList);
				}
				
				webApp.addServlet(servlet);
				break;
		
			case SERVLET_MAPPING:
				if(webApp.getServletMappingList() == null){
					List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();
					webApp.setServletMappingList(servletMappingList);
				}
				webApp.addServletMapping(servletMapping);
				break;

			case ERROR_PAGE:
				
				if(webApp.getErrorPageList() == null){
					List<ErrorPage> errorPageList = new ArrayList<ErrorPage>();
					webApp.setErrorPageList(errorPageList);
				}
				
				webApp.addErrorPage(errorPage);
				break;

			case INIT_PARAM:
				
				switch (previousElement) {
				
				case "servlet":
					
					if(servlet.getInitParamList() == null){
						List<InitParam> initParamList = new ArrayList<InitParam>();
						servlet.setInitParamList(initParamList);
					}
				
					servlet.addInitParam(initParam);
					break;
				
				case "filter":
				
					if(filter.getInitParamList() == null){
						List<InitParam> listInitParam = new ArrayList<InitParam>();
						filter.setInitParamList(listInitParam);
					}
					
					filter.addInitParam(initParam);
					break;
				}
				
				break;
			
			default:
				break;
			}
		}
		this.currentElement = "";
	}
	
	private void setAttribute(StartElement startElement){
		@SuppressWarnings("rawtypes")
		Iterator iterator = startElement.getAttributes();
		String attributeValue;
		String attributeName;
		
		while(iterator.hasNext()){
			Attribute attribute = (javax.xml.stream.events.Attribute) (iterator.next());
			attributeName = attribute.getName().toString();
			attributeValue = attribute.getValue();
			
			switch (attributeName) {
			case "id":
				this.webApp.setId(attributeValue);
				break;
			case "version":
				this.webApp.setVersion(attributeValue);
				break;
			}
		}
	}

}
