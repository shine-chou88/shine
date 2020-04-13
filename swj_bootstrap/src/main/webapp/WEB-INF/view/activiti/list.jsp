<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<div class="container-fluid"
	style="padding-left: 25px; padding-right: 20px;">
	<div class="row">
		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				姓名：<input type="text" class="form-control" id="userListName" />
			</div>
			<button type="button" class="btn btn-default" onclick="reLoad()">查询</button>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal"><span class='glyphicon glyphicon-plus'></span>&nbsp;添加</button>
		</form>
	</div>

	<div class="row">
		<table id="processListTable">
			<thead>
				<tr>
					<th data-checkbox="true"></th>
					<th data-field="id">ID</th>
					<th data-field="key" data-align="center">Key</th>
					<th data-field="name" data-align="center">流程名称</th>
					<th data-field="version" data-align="center">版本号</th>
					<th data-field="deploymentId" data-align="center">部署ID</th>
					<th data-align="center" data-formatter="processFormatter">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div class="modal" id="viewProcessModal">
        <div class="modal-dialog">
            <div class="modal-content"></div>
        </div>
    </div>
</div>

<script type="text/javascript">
$(function() {
	load();
});

function load() {
	$('#processListTable')
		.bootstrapTable(
			{
				method: 'post',
		　　　　 	contentType: "application/x-www-form-urlencoded",
				url :  "${base}/activiti/process/jsonPagination",
				cache:false,
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				singleSelect : true, // 设置为true将禁止多选
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server"
			});
}

function processFormatter(value, row, index){
	var e = '<a class="btn btn-primary" href="#" mce_href="#" title="重新部署流程" onclick="deployProcess(\''+ row.id+ '\')"><span class="glyphicon glyphicon-pencil"></span>&nbsp;重新部署流程</a> ';
	var d = '<a class="btn btn-warning" href="${base}/activiti/process/view/'+row.id+'" title="查看流程图" data-target="#viewProcessModal"  mce_href="#"><span class="glyphicon glyphicon-search"></span>&nbsp;查看流程图</a> ';
	return e + d;
}

function deployProcess(id){
	confirmModalInit("确认重新部署该流程吗?");
    $("#confirmOk").off().on("click", function() {
        $("#myConfirm").modal("hide");
        $.ajax({ 
			type: 'POST', 
			url: '${base}/activiti/process/deploy?id='+id,
			dataType: 'json',  
			success: function(data){ 
				if(data.success){
					tipsModalInit(data.info);
					$('#processListTable').bootstrapTable('refresh');
				}else{
					$.messager.alert('系统提示',data.info,'error');
					tipsModalInit(data.info);
				}
			} 
		});
    });
}
function viewProcess(id){
	
}
</script>