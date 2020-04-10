package com.gwideal.cms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gwideal.common.entity.EntityDao;

/**
 * 信息发布对象中间表
 * @author zhang_xun
 *
 */
@Entity
@Table(name="T_CMS_ARTICLE_SENDEE")
public class ArticleAndSendee implements Serializable,EntityDao{
	private static final long serialVersionUID = 6006305221986487258L;
	@Id
    @Column(name = "PID")
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "guid")
	private String id;//主键
	@Column(name="ARTICLE")
	private Article article;//发布信息
	@Column(name="CODE")
	private String code;//街道/居委代码
	
	@Override
	public String getEntryId() {
		// TODO Auto-generated method stub
		return getId();
	}
	
	@Override
	public String getJoinTable() {
		// TODO Auto-generated method stub
		return "T_CMS_ARTICLE_SENDEE";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getEntryCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
