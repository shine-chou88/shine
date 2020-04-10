<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="txt/html; charset=UTF-8" />
	<title>${title}</title>
</head>
<body> 

<div id="certificate_view_tt" class="easyui-tabs" fit="true">  
<c:if test="${ not empty publicForeign.id }">
<div title="因公普通护照" class="person_view_orderBy"  >
		<%@ include file="/WEB-INF/view/swj/certificate/view_publicForeign.jsp"%>
</div>
</c:if> 
<c:if test="${ not empty foreign.id }">
<div  title="因私普通护照" class="person_view_orderBy" >
		<%@ include file="/WEB-INF/view/swj/certificate/view_foreign.jsp"%>
</div>
</c:if> 
<c:if test="${ not empty hongkongAndMacao.id }">
<div title="中华人民共和国往来港澳通行证" class="person_view_orderBy"  >
		<%@ include file="/WEB-INF/view/swj/certificate/view_hongkong_macao.jsp"%>
</div>
</c:if> 
<c:if test="${ not empty hongkongAndMacaoPublic.id }">
<div title="因公往来香港澳门特别行政区通行证" class="person_view_orderBy" >
		<%@ include file="/WEB-INF/view/swj/certificate/view_hongkong_macao_public.jsp"%>
</div>
</c:if> 
<c:if test="${ not empty taiwan.id }">
<div title="大陆居民往来台湾通行证(因私)" class="person_view_orderBy"  >
		<%@ include file="/WEB-INF/view/swj/certificate/view_taiwan.jsp"%>
</div>
</c:if>
<c:if test="${ not empty taiwanPublic.id }">
<div title="大陆居民往来台湾通行证(因公)" class="person_view_orderBy"  >
		<%@ include file="/WEB-INF/view/swj/certificate/view_taiwan_public.jsp"%>
</div>
</c:if> 
</div>
<script type="text/javascript">
$( "#certificate_view_tt" ).tabs({'selected': '${tabSelectCertificate}' });
</script>
</body>
</html>