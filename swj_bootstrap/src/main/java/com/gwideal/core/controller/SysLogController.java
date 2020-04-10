package com.gwideal.core.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.page.SimplePage;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.common.webSocket.MyWebSocketHandler;
import com.gwideal.core.manager.SysLogMng;
import com.gwideal.core.model.SysLog;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/log")
public class SysLogController extends BaseController{

	@Autowired
	private SysLogMng logMng;
	@Autowired
    private MyWebSocketHandler myHandler;
	
	@RequestMapping("/list")
	public String list(String currentlogName,String logName,ModelMap model) {
		model.addAttribute("currentlogName",currentlogName);
		model.addAttribute("logName",logName);
		return "/WEB-INF/gwideal_core/log/log_list";
	}
	
	@RequestMapping(value="/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(SysLog log,String sort,String order,Integer page,Integer rows){
		if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=logMng.list(log,sort,order,page,rows);
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/toArchive")
	public String toArchive() {
		return "/WEB-INF/gwideal_core/log/archive";
	}
	
	@RequestMapping("/help")
	public String onLineHelpLog(String requestURI,HttpServletResponse response) {
		try {
			SysLog sysLog=new SysLog();
			sysLog.setCreator(getUser());
			sysLog.setType("ptzz");
			sysLog.setOperateUrl(requestURI);
			sysLog.setOperateContent("在线帮助");
			logMng.save(sysLog);
			response.sendRedirect(request.getContextPath()+requestURI+"?t="+Math.random());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("onLineHelpLog",e);
		}
		return null;
	}
	
	@RequestMapping("/archive")
	@ResponseBody
	public Result archive(String archiveDate,ModelMap model) {
		try {
			logMng.archive(archiveDate);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"归档成功！");
	}
	
	@RequestMapping("/exportExcel")
	@ResponseBody
	public Result exportExcel(SysLog sysLog ,HttpServletResponse response) {
		try {
			List<SysLog> exportLogs = logMng.exportList(sysLog);
			String path = request.getSession().getServletContext().getRealPath("/");
			response.setHeader("Content-Disposition","attachment; filename="+getFileName("操作日志")+".xls");   
			OutputStream out = new BufferedOutputStream(response.getOutputStream());   
			response.setContentType("application/octet-stream");   
			String filePath=path+File.separator+"template"+File.separator+"excel"+File.separator+"operate_log_export.xls";
			HSSFWorkbook workbook=exportSysLog(exportLogs,filePath);	
			workbook.write(out);
			out.flush();   
			out.close();
		} catch (Exception e) {
			log.error("", e);
			return getJsonResult(false, "导出失败,请联系管理员！");
		}
		return getJsonResult(true, "导出成功");	
	}
	
	public HSSFWorkbook exportSysLog(List<SysLog> list,String filePath) throws IOException{
		File exlFile=new File(filePath);
		FileInputStream fis=new FileInputStream(exlFile);
		HSSFWorkbook wb  = new HSSFWorkbook(fis);
		HSSFSheet sheet=wb.getSheetAt(0);
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size()&&i<65000; i++) {
				HSSFRow hssfRow=sheet.createRow(2+i);
				SysLog sysLog = list.get(i);
				HSSFCell cell1=hssfRow.createCell(0);
				HSSFCell cell2=hssfRow.createCell(1);
				HSSFCell cell3=hssfRow.createCell(2);
				HSSFCell cell4=hssfRow.createCell(3);
				HSSFCell cell5=hssfRow.createCell(4);
				//操作人
				cell1.setCellValue(StringUtil.isEmpty(sysLog.getUserName())?"":sysLog.getUserName());
				//操作时间
				cell2.setCellValue(StringUtil.isEmpty(sysLog.getCreateDateStr())?"":sysLog.getCreateDateStr());
				//操作IP
				cell3.setCellValue(sysLog.getIp());
				//操作路径
				cell4.setCellValue(StringUtil.isEmpty(sysLog.getOperateUrl())?"":sysLog.getOperateUrl());
				//操作内容
				cell5.setCellValue(StringUtil.isEmpty(sysLog.getOperateContent())?"":sysLog.getOperateContent());
			}
		}
		return wb;
		
	}
	/**
	 * APP操作日志刷新表格
	 * @param msg
	 * @param response
	 * @return
	 */
	@RequestMapping("/appLog")
	public String appLog(SysLog msg) {
		try {
			if (null != msg) {
				msg.setOperateContent(java.net.URLDecoder.decode(msg.getOperateContent(), "UTF-8"));
				msg.setUserName(java.net.URLDecoder.decode(msg.getUserName(), "UTF-8"));
				msg.setCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
				myHandler.sendMsgToAllUsers(new TextMessage(JSONObject.fromObject(msg).toString()));
			}
		} catch (Exception e) {
			log.error("appLog",e);
		}
		return null;
	}
}
