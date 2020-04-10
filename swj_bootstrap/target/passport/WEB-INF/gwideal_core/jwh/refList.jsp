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
function queryJwh(){
	$('#jwh_dg').datagrid('load',{ 
		name:$('#jwhName').val(),
		code:$('#jwhCode').val()
	}); 
}
function clearQuery(){
	$('#jwhName').textbox('setValue',"");
	$('#jwhCode').textbox('setValue',"");
}

function selectJwh(row){
	if(row==undefined){
		row = $('#jwh_dg').datagrid('getSelections');
	}
	var ids = "";
	var names = "";
	var nameid = "";
	var id = "";
	var name = "";
	if(row.length>0){//复选框多选
		for(var i=0;i<row.length;i++){
			if(row[i].haveChild=="1"){
				alert("请选择所属单位！");
				return;
			}
			id = row[i].id;
			//var code=row[i].code;
			name = row[i].jwhName;
			nameid+=id+":"+name+",";
			ids+=ids==""?id:","+id;
			names+=names==""?name:","+name;
		}
	}else{//双击选择单个
		if(row.haveChild=='1'){
			alert('请选择所属单位！');
			return;
		}
		id = row.id;
		name = row.name;
		nameid+=id+":"+name+",";
		ids+=ids==""?id:","+id;
		names+=names==""?name:","+name;
	}
	if(row==""){
		alert("请选择一条记录！");
		return;
	}
	var rtnValue = ids + '&&' + names + '&&' +nameid;
	//alert(rtnValue);
	if(window.navigator.userAgent.indexOf("Chrome")>-1){
		window.opener.returnValue = rtnValue;
	}else{
		window.returnValue = rtnValue;
	}
	//调用父窗口方法
	jwhSearch1(rtnValue);
	
	closeWindow();
}

$(function(){
	var t = $('#selectType').val();
	if(t=='single'){
		$('#jwh_dg').datagrid({
			singleSelect:true
		});
	}else if(t=='multi'){
		$('#jwh_dg').datagrid({
			singleSelect:false
		});
	}
});

</script>



<div class="main auto clearfix" style="width: 700px">
<input id="selectType" type="hidden" value="${selectType}">


    <div class=""  id="" style="height: 800px; width: 615px">
		<div id="depart_tb" style="padding:8px 10px;">
			居委会名称: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="jwhName"/>&nbsp;
			居委会代码: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="jwhCode"/>&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 70px;" onclick="queryJwh();">查询</a>&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="selectJwh()" style="width: 70px;">新增</a>&nbsp;
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="clearQuery()" style="width: 70px;">置空</a>&nbsp; -->
		</div>
	
		<table id="jwh_dg" class="easyui-datagrid"
				data-options="collapsible:true,url:'${base}/jwh/jsonPagination',
				method:'post',fit:true,pagination:true,toolbar:'#depart_tb',
				selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:25,pageList:[25,50,100],rownumbers:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',hidden:true"></th>
					<th data-options="field:'jwhName',align:'center',resizable:false" width="60%">居委会名称</th>
					<th data-options="field:'code',align:'left',resizable:false,sortable:true" width="40%">居委会代码</th>
				</tr>
			</thead>
		</table>
	</div>
	
	
	
</div>
</body>

<script type="text/javascript">

</script>

</html>
