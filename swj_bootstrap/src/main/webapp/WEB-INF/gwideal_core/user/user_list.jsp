<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
	<div class="container-fluid" style="padding-left: 25px;padding-right: 20px;">
		<div class="row">
			<form class="navbar-form navbar-left" role="search">
			  <div class="form-group">
				姓名：<input type="text" class="form-control" id="userListName"/>
			  </div>
			  <button type="button" class="btn btn-default" onclick="reLoad()">查询</button>
			  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加</button>
			</form>
		</div>

		<div class="row">
			<table id="userListTable">
				<thead>
					<tr>
						<th data-field="name">姓名</th>
						<th data-field="accountNo" data-align="center">账号</th>
						<th data-field="mobileNo" data-align="center">手机号</th>
						<th data-field="departName" data-align="center">部门名称</th>
						<th data-field="locked" data-align="center">是否锁定</th>
						<th data-field="loginCount" data-align="center">登录次数</th>
						<th data-field="lastLoginTimeStr" data-align="center">最后登录时间</th>
						<th data-align="center" data-formatter="userListFormatter">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">用户-添加</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-horizontal" role="form" id="userEditForm">
						  <div class="form-group">
						  	<label class="control-label col-sm-2">姓名：</label>
						  	<div class="col-sm-9">
								<input type="text" class="form-control" name="name"/>
							</div>
						  </div>
						  <div class="form-group">
						  	<label class="control-label col-sm-2">账号：</label>
						  	<div class="col-sm-9">
								<input type="text" class="form-control" name="accountno"/>
							</div>
						  </div>
				</form>
		      </div>
		      <div class="modal-footer col-center-block">
		        <button type="button" class="btn btn-default" onclick="save()">保存</button>
		        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
	
<script type="text/javascript">
$(function() {
	$('#userListTable').bootstrapTable({
			method: 'post',
	　　　　 	contentType: "application/x-www-form-urlencoded",
			url : "${base}/user/jsonPagination",
			cache:false,
			dataType : "json",
			pagination : true,
			singleSelect : true,
			pageSize : 10,
			pageNumber : 1,
			showColumns : false,
			sidePagination : "server",
			queryParams : queryParams
		});
});

function userListFormatter(value, row, index){
	var e = '<a class="btn btn-primary" href="#" mce_href="#" title="编辑" onclick="edit(\''+ row.id+ '\')">编辑</a> ';
	var d = '<a class="btn btn-warning" href="#" title="删除"  mce_href="#" onclick="remove(\''+ row.id+ '\')">删除</a> ';
	return e + d;
}


function queryParams(params){
	var param = {
		page: params.offset/params.limit+1,
		rows: params.limit,
		name:$('#userListName').val()
	};
	return param;
}

function reLoad() {
	$('#userListTable').bootstrapTable('refresh');
}

</script>