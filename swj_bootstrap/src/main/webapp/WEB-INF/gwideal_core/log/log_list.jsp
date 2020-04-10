<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
	<div id="log_tb" style="padding:2px 5px;">
	<form action="" id="query_log_form"  class="easyui-form" style="margin: 0px 0px 0px 0px;" onkeydown="if(event.keyCode==13){queryLog();return false; }">
		<input type="hidden" id="logName" name="logName">
		<input type="hidden" id="startTime" name="startTime">
		<input type="hidden" id="endTime" name="endTime">
		<input type="hidden" id="operateContent" name="operateContent">
		<input type="hidden" id="type" name="type">
		操作人员: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="log_list_logName" value="${logName}"/>&nbsp;
		操作时间：
			<input type="text" id="log_list_startTime" style="width: 150px;"/>
			-
			<input type="text" id="log_list_endTime" style="width: 150px;"/>&nbsp;
		操作内容：<input style="width: 200px;" type="text" class="easyui-textbox" maxlength="10" id="log_list_operateContent"/>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryLog();">查询</a>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="logListArchive();">日志归档</a>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="exportLog();">导出</a>&nbsp;
	</form>
	</div>
	
	<table id="log_dg" class="easyui-datagrid"
			data-options="singleSelect:true,collapsible:true,url:'${base}/log/jsonPagination?currentlogName=${currentlogName}&logName=${logName}',
			method:'post',fit:true,pagination:true,toolbar:'#log_tb',singleSelect: true,
			selectOnCheck: true,checkOnSelect: true,remoteSort:true,pageSize:50,pageList:[50,100,150],rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'userName',align:'left',resizable:false" width="10%">操作人</th>
				<th data-options="field:'createDateStr',align:'center',resizable:false" width="15%">操作时间</th>
				<th data-options="field:'ip',align:'center',resizable:false" width="15%">操作IP</th>
				<th data-options="field:'operateUrl',align:'center',resizable:false" width="30%">操作路径</th>
				<th data-options="field:'operateContent',align:'center',resizable:false" width="25%">操作内容</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		$('#log_list_startTime').datetimebox({
	        showSeconds: false,
	        editable:false
	    });
		$('#log_list_endTime').datetimebox({
	        showSeconds: false,
	        editable:false
	    });
		function queryLog(){
			$('#log_dg').datagrid('options').url="${base}/log/jsonPagination";
			$('#log_dg').datagrid('load',{ 
				logName:$('#log_list_logName').val(),
				startTime:$("#log_list_startTime").datetimebox('getValue'),
				endTime:$("#log_list_endTime").datetimebox('getValue'),
				operateContent:$("#log_list_operateContent").val()
			}); 
		}
		function logListArchive(){
			var win=creatWin('归档-操作日志',450,170,'icon-edit','/log/toArchive');
		    win.window('open');
		}
		function exportLog(){
			if (confirm("确定导出(注:最多65000条)？")) {
				var exportForm = document.getElementById("query_log_form");
				var logName=$('#log_list_logName').val();
				$("#logName").val(logName);
				var start=$("#log_list_startTime").datetimebox('getValue');
				$("#startTime").val(start);
				var end=$("#log_list_endTime").datetimebox('getValue');
				$("#endTime").val(end);
				var operateContent=$("#log_list_operateContent").val();
				$("#operateContent").val(operateContent);
				exportForm.setAttribute("method","post");
				exportForm.setAttribute("accept-charset","UTF-8");
				exportForm.setAttribute("action",
						"${base}/log/exportExcel");
				exportForm.submit();	
			}
		}
	</script>
</body>
</html>
