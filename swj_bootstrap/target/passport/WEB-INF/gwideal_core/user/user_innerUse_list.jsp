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
	<div data-options="region:'center'">
		<div id="user_tb" style="padding:2px 5px;">
			<form action="" id="query_user_form" class="easyui-form" style="margin-bottom: 0px;" onkeydown="if(event.keyCode==13){queryUser();return false; }">
			    <p style="padding-bottom:5px;">
					姓名: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="userName"/>&nbsp;
					账号: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="userAccountNo"/>&nbsp;
					是否锁定：<select id="userListIsLocked">
		          				<option value="">--请选择--</option>
		          				<option value="TRUE">是</option>
		          				<option value="FALSE">否</option>
		          		     </select>
		           	密钥：<select id="userListIsKey">
		          				<option value="">--请选择--</option>
		          				<option value="TRUE">有</option>
		          				<option value="FALSE">无</option>
		          		     </select>
				</p>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryUser();">查询</a>&nbsp;
				<gwideal:perm url="/user/uploadSign/{id}">
				 	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="uploadSignUser();">上传电子签名</a>&nbsp;
				</gwideal:perm>
			</form>

		</div>
		
		<table id="user_dg" border="0" class="easyui-datagrid"
				data-options="singleSelect:true,collapsible:true,url:'${base}/user/jsonPagination',
				method:'post',fit:true,pagination:true,toolbar:'#user_tb',singleSelect: true,
				selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:50,pageList:[50,100,150],rownumbers:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',hidden:true"></th>
					<th data-options="field:'name',align:'left',resizable:false,sortable:true" width="9%">姓名</th>
					<th data-options="field:'accountNo',align:'center',resizable:false,sortable:true" width="9%">账号</th>
					<th data-options="field:'idCard',align:'center',resizable:false,sortable:true" width="18%">身份证</th>
					<th data-options="field:'mobileNo',align:'center',resizable:false,sortable:true" width="11%">手机号</th>
					<th data-options="field:'departName',align:'center',resizable:false" width="12%">部门名称</th>
					<th data-options="field:'locked',align:'center',resizable:false" width="8%">是否锁定</th>
					<th data-options="field:'loginCount',align:'center',resizable:false" width="8%">登录次数</th>
					<th data-options="field:'lastLoginTimeStr',align:'center',resizable:false" width="10%">最后登录时间</th>
					<th data-options="field:'lastLoginIp',align:'center',resizable:false" width="10%">最后登录IP</th>
				</tr>
			</thead>
		</table>
    </div>
</div>
<script type="text/javascript">
function queryUser(){
	$('#user_dg').datagrid('load',{ 
		name:$('#userName').val(),
		accountNo:$('#userAccountNo').val(),
		islocked:$('#userListIsLocked').val(),
		isKey:$('#userListIsKey').val()
	}); 
}
	function uploadSignUser(){
		var row = $('#user_dg').datagrid('getSelected');
		var selections = $('#user_dg').datagrid('getSelections');
		if(null!=row && selections.length==1){
		    var win=creatWin('上传用户电子签名',600,350,'icon-edit','/user/uploadSign/'+row.id);
		    win.window('open');
		}else{
			 $.messager.alert('系统提示','请选择一条数据！','info');
		}
	}
	function formatKey(val){
		if(val==null||val==''){
			return "无";
		}else{
			return "有";
		}
	}
</script>
</body>
</html>

