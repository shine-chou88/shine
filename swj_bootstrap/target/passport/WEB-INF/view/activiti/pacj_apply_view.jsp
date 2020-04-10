<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
	<table cellpadding="5" cellspacing="0" class="main_table" width="100%">
		<tr>
			<th class="br">申报类型：</th>
			<td class="b" colspan="3">
				${bean.applyType.name}&nbsp;
			</td>
		</tr>
		<tr>
			<th class="br">所属街镇：</th>
			<td class="br">
				${bean.street.name}&nbsp;
			</td>
			<th class="br">所属居委：</th>
			<td class="b">
				${bean.jwh.jwhName}&nbsp;
			</td>
		</tr>
		<tr>
			<th class="br">申报评定项目：</th>
			<td class="b" colspan="3">
				${bean.applyProj}&nbsp;
			</td>
		</tr>
		<tr>
			<th class="br">评定对象（申报单位）：</th>
			<td class="br" colspan="3">
				${bean.applyObj}&nbsp;
			</td>
		</tr>
		<tr>
			<th class="br">申报年度：</th>
			<td class="br">
				${bean.applyYear}&nbsp;
			</td>
			<th class="br">申报时间：</th>
			<td class="b">
				${bean.applyTime}&nbsp;
			</td>
		</tr>
		<tr>
			<th class="br">自查标准负荷情况：</th>
			<td class="br" colspan="3">
				${bean.standard.name}&nbsp;
			</td>
		</tr>
		<tr>
			<th class="br">情况说明：</th>
			<td class="b" colspan="3">
				${bean.remark}&nbsp;
			</td>
		</tr>
		<c:if test="${fn:length(listFirstName)>0}">
          		<tr>
	          		<td colspan="4">
	          			<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="main_table">
	          				<tr>
	          					<th width="10%" style="text-align: center;"><b>序号</b></th>
	          					<th width="30%" style="text-align: center;"><b>指标说明</b></th>
	          					<th width="40%" style="text-align: center;" colspan="2"><b>核对和发现情况</b></th>
	          					<th width="20%" style="text-align: center;"><b>满足与否</b></th>
	          				</tr>
	          				<c:set var="index" value="1"/>
	          				<c:forEach items="${listFirstName}" var="firstName" varStatus="t">
	          					<tr>
	          						<c:if test="${fn:length((mapModel[firstName])[0].firstCode)==0}">
	          							<th width="100%" colspan="5" style="text-align: center;border-left: #c4d9ec 1px solid;border-right: #c4d9ec 1px solid;"><b>${firstName}</b></th>
	          							<c:set var="index" value="1"/>
	          						</c:if>
	          						<c:if test="${fn:length((mapModel[firstName])[0].firstCode)>0}">
	          							<td width="10%" style="text-align: center;border-left: #c4d9ec 1px solid;border-right: #c4d9ec 1px solid;" rowspan="${fn:length(mapModel[firstName])}">${index}</td>
			          					<td width="30%" style="text-align: center;border-right: #c4d9ec 1px solid;" rowspan="${fn:length(mapModel[firstName])}">${firstName}</td>
			          					<td width="20%" style="text-align: right;border-right: #c4d9ec 1px solid;">
			          						${(mapModel[firstName])[0].secondName}&nbsp;
			          					</td>
			          					<td width="20%" style="text-align: left;border-right: #c4d9ec 1px solid;">
			          						${(mapModel[firstName])[0].secondValue}&nbsp;
			          					</td>
			          					<td width="20%" style="text-align: center;border-right: #c4d9ec 1px solid;" rowspan="${fn:length(mapModel[firstName])}">
			          						${(mapModel[firstName])[0].isSure}&nbsp;
			          					</td>
			          					<c:set var="index" value="${index+1}"/>
	          						</c:if>
		          				</tr>
		          				<c:forEach items="${mapModel[firstName]}" var="model" begin="1">
		          					<tr>
		          						<td style="text-align: right;border-right: #c4d9ec 1px solid;">
		          							${model.secondName}&nbsp;
		          						</td>
		          						<td style="text-align: left;border-right: #c4d9ec 1px solid;">
			          						${model.secondValue}&nbsp;
		          						</td>
		          					</tr>
		          				</c:forEach>
	          				</c:forEach>
	          			</table>
	          		</td>
	          	</tr>
          	</c:if>
	</table>