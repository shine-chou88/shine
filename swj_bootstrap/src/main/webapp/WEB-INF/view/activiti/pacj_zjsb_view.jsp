<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
	<table cellpadding="5" cellspacing="0" class="main_table" width="100%">
           <tr>
          		<th width="20%">姓名：</th>
          		<td width="30%">${bean.jyyw.name}&nbsp;</td>
          		<th width="20%">性别：</th>
          		<td width="30%">${bean.jyyw.sex}&nbsp;</td>
          	</tr>
          	<tr>
          		<th width="20%">出生年月：</th>
          		<td width="30%">${bean.jyyw.birthday}&nbsp;</td>
          		<th width="20%">政治面貌：</th>
          		<td width="30%">${bean.jyyw.politics.name}&nbsp;</td>
          	</tr>
          	<tr>
          		<th width="20%">工作单位：</th>
          		<td width="30%" colspan="3">${bean.jyyw.unitName}&nbsp;</td>
          	</tr>
          	<tr>
          		<th width="20%">获评时间：</th>
          		<td width="30%">${bean.jyyw.getTime}&nbsp;</td>
          		<th width="20%">级别：</th>
          		<td width="30%">${bean.jyyw.level}&nbsp;</td>
          	</tr>	
          	<tr>
          		<th width="20%">资金类型：</th>
          		<td width="30%">${bean.moneyType.name}</td>
          		<th width="20%">金&nbsp;&nbsp;&nbsp;&nbsp;额：</th>
          		<td width="30%">${bean.money}</td>
          	</tr>
          	<tr>
          		<th width="20%">发放时间：</th>
          		<td width="30%" colspan="3">${bean.sendTime}</td>
          	</tr>
          	<tr>
          		<th width="20%">发放原因：</th>
          		<td width="30%" colspan="3">${bean.reason}</td>
          	</tr>
	   	
	</table>


