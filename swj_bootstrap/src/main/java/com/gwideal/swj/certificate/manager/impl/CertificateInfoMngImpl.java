package com.gwideal.swj.certificate.manager.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.gwideal.attachment.manager.AttachmentMng;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.util.POIUtil;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.DepartMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.CertificateOperateLog;
import com.gwideal.swj.certificate.entity.CertificationStatus;
import com.gwideal.swj.certificate.entity.CertificationTypeEnum;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;
import com.gwideal.swj.certificate.manager.CertificateOperateLogMng;
import com.gwideal.swj.certificate.manager.MatrixToImageWriter;

@Transactional
@Service
public class CertificateInfoMngImpl extends BaseManagerImpl<CertificateInfo> implements CertificateInfoMng {
	
	@Autowired
	private AttachmentMng attachmentMng;
	@Autowired
	private UserMng userMng;
	@Autowired
	private DepartMng departMng;
	@Autowired
	private CertificateOperateLogMng certificateOperateLogMng;
	private static final ResourceBundle res=ResourceBundle.getBundle("upload");

	@Override
	public Pagination list(CertificateInfo bean, String sort, String order,
			int pageIndex, int pageSize, boolean isQuRole,
			boolean isStreetRole, User user) {
		Finder f = Finder.create("from CertificateInfo where flag=1 ");
		if (bean!=null) {
			if(StringUtil.isEmpty(bean.getBusinessType())){
				//业务类型缺失
				return null;
			}else{
				if("public".equals(bean.getBusinessType())){
					f.append(" and certificateType  in :typeList").setParamList("typeList",Arrays.asList(new String[]{CertificationTypeEnum.publicPassport.getItemValue(),CertificationTypeEnum.publicHKAndMacaoPass.getItemValue(),CertificationTypeEnum.publicTaiwanPass.getItemValue()}));
				}else if("private".equals(bean.getBusinessType())){
					f.append(" and certificateType  in :typeList").setParamList("typeList",Arrays.asList(new String[]{CertificationTypeEnum.privatePassport.getItemValue(),CertificationTypeEnum.privateHKAndMacaoPass.getItemValue(),CertificationTypeEnum.privateTaiwanPass.getItemValue()}));
				}
			}
			if (!StringUtil.isEmpty(bean.getCertificateType())) {
				f.append(" and certificateType = :certificateType").setParam("certificateType",bean.getCertificateType());
			}
			if (!StringUtil.isEmpty(bean.getZjhm())) {
				f.append(" and zjhm like :zjhm").setParam("zjhm","%"+bean.getZjhm()+"%");
			}
			if (!StringUtil.isEmpty(bean.getName())) {
				f.append(" and name like :name").setParam("name","%"+bean.getName()+"%");
			}
			if (!StringUtil.isEmpty(bean.getSfzhm())) {
				f.append(" and sfzhm like :sfzhm").setParam("sfzhm","%"+bean.getSfzhm()+"%");
			}
			if(StringUtil.isEmpty(bean.getStatus())){
				f.append(" and status != :status").setParam("status",CertificationStatus.CANCEL.getItemValue());
				f.append(" and status != :status1").setParam("status1",CertificationStatus.WAIT.getItemValue());
			}else{
				f.append(" and status = :status").setParam("status",bean.getStatus());
			}
			if(!StringUtil.isEmpty(bean.getBelongDepartId())){
				f.append(" and belongDepart.id = :belongDepartId").setParam("belongDepartId",bean.getBelongDepartId());
			}
//			if (!StringUtil.isEmpty(bean.getIssuanceAuthority())) {
//				f.append(" and issuanceAuthority like :issuanceAuthority").setParam("issuanceAuthority","%"+bean.getIssuanceAuthority()+"%");
//			}
//			if (!StringUtil.isEmpty(bean.getIssuancePlace())) {
//				f.append(" and issuancePlace like :issuancePlace").setParam("issuancePlace","%"+bean.getIssuancePlace()+"%");
//			}
//			if (!StringUtil.isEmpty(bean.getNationCode())) {
//				f.append(" and nationCode like :nationCode").setParam("nationCode","%"+bean.getNationCode()+"%");
//			}
//			if (!StringUtil.isEmpty(bean.getType())) {
//				f.append(" and type like :type").setParam("type","%"+bean.getType()+"%");
//			}
			if(!StringUtil.isEmpty(bean.getHasPrinted())){
				f.append(" and hasPrinted = :hasPrinted").setParam("hasPrinted",bean.getHasPrinted());
			}
			if(!StringUtil.isEmpty(bean.getIsNotBack())){
				StringBuilder sb=new StringBuilder();
				sb.append(" SELECT C.pid");
				sb.append(" FROM T_PUBLIC_BUSINESS A INNER JOIN T_PUBLIC_BUSINESS_CERTIFICATE B ON A.pid = B.PUBLIC_BUSINESS_ID ");
				sb.append(" INNER JOIN T_CERTIFICATE_INFO C ON B.CERTIFICATE_ID = C.pid ");
				sb.append(" WHERE A.PFLAG = '1' AND C.PFLAG = '1' AND to_date( to_char( A.PLAN_ENTER_TIME, 'yyyy-MM-dd' ), 'yyyy-mm-dd' ) < to_date( to_char( SYSDATE - 7, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )  ");
				sb.append(" AND ( A.BACK_STATUS IS NULL OR A.BACK_STATUS != '归还完成' ) AND C.STATUS = 'using'");
				if(!user.hasRole("PUBLIC_JBGSGZRY")){
					sb.append(" and C.OWN_ID = '"+user.getId()+"'");
				}
				sb.append(" order by C.PUPDATETIME desc ");
				List<String> notBackIdList = super.getSession().createSQLQuery(sb.toString()).list();
				if("1".equals(bean.getIsNotBack())){
					if(notBackIdList!=null&&notBackIdList.size()>0){
						f.append(" and  id in :idList").setParamList("idList", Arrays.asList(notBackIdList.toArray(new String[notBackIdList.size()])));
					}else{
						f.append(" and  id = :id").setParam("id", "1");//不存在的一个id or return 
					}
				}else if("0".equals(bean.getIsNotBack())){
					if(notBackIdList!=null&&notBackIdList.size()>0){
						f.append(" and  id not in :idList").setParamList("idList", Arrays.asList(notBackIdList.toArray(new String[notBackIdList.size()])));
					}
				}
				
				
			}
		}
		if(!StringUtil.isEmpty(sort)&&!StringUtil.isEmpty(order)){
			f.append(" order by "+sort+" "+order);
		}else{
			f.append(" order by effectiveDate");
		}
		return super.find(f,pageIndex, pageSize);
	}
	
