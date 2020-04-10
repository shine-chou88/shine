<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<html>
<head>
	<title>${title}</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" contect="no-store"/>
	<style type="text/css">
		#checkcontent {
		    position: relative;
		    width: 960px;
		    height: 878px;
		    margin: 0 auto;
		    background-color: #fff;
		    background-repeat: no-repeat;
		}
        a{
            text-decoration: none;
            outline: none;
            cursor: pointer;
        }
        #buzou {
            width: 934px;
            height:800px;
            position: absolute;
            left:1px;
            top:20px;
            text-align: left;
        }
        #buzou p {
            font-family: 宋体;
            font-size: 16px;
            color:#626262;
            letter-spacing:1.5px;
            line-height: 20px;
            margin-left: 22px;
            margin-top: 8px;
        }
    </style>
</head>
<body style="background-color: #f5f5f5; width: 98%; height: 100%; position: relative;">
    <div id="checkcontent">
           <div id="buzou">
           		<p><font style="color: #000;font-size: 2em;font-weight: normal;font-family: 'Segoe UI','Lucida Grande',Verdana,Arial,Helvetica,sans-serif;text-shadow: 2px 2px 4px #4080FF;">密钥使用说明</font></p>
           		<p>&nbsp;</p>
                <p>
                1、请点击下载<a href="${base}/resource/download/Firefox-full-latest-v58.exe" style="font-weight:600; color:#1d4a81;">火狐浏览器</a>，并安装。
                </p>
                <img alt="" src="${base}/resource/images/firefox_download.png" style="margin-left: 22px; margin-top: 8px; width:887px; border:1px solid #ddd;" />
                <p ><br/>
                 2、打开火狐浏览器，请点击下载<a href="${base}/resource/download/safeengine-1.0.1.0.xpi" style="font-weight:600; color:#1d4a81;">SafeEngine</a>扩展，并“允许”和“添加”。
                </p>
                <img alt="" src="${base}/resource/images/SafeEngine_download.png" style="margin-left: 22px; margin-top: 8px; width:887px;border:1px solid #ddd;" />
                <img alt="" src="${base}/resource/images/SafeEngine_allow.png" style="margin-left: 22px; margin-top: 8px; width:887px;border:1px solid #ddd;" />
                <img alt="" src="${base}/resource/images/SafeEngine_add.png" style="margin-left: 22px; margin-top: 8px; width:887px;border:1px solid #ddd;" />
                <p><br/>
                 3、SafeEngine扩展添加好之后，请点击下载<a href="${base}/resource/download/ShecAid.exe" style="font-weight:600; color:#1d4a81;">协卡助手</a>，并安装。
                </p>
               <img alt="" src="${base}/resource/images/xkzs_download.png" style="margin-left: 22px; margin-top: 8px; width:887px;border:1px solid #ddd;" />
                <p>
                <p><br/>
                 4、修改密钥密码，协卡助手安装好之后插入密钥会自动弹出以下界面，点击图中的“SM2”，如图
                </p>
               <img alt="" src="${base}/resource/images/update_pwd_step1.jpg" style="margin-left: 22px; margin-top: 8px; width:887px;border:1px solid #ddd;" /><br/>
               	<p>点击“SM2”后出现以下界面，点击图中的“修改口令”就可以修改密码了<br/></p>
               	<img alt="" src="${base}/resource/images/update_pwd_step2.jpg" style="margin-left: 22px; margin-top: 8px; width:887px;border:1px solid #ddd;" />
                <p>
            </div>
        </div>
</body>
</html>