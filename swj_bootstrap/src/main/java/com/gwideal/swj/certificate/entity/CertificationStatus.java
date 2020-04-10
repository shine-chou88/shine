package com.gwideal.swj.certificate.entity;

public enum CertificationStatus {
	INIT("draft","初始"),
	USING("using","领用"),
	CHECKIN("checkIn","在库"),
	WAIT("wait","待注销"),
	CANCEL("cancelled","注销");
	
	
	private CertificationStatus(String itemValue, String itemName) {
		this.itemValue = itemValue;
		this.itemName = itemName;
	}

	private String itemValue;
	
	private String itemName;

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
	
	public static String getItemNameByItemValue(String itemValue){
		CertificationStatus[] arrays = CertificationStatus.values();
		for (CertificationStatus certificationStatus : arrays) {
			if(certificationStatus.getItemValue().equals(itemValue)){
				return certificationStatus.getItemName();
			}
		}
		return "";
	}
}
