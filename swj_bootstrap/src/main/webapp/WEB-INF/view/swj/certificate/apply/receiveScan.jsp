<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<title>${title}</title>
</head>

<body>
<style type="text/css">
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
.myicon-tick-checked {
display: inline-block;
position: relative;
width: 15px;
height: 15px;
border-radius: 50%;
background-color: #2ac845;
}
.myicon-tick-checked:before, .myicon-tick-checked:after {
content: '';
pointer-events: none;
position: absolute;
color: white;
border: 1px solid;
background-color: white;
}


.myicon-tick-checked:before {
width: 1px;
height: 1px;
left: 25%;
top: 50%;
transform: skew(0deg,50deg);
}


.myicon-tick-checked:after {
width: 3px;
height: 1px;
left: 45%;
top: 42%;
transform: skew(0deg,-50deg);
}

</style>
	<div class="easyui-layout" fit="true">
		<table cellpadding="5" cellspacing="0" class="a_table" width="100%">
	<tr>
		<th class="br"width="30%">请扫描:</th>
		<td class="br"><input id="elementIdScanPrivateReceive" type="text" style="width:300px;" /><span id="showScanContinueTipPrivateReceive" style="color: red;">&nbsp;&nbsp;请继续扫描</span>
		</td>
		<input type="hidden" id="shouldScanReceiveNumPrivate" value=""/>
		<input type="hidden"  id="privateApplyIdReceive" value=""/>
	</tr>
	</table>
					<div class="Section0"
						style="layout-grid: 15.6000pt; padding-top: 10px;height:520px;overflow-y:auto ">
						<p class=MsoNormal align=center style="text-align: center;">
							<b><span
								style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
									face="黑体">编号：</font></span></b><b><u><span
									style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; text-decoration: underline; text-underline: single; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanWcode">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u></b><b><span
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
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanStartDateYear">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">年</span><u><span id="receiveScanStartDateMonth" style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;</span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">月</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanStartDateDay">
														&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">日至</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanEndDateYear">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">年</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanEndDateMonth">
												&nbsp;&nbsp;&nbsp;</span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">月</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanEndDateDay">
												&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">日因（何事）</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanReason">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（探亲、旅游、访友等）赴
												（至何地）</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanDestination">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;，行程线路是：</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanItinerary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">，为期</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanDays">&nbsp;&nbsp;&nbsp;</span></u><span
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
											style="text-indent: 5.0000pt; mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">普通护照（
												<span id="type1SpanReceive"></span>）港澳通行证（<span id="type2SpanReceive"></span>
												）台湾通行证（<span id="type3SpanReceive"></span>
												）
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
											style="text-indent: 5.0000pt; mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">普通护照（&nbsp;
												）港澳通行证（&nbsp;）台湾通行证（&nbsp;）
											</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<b><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">3、（
													<span id="receiveScanHasParty"></span>）本人是中共党员，出国（境）期间党籍保留在原单位。
											</span></b><b><span
												style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
										</p>
										<p class=MsoNormal
											style="line-height: 27.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">申请人签字：<span id="receiveScanCreatorName"></span>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经办人签字：<span id="taskOrganizationAssignReceive"></span></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 27.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="receiveScanApplyTime"></span>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="taskOrganizationYearReceive"/>年
												<span id="taskOrganizationMonthReceive"/>月 <span id="taskOrganizationDayReceive"/>日</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p></td>
								</tr>
	<!-- 因私出国记录开始 -->							
	<tr>
	<td width=64 valign=center
		style="width: 48.0000pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: 1.0000pt solid windowtext; mso-border-left-alt: 0.5000pt solid windowtext; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal align=center
			style="text-align: center; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<b><span
				style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">因私出国(境)<br>记录</span></b><b><span
				style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
		</p></td>
	<td width=523 valign=top style="width: 392.6500pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: none; mso-border-left-alt: none; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: 1.0000pt solid windowtext; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;">
	<p class=MsoNormal style="mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
	<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanPrivateRecord">
	</span>
	</p>
	</td>
