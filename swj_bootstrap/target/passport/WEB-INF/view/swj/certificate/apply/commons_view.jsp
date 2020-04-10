<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<c:if test="${bean.processDefinitionKey=='DEPUTY_CADRES_PASSPORT_APPLY' || (currentUser.hasRole('DEPUTY_CADRES')==true && (bean.auditStatus=='draft' || fn:length(bean.id)==0))}">
<tr style="height: 102.1000pt;">
	<td width=64 valign=center
		style="width: 48.0000pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: 1.0000pt solid windowtext; mso-border-left-alt: 0.5000pt solid windowtext; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal align=center
			style="text-align: center; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<b><span
				style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">单位（部门）审核意见</span></b><b><span
				style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
		</p></td>
	<td width=523 valign=top
		style="width: 392.6500pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: none; mso-border-left-alt: none; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal
			style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<c:if test="${taskDepart!='taskDepart'}">${taskDepart.comment}</c:if></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal style="text-indent: 202.5000pt; mso-char-indent-count: 13.5000; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<div style="width: 98%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
				<c:if test="${taskDepart!='taskDepart'}">
					<c:if test="${not empty taskDepart.assigneeAtt}">
						<div style="float: right;"><img id="p" src="${base}/attachment/IoReadImage/${taskDepart.assigneeAtt.id}" class="signImg"/></div>
					</c:if>
					<c:if test="${empty taskDepart.assigneeAtt}">
						<div style="float: right;">${taskDepart.assigneeName}</div>
					</c:if>
				</c:if>
				<div style="float: right;">负责人签字：</div>
			</div>
		</p>
		<p class=MsoNormal
			style="margin-right: 16.0000pt; text-indent: 277.5000pt;">
			&nbsp;
		</p>
		<p class=MsoNormal align=center style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<div style="width: 98%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
				<c:if test="${taskDepart!='taskDepart'}"><fmt:formatDate value="${taskDepart.endTime}" pattern="yyyy"/></c:if>年
				<c:if test="${taskDepart!='taskDepart'}"><fmt:formatDate value="${taskDepart.endTime}" pattern="MM"/></c:if>月
				<c:if test="${taskDepart!='taskDepart'}"><fmt:formatDate value="${taskDepart.endTime}" pattern="dd"/></c:if>日
			</div>
		</p>
	</td>
</tr>
</c:if>
<tr style="height: 89.0000pt;">
	<td width=64 valign=center
		style="width: 48.0000pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: 1.0000pt solid windowtext; mso-border-left-alt: 0.5000pt solid windowtext; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal align=center
			style="text-align: center; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<b><span
				style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">局组织人事处审核意见</span></b><b><span
				style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
		</p></td>
	<td width=523 valign=top
		style="width: 392.6500pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: none; mso-border-left-alt: none; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal
			style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<c:if test="${taskDirector!='taskDirector'}">${taskDirector.comment}</c:if></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal
			style="text-indent: 202.5000pt; mso-char-indent-count: 13.5000; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<div style="width: 98%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
				<c:if test="${taskDirector!='taskDirector'}">
					<c:if test="${not empty taskDirector.assigneeAtt}">
						<div style="float: right;"><img id="p" src="${base}/attachment/IoReadImage/${taskDirector.assigneeAtt.id}" class="signImg"/></div>
					</c:if>
					<c:if test="${empty taskDirector.assigneeAtt}">
						<div style="float: right;">${taskDirector.assigneeName}</div>
					</c:if>
				</c:if>
				<div style="float: right;">负责人签字：</div>
			</div>
		</p>
		<p class=MsoNormal
			style="margin-right: 16.0000pt; text-indent: 277.5000pt;">
			&nbsp;
		<p class=MsoNormal align=center style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<div style="width: 98%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
				<c:if test="${taskDirector!='taskDirector'}"><fmt:formatDate value="${taskDirector.endTime}" pattern="yyyy"/></c:if>年
				<c:if test="${taskDirector!='taskDirector'}"><fmt:formatDate value="${taskDirector.endTime}" pattern="MM"/></c:if>月 
				<c:if test="${taskDirector!='taskDirector'}"><fmt:formatDate value="${taskDirector.endTime}" pattern="dd"/></c:if>日
			</div>
		</p>
	</td>
