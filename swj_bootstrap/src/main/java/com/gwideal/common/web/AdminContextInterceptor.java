package com.gwideal.common.web;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.gwideal.common.util.RequestUtils;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.LookupsMng;
import com.gwideal.core.manager.SysLogMng;
import com.gwideal.core.model.Function;
import com.gwideal.core.model.Lookups;
import com.gwideal.core.model.SysLog;
import com.gwideal.core.model.User;
import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;


/**
 * 上下文信息拦截器
 * 
 * 包括登录信息、权限信息
 */
public class AdminContextInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log=LoggerFactory.getLogger(AdminContextInterceptor.class);
	@Autowired  
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
	@Autowired
	private LookupsMng lookupsMng;
	@Autowired
	private SysLogMng sysLogMng;
	@Autowired
	private AttachmentMng attachmentMng;

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		String requestMapping=null;//获取对应controller方法上的@RequestMapping里面的value
        for (Map.Entry<RequestMappingInfo,HandlerMethod> m:map.entrySet()) {
            RequestMappingInfo requestMappingInfo=m.getKey().getMatchingCondition(request);
            if(null!=requestMappingInfo){
            	requestMapping=(String)requestMappingInfo.getPatternsCondition().getPatterns().toArray()[0];
            	break;
            }
        }  
		// 不在验证的范围内
		if (exclude(requestMapping)) {
			request.setAttribute("logRequestMapping",requestMapping);
			//用户退出系统需要另外处理
			if("/logout.do".equals(requestMapping)){
				HttpSession session = request.getSession(false);
				if(null!=session){
					saveOperateLog((User)session.getAttribute("currentUser"),requestMapping,null,RequestUtils.getIpAddr(request));
				}
			}
			return true;
		}
		HttpSession session = request.getSession(false);
		// 用户为null跳转到登陆页面
		if(null==session){
			response.sendRedirect(getLoginUrl(request));
			return false;
		}
		User user=(User)session.getAttribute("currentUser");
		if (user == null) {
			response.sendRedirect(getLoginUrl(request));
			return false;
		}
		
		//springMvc restful变量验证非法字符串
		Map<String,String> rmap=(Map<String,String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(null!=rmap && rmap.size()>0){
			for (String key : rmap.keySet()) {
	            String value = rmap.get(key);
	            if(!StringUtil.isEmpty(value) && validateIllegalString(value)){
	            	log.error("attack "+request.getRequestURL());
	            	response.sendRedirect(request.getContextPath() + "/404.jsp");
					return false;
	            }
	        }
		}
		
		Set<String> functionSet =(Set<String>)session.getAttribute(Function.RIGHTS_KEY);
		if (functionSet==null || !functionSet.contains(requestMapping)) {
			response.sendRedirect(request.getContextPath()+"/403.jsp");
			return false;
		}
		request.setAttribute("logRequestMapping",requestMapping);
		return true;
	}
	
	/**
	 * 验证是否含有非法字符串
	 * @param s
	 * @return
	 */
	private boolean validateIllegalString(String s){
		if(!StringUtil.isEmpty(s)){
			s=s.toLowerCase();
			if(s.indexOf("\\u0023")>=0 || s.indexOf("\\43")>=0 || s.indexOf("alert")>=0
				|| s.indexOf("%3e")>=0 || s.indexOf("%3c")>=0 || s.indexOf("%22")>=0 || s.indexOf("%27")>=0
				|| s.indexOf("0x")>=0 || s.indexOf("style")>=0 || s.indexOf("netsparker")>=0
				|| s.indexOf("--")>=0 || s.indexOf("@")>=0 || s.indexOf(">")>=0
				|| s.indexOf("<")>=0 || s.indexOf("script")>=0 || s.indexOf("img")>=0
				|| s.indexOf("'")>=0 || s.indexOf("\"")>=0 || s.indexOf("/")>=0
				|| s.indexOf("+")>=0 || s.indexOf("\\\\")>=0 || s.indexOf("frame")>=0
				|| s.indexOf("bin")>=0 || s.indexOf("win.ini")>=0 || s.indexOf("sysdatabases")>=0
				|| s.indexOf("probephishing")>=0 || s.indexOf("boot.ini")>=0 || s.indexOf("web.xml")>=0
				|| s.indexOf("withsomechars")>=0 || s.indexOf("onmouseover")>=0 || s.indexOf("onmouseout")>=0
				|| s.indexOf("and")>=0 || s.indexOf("having")>=0 || s.indexOf("select ")>=0//会出现selected参数
				|| s.indexOf("update")>=0 || s.indexOf("delete")>=0 || s.indexOf("drop")>=0
				|| s.indexOf("exec")>=0 || s.indexOf("create")>=0 || s.indexOf("insert")>=0
				|| s.indexOf("truncate")>=0 || s.indexOf("xp_cmdshell")>=0 || s.indexOf(".html")>=0
				|| s.indexOf("docbase") >= 0 || s.indexOf("class") >= 0 || s.indexOf("resource") >= 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 保存操作日志
	 * @param requestMapping
	 */
	public void saveOperateLog(User user,String requestMapping,String dataId,String ip)  throws Exception{
		if(null!=user && !StringUtil.isEmpty(user.getId()) && needSaveLogUrls(requestMapping)){
			SysLog sysLog=new SysLog();
			sysLog.setCreator(user);
			sysLog.setOperateUrl(requestMapping);
			Lookups lookup=null;
			List<Lookups> listLookups=lookupsMng.find("from Lookups Where flag=1 and code = ?",new Object[]{requestMapping});
			if(null!=listLookups && listLookups.size()>0){
				lookup=listLookups.get(0);
				if(null!=lookup && !StringUtil.isEmpty(lookup.getId())){
					sysLog.setOperateContent(lookup.getName());
				}
			}
			sysLog.setType("passport");
			if(!StringUtil.isEmpty(dataId)){
				//dataId数据库字段长度100
				if(dataId.length()>100){
					dataId=dataId.substring(0, 100);
				}
				sysLog.setDataId(dataId);
			}
			sysLog.setIp(ip);
			sysLogMng.save(sysLog);
		}
	}
	/**
	 * 保存操作日志
	 * @param requestMapping
	 */
	public void saveDownloadOperateLog(User user,String requestMapping,String content,String dataId,String ip){
		if(null!=user && !StringUtil.isEmpty(user.getId())){
			SysLog sysLog=new SysLog();
			sysLog.setCreator(user);
			sysLog.setOperateUrl(requestMapping);
			sysLog.setOperateContent(content);
			sysLog.setType("passport");
			if(!StringUtil.isEmpty(dataId)){
				//dataId数据库字段长度100
				if(dataId.length()>100){
					dataId=dataId.substring(0, 100);
				}
				sysLog.setDataId(dataId);
			}
			sysLog.setIp(ip);
			sysLogMng.save(sysLog);
		}
	}
	
	/**
	 * 判断是否需要保留操作日志
	 * @param requestMapping
	 * @return
	 */
	public boolean needSaveLogUrls(String requestMapping){
		if(null!=noNeedSaveLogUrls && noNeedSaveLogUrls.size()>0){
			if(noNeedSaveLogUrls.contains(requestMapping)){
				return false;
			}
		}
		return true;
	}
	
	private String getLoginUrl(HttpServletRequest request) {
		StringBuilder buff = new StringBuilder();
		if (loginUrl.startsWith("/")) {
			String ctx = request.getContextPath();
			if (!StringUtils.isBlank(ctx)) {
				buff.append(ctx);
			}
		}
		buff.append(loginUrl);
		return buff.toString();
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//记录系统操作日志
		HttpSession session = request.getSession(false);
		if(null!=session){
			//获取操作数据id
			String dataId=request.getParameter("id");//“？id=”传递方式
			if(StringUtil.isEmpty(dataId)){
				//“/{id}”restful传递方式
				Map<String,String> rmap=(Map<String,String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				if(null!=rmap && rmap.size()>0){
					dataId=rmap.get("id");
				}
			}
			String requestUrl=request.getAttribute("logRequestMapping").toString();
			User currentUser=(User)session.getAttribute("currentUser");
			String ip=RequestUtils.getIpAddr(request);
			if ("/attachment/download/{id}".equals(requestUrl)) {
				Attachment bean = attachmentMng.findById(request.getServletPath().substring(request.getServletPath().lastIndexOf("/")+1));
				if (null != bean) {
					saveDownloadOperateLog(currentUser,request.getServletPath(),bean.getOriginalName(),dataId,ip);
				} else {
					saveOperateLog(currentUser,requestUrl,dataId,ip);
				}
			} else {
				saveOperateLog(currentUser,requestUrl,dataId,ip);
			}
		}
	}

	private boolean exclude(String uri) {
		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}

	private List<String> noNeedSaveLogUrls;
	private String[] excludeUrls;
	private String loginUrl;

	public void setNoNeedSaveLogUrls(List<String> noNeedSaveLogUrls) {
		this.noNeedSaveLogUrls = noNeedSaveLogUrls;
	}

	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}