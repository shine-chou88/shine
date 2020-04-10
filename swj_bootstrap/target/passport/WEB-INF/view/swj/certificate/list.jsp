<%@page import="com.gwideal.swj.certificate.entity.CertificationStatus"%>
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
			<form id="certificateInfoListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<input type="hidden" name="certificateType" id="exportCertificateType"/>
				<input type="hidden" name="zjhm" id="exportZjhm"/>
				<input type="hidden" name="sfzhm" id="exportSfzhm"/>
				<input type="hidden" name="name" id="exportName"/>
				<input type="hidden" name="hasPrinted" id="exportHasPrinted"/>
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					<th class="br" width="40px;">
									姓名: 
								</th>
								<td class="br" width="70px;">
									<input class="easyui-textbox" style="width:70px" id="nameCertificateInfoList"></input>
								</td>
					  			<th class="br" width="80px;">
									证照类型: 
								</th>
								<td class="br" width="120px;">
									<input id="certificateTypeCertificateInfoList" class="easyui-combobox"  style="width:120px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'privatePassport','text':'因私普通护照'},{'id':'privateHKAndMacaoPass','text':'中华人民共和国往来港澳通行证'},{'id':'privateTaiwanPass','text':'大陆居民往来台湾通行证'}]"></input>
								</td>
					  				<th class="br" width="120px;">
									证件号码/护照号: 
								</th>
								<td class="br" width="140px;">
									<input class="easyui-textbox" style="width:150px" id="zjhmCertificateInfoList"></input>
								</td>
					  			<th class="br" width="80px;">
									身份证号码: 
								</th>
								<td class="br" width="160px;">
									<input class="easyui-textbox" style="width:160px" id="sfzhmCertificateInfoList"></input>
								</td>
								</tr>
				<tr>
				<th class="br" width="80px;">状态：</th>
          		<td class="br" width="80px;">
          		<input id="statusSelectPrivate" class="easyui-combobox" style="width:80px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'draft','text':'初始'},{'id':'using','text':'领用'},{'id':'checkIn','text':'在库'}]"></input>
          		</td>	
								<th class="br" width="50px;">打印：</th>
          		<td class="br" width="60px;">
          		<input id="hasPrintedSelect" class="easyui-combobox"  style="width:80px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'T','text':'已完成'},{'id':'F','text':'未完成'}]"></input>
          		</td>		
								<td  class="br" colspan="4">
							
							<a style="margin-left: 30px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryCertificateInfoList()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="certificateInfoListTb" style="padding:2px 5px;">
				<gwideal:perm url="/certificateInfo/add">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addCertificateInfo()" >新增</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/edit/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editCertificateInfo()">修改</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/delete/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deleteCertificateInfo()">删除</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/checkIn/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-checkIn32',size:'large',iconAlign:'left'" onclick="checkInCertificateInfo()">入库</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/cancel/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-zx32',size:'large',iconAlign:'left'" onclick="cancelCertificateInfo()">注销</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/printQrcode/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-qr32',size:'large',iconAlign:'left'" onclick="genQrCodeCertificateInfo()">二维码打印预览</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewCertificateInfo()">查看</a>&nbsp;
				<gwideal:perm url="/certificateInfo/privateImport">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-import32',size:'large',iconAlign:'left'" onclick="importInfo()">导入</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/privateCertificateExport">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export32',size:'large',iconAlign:'left'" onclick="exportCertificateInfo()">导出</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/privateExport">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export32',size:'large',iconAlign:'left'" onclick="exportInfo()">导出审批台账</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpCertificate()">帮助</a>
			</div>
			<table id="certificateInfoListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/certificateInfo/jsonPagination?businessType=private',
					method:'post',fit:true,pagination:true,toolbar:'#certificateInfoListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'hasRecord',hidden:true"></th>
																		<th data-options="field:'certificateTypeShow',align:'center',resizable:true,sortable:true" width="220px">证照类型</th>
																								<th data-options="field:'zjhm',align:'center',resizable:false,sortable:false" width="150px">证件号码/护照号</th>
																								<th data-options="field:'name',align:'center',resizable:false,sortable:false" width="70px">姓名</th>
																								<th data-options="field:'sex',align:'center',resizable:false,sortable:false" width="60px">性别</th>
																								<th data-options="field:'sfzhm',align:'center',resizable:false,sortable:false" width="160px">身份证号码</th>
<!-- 																							<th data-options="field:'birthDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="10%">出生日期</th> -->
																								<th data-options="field:'effectiveDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="90px">有效期限</th>