</tr>
<!-- 因私出国记录结束 -->
<!-- 因公出国记录开始 -->
<tr>
	<td width=64 valign=center
		style="width: 48.0000pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: 1.0000pt solid windowtext; mso-border-left-alt: 0.5000pt solid windowtext; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: none; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;"><p
			class=MsoNormal align=center
			style="text-align: center; line-height: 17.0000pt; mso-line-height-rule: exactly;">
			<b><span
				style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">因公出国(境)<br>记录</span></b><b><span
				style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
		</p></td>
	<td width=523 valign=top style="width: 392.6500pt; padding: 0.0000pt 5.4000pt 0.0000pt 5.4000pt; border-left: none; mso-border-left-alt: none; border-right: 1.0000pt solid windowtext; mso-border-right-alt: 0.5000pt solid windowtext; border-top: 1.0000pt solid windowtext; mso-border-top-alt: 0.5000pt solid windowtext; border-bottom: 1.0000pt solid windowtext; mso-border-bottom-alt: 0.5000pt solid windowtext;">
	<p class=MsoNormal style="mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
	
		<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="receiveScanPublicRecord">
		</span>
		
	
	</p>
	</td>
</tr>
<!-- 因公出国记录结束 -->								
								<tr id="departReceiveTrElement" style="height: 102.1000pt;">
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
											style="line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<span id="taskDepartCommentReceive" /></o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="text-indent: 202.5000pt; mso-char-indent-count: 13.5000; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">负责人签字：<span id="taskDepartAssignReceive" /></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-right: 16.0000pt; text-indent: 277.5000pt; mso-char-indent-count: 18.5000; text-align: left; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-right: 16.0000pt; text-indent: 215.5000pt; mso-char-indent-count: 18.5000; text-align: left; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												<span id="taskDepartYearReceive"/>年
												&nbsp;<span id="taskDepartMonthReceive"/>月 &nbsp;<span id="taskDepartDayReceive"/>日</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p></td>
								</tr>
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
											style="line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<span id="taskDirectorCommentReceive"/></o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="text-indent: 202.5000pt; mso-char-indent-count: 13.5000; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">负责人签字：<span id="taskDirectorAssignReceive"/></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-right: 16.0000pt; text-indent: 215.0000pt; mso-char-indent-count: 19.0000; text-align: left; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												<span id="taskDirectorYearReceive"/>年&nbsp;
												<span id="taskDirectorMonthReceive"/>月 &nbsp;<span id="taskDirectorDayReceive"/>日</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p></td>
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
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-left: 36.0000pt;line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<span id="taskFgLeaderCommentReceive"/></o:p></span>
										</p>
										<p class=MsoNormal align=center
											style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;负责人签字：<span id="taskFgLeaderAssignReceive"/></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal align=center
											style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="taskFgLeaderYearReceive"/>年
												&nbsp;<span id="taskFgLeaderMonthReceive"/>月 &nbsp;<span id="taskFgLeaderDayReceive"/>日</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p></td>
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
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="taskLeaderCommentReceive"/></o:p></span>
										</p>
										<p class=MsoNormal align=center
											style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;局主要领导签字：<span id="taskLeaderAssignReceive" /></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal align=center
											style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="taskLeaderYearReceive"/>年
												&nbsp;&nbsp;<span id="taskLeaderMonthReceive"/>月 &nbsp;<span id="taskLeaderDayReceive"/>日</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p></td>
								</tr>
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
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p></td>
								</tr>
							</table>
						</div>
						
					</div>
				
		<div id="receiveScanContentPrivate">
		<table cellpadding="7" cellspacing="0" class="a_table" width="100%">
	<tr>
		<td class="br" colspan="2">
							<div 
								style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-ok" onclick="confirmReceivingPrivate()">确认领回</a> 
									<a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
							</div>
		</td>
	</tr>
</table>
<form id="receiveScanPrivateForm">
	<input type="hidden" name="id" id="receiveScanTaskId" />
	<input type="hidden" name="pass" value="true" id="pass"/>
	<input type="hidden" name="nextAssignee" id="nextAssigneeId" />
	<input type="hidden" name="processInstanceId" id="processInstanceIdReceiveScan"/>
	<input type="hidden" name="comment" value="已领回"/>
</form>
	</div>
