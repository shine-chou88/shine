<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<div class="main_new">
	<div class="side-bar">
		<ul class="side-bar_wrapper">
		<!-- 
			<gwideal:perm url="/xxfb">
			<li class="side-bar_item skin-1">
				<p>
					<i class="icon"> <img src="${base}/resource/images/icon-1.png" alt="">
					</i> <span class="text">通知公告</span>
				</p>
				<ul>
					<gwideal:perm url="/xxfb/list">
					<li class="side-bar_item current" onclick="addTabs('通知公告','${base}/xxfb/list');">
						<p><a href="javascript:void(0);">通知公告</a></p>
					</li>
					</gwideal:perm>
				</ul>
			</li>
			</gwideal:perm> 
			-->
			<gwideal:perm url="/myCertificateInfo">
			<li class="side-bar_item skin-1">
				<p>
					<i class="icon"> <img src="${base}/resource/images/icon-1.png" alt="">
					</i> <span class="text">我的证照</span>
				</p>
				<ul>
					<gwideal:perm url="/certificateInfo/myList">
					<li class="side-bar_item current" onclick="addTabs('我的证照','${base}/certificateInfo/myList');">
						<p><a href="javascript:void(0);">我的证照</a></p>
					</li>
					</gwideal:perm>
				</ul>
			</li>
			</gwideal:perm>
			<!-- 证照信息因私 -->
			<gwideal:perm url="/certificateInfo">
			<li class="side-bar_item skin-1">
				<p>
					<i class="icon"> <img src="${base}/resource/images/icon-2.png" alt="">
					</i> <span class="text">证照信息管理（因私）</span>
				</p>
				<ul>
					<gwideal:perm url="/certificateInfo/list">
					<li class="side-bar_item current" onclick="addTabs('证照信息（因私）','${base}/certificateInfo/list');">
						<p><a href="javascript:void(0);">证照信息（因私）</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/certificateInfo/cancelList">
					<li class="side-bar_item current" onclick="addTabs('证照信息（因私-注销）','${base}/certificateInfo/cancelList');">
						<p><a href="javascript:void(0);">证照信息（因私-注销）</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/certificateOperateLog/list">
					<li class="side-bar_item current" onclick="addTabs('证照使用记录','${base}/certificateOperateLog/list');">
						<p><a href="javascript:void(0);">证照使用记录</a></p>
					</li>
					</gwideal:perm>
				</ul>
			</li>
			</gwideal:perm>
			
			<!-- 证照信息因公 -->
			<gwideal:perm url="/certificateInfoPublic">
			<li class="side-bar_item skin-1">
				<p>
					<i class="icon"> <img src="${base}/resource/images/icon-3.png" alt="">
					</i> <span class="text">证照信息管理（因公）</span>
				</p>
				<ul>
					<gwideal:perm url="/certificateInfo/listPublic">
					<li class="side-bar_item current" onclick="addTabs('证照信息（因公）','${base}/certificateInfo/listPublic');">
						<p><a href="javascript:void(0);">证照信息（因公）</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/certificateInfo/cancelListPublic">
					<li class="side-bar_item current" onclick="addTabs('证照信息（因公-待注销）','${base}/certificateInfo/cancelListPublic?cancelType=wait');">
						<p><a href="javascript:void(0);">证照信息（因公-待注销）</a></p>
					</li>
					<li class="side-bar_item current" onclick="addTabs('证照信息（因公-已注销）','${base}/certificateInfo/cancelListPublic?cancelType=cancelled');">
						<p><a href="javascript:void(0);">证照信息（因公-已注销）</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/publicAdjust/list">
					<li class="side-bar_item current" onclick="addTabs('证照信息调整','${base}/publicAdjust/list');">
						<p><a href="javascript:void(0);">证照信息调整</a></p>
					</li>
					</gwideal:perm>
				</ul>
			</li>
			</gwideal:perm>
			
			<gwideal:perm url="/certificateInfoApply">
			<li class="side-bar_item skin-1">
				<p>
					<i class="icon"> <img src="${base}/resource/images/icon-4.png" alt="">
					</i> <span class="text">因私出国（境）审批</span>
				</p>
				<ul>
					<gwideal:perm url="/certificateInfoApply/list">
					<li class="side-bar_item current" onclick="addTabs('申请审批','${base}/certificateInfoApply/list');">
						<p><a href="javascript:void(0);">申请审批</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/certificateInfoApply/useScanList">
					<li class="side-bar_item current" onclick="addTabs('申请证照待领用','${base}/certificateInfoApply/useScanList');">
						<p><a href="javascript:void(0);">申请证照待领用</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/certificateInfoApply/backScanList">
					<li class="side-bar_item current" onclick="addTabs('申请证照待归还','${base}/certificateInfoApply/backScanList');">
						<p><a href="javascript:void(0);">申请证照待归还</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/certificateInfoApply/receiveScanList">
					<li class="side-bar_item current" onclick="addTabs('申请证照待领回','${base}/certificateInfoApply/receiveScanList');">
						<p><a href="javascript:void(0);">申请证照待领回</a></p>
					</li>
					</gwideal:perm>
				</ul>
			</li>
			</gwideal:perm>
			<!--证照借还管理(因公) -->
			<gwideal:perm url="/publicBusiness">
			<li class="side-bar_item skin-1">
				<p>
					<i class="icon"> <img src="${base}/resource/images/icon-5.png" alt="">
					</i> <span class="text">因公出国（境）管理</span>
				</p>
				<ul>
					<gwideal:perm url="/publicBusiness/useRegisterList">
					<li class="side-bar_item current" onclick="addTabs('证照领用信息表','${base}/publicBusiness/useRegisterList');">
						<p><a href="javascript:void(0);">证照领用信息表</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/publicBusiness/backRegisterList">
					<li class="side-bar_item current" onclick="addTabs('证照归还信息表','${base}/publicBusiness/backRegisterList');">
						<p><a href="javascript:void(0);">证照归还信息表</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/publicBusiness/scanUseRegisterList">
					<li class="side-bar_item current" onclick="addTabs('证照待领用','${base}/publicBusiness/scanUseRegisterList');">
						<p><a href="javascript:void(0);">证照待领用</a></p>
					</li>
					</gwideal:perm>
					<gwideal:perm url="/publicBusiness/scanBackRegisterList">
					<li class="side-bar_item current" onclick="addTabs('证照待归还','${base}/publicBusiness/scanBackRegisterList');">
						<p><a href="javascript:void(0);">证照待归还</a></p>
					</li>
					</gwideal:perm>
				</ul>
			</li>
			</gwideal:perm>
			  	
			<gwideal:perm url="/system"> 
				<li class="side-bar_item skin-1">
					<p>
						<i class="icon"> <img src="${base}/resource/images/icon-9.png" alt="">
						</i> <span class="text">系统管理</span>
					</p>
			    	<ul>
			    		<gwideal:perm url="/smsPool/list">
			    		<li class="side-bar_item current" onclick="addTabs('短信管理','${base}/smsPool/list');">
			       			<p><a href="javascript:void(0);">短信管理</a></p>
			       		</li>
			       		</gwideal:perm>
			    		<gwideal:perm url="/activiti/process/list">
			    		<li class="side-bar_item current" onclick="addTabs('流程管理','${base}/activiti/process/list');">
			       			<p><a href="javascript:void(0);">流程管理</a></p>
			       		</li>
			       		</gwideal:perm>
			    		<gwideal:perm url="/log/list">
			    		<li class="side-bar_item current" onclick="addTabs('操作日志','${base}/log/list');">
			       			<p><a href="javascript:void(0);">操作日志</a></p>
			       		</li>
			       		</gwideal:perm>
			    		<gwideal:perm url="/category/list">
			    		<li class="side-bar_item current" onclick="addTabs('字典类型管理','${base}/category/list');">
			       			<p><a href="javascript:void(0);">字典类型管理</a></p>
			       		</li>
			       		</gwideal:perm>
			       		<gwideal:perm url="/lookup/list"> 
			       		<li class="side-bar_item current" onclick="addTabs('字典项管理','${base}/lookup/list');">
			       			<p><a href="javascript:void(0);">字典项管理</a></p>
			       		</li>
			       		 </gwideal:perm>
			    		<gwideal:perm url="/user/list"> 
			    		<li class="side-bar_item current" onclick="addTabs('用户管理','${base}/user/list');">
			        		<p><a href="javascript:void(0);">用户管理</a></p>
			        	</li>
			        	</gwideal:perm>
			        	<gwideal:perm url="/depart/list">
			        	<li class="side-bar_item current" onclick="addTabs('部门管理','${base}/depart/list');">
			                <p><a href="javascript:void(0);">部门管理</a></p>
			            </li>
			            </gwideal:perm>
			        	<gwideal:perm url="/role/list"> 
			        	<li class="side-bar_item current" onclick="addTabs('角色管理','${base}/role/list');">
			        		<p><a href="javascript:void(0);">角色管理</a></p>
			        	</li>
			        	</gwideal:perm>
			        	<gwideal:perm url="/function/main">
			        	<li class="side-bar_item current" onclick="addTabs('功能菜单','${base}/function/main');"> 
			        		<p><a href="javascript:void(0);">功能菜单</a></p>
			        	</li>
			        	</gwideal:perm> 
			        </ul>
			    </li>
			</gwideal:perm> 
			
		</ul>
	</div>
</div>





