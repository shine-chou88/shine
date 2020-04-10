<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
	<title>${title}</title>
</head>
<body> 

<style type="text/css">
	.main_table{
		border-right: 1px solid #00307b;
		border-bottom: 1px solid #00307b;
	}
	.main_table th{
		border-left: 1px solid #00307b;
		border-top: 1px solid #00307b;
		width: 10em;
		font-size: 14px;
	}
	.main_table select{
		width: 7em;
	}
	.main_table td{
		border-top: 1px solid #00307b;
		font-size: 14px;
	}
	.main_table .input1{
		width: 350px;
	}
	
</style>

<script type="text/javascript">
function saveSmsModel(){
	if(!myValidate()){
		return;
	}
	$('#smsAddForm').form('submit', {
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
					$.messager.alert('系统提示',data.info,'info');
					subClose();
				}else{
					$.messager.alert('系统提示',data.info,'error');
					$('#smsAddForm').form('clear');
				}
			} 
	});
}
function myValidate(){
	var msgTitle = $('[name=msgTitle]').val();
	var msgCircle = $('[name=msgCircle]').val();
	var msgDate = $('[name=msgDate]').val();
	var receiver = $('#div_receiver').html();
	
	if(msgTitle.replace(/(^\s*)|(\s*$)/g,"")==''){
		$.messager.alert('系统提示','请输入短信标题！','info');
		return false;
	}
	if(msgCircle=='-1'){
		$.messager.alert('系统提示','请选择提醒周期！','info');
		return false;
	}
	if(msgDate==''){
		$.messager.alert('系统提示','请选择提醒时间！','info');
		return false;
	}
	if(receiver.replace(/(^\s*)|(\s*$)/g,"")==''){
		$.messager.alert('系统提示','请选择短信接收人！','info');
		return false;
	}
	
	return true;
}
</script>


 <div class="easyui-layout" fit="true" style="padding:2px">
 	<div region="center" border="false">
 	
       <form id="smsAddForm" action="${base}/smsModel/save" method="post" data-options="novalidate:true" class="easyui-form" enctype="multipart/form-data">
          
          <input type="hidden"  value="${sModel.id}" name="id">
       
       	  <table  class="main_table" style="width: 99%; margin:10px; border: 0; ">
       	  	<tr>
            	<td colspan="4" style=" border:0; font-size: 24px; text-align: center; color: black">
            		短信提醒模板
            	</td>
            </tr>
       	  </table>
       	  
          <table width="100%"  border="0" cellpadding="0" cellspacing="0" class="main_table" style="width: 70%; margin: 0 auto;">
          	<tr>
          		<th><span style="color: red">*&nbsp;</span>短信标题：</th>
          		<td><input class="input1" type="text" name="msgTitle" value="${sModel.msgTitle}"></td>
          		<th>&nbsp;</th>
          		<td>
          			<%--<input type="radio" name="msgTimesType" value="0" <c:if test="${sModel.msgTimesType==0}">checked="checked" </c:if>/>仅一次&nbsp;&nbsp;
          			<input type="radio" name="msgTimesType" value="10" <c:if test="${sModel.msgTimesType==10}">checked="checked" </c:if>/>每周期一次
				--%></td>
          	</tr>
          	<tr>
          		<th rowspan="2"><span style="color: red">*&nbsp;</span>短信接收人：</th>
          		<td rowspan="2">
          		
          			<div id="div_receiver" style="float: left; width:300px; height:55px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" >
          				<c:forEach items="${sModel.users}" var="user" varStatus="vs">
		          				<div style='float:left;margin-left:8px;margin-top:8px;' >
		          					<input type='hidden' name='userIds' id='departIds' value='${user.id}'/>
		          					${user.name}
		          					<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick = 'cancelDepart(this)' title='删除' id='${depart.id}'/>
		          				</div>
		          			</c:forEach>
          			</div>
          			
          			<div style="float: left; text-align: center; height:55px; margin: 5px;">
					<input type="button" value="选" class="button_blue" style="margin-top: 35px" onclick="userSearch('multi')"/>
					</div>
					
          		</td>
          		<th><span style="color: red">*&nbsp;</span>提醒时间：</th>
          		<td  id="txsjtd"><input type="text" readonly="readonly" name="msgDate" value="${sModel.msgDate}" class="Wdate" size="18" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
          	</tr>
          	<tr>
          		<th><span style="color: red">*&nbsp;</span>提醒周期：</th>
          		<td width="260px">
          			<select name="msgCircle" style="width: 128px"  onchange="javascript:changeDateSeletor()">
          				<option value="-1">-请选择-</option>
          				<option value="0" <c:if test="${sModel.msgCircle==10}">selected="selected"</c:if>>每月</option>
          				<option value="10" <c:if test="${sModel.msgCircle==20}">selected="selected"</c:if>>每季度</option>
          				<option value="20" <c:if test="${sModel.msgCircle==30}">selected="selected"</c:if>>每年</option>
          				<option value="30" <c:if test="${sModel.msgCircle==0}">selected="selected"</c:if>>仅一次</option>
          			</select>
          		</td>
          	</tr>
          	<tr>
          		<th rowspan="3">短信内容：</th>
          		<td rowspan="3"><textarea name="msgContent" rows="3" cols="40">${sModel.msgContent}</textarea></td>
          		<th>填报人：</th>
          		<td>${currentUser.name}</td>
          	</tr>
          	<tr>
          		<th>填报单位：</th>
          		<td>${currentUser.depart.name}</td>
          	</tr>
          	<tr>
          		<th>填报时间：</th>
          		<td>${currentDate}</td>
          	</tr>
          	<%--<tr>
          		<th>附件：</th>
          		<td colspan="3">
          			<table width="100%" border="0px">
          				<c:forEach items="${listAtt}" var="att">
									<c:if test="${fn:contains(att.originalName,'jpg')||fn:contains(att.originalName,'png')||fn:contains(att.originalName,'jpeg')}">
										<tr id="${att.id}">
											<td style="border: 0">
												<a href="${base}/upload/xstb${fn:replace(att.fileUrl,'\\','/')}" target="_blank">
												<img id="p" src="${base}/upload/xstb${fn:replace(att.fileUrl,'\\','/')}" style="width: 180px; height: 120px"/>
												</a>
												<a href="javascript:delAtt('${att.id}')" style="text-decoration: underline;color: blue;">删除</a>
											</td>
										</tr>
									</c:if>
									<c:if test="${!fn:contains(att.originalName,'jpg')&&!fn:contains(att.originalName,'png')&&!fn:contains(att.originalName,'jpeg')}">
										<tr id="${att.id}">
											<td style="border: 0">
												<a href="${base}/attachment/downloadXstb/${att.id}" style="text-decoration: underline;color: blue;">${att.originalName}</a>&nbsp;&nbsp;&nbsp;
												<a href="javascript:delAtt('${att.id}')" style="text-decoration: underline;color: blue;">删除</a>
											</td>
										</tr>
									</c:if>
          				</c:forEach>
					</table>
					<table width="100%" id="copypic" border="0px">
						<tr>
							<td style="border: 0">
								<input type="file" name="xstbFiles"  style='width:50%'/>
								<input type="button" class="button-1" value="添加附件" onclick="addcopy('copypic');" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
          		<th>模板下载：</th>
          		<td colspan="3">
					<table width="100%" id="modelDownload" border="0px">
						<tr>
							<td style="border: 0">
								<a href="${base}/resource/download/稳定形势通报_模板.docx" style="text-decoration: underline;color: blue;">稳定形势通报-模板.docx</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
          	<tr>
          	</tr>
   		--%>
   		
   		</table>
   		
   		
   		<div region="south" border="false" class="south">
   			<input type="button"  onclick="saveSmsModel();" id="btn" class="button-blue" value="保存">
   			<input  type="button"  onclick="javascript:window.close();" class="button-blue" value="关闭">
		</div>
	 </form>	
	</div>
 </div>    	
 
