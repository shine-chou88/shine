<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
	<div id="resource_tb" style="padding:2px 5px;">
	<form action="" id="query_form"  class="easyui-form" style="margin: 0px 0px 0px 0px;" onkeydown="if(event.keyCode==13){query();return false; }">
		<input type="hidden" id="userName" name="userName">
		操作人员: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="user_list_userName" value="${userName}"/>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="query();">查询</a>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="setting();">文件上传总量参数设置</a>&nbsp;
		<gwideal:perm url="/redis/buildingInfoCanvas">
			<a href="javascript:void(0)" style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="buildingInfoCanvasDelete()">清除房屋立面图缓存</a>
		</gwideal:perm>
	</form>
	</div>
	
	<table id="resource_dg" class="easyui-datagrid"
			data-options="singleSelect:true,collapsible:true,url:'${base}/resourceAtt/jsonPagination',
			method:'post',fit:true,pagination:true,toolbar:'#resource_tb',singleSelect: true,
			selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:50,pageList:[50,100,150],rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'userName',align:'left',resizable:false" width="40%">操作人</th>
				<th data-options="field:'fileHasUsedSize',align:'center',resizable:false" width="25%">文件已上传总量(约)</th>
				<th data-options="field:'fileUnUsedSize',align:'center',resizable:false" width="25%">文件上传剩余总量(约)</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function query(){
		$('#resource_dg').datagrid('options').url="${base}/resourceAtt/jsonPagination";
		$('#resource_dg').datagrid('load',{ 
			userName:$('#user_list_userName').val()
		}); 
	}
	function setting(){
		var win=creatWin('文件上传总量参数设置',450,170,'icon-edit','/resourceAtt/toSetting');
	    win.window('open');
	}
	function buildingInfoCanvasDelete(){
		$.messager.confirm('系统提示','确认清除房屋立面图缓存么?',function(id){ 
			if(id){ 
				$.ajax({ 
					type: 'POST', 
					url: base+'/redis/buildingInfoCanvas',
					dataType: 'json',  
					success: function(data){
						if(data.success){
							//刷新数据 
							$.messager.alert('系统提示', data.info, 'info');
						}else{ 
							$.messager.alert('系统提示', data.info, 'error');
						} 
					} 
				}); 
			} 
		}); 
	}
	</script>
</body>
</html>
