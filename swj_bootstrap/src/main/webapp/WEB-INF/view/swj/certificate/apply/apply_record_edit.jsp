<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
	<input type="hidden" id="privateStart" name="privateStart" value="${bean.privateStart }"/>
	<input type="hidden" id="privateEnd" name="privateEnd" value="${bean.privateEnd }"/>
	<input type="hidden" id="privateDestination" name="privateDestination" value="${bean.privateDestination }"/>
	<input type="hidden" id="privateReason" name="privateReason" value="${bean.privateReason }"/>
	<input type="hidden" id="auto" name="auto" value="${bean.auto }"/>
	<input type="hidden" id="publicStart" name="publicStart" value="${bean.publicStart }"/>
	<input type="hidden" id="publicEnd" name="publicEnd" value="${bean.publicEnd }"/>
	<input type="hidden" id="publicDestination" name="publicDestination" value="${bean.publicDestination }"/>
	<input type="hidden" id="publicReason" name="publicReason" value="${bean.publicReason }"/>
	
	<input type="hidden" id="privateDateDesc" name="privateDateDesc" value="${bean.privateDateDesc }"/>
	<input type="hidden" id="publicDateDesc" name="publicDateDesc" value="${bean.publicDateDesc }"/>
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
	
		<p class=MsoNormal style="mso-char-indent-count: 2.0000; line-height: 25.0000pt; mso-line-height-rule: exactly;">
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
					<input type="checkbox" id="hasPrivateRecordCheck" <c:if test="${bean.privateDestination=='无'}">checked="checked"</c:if> onclick="hasCheckRecord(this,'showRecordApplyInfoPrivate')"/>无</span>
					<span style="font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><o:p></o:p></span>
		
		<span id="showRecordApplyInfoPrivate">
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><input class="easyui-textbox" type="text"  data-options="multiline:true"  id="privateDateDescInput" style="width:300px;height: 30px;" value="${bean.privateDateDesc}" /></span>
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（日期）</span>
					<br>
												<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;赴&nbsp; </span>
												<span >
												<input class="easyui-textbox" type="text"  data-options="multiline:true"  id="privateDestinationInput" style="width:300px;height: 30px;" value="${bean.privateDestination}" />
												</span><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（国家/地区）</span><br><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">  事项         </span><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312;  font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												<input class="easyui-textbox" type="text"  data-options="multiline:true"  id="privateReasonInput" style="width:300px;height: 30px;" value="${bean.privateReason}" />
												</span>
												<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（旅游、探亲）</span>
		</span>
		
	
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
	
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
					<input type="checkbox" id="hasPublicRecordCheck" <c:if test="${bean.publicDestination=='无'}">checked="checked"</c:if> onclick="hasCheckRecord(this,'showRecordApplyInfoPublic')"/>无</span>
					
		
		<span id="showRecordApplyInfoPublic">
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; text-decoration: underline; text-underline: single; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;"><input class="easyui-textbox" type="text"  data-options="multiline:true"  id="publicDateDescInput" style="width:300px;height: 30px;" value="${bean.publicDateDesc}" /></span>
			<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（日期）</span><br>
												<span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">&nbsp;&nbsp;赴&nbsp; </span>
												<span >
												<input class="easyui-textbox" type="text"  data-options="multiline:true"  id="publicDestinationInput" style="width:300px;height: 30px;" value="${bean.publicDestination}" />
												</span><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（国家/地区）</span><br><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">  事项         </span><span
												style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312;  font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">
												<input class="easyui-textbox" type="text"  data-options="multiline:true"  id="publicReasonInput" style="width:300px;height: 30px;" value="${bean.publicReason}" />
												</span>
												<span style="mso-spacerun: 'yes'; font-family: 仿宋_GB2312; font-size: 15.0000pt; mso-font-kerning: 1.0000pt;">（因公）</span>
		</span>
		</p>
		
	
	</td>
</tr>
