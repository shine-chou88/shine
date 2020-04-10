package com.gwideal.common.web;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;

public class SessionListener implements HttpSessionListener{

	protected static final Logger log=LoggerFactory.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			HttpSession session=arg0.getSession();
			Map<String,HttpSession> sessionMap=(Map)session.getServletContext().getAttribute("sessionMap");
			if(null!=sessionMap && sessionMap.size()>0){
				User user=(User)session.getAttribute("currentUser");
				if(null!=user && !StringUtil.isEmpty(user.getAccountNo())){
					if(null!=sessionMap.get(user.getAccountNo())){
						sessionMap.remove(user.getAccountNo());
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("SessionListener sessionDestroyed", e);
		}
	}

}
