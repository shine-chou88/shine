<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
	<script type="text/javascript">
		function userAddEditForm(){
			//将功能树选中的值设置到隐藏文本上
			var nodes = $('#functionUserTree').tree('getChecked');
			var ufIds = '';
			for(var i=0; i<nodes.length;i++){
				if (ufIds!='') ufIds+=',';
				ufIds+= nodes[i].id;
			}
			$('#userFuncIds').val(ufIds);
			var beanUserId='${bean.id}';
			if(beanUserId.length==0){
				var userPassword=$("#userEditPassword").val();
				if(!checkPwd(userPassword)){
					$.messager.alert('系统提示','密码至少包含一个大写字母、一个小写字母，一个数字的8-20个字符（能包含!@#$_特殊字符）！','info');
					return;
				}
				
			}
			if($('#departIds').val() == undefined || $('#departIds').val() == ''){
				$.messager.alert('系统提示','请选择部门!','info');
				return;
			}
			if($("#user_sfzhm").val()!=null&&$("#user_sfzhm").val()!=''){
				if(!validateIdCard($("#user_sfzhm").val())){
					$.messager.alert('系统提示', "身份证格式不正确，请重新输入！", 'info');
					return false;
				 }
			}
			if(!checkMobileNo()){
				return;
			}
			var encrypt = new JSEncrypt();
		    encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4z2h4JuaVJKljC6tdB09ucZzUjbyGCjJcHYi41lypUHWGZ5M4IoPmOWSbVO9XZRBQzp3xBwrbF7lJxK4EGWl9i+BjuRdCUf109jMEo6kMVyjJTtaWAfqohcYBOIXZ3Eqgyyt4sZCIvH4JAB/0mmBPQ1wS6gwW8IPSJEo2Wx3CfQIDAQAB");
		    $("#userEditRsaAccountNo").val(encrypt.encrypt($("#userEditAccountNo").val()));
		    if(beanUserId.length==0){
		    	$("#userEditRsaPwd").val(encrypt.encrypt($("#userEditPassword").val()));
			}
			var flag=false;
			$('#userAddEditForm').form('submit', {
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
						closeWindow();
						$('#userAddEditForm').form('clear');
						$("#user_dg").datagrid('reload');
					}else{
						$.messager.alert('系统提示',data.info,'error');
					}
				} 
			});
		}
		$('#functionUserTree').tree({
			onCheck: function(node,checked){
				var childRen = $('#functionUserTree').tree("getChildren",node.target);
				if(childRen.length>0){
					if(checked){
						for(var i=0;i<childRen.length;i++){
							$('#functionUserTree').tree('check', childRen[i].target);
						}						
					}else{
						for(var i=0;i<childRen.length;i++){
							$('#functionUserTree').tree('uncheck', childRen[i].target);
						}
					}
				}
			}
		});
		function checkPwd(s){
			var regu =/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d!@#$_]{8,20}$/;
			var re = new RegExp(regu);
			if (re.test(s)) {
			        return true;
			    }else{
			       return false;
			    }
		}
	</script>
    <div class="easyui-layout" fit="true">
    	<div region="center" border="false">
	    	<form id="userAddEditForm" action="${base}/user/save" method="post" data-options="novalidate:true" class="easyui-form">
	    		<input type="hidden" name="id" id="user_edit_id" value="${bean.id}"/>
	    		<input type="hidden" name="funcIds" id="userFuncIds"/>
	    		<input type="hidden" name="accountNo" id="userEditRsaAccountNo"/>
	    		<c:if test="${fn:length(bean.id)>0}">
	    			<input type="hidden" name="islocked" value="${bean.islocked}"/>
	    		</c:if>
				<table border="0" cellpadding="0" cellspacing="0" class="main_table">
					<c:if test="${fn:length(bean.id)>0}">
						<tr>
							<th width="15%"><span style="color: red;">*</span>账号：</th>
							<td width="85%" colspan="3">
								<input class="easyui-textbox" type="text" id="userEditAccountNo" data-options="required:true,validType:'length[1,20]'" value="${bean.accountNo}"/>
								<input type="hidden" name="password" value="${bean.password}"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${fn:length(bean.id)==0}">
						<tr>
							<th width="15%"><span style="color: red;">*</span>账号：</th>
							<td width="35%">
								<input class="easyui-textbox" type="text" id="userEditAccountNo" data-options="required:true,validType:'length[1,20]'" value="${bean.accountNo}"/>
							</td>
							<th width="15%"><span style="color: red;">*</span>密码：</th>
							<td width="35%">
								<input type="hidden" name="password" id="userEditRsaPwd" />
								<input class="easyui-textbox" type="password" id="userEditPassword" data-options="required:true,validType:'length[8,20]'" autocomplete="off"/>
								<span style="color: red;"><br/>至少包含一个大写字母、一个小写字母，一个数字的8-20个字符<br/>（能包含!@#$_特殊字符）</span>
							</td>
						</tr>
					</c:if>
					<tr>
						<th ><span style="color: red;">*</span>姓名：</th>
						<td>
							<input class="easyui-textbox" type="text" name="name" data-options="required:true,validType:'length[1,50]'" value="${bean.name}"/>
						</td>
						<th>密钥唯一标示：</th>
						<td>
							<input class="easyui-textbox" type="text" name="certUniqueId" data-options="validType:'length[1,50]'" style="width: 200px;" value="${bean.certUniqueId}"/>
						</td>
					</tr>
					<tr>
						<th><span style="color: red;">*</span>部门：</th>
						<td>
						
						  <c:if test="${fn:length(bean.id)==0}">
                          	<div id="user_depart" style="float: left; width:180px; height:25px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" ></div>
          			        <input type="button" value="选" class="button_blue" style="margin-top: 5px" onclick="departSelect('yhbm')"/>
						  </c:if>
						  
						 <c:if test="${fn:length(bean.id)>0}">
						    <div id="user_depart" style="float: left; width:180px; height:25px; overflow-x:hidden; border: 1px solid #D4D4D4; margin: 5px" >
                                       
		          				<div style='float:left;margin-left:8px;margin-top:8px;' >
		          					<input type='hidden' name='depart.id' id='departIds' value='${bean.depart.id}'/>
		          					<c:if test="${fn:length(bean.depart.name)>8}">
		          					<span title="${bean.depart.name}">${fn:substring(bean.depart.name,0,8)}...</span>
		          					</c:if>
		          					<c:if test="${fn:length(bean.depart.name)<=8}">
		          					<span title="${bean.depart.name}">${bean.depart.name}</span>
		          					</c:if>
		          					<c:if test="${bean.depart.name!=null}">
		          					<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick = 'cancelUserDepart(this)' title='删除' id='${bean.depart.id}'/>
		          				    </c:if>
		          				</div>
	         			    </div>
						    <input type="button" value="选" class="button_blue" style="margin-top: 5px" onclick="departSelect('yhbm')"/>
						
						</c:if>
						
						</td>
						<th>审批人：</th>
						<td>
							<input class="easyui-combobox" name="departApprover" data-options="url:'${base}/user/listAll?selected=${bean.departApprover}',method:'get',valueField:'id',textField:'text',panelHeight:'400',editable:false"/>
						</td>
					</tr>
					<tr>
						<th>身份证号码：</th>
						<td>
							<input class="easyui-textbox" type="text" name="idCard" id="user_sfzhm" data-options="validType:'length[1,20]'" style="width: 200px;" value="${bean.idCard}"/>
						</td>
						<th >职务：</th>
						<td >
							<input class="easyui-textbox" type="text" name="post" data-options="validType:'length[1,50]'" value="${bean.post}"/>
						</td>
					</tr>
					<tr>
						<th>手机号码：</th>
						<td>
							<input id="user_edit_mobileNo" class="easyui-textbox" type="text" name="mobileNo" data-options="validType:'length[1,20]'" value="${bean.mobileNo}"/>
						</td>
						<th >电话号码：</th>
						<td >
							<input class="easyui-textbox" type="text" name="telNo" data-options="validType:'length[1,20]'" value="${bean.telNo}"/>
						</td>
					</tr>
					<tr>
						<th >传真号码：</th>
						<td >
							<input class="easyui-textbox" type="text" name="faxNo" data-options="validType:'length[1,50]'"value="${bean.faxNo}"/>
						</td>
						<th >邮编：</th>
						<td >
							<input class="easyui-textbox" type="text" name="postalCode" data-options="validType:'length[1,50]'" value="${bean.postalCode}"/>
						</td>
					</tr>
					<tr>
						<th >邮箱：</th>
						<td >
							<input class="easyui-textbox" type="text" name="email" data-options="validType:'length[1,50]'" value="${bean.email}"/>
						</td>
						<th>地址：</th>
						<td>
							<input class="easyui-textbox" type="text" name="address" data-options="validType:'length[1,50]'" value="${bean.address}"/>
						</td>
					</tr>
					<tr>
						<th>角色：</th>
						<td colspan="3">
							<c:forEach items="${listRole}" var="role" varStatus="s">
								<c:set var="checked" value="false"/>
								<c:forEach items="${bean.roles}" var="r">
									<c:if test="${r.id==role.id}">
										<c:set var="checked" value="true"/>		
									</c:if>
								</c:forEach>
								<span style="display:inline-block;"><input type="checkbox" id="${role.code}" name="roleIds" value="${role.id}" <c:if test="${checked=='true'}">checked="checked"</c:if>/>&nbsp;${role.name}&nbsp;</span>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>拥有的功能：</th>
						<td colspan="3">
							<ul id="functionUserTree" class="easyui-tree" data-options="url:'${base}/user/functionTree?userId=${bean.id}',animate:true,lines:true,checkbox:true,cascadeCheck:false"></ul>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="south" border="false" class="south">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="userAddEditForm();">保存</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
	    var names = new Array();
		function departSelect(selectType){
			var win=creatSearchDataWin('选择-部门',590,400,'icon-add',"/depart/refUserDepart/"+selectType);
		    win.window('open');
		}
		function departReturnSelect(ret){
			if (ret == "clear") {
		       $("#user_depart").html("");
			}

		    if (ret!="&&&&" && ret != undefined && ret != null && ret.indexOf("&&") != -1 && ret != "cancel") {
		    	var retArray = ret.split("&&");
		        var str= new Array(); 
		       	str=retArray[2].split(","); 
		       	for(var i=0;i<str.length-1 ;i++){
		       		if(null!=str[i]){
		       			var str1= new Array();
		       			str1=str[i].split(":");
		       			var div1 = $("#user_depart");
		       			if(!isUserTreeExist(str1[0]) && !isUserTreeExist(str1[1])){
							names.push(str1[0]);	
							div1.html("<div  style='float:left;margin-left:8px;margin-top:8px;' > "+
										"<input type='hidden' name='depart.id' id='departIds' value='"+str1[0]+"'/>"+
										"<span title='"+str1[1]+"'>"+((str1[1].length>8)?str1[1].substring(0,8)+"...":str1[1])+"</span>"+
										"<img src='${base}/resource/images/cancelDepart.jpg' style='cursor:pointer;margin-left:5px;height:15px;width:15px;' onclick = 'cancelUserDepart(this)' title='删除' id='"+str1[0]+"'/>"+
										"</div>")
						}
		       		}
		       	}
		    }
		}
		function isUserTreeExist(name){
			for(var i=0; i<names.length; i++){
				if(names[i] == name){
					return true;
				}
			}
			return false;
		}
		
		function cancelUserDepart(obj){
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
		function checkMobileNo(){
			var mobile=$("#user_edit_mobileNo").textbox("getValue");
			var id=$("#user_edit_id").val();
			var tag="0";
			if(mobile!=''){
				 $.ajax({
		  			   type : "post",
		  			   url : "${base}/user/mobileCheck",
		  			   data:{"mobileNo":mobile,"id":id},
		  			   dataType: 'json',  
					   async: false,
		  			   success : function(data){
		  				   if(data.success){ 	
		  			   	     $.messager.alert('系统提示', "手机号在系统中已存在，请更换手机号！", 'info');
		  			   	     tag="1";
		  			   	   }else{
		  			   		 tag="0"; 
		  			   	   }  
		  			   }
		  		   })
			}
			 if(tag=="0"){
		    	return true; 
		     }else if(tag=="1"){
		    	return false; 
		     }
		}
	</script>
</body>
</html>

