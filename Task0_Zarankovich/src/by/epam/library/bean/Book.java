package by.epam.library.bean;

public class Book {
	private int id;
	private String brief;
	private String publisheYear;
	private String author;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBRIEF() {
		return brief;
	}

	public void setBRIEF(String bRIEF) {
		brief = bRIEF;
	}

	public String getPublisheYear() {
		return publisheYear;
	}

	public void setPublisheYear(String publisheYear) {
		this.publisheYear = publisheYear;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + id;
		result = prime * result + ((publisheYear == null) ? 0 : publisheYear.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id != other.id)
			return false;
		if (publisheYear == null) {
			if (other.publisheYear != null)
				return false;
		} else if (!publisheYear.equals(other.publisheYear))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", BRIEF=" + brief + ", publisheYear=" + publisheYear + ", author=" + author + "]";
	}
	
	
	
}
