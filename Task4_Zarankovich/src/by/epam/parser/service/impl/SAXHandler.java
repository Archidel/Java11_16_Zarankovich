package by.epam.parser.service.impl;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.parser.bean.ErrorPage;
import by.epam.parser.bean.Filter;
import by.epam.parser.bean.FilterMapping;
import by.epam.parser.bean.InitParam;
import by.epam.parser.bean.Listener;
import by.epam.parser.bean.Servlet;
import by.epam.parser.bean.ServletMapping;
import by.epam.parser.bean.WebApp;
import by.epam.parser.service.name_tag.WebNameTag;

public class SAXHandler extends DefaultHandler{
	
	private WebApp webApp; // жестоким способом убиваем многопоточность
	private String currentElement;
	private String previousElement;
	// ну и что делать с currentElement, previous????????????
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		this.currentElement = qName;
		if(qName.equals("web-app")){
			webApp = new WebApp();
			webApp.setId(attributes.getValue(0));			
			webApp.setVersion(attributes.getValue(1));
		}	
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String textContent = new String(ch, start, length);		
		
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
				webApp.setFilterMappingList(new ArrayList<FilterMapping>());
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
				break;
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
				webApp.setErrorPageList(new ArrayList<ErrorPage>());
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
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.currentElement = "";
	}

	public WebApp getWebApp() {
		return webApp;
	}

}
