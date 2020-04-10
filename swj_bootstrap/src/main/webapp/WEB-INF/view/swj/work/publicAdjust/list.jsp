<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'north'" border="false" style="margin-bottom: 5px;">
			<form id="publicAdjustListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					  	<th class="br" width="60px;">单位: </th>
						<td class="br" width="180px;">
							<input type="text" id="departIdPublicAdjustList"  data-options="url:'${base}/depart/certificateDepartJson?',valueField:'id',textField:'text'" class="easyui-combobox"  style="width:250px"></input>
						</td>
			  			<th class="br" width="90px;">填写人姓名: </th>
						<td class="br" width="180px;">
							<input class="easyui-textbox" style="width:170px" id="writeUserNamePublicAdjustList"></input>
						</td>
						<th class="br" width="60px;">状态：</th>
		          		<td class="br" width="100px;">
		          			<input id="statusPublicAdjustList" class="easyui-combobox" name="isNotBack" style="width:100px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'已申请','text':'已申请'},{'id':'已通过','text':'已通过'}]"></input>
		          		</td>
					  	<td class="br">
							<a style="margin-left: 30px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryPublicAdjustList()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="publicAdjustListTb" style="padding:2px 5px;">
				<gwideal:perm url="/publicAdjust/add">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addPublicAdjust()" >新增</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/publicAdjust/edit/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editPublicAdjust()">修改</a>&nbsp;
				</gwideal:perm>
<%-- 				<gwideal:perm url="/publicAdjust/delete/{id}"> --%>
<!-- 					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deletePublicAdjust()">删除</a>&nbsp; -->
<%-- 				</gwideal:perm> --%>

				<gwideal:perm url="/publicAdjust/adjustConfirm/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="confirmPublicAdjust()">调整确认</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewPublicAdjust()">查看</a>&nbsp;
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpPublicAdjust()">帮助</a>
			</div>
			<table id="publicAdjustListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/publicAdjust/jsonPagination',
					method:'post',fit:true,pagination:true,toolbar:'#publicAdjustListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
																		<th data-options="field:'departName',align:'left',resizable:true,sortable:true" width="150px">单位</th>
																								<th data-options="field:'writeUserName',align:'center',resizable:false,sortable:false" width="100px">填写人姓名</th>
																								<th data-options="field:'afterDepartInnerName',align:'center',resizable:false,sortable:false" width="200px">(系统内)调动后单位名称</th>
																								<th data-options="field:'afterDepartOuter',align:'center',resizable:false,sortable:false" width="200px">(系统外)调动后单位名称</th>
																								<th data-options="field:'hasRetired',align:'center',resizable:false,sortable:false" width="100px">是否退休</th>
																								<th data-options="field:'writeUserPhone',align:'center',resizable:false,sortable:false" width="150px">填写人电话</th>
																								<th data-options="field:'writeDate',align:'center',resizable:false,sortable:false" width="150px"formatter="ChangeDateFormat2">填写日期</th>
																								<th data-options="field:'status',align:'center',resizable:false,sortable:false" width="80px" >状态</th>
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		function queryPublicAdjustList(){
			 $('#publicAdjustListDg').datagrid('load',{
			 			departId:$('#departIdPublicAdjustList').textbox('getValue'),
						writeUserName:$('#writeUserNamePublicAdjustList').textbox('getValue'),
						status:$('#statusPublicAdjustList').combobox('getValue')
			}); 
		} 

		function addPublicAdjust(){
		    var win=creatWin('新增-证照信息调整',780,540,'icon-add','/publicAdjust/add');
		    win.window('open');
		}
		
		function editPublicAdjust(){
			var row = $('#publicAdjustListDg').datagrid('getSelected');
			var selections = $('#publicAdjustListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    if (row.status == '已通过') {
			    	$.messager.alert('系统提示', '已通过的数据不能修改！', 'info');
			    	return;
			    }
			    var win=creatWin('修改-证照信息调整',780,540,'icon-edit','/publicAdjust/edit/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}

		function viewPublicAdjust(){
			var row = $('#publicAdjustListDg').datagrid('getSelected');
			var selections = $('#publicAdjustListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatFirstWin('查看-证照信息调整',780,540,'icon-search','/publicAdjust/view/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function deletePublicAdjust(){
			var row = $('#publicAdjustListDg').datagrid('getSelected');
			var selections = $('#publicAdjustListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				$.messager.confirm('系统提示','确认删除吗?',function(id){ 
					if(id){ 
						$.ajax({ 
							type: 'POST', 
							url: base+'/publicAdjust/delete/'+row.id,
							dataType: 'json',  
							success: function(data){
								if(data.success){
									$.messager.alert('系统提示', data.info, 'info');
									$("#publicAdjustListDg").datagrid('reload'); 
								}else{ 
									$.messager.alert('系统提示', data.info, 'info');
								} 
							} 
						}); 
					} 
				}); 		
			}else{
				$.messager.alert('系统提示','请选择一条要删除的数据！','info');
			}
		}

		function confirmPublicAdjust(){
			var row = $('#publicAdjustListDg').datagrid('getSelected');
			var selections = $('#publicAdjustListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if (row.status == '已通过') {
			    	$.messager.alert('系统提示', '已通过的数据不能确认！', 'info');
			    	return;
			    }
				$.messager.confirm('系统提示','确认调整吗?',function(id){ 
					if(id){ 
						$.ajax({ 
							type: 'POST', 
							url: base+'/publicAdjust/adjustConfirm/'+row.id,
							dataType: 'json',  
							success: function(data){
								if(data.success){
									$.messager.alert('系统提示', data.info, 'info');
									$("#publicAdjustListDg").datagrid('reload'); 
								}else{ 
									$.messager.alert('系统提示', data.info, 'info');
								} 
							} 
						}); 
					} 
				}); 		
			}else{
				$.messager.alert('系统提示','请选择一条要确认的数据！','info');
			}
		}
		
		function helpPublicAdjust(){
			window.open("./resource/onlinehelp/yingong/zzxxtz/help.html");
		}
	</script>
</body>
</html>