</tr>
<tr style="height: 94.5000pt;">
	<td width=64 valign=center
		style="width: 48.0000pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: 1.0000pt solid windowtext; mso-border-left-alt: 0.5000pt solid windowtext; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal align=center
			style="text-align: center; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<b><span
				style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">分管局领导审批意见</span></b><b><span
				style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
		</p></td>
	<td width=523 valign=top
		style="width: 392.6500pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: none; mso-border-left-alt: none; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal
			style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<c:if test="${taskFgLeader!='taskFgLeader'}">${taskFgLeader.comment}</c:if></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left: 36.0000pt;line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal align=center
			style=" line-height: 25.0000pt; mso-line-height-rule: exactly;text-indent: 42.5000pt; ">
			<div style="width: 98%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
				<c:if test="${taskFgLeader!='taskFgLeader'}">
					<c:if test="${not empty taskFgLeader.assigneeAtt}">
						<div style="float: right;"><img id="p" src="${base}/attachment/IoReadImage/${taskFgLeader.assigneeAtt.id}" class="signImg"/></div>
					</c:if>
					<c:if test="${empty taskFgLeader.assigneeAtt}">
						<div style="float: right;">${taskFgLeader.assigneeName}</div>
					</c:if>
				</c:if>
				<div style="float: right;">负责人签字：</div>
			</div>
		</p>
		<p class=MsoNormal
			style="margin-right: 16.0000pt; text-indent: 277.5000pt;">
			&nbsp;
		</p>
		<p class=MsoNormal align=center
			style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<div style="width: 98%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
				<c:if test="${taskFgLeader!='taskFgLeader'}"><fmt:formatDate value="${taskFgLeader.endTime}" pattern="yyyy"/></c:if>年
				<c:if test="${taskFgLeader!='taskFgLeader'}"><fmt:formatDate value="${taskFgLeader.endTime}" pattern="MM"/></c:if>月 
				<c:if test="${taskFgLeader!='taskFgLeader'}"><fmt:formatDate value="${taskFgLeader.endTime}" pattern="dd"/></c:if>日
			</div>
		</p>
	</td>
</tr>
<tr style="height: 94.5000pt;">
	<td width=64 valign=center
		style="width: 48.0000pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: 1.0000pt solid windowtext; mso-border-left-alt: 0.5000pt solid windowtext; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal align=center
			style="text-align: center; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<b><span
				style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">局主要领导审批意见</span></b><b><span
				style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
		</p></td>
	<td width=523 valign=top
		style="width: 392.6500pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: none; mso-border-left-alt: none; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal
			style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<c:if test="${taskLeader!='taskLeader'}">${taskLeader.comment}</c:if></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left: 36.0000pt;line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<span
				style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal align=center
			style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;text-indent: 72.5000pt;">
			<div style="width: 98%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
				<c:if test="${taskLeader!='taskLeader'}">
					<c:if test="${not empty taskLeader.assigneeAtt}">
						<div style="float: right;"><img id="p" src="${base}/attachment/IoReadImage/${taskLeader.assigneeAtt.id}" class="signImg"/></div>
					</c:if>
					<c:if test="${empty taskLeader.assigneeAtt}">
						<div style="float: right;">${taskLeader.assigneeName}</div>
					</c:if>
				</c:if>
				<div style="float: right;">局主要领导签字：</div>
			</div>
		</p>
		<p class=MsoNormal
			style="margin-right: 16.0000pt; text-indent: 277.5000pt;">
			&nbsp;
		</p>
		<p class=MsoNormal align=center
			style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<div style="width: 98%;float: left;text-align: right;font-family: 仿宋_GB2312;font-size: 15.0000pt;">
				<c:if test="${taskLeader!='taskLeader'}"><fmt:formatDate value="${taskLeader.endTime}" pattern="yyyy"/></c:if>年
				<c:if test="${taskLeader!='taskLeader'}"><fmt:formatDate value="${taskLeader.endTime}" pattern="MM"/></c:if>月 
				<c:if test="${taskLeader!='taskLeader'}"><fmt:formatDate value="${taskLeader.endTime}" pattern="dd"/></c:if>日
			</div>
		</p>
	</td>
</tr>
