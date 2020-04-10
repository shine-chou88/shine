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
			<form id="${cancelType}_certificateCancelInfoPublicListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
						<th class="br">姓名: </th>
						<td class="br">
							<input class="easyui-textbox" style="width:70px" id="${cancelType}_nameCertificateInfoPublicListCancel"></input>
						</td>
						<th class="br" width="65px;">所在单位: </th>
						<td class="br" width="200px;">
							<input id="${cancelType}_departCertificateInfoPublicListCancel" class="easyui-combobox"  style="width:200px;" data-options="valueField:'id',textField:'text',url:'${base}/depart/certificateDepartJson'"></input>
						</td>
			  			<th class="br" width="65px;">证照类型: </th>
						<td class="br" width="160px;">
							<input id="${cancelType}_certificateTypeCertificateInfoPublicListCancel" class="easyui-combobox" name="certificateType" style="width:160px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'publicPassport','text':'因公普通护照'},{'id':'publicHKAndMacaoPass','text':'因公往来香港澳门特别行政区通行证'},{'id':'publicTaiwanPass','text':'大陆居民往来台湾通行证'}]"></input>
						</td>
			  			<th class="br" width="110px;">证件号码/护照号: </th>
						<td class="br">
							<input class="easyui-textbox" style="width:170px" id="${cancelType}_zjhmCertificateInfoPublicListCancel"></input>
						</td>
					</tr>
					<tr>
						<th class="br" width="80px;">身份证号码: </th>
						<td class="br" width="160px;">
							<input class="easyui-textbox" style="width:160px;" id="${cancelType}_sfzhmCertificateInfoPublicListCancel"></input>
						</td>	
						<td  class="br" colspan="6">
							<a style="margin-left: 10px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryCertificateInfoPublicListCancel${cancelType}()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="${cancelType}_certificateInfoPublicListTbCancel" style="padding:2px 5px;">
			<gwideal:perm url="/certificateInfo/overdueUpdate">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editCertificateInfoPublic${cancelType}()">修改</a>&nbsp;
			</gwideal:perm>
				<gwideal:perm url="/certificateInfo/cancelWaitPublic/{id}">
					<c:if test="${cancelType=='wait'}">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-zx32',size:'large',iconAlign:'left'" onclick="cancelCertificateInfoPublic${cancelType}()">注销</a>&nbsp;
					</c:if>
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewCertificateCancelInfoPublic${cancelType}()">查看</a>
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpCertificatePublicCancel${cancelType}()">帮助</a>
			</div>
			<table id="${cancelType}_certificateInfoPublicListDgCancel" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/certificateInfo/jsonPagination?status=${cancelType}&businessType=public',
					method:'post',fit:true,pagination:true,toolbar:'#${cancelType}_certificateInfoPublicListTbCancel',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
																		<th data-options="field:'certificateTypeShow',align:'left',resizable:true,sortable:true" width="230px">证照类型</th>
																								<th data-options="field:'zjhm',align:'center',resizable:false,sortable:false" width="150px">证件号码/护照号</th>
																								<th data-options="field:'belongDepartName',align:'center',resizable:true,sortable:false" width="200px">所在单位</th>
																								<th data-options="field:'name',align:'center',resizable:false,sortable:false" width="70px">姓名</th>
																								<th data-options="field:'sex',align:'center',resizable:false,sortable:false" width="60px">性别</th>
																								<th data-options="field:'sfzhm',align:'center',resizable:false,sortable:false" width="160px">身份证号码</th>
<!-- 																								<th data-options="field:'birthDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="10%">出生日期</th> -->
																								<th data-options="field:'effectiveDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="90px">有效期限</th>
<!-- 																								<th data-options="field:'issuanceAuthority',align:'center',resizable:false,sortable:false" width="10%">签发机关</th> -->
<!-- 																								<th data-options="field:'issuancePlace',align:'center',resizable:false,sortable:false" width="10%">签发地点</th> -->
<!-- 																								<th data-options="field:'issuanceDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="10%">签发日期</th> -->
<!-- 																								<th data-options="field:'nationCode',align:'center',resizable:false,sortable:false" width="10%">国家码</th> -->
<!-- 																								<th data-options="field:'type',align:'center',resizable:false,sortable:false" width="13%">通行证种类/护照类型</th> -->
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		function queryCertificateInfoPublicListCancel${cancelType}(){
			 $('#${cancelType}_certificateInfoPublicListDgCancel').datagrid('load',{
			 				 									certificateType:$('#${cancelType}_certificateTypeCertificateInfoPublicListCancel').combobox('getValue'),
															 	zjhm:$('#${cancelType}_zjhmCertificateInfoPublicListCancel').textbox('getValue'),
															 	sfzhm:$('#${cancelType}_sfzhmCertificateInfoPublicListCancel').textbox('getValue'),
															 	name:$('#${cancelType}_nameCertificateInfoPublicListCancel').textbox('getValue'),
															 	belongDepartId:$('#${cancelType}_departCertificateInfoPublicListCancel').combobox('getValue')
															 								 								 								 								 								 								 								 								 								}); 
		} 


		function viewCertificateCancelInfoPublic${cancelType}(){
			var row = $('#${cancelType}_certificateInfoPublicListDgCancel').datagrid('getSelected');
			var selections = $('#${cancelType}_certificateInfoPublicListDgCancel').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-证照信息',880,730,'icon-search','/certificateInfo/view/'+row.id+'?businessType=single');
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function cancelCertificateInfoPublic${cancelType}(){
			var row = $('#${cancelType}_certificateInfoPublicListDgCancel').datagrid('getSelected');
			var selections = $('#${cancelType}_certificateInfoPublicListDgCancel').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.showStatus!=null&&row.showStatus=='领用'){
					$.messager.alert('系统提示','领用状态暂时无法注销！','info');
					return false;
				}
				if(row.hasRecord!=null&&row.hasRecord=='1'){
					$.messager.alert('系统提示','该证照正在申请记录中，无法注销！','info');
					return false;
				}
				var win=creatWin('注销-证照信息',500,360,'icon-edit','/certificateInfo/cancelWaitPublic/'+row.id+'?status='+row.status);
			    win.window('open');	 		
			}else{
				$.messager.alert('系统提示','请选择一条要注销的数据！','info');
			}
		}
		
		function editCertificateInfoPublic${cancelType}(){
			var row = $('#${cancelType}_certificateInfoPublicListDgCancel').datagrid('getSelected');
			var selections = $('#${cancelType}_certificateInfoPublicListDgCancel').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('修改-证照信息',880,730,'icon-edit','/certificateInfo/editPublic/'+row.id+"?cancelType=${cancelType}");
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}
		function helpCertificatePublicCancel${cancelType}(){
			<c:if test="${cancelType=='wait'}">
				window.open("./resource/onlinehelp/yingong/zzxxdzx/help.html");
			</c:if>
			<c:if test="${cancelType=='cancelled'}">
				window.open("./resource/onlinehelp/yingong/zzxxyzx/help.html");
			</c:if>
		}
	</script>
</body>
</html>

