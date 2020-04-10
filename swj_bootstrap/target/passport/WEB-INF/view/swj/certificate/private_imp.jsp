<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
<title></title>
<script type="text/javascript">

</script>
</head>
<body> 
<div class="easyui-layout" fit="true" style="padding:2px">
		<form id="privateFileForm" action="${base}/certificateInfo/privateImportData" method="post" data-options="novalidate:true" class="easyui-form" enctype="multipart/form-data">
         <table width="100%"  border="0" cellpadding="0" cellspacing="0" class="a_search_table">
          
          <tr style="height: 60px;">
          		<td colspan="2" style="text-align: center;">请选择需要导入的文件：
          		<input id="privateFile" type="file" name="privateFile"/>
           		</td>
           </tr>
			<tr>
          		<th>模板下载：</th>
          		<td >
					<a href="${base}/resource/download/private_import.xls" style="text-decoration: underline;color: blue;">因私出国（境）_导入模板.xls</a>
				</td>
			</tr>
          	</table>
         </form>	
   		<div region="south" border="false" class="south">
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="saveImportData();">确认导入</a> 
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
   		</div>	
		
	</div>
<script type="text/javascript">


function saveImportData(){
	if($("#privateFile").val()==null||$("#privateFile").val()==""){
		alert("请选择需要导入的文件！");
		return;
	}
	var flag=true;
	$('#privateFileForm').form('submit', {
		dataType:'json',
		onSubmit: function(){ 
			if(flag){
				$.messager.progress();
			}
			return flag;
		}, 
		success:function(data){
			if(flag){
				$.messager.progress('close');
			}
			var data = eval('(' + data + ')');
			if(data.success){
				$.messager.alert('系统提示',data.info,'info');
				$("#certificateInfoListDg").datagrid('reload');
				closeWindow();
			}else{
				$.messager.alert('系统提示',data.info,'error');
				$('#privateFileForm').form('clear');
			}
		} 
	});
}

</script>
</body>
</html>

