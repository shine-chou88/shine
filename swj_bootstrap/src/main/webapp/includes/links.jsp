<%@ page language="java" pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="${base}/resource/plugins/My97DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="${base}/resource/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resource/js/jsencrypt.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/bootstrap-table.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/bootstrap-switch.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/bootstrap-validator.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/css/left-side-menu.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/css/iconfont.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/css/jquery-tabs.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/bootstrap-select.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/bootstrap-treeview.css"/>
<script type="text/javascript" src="${base}/resource/bootstrap/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${base}/resource/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-table.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-switch.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-validator.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-validator-zh_CN.js"></script>
<script type="text/javascript" src="${base}/resource/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${base}/resource/js/left-side-menu.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-select.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-treeview.js"></script>
<script type="text/javascript">
	var base='${base}';
	function confirmModalInit(message){
		$("#confirmMessage").html(message);
		$("#confirmModal").modal("show");
	}
	function tipsModalInit(message){
		$("#tipsMessage").html(message);
		$("#tipsModal").modal("show");
	}
</script>

<div id="confirmModal" class="modal fade" data-backdrop="static" data-keyboard="false" style="z-index: 10000;">  
      <div class="modal-dialog modal-lm">  
        <div class="modal-content">  
          <div class="modal-header">  
            <h4 class="modal-title"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;系统提示</h4>  
          </div>  
          <div class="modal-body small">  
            <span id="confirmMessage"></span>  
          </div>  
          <div class="modal-footer"> 
          	<center> 
            	<button type="button" class="btn btn-primary ok" data-dismiss="modal" id="confirmOk">确认</button>  
            	<button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
            </center>  
          </div>  
        </div>  
      </div>  
</div>

<div id="tipsModal" class="modal fade" data-backdrop="static" data-keyboard="false" style="z-index: 10000;">  
      <div class="modal-dialog modal-sm">  
        <div class="modal-content">  
          <div class="modal-header">  
            <h4 class="modal-title"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;系统提示</h4>  
          </div>  
          <div class="modal-body small">  
            <span id="tipsMessage"></span>  
          </div>  
          <div class="modal-footer">  
          	<center>
            	<button type="button" class="btn btn-primary cancel" data-dismiss="modal" id="tipsOk">确认</button>
            </center>  
          </div>  
        </div>  
      </div>  
</div>  