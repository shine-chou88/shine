<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<title>${title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'north'" border="false" style="margin-bottom: 5px;">
			<form id="certificateInfoApplyListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					  	<th class="br" width="90px;">
							申请编号: 
						</th>
						<td class="br" width="180px;">
							<input class="easyui-textbox" style="width:170px" id="wcodeCertificateInfoApplyList"></input>
						</td>
						<th class="br" width="90px;">
							申请时间: 
						</th>
						<td class="br" width="340px;">
							<input type="text" readonly="readonly" id="applyStartTime"  class="Wdate"  data-options="required:true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'applyEndTime\')}'})" style="width:145px" ></input>
        				至&nbsp;<input type="text" readonly="readonly" id="applyEndTime" class="Wdate" data-options="required:true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'applyStartTime\')}'})" style="width:145px" ></input>
						</td>
						<th class="br" width="45px;">
							状态：
						</th>
						<td class="br" width="105px;">
							<select class="easyui-combobox" style="width: 100px;" id="statusCertificateInfoApplyList">
								<option value="">--请选择--</option>
								<option value="draft">草稿</option>
								<option value="toApprove">待审批</option>
								<option value="approved">已通过</option>
								<option value="rejected">不通过</option>
								<option value="returned">已退回</option>
								<option value="archived">已归档</option>
								<option value="canceled">取消</option>
							</select>
						</td>
						<td class="br">
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryCertificateInfoApplyList()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="certificateInfoApplyListTb" style="padding:2px 5px;">
				<gwideal:perm url="/certificateInfoApply/add">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addCertificateInfoApply()">填表</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfoApply/edit/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editCertificateInfoApply()">修改</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfoApply/delete/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deleteCertificateInfoApply()">删除</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfoApply/genDocLetter/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-genDoc32',size:'large',iconAlign:'left'" onclick="genCertificateInfoApplyLetter()">生成申办文件函</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfoApply/genApprovedDetailInfo/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-genDoc32',size:'large',iconAlign:'left'" onclick="genCertificateInfoApplyDoc()">生成审批表</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewCertificateInfoApply()">查看</a>&nbsp;
				<gwideal:perm url="/certificateInfoApply/cancel/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel32',size:'large',iconAlign:'left'" onclick="cancelCertificateInfoApply()">取消</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpCertificateApply()">帮助</a>&nbsp;
				<a href="${base}/resource/download/user_manual_private_v1.0.pdf" style="text-decoration: underline;color: blue;">操作手册</a>
			</div>
			<table id="certificateInfoApplyListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/certificateInfoApply/jsonPagination',
					method:'post',fit:true,pagination:true,toolbar:'#certificateInfoApplyListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'auditStatus',hidden:true"></th>
						<th data-options="field:'processInstanceId',hidden:true"></th>
						<th data-options="field:'creatorId',hidden:true"></th>
						<th data-options="field:'wcode',align:'left',resizable:true,sortable:true" width="130px">申请编号</th>
						<th data-options="field:'startDate',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">开始时间</th>
						<th data-options="field:'endDate',align:'center',resizable:false,sortable:false" width="100px" formatter="ChangeDateFormat2">结束时间</th>
						<th data-options="field:'reason',align:'center',resizable:false,sortable:false" width="140px">原因</th>
						<th data-options="field:'destination',align:'center',resizable:false,sortable:false" width="100px">目的地</th>
						<th data-options="field:'itinerary',align:'center',resizable:false,sortable:false" width="150px">行程路线</th>
						<th data-options="field:'applyUseType',align:'center',resizable:false,sortable:false" formatter="applyTypeFormat" width="80px">申请类型</th>
						<th data-options="field:'auditStatusStr',align:'center',resizable:false,sortable:false" width="100px">状态</th>
						<th data-options="field:'creatorName',align:'center',resizable:false,sortable:false" width="100px">申请人</th>
						<th data-options="field:'applyTime',align:'center',resizable:false,sortable:false" width="140px" formatter="dateFormatYYYYMMDDHHmm">申请时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<form action="" id="genDocForm"></form>
	<script type="text/javascript">
		function queryCertificateInfoApplyList(){
			 $('#certificateInfoApplyListDg').datagrid('load',{
			 	wcode:$('#wcodeCertificateInfoApplyList').textbox('getValue'),
			 	applyStartTime:$("#applyStartTime").val(),
			 	applyEndTime:$("#applyEndTime").val(),
			 	auditStatus:$("#statusCertificateInfoApplyList").textbox('getValue')
			 }); 
		} 

		function applyTypeFormat(value){
			if(null!=value && value.length>0){
				return "领用";
			}else{
				return "办理";
			}
		}
		
		function addCertificateInfoApply(){
			$.messager.confirm({
				width: 460, 
				title: '系统提示', 
				msg: '<span style="font-size: 18px;">1.涉密人员同步在局办公室办理涉密审批手续；<br>2.主要领导在局办公室办理外出报告。<span>', 
				fn: function (r){
					if(r){
						var win=creatWin('填表-因私出国（境）申请',880,600,'icon-add','/certificateInfoApply/add');
						win.window('open');
					}
				}
			});
		}
		
		function editCertificateInfoApply(){
			var row = $('#certificateInfoApplyListDg').datagrid('getSelected');
			var selections = $('#certificateInfoApplyListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				var auditStatus=row.auditStatus;
				if(auditStatus=='draft' || auditStatus=='returned'){
					if(row.creatorId=='${currentUser.id}'){
						if(auditStatus=='returned'){
							$.ajax({ 
								type: 'POST', 
								url: base+'/certificateInfoApply/canCancelOrEdit/'+row.id,
								dataType: 'json',  
								success: function(data){
									if(data.success){
										var win=creatWin('修改-因私出国（境）申请',880,600,'icon-edit','/certificateInfoApply/edit/'+row.id);
									    win.window('open');
									}else{ 
										$.messager.alert('系统提示', data.info, 'error');
									} 
								} 
							});
						}else{
							var win=creatWin('修改-因私出国（境）申请',880,600,'icon-edit','/certificateInfoApply/edit/'+row.id);
						    win.window('open');
						}
					}else{
						$.messager.alert('系统提示','只能操作自己的数据！','info');
					}
				}else{
					$.messager.alert('系统提示','只有草稿和已退回状态下的数据可以修改！','info');
				}
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}

		function viewCertificateInfoApply(){
			var row = $('#certificateInfoApplyListDg').datagrid('getSelected');
			var selections = $('#certificateInfoApplyListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-因私出国（境）申请',880,600,'icon-search','/certificateInfoApply/view/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function deleteCertificateInfoApply(){
			var row = $('#certificateInfoApplyListDg').datagrid('getSelected');
			var selections = $('#certificateInfoApplyListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				var auditStatus=row.auditStatus;
				if(auditStatus=='draft'){
					if(row.creatorId=='${currentUser.id}'){
						$.messager.confirm('系统提示','确认删除吗?',function(id){ 
							if(id){ 
								$.ajax({ 
									type: 'POST', 
									url: base+'/certificateInfoApply/delete/'+row.id,
									dataType: 'json',  
									success: function(data){
										if(data.success){
											$.messager.alert('系统提示', data.info, 'info');
											$("#certificateInfoApplyListDg").datagrid('reload'); 
										}else{ 
											$.messager.alert('系统提示', data.info, 'error');
										} 
									} 
								}); 
							} 
						}); 		
					}else{
						$.messager.alert('系统提示','只能操作自己的数据！','info');
					}
				}else{
					$.messager.alert('系统提示','只有草稿状态下的数据可以删除！','info');
				}
			}else{
				$.messager.alert('系统提示','请选择一条要删除的数据！','info');
			}
		}
		
		function cancelCertificateInfoApply(){
			var row = $('#certificateInfoApplyListDg').datagrid('getSelected');
			var selections = $('#certificateInfoApplyListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				var auditStatus=row.auditStatus;
				if(auditStatus=='returned'){
					if(row.creatorId=='${currentUser.id}'){
						$.ajax({ 
							type: 'POST', 
							url: base+'/certificateInfoApply/canCancelOrEdit/'+row.id,
							dataType: 'json',  
							success: function(data){
								if(data.success){
									var win=creatWin('取消-因私出国（境）申请',620,200,'icon-edit','/certificateInfoApply/toCancel/'+row.id);
								    win.window('open');
								}else{ 
									$.messager.alert('系统提示', data.info, 'error');
								} 
							} 
						}); 
					}else{
						$.messager.alert('系统提示','只能操作自己的数据！','info');
					}
				}else{
					$.messager.alert('系统提示','只有已退回状态下的数据可以取消！','info');
				}
			}else{
				$.messager.alert('系统提示','请选择一条要取消的数据！','info');
			}
		}
function genCertificateInfoApplyDoc(){
	var row = $('#certificateInfoApplyListDg').datagrid('getSelected');
	var selections = $('#certificateInfoApplyListDg').datagrid('getSelections');
	if(row!=null && selections.length==1){
		if(row.auditStatusStr!='已通过'&&row.auditStatusStr!='已归档'){
			$.messager.alert('系统提示','只能导出审批通过的数据！','info');
			return false;
		}
		$.messager.confirm('系统提示','确认生成吗?',function(id){ 
			if(id){ 
				var exportForm = document.getElementById("genDocForm");
				exportForm.setAttribute("action",base+'/certificateInfoApply/genApprovedDetailInfo/'+row.id);
				exportForm.submit();
			} 
		}); 
	}else{
		$.messager.alert('系统提示','请选择一条要生成审批表的数据！','info');
	}
}
function genCertificateInfoApplyLetter(){
	var row = $('#certificateInfoApplyListDg').datagrid('getSelected');
	var selections = $('#certificateInfoApplyListDg').datagrid('getSelections');
	if(row!=null && selections.length==1){
		if(row.auditStatusStr!='已通过'&&row.auditStatusStr!='已归档'){
			$.messager.alert('系统提示','只能导出审批通过的数据！','info');
			return false;
		}
		$.messager.confirm('系统提示','确认生成吗?',function(id){ 
			if(id){ 
				var exportForm = document.getElementById("genDocForm");
				exportForm.setAttribute("action",base+'/certificateInfoApply/genDocLetter/'+row.id);
				exportForm.submit();
			} 
		}); 
	}else{
		$.messager.alert('系统提示','请选择一条要生成申办文件函的数据！','info');
	}
}

		function helpCertificateApply(){
			window.open("./resource/onlinehelp/yingsi/sqsp/help.html");
		}
	</script>
</body>
</html>

