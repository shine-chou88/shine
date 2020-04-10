package com.gwideal.common.entity;

import java.util.List;


public class TreeEntity{
	private String id;
	private String text;
	private String state;//节点状态
	private boolean isLeaf;//是否为叶子节点
	private String code;
	private String haveChild;//是否含有子节点
	private String parentId;//父节点id
	private List<TreeEntity> children;//子节点集合
	
	//部门表中 个人涉稳相关字段 
	private String grww_zrld;
	private String grww_zrlddh;
	private String grww_lxr;
	private String grww_lxrdh;
	
	private boolean departStatus;//是否是部门
	
	public boolean isDepartStatus() {
		return departStatus;
	}
	public void setDepartStatus(boolean departStatus) {
		this.departStatus = departStatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHaveChild() {
		return haveChild;
	}
	public void setHaveChild(String haveChild) {
		this.haveChild = haveChild;
	}
	public List<TreeEntity> getChildren() {
		return children;
	}
	public void setChildren(List<TreeEntity> children) {
		this.children = children;
	}
	public String getGrww_zrld() {
		return grww_zrld;
	}
	public void setGrww_zrld(String grwwZrld) {
		grww_zrld = grwwZrld;
	}
	public String getGrww_zrlddh() {
		return grww_zrlddh;
	}
	public void setGrww_zrlddh(String grwwZrlddh) {
		grww_zrlddh = grwwZrlddh;
	}
	public String getGrww_lxr() {
		return grww_lxr;
	}
	public void setGrww_lxr(String grwwLxr) {
		grww_lxr = grwwLxr;
	}
	public String getGrww_lxrdh() {
		return grww_lxrdh;
	}
	public void setGrww_lxrdh(String grwwLxrdh) {
		grww_lxrdh = grwwLxrdh;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}