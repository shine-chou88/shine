<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
<script type="text/javascript">
function queryDepart(){
	$('#depart_select_tree').treegrid('reload',{
		name:$('#departName').val(),
		code:$('#departCode').val()
	});	 
}

function selectDepartTree(row){
	//调用父窗口方法
	var t = $('#selectType').val();
	var num = $('#num').val();
	var depart_id = $('#depart_id').val();
	var strgetSelectValue='';
	$("input[name='departcheckboxid']:checked").each(function(j) {
	    if (j >= 0) {
	        strgetSelectValue += $(this).val() + ","
	    }
	});
	if(row==undefined){
		row = $('#depart_select_tree').treegrid('getSelections');
	}
	if (t=='multi') {
		if(strgetSelectValue==""){
			$.messager.alert('系统提示',"请选择一条记录！",'info');
			return;
		}
	} else {
		if(row==""){
			$.messager.alert('系统提示',"请选择一条记录！",'info');
			return;
		}
		if(row[0].haveChild=='1'){
			$.messager.alert('系统提示',"请选择具体部门！",'info');
			return;
		}
	}
	
	supervisionChoose("&&&&"+strgetSelectValue);
	closeWindow();
}


//页面加载完成 控制单选或多选
$(function(){
	var t = $('#selectType').val();
	if(t=='single' || t=='qtbm' || t=='phdw'  || t=='zrbm'  || t=='hjbm'  || t=='wkbm'){
		$('#depart_select_tree').treegrid({
			singleSelect:true
		});
	}else if(t=='multi'){
		$('#depart_select_tree').treegrid({
			singleSelect:false
		});
	}
});

function departcheckboxid(value, row, index) {
	var isDepart=row.departStatus==true?"1":"0";
    if (row.haveChild=='0') {
    	return "<input type='checkbox' name='departcheckboxid' value='"+row.id+":"+row.text+":"+isDepart+"' onclick=show('" + row.id + "') id='check_" + row.id + "'/>";
    } else {
    	return "<input type='checkbox' value='' onclick=show('" + row.id + "') id='check_" + row.id + "'/>";
    }
}
function show(checkid) {
    var s = '#check_' + checkid;
    //alert( $(s).attr("id"));
    // alert($(s)[0].checked);
    /*选子节点*/
    var nodes = $("#depart_select_tree").treegrid("getChildren", checkid);
    for (i = 0; i < nodes.length; i++) {
        $(('#check_' + nodes[i].id))[0].checked = $(s)[0].checked;
    }
    //选上级节点
    if (!$(s)[0].checked) {
        var parent = $("#depart_select_tree").treegrid("getParent", checkid);
	        if(parent!=null){
	        $(('#check_' + parent.id))[0].checked = false;
	        while (parent) {
	            parent = $("#depart_select_tree").treegrid("getParent", parent.id);
	            $(('#check_' + parent.id))[0].checked = false;
	        }
        }
    } else {
        var parent = $("#depart_select_tree").treegrid("getParent", checkid);
        var flag = true;
        var sons = parent.sondata.split(',');
        for (j = 0; j < sons.length; j++) {
            if (!$(('#check_' + sons[j]))[0].checked) {
                flag = false;
                break;
            }
        }
        if (flag)
            $(('#check_' + parent.id))[0].checked = true;
        while (flag) {
            parent = $("#depart_select_tree").treegrid("getParent", parent.id);
            if (parent) {
                sons = parent.sondata.split(',');
                for (j = 0; j < sons.length; j++) {
                    if (!$(('#check_' + sons[j]))[0].checked) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag)
                $(('#check_' + parent.id))[0].checked = true;
        }
    }
}
</script>



<div class="easyui-layout" fit="true">
    	<div region="center" border="false">
<input id="selectType" type="hidden" value="${selectType}">
<input id="num" type="hidden" value="${num}">
<input id="depart_id" type="hidden" value="${depart_id}">


	<div id="depart_tb" style="padding:8px 10px;">
		部门名称: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="departName"/>&nbsp;
		部门代码: <input type="text" size="15" class="easyui-textbox" maxlength="10" id="departCode"/>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 70px;" onclick="queryDepart();">查询</a>&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="selectDepartTree()" style="width: 70px;">确认</a>&nbsp;
	</div>


	<div>
	<table id="depart_select_tree" class="easyui-treegrid"
		data-options="
			url:'${base}/depart/treeSupervisionGrid',
			rownumbers:true, 
			border:false,
			singleSelect:false,
			method:'post',
			idField:'id',
			treeField:'text'
			">
		<thead>
			<tr>
				<th field="id" width="30px" name="rid" formatter="departcheckboxid" ></th>
				<th field="text" width="360px">部门(人员)名称</th>
				<th field="code" width="90px">部门代码</th>
			</tr>
		</thead>
	</table>
	</div>
	
</div>	
</div>
</body>

</html>
