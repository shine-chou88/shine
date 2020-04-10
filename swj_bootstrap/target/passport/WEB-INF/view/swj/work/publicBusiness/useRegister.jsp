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
			<form id="publicBusinessEditForm"
				action="${base}/publicBusiness/saveUseRegister" method="post"
				data-options="novalidate:true" class="easyui-form">
				<input type="hidden" name="id" value="${bean.id}" />
				<input type="hidden"  name="useStatus" value="${bean.useStatus}" />
				<input type="hidden" name="hasSubmit" id="useRegisterSubmit" />
				<table cellpadding="5" cellspacing="0"  width="100%">
	<tr>
		<th colspan="8" class="br"
			style="font-size: 16px; text-align: center; height: 25px; line-height: 25px;">证照领用信息表</th>
	</tr>
</table>
				<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>组团单位：
						</th>
						<td width="70%" class="br">
							<input type="text" id="groupDepartId" name="groupDepart.id" data-options="url:'${base}/depart/certificateDepartJson?selected=${empty bean.groupDepart.code? currentUser.depart.code :bean.groupDepart.code}',valueField:'id',textField:'text'" class="easyui-combobox"  style="width:250px" />
							</td>
					</tr>
					<tr>
				            <th class="br"><strong style="color: red;">*</strong>证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div id="certificate_div_info" style="float: left; width:450px; height:100px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" >
			          			        <c:forEach items="${bean.certificateInfos}" var="certificate" varStatus="vs">
					          				<div style='float:left;margin-left:8px;margin-top:8px;' >
					          					<input type='hidden' name='certificateIds'  value='${certificate.id}'></input>
					          					${certificate.zjhm}-${certificate.name}
					          					<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick = 'cancelCertificateInfoSelect(this)' title='删除' id='${certificate.id}'></img>
					          				</div>
					          			</c:forEach>
			          			</div>          			
			          			<div style="float: left; text-align: center; height:65px; margin: 5px;">
								   <input type="button" value="选" class="button_blue" style="margin-top: 75px" onclick="selectCertificateInfoMuti()"></input>
								</div>
								<div style="float: bottom; width:450px">
								<span style="font-size:12px;color:red">注:只能查询到有效期限大于6个月的证照信息</span>
								</div>
			          		</td>     	        		
				         </tr>
					<tr>
						<th width="30%" class="br"><strong id="publicBusiness_useRegister_approvalNo_check" style="color: red;<c:if test="${!empty bean && empty bean.approvalNo}">display: none;</c:if>">*</strong>批件号：</th>
						<td width="70%" class="br"><input type="text"
							name="approvalNo" class="easyui-textbox" id="publicBusiness_useRegister_approvalNo"
							value="${ bean.approvalNo}" style="width: 200px" <c:if test="${!empty bean && empty bean.approvalNo}"> readonly="readonly"</c:if> <c:if test="${empty bean}">data-options="required:true"</c:if> ></input>
							<input type="checkbox" <c:if test="${!empty bean && empty bean.approvalNo}"> checked="checked"</c:if> onclick="useRegisterApprovalNo(this)"></input>其他理由借用
						</td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>计划出境日期：</th>
						<td width="70%" class="br"><input id="planExitTimeSelect"
							name="planExitTime" value="<fmt:formatDate value="${bean.planExitTime}" pattern="yyyy-MM-dd" />" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'planEnterTimeSelect\')}'})" readonly="readonly" style="width: 88px;"></input></td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>计划入境日期：</th>
						<td width="70%" class="br"><input id="planEnterTimeSelect"
							name="planEnterTime" value="<fmt:formatDate value="${ bean.planEnterTime}" pattern="yyyy-MM-dd" />" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'planExitTimeSelect\')}'})" readonly="readonly" style="width: 88px;"></input></td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>领用人姓名：</th>
						<td width="70%" class="br"><input type="text"
							name="applyUserName" class="easyui-textbox"
							value="${ bean.applyUserName}" style="width: 200px" data-options="required:true"></input></td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>领用人单位：</th>
						<td width="70%" class="br"><input type="text"
							name="applyUserDepartName" class="easyui-textbox"
							value="${ bean.applyUserDepartName}" style="width: 200px" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">领用人电话：</th>
						<td width="70%" class="br"><input type="text"
							name="applyUserPhone" class="easyui-textbox"
							value="${ bean.applyUserPhone}" style="width: 200px"></input></td>
					</tr>
					<tr>
						<th width="30%" class="br">计划领用日期：</th>
						<td width="70%" class="br"><input type="text"
							name="applyDate" 
							value="<fmt:formatDate value="${bean.applyDate}" pattern="yyyy-MM-dd" />"  class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'planExitTimeSelect\')}'})" readonly="readonly" style="width: 88px;"></input></td>
					</tr>
					<tr>
						<td class="br" colspan="2">
							<div region="south" border="false"
								style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-save" onclick="savePublicBusinessForm('no')">保存</a> 
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-ok" onclick="savePublicBusinessForm('yes')">提交</a> 
									<a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
