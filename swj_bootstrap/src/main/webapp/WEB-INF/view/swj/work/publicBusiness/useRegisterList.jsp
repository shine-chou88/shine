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
			<form id="publicBusinessListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					  						  		<th class="br" width="90px;">
									组团单位: 
								</th>
								<td class="br" width="180px;">
									<input class="easyui-textbox" style="width:170px" id="groupDepartIdPublicBusinessList"></input>
								</td>
					  							  						  		<th class="br" width="90px;">
									批件号: 
								</th>
								<td class="br" width="180px;">
									<input class="easyui-textbox" style="width:170px" id="approvalNoPublicBusinessList"></input>
								</td>
					  		<th class="br" width="65px;">
									状态: 
								</th>
								<td class="br" width="80px;">
								<c:choose>
								    <c:when test="${isConfirmer}=='true'">
								       <input id="useStatusPublicBusinessList" class="easyui-combobox"  style="width:140px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[{ 'text': '领用待确认', 'id': '领用待确认' },{ 'text': '待领用', 'id': '待领用' } ,{ 'text': '领用完成', 'id': '领用完成' }] "></input>
								    </c:when>
								    <c:otherwise>
								         <input id="useStatusPublicBusinessList" class="easyui-combobox"  style="width:140px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[{ 'text': '草稿', 'id': '领用草稿' },{ 'text': '领用待确认', 'id': '领用待确认' },{ 'text': '待领用', 'id': '待领用' } ,{ 'text': '领用完成', 'id': '领用完成' }]"></input>
								    </c:otherwise>
								</c:choose>
								</td>
								<td class="br">	
							<a style="margin-left: 30px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryPublicBusinessList()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="publicBusinessListTb" style="padding:2px 5px;">
				<gwideal:perm url="/publicBusiness/useRegister">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="usePublicBusiness()" >新增</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/publicBusiness/editUseRegister/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editPublicBusiness()">修改</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/publicBusiness/delete/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deletePublicBusiness()">删除</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/publicBusiness/backRegister/{id}">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-back32',size:'large',iconAlign:'left'" onclick="backPublicBusiness()" >归还</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/publicBusiness/confirmUseRegister/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-useConfirm32',size:'large',iconAlign:'left'" onclick="confirmUseRegister()">领用表确认</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewPublicBusiness()">查看</a>&nbsp;
				<gwideal:perm url="/publicBusiness/exportUseRegister/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exports32',size:'large',iconAlign:'left'" onclick="exportPublicBusiness()">导出</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpPublicBusinessUseRegister()">帮助</a>&nbsp;
				<a href="${base}/resource/download/user_manual_public_v1.0.pdf" style="text-decoration: underline;color: blue;">操作手册</a>
			</div>
			<table id="publicBusinessListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/publicBusiness/jsonPagination?businessType=use',
					method:'post',fit:true,pagination:true,toolbar:'#publicBusinessListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'backStatus',hidden:true"></th>
																		<th data-options="field:'groupDepartName',align:'left',resizable:true,sortable:true" width="200px">组团单位</th>
																								<th data-options="field:'approvalNo',align:'center',resizable:false,sortable:false" width="100px">批件号</th>
																								<th data-options="field:'planExitTime',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">计划出境日期</th>
																								<th data-options="field:'planEnterTime',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">计划入境日期</th>
																								<th data-options="field:'applyUserName',align:'center',resizable:false,sortable:false" width="100px">领用人姓名</th>
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
																								<th data-options="field:'useStatus',align:'center',resizable:false,sortable:false" width="90px" formatter="showPublicBusiness">状态</th>
																								
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	<form action="" id="exportUseRegisterForm"></form>
	<script type="text/javascript">
		function queryPublicBusinessList(){
			 $('#publicBusinessListDg').datagrid('load',{
			 				 									groupDepartId:$('#groupDepartIdPublicBusinessList').textbox('getValue'),
															 									approvalNo:$('#approvalNoPublicBusinessList').textbox('getValue'),
															 									useStatus:$('#useStatusPublicBusinessList').combobox('getValue')
															 								
															 								 								 								 								 								 								 								 								 								 								 								 								 								 								 								}); 
		} 

		function usePublicBusiness(){
		    var win=creatWin('新增-证照领用信息表',780,650,'icon-add','/publicBusiness/useRegister');
		    win.window('open');
		}
		
		function editPublicBusiness(){
			var row = $('#publicBusinessListDg').datagrid('getSelected');
			var selections = $('#publicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.useStatus!='领用草稿'){
					 $.messager.alert('系统提示','只能修改草稿单据！','info');
					 return false;
				}
			    var win=creatWin('修改-证照领用信息表',780,650,'icon-edit','/publicBusiness/editUseRegister/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}

		function viewPublicBusiness(){
			var row = $('#publicBusinessListDg').datagrid('getSelected');
			var selections = $('#publicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-证照领用信息表',780,650,'icon-search','/publicBusiness/view/'+row.id+'?type=use');
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function deletePublicBusiness(){
			var row = $('#publicBusinessListDg').datagrid('getSelected');
			var selections = $('#publicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.useStatus!='领用草稿'){
					 $.messager.alert('系统提示','非草稿状态无法删除！','info');
					 return false;
				}
				$.messager.confirm('系统提示','确认删除吗?',function(id){ 
					if(id){ 
						$.ajax({ 
							type: 'POST', 
							url: base+'/publicBusiness/delete/'+row.id,
							dataType: 'json',  
							success: function(data){
								if(data.success){
									$.messager.alert('系统提示', data.info, 'info');
									$("#publicBusinessListDg").datagrid('reload'); 
								}else{ 
									$.messager.alert('系统提示', data.info, 'info');
								} 
							} 
						}); 
					} 
				}); 		
			}else{
				$.messager.alert('系统提示','请选择一条要删除的数据！','info');
			}
		}

		function exportPublicBusiness(){
			var row = $('#publicBusinessListDg').datagrid('getSelected');
			var selections = $('#publicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				$.messager.confirm('系统提示','确认导出吗?',function(id){ 
					if(id){ 
						var exportForm = document.getElementById("exportUseRegisterForm");
						exportForm.setAttribute("action",base+'/publicBusiness/exportUseRegister/'+row.id);
						exportForm.submit();
					} 
				}); 
			}else{
				$.messager.alert('系统提示','请选择一条要导出的数据！','info');
			}
		}
		
		function backPublicBusiness(){
			var row = $('#publicBusinessListDg').datagrid('getSelected');
			var selections = $('#publicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.useStatus!='领用完成'){
					 $.messager.alert('系统提示','只有领用完成状态数据才能发起归还登记！','info');
					 return false;
				}
				if(row.backStatus!=''&&row.backStatus!=null){
					$.messager.alert('系统提示','您已经发起过归还登记，请到归还信息表菜单进行操作！','info');
					 return false;
				}
			    var win=creatWin('编辑-证照归还信息表',780,760,'icon-edit','/publicBusiness/backRegister/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要归还的数据！','info');
			}
		   
		}
		
		function confirmUseRegister(){
			var row = $('#publicBusinessListDg').datagrid('getSelected');
			var selections = $('#publicBusinessListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.useStatus!='领用待确认'){
					 $.messager.alert('系统提示','非领用待确认状态无法进行领用确认！','info');
					 return false;
				}
				var win=creatWin('确认-证照领用信息表',780,650,'icon-search','/publicBusiness/confirmUseRegister/'+row.id);
				win.window('open');
			}else{
				$.messager.alert('系统提示','请选择一条要确认的数据！','info');
			}
		}
		
		function helpPublicBusinessUseRegister(){
			window.open("./resource/onlinehelp/yingong/zzlyxxb/help.html");
		}
	</script>
</body>
</html>

