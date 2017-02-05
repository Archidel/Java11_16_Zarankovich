package by.epam.parser.bean;

import java.io.Serializable;

public class Response implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String responseMessage;
	private String errorMessage;
	private boolean errorStatus;
	private WebApp webApp;
	
	public Response() {}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public boolean isErrorStatus() {
		return errorStatus;
	}
	
	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}
	
	public WebApp getWebApp() {
		return webApp;
	}
	
	public void setWebApp(WebApp webApp) {
		this.webApp = webApp;
	}
	
}
