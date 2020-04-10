<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/includes/taglibs.jsp"%>
<%@ include file="/includes/links.jsp"%>
<html>
<head>
	<title>${title}</title>
	<script type="text/javascript" src="${base}/resource/js/ckplayer/ckplayer.js"></script>
</head>
<body>
<!--main start-->
<div class="main auto clearfix">
     <!--right start-->
		<div class="right clearfix"  id="right">
	        <div class="list-text" style="padding-bottom: 10px;">
	            <dl>
	               <dt>${bean.title}</dt>
	               <dd>（发布人 ：${bean.releaseUser.name}&nbsp;&nbsp;发布人所在单位：${bean.releaseUser.depart.name}&nbsp;&nbsp;发布时间：<fmt:formatDate value="${bean.releaseTime}" pattern="yyyy-MM-dd"/>）</dd>
	            </dl>
	            <div class="list-t-1">
	        	    <c:forEach items="${listAtt}" var="att" varStatus="t">
	             	 <c:if test="${fn:contains(att.fileUrl,'.mp4')}">
	             	 <c:if test="${fn:contains(att.serviceType,'MP4')}">
				 	 <p id="attPlay" align="center">
				 	 </p>
				 	 <p align="center" style="font-size: 18px;color:blue;"><b>视频加载完成之后才能播放</b></p>
				 	 </c:if>
				 	 </c:if>
	      			</c:forEach>
	                <p>
	                    ${bean.content}&nbsp;
	                </p>
	                <c:if test="${fn:length(listAtt)>0}">
	                	<p><div>附件：</div></p>
	                	<p>
	                		<div>
		                	<c:forEach items="${listAtt}" var="att" varStatus="t">
		                		<c:if test="${!fn:contains(att.serviceType,'INDEX_PIC')}">
		                			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<c:if test="${fn:contains(att.serviceType,'MP4')}">
										<a href="${base}/upload/jyyw${fn:replace(att.fileUrl,'\\','/')}" style="text-decoration: underline;color: blue;">${att.originalName}</a>&nbsp;&nbsp;&nbsp;
										<br/>
									</c:if>
									<c:if test="${!fn:contains(att.serviceType,'MP4')}">
										<a href="${base}/attachment/download/${att.id}" style="text-decoration: underline;color: blue;">${att.originalName}</a>&nbsp;&nbsp;&nbsp;
										<br/>
									</c:if>
								</c:if>
	          				</c:forEach>
	          				</div>
		                </p>
	                </c:if>
	            </div>
	        </div>
	        <c:if test="${bean.isRelease==true}">
	        	<div class="list-top" align="left">反馈</div>
	         	<form id="saveSuggestion" action="${base}/xxfb/saveSuggestion" method="post" enctype="multipart/form-data">
	        		<textarea  rows="4" cols="119" style="resize:none; margin-bottom: 5px;margin-left: 19px" id="suggestion" name="suggestion"></textarea>
	        		</br><span style="color: blue;font-size: 12px;margin-left: 19px;">注：建议反馈内容在500字以内</span>
		        		</br>
		        		<table width="95%"  border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;padding-left: 20px;">
			        		<tr>
				          		<th style="font-weight: normal;text-align: right;background-color: #d6e7ff;border-bottom: 1px solid #c4d9ec;border-right: 1px solid #c4d9ec;padding: 7px;color: #00307b;">附件：</th>
				          		<td style="padding: 7px;font-weight: normal;text-align: left;border-bottom: 1px solid #c4d9ec;color: #00307b;">
				          			<table width="100%" id="xxfbSuggestionTable" border="0px">
										<tr>
											<td>
												<input type="file" name="suggestionFiles"  style='width:80%'/>&nbsp;&nbsp;
												<input type="button" class="button-1" value="添加附件" onclick="addcopy('xxfbSuggestionTable');" />
											</td>
										</tr>
									</table>
				          		</td>
				          	</tr>
	          			</table>
	   				<input type="hidden" name="articleId" value="${bean.id}">
	         		<div region="south" border="false" class="south">
	   					<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="sendMessage()" >提交反馈</a>
	   				</div>
	   			</form>
	        </c:if>
         	<c:if test="${!empty listRecord}">
		         <div class="list-top" style="background:no-repeat #ededed;height:31px;display:block; color:#1f5ba7; font-size:14px; font-weight:bold; margin:5px 20px 10px 20px; line-height:31px; text-align:center left; padding-left:10px;">阅读记录</div>
		         <div class="list-text" style="padding:0px;">
		         	<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tab-2" style="margin:0px 20px 10px 20px;width:96%;border-right:1px solid #c4d9ec;border-bottom:1px solid #c4d9ec;background:#fff; margin-bottom:10px;">
		          		<tr>
			              	<th width="10%">序号</th>
			              	<th width="15%">阅读人</th>
			              	<th width="20%">所属单位</th>
			              	<th width="20%">时间</th>
			              	<th width="45%">反馈</th>
			              </tr>
				         <c:forEach items="${listRecord}" var="record" varStatus="i">
				         	<c:if test="${currentUser.hasRole('QU_ROLE')==true}">
					     		<tr>
						      		<td>${i.index+1}</td>
						      		<td>${record.creator.name}</td>
						      		<td>${record.creator.depart.name}&nbsp</td>
						      		<td><fmt:formatDate value="${record.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						      		<td>
						      			${record.suggestion}<br/>
						      			<c:forEach items="${record.listAtt}" var="att">
						      				<a href="${base}/attachment/download/${att.id}" style="text-decoration: underline;color: blue;">${att.originalName}</a>
											<br/>
						      			</c:forEach>
						      		</td>
								</tr>
							</c:if>
							<c:if test="${currentUser.hasRole('QU_ROLE')==false && record.creator.depart.id==currentUser.depart.id}">
					     		<tr>
						      		<td>${i.index+1}</td>
						      		<td>${record.creator.name}</td>
						      		<td>${record.creator.depart.name}&nbsp</td>
						      		<td><fmt:formatDate value="${record.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						      		<td>
						      			${record.suggestion}<br/>
						      			<c:forEach items="${record.listAtt}" var="att">
						      				<a href="${base}/attachment/download/${att.id}" style="text-decoration: underline;color: blue;">${att.originalName}</a>
											<br/>
						      			</c:forEach>
						      		</td>
								</tr>
							</c:if>
				         </c:forEach>
		         	</table>
		         </div> 
	        </c:if> 
	        <c:if test="${!empty listLog}">
		         <div class="list-top" style="background:no-repeat #ededed;height:31px;display:block; color:#1f5ba7; font-size:14px; font-weight:bold; margin:5px 20px 10px 20px; line-height:31px; text-align:center left; padding-left:10px;">下载记录</div>
		         <div class="list-text" style="padding:0px;">
		         	<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tab-2" style="margin:0px 20px 10px 20px;width:96%;border-right:1px solid #c4d9ec;border-bottom:1px solid #c4d9ec;background:#fff; margin-bottom:10px;">
		          		<tr>
			              	<th width="10%">序号</th>
			              	<th width="15%">下载人</th>
			              	<th width="20%">所属单位</th>
			              	<th width="20%">时间</th>
			              	<th width="45%">附件</th>
			              </tr>
				         <c:forEach items="${listLog}" var="log" varStatus="i">
				         	<c:if test="${currentUser.hasRole('QU_ROLE')==true}">
					     		<tr>
						      		<td>${i.index+1}</td>
						      		<td>${log.creator.name}</td>
						      		<td>${log.creator.depart.name}&nbsp</td>
						      		<td><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						      		<td>${log.operateContent}</td>
								</tr>
							</c:if>
							<c:if test="${currentUser.hasRole('QU_ROLE')==false && log.creator.depart.id==currentUser.depart.id}">
					     		<tr>
						      		<td>${i.index+1}</td>
						      		<td>${log.creator.name}</td>
						      		<td>${log.creator.depart.name}&nbsp</td>
						      		<td><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						      		<td>${log.operateContent}</td>
								</tr>
							</c:if>
				         </c:forEach>
		         	</table>
		         </div> 
	        </c:if>  
	        <c:if test="${bean.isRelease==true && bean.creator.id==currentUser.id}">
	        	<div class="list-top" style="background:no-repeat #ededed;height:31px;display:block; color:#1f5ba7; font-size:14px; font-weight:bold; margin:5px 20px 10px 20px; line-height:31px; text-align:center left; padding-left:10px;">未读用户</div>
	        	<table width="95%"  border="0" cellpadding="0" cellspacing="0" style="font-size: 14px;padding-left: 20px;">
	        		<c:forEach items="${listUnReadDepartName}" var="departName">
		        		<tr>
			          		<th style="font-weight: normal;text-align: right;background-color: #d6e7ff;border-bottom: 1px solid #c4d9ec;border-right: 1px solid #c4d9ec;padding: 7px;color: #00307b;" width="20%">${departName }：</th>
			          		<td style="padding: 7px;font-weight: normal;text-align: left;border-bottom: 1px solid #c4d9ec;color: #00307b;" width="80%">
			          			<c:forEach items="${listUnReadUser}" var="u">
			          				<c:if test="${u.depart.name==departName}">
			          					${u.name},
			          				</c:if>
			          			</c:forEach>
			          		</td>
			          	</tr>
		          	</c:forEach>
        		</table>
	        </c:if>
