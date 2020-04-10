package com.gwideal.swj.certificate.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwideal.attachment.manager.AttachmentMng;
import com.gwideal.common.aspect.ArchivesLog;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.page.SimplePage;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.CertificationStatus;
import com.gwideal.swj.certificate.entity.CertificationTypeEnum;
import com.gwideal.swj.certificate.entity.PrivateExportEntity;
import com.gwideal.swj.certificate.manager.CertificateInfoApplyMng;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/certificateInfo")
public class CertificateInfoController extends BaseController {
	protected static final Logger log = LoggerFactory.getLogger(CertificateInfo.class);
	
	private static final ResourceBundle res=ResourceBundle.getBundle("upload");
	
	@Autowired
	private CertificateInfoMng certificateInfoMng;
	@Autowired
	private AttachmentMng attachmentMng;
	@Autowired
	private CertificateInfoApplyMng certificateInfoApplyMng;

	/**
	 * 因私列表页面
	 * 
	 * @param model
	 * @param status
	 * @param
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return "/WEB-INF/view/swj/certificate/list";
	}

	/**
	 * 因私注销页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancelList")
	public String cancelList(Model model) {
		return "/WEB-INF/view/swj/certificate/cancelList";
	}

	/**
	 * 因公列表页面
	 * 
	 * @param model
	 * @param status
	 * @param
	 * @return
	 */
	@RequestMapping("/listPublic")
	public String listPublic(Model model) {
		return "/WEB-INF/view/swj/certificate/publicList";
	}
	
