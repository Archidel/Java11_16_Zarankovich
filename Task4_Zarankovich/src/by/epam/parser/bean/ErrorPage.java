package by.epam.parser.bean;

import java.io.Serializable;

public class ErrorPage implements Serializable{
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String location;
	private String exceptionType;
	
	public ErrorPage() {}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	@Override
	public String toString() {
		return "ErrorPage [errorCode=" + errorCode + ", location=" + location + ", exceptionType=" + exceptionType
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result + ((exceptionType == null) ? 0 : exceptionType.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj){
			return true;		
		}
		
		if (obj == null){
			return false;
		}
		
		if (getClass() != obj.getClass()){
			return false;
		}
		
		ErrorPage errorPage = (ErrorPage) obj;
	
		if (errorCode == null) {
			if (errorPage.errorCode != null){
				return false;
			}
		} else if (!errorCode.equals(errorPage.errorCode)){
			return false;
		}
		
		if (exceptionType == null) {
			if (errorPage.exceptionType != null){
				return false;
			}
		} else if (!exceptionType.equals(errorPage.exceptionType)){
			return false;
		}
		
		if (location == null) {
			if (errorPage.location != null){
				return false;
			}
		} else if (!location.equals(errorPage.location)){
			return false;
		}
		
		return true;
	}
	
}
