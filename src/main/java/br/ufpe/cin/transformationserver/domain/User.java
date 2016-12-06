package br.ufpe.cin.transformationserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Audited
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"email"})})
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotBlank(message= "{User.name.NotBlank}")
	private String name;
	private String email;
	private String password;
	@Transient
	private String confirmPassword;

	public User(){}

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
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
			this.password = DigestUtils.md5Hex(password);
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = DigestUtils.md5Hex(confirmPassword);
	}

}
