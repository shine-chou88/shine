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
	<div id="adjust_certificateInfo_selector_tb" style="padding:8px 10px;">
		身份证号码: <input type="text" size="15" class="easyui-textbox"
					maxlength="10" id="sfzhm_selector_certificate_adjust" />&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 70px;" onclick="queryCertificateSelectorAdjust();">查询</a>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="selectCertificateRefListSureAdjust()" style="width: 70px;">选定</a>&nbsp;
	</div>
</div>
	<div region="center" border="false">
	<table id="adjust_certificateInfo_select_table_dg" class="easyui-datagrid"
		data-options="singleSelect:true,collapsible:true,url:'${base}/certificateInfo/jsonPagination?businessType=public&status=checkIn',
			method:'post',fit:true,pagination:true,selectOnCheck: true,checkOnSelect:true,remoteSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'certificateTypeShow',align:'center',resizable:false,sortable:true" width="200px">证照类型</th>
				<th data-options="field:'zjhm',align:'center',resizable:false,sortable:true" width="180px">证照号码</th>
				<th data-options="field:'sfzhm',align:'center',resizable:false,sortable:true" width="180px">身份证号码</th>
				<th data-options="field:'name',align:'center',resizable:false,sortable:true" width="90px">姓名</th>
			</tr>
		</thead>
	</table>
	</div>
	
</div>	
<script type="text/javascript">
function queryCertificateSelectorAdjust(){
	$('#adjust_certificateInfo_select_table_dg').datagrid('load',{ 
		sfzhm:$('#sfzhm_selector_certificate_adjust').val()
	} );  
}
function selectCertificateRefListSureAdjust(){
	var row = $('#adjust_certificateInfo_select_table_dg').datagrid('getSelected');
	var selections = $('#adjust_certificateInfo_select_table_dg').datagrid('getSelections');
	if(row!=null&&selections.length>=1){
		var certificateItemDiv = $("#certificate_adjust_info");
		for(var i=0;i<selections.length;i++){
			if(!isAdjustExist(selections[i].id)){
				adjustNames.push(selections[i].id);
				var divCertifcate="<div style='float:left;margin-left:8px;margin-top:8px;' >";
				divCertifcate+="<input type='hidden' name='certificateIds' value='"+selections[i].id+"'/>"+selections[i].zjhm+"-"+selections[i].name;
				divCertifcate+="<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick=\"cancelCertificateAdjust(this)\" id='"+selections[i].id+"' title='删除'/>";
				divCertifcate+="</div>";
				certificateItemDiv.append(divCertifcate);
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
