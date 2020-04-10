<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
	<script type="text/javascript">
		function deploy(){
			var row = $('#process_dg').datagrid('getSelected');
			var selections = $('#process_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
				$.messager.confirm('系统提示','确认重新部署该流程吗?',function(r){
					if(r){
						$.ajax({ 
							type: 'POST', 
							url: '${base}/activiti/process/deploy?id='+row.id,
							dataType: 'json',  
							success: function(data){ 
								if(data.success){
									$.messager.alert('系统提示',data.info,'info');
									$("#process_dg").datagrid('reload');
								}else{
									$.messager.alert('系统提示',data.info,'error');
								}
							} 
						});
					}
				});
			}else{
				 $.messager.alert('系统提示','请选择一条数据！','info');
			}
		}
		function deployAll(){
			$.messager.confirm('系统提示','确认部署所有流程吗?',function(r){
				if(r){
					$.ajax({ 
						type: 'POST', 
						url: '${base}/activiti/process/deployAll',
						dataType: 'json',  
						success: function(data){ 
							if(data.success){
								$.messager.alert('系统提示',data.info,'info');
								$("#process_dg").datagrid('reload');
							}else{
								$.messager.alert('系统提示',data.info,'error');
							}
						} 
					});
				}
			});
		}
		function viewProcessDiagram(){
			var row = $('#process_dg').datagrid('getSelected');
			var selections = $('#process_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
				var win=creatWin('查看-流程信息',1120,650,'icon-search','/activiti/process/view/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条数据！','info');
			}
		}
	</script>
		<div id="process_tb" style="padding:2px 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="deploy();" style="width: 120px;">重新部署流程</a>&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="deployAll();" style="width: 130px;">部署所有流程</a>&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="viewProcessDiagram();" style="width: 100px;">查看流程图</a>&nbsp;
			
		</div>
	
		<table id="process_dg" style="width:400px;height:250px" class="easyui-datagrid" data-options="singleSelect:true,collapsible:true,url:'${base}/activiti/process/jsonPagination',
				method:'post',fit:true,toolbar:'#process_tb',singleSelect: true,selectOnCheck: true,checkOnSelect: true,remoteSort:true,rownumbers:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',align:'left',resizable:false,sortable:true" width="20%">ID</th>
					<th data-options="field:'key',align:'center',resizable:false,sortable:true" width="20%">Key</th>
					<th data-options="field:'name',align:'center',resizable:false,sortable:true" width="30%">流程名称</th>
					<th data-options="field:'version',align:'center',resizable:false,sortable:true" width="10%">版本号</th>
					<th data-options="field:'deploymentId',align:'center',resizable:false" width="16%">部署ID</th>
				</tr>
			</thead>
		</table>
</body>
</html>

