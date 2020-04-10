package com.gwideal.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class MapChart implements Serializable{
	private static final long serialVersionUID = -8065195447759916516L;
	
	private String maxVal;
	
	private String series;
	
    private String mapType;
	
    private String provinceSeries;
    
	private String provinceMaxVal;
	

	public String getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(String maxVal) {
		this.maxVal = maxVal;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getMapType() {
		return mapType;
	}
	public void setMapType(String mapType) {
		this.mapType = mapType;
	}
	public String getProvinceSeries() {
		return provinceSeries;
	}
	public void setProvinceSeries(String provinceSeries) {
		this.provinceSeries = provinceSeries;
	}
	public String getProvinceMaxVal() {
		return provinceMaxVal;
	}
	public void setProvinceMaxVal(String provinceMaxVal) {
		this.provinceMaxVal = provinceMaxVal;
	}
	
	
}
