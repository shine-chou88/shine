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
function queryrefMsgUser(){
	$('#user_refMsg_dg').datagrid('load',{ 
		name:$('#userName').val(),
		mobileNo:$('#mobileNo').val()
	}); 
}
function clearQuery(){
	$('#departName').textbox('setValue',"");
	$('#departCode').textbox('setValue',"");
}

function selectUser(row){
	if ('messagePush'==$('#selectType').val()) {
		var selections = $('#user_refMsg_dg').datagrid('getSelections');
		if(selections.length==1){
			$('#messagePush_add_acceptUserIds').val(selections[0].id);
	       	$('#messagePush_add_acceptNames').textbox('setValue',selections[0].name);
		}else{
			 $.messager.alert('系统提示','请选择一条数据！','info');
		}
		closeWindow();
		return;
	} else if ('device'==$('#selectType').val()) {
		var selections = $('#user_refMsg_dg').datagrid('getSelections');
		if(selections.length==1){
			$('#inspectDevice_add_userId').val(selections[0].id);
	       	$('#inspectDevice_add_userName').textbox('setValue',selections[0].name);
	       	$('#inspectDevice_add_streetId').val(selections[0].streetId);
	       	$('#inspectDevice_add_departId').val(selections[0].departId);
	       	$('#inspectDevice_add_owner').val(selections[0].departName);
	       	$('#inspectDevice_add_ownerHz').text(selections[0].departName);
		}else{
			 $.messager.alert('系统提示','请选择一条数据！','info');
		}
		closeWindow();
		return;
	} else if ('taskDistributeAdd'==$('#selectType').val()) {
		var selections = $('#user_refMsg_dg').datagrid('getSelections');
		if(selections.length > 0){
			var html = '';
			for(var i=0;i<selections.length;i++){
				if (document.getElementById(selections[i].id)==undefined) {
					html += "<div id='"+selections[i].id+"' style='float:left;margin-left:8px;margin-top:8px;' > "+
					"<input type='hidden' name='userIds' value='"+selections[i].id+"'/>"+
					""+selections[i].name+""+
					"<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick = 'taskDistributecancelUser(this)' title='删除' />"+
					"</div>";
				}
			}
			$("#taskDistribute_div_user").append(html);
		}else{
			 $.messager.alert('系统提示','请选择一条数据！','info');
		}
		closeWindow();
		return;
	}
	if(row==undefined){
		row = $('#user_refMsg_dg').datagrid('getSelections');
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
			name = row[i].name;
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
	if(ids==""){
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
	userSearch1(rtnValue);
	
	closeWindow();
	//parent.window.closeWind();  
	//window.close();
}

$(function(){
	var t = $('#selectType').val();
	if(t=='single'){
		$('#user_refMsg_dg').datagrid({
			singleSelect:true
		});
	}else if(t=='multi'){
		$('#user_refMsg_dg').datagrid({
			singleSelect:false
		});
	}else if(t=='messagePush'||t=='device'){
		$('#user_refMsg_dg').datagrid({
			singleSelect:true
		});
	}
});

</script>



<div class="easyui-layout" fit="true">
    	<div region="center" border="false">

     <!--right start-->
    <input id="selectType" type="hidden" value="${selectType}">
		<div id="user_refMsg_tb" style="padding:8px 10px;">
			姓名: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="userName"/>&nbsp;
			手机号码： <input type="text" size="15" class="easyui-textbox" maxlength="10" id="mobileNo"/>&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 70px;" onclick="queryrefMsgUser();">查询</a>&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="selectUser()" style="width: 70px;">确认</a>&nbsp;
		</div>
	
		<table id="user_refMsg_dg" class="easyui-datagrid"
				data-options="collapsible:true,url:'${base}/user/<c:if test="${selectType !='device' && selectType !='taskDistributeAdd'}">jsonPaginationMsgReceiver</c:if><c:if test="${selectType =='device' || selectType =='taskDistributeAdd'}">jsonPagination?jddm=${jddm }</c:if>',
				method:'post',fit:true,pagination:true,toolbar:'#user_refMsg_tb',
				selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:25,pageList:[25,50,100],rownumbers:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',hidden:true"></th>
					<th data-options="field:'name',align:'center',resizable:false" width="25%">姓名</th>
					<th data-options="field:'mobileNo',align:'left',resizable:false,sortable:true" width="25%">手机号码</th>
					<th data-options="field:'departName',align:'left',resizable:false,sortable:true" width="35%">部门名称</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
</body>
</html>
