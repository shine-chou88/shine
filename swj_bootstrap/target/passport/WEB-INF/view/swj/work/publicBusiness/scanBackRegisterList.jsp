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
			<form id="scanBackpublicBusinessListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					  						  		<th class="br" width="90px;">
									组团单位: 
								</th>
								<td class="br" width="180px;">
									<input class="easyui-textbox" style="width:170px" id="scanBackgroupDepartIdPublicBusinessList"></input>
								</td>
					  							  						  		<th class="br" width="90px;">
									批件号: 
								</th>
								<td class="br" width="180px;">
									<input class="easyui-textbox" style="width:170px" id="scanBackapprovalNoPublicBusinessList"></input>
								</td>
					  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  						  							  							<td class="br">
							<a style="margin-left: 30px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="scanBackqueryPublicBusinessList()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="scanBackpublicBusinessListTb" style="padding:2px 5px;">
				<gwideal:perm url="/publicBusiness/backScan">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-backScan32',size:'large',iconAlign:'left'" onclick="backScan()">归还扫描</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewScanBackPublicBusiness()">查看</a>&nbsp;
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpPublicBusinessScanBackRegister()">帮助</a>
			</div>
			<table id="scanBackpublicBusinessListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/publicBusiness/jsonPagination?scanType=back',
					method:'post',fit:true,pagination:true,toolbar:'#scanBackpublicBusinessListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
																		<th data-options="field:'groupDepartName',align:'left',resizable:true,sortable:true" width="200px">组团单位</th>
																								<th data-options="field:'approvalNo',align:'center',resizable:false,sortable:false" width="100px">批件号</th>
<!-- 																								<th data-options="field:'planExitTime',align:'center',resizable:false,sortable:false" width="140px" formatter="ChangeDateFormat2">计划出境日期</th> -->
<!-- 																								<th data-options="field:'planEnterTime',align:'center',resizable:false,sortable:false" width="140px" formatter="ChangeDateFormat2">计划入境日期</th> -->
<!-- 																								<th data-options="field:'applyUserDepartName',align:'center',resizable:false,sortable:false" width="200px">领用人单位</th> -->
<!-- 																								<th data-options="field:'applyUserPhone',align:'center',resizable:false,sortable:false" width="150px">领用人电话</th> -->
<!-- 																								<th data-options="field:'applyDate',align:'center',resizable:false,sortable:false" width="140px" formatter="ChangeDateFormat2">计划领用日期</th> -->
																								<th data-options="field:'realExitTime',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">实际出境日期</th>
																								<th data-options="field:'realEnterTime',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">实际入境日期</th>
<!-- 																								<th data-options="field:'realVisitCountry',align:'center',resizable:false,sortable:false" width="250px">实际出访国家地区(含经停城市)</th> -->
<!-- 																								<th data-options="field:'hasViolationSituation',align:'center',resizable:false,sortable:false" width="250px">有无超天数超国家等违规情况</th> -->
																								<th data-options="field:'applyUserName',align:'center',resizable:false,sortable:false" width="80px">领用人姓名</th>
																								<th data-options="field:'backUserName',align:'center',resizable:false,sortable:false" width="80px">归还人姓名</th>
																								<th data-options="field:'backUserDepartName',align:'center',resizable:false,sortable:false" width="200px">归还人单位</th>
																								<th data-options="field:'backUserPhone',align:'center',resizable:false,sortable:false" width="120px">归还人电话</th>
																								<th data-options="field:'backDate',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">计划归还日期</th>
																								<th data-options="field:'backStatus',align:'center',resizable:false,sortable:false" width="90px">状态</th>
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function scanBackqueryPublicBusinessList(){
			 $('#scanBackpublicBusinessListDg').datagrid('load',{
			 				 									groupDepartId:$('#scanBackgroupDepartIdPublicBusinessList').textbox('getValue'),
															 									approvalNo:$('#scanBackapprovalNoPublicBusinessList').textbox('getValue')
															 								 								 								 								 								 								 								 								 								 								 								 								 								 								 								}); 
		} 

		function backScan(){
			var win=creatWin('证照归还-扫描',780,650,'icon-search','/publicBusiness/backScan');
		    win.window('open');
		}
		
		function viewScanBackPublicBusiness(){
			var row = $('#scanBackpublicBusinessListDg').datagrid('getSelected');
			var selections = $('#scanBackpublicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-证照待归还',780,650,'icon-search','/publicBusiness/view/'+row.id+"?type=back");
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function helpPublicBusinessScanBackRegister(){
			window.open("./resource/onlinehelp/yingong/zzdgh/help.html");
		}
	</script>
</body>
</html>

