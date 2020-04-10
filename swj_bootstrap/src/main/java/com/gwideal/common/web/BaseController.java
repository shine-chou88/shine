package com.gwideal.common.web;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwideal.common.entity.Combobox;
import com.gwideal.common.entity.ComboboxType;
import com.gwideal.common.page.ComboboxJson;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.util.RequestUtils;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.Function;
import com.gwideal.core.model.Role;
import com.gwideal.core.model.User;

/**
 * action基类 提供一些常用方法和变量
 * 
 * @author zhou_liang
 * 
 */
@SuppressWarnings({ "serial" })
public class BaseController implements Serializable {
	protected static final Logger log = LoggerFactory.getLogger(BaseController.class);
	private static final String[] MSBrowser = {"MSIE", "Trident", "Edge"};//微软浏览器类型，Trident为IE11
	
	@Autowired
	protected HttpServletRequest request;

	/**
	 * 获取文件下载时编码好的文件名
	 * @param fileName
	 * @return
	 */
	public String getFileName(String fileName) throws Exception{
        if (isMSBrowser(request)) {  
             fileName = URLEncoder.encode(fileName, "UTF-8");  
        } else {  
             fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");  
        }  
		return fileName;
	}
	
	/**
	 * 判断是否为微软浏览器
	 * @param request
	 * @return
	 */
    public boolean isMSBrowser(HttpServletRequest request) {  
        String userAgent = request.getHeader("User-Agent");
        if(!StringUtil.isEmpty(userAgent)){
        	for (String browser : MSBrowser) {  
                if (userAgent.contains(browser))  
                    return true;  
            }  
        }
        return false;  
    }  
	
	public String getIp() {
		return RequestUtils.getIpAddr(request);
	}

	public User getUser() {
		HttpSession session = request.getSession(false);
		return null == session ? null : (User) session.getAttribute("currentUser");
	}

