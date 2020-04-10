package com.gwideal.common.entity;

import java.io.Serializable;

public class ECharts implements Serializable{
	private static final long serialVersionUID = -478347365194226114L;

	private String title;//标题
	
	private String legendData;//图例数据
	
	private String data;//显示数据
	
	private String indicator;//指标(雷达图)

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLegendData() {
		return legendData;
	}

	public void setLegendData(String legendData) {
		this.legendData = legendData;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
