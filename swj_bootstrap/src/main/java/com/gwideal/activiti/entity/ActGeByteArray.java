package com.gwideal.activiti.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 二进制数据表，存储通用的流程定义和流程资源
 * @author zhou_liang
 */
@Entity
@Table(name = "ACT_GE_BYTEARRAY")
public class ActGeByteArray implements Serializable {
	private static final long serialVersionUID = -848900686239847599L;

	@Id
    @Column(name = "ID_")
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "guid")
    private String id;
	
	//版本
	@Column(name = "REV_")
	private String rev;
	
	//部署的文件名称
	@Column(name = "NAME_")
	private String name;
	
	//部署表ID
	@Column(name = "DEPLOYMENT_ID_")
	private String deploymentId;
	
	//部署文件
	@Column(name = "BYTES_")
	@Lob
	private byte[] bytes;
	
	//是否是引擎生成 0为用户生成，1为activiti生成
	@Column(name = "GENERATED_")
	private String generated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getGenerated() {
		return generated;
	}

	public void setGenerated(String generated) {
		this.generated = generated;
	}
	
}
