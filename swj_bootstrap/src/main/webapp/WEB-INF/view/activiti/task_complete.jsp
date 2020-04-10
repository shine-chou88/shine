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
	$(function(){
		if("${isOrgStaff}"=="true"&&"${taskDefinitionKey}"=='ORGANIZATION_STAFF'){
			if("${bean.privateDestination}"=='无'){
				document.getElementById("showRecordApplyInfoPrivate").style.display="none";
			}
			if("${bean.publicDestination}"=='无'){
				document.getElementById("showRecordApplyInfoPublic").style.display="none";
			}
		}
	})
		function taskCompleteForm(pass,rejected){
			document.getElementById("pass").value=pass;
			document.getElementById("rejected").value=rejected;
			var tips="确认通过吗？";
			if(${processButton.needNoPassButton}==false){
				tips="确定吗？";
			}
			if(rejected=="true"){
				tips="确认不通过吗？";	
			}else{
				if(pass=="false"){
					tips="确认退回吗？";	
				}
			}
			var flag=false;
			$('#taskCompleteForm').form('submit', {
				onSubmit: function(){ 
					flag=$(this).form('enableValidation').form('validate');
						if($("#hasPrivateRecordCheck")[0]!=undefined&&$("#hasPrivateRecordCheck")[0]!=null){
							if(!$("#hasPrivateRecordCheck")[0].checked){
								$("#privateDateDesc").val($("#privateDateDescInput").val());
								$("#privateDestination").val($("#privateDestinationInput").val());
								$("#privateReason").val($("#privateReasonInput").val());
									if(judgeIsNull("privateDateDesc")||judgeIsNull("privateDestination")||judgeIsNull("privateReason")){
										if(tips=="确认通过吗？"){
										$.messager.alert('系统提示','因私出国（境）记录勾选无或请填写完整！', 'info');
										return false;
										}
									}
							}
						}
						if($("#hasPublicRecordCheck")[0]!=undefined&&$("#hasPublicRecordCheck")[0]!=null){
							if(!$("#hasPublicRecordCheck")[0].checked){
								$("#publicDateDesc").val($("#publicDateDescInput").val());
								$("#publicDestination").val($("#publicDestinationInput").val());
								$("#publicReason").val($("#publicReasonInput").val());
									if(judgeIsNull("publicDateDesc")||judgeIsNull("publicDestination")||judgeIsNull("publicReason")){
										if(tips=="确认通过吗？"){
										$.messager.alert('系统提示','因公出国（境）记录勾选无或请填写完整！', 'info');
										return false;
										}
									}
							}
						}
					if(flag){
						flag=confirm(tips);
					}
					if(flag){
						$.messager.progress();
						if(rejected=="false"&&pass=="false"&&$('#taskComment').textbox('getValue')=='同意'){
							$('#taskComment').textbox('setValue','退回');	
						}
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
						$('#taskCompleteForm').form('clear');
						$("#activiti_task_dg").datagrid('reload');
						$.ajax({ 
							type: 'POST', 
							url: '${base}/index/getUnReadMsg',
							dataType: 'json',  
							success: function(data){
								if(data.success){
									$("#unReadTaskSpan").html(data.info);
								}
							} 
						}); 
						document.getElementById('indexIframe').src="${base}/index/indexPart";
					}else{
						$.messager.alert('系统提示',data.info,'error');
					}
				} 
			});
		}
		
		function commentCheckedChange(){
			var commentChecked = $("input:radio[name='commentChecked']:checked").val();
			if(commentChecked.length>0){
				$('#taskComment').textbox('setValue',commentChecked);
			}
		}
		function hasCheckRecord(obj,id){
			if(obj.checked){
				document.getElementById(id).style.display="none";
				if(id=='showRecordApplyInfoPublic'){
					$("#publicDateDesc").val("");
					$("#publicDestination").val("无");
					$("#publicReason").val("");
				}else if(id=='showRecordApplyInfoPrivate'){
					$("#privateDateDesc").val("");
					$("#privateDestination").val("无");
					$("#privateReason").val("");
				}
			}else{
				document.getElementById(id).style.display="";
				if(id=='showRecordApplyInfoPublic'){
					$("#publicDestination").val("");
					if($("#publicDestinationInput").val()=="无"){
						$("#publicDestinationInput").textbox('setValue','');
					}
				}else if(id=='showRecordApplyInfoPrivate'){
					$("#privateDestination").val("");
					if($("#privateDestinationInput").val()=="无"){
						$("#privateDestinationInput").textbox('setValue','');
					}
				}
			}
		}
		function judgeIsNull(id){
			if($("#"+id).val()!=null&&$("#"+id).val()!=""){
				return false;
			}else{
				return true;
			}
		}
	</script>
    <div class="easyui-layout" fit="true">
      <form id="taskCompleteForm" action="${base}/activiti/process/complete" method="post" data-options="novalidate:true" class="easyui-form">
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
		<c:set var="southHeight" value="150px" scope="request"/>
		<c:if test="${taskDefinitionKey=='OVER_SETUP--ORGANIZATION_STAFF' || taskDefinitionKey=='INPUT_OVER_SETUP--ORGANIZATION_STAFF'}">
			<c:set var="southHeight" value="180px" scope="request"/>
		</c:if>
		<c:if test="${taskDefinitionKey=='ORGANIZATION_DIRECTOR'}">
			<c:set var="southHeight" value="200px" scope="request"/>
		</c:if>
		<div region="south" border="false" style="text-align: center;padding: 2px 2px 2px 2px;height: ${southHeight};">
	    		<input type="hidden" name="id" value="${taskId}"/>
	    		<input type="hidden" name="pass" value="true" id="pass"/>
	    		<input type="hidden" name="hasPassport" value="${hasPassport}" id="hasPassport"/>
	    		<input type="hidden" name="hasOldPassport" value="${hasOldPassport}" id="hasOldPassport"/>
	    		<input type="hidden" name="rejected" value="false" id="rejected"/>
	    		<c:set var="defCommentVal" value="同意"/>
				<table border="0" cellpadding="0" cellspacing="0" class="main_table">
					<tr>
						<th width="20%">
							<c:if test="${processButton.needNoPassButton==true}"><strong style="color: red;">*</strong>审批意见：</c:if>
							<c:if test="${processButton.needNoPassButton==false}"><strong style="color: red;">*</strong>备注：</c:if>
						</th>
						<td width="80%">
							<c:if test="${taskDefinitionKey=='OVER_SETUP--ORGANIZATION_STAFF' || taskDefinitionKey=='INPUT_OVER_SETUP--ORGANIZATION_STAFF'}">
								<div style="padding-bottom: 10px;">
									<input type="radio" value="归档" name="commentChecked" onclick="commentCheckedChange()"/>归档&nbsp;
									<input type="radio" value="更换证照" name="commentChecked" onclick="commentCheckedChange()"/>更换证照&nbsp;
									<input type="radio" value="行程取消" name="commentChecked" onclick="commentCheckedChange()"/>行程取消&nbsp;
									<input type="radio" value="行程变更" name="commentChecked" onclick="commentCheckedChange()"/>行程变更
								</div>
								<c:set var="defCommentVal" value=""/>
							</c:if>
							<input class="easyui-textbox" id="taskComment" type="text" name="comment" value="${defCommentVal}" data-options="required:true,multiline:true,validType:'length[1,1000]'" style="height:80px;width: 750px;"/>
						</td>
					</tr>
					<c:if test="${taskDefinitionKey=='ORGANIZATION_DIRECTOR'}">
					<tr>
						<th><strong style="color: red;">*</strong>分管局领导：</th>
						<td>
							<input id="nextApproveUser" required="required" style="width: 120px;" class="easyui-combobox" name="nextAssignee" data-options="url:'${base}/activiti/process/approveUser?actId=${listUserTask[0].actId}',method:'get',valueField:'id',textField:'text',panelHeight:'auto',editable:false"/>
						</td>
					</tr>
					</c:if>
					<tr>
						<td colspan="4" style="text-align: center;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="taskCompleteForm('true','false');">
								<c:if test="${processButton.needNoPassButton==true}">通过</c:if>
								<c:if test="${processButton.needNoPassButton==false}">确定</c:if>
							</a>
							<c:if test="${processButton.needNoPassButton==true}">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="taskCompleteForm('false','false');">退回</a>
							</c:if>
							<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
						</td>
					</tr>
				</table>
		</div>
		</form>
	</div>
</body>
</html>

