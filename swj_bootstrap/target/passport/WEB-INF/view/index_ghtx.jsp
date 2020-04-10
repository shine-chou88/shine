<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<!--效期提醒 start-->
<div class="tzgg">
	<!-- g_tit start-->
	<div class="g_tit clearfix">
		<span class="l"><img
				src="${base}/resource/images/index/sy-ghtx.png">
		</span>
<%-- 		<gwideal:perm url="/certificateInfo/listPublic"> --%>
<!-- 			<a href="javascript:void(0);"  -->
<%-- 			onclick="javascript:addTabs('证照信息(因公)','${base}/certificateInfo/listPublic');" class="rt" style="color: #FFF"><img --%>
<%-- 				src="${base}/resource/images/index/more-01.png">更多</a> --%>
<%-- 		</gwideal:perm> --%>
	</div>
	<!--g_tit end-->
	<!--tzgg-cont start-->
	<div class="tzgg-cont" style="height: 280px;">
		<ul>
			<c:forEach items="${ghtxList}" var="msg" end="7">
				<li class="clearfix">
					<a href="javascript:void(0);" onclick="showRemindDetail('${msg.objectType}','${msg.objectId}')" class="l s1">${msg.title}</a>
<%-- 					<span class="rt"><fmt:formatDate value="${msg.releaseTime}" pattern="yyyy-MM-dd" /></span> --%>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!--tzgg-cont start-->
</div>
<!--效期提醒 end-->