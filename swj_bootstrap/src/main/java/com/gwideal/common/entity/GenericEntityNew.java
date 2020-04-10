package com.gwideal.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.core.model.User;

@SuppressWarnings("serial")
@MappedSuperclass
public class GenericEntityNew extends AbstractEntity{
	@Id
    @Column(name = "pid")
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "guid")
    private String id;
	
	@Column(name = "pflag")
    private Integer flag=1;//逻辑删除标志位 1表示有效，0表示无效 
	
    @ManyToOne
	@JoinColumn(name = "pupdater")
    @JsonIgnore
	private User updator;
    
    @Column(name = "op_date")
    private Date opDate=new Date();
    
    @ManyToOne
    @JoinColumn(name = "creator",updatable=false)
    @JsonIgnore
	private User creator;
    
	@Column(name="createtime",updatable=false)
	private Date createTime=new Date();
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUpdator() {
		return updator;
	}

	public void setUpdator(User updator) {
		this.updator = updator;
	}


	public Date getOpDate() {
		return opDate;
	}

	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
