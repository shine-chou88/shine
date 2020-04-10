<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>

    <div class="easyui-layout" fit="true">
		<div data-options="region:'west',split:false"  style="width:280px;">
			<ul id="functionTree" class="easyui-tree" data-options="url:'${base}/function/tree',animate:true,lines:true"></ul>
		</div>
		<div data-options="region:'center'">
			<div id="function_tb" style="padding:2px 5px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addFunction();">新增</a>&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editFunction();">修改</a>&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deleteFunction();">删除</a>&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="refreshTree();">刷新目录</a>
			</div>
			
			<table id="function_dg" class="easyui-datagrid"
					data-options="singleSelect:true,collapsible:true,url:'${base}/function/jsonPagination',
					method:'post',fit:true,pagination:false,toolbar:'#function_tb',singleSelect: true,
					selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:50,pageList:[50,100,150],rownumbers:true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'name',align:'center',resizable:false" width="32%">名称</th>
						<th data-options="field:'priority',align:'center',resizable:false" width="10%">排列顺序</th>
						<th data-options="field:'url',align:'center',resizable:false" width="54%">URL</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		$('#functionTree').tree({
			onClick: function(node){
				$('#function_dg').datagrid('load',{ 
					pid:node.id
				}); 
			}
		});
		function refreshTree(){
			$('#functionTree').tree('reload');
		}
		function addFunction(){
			var node=$('#functionTree').tree('getSelected');
			var win=creatWin('新增-功能菜单',750,550,'icon-add','/function/add');
			if(null!=node){
				win=creatWin('新增-功能菜单',750,550,'icon-add','/function/add?pid='+node.id);
			}
			win.window('open');
		}
		function editFunction(){
			var row = $('#function_dg').datagrid('getSelected');
			var selections = $('#function_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
			    var win=creatWin('修改-功能菜单',750,550,'icon-edit','/function/edit/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条数据！','info');
			}
		}
		function deleteFunction(){
			var row = $('#function_dg').datagrid('getSelected');
			var selections = $('#function_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
				$.messager.confirm('系统提示','确认删除吗?',function(id){ 
					if(id){ 
						$.ajax({ 
							type: 'POST', 
							url: '${base}/function/delete?id='+row.id,
							dataType: 'json',  
							success: function(data){ 
								if(data.success){
									$.messager.alert('系统提示',data.info,'info');
									$("#function_dg").datagrid('reload');
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
</body>
</html>

