<%@ page language="java" pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="${base}/resource/plugins/My97DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="${base}/resource/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resource/js/jsencrypt.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/bootstrap/css/bootstrap-table.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/css/left-side-menu.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/css/iconfont.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resource/css/jquery-tabs.css"/>
<script type="text/javascript" src="${base}/resource/bootstrap/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="${base}/resource/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${base}/resource/js/left-side-menu.js"></script>
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

<div id="confirmModal" class="modal">  
      <div class="modal-dialog modal-lm">  
        <div class="modal-content">  
          <div class="modal-header">  
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>  
            <h5 class="modal-title"><i class="fa fa-exclamation-circle"></i><span class="glyphicon glyphicon-info-sign"></span>&nbsp;系统提示</h5>  
          </div>  
          <div class="modal-body small">  
            <p id="confirmMessage"></p>  
          </div>  
          <div class="modal-footer">  
            <button type="button" class="btn btn-primary ok" data-dismiss="modal" id="confirmOk">确认</button>  
            <button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>  
          </div>  
        </div>  
      </div>  
</div>

<div id="tipsModal" class="modal" style="z-index:9999;display: none;">  
      <div class="modal-dialog modal-sm">  
        <div class="modal-content">  
          <div class="modal-header">  
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>  
            <h5 class="modal-title"><i class="fa fa-exclamation-circle"></i><span class="glyphicon glyphicon-info-sign"></span>&nbsp;系统提示</h5>  
          </div>  
          <div class="modal-body small">  
            <p id="tipsMessage"></p>  
          </div>  
          <div class="modal-footer">  
            <button type="button" class="btn btn-primary cancel" data-dismiss="modal" id="confirmOk">确认</button>  
          </div>  
        </div>  
      </div>  
</div>  