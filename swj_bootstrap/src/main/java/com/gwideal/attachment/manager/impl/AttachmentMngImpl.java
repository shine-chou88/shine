package com.gwideal.attachment.manager.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gwideal.common.entity.EntityDao;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;
import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;
@Service
@Transactional
public class AttachmentMngImpl extends BaseManagerImpl<Attachment> implements AttachmentMng{
	
	@Override
	public void upload(EntityDao entityDao,String[] serviceTypes,MultipartFile[] file,String savePath,User user){
		// TODO Auto-generated method stub
		if(null!=file && 0!=file.length){
			int t=file.length;
			for (int i = 0; i < t; i++) {
				if(file[i].getSize()>0){
					String fileName=file[i].getOriginalFilename();
					String contentType=file[i].getContentType();
					String postfix=fileName.substring(fileName.lastIndexOf("."),fileName.length());//文件后缀
					String fn=UUID.randomUUID()+postfix;//系统文件名
					String dateRoot=new SimpleDateFormat("yyyyMM").format(new Date());//把年月作为图片的底层目录
					String root=savePath+File.separator+dateRoot+File.separator;//图片存放目录
					File fileRoot=new File(root);
					if(!fileRoot.exists()){//判断是否存在这个目录,不存在创建 
						fileRoot.mkdirs();
					}
					String path=savePath+File.separator+dateRoot+File.separator+fn;
					File f=new File(path);
					Attachment att=new Attachment();
					att.setCreator(user);
					att.setEntryId(entityDao.getEntryId());
					att.setJoinTable(entityDao.getJoinTable());
					att.setFileName(path);
					att.setFileType(contentType);
					att.setFileUrl(File.separator+dateRoot+File.separator+fn);
					att.setFileSize(getFileSize(file[i].getSize()));
					att.setOriginalName(fileName);
					if(serviceTypes!=null&&serviceTypes.length>0){
						att.setServiceType(serviceTypes[i]);
					}
					super.save(att);
					try {
						file[i].transferTo(f);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 计算文件大小
	 * @param fileSize
	 * @return
	 */
	public String getFileSize(long fileSize){
		double size=0.0;
		String sizeStr="0";
		String unit="字节";
		if(fileSize>=1024){
			size=fileSize/1024;
			if(size<1024){
				unit="KB";
			}
			if(size>=1024){
				size=size/1024;
				unit="MB";
			}
			if(size>=1024){
				size=size/1024;
				unit="GB";
			}
			DecimalFormat df=new DecimalFormat("#.0");
			sizeStr=df.format(size);
		}else{
			sizeStr=String.valueOf(fileSize);
		}
		return sizeStr+" "+unit;
	}
	
	@Override
	public List<Attachment> list(EntityDao entityDao) {
		Finder hql=Finder.create("from Attachment where flag=1 and entryId=:entryId and joinTable=:joinTable");
		hql.setParam("entryId", entityDao.getEntryId());
		hql.setParam("joinTable", entityDao.getJoinTable());
		return super.find(hql);
	}

	@Override
	public Attachment upload(String serviceType,MultipartFile file,String savePath,User user) {
		// TODO Auto-generated method stub
		if(null!=file && file.getSize()>0){
			String fileName=file.getOriginalFilename();//文件名
			String contentType=file.getContentType();//文件类型
			String postfix=fileName.substring(fileName.lastIndexOf("."),fileName.length());//文件后缀
			String fn=UUID.randomUUID()+postfix;//系统文件名
			String dateRoot=new SimpleDateFormat("yyyyMM").format(new Date());//把年月作为图片的底层目录
			String root=savePath+File.separator+dateRoot+File.separator;//图片存放目录
			File fileRoot=new File(root);
			if(!fileRoot.exists()){//判断是否存在这个目录,不存在创建 
				fileRoot.mkdirs();
			}
			String path=savePath+File.separator+dateRoot+File.separator+fn;
			File f=new File(path);
			Attachment att=new Attachment();
			att.setCreator(user);
			att.setFileName(path);
			att.setFileType(contentType);
			att.setFileUrl(File.separator+dateRoot+File.separator+fn);
			att.setFileSize(getFileSize(file.getSize()));
			att.setOriginalName(fileName);
			att.setServiceType(serviceType);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return (Attachment)super.merge(att);
		}
		return null;
	}
	
	@Override
	public void uploadmp4(EntityDao entityDao,String[] serviceTypes,MultipartFile[] file,String savePath,User user)
	{
		// TODO Auto-generated method stub
		if(null!=file && 0!=file.length){
			int t=file.length;
			for (int i = 0; i < t; i++) {
				if(file[i].getSize()>0){
					String fileName=file[i].getOriginalFilename();
					String contentType=file[i].getContentType();
					String postfix=fileName.substring(fileName.lastIndexOf("."),fileName.length());//文件后缀
					String fn=UUID.randomUUID()+postfix;//系统文件名
					String dateRoot=new SimpleDateFormat("yyyyMM").format(new Date());//把年月作为图片的底层目录
					String root=savePath+File.separator+dateRoot+File.separator;//图片存放目录
					File fileRoot=new File(root);
					if(!fileRoot.exists()){//判断是否存在这个目录,不存在创建 
						fileRoot.mkdirs();
					}
					String path=savePath+File.separator+dateRoot+File.separator+fn;
					File f=new File(path);
					Attachment att=new Attachment();
					att.setServiceType("MP4");
					att.setCreator(user);
					att.setEntryId(entityDao.getEntryId());
					att.setJoinTable(entityDao.getJoinTable());
					att.setFileName(path);
					att.setFileType(contentType);
					att.setFileUrl(File.separator+dateRoot+File.separator+fn);
					att.setFileSize(getFileSize(file[i].getSize()));
					att.setOriginalName(fileName);
					if(serviceTypes!=null&&serviceTypes.length>0){
						att.setServiceType(serviceTypes[i]);
					}
					super.save(att);
					try {
						file[i].transferTo(f);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public Attachment upload(EntityDao entityDao, String serviceType,
			MultipartFile file, String savePath, User user) {
		if(null!=file && file.getSize()>0){
			String fileName=file.getOriginalFilename();//文件名
			String contentType=file.getContentType();//文件类型
			String postfix=fileName.substring(fileName.lastIndexOf("."),fileName.length());//文件后缀
			String fn=UUID.randomUUID()+postfix;//系统文件名
			String dateRoot=new SimpleDateFormat("yyyyMM").format(new Date());//把年月作为图片的底层目录
			String root=savePath+File.separator+dateRoot+File.separator;//图片存放目录
			File fileRoot=new File(root);
			if(!fileRoot.exists()){//判断是否存在这个目录,不存在创建 
				fileRoot.mkdirs();
			}
			String path=savePath+File.separator+dateRoot+File.separator+fn;
			File f=new File(path);
			Attachment att=new Attachment();
			att.setCreator(user);
			att.setFileName(path);
			att.setEntryId(entityDao.getEntryId());
			att.setJoinTable(entityDao.getJoinTable());
			att.setFileType(contentType);
			att.setFileUrl(File.separator+dateRoot+File.separator+fn);
			att.setFileSize(getFileSize(file.getSize()));
			att.setOriginalName(fileName);
			att.setServiceType(serviceType);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return (Attachment)super.merge(att);
		}
		return null;
	}

	@Override
	public List<Attachment> listByClass(EntityDao entityDao) {
		// TODO Auto-generated method stub
		Finder hql = Finder.create("from Attachment where flag=1 and joinTable=:joinTable");
		hql.setParam("joinTable", entityDao.getJoinTable());
		hql.append(" order by createtime desc ");
		return super.find(hql);
	}
	
	@Override
	public List<Attachment> listBySupervision(EntityDao entityDao,String type) {
		Finder hql=Finder.create("from Attachment where flag=1 and entryId=:entryId and joinTable=:joinTable");
		hql.setParam("entryId", entityDao.getEntryId());
		hql.setParam("joinTable", entityDao.getJoinTable());
		if(!StringUtil.isEmpty(type)){
			hql.append(" and serviceType=:serviceType").setParam("serviceType", type);
		}else{
			hql.append(" and (serviceType is null or serviceType ='' ) ");
		}
		return super.find(hql);
	}
	
}