package cz.asmk.muvsweb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private long id;
	private String titleCz;
	private String perexCz;
	private String titleEn;
	private String perexEn;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Article> articles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getTitleCz() {
		return titleCz;
	}

	public void setTitleCz(String titleCz) {

		if (titleCz == null || titleCz.length() < 2)
			throw new IllegalArgumentException("Category TitleCz is invalid or too short (minimum 2 letters)");
		if (titleCz.length() > 50)
			throw new IllegalArgumentException("Category TitleCz is too long (maximum 50 letters)");
		this.titleCz = titleCz;
	}

	public String getPerexCz() {
		return perexCz;
	}

	public void setPerexCz(String perexCz) {

		if (perexCz == null || perexCz.length() < 2)
			throw new IllegalArgumentException("Category PerexCz is invalid or too short (minimum 2 letters)");
		if (perexCz.length() > 50)
			throw new IllegalArgumentException("Category PerexCz is too long (maximum 50 letters)");
		this.perexCz = perexCz;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {

		if (titleEn != null && titleEn.length() < 2)
			throw new IllegalArgumentException("Category TitleEn is invalid or too short (minimum 2 letters)");
		if (titleEn != null && titleEn.length() > 50)
			throw new IllegalArgumentException("Category TitleEn is too long (maximum 50 letters)");
		this.titleEn = titleEn;
	}

	public String getPerexEn() {
		return perexEn;
	}

	public void setPerexEn(String perexEn) {
		if (perexEn != null && perexEn.length() < 2)
			throw new IllegalArgumentException("Category PerexEn is invalid or too short (minimum 2 letters)");
		if (perexEn != null && perexEn.length() > 50)
			throw new IllegalArgumentException("Category PerexEn is too long (maximum 50 letters)");
		this.perexEn = perexEn;
	}
}
