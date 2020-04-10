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
		function roleEditForm(){
			//将功能树选中的值设置到隐藏文本上
			var nodes = $('#functionRoleTree').tree('getChecked');
			var fIds = '';
			for(var i=0; i<nodes.length;i++){
				if (fIds!='') fIds+=',';
				fIds+= nodes[i].id;
			}
			$('#funcIds').val(fIds);
			//form 提交
			var flag=false;
			$('#roleEditForm').form('submit', {
				onSubmit: function(){ 
					flag=$(this).form('enableValidation').form('validate');
					if(flag){
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
						closeWindow();
						$('#roleEditForm').form('clear');
						$("#role_dg").datagrid('reload');
					}else{
						$.messager.alert('系统提示',data.info,'error');
						closeWindow();
						$('#roleEditForm').form('clear');
					}
				} 
			});
		}
		$('#functionRoleTree').tree({
			onCheck: function(node,checked){
				var childRen = $('#functionRoleTree').tree("getChildren",node.target);
				if(childRen.length>0){
					if(checked){
						for(var i=0;i<childRen.length;i++){
							$('#functionRoleTree').tree('check', childRen[i].target);
						}						
					}else{
						for(var i=0;i<childRen.length;i++){
							$('#functionRoleTree').tree('uncheck', childRen[i].target);
						}
					}
				}
			}
		});
	</script>
    <div class="easyui-layout" fit="true">
    	<div region="center" border="false">
	    	<form id="roleEditForm" action="${base}/role/save" method="post" data-options="novalidate:true" class="easyui-form">
	    		<input type="hidden" name="id" value="${bean.id}"/>
	    		<input type="hidden" name="funcIds" id="funcIds"/>
				<table border="0" cellpadding="0" cellspacing="0" class="main_table">
					<tr>
						<th width="20%"><span style="color: red;">*</span>角色代码：</th>
						<td width="80%" colspan="3">
							<input class="easyui-textbox" type="text" name="code" data-options="required:true,validType:'length[1,50]'" size="50" value="${bean.code}"/>
						</td>
					</tr>
					<tr>
						<th><span style="color: red;">*</span>角色名称：</th>
						<td colspan="3">
							<input class="easyui-textbox" type="text" name="name" data-options="required:true,validType:'length[1,50]'" size="50" value="${bean.name}"/>
						</td>
					</tr>
					<tr>
						<th><span style="color: red;">*</span>排列顺序：</th>
						<td colspan="3">
							<input class="easyui-textbox" type="text" name="orderNo" data-options="required:true,validType:'length[1,10]'" size="50" value="${bean.orderNo}"/>
						</td>
					</tr>
					<tr>
						<th>描述：</th>
						<td colspan="3">
							<input class="easyui-textbox" type="text" name="description" data-options="multiline:true,validType:'length[1,200]'" size="70" style="height:100px" value="${bean.description}"/>
						</td>
					</tr>
					<tr>
						<th>拥有的功能：</th>
						<td colspan="3">
							<ul id="functionRoleTree" class="easyui-tree" data-options="url:'${base}/role/functionTree?roleId=${bean.id}',animate:true,lines:true,checkbox:true,cascadeCheck:false"></ul>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="south" border="false" class="south">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="roleEditForm();">保存</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
		</div>
	</div>
</body>
</html>

