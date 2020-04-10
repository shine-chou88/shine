<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>因私出国护照</title>
<style type="text/css">
</style>
</head>
<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
	<tr>
		<th width="220px" class="br">所在单位：</th>
		<td width="400px" class="br">${ foreign.belongDepart.name}&nbsp;</td>
		<td colspan="2" rowspan="6"
			style="border-bottom: 1px solid #95B8E7; border-right: 1px solid #95B8E7"><div
				style="width: 200px; height: 200px; display: flex; align-items: center; justify-content: center;">
				<img style="width: 100%; height: auto"
					src="${base}/attachment/IoQrCodeImg/${foreign.id}"></img>
			</div>
			<div style="display: flex; align-items: center; justify-content: center;">
			<span style="font-size:14px">校验码：${fn:substring(foreign.id,fn:length(foreign.id)-22,fn:length(foreign.id)-14)}</span>
			</div>
			<gwideal:perm url="/certificateInfo/printQrcode/{id}">
			<div style="display: flex; align-items: center; justify-content: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconcls="icon-print" onclick="printQrCode('${foreign.id}')">打印预览</a>
			</div>
			</gwideal:perm>
			</td>
	</tr>
	<tr>
		<th class="br">证照类型：</th>
		<td class="br">${ foreign.certificateTypeShow}&nbsp;</td>
		<td colspan="2"></td>

	</tr>
	<tr>
		<th class="br">国家码：</th>
		<td class="br">${ foreign.nationCode}&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">证件号码/护照号：</th>
		<td class="br">${ foreign.zjhm}&nbsp;<span
			<c:if test="${foreign.showStatus=='注销'}"> style="color:#e31e48;font-size:20px"</c:if>
			<c:if test="${foreign.showStatus!='注销'}">style="color:#1e89e3;font-size:20px"</c:if>>${foreign.showStatus}</span>
			<c:if test="${foreign.showStatus=='在库'}"> 
				<gwideal:perm url="/certificateInfo/expireScan/{id}">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-useScan32',size:'large',iconAlign:'left'" onclick="expireBackScan()" >过期领回扫描</a>&nbsp;
				</gwideal:perm>
			</c:if>
		</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">姓名：</th>
		<td class="br">${ foreign.name}&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">身份证号码：</th>
		<td class="br">${ foreign.sfzhm}&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">性别：</th>
		<td colspan="3" class="br">${ foreign.sex}&nbsp;</td>
	</tr>
	<tr>
		<th class="br">出生日期：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ foreign.birthDate}" pattern="yyyy-MM-dd" /> &nbsp;</td>
	</tr>
	<tr>
		<th class="br">出生地点：</th>
		<td colspan="3" class="br">${ foreign.birthPlace}&nbsp;</td>
	</tr>
	<tr>
		<th class="br">有效期限：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ foreign.effectiveDate}" pattern="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th class="br">签发机关：</th>
		<td colspan="3" class="br">${ foreign.issuanceAuthority}&nbsp;</td>
	</tr>

	<tr>
		<th class="br">签发地点：</th>
		<td colspan="3" class="br">${ foreign.issuancePlace}&nbsp;</td>
	</tr>

	<tr>
		<th class="br">签发日期：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ foreign.issuanceDate}" pattern="yyyy-MM-dd" /> <%--           			${ bean.issuanceDate}&nbsp; --%>
		</td>
	</tr>
	<tr>
		<th class="br">通行证种类/护照类型：</th>
		<td colspan="3" class="br">${ foreign.type}&nbsp;</td>
	</tr>
	<c:if test="${not empty foreign.cancelTime }">
		<tr>
			<th class="br">操作人：</th>
			<td colspan="3" class="br">${ foreign.cancelUser.name}&nbsp;</td>
		</tr>
		<tr>
			<th class="br">注销时间：</th>
			<td colspan="3" class="br"><fmt:formatDate
					value="${ foreign.cancelTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
	</c:if>
</table>
<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
	<tr>
		<th colspan="8" class="br"
			style="font-size: 16px; text-align: center; background: -moz-linear-gradient(top, #F0FFFF, #60B0FF); height: 25px; line-height: 25px;">证照使用记录</th>
	</tr>
</table>
<div border="false" style="height: 200px">
	<table id="foreign_certificateLog_dg" class="easyui-datagrid"
		data-options="collapsible:true,rownumbers:true,url:'${base }/certificateOperateLog/jsonPagination?certificateId=${foreign.id}',
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
					width="150px">操作内容</th>
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
<div region="south" border="false"
	style="text-align: center; padding: 2px 2px 2px 2px;">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
</div>
<script type="text/javascript">
function expireBackScan(){
	var win=creatFreeWindow("child_window_1",'证照过期领回-扫描',780,350,'icon-search','/certificateInfo/expireScan/${foreign.id}');
    win.window('open');
}
</script>
</html>