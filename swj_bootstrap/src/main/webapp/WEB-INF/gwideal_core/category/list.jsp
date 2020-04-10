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
		function editCategory(){
			var row = $('#category_dg').datagrid('getSelected');
			var selections = $('#category_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
			    var win=creatWin('修改-字典类型信息',650,330,'icon-edit','/category/edit/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}
		function addCategory(){
			var node=$('#CategoryTree').tree('getSelected');
			var win=creatWin('新增-字典类型信息',650,330,'icon-add','/category/add');
			if(null!=node){
				win=creatWin('新增-字典类型信息',650,330,'icon-add','/category/add?code='+node.code);
				win.window('open');
			}else{
				win.window('open');
			}
		}
		function queryCategory(){
			$('#category_dg').datagrid('load',{ 
				name:$('#categoryName').val()
			}); 
		}
	</script>

    <div class="easyui-layout" fit="true">
    <div data-options="region:'west',split:false"  style="width:200px;">
		<ul id="CategoryTree" class="easyui-tree" data-options="url:'${base}/category/tree',animate:true,lines:true"></ul>
	</div>
	<div data-options="region:'center'">
	<div id="category_tb" style="padding:2px 5px;">
		字典类型名称: <input type="text" style="width: 230px;" size="15" class="easyui-textbox" maxlength="10" id="categoryName"/>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryCategory();">查询</a>&nbsp;
		<gwideal:perm url="/category/add">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addCategory();">新增</a>&nbsp;
		</gwideal:perm>
		<gwideal:perm url="/category/edit/{id}">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editCategory();">修改</a>&nbsp;
		</gwideal:perm>
		<gwideal:perm url="/category/delete/{id}">
			    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deleteCategory();">删除</a>&nbsp;
		</gwideal:perm>
	</div>
	
	<table id="category_dg" class="easyui-datagrid"
			data-options="singleSelect:true,collapsible:true,url:'${base}/category/jsonPagination',
			method:'post',fit:true,pagination:true,toolbar:'#category_tb',singleSelect: true,
			selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:50,pageList:[50,100,150],rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'code',align:'left',resizable:false,sortable:true" width="10%">字典类型代码</th>
				<th data-options="field:'name',align:'center',resizable:false" width="22%">字典类型名称</th>
				<th data-options="field:'parentName',align:'center',resizable:false" width="22%">父字典类型名称</th>
				<th data-options="field:'orderNo',align:'center',resizable:false" width="10%">排列顺序</th>
				<th data-options="field:'description',align:'center',resizable:false" width="30%">说明</th>
			</tr>
		</thead>
	</table>
   </div>
</div>

<script type="text/javascript">
$('#CategoryTree').tree({
	onClick: function(node){
		$('#category_dg').datagrid('load',{ 
			code:node.code
		}); 
	}
});

function deleteCategory(){
	var row = $('#category_dg').datagrid('getSelected');
	var selections = $('#category_dg').datagrid('getSelections');
	if(null!=row && selections.length==1){
		$.messager.confirm('系统提示','删除字典会同时删除对应的字典项，确认删除吗?',function(r){
			if(r){
				$.ajax({ 
					type: 'POST', 
					url: '${base}/category/delete/'+row.id,
					dataType: 'json',  
					success: function(data){ 
						if(data.success){
							$.messager.alert('系统提示',data.info,'info');
							$("#category_dg").datagrid('reload');
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
