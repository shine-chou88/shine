<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div class="container-fluid" style="padding-left: 25px;padding-right: 20px;">
	<div class="modal fade" id="viewProcessModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">用户-添加</h4>
		      </div>
		      <div class="modal-body">
		      		<table border="0" cellpadding="0" cellspacing="0" class="view_table">
						<tr>
							<td><img src="${base}/activiti/process/viewDiagram/${processDefinitionId}"/></td>
						</tr>
					</table>
		      </div>
		      <div class="modal-footer col-center-block">
		        <button type="button" class="btn btn-default" onclick="save()">保存</button>
		        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
		      </div>
		    </div>
		  </div>
		</div>
</div>