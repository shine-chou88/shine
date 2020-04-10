package com.gwideal.core.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gwideal.common.security.BadCredentialsException;
import com.gwideal.common.security.LockedException;
import com.gwideal.common.security.UserNameNotFoundException;
import com.gwideal.common.util.DBHelper;
import com.gwideal.common.util.DateUtils;
import com.gwideal.common.util.RSAUtils;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.core.manager.FunctionMng;
import com.gwideal.core.manager.IndexMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.Function;
import com.gwideal.core.model.User;

@SuppressWarnings("serial")
@Controller
public class LoginController extends BaseController {
	protected static final Logger log=LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserMng userMng;
	@Autowired
	private FunctionMng functionMng;
	@Autowired
	private IndexMng indexMng;
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String input(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.addAttribute("unReadMsgCount", indexMng.getUnReadMessage(getUser()));
		return "/WEB-INF/view/index";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String submit(String accountNo, String password, String captcha,
			String message,String street, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			HttpSession session = request.getSession();
			accountNo=RSAUtils.decrypt(accountNo);
			password=RSAUtils.decrypt(password);
			User user = userMng.login(accountNo, password);
			userMng.saveLoginInfo(getIp(), user);
			user.hasRole("ADMIN");//resolve roles LazyInitializationException
			session.setAttribute("currentUser", user);
			session.setAttribute("currentDate", DateUtils.formatDate(new Date()));
			session.setAttribute(Function.RIGHTS_KEY, functionMng.getFunctionItems(user.getId()));
			onlyAllowSingletonUser(user,request,session);
			return "redirect:/index.do";
		} catch (UserNameNotFoundException e) {
			model.addAttribute("loginMsg", e.getMessage());
		} catch (LockedException e) {
			model.addAttribute("loginMsg", e.getMessage());
		} catch (BadCredentialsException e) {
			User user = userMng.getByAccount(accountNo);
			if(user!=null){
				int wrongNum=user.getWrongNum()==null?0:user.getWrongNum();
				//5次密码输错锁定 含5次
				if(wrongNum>=4){
					user.setIslocked("TRUE");
				}
				user.setWrongNum(wrongNum+1);
				userMng.update(user);
			}
			model.addAttribute("loginMsg", e.getMessage());
		}catch(BadPaddingException e){
			log.error("accountNo = "+accountNo);
			log.error("password = "+password);
			log.error("submit", e);
			model.addAttribute("loginMsg", "密码错误,请重新输入！");
		}catch (Exception e) {
			log.error("accountNo = "+accountNo);
			log.error("password = "+password);
			log.error("submit", e);
			model.addAttribute("loginMsg", "系统发生错误,请联系管理员!");
		}
		return "login";
	}
	
