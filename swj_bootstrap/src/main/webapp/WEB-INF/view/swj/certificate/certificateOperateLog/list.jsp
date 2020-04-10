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
			<form id="certificateOperateLogListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					<th class="br" width="75px;">
									证照姓名: 
								</th>
								<td class="br" width="40px;">
									<input class="easyui-textbox" style="width:100px" id="nameLogCertificateInfo"></input>
								</td>
					  						  		<th class="br" width="90px;">
									操作类型: 
								</th>
								<td class="br" width="180px;">
									<input id="operateTypeCertificateOperateLogList" class="easyui-combobox" style="width:90px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[{'id':'','text':'--请选择--'},
								{'id':'print','text':'打印'},{'id':'checkIn','text':'入库'},{'id':'delete','text':'删除'},{'id':'cancel','text':'注销'},{'id':'use','text':'领用'},{'id':'back','text':'归还'}]"></input>	
								</td>
								<th class="br" width="90px;">
									操作时间: 
								</th>
					  			<td class="br"width="200px;" >
							<input id="operate_startDate" class="Wdate" type="text" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'operate_endDate\')}'})" readonly="readonly" style="width: 88px;"></input>
								-
							<input id="operate_endDate" class="Wdate" type="text" onClick="WdatePicker({minDate:'#F{$dp.$D(\'operate_startDate\')}'})" readonly="readonly" style="width: 88px;"></input>
						</td>			  						  							  						  							  						  							  							<td class="br">
							<a style="margin-left: 30px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryCertificateOperateLogList()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="certificateOperateLogListTb" style="padding:2px 5px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewCertificateOperateLog()">查看</a>&nbsp;
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpCertificateOperateLog()">帮助</a>
			</div>
			<table id="certificateOperateLogListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/certificateOperateLog/jsonPagination?businessType=private',
					method:'post',fit:true,pagination:true,toolbar:'#certificateOperateLogListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'certificateId',hidden:true"></th>
						<th data-options="field:'certificateName',align:'center',resizable:false,sortable:false" width="120px">证照姓名</th>
						<th data-options="field:'certificateTypeShow',align:'center',resizable:false,sortable:false" width="220px">证照类型</th>
						<th data-options="field:'certificateNoShow',align:'center',resizable:false,sortable:false" width="180px">证照编号</th>
						<th data-options="field:'departNameShow',align:'center',resizable:false,sortable:false" width="150px">所在单位</th>
						<th data-options="field:'operateUserName',align:'center',resizable:false,sortable:false" width="150px">操作人</th>
																								<th data-options="field:'operateContent',align:'center',resizable:false,sortable:false" width="200px">操作内容</th>
																								
																								<th data-options="field:'operateTime',align:'center',resizable:false,sortable:false" width="150px" formatter="ChangeDateFormat">操作时间</th>
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		function queryCertificateOperateLogList(){
			 $('#certificateOperateLogListDg').datagrid('load',{
			 	certificateName:$("#nameLogCertificateInfo").val(),			 									
				 operateType:$('#operateTypeCertificateOperateLogList').combobox('getValue'),
															 	startTime:$("#operate_startDate").val(),
															 	endTime:$("#operate_endDate").val()
															 								 								 								}); 
		} 


		function viewCertificateOperateLog(){
			var row = $('#certificateOperateLogListDg').datagrid('getSelected');
			var selections = $('#certificateOperateLogListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-证照使用记录',800,650,'icon-search','/certificateInfo/view/'+row.certificateId+'?businessType=single');
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function helpCertificateOperateLog(){
			window.open("./resource/onlinehelp/yingsi/zzsyjl/help.html");
		}
	</script>
</body>
</html>

