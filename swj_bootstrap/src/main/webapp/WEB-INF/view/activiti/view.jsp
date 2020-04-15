<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
   <h4 class="modal-title" id="myModalLabel">查看-流程图</h4>
</div>
<div class="modal-body" style="width:900px;overflow-x: scroll;">
  	<img src="${base}/activiti/process/viewDiagram/${processDefinitionId}"/>
</div>
<div class="modal-footer center-block">
  <center><button type="button" class="btn btn-default cancel" data-dismiss="modal">关闭</button></center>
</div>
		  