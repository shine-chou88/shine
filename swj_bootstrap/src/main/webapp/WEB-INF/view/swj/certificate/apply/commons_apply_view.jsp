<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<p class=MsoNormal style="line-height: 27.0000pt; mso-line-height-rule: exactly;">
	<div style="width: 49%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
		<c:if test="${not empty bean.creatorAssigneeAtt}">
			<div style="float: right;"><img id="p" src="${base}/attachment/IoReadImage/${bean.creatorAssigneeAtt.id}" class="signImg"/></div>
		</c:if>
		<c:if test="${empty bean.creatorAssigneeAtt}">
			<div style="float: right;">${bean.creator.name}</div>
		</c:if>
		<div style="float: right;">申请人签字：</div>
	</div>
	<div style="width: 49%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
		<c:if test="${taskOrganization!='taskOrganization'}">
			<c:if test="${not empty taskOrganization.assigneeAtt}">
				<div style="float: right;"><img id="p" src="${base}/attachment/IoReadImage/${taskOrganization.assigneeAtt.id}" class="signImg"/></div>
			</c:if>
			<c:if test="${empty taskOrganization.assigneeAtt}">
				<div style="float: right;">${taskOrganization.assigneeName}</div>
			</c:if>
		</c:if>
		<div style="float: right;">经办人签字：</div>
	</div>
</p>
<p class=MsoNormal style="line-height: 27.0000pt; mso-line-height-rule: exactly;">
	<div style="width: 49%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;margin-top: 5px;">
		<fmt:formatDate value="${bean.applyTime}" pattern="yyyy"/>年
		<fmt:formatDate value="${bean.applyTime}" pattern="MM"/>月
		<fmt:formatDate value="${bean.applyTime}" pattern="dd"/>日
	</div>
	<div style="width: 49%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;margin-top: 5px;">
		<c:if test="${taskOrganization!='taskOrganization'}"><fmt:formatDate value="${taskOrganization.endTime}" pattern="yyyy"/></c:if>年
		<c:if test="${taskOrganization!='taskOrganization'}"><fmt:formatDate value="${taskOrganization.endTime}" pattern="MM"/></c:if>月
		<c:if test="${taskOrganization!='taskOrganization'}"><fmt:formatDate value="${taskOrganization.endTime}" pattern="dd"/></c:if>日
	</div>
</p>
