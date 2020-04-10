package com.gwideal.swj.certificate.manager;

import java.text.ParseException;
import java.util.List;

import com.gwideal.activiti.entity.TaskJson;
import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfoApply;
import com.gwideal.swj.certificate.entity.PrivateExportEntity;

public interface CertificateInfoApplyMng extends BaseManager<CertificateInfoApply>{
	
	/**
	 * 因私事出国（境）审批
	 * @param bean
	 * @param sort
	 * @param order
	 * @param pageIndex
	 * @param pageSize
	 * @param isOrganizationStaff 组织处工作人员可以查看所有数据
	 * @param user
	 * @return
	 */
	public Pagination list(CertificateInfoApply bean, String sort, String order,
			int pageIndex, int pageSize,boolean isOrganizationStaff, User user);
	
	public void save(CertificateInfoApply bean, User user);
	
	public void delete(String id, User user);
	
	/**
	 * 验证是否已存在“正在申请”的证照申请信息
	 * @param bean
	 * @return
	 */
	public boolean isExist(CertificateInfoApply bean,User user);
	
	/**
	 * 取消流程
	 * @param id
	 * @param reason 取消原因
	 * @param user
	 */
	public void cancel(String id,String reason,User user);

	public CertificateInfoApply findByCertificateIdAndScanType(String id, String type);

	public void saveBackScan(String id, TaskJson taskJson, User user);

	public void saveUseScan(String id, TaskJson taskJson, User user);

	public List<PrivateExportEntity> listExportData(String startTime, String endTime, String certificateType, String exportType) throws ParseException;
	
	public void setLockCertificateInfo(CertificateInfoApply bean,Integer isLock,User user);
	 
	public CertificateInfoApply findByStatusAndUser(String[] statusArr,User user);

	public void saveReceiveScan(String id, TaskJson taskJson, User user);
}
