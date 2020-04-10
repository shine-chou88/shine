package com.gwideal.common.web;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Context;
import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;

/**
 * 在线人数过滤器
 */
public class RequestFilter implements Filter {
	protected final Logger log = LoggerFactory.getLogger(RequestFilter.class);
	private static final ResourceBundle res=ResourceBundle.getBundle("system");

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (request instanceof RequestFacade) {
			try {
				String maxOnLineUsers = res.getString("maxOnLineUsers");
				int maxSessionNum = StringUtil.isEmpty(maxOnLineUsers) ? 100 : Integer.valueOf(maxOnLineUsers);
				Field requestField = request.getClass().getDeclaredField("request");
				requestField.setAccessible(true);
				Request req1 = (Request) requestField.get(request);
				Context context = req1.getContext();
				Manager manager = context.getManager();
				Session[] sessions = manager.findSessions();
				int activeSessions = 0;
				if (sessions != null && sessions.length > 0) {
					for (Session session : sessions) {
						HttpSession httpSession = session.getSession();
						if (httpSession != null) {
							User user = (User) httpSession.getAttribute("currentUser");
							if (user != null && !StringUtil.isEmpty(user.getId())) {
								activeSessions += 1;
							}
						}
					}
				}
				log.info("当前用户 activeSessions = " + activeSessions);
				if (activeSessions > maxSessionNum) {
					log.error("activeSessions = " + activeSessions+ "，当前系统访问人数过多，请稍后再访问!");
					resp.sendRedirect(request.getContextPath() + "/busy.jsp");
					return;
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
