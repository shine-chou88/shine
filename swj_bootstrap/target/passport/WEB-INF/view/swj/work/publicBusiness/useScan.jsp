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
	<div class="easyui-layout" fit="true">
		<table cellpadding="7" cellspacing="0" class="a_table" width="100%">
	<tr>
		<th class="br"width="30%">请扫描:</th>
		<td class="br"><input id="elementIdScan" type="text" style="width:300px;" /><span id="showScanContinueTip" style="color: red;">&nbsp;&nbsp;请继续扫描</span>
		</td>
		<input type="hidden" id="shouldScanUseNum" value=""/>
		<input type="hidden"  id="publicBusinessId" value=""/>
	</tr>
</table>

		<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
					<tr>
						<th width="30%" class="br">组团单位：
						</th>
						<td width="70%" class="br" >
							<span id="useScanGroupDepartName"></span>
							</td>
					</tr>
					<tr>
				            <th class="br">证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div style="float: left; width:450px; height:100px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" >
			          			       <div id="useScanCertificateInfos"></div> 
			          			</div>          			
			          		</td>     	        		
				         </tr>
					<tr>
						<th width="30%" class="br">批件号：</th>
						<td width="70%" class="br"><span id="useScanApprovalNo"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">计划出境日期：</th>
						<td width="70%" class="br"><span id="useScanPlanExitTime"></span>
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">计划入境日期：</th>
						<td width="70%" class="br"><span id="useScanPlanEnterTime"></span>
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">领用人姓名：</th>
						<td width="70%" class="br"><span id="useScanApplyUserName"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">领用人单位：</th>
						<td width="70%" class="br"><span id="useScanApplyUserDepartName"></span>
						</td>
					</tr>
					<tr>
						<th width="30%" class="br">领用人电话：</th>
						<td width="70%" class="br"><span id="useScanApplyUserPhone"></span></td>
					</tr>
					<tr>
						<th width="30%" class="br">计划领用日期：</th>
						<td width="70%" class="br"><span id="useScanApplyDate"></span>
						</td>
					</tr>
				</table>
				
				<div id="useScanContent">
		<table cellpadding="7" cellspacing="0" class="a_table" width="100%">
	<tr>
		<td class="br" colspan="2">
							<div 
								style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-ok" onclick="confirmUsing()">确认领用</a> 
									<a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
							</div>
		</td>
	</tr>
</table>
</div>
	</div>
	<script type="text/javascript">
	$(function(){
		$("#elementIdScan").focus();
		$("#useScanContent").hide();
		$('#elementIdScan').bind('keyup', function(event) {
	        if (event.keyCode == "13") {
	            //回车执行查询
	            useScanner();
	        }
	  });
	})
	var showGreenNum=0;
	function useScanner(){
		var value=$("#elementIdScan").val().trim();
			if($("#publicBusinessId").val()==''){
				$.ajax({ 
					type: 'POST', 
					url: base+'/publicBusiness/useRegisterScan/'+value,
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
							$("#useScanGroupDepartName").html(data.groupDepartName);
							$("#useScanCertificateInfos").html(data.certificateNames);
							$("#useScanApprovalNo").html(((data.approvalNo==null||data.approvalNo=='')?'无(其他理由借用) ':data.approvalNo));
							$("#useScanPlanExitTime").html(ChangeDateFormat2(data.planExitTime));
							$("#useScanPlanEnterTime").html(ChangeDateFormat2(data.planEnterTime));
							$("#useScanApplyUserDepartName").html(data.applyUserDepartName);
							$("#useScanApplyUserName").html(data.applyUserName);
							$("#useScanApplyUserPhone").html(data.applyUserPhone);
							$("#useScanApplyDate").html(ChangeDateFormat2(data.applyDate));
							$("#shouldScanUseNum").val(data.shouldScanNum);
							$("#publicBusinessId").val(data.id);
							if($("#"+value+"_scanSpan")!=null&&$("#"+value+"_scanSpan")!=undefined){
								$("#"+value+"_scanSpan").addClass("myicon-tick-checked");
							}
							showGreenNum=showGreenNum+1;
							if(data.shouldScanNum!=''&&data.shouldScanNum==showGreenNum){
								$("#elementIdScan").blur();
								$("#elementIdScan").attr("readonly","readonly");
								$("#showScanContinueTip").html("&nbsp;&nbsp;已全部扫描");
								$("#useScanContent").show();
							}
						}
					}
				}); 
			}else{
				$.ajax({ 
					type: 'POST', 
					url: base+'/publicBusiness/useRegisterScan/'+value,
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
				showGreenNum=$(".myicon-tick-checked").length;
				if($("#shouldScanUseNum").val()!=''&&$("#shouldScanUseNum").val()==showGreenNum){
					$("#elementIdScan").blur();
					$("#elementIdScan").attr("readonly","readonly");
					$("#showScanContinueTip").html("&nbsp;&nbsp;已全部扫描");
					$("#useScanContent").show();
				}
			}
			$("#elementIdScan").val("");
			$("#elementIdScan").focus();
		
	}
	function confirmUsing(){
		var businessId=$("#publicBusinessId").val();
		if(businessId==''){
			$.messager.alert('系统提示', '数据有误，请联系管理员', 'info');
			return false;
		}
		$.messager.confirm('系统提示','确认领用吗?',function(id){ 
			if(id){ 
				$.ajax({ 
					type: 'POST', 
					url: base+'/publicBusiness/saveUseRegisterScan/'+businessId,
					dataType: 'json',  
					success: function(data){
						if(data.success){
							$.messager.alert('系统提示', data.info, 'info');
							closeWindow();
							$("#scanUsepublicBusinessListDg").datagrid('reload'); 
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