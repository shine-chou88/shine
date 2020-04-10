<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
	<title>${title}</title>
</head>
<body> 
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<form id="certificateInfoPublicEditForm" action="${base}/certificateInfo/savePublic" method="post" enctype="multipart/form-data" data-options="novalidate:true" class="easyui-form">
       		<input type="hidden" name="id" value="${bean.id}"/>
       		<input type="hidden" name="status" value="${bean.status}"/>
       		<input type="hidden" name="hasRecord" value="${bean.hasRecord}"/>
        <table cellpadding="5" cellspacing="0" class="a_table" width="100%">
       
        	 <tr>
          		<th width="200px" class="br">
          			<strong style="color: red;">*</strong>所在单位：
          		</th>
          		<td width="600px" class="br">
          			<input type="text" id="publicBelongDepart" name="belongDepart.id"  class="easyui-combobox"  style="width:250px" data-options="url:'${base}/depart/certificateDepartJson?selected=${empty bean.belongDepart.id?'SHSSWJ':bean.belongDepart.id }',valueField:'id',textField:'text'"/>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			<strong style="color: red;">*</strong>证照类型：
          		</th>
          		<td  class="br">
					<input type="radio" name="certificateType" value="publicPassport" <c:if test="${empty bean.certificateType ||bean.certificateType=='publicPassport'}">checked="checked"</c:if>/>因公普通护照&nbsp;&nbsp;
          			<input type="radio" name="certificateType" value="publicHKAndMacaoPass" <c:if test="${bean.certificateType=='publicHKAndMacaoPass'}">checked="checked"</c:if>/>因公往来香港澳门特别行政区通行证&nbsp;&nbsp;
          			<input type="radio" name="certificateType" value="publicTaiwanPass" <c:if test="${bean.certificateType=='publicTaiwanPass'}">checked="checked"</c:if>/>大陆居民往来台湾通行证
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
          			<input type="hidden" id="certificateOwnerPublicId" name="owner.id" value="${bean.owner.id}"/>
          			<input type="text" id="certificateOwnerPublicName" name="name"   class="my_input_style" value="${ bean.name}" style="width:200px"></input>
          			<input type="button" value="选" class="button_blue"  onclick="selectCertificateOwner('public')"></input>
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
          			<input type="text" id="certificate_sfzhm_public" name="sfzhm"   class="my_input_style" value="${ bean.sfzhm}" style="width:200px" onblur="sfzhmCheckPublic()"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th  class="br">
          			出生日期：
          		</th>
          		<td  class="br">
          			<input id="privateCertificateBirthDatePublic" type="text" name="birthDate"  class="easyui-datebox" value="${ bean.birthDate}" style="width:120px" data-options="editable:false" ></input>
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
          			<input type="text" name="effectiveDate"  data-options="required:true,editable:false" class="easyui-datebox" value="${ bean.effectiveDate}" style="width:120px" ></input>
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
          			<input type="text" name="issuanceDate"  class="easyui-datebox" value="${ bean.issuanceDate}"  style="width:120px" data-options="editable:false"></input>
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
          		<th class="br">附件：</th>
          		<td colspan="3" class="br">
          			<table width="100%" border="0px">
          				<c:forEach items="${listAtt}" var="att">
							<c:if test="${fn:contains(att.originalName,'jpg')||fn:contains(att.originalName,'png')||fn:contains(att.originalName,'jpeg')}">
										<tr id="${att.id}">
											<td style="border: 0">
												<a href="${base}/attachment/download/${att.id}" target="_blank">
												<img id="p" src="${base}/attachment/IoReadImage/${att.id}" style="width: 180px; height: 120px"/>
												</a>
												<a href="javascript:delAtt('${att.id}')" style="text-decoration: underline;color: blue;">删除</a>
											</td>
										</tr>
									</c:if>
									<c:if test="${!fn:contains(att.originalName,'jpg')&&!fn:contains(att.originalName,'png')&&!fn:contains(att.originalName,'jpeg')}">
										<tr id="${att.id}">
											<td style="border: 0">
												<a href="${base}/attachment/download/${att.id}" style="text-decoration: underline;color: blue;">${att.originalName}</a>&nbsp;&nbsp;&nbsp;
												<a href="javascript:delAtt('${att.id}')" style="text-decoration: underline;color: blue;">删除</a>
											</td>
										</tr>
									</c:if>
          				</c:forEach>
					</table>
          			<table width="100%" id="copypic" border="0px">
						<tr>
							<td>
								<input type="file" name="publicFiles" style='width:50%'/>
								<input type="button" class="button-1" value="添加附件" onclick="addcopy('copypic');" />
							</td>
						</tr>
					</table>
          		</td>
          	</tr>
        	            <tr>
            <td class="br" colspan="2">
           		<div region="south" border="false" style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-ok" onclick="saveCertificateInfoPublicForm()">保存</a> 
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
function saveCertificateInfoPublicForm(){
	var flag=false;
	var publicBelongDepart=$("#publicBelongDepart").combobox('getValue');
	if(publicBelongDepart==null||publicBelongDepart==''){
		$.messager.alert('系统提示', "所在单位不能为空", 'info');
		return false;
	}
	if($("#certificateOwnerPublicName").val()==null||$("#certificateOwnerPublicName").val()==''){
		$.messager.alert('系统提示', '姓名不能为空，请选择', 'info');
		return false;
	}
	if(!sfzhmCheckPublic()){
		return false;
	}
	$('#certificateInfoPublicEditForm').form('submit', {
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
				$('#certificateInfoPublicEditForm').form('clear');
				if("${cancelType}"!=""){
					$('#${cancelType}_certificateInfoPublicListDgCancel').datagrid('reload');
				}else{
					$('#certificateInfoPublicListDg').datagrid('reload');
				}
			}else{
				$.messager.alert('系统提示', data.info, 'error');
			}
		} 
	});
}

function sfzhmCheckPublic(){
	var sfzhm=$("#certificate_sfzhm_public").val();
	if(sfzhm==null||sfzhm==''){
		return true;
	}
	//验证格式
	 if(!validateIdCard(sfzhm)){
		$.messager.alert('系统提示', "身份证格式不正确，请重新输入！", 'info');
		return false;
	 }
	 setCsrq(sfzhm,'privateCertificateBirthDatePublic');
	 return true;
}
//动态增加的文件个数
var copynum = 1;
//动态增加添加附件的行
function addcopy(id){
    var row,cell,str;
    row = eval("document.all."+id).insertRow();
	    if(row != null ){
			cell = row.insertCell();
			cell.style="border:0";
			str="<input type=\"file\"  id=\"f"+copynum+"\" name=\"publicFiles\" style='width:50%'><input class=\""+"button-1"+"\" type=\""+"button"+"\" value=\""+"删除附件"+"\" onclick=\"deletecopy(this,'copypic');\">"
			cell.innerHTML=str;
		}
	copynum++;
}
//删除附件的行
function deletecopy(obj,id){
	var rowNum,curRow;
	curRow = obj.parentNode.parentNode;
	rowNum = eval("document.all."+id).rows.length - 1;
	eval("document.all["+'"'+id+'"'+"]").deleteRow(curRow.rowIndex);
	copynum--;
}
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
				}else{
					$.messager.alert('系统提示',data.info,'error');
				}
			} 
		}); 
	}
}
</script>
</body>
</html>