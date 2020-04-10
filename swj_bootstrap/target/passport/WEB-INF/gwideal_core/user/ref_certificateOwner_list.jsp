<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>${title}</title>
</head>
<body>
<div class="easyui-layout" fit="true">
		<div region="north" border="false" style="margin-bottom: 5px;">
			<form action=""  class="easyui-form" method="post">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%">
					<tr>
						<th width="70px" class="br">姓名：</th>
						<td width="150px" class="br">
							<input type="text" id="refCertificateOwnerName" class="easyui-textbox"/>
						</td>
						<td class="br">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 70px;" onclick="queryRefCertificateOwner()">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div  region="center" border="false">
			<table id="certificateOwnerRefList_dg" class="easyui-datagrid"
			data-options="singleSelect:true,collapsible:true,url:'${base}/user/jsonPagination',
			method:'post',fit:true,pagination:true,selectOnCheck: true,checkOnSelect: true,remoteSort:true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'idCard',hidden:true"></th>
						<th data-options="field:'name',align:'center',resizable:false,sortable:true" width="20%">姓名</th>
						<th data-options="field:'job_title',align:'center',resizable:false,sortable:true" width="20%">职务</th>
						<th data-options="field:'departName',align:'center',resizable:false,sortable:true" width="20%">部门</th>
						<th data-options="field:'mobile',align:'center',resizable:false,sortable:true" width="18%">手机</th>
					</tr>
				</thead>
			</table>
		</div>
		<div region="south" border="false" style="text-align: center; height: 32px; line-height: 30px;padding-left: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="certificateOwnerRefListSure('${businessType}');">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeFreeWindow('third_window');">关闭</a>
		</div>
</div>
<script type="text/javascript">
function certificateOwnerRefListSure(type){
	var row = $('#certificateOwnerRefList_dg').datagrid('getSelected');
	var selections = $('#certificateOwnerRefList_dg').datagrid('getSelections');
	if(row!=null&&selections.length==1){
		if(type=='private'){
			$("#certificateOwnerId").val(row.id);
			$("#certificateOwnerName").val(row.name);
			if(row.idCard!=null&&row.idCard!=''){
				$("#certificate_sfzhm").val(row.idCard);
				$("#certificate_sfzhm").blur();
				
			}else{
				$("#certificate_sfzhm").val("");
			}
		}else if(type=='public'){
			$("#certificateOwnerPublicId").val(row.id);
			$("#certificateOwnerPublicName").val(row.name);
			if(row.idCard!=null&&row.idCard!=''){
				$("#certificate_sfzhm_public").val(row.idCard);
				$("#certificate_sfzhm_public").blur();
				
			}else{
				$("#certificate_sfzhm_public").val("");
			}
		}
		closeFreeWindow('third_window');
	}else{
		 $.messager.alert('系统提示','请选择一条数据！','info');
	}
}

function queryRefCertificateOwner(){
	$('#certificateOwnerRefList_dg').datagrid('load',{ 
		name:$('#refCertificateOwnerName').val(),
	} ); 
}
</script>
</body>
</html>

