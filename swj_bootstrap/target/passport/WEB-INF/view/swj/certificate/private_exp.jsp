<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title></title>
<style type="text/css">
.blueFont{
	font-size:12px;
	color:#0099ff;
	font-weight:bold;
}
</style>
</head>
<body>
	<div class="easyui-layout" fit="true">		
		<form  id="privateExportFileForm" action="" method="post" data-options="novalidate:true" class="easyui-form">
			<div region="center" border="false" style="background: #fff; border-bottom: 1px solid #ccc;">
				<table class="a_table" width="100%">		         
		          <tr height="35">
		             <th class="br"   width="90px;">填表时间：</th>
		             <td class="br" >
		                <input type="text" id="apply_export_start" name="startTime" class="easyui-datebox" value="" size="15"></input>至
		                <input type="text" id="apply_export_end" name="endTime" class="easyui-datebox" value="" size="15"></input>
		             </td>
		           </tr>
		           	<tr>
		           	<th class="br" width="90px;">
									证照类型：
								</th>
								<td class="br" width="140px;">
									<input id="certificateTypeExportList" name="certificateType" class="easyui-combobox"  style="width:170px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',data:[
								{'id':'','text':'--请选择--','selected':true},{'id':'privatePassport','text':'因私普通护照'},{'id':'privateHKAndMacaoPass','text':'中华人民共和国往来港澳通行证'},{'id':'privateTaiwanPass','text':'大陆居民往来台湾通行证(因私)'}]"></input>
								</td>
		           	</tr>
		           	<tr>
		           	<th class="br" width="90px;">
									导出类型： 
								</th>
								<td class="br" width="140px;">
								<input type="radio" name="exportType" value="已归档" />已归档&nbsp;&nbsp;
		          				<input type="radio" name="exportType" value="未归档" />未归档&nbsp;&nbsp;
								</td>
		           	</tr>
		     </table>													    
		    </div>
		    <div region="south" border="false" style="text-align: center;padding: 2px 2px 2px 2px;">
				 <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="exportExcel()">确定</a>
				 <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
		    </div>				
		</form>	
		
	</div>
	<script type="text/javascript">
	
	//确定导出
	function exportExcel(){		
		if (confirm("确定导出？")) {
			var queryForm = document.getElementById("privateExportFileForm");
			queryForm.setAttribute("action",
					"${base}/certificateInfo/privateExportData");
			queryForm.submit();	
		}
	}
	
	</script>
</body>
</html>

