package cz.asmk.muvsweb.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;
import org.springframework.lang.NonNull;

import cz.asmk.muvsweb.util.DocumentUtil;
import cz.asmk.muvsweb.util.LengthUtil;

@Entity
@Table(
		name = "document",
		uniqueConstraints= {
			@UniqueConstraint(columnNames = {"name", "location"})
})
public class Document {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private long id;
	@NonNull
	private String name;
	@NonNull
	private String location;
	private String extention;
	private String description;

	private static final String NAME_IS_NULL_OR_EMPTY = "Name is null or empty!";
	private static final String LOCATION_IS_NULL_OR_EMPTY = "Location is null or empty!";
	private static final String EXTENSION_IS_NULL_OR_EMPTY = "Extension is null or empty!";
	private static final String FILENAME_LENGTH_WRONG = "Length of filename is wrong!";

	public Document() {
	}

	public Document(String name, String location, String extention, String description) {
		if(!StringUtils.isNotBlank(name)) throw new IllegalArgumentException(NAME_IS_NULL_OR_EMPTY);
		if(!LengthUtil.checkLength(name, LengthUtil.MINIMAL_FILENAME_LENGTH, LengthUtil.MAXIMAL_FILENAME_LENGTH))
			throw new IllegalArgumentException(FILENAME_LENGTH_WRONG);
		if(!StringUtils.isNotBlank(location)) throw new IllegalArgumentException(LOCATION_IS_NULL_OR_EMPTY);
		if(!StringUtils.isNotBlank(extention)) throw new IllegalArgumentException(EXTENSION_IS_NULL_OR_EMPTY);
		DocumentUtil.checkFileExt(extention);
		this.name = name;
		this.location = location;
		this.extention = extention;
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
		if(!StringUtils.isNotBlank(name)) throw new IllegalArgumentException(NAME_IS_NULL_OR_EMPTY);
		if(!LengthUtil.checkLength(name, LengthUtil.MINIMAL_FILENAME_LENGTH, LengthUtil.MAXIMAL_FILENAME_LENGTH))
			throw new IllegalArgumentException(FILENAME_LENGTH_WRONG);
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if(!StringUtils.isNotBlank(location)) throw new IllegalArgumentException(LOCATION_IS_NULL_OR_EMPTY);
		this.location = location;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		if(!StringUtils.isNotBlank(extention)) throw new IllegalArgumentException(EXTENSION_IS_NULL_OR_EMPTY);
		DocumentUtil.checkFileExt(extention);
		this.extention = extention;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Document document = (Document) o;
		return id == document.id &&
				Objects.equals(name, document.name) &&
				Objects.equals(location, document.location) &&
				Objects.equals(extention, document.extention) &&
				Objects.equals(description, document.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, location, extention, description);
	}
}
