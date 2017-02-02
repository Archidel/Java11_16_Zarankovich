package by.epam.analyzer.bean;

public class Element {
	private String nameElement;
	private String typeElement;
	
	public Element() {}

	public String getNameElement() {
		return nameElement;
	}

	public void setNameElement(String nameelement) {
		this.nameElement = nameelement;
	}

	public String getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(String typeElement) {
		this.typeElement = typeElement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameElement == null) ? 0 : nameElement.hashCode());
		result = prime * result + ((typeElement == null) ? 0 : typeElement.hashCode());
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
		
		Element element = (Element) obj;
		
		if (nameElement == null) {
			if (element.nameElement != null){
				return false;
			}
		} else if (!nameElement.equals(element.nameElement)){
			return false;
		}
		
		if (typeElement == null) {
			if (element.typeElement != null){
				return false;
			}
		} else if (!typeElement.equals(element.typeElement)){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Element [nameElement=" + nameElement + ", typeElement=" + typeElement + "]";
	}
	
}

