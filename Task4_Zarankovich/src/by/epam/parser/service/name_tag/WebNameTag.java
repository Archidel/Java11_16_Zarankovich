package by.epam.parser.service.name_tag;

public enum WebNameTag {
	WEB_APP, DISPLAY_NAME, WELCOME_FILE_LIST, WELCOME_FILE, FILTER, FILTER_MAPPING, LISTENER, SERVLET, SERVLET_MAPPING, ERROR_PAGE, FILTER_NAME, FILTER_CLASS, INIT_PARAM, PARAM_NAME, PARAM_VALUE, URL_PATTERN, DISPATCHER, LISTENER_CLASS, SERVLET_NAME, SERVLET_CLASS, ERROR_CODE, LOCATION, EXCEPTION_TYPE;

	public static WebNameTag getNameTag(String element){
		switch (element) {
		case "web-app":
			return WEB_APP;
		case "display-name":
			return DISPLAY_NAME;
		case "welcome-file-list":
			return WELCOME_FILE_LIST;
		case"welcome-file":
			return WELCOME_FILE;
		case "filter":
			return FILTER;
		case "filter-mapping":
			return FILTER_MAPPING;
		case "listener":
			return LISTENER;
		case "servlet":
			return SERVLET;
		case "servlet-mapping":
			return SERVLET_MAPPING;
		case "error-page":
			return ERROR_PAGE;
		case "filter-name":
			return FILTER_NAME;
		case "filter-class":
			return FILTER_CLASS;
		case "init-param":
			return INIT_PARAM;
		case "param-name":
			return PARAM_NAME;
		case "param-value":
			return PARAM_VALUE;
		case "url-pattern":
			return URL_PATTERN;
		case "dispatcher":
			return DISPATCHER;
		case "listener-class":
			return LISTENER_CLASS;
		case "servlet-name":
			return SERVLET_NAME;
		case "servlet-class":
			return SERVLET_CLASS;
		case "error-code":
			return ERROR_CODE;
		case "location":
			return LOCATION;
		default:
			throw new EnumConstantNotPresentException(WebNameTag.class, element);
		}
	}

}
