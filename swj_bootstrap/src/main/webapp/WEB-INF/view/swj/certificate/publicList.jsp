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
			<form id="certificateInfoPublicListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					<th class="br" width="35px;">
									姓名: 
								</th>
								<td class="br" width="40px;">
									<input class="easyui-textbox" style="width:70px" id="nameCertificateInfoPublicList"></input>
								</td>
					
					  			<th class="br" width="65px;">
									证照类型: 
								</th>
								<td class="br" width="80px;">
									<input id="certificateTypeCertificateInfoPublicList" class="easyui-combobox" name="certificateType" style="width:100px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'publicPassport','text':'因公普通护照'},{'id':'publicHKAndMacaoPass','text':'因公往来香港澳门特别行政区通行证'},{'id':'publicTaiwanPass','text':'大陆居民往来台湾通行证'}]"></input>
								</td>
					  				<th class="br" width="110px;">
									证件号码/护照号: 
								</th>
								<td class="br" width="100px;">
									<input class="easyui-textbox" style="width:140px" id="zjhmCertificateInfoPublicList"></input>
								</td>
					  			<th class="br" width="80px;">
									身份证号码: 
								</th>
								<td class="br" width="125px;">
									<input class="easyui-textbox" style="width:160px" id="sfzhmCertificateInfoPublicList"></input>
								</td>
								
					</tr>
					<tr>
					
					<th class="br" width="100px;">打印情况：</th>
          		<td class="br" width="60px;">
          		<input id="hasPrintedSelectPublic" class="easyui-combobox"  style="width:80px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'T','text':'已完成'},{'id':'F','text':'未完成'}]"></input>
          		</td>	
          		<th class="br" width="80px;">归还逾期：</th>
          		<td class="br" width="80px;">
          		<input id="isNotBackSelect" class="easyui-combobox" name="isNotBack" style="width:80px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'1','text':'是'},{'id':'0','text':'否'}]"></input>
          		</td>
          		<th class="br" width="65px;">
									所在单位: 
								</th>
								<td class="br" width="50px;">
									<input id="departCertificateInfoPublicList" class="easyui-combobox"  style="width:200px;" data-options="valueField:'id',textField:'text',url:'${base}/depart/certificateDepartJson'"></input>
					</td>
				<th class="br" width="80px;">状态：</th>
          		<td class="br" width="150px;">
          		<input id="statusSelectPublic" class="easyui-combobox" style="width:80px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'draft','text':'初始'},{'id':'using','text':'领用'},{'id':'checkIn','text':'在库'}]"></input>
							<a style="margin-left: 10px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryCertificateInfoPublicList()">查询</a>&nbsp;&nbsp;
						</td>	
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="certificateInfoPublicListTb" style="padding:2px 5px;">
				<gwideal:perm url="/certificateInfo/addPublic">
					<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-add32',size:'large',iconAlign:'left'" onclick="addCertificateInfoPublic()" >新增</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/editPublic/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit32',size:'large',iconAlign:'left'" onclick="editCertificateInfoPublic()">修改</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/delete/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut32',size:'large',iconAlign:'left'" onclick="deleteCertificateInfoPublic()">删除</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/printQrcode/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-qr32',size:'large',iconAlign:'left'" onclick="genQrCodeCertificateInfoPublic()">二维码打印预览</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/checkInPublic/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-checkIn32',size:'large',iconAlign:'left'" onclick="checkInCertificateInfoPublic()">入库</a>&nbsp;
				</gwideal:perm>
				<gwideal:perm url="/certificateInfo/cancelWaitPublic/{id}">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-zx32',size:'large',iconAlign:'left'" onclick="waitCancelCertificateInfoPublic()">注销</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewCertificateInfoPublic()">查看</a>&nbsp;
				<gwideal:perm url="/certificateInfo/publicImport">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-import32',size:'large',iconAlign:'left'" onclick="importPublicInfo()">导入</a>&nbsp;
				</gwideal:perm>
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpCertificatePublic()">帮助</a>
			</div>
			<table id="certificateInfoPublicListDg" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/certificateInfo/jsonPagination?businessType=public',
					method:'post',fit:true,pagination:true,toolbar:'#certificateInfoPublicListTb',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'hasRecord',hidden:true"></th>
																		<th data-options="field:'certificateTypeShow',align:'left',resizable:true,sortable:true" width="230px">证照类型</th>
																								<th data-options="field:'zjhm',align:'center',resizable:false,sortable:false" width="150px">证件号码/护照号</th>
																								<th data-options="field:'belongDepartName',align:'center',resizable:true,sortable:false" width="200px">所在单位</th>
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
		function queryCertificateInfoPublicList(){
			 $('#certificateInfoPublicListDg').datagrid('load',{
			 				 									certificateType:$('#certificateTypeCertificateInfoPublicList').combobox('getValue'),
															 	zjhm:$('#zjhmCertificateInfoPublicList').textbox('getValue'),
															 	sfzhm:$('#sfzhmCertificateInfoPublicList').textbox('getValue'),
															 	name:$('#nameCertificateInfoPublicList').textbox('getValue'),
															 	belongDepartId:$('#departCertificateInfoPublicList').combobox('getValue'),
															 	isNotBack:$('#isNotBackSelect').combobox('getValue'),
															 	hasPrinted:$('#hasPrintedSelectPublic').combobox('getValue'),
															 	status:$('#statusSelectPublic').combobox('getValue')
															 								 								 								 								 								 								 								 								 								}); 
		} 

		function addCertificateInfoPublic(){
		    var win=creatWin('新增-证照信息',880,730,'icon-add','/certificateInfo/addPublic');
		    win.window('open');
		}
		
		function editCertificateInfoPublic(){
			var row = $('#certificateInfoPublicListDg').datagrid('getSelected');
			var selections = $('#certificateInfoPublicListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('修改-证照信息',880,730,'icon-edit','/certificateInfo/editPublic/'+row.id);
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要修改的数据！','info');
			}
		}

		function viewCertificateInfoPublic(){
			var row = $('#certificateInfoPublicListDg').datagrid('getSelected');
			var selections = $('#certificateInfoPublicListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-证照信息',880,730,'icon-search','/certificateInfo/view/'+row.id+'?businessType=public');
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function deleteCertificateInfoPublic(){
			var row = $('#certificateInfoPublicListDg').datagrid('getSelected');
			var selections = $('#certificateInfoPublicListDg').datagrid('getSelections');
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
									$("#certificateInfoPublicListDg").datagrid('reload'); 
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
		function waitCancelCertificateInfoPublic(){
			var row = $('#certificateInfoPublicListDg').datagrid('getSelected');
			var selections = $('#certificateInfoPublicListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.showStatus!=null&&row.showStatus=='领用'){
					$.messager.alert('系统提示','领用状态暂时无法注销！','info');
					return false;
				}
				if(row.hasRecord!=null&&row.hasRecord=='1'){
					$.messager.alert('系统提示','该证照正在申请记录中，无法注销！','info');
					return false;
				}
				var win=creatWin('待注销-证照信息',500,360,'icon-search','/certificateInfo/cancelWaitPublic/'+row.id+'?status='+row.status);
			    win.window('open');	 		
			}else{
				$.messager.alert('系统提示','请选择一条要注销的数据！','info');
			}
		}
		function checkInCertificateInfoPublic(){
			var row = $('#certificateInfoPublicListDg').datagrid('getSelected');
			var selections = $('#certificateInfoPublicListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				if(row.showStatus!='初始'){
					$.messager.alert('系统提示', '非初始状态不能入库', 'info');
					return false;
				}
				var win=creatWin('入库-证照信息',800,730,'icon-edit','/certificateInfo/checkInPublic/'+row.id);
			    win.window('open');		
			}else{
				$.messager.alert('系统提示','请选择一条要入库的数据！','info');
			}
		}
		
		function importPublicInfo(){
			var win=creatWin('导入-证照信息(因公)',450,140,'icon-search','/certificateInfo/publicImport');
			win.window('open');
		}
		function genQrCodeCertificateInfoPublic(){
			var row = $('#certificateInfoPublicListDg').datagrid('getSelected');
			var selections = $('#certificateInfoPublicListDg').datagrid('getSelections');
			if(row!=null && selections.length==1){
				var win=creatFreeWindow("four_window",'打印-二维码预览',420,280,'icon-search','/certificateInfo/printQrcode/'+row.id);
				 win.window('open');
			}else{
				$.messager.alert('系统提示','请选择一条要二维码打印预览的数据！','info');
			}
		}
		
		function helpCertificatePublic(){
			window.open("./resource/onlinehelp/yingong/zzxxgl/help.html");
		}
	</script>
</body>
</html>

