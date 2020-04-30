<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
	<div class="container-fluid" style="padding-left: 25px;padding-right: 20px;">
		<div class="row">
			<form class="navbar-form navbar-left" role="search">
			  <div class="form-group">
				姓名：<input type="text" class="form-control" id="userListName"/>
			  </div>
			  <div class="form-group">
				&nbsp;&nbsp;账号：<input type="text" class="form-control" id="userListAccount"/>
			  </div>
			  <div class="form-group">
				&nbsp;&nbsp;是否锁定：
				<select class="selectpicker" id="userListLock">
				  <option value="">--请选择--</option>
		          <option value="TRUE">是</option>
		          <option value="FALSE">否</option>
				</select>
			  </div>
			  <button type="button" class="btn btn-default" onclick="listUserReLoad();"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
			  <button type="button" class="btn btn-primary" onclick="userAdd();"><span class='glyphicon glyphicon-plus'></span>&nbsp;添加</button>
			  <button type="button" class="btn btn-warning" onclick="reSetPassword();"><span class='glyphicon glyphicon-repeat'></span>&nbsp;重置密码</button>
			  <button type="button" class="btn btn-warning" onclick="userLock();"><span class='glyphicon glyphicon-lock'></span>&nbsp;锁定/解锁用户</button>
			</form>
		</div>

		<div class="row">
			<table id="userListTable" data-single-select="true" class="table table-bordered table-hover" data-click-to-select="true">
				<thead>
					<tr style="height: 52px;">
						<th data-checkbox="true"></th>
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
		
		<div class="modal fade" id="userEditModal" data-backdrop="static" data-keyboard="false">
	        <div class="modal-dialog" style="width:900px;overflow: hidden;">
	            <div class="modal-content" style="width:900px;overflow: hidden;"></div>
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
		pageSize : 10,
		pageNumber : 1,
		showColumns : false,
		sidePagination : "server",
		queryParams : queryParams
	});
	$('.selectpicker').selectpicker({'width':110});
});

function userListFormatter(value, row, index){
	var e = '<a class="btn btn-primary" href="#" mce_href="#" title="编辑" onclick="userEdit(\''+ row.id+ '\')"><span class="glyphicon glyphicon-pencil"></span>&nbsp;编辑</a> ';
	var d = '<a class="btn btn-warning" href="#" title="删除"  mce_href="#" onclick="userRemove(\''+ row.id+ '\')"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a> ';
	return e + d;
}


function queryParams(params){
	var param = {
		page: params.offset/params.limit+1,
		rows: params.limit,
		name:$('#userListName').val(),
		accountNo:$('#userListAccount').val(),
		islocked:$("#userListLock").val()
	};
	return param;
}

function listUserReLoad() {
	$('#userListTable').bootstrapTable('refresh');
}

function userAdd(){
	if($("#userEditForm")[0]!=undefined){
		$("#userEditForm")[0].reset();
	}	
	$("#userEditModal").modal({
        remote: '${base}/user/add'
    });
}

function userEdit(id){
	if($("#userEditForm")[0]!=undefined){
		$("#userEditForm")[0].reset();
	}	
	$("#userEditModal").modal({
        remote: '${base}/user/edit/'+id
    });
}

function userLock(){
	var row=$("#userListTable").bootstrapTable('getSelections');
	if(row.length==1){
		confirmModalInit("确认锁定/解锁用户吗?");
		$("#confirmOk").off().on("click", function() {
			$.ajax({ 
				type: 'POST', 
				url: '${base}/user/lock/'+row[0].id,
				dataType: 'json',  
				success: function(data){ 
					if(data.success){
						listUserReLoad();
						tipsModalInit(data.info);
					}else{
						tipsModalInit(data.info);
					}
				} 
			});
		});
	}else{
		tipsModalInit('请先选中一条数据！');
	}
}

function userRemove(id){
	confirmModalInit("确认删除用户吗?");
	$("#confirmOk").off().on("click", function() {
		$.ajax({ 
			type: 'POST', 
			url: '${base}/user/delete/'+id,
			dataType: 'json',  
			success: function(data){ 
				if(data.success){
					listUserReLoad();
					tipsModalInit(data.info);
				}else{
					tipsModalInit(data.info);
				}
			} 
		});
	});
}

function reSetPassword(){
	var row=$("#userListTable").bootstrapTable('getSelections');
	if(row.length==1){
		confirmModalInit("确认重置密码为123456吗?");
		$("#confirmOk").off().on("click", function() {
			$.ajax({ 
				type: 'POST', 
				url: '${base}/user/reSetPwd/'+row[0].id,
				dataType: 'json',  
				success: function(data){ 
					if(data.success){
						listUserReLoad();
						tipsModalInit(data.info);
					}else{
						tipsModalInit(data.info);
					}
				} 
			});
		});
	}else{
		tipsModalInit('请先选中一条数据！');
	}
}
</script>