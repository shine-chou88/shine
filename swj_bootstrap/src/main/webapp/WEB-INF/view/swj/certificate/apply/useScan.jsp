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
		<td class="br"><input id="elementIdScanPrivate" type="text" style="width:300px;" /><span id="showScanContinueTipPrivate" style="color: red;">&nbsp;&nbsp;请继续扫描</span>
		</td>
		<input type="hidden" id="shouldScanUseNumPrivate" value=""/>
		<input type="hidden"  id="privateApplyId" value=""/>
	</tr>
	</table>
					<div class="Section0"
						style="layout-grid: 15.6000pt; padding-top: 10px;height:520px;overflow-y:auto ">
						<p class=MsoNormal align=center style="text-align: center;">
							<b><span
								style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
									face="黑体">编号：</font></span></b><b><u><span
									style="mso-spacerun: 'yes'; font-family: 黑体; font-weight: bold; text-decoration: underline; text-underline: single; font-size: 12.0000pt; mso-font-kerning: 1.0000pt;" id="useScanWcode">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u></b><b><span
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
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanStartDateYear">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">年</span><u><span id="useScanStartDateMonth" style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;</span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">月</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanStartDateDay">
														&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">日至</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanEndDateYear">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">年</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanEndDateMonth">
												&nbsp;&nbsp;&nbsp;</span></u><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">月</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanEndDateDay">
												&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">日因（何事）</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanReason">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（探亲、旅游、访友等）赴
												（至何地）</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanDestination">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;，行程线路是：</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanItinerary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">，为期</span><u><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanDays">&nbsp;&nbsp;&nbsp;</span></u><span
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
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">普通护照（&nbsp;
												）港澳通行证（&nbsp;）台湾通行证（&nbsp;）
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
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">普通护照（
												<span id="type1Span"></span>）港澳通行证（<span id="type2Span"></span>
												）台湾通行证（<span id="type3Span"></span>
												）
											</span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<b><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">3、（
													<span id="useScanHasParty"></span>）本人是中共党员，出国（境）期间党籍保留在原单位。
											</span></b><b><span
												style="font-family: 仿宋_GB2312; font-weight: bold; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span></b>
										</p>
										<p class=MsoNormal
											style="line-height: 27.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">申请人签字：<span id="useScanCreatorName"></span>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经办人签字：<span id="taskOrganizationAssign"></span></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="line-height: 27.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="useScanApplyTime"></span>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="taskOrganizationYear"/>年
												<span id="taskOrganizationMonth"/>月 <span id="taskOrganizationDay"/>日</span><span
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
	<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanPrivateRecord">
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
	
		<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;" id="useScanPublicRecord">
		</span>
		
	
	</p>
	</td>
</tr>
<!-- 因公出国记录结束 -->								
								<tr id="departTrElement" style="height: 102.1000pt;">
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
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<span id="taskDepartComment" /></o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="text-indent: 202.5000pt; mso-char-indent-count: 13.5000; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">负责人签字：<span id="taskDepartAssign" /></span><span
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
												<span id="taskDepartYear"/>年
												&nbsp;<span id="taskDepartMonth"/>月 &nbsp;<span id="taskDepartDay"/>日</span><span
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
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<span id="taskDirectorComment"/></o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-left: 36.0000pt; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;</o:p></span>
										</p>
										<p class=MsoNormal
											style="text-indent: 202.5000pt; mso-char-indent-count: 13.5000; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">负责人签字：<span id="taskDirectorAssign"/></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal
											style="margin-right: 16.0000pt; text-indent: 215.0000pt; mso-char-indent-count: 19.0000; text-align: left; line-height: 17.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												<span id="taskDirectorYear"/>年&nbsp;
												<span id="taskDirectorMonth"/>月 &nbsp;<span id="taskDirectorDay"/>日</span><span
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
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;<span id="taskFgLeaderComment"/></o:p></span>
										</p>
										<p class=MsoNormal align=center
											style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;负责人签字：<span id="taskFgLeaderAssign"/></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal align=center
											style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="taskFgLeaderYear"/>年
												&nbsp;<span id="taskFgLeaderMonth"/>月 &nbsp;<span id="taskFgLeaderDay"/>日</span><span
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
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="taskLeaderComment"/></o:p></span>
										</p>
										<p class=MsoNormal align=center
											style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;局主要领导签字：<span id="taskLeaderAssign" /></span><span
												style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
										</p>
										<p class=MsoNormal align=center
											style="text-align: center; line-height: 25.0000pt; mso-line-height-rule: exactly;">
											<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="taskLeaderYear"/>年
												&nbsp;&nbsp;<span id="taskLeaderMonth"/>月 &nbsp;<span id="taskLeaderDay"/>日</span><span
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
				
		<div id="useScanContentPrivate">
		<table cellpadding="7" cellspacing="0" class="a_table" width="100%">
	<tr>
		<td class="br" colspan="2">
							<div 
								style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-ok" onclick="confirmUsingPrivate()">确认领用</a> 
									<a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
							</div>
		</td>
	</tr>
