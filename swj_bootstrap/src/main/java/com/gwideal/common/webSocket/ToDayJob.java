package com.gwideal.common.webSocket;
/**
 * 
* @Description: 更新首页表格数据
* @author li_chong
* @date 2017-11-3 下午5:04:04
* @version V1.0
 */
public class ToDayJob {
	private String id;
	private String date;//时间
	private String content;//内容
	private String status;//状态
	private String type;//类型
	private String streetId;
	private String[] onlineStreetCodes;//在线街道代码
	public String[] getOnlineStreetCodes() {
		return onlineStreetCodes;
	}
	public void setOnlineStreetCodes(String[] onlineStreetCodes) {
		this.onlineStreetCodes = onlineStreetCodes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStreetId() {
		return streetId;
	}
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	
}
