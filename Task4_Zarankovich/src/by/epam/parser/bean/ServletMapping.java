package by.epam.parser.bean;

import java.io.Serializable;

public class ServletMapping implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String servletName;
	private String urlPattern;
	
	public ServletMapping() {}
	
	public String getServletName() {
		return servletName;
	}
	
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	
	public String getUrlPattern() {
		return urlPattern;
	}
	
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	@Override
	public String toString() {
		return "ServletMapping [servletName=" + servletName + ", urlPattern=" + urlPattern + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((servletName == null) ? 0 : servletName.hashCode());
		result = prime * result + ((urlPattern == null) ? 0 : urlPattern.hashCode());
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
		
		ServletMapping servletMapping = (ServletMapping) obj;
		
		if (servletName == null) {
			if (servletMapping.servletName != null){
				return false;
			}
		} else if (!servletName.equals(servletMapping.servletName)){
			return false;
		}
		
		if (urlPattern == null) {
			if (servletMapping.urlPattern != null){
				return false;
			}
		} else if (!urlPattern.equals(servletMapping.urlPattern)){
			return false;
		}
		
		return true;
	}	
	
}
