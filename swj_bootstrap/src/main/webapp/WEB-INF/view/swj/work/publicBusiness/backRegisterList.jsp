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
			<form id="backpublicBusinessListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					  			<th class="br" width="90px;">
									组团单位: 
								</th>
								<td class="br" width="180px;">
									<input class="easyui-textbox" style="width:170px" id="backgroupDepartIdPublicBusinessList"></input>
								</td>
					  			<th class="br" width="90px;">
									批件号: 
								</th>
								<td class="br" width="180px;">
									<input class="easyui-textbox" style="width:170px" id="backapprovalNoPublicBusinessList"></input>
								</td>
								<th class="br" width="65px;">
									状态: 
								</th>
								<td class="br" width="80px;">
					  			<c:choose>
								    <c:when test="${isConfirmer}=='true'">
								       <input id="backStatusPublicBusinessList" class="easyui-combobox"  style="width:140px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[{ 'text': '归还待确认', 'id': '归还待确认' },{ 'text': '待归还', 'id': '待归还' } ,{ 'text': '归还完成', 'id': '归还完成' }] "></input>
								    </c:when>
								    <c:otherwise>
								         <input id="backStatusPublicBusinessList" class="easyui-combobox"  style="width:140px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[{ 'text': '草稿', 'id': '归还草稿' },{ 'text': '归还待确认', 'id': '归还待确认' },{ 'text': '待归还', 'id': '待归还' } ,{ 'text': '归还完成', 'id': '归还完成' }]"></input>
								    </c:otherwise>
								</c:choose>	
								</td>
								<td class="br" >
								<a style="margin-left: 30px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="backqueryPublicBusinessList()">查询</a>&nbsp;&nbsp;
					</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="backpublicBusinessListTb" style="padding:2px 5px;">
				<gwideal:perm url="/publicBusiness/editBackRegister/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editBackPublicBusiness()">修改</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/publicBusiness/confirmBackRegister/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-backConfirm32',size:'large',iconAlign:'left'" onclick="confirmBackRegister()">归还表确认</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewBackPublicBusiness()">查看</a>&nbsp;
				<gwideal:perm url="/publicBusiness/exportBackRegister/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exports32',size:'large',iconAlign:'left'" onclick="exportBackPublicBusiness()">导出</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpPublicBusinessBackRegister()">帮助</a>
			</div>
			<table id="backpublicBusinessListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/publicBusiness/jsonPagination?businessType=back',
					method:'post',fit:true,pagination:true,toolbar:'#backpublicBusinessListTb',singleSelect: true,
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
																								<th data-options="field:'backUserDepartName',align:'center',resizable:false,sortable:false" width="150px">归还人单位</th>
																								<th data-options="field:'backUserPhone',align:'center',resizable:false,sortable:false" width="120px">归还人电话</th>
																								<th data-options="field:'backDate',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">计划归还日期</th>
																								<th data-options="field:'backStatus',align:'center',resizable:false,sortable:false" width="90px" formatter="showPublicBusiness">状态</th>
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	<form action="" id="exportBackRegisterForm"></form>
	<script type="text/javascript">
		function backqueryPublicBusinessList(){
			 $('#backpublicBusinessListDg').datagrid('load',{
			 				 									groupDepartId:$('#backgroupDepartIdPublicBusinessList').textbox('getValue'),
															 									approvalNo:$('#backapprovalNoPublicBusinessList').textbox('getValue'),
															 									backStatus:$('#backStatusPublicBusinessList').combobox('getValue')
															 								 								 								 								 								 								 								 								 								 								 								 								 								 								 								}); 
		} 

		function editBackPublicBusiness(){
			var row = $('#backpublicBusinessListDg').datagrid('getSelected');
			var selections = $('#backpublicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.backStatus!='归还草稿'){
					 $.messager.alert('系统提示','非归还草稿状态无法修改！','info');
					 return false;
				}
			    var win=creatWin('修改-归还登记',780,650,'icon-edit','/publicBusiness/editBackRegister/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}

		function viewBackPublicBusiness(){
			var row = $('#backpublicBusinessListDg').datagrid('getSelected');
			var selections = $('#backpublicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-信息',780,650,'icon-search','/publicBusiness/view/'+row.id+"?type=back");
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function exportBackPublicBusiness(){
			var row = $('#backpublicBusinessListDg').datagrid('getSelected');
			var selections = $('#backpublicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				$.messager.confirm('系统提示','确认导出吗?',function(id){ 
					if(id){ 
						var exportForm = document.getElementById("exportBackRegisterForm");
						exportForm.setAttribute("action",base+'/publicBusiness/exportBackRegister/'+row.id);
						exportForm.submit();
					} 
				}); 
			}else{
				$.messager.alert('系统提示','请选择一条要导出的数据！','info');
			}
		}
		
		function confirmBackRegister(){
			var row = $('#backpublicBusinessListDg').datagrid('getSelected');
			var selections = $('#backpublicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.backStatus!='归还待确认'){
					 $.messager.alert('系统提示','非归还待确认状态无法进行归还确认！','info');
					 return false;
				}
				var win=creatWin('归还信息-确认',780,650,'icon-search','/publicBusiness/confirmBackRegister/'+row.id);
				win.window('open');		
			}else{
				$.messager.alert('系统提示','请选择一条要确认的数据！','info');
			}
		}
		function helpPublicBusinessBackRegister(){
			window.open("./resource/onlinehelp/yingong/zzghxxb/help.html");
		}
	</script>
</body>
</html>

