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
		<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
	<tr>
		<th class="br"width="30%">请扫描:</th>
		<td class="br"><input id="elementIdScanPrivateExpire" type="text" style="width:300px;" /><span id="showScanExpireBack" style="color: red;">&nbsp;&nbsp;请扫描</span>
		</td>
		<input type="hidden"  id="privateExpire" value="${expireId}"/>
	</tr>
	</table>

	<table cellpadding="7" cellspacing="0" class="a_table" width="100%">	
	<tr>
		<td class="br" colspan="2">
							<div 
								style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
									<a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-cancel" onclick="closeFreeWindow('child_window_1')">关闭</a>
							</div>
		</td>
	</tr>
</table>

</div>
	<script type="text/javascript">
	$(function(){
		$("#elementIdScanPrivateExpire").focus();
		$('#elementIdScanPrivateExpire').bind('keyup', function(event) {
	        if (event.keyCode == "13") {
	            //回车执行查询
	            expireScannerPrivateBack();
	        }
	  });
	})
	function expireScannerPrivateBack(){
		var value=$("#elementIdScanPrivateExpire").val().trim();
		if(value!=''){
			if($("#privateExpire").val()==value){
				$.ajax({ 
					type: 'POST', 
					url: base+'/certificateInfo/expireScanSave/'+value,
					dataType: 'json',  
					success: function(data){
						if(data.success){
							$.messager.alert('提示', data.info ,'info', function (fn) {
								top.location.reload();
	                        });
						}else{
							$.messager.show({
								title:'提示信息',
								msg:data.info,
								timeout:3000,
								showType:'slide'
							});
						}
					},
					error:function(){
						$.messager.show({
							title:'提示信息',
							msg:"发生异常，请联系管理员",
							timeout:3000,
							showType:'slide'
						});
					}
				}); 
			}else{
				$.messager.show({
					title:'提示信息',
					msg:"对不起，扫描信息与该证照信息不符!",
					timeout:3000,
					showType:'slide'
				});
			}
		}	
		
	}
	</script>
</body>
</html>