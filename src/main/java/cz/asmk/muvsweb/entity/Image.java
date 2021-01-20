package cz.asmk.muvsweb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "img_generator")
	@SequenceGenerator(name="img_generator", sequenceName = "img_seq", initialValue = 15)
	private long id;
	private String title;
	private String url;
	private String urlThumb;
	private String descriptionCz;
	private String descriptionEn;
	private long ordering;

	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (url == null)
			throw new IllegalArgumentException("Image Url is invalid");
		this.url = url;
	}


	public String getUrlThumb() {
		return urlThumb;
	}

	public void setUrlThumb(String urlThumb) {
		if (urlThumb == null)
			throw new IllegalArgumentException("Image UrlThumb is invalid");
		this.urlThumb = urlThumb;
	}


	public long getOrdering() {
		return ordering;
	}

	public void setOrdering(long ordering) {
		if (ordering < 0)
			throw new IllegalArgumentException("Image Ordering index invalid");
		this.ordering = ordering;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		if (article == null)
			throw new IllegalArgumentException("Image Article is null");
		this.article = article;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.length() < 2)
			throw new IllegalArgumentException("Image Title is invalid or too short (minimum 2 letters)");
		if (title.length() > 50)
			throw new IllegalArgumentException("Image Title is too long (maximum 50 letters)");

		this.title = title;
	}

	public String getDescriptionCz() {
		return descriptionCz;
	}

	public void setDescriptionCz(String descriptionCz) {
		if (descriptionCz != null && descriptionCz.length() > 100)
			throw new IllegalArgumentException("Image DescriptionCz is too long (maximum 100 letters)");
		this.descriptionCz = descriptionCz;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
}
