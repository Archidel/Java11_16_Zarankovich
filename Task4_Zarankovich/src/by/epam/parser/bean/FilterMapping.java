package by.epam.parser.bean;

import java.io.Serializable;

public class FilterMapping implements Serializable{
	private static final long serialVersionUID = 1L;

	private String filterName;
	private String urlPattern;
	private String dispatcher;
	
	public FilterMapping() {}
	
	public String getFilterName() {
		return filterName;
	}
	
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	
	public String getUrlPattern() {
		return urlPattern;
	}
	
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	
	public String getDispatcher() {
		return dispatcher;
	}
	
	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public String toString() {
		return "FilterMapping [filterName=" + filterName + ", urlPattern=" + urlPattern + ", dispatcher=" + dispatcher
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dispatcher == null) ? 0 : dispatcher.hashCode());
		result = prime * result + ((filterName == null) ? 0 : filterName.hashCode());
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
		
		FilterMapping filterMapping = (FilterMapping) obj;
		if (dispatcher == null) {
			if (filterMapping.dispatcher != null){
				return false;
			}
		} else if (!dispatcher.equals(filterMapping.dispatcher)){
			return false;
		}
		if (filterName == null) {
			if (filterMapping.filterName != null){
				return false;
			}
		} else if (!filterName.equals(filterMapping.filterName)){
			return false;
		}
		if (urlPattern == null) {
			if (filterMapping.urlPattern != null){
				return false;
			}
		} else if (!urlPattern.equals(filterMapping.urlPattern)){
			return false;
		}
		
		return true;
	}
	
}
