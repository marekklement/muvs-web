package cz.asmk.muvsweb.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.springframework.lang.NonNull;

import cz.asmk.muvsweb.util.DateUtil;
import cz.asmk.muvsweb.util.LengthUtil;

@Entity
@Table(name = "link")
public class Link {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private long id;
	private String url;
	@NonNull
	@Column(unique=true)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public Link() {
	}

	public Link(String url, String name, Date created) {
		if(!StringUtils.isNotBlank(name)) throw new IllegalArgumentException("Name should not be null!");
		if(!LengthUtil.checkLength(name,5,40)) throw new IllegalArgumentException("Name is too long");
		if(!LengthUtil.checkLength(url,2,70)) throw new IllegalArgumentException("Url is too long");
		DateUtil.validationOfDate(created);
		this.url = url;
		this.name = name;
		this.created = created;
	}

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
		if(!LengthUtil.checkLength(url,2,70)) throw new IllegalArgumentException("Url is too long");
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(!StringUtils.isNotBlank(name)) throw new IllegalArgumentException("Name should not be null!");
		if(!LengthUtil.checkLength(name,5,40)) throw new IllegalArgumentException("Name is too long");
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		DateUtil.validationOfDate(created);
		this.created = created;
	}
}
