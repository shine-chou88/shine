<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title></title>
	</head>
	<body>
		<div class="easyui-layout" fit="true">
			<div data-options="region:'north'" border="false"
				style="margin-bottom: 5px;">
				<form action="" id="query_smspool_form" class="easyui-form"
					style="margin: 0px 0px 0px 0px;">
					<table cellpadding="5" cellspacing="0" class="a_search_table"
						width="100%" border="0">
						<tr>
							<th class="br" width="120px;">
								计划发送时间：
							</th>
							<td class="br" width="280px;">
								<input type="text" readonly="readonly"
									id="smspool_list_planSendTimes" name="planSendTimes"
									value="${bean.planSendTimes}" class="Wdate" style="width: 100px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								&nbsp;至&nbsp;
								<input type="text" readonly="readonly"
									id="smspool_list_planSendTimee" name="planSendTimee"
									value="${bean.planSendTimee}" class="Wdate" style="width: 100px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</td>
							<th class="br" width="90px;">
								接收号码：
							</th>
							<td class="br" width="110px;">
								<input type="text" class="easyui-textbox" style="width: 100px" id="smspool_list_receiveMobile" name="receiveMobile" value="${bean.receiveMobile}" />
							</td>
							<th class="br" width="90px;">
								发送状态：
							</th>
							<td class="br">
								<select id="smspool_list_smsState" name="smsState"
									class="easyui-combobox" style="width: 100px">
									<option value="">-请选择-</option>
									<option value="10" <c:if test="${bean.smsState=='10'}">selected="selected"</c:if>>未发送</option>
									<option value="12" <c:if test="${bean.smsState=='12'}">selected="selected"</c:if>>发送成功</option>
									<option value="0"<c:if test="${bean.smsState=='0'}">selected="selected"</c:if>>发送失败</option>
									<option value="14"<c:if test="${bean.smsState=='14'}">selected="selected"</c:if>>取消发送</option>
								</select>

							</td>
						</tr>
						<tr>
							<th class="br" width="90px;">
								发送内容：
							</th>
							<td class="br" colspan="5">
								<input style="width: 260px" class="easyui-textbox"
									value="${bean.content}" id="smspool_list_content"
									name="content" />
								<a href="javascript:void(0)" style="margin-left: 20px;"
									class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'"
									onclick="searchSmspool()">查询</a>&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div region="center" border="false">
			
				<div id="smspool_tb" style="padding: 2px 5px;">
				<gwideal:perm url="/smsPool/batchDelete/{ids}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="batchDelete()">批量删除</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/smsPool/batchCancel/{ids}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel32',size:'large',iconAlign:'left'" onclick="batchCancel()">取消发送</a>&nbsp;
				</gwideal:perm>
				<!--<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpSmsPool()">帮助</a>-->
				</div>
				
				<table id="smsPool_dg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/smsPool/smsPoolJson',
					method:'post',fit:true,pagination:true,toolbar:'#smspool_tb',singleSelect: false,
					selectOnCheck:true,checkOnSelect: true">
					<thead>
						<tr>
							<th data-options="field:'ck',checkbox:true"></th>
							<th data-options="field:'id',hidden:true"></th>
							<th
								data-options="field:'planSendTimeName',align:'left',resizable:true,sortable:true"
								width="15%">
								计划发送时间
							</th>
							<th
								data-options="field:'receiveMobile',align:'center',resizable:true,sortable:true"
								width="15%">
								接收号码
							</th>
							<th
								data-options="field:'receivePerson',align:'center',resizable:true,sortable:true"
								width="15%">
								接收人
							</th>
							<th
								data-options="field:'smsStateName',align:'center',resizable:true,sortable:true"
								width="10%">
								发送状态
							</th>
							<th data-options="field:'content',align:'center',resizable:true"
								width="40%" formatter="displayContent">
								发送内容
							</th>
						</tr>
					</thead>
				</table>
			</div>

		</div>

		<script type="text/javascript">
		function displayContent(value){
			return '<div title="'+value+'">'+value+'</div>';
		}
		
		function searchSmspool() {
			$('#smsPool_dg').datagrid('load', {
				//	计划发送时间 接收号码 发送状态 发送内容	
				planSendTimes : $('#smspool_list_planSendTimes').val(),
				planSendTimee : $('#smspool_list_planSendTimee').val(),
				receiveMobile : $('#smspool_list_receiveMobile').textbox('getValue'),
				smsState : $('#smspool_list_smsState').combobox('getValue'),
				content : $('#smspool_list_content').textbox('getValue')
			});
		}
		
		function batchCancel() {
			var row = $('#smsPool_dg').datagrid('getSelections');
			var selections = $('#smsPool_dg').datagrid('getSelections');
			var id = ''
			for (i = 0; i < row.length; i++) {
				id = id + row[i].id + ',';
			}
			if (row != null) {
				$.messager.confirm('系统提示', '确认取消发送么?', function(r) {
					if (r) {
						$.ajax( {
							type : 'POST',
							url : base + '/smsPool/batchCancel/' + id,
							dataType : 'json',
							success : function(data) {
								if (data.success) {
									//刷新数据 
							$.messager.alert('系统提示', data.info, 'info');
							$("#smsPool_dg").datagrid('reload');
						} else {
							$.messager.alert('系统提示', data.info, 'info');
						}
					}
						});
					}
				});
			} else {
				$.messager.alert('系统提示', '请选择要删除的数据！', 'info');
			}
		}
		function batchDelete() {
			var row = $('#smsPool_dg').datagrid('getSelections');
			var selections = $('#smsPool_dg').datagrid('getSelections');
			var id = ''
			for (i = 0; i < row.length; i++) {
				id = id + row[i].id + ',';
			}
			if (row != null) {
				$.messager.confirm('系统提示', '确认删除么?', function(r) {
					if (r) {
						$.ajax( {
							type : 'POST',
							url : base + '/smsPool/batchDelete/' + id,
							dataType : 'json',
							success : function(data) {
								if (data.success) {
									//刷新数据 
							$.messager.alert('系统提示', data.info, 'info');
							$("#smsPool_dg").datagrid('reload');
						} else {
							$.messager.alert('系统提示', data.info, 'info');
						}
					}
						});
					}
				});
			} else {
				$.messager.alert('系统提示', '请选择要删除的数据！', 'info');
			}
		}
		
		function helpSmsPool(){
			window.open("./resource/onlinehelp/sms/pool/help.html");
		}
	</script>
	</body>
</html>

