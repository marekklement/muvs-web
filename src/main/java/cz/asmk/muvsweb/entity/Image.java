package cz.asmk.muvsweb.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import cz.asmk.muvsweb.util.LengthUtil;


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

	private static final String TITLE_IS_NULL_OR_EMPTY = "Title is null or empty!";
	private static final String URL_IS_NULL_OR_EMPTY = "URL is null or empty!";

	public Image() {
	}

	public Image(String title, String url, String urlThumb, String descriptionCz, String descriptionEn, long ordering,
				 Article article) {
		if(!StringUtils.isNotBlank(title)) throw new IllegalArgumentException(TITLE_IS_NULL_OR_EMPTY);
		if(!LengthUtil.checkLength(title, LengthUtil.MINIMAL_TITLE_LENGTH, LengthUtil.MAXIMAL_TITLE_LENGTH)) throw new IllegalArgumentException(LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
		if(!StringUtils.isNotBlank(url)) throw new IllegalArgumentException(URL_IS_NULL_OR_EMPTY);
		this.title = title;
		this.url = url;
		this.urlThumb = urlThumb;
		this.descriptionCz = descriptionCz;
		this.descriptionEn = descriptionEn;
		this.ordering = ordering;
		this.article = article;
	}

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
		if(!StringUtils.isNotBlank(url)) throw new IllegalArgumentException(URL_IS_NULL_OR_EMPTY);
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
		if(!StringUtils.isNotBlank(title)) throw new IllegalArgumentException(TITLE_IS_NULL_OR_EMPTY);
		if(!LengthUtil.checkLength(title, LengthUtil.MINIMAL_TITLE_LENGTH, LengthUtil.MAXIMAL_TITLE_LENGTH)) throw new IllegalArgumentException(LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Image image = (Image) o;
		return id == image.id &&
				ordering == image.ordering &&
				Objects.equals(title, image.title) &&
				Objects.equals(url, image.url) &&
				Objects.equals(urlThumb, image.urlThumb) &&
				Objects.equals(descriptionCz, image.descriptionCz) &&
				Objects.equals(descriptionEn, image.descriptionEn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, url, urlThumb, descriptionCz, descriptionEn, ordering);
	}
}
