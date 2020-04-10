<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>${title}</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
	<table cellpadding="5" cellspacing="0"  width="100%">
	<tr>
		<th colspan="8" class="br"
			style="font-size: 20px; text-align: center; height: 25px; line-height: 25px;">证照领用信息表</th>
	</tr>
</table>
				<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
					<tr>
						<th width="30%" class="br">组团单位：
						</th>
						<td width="70%" class="br">
							${bean.groupDepartName}&nbsp;&nbsp;
							</td>
					</tr>
					<tr>
				            <th class="br">证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div id="certificate_div_info" style="float: left; width:450px; height:100px; overflow-x:hidden; border: 0px solid #D4D4D4; margin: 5px" >
			          			        <c:forEach items="${bean.certificateInfos}" var="certificate" varStatus="vs">
					          				<div style='float:left;margin-left:8px;margin-top:8px;' >
					          					${certificate.zjhm}-${certificate.name}
					          				</div>
					          			</c:forEach>
			          			</div>
			          			<div style="float: left; text-align: center; height:55px; margin: 5px;">
 								   <input type="button" value="选" class="button_blue" style="margin-top: 75px" onclick="selectCertificateInfoMuti()"></input>
 								</div>          			
			          		</td>     	        		
				         </tr>
					<tr>
						<th width="30%" class="br">批件号：</th>
						<td width="70%" class="br">${ bean.approvalNo}<c:if test="${empty bean.approvalNo}">无(其他理由借用) </c:if>&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">计划出境日期：</th>
						<td width="70%" class="br">
						<fmt:formatDate
				value="${ bean.planExitTime}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">计划入境日期：</th>
						<td width="70%" class="br">
						<fmt:formatDate
				value="${ bean.planEnterTime}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">领用人姓名：</th>
						<td width="70%" class="br">${ bean.applyUserName}&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">领用人单位：</th>
						<td width="70%" class="br">${ bean.applyUserDepartName}&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">领用人电话：</th>
						<td width="70%" class="br">${ bean.applyUserPhone}&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<th width="30%" class="br">计划领用日期：</th>
						<td width="70%" class="br">
						<fmt:formatDate
				value="${ bean.applyDate}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</td>
					</tr>
					<c:if test="${bean.useStatus=='领用完成' }">
					<th width="30%" class="br">实际领用日期：</th>
						<td width="70%" class="br">
						<fmt:formatDate
				value="${ bean.realApplyDate}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</td>
					</c:if>
					<tr>
						<td class="br" colspan="2">
							<div region="south" border="false"
								style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
								 <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="addUseScanCertificateInfos('${bean.id}')">保存</a> 
								 <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
							</div>
						</td>
					</tr>
				</table>
</div>
</div>
<script type="text/javascript">
	var certifcateNames = new Array();
	function selectCertificateInfoMuti(){
		var win=creatFreeWindow("third_window",'添加-证照信息',830,450,'icon-add',"/certificateInfo/refList");
	    win.window('open');
	}
	function isCertificateExist(id) {
		for (i = 0; i < certifcateNames.length; i++) {
			if (certifcateNames[i] == id) {
				return true;
			}
		}
		if($("#"+id+"_scanSpan")!=null&&$("#"+id+"_scanSpan").html()!=undefined){
			return true;
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
	function addUseScanCertificateInfos(pid){
		var certificateIdsInput= $('#certificate_div_info input[name="certificateIds"]');
		var certificateIds = certificateIdsInput.val();
		if (certificateIds == undefined) {
			$.messager.alert('系统提示', '请先选择证照！', 'info');
			return;
		}
		$.messager.confirm('系统提示','确认新增证照?',function(id){ 
			if(id){ 
				$.ajax({ 
					type: 'POST', 
					url: base+'/publicBusiness/addBackRegisterScan/'+pid+'?certificateIds='+certificateIds,
					dataType: 'json',  
					success: function(data){
						if(data.success){
							$.messager.alert('系统提示', data.info, 'info');
							closeWindow();
							$("#scanUsepublicBusinessListDg").datagrid('reload'); 
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