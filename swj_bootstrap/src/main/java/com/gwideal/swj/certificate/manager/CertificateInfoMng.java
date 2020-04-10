package com.gwideal.swj.certificate.manager;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfo;

public interface CertificateInfoMng extends BaseManager<CertificateInfo>{
	
	public Pagination list(CertificateInfo bean, String sort, String order,
			int pageIndex, int pageSize,boolean isQuRole,boolean isStreetRole, User user);
	
	public void save(CertificateInfo bean, User user)throws Exception;
	
	public void delete(String id, User user);
	
	public CertificateInfo findOneByThreeCondition(String certificateType,String sfzhm,String zjhm)throws Exception ;

	public void cancel(String id, User user);

	public String  genQrCode(String id, User user, HttpServletRequest request);
	
	public CertificateInfo findOneByTypeAndSfzhm(String certificateType,String sfzhm,String[] statusArr);

	public void checkIn(String id, User user);

	public Pagination myList(CertificateInfo bean, String sort, String order, Integer page, Integer rows,
			boolean hasRole, boolean streetRole, User user);

	public void savePublic(CertificateInfo bean, MultipartFile[] publicFiles,String savePath, User user)throws Exception;

	public Pagination refList(CertificateInfo bean, String sort, String order, Integer page, Integer rows,
			boolean hasRole, boolean streetRole, User user);

	public List<CertificateInfo> findPublicBySfzhm(String sfzhm);
	
	/**
	 * 验证证件是否可用
	 * @param certificateType 证件类型
	 * @param startDate 出发时间
	 * @param user 申请用户
	 * @return
	 */
	public Result isValidEffectiveDate(String certificateType,Date startDate,User user);
	
	/**
	 * 新办护照的时候判断是否需要领回旧护照
	 * @param certificateType
	 * @param user
	 * @return
	 */
	public boolean isExistOldPassport(String certificateType,User user);
	
	public List<CertificateInfo>findPrivateByTypeAndOwnId(String[] typeArr,String ownId);

	public int impPrivateData(MultipartFile privateFile, User user) throws Exception;

	public int impPublicData(MultipartFile publicFile, User user)throws Exception;
	
	public CertificateInfo findOneByZjhmAndName(String name,String zjhm);
	
	public List<CertificateInfo> exportCertificateInfo(CertificateInfo bean,String sort, String order,User user);

	public void saveExpireScan(String id, User user);
	
}
