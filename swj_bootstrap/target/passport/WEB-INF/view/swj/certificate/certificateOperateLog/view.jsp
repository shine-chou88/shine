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
          			操作类型：
          		</th>
          		<td width="70%" class="br">
          			${ bean.operateType}&nbsp;
          		</td>
        	</tr>
        	        	<tr>
          		<th width="30%" class="br">
          			操作内容：
          		</th>
          		<td width="70%" class="br">
          			${ bean.operateContent}&nbsp;
          		</td>
        	</tr>
        	        	<tr>
          		<th width="30%" class="br">
          			操作人：
          		</th>
          		<td width="70%" class="br">
          			${ bean.operateUser}&nbsp;
          		</td>
        	</tr>
        	        	<tr>
          		<th width="30%" class="br">
          			操作时间：
          		</th>
          		<td width="70%" class="br">
          			${ bean.operateTime}&nbsp;
          		</td>
        	</tr>
        	        	<tr>
          		<th width="30%" class="br">
          			证照信息ID：
          		</th>
          		<td width="70%" class="br">
          			${ bean.certificateId}&nbsp;
          		</td>
        	</tr>
        	            <tr>
            <td class="br" colspan="2">
           		<div region="south" border="false" style="text-align: center; height: 32px; line-height: 30px; padding-left: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-cancel" onclick="closeWindow()">关闭</a>
				</div>
            </td>
            </tr>
        </table>
	</div>
</div>    	
</body>
</html>