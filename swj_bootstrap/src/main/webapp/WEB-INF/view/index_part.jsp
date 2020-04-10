<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="${base}/resource/street/css/layout.css"/>
	<link rel="stylesheet" type="text/css" href="${base}/resource/street/css/reset.css"/>
	<link rel="stylesheet" id="easyuiTheme" type="text/css" href="${base}/resource/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${base}/resource/ui/themes/icon.css">
	<script type="text/javascript" src="${base}/resource/ui/jquery.min.js"></script>
	<script type="text/javascript" src="${base}/resource/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resource/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="${base}/resource/highcharts/modules/drilldown.js"></script>
	<script type="text/javascript" src="${base}/resource/highcharts/modules/exporting.js"></script>
	<script type="text/javascript" src="${base}/resource/highcharts/highcharts-3d.js"></script>
	<script type="text/javascript" src="${base}/resource/js/sockjs.js"></script>
	<script type="text/javascript" src="${base}/resource/js/stomp.js"></script>
	<script type="text/javascript" src="${base}/resource/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${base}/resource/ui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${base}/resource/echarts/echarts.js"></script>
</head>
<body style="background:url(${base}/resource/street/images/bg-img.jpg) fixed center bottom no-repeat;background-size: cover;">
<!--hhzl_box start-->
<div class="hhzl_box" >
    
    <div class="cont-bottom clearfix">
      <div class="l left">
      	<!--待办任务-->
        <s:controller url="/index/currentTask"/>
      </div>
      <div class="rt right">
      	<!--通知公告-->
        <s:controller url="/index/ghtx"/>
      </div>
      <div class="l left" style="width: 100%;">
      	<!--通知公告-->
        <s:controller url="/index/xqtx"/>
      </div>
   </div>
    
  </div>
<!--hhzl_box end-->
<script type="text/javascript">
function addTabs(title, url) {
	 parent.addTabs(title, url);
}

function completeCurrentTask(id,name,processDefinitionName,actId,processDefinitionKey,businessKey){
	parent.completeCurrentTask(id,name,processDefinitionName,actId,processDefinitionKey,businessKey);
}

function showRemindDetail(type,id){
	if(type!=''&&id!=''){
		if(type=='viewCertificateInfo'){
			var win=creatWin('过期-证照信息',880,530,'icon-search','/certificateInfo/view/'+id+'?businessType=single');
		    win.window('open');
		}else if(type=='viewPublicBusiness'){
			var win=creatWin('逾期-证照领用信息表',780,450,'icon-search','/publicBusiness/view/'+id+'?type=use');
		    win.window('open');
		}else if(type=='viewPrivateApply'){
			var win=creatWin('逾期-因私出国（境）申请',880,450,'icon-search','/certificateInfoApply/view/'+id);
		    win.window('open');
		}
		
			 
	}
}
</script>
</body>
</html>
