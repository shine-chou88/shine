package com.gwideal.common.web;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.gwideal.common.util.StringUtil;


/**
 * @author Tom
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	/**
     * 非法字符过滤正则表达式,表达式前添加(?i)忽略大小写
     */
    private static final String REGEX="(?i)(%C0)|(%A7)|(%7C)|(%94)|(%BF)|(%B5)|(%84)|(%E9)|(%87)|(%91)|(%26)|(%27)|(%2B)|(%28)|(%29)|(%22)|(%3E)|(%3C)|(%20)|(%3D)|(%3A)|(%2F)|(%3B)|(%00)|(%E6)|(%98)|(%AF)|(%E5)|(%AE)|(%A1)|(%89)|(%B9)|(%96)|(%B0)|(%BB)|(%BA)|(%E4)|(%B8)|(%AD)|(%A4)|(%E8)|(%B4)|(%A2)|(cmd)|(win.ini)|(sysdatabases)|(frame)|(ProbePhishing)|(boot.ini)|(web.xml)|(WithSomeChars)|(onmouseover)|(onmouseout)|(' and ')|( having )|(select )|(alert)|(' \\|\\| ')|(script)|(create )|(update )|(drop )|(delete )|(insert )|(truncate )|(xp_cmdshell )|(exec )|('\\+)|(\\+')|(\\')";

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public String getQueryString() {
		String value = super.getQueryString();
		if (!StringUtil.isEmpty(value)) {
			value = replaceAll(value);
		}
		return value;
	}
	
	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (!StringUtil.isEmpty(value)) {
			value = replaceAll(value);
		}
		return value;
	}
	
	public String[] getParameterValues(String name) {
		String[]parameters=super.getParameterValues(name);
		if (parameters==null||parameters.length == 0) {
			return null;
		}
		for (int i = 0; i < parameters.length; i++) {
			if(!StringUtil.isEmpty(parameters[i])){
				parameters[i]=replaceAll(parameters[i]);
			}
		}
		return parameters;
	}
	
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = new HashMap<String, String[]>();
        Map<String, String[]> parameters = super.getParameterMap();
        if(null== parameters || parameters.size()==0){
        	return null;
        }
        for (String key : parameters.keySet()) {
            String[] values = parameters.get(key);
            for (int i = 0; i < values.length; i++) {
            	if(!StringUtil.isEmpty(values[i])){
            		values[i]=replaceAll(values[i]);
            	}
            }
            map.put(key, values);
        }
        return map;
    }
	
	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> getHeaderNames 也可能需要覆盖
	 */
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (!StringUtil.isEmpty(value)) {
			value = replaceAll(value);
		}
		return value;
	}
	
	
	/**
     * 替换特殊字符
     * @param s
     * @return
     */
    private static String replaceAll(String s){
    	Pattern pattern = Pattern.compile(REGEX);
    	Matcher matcher = pattern.matcher(s);
    	if(matcher.find()){
    		s=s.replaceAll(REGEX,"");
    		return replaceAll(s);
    	}
    	return s;
    }
}
