package by.epam.parser.bean;

import java.io.Serializable;
import java.util.List;

public class WebApp implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String version;
	private List<String> displayNameList;
	private List<String> welcomeFileList;
	private List<Filter> filterList;
	private List<FilterMapping> filterMappingList;
	private List<Listener> listenerList;
	private List<Servlet> servletList;
	private List<ServletMapping> servletMappingList;
	private List<ErrorPage> errorPageList;
	
	public WebApp() {}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public List<String> getWelcomeFileList() {
		return welcomeFileList;
	}
	
	public void setWelcomeFileList(List<String> welcomeFileList) {
		this.welcomeFileList = welcomeFileList;
	}
	
	public List<Filter> getFilterList() {
		return filterList;
	}
	
	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}
	
	public List<FilterMapping> getFilterMappingList() {
		return filterMappingList;
	}
	
	public void setFilterMappingList(List<FilterMapping> filterMappingList) {
		this.filterMappingList = filterMappingList;
	}
	
	public List<Servlet> getServletList() {
		return servletList;
	}
	
	public void setServletList(List<Servlet> servletList) {
		this.servletList = servletList;
	}
	
	public List<ErrorPage> getErrorPageList() {
		return errorPageList;
	}
	
	public void setErrorPageList(List<ErrorPage> errorPageList) {
		this.errorPageList = errorPageList;
	}

	public List<String> getDisplayNameList() {
		return displayNameList;
	}

	public void setDisplayNameList(List<String> displayNameList) {
		this.displayNameList = displayNameList;
	}

	public List<ServletMapping> getServletMappingList() {
		return servletMappingList;
	}

	public void setServletMappingList(List<ServletMapping> servletMappingList) {
		this.servletMappingList = servletMappingList;
	}

	@Override
	public String toString() {
		return "WebApp [id=" + id + ", version=" + version + ", \n displayNameList=" + displayNameList
				+ ", welcomeFileList=" + welcomeFileList + ",\n filterList=" + filterList + ",\n filterMappingList="
				+ filterMappingList + ",\n listnerList=" + listenerList + ",\n servletList=" + servletList
				+ ",\n servletMappingList=" + servletMappingList + ",\n errorPageList=" + errorPageList + "]";
	}

	public List<Listener> getListenerList() {
		return listenerList;
	}

	public void setListenerList(List<Listener> listenerList) {
		this.listenerList = listenerList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayNameList == null) ? 0 : displayNameList.hashCode());
		result = prime * result + ((errorPageList == null) ? 0 : errorPageList.hashCode());
		result = prime * result + ((filterList == null) ? 0 : filterList.hashCode());
		result = prime * result + ((filterMappingList == null) ? 0 : filterMappingList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listenerList == null) ? 0 : listenerList.hashCode());
		result = prime * result + ((servletList == null) ? 0 : servletList.hashCode());
		result = prime * result + ((servletMappingList == null) ? 0 : servletMappingList.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((welcomeFileList == null) ? 0 : welcomeFileList.hashCode());
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
		
		WebApp webApp = (WebApp) obj;
		
		if (displayNameList == null) {
			if (webApp.displayNameList != null){
				return false;
			}
		} else if (!displayNameList.equals(webApp.displayNameList)){
			return false;
		}
		
		if (errorPageList == null) {
			if (webApp.errorPageList != null){
				return false;
			}
		} else if (!errorPageList.equals(webApp.errorPageList)){
			return false;
		}
		
		if (filterList == null) {
			if (webApp.filterList != null){
				return false;
			}
		} else if (!filterList.equals(webApp.filterList)){
			return false;
		}
		
		if (filterMappingList == null) {
			if (webApp.filterMappingList != null){
				return false;
			}
		} else if (!filterMappingList.equals(webApp.filterMappingList)){
			return false;
		}
		
		if (id == null) {
			if (webApp.id != null){
				return false;
			}
		} else if (!id.equals(webApp.id)){
			return false;
		}
		
		if (listenerList == null) {
			if (webApp.listenerList != null){
				return false;
			}
		} else if (!listenerList.equals(webApp.listenerList)){
			return false;
		}
		
		if (servletList == null) {
			if (webApp.servletList != null){
				return false;
			}
		} else if (!servletList.equals(webApp.servletList)){
			return false;
		}
		
		if (servletMappingList == null) {
			if (webApp.servletMappingList != null){
				return false;
			}
		} else if (!servletMappingList.equals(webApp.servletMappingList)){
			return false;
		}
		
		if (version == null) {
			if (webApp.version != null){
				return false;
			}
		} else if (!version.equals(webApp.version)){
			return false;
		}
		
		if (welcomeFileList == null) {
			if (webApp.welcomeFileList != null){
				return false;
			}
		} else if (!welcomeFileList.equals(webApp.welcomeFileList)){
			return false;
		}
		
		return true;
	}

	public void addDisplayName(String displayName){
		this.displayNameList.add(displayName);
	}
	
	public void addWelcomeFile(String welcomeFile){
		this.welcomeFileList.add(welcomeFile);
	}
	
	public void addFilter(Filter filter){
		this.filterList.add(filter);
	}
	
	public void addFilterMapping(FilterMapping filterMapping){
		this.filterMappingList.add(filterMapping);
	}
	
	public void addListener(Listener listener){
		this.listenerList.add(listener);
	}
	
	public void addServlet(Servlet servlet){
		this.servletList.add(servlet);
	}
	
	public void addServletMapping(ServletMapping servletMapping){
		this.servletMappingList.add(servletMapping);
	}

	public void addErrorPage(ErrorPage errorPage){
			this.errorPageList.add(errorPage);
	}
	
	public int getSizeDisplayNameList(){
		return displayNameList.size();
	}
	
	public int getSizeWelcomeFileList(){
		return welcomeFileList.size();
	}
	
	public int getSizeFilterList(){
		return filterList.size();
	}
	
	public int getSizeFilterMappingList(){
		return filterMappingList.size();
	}
	
	public int getSizeListenerList(){
		return listenerList.size();
	}
	
	public int getSizeServletList(){
		return servletList.size();
	}
	
	public int getSizeServletMappingList(){
		return servletMappingList.size();
	}
	
	public int getSizeErrorPageList(){
		return errorPageList.size();
	}

	public Filter getLastFilter(){
		Filter lastFilter = this.filterList.get(getSizeFilterList()-1);
		return  lastFilter;
	}

	public FilterMapping getLastFilterMapping(){
		FilterMapping lastFilterMapping = this.filterMappingList.get(getSizeFilterMappingList()-1);
		return  lastFilterMapping;
	}

	public Listener getLastListener(){
		Listener lastListener = this.listenerList.get(getSizeListenerList()-1);
		return  lastListener;
	}

	public Servlet getLastServlet(){
		Servlet lastServlet = this.servletList.get(getSizeServletList()-1);
		return  lastServlet;
	}

	public ServletMapping getLastServletMapping(){
		ServletMapping lastServletMapping = this.servletMappingList.get(getSizeServletMappingList()-1);
		return  lastServletMapping;
	}

	public ErrorPage getLastErrorPage(){
		ErrorPage errorPage = this.errorPageList.get(getSizeErrorPageList()-1);
		return errorPage;
	}
}

