<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
    <title>${title}</title>
</head>
<body class="easyui-layout" style="text-align: left;" id="content">
	<div data-options="region:'north'" style="height: 84px;">
		<%@include file="/WEB-INF/view/top.jsp"%>
	</div>
	<div data-options="region:'west',split:true,title:'系统菜单',minWidth:240,maxWidth:240">
		<jsp:include page="/WEB-INF/view/left.jsp"/>
	</div>
	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools',fit:true,border:false" style="position: absolute; overflow-y: auto;">
			<div id="index_tabs" title="首页" style="overflow:hidden;">
				<iframe id="indexIframe" scrolling="yes" frameborder="0" style="width:100%;height:100%;margin:0px;padding:0px"></iframe>
			</div>
			<!-- tabs上的右键菜单 -->
			<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
				<div name="close">关闭</div>
				<div name="Other">关闭其他</div>
				<div name="All">关闭所有</div>
			</div>
		</div>
	</div>
	<div data-options="region:'south'" style="height: 20px; text-align: center; line-height: 18px;font-size: 14px;">技术支持：上海长城电子信息网络有限公司</div>
<script type="text/javascript">
	function completeCurrentTask(id,name,processDefinitionName,actId,processDefinitionKey,businessKey){
		var win=null;
		if(processDefinitionName.indexOf('因私')>=0){
			if(actId=='DRAF'){//直接跳转到对应业务的填报页面
				if(processDefinitionKey=='DEPUTY_CADRES_PASSPORT_APPLY' || processDefinitionKey=='OFFICAL_CADRES_PASSPORT_APPLY'){
					 win=creatWin('修改-因私出国(境)申请',880,600,'icon-edit','/certificateInfoApply/edit/'+businessKey);
				}					
			}else{
				if(name=='申请人扫码领用护照'){
					$.messager.alert('系统提示','请前往组织人事处工作人员处扫码领用！','info');
				}else if(name=='申请人扫码归还护照'){
					$.messager.alert('系统提示','请前往组织人事处工作人员处扫码归还！','info');
				}else if(name=='申请人扫码领回旧护照'){
					$.messager.alert('系统提示','请前往组织人事处工作人员处扫码领回！','info');
				}else{
					win=creatWin('审批',1050,650,'icon-edit','/activiti/process/toComplete/'+id);
				}
			}
		}else if(processDefinitionName=='因公出国（境）证照领用信息表'){
				win=creatWin('确认-因公出国（境）证照领用信息表',780,650,'icon-search','/publicBusiness/confirmUseRegister/'+id+"?todoType=unRead");
		}else if(processDefinitionName=='因公出国（境）证照归还信息表'){
				win=creatWin('确认-因公出国（境）证照归还信息表',780,650,'icon-search','/publicBusiness/confirmBackRegister/'+id+"?todoType=unRead");
		}
		if(win!=null){
		    win.window('open');
		}    
		
	}
	
	function initIndexIframe(){
		$('#indexIframe').attr('src','${base}/index/indexPart');		
	}
	
	//解决ie easyui iframe js 重复加载bug
	setTimeout("initIndexIframe()",100);
</script>
</body>
</html>