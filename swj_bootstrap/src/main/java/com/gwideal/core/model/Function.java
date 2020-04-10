package com.gwideal.core.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.GenericIntEntity;
import com.gwideal.common.entity.SelectTree;

@Entity
@Table(name="SYS_FUNCTION")
public class Function extends GenericIntEntity implements SelectTree,Serializable {

	private static final long serialVersionUID = -5739512476232195372L;
	/**
	 * 权限key,里面存放访问路径,保存在session
	 */
	public static final String RIGHTS_KEY = "_rights_key";
	
	@ManyToOne
	@JoinColumn(name="creator",updatable=false)
	@JsonIgnore
	private User creator;
	private java.lang.String name;
	private java.lang.String url;
	private java.lang.String funcs;
	private java.lang.String description;
	private java.lang.Integer priority;
	@Column(name="IS_MENU")
	private java.lang.Boolean menu;

	@ManyToOne
    @JoinColumn(name = "PARENT")
	@JsonIgnore
	private Function parent;

	@OneToMany(targetEntity = Function.class,mappedBy = "parent",cascade = CascadeType.MERGE,fetch=FetchType.LAZY)
	@Where(clause = "pflag = '1'")
	@OrderBy(value="priority")
	@JsonIgnore
	private Set<Function> child;
	
	@ManyToMany(targetEntity = Role.class,mappedBy = "functions",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	@Where(clause = "pflag = '1'")
	@JsonIgnore
	private List<Role> roles;
	
	@ManyToMany(targetEntity = User.class,mappedBy = "functions",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	@Where(clause = "pflag = '1'")
	@JsonIgnore
	private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.getUsers().add(user);
		user.getFunctions().add(this);
	}

	public void removeUser(User user) {
		this.getUsers().remove(user);
		user.getFunctions().remove(this);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.getRoles().add(role);
		role.getFunctions().add(this);
	}

	public void removeRole(Role role) {
		this.getRoles().remove(role);
		role.getFunctions().remove(this);
	}
	
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.String getFuncs() {
		return funcs;
	}

	public void setFuncs(java.lang.String funcs) {
		this.funcs = funcs;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Integer getPriority() {
		return priority;
	}

	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}

	public java.lang.Boolean getMenu() {
		return menu;
	}

	public void setMenu(java.lang.Boolean menu) {
		this.menu = menu;
	}

	public Function getParent() {
		return parent;
	}

	public void setParent(Function parent) {
		this.parent = parent;
	}

	public Set<Function> getChild() {
		return child;
	}

	public void setChild(Set<Function> child) {
		this.child = child;
	}
	
	/**
	 * 功能集的分隔符
	 */
	public static final String FUNC_SPLIT = "@";
	
	@Transient
	private String selectTree;
	
	@Override
	@JsonIgnore
	public SelectTree getTreeParent() {
		// TODO Auto-generated method stub
		return getParent();
	}

	@Override
	public String getTreeName() {
		// TODO Auto-generated method stub
		return getName();
	}

	@Override
	public String getSelectTree() {
		// TODO Auto-generated method stub
		return selectTree;
	}

	@Override
	public void setSelectTree(String selectTree) {
		// TODO Auto-generated method stub
		this.selectTree = selectTree;
	}

	@Override
	@JsonIgnore
	public Set<? extends SelectTree> getTreeChild() {
		// TODO Auto-generated method stub
		return getChild();
	}

	@Override
	public Set<? extends SelectTree> getTreeChildRaw() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setTreeChild(Set treeChild) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTreeId() {
		// TODO Auto-generated method stub
		return getId().toString();
	}

}