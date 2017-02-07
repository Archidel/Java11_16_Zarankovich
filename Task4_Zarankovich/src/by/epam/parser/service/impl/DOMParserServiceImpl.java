package by.epam.parser.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import by.epam.parser.bean.ErrorPage;
import by.epam.parser.bean.Filter;
import by.epam.parser.bean.FilterMapping;
import by.epam.parser.bean.InitParam;
import by.epam.parser.bean.Listener;
import by.epam.parser.bean.Response;
import by.epam.parser.bean.Servlet;
import by.epam.parser.bean.ServletMapping;
import by.epam.parser.bean.WebApp;
import by.epam.parser.service.DOMParserService;
import by.epam.parser.service.FileParameters;
import by.epam.parser.service.exception.ServiceException;
import by.epam.parser.service.name_tag.WebNameTag;
import by.epam.parser.service.validation.ValidationData;

public class DOMParserServiceImpl implements DOMParserService {

	@Override
	public Response doParsing(String filename) throws ServiceException {

		if(!ValidationData.validationString(filename)){
			throw new ServiceException("Incorrect filename");
		}
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		InputSource inputSource = new InputSource(FileParameters.FILE_PATH + filename);
		DocumentBuilder documentBuilder = null;
		Document document = null;
		
		try {	
			documentBuilder = factory.newDocumentBuilder();
			document = documentBuilder.parse(inputSource);
		} catch (ParserConfigurationException e) {
			throw new ServiceException(e);
		} catch (SAXException e) {
			throw new ServiceException(e);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		
		Response response = new Response();
		response.setResponseMessage("The file has been successfully parsed");
		response.setWebApp(getWebApp(document));
		return response;
	}

	private WebApp getWebApp(Document document){
		WebApp webApp = new WebApp();
		
		Node rootNode = document.getDocumentElement();
		setRootAttribute(rootNode, webApp); // Читаем и записываем атрибуты из тега web-app 
		NodeList childeRootList = rootNode.getChildNodes();
		
		for(int i = 0; i < childeRootList.getLength(); i++){
			Node chileRootNode = childeRootList.item(i);
			
			if(textExist(chileRootNode)){
				
				String nodeName = chileRootNode.getNodeName(); 
				WebNameTag tagName = WebNameTag.valueOf(nodeName.toUpperCase().replace("-", "_"));
				
				switch (tagName) {
				case DISPLAY_NAME:
					webApp.setDisplayNameList(getDisplayNameList(chileRootNode));
					break;
				case WELCOME_FILE_LIST:					
					webApp.setWelcomeFileList(getWelcomeFileList(document));
					break;
				case FILTER:
					webApp.setFilterList(getFilterList(document, nodeName));		
					break;
				case FILTER_MAPPING:
					webApp.setFilterMappingList(getFilterMappingList(document, nodeName));			
					break;
				case LISTENER:
					webApp.setListenerList(getListenerList(document));
					break;
				case SERVLET:
					webApp.setServletList(getServletList(document, nodeName));
					break;
				case SERVLET_MAPPING:
					webApp.setServletMappingList(getServletMappingList(document, nodeName));
					break;
				case ERROR_PAGE:
					webApp.setErrorPageList(getErrorPageList(document, nodeName));			
					break;
				default:
					throw new EnumConstantNotPresentException(WebNameTag.class, nodeName);
				}	
			}
		}
		return webApp;
	}
	
	private List<String> getDisplayNameList(Node node) {
		List<String> displayNameList = new ArrayList<String>();
		displayNameList.add(node.getTextContent());
		return displayNameList;
	}

	private List<ErrorPage> getErrorPageList(Document document, String nodeName){
		List<ErrorPage> errorPageList = new ArrayList<ErrorPage>();
		NodeList errorPageNodeList = document.getElementsByTagName(nodeName);
		
		for(int j = 0; j < errorPageNodeList.getLength(); j++){
			if(isElement(errorPageNodeList.item(j))){
				
				ErrorPage errorPage = new ErrorPage();
				Element errorPageElement = (Element) errorPageNodeList.item(j);
				NodeList childErrorPageNodeList = errorPageElement.getChildNodes();
				
				for(int k = 0; k < childErrorPageNodeList.getLength(); k++){
					if(isElement(childErrorPageNodeList.item(k))){
						
						Element childeErrorPageElement = (Element) childErrorPageNodeList.item(k);
						String childNodeName = childeErrorPageElement.getNodeName();
						WebNameTag childtagName = WebNameTag.valueOf(childNodeName.toUpperCase().replace("-", "_"));
	
						switch (childtagName) {
						case ERROR_CODE:
							errorPage.setErrorCode(childeErrorPageElement.getTextContent());
							break;
						case LOCATION:
							errorPage.setLocation(childeErrorPageElement.getTextContent());
							break;				
						default:
							break;
						}
					}
				}
				errorPageList.add(errorPage);
			}
		}
		return errorPageList;
	}
	
	private List<FilterMapping> getFilterMappingList(Document document, String nodeName){
		List<FilterMapping> filterMappingList = new ArrayList<FilterMapping>();
		NodeList filterMappingNodeList = document.getElementsByTagName(nodeName);
		
		for(int j = 0; j < filterMappingNodeList.getLength(); j++){
			if(isElement(filterMappingNodeList.item(j))){
				
				FilterMapping filterMapping = new FilterMapping();
				Element filterMappingElement = (Element) filterMappingNodeList.item(j);
				NodeList childFilterMappingNodeList = filterMappingElement.getChildNodes();
				
				for(int k = 0; k < childFilterMappingNodeList.getLength(); k++){
					if(isElement(childFilterMappingNodeList.item(k))){
						
						Element childeFilerMappingElement = (Element) childFilterMappingNodeList.item(k);
						String childNodeName = childeFilerMappingElement.getNodeName();
						WebNameTag childtagName = WebNameTag.valueOf(childNodeName.toUpperCase().replace("-", "_"));
	
						switch (childtagName) {
						case FILTER_NAME:
							filterMapping.setFilterName(childeFilerMappingElement.getTextContent());
							break;
						case URL_PATTERN:
							filterMapping.setUrlPattern(childeFilerMappingElement.getTextContent());
							break;
						case DISPATCHER:
							filterMapping.setDispatcher(childeFilerMappingElement.getTextContent());
							break;
						default:
							break;
						}
					}
				}
				filterMappingList.add(filterMapping);
			}
		}
		return filterMappingList;
	}
	
	private List<ServletMapping> getServletMappingList(Document document, String nodeName){
		List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();
		NodeList servletMappingNodeList = document.getElementsByTagName(nodeName);
		
		for(int j = 0; j < servletMappingNodeList.getLength(); j++){
			if(isElement(servletMappingNodeList.item(j))){
				
				ServletMapping servletMapping = new ServletMapping();
				Element servletMappingElement = (Element) servletMappingNodeList.item(j);
				NodeList childServletMappingNodeList = servletMappingElement.getChildNodes();
				
				for(int k = 0; k < childServletMappingNodeList.getLength(); k++){
					if(isElement(childServletMappingNodeList.item(k))){
						
						Element childeServletMappingElement = (Element) childServletMappingNodeList.item(k);
						String childNodeName = childeServletMappingElement.getNodeName();
						WebNameTag childtagName = WebNameTag.valueOf(childNodeName.toUpperCase().replace("-", "_"));
	
						switch (childtagName) {
						case SERVLET_NAME:
							servletMapping.setServletName(childeServletMappingElement.getTextContent());
							break;

						case URL_PATTERN:
							servletMapping.setUrlPattern(childeServletMappingElement.getTextContent());
							break;
							
						default:
							break;
						}
					}
				}
				servletMappingList.add(servletMapping);
			}
		}
		return servletMappingList;
	}
	
	private List<Servlet> getServletList(Document document, String nodeName){
		List<Servlet> servletList = new ArrayList<Servlet>();
		NodeList servletNodeList = document.getElementsByTagName(nodeName);
		
		for(int j = 0; j < servletNodeList.getLength(); j++){
			if(isElement(servletNodeList.item(j))){
				
				Servlet servlet = new Servlet();
				Element servletElement = (Element) servletNodeList.item(j);
				NodeList childerServletNodeList = servletElement.getChildNodes();
				
				for(int k = 0; k < childerServletNodeList.getLength(); k++){
					if(isElement(childerServletNodeList.item(k))){
						
						Element childeServletElement = (Element) childerServletNodeList.item(k);
						String childNodeName = childeServletElement.getNodeName();
						WebNameTag childtagName = WebNameTag.valueOf(childNodeName.toUpperCase().replace("-", "_"));
						
						switch (childtagName) {
						case SERVLET_NAME:
							servlet.setServletName(childeServletElement.getTextContent());
							break;
						case SERVLET_CLASS:
							servlet.setServletClass(childeServletElement.getTextContent());
							break;
						default:
							break;
						}
					}
				}
				servletList.add(servlet);
			}
		}
	
		return servletList;
	}
	
	private List<Listener> getListenerList(Document document){
		List<Listener> listenerList = new ArrayList<Listener>();
		NodeList listenerNodeList = document.getElementsByTagName("listener-class");
		
		for(int j = 0; j < listenerNodeList.getLength(); j++){
			if(isElement(listenerNodeList.item(j))){
				Listener listener = new Listener();
				Element listenerElement = (Element) listenerNodeList.item(j);
				listener.setListnerClass(listenerElement.getTextContent());
				listenerList.add(listener);
			}
		}
		
		return listenerList;
	}
	
	private List<Filter> getFilterList(Document document, String nodeName){
		List<Filter> filterList = new ArrayList<Filter>();
		NodeList filterNodeList = document.getElementsByTagName(nodeName);
		
		for(int j = 0; j < filterNodeList.getLength(); j++){
			if(isElement(filterNodeList.item(j))){
				
				Filter filter = new Filter();
				Element filterElement = (Element) filterNodeList.item(j);
				NodeList childerFilterNodeList = filterElement.getChildNodes();

				for(int k = 0; k < childerFilterNodeList.getLength(); k++){
					if(isElement(childerFilterNodeList.item(k))){
						
						Element childeFilterElement = (Element) childerFilterNodeList.item(k);
						String childNodeName = childeFilterElement.getNodeName();
						WebNameTag childtagName = WebNameTag.valueOf(childNodeName.toUpperCase().replace("-", "_"));
						
						switch (childtagName) {
						case FILTER_NAME:
							filter.setFilterName(childeFilterElement.getTextContent());
							break;
						case FILTER_CLASS:
							filter.setFilterClass(childeFilterElement.getTextContent());
							break;
						case INIT_PARAM:
							filter.setInitParam(getInitParam(childeFilterElement));
							break;			
						default:
							break;
						}
					}
				}
				filterList.add(filter);
			}
		}
		return filterList;
	}
	
	private InitParam getInitParam(Element element) {
		InitParam initParam = new InitParam();
		NodeList initParamNodeList = element.getChildNodes();
		
		for(int c = 0; c < initParamNodeList.getLength(); c++){
			if(isElement(initParamNodeList.item(c))){
		
				Element initParamElement = (Element)initParamNodeList.item(c);
				String initParamNodeName = initParamElement.getNodeName();
				WebNameTag initTagName = WebNameTag.valueOf(initParamNodeName.toUpperCase().replace("-", "_"));
				
				switch (initTagName) {
				case PARAM_NAME:
					initParam.setParamName(initParamElement.getTextContent());
					break;
				case PARAM_VALUE:
					initParam.setParamValue(initParamElement.getTextContent());
					break;
				default:
					break;
				}
			}
		}
		return initParam;
	}

	private List<String> getWelcomeFileList(Document document){
		List<String> welcomFileList = new ArrayList<String>();
		NodeList welcomeFileNodeList = document.getElementsByTagName("welcome-file");
		
		for(int j = 0; j < welcomeFileNodeList.getLength(); j++){	
			if(isElement(welcomeFileNodeList.item(j))){
				
				Element welcomeFileElement = (Element) welcomeFileNodeList.item(j);
				String textContent = welcomeFileElement.getTextContent();
				welcomFileList.add(textContent);
			}
		}
		return welcomFileList;
	}
	
	private boolean textExist(Node node){
		if(node.getNodeType() != Node.TEXT_NODE){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isElement(Node node){
		if(node.getNodeType() == Node.ELEMENT_NODE){
			return true;
		}else{
			return false;
		}
	}
	
	private void setRootAttribute(Node node, WebApp webApp){
		Element rootElement = (Element) node;
		String idWebApp = rootElement.getAttribute("id");
		String versionWebApp = rootElement.getAttribute("version");
		webApp.setId(idWebApp);
		webApp.setVersion(versionWebApp);
	}
	
}
