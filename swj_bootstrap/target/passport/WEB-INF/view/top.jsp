<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="gwideal" uri="/gwideal-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
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
	
	<div class="header_new">
        <!-- <img src="${base}/resource/images/logo.png" alt="" class="logo"/> -->
        <div class="user-control_wrapper">
        	<div class="next user-control_item" style="cursor: default;">
               	 待办任务
                <span class="count">
                	<a href="javascript:addTabs('待办任务列表','${base}/activiti/process/taskList');" style="color:#f59519;">
	                    <span class="text" id="unReadTaskSpan">${unReadMsgCount}</span>
                    </a>
                </span>
            </div>
            <div style="cursor:default" class="user-control_item">欢迎您：${currentUser.name}</div>
            <div class="user-control_item">
            	<a href="javascript:void(0);" onclick="updatePwd()" style="color: #fff;">修改密码</a>
            </div>
            <div class="user-control_item">
            	<a href="javascript:void(0);" onclick="helpOnline()" style="color: #fff;">在线帮助</a>
            </div>
            <c:if test="${currentUser.hasRole('PUBLIC_JBGSGZRY')==true || currentUser.hasRole('PUBLIC_JXSDWBGSZR')==true || currentUser.hasRole('PUBLIC_JBGSZR')==true}">
	            <div class="user-control_item">
	            	<a href="${base}/resource/download/user_manual_public_simple_v1.1.pdf" style="color: #fff;">因公用户手册</a>
	            </div>
            </c:if>
            <c:if test="${currentUser.hasRole('DEPUTY_CADRES')==true || currentUser.hasRole('OFFICAL_CADRES')==true || currentUser.hasRole('ORGANIZATION_STAFF')==true
            || currentUser.hasRole('ORGANIZATION_DIRECTOR')==true || currentUser.hasRole('BUREAU_DIRECTOR')==true || currentUser.hasRole('BUREAU_MAIN_LEADER')==true}">
	            <div class="user-control_item">
	            	<a href="${base}/resource/download/user_manual_private_simple_v1.1.pdf" style="color: #fff;">因私用户手册</a>
	            </div>
            </c:if>
            <div class="user-control_item">
            	<a href="javascript:void(0);" onclick="exitSystem()" style="color: #fff;">退出系统</a>
            </div>
        </div>
<%--         <img src="${base}/resource/images/head-right-bg.png" alt="" class="head-right_bg"/> --%>
    </div>
</body>
</html>
