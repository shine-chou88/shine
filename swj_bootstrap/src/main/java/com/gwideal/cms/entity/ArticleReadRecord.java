package com.gwideal.cms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gwideal.common.entity.EntityDao;
import com.gwideal.common.entity.GenericEntityNow;
/**
 * 信息发布已读记录
 * @author zhou_liang
 *
 */
@Entity
@Table(name="T_CMS_ARTICLE_READ_RECORD")
public class ArticleReadRecord extends GenericEntityNow implements Serializable,EntityDao{
	private static final long serialVersionUID = -2086892954271643598L;

	@Column(name="ARTICLE_ID")
	private String articleId;//信息ID

	@Column(name="SUGGESTION")
	private String suggestion;//阅读人员反馈意见

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	@Override
	public String getJoinTable() {
		// TODO Auto-generated method stub
		return "T_CMS_ARTICLE_READ_RECORD";
	}

	@Override
	public String getEntryId() {
		// TODO Auto-generated method stub
		return getId();
	}

	@Override
	public String getEntryCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