</div>      
<!--main end-->
<script type="text/javascript">
	function viewSftjArticle(id){
		window.open("${base}/sftjArticle/view/"+id);
	}
	
	function sendMessage(){
		var suggestion=document.getElementById("suggestion").value;
		if(suggestion.length==0){
			$.messager.alert('系统提示','请填写反馈内容！','info');
			return;
		}
		if(confirm("确认提交吗?")){
			var flag = false;
			$('#saveSuggestion').form('submit',{
				onSubmit: function(){
					//验证数据是否有效
					flag=$(this).form('enableValidation').form('validate');
					if(flag){
						$.messager.progress();
					}
				return flag;
				},
				dateType:'json',
				success:function(data){
					if(flag){
					$.messager.progress('close');
					}
					var data = eval('(' + data + ')');
					if(data.success){
						$.messager.alert('系统提示',data.info,'info');
						location.reload();
						$('#suggestion').val("");
					}else{
						$.messager.alert('系统提示',data.info,'error');
						$('#suggestion').val("");
					}
				}
			});
		}
	}
	
	//动态增加的文件个数
	var copynum = 1;
	//动态增加添加附件的行
	function addcopy(id){
	    var row,cell,str;
	    row = eval("document.all."+id).insertRow();
		    if(row != null ){
				cell = row.insertCell();
				str="<input type=\"file\"  id=\"f"+copynum+"\" name=\"suggestionFiles\" style='width:80%'>&nbsp;&nbsp;&nbsp;<input class=\""+"button-1"+"\" type=\""+"button"+"\" value=\""+"删除附件"+"\" onclick=\"deletecopy(this,'xxfbSuggestionTable');\">"
				cell.innerHTML=str;
			}
		copynum++;
	}
	//删除附件的行
	function deletecopy(obj,id){
		var curRow;
		curRow = obj.parentNode.parentNode;
		rowNum = eval("document.all."+id).rows.length - 1;
		eval("document.all["+'"'+id+'"'+"]").deleteRow(curRow.rowIndex);
		copynum--;
	}
	
	//刷新未读信息列表
	window.opener.$('#unReadMsg_dg').datagrid('reload');
</script>

<c:forEach items="${listAtt}" var="att" varStatus="t">
<c:if test="${fn:contains(att.fileUrl,'.mp4')}">
<c:if test="${fn:contains(att.serviceType,'MP4')}">
<script type="text/javascript">
	var flashvars={
		f:"${base}/upload/jyyw${fn:replace(att.fileUrl,'\\','/')}",
		c:0,
		b:1,
		i:''
		};
	var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
	CKobject.embedSWF('${base}/resource/js/ckplayer/ckplayer.swf','attPlay','ckplayer_attPlay','600','400',flashvars,params);
	var video=["${base}/upload/jyyw${fn:replace(att.fileUrl,'\\','/')}->video/mp4"];
	var support=['iPad','iPhone','ios','android+false','msie10+false'];
	CKobject.embedHTML5('attPlay','ckplayer_attPlay',600,400,video,flashvars,support);
	
	
	function closelights(){//关灯
		alert(' 本演示不支持开关灯');
	}
	function openlights(){//开灯
		alert(' 本演示不支持开关灯');
	}
  </script>
 </c:if>
 </c:if>
</c:forEach>

</body>
</html>