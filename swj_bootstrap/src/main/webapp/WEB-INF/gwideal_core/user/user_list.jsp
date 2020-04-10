<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<script type="text/javascript" src="${base}/resource/js/data/core/user/list.js"></script>
	<div class="container-fluid" style="padding-left: 25px;padding-right: 20px;">
		<div class="row">
			<form class="navbar-form navbar-left" role="search">
			  <div class="form-group">
				姓名：<input type="text" class="form-control" id="userListName"/>
			  </div>
			  <button type="button" class="btn btn-default" onclick="reLoad()">查询</button>
			  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加</button>
			</form>
		</div>

		<div class="row">
			<table id="userListTable"></table>
		</div>
	
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">用户-添加</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-horizontal" role="form" id="userEditForm">
						  <div class="form-group">
						  	<label class="control-label col-sm-2">姓名：</label>
						  	<div class="col-sm-9">
								<input type="text" class="form-control" name="name"/>
							</div>
						  </div>
						  <div class="form-group">
						  	<label class="control-label col-sm-2">账号：</label>
						  	<div class="col-sm-9">
								<input type="text" class="form-control" name="accountno"/>
							</div>
						  </div>
				</form>
		      </div>
		      <div class="modal-footer col-center-block">
		        <button type="button" class="btn btn-default" onclick="save()">保存</button>
		        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>