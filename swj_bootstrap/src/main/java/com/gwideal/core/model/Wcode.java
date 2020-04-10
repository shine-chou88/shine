package com.gwideal.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.gwideal.common.entity.GenericEntityNow;

//流水号
@Entity
@Table(name = "SYS_WCODE")
public class Wcode extends GenericEntityNow implements Serializable {
	private static final long serialVersionUID = -4653825897270020678L;
	
	//对应功能类
	@Column(name = "CLASS_NAME")
	private String className;
	//生成规则
	@Column(name = "WRULE")
	private String wrule;
	//流水号
	@Column(name = "WCODE")
	private Integer wcode;

	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
	public void setWrule(String wrule) {
		this.wrule = wrule;
	}
	
	public String getWrule() {
		return wrule;
	}
	public void setWcode(Integer wcode) {
		this.wcode = wcode;
	}
	
	public Integer getWcode() {
		return wcode;
	}
	
	/**
	 * 获取四位流水号
	 * @return
	 */
	public String getWcodeStr(){
		if(wcode>0 && wcode<10){
			return "000"+wcode;
		}else if(wcode>=10 && wcode<100){
			return "00"+wcode;
		}else if(wcode>=100 && wcode<1000){
			return "0"+wcode;
		}else{
			return String.valueOf(wcode);
		}
	}
}
