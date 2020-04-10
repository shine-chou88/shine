<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<!--效期提醒 start-->
<div class="tzgg">
	<!-- g_tit start-->
	<div class="g_tit clearfix">
		<span class="l"><img
				src="${base}/resource/images/index/sy-gqtx.png">
		</span>
<!-- 			<a href="javascript:void(0);"  -->
<%-- 			onclick="javascript:addTabs('通知公告','${base}/xxfb/list');" class="rt" style="color: #FFF"><img --%>
<%-- 				src="${base}/resource/images/index/more-01.png">更多</a> --%>
	</div>
	<!--g_tit end-->
	<!--tzgg-cont start-->
	<div class="tzgg-cont" style="height: 280px;">
		<ul>
			<c:forEach items="${xqtxList}" var="msg" end="15" varStatus="i">
				<li class="clearfix" style="width: 48%;float: left;<c:if test="${i.index%2!=0}">margin-left: 40px;</c:if>">
					<a href="javascript:void(0);" onclick="showRemindDetail('${msg.objectType}','${msg.objectId}')" class="l s1">${msg.title}</a>
					<%-- <span class="rt"><fmt:formatDate value="${msg.releaseTime}" pattern="yyyy-MM-dd" /></span> --%>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!--tzgg-cont start-->
</div>
<!--效期提醒 end-->