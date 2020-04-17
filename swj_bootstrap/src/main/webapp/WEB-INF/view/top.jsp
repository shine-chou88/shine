<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function updatePwd(){
		var win=creatWin('修改-用户密码',650,300,'icon-add','/user/editPwd');
		win.window('open');
	}
	function exitSystem(){
		confirmModalInit("确认退出?");
		$("#confirmOk").off().on("click", function() {
			location.href='${base}/logout.do';
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
			<li><a href="javascript:void(0);" onclick="exitSystem()">退出</a></li>
		  </ul>
		</div>
	  </div>
	</div>
</div>