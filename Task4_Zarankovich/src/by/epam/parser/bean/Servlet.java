package by.epam.parser.bean;

import java.io.Serializable;

public class Servlet implements Serializable{
	private static final long serialVersionUID = 1L;

	private String servletName;
	private String servletClass;
	
	public Servlet() {}
	
	public String getServletName() {
		return servletName;
	}
	
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	
	public String getServletClass() {
		return servletClass;
	}
	
	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((servletClass == null) ? 0 : servletClass.hashCode());
		result = prime * result + ((servletName == null) ? 0 : servletName.hashCode());
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
		
		Servlet servlet = (Servlet) obj;
		
		if (servletClass == null) {
			if (servlet.servletClass != null){
				return false;
			}
		} else if (!servletClass.equals(servlet.servletClass)){
			return false;
		}
		
		if (servletName == null) {
			if (servlet.servletName != null){
				return false;
			}
		} else if (!servletName.equals(servlet.servletName)){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Servlet [servletName=" + servletName + ", servletClass=" + servletClass + "]";
	}
	
}
