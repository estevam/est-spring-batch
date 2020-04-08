package ca.est.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cms_user")
public class CmsUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	public CmsUser() {
	}

	public CmsUser(Long id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CmsUser [id=" + id + ", username=" + username + ", email=" + email + "]";
	}
}
