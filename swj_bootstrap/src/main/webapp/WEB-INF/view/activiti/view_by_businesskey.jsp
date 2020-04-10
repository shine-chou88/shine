<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
    <div class="easyui-layout"  style="overflow: auto;height:auto;">
		<table border="0" cellpadding="0" cellspacing="0" class="view_table">
			<tr>
				<td colspan="5"><img src="${base}/activiti/process/viewDiagramByProcessInstanceId?processInstanceId=${processInstanceId}&random=${random}"/></td>
			</tr>
			<tr>
				<th width="20%">名称</th>
				<th width="35%">意见</th>
				<th width="10%">操作人</th>
				<th width="15%">开始时间</th>
				<th width="15%">结束时间</th>
			</tr>
			<c:forEach items="${listTaskHis}" var="t">
				<tr>
					<td>${t.taskName}&nbsp;</td>
					<td>${t.comment}&nbsp;</td>
					<td>${t.assigneeName}&nbsp;</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${t.startTime}"/>&nbsp;</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${t.endTime}"/>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>

