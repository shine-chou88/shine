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
			<form id="certificateCancelInfoListForm" class="easyui-form" style="margin: 0px 0px 0px 0px;">
				<table cellpadding="5" cellspacing="0" class="a_search_table" width="100%" border="0" >
					<tr>
					<th class="br" width="60px;">
									姓名: 
								</th>
								<td class="br" width="100px;">
									<input class="easyui-textbox" style="width:100px" id="nameCertificateInfoListCancel"></input>
								</td>
					  			<th class="br" width="90px;">
									证照类型: 
								</th>
								<td class="br" width="140px;">
									<input id="certificateTypeCertificateInfoListCancel" class="easyui-combobox" name="certificateType" style="width:170px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'privatePassport','text':'因私普通护照'},{'id':'privateHKAndMacaoPass','text':'中华人民共和国往来港澳通行证'},{'id':'privateTaiwanPass','text':'大陆居民往来台湾通行证'}]"></input>
								</td>
					  				<th class="br" width="110px;">
									证件号码/护照号: 
								</th>
								<td class="br" width="140px;">
									<input class="easyui-textbox" style="width:170px" id="zjhmCertificateInfoListCancel"></input>
								</td>
					  			<th class="br" width="100px;">
									身份证号码: 
								</th>
								<td class="br" width="160px;">
									<input class="easyui-textbox" style="width:160px" id="sfzhmCertificateInfoListCancel"></input>
								</td>	
								<td  class="br">	
							<a style="margin-left: 30px;" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search32',size:'large',iconAlign:'left'" onclick="queryCertificateInfoListCancel()">查询</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="certificateInfoListTbCancel" style="padding:2px 5px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-view32',size:'large',iconAlign:'left'" onclick="viewCertificateCancelInfo()">查看</a>&nbsp;
				<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-help',size:'large',iconAlign:'left'" onclick="helpCancelCertificate()">帮助</a>
			</div>
			<table id="certificateInfoListDgCancel" class="easyui-datagrid"
					data-options="collapsible:true,rownumbers:true,url:'${base}/certificateInfo/jsonPagination?status=cancelled&businessType=private',
					method:'post',fit:true,pagination:true,toolbar:'#certificateInfoListTbCancel',singleSelect: true,
					selectOnCheck:true,checkOnSelect: true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',hidden:true"></th>
																		<th data-options="field:'certificateTypeShow',align:'left',resizable:true,sortable:true" width="220px">证照类型</th>
																								<th data-options="field:'zjhm',align:'center',resizable:false,sortable:false" width="150px">证件号码/护照号</th>
																								<th data-options="field:'name',align:'center',resizable:false,sortable:false" width="70px">姓名</th>
																								<th data-options="field:'sex',align:'center',resizable:false,sortable:false" width="60px">性别</th>
																								<th data-options="field:'sfzhm',align:'center',resizable:false,sortable:false" width="160px">身份证号码</th>
<!-- 																								<th data-options="field:'birthDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="10%">出生日期</th> -->
																								<th data-options="field:'effectiveDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="90px">有效期限</th>
<!-- 																								<th data-options="field:'issuanceAuthority',align:'center',resizable:false,sortable:false" width="10%">签发机关</th> -->
<!-- 																								<th data-options="field:'issuancePlace',align:'center',resizable:false,sortable:false" width="10%">签发地点</th> -->
<!-- 																								<th data-options="field:'issuanceDate',align:'center',resizable:false,sortable:false" formatter="ChangeDateFormat2" width="10%">签发日期</th> -->
<!-- 																								<th data-options="field:'nationCode',align:'center',resizable:false,sortable:false" width="10%">国家码</th> -->
<!-- 																								<th data-options="field:'type',align:'center',resizable:false,sortable:false" width="10%">通行证种类/护照类型</th> -->
																	</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		function queryCertificateInfoListCancel(){
			 $('#certificateInfoListDgCancel').datagrid('load',{
			 				 									certificateType:$('#certificateTypeCertificateInfoListCancel').combobox('getValue'),
															 	zjhm:$('#zjhmCertificateInfoListCancel').textbox('getValue'),
															 	sfzhm:$('#sfzhmCertificateInfoListCancel').textbox('getValue'),
															 	name:$('#nameCertificateInfoListCancel').textbox('getValue')
															 								 								 								 								 								 								 								 								 								}); 
		} 


		function viewCertificateCancelInfo(){
			var row = $('#certificateInfoListDgCancel').datagrid('getSelected');
			var selections = $('#certificateInfoListDgCancel').datagrid('getSelections');
			if(row!=null && selections.length==1){
			    var win=creatWin('查看-证照信息',800,650,'icon-search','/certificateInfo/view/'+row.id+'?businessType=single');
			    win.window('open');
			}else{
				 $.messager.alert('系统提示','请选择一条要查看的数据！','info');
			}
		}
		
		function helpCancelCertificate(){
			window.open("./resource/onlinehelp/yingsi/zzxxzx/help.html");
		}
	</script>
</body>
</html>

