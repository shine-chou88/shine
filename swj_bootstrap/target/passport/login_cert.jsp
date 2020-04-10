<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<html>
<head>
	<title>${title}</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" contect="no-store"/>
	<link rel="stylesheet" type="text/css" href="${base}/resource/css/layout.css">
	<link rel="stylesheet" type="text/css" href="${base}/resource/css/reset.css">
	<script type="text/javascript" src="${base}/resource/ui/jquery.min.js"></script>
	<script type="text/javascript" src="${base}/resource/js/jsencrypt.min.js"></script>
	<script type="text/javascript">
		var encrypt = new JSEncrypt();
	    encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4z2h4JuaVJKljC6tdB09ucZzUjbyGCjJcHYi41lypUHWGZ5M4IoPmOWSbVO9XZRBQzp3xBwrbF7lJxK4EGWl9i+BjuRdCUf109jMEo6kMVyjJTtaWAfqohcYBOIXZ3Eqgyyt4sZCIvH4JAB/0mmBPQ1wS6gwW8IPSJEo2Wx3CfQIDAQAB");
		$(function(){
			$(".login_c_1").hide();
	        $(".login_c_2").show();
		    $(".login_cont_tit .li1").click(function(){
		    	window.location.href='${base}/login_cert.jsp';
		    });
		}); 
		
		var loginMsg='${loginMsg}';
		if(loginMsg.length>0){
			alert(loginMsg);
		}
		
		ESE_GetSelfCertificate("36","com1","","0","36","com1","")//获取证书内容
	    .then(function(res){
	        return ESE_GetCertInfoByOID("36","com1","","0","36","com1","",res,"1.2.156.112570.11.205");//获取key唯一标示
	    }).then(function(res){
	        $.ajax({ 
				type: 'POST', 
				url: '${base}/getJdCodeByCertUniqueId/'+res,
				dataType: 'json',  
				success: function(data){
					if(null!=data){
						if(data.success){
							if(data.info!="PT"){
								window.location.href='${base}/login_cert_'+data.info+'.jsp';
							}
						}else{
							alert(data.info);
						}
					}
				} 
			}); 
	    }).catch(function(error){
	    	if(error.LastError==800 && error.ErrorMsg=='No such native application com.safeengine.ucclientctrl'){
	    		alert('没有安装协卡助手，请先安装！');
	    	}else{
	    		alert(getErrorInfo(error.LastError));			    		
	    	}
	        return;
	    });				
		
		function doCertSubmit(){
			var isFirefox=navigator.userAgent.indexOf("Firefox");
			if(isFirefox<=0){
				alert("目前密钥登录只支持火狐浏览器，请先到本页面下载安装！");
				return false;
			}
			if(typeof(SHECASafeEngineExtensionIsInstalled) != "object"){
				alert('浏览器没有安装SafeEngine扩展或安装失败，请先安装！');
				return false; 
			}			
			if(SHECASafeEngineExtensionIsInstalled.company != "SHECA"){
				alert('浏览器没有安装SafeEngine扩展或安装失败，请先安装！');
				return false;
			}
			if(SHECASafeEngineExtensionIsInstalled.display == false){
				alert('浏览器没有安装SafeEngine扩展或安装失败，请先安装！');
				return false;
			}
			var certPwd=document.getElementById("certPwd").value;
			if(certPwd.length==0){
				alert('请先输入密钥密码！');
				return false;
			}else{
				ESE_GetSelfCertificate("36","com1",certPwd,"0","36","com1",certPwd)//获取证书内容
			    .then(function(res){
			        return ESE_GetCertInfoByOID("36","com1",certPwd,"0","36","com1",certPwd,res,"1.2.156.112570.11.205");//获取key唯一标示
			    }).then(function(res){
			        document.getElementById("certUniqueId").value=encrypt.encrypt(res);
			        document.getElementById("certLoginForm").submit();
			    }).catch(function(error){
			    	if(error.LastError==800 && error.ErrorMsg=='No such native application com.safeengine.ucclientctrl'){
			    		alert('没有安装协卡助手，请先安装！');
			    	}else{
			    		alert(getErrorInfo(error.LastError));			    		
			    	}
			        return;
			    });				
			}
			return false;
		}
	</script>
