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
	var ids = "";
	var names = "";
	var nameid = "";
	var id = "";
	var name = "";
	if(row.length>0){//复选框多选
		for(var i=0;i<row.length;i++){
			id = row[i].id;
			name = row[i].text;
			nameid+=id+":"+name+",";
			ids+=ids==""?id:","+id;
			names+=names==""?name:","+name;
		}
	}else{//双击选择单个
		//if(row.haveChild=='1'){
		//	$.messager.alert('系统提示',"请选择所属单位！",'info');
		//	return;
		//}
		id = row.id;
		name = row.name;
		nameid+=id+":"+name+",";
		ids+=ids==""?id:","+id;
		names+=names==""?name:","+name;
	}
	var rtnValue = ids + '&&' + names + '&&' +nameid;
	
	if(window.navigator.userAgent.indexOf("Chrome")>-1){
		window.opener.returnValue = rtnValue;
	}else{
		window.returnValue = rtnValue;
	}
	
	
	if(t=='single'){
		tbdwSearch1(rtnValue);
	}else if(t=='multi'){
		departSearch1('&&&&'+strgetSelectValue);
	}else if(t=='hjbm'){
		disposeUnitName(rtnValue);
	}else if(t=='qtbm'){//选择牵头部门
		row = $('#depart_select_tree').treegrid('getSelections');
		var id=row[0].id;
		var name=row[0].text;
		var zrld=row[0].grww_zrld;
		var zrlddh=row[0].grww_zrlddh;
		var lxr=row[0].grww_lxr;
		var lxrdh=row[0].grww_lxrdh;
		var rtnValue=id+"&&"+name+"&&"+zrld+"&&"+zrlddh+"&&"+lxr+"&&"+lxrdh;
		var reg1=new RegExp('null','g');//替换null字符串
		var reg2=new RegExp('undefined','g');
		rtnValue=rtnValue.replace(reg1,'').replace(reg2,'');
		complete_qtbm(rtnValue);
	}else if(t=='zrbm'){
		event(rtnValue,depart_id,num);
	}else if(t=='phdw'){//选择配合单位
		row = $('#depart_select_tree').treegrid('getSelections');
		var id=row[0].id;
		var name=row[0].text;
		var zrld=row[0].grww_zrld;
		var zrlddh=row[0].grww_zrlddh;
		var lxr=row[0].grww_lxr;
		var lxrdh=row[0].grww_lxrdh;
		var rtnValue=id+"&&"+name+"&&"+zrld+"&&"+zrlddh+"&&"+lxr+"&&"+lxrdh;
		var reg1=new RegExp('null','g');//替换null字符串
		var reg2=new RegExp('undefined','g');
		rtnValue=rtnValue.replace(reg1,'').replace(reg2,'');
		complete_phdw(rtnValue);
	}else if(t=='wkbm'){//选择牵头部门
		$('#jcxx_add_wkUnitId').val(ids);
		$('#jcxx_add_wkUnitName').val(names);
		$('#jcxx_edit_wkUnitId').val(ids);
		$('#jcxx_edit_wkUnitName').val(names);
	}
	
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
    if (row.haveChild=='0') {
    	return "<input type='checkbox' name='departcheckboxid' value='"+row.id+":"+row.text+"' onclick=show('" + row.id + "') id='check_" + row.id + "'/>";
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
        $(('#check_' + parent.id))[0].checked = false;
        while (parent) {
            parent = $("#depart_select_tree").treegrid("getParent", parent.id);
            $(('#check_' + parent.id))[0].checked = false;
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
			url:'${base}/depart/treeGrid',
			rownumbers:true, 
			border:false,
			singleSelect:false,
			method:'post',
			idField:'id',
			treeField:'text'
			">
		<thead>
			<tr>
				<c:if test="${selectType=='multi' }"><th field="id" width="30px" name="rid" formatter="departcheckboxid" ></th></c:if>
				<c:if test="${selectType!='multi' }"><th field="id" width="15px" checkbox="true" name="rid">部门id</th></c:if>
				<th field="text" width="360px">部门名称</th>
				<th field="code" width="90px">部门代码</th>
			</tr>
		</thead>
	</table>
	</div>
	
</div>	
</div>
</body>

</html>
