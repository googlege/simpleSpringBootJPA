package de.homedev.springboot.jpav2.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Access(AccessType.FIELD)
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "APP_USER";

	@Id
	@TableGenerator(name = TABLE_NAME, table = "BT_SEQUENCES", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@Column(name = "USER_NAME", unique = true, length = AppConstants.USERNAME_MAX_LENGTH, nullable = false)
	private String username;

	@Column(name = "USER_PASSWORD", length = AppConstants.USERPASSWORD_MAX_LENGTH, nullable = false)
	private String password;

	@OneToMany(mappedBy = "userEntity", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<UserRightEntity> userRightsList;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "USERINFO_ID_FK", unique = true, nullable = false, updatable = false)
	private UserInfoEntity userInfo;

	public UserEntity() {
	}

	public UserEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRightEntity> getUserRightsList() {
		return userRightsList;
	}

	public void setUserRightsList(List<UserRightEntity> userRightsList) {
		this.userRightsList = userRightsList;
	}

	public UserInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoEntity userInfo) {
		this.userInfo = userInfo;
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

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", version=" + version + ", username=" + username + ", password=" + password + ", userRightsList=" + userRightsList
				+ ", userInfo=" + userInfo + "]";
	}

}
