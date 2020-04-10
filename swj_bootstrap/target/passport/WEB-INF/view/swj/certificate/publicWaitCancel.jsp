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
	<div data-options="region:'north'" border="false" style="margin-bottom: 5px;">
		<form id="publicWaitCancel" action="<c:if test="${bean.status=='wait'}">${base}/certificateInfo/saveCancelPublic/${bean.id}</c:if><c:if test="${bean.status!='wait'}">${base}/certificateInfo/saveCancelWaitPublic/${bean.id}</c:if> " method="post" data-options="novalidate:true" class="easyui-form" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${bean.id}"/>
            <table width="100%"  border="0" cellpadding="5" cellspacing="0" class="a_table">
	            <tr>
	          		<th class="br" width="120px">所在单位：</th>
	          		<td class="br">${bean.belongDepart.name}</td>
	            </tr>
	            <tr>
	          		<th class="br">证照类型：</th>
	          		<td class="br">${bean.certificateTypeShow}</td>
	            </tr>
	            <tr>
	          		<th class="br">证件号码/护照号：</th>
	          		<td class="br">${bean.zjhm}</td>
	            </tr>
	            <tr>
	          		<th class="br">姓名：</th>
	          		<td class="br">${bean.name}</td>
	            </tr>
	            <tr>
	          		<th class="br"><strong style="color: red;">*</strong>注销原因：</th>
	          		<td class="br">
						<input type="radio" onclick="publicWaitCancelRemark(this)" name="publicWaitCancel_remark" <c:if test="${fn:contains(bean.cancelRemark,'退休')}">checked="checked"</c:if>  value="退休" ></input>退休
						<input type="radio" onclick="publicWaitCancelRemark(this)" name="publicWaitCancel_remark" <c:if test="${empty bean.cancelRemark || fn:contains(bean.cancelRemark,'过期')}"> checked="checked"</c:if> value="过期" ></input>过期
						<input type="radio" onclick="publicWaitCancelRemark(this)" name="publicWaitCancel_remark" <c:if test="${fn:contains(bean.cancelRemark,'调离')}">checked="checked"</c:if> value="调离" ></input>调离
						<br>
						<textarea rows="3" cols="35" id="publicWaitCancel_cancelRemark" name="cancelRemark"><c:if test="${empty bean.cancelRemark}">过期</c:if>${bean.cancelRemark}</textarea>
					</td>
	            </tr>
          	</table>
         </form>
    </div>	
   	<div region="south" border="false" class="south">
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="savePublicWaitCancel();">确认</a> 
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
   	</div>	
		
</div>
<script type="text/javascript">
function publicWaitCancelRemark(obj) {
$('#publicWaitCancel_cancelRemark').val(obj.value);
}

function savePublicWaitCancel(){
	if($("#publicWaitCancel_cancelRemark").val()==""){
		alert("请填写注销原因！");
		return;
	}
	var flag=true;
	$('#publicWaitCancel').form('submit', {
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
				$("#certificateInfoPublicListDg").datagrid('reload');
				$("#wait_certificateInfoPublicListDgCancel").datagrid('reload');
				closeWindow();
			}else{
				$.messager.alert('系统提示',data.info,'error');
				$('#publicWaitCancel').form('clear');
			}
		} 
	});
}

</script>
</body>
</html>

