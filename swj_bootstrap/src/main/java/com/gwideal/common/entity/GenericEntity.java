package com.gwideal.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.gwideal.core.model.User;

@SuppressWarnings("serial")
@MappedSuperclass
public class GenericEntity extends AbstractEntity{
	@Id
    @Column(name = "pid")
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "guid")
    private String id;
	
    @Column(name = "pflag")
    private Integer flag=1;      //逻辑删除标志位 1表示有效，0表示无效 
    
    @ManyToOne
	@JoinColumn(name = "pupdater")
	private User updator;
	
    @Column(name = "pupdatetime")
    private Date updateTime=new Date();
    
	public User getUpdator() {
		return updator;
	}

	public void setUpdator(User updator) {
		this.updator = updator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	
}