	@RequestMapping(value = "/loginCert.do", method = RequestMethod.POST)
	public String submitCert(String certUniqueId,String street, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			HttpSession session = request.getSession();
			certUniqueId=RSAUtils.decrypt(certUniqueId);
			User user = userMng.loginByCertUniqueId(certUniqueId);
			userMng.saveLoginInfo(getIp(), user);
			session.setAttribute("currentUser", user);
			session.setAttribute("currentDate", DateUtils.formatDate(new Date()));
			session.setAttribute(Function.RIGHTS_KEY, functionMng.getFunctionItems(user.getId()));
			onlyAllowSingletonUser(user,request,session);
			return "redirect:/index.do";
		} catch (UserNameNotFoundException e) {
			model.addAttribute("loginMsg", e.getMessage());
		} catch (LockedException e) {
			model.addAttribute("loginMsg", e.getMessage());
		}catch(BadPaddingException e){
			log.error("certUniqueId = "+certUniqueId);
			log.error("submitCert", e);
			model.addAttribute("loginMsg", "密码错误,请重新输入！");
		}catch (Exception e) {
			log.error("certUniqueId = "+certUniqueId);
			log.error("submitCert", e);
			model.addAttribute("loginMsg", "系统发生错误,请联系管理员!");
		}
		return "login_cert";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request,
			HttpServletResponse response)  {
		HttpSession session = request.getSession(false);
		if (null != session) {
			Map<String,HttpSession> sessionMap=(Map)request.getServletContext().getAttribute("sessionMap");
			if(null!=sessionMap && sessionMap.size()>0){
				User user=(User)session.getAttribute("currentUser");
				if(null!=user && !StringUtil.isEmpty(user.getAccountNo())){
					if(null!=sessionMap.get(user.getAccountNo())){
						sessionMap.remove(user.getAccountNo());
					}
				}
			}
			try {
				session.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
				log.error("session invalidate", e);
			}
		}
		return "redirect:login.do";
	}

	/**
	 * 同一时间同一个用户只能一个在线
	 * @param user
	 * @param request
	 * @param session
	 */
	private void onlyAllowSingletonUser(User user,HttpServletRequest request,HttpSession session){
		Map<String,HttpSession> sessionMap=(Map)request.getServletContext().getAttribute("sessionMap");
		if(null!=sessionMap && sessionMap.size()>0){
			HttpSession hs=sessionMap.get(user.getAccountNo());
			if(null!=hs && !session.getId().equals(hs.getId())){
				try {
					hs.invalidate();
				} catch (Exception e) {
					// TODO: handle exception
					log.error("session invalidate", e);
				}
				sessionMap.remove(user.getAccountNo());
			}
			sessionMap.put(user.getAccountNo(), session);
		}else{
			sessionMap=new HashMap<String,HttpSession>();
			sessionMap.put(user.getAccountNo(), session);
		}
		request.getServletContext().setAttribute("sessionMap", sessionMap);
	}
	/**
	 * 给OA返回待办数量
	 * @param account
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/taskCount.do")
	@ResponseBody
	public String taskCount(String account,String token){
		if (!StringUtil.isEmpty(account) && !StringUtil.isEmpty(token)) {
			try {
				User user = userMng.getByAccount(account);
				if (null != user) {
					int t = indexMng.getUnReadMessage(user);
					if (t == 0) {
						return null;
					} else {
						return "{\"value\":\""+t+"\",\"url\":\"http://31.16.14.200:32373/passport/taskList.do\"}";
					}
				}
			} catch (Exception e) {
				log.error("返回待办数量",e);
			}
		}
		return null;
	}
	/**
	 * OA访问待办列表
	 * @param request
	 * @param account
	 * @param token
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/taskList.do")
	public String taskList(HttpServletRequest request,String account,String token, ModelMap model) {
		if (!StringUtil.isEmpty(account) && !StringUtil.isEmpty(token)) {
			User user = null;
			try {
				user = getUser();
				if (null != user && account.equals(user.getAccountNo())) {
					model.addAttribute("baoxin", "index");
					return "/WEB-INF/view/activiti/task_list";
				}
			} catch (Exception e1) {
				log.error("判断是否登陆失败",e1);
			}
			try {
				String url = "http://31.16.10.180:8080/CXFRest/rest/sample/checkLoginStatus/account="+account+"&tokenId="+token;
				String result = DBHelper.getHttp(url, "GET");
				if (StringUtil.isEmpty(result)) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					result = DBHelper.getHttp(url, "GET");
				}
				if (!StringUtil.isEmpty(result) && result.contains("login")) {
					user = userMng.getByAccount(account);
					if (null != user) {
						HttpSession session = request.getSession();
						userMng.saveLoginInfo(getIp(), user);
						user.hasRole("ADMIN");//resolve roles LazyInitializationException
						session.setAttribute("currentUser", user);
						session.setAttribute("currentDate", DateUtils.formatDate(new Date()));
						session.setAttribute(Function.RIGHTS_KEY, functionMng.getFunctionItems(user.getId()));
						model.addAttribute("baoxin", "index");
						onlyAllowSingletonUser(user,request,session);
						return "/WEB-INF/view/activiti/task_list";
					}
				}
			} catch (Exception e) {
				log.error("检查账号状态失败",e);
			}
		} 
		return "login";
	}
	/**
	 * OA单点登录系统
	 * @param request
	 * @param account
	 * @param token
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/OAIndex.do")
	public String OAIndex(HttpServletRequest request,String account,String token) {
		if (!StringUtil.isEmpty(account) && !StringUtil.isEmpty(token)) {
			User user = null;
			try {
				user = getUser();
				if (null != user && account.equals(user.getAccountNo())) {
					return "redirect:/index.do";
				}
			} catch (Exception e1) {
				log.error("判断是否登陆失败",e1);
			}
			try {
				String url = "http://31.16.10.180:8080/CXFRest/rest/sample/checkLoginStatus/account="+account+"&tokenId="+token;
				String result = DBHelper.getHttp(url, "GET");
				if (StringUtil.isEmpty(result)) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					result = DBHelper.getHttp(url, "GET");
				}
				if (!StringUtil.isEmpty(result) && result.contains("login")) {
					user = userMng.getByAccount(account);
					if (null != user) {
						HttpSession session = request.getSession();
						userMng.saveLoginInfo(getIp(), user);
						user.hasRole("ADMIN");//resolve roles LazyInitializationException
						session.setAttribute("currentUser", user);
						session.setAttribute("currentDate", DateUtils.formatDate(new Date()));
						session.setAttribute(Function.RIGHTS_KEY, functionMng.getFunctionItems(user.getId()));
						onlyAllowSingletonUser(user,request,session);
						return "redirect:/index.do";
					}
				}
			} catch (Exception e) {
				log.error("检查账号状态失败",e);
			}
		} 
		return "login";
	}
	

}