<script type="text/javascript">


var names = new Array();
function userSearch(selectType){
	var win=creatWin('添加-短信接收人',630,450,'icon-add',"/user/refList/"+selectType);
    win.window('open');
}
function userSearch1(ret){
	if (ret == "clear") {
       $("#div_receiver").html("");
	}
    if (ret!="&&&&" && ret != undefined && ret != null && ret.indexOf("&&") != -1 && ret != "cancel") {
    	var retArray = ret.split("&&");
        var str= new Array(); 
       	str=retArray[2].split(","); 
       	for(var i=0;i<str.length-1 ;i++){
       		if(null!=str[i]){
       			var str1= new Array();
       			str1=str[i].split(":");
       			var div1 = $("#div_receiver");
       			if(!isExist(str1[0]) && !isExist(str1[1])){
					names.push(str1[0]);	
					div1.append("<div  style='float:left;margin-left:8px;margin-top:8px;' > "+
								"<input type='hidden' name='userIds' id='userIds' value='"+str1[0]+"'/>"+
								""+str1[1]+""+
								"<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick = 'cancelDepart(this)' title='删除' id='"+str1[0]+"'/>"+
								"</div>")
				}
       		}
       	}
    }
}

function isExist(name){
	for(i=0; i<names.length; i++){
		if(names[i] == name){
			return true;
		}
	}
	return false;
}

