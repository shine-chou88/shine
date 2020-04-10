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
        <table cellpadding="5" cellspacing="0" class="a_table" width="100%">
        	        	<tr>
          		<th width="30%" class="br">
          			单位：
          		</th>
          		<td width="70%" class="br">
          			${bean.departName }
          		</td>
        	</tr>
        	        	<tr>
          		<th width="30%" class="br">
          			<strong style="color: red;">*</strong>填写人姓名：
          		</th>
          		<td width="70%" class="br">
          			${ bean.writeUserName}
          		</td>
        	</tr>
        	<tr>
				            <th class="br"><strong style="color: red;">*</strong>证照编号及持有人姓名：</th>
			          		<td class="br" >
			          			<div id="certificate_adjust_info" style="float: left; width:450px; overflow-x:hidden;  margin: 5px" >
			          			        <c:forEach items="${bean.certificateList}" var="certificate" varStatus="vs">
					          				<div style='float:left;margin-left:8px;' >
					          					<input type='hidden' name='certificateIds'  value='${certificate.id}'></input>
					          					<a href="javascript:void(0)" style="color: blue;text-decoration:underline;" onclick="publicAdjustViewCertificateInfo('${certificate.id}')">${certificate.zjhm}-${certificate.name}</a>
					          				</div>
					          			</c:forEach>
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
          			${bean.afterDepartInnerName }
          		</td>
        	</tr>
        	        	<tr>
          		<td width="70%" class="br">
          			(系统外)调动后单位名称：${ bean.afterDepartOuter}
          		</td>
        	</tr>
        	        	<tr>
          		<td width="70%" class="br">
          			<c:if test="${bean.hasRetired=='是'}">已退休</c:if>
          			<c:if test="${empty bean.hasRetired || bean.hasRetired=='否'}">未退休</c:if>
          		</td>
        	</tr>
        	        	
        	        	<tr>
          		<td width="70%" class="br">
          			其他：${ bean.other}
          		</td>
        	</tr>
        	            <tr>
        	<tr>
          		<th width="30%" class="br">
          			填写人电话：
          		</th>
          		<td width="70%" class="br">
          			${ bean.writeUserPhone}
          		</td>
        	</tr>
        	        	<tr>
          		<th width="30%" class="br">
          			填写日期：
          		</th>
          		<td width="70%" class="br">
          			<fmt:formatDate value="${bean.writeDate}" pattern="yyyy-MM-dd"/>
          		</td>
        	</tr>
        	            <tr>
            <td class="br" colspan="2">
           		<div region="south" border="false" style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-cancel" onclick="closeFirstWindow()">关闭</a>
				</div>
            </td>
            </tr>
        </table>
	</div>
</div>  
<script type="text/javascript">
function publicAdjustViewCertificateInfo(id){
	if (id != '') {
		var win=creatWin('查看-证照信息',880,730,'icon-search','/certificateInfo/view/'+id+'?businessType=public');
		win.window('open');
	}
	
}
</script>  	
</body>
</html>