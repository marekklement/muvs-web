package cz.asmk.muvsweb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import cz.asmk.muvsweb.util.LengthUtil;
import cz.asmk.muvsweb.util.UserUtil;

@Entity
@Table(name = "muvsuser")
public class MUVSUser {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private long id;
	@Column(unique=true)
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@OneToMany(mappedBy = "muvsUser", cascade = CascadeType.ALL)
	private List<Article> articles;

	private static final String USERNAME_IS_NULL_OR_EMPTY = "Username is null or empty!";
	private static final String USERNAME_LENGTH = "Username has wrong length!";

	public MUVSUser() {
	}

	public MUVSUser(String username, String firstName, String lastName, String email, String phoneNumber,
					List<Article> articles) {
		if(!StringUtils.isNotBlank(username)) throw new IllegalArgumentException(USERNAME_IS_NULL_OR_EMPTY);
		if(!LengthUtil.checkLength(username, 5, 31)) throw new IllegalArgumentException(USERNAME_LENGTH);
		if(StringUtils.isNotBlank(email)){
			UserUtil.checkEmail(email);
		}
		if(StringUtils.isNotBlank(phoneNumber)){
			UserUtil.checkPhoneNumber(phoneNumber);
		}
		if(StringUtils.isNotBlank(firstName)){
			UserUtil.validateFirstName(firstName);
		}
		if(StringUtils.isNotBlank(lastName)){
			UserUtil.validateLastName(lastName);
		}
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.articles = articles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(!StringUtils.isNotBlank(username)) throw new IllegalArgumentException(USERNAME_IS_NULL_OR_EMPTY);
		if(!LengthUtil.checkLength(username, 5, 31)) throw new IllegalArgumentException(USERNAME_LENGTH);
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(StringUtils.isNotBlank(firstName)){
			UserUtil.validateFirstName(firstName);
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(StringUtils.isNotBlank(lastName)){
			UserUtil.validateLastName(lastName);
		}
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		UserUtil.checkEmail(email);
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		UserUtil.checkPhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
