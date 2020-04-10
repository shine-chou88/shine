<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
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
	<c:choose>
		<c:when test="${not empty bean.privateDestination && bean.privateDestination!='无'}">
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
		${bean.privateDateDesc}赴${bean.privateDestination}，${bean.privateReason }
		</span>
		</c:when>
		<c:when test="${not empty bean.privateDestination && bean.privateDestination=='无'}">
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
			无
			</span>
		</c:when>
		<c:otherwise>
		  &nbsp;
		</c:otherwise>
	</c:choose>
	
	</p>
	</td>
</tr>

<!-- 因公 -->
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
	
		<c:choose>
		<c:when test="${not empty bean.publicDestination && bean.publicDestination!='无'}">
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
		${bean.publicDateDesc}赴${bean.publicDestination}，${bean.publicReason }
		</span>
		</c:when>
		<c:when test="${not empty bean.publicDestination && bean.publicDestination=='无'}">
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
			无
			</span>
		</c:when>
		<c:otherwise>
		  &nbsp;
		</c:otherwise>
	</c:choose>
		
	
	</p>
	</td>
</tr>
