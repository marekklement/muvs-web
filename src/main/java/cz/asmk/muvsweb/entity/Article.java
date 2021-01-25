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
import cz.asmk.muvsweb.util.LengthUtil;

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
	@JoinColumn(name = "muvsuser_id")
	private MUVSUser muvsUser;

	private static final String AUTHOR_NULL_EXCEPTION = "Article should have author!";

	public Article(){

	}

	public Article(String perexCz, String titleCz, String perexEn, String titleEn, String textCz, String textEn,
				   List<Image> images, Category category, MUVSUser muvsUser) {
		LengthUtil.checkIfSomeLocationSet(titleCz, titleEn);
		LengthUtil.checkTitleLength(titleCz, titleEn);
		LengthUtil.checkTextLength(textCz, textEn);
		if(muvsUser == null) throw new IllegalArgumentException(AUTHOR_NULL_EXCEPTION);
		this.perexCz = perexCz;
		this.titleCz = titleCz;
		this.perexEn = perexEn;
		this.titleEn = titleEn;
		this.textCz = textCz;
		this.textEn = textEn;
		this.images = images;
		this.category = category;
		this.muvsUser = muvsUser;
	}

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
		boolean check = LengthUtil.checkLength(titleEn, LengthUtil.MINIMAL_TITLE_LENGTH,
				LengthUtil.MAXIMAL_TITLE_LENGTH);
		if(!check) throw new IllegalArgumentException(LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
		this.titleEn = titleEn;
	}

	public String getTextEn() {
		return textEn;
	}

	public void setTextEn(String textEn) {
		boolean check = LengthUtil.checkLength(textEn, LengthUtil.MINIMAL_TEXT_LENGTH,
				LengthUtil.MAXIMAL_TEXT_LENGTH);
		if(!check) throw new IllegalArgumentException(LengthUtil.ARTICLE_TEXT_WRONG_LENGTH);
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
		boolean check = LengthUtil.checkLength(titleCz, LengthUtil.MINIMAL_TITLE_LENGTH,
				LengthUtil.MAXIMAL_TITLE_LENGTH);
		if(!check) throw new IllegalArgumentException(LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
		this.titleCz = titleCz;
	}

	public String getTextCz() {
		return textCz;
	}

	public void setTextCz(String textCz) {
		boolean check = LengthUtil.checkLength(textCz, LengthUtil.MINIMAL_TEXT_LENGTH,
				LengthUtil.MAXIMAL_TEXT_LENGTH);
		if(!check) throw new IllegalArgumentException(LengthUtil.ARTICLE_TEXT_WRONG_LENGTH);
		this.textCz = textCz;
	}

	public MUVSUser getMUVSUser() {
		return muvsUser;
	}

	public void setMUVSUser(MUVSUser muvsUser) {
		if(muvsUser == null) throw new IllegalArgumentException(AUTHOR_NULL_EXCEPTION);
		this.muvsUser = muvsUser;
	}
}