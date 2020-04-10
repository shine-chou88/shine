package com.gwideal.common.page;

import java.io.Serializable;
import java.util.List;

public class JsonPagination implements Serializable{
	
	private static final long serialVersionUID = -2554174279007555328L;
	
	private boolean success=true;
	private int totalRows;
	private int curPage;
	private List data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	private Integer total;
	private List rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	/**
	 * 分组grid数据格式
	 */
}
