package by.epam.parser.bean;

import java.io.Serializable;
import java.util.List;

public class Filter implements Serializable{
	private static final long serialVersionUID = 1L;

	private String filterName;
	private String filterClass;
	private List<InitParam> initParamList;
	
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
	
	public List<InitParam> getInitParamList() {
		return initParamList;
	}
	
	public void setInitParamList(List<InitParam> initParamList) {
		this.initParamList = initParamList;
	}

	@Override
	public String toString() {
		return "Filter [filterName=" + filterName + ", filterClass=" + filterClass + ", initParamList=" + initParamList
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filterClass == null) ? 0 : filterClass.hashCode());
		result = prime * result + ((filterName == null) ? 0 : filterName.hashCode());
		result = prime * result + ((initParamList == null) ? 0 : initParamList.hashCode());
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
		
		if (initParamList == null) {
			if (filter.initParamList != null){
				return false;
			}
		} else if (!initParamList.equals(filter.initParamList)){
			return false;
		}
		
		return true;
	}
		
	public int getSizeInitParamList(){
		return initParamList.size();
	}
	
	public void addInitParam(InitParam initParam){
		this.initParamList.add(initParam);
	}

	public InitParam getLastInitParam(){
		InitParam initParam = this.initParamList.get(getSizeInitParamList()-1);
		return initParam;
	}
}
