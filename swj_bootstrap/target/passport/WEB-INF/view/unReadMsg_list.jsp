<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
	<table id="unReadMsg_dg" class="easyui-datagrid" data-options="singleSelect:true,collapsible:true,url:'${base}/index/unReadMsgJsonPagination',
			method:'post',fit:true,selectOnCheck: true,checkOnSelect: true,remoteSort:true,rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'id',align:'left',resizable:false,sortable:true,hidden:true" width="5%">ID</th>
				<th data-options="field:'functionType',align:'left',resizable:false,sortable:true,hidden:true" width="5%">功能类型</th>
				<th data-options="field:'title',align:'left',resizable:true,formatter:titleColumn,sortable:false" width="30%" nowrap="nowrap">标题</th>
				<th data-options="field:'type',align:'center',resizable:false,sortable:false" width="25%" nowrap="nowrap">类型</th>
				<th data-options="field:'creatorName',align:'center',resizable:false,sortable:false" width="15%" nowrap="nowrap">发布人</th>
				<th data-options="field:'releaseTime',align:'center',resizable:false,sortable:false" width="15%">发布时间</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function titleColumn(value,row,index){
		if(row.functionType=='TZGG' || row.functionType=='WWTZGG'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:articleViewUnRead('"+row.id+"');\">"+value+"</a>";
		}else if(row.functionType=='WWGZ14'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:leaderInstructionsFeedbackUnRead('"+row.id+"','"+row.type+"');\">"+value+"</a>";
		}else if(row.functionType=='WWGZ23'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:leaderInstructionsViewUnRead('"+row.id+"','"+row.type+"');\">"+value+"</a>";
		}else if(row.functionType=='ACTION'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:actionInformationUnRead('"+row.id+"');\">"+value+"</a>";
		}else if(row.functionType=='DRILLS'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:drillsViewUnRead('"+row.id+"');\">"+value+"</a>";
		}else if(row.functionType=='JCXX'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:jcxxUnRead('"+row.id+"','"+row.type+"');\">"+value+"</a>";
		}else if(row.functionType=='STABLE'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:stableUnRead('"+row.id+"');\">"+value+"</a>";
		}else if(row.functionType=='MESSAGE'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:replyUnRead('"+row.id+"');\">"+value+"</a>";
		}else if(row.functionType=='REPLY'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:replyReadUnRead('"+row.id+"');\">"+value+"</a>";
		}else if(row.functionType=='DBQD'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:supervisionFeedbackUnRead('"+row.id+"');\">"+value+"</a>";
		}else if(row.functionType=='DBQD_U'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:supervisionFeedbackUser('"+row.id+"');\">"+value+"</a>";
		}else if(row.functionType=='PACJAPPLY'){
			return "<a style=\"color:blue;text-decoration: underline;\" href=\"javascript:void(0);\" onclick=\"javascript:activitiComplete('"+row.id+"');\">"+value+"</a>";
		}
	}
	
	function activitiComplete(id){
		var win=creatWin('审批-平安创建申报',880,620,'icon-edit','/activiti/process/toComplete/'+id);
	    win.window('open');
	}
	
	function articleViewUnRead(id){
		window.open("${base}/xxfb/view/"+id);
	}
	
	function supervisionFeedbackUnRead(id){
		var win=creatFirstWin('反馈-督办清单',950,560,'icon-search','/supervisionList/feedBack/'+id);
		win.window('open');
	}
	function supervisionFeedbackUser(id){
		var win=creatFirstWin('反馈-督办清单',950,560,'icon-search','/supervisionList/feedBack/'+id+"?type=user");
		win.window('open');
	}
	function leaderInstructionsFeedbackUnRead(id,type){
		var win=creatFirstWin('反馈-'+type,950,560,'icon-search','/leaderInstructions/feedBack/'+id);
		win.window('open');
	}
	function leaderInstructionsViewUnRead(id,type){
		var win=creatFirstWin('查看-'+type,850,550,'icon-search','/leaderInstructions/detail/'+id);
	    win.window('open');
	}
	function actionInformationUnRead(id){
		var win=creatFirstWin('反馈-行动性信息、快报、情报',900,550,'icon-search','/actionInformation/feedBack/'+id);
		win.window('open');
	}
	function drillsViewUnRead(id){
		var win=creatFirstWin('查看-应急处突演练',650,450,'icon-search','/drills/detail/'+id);
	   win.window('open');
	}
	function jcxxUnRead(id,type){
		var win=creatWin('反馈-'+type,1050,600,'icon-search','/jcxx/feedback/'+id);
	   win.window('open');
	}
	function stableUnRead(id){
		var win=creatWin('反馈-涉稳信息报送',1050,600,'icon-search','/stable/feedback/'+id);
	   win.window('open');
	}
	function replyUnRead(id){
	   var win=creatWin('回复-信息提示',1050,600,'icon-search','/jcxx/reply/'+id);
	   win.window('open');
	}
	function replyReadUnRead(id){
	   var win=creatWin('阅读-信息回复',1050,600,'icon-search','/jcxx/replyRead/'+id);
	   win.window('open');
	}
	</script>
</body>
</html>

