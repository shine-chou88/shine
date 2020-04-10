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
			<form id="backpublicBusinessEditForm"
				action="${base}/publicBusiness/saveBackRegister" method="post"
				data-options="novalidate:true" class="easyui-form">
				<input type="hidden" name="id" value="${bean.id}" />
				<input type="hidden" name="hasSubmit" id="backRegisterSubmit" />
				<input type="hidden" name="useStatus" value="${bean.useStatus}" />
				<input type="hidden" name="approvalNo" value="${bean.approvalNo}" />
				<input type="hidden" name="planExitTime" value="${bean.planExitTime}" />
				<input type="hidden" name="planEnterTime" value="${bean.planEnterTime}" />
				<input type="hidden" name="applyUserDepartName" value="${bean.applyUserDepartName}" />
				<input type="hidden" name="applyUserPhone" value="${bean.applyUserPhone}" />
				<input type="hidden" name="applyDate" value="${bean.applyDate}" />
				<input type="hidden" name="realApplyDate" value="${bean.realApplyDate }"/>
				<input type="hidden" name="applySubmitTime" value="${bean.applySubmitTime }"/>
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
							<input type="text" id="groupDepartId" name="groupDepart.id" data-options="url:'${base}/depart/certificateDepartJson?selected=${bean.groupDepart.code}',valueField:'id',textField:'text'" class="easyui-combobox"  style="width:250px" readonly="readonly"/>
							</td>
					</tr>
					<tr>
				            <th class="br"><strong style="color: red;">*</strong>证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div id="certificate_div_info" style="float: left; width:450px; height:100px; overflow-x:hidden;  margin: 5px" >
			          			        <c:forEach items="${bean.certificateInfos}" var="certificate" varStatus="vs">
					          				<div style='float:left;margin-left:8px;margin-top:8px;' >
					          					<input type='hidden' name='certificateIds'  value='${certificate.id}'></input>
					          					${certificate.zjhm}-${certificate.name}
					          				</div>
					          			</c:forEach>
			          			</div>          			
<!-- 			          			<div style="float: left; text-align: center; height:55px; margin: 5px;"> -->
<!-- 								   <input type="button" value="选" class="button_blue" style="margin-top: 75px" onclick="selectCertificateInfoMuti()"></input> -->
<!-- 								</div> -->
			          		</td>     	        		
				         </tr>
				    <tr>
						<th width="30%" class="br">领用人姓名：</th>
						<td width="70%" class="br"><input type="hidden"
							name="applyUserName" 
							value="${ bean.applyUserName}" ></input>${ bean.applyUserName}</td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>实际出境日期：</th>
						<td width="70%" class="br"><input type="text"
							name="realExitTime" id="realExitTimeSelect"
							value="<fmt:formatDate value="${ bean.realExitTime}" pattern="yyyy-MM-dd" />" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'realEnterTimeSelect\')}'})" readonly="readonly" style="width: 88px;"></input></td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>实际入境日期：</th>
						<td width="70%" class="br"><input type="text"
							name="realEnterTime" id="realEnterTimeSelect"
							value="<fmt:formatDate value="${ bean.realEnterTime}" pattern="yyyy-MM-dd" />" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'realExitTimeSelect\')}'})" readonly="readonly" style="width: 88px;"></input></td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>实际出访国家地区(含经停城市)：</th>
						<td width="70%" class="br">
						<textarea id="realVisitCountryTextarea" name="realVisitCountry"  rows="3" cols="40" >${bean.realVisitCountry }</textarea>	
							
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">有无超天数超国家等违规情况：</th>
						<td width="70%" class="br">
						<textarea id="hasViolationSituationTextarea" name="hasViolationSituation" rows="3"cols="40">${bean.hasViolationSituation }</textarea>	
							
						</td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>归还人姓名：</th>
						<td width="70%" class="br"><input type="text"
							name="backUserName" class="easyui-textbox"
							value="${ bean.backUserName}" style="width: 200px" data-options="required:true"></input></td>
					</tr>
					<tr>
						<th width="30%" class="br"><strong style="color: red;">*</strong>归还人单位：</th>
						<td width="70%" class="br"><input type="text"
							name="backUserDepartName" class="easyui-textbox"
							value="${ bean.backUserDepartName}" style="width: 200px" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人电话：</th>
						<td width="70%" class="br"><input type="text"
							name="backUserPhone" class="easyui-textbox"
							value="${ bean.backUserPhone}" style="width: 200px"></input></td>
					</tr>
					<tr>
						<th width="30%" class="br">计划归还日期：</th>
						<td width="70%" class="br"><input type="text"
							name="backDate" 
							value="<fmt:formatDate value="${bean.backDate}" pattern="yyyy-MM-dd" />"class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'realEnterTimeSelect\')}'})" readonly="readonly" style="width: 88px;"></input></td>
					</tr>
					<tr>
						<td class="br" colspan="2">
							<div region="south" border="false"
								style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-save" onclick="saveBackPublicBusinessForm('no')">保存</a>
									<a href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-ok" onclick="saveBackPublicBusinessForm('yes')">提交</a>
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
function saveBackPublicBusinessForm(type){
	if('yes'==type){
		$("#backRegisterSubmit").val('true');
	}else{
		$("#backRegisterSubmit").val('false');
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
	if($("#realExitTimeSelect").val()==null||$("#realExitTimeSelect").val()==''){
		$.messager.alert('系统提示', "实际出境日期不能为空", 'info');
		return false;
	}
	if($("#realEnterTimeSelect").val()==null||$("#realEnterTimeSelect").val()==''){
		$.messager.alert('系统提示', "实际入境日期不能为空", 'info');
		return false;
	}
	if($("#realVisitCountryTextarea").val()==null||$("#realVisitCountryTextarea").val()==''){
		$.messager.alert('系统提示', "实际出访国家地区(含经停城市)不能为空", 'info');
		return false;
	}
	var flag=false;
	$('#backpublicBusinessEditForm').form('submit', {
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
				if('${closeBack}'=='true'){
					$('#backpublicBusinessEditForm').form('clear');
					$('#backpublicBusinessListDg').datagrid('reload');
				}else{
					$('#publicBusinessEditForm').form('clear');
					$('#publicBusinessListDg').datagrid('reload');
				}
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
</script>
</body>
</html>