</div>
	<script type="text/javascript">
	$(function(){
		$("#elementIdScanPrivateReceive").focus();
		$("#receiveScanContentPrivate").hide();
		$('#elementIdScanPrivateReceive').bind('keyup', function(event) {
	        if (event.keyCode == "13") {
	            //回车执行查询
	            receiveScannerPrivate();
	        }
	  });
		$("#elementIdScanPrivateReceive").focus();
	})
	var showGreenNumPrivateReceive=0;
	function receiveScannerPrivate(){
		var value=$("#elementIdScanPrivateReceive").val().trim();
			if($("#privateApplyIdReceive").val()==''){
				$.ajax({ 
					type: 'POST', 
					url: base+'/certificateInfoApply/scanSearch/'+value+"?type=receive",
					dataType: 'json',  
					success: function(data){
						var msg=data.msg;
						var comments=data.comment;
						var attId=data.signAttId;
						var data=data.apply;
						if(msg!=null&&msg!=''){
							$.messager.show({
								title:'提示信息',
								msg:msg,
								timeout:3000,
								showType:'slide'
							});
						}
						if(data!=null){
							var startDate=ChangeDateFormat2(data.startDate);
							var endDate=ChangeDateFormat2(data.endDate);
							var applyDate=ChangeDateFormat2(data.applyTime);
							$("#receiveScanWcode").html(data.wcode);
							$("#receiveScanStartDateYear").html(startDate.substring(0,4));
							$("#receiveScanStartDateMonth").html(startDate.substring(5,7));
							$("#receiveScanStartDateDay").html(startDate.substring(8,10));
							$("#receiveScanEndDateYear").html(endDate.substring(0,4));
							$("#receiveScanEndDateMonth").html(endDate.substring(5,7));
							$("#receiveScanEndDateDay").html(endDate.substring(8,10));
							$("#receiveScanReason").html(data.reason);
							$("#receiveScanDestination").html(data.destination);
							$("#receiveScanItinerary").html(data.itinerary);
							$("#receiveScanDays").html(data.days);
							$("#receiveScanPrivateRecord").html(data.privateRecord);
							$("#receiveScanPublicRecord").html(data.publicRecord);
							if(data.receiveTypeOne!=''&&data.receiveTypeOne!=null){
								$("#type1SpanReceive").html(data.receiveTypeOne);
							}
							if(data.receiveTypeTwo!=''&&data.receiveTypeTwo!=null){
								$("#type2SpanReceive").html(data.receiveTypeTwo);					
							}
							if(data.receiveTypeThree!=''&&data.receiveTypeThree!=null){
								$("#type3SpanReceive").html(data.receiveTypeThree);
							}
							var partyStr=("T"==data.hasParty?"√":"");
							$("#receiveScanHasParty").html(partyStr);
							if(attId!=null&&attId!=''){
								$("#receiveScanCreatorName").html('<img id="p" src="${base}/attachment/IoReadImage/'+attId+'" class="signImg"/>');
							}else{
								$("#receiveScanCreatorName").html(data.creatorName);
							}
							$("#receiveScanApplyTime").html(applyDate.substring(0,4)+"年"+applyDate.substring(5,7)+"月"+applyDate.substring(8,10)+"日");
							$("#shouldScanReceiveNumPrivate").val(data.shouldScanNum);
							$("#privateApplyIdReceive").val(data.id);
							$("#processInstanceIdReceiveScan").val(data.processInstanceId);
							$("#receiveScanTaskId").val(data.curTaskId);
							$("#nextAssigneeId").val(data.nextAssignee);
							console.log(comments);
							if(comments.length>0){
								var taskDepart;
								var taskDirector;
								var taskLeader;
								var taskFgLeader;
								var taskOrganization;
								for (var i = 0; i < comments.length; i++) {
									var obj=comments[i];
									if(obj!=null&&taskDepart==null&&obj.taskDefKey=='OFFICAL_CADRES'){
										taskDepart=obj;
									}
									if(obj!=null&&taskDirector==null&&obj.taskDefKey=='ORGANIZATION_DIRECTOR'){
										taskDirector=obj;
									}
									if(obj!=null&&taskLeader==null&&obj.taskDefKey=='BUREAU_MAIN_LEADER'){
										taskLeader=obj;
									}
									if(obj!=null&&taskFgLeader==null&&obj.taskDefKey=='BUREAU_DIRECTOR'){
										taskFgLeader=obj;
									}
									if(obj!=null&&taskOrganization==null&&obj.taskDefKey=='ORGANIZATION_STAFF'){
										taskOrganization=obj;
									}
								}
								if(data.processDefinitionKey=='DEPUTY_CADRES_PASSPORT_APPLY'){
									if(taskDepart!=null){
										packageTaskShowReceive('taskDepart',taskDepart);
									}
								}else{
									document.getElementById("departReceiveTrElement").style.display = 'none';
								}
								if(taskOrganization!=null){
									if(taskOrganization.assigneeAtt!=null&&taskOrganization.assigneeAtt!=''){
										$("#taskOrganizationAssignReceive").html('<img id="p" src="${base}/attachment/IoReadImage/'+taskOrganization.assigneeAtt.id+'" class="signImg"/>');
									}else{
										$("#taskOrganizationAssignReceive").html(taskOrganization.assigneeName);
									}
									var taskOrganizationEndTime=ChangeDateFormat2(taskOrganization.endTime);
									if(taskOrganizationEndTime!=null&&taskOrganizationEndTime!=''){
										$("#taskOrganizationYearReceive").html(taskOrganizationEndTime.substring(0,4));
										$("#taskOrganizationMonthReceive").html(taskOrganizationEndTime.substring(5,7));
										$("#taskOrganizationDayReceive").html(taskOrganizationEndTime.substring(8,10));
									}
								}
								if(taskDirector!=null){
									packageTaskShowReceive('taskDirector',taskDirector);
								}
								if(taskFgLeader!=null){
									packageTaskShowReceive('taskFgLeader',taskFgLeader);
								}
								if(taskLeader!=null){
									packageTaskShowReceive('taskLeader',taskLeader);
								}
							}
							if($("#"+value+"_scanSpan")!=null&&$("#"+value+"_scanSpan")!=undefined){
								$("#"+value+"_scanSpan").addClass("myicon-tick-checked");
							}
							showGreenNumPrivateReceive=showGreenNumPrivateReceive+1;
							if(data.shouldScanNum!=''&&data.shouldScanNum==showGreenNumPrivateReceive){
								$("#elementIdScanPrivateReceive").blur();
								$("#elementIdScanPrivateReceive").attr("readonly","readonly");
								$("#showScanContinueTipPrivateReceive").html("&nbsp;&nbsp;已全部扫描");
								$("#receiveScanContentPrivate").show();
							}
						}
					}
				}); 
			}else{
				$.ajax({ 
					type: 'POST', 
					url: base+'/certificateInfoApply/scanSearch/'+value+"?type=receive",
					dataType: 'json',  
					success: function(data){
						var msg=data.msg;
						if(msg!=null&&msg!=''){
							$.messager.show({
								title:'提示信息',
								msg:msg,
								timeout:3000,
								showType:'slide'
							});
						}
						
					}
				}); 
				if($("#"+value+"_scanSpan")!=null&&$("#"+value+"_scanSpan")!=undefined){
					$("#"+value+"_scanSpan").addClass("myicon-tick-checked");
				}
				showGreenNumPrivateReceive=$(".myicon-tick-checked").length;
				if($("#shouldScanReceiveNumPrivate").val()!=''&&$("#shouldScanReceiveNumPrivate").val()==showGreenNumPrivateReceive){
					$("#elementIdScanPrivateReceive").blur();
					$("#elementIdScanPrivateReceive").attr("readonly","readonly");
					$("#showScanContinueTipPrivateReceive").html("&nbsp;&nbsp;已全部扫描");
					$("#receiveScanContentPrivate").show();
				}
			}
			$("#elementIdScanPrivateReceive").val("");
			$("#elementIdScanPrivateReceive").focus();
		
	}
	function confirmReceivingPrivate(){
		var privateApplyId=$("#privateApplyIdReceive").val();
		if(privateApplyId==''){
			$.messager.alert('系统提示', '数据有误，请联系管理员', 'info');
			return false;
		}
		$.messager.confirm('系统提示','确认领回吗?',function(id){ 
			if(id){ 
				$.ajax({ 
					type: 'POST', 
					url: base+'/certificateInfoApply/receiveScanSave/'+privateApplyId,
					dataType: 'json', 
					data:$('#receiveScanPrivateForm').serialize(),
					success: function(data){
						if(data.success){
							$.messager.alert('系统提示', data.info, 'info');
							closeWindow();
							if("${todoType}"=='unRead'){
								$('#tt').tabs('select', '待办任务列表');
							}else{
								$('#tt').tabs('select', '申请证照待领回');
							}
							var tab = $('#tt').tabs('getSelected');
							tab.panel('refresh');
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
							$.messager.alert('系统提示', data.info, 'info');
						} 
					} 
				}); 
			} 
		}); 	
	}
	function packageTaskShowReceive(taskType,task){
		$("#"+taskType+"CommentReceive").html(task.comment);
		if(task.assigneeAtt!=null&&task.assigneeAtt!=''){
			$("#"+taskType+"AssignReceive").html('<img id="p" src="${base}/attachment/IoReadImage/'+task.assigneeAtt.id+'" class="signImg"/>');
		}else{
			$("#"+taskType+"AssignReceive").html(task.assigneeName);
		}
		var endTime=ChangeDateFormat2(task.endTime);
		if(endTime!=null&&endTime!=''){
			$("#"+taskType+"YearReceive").html(endTime.substring(0,4));
			$("#"+taskType+"MonthReceive").html(endTime.substring(5,7));
			$("#"+taskType+"DayReceive").html(endTime.substring(8,10));
		}
	}

	</script>
</body>
</html>