<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
	<title>${title}</title>
</head>
<body> 
<script src="${base}/resource/fckeditor/fckeditor.js" type="text/javascript"></script>
 <div class="easyui-layout" style="padding:2px">
 <jsp:include page="/commons/image_upload.jsp"></jsp:include>
 	<div region="center" border="false">
       <form id="articleEditForm" action="${base}/xxfb/save" method="post" enctype="multipart/form-data" data-options="novalidate:true" class="easyui-form">
        <input type="hidden" name="id" value="${bean.id}"/>
        <input type="hidden" name="departIds" id="departIds"/>
          <table width="100%"  border="0" cellpadding="0" cellspacing="0" class="main_table">
          	<tr>
          		<th width="15%">类型：</th>
          		<td width="85%">
          			<select name="type.id" class="w-203">
	       				<c:forEach items="${typeList}" var="type">
	       					<option value="${type.id}"  <c:if test="${type.id==bean.type.id}">selected="selected"</c:if>>${type.name}</option>
	       				</c:forEach>
            		</select>
          		</td>
          	</tr>
          	<tr>
          		<th><span style="color: red;">*&nbsp;</span>标题：</th>
          		<td><input type="text" class="easyui-textbox" data-options="required:true,validType:'length[1,100]'"  name="title" value="${bean.title}" size="80" maxlength="100"/></td>
          	</tr>
          	<tr>
          		<th><span style="color: red;">*&nbsp;</span>发布时间：</th>
          		<td><input type="text" id="releaseTime" name="releaseTime" data-options="required:true" value="${releaseTime}" style="width: 160px;"/></td>
          	</tr>
          	<tr>
          	<tr>
          		<th>内容：</th>
          		<td>
          			<script type="text/javascript">
						var _fck_material_fwNr=new FCKeditor('content');
						_fck_material_fwNr.BasePath='${base}/resource/fckeditor/';
						_fck_material_fwNr.Config["CustomConfigurationsPath"]="${base}/resource/fckeditor/myconfig.js";
						_fck_material_fwNr.Config["ImageBrowser"] = false ;
						_fck_material_fwNr.Config["FlashBrowser"] = false ;
						_fck_material_fwNr.Config["LinkBrowser"] = false ;
						_fck_material_fwNr.Config["MediaBrowser"] = false ;
						_fck_material_fwNr.Height=400;
						_fck_material_fwNr.Width=700;
						_fck_material_fwNr.Value="";
						_fck_material_fwNr.Create();
					</script>
          		</td>
          	</tr>
          	<tr>
          		<th>附件：</th>
          		<td>
          			<table width="100%" id="copypic" border="0px">
						<tr>
							<td>
								<input type="file" name="xxfbFiles"  style='width:50%'/>
								<input type="button" class="button-1" value="添加附件" onclick="addcopy('copypic');" />
							</td>
						</tr>
					</table>
          		</td>
          	</tr>
   		</table>
   		<div region="south" border="false" class="south">
   			<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="articleEditForm()" >保存</a>&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:window.close();">关闭</a>
		</div>
		</form>	
	</div>
 </div>    	
<script type="text/javascript">
$('#releaseTime').datetimebox({
    editable:false
});
function articleEditForm(){
	var flag=false;
	$('#articleEditForm').form('submit', {
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
				window.opener.$("#xxfbList_dg").datagrid('reload');
				window.close();
			}else{
				$.messager.alert('系统提示',data.info,'error');
			}
		} 
	});
}
//动态增加的文件个数
var copynum = 1;
//动态增加添加附件的行
function addcopy(id){
    var row,cell,str;
    row = eval("document.all."+id).insertRow();
	    if(row != null ){
			cell = row.insertCell();
			str="<input type=\"file\"  id=\"f"+copynum+"\" name=\"xxfbFiles\" style='width:50%'><input class=\""+"button-1"+"\" type=\""+"button"+"\" value=\""+"删除附件"+"\" onclick=\"deletecopy(this,'copypic');\">"
			cell.innerHTML=str;
		}
	copynum++;
}
//删除附件的行
function deletecopy(obj,id){
	var curRow;
	curRow = obj.parentNode.parentNode;
	rowNum = eval("document.all."+id).rows.length - 1;
	eval("document.all["+'"'+id+'"'+"]").deleteRow(curRow.rowIndex);
	copynum--;
}
</script>
</body>
</html>