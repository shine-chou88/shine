package com.gwideal.core.manager;

import java.util.List;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.security.BadCredentialsException;
import com.gwideal.common.security.LockedException;
import com.gwideal.common.security.UserNameNotFoundException;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.Lookups;
import com.gwideal.core.model.User;

public interface UserMng extends BaseManager<User>{
	public User login(String accountNo,String password) throws UserNameNotFoundException,BadCredentialsException,LockedException;
	public User loginByCertUniqueId(String certUniqueId) throws UserNameNotFoundException,LockedException;;//ukey登录
	public User SSOLogin(String accountNo) throws UserNameNotFoundException,BadCredentialsException,LockedException;
	public Pagination list(User user,String departId,String jddm,String jwdm,String sort, String order,int pageNo,int pageSize);
	
	/**
	 * 巡查责任人
	 * @param bean
	 * @param sort
	 * @param order
	 * @param pageIndex
	 * @param pageSize
	 * @param isQuRole
	 * @param isStreetRole
	 * @param user
	 * @return
	 */
	public Pagination refInspectorList(User bean,String sort, String order,
			int pageIndex, int pageSize,boolean isQuRole,boolean isStreetRole,User user);
	
	/**
	 * 巡查责任人
	 * @param isQuRole
	 * @param isStreetRole
	 * @param user
	 * @return
	 */
	public List<User> inspectorList(boolean isQuRole,boolean isStreetRole,User user);
	
	/**
	 * 重置密码
	 * @param userId
	 * @param user 操作用户
	 */
	public void reSetPwd(String userId,User user);
	
	/**
	 * 验证用户是否已存在
	 * @param user
	 * @return
	 */
	public boolean isExist(User user);
	
	/**
	 * 保存
	 * @param bean
	 * @param roleIds
	 * @param 功能Ids
	 * @param user 操作用户
	 */
	public void save(User bean,String roleIds,String funcIds,User user);
	
	/**
	 * 根据条件获取用户
	 * @param roleCode 角色代码
	 * @return
	 */
	public List<User> listByRole(String roleCode);
	
	
	public List<Lookups> getXiangMuJueSe();
	public List<User> getUserInfo();
	/**
	 * 获取某个部门下所有用户
	 * @param departCode
	 * @return
	 */
	public List<User> getUserByDepartCode(String departCode);
	/**
	 * 获取所有用户
	 * @param 
	 * @return
	 */
	public List<User> getALL();
	
	public List<User> getByIds(String ids);
	
	
	public User getByAccount(String account);
	/**
	 * 获取责任人
	 * @param 
	 * @return
	 */
	public List<User> getChildByJwhCode(String jwhId);
	
	
	public  boolean findByAccount(String account);
	/**
	 * 获取责任人
	 * @param jwhId  居委会id
	 * @param hasqu  区权限判断
	 * @param hasStreet  街道判断
	 * @return
	 */
	public List<User> findChild(String streetId,String jwhId,String liablePerson,boolean hasqu,boolean hasStreet,User user);
	
	public Pagination liablelist(User user,boolean isQuRole,boolean isStreetRole,String sort, String order,int pageNo,int pageSize,User u);

	public User zzwwlogin(String accountNo, String password) throws UserNameNotFoundException, BadCredentialsException, LockedException;
	
	/**
	 * 获取有某个角色的人员
	 * @param roleCode
	 * @return
	 */
	public List<User> listAuditor(String roleCode);
	
	/**
	 * 检查手机号唯一
	 * @return
	 */
	public boolean checkMobileNo(String mobileNo,String id); 
	
	public List<User> findByMobileNo(String mobileNo);
	
	/**
	 * 保存登录信息
	 * @param loginIp
	 * @param user
	 */
	public void saveLoginInfo(String loginIp,User user);
	/**
	 * 最近一个小时登录的人
	 * @return
	 */
	public List<User> findCurrentUser();
	
	public User findByUserNameAndIdCard(String name ,String idCard);
	
}
