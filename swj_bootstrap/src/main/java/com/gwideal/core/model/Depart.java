package com.gwideal.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.common.entity.SelectTree;
import com.gwideal.common.util.StringUtil;

@Entity
@Table(name = "sys_depart")
public class Depart extends GenericEntityNow implements SelectTree,Serializable{
	
	private static final long serialVersionUID = 3844645003343878376L;

	@Column(name = "DEPARTCODE")
	private String code;//单位代码
	
	@Column(name = "DEPARTNAME")
	private String name;//单位名称
	
	@Transient
	private String checked;
	
    @ManyToOne
    @JoinColumn(name = "PARENTID")
    @JsonIgnore
	private Depart parent;//上级单位

	@Column(name = "DESCRIPTION")
	private String description;//简称

	@Column(name = "ORDERNO")
	private String orderNo;//排序
	
    @OneToMany(targetEntity = Depart.class, mappedBy = "parent", cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    @Where(clause = "pflag = 1")
    @JsonIgnore
    private Set<Depart> children;

    @Transient
    private List<User> listUser=new ArrayList<User>();

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}
	
	public Set<Depart> getChildren() {
		return children;
	}

	public void setChildren(Set<Depart> children) {
		this.children = children;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getParentName(){
		if(null!=parent && !StringUtil.isEmpty(parent.getName())){
			return parent.getName();
		}
		return "";
	}

	public Depart getParent() {
		return parent;
	}

	public void setParent(Depart parent) {
		this.parent = parent;
	}

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
		return getChildren();
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
