<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
	<title>${title}</title>
</head>
<body> 
<style>
@font-face {
	font-family: "Times New Roman";
}

@font-face {
	font-family: "宋体";
}

@font-face {
	font-family: "Wingdings";
}

@font-face {
	font-family: "方正小标宋简体";
}

@font-face {
	font-family: "黑体";
}

@font-face {
	font-family: "仿宋_GB2312";
}

p.MsoNormal {
	mso-style-name: 正文;
	mso-style-parent: "";
	margin: 0pt;
	margin-bottom: .0001pt;
	mso-pagination: none;
	text-align: justify;
	text-justify: inter-ideograph;
	font-family: 'Times New Roman';
	font-size: 10.5000pt;
	mso-font-kerning: 1.0000pt;
}

span.10 {
	font-family: 'Times New Roman';
}

span.15 {
	font-family: 'Times New Roman';
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

span.16 {
	font-family: 'Times New Roman';
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

span.17 {
	font-family: 'Times New Roman';
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

span.18 {
	font-family: 方正小标宋简体;
	font-size: 22.0000pt;
	mso-font-kerning: 1.0000pt;
}

p.MsoFooter {
	mso-style-name: 页脚;
	margin: 0pt;
	margin-bottom: .0001pt;
	layout-grid-mode: char;
	mso-pagination: none;
	text-align: left;
	font-family: 'Times New Roman';
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

p.MsoHeader {
	mso-style-name: 页眉;
	margin: 0pt;
	margin-bottom: .0001pt;
	border-bottom: 1.0000pt solid windowtext;
	mso-border-bottom-alt: 0.7500pt solid windowtext;
	padding: 0pt 0pt 1pt 0pt;
	layout-grid-mode: char;
	mso-pagination: none;
	text-align: center;
	font-family: 'Times New Roman';
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

p.MsoAcetate {
	mso-style-name: 批注框文本;
	margin: 0pt;
	margin-bottom: .0001pt;
	mso-pagination: none;
	text-align: justify;
	text-justify: inter-ideograph;
	font-family: 'Times New Roman';
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

p.22 {
	mso-style-name: 发文2号标题;
	margin: 0pt;
	margin-bottom: .0001pt;
	mso-pagination: none;
	text-align: center;
	mso-line-height-alt: 0pt;
	font-family: 方正小标宋简体;
	font-size: 22.0000pt;
	mso-font-kerning: 1.0000pt;
}

span.msoIns {
	mso-style-type: export-only;
	mso-style-name: "";
	text-decoration: underline;
	text-underline: single;
	color: blue;
}

span.msoDel {
	mso-style-type: export-only;
	mso-style-name: "";
	text-decoration: line-through;
	color: red;
}

table.MsoNormalTable {
	mso-style-name: 普通表格;
	mso-style-parent: "";
	mso-style-noshow: yes;
	mso-tstyle-rowband-size: 0;
	mso-tstyle-colband-size: 0;
	mso-padding-alt: 0.0000pt 5.4000pt 0.0000pt 5.4000pt;
	mso-para-margin: 0pt;
	mso-para-margin-bottom: .0001pt;
	mso-pagination: widow-orphan;
	font-family: 'Times New Roman';
	font-size: 10.0000pt;
	mso-ansi-language: #0400;
	mso-fareast-language: #0400;
	mso-bidi-language: #0400;
}

table.MsoTableGrid {
	mso-style-name: 网格型;
	mso-tstyle-rowband-size: 0;
	mso-tstyle-colband-size: 0;
	mso-padding-alt: 0.0000pt 5.4000pt 0.0000pt 5.4000pt;
	mso-border-top-alt: 0.5000pt solid windowtext;
	mso-border-left-alt: 0.5000pt solid windowtext;
	mso-border-bottom-alt: 0.5000pt solid windowtext;
	mso-border-right-alt: 0.5000pt solid windowtext;
	mso-border-insideh: 0.5000pt solid windowtext;
	mso-border-insidev: 0.5000pt solid windowtext;
	mso-para-margin: 0pt;
	mso-para-margin-bottom: .0001pt;
	mso-pagination: none;
	text-align: justify;
	text-justify: inter-ideograph;
	font-family: 'Times New Roman';
	font-size: 10.0000pt;
	mso-ansi-language: #0400;
	mso-fareast-language: #0400;
	mso-bidi-language: #0400;
}

@page {
	mso-page-border-surround-header: no;
	mso-page-border-surround-footer: no;
}

@page Section0 {
	margin-top: 42.5500pt;
	margin-bottom: 42.5500pt;
	margin-left: 89.8500pt;
	margin-right: 89.8500pt;
	size: 595.3000pt 841.9000pt;
	layout-grid: 15.6000pt;
}

div.Section0 {
	page: Section0;
}
</style>
<c:set var="taskOrganization" value="taskOrganization" scope="request"/>
<c:set var="taskDepart" value="taskDepart" scope="request"/>
<c:set var="taskDirector" value="taskDirector" scope="request"/>
<c:set var="taskFgLeader" value="taskFgLeader" scope="request"/>
<c:set var="taskLeader" value="taskLeader" scope="request"/>
<c:forEach items="${listHisTask}" var="task">
	<c:if test="${taskLeader=='taskLeader' && task.taskDefKey=='BUREAU_MAIN_LEADER' && fn:length(task.comment)>0}">
		<c:set var="taskLeader" value="${task}" scope="request"/>		
	</c:if>
	<c:if test="${taskFgLeader=='taskFgLeader' && task.taskDefKey=='BUREAU_DIRECTOR' && fn:length(task.comment)>0}">
		<c:set var="taskFgLeader" value="${task}" scope="request"/>		
	</c:if>
	<c:if test="${taskDirector=='taskDirector' && task.taskDefKey=='ORGANIZATION_DIRECTOR' && fn:length(task.comment)>0}">
		<c:set var="taskDirector" value="${task}" scope="request"/>		
	</c:if>
	<c:if test="${taskDepart=='taskDepart' && task.taskDefKey=='OFFICAL_CADRES' && fn:length(task.comment)>0}">
		<c:set var="taskDepart" value="${task}" scope="request"/>		
	</c:if>
	<c:if test="${taskOrganization=='taskOrganization' && task.taskDefKey=='ORGANIZATION_STAFF' && fn:length(task.comment)>0}">
		<c:set var="taskOrganization" value="${task}" scope="request"/>		
	</c:if>
</c:forEach>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<div class="easyui-tabs" border="0">
			<div title="因私出国（境）审批">
				<form id="certificateInfoApplyEditForm" action="${base}/certificateInfoApply/save" method="post" data-options="novalidate:true" class="easyui-form">
		       		<input type="hidden" name="wcode" value="${bean.wcode}"/>
		       		<input type="hidden" name="id" value="${bean.id}"/>
		       		<input type="hidden" name="applyTime" value="<fmt:formatDate value="${bean.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		       		<input type="hidden" name="auditStatus" value="${bean.auditStatus}" id="certificateInfoApplyAuditStatus"/>
		       		<input type="hidden" name="processInstanceId" value="${bean.processInstanceId}"/>
		       		<input type="hidden" name="processDefinitionKey" value="${bean.processDefinitionKey}"/>
		       		<input type="hidden" id="privateStart" name="privateStart" value="${bean.privateStart }"/>
	<input type="hidden" id="privateEnd" name="privateEnd" value="${bean.privateEnd }"/>
	<input type="hidden" id="privateDestination" name="privateDestination" value="${bean.privateDestination }"/>
	<input type="hidden" id="privateReason" name="privateReason" value="${bean.privateReason }"/>
	<input type="hidden" id="publicStart" name="publicStart" value="${bean.publicStart }"/>
	<input type="hidden" id="publicEnd" name="publicEnd" value="${bean.publicEnd }"/>
	<input type="hidden" id="publicDestination" name="publicDestination" value="${bean.publicDestination }"/>
	<input type="hidden" id="publicReason" name="publicReason" value="${bean.publicReason }"/>
	<input type="hidden" id="privateDateDesc" name="privateDateDesc" value="${bean.privateDateDesc }"/>
	<input type="hidden" id="publicDateDesc" name="publicDateDesc" value="${bean.publicDateDesc }"/>
		       		<div class="Section0"
						style="layout-grid: 15.6000pt; padding-top: 10px;">
						<p class=MsoNormal align=center style="text-align: center;">
							<b><span
								style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
									face="黑体">编号：</font></span></b><b><u><span
									style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; text-decoration: underline; text-underline: single; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;">
									<c:if test="${fn:length(bean.wcode)==0}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
									<c:if test="${fn:length(bean.wcode)>0}">${bean.wcode}</c:if>
								</span></u></b><b><span
								style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;</span></b><b><span
								style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
						</p>
						<p class=MsoNormal align=center
							style="text-align: center; line-height: 20.0000pt; mso-line-height-rule: exactly;">
							<b><span
								style="mso-spacerun: 'yes'; font-family: 宋体; font-weight: bold; font-size: 18.0000pt; mso-font-kerning: 1.0000pt;"><font
									face="宋体">上海市水务局（上海市海洋局）</font></span></b><b><span
								style="mso-spacerun: 'yes'; font-family: 宋体; font-weight: bold; font-size: 18.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
						</p>
						<p class=MsoNormal align=center
							style="text-align: center; line-height: 20.0000pt; mso-line-height-rule: exactly;">
							<b><span
								style="mso-spacerun: 'yes'; font-family: 宋体; font-weight: bold; font-size: 18.0000pt; mso-font-kerning: 1.0000pt;"><font
									face="宋体">现职处级干部因私事出国（境）审批表</font></span></b><b><span
								style="mso-spacerun: 'yes'; font-family: 宋体; font-weight: bold; font-size: 18.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
						</p>
						<p class=MsoNormal align=center style="text-align: center;">
							<b><span
								style="mso-spacerun: 'yes'; font-family: 宋体; font-weight: bold; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span></b>
						</p>
						<div align=center>
							<table class=MsoNormalTable border=1 cellspacing=0
								style="border-collapse: collapse; width: 440.6500pt; mso-table-layout-alt: fixed; border: none; mso-border-left-alt: 0.5000pt solid windowtext; mso-border-top-alt: 0.5000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext; mso-border-insideh: 0.5000pt solid windowtext; mso-border-insidev: 0.5000pt solid windowtext; mso-padding-alt: 0.0000pt 5.4000pt 0.0000pt 5.4000pt;">
								<tr style="height: 285.2000pt;">
									<td width=64 valign=center
										style="width: 48.0000pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: 1.0000pt solid windowtext; mso-border-left-alt: 0.5000pt solid windowtext; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: 1.0000pt solid windowtext; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
											class=MsoNormal align=center style="text-align: center;">
											<b><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">申请事项</span></b><b><span
												style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
										</p></td>
									<td width=523 valign=top
										style="width: 392.6500pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: none; mso-border-left-alt: none; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: 1.0000pt solid windowtext; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
											class=MsoNormal
											style="mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">本人计划于</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><input  type="text" readonly="readonly" name="startDate" value="<fmt:formatDate value="${bean.startDate}" pattern="yyyy-MM-dd"/>" id="startDate" class="Wdate"  data-options="required:true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})" style="width:100px" ></input></span></u>至</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><input type="text" readonly="readonly" name="endDate" value="<fmt:formatDate value="${bean.endDate}" pattern="yyyy-MM-dd"/>" id="endDate" class="Wdate" data-options="required:true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})" style="width:100px" ></input></span></u>
												<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">因（何事）</span><u>
												<span >
												<input class="easyui-textbox" type="text" name="reason" data-options="multiline:true,required:true"  id="reason" style="width:500px;height: 50px;" value="${bean.reason}" />
												</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;float: left;">（探亲、旅游、访友等）赴
												（至何地）</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												<input class="easyui-textbox" type="text"  data-options="multiline:true,required:true" name="destination" id="destination" style="width:500px;height: 50px;" value="${bean.destination}" />
												</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;float: left;">，行程线路是：</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												<input class="easyui-textbox" type="text" data-options="multiline:true,required:true" name="itinerary" id="itinerary" style="width:500px;height: 50px;" value="${bean.itinerary}" />
												</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><br/>，为期</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><input class="easyui-numberbox" precision="0" type="text"  data-options="required:true" name="days" id="days" style="width:50px" value="${bean.days}" /></span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">天
												，费用自理。</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<b><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">1、申请办理因私出国（境）证件：</span></b><b><span
												style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
										</p>
										<p class=MsoNormal
											style="text-indent: 20.0000pt; mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												普通护照（<input type="checkbox" name="applyHandleType" value="privatePassport" <c:if test="${fn:contains(bean.applyHandleType,'privatePassport')}">checked="checked"</c:if>/>）
												港澳通行证（<input type="checkbox" name="applyHandleType" value="privateHKAndMacaoPass" <c:if test="${fn:contains(bean.applyHandleType,'privateHKAndMacaoPass')}">checked="checked"</c:if>/>）
												台湾通行证（<input  type="checkbox" name="applyHandleType" value="privateTaiwanPass" <c:if test="${fn:contains(bean.applyHandleType,'privateTaiwanPass')}">checked="checked"</c:if>/>）
											</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<b><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">2、申请领用因私出国（境）证件：</span></b><b><span
												style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
										</p>
										<p class=MsoNormal
											style="text-indent: 20.0000pt; mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												普通护照（<input  type="checkbox" name="applyUseType" value="privatePassport" <c:if test="${fn:contains(bean.applyUseType,'privatePassport')}">checked="checked"</c:if>/>）
												港澳通行证（<input  type="checkbox" name="applyUseType" value="privateHKAndMacaoPass" <c:if test="${fn:contains(bean.applyUseType,'privateHKAndMacaoPass')}">checked="checked"</c:if>/>）
												台湾通行证（<input  type="checkbox" name="applyUseType" value="privateTaiwanPass" <c:if test="${fn:contains(bean.applyUseType,'privateTaiwanPass')}">checked="checked"</c:if>/>）
											</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<b><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												3、（<input type="checkbox" name="hasParty" value="T" <c:if test="${bean.hasParty=='T'}">checked="checked"</c:if>/>）本人是中共党员，出国（境）期间党籍保留在原单位。
											</span></b><b><span
												style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
										</p>
										<jsp:include page="/WEB-INF/view/swj/certificate/apply/commons_apply_view.jsp"></jsp:include>
									</td>
								</tr>
								<!-- 记录 -->
<%-- 								<jsp:include page="/WEB-INF/view/swj/certificate/apply/apply_record_edit.jsp"></jsp:include> --%>
								<jsp:include page="/WEB-INF/view/swj/certificate/apply/commons_view.jsp"></jsp:include>
								<tr style="height: 36.4000pt;">
									<td width=64 valign=center
										style="width: 48.0000pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: 1.0000pt solid windowtext; mso-border-left-alt: 0.5000pt solid windowtext; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
											class=MsoNormal align=center
											style="text-align: center; line-height: 20.0000pt; mso-line-height-rule: exactly;">
											<b><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">备注</span></b><b><span
												style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
										</p></td>
									<td width=523 valign=top
										style="width: 392.6500pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: none; mso-border-left-alt: none; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
											class=MsoNormal>
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;${bean.fileSummary}</o:p></span>
										</p></td>
								</tr>
							</table>
						</div>
						<p>
							<span>&nbsp;</span>
						</p>
					</div>
				</form>
			</div>
			<c:if test="${fn:length(bean.processInstanceId)>0}">
				<div title="审批流程">
			  		 <jsp:include page="/activiti/process/viewByProcessInstanceId/${bean.processInstanceId}"></jsp:include>
				</div>
    	    </c:if>
		</div>
	</div>
	<div region="south" border="false" style="text-align: center; height: 45px;padding-top: 10px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconcls="icon-ok" onclick="saveCertificateInfoApply('draft')">暂存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconcls="icon-ok" onclick="commitCertificateInfoApply('toApprove')">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
	</div>
</div>    	
<script type="text/javascript">
function commitCertificateInfoApply(auditStatus){
	$.messager.confirm('系统提示','提交之后数据不能修改，确认提交吗?',function(id){ 
		if(id){
			saveCertificateInfoApply(auditStatus);
		}
	});
}
function saveCertificateInfoApply(auditStatus){
	var flag=false;
	var certificateInfoApplyId="${bean.id}";
	if(certificateInfoApplyId.length>0){
		if(auditStatus=='toApprove'){
			$("#certificateInfoApplyAuditStatus").val(auditStatus);
		}
	}else{
		$("#certificateInfoApplyAuditStatus").val(auditStatus);
	}
	$('#certificateInfoApplyEditForm').form('submit', {
		onSubmit: function(){
			var startDate=$("#startDate").val();
			var endDate=$("#endDate").val();
			if(startDate.length==0 || endDate.length==0){
				$.messager.alert('系统提示','请选择时间！', 'info');
				return false;
			}
			var applyUseType = $("input:checkbox[name='applyUseType']:checked");
			var applyHandleType = $("input:checkbox[name='applyHandleType']:checked");
			if(applyUseType.length==0 && applyHandleType.length==0){
				$.messager.alert('系统提示','申请办理证件和申请领用证件必须选择一项！', 'info');
				return false;
			}
			if(applyUseType.length>=1 && applyHandleType.length>=1){
				$.messager.alert('系统提示','申请办理证件和申请领用证件只能选择一项！', 'info');
				return false;					
			}
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
				$('#certificateInfoApplyEditForm').form('clear');
				$('#certificateInfoApplyListDg').datagrid('reload');
				$('#activiti_task_dg').datagrid('reload');
				$.ajax({ 
					type: 'POST', 
					url: '${base}/index/getUnReadMsg',
					dataType: 'json',  
					success: function(data){
						if(data.success){
							$("#unReadTaskSpan").html(data.info);
						}
					} 
				}); 
				document.getElementById('indexIframe').src="${base}/index/indexPart";
			}else{
				$.messager.alert('系统提示', data.info, 'error');
			}
		} 
	});
}


</script>
</body>
</html>