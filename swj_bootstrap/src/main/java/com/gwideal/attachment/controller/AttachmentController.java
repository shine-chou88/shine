package com.gwideal.attachment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;
import com.gwideal.common.page.Result;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.fckeditor.UploadRule;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/attachment")
public class AttachmentController extends BaseController{
	
	private static final ResourceBundle res=ResourceBundle.getBundle("upload");
	
	@Autowired
	private AttachmentMng attachmentMng;
	
	@RequestMapping("/uploadImg")
	@ResponseBody
	public Result uploadImg(@RequestParam(value="uploadFile",required=false) MultipartFile uploadFile,ModelMap model){
		String uploadFileFileName=uploadFile.getOriginalFilename();
		if (StringUtil.isEmpty(uploadFileFileName) || uploadFile.getSize()==0) {
			return getJsonResult(false,"上传失败！没有找到上传文件");
		}
		int suffixIndex = uploadFileFileName.lastIndexOf('.');
		if (suffixIndex == -1) {
			return getJsonResult(false,"上传失败！文件没有后缀名，不允许上传！");
		}
		String suffix=uploadFileFileName.substring(suffixIndex+1).toLowerCase();
		UploadRule rule=new UploadRule(null,null);
		if (!rule.getAcceptImg().contains(suffix)) {
			return getJsonResult(false,"上传失败！该后缀名不允许上传：" + suffix);
		}
		try {
			Attachment bean=attachmentMng.upload(null,null,null,getUser());
			return getJsonResult(true,bean.getId()+","+bean.getFileUrl());
		} catch (Exception e) {
			e.printStackTrace();
			return getJsonResult(false,"出现错误，请联系管理员！");
		}
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id){
		try {
			Attachment bean=attachmentMng.findById(id);
			bean.setFlag(0);
			bean.setUpdator(getUser());
			bean.setUpdateTime(new Date());
			attachmentMng.updateDefault(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return getJsonResult(false,"删除失败,请联系管理员！");
		}
		return getJsonResult(true,"删除成功！");
	}
	
	private String getSavePath(){
		return res.getString("upload.save_path");
		//return request.getServletContext().getRealPath(FileUpLoadUtil.getSavePath());
	}
	
	@RequestMapping("/download/{id}")
	@ResponseBody
	public Result download(@PathVariable String id,HttpServletResponse response){
		OutputStream out = null;
		InputStream in = null;
		try {
			Attachment bean=attachmentMng.findById(id);
			File file = new File(getSavePath()+bean.getFileUrl());
			if(file.exists()){
				in = new FileInputStream(file);
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setContentLength(in.available());
				response.setHeader("Content-Disposition","attachment; filename=\""+getFileName(bean.getOriginalName())+"\"");
				out = response.getOutputStream();
				byte[] bt = new byte[1000];
				int a; 
				while ((a = in.read(bt, 0, 1000)) > 0) {
					out.write(bt, 0, a);
					out.flush();
				}
			}else{
				return getJsonResult(false,"文件不存在！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getJsonResult(false,"出现错误,请联系管理员！");
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return getJsonResult(true,"操作成功！");
	}
	
	@RequestMapping(value = "/IoQrCodeImg/{fileName}", method = RequestMethod.GET)  
    public String IoQrCodeImg(@PathVariable String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {  
        ServletOutputStream out = null;  
        FileInputStream ips = null;  
        try {  
            //获取图片存放路径  
			File file = new File(res.getString("upload.qrCode_path")+File.separator+fileName+".gif");
            ips = new FileInputStream(file);  
            response.setContentType("multipart/form-data");  
            out = response.getOutputStream();  
            //读取文件流  
            int len = 0;  
            byte[] buffer = new byte[1024 * 10];  
            while ((len = ips.read(buffer)) != -1){  
                out.write(buffer,0,len);  
            }  
            out.flush();  
        }catch (Exception e){  
            e.printStackTrace();  
        }finally {  
            out.close();  
            ips.close();  
        }  
        return null;  
    }  
	
	/**
	 * 附件图片
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/IoReadImage/{id}", method = RequestMethod.GET)  
    public String IoReadImage(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws IOException {  
        ServletOutputStream out = null;  
        FileInputStream ips = null;  
        try {  
            //获取图片存放路径  
        	Attachment bean=attachmentMng.findById(id);
			File file = new File(getSavePath()+bean.getFileUrl());
            ips = new FileInputStream(file);  
            response.setContentType("multipart/form-data");  
            out = response.getOutputStream();  
            //读取文件流  
            int len = 0;  
            byte[] buffer = new byte[1024 * 10];  
            while ((len = ips.read(buffer)) != -1){  
                out.write(buffer,0,len);  
            }  
            out.flush();  
        }catch (Exception e){  
            log.error("", e);
        }finally {  
        	if(null!=ips){
        		ips.close();
        	}
        	if(null!=out){
        		out.close();  
        	}
        }  
        return null;  
    }  
}