function savePublicBusinessForm(type){
	if('yes'==type){
		$("#useRegisterSubmit").val('true');
	}else{
		$("#useRegisterSubmit").val('false');
	}
	var groupDepartId=$("#groupDepartId").combobox('getValue');
	if(groupDepartId==null||groupDepartId==''){
		$.messager.alert('系统提示', "组团单位不能为空", 'info');
		return false;
	}
	var certificateIds=document.getElementsByName("certificateIds");
	if(certificateIds.length==0){
		$.messager.alert('系统提示', "证照编号及持有人姓名不能为空", 'info');
		return false;
	}
	if($("#planExitTimeSelect").val()==null||$("#planExitTimeSelect").val()==''){
		$.messager.alert('系统提示', "计划出境日期不能为空", 'info');
		return false;
	}
	if($("#planEnterTimeSelect").val()==null||$("#planEnterTimeSelect").val()==''){
		$.messager.alert('系统提示', "计划入境日期不能为空", 'info');
		return false;
	}
	var flag=false;
	$('#publicBusinessEditForm').form('submit', {
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
				$('#publicBusinessEditForm').form('clear');
				$('#publicBusinessListDg').datagrid('reload');
			}else{
				$.messager.alert('系统提示', data.info, 'error');
			}
		} 
	});
}


	var certifcateNames = new Array();
	function selectCertificateInfoMuti(selectType){
		var win=creatFreeWindow("third_window",'添加-证照信息',830,450,'icon-add',"/certificateInfo/refList");
	    win.window('open');
	}
	
	function isCertificateExist(id) {
		for (i = 0; i < certifcateNames.length; i++) {
			if (certifcateNames[i] == id) {
				return true;
			}
		}
		return false;
	}
	function cancelCertificateInfoSelect(obj) {
		var id = obj.id;
		if (certifcateNames.length > 0) {
			for (i = 0; i < certifcateNames.length; i++) {
				if (certifcateNames[i] == id) {
					certifcateNames.splice(i, 1);
				}
			}
		}
		$(obj).parent().remove();
	}
	
	function useRegisterApprovalNo(obj) {
		if ($(obj).is(':checked')) {
			$('#publicBusiness_useRegister_approvalNo_check').hide();
			$('#publicBusiness_useRegister_approvalNo').val("");
			$('#publicBusiness_useRegister_approvalNo')[0].removeAttribute("data-options");
			$('#publicBusiness_useRegister_approvalNo').textbox('textbox').validatebox('options').required = false;
			$('#publicBusiness_useRegister_approvalNo').textbox('textbox').attr('readonly',true);
		} else {
			$('#publicBusiness_useRegister_approvalNo_check').show();
			$('#publicBusiness_useRegister_approvalNo').textbox('textbox').validatebox('options').required = true;
			$('#publicBusiness_useRegister_approvalNo').textbox('textbox').attr('readonly',false);
		}
	}
</script>
</body>
</html>