<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div class="modal-header  bg-primary">
   <h4 class="modal-title"><span class='glyphicon glyphicon-ok'></span>&nbsp;选择-部门</h4>
</div>
<div class="modal-body" style="height: 240px;">
  	<div class="form-group">
  		<div class="col-sm-12">
			<div id="userDepartEditTree" style="overflow-y: auto;height: 230px;"></div>
		</div>
  	</div>
</div>	
<div class="modal-footer center-block">
  <center>
  	<button type="button" class="btn btn-primary" onclick="userDepartTreeViewSure();"><span class='glyphicon glyphicon-ok'></span>&nbsp;确认</button>
	<button type="button" class="btn btn-default" data-dismiss="modal"><span class='glyphicon glyphicon-remove'></span>&nbsp;关闭</button>
  </center>
</div>

<script type="text/javascript">
$(function(){
	$.ajax({
		cache : false,
		type : "POST",
		url : "${base}/depart/jsonTree",
		async : false,
		dataType: 'json',
		success : function(treeData) {
			$("#userDepartEditTree").treeview(
				{
					data:treeData,
					collapseIcon: "glyphicon glyphicon-menu-up",
					expandIcon: "glyphicon glyphicon-menu-down",
					selectedIcon: 'glyphicon glyphicon-ok',
					showBorder:true
				}
			);
		}
	});
});

function userDepartTreeViewSure(){
	var selectDepart=$('#userDepartEditTree').treeview('getSelected');
	if(selectDepart.length>0){
		$("#${elementId}").val(selectDepart[0].id);
		$("#${elementName}").html(selectDepart[0].text);
		$("#${elementName}").parent().attr("class","btn btn-default");
		$("#userEditDepartModal").modal('hide');
		$('.modal-backdrop:last').remove();
	}else{
		tipsModalInit('请选择一条数据！');
	}
}
</script>