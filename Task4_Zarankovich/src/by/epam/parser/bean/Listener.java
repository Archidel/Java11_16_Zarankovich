package by.epam.parser.bean;

import java.io.Serializable;

public class Listener implements Serializable{
	private static final long serialVersionUID = 1L;

	private String listnerClass;

	public Listener() {}
	
	public String getListnerClass() {
		return listnerClass;
	}

	public void setListnerClass(String listnerClass) {
		this.listnerClass = listnerClass;
	}

	@Override
	public String toString() {
		return "Listner [listnerClass=" + listnerClass + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listnerClass == null) ? 0 : listnerClass.hashCode());
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
		
		Listener listener = (Listener) obj;
		
		if (listnerClass == null) {
			if (listener.listnerClass != null){
				return false;
			}
		} else if (!listnerClass.equals(listener.listnerClass)){
			return false;
		}
		
		return true;
	}

}
