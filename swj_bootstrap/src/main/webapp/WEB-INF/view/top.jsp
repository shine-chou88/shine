<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function updatePwd(){
		var win=creatWin('修改-用户密码',650,300,'icon-add','/user/editPwd');
		win.window('open');
	}
	function exitSystem(){
		$.messager.confirm('系统提示', '确认退出?', function(r){
			if (r){
				location.href='${base}/logout.do';
			}
		});
	}
	function winFullScreen(){
		if($.support.fullscreen){
			$('#content').fullScreen();
		}
	}
	function helpOnline(){
		window.open("./resource/onlinehelp/index.html");
	}
</script>
<div class="row" style="padding-top:51px;">
	<div class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container">
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		  </button>
		  <a class="navbar-brand" href="#">gwideal</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
		  <ul class="nav navbar-nav navbar-right">
			<li>
				<a href="#">
					待办任务
		            <span style="color:#f59519;" class="text" id="unReadTaskSpan" onclick="javascript:addTabs('待办任务列表','${base}/activiti/process/taskList');">${unReadMsgCount}</span>
				</a>
			</li>
			<li><a href="#">欢迎您：<font color="white">${currentUser.name}</font></a></li>
			<li><a href="javascript:void(0);" onclick="updatePwd()">修改密码</a></li>
			<li><a href="javascript:void(0);" onclick="helpOnline()">在线帮助</a></li>
			<c:if test="${currentUser.hasRole('PUBLIC_JBGSGZRY')==true || currentUser.hasRole('PUBLIC_JXSDWBGSZR')==true || currentUser.hasRole('PUBLIC_JBGSZR')==true}">
	            <li><a href="${base}/resource/download/user_manual_public_simple_v1.1.pdf">因公用户手册</a></li>
            </c:if>
            <c:if test="${currentUser.hasRole('DEPUTY_CADRES')==true || currentUser.hasRole('OFFICAL_CADRES')==true || currentUser.hasRole('ORGANIZATION_STAFF')==true
            || currentUser.hasRole('ORGANIZATION_DIRECTOR')==true || currentUser.hasRole('BUREAU_DIRECTOR')==true || currentUser.hasRole('BUREAU_MAIN_LEADER')==true}">
	            <li><a href="${base}/resource/download/user_manual_private_simple_v1.1.pdf">因私用户手册</a></li>
            </c:if>
			<li><a href="javascript:void(0);" onclick="exitSystem()">退出</a></li>
		  </ul>
		</div>
	  </div>
	</div>
</div>