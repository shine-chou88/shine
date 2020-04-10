<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
</head>
<body>
    <div class="easyui-layout" fit="true">
    	<div region="center" border="false">
				<table border="0" cellpadding="0" cellspacing="0" class="main_table">
					<tr>
						<th width="20%">账号：</th>
						<td width="80%" colspan="3">
							${user.accountNo}
						</td>
					</tr>
					<tr>
						<th width="20%">姓名：</th>
						<td width="30%">
							${user.name}
						</td>
						<th width="20%">密钥唯一标示：</th>
						<td width="30%">
							${user.certUniqueId}
						</td>
					</tr>
					<tr>
						<th>部门：</th>
						<td>${user.depart.name}</td>
						<th >职务：</th>
						<td >
							${user.post}
						</td>
					</tr>
					<tr>
						<th>身份证号码：</th>
						<td colspan="3">
							${user.idCard}
						</td>
					</tr>
					<tr>
						<th>手机号码：</th>
						<td>
							${user.mobileNo}
						</td>
						<th >电话号码：</th>
						<td >
							${user.telNo}
						</td>
					</tr>
					<tr>
						<th >传真号码：</th>
						<td >
							${user.faxNo}
						</td>
						<th >邮编：</th>
						<td >
							${user.postalCode}
						</td>
					</tr>
					<tr>
						<th >邮箱：</th>
						<td >
							${user.email}
						</td>
						<th>地址：</th>
						<td>
							${user.address}
						</td>
					</tr>
					<tr>
						<th>角色：</th>
						<td colspan="3">
							<c:forEach items="${user.roles}" var="role">
								${role.name}，
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>拥有的功能：</th>
						<td colspan="3">
							<ul id="functionUserTree" class="easyui-tree" data-options="url:'${base}/user/functionTree?userId=${user.id}',animate:true,lines:true,checkbox:true,cascadeCheck:false"></ul>
						</td>
					</tr>
				</table>
		</div>
		<div region="south" border="false" class="south">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="closeWindow();">关闭</a>
		</div>
	</div>
</body>
</html>