function cancelDepart(obj){
	var id=obj.id;
	if(names.length>0)
	{
		for(i=0; i<names.length; i++){
			if(names[i] == id){
				names.splice(i,1);
			}
		}
	}
	$(obj).parent().remove();
}

//动态增加的文件个数
var copynum = 1;
//动态增加添加附件的行
function addcopy(id){
    var row,cell,str;
    row = eval("document.all."+id).insertRow();
	    if(row != null ){
			cell = row.insertCell();
			cell.style="border:0";
			str="<input type=\"file\"  id=\"f"+copynum+"\" name=\"xstbFiles\" style='width:50%'><input class=\""+"button-1"+"\" type=\""+"button"+"\" value=\""+"删除附件"+"\" onclick=\"deletecopy(this,'copypic');\">"
			cell.innerHTML=str;
		}
	copynum++;
}
//删除附件的行
function deletecopy(obj,id){
	var rowNum,curRow;
	curRow = obj.parentNode.parentNode;
	rowNum = eval("document.all."+id).rows.length - 1;
	eval("document.all["+'"'+id+'"'+"]").deleteRow(curRow.rowIndex);
	copynum--;
}
//删除附件
function delAtt(id){
	if(confirm("确认删除吗？")){
		$.ajax({ 
			type: 'POST', 
			url: '${base}/attachment/delete/'+id,
			dataType: 'json',  
			success: function(data){ 
				if(data.success){
					$.messager.alert('系统提示',data.info,'info');
					document.getElementById(id).style.display="none";
				}else{
					$.messager.alert('系统提示',data.info,'error');
				}
			} 
		}); 
	}
}
//根据提醒周期改变提醒时间选择器格式
function changeDateSeletor(){
	var cycle=$('[name="msgCircle"] option:selected').val();
	//alert('cycle='+cycle);
	if(cycle=='0'){
		//alert(0)
		$('[name="msgDate"]').attr('onclick',"WdatePicker({dateFmt:'yyyy-MM-dd'})");
		$('[name="msgDate"]').attr('class',"Wdate1");
		$('[name="msgDate"]').attr('class',"Wdate");
		var selector="<input type=\"text\" readonly=\"readonly\" name=\"msgDate\" value=\"${currentDate}\" class=\"Wdate\" size=\"18\" onclick=\"WdatePicker({dateFmt:\'yyyy-MM-dd\'})\"/>";
		$('#txsjtd').html(selector);
	}
	if(cycle=="10"){
		//alert(10)
		var selector="<input type=\"text\" readonly=\"readonly\" name=\"msgDate\" class=\"Wdate\" size=\"18\" onclick=\"WdatePicker({dateFmt:\'dd\'})\"/>";
		$('#txsjtd').html(selector);
	}
	if(cycle=="20" || cycle=="30"){
		//alert(20)
		var selector="<input type=\"text\" readonly=\"readonly\" name=\"msgDate\" class=\"Wdate\" size=\"18\" onclick=\"WdatePicker({dateFmt:\'MM-dd\'})\"/>";
		$('#txsjtd').html(selector);
	}
</script>
</body>
</html>