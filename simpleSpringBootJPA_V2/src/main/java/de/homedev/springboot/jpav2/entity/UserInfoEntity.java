package de.homedev.springboot.jpav2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Table(name = UserInfoEntity.TABLE_NAME)
public class UserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "app_user_info";

	@Id
	@TableGenerator(name = TABLE_NAME, table = "bt_sequences", pkColumnName = "sequencename", valueColumnName = "sequencevalue", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "version", nullable = false)
	private Integer version;

	@Column(length = AppConstants.LASTNAME_MAX_LENGTH)
	private String name;

	@Column(length = AppConstants.FIRSTNAME_MAX_LENGTH)
	private String vorname;

	@Column(length = AppConstants.EMAIL_MAX_LENGTH)
	private String email;

	@OneToOne(optional = false, mappedBy = "userInfo")
	private UserEntity userEntity;

	public UserInfoEntity() {
		super();
	}

	public UserInfoEntity(String name, String vorname, String email) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
