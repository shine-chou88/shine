<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
	<script type="text/javascript">
		function complete(){
			var row = $('#activiti_task_dg').datagrid('getSelected');
			var selections = $('#activiti_task_dg').datagrid('getSelections');
			if(null!=row && selections.length==1){
				var win=null;
				var processDefinitionKey=row.processDefinitionKey;
				var actId=row.actId;
				if(row.processDefinitionName.indexOf('因私')>=0){
					if(actId=='DRAF'){//直接跳转到对应业务的填报页面
						if(processDefinitionKey=='DEPUTY_CADRES_PASSPORT_APPLY' || processDefinitionKey=='OFFICAL_CADRES_PASSPORT_APPLY'){
							 win=creatWin('修改-因私出国(境)申请',880,600,'icon-edit','/certificateInfoApply/edit/'+row.businessKey);
						}					
					}else{
						if(row.name=='申请人扫码领用护照'){
							 $.messager.alert('系统提示','请前往组织人事处工作人员处领用！','info');
							//win=creatWin('证照领用-扫描',780,650,'icon-search','/certificateInfoApply/useScan?todoType=unRead');
						}else if(row.name=='申请人扫码归还护照'){
							 $.messager.alert('系统提示','请前往组织人事处工作人员处归还！','info');
							//win=creatWin('证照归还-扫描',780,650,'icon-search','/certificateInfoApply/backScan?todoType=unRead');
						}else{
							win=creatWin('审批',1050,600,'icon-edit','/activiti/process/toComplete/'+row.id);
						}
					}
				}else if(row.processDefinitionName=='因公出国（境）证照领用信息表'){
						win=creatWin('确认-因公出国（境）证照领用信息表',780,650,'icon-search','/publicBusiness/confirmUseRegister/'+row.id+"?todoType=unRead");
				}else if(row.processDefinitionName=='因公出国（境）证照归还信息表'){
						win=creatWin('确认-因公出国（境）证照归还信息表',780,650,'icon-search','/publicBusiness/confirmBackRegister/'+row.id+"?todoType=unRead");
				}
				if(win!=null){
				    win.window('open');
				}    
			}else{
				 $.messager.alert('系统提示','请选择一条要操作的数据！','info');
			}
		}
		function showTaskTitle(value, row, index){
			return '<a style="text-decoration:underline;color:blue;" href="javascript:void(0);" class="l s1" onclick="completeCurrentTask(\''+row.id+'\',\''+row.name+'\',\''+row.processDefinitionName+'\',\''+row.actId+'\',\''+row.processDefinitionKey+'\',\''+row.businessKey+'\');">'+row.title+'</a>';
		}
		function completeCurrentTask(id,name,processDefinitionName,actId,processDefinitionKey,businessKey){
			var win=null;
			if(processDefinitionName.indexOf('因私')>=0){
				if(actId=='DRAF'){//直接跳转到对应业务的填报页面
					if(processDefinitionKey=='DEPUTY_CADRES_PASSPORT_APPLY' || processDefinitionKey=='OFFICAL_CADRES_PASSPORT_APPLY'){
						 win=creatWin('修改-因私出国(境)申请',880,600,'icon-edit','/certificateInfoApply/edit/'+businessKey);
					}					
				}else{
					if(name=='申请人扫码领用护照'){
						$.messager.alert('系统提示','请前往组织人事处工作人员处扫码领用！','info');
					}else if(name=='申请人扫码归还护照'){
						$.messager.alert('系统提示','请前往组织人事处工作人员处扫码归还！','info');
					}else if(name=='申请人扫码领回旧护照'){
						$.messager.alert('系统提示','请前往组织人事处工作人员处扫码领回！','info');
					}else{
						win=creatWin('审批',1050,600,'icon-edit','/activiti/process/toComplete/'+id);
					}
				}
			}else if(processDefinitionName=='因公出国（境）证照领用信息表'){
					win=creatWin('确认-因公出国（境）证照领用信息表',780,650,'icon-search','/publicBusiness/confirmUseRegister/'+id+"?todoType=unRead");
			}else if(processDefinitionName=='因公出国（境）证照归还信息表'){
					win=creatWin('确认-因公出国（境）证照归还信息表',780,650,'icon-search','/publicBusiness/confirmBackRegister/'+id+"?todoType=unRead");
			}
			if(win!=null){
			    win.window('open');
			}    
		}
	</script>
	<div id="activiti_task_tb" style="padding:2px 5px 2px 5px;">
		&nbsp;
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-approve32',size:'large',iconAlign:'left'" onclick="complete();">审批</a> -->
		<c:if test="${!empty baoxin && baoxin == 'index'}">
			<a href="${base}/index.do" target="_blank" style="text-decoration: underline;color: blue;position: absolute;right:50px;font-size:16px;">出国（境）管理系统->首页入口</a>
		</c:if>
	</div>

	<table id="activiti_task_dg" class="easyui-datagrid" data-options="singleSelect:true,collapsible:true,url:'${base}/activiti/process/taskListJsonPagination',
			method:'post',fit:true,toolbar:'#activiti_task_tb',singleSelect: true,selectOnCheck: true,checkOnSelect: true,remoteSort:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'actId',hidden:true"></th>
				<th data-options="field:'businessKey',hidden:true"></th>
				<th data-options="field:'processDefinitionKey',hidden:true"></th>
				<th data-options="field:'id',align:'left',resizable:false,sortable:true,hidden:true">ID</th>
				<th data-options="field:'title',align:'left',resizable:false,sortable:true" width="15%" nowrap="nowrap" formatter="showTaskTitle">标题</th>
				<th data-options="field:'processDefinitionName',align:'center',resizable:false,sortable:true" width="20%" nowrap="nowrap">类型</th>
				<th data-options="field:'name',align:'center',resizable:false,sortable:true" width="17%">当前节点</th>
				<th data-options="field:'lastName',align:'center',resizable:false,sortable:true" width="18%">上一节点</th>
				<th data-options="field:'lastAssigneeName',align:'center',resizable:false,sortable:true" width="10%">上一节点执行人</th>
				<th data-options="field:'lastEndTime',align:'center',resizable:false,sortable:true" width="15%">上一节点执行时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>

