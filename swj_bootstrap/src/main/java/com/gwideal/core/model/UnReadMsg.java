package com.gwideal.core.model;

import java.io.Serializable;

/**
 * 未读消息
 * @author zhouliang
 *
 */
public class UnReadMsg implements Serializable{

	private static final long serialVersionUID = -8776860850169092484L;
	
	
	private String id;
	private String title;//标题
	private String type;//类型
	private String releaseTime;//发布时间
	private String functionType;//功能类型
	private String creatorName;//创建人
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getFunctionType() {
		return functionType;
	}
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
}