</head>
<body class="login_body">
<script type="text/javascript">
//easyui只加载body里面的内容
var url=window.location.href;
if(url.substring(url.length-9,url.length)!="login.jsp"){
	if(url.substring(url.length-14,url.length)!="login_cert.jsp" && url.substring(url.length-12,url.length)!="loginCert.do"){
		window.location.href='${base}/login_cert.jsp';				
	}
}
</script>
 <div class="login_head">
        <div class="login_head_cont"><i><img src="${base}/resource/images/login-logo.png"></i></div>
 </div>
	<div class="login_box clearfix">
	    <div class="login_cont r">
	    	<div class="login_cont_tit">
	          <ul class="clearfix">
	            <li class="login_on li2"><img src="${base}/resource/images/l-2.png"></li> 
	          </ul>
	       </div>   
	       <div class="login_c_1"></div>
	       <div class="login_c_2">
	       		<form id="certLoginForm" action="${base}/loginCert.do" method="post" onsubmit="return doCertSubmit();">
		        	<input id="certPwd" class="name" type="password" placeholder="请输入密钥密码" autocomplete="off"/>
		        	<input id="certUniqueId" name="certUniqueId" type="hidden"/>
		        </form>
		        <a href="javascript:void(0)" class="login_btn" onclick="doCertSubmit();">登录</a>
		        <div class="tj">
	                                           推荐浏览器：<a href="${base}/resource/download/Firefox-full-latest-v58.exe" style="text-decoration: underline;color: blue;">Firefox</a>&nbsp;&nbsp;&nbsp;&nbsp;
	                                           <a href="${base}/resource/download/safeengine-1.0.1.0.xpi" style="text-decoration: underline;color: blue;">SafeEngine</a>&nbsp;&nbsp;&nbsp;&nbsp;
	                                           <a href="${base}/resource/download/ShecAid.exe" style="text-decoration: underline;color: blue;">协卡助手</a>&nbsp;&nbsp;&nbsp;&nbsp;
	                                           <br/><br/>
	                                           <a href="javascript:void(0);" onclick="javascript:window.open('${base}/login_cert_help.jsp');" style="text-decoration: underline;color: blue;font-size: 16px;">密钥使用说明</a>&nbsp;&nbsp;&nbsp;&nbsp;
	             </div>
       		</div>
	    </div>
	</div> 
 <div class="login_footer">普陀区社会治安综合治理（政法维稳）信息化管理平台</div>
 <script type="text/javascript">
		function getErrorInfo(errCode){
			var result='';
			switch (errCode){
				case -2113601536 :
				    result = "读取文件错误(-2113601536)！";
					break;
				case -2113601535 :
				    result = "密码错误(-2113601535)！";
					break;
				case -2113601534 :
				    result = "非法句柄错误(-2113601534)！";
					break;
				case -2113601533 :
				    result = "缺少ECC KEY错误(-2113601533)！";
					break;
				case -2113601532 :
				    result = "ECC KEY 与算法不匹配错误(-2113601532)！";
					break;
				case -2113601531 :
				    result = "非法算法错误(-2113601531)！";
					break;
				case -2113601530 :
				    result = "数字签名错误(-2113601530)！";
					break;
				case -2113601529 :
				    result = "摘要错误(-2113601529)！";
					break;
				case -2113601528 :
				    result = "缓冲区太小(-2113601528)！";
					break;
				case -2113601527 :
				    result = "证书格式错误(-2113601527)！";
					break;
				case -2113601526 :
				    result = "缺少公钥错误(-2113601526)！";
					break;
				case -2113601525 :
				    result = "验证签名错误(-2113601525)！";
					break;
				case -2113601524 :
				    result = "产生公私钥对错误(-2113601524)！";
					break;
				case -2113601523 :
				    result = "PKCS12编码错误(-2113601523)！";
					break;
				case -2113601522 :
				    result = "PKCS12格式错误(-2113601522)！";
					break;
				case -2113601520 :
				    result = "SE_ECC_ERROR_LOAD_BUILTIN_EC(-2113601520)！";
					break;
				case -2113601519 :
				    result = "公私钥不匹配错误(-2113601519)！";
					break;
				case -2113601518 :
				    result = "PKCS10编码错误(-2113601518)！";
					break;
				case -2113601517 :
				    result = "PKCS10解码错误(-2113601517)！";
					break;
				case -2113601516 :
				    result = "公钥格式错误(-2113601516)！";
					break;
				case -2113601515 :
				    result = "PKCS10格式错误(-2113601515)！";
					break;
				case -2113601514 :
				    result = "验证PKCS10错误(-2113601514)！";
					break;
				case -2113601513 :
				    result = "ECC KEY格式错误(-2113601513)！";
					break;
				case -2113601512 :
				    result = "公钥解码错误(-2113601512)！";
					break;
				case -2113601511 :
				    result = "签名格式错误(-2113601511)！";
					break;
				case -2113601510 :
				    result = "EC格式错误(-2113601510)！";
					break;
				case -2113601509 :
				    result = "ECC KEY解码错误(-2113601509)！";
					break;
				case -2113601508 :
				    result = "写入文件错误(-2113601508)！";
					break;
				case -2113601507 :
				    result = "证书链非法错误(-2113601507)！";
					break;
				case -2113601506 :
				    result = "内存分配错误(-2113601506)！";
					break;
				case -2113601505 :
				    result = "初始化环境错误(-2113601505)！";
					break;
				case -2113601504 :
				    result = "读取配置文件错误(-2113601504)！";
					break;
				case -2113601503 :
				    result = "打开设备错误(-2113601503)！";
					break;
				case -2113601502 :
				    result = "打开会话错误(-2113601502)！";
					break;
				case -2113601501 :
				    result = "装载动态库错误(-2113601501)！";
					break;
				case -2113601500 :
				    result = "设备类型错误(-2113601500)！";
					break;
				case -2113601499 :
				    result = "算法不支持错误(-2113601499)！";
					break;
				case -2113601498 :
				    result = "产生PKCS10错误(-2113601498)！";
					break;
				case -2113601497 :
				    result = "导出公钥错误(-2113601497)！";
					break;
				case -2113601496 :
				    result = "EC_POINT非法错误(-2113601496)！";
					break;m
				case -2113601495 :
				    result = "对称加密错误(-2113601495)！";
					break;
				case -2113601494 :
				    result = "对称解密错误(-2113601494)！";
					break;
				case -2113601493 :
				    result = "PEM解码错误(-2113601493)！";
					break;
				case -2113601492 :
				    result = "获取证书细目错误(-2113601492)！";
					break;
				case -2113601491 :
				    result = "PEM编码错误(-2113601491)！";
					break;
				case -2113601490 :
				    result = "获取证书扩展项错误(-2113601490)！";
					break;
				case -2113601489 :
				    result = "非法接口类型错误(-2113601489)！";
					break;
				case -2113601488 :
				    result = "非法参数错误(-2113601488)！";
					break;
				case -2113601487 :
				    result = "枚举设备错误(-2113601487)！";
					break;
				case -2113601486 :
				    result = "未检测到密钥，请先插入密钥！";
					break;m
				case -2113601485 :
				    result = "设备连接错误(-2113601485)！";
					break;
				case -2113601484 :
				    result = "产生随机数错误(-2113601484)！";
					break;
				case -2113601483 :
				    result = "SE_ECC_ERROR_SKF_SET_SYMKEY(-2113601483)！";
					break;
				case -2113601482 :
				    result = "对称加密初始化错误(-2113601482)！";
					break;
				case -2113601481 :
				    result = "对称加密错误(-2113601481)！";
					break;
				case -2113601480 :
				    result = "设备管理员口令错误(-2113601480)！";
					break;
				case -2113601479 :
				    result = "打开应用错误(-2113601479)！";
					break;
				case -2113601478 :
				    result = "设备已锁(-2113601478)！";
					break;
				case -2113601477 :
				    result = "密钥密码错误，请重新输入！";
					break;
				case -2113601476 :
				    result = "枚举应用错误(-2113601476)！";
					break;
				case -2113601475 :
				    result = "删除应用错误(-2113601475)！";
					break;
				case -2113601474 :
				    result = "创建应用错误(-2113601474)！";
					break;
				case -2113601473 :
				    result = "创建容器错误(-2113601473)！";
					break;
				case -2113601472 :
				    result = "设备不支持错误(-2113601472)！";
					break;
				case -2113601471 :
				    result = "打开容器错误(-2113601471)！";
					break;
				case -2113601470 :
				    result = "导出公钥错误(-2113601470)！";
					break;
				case -2113601466 :
				    result = "对称加密错误(-2113601466)！";
					break;
				case -2113601465 :
				    result = "导入密钥对错误(-2113601465)！";
					break;
				case -2113601464 :
				    result = "修改设备口令错误(-2113601464)！";
					break;
				case -2113601463 :
				    result = "导入证书错误(-2113601463)！";
				    break;
				case -2113601462 :
				    result = "导出证书错误(-2113601462)！";
				    break;
				case -2113601461 :
				    result = "创建文件错误(-2113601461)！";
				    break;
				case -2113601460 :
				    result = "写入文件错误(-2113601460)！";
				    break;
				case -2113601459 :
				    result = "获取文件信息错误(-2113601459)！";
				    break;
			    case -2113601458 :
				    result = "读取文件错误(-2113601458)！";
				    break;
				case -2113601457 :
				    result = "获取公钥错误(-2113601457)！";
				    break;
				case -2113601454 :
				    result = "生成密钥对错误(-2113601454)！";
				    break;
			    case -2113601453 :
				    result = "证书已过期(-2113601453)！";
				    break;
			    case -2113601452 :
				    result = "多个设备错误(-2113601452)！";
				    break;
				case -2113601451 :
				    result = "没有设备(-2113601451)！";
				    break;
				case -2113601450 :
				    result = "自动检测设备错误(-2113601450)！";
				    break;
				case -2113601449 :
				    result = "设备无法识别(-2113601449)！";
				    break;
		        case -2113601448 :
				    result = "获取会话密钥错(-2113601448)！";
				    break;
				case -2113601447 :
				    result = "导入会话密钥错(-2113601447)！";
				    break;
				case -2113601446 :
				    result = "初始化摘要错误(-2113601446)！";
				    break;
				case -2113601445 :
				    result = "更新摘要错误(-2113601445)！";
				    break;
				case -2113601444 :
				    result = "生成会话密钥错(-2113601444)！";
				    break;
				case -2113601442 :
				    result = "导入会话密钥错(-2113601442)！";
				    break;
			    case -2113601441 :
				    result = "缓冲区太小(-2113601441)！";
				    break;
			    case -2113601440 :
				    result = "P7签名数据初始化错误(-2113601440)！";
				    break;
			    case -2113601439 :
				    result = "产生随机数错误(-2113601439)！";
				    break; 
				case -2113601438 :
				    result = "对称加密错误(-2113601438)！";
				    break; 
				case -2113601437 :
				    result = "对称解密错误(-2113601437)！";
				    break; 
				case -2113601436 :
				    result = "导出公钥错误(-2113601436)！";
				    break;   
				case -2113601435 :
				    result = "添加p7算法错误(-2113601435)！";
				    break;   
				case -2113601434 :
				    result = "P7数据处理错误(-2113601434)！";
				    break;   
				case -2113601433 :
				    result = "SE_ECC_ERROR_ENVELOPE_ADD_RECIP(-2113601433)！";
				    break;   
				case -2113601432 :
				    result = "签名数据错误(-2113601432)！";
				    break;   
			    case -2113601431 :
				    result = "摘要数据处理错误(-2113601431)！";
				    break;   
				case -2113601430 :
				    result = "加密更新错误(-2113601430)！";
				    break;   
				case -2113601429 :
				    result = "加密处理错误(-2113601429)！";
				    break;   
				case -2113601428 :
				    result = "解密初始化错误(-2113601428)！";
				    break;   
				case -2113601427 :
				    result = "解密更新错误(-2113601427)！";
				    break;   
				case -2113601426 :
				    result = "解密处理错误(-2113601426)！";
				    break;   
				case -2113601425 :
				    result = "p7格式错误(-2113601425)！";
				    break;   
				case -2113601424 :
				    result = "SE_ECC_ERROR_P7_NO_RECIP(-2113601424)！";
				    break;  
				case -2113601423 :
				    result = "算法非法(-2113601423)！";
				    break;   
				case -2113601422 :
				    result = "私钥长度错误(-2113601422)！";
				    break;   
				case -2113601421 :
				    result = "P7签名错误(-2113601421)！";
				    break;   
				case -2113601420 :
				    result = "验证P7签名错误(-2113601420)！";
				    break;   
			    case -2113601419 :
				    result = "P7签名设置版本错误(-2113601419)！";
				    break;  
				case -2113601418 :
				    result = "锁设备错误(-2113601418)！";
				    break;  
			    case -2113601417 :
				    result = "缓冲区太小(-2113601417)！";
				    break;  
				case -2113601416 :
				    result = "从LDAP获取证书错误(-2113601416)！";
				    break;  
				case -2113601415 :
				    result = "连接OCSP服务器错误(-2113601415)！";
				    break;   
			    case -2113601414 :
				    result = "参数错误(-2113601414)！";
				    break;   
				case -2113601413 :
				    result = "CRL格式错误(-2113601413)！";
				    break;   
				case -2113601412 :
				    result = "证书废除(-2113601412)！";
				    break;      
				case -2113601411 :
				    result = "证书链格式错误(-2113601411)！";
				    break;   
				case -2113601410 :
				    result = "验证证书链错误(-2113601410)！";
				    break;     
				case -2113601409 :
				    result = "管理员密码错误(-2113601409)！";
				    break;  
				case -2113601408 :
				    result = "设备标签格式错误(-2113601408)！";
				    break;      
				case -2113601407 :
				    result = "删除容器错误(-2113601407)！";
				    break;      
				case -2113601406 :
				    result = "枚举文件错误(-2113601406)！";
				    break;      
				case -2113601405 :
				    result = "删除文件错误(-2113601405)！";
				    break;    
				case -2113601404 :
				    result = "枚举容器错误(-2113601404)！";
				    break;      
				case -2113601403 :
				    result = "关闭应用错误(-2113601403)！";
				    break;      
				case -2113568768 :
				    result = "SE_ECC_ERROR_FUNC_LOCAL(-2113568768)！";
				    break;             
				case -2113666824 :
				    result = "无效的登录凭证(-2113666824)！";
					break;
				case -2113666823 :
				    result = "参数错误(-2113666823)！";
					break;
				case -2113666822 :
				    result = "不是服务器证书(-2113666822)！";
					break;
				case -2113666821 :
				    result = "登录错误(-2113666821)！";
					break;
				case -2113666820 :
				    result = "证书验证方式错误(-2113666820)！";
					break;
				case -2113666819 :
				    result = "随机数验证错误(-2113666819)！";
					break;
				case -2113666818 :
				    result = "与单点登录客户端代理通信(-2113666818)！";
					break;
				default:
					result="出现错误，请联系管理员！";
				break;
			}
			return result;
		}
	</script>
</body>
</html>