	@Override
	public void save(CertificateInfo bean, User user) throws Exception{
		if(StringUtil.isEmpty(bean.getId())) {
			bean.setCreator(user);
			bean.setHasPrinted("F");
			bean.setStatus(CertificationStatus.INIT.getItemValue());//初始状态
		}else{
			bean.setUpdator(user);			
		}
		CertificateInfo certificateInfo = findOneByThreeCondition(bean.getCertificateType(), bean.getSfzhm(), bean.getZjhm());
		//新增同号码的证照判断
		if(StringUtil.isEmpty(bean.getId())&&certificateInfo!=null){
			if(CertificationStatus.CANCEL.getItemValue().equals(certificateInfo.getStatus())){
				throw new Exception("该证照已存在，请到“证照信息（因私-注销）”菜单下查看");
			}else{
				throw new Exception("该证照已存在，请到“证照信息（因私）”菜单下查看");
			}
		}
		//不能一条数据修改为另一条数据已存在的
		if(bean!=null&&certificateInfo!=null&&!StringUtil.isEmpty(bean.getId())&&!StringUtil.isEmpty(certificateInfo.getId())){
			if(!bean.getId().equals(certificateInfo.getId())){
				throw new Exception("修改的证照信息已存在");
			}
		}
		if(certificateInfo==null){
			certificateInfo =(CertificateInfo) super.merge(bean);
		}else{
			BeanUtils.copyProperties(bean, certificateInfo, new String[]{"id","status","realUseTime","realBackTime","qrCodeUrl","hasPrinted"});
			super.update(certificateInfo);
		}
		CertificateOperateLog operateLog=null;
		if(StringUtil.isEmpty(bean.getId())) {
			operateLog=new CertificateOperateLog("new","新增",user,new Date(),certificateInfo);
		}else{
			operateLog=new CertificateOperateLog("update","修改",user,new Date(),certificateInfo);		
		}
		if(operateLog!=null){
			certificateOperateLogMng.save(operateLog);
		}
		
	}
	
	@Override
	public void delete(String id, User user) {
		if(!StringUtil.isEmpty(id)) {
			CertificateInfo bean=super.findById(id);
			bean.setFlag(0);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			super.save(bean);
		}
	}

	@Override
	public CertificateInfo findOneByThreeCondition(String certificateType, String sfzhm, String zjhm) throws Exception {
		Finder f = Finder.create("from CertificateInfo where flag=1 ");
//		if(!StringUtil.isEmpty(certificateType)){
//			f.append(" and certificateType = :certificateType").setParam("certificateType",certificateType);
//		}
//		if(!StringUtil.isEmpty(sfzhm)){
//			f.append(" and sfzhm = :sfzhm").setParam("sfzhm",sfzhm);
//		}
		if(!StringUtil.isEmpty(zjhm)){
			f.append(" and zjhm = :zjhm").setParam("zjhm",zjhm);
		}else{
			throw new Exception("证照的证件号码为空");
		}
		List list = super.find(f);
		if(list!=null&&list.size()>0){
			return (CertificateInfo) list.get(0);
		}
		return null;
	}

	@Override
	public void cancel(String id, User user) {
		if(!StringUtil.isEmpty(id)) {
			CertificateInfo bean=super.findById(id);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			bean.setStatus(CertificationStatus.CANCEL.getItemValue());
			bean.setCancelUser(user);
			bean.setCancelTime(new Date());
			super.save(bean);
		}
		
	}
	
