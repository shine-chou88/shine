<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
	<table cellpadding="5" cellspacing="0" class="main_table" width="100%">
	  <tr>
          		<th width="20%">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</th>
          		<td width="30%">${bean.name}</td>
          		<th width="20%" rowspan="5">照&nbsp;&nbsp;&nbsp;&nbsp;片：</th>
          		<td width="30%" rowspan="5">
          			<c:if test="${fn:length(listAtt)>0}">
          				<a href="${base}/upload/jyyw${fn:replace(listAtt[0].fileUrl,'\\','/')}" target="_blank">
          					<img id="preImg1" alt="预览区" src="${base}/upload/jyyw${fn:replace(listAtt[0].fileUrl,'\\','/')}" srcRoot="" style="width:200px;height:120px;background-color:#CCCCCC;border:1px solid #333"/>
          				</a>
          			</c:if>
          		</td>
          	</tr>
          	<tr>
          		<th width="20%">性&nbsp;&nbsp;&nbsp;&nbsp;别：</th>
          		<td width="30%">${bean.sex}&nbsp;</td>
          	</tr>
          	<tr>
          		<th width="20%">身份证号：</th>
          		<td width="30%">
          			<c:if test="${fn:length(bean.cardID)>0}">
				   		${bean.cardID}
			   		</c:if>
          			&nbsp;
          		</td>
          	</tr>
          	<tr>
          		<th width="20%">出生年月：</th>
          		<td width="30%">${bean.birthday}&nbsp;</td>
          	</tr>
          	<tr>
          	<th width="20%">民&nbsp;&nbsp;&nbsp;&nbsp;族：</th>
          		<td width="30%">${bean.nation.name}&nbsp;</td>
          	</tr>
          	<tr>
          		<th width="20%">所属街镇/居委：</th>
          		<td width="30%" colspan="3">${bean.street.name}&nbsp;/&nbsp;${bean.jwh.jwhName}</td>
          	</tr>
          	<tr>
          		<th width="20%">职&nbsp;&nbsp;&nbsp;&nbsp;业：</th>
          		<td width="30%">${bean.career}&nbsp;</td>
          		<th width="20%">联系电话：</th>
          		<td width="30%">${bean.tel}&nbsp;</td>
          	</tr>
          	<%--<tr>
          		<th width="20%">出生年月：</th>
          		<td width="30%">${bean.birthday}&nbsp;</td>
          	</tr>
          	<tr>
          		<th width="20%">伤残情况：</th>
          		<td width="30%">${bean.disabled.name}&nbsp;</td>
          	</tr>
          	--%><tr>
          		<th width="20%">户籍地址：</th>
          		<td width="30%">${bean.bornAddress}&nbsp;</td>
          		<th width="20%">现住地址：</th>
          		<td width="30%">${bean.address}&nbsp;</td>
          	</tr>
          	<tr>
          		<th width="20%">文化程度：</th>
          		<td width="30%">${bean.degree.name}&nbsp;</td>
          		<th width="20%">政治面貌：</th>
          		<td width="30%">${bean.politics.name}&nbsp;</td>
          	</tr>
          	<tr>
          		<th width="20%">工作单位：</th>
          		<td width="30%">${bean.unitName}&nbsp;</td>
          		<th width="20%">级别：</th>
          		<td width="30%">${bean.level}&nbsp;</td>
          	</tr><%--
          	<tr>
          		<th width="20%">联系手机：</th>
          		<td width="30%">${bean.mobile}&nbsp;</td>
          		<th width="20%">联系电话：</th>
          		<td width="30%">${bean.tel}&nbsp;</td>
          	</tr>
          	--%>
          		
          		<%--<th width="20%">邮&nbsp;&nbsp;&nbsp;&nbsp;编：</th>
          		<td width="30%">${bean.code}&nbsp;</td>
          	--%>
          	<tr>
          		<th>事迹类型：</th>
          		<td colspan="3">
          		${bean.mainStoryType}&nbsp;
          		</td>
          	</tr>
          	<tr>
          		<th>伤亡情况：</th>
          		<td colspan="3">
          		${bean.swqk}&nbsp;
          		</td>
          	</tr>
          	<tr>
          		<th width="20%">主要事迹：</th>
          		<td width="30%" colspan="3">${bean.mainStory}&nbsp;</td>
          	</tr>
	   	
	</table>


