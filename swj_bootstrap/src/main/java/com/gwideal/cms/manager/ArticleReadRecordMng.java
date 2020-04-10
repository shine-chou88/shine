package com.gwideal.cms.manager;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.core.model.User;
import com.gwideal.cms.entity.ArticleReadRecord;

public interface ArticleReadRecordMng extends BaseManager<ArticleReadRecord>{
	/**
	 * 获取文章的已读记录
	 * @param articleId 信息发布ID
	 * @param 用户ID
	 * @return
	 */
	public List<ArticleReadRecord> list(String articleId,String userId);
	
	/**
	 * 保存反馈
	 * @param bean
	 * @param suggestionFiles
	 */
	public void save(ArticleReadRecord bean,MultipartFile[] suggestionFiles,String savePath,User user);
}
