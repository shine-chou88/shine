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
	<div class="easyui-layout" fit="true"
		style="tab-interval: 21pt; text-justify-trim: punctuation;">
		<div region="center" border="false">
			<div class="easyui-tabs" border="0" fit="true">
				<div title="因私出国（境）审批">
					<div class="Section0"
						style="layout-grid: 15.6000pt; padding-top: 10px;">
						<p class=MsoNormal align=center style="text-align: center;">
							<b><span
								style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
									face="黑体">编号：</font></span></b><b><u><span
									style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; text-decoration: underline; text-underline: single; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;">${bean.wcode}</span></u></b><b><span
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
											style="text-indent: 30.0000pt; mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">本人计划于</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><fmt:formatDate
														value="${bean.startDate}" pattern="yyyy" /></span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">年</span><u><span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><fmt:formatDate value="${bean.startDate}" pattern="MM" /></span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">月</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><fmt:formatDate
														value="${bean.startDate}" pattern="dd" /></span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">日至</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><fmt:formatDate
														value="${bean.endDate}" pattern="yyyy" /></span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">年</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><fmt:formatDate
														value="${bean.endDate}" pattern="MM" /></span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">月</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><fmt:formatDate
														value="${bean.endDate}" pattern="dd" /></span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">日因（何事）</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">${bean.reason}</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（探亲、旅游、访友等）赴
												（至何地）</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">${bean.destination}</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;，行程线路是：</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">${bean.itinerary}</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">，为期</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">${bean.days}</span></u><span
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
											style="text-indent: 30.0000pt; mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">普通护照（
												<c:if
													test="${fn:contains(bean.applyHandleType,'privatePassport')}">√</c:if>）港澳通行证（<c:if
													test="${fn:contains(bean.applyHandleType,'privateHKAndMacaoPass')}">√</c:if>）台湾通行证（
												<c:if
													test="${fn:contains(bean.applyHandleType,'privateTaiwanPass')}">√</c:if>）
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
											style="text-indent: 30.0000pt; mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">普通护照（
												<c:if
													test="${fn:contains(bean.applyUseType,'privatePassport')}">√</c:if>）港澳通行证（
												<c:if
													test="${fn:contains(bean.applyUseType,'privateHKAndMacaoPass')}">√</c:if>）台湾通行证（
												<c:if
													test="${fn:contains(bean.applyUseType,'privateTaiwanPass')}">√</c:if>）
											</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<b><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">3、（
													<c:if test="${bean.hasParty=='T'}">√</c:if>）本人是中共党员，出国（境）期间党籍保留在原单位。
											</span></b><b><span
												style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
										</p>
										<jsp:include page="/WEB-INF/view/swj/certificate/apply/commons_apply_view.jsp"></jsp:include>
									</td>
								</tr>
								<!-- 记录 -->
								<jsp:include page="/WEB-INF/view/swj/certificate/apply/apply_record_readonly.jsp"></jsp:include>
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
				</div>
				<c:if test="${fn:length(bean.processInstanceId)>0}">
					<div title="审批流程">
						<jsp:include
							page="/activiti/process/viewByProcessInstanceId/${bean.processInstanceId}"></jsp:include>
					</div>
				</c:if>
			</div>
		</div>
		<div region="south" border="false"
			style="text-align: center; height: 45px; padding-top: 10px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
		</div>
	</div>
</body>
</html>