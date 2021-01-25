package cz.asmk.muvsweb.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cz.asmk.muvsweb.util.LengthUtil;

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

	public Category() {
	}

	public Category(String titleCz, String perexCz, String titleEn, String perexEn, List<Article> articles) {
		LengthUtil.checkIfSomeLocationSet(titleCz, titleEn);
		LengthUtil.checkTitleLength(titleCz, titleEn);
		LengthUtil.checkIfSomeLocationSet(perexCz, perexEn);
		LengthUtil.checkPerexLength(perexCz,perexEn);
		this.titleCz = titleCz;
		this.perexCz = perexCz;
		this.titleEn = titleEn;
		this.perexEn = perexEn;
		this.articles = articles;
	}

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

		boolean check = LengthUtil.checkLength(titleCz, LengthUtil.MINIMAL_TITLE_LENGTH,
				LengthUtil.MAXIMAL_TITLE_LENGTH);
		if(!check) throw new IllegalArgumentException(LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
		this.titleCz = titleCz;
	}

	public String getPerexCz() {
		return perexCz;
	}

	public void setPerexCz(String perexCz) {

		boolean check = LengthUtil.checkLength(perexCz, LengthUtil.MINIMAL_PEREX_LENGTH,
				LengthUtil.MAXIMAL_PEREX_LENGTH);
		if(!check) throw new IllegalArgumentException(LengthUtil.ARTICLE_PEREX_WRONG_LENGTH);
		this.perexCz = perexCz;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {

		boolean check = LengthUtil.checkLength(titleEn, LengthUtil.MINIMAL_TITLE_LENGTH,
				LengthUtil.MAXIMAL_TITLE_LENGTH);
		if(!check) throw new IllegalArgumentException(LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
		this.titleEn = titleEn;
	}

	public String getPerexEn() {
		return perexEn;
	}

	public void setPerexEn(String perexEn) {
		boolean check = LengthUtil.checkLength(perexEn, LengthUtil.MINIMAL_PEREX_LENGTH,
				LengthUtil.MAXIMAL_PEREX_LENGTH);
		if(!check) throw new IllegalArgumentException(LengthUtil.ARTICLE_PEREX_WRONG_LENGTH);
		this.perexEn = perexEn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		return id == category.id &&
				Objects.equals(titleCz, category.titleCz) &&
				Objects.equals(perexCz, category.perexCz) &&
				Objects.equals(titleEn, category.titleEn) &&
				Objects.equals(perexEn, category.perexEn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, titleCz, perexCz, titleEn, perexEn);
	}
}
