<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'north'" border="false" style="margin-bottom: 5px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
						<th class="br" width="60px">
							标题：
						</th> 
						<td class="br" width="150px">
							<input id="xxfbListTitle" class="easyui-textbox"></input>
						</td>
						<th class="br" width="60px">
							类型：
						</th> 
						<td class="br" width="140px">
							<select id="xxfbListType" class="easyui-combobox"  data-options="editable:false">
								<option value="">--请选择--</option>
         						<c:forEach items="${typeList}" var="type">
         							<option value="${type.id}">${type.name}</option>
         						</c:forEach>
	          				</select>
						</td>
						<th class="br" width="90px">
							发布状态：
						</th> 
						<td class="br" width="110px">
							<select id="xxfbListReleaseStatus" class="easyui-combobox"  data-options="editable:false">
								<option value="">--请选择--</option>
         						<option value="true">已发布</option>
         						<option value="false">未发布</option>
	          				</select>
						</td>
						<th class="br" width="90px">
							发布时间：
						</th> 
						<td class="br" width="220px">
							<input id="xxfbListStartDate" class="Wdate" type="text" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'xxfbListendDate\')}'})" readonly="readonly" style="width: 85px;"></input>
								-
							<input id="xxfbListendDate" class="Wdate" type="text" onClick="WdatePicker({minDate:'#F{$dp.$D(\'xxfbListStartDate\')}'})" readonly="readonly" style="width: 85px;"></input>
						</td>
						<td  class="br">
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryXxfb();">查询</a>
						</td>
					</tr>
				</table>
		</div>
		<div  region="center" border="false">
			<div id="xxfbListTab" style="padding:2px 5px;">
				<gwideal:perm url="/xxfb/add">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addArticle()" >新增</a>&nbsp;
				</gwideal:perm> 
			    <gwideal:perm url="/xxfb/edit/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editArticle()">修改</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/xxfb/delete/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deleteArticle()">删除</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'"  onclick="detailArticle()">查看</a>&nbsp;
				<gwideal:perm url="/xxfb/release/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="releaseArticle()">发布/取消发布</a>&nbsp;
				</gwideal:perm>
			</div>
			
			<table id="xxfbList_dg" class="easyui-datagrid"
			data-options="collapsible:true,url:'${base}/xxfb/jsonPagination?comeFrom=${comeFrom}',
			method:'post',fit:true,pagination:true,toolbar:'#xxfbListTab',singleSelect: true,
			selectOnCheck: true,checkOnSelect: true,remoteSort:true,nowrap:false">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'creatorId',hidden:true"></th>
						<th data-options="field:'isRelease',hidden:true"></th>
						<th data-options="field:'creatorName',align:'left',resizable:false,sortable:true" width="10%">创建人</th>
						<th data-options="field:'createTimeStr',align:'center',resizable:false,sortable:true" width="15%">创建时间</th>
						<th data-options="field:'title',align:'center',resizable:false,sortable:true" width="20%">标题</th>
						<th data-options="field:'typeName',align:'center',resizable:false,sortable:true" width="10%">类型</th>
						<th data-options="field:'releaseStatus',align:'center',resizable:false,sortable:true" width="10%">发布状态</th>
						<th data-options="field:'releaseTimeStr',align:'center',resizable:false,sortable:true" width="15%">发布时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	


<script type="text/javascript">
$(function(){
	$('#xxfbList_dg').datagrid({
		onDblClickRow :function(rowIndex,rowData){
			detailArticle();
		}
	});
});
function queryXxfb(){  
	$('#xxfbList_dg').datagrid('load',{ 
		title:$('#xxfbListTitle').val(),
		searchTypeId:$('#xxfbListType').combobox('getValue'),
		isRelease:$('#xxfbListReleaseStatus').combobox('getValue'),
		startDate:$('#xxfbListStartDate').val(),
		endDate:$('#xxfbListendDate').val(),
	} ); 
}
function addArticle(){
	window.open('${base}/xxfb/add?comeFrom=${comeFrom}');
}
function detailArticle(){
	var row = $('#xxfbList_dg').datagrid('getSelected');
	var selections = $('#xxfbList_dg').datagrid('getSelections');
	if(row!=null&&selections.length==1){
		window.open("${base}/xxfb/view/"+row.id);
	}else{
		 $.messager.alert('系统提示','请选择一条数据！','info');
	}
}
function editArticle(id){
	var row = $('#xxfbList_dg').datagrid('getSelected');
	var selections = $('#xxfbList_dg').datagrid('getSelections');
	if(row!=null&&selections.length==1){
		if(row.creatorId!="${currentUser.id}"){
			$.messager.alert('系统提示','您不能操作其他人发布的数据！','info');
			 return;
		}
		if(row.releaseStatus=="已发布"){
			 $.messager.alert('系统提示','发布状态为已发布的无法修改，请修改发布状态后再修改！','info');
			 return;
		}
		window.open("${base}/xxfb/edit/"+row.id+"?comeFrom=${comeFrom}");
	}else{
		 $.messager.alert('系统提示','请选择一条数据！','info');
	}
}
function deleteArticle(id){
	var row = $('#xxfbList_dg').datagrid('getSelected');
	var selections = $('#xxfbList_dg').datagrid('getSelections');
	if(row!=null&&selections.length==1){
		if(row.creatorId!="${currentUser.id}"){
			$.messager.alert('系统提示','您不能操作其他人发布的数据！','info');
			 return;
		}
		if(confirm("确认删除吗？")){
			$.ajax({ 
				type: 'POST', 
				url: '${base}/xxfb/delete/'+row.id,
				dataType: 'json',  
				success: function(data){ 
					if(data.success){
						$.messager.alert('系统提示',data.info,'info');
						$("#xxfbList_dg").datagrid('reload');
					}else{
						$.messager.alert('系统提示',data.info,'error');
					}
				} 
			}); 
		}
	}else{
		 $.messager.alert('系统提示','请选择一条数据！','info');
	}
}
function releaseArticle(id){
	var row = $('#xxfbList_dg').datagrid('getSelected');
	var selections = $('#xxfbList_dg').datagrid('getSelections');
	if(row!=null&&selections.length==1){
		if(row.creatorId!="${currentUser.id}"){
			$.messager.alert('系统提示','您不能操作其他人发布的数据！','info');
			 return;
		}
		var s="确认发布吗？";
		if(row.isRelease==true){
			s="确认取消发布吗？";
		}
		if(confirm(s)){
			$.ajax({ 
				type: 'POST', 
				url: '${base}/xxfb/release/'+row.id,
				dataType: 'json',  
				success: function(data){ 
					if(data.success){
						$.messager.alert('系统提示',data.info,'info');
						$("#xxfbList_dg").datagrid('reload');
					}else{
						$.messager.alert('系统提示',data.info,'error');
					}
				} 
			}); 
		}
	}else{
		 $.messager.alert('系统提示','请选择一条数据！','info');
	}
}
</script>
</body>
</html>

