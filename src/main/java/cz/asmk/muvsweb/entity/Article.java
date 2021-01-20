package cz.asmk.muvsweb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private long id;
	private String perexCz;
	private String titleCz;
	private String perexEn;
	private String titleEn;

	@Column(length = 65535)
	private String textCz;

	@Column(length = 65535)
	private String textEn;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private List<Image> images;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getPerexEn() {
		return perexEn;
	}

	public void setPerexEn(String perexEn) {
		this.perexEn = perexEn;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getTextEn() {
		return textEn;
	}

	public void setTextEn(String textEn) {
		this.textEn = textEn;
	}

	public String getPerexCz() {
		return perexCz;
	}

	public void setPerexCz(String perexCz) {
		this.perexCz = perexCz;
	}

	public String getTitleCz() {
		return titleCz;
	}

	public void setTitleCz(String titleCz) {
		this.titleCz = titleCz;
	}

	public String getTextCz() {
		return textCz;
	}

	public void setTextCz(String textCz) {
		this.textCz = textCz;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}