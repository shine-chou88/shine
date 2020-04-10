<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>入库页面</title>
<style type="text/css">
</style>
</head>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
	<tr>
		<th width="220px" class="br">国家码：</th>
		<td width="400px" class="br">${ bean.nationCode}&nbsp;</td>
		<td colspan="2" rowspan="6"
			style="border-bottom: 1px solid #95B8E7; border-right: 1px solid #95B8E7"><div
				style="width: 210px; height: 210px; display: flex; align-items: center; justify-content: center;">
				<img style="width: 100%; height: auto"
					src="${base}/attachment/IoQrCodeImg/${bean.id}"></img>
	
			</div>
			<div style="display: flex; align-items: center; justify-content: center;">
			<span style="font-size:14px">校验码：${fn:substring(bean.id,fn:length(bean.id)-8,fn:length(bean.id))}</span>
			</div>
			<div  style="display: flex; align-items: center; justify-content: center;"><a href="javascript:void(0)" class="easyui-linkbutton"
		iconcls="icon-print" onclick="printQrCode('${bean.id}')">打印预览</a></div>
			</td>
	</tr>
	<tr>
		<th class="br">证照类型：</th>
		<td class="br">${ bean.certificateTypeShow}&nbsp;</td>
		<td colspan="2"></td>

	</tr>
	<tr>
		<th class="br">证件号码/护照号：</th>
		<td class="br">${ bean.zjhm}&nbsp;<span
			<c:if test="${bean.showStatus=='注销'}"> style="color:#e31e48;font-size:20px"</c:if>
			<c:if test="${bean.showStatus!='注销'}">style="color:#1e89e3;font-size:20px"</c:if>>${bean.showStatus}</span>
		</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">所在单位：</th>
		<td class="br">${ bean.belongDepart.name}&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">姓名：</th>
		<td class="br">${ bean.name}&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th class="br">身份证号码：</th>
		<td  class="br" >${ bean.sfzhm}&nbsp;
		</td>
	</tr>
	<tr>
		<th class="br">性别：</th>
		<td colspan="3" class="br">${ bean.sex}&nbsp;</td>
	</tr>
	<tr>
		<th class="br">出生日期：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ bean.birthDate}" pattern="yyyy-MM-dd" /> &nbsp;</td>
	</tr>
	<tr>
		<th class="br">出生地点：</th>
		<td colspan="3" class="br">${ bean.birthPlace}&nbsp;</td>
	</tr>
	<tr>
		<th class="br">有效期限：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ bean.effectiveDate}" pattern="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th class="br">签发机关：</th>
		<td colspan="3" class="br">${ bean.issuanceAuthority}&nbsp;</td>
	</tr>

	<tr>
		<th class="br">签发地点：</th>
		<td colspan="3" class="br">${ bean.issuancePlace}&nbsp;</td>
	</tr>

	<tr>
		<th class="br">签发日期：</th>
		<td colspan="3" class="br"><fmt:formatDate
				value="${ bean.issuanceDate}" pattern="yyyy-MM-dd" /> 
		</td>
	</tr>
	<tr>
		<th class="br">通行证种类/护照类型：</th>
		<td colspan="3" class="br">${ bean.type}&nbsp;</td>
	</tr>
</table>
</div>
<div region="south" border="false"
	style="text-align: center; padding: 2px 2px 2px 2px;">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconcls="icon-ok" onclick="saveCheckIn()">入库</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
</div>
</div>
<script>
function saveCheckIn(){
	$.messager.confirm('系统提示','确认入库吗?',function(id){ 
		if(id){ 
			$.ajax({ 
				type: 'POST', 
				url: base+'/certificateInfo/saveCheckIn/${bean.id}',
				dataType: 'json',  
				success: function(data){
					if(data.success){
						$.messager.alert('系统提示', data.info, 'info');
						closeWindow();
						$("#certificateInfoListDg").datagrid('reload'); 
					}else{ 
						$.messager.alert('系统提示', data.info, 'info');
					} 
				} 
			}); 
		} 
	}); 	
}
</script>
</html>