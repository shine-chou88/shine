<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
	<title>${title}</title>
</head>
<body> 
<script type="text/javascript">

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<form id="certificateInfoEditForm" action="${base}/certificateInfo/save" method="post" data-options="novalidate:true" class="easyui-form">
       		<input type="hidden" name="id" value="${bean.id}"/>
       		<input type="hidden" name="status" value="${bean.status}"/>
       		<input type="hidden" name="hasRecord" value="${bean.hasRecord}"/>
        <table cellpadding="5" cellspacing="0" class="a_table" width="100%">
       
        	 <tr>
          		<th width="200px" class="br">
          			<strong style="color: red;">*</strong>所在单位：
          		</th>
          		<td width="600px"  class="br">
          			<input type="text" name="belongDepart.id" value="${bean.belongDepart.id}" class="easyui-combobox"  style="width:200px" data-options="url:'${base}/depart/certificateDepartJson?selected=${empty bean.belongDepart.id?'SHSSWJ':bean.belongDepart.id }',valueField:'id',textField:'text'" readonly="readonly"/>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			<strong style="color: red;">*</strong>证照类型：
          		</th>
          		<td  class="br">
					<input type="radio" name="certificateType" value="privatePassport" <c:if test="${empty bean.certificateType ||bean.certificateType=='privatePassport'}">checked="checked"</c:if>/>因私普通护照&nbsp;&nbsp;
          			<input type="radio" name="certificateType" value="privateHKAndMacaoPass" <c:if test="${bean.certificateType=='privateHKAndMacaoPass'}">checked="checked"</c:if>/>中华人民共和国往来港澳通行证&nbsp;&nbsp;
          			<input type="radio" name="certificateType" value="privateTaiwanPass" <c:if test="${bean.certificateType=='privateTaiwanPass'}">checked="checked"</c:if>/>大陆居民往来台湾通行证
          		</td>
        	</tr>
        	 <tr>
          		<th  class="br">
          			国家码：
          		</th>
          		<td  class="br">
          			<input type="text" name="nationCode"  class="easyui-textbox" value="${ bean.nationCode}" style="width:200px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			<strong style="color: red;">*</strong>证件号码/护照号：
          		</th>
          		<td  class="br">
          			<input type="text" name="zjhm"  data-options="required:true" class="easyui-textbox" value="${ bean.zjhm}" style="width:200px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			<strong style="color: red;">*</strong>姓名：
          		</th>
          		<td  class="br">
          			<input type="hidden" id="certificateOwnerId" name="owner.id" value="${bean.owner.id}"/>
          			<input type="text" id="certificateOwnerName" name="name"   class="my_input_style" value="${ bean.name}" style="width:200px" readonly="readonly"></input>
					<input type="button" value="选" class="button_blue"  onclick="selectCertificateOwner('private')"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			性别：
          		</th>
          		<td  class="br">
          			<input type="radio" name="sex" value="男" <c:if test="${bean.sex=='男'}">checked="checked"</c:if>/>男&nbsp;&nbsp;
          			<input type="radio" name="sex" value="女" <c:if test="${bean.sex=='女'}">checked="checked"</c:if>/>女
          		</td>
        	</tr>
        	<tr>
          		<th  class="br">
          			<!-- <strong style="color: red;">*</strong>-->身份证号码： 
          		</th>
          		<td  class="br">
          			<input type="text" id="certificate_sfzhm" name="sfzhm"   class="my_input_style" value="${ bean.sfzhm}" style="width:200px" onblur="sfzhmCheck()" ></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			出生日期：
          		</th>
          		<td  class="br">
          			<input id="privateCertificateBirthDate" type="text" name="birthDate"  class="easyui-datebox" value="${ bean.birthDate}" data-options="editable:false" style="width:120px"></input>
          		</td>
        	</tr>
        	<tr>
          		<th  class="br">
          			出生地点：
          		</th>
          		<td  class="br">
          			<input type="text" name="birthPlace"  class="easyui-textbox" value="${ bean.birthPlace}" style="width:200px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			<strong style="color: red;">*</strong>有效期限：
          		</th>
          		<td  class="br">
          			<input type="text" name="effectiveDate"  data-options="required:true,editable:false" class="easyui-datebox" value="${ bean.effectiveDate}" style="width:120px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			签发机关：
          		</th>
          		<td  class="br">
          			<input type="text" name="issuanceAuthority"  class="easyui-textbox" value="${ bean.issuanceAuthority}" style="width:350px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			签发地点：
          		</th>
          		<td  class="br">
          			<input type="text" name="issuancePlace"  class="easyui-textbox" value="${ bean.issuancePlace}" style="width:350px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			签发日期：
          		</th>
          		<td  class="br">
          			<input type="text" name="issuanceDate"  class="easyui-datebox" data-options="editable:false" value="${ bean.issuanceDate}" style="width:120px"></input>
          		</td>
        	</tr>
        	        	
        	        	<tr>
          		<th  class="br">
          			<strong style="color: red;">*</strong>通行证种类/护照类型：
          		</th>
          		<td  class="br">
          			<input type="text" name="type"  data-options="required:true" class="easyui-textbox" value="${ bean.type}" style="width:200px"></input>
          		</td>
        	</tr>
        	            <tr>
            <td class="br" colspan="2">
           		<div region="south" border="false" style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-ok" onclick="saveCertificateInfoForm()">保存</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
				</div>
            </td>
            </tr>
          	</table>
		</form>	
	</div>
</div>    	
<script type="text/javascript">
function saveCertificateInfoForm(){
	var flag=false;
	if($("#certificateOwnerName").val()==null||$("#certificateOwnerName").val()==''){
		$.messager.alert('系统提示', '姓名不能为空，请选择', 'info');
		return false;
	}
	if(!sfzhmCheck()){
		return false;
	}
	$('#certificateInfoEditForm').form('submit', {
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
				$.messager.alert('系统提示', data.info, 'info');
				closeWindow();
				$('#certificateInfoEditForm').form('clear');
				$('#certificateInfoListDg').datagrid('reload');
			}else{
				$.messager.alert('系统提示', data.info, 'error');
			}
		} 
	});
}
function sfzhmCheck(){
	var sfzhm=$("#certificate_sfzhm").val();
	if(sfzhm==null||sfzhm==''){
		return true;
	}
	//验证格式
	 if(!validateIdCard(sfzhm)){
		$.messager.alert('系统提示', "身份证格式不正确，请重新输入！", 'info');
		return false;
	 }
	 setCsrq(sfzhm,'privateCertificateBirthDate');
	 return true;
}

</script>
</body>
</html>