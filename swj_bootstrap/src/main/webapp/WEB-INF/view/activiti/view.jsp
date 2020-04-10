<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
    <div class="easyui-layout" fit="true">
    	<div region="center" border="false">
			<table border="0" cellpadding="0" cellspacing="0" class="view_table">
				<tr>
					<td><img src="${base}/activiti/process/viewDiagram/${processDefinitionId}"/></td>
				</tr>
			</table>
		</div>
		<div region="south" border="false" style="text-align: center;padding: 5px 5px 5px 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
		</div>
	</div>
</body>
</html>