	/**
	 * 判断用户是否是“街镇角色”
	 * 
	 * @return
	 */
	public boolean isStreetRole() {
		boolean flag = false;
		List<Role> listRole = getUser().getRoles();
		if (null != listRole && listRole.size() > 0) {
			for (Role r : listRole) {
				if ("STREET_ROLE".equals(r.getCode())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断用户是否有某个角色
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return
	 */
	public boolean hasRole(String roleCode) {
		boolean flag = false;
		List<Role> listRole = getUser().getRoles();
		if (null != listRole && listRole.size() > 0
				&& !StringUtil.isEmpty(roleCode)) {
			for (Role r : listRole) {
				if (roleCode.equals(r.getCode())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public boolean hasFunction(String url) {
		Set<String> functionSet = (Set<String>) request.getSession(false).getAttribute(Function.RIGHTS_KEY);
		if (functionSet.contains(url)) {
			return true;
		} else {
			return false;
		}
	}

	public JsonPagination getJsonPagination(Pagination p, int curPage) {
		JsonPagination jp = new JsonPagination();
		jp.setTotal(p.getTotalCount());
		jp.setCurPage(curPage);
		jp.setRows(p.getList());
		return jp;
	}

	public Result getJsonResult(boolean success, String info) {
		return new Result(success,info);
	}

	public Result getErrorJsonResult() {
		return new Result(false,Result.ERRORMSG);
	}

	public JsonPagination paginationJson(Pagination p, int curPage) {
		JsonPagination jp = new JsonPagination();
		jp.setTotal(p.getTotalCount());
		jp.setCurPage(curPage);
		jp.setRows(p.getList());
		return jp;
	}

	public List<ComboboxJson> getComboboxJson(List<?> list) {
		List<ComboboxJson> listCombobox = new ArrayList<ComboboxJson>();
		ComboboxJson comboboxJson = null;
		if (null != list && list.size() > 0) {
			int t = list.size();
			for (int i = 0; i < t; i++) {
				comboboxJson = new ComboboxJson();
				Combobox combobox = (Combobox) list.get(i);
				comboboxJson.setId(combobox.getId());
				comboboxJson.setText(combobox.getText());
				if (i == 0) {// 默认选中第一个
					comboboxJson.setSelected(true);
				}
				listCombobox.add(comboboxJson);
			}
		}
		return listCombobox;
	}

	/**
	 * easyui 下拉框
	 * 
	 * @param list
	 *            数据集合
	 * @param selected
	 *            要选中的值
	 * @return
	 */
	public List<ComboboxJson> getComboboxJson(List<?> list, String selected) {
		List<ComboboxJson> listCombobox = new ArrayList<ComboboxJson>();
		ComboboxJson comboboxJson = null;
		ComboboxJson comboboxJsonDefault = new ComboboxJson();
		comboboxJsonDefault.setId("");
		comboboxJsonDefault.setCode("");
		comboboxJsonDefault.setText("--请选择--");
		if (StringUtil.isEmpty(selected)) {
			comboboxJsonDefault.setSelected(true);
		}
		listCombobox.add(comboboxJsonDefault);
		if (null != list && list.size() > 0) {
			int t = list.size();
			for (int i = 0; i < t; i++) {
				comboboxJson = new ComboboxJson();
				Combobox combobox = (Combobox) list.get(i);
				comboboxJson.setId(combobox.getId());
				comboboxJson.setCode(combobox.getCode());
				comboboxJson.setText(combobox.getText());
				if (!StringUtil.isEmpty(selected)) {
					if (selected.equals(combobox.getId())
							|| selected.equals(combobox.getCode())
							|| selected.equals(combobox.getText())) {
						comboboxJson.setSelected(true);
					}
				}
				listCombobox.add(comboboxJson);
			}
		}
		return listCombobox;
	}

	/**
	 * easyui 下拉框
	 * 
	 * @param list
	 *            数据集合
	 * @param selected
	 *            要选中的值
	 * @return
	 */
	public List<ComboboxJson> getComboboxJsonType(List<?> list, String type,
			String selected) {
		List<ComboboxJson> listCombobox = new ArrayList<ComboboxJson>();
		ComboboxJson comboboxJson = null;
		ComboboxJson comboboxJsonDefault = new ComboboxJson();
		comboboxJsonDefault.setId("");
		comboboxJsonDefault.setCode("");
		comboboxJsonDefault.setText("--请选择--");
		comboboxJsonDefault.setSelected(true);
		listCombobox.add(comboboxJsonDefault);
		if (null != list && list.size() > 0) {
			int t = list.size();
			for (int i = 0; i < t; i++) {
				comboboxJson = new ComboboxJson();
				ComboboxType combobox = (ComboboxType) list.get(i);
				comboboxJson.setId(combobox.getsId());
				if ("specialty".equals(type)) {
					comboboxJson.setText(combobox.getText());
				} else if ("bigType".equals(type)) {
					comboboxJson.setText(combobox.getText2());
				} else if ("smallType".equals(type)) {
					comboboxJson.setText(combobox.getText3());
				}
				comboboxJson.setCode(comboboxJson.getText());
				if (!StringUtil.isEmpty(selected)) {
					if (selected.equals(combobox.getsId())
							|| selected.equals(combobox.getCode())) {
						comboboxJson.setSelected(true);
					}
					if ("specialty".equals(type)
							&& selected.equals(combobox.getText())) {
						comboboxJson.setSelected(true);
					} else if ("bigType".equals(type)
							&& selected.equals(combobox.getText2())) {
						comboboxJson.setSelected(true);
					} else if ("smallType".equals(type)
							&& selected.equals(combobox.getText3())) {
						comboboxJson.setSelected(true);
					}
				}
				listCombobox.add(comboboxJson);
			}
		}
		return listCombobox;
	}

	/***
	 * 返回前台不分页的json
	 * 
	 * @param list
	 * @return
	 */
	public JsonPagination listJson(List<?> list) {
		JsonPagination jp = new JsonPagination();
		jp.setTotal(list.size());
		jp.setCurPage(1);
		jp.setRows(list);
		return jp;
	}

	/***
	 * 返回前台分页的json
	 * 
	 * @param list
	 * @return
	 */
	public JsonPagination pageListJson(List<?> list, int total, int curPage) {
		JsonPagination jp = new JsonPagination();
		jp.setTotal(total);
		jp.setCurPage(curPage);
		jp.setRows(list);
		return jp;
	}

	/**
	 * 返回布尔
	 * 
	 * @param ajax
	 * @return
	 */
	public Boolean getAjaxJson(Boolean ajax) {
		return ajax;
	}

	/**
	 * 返回String
	 */
	public String getStrJson(String str) {
		return str;
	}

	/**
	 * easyui 多选下拉框
	 * 
	 * @param list
	 *            数据集合
	 * @param selected
	 *            要选中的值
	 * @return
	 */
	public List<ComboboxJson> getMultipleComboboxJson(List<?> list,
			String selected) {
		List<ComboboxJson> listCombobox = new ArrayList<ComboboxJson>();
		ComboboxJson comboboxJson = null;
		if (null != list && list.size() > 0) {
			int t = list.size();
			for (int i = 0; i < t; i++) {
				comboboxJson = new ComboboxJson();
				Combobox combobox = (Combobox) list.get(i);
				comboboxJson.setId(combobox.getId());
				comboboxJson.setCode(combobox.getCode());
				comboboxJson.setText(combobox.getText());
				if (!StringUtil.isEmpty(selected)) {
					if (selected.equals(combobox.getId())
							|| selected.equals(combobox.getCode())
							|| selected.contains(combobox.getText())) {
						comboboxJson.setSelected(true);
					}
				}
				listCombobox.add(comboboxJson);
			}
		}
		return listCombobox;
	}
}
