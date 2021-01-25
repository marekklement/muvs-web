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
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private long id;
	@NonNull
	@Column(unique=true)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String length;
	private String description;

	public Event() {
	}

	public Event(String name, Date created, Date date, String length, String description) {
		if(!StringUtils.isNotBlank(name)) throw new IllegalArgumentException("Name should not be null!");
		if(!LengthUtil.checkLength(name,2,40)) throw new IllegalArgumentException("Name is too long");
		DateUtil.validationOfDate(created);
		DateUtil.validationOfDate(date);
		this.name = name;
		this.created = created;
		this.date = date;
		this.length = length;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(!StringUtils.isNotBlank(name)) throw new IllegalArgumentException("Name should not be null!");
		if(!LengthUtil.checkLength(name,2,40)) throw new IllegalArgumentException("Name is too long");
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		DateUtil.validationOfDate(created);
		this.created = created;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		DateUtil.validationOfDate(date);
		this.date = date;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