</table>
<form id="useScanPrivateForm">
	<input type="hidden" name="id" id="useScanTaskId" />
	<input type="hidden" name="pass" value="true" id="pass"/>
	<input type="hidden" name="nextAssignee" id="nextAssigneeId" />
	<input type="hidden" name="processInstanceId" id="processInstanceIdUseScan"/>
	<input type="hidden" name="comment" value="已领用"/>
</form>
	</div>
</div>
	<script type="text/javascript">
	$(function(){
		$("#elementIdScanPrivate").focus();
		$("#useScanContentPrivate").hide();
		$('#elementIdScanPrivate').bind('keyup', function(event) {
	        if (event.keyCode == "13") {
	            //回车执行查询
	            useScannerPrivate();
	        }
	  });
		$("#elementIdScanPrivate").focus();
	})
	var showGreenNumPrivate=0;
	function useScannerPrivate(){
		var value=$("#elementIdScanPrivate").val().trim();
			if($("#privateApplyId").val()==''){
				$.ajax({ 
					type: 'POST', 
					url: base+'/certificateInfoApply/scanSearch/'+value+"?type=use",
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
							$("#useScanWcode").html(data.wcode);
							$("#useScanStartDateYear").html(startDate.substring(0,4));
							$("#useScanStartDateMonth").html(startDate.substring(5,7));
							$("#useScanStartDateDay").html(startDate.substring(8,10));
							$("#useScanEndDateYear").html(endDate.substring(0,4));
							$("#useScanEndDateMonth").html(endDate.substring(5,7));
							$("#useScanEndDateDay").html(endDate.substring(8,10));
							$("#useScanReason").html(data.reason);
							$("#useScanDestination").html(data.destination);
							$("#useScanItinerary").html(data.itinerary);
							$("#useScanDays").html(data.days);
							$("#useScanPrivateRecord").html(data.privateRecord);
							$("#useScanPublicRecord").html(data.publicRecord);
							if(data.useTypeOne!=''&&data.useTypeOne!=null){
								$("#type1Span").html(data.useTypeOne);
							}
							if(data.useTypeTwo!=''&&data.useTypeTwo!=null){
								$("#type2Span").html(data.useTypeTwo);					
							}
							if(data.useTypeThree!=''&&data.useTypeThree!=null){
								$("#type3Span").html(data.useTypeThree);
							}
							var partyStr=("T"==data.hasParty?"√":"");
							$("#useScanHasParty").html(partyStr);
							if(attId!=null&&attId!=''){
								$("#useScanCreatorName").html('<img id="p" src="${base}/attachment/IoReadImage/'+attId+'" class="signImg"/>');
							}else{
								$("#useScanCreatorName").html(data.creatorName);
							}
							$("#useScanApplyTime").html(applyDate.substring(0,4)+"年"+applyDate.substring(5,7)+"月"+applyDate.substring(8,10)+"日");
							$("#shouldScanUseNumPrivate").val(data.shouldScanNum);
							$("#privateApplyId").val(data.id);
							$("#processInstanceIdUseScan").val(data.processInstanceId);
							$("#useScanTaskId").val(data.curTaskId);
							$("#nextAssigneeId").val(data.nextAssignee);
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
										packageTaskShow('taskDepart',taskDepart);
									}
								}else{
									document.getElementById("departTrElement").style.display = 'none';
								}
								if(taskOrganization!=null){
									if(taskOrganization.assigneeAtt!=null&&taskOrganization.assigneeAtt!=''){
										$("#taskOrganizationAssign").html('<img id="p" src="${base}/attachment/IoReadImage/'+taskOrganization.assigneeAtt.id+'" class="signImg"/>');
									}else{
										$("#taskOrganizationAssign").html(taskOrganization.assigneeName);
									}
									var taskOrganizationEndTime=ChangeDateFormat2(taskOrganization.endTime);
									if(taskOrganizationEndTime!=null&&taskOrganizationEndTime!=''){
										$("#taskOrganizationYear").html(taskOrganizationEndTime.substring(0,4));
										$("#taskOrganizationMonth").html(taskOrganizationEndTime.substring(5,7));
										$("#taskOrganizationDay").html(taskOrganizationEndTime.substring(8,10));
									}
								}
								if(taskDirector!=null){
									packageTaskShow('taskDirector',taskDirector);
								}
								if(taskFgLeader!=null){
									packageTaskShow('taskFgLeader',taskFgLeader);
								}
								if(taskLeader!=null){
									packageTaskShow('taskLeader',taskLeader);
								}
							}
							if($("#"+value+"_scanSpan")!=null&&$("#"+value+"_scanSpan")!=undefined){
								$("#"+value+"_scanSpan").addClass("myicon-tick-checked");
							}
							showGreenNumPrivate=showGreenNumPrivate+1;
							if(data.shouldScanNum!=''&&data.shouldScanNum==showGreenNumPrivate){
								$("#elementIdScanPrivate").blur();
								$("#elementIdScanPrivate").attr("readonly","readonly");
								$("#showScanContinueTipPrivate").html("&nbsp;&nbsp;已全部扫描");
								$("#useScanContentPrivate").show();
							}
						}
					}
				}); 
			}else{
				$.ajax({ 
					type: 'POST', 
					url: base+'/certificateInfoApply/scanSearch/'+value+"?type=use",
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
				showGreenNumPrivate=$(".myicon-tick-checked").length;
				if($("#shouldScanUseNumPrivate").val()!=''&&$("#shouldScanUseNumPrivate").val()==showGreenNumPrivate){
					$("#elementIdScanPrivate").blur();
					$("#elementIdScanPrivate").attr("readonly","readonly");
					$("#showScanContinueTipPrivate").html("&nbsp;&nbsp;已全部扫描");
					$("#useScanContentPrivate").show();
				}
			}
			$("#elementIdScanPrivate").val("");
			$("#elementIdScanPrivate").focus();
		
	}
	function confirmUsingPrivate(){
		var privateApplyId=$("#privateApplyId").val();
		if(privateApplyId==''){
			$.messager.alert('系统提示', '数据有误，请联系管理员', 'info');
			return false;
		}
		$.messager.confirm('系统提示','确认领用吗?',function(id){ 
			if(id){ 
				$.ajax({ 
					type: 'POST', 
					url: base+'/certificateInfoApply/useScanSave/'+privateApplyId,
					dataType: 'json', 
					data:$('#useScanPrivateForm').serialize(),
					success: function(data){
						if(data.success){
							$.messager.alert('系统提示', data.info, 'info');
							closeWindow();
							if("${todoType}"=='unRead'){
								$('#tt').tabs('select', '待办任务列表');
							}else{
								$('#tt').tabs('select', '申请证照待领用');
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
	function packageTaskShow(taskType,task){
		$("#"+taskType+"Comment").html(task.comment);
		if(task.assigneeAtt!=null&&task.assigneeAtt!=''){
			$("#"+taskType+"Assign").html('<img id="p" src="${base}/attachment/IoReadImage/'+task.assigneeAtt.id+'" class="signImg"/>');
		}else{
			$("#"+taskType+"Assign").html(task.assigneeName);
		}
		var endTime=ChangeDateFormat2(task.endTime);
		if(endTime!=null&&endTime!=''){
			$("#"+taskType+"Year").html(endTime.substring(0,4));
			$("#"+taskType+"Month").html(endTime.substring(5,7));
			$("#"+taskType+"Day").html(endTime.substring(8,10));
		}
	}

	</script>
</body>
</html>