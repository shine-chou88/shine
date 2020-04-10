package com.gwideal.cms.manager;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.User;
import com.gwideal.cms.entity.Article;

public interface ArticleMng extends BaseManager<Article>{
	/**
	 * 通知公告列表
	 * @param bean
	 * @param comeFrom 来源(通过类型可以使通知公告可以在不同功能模块使用)
	 * @param sort
	 * @param order
	 * @param pageIndex
	 * @param pageSize
	 * @param isQuRole
	 * @param isStreetRole
	 * @param user
	 * @return
	 */
	public Pagination list(Article bean,String comeFrom,String sort,String order,int pageIndex,int pageSize,boolean isQuRole, boolean isStreetRole, User user);
	/**
	 * 信息发布保存
	 * @param bean 发布信息
	 * @param departIds 接收部门
	 * @param xxfbFiles
	 * @param originalFileName
	 * @param savePath
	 * @param user
	 * @param attachmentId
	 */
	public void save(Article bean,String departIds,MultipartFile[] xxfbFiles,MultipartFile[] xxfbPlayFiles,String[] originalFileName,String savePath,User user,String attachmentId);
	public List<Article> getAll(boolean isQuRole, boolean isStreetRole, User user);
	public List<Article> getAll(boolean isQuRole, boolean isStreetRole, User user,String id);
	/**
	 * 根据信息 得到接收对象的id列表
	 * @param id
	 * @return
	 */
	List<String> getArticleReceiverId(Article article);
	
	/**
	 * 根据信息 得到接收对象的名称列表
	 * @param article
	 * @return
	 */
	List<String> getArticleReceiverName(Article article);
	
	/**
	 * 是否已存在“首页飘窗”
	 * @param article
	 * @return
	 */
	public boolean isExistIndexFloat(Article article,boolean isQuRole, boolean isStreetRole,User user);
	
	/**
	 * 根据用户权限 得到首页飘窗
	 * @param article
	 * @param isQuRole
	 * @param isStreetRole
	 * @param user
	 * @return
	 */
	public List<Article> getIndexFloat(boolean isQuRole, boolean isStreetRole,User user);
	
	/**
	 * 获取通知公告未读用户信息
	 * @param articleId
	 * @return
	 */
	public List<User> listUnReadUser(String articleId,List<Depart> listDepart,boolean isWwgzTzgg);
}
