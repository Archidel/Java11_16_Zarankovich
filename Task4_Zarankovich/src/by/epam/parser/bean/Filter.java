package by.epam.parser.bean;

import java.io.Serializable;

public class Filter implements Serializable{
	private static final long serialVersionUID = 1L;

	private String filterName;
	private String filterClass;
	private InitParam initParam;
	
	public Filter() {}
	
	public String getFilterName() {
		return filterName;
	}
	
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	
	public String getFilterClass() {
		return filterClass;
	}
	
	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
	}
	
	public InitParam getInitParam() {
		return initParam;
	}

	public void setInitParam(InitParam initParam) {
		this.initParam = initParam;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filterClass == null) ? 0 : filterClass.hashCode());
		result = prime * result + ((filterName == null) ? 0 : filterName.hashCode());
		result = prime * result + ((initParam == null) ? 0 : initParam.hashCode());
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
		
		Filter filter = (Filter) obj;
		
		if (filterClass == null) {
			if (filter.filterClass != null){
				return false;
			}
		} else if (!filterClass.equals(filter.filterClass)){
			return false;
		}
		
		if (filterName == null) {
			if (filter.filterName != null){
				return false;
			}
		} else if (!filterName.equals(filter.filterName)){
			return false;
		}
		
		if (initParam == null) {
			if (filter.initParam != null){
				return false;
			}
		} else if (!initParam.equals(filter.initParam)){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Filter [filterName=" + filterName + ", filterClass=" + filterClass + ", initParam=" + initParam + "]";
	}
	
}
