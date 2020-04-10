var prefix = base+"/user";
$(function() {
	load();
});

function load() {
	$('#userListTable')
		.bootstrapTable(
			{
				method: 'post',
		　　　　 	contentType: "application/x-www-form-urlencoded",
				url : prefix + "/jsonPagination",
				cache:false,
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				singleSelect : true, // 设置为true将禁止多选
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : queryParams,//查询条件
				columns : [
					{checkbox : true},
					{field : 'name',title : '姓名'},
					{field : 'accountNo',title : '账号',align : 'center'},
					{field : 'mobileNo',title : '手机号',align : 'center'},
					{field : 'departName',title : '部门名称',align : 'center'},
					{field : 'locked',title : '是否锁定',align : 'center'},
					{field : 'loginCount',title : '登录次数',align : 'center'},
					{field : 'lastLoginTimeStr',title : '最后登录时间',align : 'center'},
					{
						title : '操作',
						field : 'operation',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary" href="#" mce_href="#" title="编辑" onclick="edit(\''+ row.id+ '\')">编辑</a> ';
							var d = '<a class="btn btn-warning" href="#" title="删除"  mce_href="#" onclick="remove(\''+ row.id+ '\')">删除</a> ';
							return e + d;
						}
					} ]
			});
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
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	alert('正在开发。。。');
}
function remove(id) {
	alert('正在开发。。。');
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : base+"user/save",
		data : $('#userEditForm').serialize(),// 你的formid
		async : false,
		success : function(data) {
			if (data.success == true) {
				alert(data.info);
				$('#myModal').modal('handleUpdate');
				$('#myModal').modal('hide');
				reLoad();
			} else {
				alert("出现错误，请联系管理员！");
			}

		}
	});

}