<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html lang="en">
<head>
    <title>${title}</title>
</head>
<body>
<div class="container-fluid">
	<%@include file="/WEB-INF/view/top.jsp"%>
	<div class="row">
		<%@include file="/WEB-INF/view/left.jsp"%>
		<div class="container-tabs">
			<div class="price" id="indexTabs">
				<div class="category">
					<ul>
						<li class="active" title="首页">首页</li>
						<li title="计算">计算</li>
						<li title="存储">存储</li>
					</ul>
					<a href="javascript:;" class="prev"><span></span></a>
					<a href="javascript:;" class="next"><span></span></a>
				</div>
				<div class="cont active" title="首页">
					<ul class="tab-content">
						<li>
							<a href="#">
								<div class="cont_main">
									<h3 class="title">云服务器</h3>
									<p class="desc">稳定安全，高易用可弹性伸缩的计算服务</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#">
								<div class="cont_main">
									<h3 class="title">云数据库 MySQL</h3>
									<p class="desc">一体化多维度监控，高效管理海量数据库</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#">
								<div class="cont_main">
									<h3 class="title">对象存储 COS</h3>
									<p class="desc">可靠、安全、易用的可扩展文件存储</p>
								</div>
							</a>
						</li>
					</ul>
				</div>
				<div class="cont" title="计算">
					<ul class="tab-content">
						<li>
							<a href="#">
								<div class="cont_main">
									<h3 class="title">计算</h3>
									<p class="desc">稳定安全，高易用可弹性伸缩的计算服务</p>
								</div>
							</a>
						</li>					
					</ul>
				</div>
				<div class="cont" title="存储">
					<ul class="tab-content">
						<li>
							<a href="#">
								<div class="cont_main">
									<h3 class="title">存储</h3>
									<p class="desc">稳定安全，高易用可弹性伸缩的计算服务</p>
								</div>
							</a>
						</li>					
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/view/footer.jsp"%>
</div>
<script type="text/javascript">
	function addTab(tabName,url){
		var tabs = $("#indexTabs");
		var currentTab=$("#indexTabs .category ul li[title='"+tabName+"']");
		tabs.find("li").removeClass("active");
		$(".container-tabs .price div").removeClass("active");
		if(currentTab.text()==tabName){
			currentTab.attr("class","active");
			$(".container-tabs .price div[title='"+tabName+"']").attr("class","cont active");
		}else{
			tabs.find(".category ul").append("<li class='active' title="+tabName+">"+tabName+"</li>");
			$.ajax( {
				type : 'POST',
				url :   url,
				dataType : 'html',
				success : function(data) {
					$(".container-tabs .price").append("<div class='cont active' title='"+tabName+"'>"+data+"</div>");
				}
			});
		}
		$('.category ul li').click(function(){
			indexC = $(this).index();
			$(this).addClass('active').siblings().removeClass('active');
			$('.cont').eq(indexC).addClass('active').siblings().removeClass('active');
		});
	}

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
	
	$(function(){
		//左边菜单选中
		$('.lsm-sidebar ul li a').click(function(){
			$('.lsm-sidebar ul li a').removeClass("active");
			if($(this).attr("class")!='parent'){
				$(this).addClass('active');				
			}
		});
		//选项卡切换
		$('.category ul li').click(function(){
			indexC = $(this).index();
			$(this).addClass('active').siblings().removeClass('active');
			$('.cont').eq(indexC).addClass('active').siblings().removeClass('active');
		});
		//按钮箭头
		var catew = $('.category').width()-150;
		var cateLiw = 0;
		$('.category ul li').each(function(){
			cateLiw +=$(this).outerWidth();
		})
		var i =0;
		$('.next').click(function(){
			$('.category ul').animate({
				"margin-left":-catew+'px'
			},500)
			i++;
			if((catew+150)*i+(catew+150)>=cateLiw){
				$('.prev').show();
				$('.next').hide();
			}
		})
		$('.prev').click(function(){
			$('.category ul').animate({
				"margin-left":0+'px'
			},500)
			$(this).hide();$('.next').show();
		})
	})
</script>
</body>
</html>