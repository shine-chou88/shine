package com.gwideal.attachment.manager;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwideal.common.entity.EntityDao;
import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.core.model.User;
import com.gwideal.attachment.entity.Attachment;

public interface AttachmentMng extends BaseManager<Attachment> {
	/**
	 * 多个文件上传
	 * @param entityDao
	 * @param serviceTypes
	 * @param file
	 * @param getSavePath
	 */
	public void upload(EntityDao entityDao,String[] serviceTypes,MultipartFile[] file,String savePath,User user);
	/**
	 * 多个mp4文件上传
	 * @param entityDao
	 * @param serviceTypes
	 * @param file
	 * @param getSavePath
	 */
	public void uploadmp4(EntityDao entityDao,String[] serviceTypes,MultipartFile[] file,String savePath,User user);
	/**
	 * 获取实体对应的附件
	 * @param entityDao
	 * @return
	 */
	public List<Attachment> list(EntityDao entityDao);
	
	/**
	 * 单个文件上传
	 * @param serviceType
	 * @param file
	 * @param getSavePath
	 */
	public Attachment upload(String serviceType,MultipartFile file,String savePath,User user);
	/**
	 * 单个文件上传
	 * @param serviceType
	 * @param file
	 * @param getSavePath
	 */
	public Attachment upload(EntityDao entityDao,String serviceType,MultipartFile file,String savePath,User user);
	
	/**
	 * 获取实体类对应的附件
	 * @param entityDao
	 * @return
	 */
	public List<Attachment> listByClass(EntityDao entityDao);
	
	/**
	 * 获取督办工作的附件
	 * @param entityDao
	 * @param type
	 * @return
	 */
	List<Attachment> listBySupervision(EntityDao entityDao,String type);
}
