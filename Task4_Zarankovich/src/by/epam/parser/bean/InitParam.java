package by.epam.parser.bean;

import java.io.Serializable;

public class InitParam implements Serializable{
	private static final long serialVersionUID = 1L;

	private String paramName;
	private String paramValue;
	
	public InitParam() {}
	
	public String getParamName() {
		return paramName;
	}
	
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public String getParamValue() {
		return paramValue;
	}
	
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public String toString() {
		return "InitParam [paramName=" + paramName + ", paramValue=" + paramValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paramName == null) ? 0 : paramName.hashCode());
		result = prime * result + ((paramValue == null) ? 0 : paramValue.hashCode());
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
		
		InitParam initParam = (InitParam) obj;
		
		if (paramName == null) {
			if (initParam.paramName != null){
				return false;
			}
		} else if (!paramName.equals(initParam.paramName)){
			return false;
		}
		
		if (paramValue == null) {
			if (initParam.paramValue != null){
				return false;
			}
		} else if (!paramValue.equals(initParam.paramValue)){
			return false;
		}
		
		return true;
	}
		
}
