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
		
	</script>
    <div class="easyui-layout" fit="true">
    	<div region="center" border="false">
	    	<form id="userSignEditForm" action="${base}/user/saveUploadSign?userId=${bean.id}" method="post" enctype="multipart/form-data" data-options="novalidate:true" class="easyui-form">
				<table border="0" cellpadding="0" cellspacing="0" class="main_table">
					<tr>
					<th width="15%">账号：</th>
							<td width="35%" >
								${bean.accountNo}
							</td>
						<th width="15%">姓名：</th>
						<td>
							${bean.name}
						</td>
					</tr>
					<tr>
          		<th class="br">电子签名附件：</th>
          		<td colspan="3" class="br">
          			<table width="100%" border="0px">
          			<c:if test="${not empty signAtt }">
										<tr id="${signAtt.id}">
											<td style="border: 0">
												<a href="${base}/attachment/download/${signAtt.id}" target="_blank">
												<img id="p" src="${base}/attachment/IoReadImage/${signAtt.id}" style="width: 100px; height: 60px"/>
												</a>
												<a href="javascript:delAtt('${signAtt.id}')" style="text-decoration: underline;color: blue;">删除</a>
											</td>
										</tr>
					</c:if>
					</table>
          			<table width="100%" id="copypic" border="0px">
						<tr>
							<td>
								<input id="signFile" type="file" name="signFile" />
							</td>
						</tr>
					</table>
          		</td>
          		</tr>
					
				</table>
			</form>
		</div>
		<div region="south" border="false" class="south">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="userSignEditForm();">保存</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
	function delAtt(id){
		if(confirm("确认删除吗？")){
			$.ajax({ 
				type: 'POST', 
				url: '${base}/attachment/delete/'+id,
				dataType: 'json',  
				success: function(data){ 
					if(data.success){
						$.messager.alert('系统提示',data.info,'info');
						document.getElementById(id).style.display="none";
						document.getElementById("copypic").style.display="";
					}else{
						$.messager.alert('系统提示',data.info,'error');
					}
				} 
			}); 
		}
	}
	function userSignEditForm(){
		if($("#signFile").val()==null||$("#signFile").val()==""){
			alert("请选择需要上传的电子签名图片！");
			return;
		}
		$('#userSignEditForm').form('submit', {
			dataType:'json',
			success:function(data){
				var data = eval('(' + data + ')');
				if(data.success){
					$.messager.alert('系统提示', data.info, 'info');
					closeWindow();
					$('#userSignEditForm').form('clear');
				}else{
					$.messager.alert('系统提示', data.info, 'error');
				}
			} 
		});
	}
	</script>
</body>
</html>