<!-- 																								<th data-options="field:'issuanceAuthority',align:'center',resizable:false,sortable:false" width="150px">签发机关</th> -->
<!-- 																							<th data-options="field:'issuancePlace',align:'center',resizable:false,sortable:false" width="10%">签发地点</th> -->
<!-- 																								<th data-options="field:'issuanceDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="100px">签发日期</th> -->
<!-- 																						    <th data-options="field:'nationCode',align:'center',resizable:false,sortable:false" width="10%">国家码</th> -->
<!-- 																								<th data-options="field:'type',align:'center',resizable:false,sortable:false" width="180px">通行证种类/护照类型</th> -->
																								<th data-options="field:'showStatus',align:'center',resizable:false,sortable:false" width="50px">状态</th>
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function queryCertificateInfoList(){
			 $('#certificateInfoListDg').datagrid('load',{
			 				 									certificateType:$('#certificateTypeCertificateInfoList').combobox('getValue'),
															 	zjhm:$('#zjhmCertificateInfoList').textbox('getValue'),
															 	sfzhm:$('#sfzhmCertificateInfoList').textbox('getValue'),
															 	name:$('#nameCertificateInfoList').textbox('getValue'),
															 	hasPrinted:$('#hasPrintedSelect').combobox('getValue'),
															 	status :$('#statusSelectPrivate').combobox('getValue')
															 								 								 								 								 								 								 								 								 								}); 
		} 

		function addCertificateInfo(){
		    var win=creatWin('新增-证照信息',830,680,'icon-add','/certificateInfo/add?businessType=private');
		    win.window('open');
		}
		
		function editCertificateInfo(){
			var row = $('#certificateInfoListDg').datagrid('getSelected');
			var selections = $('#certificateInfoListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('修改-证照信息',830,680,'icon-edit','/certificateInfo/edit/'+row.id+'?businessType=private');
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}

		function viewCertificateInfo(){
			var row = $('#certificateInfoListDg').datagrid('getSelected');
			var selections = $('#certificateInfoListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-证照信息',830,680,'icon-search','/certificateInfo/view/'+row.id+'?businessType=private');
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function deleteCertificateInfo(){
			var row = $('#certificateInfoListDg').datagrid('getSelected');
			var selections = $('#certificateInfoListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.hasRecord!=null&&row.hasRecord=='1'){
					$.messager.alert('系统提示','该证照正在申请记录中，无法删除！','info');
					return false;
				}
				$.messager.confirm('系统提示','确认删除吗?',function(id){ 
					if(id){ 
						$.ajax({ 
							type: 'POST', 
							url: base+'/certificateInfo/delete/'+row.id,
							dataType: 'json',  
							success: function(data){
								if(data.success){
									$.messager.alert('系统提示', data.info, 'info');
									$("#certificateInfoListDg").datagrid('reload'); 
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
		function cancelCertificateInfo(){
			var row = $('#certificateInfoListDg').datagrid('getSelected');
			var selections = $('#certificateInfoListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.showStatus!=null&&row.showStatus=='领用'){
					$.messager.alert('系统提示','领用状态暂时无法注销！','info');
					return false;
				}
				if(row.hasRecord!=null&&row.hasRecord=='1'){
					$.messager.alert('系统提示','该证照正在申请记录中，无法注销！','info');
					return false;
				}
				$.messager.confirm('系统提示','确认注销吗?',function(id){ 
					if(id){ 
						$.ajax({ 
							type: 'POST', 
							url: base+'/certificateInfo/cancel/'+row.id,
							dataType: 'json',  
							success: function(data){
								if(data.success){
									$.messager.alert('系统提示', data.info, 'info');
									$("#certificateInfoListDg").datagrid('reload'); 
								}else{ 
									$.messager.alert('系统提示', data.info, 'info');
								} 
							} 
						}); 
					} 
				}); 		
			}else{
				$.messager.alert('系统提示','请选择一条要注销的数据！','info');
			}
		}
		function checkInCertificateInfo(){
			var row = $('#certificateInfoListDg').datagrid('getSelected');
			var selections = $('#certificateInfoListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.showStatus!='初始'){
					$.messager.alert('系统提示', '非初始状态不能入库', 'info');
					return false;
				}
				var win=creatWin('入库-证照信息',800,680,'icon-search','/certificateInfo/checkIn/'+row.id);
			    win.window('open');		
			}else{
				$.messager.alert('系统提示','请选择一条要入库的数据！','info');
			}
		}
		function genQrCodeCertificateInfo(){
			var row = $('#certificateInfoListDg').datagrid('getSelected');
			var selections = $('#certificateInfoListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				var win=creatFreeWindow("four_window",'打印-二维码预览',420,280,'icon-search','/certificateInfo/printQrcode/'+row.id);
				 win.window('open');
			}else{
				$.messager.alert('系统提示','请选择一条要二维码打印预览的数据！','info');
			}
		}
		function importInfo(){
			var win=creatWin('导入-证照信息(因私)',450,160,'icon-search','/certificateInfo/privateImport');
			win.window('open');
		}
		function exportInfo(){
			var win=creatWin('导出台账-证照信息(因私)',450,180,'icon-search','/certificateInfo/privateExport');
			win.window('open');
		}
		function exportCertificateInfo(){
			if (confirm("确定导出？")) {
				var exportForm = document.getElementById("certificateInfoListForm");
				var certificateType=$('#certificateTypeCertificateInfoList').combobox('getValue');
				var zjhm=$('#zjhmCertificateInfoList').textbox('getValue');
				var sfzhm=$('#sfzhmCertificateInfoList').textbox('getValue');
				var name=$('#nameCertificateInfoList').textbox('getValue');
				var hasPrinted=$('#hasPrintedSelect').combobox('getValue');
				$("#exportCertificateType").val(certificateType);
				$("#exportZjhm").val(zjhm);
				$("#exportSfzhm").val(sfzhm);
				$("#exportName").val(name);
				$("#exportHasPrinted").val(hasPrinted);
				exportForm.setAttribute("method","post");
				exportForm.setAttribute("accept-charset","UTF-8");
				exportForm.setAttribute("action",
						"${base}/certificateInfo/privateInfoExport?businessType=private");
				exportForm.submit();	
			}
		}
		
		function helpCertificate(){
			window.open("./resource/onlinehelp/yingsi/zzxxgl/help.html");
		}
	</script>
</body>
</html>

