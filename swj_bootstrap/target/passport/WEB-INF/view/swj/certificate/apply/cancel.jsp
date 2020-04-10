<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
	<title>${title}</title>
</head>
<body> 
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<form id="certificateInfoApplyCancelForm" action="${base}/certificateInfoApply/cancel/${id}" method="post" data-options="novalidate:true" class="easyui-form">
       		<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
	        	<tr>
	          		<th class="br">
	          			<strong style="color: red;">*</strong>取消原因：
	          		</th>
	          		<td class="br">
	          			<input class="easyui-textbox" type="text" name="reason" data-options="multiline:true,required:true" style="width:450px;height: 100px;"/>
	          		</td>
	        	</tr>
	        	<tr>
		            <td class="br" colspan="2">
		           		<div region="south" border="false" style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconcls="icon-ok" onclick="saveCancelCertificateInfoApply()">保存</a> 
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
						</div>
		            </td>
		        </tr>
       		</table>
		</form>	
		
	</div>
</div>    	
<script type="text/javascript">
function saveCancelCertificateInfoApply(){
	$.messager.confirm('系统提示','确认取消吗?',function(id){ 
		if(id){
			var flag=false;
			$('#certificateInfoApplyCancelForm').form('submit', {
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
						$.messager.alert('系统提示', data.info, 'info');
						closeWindow();
						$('#certificateInfoApplyCancelForm').form('clear');
						$('#certificateInfoApplyListDg').datagrid('reload');
					}else{
						$.messager.alert('系统提示', data.info, 'error');
					}
				} 
			});
		}
	});
}
</script>
</body>
</html>