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
</script>


 <div class="easyui-layout" fit="true" style="padding:2px">
 	<div region="center" border="false">
 	
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
          		<td>${sModel.msgTitle}</td>
          		<th>&nbsp;</th>
          		<td width="260px">
          		</td>
          	</tr>
          	<tr>
          		<th rowspan="2"><span style="color: red">*&nbsp;</span>短信接收人：</th>
          		<td rowspan="2">
          			<c:forEach items="${sModel.users}" var="user" varStatus="vs">
          				${user.name}&nbsp;
          			</c:forEach>
          		</td>
          		<th>提醒时间：</th>
          		<td>${sModel.msgDate}</td>
          	</tr>
          	<tr>
          		<th>提醒周期：</th>
          		<td width="260px">
          			<c:if test="${sModel.msgCircle==10}">每月</c:if>
          			<c:if test="${sModel.msgCircle==20}">每季度</c:if>
          			<c:if test="${sModel.msgCircle==30}">每年</c:if>
          			<c:if test="${sModel.msgCircle==0}">仅一次</c:if>
          		</td>
          	</tr>
          	<tr>
          		<th rowspan="3">短信内容：</th>
          		<td rowspan="3">${sModel.msgContent}</td>
          		<th>填报人：</th>
          		<td>${sModel.updator.name}</td>
          	</tr>
          	<tr>
          		<th>填报单位：</th>
          		<td>${sModel.updator.depart.name}</td>
          	</tr>
          	<tr>
          		<th>填报时间：</th>
          		<td><fmt:formatDate value="${sModel.updateTime}" pattern="yyyy-MM-dd"/></td>
          	</tr>
          	<tr>
          		<%--<th>附件：</th>
          		<td colspan="3">
          			<table width="100%" border="0px">
          				<c:forEach items="${listAtt}" var="att" varStatus="vs">
									<c:if test="${fn:contains(att.originalName,'jpg')||fn:contains(att.originalName,'png')||fn:contains(att.originalName,'jpeg')}">
										<tr id="${att.id}">
											<td style="border: 0">
												<a href="${base}/upload/xstb${fn:replace(att.fileUrl,'\\','/')}" target="_blank">
												<img id="p" src="${base}/upload/xstb${fn:replace(att.fileUrl,'\\','/')}" style="width: 180px; height: 120px"/>
												</a>
											</td>
										</tr>
									</c:if>
									<c:if test="${!fn:contains(att.originalName,'jpg')&&!fn:contains(att.originalName,'png')&&!fn:contains(att.originalName,'jpeg')}">
										<tr id="${att.id}">
											<td style="border: 0">
												<a href="${base}/attachment/downloadXstb/${att.id}" style="text-decoration: underline;color: blue;">${att.originalName}</a>&nbsp;&nbsp;&nbsp;
											</td>
										</tr>
									</c:if>
          				</c:forEach>
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
   			<input  type="button"  onclick="javascript:window.close();" class="button-blue" value="关闭">
		</div>
	</div>
 </div>    	
 
</body>
</html>