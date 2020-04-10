package com.gwideal.common.entity;

import java.io.Serializable;

public class HighCharts implements Serializable{
	
	
	private static final long serialVersionUID = -8355599378971583705L;
	
	private String xaxis;//x轴
	
	private String series;//数据
	
	private String total;//合计
	
	private String drilldownSeries;//跳转数据
	
	private String statDate;//统计日期

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public String getXaxis() {
		return xaxis;
	}

	public void setXaxis(String xaxis) {
		this.xaxis = xaxis;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDrilldownSeries() {
		return drilldownSeries;
	}

	public void setDrilldownSeries(String drilldownSeries) {
		this.drilldownSeries = drilldownSeries;
	}

}
