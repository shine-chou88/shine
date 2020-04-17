<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div class="modal-header bg-primary">
   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
   <h4 class="modal-title"><span class='glyphicon glyphicon-plus'></span>&nbsp;添加-用户</h4>
</div>
<form class="form-horizontal" role="form" id="userEditForm">
	<div class="modal-body" style="width:900px;overflow-x: scroll;">
  		<input type="hidden" name="id" id="user_edit_id" value="${bean.id}"/>
	    <input type="hidden" name="funcIds" id="userFuncIds"/>
	    <input type="hidden" name="accountNo" id="userEditRsaAccountNo"/>
	    <c:if test="${fn:length(bean.id)>0}">
   			<input type="hidden" name="islocked" value="${bean.islocked}"/>
   		</c:if>
   		<c:if test="${fn:length(bean.id)>0}">
			<div class="form-group">
				<label class="control-label col-sm-2"><span style="color: red;">*</span>账号：</label>
		  		<div class="col-sm-10">
					<input type="text" class="form-control" name="accountNoNotRsa" id="userEditAccountNo" data-options="required:true,validType:'length[5,20]'" value="${bean.accountNo}"/>
					<input type="hidden" name="password" value="${bean.password}"/>
				</div>
		  	</div>
		</c:if>
   		<c:if test="${fn:length(bean.id)==0}">
   			<fieldset>
				<div class="form-group col-sm-6">
			  		<label class="control-label col-sm-4"><span style="color: red;">*</span>账号：</label>
			  		<div class="col-sm-8">
						<input type="text" class="form-control" name="accountNoNotRsa" id="userEditAccountNo" data-options="required:true,validType:'length[5,20]'" value="${bean.accountNo}"/>
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label class="control-label col-sm-4"><span style="color: red;">*</span>密码：</label>
			  		<div class="col-sm-8">
			  			<input type="hidden" name="password" id="userEditRsaPwd" />
						<input class="form-control" type="password" name="passwordNotRsa" id="userEditPassword" data-options="required:true,validType:'length[8,20]'" autocomplete="off" title="至少包含一个大写字母、一个小写字母，一个数字的8-20个字符（能包含!@#$_特殊字符）"/>
					</div>
			  	</div>
		  	</fieldset>
		</c:if>
		<fieldset>
			<div class="form-group col-sm-6">
		  		<label class="control-label col-sm-4"><span style="color: red;">*</span>姓名：</label>
		  		<div class="col-sm-8">
					<input type="text" class="form-control" name="name"  data-options="required:true,validType:'length[2,20]'" value="${bean.name}"/>
				</div>
			</div>
			<div class="form-group col-sm-6">
				<label class="control-label col-sm-4">密钥唯一标示：</label>
		  		<div class="col-sm-8">
					<input type="text" class="form-control" name="certUniqueId" data-options="required:true,validType:'length[8,50]'" value="${bean.certUniqueId}"/>
				</div>
		  	</div>
	  	</fieldset>
		<div class="form-group form-inline">
	  		<label class="control-label col-sm-2">身份证号码：</label>
	  		<div class="col-sm-4">
				<input type="text" class="form-control" name="idCard"  data-options="required:true,validType:'length[1,20]'" value="${bean.idCard}"/>
			</div>
			<label class="control-label col-sm-2">职务：</label>
	  		<div class="col-sm-4">
				<input type="text" class="form-control" name="post" data-options="required:true,validType:'length[1,50]'" value="${bean.post}"/>
			</div>
	  	</div>
	  	<div class="form-group form-inline">
	  		<label class="control-label col-sm-2">手机号码：</label>
	  		<div class="col-sm-4">
				<input type="text" class="form-control" name="mobileNo"  data-options="required:true,validType:'length[1,20]'" value="${bean.mobileNo}"/>
			</div>
			<label class="control-label col-sm-2">电话号码：</label>
	  		<div class="col-sm-4">
				<input type="text" class="form-control" name="telNo" data-options="required:true,validType:'length[1,50]'" value="${bean.telNo}"/>
			</div>
	  	</div>
	  	<div class="form-group form-inline">
	  		<label class="control-label col-sm-2">传真号码：</label>
	  		<div class="col-sm-4">
				<input type="text" class="form-control" name="faxNo"  data-options="required:true,validType:'length[1,50]'" value="${bean.faxNo}"/>
			</div>
			<label class="control-label col-sm-2">邮编：</label>
	  		<div class="col-sm-4">
				<input type="text" class="form-control" name="postalCode" data-options="required:true,validType:'length[1,50]'" value="${bean.postalCode}"/>
			</div>
	  	</div>
	  	<div class="form-group form-inline">
	  		<label class="control-label col-sm-2">邮箱：</label>
	  		<div class="col-sm-4">
				<input type="text" class="form-control" name="email"  data-options="required:true,validType:'length[1,50]'" value="${bean.email}"/>
			</div>
			<label class="control-label col-sm-2">地址：</label>
	  		<div class="col-sm-4">
				<input type="text" class="form-control" name="address" data-options="required:true,validType:'length[1,50]'" value="${bean.address}"/>
			</div>
	  	</div>
	  	<div class="form-group form-inline">
			<label class="control-label col-sm-2">角色：</label>
	  		<div class="col-sm-10">
				<c:forEach items="${listRole}" var="role" varStatus="s">
					<c:set var="checked" value="false"/>
					<c:forEach items="${bean.roles}" var="r">
						<c:if test="${r.id==role.id}">
							<c:set var="checked" value="true"/>		
						</c:if>
					</c:forEach>
					<span style="display:inline-block;"><input type="checkbox" id="${role.code}" name="roleIds" value="${role.id}" <c:if test="${checked=='true'}">checked="checked"</c:if>/>&nbsp;${role.name}&nbsp;</span>
				</c:forEach>
			</div>
	  	</div>
	  	<div class="form-group form-inline">
			<label class="control-label col-sm-2">拥有的功能：</label>
	  		<div class="col-sm-10">
				<ul id="functionUserTree" class="easyui-tree" data-options="url:'${base}/user/functionTree?userId=${bean.id}',animate:true,lines:true,checkbox:true,cascadeCheck:false"></ul>
			</div>
	  	</div>
	</div>
	<div class="modal-footer center-block">
	  <center>
	  	<button type="submit" class="btn btn-primary"><span class='glyphicon glyphicon-ok'></span>&nbsp;保存</button>
		<button type="button" class="btn btn-default" data-dismiss="modal"><span class='glyphicon glyphicon-remove'></span>&nbsp;关闭</button>
	  </center>
	</div>
