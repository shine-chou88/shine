<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>大陆居民往来台湾通行证(因私)</title>
<style type="text/css">
</style>
</head>
<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
	<tr>
		<th width="220px" class="br">所在单位：</th>
		<td width="400px" class="br">${ taiwan.belongDepart.name}&nbsp;</td>
		<td colspan="2" rowspan="6"
			style="border-bottom: 1px solid #95B8E7; border-right: 1px solid #95B8E7"><div
				style="width: 200px; height: 200px; display: flex; align-items: center; justify-content: center;">
				<img style="width: 100%; height: auto"
					src="${base}/attachment/IoQrCodeImg/${taiwan.id}"></img>
					
			</div>
			<div style="display: flex; align-items: center; justify-content: center;">
			<span style="font-size:14px">校验码：${fn:substring(taiwan.id,fn:length(taiwan.id)-22,fn:length(taiwan.id)-14)}</span>
			</div>
			<gwideal:perm url="/certificateInfo/printQrcode/{id}">
			<div style="display: flex; align-items: center; justify-content: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconcls="icon-print" onclick="printQrCode('${taiwan.id}')">打印预览</a>
			</div>
			</gwideal:perm>
			</td>
	</tr>
	<tr>
		<th class="br">证照类型：</th>
		<td class="br">${ taiwan.certificateTypeShow}&nbsp;</td>
		<td colspan="2"></td>

	</tr>
	<tr>
		<th class="br">国家码：</th>
		<td class="br">${ taiwan.nationCode}&nbsp;</td>
		<td colspan="2"></td>

	</tr>
	<tr>
		<th class="br">证件号码/护照号：</th>
		<td class="br">${ taiwan.zjhm}&nbsp;<span
			<c:if test="${taiwan.showStatus=='注销'}"> style="color:#e31e48;font-size:20px"</c:if>
			<c:if test="${taiwan.showStatus!='注销'}">style="color:#1e89e3;font-size:20px"</c:if>>${taiwan.showStatus}</span>
			<c:if test="${taiwan.showStatus=='在库'}"> 
				<gwideal:perm url="/certificateInfo/expireScan/{id}">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-useScan32',size:'large',iconAlign:'left'" onclick="expireBackScan()" >过期领回扫描</a>&nbsp;
				</gwideal:perm>
			</c:if>
		</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">姓名：</th>
		<td class="br">${ taiwan.name}&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">身份证号码：</th>
		<td  class="br">${ taiwan.sfzhm}&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">性别：</th>
		<td colspan="3" class="br">${ taiwan.sex}&nbsp;</td>
	</tr>
	<tr>
		<th class="br">出生日期：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ taiwan.birthDate}" pattern="yyyy-MM-dd" /> &nbsp;</td>
	</tr>
	<tr>
		<th class="br">出生地点：</th>
		<td colspan="3" class="br">${ taiwan.birthPlace}&nbsp;</td>
	</tr>
	<tr>
		<th class="br">有效期限：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ taiwan.effectiveDate}" pattern="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th class="br">签发机关：</th>
		<td colspan="3" class="br">${ taiwan.issuanceAuthority}&nbsp;</td>
	</tr>

	<tr>
		<th class="br">签发地点：</th>
		<td colspan="3" class="br">${ taiwan.issuancePlace}&nbsp;</td>
	</tr>

	<tr>
		<th class="br">签发日期：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ taiwan.issuanceDate}" pattern="yyyy-MM-dd" /> <%--           			${ bean.issuanceDate}&nbsp; --%>
		</td>
	</tr>
	<tr>
		<th class="br">通行证种类/护照类型：</th>
		<td colspan="3" class="br">${ taiwan.type}&nbsp;</td>
	</tr>
	<c:if test="${not empty taiwan.cancelTime }">
		<tr>
			<th class="br">操作人：</th>
			<td colspan="3" class="br">${ taiwan.cancelUser.name}&nbsp;</td>
		</tr>
		<tr>
			<th class="br">注销时间：</th>
			<td colspan="3" class="br"><fmt:formatDate
					value="${ taiwan.cancelTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
	<table id="taiwan_certificateLog_dg" class="easyui-datagrid"
		data-options="collapsible:true,rownumbers:true,url:'${base }/certificateOperateLog/jsonPagination?certificateId=${taiwan.id}',
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
	var win=creatFreeWindow("child_window_1",'证照过期领回-扫描',780,350,'icon-search','/certificateInfo/expireScan/${taiwan.id}');
    win.window('open');
}
</script>
</html>