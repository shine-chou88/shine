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
			<form id="scanUsepublicBusinessListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					  						  		<th class="br" width="90px;">
									组团单位: 
								</th>
								<td class="br" width="180px;">
									<input class="easyui-textbox" style="width:170px" id="scanUsegroupDepartIdPublicBusinessList"></input>
								</td>
					  							  						  		<th class="br" width="90px;">
									批件号: 
								</th>
								<td class="br" width="180px;">
									<input class="easyui-textbox" style="width:170px" id="scanUseapprovalNoPublicBusinessList"></input>
								</td>
					  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  							<td class="br">
							<a style="margin-left: 30px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="scanUsequeryPublicBusinessList()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="scanUsepublicBusinessListTb" style="padding:2px 5px;">
				<gwideal:perm url="/publicBusiness/useScan">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-useScan32',size:'large',iconAlign:'left'" onclick="useScan()">领用扫描</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/publicBusiness/editUse/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editScanCertificate()">添加证照</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewScanPublicBusiness()">查看</a>&nbsp;
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpPublicBusinessScanUseRegister()">帮助</a>
			</div>
			<table id="scanUsepublicBusinessListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/publicBusiness/jsonPagination?scanType=use',
					method:'post',fit:true,pagination:true,toolbar:'#scanUsepublicBusinessListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
																		<th data-options="field:'groupDepartName',align:'left',resizable:true,sortable:true" width="200px">组团单位</th>
																								<th data-options="field:'approvalNo',align:'center',resizable:false,sortable:false" width="100px">批件号</th>
																								<th data-options="field:'planExitTime',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">计划出境日期</th>
																								<th data-options="field:'planEnterTime',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">计划入境日期</th>
																								<th data-options="field:'applyUserName',align:'center',resizable:false,sortable:false" width="80px">领用人姓名</th>
																								<th data-options="field:'applyUserDepartName',align:'center',resizable:false,sortable:false" width="200px">领用人单位</th>
																								<th data-options="field:'applyUserPhone',align:'center',resizable:false,sortable:false" width="120px">领用人电话</th>
																								<th data-options="field:'applyDate',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">计划领用日期</th>
<!-- 																								<th data-options="field:'realExitTime',align:'center',resizable:false,sortable:false" width="10%" formatter="ChangeDateFormat2">实际出境日期</th> -->
<!-- 																								<th data-options="field:'realEnterTime',align:'center',resizable:false,sortable:false" width="10%" formatter="ChangeDateFormat2">实际入境日期</th> -->
<!-- 																								<th data-options="field:'realVisitCountry',align:'center',resizable:false,sortable:false" width="10%">实际出访国家地区(含经停城市)</th> -->
<!-- 																								<th data-options="field:'hasViolationSituation',align:'center',resizable:false,sortable:false" width="10%">有无超天数超国家等违规情况</th> -->
<!-- 																								<th data-options="field:'backUserName',align:'center',resizable:false,sortable:false" width="10%">归还人姓名</th> -->
<!-- 																								<th data-options="field:'backUserDepartName',align:'center',resizable:false,sortable:false" width="10%">归还人单位</th> -->
<!-- 																								<th data-options="field:'backUserPhone',align:'center',resizable:false,sortable:false" width="10%">归还人电话</th> -->
<!-- 																								<th data-options="field:'backDate',align:'center',resizable:false,sortable:false" width="10%" formatter="ChangeDateFormat2">归还日期</th> -->
																								<th data-options="field:'useStatus',align:'center',resizable:false,sortable:false" width="90px">状态</th>
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	<form action="" id="exportConfirmRegisterForm"></form>
	<script type="text/javascript">
		function scanUsequeryPublicBusinessList(){
			 $('#scanUsepublicBusinessListDg').datagrid('load',{
			 				 									groupDepartId:$('#scanUsegroupDepartIdPublicBusinessList').textbox('getValue'),
															 									approvalNo:$('#scanUseapprovalNoPublicBusinessList').textbox('getValue')
															 								 								 								 								 								 								 								 								 								 								 								 								 								 								 								}); 
		} 

		function useScan(){
			var win=creatWin('证照领用-扫描',780,650,'icon-search','/publicBusiness/useScan');
		    win.window('open');
		}
		
		function viewScanPublicBusiness(){
			var row = $('#scanUsepublicBusinessListDg').datagrid('getSelected');
			var selections = $('#scanUsepublicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-证照待领用',780,650,'icon-search','/publicBusiness/view/'+row.id+"?type=use");
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function editScanCertificate(){
			var row = $('#scanUsepublicBusinessListDg').datagrid('getSelected');
			var selections = $('#scanUsepublicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('添加证照-证照待领用',780,650,'icon-search','/publicBusiness/editUse/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}
		
		function helpPublicBusinessScanUseRegister(){
			window.open("./resource/onlinehelp/yingong/zzdly/help.html");
		}
	</script>
</body>
</html>

