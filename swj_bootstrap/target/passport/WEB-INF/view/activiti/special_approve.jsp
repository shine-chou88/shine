<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
	<script type="text/javascript">
		function saveSpecialApproveCompleteForm(){
			if($("#specialApproveTargetTaskDefinitionKey").combobox("getValue").length==0){
				$.messager.alert('系统提示','请选择要特送到哪个节点！','info');
				return;
			}
			var flag=false;
			$('#specialApproveCompleteForm').form('submit', {
				onSubmit: function(){ 
					flag=$(this).form('enableValidation').form('validate');
					if(flag){
						flag=confirm("确定特送吗？");
					}
					if(flag){
						$.messager.progress();
					}
					return flag;
				}, 
				success:function(data){
					if(flag){
						$.messager.progress('close');
					}
					var data = eval('(' + data + ')');
					if(data.success){
						$.messager.alert('系统提示',data.info,'info');
						closeWindow();
						$('#specialApproveCompleteForm').form('clear');
						$("#certificateInfoApplyListDg").datagrid('reload');
					}else{
						$.messager.alert('系统提示',data.info,'error');
					}
				} 
			});
		}
	</script>
    <div class="easyui-layout" fit="true">
      <form id="specialApproveCompleteForm" action="${base}/activiti/process/specialApprove" method="post" data-options="novalidate:true" class="easyui-form">
    	<div region="center" border="false" style="border-bottom: 1px solid #ccc;">
    		<div class="easyui-tabs" border="0">
				<c:if test="${processDefinitionKey=='DEPUTY_CADRES_PASSPORT_APPLY' || processDefinitionKey=='OFFICAL_CADRES_PASSPORT_APPLY'}">
					<div title="因私出国（境）审批">
						<jsp:include page="/WEB-INF/view/activiti/certificate_apply_view.jsp"></jsp:include>
					</div>
				</c:if>
				<div title="审批流程">
					<jsp:include page="/activiti/process/viewByProcessInstanceId/${processInstanceId}"></jsp:include>
				</div>
			</div>
		</div>
		<c:set var="southHeight" value="180px" scope="request"/>
		<div region="south" border="false" style="text-align: center;padding: 2px 2px 2px 2px;height: ${southHeight};">
	    		<input type="hidden" name="id" value="${taskId}"/>
	    		<input type="hidden" name="pass" value="true" id="pass"/>
	    		<input type="hidden" name="hasPassport" value="${hasPassport}" id="hasPassport"/>
	    		<input type="hidden" name="rejected" value="false" id="rejected"/>
	    		<input type="hidden" name="processInstanceId" value="${bean.processInstanceId}" id="processInstanceId"/>
				<table border="0" cellpadding="0" cellspacing="0" class="main_table">
					<tr>
						<th width="20%"><strong style="color: red;">*</strong>特送至：</th>
						<td width="80%">
							<select name="targetTaskDefinitionKey" class="easyui-combobox" id="specialApproveTargetTaskDefinitionKey">
								<option value="">--请选择--</option>
								<c:forEach items="${listTask}" var="task">
									<option value="${task.actId}">${task.actName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th width="20%"><strong style="color: red;">*</strong>特送原因：</th>
						<td width="80%">
							<input class="easyui-textbox" type="text" name="comment" data-options="required:true,multiline:true,validType:'length[1,1000]'" style="height:80px;width: 750px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="saveSpecialApproveCompleteForm();">确定</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
						</td>
					</tr>
				</table>
		</div>
		</form>
	</div>
</body>
</html>

