﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<script type="text/javascript">
//上传图片
function upload(n) {
	var of = $('#uploadFile'+n);
	//检查是否选择了图片
	if(of.val()=='') {
		alert('请选择要上传的图片');
		return;
	}
	//将file移动至上传表单
	$('#fileContent').empty();
	$('#fileContent').append(of);
	//复制一个file放至原处
	$('#ufc'+n).append(of.clone());
	//修改属性
	of.attr('id','');
	of.attr('name','uploadFile');
	//其他表单
	if($('#zoom'+n).attr('checked')) {
		$('#ufZoom').val('true');
	} else {
		$('#ufZoom').val('false');
	}
	$('#ufWidth').val($('#zoomWidth'+n).val());
	$('#ufHeight').val($('#zoomHeight'+n).val());
	$('#uploadNum').val(n);
	var flag=false;
	$('#uploadForm').form('submit', {
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
				var arr=data.info.split(",");
				parent.document.getElementById('attachmentId').value=arr[0];
				var imgSrc = parent.document.getElementById('preImg1');
				$(imgSrc).attr("src","${base}/upload/jyyw"+arr[1]);
			}else{
				$.messager.alert('系统提示',data.info,'error');
				$('#uploadForm').form('clear');
			}
		} 
	});
}

//清除图片
function clearImg(n) {
	$('#uploadImgPath'+n).val("");
	$('#preImg'+n).attr("src","");
}
</script>
<form id="uploadForm" action="${base}/attachment/uploadImg" data-options="novalidate:true" class="easyui-form" method="post" enctype="multipart/form-data" target="hiddenIframe" style="display:none;width:0px;height:0px;">
<span id="fileContent"></span>
<input id="ufZoom" type="hidden" name="zoom"/>
<input id="ufWidth" type="hidden" name="zoomWidth"/>
<input id="ufHeight" type="hidden" name="zoomHeight"/>
<input id="uploadNum" type="hidden" name="uploadNum"/>
</form>
<iframe name="hiddenIframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>