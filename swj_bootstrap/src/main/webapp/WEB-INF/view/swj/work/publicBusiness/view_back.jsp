<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>${title}</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
	<table cellpadding="5" cellspacing="0"  width="100%">
	<tr>
		<th colspan="8" class="br"
			style="font-size: 16px; text-align: center; height: 25px; line-height: 25px;">证照归还信息表</th>
	</tr>
</table>
				<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
					<tr>
						<th width="30%" class="br">组团单位：
						</th>
						<td width="70%" class="br">
							${bean.groupDepartName}&nbsp;&nbsp;
							</td>
					</tr>
					<tr>
				            <th class="br">证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div  style="float: left; width:450px; height:100px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" >
			          			        <c:forEach items="${bean.certificateInfos}" var="certificate" varStatus="vs">
					          				<div style='float:left;margin-left:8px;margin-top:8px;' >
					          					${certificate.zjhm}-${certificate.name}
					          				</div>
					          			</c:forEach>
			          			</div>          			
			          		</td>     	        		
				         </tr>
				   <tr>
						<th width="30%" class="br">批件号：</th>
						<td width="70%" class="br">${ bean.approvalNo}<c:if test="${empty bean.approvalNo}">无(其他理由借用) </c:if>&nbsp;&nbsp;</td>
					</tr>
				    <tr>
						<th width="30%" class="br">领用人姓名：</th>
						<td width="70%" class="br">${ bean.applyUserName}&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">实际出境日期：</th>
						<td width="70%" class="br">
						<fmt:formatDate
				value="${ bean.realExitTime}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">实际入境日期：</th>
						<td width="70%" class="br">
						<fmt:formatDate
				value="${ bean.realEnterTime}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">实际出访国家地区(含经停城市)：</th>
						<td width="70%" class="br">${ bean.realVisitCountry}&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">有无超天数超国家等违规情况：</th>
						<td width="70%" class="br">${ bean.hasViolationSituation}&nbsp;&nbsp;</td>
					</tr>
					
					<tr>
						<th width="30%" class="br">归还人姓名：</th>
						<td width="70%" class="br">${ bean.backUserName}&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人单位：</th>
						<td width="70%" class="br">${ bean.backUserDepartName}&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人电话：</th>
						<td width="70%" class="br">${ bean.backUserPhone}&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">计划归还日期：</th>
						<td width="70%" class="br"><fmt:formatDate
				value="${ bean.backDate}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</td>
					</tr>
					<c:if test="${bean.backStatus=='归还完成' }">
					<th width="30%" class="br">实际归还日期：</th>
						<td width="70%" class="br">
						<fmt:formatDate
				value="${ bean.realBackDate}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</td>
					</c:if>
				</table>
				<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
	<tr>
		<th colspan="8" class="br"
			style="font-size: 16px; text-align: center; background: -moz-linear-gradient(top, #F0FFFF, #60B0FF); height: 25px; line-height: 25px;">证照归还信息表记录</th>
	</tr>
</table>
<div border="false" style="height: 200px">
	<table id="use_publicBusinessLog_dg" class="easyui-datagrid"
		data-options="collapsible:true,rownumbers:true,url:'${base }/publicBusinessLog/jsonPagination?businessId=${bean.id}&operateType=back',
					method:'post',fit:true,singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true"></th>
				<th
					data-options="field:'operateTime',align:'left',resizable:true,sortable:true"
					formatter="ChangeDateFormat">操作时间</th>
				<th
					data-options="field:'operateContent',align:'left',resizable:true,sortable:true"
					width="200px">操作内容</th>
				<th
					data-options="field:'operateUserName',align:'left',resizable:true,sortable:true"
					width="80px">操作人</th>
				<th
					data-options="field:'operateUserDepartName',align:'left',resizable:true,sortable:true"
					width="150px">操作人所在单位</th>

			</tr>
		</thead>
	</table>

</div>
</div>
<div region="south" border="false"
	style="text-align: center; padding: 2px 2px 2px 2px;">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
</div>
</div>	
</body>
</html>