</form>
<script type="text/javascript">
$(function(){
	$('#userEditForm')
		.bootstrapValidator({
		    message: 'This value is not valid',
		    feedbackIcons: {
		        valid: 'glyphicon glyphicon-ok',
		        invalid: 'glyphicon glyphicon-remove',
		        validating: 'glyphicon glyphicon-refresh'
		    },
		    fields: {
		        accountNoNotRsa: {
		            message: 'The username is not valid',
		            validators: {
		                notEmpty: {
		                    message: '用户名不能为空!'
		                },
		                stringLength: {
		                    min: 5,
		                    max: 20,
		                    message: '用户名必须输入5到20个字符！'
		                }
		                /*remote: {
		                    url: 'remote.php',
		                    message: 'The username is not available'
		                },*/
		            }
		        },
		        passwordNotRsa: {
		            message: 'The username is not valid',
		            validators: {
		                notEmpty: {
		                    message: '密码不能为空'
		                },
		                stringLength: {
		                    min: 8,
		                    max: 20,
		                    message: '密码必须输入8到20个字符！'
		                },
		                regexp: {
		                    regexp: /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d!@#$_]{8,20}$/,
		                    message: '密码至少包含一个大写字母、一个小写字母，一个数字的8-20个字符（能包含!@#$_特殊字符）！'
		                }
		            }
		        },
		        name: {
		            message: 'The username is not valid',
		            validators: {
		                notEmpty: {
		                    message: '姓名不能为空！'
		                },
		                stringLength: {
		                    min: 2,
		                    max: 20,
		                    message: '姓名必须输入5到20个字符！'
		                }
		            }
		        }
		    }
		})
		.on('success.form.bv', function(e) {
		    // Prevent form submission
		    e.preventDefault();
		    // Get the form instance
		    var $form = $(e.target);
		    // Get the BootstrapValidator instance
		    var bv = $form.data('bootstrapValidator');
		    var beanUserId='${bean.id}';
		    var encrypt = new JSEncrypt();
		    encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4z2h4JuaVJKljC6tdB09ucZzUjbyGCjJcHYi41lypUHWGZ5M4IoPmOWSbVO9XZRBQzp3xBwrbF7lJxK4EGWl9i+BjuRdCUf109jMEo6kMVyjJTtaWAfqohcYBOIXZ3Eqgyyt4sZCIvH4JAB/0mmBPQ1wS6gwW8IPSJEo2Wx3CfQIDAQAB");
		    $("#userEditRsaAccountNo").val(encrypt.encrypt($("#userEditAccountNo").val()));
		    if(beanUserId.length==0){
		    	$("#userEditRsaPwd").val(encrypt.encrypt($("#userEditPassword").val()));
			}
		    $.ajax({
				cache : false,
				type : "POST",
				url : "${base}/user/save",
				data : $('#userEditForm').serialize(),
				async : false,
				dataType: 'json',
				success : function(data) {
					if (data.success) {
						$('#userAddModal').modal('handleUpdate');
						$('#userAddModal').modal('hide');
						tipsModalInit(data.info);
						listUserReLoad();
					} else {
						tipsModalInit(data.info);
					}

				}
			});
	});
});

function saveUser() {
	$.ajax({
		cache : false,
		type : "POST",
		url : "${base}/user/save",
		data : $('#userEditForm').serialize(),
		async : false,
		success : function(data) {
			var data = eval('(' + data + ')');
			if (data.success == true) {
				$('#userAddModal').modal('handleUpdate');
				$('#userAddModal').modal('hide');
				tipsModalInit(data.info);
				listUserReLoad();
			} else {
				tipsModalInit(data.info);
			}

		}
	});
}
</script>