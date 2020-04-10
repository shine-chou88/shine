package com.gwideal.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;
import com.gwideal.cms.entity.ArticleReadRecord;
import com.gwideal.cms.manager.ArticleReadRecordMng;
import com.gwideal.attachment.manager.AttachmentMng;
@SuppressWarnings("unchecked")
@Transactional
@Service
public class ArticleReadRecordMngImpl extends BaseManagerImpl<ArticleReadRecord> implements ArticleReadRecordMng{

	@Autowired
	private AttachmentMng attachmentMng;
	
	@Override
	public List<ArticleReadRecord> list(String articleId,String userId) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from ArticleReadRecord Where flag=1");
		if(!StringUtil.isEmpty(articleId)){
			f.append(" and articleId = :articleId");
			f.setParam("articleId", articleId);
		}
		if(!StringUtil.isEmpty(userId)){
			f.append(" and creator.id = :cId");
			f.setParam("cId", userId);
		}
		f.append(" order by createTime desc");
		return super.find(f);
	}

	@Override
	public void save(ArticleReadRecord bean, MultipartFile[] suggestionFiles,String savePath,User user) {
		// TODO Auto-generated method stub
		bean.setCreator(user);
		bean = (ArticleReadRecord) super.merge(bean);
		//保存上传附件
		attachmentMng.upload(bean,null,suggestionFiles,savePath,user);
	}

}
