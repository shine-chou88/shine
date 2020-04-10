<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<title>${title}</title>
</head>

<body>
<style type="text/css">
.myicon-tick-checked {
display: inline-block;
position: relative;
width: 15px;
height: 15px;
border-radius: 50%;
background-color: #2ac845;
}
.myicon-tick-checked:before, .myicon-tick-checked:after {
content: '';
pointer-events: none;
position: absolute;
color: white;
border: 1px solid;
background-color: white;
}


.myicon-tick-checked:before {
width: 1px;
height: 1px;
left: 25%;
top: 50%;
transform: skew(0deg,50deg);
}


.myicon-tick-checked:after {
width: 3px;
height: 1px;
left: 45%;
top: 42%;
transform: skew(0deg,-50deg);
}

</style>
	<div class="easyui-layout" fit="true" style="padding:2px">
		<table cellpadding="7" cellspacing="0" class="a_table" width="100%">
			<tr>
				<th class="br"width="30%">请扫描:</th>
				<td class="br"><input id="backelementIdScan" type="text" style="width:300px;"/><span id="backshowScanContinueTip" style="color: red;">&nbsp;&nbsp;请继续扫描</span>
				</td>
				<input type="hidden" id="backshouldScanUseNum" value=""/>
				<input type="hidden"  id="backpublicBusinessId" value=""/>
			</tr>
		</table>

		<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
					<tr>
						<th width="30%" class="br">组团单位：
						</th>
						<td width="70%" class="br">
							<span id="backScanGroupDepartName"></span>
							</td>
					</tr>
					<tr>
				            <th class="br">证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div id="certificate_div_info" style="float: left; width:450px; height:100px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" >
			          			<div id="backScanCertificateInfos"></div> 
			          			</div>  
			          		</td>     	        		
				         </tr>
					<tr>
						<th width="30%" class="br">实际出境日期：</th>
						<td width="70%" class="br">
						<span id="backScanRealExitTime"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">实际入境日期：</th>
						<td width="70%" class="br">
						<span id="backScanRealEnterTime"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">实际出访国家地区(含经停城市)：</th>
						<td width="70%" class="br"><span id="backScanRealVisitCountry"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">有无超天数超国家等违规情况：</th>
						<td width="70%" class="br"><span id="backScanHasViolationSituation"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">领用人姓名：</th>
						<td width="70%" class="br"><span id="backScanApplyUserName"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人姓名：</th>
						<td width="70%" class="br"><span id="backScanBackUserName"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人单位：</th>
						<td width="70%" class="br"><span id="backScanBackUserDepartName"></span>
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">归还人电话：</th>
						<td width="70%" class="br"><span id="backScanBackUserPhone"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">计划归还日期：</th>
						<td width="70%" class="br"><span id="backScanBackDate"></span></td>
					</tr>
				</table>
		<div id="backScanContent" style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="confirmBackCheckIn()">确认归还</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
	var scanBackNum=0;
	var backScanXzhtml = '';
	var backScanSavehtml = '';
	$(function(){
		$("#backelementIdScan").focus();
		$("#backScanContent").hide();
		 $('#backelementIdScan').bind('keyup', function(event) {
		        if (event.keyCode == "13") {
		            //回车执行查询
		            backScanner();
		        }
		  });
	})
	function backScanner(){
		var value=$("#backelementIdScan").val().trim();
		if($("#backpublicBusinessId").val()==''){
				$.ajax({ 
					type: 'POST', 
					url: base+'/publicBusiness/backRegisterScan/'+value,
					dataType: 'json',  
					success: function(data){
						var msg=data.msg;
						var data=data.business;
						if(msg!=null&&msg!=''){
							$.messager.show({
								title:'提示信息',
								msg:msg,
								timeout:3000,
								showType:'slide'
							});
						}
						if(data!=null){
							$("#backScanGroupDepartName").html(data.groupDepartName);
							$("#backScanCertificateInfos").html(data.certificateNames);
							$("#backScanRealExitTime").html(ChangeDateFormat2(data.realExitTime));
							$("#backScanRealEnterTime").html(ChangeDateFormat2(data.realEnterTime));
							$("#backScanRealVisitCountry").html(data.realVisitCountry);
							$("#backScanHasViolationSituation").html(data.hasViolationSituation);
							$("#backScanApplyUserName").html(data.applyUserName);
							$("#backScanBackUserName").html(data.backUserName);
							$("#backScanBackUserDepartName").html(data.backUserDepartName);
							$("#backScanBackUserPhone").html(data.backUserPhone);
							$("#backScanBackDate").html(ChangeDateFormat2(data.backDate));
							$("#backshouldScanUseNum").val(data.shouldScanNum);
							$("#backpublicBusinessId").val(data.id);
							if($("#"+value+"_scanSpan")!=null&&$("#"+value+"_scanSpan")!=undefined){
								$("#"+value+"_scanSpan").addClass("myicon-tick-checked");
							}
							scanBackNum=scanBackNum+1;
							if($("#backshouldScanUseNum").val()!=''&&$("#backshouldScanUseNum").val()==scanBackNum){
								$("#backelementIdScan").blur();
								$("#backelementIdScan").attr("readonly","readonly");
								$("#backshowScanContinueTip").html("&nbsp;&nbsp;已全部扫描");
								$("#backScanContent").show();
								//backScanXzhtml();
							}
						}
					} 
				}); 
			}else{
				$.ajax({ 
					type: 'POST', 
					url: base+'/publicBusiness/backRegisterScan/'+value,
					dataType: 'json',  
					success: function(data){
						var msg=data.msg;
						if(msg!=null&&msg!=''){
							$.messager.show({
								title:'提示信息',
								msg:msg,
								timeout:3000,
								showType:'slide'
							});
						}
					} 
				}); 
				if($("#"+value+"_scanSpan")!=null&&$("#"+value+"_scanSpan")!=undefined){
					$("#"+value+"_scanSpan").addClass("myicon-tick-checked");
				}
				scanBackNum=$(".myicon-tick-checked").length;
				if($("#backshouldScanUseNum").val()!=''&&$("#backshouldScanUseNum").val()==scanBackNum){
					$("#backelementIdScan").blur();
					$("#backelementIdScan").attr("readonly","readonly");
					$("#backshowScanContinueTip").html("&nbsp;&nbsp;已全部扫描");
					$("#backScanContent").show();
					//backScanXzhtml();
				}
			}
			$("#backelementIdScan").val("");
			$("#backelementIdScan").focus();
				
	}
	
	function backScanXzhtml(){
		if (backScanXzhtml == '') {
			backScanXzhtml = '<div style="float: left; text-align: center; height:65px; margin: 5px;">';
			backScanXzhtml += '<input type="button" value="选" class="button_blue" style="margin-top: 75px" onclick="selectCertificateInfoMuti()"></input></div>';
			$('#certificate_div_info').parent().append(backScanXzhtml);
		}
	}
	function confirmBackCheckIn(){
		var businessId=$("#backpublicBusinessId").val();
		if(businessId==''){
			$.messager.alert('系统提示', '数据有误，请联系管理员', 'info');
			return false;
		}
		$.messager.confirm('系统提示','确认归还吗?',function(id){ 
			if(id){ 
				$.ajax({ 
					type: 'POST', 
					url: base+'/publicBusiness/saveBackRegisterScan/'+businessId,
					dataType: 'json',  
					success: function(data){
						if(data.success){
							$.messager.alert('系统提示', data.info, 'info');
							closeWindow();
							$("#scanBackpublicBusinessListDg").datagrid('reload'); 
						}else{ 
							$.messager.alert('系统提示', data.info, 'info');
						} 
					} 
				}); 
			} 
		}); 	
	}
	
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
	function addBackScanCertificateInfos(obj){
		var businessId = $("#backpublicBusinessId").val();
		if(businessId==''){
			$.messager.alert('系统提示', '数据有误，请重试或联系管理员', 'info');
			return false;
		}
		var certificateIdsInput= $('#certificate_div_info input[name="certificateIds"]');
		var certificateIds = certificateIdsInput.val();
		if (certificateIds == undefined) {
			$.messager.alert('系统提示', '请先选择证照！', 'info');
			return;
		}
		$.messager.confirm('系统提示','确认新增待归还证照?',function(id){ 
			if(id){ 
				$.ajax({ 
					type: 'POST', 
					url: base+'/publicBusiness/addBackRegisterScan/'+businessId+'?certificateIds='+certificateIds,
					dataType: 'json',  
					success: function(data){
						if(data.success){
							certifcateNames = new Array();
							for(k = 0;k < certificateIdsInput.length;k++){
								$("#backScanCertificateInfos").append('<span id="'+$(certificateIdsInput[k]).val()+'_scanSpan" class="myicon-tick-checked"></span>'+$(certificateIdsInput[0]).parent().text());
								$(certificateIdsInput[k]).parent().remove();
							}
							$(obj).parent().remove();
							backScanSavehtml = '';
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