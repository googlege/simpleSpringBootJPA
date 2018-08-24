package de.homedev.springboot.jpa.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Access(AccessType.FIELD)
@Table(name = UserRightEntity.TABLE_NAME)
public class UserRightEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "app_user_right";

    @Id
    @TableGenerator(name = TABLE_NAME, table = "bt_sequences", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "USER_RIGHT", nullable = false)
    private int userRight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID_FK", unique = false, nullable = false, updatable = false)
    private UserEntity userEntity;

    public UserRightEntity() {
        super();
    }

    public UserRightEntity(int userRight) {
        super();
        this.userRight = userRight;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public int getUserRight() {
        return userRight;
    }

    public void setUserRight(int userRight) {
        this.userRight = userRight;
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

}
