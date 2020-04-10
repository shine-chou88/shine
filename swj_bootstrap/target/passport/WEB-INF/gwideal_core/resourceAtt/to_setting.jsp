<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
<script type="text/javascript">
function saveSetting(){
	var fileMaxSize =document.getElementById("fileMaxSize").value;
	if(fileMaxSize.length==0){
		$.messager.alert('系统提示',"请设置文件上传总量！",'info');
		return;
	}
	if (isNaN(fileMaxSize)){
		$.messager.alert('系统提示',"文件上传总量大小必须是数字！",'info');
		return;
	}
	var flag=false;
	$('#settingForm').form('submit', {
				onSubmit: function(){ 
					flag=$(this).form('enableValidation').form('validate');
					if(flag){
						$.messager.progress();
					}
					return flag;
				}, 
				dataType:'json',
				success:function(data){
					if(flag){
						$.messager.progress('close');
					}
					var data = eval('(' + data + ')');
					if(data.success){
						$.messager.alert('系统提示',data.info,'info');
						closeWindow();
						$("#resource_dg").datagrid('reload');
					}else{
						$.messager.alert('系统提示',data.info,'error');
					}
				} 
	});
}
</script>
<div class="easyui-layout" fit="true">
	<form action="${base}/resourceAtt/setting" id="settingForm" method="post" data-options="novalidate:true" class="easyui-form">
		<div region="center" border="false" style="border-bottom: 0px solid #ccc;">
			<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
				<tr>
					<th class="br" colspan="2" style="text-align: center"></th>
				</tr>
				<tr>
					<th class="br" style="text-align: right;">文件上传总量：</th>
					<td class="br" style="text-align: left;">
						<input id="fileMaxSize" name="fileMaxSize"  type="text"  style="width: 100px;" value="${currMaxSize}"></input>&nbsp;GB
					</td>
				</tr>
			</table>
		</div>
		<div region="south" border="false" style="text-align: center; height: 32px; line-height: 30px;padding-left: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="saveSetting();">设置</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
		</div>
	</form>
</div>

</body>
</html>