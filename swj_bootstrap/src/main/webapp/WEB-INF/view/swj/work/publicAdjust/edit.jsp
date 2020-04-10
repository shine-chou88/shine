<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
	<title>${title}</title>
</head>
<body> 
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<form id="publicAdjustEditForm" action="${base}/publicAdjust/save" method="post" data-options="novalidate:true" class="easyui-form">
       		<input type="hidden" name="id" value="${bean.id}"/>
       		<input type="hidden" name="status" value="${empty bean.status?'已申请':bean.status}"/>
        <table cellpadding="5" cellspacing="0" class="a_table" width="100%">
        	        	<tr>
          		<th width="30%" class="br">
          			<strong style="color: red;">*</strong>单位：
          		</th>
          		<td width="70%" class="br">
          			<input type="text" id="adjustDepartId" name="depart.id" data-options="url:'${base}/depart/certificateDepartJson?selected=${bean.depart.code}',valueField:'id',textField:'text'" class="easyui-combobox"  style="width:250px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th width="30%" class="br">
          			<strong style="color: red;">*</strong>填写人姓名：
          		</th>
          		<td width="70%" class="br">
          			<input type="text" data-options="required:true" name="writeUserName"  class="easyui-textbox" value="${ bean.writeUserName}" style="width:200px"></input>
          		</td>
        	</tr>
        	<tr>
				            <th class="br"><strong style="color: red;">*</strong>证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div id="certificate_adjust_info" style="float: left; width:200px; height:30px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" >
			          			        <c:forEach items="${bean.certificateList}" var="certificate" varStatus="vs">
					          				<div style='float:left;margin-left:8px;margin-top:8px;' >
					          					<input type='hidden' name='certificateIds'  value='${certificate.id}'></input>
					          					${certificate.zjhm}-${certificate.name}
					          					<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick = 'cancelCertificateAdjust(this)' title='删除' id='${certificate.id}'></img>
					          				</div>
					          			</c:forEach>
			          			</div>          			
			          			<div style="float: left; text-align: center; height:30px; margin: 5px;">
								   <input type="button" value="选" class="button_blue" style="margin-top: 5px" onclick="selectCertificateAdjust()"></input>
								</div>
			          		</td>     	        		
				         </tr>
				<tr>
				<th rowspan="5" width="30%" class="br">
          			调整信息类型：
          		</th>
				
				</tr>	
        	        	<tr>
          		<td width="70%" class="br">
          			<strong style="color: red;">*</strong>(系统内)调动后单位名称：
          			<input type="text" id="afterDepartInnerId" name="afterDepartInner.id" data-options="url:'${base}/depart/certificateDepartJson?selected=${bean.depart.code}',valueField:'id',textField:'text'" class="easyui-combobox"  style="width:250px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<td width="70%" class="br">
          			(系统外)调动后单位名称：<input type="text" id="afterDepartOuterId" name="afterDepartOuter"  class="easyui-textbox" value="${ bean.afterDepartOuter}" style="width:200px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<td width="70%" class="br">
          			<input type="radio" name="hasRetired" value="是" <c:if test="${bean.hasRetired=='是'}">checked="checked"</c:if>/>已退休&nbsp;&nbsp;
          			<input type="radio" name="hasRetired" value="否" <c:if test="${empty bean.hasRetired || bean.hasRetired=='否'}">checked="checked"</c:if>/>未退休
          		</td>
        	</tr>
        	        	
        	        	<tr>
          		<td width="70%" class="br">
          			其他：<input type="text" name="other"  class="easyui-textbox" value="${ bean.other}" style="width:200px"></input>
          		</td>
        	</tr>
        	            <tr>
        	<tr>
          		<th width="30%" class="br">
          			填写人电话：
          		</th>
          		<td width="70%" class="br">
          			<input type="text" name="writeUserPhone"  class="easyui-textbox" value="${ bean.writeUserPhone}" style="width:200px"></input>
          		</td>
        	</tr>
        	        	<tr>
          		<th width="30%" class="br">
          			填写日期：
          		</th>
          		<td width="70%" class="br">
          			<input type="text" name="writeDate"  class="easyui-datebox" value="${empty bean.writeDate?nowDate:bean.writeDate}" style="width:200px"></input>
          		</td>
        	</tr>
            <td class="br" colspan="2">
           		<div region="south" border="false" style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-ok" onclick="savePublicAdjustForm()">保存</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
				</div>
            </td>
            </tr>
          	</table>
		</form>	
	</div>
</div>    	
<script type="text/javascript">
function savePublicAdjustForm(){
	var adjustDepartId=$("#adjustDepartId").combobox('getValue');
	if(adjustDepartId==null||adjustDepartId==''){
		$.messager.alert('系统提示', "单位不能为空", 'info');
		return false;
	}
	var certificateIds=document.getElementsByName("certificateIds");
	if(certificateIds.length==0){
		$.messager.alert('系统提示', "证照编号及持有人姓名不能为空", 'info');
		return false;
	}
	var afterDepartInnerId=$("#afterDepartInnerId").combobox('getValue');
	if(afterDepartInnerId==null||afterDepartInnerId==''){
		var afterDepartOuterId=$("#afterDepartOuterId").val();
		if(afterDepartOuterId==null||afterDepartOuterId==''){
			$.messager.alert('系统提示', "(系统外)调动后单位不能为空", 'info');
			return false;
		}
	}
	var flag=false;
	$('#publicAdjustEditForm').form('submit', {
		onSubmit: function(){ 
			flag=$(this).form('enableValidation').form('validate');
			if(flag){
				$.messager.progress();
			}
			return flag;
		},
		dataType:'json',
		success:function(data){
			if(flag){
				$.messager.progress('close');
			}
			var data = eval('(' + data + ')');
			if(data.success){
				$.messager.alert('系统提示', data.info, 'info');
				closeWindow();
				$('#publicAdjustEditForm').form('clear');
				$('#publicAdjustListDg').datagrid('reload');
			}else{
				$.messager.alert('系统提示', data.info, 'error');
			}
		} 
	});
}

var adjustNames = new Array();
function selectCertificateAdjust(){
	var win=creatFreeWindow("third_window",'添加-证照信息',830,450,'icon-add',"/certificateInfo/refAdjustList?selectType=single");
    win.window('open');
}

function isAdjustExist(id) {
	for (i = 0; i < adjustNames.length; i++) {
		if (adjustNames[i] == id) {
			return true;
		}
	}
	return false;
}
function cancelCertificateAdjust(obj) {
	var id = obj.id;
	if (adjustNames.length > 0) {
		for (i = 0; i < adjustNames.length; i++) {
			if (adjustNames[i] == id) {
				adjustNames.splice(i, 1);
			}
		}
	}
	$(obj).parent().remove();
}
</script>
</body>
</html>