	/**
	 * 因公待注销页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancelWaitPublic/{id}")
	public String cancelWaitPublic(Model model,@PathVariable String id,String status) {
		model.addAttribute("bean", certificateInfoMng.findById(id));
		model.addAttribute("status", status);
		return "/WEB-INF/view/swj/certificate/publicWaitCancel";
	}
	
	/**
	 * 因公待注销
	 * 
	 * @param id
	 * @param cancelRemark 注销原因
	 * @return
	 */
	@ArchivesLog(operationType = "wait", operationName = "待注销")
	@RequestMapping("/saveCancelWaitPublic/{id}")
	@ResponseBody
	public Result saveCancelWaitPublic(@PathVariable String id,String cancelRemark) {
		try {
			if(!StringUtil.isEmpty(id)) {
				CertificateInfo bean = certificateInfoMng.findById(id);
				bean.setUpdator(getUser());
				bean.setUpdateTime(new Date());
				bean.setStatus(CertificationStatus.WAIT.getItemValue());
				bean.setCancelRemark(cancelRemark);
				bean.setCancelUser(getUser());
				bean.setCancelTime(new Date());
				certificateInfoMng.save(bean);
			}
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("", e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	/**
	 * 因公注销
	 * 
	 * @param id
	 * @param cancelRemark 注销原因
	 * @return
	 */
	@ArchivesLog(operationType = "cancel", operationName = "注销")
	@RequestMapping("/saveCancelPublic/{id}")
	@ResponseBody
	public Result saveCancelPublic(@PathVariable String id,String cancelRemark) {
		try {
			if(!StringUtil.isEmpty(id)) {
				CertificateInfo bean = certificateInfoMng.findById(id);
				bean.setUpdator(getUser());
				bean.setUpdateTime(new Date());
				bean.setStatus(CertificationStatus.CANCEL.getItemValue());
				bean.setCancelRemark(cancelRemark);
				bean.setCancelUser(getUser());
				bean.setCancelTime(new Date());
				certificateInfoMng.save(bean);
			}
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("", e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}

	/**
	 * 因公注销页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancelListPublic")
	public String cancelListPublic(Model model,String cancelType) {
		model.addAttribute("cancelType", cancelType);
		return "/WEB-INF/view/swj/certificate/publicCancelList";
	}

	@RequestMapping("/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(CertificateInfo bean, String sort, String order, Integer page, Integer rows,
			ModelMap model) {
		if (page == null) {
			page = 1;
		}
		if (rows == null) {
			rows = SimplePage.DEF_COUNT;
		}
		Pagination p = certificateInfoMng.list(bean, sort, order, page, rows, hasRole("QU_ROLE"), isStreetRole(),
				getUser());
		return getJsonPagination(p, page);
	}

	/**
	 * 因私新增页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "/WEB-INF/view/swj/certificate/edit";
	}

	/**
	 * 因公新增页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPublic")
	public String addPublic() {
		return "/WEB-INF/view/swj/certificate/publicEdit";
	}

	/**
	 * 因私编辑页面
	 * 
	 * @param id
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, Model model) {
		model.addAttribute("bean", certificateInfoMng.get(id));
		return "/WEB-INF/view/swj/certificate/edit";
	}

	/**
	 * 因公编辑页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPublic/{id}")
	public String editPublic(@PathVariable("id") String id, Model model,String cancelType) {
		CertificateInfo bean = certificateInfoMng.get(id);
		model.addAttribute("bean", bean);
		model.addAttribute("listAtt",attachmentMng.list(bean));
		model.addAttribute("cancelType", cancelType);
		return "/WEB-INF/view/swj/certificate/publicEdit";
	}

	/**
	 * 查看页面
	 * 
	 * @param id
	 * @param model
	 * @param businessType
	 * @return
	 */
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") String id, Model model, String businessType) {
		CertificateInfo certificateInfo = certificateInfoMng.get(id);
		if (certificateInfo != null) {
			String sfzhm = certificateInfo.getSfzhm();
			String typeStr = "";
			String[] notStatus=null;
			if ("public".equals(businessType)) {
				typeStr = CertificationTypeEnum.getPublicItemValue();
				notStatus=new String[]{"cancelled","wait"};
			} else if ("private".equals(businessType)) {
				typeStr = CertificationTypeEnum.getPrivateItemValue();
				notStatus=new String[]{"cancelled"};
			} else if("single".equals(businessType)){
				typeStr =certificateInfo.getCertificateType();
			}else {
				typeStr = CertificationTypeEnum.getAllItemValue();
			}
			if(StringUtil.isEmpty(sfzhm)&&(certificateInfo.getOwner()==null||"public".equals(businessType))){
				typeStr =certificateInfo.getCertificateType();
			}
			String[] typeArr = typeStr.split(",");
			for (String type : typeArr) {
				CertificateInfo bean=null;
				if(!StringUtil.isEmpty(sfzhm)){
					bean= certificateInfoMng.findOneByTypeAndSfzhm(type, sfzhm,notStatus);
				}else{
					if("private".equals(businessType)&&certificateInfo.getOwner()!=null){
						List<CertificateInfo> privates = certificateInfoMng.findPrivateByTypeAndOwnId(new String[]{type}, certificateInfo.getOwner().getId());
						if(privates!=null&&privates.size()>0){
							bean=privates.get(0);
						}
					}else{
						bean=certificateInfo;
					}
				}
				if("single".equals(businessType)){
					bean=certificateInfo;
				}
				//String basePath = request.getSession().getServletContext().getRealPath("/");
				if(bean!=null){
					try{
					String fileDirPath = getSaveQrImage();
					File outputFile = new File(fileDirPath+File.separator+bean.getId()+".gif");
					if(!outputFile.exists()){
						String qrCodeUrl = certificateInfoMng.genQrCode(bean.getId(), getUser(), request);
						bean.setQrCodeUrl(qrCodeUrl);
					}
					if (CertificationTypeEnum.privatePassport.getItemValue().equals(type)) {
						model.addAttribute("foreign", bean);
					} else if (CertificationTypeEnum.privateHKAndMacaoPass.getItemValue().equals(type)) {
						model.addAttribute("hongkongAndMacao", bean);
					} else if (CertificationTypeEnum.privateTaiwanPass.getItemValue().equals(type)) {
						model.addAttribute("taiwan", bean);
					} else if (CertificationTypeEnum.publicPassport.getItemValue().equals(type)) {
						model.addAttribute("publicForeign", bean);
							model.addAttribute("publicForeignListAtt",attachmentMng.list(bean));
					}else if(CertificationTypeEnum.publicHKAndMacaoPass.getItemValue().equals(type)){
						model.addAttribute("hongkongAndMacaoPublic", bean);
						model.addAttribute("hongkongAndMacaoPublicListAtt",attachmentMng.list(bean));
					}else if (CertificationTypeEnum.publicTaiwanPass.getItemValue().equals(type)){
						model.addAttribute("taiwanPublic", bean);
						model.addAttribute("taiwanPublicListAtt",attachmentMng.list(bean));
					}
					}catch(Exception e){
						log.error("error", e);
						log.info("文件路径问题");
					}
				}
			}
			String certificateTypeShow = certificateInfo.getCertificateTypeShow();
			if(!StringUtil.isEmpty(certificateTypeShow)&&certificateTypeShow.contains("台湾")){
				if(certificateInfo.getCertificateType().contains("private")){
					certificateTypeShow+="(因私)";
				}else if(certificateInfo.getCertificateType().contains("public")){
					certificateTypeShow+="(因公)";
				}
			}
			model.addAttribute("tabSelectCertificate", certificateTypeShow);
		}
		return "/WEB-INF/view/swj/certificate/view";
	}
	
	

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Result save(CertificateInfo bean) {
		boolean flag=false;
		try {
			if(StringUtil.isEmpty(bean.getId())){
				flag=judgeCertificateInfoByPrivate(bean);
			}else{
				CertificateInfo info = certificateInfoMng.findById(bean.getId());
				if(!info.getCertificateType().equals(bean.getCertificateType())){
					flag=judgeCertificateInfoByPrivate(bean);
				}
			}
			if(flag){
				return getJsonResult(false, "对不起，该人已有一本同类型的证照");
			}
			certificateInfoMng.save(bean, getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("", e);
			if(e.getMessage().contains("证照")){
				return getJsonResult(false, e.getMessage());
			}
			return getJsonResult(false, Result.ERRORMSG);
		}
	}

	
	/**
	 * 因公证照保存
	 * @param bean
	 * @param publicFiles
	 * @return
	 */
	@RequestMapping(value = "/savePublic")
	@ResponseBody
	public Result savePublic(CertificateInfo bean,@RequestParam(value="publicFiles",required=false) MultipartFile[] publicFiles) {
		boolean flag=false;
		try {
			if(StringUtil.isEmpty(bean.getId())){
				flag=judgeCertificateInfoByPublic(bean);
			}else{
				CertificateInfo info = certificateInfoMng.findById(bean.getId());
				if(!info.getCertificateType().equals(bean.getCertificateType())){
					flag=judgeCertificateInfoByPublic(bean);
				}
			}
			if(flag){
				return getJsonResult(false, "对不起，该人已有一本同类型的证照");
			}
			certificateInfoMng.savePublic(bean, publicFiles,getFileSavePath(),getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("", e);
			if(e.getMessage().contains("证照")){
				return getJsonResult(false, e.getMessage());
			}
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ArchivesLog(operationType = "delete", operationName = "删除")
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		try {
			certificateInfoMng.delete(id, getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("", e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}

	/**
	 * 注销
	 * 
	 * @param id
	 * @return
	 */
	@ArchivesLog(operationType = "cancel", operationName = "注销")
	@RequestMapping("/cancel/{id}")
	@ResponseBody
	public Result cancel(@PathVariable String id) {
		try {
			certificateInfoMng.cancel(id, getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("", e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}

	/**
	 * 生成二维码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/genQrCode/{id}")
	@ResponseBody
	public Result genQrCode(@PathVariable String id) {
		try {
			certificateInfoMng.genQrCode(id, getUser(), request);
			return getJsonResult(true, "生成二维码成功");
		} catch (Exception e) {
			log.error("", e);
			return getJsonResult(false, "生成二维码失败");
		}
	}

	/**
	 * 因私入库页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkIn/{id}")
	public String checkIn(@PathVariable String id, Model model) {
		CertificateInfo bean = certificateInfoMng.get(id);
		//String basePath = request.getSession().getServletContext().getRealPath("/");
		if(bean!=null){
			try{
			String fileDirPath = getSaveQrImage();
			File outputFile = new File(fileDirPath+File.separator+bean.getId()+".gif");
			if(!outputFile.exists()){
				String qrCodeUrl = certificateInfoMng.genQrCode(bean.getId(), getUser(), request);
				bean.setQrCodeUrl(qrCodeUrl);
			}
			}catch(Exception e){
				log.error("error", e);
				log.info("文件路径问题");
			}
		}
		model.addAttribute("bean", bean);
		return "/WEB-INF/view/swj/certificate/checkIn";
	}

	/**
	 * 因公入库页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkInPublic/{id}")
	public String checkInPublic(@PathVariable String id, Model model) {
		CertificateInfo bean = certificateInfoMng.get(id);
		//String basePath = request.getSession().getServletContext().getRealPath("/");
		if(bean!=null){
			String fileDirPath = getSaveQrImage();
			File outputFile = new File(fileDirPath+File.separator+bean.getId()+".gif");
			if(!outputFile.exists()){
				String qrCodeUrl = certificateInfoMng.genQrCode(bean.getId(), getUser(), request);
				bean.setQrCodeUrl(qrCodeUrl);
			}
		}
		model.addAttribute("bean", bean);
		model.addAttribute("listAtt",attachmentMng.list(bean));
		return "/WEB-INF/view/swj/certificate/checkInPublic";
	}

	/**
	 * 保存入库
	 * 
	 * @param id
	 * @return
	 */
	@ArchivesLog(operationType = "checkIn", operationName = "入库")
	@RequestMapping("/saveCheckIn/{id}")
	@ResponseBody
	public Result saveCheckIn(@PathVariable String id) {
		try {
			certificateInfoMng.checkIn(id, getUser());
			return getJsonResult(true, "入库成功");
		} catch (Exception e) {
			log.error("", e);
			return getJsonResult(false, "入库失败");
		}
	}

	@RequestMapping("/refList")
	public String refList(ModelMap model) {
		return "/WEB-INF/view/swj/certificate/refCertificateGrid";
	}

	@RequestMapping("/refAdjustList")
	public String refAdjustList(ModelMap model,String selectType) {
		model.addAttribute("selectType", selectType);
		return "/WEB-INF/view/swj/certificate/refAdjustGrid";
	}

	@RequestMapping("/myList")
	public String myList(Model model) {
		return "/WEB-INF/view/swj/certificate/myList";
	}

	@RequestMapping("/myJsonPagination")
	@ResponseBody
	public JsonPagination myJsonPagination(CertificateInfo bean, String sort, String order, Integer page, Integer rows,
			ModelMap model) {
		if (page == null) {
			page = 1;
		}
		if (rows == null) {
			rows = SimplePage.DEF_COUNT;
		}
		if(StringUtil.isEmpty(getUser().getIdCard())){
			return null;
		}
		Pagination p = certificateInfoMng.myList(bean, sort, order, page, rows, hasRole("QU_ROLE"), isStreetRole(),
				getUser());
		return getJsonPagination(p, page);
	}
	
	@RequestMapping("/printQrcode/{id}")
	public String printQrcode(@PathVariable String id,Model model) {
		CertificateInfo bean = certificateInfoMng.get(id);
		model.addAttribute("bean", bean);
		return "/WEB-INF/view/swj/certificate/print";
	}
	
	@RequestMapping("/printQrcodeChild/{id}")
	public String printQrcodeChild(@PathVariable String id,Model model) {
		CertificateInfo bean = certificateInfoMng.get(id);
		model.addAttribute("bean", bean);
		if(bean!=null){
			String fileDirPath = getSaveQrImage();
			File outputFile = new File(fileDirPath+File.separator+bean.getId()+".gif");
			if(!outputFile.exists()){
				certificateInfoMng.genQrCode(bean.getId(), getUser(), request);
			}
		}
		model.addAttribute("curDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "/WEB-INF/view/swj/certificate/qrPrint";
	}

	
	@ArchivesLog(operationType = "print", operationName = "打印完成")
	@RequestMapping("/printComplete/{id}")
	@ResponseBody
	public Result printComplete(@PathVariable String id) {
		CertificateInfo bean = certificateInfoMng.get(id);
		if(bean!=null){
			bean.setHasPrinted("T");
			certificateInfoMng.merge(bean);
		}
		return getJsonResult(true, "打印完成");
	}
	
	@RequestMapping("/refJsonPagination")
	@ResponseBody
	public JsonPagination refJsonPagination(CertificateInfo bean, String sort, String order, Integer page, Integer rows,
			ModelMap model) {
		if (page == null) {
			page = 1;
		}
		if (rows == null) {
			rows = SimplePage.DEF_COUNT;
		}
		Pagination p = certificateInfoMng.refList(bean, sort, order, page, rows, hasRole("QU_ROLE"), isStreetRole(),
				getUser());
		return getJsonPagination(p, page);
	}
	
	@RequestMapping("/publicRefTip/{sfzhm}")
	@ResponseBody
	public Result publicRefTip(@PathVariable String sfzhm) {
		StringBuilder sb=new StringBuilder();
		List<CertificateInfo> list = certificateInfoMng.findPublicBySfzhm(sfzhm);
		if(list!=null&&list.size()>0){
			for (CertificateInfo bean : list) {
				if(bean.getIsBeyondSixMonth()!=null&&!bean.getIsBeyondSixMonth()){
					if(!StringUtil.isEmpty(sb.toString())){
						sb.append(",");
					}
					sb.append(bean.getCertificateType());
				}
			}
		}
		if(!StringUtil.isEmpty(sb.toString())){
			return getJsonResult(true, "该身份证号:"+sfzhm+"的"+sb.toString()+"的有效期不足6个月");
		}
		return getJsonResult(true, "");
	}
	
	private String getSaveQrImage(){
		String filePath = res.getString("upload.qrCode_path");
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		return filePath;
	}
	
	private String getFileSavePath(){
		String filePath = res.getString("upload.save_path");
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		return filePath;
	}
	
	
	/**
	 * 因私导入页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/privateImport")
	public String privateImport(Model model) {
		return "/WEB-INF/view/swj/certificate/private_imp";
	}
	
	@RequestMapping("/privateImportData")
	@ResponseBody
	public Result privateImportData(@RequestParam(value="privateFile",required=false) MultipartFile privateFile){
		int num=0;
		try {
			String fileType = privateFile.getOriginalFilename().substring(privateFile.getOriginalFilename().lastIndexOf(".")+1);
			if(!fileType.equals("xls")&&!fileType.equals("xlsx")){
				return getJsonResult(false,"请选择excel文件！");
			}else{
				num = certificateInfoMng.impPrivateData(privateFile,getUser());
			}
		} catch (Exception e) {
			log.error("CertificateInfoController privateImportData", e);
			return getJsonResult(false,e.getMessage());
		}
		return getJsonResult(true,"导入成功！本次更新"+num+"条数据！");
	}
	
	/**
	 * 因私台账导出
	 * @param model
	 * @return
	 */
	@RequestMapping("/privateExport")
	public String privateExport(ModelMap model){
		return "/WEB-INF/view/swj/certificate/private_exp";
	}
	
	@RequestMapping("/privateExportData")
	@ResponseBody
	public Result privateExportData(String startTime,String endTime,String certificateType,String exportType,HttpServletResponse response) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			response.setHeader("Content-Disposition","attachment; filename="+getFileName("因私导出台账")+".xls");   
			OutputStream out = new BufferedOutputStream(response.getOutputStream());   
			response.setContentType("application/octet-stream"); 
			String filePath=path+ File.separator +"template"+File.separator+"excel"+File.separator+"private_export.xls";
			List<PrivateExportEntity> exportList=certificateInfoApplyMng.listExportData(startTime,endTime,certificateType,exportType);
			HSSFWorkbook workbook=exportPrivateCertificateInfo(exportList,filePath);	
			workbook.write(out);
			out.flush();   
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return getJsonResult(false, "导出失败,请联系管理员！");
		}
		return getJsonResult(true, "导出成功");	
	}
	
	public HSSFWorkbook exportPrivateCertificateInfo(List<PrivateExportEntity> list, String filePath) throws Exception {
		File exlFile=new File(filePath);
		FileInputStream fis=new FileInputStream(exlFile);
		HSSFWorkbook wb  = new HSSFWorkbook(fis);
		HSSFSheet sheet=wb.getSheetAt(0);
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				PrivateExportEntity entity = list.get(i);
				HSSFRow hssfRow=sheet.createRow(2+i);
				HSSFCell cell1=hssfRow.createCell(0);
				cell1.setCellValue(i+1);
				HSSFCell cell2=hssfRow.createCell(1);
				cell2.setCellValue(entity.getName());
				HSSFCell cell3=hssfRow.createCell(2);
				cell3.setCellValue(entity.getDestination());
				HSSFCell cell4=hssfRow.createCell(3);
				cell4.setCellValue(entity.getReason());
				HSSFCell cell5=hssfRow.createCell(4);
				cell5.setCellValue(entity.getZjhm());
				HSSFCell cell6=hssfRow.createCell(5);
				cell6.setCellValue(entity.getStartDate());
				HSSFCell cell7=hssfRow.createCell(6);
				cell7.setCellValue(entity.getEndDate());
				HSSFCell cell8=hssfRow.createCell(7);
				cell8.setCellValue(entity.getRealUseTime());
				HSSFCell cell9=hssfRow.createCell(8);
				cell9.setCellValue(entity.getRealBackTime());
				HSSFCell cell10=hssfRow.createCell(9);
				cell10.setCellValue(StringUtil.isEmpty(entity.getRemark())?"":entity.getRemark());
				
			}
		}
		return wb;
	}
	
	
	/**
	 * 因公导入页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/publicImport")
	public String publicImport(Model model) {
		return "/WEB-INF/view/swj/certificate/public_imp";
	}
	
	/**
	 * 因公导入
	 * @param publicFile
	 * @return
	 */
	@RequestMapping("/publicImportData")
	@ResponseBody
	public Result publicImportData(@RequestParam(value="publicFile",required=false) MultipartFile publicFile){
		int num=0;
		try {
			String fileType = publicFile.getOriginalFilename().substring(publicFile.getOriginalFilename().lastIndexOf(".")+1);
			if(!fileType.equals("xls")&&!fileType.equals("xlsx")){
				return getJsonResult(false,"请选择excel文件！");
			}else{
				num = certificateInfoMng.impPublicData(publicFile,getUser());
			}
		} catch (Exception e) {
			log.error("CertificateInfoController publicImportData", e);
			return getJsonResult(false,e.getMessage());
		}
		return getJsonResult(true,"导入成功！本次更新"+num+"条数据！");
	}
	
	private boolean judgeCertificateInfoByPrivate(CertificateInfo bean){
		if(bean!=null){
			if(!StringUtil.isEmpty(bean.getCertificateType())){
				if(bean.getOwner()!=null&&!StringUtil.isEmpty(bean.getOwner().getId())){
					List<CertificateInfo> list = certificateInfoMng.findPrivateByTypeAndOwnId(new String[]{bean.getCertificateType()}, bean.getOwner().getId());
					if(list!=null&&list.size()>0){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean judgeCertificateInfoByPublic(CertificateInfo bean){
		if(bean!=null){
			if(!StringUtil.isEmpty(bean.getCertificateType())){
				if(!StringUtil.isEmpty(bean.getSfzhm())){
					CertificateInfo info = certificateInfoMng.findOneByTypeAndSfzhm(bean.getCertificateType(), bean.getSfzhm(),new String[]{CertificationStatus.WAIT.getItemValue(),CertificationStatus.CANCEL.getItemValue()});
					if(info!=null&&!StringUtil.isEmpty(info.getStatus())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	@RequestMapping("/privateInfoExport")
	public void privateInfoExport(CertificateInfo bean ,HttpServletResponse response) {
		try {
			List<CertificateInfo> certificateInfos = certificateInfoMng.exportCertificateInfo(bean, null, null, getUser());
			String path = request.getSession().getServletContext().getRealPath("/");
			response.setHeader("Content-Disposition","attachment; filename="+getFileName("证照信息（因私）导出")+".xls");   
			OutputStream out = new BufferedOutputStream(response.getOutputStream());   
			response.setContentType("application/octet-stream");   
			String filePath=path+ File.separator +"template"+File.separator+"excel"+File.separator+"private_info_export.xls";
			HSSFWorkbook workbook=exportPrivateInfos(certificateInfos,filePath);	
			workbook.write(out);
			out.flush();   
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HSSFWorkbook exportPrivateInfos(List<CertificateInfo> list,String filePath) throws IOException{
		File exlFile=new File(filePath);
		FileInputStream fis=new FileInputStream(exlFile);
		HSSFWorkbook wb  = new HSSFWorkbook(fis);
		HSSFSheet sheet=wb.getSheetAt(0);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size()&&i<65000; i++) {
				HSSFRow hssfRow=sheet.createRow(2+i);
				CertificateInfo info = list.get(i);
				HSSFCell cell1=hssfRow.createCell(0);
				HSSFCell cell2=hssfRow.createCell(1);
				HSSFCell cell3=hssfRow.createCell(2);
				HSSFCell cell4=hssfRow.createCell(3);
				HSSFCell cell5=hssfRow.createCell(4);
				HSSFCell cell6=hssfRow.createCell(5);
				HSSFCell cell7=hssfRow.createCell(6);
				HSSFCell cell8=hssfRow.createCell(7);
				//序号
				cell1.setCellValue((i+1)+"");
				//证照类型
				cell2.setCellValue(info.getCertificateTypeShow());
				//证件号码/护照号
				cell3.setCellValue(info.getZjhm()==null?"":info.getZjhm());
				//姓名
				cell4.setCellValue(info.getName()==null?"":info.getName());
				//性别
				cell5.setCellValue(info.getSex()==null?"":info.getSex());
				//身份证号码
				cell6.setCellValue(info.getSfzhm()==null?"":info.getSfzhm());
				//有效期限
				cell7.setCellValue(info.getEffectiveDate()==null?"":sdf.format(info.getEffectiveDate()));
				//状态
				cell8.setCellValue(info.getShowStatus()==null?"":info.getShowStatus());
				
			}
		}
		return wb;
		
	}
	
	@RequestMapping("/expireScan/{id}")
	public String expireScan(Model model,@PathVariable("id") String id){
		model.addAttribute("expireId", id);
		return "/WEB-INF/view/swj/certificate/expireScan";
	}
	
	/**
	 * 过期领回扫描保存
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/expireScanSave/{id}")
	@ResponseBody
	public Result expireScanSave(@PathVariable String id){
		try {
			certificateInfoMng.saveExpireScan(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
}
