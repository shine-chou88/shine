<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
	<table cellpadding="5" cellspacing="0" class="main_table" width="100%">
	   	<tr>
	   		<th width="20%">姓名：</th>
	   		<td width="30%">${bean.xm}&nbsp;</td>
	   		<th width="20%">证件号码：</th>
	   		<td width="30%">${fn:substring(bean.zjhm,0,12)}******&nbsp;</td>
	   	</tr>
	   	<tr>
          	<th width="20%">所属街镇：</th>
          	<td width="30%">${bean.jddm.name}&nbsp;</td>
          	<th width="20%">所属居委：</th>
          	<td width="30%">${bean.jcwdm.jwhName}&nbsp;</td>
	   	</tr>
	   	<tr>
	   		<th width="20%">性别：</th>
          	<td width="30%">${bean.bz1}&nbsp;</td>
          	<th width="20%">类别：</th>
          	<td width="30%">${bean.lb}&nbsp;</td>
	   	</tr>
	   	<tr>
	   		<th width="20%">户籍地址：</th>
          	<td width="30%">${bean.hjdxz}&nbsp;</td>
          	<th width="20%">居住地址：</th>
          	<td width="30%">${bean.jzdz}&nbsp;</td>
	   	</tr>
	   	<tr>
	   		<th width="20%">毒瘾状态：</th>
          	<td width="30%">${bean.dyzt}&nbsp;</td>
          	<th width="20%">户籍状态：</th>
          	<td width="30%">${bean.hjzt}&nbsp;</td>
	   	</tr>
	   	<tr>
	   		<th width="20%">戒毒状态：</th>
          	<td width="30%">${bean.jdzt}&nbsp;</td>
          	<th width="20%">疾病状态：</th>
          	<td width="30%">${bean.jbzt}&nbsp;</td>
	   	</tr>
	   	<tr>
	   		<th width="20%">住址状态：</th>
          	<td width="30%">${bean.zzzt}&nbsp;</td>
          	<th width="20%">居委会电话：</th>
          	<td width="30%">${bean.jcwdh}&nbsp;</td>
	   	</tr>
	   	<tr>
	   		<th width="20%">治保主任：</th>
          	<td width="30%">${bean.zbzr}&nbsp;</td>
          	<th width="20%">责任社工：</th>
          	<td width="30%">${bean.zrsg}&nbsp;</td>
	   	</tr>
	   	
	</table>


