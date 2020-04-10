<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<!--待办任务 start-->
<div class="tzgg">
	<!-- g_tit start-->
	<div class="g_tit clearfix">
		<span class="l"><img
				src="${base}/resource/images/index/sy-dbrw.png">
		</span>
 		<a href="javascript:void(0);" onclick="javascript:addTabs('待办任务列表','${base}/activiti/process/taskList');" class="rt" style="color: #FFF">
 		<img src="${base}/resource/images/index/more-01.png">更多</a> 
	</div>
	<!--g_tit end-->
	<!--tzgg-cont start-->
	<div class="tzgg-cont" style="height: 280px;">
		<ul>
			<c:forEach items="${listCurrentTask}" var="task" end="7">
				<li class="clearfix">
					<a href="javascript:void(0);" class="l s1" onclick="completeCurrentTask('${task.id}','${task.name}','${task.processDefinitionName}','${task.actId}','${task.processDefinitionKey}','${task.businessKey}');">
						${task.title}
					</a>
					<span class="rt">${task.lastEndTime}</span>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!--tzgg-cont start-->
</div>
<!--待办任务 end-->