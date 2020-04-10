package com.gwideal.swj.certificate.entity;

import com.gwideal.common.util.StringUtil;

public enum CertificationTypeEnum {
	privatePassport("privatePassport","因私普通护照","因私护照"),
	privateHKAndMacaoPass("privateHKAndMacaoPass","中华人民共和国往来港澳通行证","因私港澳"),
	privateTaiwanPass("privateTaiwanPass","大陆居民往来台湾通行证","因私台湾"),
	publicPassport("publicPassport","因公普通护照","因公护照"),
	publicHKAndMacaoPass("publicHKAndMacaoPass","因公往来香港澳门特别行政区通行证","因公港澳"),
	publicTaiwanPass("publicTaiwanPass","大陆居民往来台湾通行证","因公台湾");
	
	
	private CertificationTypeEnum(String itemValue, String itemName,String briefName) {
		this.itemValue = itemValue;
		this.itemName = itemName;
		this.briefName=briefName;
	}

	private String itemValue;
	
	private String itemName;
	
	private String briefName;

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	public String getBriefName() {
		return briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}

	public static String getItemNameByItemValue(String itemValue){
		CertificationTypeEnum[] arrays = CertificationTypeEnum.values();
		for (CertificationTypeEnum certificationStatus : arrays) {
			if(certificationStatus.getItemValue().equals(itemValue)){
				return certificationStatus.getItemName();
			}
		}
		return "";
	}
	
	public static String getPublicItemValue(){
		StringBuilder sb=new StringBuilder();
		CertificationTypeEnum[] arrays = CertificationTypeEnum.values();
		for (CertificationTypeEnum certificationStatus : arrays) {
			if(certificationStatus.getItemValue().contains("public")){
				if(!StringUtil.isEmpty(sb.toString())){
					sb.append(",");
				}
				sb.append(certificationStatus.getItemValue());
			}
		}
		return sb.toString();
	}
	
	public static String getPrivateItemValue(){
		StringBuilder sb=new StringBuilder();
		CertificationTypeEnum[] arrays = CertificationTypeEnum.values();
		for (CertificationTypeEnum certificationStatus : arrays) {
			if(certificationStatus.getItemValue().contains("private")){
				if(!StringUtil.isEmpty(sb.toString())){
					sb.append(",");
				}
				sb.append(certificationStatus.getItemValue());
			}
		}
		return sb.toString();
	}
	
	public static String getAllItemValue(){
		StringBuilder sb=new StringBuilder();
		CertificationTypeEnum[] arrays = CertificationTypeEnum.values();
		for (CertificationTypeEnum certificationStatus : arrays) {
				if(!StringUtil.isEmpty(sb.toString())){
					sb.append(",");
				}
				sb.append(certificationStatus.getItemValue());
		}
		return sb.toString();
	}
	
	public static String getItemBriefNameByItemValue(String itemValue){
		CertificationTypeEnum[] arrays = CertificationTypeEnum.values();
		for (CertificationTypeEnum certificationStatus : arrays) {
			if(certificationStatus.getItemValue().equals(itemValue)){
				return certificationStatus.getBriefName();
			}
		}
		return "";
	}
	
	public static String getItemValueByItemName(String itemName){
		CertificationTypeEnum[] arrays = CertificationTypeEnum.values();
		for (CertificationTypeEnum certificationStatus : arrays) {
			if(certificationStatus.getItemName().equals(itemName)){
				return certificationStatus.getItemValue();
			}
		}
		return "";
	}
}
