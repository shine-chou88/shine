<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<html>
<head>
	<title>${title}</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" content="no-store"/>
	<script type="text/javascript" src="${base}/resource/ui/jquery.min.js"></script>
	<script type="text/javascript" src="${base}/resource/js/jsencrypt.min.js"></script>
	<style type="text/css">
			li {
				list-style: none;
			}
			body {
				position: relative;
				height: 100vh;
                font-size: 14px;
                background: url("${base}/resource/images/bg.png") no-repeat center center /cover;
			}
			* {
				padding: 0;
				margin: 0;
				font-family: '微软雅黑';
			}
			.main {
                background: url("${base}/resource/images/left.png") no-repeat top left #fff;
                border-radius: 12px;
				height: 438px;
				width: 748px;
				position: absolute;
				margin: auto;
				top: 0;
				left: 0;
				bottom: 0;
				right: 0;
			}
			.form-wrapper {
                padding: 0 80px;
				margin-left: 296px;
			}
			.form-wrapper ul li label {
				display: block;
				margin-bottom: 10px;
			}
			.form-wrapper ul li label span {
				font-size: 14px;
				color: #b3b3d5;
				white-space: 4px;
				vertical-align: text-bottom;
			}
			.form-wrapper ul li label span:first-child {
				color: #1a1a51;
				font-size: 18px;
				font-weight: bold;
			}
            .form-wrapper .title{
                text-align: center;
                padding: 50px 0 30px 0;
            }
			.form-wrapper ul li {
				margin-bottom: 20px;
			}
			.form-wrapper ul li input {
				height: 39px;
				box-sizing: border-box;
				width: 100%;
				border-radius: 5px;
				border: 1px solid #b1b9da;
				padding: 0 10px;
				color: #7c7cb2;
			}
			.form-wrapper ul li input::placeholder {
				color: #7c7cb2;
			}
			.form-wrapper .check-box {
				color: #7c7cb2;
			}
			.form-wrapper .line {
				margin-bottom: 20px;
			}
			.form-wrapper .check-box input[type='checkbox'] {
				margin-left: 5px;
				margin-top: -3px;
				vertical-align: middle;
			}
			.form-wrapper button {
				background-color: #3eb5e9;
				width: 100%;
				border-radius: 5px;
				height: 39px;
				line-height: 39px;
                border: 1px solid #d2dbee;
                color: #fff;
                box-sizing: border-box;
                transition: .15s linear all;
			}
            .form-wrapper button:hover{
                box-shadow: -1px 3px 7px 1px #cbcbe3;
            }
            .main{
				box-shadow:0px 3px 7px 3px rgba(0, 0, 0, .39)
			}
            .tj{ color:#4c5d74;text-align:center;clear: both;font-size: 14px;margin-top: 20px;}
		</style>
	<script type="text/javascript">
		var encrypt = new JSEncrypt();
	    encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4z2h4JuaVJKljC6tdB09ucZzUjbyGCjJcHYi41lypUHWGZ5M4IoPmOWSbVO9XZRBQzp3xBwrbF7lJxK4EGWl9i+BjuRdCUf109jMEo6kMVyjJTtaWAfqohcYBOIXZ3Eqgyyt4sZCIvH4JAB/0mmBPQ1wS6gwW8IPSJEo2Wx3CfQIDAQAB");
		
		var loginMsg='${loginMsg}';
		if(loginMsg.length>0){
			alert(loginMsg);
		}
		
		function doSubmit(){
			if($("#login_check_accountNo").val()==null||$("#login_check_accountNo").val()==""){
				alert("请输入您的用户名或手机号");
				return false;
			}
			if($("#check_password").val()==null||$("#check_password").val()==""){
				alert("请输入您的密码");
				return false;
			}
			document.getElementById("accountNo").value=encrypt.encrypt($("#login_check_accountNo").val());
			document.getElementById("password").value=encrypt.encrypt($("#check_password").val());
			document.getElementById("loginForm").submit();
		}
		//回车键登录
		$(document).ready(function(){
			document.onkeydown = function (event){
				if (event.keyCode==13){
					if ($("input:focus").attr("class") == 'name') {
						window.event.keyCode=9;
						//$(".password").focus();
					} else {
						doSubmit();
					}
				}
			};
		});
	</script>
</head>
<body>
<script type="text/javascript">
//easyui只加载body里面的内容
var url=window.location.href;
if(url.substring(url.length-9,url.length)!="login.jsp"){
	window.location.href='${base}/login.jsp';
}
</script>
	<div class="main">
		<div class="form-wrapper">
			<div class="title">
				<img src="${base}/resource/images/title.png" alt="" />
			</div>
			<div>
				<form id="loginForm" action="${base}/login.do" method="post" onsubmit="return doSubmit();">
					<input type="hidden" name="accountNo" id="accountNo"/>
	        	 	<input type="hidden" name="password" id="password"/>
					<ul>
						<li>
							<label>
								<span>
									用户名
								</span>
								<span>
									USER NAME
								</span>
							</label>
							<input class="name"  id="login_check_accountNo" type="text" placeholder="请输入您的用户名或手机号"/>
						</li>
						<li>
							<label>
								<span>
									密 码
								</span>
								<span>
									PASSWORD
								</span>
							</label>
							<input class="password" type="password" id="check_password" placeholder="请输入您的密码" autocomplete="off"/>
						</li>
					</ul>
					<button type="submit">
						登 录
					</button>
					<div class="tj">
		                                           推荐浏览器：<a href="${base}/resource/download/Firefox-full-latest-v67.exe" style="text-decoration: underline;color: blue;">Firefox</a>
		             </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>