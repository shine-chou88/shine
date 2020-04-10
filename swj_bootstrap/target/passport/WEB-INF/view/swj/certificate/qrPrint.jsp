<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="txt/html; charset=UTF-8" />
	<title>${title}</title>
</head>
<body style="background:transparent;"> 
	<table cellspacing="0" class="a_table" width="100%">
		<tr>
			<td>
			<!--startprint-->
			<div id="dlg-view" width="350px" height="220px">
			<div  style="margin-left:40px;width: 210px;float:left">
				<img id="" style="width: 200px;height: 195px"
					src="${base}/attachment/IoQrCodeImg/${bean.id}"></img>
				</div>
			<div  style="padding-left:15px;width: 100px;float:left">
					<span style="font-size:18px">${bean.name}&nbsp;&nbsp;</span>
					<br><br>
					<span style="font-size:20px">${fn:substring(bean.id,fn:length(bean.id)-22,fn:length(bean.id)-14)}</span>
					<br><br>
					<span style="font-size:20px">${curDate}</span>
					<br><br>
					<span style="font-size:18px">${bean.briefType}&nbsp;&nbsp;</span>
					<br>
				</div>
			</div>
			<!--endprint-->
			</td>
		
		</tr>
		
	</table>
		
	
<script type="text/javascript">
function iframePrint(){    //添加打印事件
    window.print();
 }
</script>
</body>
</html>