	/**
	 * 生成二维码
	 * @return
	 */
	private String genQrCodeUrl(String QrCode,String certificateType,String zjhm,String sfzhm,String name,HttpServletRequest request){
		String text=QrCode;
		String path="";
	    int width = 200;    
	    int height = 200;    
	    String format = "gif";    
	    try {
			Hashtable hints = new Hashtable();    
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");    
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text,    
			        BarcodeFormat.QR_CODE, width, height, hints);
			bitMatrix=reduceWhite(bitMatrix, 8);
			//String basePath = request.getSession().getServletContext().getRealPath("/");
			String fileDirPath = res.getString("upload.qrCode_path");
			File outputFile = new File(fileDirPath+File.separator+QrCode+".gif");  
			if(!outputFile.exists()){
				MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			}
			path=QrCode+".gif";
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return path;
	}
	 private static BitMatrix reduceWhite(BitMatrix matrix, int margin) {
	        int tempM = margin * 2;
	        int[] rec = matrix.getEnclosingRectangle(); // 获取二维码图案的属性
	        int resWidth = rec[2] + tempM;
	        int resHeight = rec[3] + tempM;
	        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
	        resMatrix.clear();
	        for (int i = margin; i < resWidth - margin; i++) { // 循环，将二维码图案绘制到新的bitMatrix中
	            for (int j = margin; j < resHeight - margin; j++) {
	                if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
	                    resMatrix.set(i, j);
	                }
	            }
	        }
	        return resMatrix;
	   }
	

	@Override
	public String genQrCode(String id, User user,HttpServletRequest request) {
		CertificateInfo bean=super.findById(id);
		String QrCode=id;
		if(bean!=null&&!StringUtil.isEmpty(QrCode)){
			String qrCodeUrl = genQrCodeUrl(QrCode, bean.getCertificateType(), bean.getZjhm(), bean.getSfzhm(), bean.getName(),request);
			bean.setQrCodeUrl(qrCodeUrl);
			super.update(bean);
			return qrCodeUrl;
		}
		return null;
	}

	@Override
	public CertificateInfo findOneByTypeAndSfzhm(String certificateType, String sfzhm,String[] statusArr) {
		Finder f = Finder.create("from CertificateInfo where flag=1 ");
		f.append(" and certificateType = :certificateType").setParam("certificateType",certificateType);
		f.append(" and sfzhm = :sfzhm").setParam("sfzhm",sfzhm);
		if(statusArr!=null&&statusArr.length>0){
			f.append(" and status not in :statusList").setParamList("statusList", Arrays.asList(statusArr));
		}
		f.append(" order by updateTime desc");
		List list = super.find(f);
		if(list!=null&&list.size()>0){
			return (CertificateInfo) list.get(0);
		}
		return null;
	}

	@Override
	public void checkIn(String id, User user) {
		if(!StringUtil.isEmpty(id)) {
			CertificateInfo bean=super.findById(id);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			bean.setStatus(CertificationStatus.CHECKIN.getItemValue());
			super.save(bean);
		}
		
	}

	@Override
	public Pagination myList(CertificateInfo bean, String sort, String order, Integer page, Integer rows,
			boolean hasRole, boolean streetRole, User user) {
		Finder f = Finder.create("from CertificateInfo where flag=1 ");
		if (bean!=null) {
			//只查因私的
			f.append(" and certificateType  in :typeList").setParamList("typeList",Arrays.asList(new String[]{CertificationTypeEnum.privatePassport.getItemValue(),CertificationTypeEnum.privateHKAndMacaoPass.getItemValue(),CertificationTypeEnum.privateTaiwanPass.getItemValue()}));
			if (!StringUtil.isEmpty(bean.getCertificateType())) {
				f.append(" and certificateType = :certificateType").setParam("certificateType",bean.getCertificateType());
			}
			f.append(" and status != :status").setParam("status",CertificationStatus.CANCEL.getItemValue());
			f.append(" and owner.id =:ownId").setParam("ownId",user.getId());
		}
		if(!StringUtil.isEmpty(sort)&&!StringUtil.isEmpty(order)){
			f.append(" order by "+sort+" "+order);
		}else{
			f.append(" order by updateTime desc");
		}
		return super.find(f,page, rows);
	}

	@Override
	public void savePublic(CertificateInfo bean, MultipartFile[] publicFiles,String savePath, User user) throws Exception {
		if(StringUtil.isEmpty(bean.getId())) {
			bean.setCreator(user);
			bean.setHasPrinted("F");
			bean.setStatus(CertificationStatus.INIT.getItemValue());//初始状态
		}else{
			bean.setUpdator(user);			
		}
		if(bean.getOwner()!=null&&StringUtil.isEmpty(bean.getOwner().getId())){
			bean.setOwner(null);
		}
		CertificateInfo certificateInfo = findOneByThreeCondition(bean.getCertificateType(), bean.getSfzhm(), bean.getZjhm());
		//新增同号码的证照判断
		if(StringUtil.isEmpty(bean.getId())&&certificateInfo!=null){
			if(CertificationStatus.WAIT.getItemValue().equals(certificateInfo.getStatus())){
				throw new Exception("该证照已存在，请到“证照信息（因公-待注销）”菜单下查看");
			}else if(CertificationStatus.CANCEL.getItemValue().equals(certificateInfo.getStatus())){
				throw new Exception("该证照已存在，请到“证照信息（因公-已注销）”菜单下查看");
			}else{
				throw new Exception("该证照已存在，请到“证照信息（因公）”菜单下查看");
			}
		}
		//不能一条数据修改为另一条数据已存在的
		if(bean!=null&&certificateInfo!=null&&!StringUtil.isEmpty(bean.getId())&&!StringUtil.isEmpty(certificateInfo.getId())){
			if(!bean.getId().equals(certificateInfo.getId())){
				throw new Exception("修改的证照信息已存在");
			}
		}
		if(certificateInfo==null){
			certificateInfo = (CertificateInfo) super.merge(bean);
		}else{
			BeanUtils.copyProperties(bean, certificateInfo, new String[]{"id","status","realUseTime","realBackTime","qrCodeUrl","hasPrinted"});
			super.update(certificateInfo);
		}
		//保存上传附件
		attachmentMng.upload(certificateInfo,null,publicFiles,savePath,user);
		CertificateOperateLog operateLog=null;
		if(StringUtil.isEmpty(bean.getId())) {
			operateLog=new CertificateOperateLog("new","新增",user,new Date(),certificateInfo);
		}else{
			operateLog=new CertificateOperateLog("update","修改",user,new Date(),certificateInfo);		
		}
		if(operateLog!=null){
			certificateOperateLogMng.save(operateLog);
		}
	}

	@Override
	public Pagination refList(CertificateInfo bean, String sort, String order, Integer page, Integer rows,
			boolean hasRole, boolean streetRole, User user) {
		Finder f = Finder.create("from CertificateInfo where flag=1 ");
		if (bean!=null) {
			if(StringUtil.isEmpty(bean.getBusinessType())){
				//业务类型缺失
				return null;
			}else{
				if("public".equals(bean.getBusinessType())){
					f.append(" and certificateType  in :typeList").setParamList("typeList",Arrays.asList(new String[]{CertificationTypeEnum.publicPassport.getItemValue(),CertificationTypeEnum.publicHKAndMacaoPass.getItemValue(),CertificationTypeEnum.publicTaiwanPass.getItemValue()}));
				}
			}
			if (!StringUtil.isEmpty(bean.getCertificateType())) {
				f.append(" and certificateType = :certificateType").setParam("certificateType",bean.getCertificateType());
			}
			if (!StringUtil.isEmpty(bean.getZjhm())) {
				f.append(" and zjhm like :zjhm").setParam("zjhm","%"+bean.getZjhm()+"%");
			}
			if (!StringUtil.isEmpty(bean.getName())) {
				f.append(" and name like :name").setParam("name","%"+bean.getName()+"%");
			}
			//身份证号精确查询
			if (!StringUtil.isEmpty(bean.getSfzhm())) {
				f.append(" and sfzhm = :sfzhm").setParam("sfzhm",bean.getSfzhm());
			}
			if(StringUtil.isEmpty(bean.getStatus())){
				f.append(" and status != :status").setParam("status",CertificationStatus.CANCEL.getItemValue());
				f.append(" and status != :status1").setParam("status1",CertificationStatus.WAIT.getItemValue());
			}else{
				f.append(" and status = :status").setParam("status",bean.getStatus());
			}
			if(!StringUtil.isEmpty(bean.getBelongDepartId())){
				f.append(" and belongDepart.id = :belongDepartId").setParam("belongDepartId",bean.getBelongDepartId());
			}
			f.append(" and effectiveDate >= :nowSixMonthDate").setParam("nowSixMonthDate", DateUtils.add(new Date(),Calendar.MONTH,6));
		}
		if(!StringUtil.isEmpty(sort)&&!StringUtil.isEmpty(order)){
			f.append(" order by "+sort+" "+order);
		}else{
			f.append(" order by updateTime desc");
		}
		return super.find(f,page, rows);
	}

	@Override
	public List<CertificateInfo> findPublicBySfzhm(String sfzhm) {
		Finder f = Finder.create("from CertificateInfo where flag=1 and status !='cancelled' ");
		f.append(" and certificateType  in :typeList").setParamList("typeList",Arrays.asList(new String[]{CertificationTypeEnum.publicPassport.getItemValue(),CertificationTypeEnum.publicHKAndMacaoPass.getItemValue(),CertificationTypeEnum.publicTaiwanPass.getItemValue()}));
		if (!StringUtil.isEmpty(sfzhm)) {
			f.append(" and sfzhm = :sfzhm").setParam("sfzhm",sfzhm);
		}
		return super.find(f);
	}

	@Override
	public Result isValidEffectiveDate(String certificateType, Date startDate,User user) {
		// TODO Auto-generated method stub
		//有“在库”的证照才能领用
		String hql="from CertificateInfo Where flag=1 and certificateType in ("+StringUtil.getSplitString(certificateType,",")+") and owner.id=? and status='checkIn'";
		List<CertificateInfo> list=super.find(hql,new Object[]{user.getId()});
		if(null!=list && list.size()>0){
			String[] arr=certificateType.split(",");
			if(list.size()<arr.length){
				return new Result(false,"您申请的证照数量和数据库证照数量不匹配，不能申请领用！");
			}
			List<String> listStatus=new ArrayList<String>();//一个证照可能存在两条“在库”状态的数据，需要排除
			boolean expire=false;
			for(CertificateInfo c:list){
				int days=com.gwideal.common.util.DateUtils.getDays(startDate,c.getEffectiveDate());
				if(days<180){
					expire=true;
				}
				if(!listStatus.contains(c.getCertificateType())){
					listStatus.add(c.getCertificateType());
				}
			}
			if(listStatus.size()<arr.length){
				return new Result(false,"您申请的证照数量和数据库证照数量不匹配，不能申请领用！");
			}
			if(expire){
				return new Result(false,"您存在即将过期的证照信息，不能申请领用！");
			}
		}else{
			return new Result(false,"您没有“在库”状态的证照信息，不能申请领用！");
		}
		return new Result(true,"可以申请领用！");
	}

	@Override
	public boolean isExistOldPassport(String certificateType, User user) {
		// TODO Auto-generated method stub
		//有“在库”的证照才能领回
		String hql="from CertificateInfo Where flag=1 and certificateType in ("+StringUtil.getSplitString(certificateType,",")+") and owner.id=? and status='checkIn'";
		List<CertificateInfo> list=super.find(hql,new Object[]{user.getId()});
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
	public List<CertificateInfo> findPrivateByTypeAndOwnId(String[] typeArr, String ownId) {
		Finder f = Finder.create("from CertificateInfo where flag=1 and status !='cancelled'");
		f.append(" and certificateType  in :typeList").setParamList("typeList",Arrays.asList(typeArr));
		f.append(" and owner.id = :ownId").setParam("ownId",ownId);
		return super.find(f);
	}

	@Override
	public int impPrivateData(MultipartFile privateFile, User user) throws Exception {
		int num=0;
		Workbook hssfWorkbook = null;
		try {
			InputStream fis= privateFile.getInputStream();
			if (privateFile.getOriginalFilename().indexOf(".xlsx") == -1) {
				hssfWorkbook = new HSSFWorkbook(fis);
			} else {
				hssfWorkbook = new XSSFWorkbook(fis);
			}
			Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
			Row hssfRow = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			hssfRow = hssfSheet.getRow(1);
			int sumRows = hssfSheet.getPhysicalNumberOfRows();
			if(!"序号".equals(getValue(hssfRow.getCell(0)))||!"证照类型".equals(getValue(hssfRow.getCell(1)))||
					!"证件号码/护照号".equals(getValue(hssfRow.getCell(2)))||
					!"姓名".equals(getValue(hssfRow.getCell(3)))||!"性别".equals(getValue(hssfRow.getCell(4)))||
					!"身份证号码".equals(getValue(hssfRow.getCell(5)))||
					!"签发日期".equals(getValue(hssfRow.getCell(6)))||!"签发地点".equals(getValue(hssfRow.getCell(7)))
					||!"有效期至".equals(getValue(hssfRow.getCell(8)))||!"通行证种类/护照类型".equals(getValue(hssfRow.getCell(9)))
					){
				throw new Exception("导入模板不对");
			}
			StringBuilder checkOnlySb=new StringBuilder();
			List<CertificateInfo> importInfoList=new ArrayList<>();
			Depart depart = departMng.findByCode("SHSSWJ");
			Pattern pattern = Pattern.compile("\\^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$");
			for (int rowNum = 2; rowNum <= sumRows; rowNum++){
				hssfRow=hssfSheet.getRow(rowNum);
				if(hssfRow==null||StringUtil.isEmpty(getValue(hssfRow.getCell(1)))){
					continue;
				}
				String certificateType = getValue(hssfRow.getCell(1));
				String idCard=getValue(hssfRow.getCell(5));
				String name = getValue(hssfRow.getCell(3));
				String zjhm = getValue(hssfRow.getCell(2));
				String sex=getValue(hssfRow.getCell(4));
				String signDateStr=getValue(hssfRow.getCell(6));
				String signPlace=getValue(hssfRow.getCell(7));
				String effectiveDateStr =getValue(hssfRow.getCell(8));
				String kindType=getValue(hssfRow.getCell(9));
				Date effectiveDate=null;
				Date signDate=null;
				if(StringUtil.isEmpty(certificateType)){
					throw new Exception("第"+(rowNum+1)+"行，证照类型不能为空");
				}
				if(StringUtil.isEmpty(zjhm)){
					throw new Exception("第"+(rowNum+1)+"行，证件号码/护照号不能为空");
				}
				if(StringUtil.isEmpty(name)){
					throw new Exception("第"+(rowNum+1)+"行，姓名不能为空");
				}
				if(StringUtil.isEmpty(idCard)){
					throw new Exception("第"+(rowNum+1)+"行，身份证号码不能为空");
				}
				if(StringUtil.isEmpty(effectiveDateStr)){
					throw new Exception("第"+(rowNum+1)+"行，有效期至不能为空");
				}
				if(StringUtil.isEmpty(kindType)){
					throw new Exception("第"+(rowNum+1)+"行，通行证种类/护照类型不能为空");
				}
				String realCertificateType="";
				signDateStr=signDateStr.replaceAll("\\.", "-");
				effectiveDateStr=effectiveDateStr.replaceAll("\\.", "-");
				if(certificateType.contains("普通")){
					realCertificateType=CertificationTypeEnum.privatePassport.getItemValue();
				}else if(certificateType.contains("港澳")){
					realCertificateType=CertificationTypeEnum.privateHKAndMacaoPass.getItemValue();
				}else if(certificateType.contains("台湾")){
					realCertificateType=CertificationTypeEnum.privateTaiwanPass.getItemValue();
				}
				if(StringUtil.isEmpty(realCertificateType)){
					throw new Exception("第"+(rowNum+1)+"行，证照类型数据有误");
				}
				Matcher matcher = pattern.matcher(idCard);
				if(!matcher.matches()){
					throw new Exception("第"+(rowNum+1)+"行，身份证号码数据不合法");
				}
				try{
					effectiveDate = sdf.parse(effectiveDateStr);
				}catch(ParseException e){
					throw new Exception("第"+(rowNum+1)+"行，有效期至数据不合法");
				}
				if(!StringUtil.isEmpty(signDateStr)){
					try{
						signDate=sdf.parse(signDateStr);
					}catch(ParseException e){
						throw new Exception("第"+(rowNum+1)+"行，签发日期数据不合法");
					}
				}
				if(!StringUtil.isEmpty(checkOnlySb.toString())){
					if(checkOnlySb.toString().contains(zjhm)){
						throw new Exception("第"+(rowNum+1)+"行，证件号码重复");
					}
				}
				User owner = userMng.findByUserNameAndIdCard(name, idCard);
				if(owner!=null){
					List<CertificateInfo> ownIdList = findPrivateByTypeAndOwnId(new String[]{realCertificateType}, owner.getId());
					if(ownIdList!=null&&ownIdList.size()>0){
						throw new Exception("第"+(rowNum+1)+"行，对不起，该人已有一本同类型的证照");
					}
				}else{
					throw new Exception("第"+(rowNum+1)+"行，对不起，该身份证号尚未绑定用户，请到用户管理菜单进行绑定操作");
				}
				checkOnlySb.append(zjhm).append(",");
				CertificateInfo info = this.findOneByThreeCondition(realCertificateType, idCard, zjhm);
				if(info==null){
					info=new CertificateInfo();
					info.setStatus(CertificationStatus.INIT.getItemValue());
					info.setBelongDepart(depart);
					info.setHasPrinted("F");
					String birthDateStr=idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14);
					info.setBirthDate(sdf.parse(birthDateStr));
					info.setCreator(user);
				}else{
					info.setUpdator(user);
				}
				info.setCertificateType(realCertificateType);
				info.setEffectiveDate(effectiveDate);
				info.setZjhm(zjhm);
				info.setName(name);
				info.setSex(sex);
				info.setIssuanceDate(signDate);
				info.setIssuancePlace(signPlace);
				info.setType(kindType);
				if(owner!=null){
					info.setOwner(owner);
				}
				info.setSfzhm(idCard);
				if(effectiveDate!=null){
					if(effectiveDate.compareTo(new Date())<0){
						info.setStatus(CertificationStatus.CANCEL.getItemValue());
					}
				}
				importInfoList.add(info);
			}
			//
			if(importInfoList!=null&&importInfoList.size()>0){
				for (CertificateInfo info : importInfoList) {
					this.merge(info);
					num++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}finally{
			if(hssfWorkbook!=null){
				hssfWorkbook.close();
			}
		}
		return num;
	}
	
	
	@SuppressWarnings("deprecation")
	private String getValue(Cell hssfCell) {
        if (null == hssfCell) {
       	 return "";
        }
        switch (hssfCell.getCellType()) {
        case Cell.CELL_TYPE_BLANK:
       	 return "";
        case Cell.CELL_TYPE_STRING:
       	 return "/".equals(String.valueOf(hssfCell.getStringCellValue()).trim())?"":String.valueOf(hssfCell.getStringCellValue());
        case Cell.CELL_TYPE_NUMERIC:
       	 if (hssfCell.getNumericCellValue() == 0) {
           	  return "0";
            }
       	 if (String.valueOf(hssfCell.getNumericCellValue()).indexOf("E") != -1) {
       		 return new DecimalFormat("#").format(hssfCell.getNumericCellValue());
       	 }
       	 if (String.valueOf(hssfCell.getNumericCellValue()).indexOf(".") != -1) {
       		 return String.valueOf(hssfCell.getNumericCellValue()).substring(0, String.valueOf(hssfCell.getNumericCellValue()).indexOf("."));
       	 }
       	 return String.valueOf(hssfCell.getNumericCellValue());
        default:
            return String.valueOf(hssfCell.getNumericCellValue());
        }
    }

	@Override
	public int impPublicData(MultipartFile publicFile, User user) throws Exception {
		int num=0;
		Workbook hssfWorkbook = null;
		try{
			InputStream fis= publicFile.getInputStream();
			if (publicFile.getOriginalFilename().indexOf(".xlsx") == -1) {
				hssfWorkbook = new HSSFWorkbook(fis);
			} else {
				hssfWorkbook = new XSSFWorkbook(fis);
			}
			Sheet sheetOne = hssfWorkbook.getSheetAt(0);//局机关护照 因公普通护照
			Sheet sheetTwo = hssfWorkbook.getSheetAt(1);//局直属单位
			Sheet sheetThree = hssfWorkbook.getSheetAt(2);//其他证件
			Sheet sheetFour=hssfWorkbook.getSheetAt(3);//局机关
			List<CertificateInfo> importInfoList=new ArrayList<>();
			//处理第一个sheet
			Row hssfRow = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int sumRows = sheetOne.getPhysicalNumberOfRows();
			for (int rowNum = 2; rowNum <= sumRows; rowNum++){
				hssfRow=sheetOne.getRow(rowNum);
				if(hssfRow==null||StringUtil.isEmpty(getValue(hssfRow.getCell(1)))){
					continue;
				}
				String name= getValue(hssfRow.getCell(1)).trim();//姓名
				String birthDateStr= POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(2));//出生日期
				String zjhm=getValue(hssfRow.getCell(3)).trim();//证件号码
				String signDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(4));//签发日期
				String effectiveDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(5));//有效期
				String idCard=getValue(hssfRow.getCell(11));//身份证号
				if(!StringUtil.isEmpty(signDateStr)){
					signDateStr=signDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(birthDateStr)){
					birthDateStr=birthDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(effectiveDateStr)){
					effectiveDateStr=effectiveDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				CertificateInfo info = this.findOneByZjhmAndName(name, zjhm);
				info=packageCertificateInfo(info, name,zjhm, idCard, birthDateStr, signDateStr, effectiveDateStr, sdf);
				importInfoList.add(info);
			}
			//处理第二个sheet
			sumRows = sheetTwo.getPhysicalNumberOfRows();
			for (int rowNum = 1; rowNum <= sumRows; rowNum++) {
				hssfRow=sheetTwo.getRow(rowNum);
				if(hssfRow==null||StringUtil.isEmpty(getValue(hssfRow.getCell(2)))){
					continue;
				}
				String name= getValue(hssfRow.getCell(2)).trim();//姓名
				String birthDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(3));//出生日期
				String zjhm=getValue(hssfRow.getCell(4)).trim();//证件号码
				String signDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(5));//发证日期
				String effectiveDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(6));//有效期
				String idCard=getValue(hssfRow.getCell(10));//身份证号
				String belongDepartStr=getValue(hssfRow.getCell(11));//所属单位
				Depart belongDepart=null;
				if(!StringUtil.isEmpty(signDateStr)){
					signDateStr=signDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(birthDateStr)){
					birthDateStr=birthDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(effectiveDateStr)){
					effectiveDateStr=effectiveDateStr.replaceAll("\\/", "-").replaceAll(",", "").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(belongDepartStr)){
					belongDepartStr=belongDepartStr.trim();
					belongDepart=this.findByDepartName(belongDepartStr);
				}else{
					belongDepart=departMng.findByCode("SHSSWJ");
				}
				CertificateInfo info=null;
				name=name.replaceAll("\\d+", "").replaceAll("\\*", "");
				if(!StringUtil.isEmpty(zjhm)){
					if(zjhm.contains("/")){
						String[] zjhmArr = zjhm.split("/");
						for (int i = 0; i < zjhmArr.length; i++) {
							info = this.findOneByZjhmAndName(name, zjhmArr[i]);
							info=packageCertificateInfo(info,name, zjhmArr[i], idCard, birthDateStr, signDateStr, effectiveDateStr, sdf);
							if(belongDepart!=null){
								info.setBelongDepart(belongDepart);
							}
							importInfoList.add(info);
						}
					}else{
						info = this.findOneByZjhmAndName(name,zjhm);
						info=packageCertificateInfo(info,name, zjhm, idCard, birthDateStr, signDateStr, effectiveDateStr, sdf);
						if(belongDepart!=null){
							info.setBelongDepart(belongDepart);
						}
						importInfoList.add(info);
					}
				}else{
					info=packageCertificateInfo(info,name,zjhm,  idCard, birthDateStr, signDateStr, effectiveDateStr, sdf);
					if(belongDepart!=null){
						info.setBelongDepart(belongDepart);
					}
					importInfoList.add(info);
				}
				
			}
			//处理第三个sheet（港澳台）
			sumRows = sheetThree.getPhysicalNumberOfRows();
			for (int rowNum = 1; rowNum <= sumRows; rowNum++){
				hssfRow=sheetThree.getRow(rowNum);
				if(hssfRow==null||StringUtil.isEmpty(getValue(hssfRow.getCell(1)))){
					continue;
				}
				if("其他".equals(getValue(hssfRow.getCell(0)))){
					break;
				}
				String name=getValue(hssfRow.getCell(1)).trim();//姓名
				String zjhm=getValue(hssfRow.getCell(2));//证件号码
				String certificateType=getValue(hssfRow.getCell(4));//证件类型
				String signDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(5));//签发日期
				String effectiveDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(6));//效期
				if(!StringUtil.isEmpty(signDateStr)){
					signDateStr=signDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(effectiveDateStr)){
					effectiveDateStr=effectiveDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				CertificateInfo info=null;
				info = this.findOneByZjhmAndName(name, zjhm);
				if(info==null){
					info=new CertificateInfo();
					info.setStatus(CertificationStatus.INIT.getItemValue());
					info.setName(name);
					info.setZjhm(zjhm);
					info.setHasPrinted("F");
				}
				if("港澳通行证".equals(certificateType)){
					info.setCertificateType(CertificationTypeEnum.publicHKAndMacaoPass.getItemValue());
				}else if("台湾通行证".equals(certificateType)){
					info.setCertificateType(CertificationTypeEnum.publicTaiwanPass.getItemValue());
				}
				info.setIssuanceDate(sdf.parse(signDateStr));
				Date effectDate = sdf.parse(effectiveDateStr);
				info.setEffectiveDate(effectDate);
				if(effectDate!=null){
					if(effectDate.compareTo(new Date())<0){
						info.setStatus(CertificationStatus.CANCEL.getItemValue());
					}
				}
				importInfoList.add(info);
				
			}
			//处理第四个sheet
			sumRows = sheetFour.getPhysicalNumberOfRows();
			for (int rowNum = 2; rowNum <= sumRows; rowNum++) {
				hssfRow=sheetFour.getRow(rowNum);
				if(hssfRow==null||StringUtil.isEmpty(getValue(hssfRow.getCell(3)))){
					continue;
				}
				String name= getValue(hssfRow.getCell(3)).trim();//姓名
				String birthDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(4));//出生日期
				String zjhm=getValue(hssfRow.getCell(5)).trim();//证件号码
				String signDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(6));//发证日期
				String effectiveDateStr=POIUtil.getDateCellValue((HSSFCell)hssfRow.getCell(7));//有效期
				String idCard=getValue(hssfRow.getCell(13));//身份证号
				String belongDepartStr=getValue(hssfRow.getCell(14));//所属单位
				Depart belongDepart=null;
				if(!StringUtil.isEmpty(signDateStr)){
					signDateStr=signDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(birthDateStr)){
					birthDateStr=birthDateStr.replaceAll("\\/", "-").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(effectiveDateStr)){
					effectiveDateStr=effectiveDateStr.replaceAll("\\/", "-").replaceAll(",", "").replaceAll("\\.", "-");
				}
				if(!StringUtil.isEmpty(belongDepartStr)){
					belongDepartStr=belongDepartStr.trim();
					belongDepart=this.findByDepartNameAnother(belongDepartStr);
				}else{
					belongDepart=departMng.findByCode("SHSSWJ");
				}
				CertificateInfo info=null;
				info = this.findOneByZjhmAndName(name, zjhm);
				if(info==null){
					info=new CertificateInfo();
					info.setStatus(CertificationStatus.INIT.getItemValue());
					info.setName(name);
					info.setZjhm(zjhm);
					info.setHasPrinted("F");
				}
				if(!StringUtil.isEmpty(signDateStr))
					info.setIssuanceDate(sdf.parse(signDateStr));
				if(!StringUtil.isEmpty(birthDateStr))
					info.setBirthDate(sdf.parse(birthDateStr));
				Date effectDate = null;
				if(!StringUtil.isEmpty(effectiveDateStr)){
					effectDate = sdf.parse(effectiveDateStr);
					info.setEffectiveDate(effectDate);
				}
				if(!StringUtil.isEmpty(idCard)){
					idCard=idCard.replaceAll(" ", "").trim();
					if(idCard.length()>=18){
						idCard=idCard.substring(idCard.length()-18, idCard.length());
					}else if(idCard.length()>=15&&idCard.length()<18){
						idCard=idCard.substring(idCard.length()-15, idCard.length());
					}
					info.setSfzhm(idCard);
				}
				if(effectDate!=null){
					if(effectDate.compareTo(new Date())<0){
						info.setStatus(CertificationStatus.CANCEL.getItemValue());
					}
				}
				if(belongDepart!=null){
					info.setBelongDepart(belongDepart);
				}
				info.setCertificateType(CertificationTypeEnum.publicPassport.getItemValue());
				importInfoList.add(info);
			}
			if(importInfoList!=null&&importInfoList.size()>0){
				for (CertificateInfo certificateInfo : importInfoList) {
					this.merge(certificateInfo);
					num++;
				}
			}
		}catch(Exception e){
			log.error("error",e);
			throw new Exception(e);
		}finally{
			if(hssfWorkbook!=null){
				hssfWorkbook.close();
			}
		}
		return num;
	}

	@Override
	public CertificateInfo findOneByZjhmAndName(String name, String zjhm) {
		Finder f = Finder.create("from CertificateInfo where flag=1 ");
		if (!StringUtil.isEmpty(zjhm)) {
			f.append(" and zjhm = :zjhm").setParam("zjhm",zjhm);
		}
		if(!StringUtil.isEmpty(name)){
			f.append(" and name = :name").setParam("name",name);
		}
		List list = super.find(f);
		if(list!=null&&list.size()>0){
			return (CertificateInfo) list.get(0);
		}
		return null;
	}
	
	private CertificateInfo packageCertificateInfo(CertificateInfo info,String name,String zjhm,String idCard,String birthDateStr,String signDateStr,String effectiveDateStr,SimpleDateFormat sdf) throws ParseException{
		if(info==null){
			info=new CertificateInfo();
			info.setName(name);
			info.setZjhm(zjhm);
			info.setStatus(CertificationStatus.INIT.getItemValue());
			info.setCertificateType(CertificationTypeEnum.publicPassport.getItemValue());
		}
		if(!StringUtil.isEmpty(idCard)){
			idCard=idCard.replaceAll(" ", "").trim();
			if(idCard.length()>=18){
				idCard=idCard.substring(idCard.length()-18, idCard.length());
			}else if(idCard.length()>=15&&idCard.length()<18){
				idCard=idCard.substring(idCard.length()-15, idCard.length());
			}
			info.setSfzhm(idCard);
		}
		if(!StringUtil.isEmpty(birthDateStr))
			info.setBirthDate(sdf.parse(birthDateStr));
		if(!StringUtil.isEmpty(signDateStr))
			info.setIssuanceDate(sdf.parse(signDateStr));
		if(!StringUtil.isEmpty(effectiveDateStr)){
			Date effectDate = sdf.parse(effectiveDateStr);
			info.setEffectiveDate(effectDate);
			if(effectDate!=null){
				if(effectDate.compareTo(new Date())<0){
					info.setStatus(CertificationStatus.CANCEL.getItemValue());
				}
			}
			
		}	
		return info;
	}
	
	private Depart findByDepartName(String departName){
		String departCode="SHSSWJ";
		if("执法总队".equals(departName)){
			departCode="SWJ_SWJZFZD";
		}else if("行政服务中心".equals(departName)){
			departCode="SWJ_XZFWZX";
		}else if("排水处".equals(departName)){
			departCode="SWJ_PSGLC";
		}else if("供水处".equals(departName)){
			departCode="SWJ_GSGLC";
		}else if("供水调度中心".equals(departName)){
			departCode="SWJ_GSDDJCZX";
		}else if("规划院".equals(departName)){
			departCode="SWJ_SWGHSJYJY";
		}else if("质监站".equals(departName)){
			departCode="SWJ_SWJSGCAQZLJDZ";
		}else if("水利处".equals(departName)){
			departCode="SWJ_SLGLC";
		}else if("信息中心".equals(departName)){
			departCode="SWJ_SWXXZX";
		}else if("水文总站".equals(departName)){
			departCode="SWJ_SWZZ";
		}else if("堤防".equals(departName)){
			departCode="SWJ_DFSSGLC";
		}else if("海洋中心".equals(departName)){
			departCode="SWJ_HYGLSWZX";
		}else if("海洋监测中心".equals(departName)){
			departCode="SWJ_HYHJJCYBZX";
		}else if("水利投资".equals(departName)){
			departCode="SWJ_QT";
		}else if("设计院".equals(departName)){
			departCode="SWJ_QT";
		}else if("其他".equals(departName)){
			departCode="SWJ_QT";
		}else if("区县水务局".equals(departName)){
			departCode="SWJ_QT";
		}else if("财政局".equals(departName)){
			departCode="SWJ_QT";
		}
		return departMng.findByCode(departCode);
	}
	
	private Depart findByDepartNameAnother(String departName){
		String departCode="SHSSWJ";
		if("办公室".equals(departName)){
			departCode="SWJ_BGS";
		}else if("规划处".equals(departName)){
			departCode="SWJ_ZHGHC";
		}else if("防安处".equals(departName)){
			departCode="SWJ_SHHHYZHFYC";
		}else if("法规处".equals(departName)){
			departCode="SWJ_FGC";
		}else if("农水处".equals(departName)){
			departCode="SWJ_NCSLC";
		}else if("水资源处".equals(departName)){
			departCode="SWJ_SZYGLC";
		}else if("计财处".equals(departName)){
			departCode="SWJ_JHCWC";
		}else if("科信处".equals(departName)){
			departCode="SWJ_KJFZC";
		}else if("滩涂处".equals(departName)){
			departCode="SWJ_SSYXGLC";
		}else if("海域处".equals(departName)){
			departCode="SWJ_HYHDGLC";
		}else if("海保处".equals(departName)){
			departCode="SWJ_JHZZGZC";
		}else if("组织处".equals(departName)){
			departCode="SWJ_ZZRSC";
		}else if("宣传处".equals(departName)){
			departCode="SWJ_ZZRSC";
		}else if("工会".equals(departName)){
			departCode="SWJ_GH";
		}else if("纪委".equals(departName)){
			departCode="SWJ_TW";//团委
		}else if("建管处".equals(departName)){
			departCode="SWJ_JSGLC";
		}else if("外单位".equals(departName)){
			departCode="SWJ_QT";
		}
		return departMng.findByCode(departCode);
	}

	@Override
	public List<CertificateInfo> exportCertificateInfo(CertificateInfo bean,String sort, String order,User user) {
		Finder f = Finder.create("from CertificateInfo where flag=1 ");
		if (bean!=null) {
			if(StringUtil.isEmpty(bean.getBusinessType())){
				//业务类型缺失
				return null;
			}else{
				if("public".equals(bean.getBusinessType())){
					f.append(" and certificateType  in :typeList").setParamList("typeList",Arrays.asList(new String[]{CertificationTypeEnum.publicPassport.getItemValue(),CertificationTypeEnum.publicHKAndMacaoPass.getItemValue(),CertificationTypeEnum.publicTaiwanPass.getItemValue()}));
				}else if("private".equals(bean.getBusinessType())){
					f.append(" and certificateType  in :typeList").setParamList("typeList",Arrays.asList(new String[]{CertificationTypeEnum.privatePassport.getItemValue(),CertificationTypeEnum.privateHKAndMacaoPass.getItemValue(),CertificationTypeEnum.privateTaiwanPass.getItemValue()}));
				}
			}
			if (!StringUtil.isEmpty(bean.getCertificateType())) {
				f.append(" and certificateType = :certificateType").setParam("certificateType",bean.getCertificateType());
			}
			if (!StringUtil.isEmpty(bean.getZjhm())) {
				f.append(" and zjhm like :zjhm").setParam("zjhm","%"+bean.getZjhm()+"%");
			}
			if (!StringUtil.isEmpty(bean.getName())) {
				f.append(" and name like :name").setParam("name","%"+bean.getName()+"%");
			}
			if (!StringUtil.isEmpty(bean.getSfzhm())) {
				f.append(" and sfzhm like :sfzhm").setParam("sfzhm","%"+bean.getSfzhm()+"%");
			}
			if(StringUtil.isEmpty(bean.getStatus())){
				f.append(" and status != :status").setParam("status",CertificationStatus.CANCEL.getItemValue());
			}else{
				f.append(" and status = :status").setParam("status",bean.getStatus());
			}
			if(!StringUtil.isEmpty(bean.getBelongDepartId())){
				f.append(" and belongDepart.id = :belongDepartId").setParam("belongDepartId",bean.getBelongDepartId());
			}
			if(!StringUtil.isEmpty(bean.getHasPrinted())){
				f.append(" and hasPrinted = :hasPrinted").setParam("hasPrinted",bean.getHasPrinted());
			}
			if(!StringUtil.isEmpty(bean.getIsNotBack())){
				StringBuilder sb=new StringBuilder();
				sb.append(" SELECT C.pid");
				sb.append(" FROM T_PUBLIC_BUSINESS A INNER JOIN T_PUBLIC_BUSINESS_CERTIFICATE B ON A.pid = B.PUBLIC_BUSINESS_ID ");
				sb.append(" INNER JOIN T_CERTIFICATE_INFO C ON B.CERTIFICATE_ID = C.pid ");
				sb.append(" WHERE A.PFLAG = '1' AND C.PFLAG = '1' AND to_date( to_char( A.PLAN_ENTER_TIME, 'yyyy-MM-dd' ), 'yyyy-mm-dd' ) < to_date( to_char( SYSDATE - 7, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )  ");
				sb.append(" AND ( A.BACK_STATUS IS NULL OR A.BACK_STATUS != '归还完成' ) AND C.STATUS = 'using'");
				if(!user.hasRole("PUBLIC_JBGSGZRY")){
					sb.append(" and C.OWN_ID = '"+user.getId()+"'");
				}
				sb.append(" order by C.PUPDATETIME desc ");
				List<String> notBackIdList = super.getSession().createSQLQuery(sb.toString()).list();
				if("1".equals(bean.getIsNotBack())){
					if(notBackIdList!=null&&notBackIdList.size()>0){
						f.append(" and  id in :idList").setParamList("idList", Arrays.asList(notBackIdList.toArray(new String[notBackIdList.size()])));
					}else{
						f.append(" and  id = :id").setParam("id", "1");//不存在的一个id or return 
					}
				}else if("0".equals(bean.getIsNotBack())){
					if(notBackIdList!=null&&notBackIdList.size()>0){
						f.append(" and  id not in :idList").setParamList("idList", Arrays.asList(notBackIdList.toArray(new String[notBackIdList.size()])));
					}
				}
				
				
			}
		}
		if(!StringUtil.isEmpty(sort)&&!StringUtil.isEmpty(order)){
			f.append(" order by "+sort+" "+order);
		}else{
			f.append(" order by updateTime desc");
		}
		return super.find(f);
	}

	@Override
	public void saveExpireScan(String id, User user) {
		CertificateInfo info = super.findById(id);
		if(info!=null){
			info.setUpdator(user);
			info.setUpdateTime(new Date());
			info.setStatus(CertificationStatus.CANCEL.getItemValue());
			info.setCancelUser(user);
			info.setCancelTime(new Date());
			super.merge(info);
			CertificateOperateLog operateLog=new CertificateOperateLog("expireBack","过期领回",user,new Date(),info);
        	certificateOperateLogMng.save(operateLog);
		}
		
	}
}
