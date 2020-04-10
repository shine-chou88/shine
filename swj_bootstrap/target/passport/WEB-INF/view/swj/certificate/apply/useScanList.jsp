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
			<form id="scancertificateInfoApplyListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					  	<th class="br" width="90px;">
							申请编号: 
						</th>
						<td class="br" width="180px;">
							<input class="easyui-textbox" style="width:170px" id="scanwcodeCertificateInfoApplyList"></input>
						</td>
						<th class="br" width="90px;">
							申请时间: 
						</th>
						<td class="br" width="320px;">
							<input type="text" readonly="readonly" id="scanapplyStartTime"  class="Wdate"  data-options="required:true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'scanapplyEndTime\')}'})" style="width:130px" ></input>
        				至&nbsp;<input type="text" readonly="readonly" id="scanapplyEndTime" class="Wdate" data-options="required:true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'scanapplyStartTime\')}'})" style="width:130px" ></input>
						</td>
						<td class="br">
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="usescanQueryCertificateInfoApplyList()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="scancertificateInfoApplyListTb" style="padding:2px 5px;">
				<gwideal:perm url="/certificateInfoApply/useScan">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-useScan32',size:'large',iconAlign:'left'" onclick="useScanCertificateInfoApply()" >领用扫描</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="usescanViewCertificateInfoApply()">查看</a>&nbsp;
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpCertificateApplyUse()">帮助</a>
			</div>
			<table id="scancertificateInfoApplyListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/certificateInfoApply/scanJsonPagination?scanType=use',
					method:'post',fit:true,pagination:true,toolbar:'#scancertificateInfoApplyListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'auditStatus',hidden:true"></th>
						<th data-options="field:'processInstanceId',hidden:true"></th>
						<th data-options="field:'wcode',align:'left',resizable:true,sortable:true" width="10%">申请编号</th>
						<th data-options="field:'startDate',align:'center',resizable:false,sortable:false" width="10%" formatter="ChangeDateFormat2">开始时间</th>
						<th data-options="field:'endDate',align:'center',resizable:false,sortable:false" width="10%" formatter="ChangeDateFormat2">结束时间</th>
						<th data-options="field:'reason',align:'center',resizable:false,sortable:false" width="10%">原因</th>
						<th data-options="field:'destination',align:'center',resizable:false,sortable:false" width="10%">目的地</th>
						<th data-options="field:'itinerary',align:'center',resizable:false,sortable:false" width="12%">行程路线</th>
						<th data-options="field:'applyUseType',align:'center',resizable:false,sortable:false" formatter="applyTypeFormat" width="8%">申请类型</th>
						<th data-options="field:'auditStatusStr',align:'center',resizable:false,sortable:false" width="8%">状态</th>
						<th data-options="field:'creatorName',align:'center',resizable:false,sortable:false" width="8%">申请人</th>
						<th data-options="field:'applyTime',align:'center',resizable:false,sortable:false" width="10%" formatter="dateFormatYYYYMMDDHHmm">申请时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function usescanqueryCertificateInfoApplyList(){
			 $('#scancertificateInfoApplyListDg').datagrid('load',{
			 	wcode:$('#scanwcodeCertificateInfoApplyList').textbox('getValue'),
			 	applyStartTime:$("#scanapplyStartTime").val(),
			 	applyEndTime:$("#scanapplyEndTime").val()
			 }); 
		} 

		function applyTypeFormat(value){
			if(null!=value && value.length>0){
				return "领用";
			}else{
				return "办理";
			}
		}
		

		function usescanViewCertificateInfoApply(){
			var row = $('#scancertificateInfoApplyListDg').datagrid('getSelected');
			var selections = $('#scancertificateInfoApplyListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-因私出国(境)申请',780,650,'icon-search','/certificateInfoApply/view/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function useScanCertificateInfoApply(){
			var win=creatWin('证照领用-扫描',780,650,'icon-search','/certificateInfoApply/useScan');
		    win.window('open');
		}
		
		function helpCertificateApplyUse(){
			window.open("./resource/onlinehelp/yingsi/zzdly/help.html");
		}
	</script>
</body>
</html>

