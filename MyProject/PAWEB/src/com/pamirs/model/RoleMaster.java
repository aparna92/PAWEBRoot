package com.pamirs.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RoleMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role_master", catalog = "pamirs")
public class RoleMaster implements java.io.Serializable {

	// Fields

	private Integer roleMasterId;
	private String roleName;
	private String roleDes;
	private Set<Users> userses = new HashSet<Users>(0);

	// Constructors

	/** default constructor */
	public RoleMaster() {
	}

	/** full constructor */
	public RoleMaster(String roleName, String roleDes, Set<Users> userses) {
		this.roleName = roleName;
		this.roleDes = roleDes;
		this.userses = userses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_master_id", unique = true, nullable = false)
	public Integer getRoleMasterId() {
		return this.roleMasterId;
	}

	public void setRoleMasterId(Integer roleMasterId) {
		this.roleMasterId = roleMasterId;
	}

	@Column(name = "role_name", length = 45)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_des", length = 45)
	public String getRoleDes() {
		return this.roleDes;
	}

	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleMaster")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}