<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>证照信息选择器</title>
</head>
<body>

<div class="easyui-layout" fit="true">
    	
<div region="north" border="false" style="margin-bottom: 5px;">
	<div id="certificateInfo_selector_tb" style="padding:8px 10px;">
		姓名: <input style="width:70px;" type="text" class="easyui-textbox" id="sfzhm_selector_name" />&nbsp;
		身份证号码: <input type="text" size="30" class="easyui-textbox" id="sfzhm_selector_certificate" />&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 70px;" onclick="queryCertificateSelector();">查询</a>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="selectCertificateRefListSure()" style="width: 70px;">选定</a>&nbsp;
	</div>
</div>
	<div region="center" border="false">
	<table id="certificateInfo_select_table_dg" class="easyui-datagrid"
		data-options="singleSelect:false,collapsible:true,url:'${base}/certificateInfo/refJsonPagination?businessType=public&status=checkIn',
			method:'post',fit:true,pagination:true,selectOnCheck: true,checkOnSelect:true,remoteSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'belongDepartName',align:'center',resizable:true,sortable:true" width="190px">部门</th>
				<th data-options="field:'certificateTypeShow',align:'center',resizable:true,sortable:true" width="200px">证照类型</th>
				<th data-options="field:'zjhm',align:'center',resizable:false,sortable:true" width="120px">证照号码</th>
				<th data-options="field:'name',align:'center',resizable:false,sortable:true" width="70px">姓名</th>
				<th data-options="field:'effectiveDate',align:'center',resizable:false,sortable:true" width="90px" formatter="ChangeDateFormat2">有效期限</th>
				<th data-options="field:'sfzhm',align:'center',resizable:false,sortable:true" width="180px">身份证号码</th>
			</tr>
		</thead>
	</table>
	</div>
	
</div>	
<script type="text/javascript">
function queryCertificateSelector(){
	var querySfzhm=$('#sfzhm_selector_certificate').val();
	$('#certificateInfo_select_table_dg').datagrid('load',{ 
		sfzhm:querySfzhm,
		name:$('#sfzhm_selector_name').val()
	} ); 
	if(querySfzhm!=''){
		$.ajax({ 
			type: 'POST', 
			url: base+'/certificateInfo/publicRefTip/'+querySfzhm,
			dataType: 'json',  
			success: function(data){
				if(data.success){
					if(data.info!=null&&data.info!=''){
						$.messager.alert('系统提示', data.info, 'info');
					}
				}
			} 
		}); 
	}
}
function selectCertificateRefListSure(){
	var row = $('#certificateInfo_select_table_dg').datagrid('getSelected');
	var selections = $('#certificateInfo_select_table_dg').datagrid('getSelections');
	if(row!=null&&selections.length>=1){
		var certificateItemDiv = $("#certificate_div_info");
		for(var i=0;i<selections.length;i++){
			if(!isCertificateExist(selections[i].id)){
				certifcateNames.push(selections[i].id);
				var divCertifcate="<div style='float:left;margin-left:8px;margin-top:8px;' >";
				divCertifcate+="<input type='hidden' name='certificateIds' value='"+selections[i].id+"'/>"+selections[i].zjhm+"-"+selections[i].name;
				divCertifcate+="<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick=\"cancelCertificateInfoSelect(this)\" id='"+selections[i].id+"' title='删除'/>";
				divCertifcate+="</div>";
				certificateItemDiv.append(divCertifcate);
				<%--if ($("#backScanCertificateInfos").html() != undefined) {
					if (backScanSavehtml == '') {
						backScanSavehtml = '<div style="float: bottom; width:450px">';
						backScanSavehtml += '<input type="button" value="确认新增" class="button_blue"  onclick="addBackScanCertificateInfos(this)"></input></div>';
						$('#certificate_div_info').parent().append(backScanSavehtml);
					}
				}--%>
			}
		}
		closeFreeWindow('third_window');
	}else{
		 $.messager.alert('系统提示','请选择一条数据！','info');
	}
}


</script>
</body>
</html>
