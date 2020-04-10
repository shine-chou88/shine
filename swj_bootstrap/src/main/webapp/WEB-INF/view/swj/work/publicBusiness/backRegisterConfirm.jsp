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
				 method="post"
				 class="easyui-form">
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
							<input type="text" id="groupDepartId" name="groupDepart.id" data-options="url:'${base}/depart/certificateDepartJson?selected=${bean.groupDepart.code}',valueField:'id',textField:'text'" class="easyui-combobox"  style="width:250px" />
							</td>
					</tr>
					<tr>
				            <th class="br">证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div id="certificate_div_info" style="float: left; width:450px; height:100px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" >
			          			        <c:forEach items="${bean.certificateInfos}" var="certificate" varStatus="vs">
					          				<div style='float:left;margin-left:8px;margin-top:8px;' >
					          					<input type='hidden' name='certificateIds'  value='${certificate.id}'></input>
					          					${certificate.zjhm}-${certificate.name}
					          				</div>
					          			</c:forEach>
			          			</div>          			
			          		</td>     	        		
				         </tr>
				         <tr>
						<th width="30%" class="br">领用人姓名：</th>
						<td width="70%" class="br">${ bean.applyUserName}</td>
					</tr>
					<tr>
						<th width="30%" class="br">实际出境日期：</th>
						<td width="70%" class="br"><fmt:formatDate value="${ bean.realExitTime}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<th width="30%" class="br">实际入境日期：</th>
						<td width="70%" class="br">
							<fmt:formatDate value="${ bean.realEnterTime}" pattern="yyyy-MM-dd" />
							<input id="useRegisterConfirm_realExitTimeSelect" value="<fmt:formatDate value="${bean.realEnterTime}" pattern="yyyy-MM-dd" />"  type="hidden" ></input>
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">实际出访国家地区(含经停城市)：</th>
						<td width="70%" class="br">${ bean.realVisitCountry}</td>
					</tr>
					<tr>
						<th width="30%" class="br">有无超天数超国家等违规情况：</th>
						<td width="70%" class="br">${ bean.hasViolationSituation}</td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人姓名：</th>
						<td width="70%" class="br">${ bean.backUserName}</td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人单位：</th>
						<td width="70%" class="br">${ bean.backUserDepartName}
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人电话：</th>
						<td width="70%" class="br">${ bean.backUserPhone}</td>
					</tr>
					<tr>
						<th width="30%" class="br">计划归还日期：</th>
						<td width="70%" class="br"><input type="text" id="useRegisterConfirm_backDate" 
								value="<fmt:formatDate value="${bean.backDate}" pattern="yyyy-MM-dd" />"  class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'useRegisterConfirm_realExitTimeSelect\')}'})" readonly="readonly" style="width: 88px;"></input>
						</td>
					</tr>
					<tr>
						<td class="br" colspan="2">
							<div region="south" border="false"
								style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-save" onclick="saveConfirmBackForm()">确认</a>
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
function saveConfirmBackForm(){
	$.messager.confirm('系统提示','要归还确认吗?',function(id){ 
		if(id){ 
			$.ajax({ 
				type: 'POST', 
				url: base+'/publicBusiness/saveConfirmBackRegister/${bean.id}?backDate='+$('#useRegisterConfirm_backDate').val(),
				dataType: 'json',  
				success: function(data){
					if(data.success){
						$.messager.alert('系统提示', data.info, 'info');
						closeWindow();
						if("${todoType}"=='unRead'){
							$('#tt').tabs('select', '待办任务列表');
						}else{
							$('#tt').tabs('select', '证照归还信息表');
						}
						var tab = $('#tt').tabs('getSelected');
						tab.panel('refresh');
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
						$.messager.alert('系统提示', data.info, 'info');
					} 
				} 
			}); 
		} 
	}); 
}

</script>
</body>
</html>