package com.gwideal.common.web;

/**
 * @author Tom
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gwideal.common.util.StringUtil;

public class XssFilter implements Filter {
	private static final Logger log=LoggerFactory.getLogger(XssFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//跨帧脚本编制防御
		resp.setHeader("x-frame-options", "SAMEORIGIN");
		//跨站点请求伪造
//		String referer = req.getHeader("referer");  
//		if(!StringUtil.isEmpty(referer) && !referer.contains(req.getServerName())){
//			resp.sendRedirect(req.getContextPath() + "/404.jsp");
//			return;
//		}
		//?后台的参数过滤非法字符串
		String paramStr=req.getQueryString();
		if(!StringUtil.isEmpty(paramStr) && validateIllegalString(paramStr)){
			log.error("attack "+req.getRequestURL()+"?"+paramStr);
			resp.sendRedirect(req.getContextPath() + "/404.jsp");
			return;
		}
		String servletPath=req.getServletPath();//websocket不做过滤
		if(!StringUtil.isEmpty(servletPath) && servletPath.startsWith("/socket/")){
			chain.doFilter(request, response);
		}else{
			chain.doFilter(new XssHttpServletRequestWrapper(req), response);
		}
	}
	
	/**
	 * 验证是否含有非法字符串
	 * @param s
	 * @return
	 */
	private boolean validateIllegalString(String s){
		if(!StringUtil.isEmpty(s)){
			s=s.toLowerCase();
			//过滤在线帮助
			if(s.contains("resource/onlinehelp") || s.contains("/ptzz/upload") || s.contains("random=")){
				return false;
			}
			if(s.indexOf("\\u0023")>=0 || s.indexOf("\\43")>=0 || s.indexOf("alert")>=0
				|| s.indexOf("%3e")>=0 || s.indexOf("%3c")>=0 || s.indexOf("%22")>=0 || s.indexOf("%27")>=0
				|| s.indexOf("0x")>=0 || s.indexOf("style")>=0 || s.indexOf("netsparker")>=0
				|| s.indexOf("--")>=0 || s.indexOf("@")>=0 || s.indexOf(">")>=0
				|| s.indexOf("<")>=0 || s.indexOf("script")>=0 || s.indexOf("img")>=0
				|| s.indexOf("'")>=0 || s.indexOf("\"")>=0 || s.indexOf("/")>=0
				|| s.indexOf("+")>=0 || s.indexOf("\\\\")>=0 || s.indexOf("frame")>=0
				|| s.indexOf("win.ini")>=0 || s.indexOf("sysdatabases")>=0
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

}
