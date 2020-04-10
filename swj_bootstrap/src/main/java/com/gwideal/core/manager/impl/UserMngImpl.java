package com.gwideal.core.manager.impl;

import com.gwideal.core.manager.FunctionMng;
import com.gwideal.core.manager.RoleMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.Function;
import com.gwideal.core.model.Lookups;
import com.gwideal.core.model.Role;
import com.gwideal.core.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.security.BadCredentialsException;
import com.gwideal.common.security.CodeHelper;
import com.gwideal.common.security.LockedException;
import com.gwideal.common.security.UserNameNotFoundException;
import com.gwideal.common.util.StringUtil;
import com.gwideal.sms.manager.SmsPoolMng;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class UserMngImpl extends BaseManagerImpl<User> implements UserMng{
	
	@Autowired
	private SmsPoolMng smsPoolMng;
	@Autowired
	private RoleMng roleMng;
	@Autowired
	private FunctionMng functionMng;

	@Override
	public synchronized boolean isExist(User user) {
		List<User> list = null;
		if (StringUtil.isEmpty(user.getId())) {
			list=super.find("from User where flag=1 and accountNo=?",new Object[]{user.getAccountNo()});
		} else {
			String hql ="from User where accountNo=? and id<>? and flag=1";
			list=super.find(hql,new Object[]{user.getAccountNo(),user.getId()});
		}
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
	public User SSOLogin(String accountNo) throws UserNameNotFoundException,
			BadCredentialsException, LockedException {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(accountNo)){
			throw new UserNameNotFoundException("登录信息填写不完整！");
		}
		User user=getUserByAccountNo(accountNo);
		if(null==user){
			throw new UserNameNotFoundException("该用户不存在！");
		}else{
			if(!StringUtil.isEmpty(user.getIslocked()) && "TRUE".equals(user.getIslocked())){
				throw new LockedException("该用户已被锁定！");
			}
		}
		return user;
	}
	
	public List<User> getUserInfoByDepart(String departId){
		String hql = null;
		if(departId !=null && !"".equals(departId)){
			hql = "from User where flag=1 and display_type=10 and depart.id ='"+departId+"' order by name";
		}
		return find(hql);
	}
	
	/**
	 * 得到项目角色
	 * 2012-5-15
	 * @author wang_dong
	 * @return
	 */
	@Override
	 public List<Lookups> getXiangMuJueSe(){
		 String hql=null;
		 hql="from Lookups where lookupcode='XMJS' order by orderNo asc";
		 return find(hql);
	 }


	 /**
	 * 根据 name 排序获得所有用户
	 * @return
	 */
	@Override
	public List<User> getUserInfo() {
		// TODO Auto-generated method stub
		String hql = "from User where flag=1 and display_type=10 order by name ";
		return super.find(hql);
	}

	@Override
	public User login(String accountNo, String password)
			throws UserNameNotFoundException, BadCredentialsException,
			LockedException {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(accountNo) || StringUtil.isEmpty(password)){
			throw new UserNameNotFoundException("登录信息填写不完整！");
		}
		User user=getUserByAccountNo(accountNo);
		if(null==user){
			throw new UserNameNotFoundException("该用户不存在！");
		}else{
			if(!StringUtil.isEmpty(user.getIslocked()) && "TRUE".equals(user.getIslocked())){
				throw new LockedException("该用户已被锁定！");
			}
			if(!user.getPassword().equals(CodeHelper.encryptPassword(password))){
				throw new BadCredentialsException("密码错误,请重新输入！");
			}
		}
		return user;
	}
	@Override
	public User zzwwlogin(String accountNo, String password)
			throws UserNameNotFoundException, BadCredentialsException,
			LockedException {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(accountNo) || StringUtil.isEmpty(password)){
			throw new UserNameNotFoundException("登录信息填写不完整！");
		}
		User user=getUserByAccountNo(accountNo);
		if(null==user){
			throw new UserNameNotFoundException("该用户不存在！");
		}else{
			if(!StringUtil.isEmpty(user.getIslocked()) && "TRUE".equals(user.getIslocked())){
				throw new LockedException("该用户已被锁定！");
			}
			if(!user.getPassword().equals(password)){
				throw new BadCredentialsException("密码错误,请重新输入！");
			}
		}
		return user;
	}
	
	public User getUserByAccountNo(String accountNo){
		return	(User)super.findUnique("from User Where flag=1 and (accountNo = ? or mobileNo=?)",accountNo,accountNo);
	}
	
	public User getUserByCertUniqueId(String certUniqueId){
		return	(User)super.findUnique("from User Where flag=1 and certUniqueId = ?",certUniqueId);
	}

	@Override
	public Pagination list(User user,String departId,String jddm,String jwdm,String sort,String order,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder finder=Finder.create("from User bean left join fetch bean.depart dp Where bean.flag=1");
		if(null!=user){
			if(!StringUtil.isEmpty(user.getName())){
				finder.append(" and bean.name like :name");
				finder.setParam("name","%"+user.getName()+"%");
			}
			if(!StringUtil.isEmpty(user.getAccountNo())){
				finder.append(" and bean.accountNo like :accountNo");
				finder.setParam("accountNo","%"+user.getAccountNo()+"%");
			}
			if(!StringUtil.isEmpty(jddm)){
				finder.append(" and bean.street.streetCode = :streetCode");
				finder.setParam("streetCode",jddm);
			}
			if(!StringUtil.isEmpty(jwdm)){
				finder.append(" and bean.jwh.jwhCode = :jwhCode");
				finder.setParam("jwhCode",jwdm);
			}
			if(!StringUtil.isEmpty(user.getMobileNo())){
				finder.append(" and bean.mobileNo = :mobileNo");
				finder.setParam("mobileNo",user.getMobileNo());
			}
			//部门Id
			if(!StringUtil.isEmpty(departId)){
				finder.append(" and bean.depart.id = :departId");
				finder.setParam("departId",departId);
			}
			if(!StringUtil.isEmpty(user.getIslocked())){
				finder.append(" and bean.islocked = :islocked");
				finder.setParam("islocked", user.getIslocked());
			}
			if(!StringUtil.isEmpty(user.getIsKey())){
				if("TRUE".equals(user.getIsKey())){
					finder.append(" and bean.certUniqueId is not null and bean.certUniqueId !='' ");
				}else if("FALSE".equals(user.getIsKey())){
					finder.append(" and ( bean.certUniqueId is  null or bean.certUniqueId ='' ) ");
				}
			}
		}
		if(!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order) && (order.equals("asc") || order.equals("desc"))){
			finder.append(" order by bean."+sort+" "+order);
		}else{
			finder.append(" order by bean.name");
		}
		return super.find(finder, pageNo, pageSize);
	}

	@Override
	public void reSetPwd(String userId,User user) {
		// TODO Auto-generated method stub
		User bean=super.findById(userId);
		bean.setPassword(CodeHelper.reSetPwd());
		bean.setUpdator(user);
		bean.setUpdateTime(new Date());
		super.updateDefault(bean);
		//发送短信给密码被重置的人，暂时不发送
		//if(!StringUtil.isEmpty(bean.getMobileNo())){
			//String content="您的账号"+bean.getAccountNo()+"，密码已经重置为 "+CodeHelper.RESET_PWD+"，请登录系统后修改密码。【出国（境）管理】";
			//smsPoolMng.saveSendSms(bean.getMobileNo(),bean.getName(),content,user);
		//}
	}

	@Override
	public void save(User bean, String roleIds,String funcIds,User user) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(bean.getId())){//新增时密码为帐号123#
			bean.setCreator(user);
			bean.setPassword(CodeHelper.encryptPassword(bean.getPassword()));
			//设置角色
			if(!StringUtil.isEmpty(roleIds)){
				bean.setRoles(new ArrayList<Role>());
				String[] rIds = roleIds.split(",");
				// 添加新选择的角色
				for (String rid:rIds) {
					bean.addRole(roleMng.findById(rid));
				}
			}
			//设置功能
			if(!StringUtil.isEmpty(funcIds)){
				bean.setFunctions(new ArrayList<Function>());
				String[] fids=funcIds.split(",");
				for(String fid:fids){
					bean.addFunction(functionMng.findById(Long.valueOf(fid)));
				}
			}
		}else{
			bean.setUpdator(user);
			User u=super.findById(bean.getId());
			bean.setLoginCount(u.getLoginCount());
			bean.setLastLoginTime(u.getLastLoginTime());
			bean.setLastLoginIp(u.getLastLoginIp());
			//设置角色
			bean.setRoles(u.getRoles());
			if (!StringUtil.isEmpty(roleIds)) {
				String[] rIds = roleIds.split(",");
				// 先删除角色
				List<Role> deleteRoles = new ArrayList<Role>();
				if(null==bean.getRoles()){
					bean.setRoles(new ArrayList<Role>());
				}
				for (Role r:bean.getRoles()) {
					if (roleIds.indexOf(r.getId())==-1){
						deleteRoles.add(r);
					}
				}
				for (Role r:deleteRoles) {
					bean.removeRole(r);
				}
				// 添加新选择的角色
				for (String rid:rIds) {
					Role r=roleMng.findById(rid);
					if(!bean.getRoles().contains(r)){
						bean.addRole(r);
					}
				}
			}else{
				if(null!=bean.getRoles() && bean.getRoles().size()>0){
					List<Role> deleteRoles = new ArrayList<Role>();
					for (Role r:bean.getRoles()) {
						deleteRoles.add(r);
					}
					for (Role r:deleteRoles) {
						bean.removeRole(r);
					}
				}
			}
			//设置功能
			bean.setFunctions(u.getFunctions());
			if(!StringUtil.isEmpty(funcIds)){
				String[] functionArr=funcIds.split(",");
				List<Long> funcs=new ArrayList<Long>();
				for(String id:functionArr){
					funcs.add(Long.valueOf(id));
				}
				List<Function> listRemoveFunction=new ArrayList<Function>();
				if(null==bean.getFunctions()){
					bean.setFunctions(new ArrayList<Function>());
				}
				for(Function function:bean.getFunctions()){
					if(!funcs.contains(function.getId())){
						listRemoveFunction.add(function);
					}
				}
				for(Function function:listRemoveFunction){
					bean.removeFunction(function);
				}
				for(Long fid:funcs){
					Function function=functionMng.findById(fid);
					if(null==function.getUsers()){
						function.setUsers(new ArrayList<User>());
					}
					if(!bean.getFunctions().contains(function)){
						bean.addFunction(function);
					}
				}
			}else{
				bean.setFunctions(null);
			}
		}
		if(null==bean.getDepart() || StringUtil.isEmpty(bean.getDepart().getId())){
			bean.setDepart(null);
		}
		super.save(bean);
	}

	@Override
	public List<User> listByRole(String roleCode) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("Select u from User u join u.roles r Where u.flag=1");
		f.append(" and r.code= :rcode");
		f.setParam("rcode",roleCode);
		f.append(" order by u.name");
		return super.find(f);
	}
	@Override
	public List<User> getUserByDepartCode(String departCode) {
		// TODO Auto-generated method stub
		String hql="from User Where flag=1 and depart.code=?";
		return super.find(hql,new Object[]{departCode});
	}
	@Override
	public List<User> getALL() {
		// TODO Auto-generated method stub
		String hql="from User Where flag=1 order by name";
		return super.find(hql);
	}
	@Override
	public List<User> getByIds(String ids) {
		// TODO Auto-generated method stub
		String hql="from User Where flag=1 and id in ("+ids+")";
		return super.find(hql);
	}
	@Override
	public List<User> getChildByJwhCode(String jwhId) {
		// TODO Auto-generated method stub
		String hql = "from User where flag=1 and jwh.id = ? and isPersonLiable='1' order by name";
		return find(hql, jwhId);
	}
	@Override
	public  boolean findByAccount(String account){
		String hql = "from User where flag=1 and accountNo = ? ";
		List<User> list=super.find(hql, account);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<User> findChild(String streetId,String jwhId,String liablePerson,boolean hasqu, boolean hasStreet,User user) {		
		StringBuffer hql =new StringBuffer("from User where flag=1 and isPersonLiable='1'");
        
        if(streetId!=null&&!streetId.equals("")){
        	hql.append(" and street.id='"+streetId+"'");
        }
        if(jwhId!=null&&!jwhId.equals("")){
        	hql.append(" and jwh.id = '"+jwhId+"'");
        }
        if(liablePerson!=null&&!liablePerson.equals("")){
        	hql.append(" and name like '%"+liablePerson+"%'");
        }
        hql.append(" order by name");
		return find(hql.toString());
	}
	
	public User getByAccount(String account){
		String hql = "from User where flag=1 and accountNo = ? ";
		List<User> list=super.find(hql, account);
		return (null != list && list.size() > 0) ? list.get(0) : null;
	}

	@Override
	public Pagination liablelist(User user, boolean isQuRole,
			boolean isStreetRole, String sort, String order, int pageNo,
			int pageSize, User u) {
		Finder finder=Finder.create("from User Where flag=1 and isPersonLiable='1' ");
		
		if(null!=user){
			if(!StringUtil.isEmpty(user.getName())){
				finder.append(" and name like :Name");
				finder.setParam("Name","%"+user.getName()+"%");
			}
		}
		if(!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order) && (order.equals("asc") || order.equals("desc"))){
			finder.append(" order by "+sort+" "+order);
		}else{
			finder.append(" order by name");
		}
		return super.find(finder, pageNo, pageSize);
	}

	@Override
	public Pagination refInspectorList(User bean, String sort, String order,
			int pageIndex, int pageSize, boolean isQuRole,
			boolean isStreetRole, User user) {
		// TODO Auto-generated method stub
		Finder finder=Finder.create("from User Where flag=1 and isPersonLiable='1' and status='10'");
		if(null!=bean){
			if(!StringUtil.isEmpty(bean.getName())){
				finder.append(" and name like :name");
				finder.setParam("name","%"+bean.getName()+"%");
			}
		}
		if(!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order) && (order.equals("asc") || order.equals("desc"))){
			finder.append(" order by "+sort+" "+order);
		}else{
			finder.append(" order by name");
		}
		return super.find(finder, pageIndex, pageSize);
	}

	@Override
	public List<User> inspectorList(boolean isQuRole, boolean isStreetRole,
			User user) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer("from User Where flag=1 and isPersonLiable='1' and status='10'");
		hql.append(" order by name");
		return super.find(hql.toString(),new Object[]{});
	}
	
	/**
	 * 获取有某个角色的人员
	 * @param roleCode
	 * @return
	 */
	public List<User> listAuditor(String roleCode){
		List<User> auditors=new ArrayList<User>();
		StringBuffer hql=new StringBuffer("from User Where flag=1");
		List<User> list=super.find(hql.toString());
		for(User us: list){
			List<Role> listRole=us.getRoles();
			if(null!=listRole && listRole.size()>0){
				for(Role r:listRole){
					if(roleCode.equals(r.getCode())){
						auditors.add(us);
					}
				}
			}
		}
		return auditors;  
	}
	/**
	 * 检查手机号唯一
	 * @return
	 */
	public boolean checkMobileNo(String mobileNo,String id){
		Finder hql=Finder.create("from User where flag=1 ");
		hql.append(" and mobileNo=:mobileNo").setParam("mobileNo", mobileNo);
		if(!StringUtil.isEmpty(id)){
			hql.append(" and id <>:id").setParam("id", id);
		}
		List<User> list=super.find(hql);
		if(list!=null&&list.size()>0){
			return true;
		}
		
		return false;
	}

	@Override
	public User loginByCertUniqueId(String certUniqueId) throws UserNameNotFoundException,LockedException{
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(certUniqueId)){
			throw new UserNameNotFoundException("没有获取到密钥的唯一标示！");
		}
		User user=getUserByCertUniqueId(certUniqueId);
		if(null==user){
			throw new UserNameNotFoundException("该密钥没有绑定用户！");
		}else{
			if(!StringUtil.isEmpty(user.getIslocked()) && "TRUE".equals(user.getIslocked())){
				throw new LockedException("该密钥绑定的用户已被锁定！");
			}
		}
		return user;
	}

	@Override
	public List<User> findByMobileNo(String mobileNo) {
		return super.find("from User where mobileNo=? and flag=1",new Object[]{mobileNo});
	}

	@Override
	public void saveLoginInfo(String loginIp,User user) {
		// TODO Auto-generated method stub
		if(null!=user.getLoginCount()){
			user.setLoginCount(user.getLoginCount()+1);
		}else{
			user.setLoginCount(1);
		}
		user.setLastLoginIp(loginIp);
		user.setLastLoginTime(new Date());
		user.setIslocked("FALSE");
		user.setWrongNum(0);
		super.save(user);
	}

	@Override
	public List<User> findCurrentUser() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.HOUR, -1);
		return super.find("from User where lastLoginTime>='"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date.getTime())+"' and flag=1");
	}

	@Override
	public User findByUserNameAndIdCard(String name, String idCard) {
		Finder hql=Finder.create("from User where flag=1 ");
		hql.append(" and name=:name").setParam("name", name);
		hql.append(" and idCard =:idCard").setParam("idCard", idCard);
		List<User> list=super.find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
