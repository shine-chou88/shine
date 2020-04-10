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
		function editDepart(){
			var row = $('#depart_dg').datagrid('getSelected');
			var selections = $('#depart_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
			    var win=creatWin('修改-部门信息',650,330,'icon-edit','/depart/edit/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}
		function addDepart(){
			var node=$('#DepartTree').tree('getSelected');
			var win=creatWin('新增-部门信息',650,330,'icon-add','/depart/add');
			if(null!=node){
				win=creatWin('新增-部门信息',650,330,'icon-add','/depart/add?id='+node.id);
				win.window('open');
			}else{
				win.window('open');
			}
		}
		function deleteDepart(){
			var row = $('#depart_dg').datagrid('getSelected');
			var selections = $('#depart_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
				$.messager.confirm('系统提示','确认删除吗?',function(id){ 
					if(id){ 
						$.ajax({ 
							type: 'POST', 
							url: '${base}/depart/delete/'+row.id,
							dataType: 'json',  
							success: function(data){ 
								if(data.success){
									$.messager.alert('系统提示',data.info,'info');
									$('#DepartTree').tree('reload');
									$("#depart_dg").datagrid('reload');
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
		function queryDepart(){
			$('#depart_dg').datagrid('load',{ 
				name:$('#departName').val(),
				code:$('#departCode').val()
			}); 
		}
	</script>

    <div class="easyui-layout" fit="true">
        <div data-options="region:'west',split:false"  style="width:230px;">
		 <ul id="DepartTree" class="easyui-tree" data-options="url:'${base}/depart/tree',animate:true,lines:true"></ul>
	    </div>
	<div data-options="region:'center'">    
	<div id="depart_tb" style="padding:2px 5px;">
		部门名称: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="departName"/>&nbsp;
		部门代码: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="departCode"/>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryDepart();">查询</a>&nbsp;
		<gwideal:perm url="/depart/add">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addDepart();">新增</a>&nbsp;
		</gwideal:perm>
		<gwideal:perm url="/depart/edit/{id}">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editDepart();">修改</a>&nbsp;
		</gwideal:perm>
		<gwideal:perm url="/depart/delete/{id}">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deleteDepart();">删除</a>&nbsp;
		</gwideal:perm>
		<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpDepart()">帮助</a>
	</div>
	
	<table id="depart_dg" class="easyui-datagrid"
			data-options="singleSelect:true,collapsible:true,url:'${base}/depart/jsonPagination',
			method:'post',fit:true,pagination:true,toolbar:'#depart_tb',singleSelect: true,
			selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:50,pageList:[50,100,150],rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'code',align:'left',resizable:false,sortable:true" width="16%">部门代码</th>
				<th data-options="field:'name',align:'center',resizable:false" width="22%">部门名称</th>
				<th data-options="field:'parentName',align:'center',resizable:false" width="22%">上级部门名称</th>
				<th data-options="field:'orderNo',align:'center',resizable:false" width="10%">排列顺序</th>
				<th data-options="field:'description',align:'center',resizable:false" width="25%">说明</th>
			</tr>
		</thead>
	</table>
  </div>
</div>

<script type="text/javascript">
$('#DepartTree').tree({
	onClick: function(node){
		$('#depart_dg').datagrid('load',{ 
			id:node.id
		}); 
	}
});

function helpDepart(){
	window.open("./resource/onlinehelp/xtgl/bmgl/help.html");
}
</script>
</body>
</html>
