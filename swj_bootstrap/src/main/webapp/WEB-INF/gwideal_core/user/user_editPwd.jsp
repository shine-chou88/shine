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
		function userEditPwdForm(){
			var flag=false;
			$('#userEditPwdForm').form('submit',{
				onSubmit: function(){
					flag=$(this).form('enableValidation').form('validate');
					if(flag){
						var newPwd=$("[name='newPwd']").val();
						var confirmPwd=$("[name='confirmNewPwd']").val();
						if(!checkPwd(newPwd)){
							$.messager.alert('系统提示','新密码规则至少包含一个大写字母、一个小写字母，一个数字的8-20个字符（能包含!@#$_特殊字符）！','info');
							return false;
						}
						if(newPwd!=confirmPwd){
							$.messager.alert('系统提示','新密码与确认新密码必须保持一致！','info');
							return false;
						}
					}
					if(flag){
						var encrypt = new JSEncrypt();
					    encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4z2h4JuaVJKljC6tdB09ucZzUjbyGCjJcHYi41lypUHWGZ5M4IoPmOWSbVO9XZRBQzp3xBwrbF7lJxK4EGWl9i+BjuRdCUf109jMEo6kMVyjJTtaWAfqohcYBOIXZ3Eqgyyt4sZCIvH4JAB/0mmBPQ1wS6gwW8IPSJEo2Wx3CfQIDAQAB");
					    $("[name='originalPwd']").val(encrypt.encrypt($("[name='originalPwd']").val()));
					    $("[name='newPwd']").val(encrypt.encrypt($("[name='newPwd']").val()));
					    $("[name='confirmNewPwd']").val(encrypt.encrypt($("[name='confirmNewPwd']").val()));
					    $.messager.progress();
					}
					return flag;
				}, 
				dataType:'json',
				success:function(data){
					if(flag){
						$.messager.progress('close');
					}
					var data = eval('(' + data + ')');
					if(data.success){
						$.messager.alert('系统提示',data.info,'info');
						$('#userEditPwdForm').form('clear');
						closeWindow();
					}else{
						$.messager.alert('系统提示',data.info,'error');
						$('#userEditPwdForm').form('clear');
					}
				} 
			});
		}
		function checkPwd(s){
			var regu =/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d!@#$_]{8,20}$/;
			var re = new RegExp(regu);
			if (re.test(s)) {
			        return true;
			    }else{
			       return false;
			    }
		}
	</script>
    <div class="easyui-layout" fit="true">
    	<div region="center" border="false">
	    	<form id="userEditPwdForm" action="${base}/user/saveEditPwd" method="post" data-options="novalidate:true" class="easyui-form">
	    		<input type="hidden" name="userId" value="${currentUser.id}"/>
				<table border="0" cellpadding="0" cellspacing="0" class="main_table">
					<tr>
						<th width="30%">姓名：</th>
						<td width="70%">
							${currentUser.name}
						</td>
					</tr>
					<tr>
						<th><span style="color: red;">*</span>原始密码：</th>
						<td>
							<input  class="easyui-textbox" type="password" name="originalPwd" data-options="required:true,validType:'length[1,50]'" size="30" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<th><span style="color: red;">*</span>新密码：</th>
						<td>
							<input  class="easyui-textbox" type="password" name="newPwd" data-options="required:true,validType:'length[8,20]'" size="30" autocomplete="off"/>
							<span style="color: red;"><br/>至少包含一个大写字母、一个小写字母，一个数字的8-20个字符<br/>（能包含!@#$_特殊字符）</span>
						</td>
					</tr>
					<tr>
						<th><span style="color: red;">*</span>再输一次：</th>
						<td>
							<input  class="easyui-textbox" type="password" name="confirmNewPwd" data-options="required:true,validType:'length[8,20]'" size="30" autocomplete="off"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="south" border="false" style="text-align: center;padding: 2px 2px 2px 2px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="userEditPwdForm();">保存</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
		</div>
	</div>
</body>
</html>

