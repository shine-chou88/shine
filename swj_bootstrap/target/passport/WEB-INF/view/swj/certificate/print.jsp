<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="txt/html; charset=UTF-8" />
	<title>${title}</title>
</head>
<body> 
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
	<table cellspacing="0" class="a_table" width="100%">
		<tr>
			<td>
			<iframe id="childQrCodeIframe"name="qrPrint" src="${base}/certificateInfo/printQrcodeChild/${bean.id}" width="380px" height="205px" border="1" frameborder="1" scrolling="no" marginheight="0" marginwidth="0" allowtransparency="true"></iframe>
<!-- 			<!--startprint--> 
<!-- 			<div id="dlg-view" width="350px" height="210px"> -->
<!-- 			<div  style="margin-left:40px;width: 200px;float:left"> -->
<!-- 				<img id="" style="width: 200px;height: 200px" -->
<%-- 					src="${base}/resource/QrCodeImages/${bean.qrCodeUrl}"></img> --%>
<!-- 				</div> -->
<!-- 			<div  style="padding-left:20px;width: 100px;float:left"> -->
<!-- 			<br><br> -->
<%-- 					<span style="font-size:18px">&nbsp;&nbsp;${bean.name}&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
<!-- 					<br><br><br> -->
<%-- 					<span style="font-size:20px">${fn:substring(bean.id,fn:length(bean.id)-8,fn:length(bean.id))}</span> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<!--endprint--> 
			</td>
		
		</tr>
		
		<tr><td><div style="display: flex; align-items: center; justify-content: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
		iconcls="icon-print" onclick="printContent()">打印</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton"
		iconcls="icon-printComplete" onclick="printComplete()">打印完成</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton"
		iconcls="icon-cancel" onclick="closeFreeWindow('four_window');">关闭</a>
	
	</div></td></tr>
	</table>
		
			
</div>	
	
<script type="text/javascript">
	function printContent(){
		if (navigator.userAgent.indexOf('Firefox') >= 0){
			document.getElementById('childQrCodeIframe').contentWindow.iframePrint();
		}else{
			alert("请使用火狐浏览器进行打印操作");
		}
	}
	function printComplete(){
		$.ajax({ 
			type: 'POST', 
			url: base+'/certificateInfo/printComplete/${bean.id}',
			dataType: 'json',  
			success: function(data){
					closeFreeWindow('four_window');
			} 
		}); 
	}
</script>
</body>
</html>