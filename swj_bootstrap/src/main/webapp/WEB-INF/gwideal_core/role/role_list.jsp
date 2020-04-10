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
		function editRole(){
			var row = $('#role_dg').datagrid('getSelected');
			var selections = $('#role_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
			    var win=creatWin('修改-角色信息',650,550,'icon-edit','/role/edit/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}
		function addRole(){
			var win=creatWin('新增-角色信息',650,330,'icon-add','/role/add');
			win.window('open');
		}
		function queryRole(){
			$('#role_dg').datagrid('load',{ 
				name:$('#roleName').val()
			}); 
		}
		function viewRole(){
			var row = $('#role_dg').datagrid('getSelected');
			var selections = $('#role_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
			    var win=creatWin('角色信息-查看',650,330,'icon-search','/role/view/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		function deleteRole(){
			var row = $('#role_dg').datagrid('getSelected');
			var selections = $('#role_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
				$.messager.confirm('系统提示','确认删除吗?',function(id){ 
					if(id){ 
						$.ajax({ 
							type: 'POST', 
							url: '${base}/role/delete/'+row.id,
							dataType: 'json',  
							success: function(data){ 
								if(data.success){
									$.messager.alert('系统提示',data.info,'info');
									$("#role_dg").datagrid('reload');
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
	</script>
	
	<div id="role_tb" style="padding:2px 5px;">
		角色名称: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="roleName"/>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryRole();">查询</a>&nbsp;
		<gwideal:perm url="/role/add">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addRole();">新增</a>&nbsp;
		</gwideal:perm>
		<gwideal:perm url="/role/edit/{id}">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editRole();">修改</a>&nbsp;
		</gwideal:perm>
		<gwideal:perm url="/role/delete/{id}">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deleteRole()">删除</a>
		</gwideal:perm>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewRole()">查看</a>
	</div>
	
	<table id="role_dg" class="easyui-datagrid"
			data-options="singleSelect:true,collapsible:true,url:'${base}/role/jsonPagination',
			method:'post',fit:true,pagination:true,toolbar:'#role_tb',singleSelect: true,
			selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:50,pageList:[50,100,150],rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'code',align:'left',resizable:false,sortable:true" width="16%">角色代码</th>
				<th data-options="field:'name',align:'center',resizable:false" width="22%">角色名称</th>
				<th data-options="field:'orderNo',align:'center',resizable:false" width="10%">排列顺序</th>
				<th data-options="field:'description',align:'center',resizable:false" width="48%">说明</th>
			</tr>
		</thead>
	</table>

</body>
</html>
