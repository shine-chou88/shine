﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="gwideal" uri="/gwideal-tags" %>
<div class="left-side-menu">
	<!-- <div class="lsm-expand-btn">
        <div class="lsm-mini-btn">
            <label>
                <input type="checkbox" checked="checked">
                <svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="50" cy="50" r="30" />
                    <path class="line--1" d="M0 40h62c18 0 18-20-17 5L31 55" />
                    <path class="line--2" d="M0 50h80" />
                    <path class="line--3" d="M0 60h62c18 0 18 20-17-5L31 45" />
                </svg>
            </label>

        </div>
    </div> -->
	<div class="lsm-container">
        <div class="lsm-scroll" >
            <div class="lsm-sidebar">
                <ul>
                	<gwideal:perm url="/system">
                    <li class="lsm-sidebar-item">
                        <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon icon_1"></i><span>系统管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                        	<gwideal:perm url="/activiti/process/list">
                            	<li><a href="javascript:void(0);" onclick="addTab('流程管理','${base}/activiti/process/list');"><span>流程管理</span></a></li>
                            </gwideal:perm>
                            <gwideal:perm url="/user/list">
                            	<li><a href="javascript:void(0);" onclick="addTab('用户管理','${base}/user/list');"><span>用户管理</span></a></li>
                            </gwideal:perm>
                            <li><a href="javascript:void(0);" onclick="addTab('角色管理','${base}/role/list');"><span>角色管理</span></a></li>
                        </ul>
                    </li>
                    </gwideal:perm>
                    <li class="lsm-sidebar-item">
                        <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon icon_2"></i><span>网络设置</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="javascript:;"><span>地爆天星1</span></a></li>
                            <li><a href="javascript:;"><span>神罗天征1</span></a></li>
                            <li><a href="javascript:;"><span>八门遁甲1</span></a></li>
                            <li><a href="javascript:;"><span>一乐拉面</span></a></li>
                            <li class="lsm-sidebar-item">
                                <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon "></i><span>二级菜单11</span><i class="my-icon lsm-sidebar-more"></i></a>
                                <ul>
                                    <li><a href="javascript:;"><span>地爆天星</span></a></li>
                                    <li><a href="javascript:;"><span>无线月读</span></a></li>
                                    <li><a href="javascript:;"><span>一乐拉面</span></a></li>
                                    <li><a href="javascript:;"><span>血继限界</span></a></li>
                                    <li><a href="javascript:;"><span>秽土转生</span></a></li>
                                    <li><a href="javascript:;"><span>万象天引</span></a></li>
                                    <li><a href="javascript:;"><span>尸鬼封尽</span></a></li>
                                    <li><a href="javascript:;"><span>飞雷神之术</span></a></li>
                                    <li><a href="javascript:;"><span>多重影分身术</span></a></li>
                                    <li><a href="javascript:;"><span>飞雷神之术</span></a></li>
                                    <li><a href="javascript:;"><span>须佐之男</span></a></li>
                                    <li><a href="javascript:;"><span>外道魔像</span></a></li>
                                    <li><a href="javascript:;"><span>双蛇相杀</span></a></li>
                                </ul>
                            </li>

                            <li class="lsm-sidebar-item">
                                <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon "></i><span>二级菜单22</span><i class="my-icon lsm-sidebar-more"></i></a>
                                <ul >
                                    <li><a href="javascript:;"><span>血继限界</span></a></li>
                                    <li><a href="javascript:;"><span>秽土转生</span></a></li>
                                    <li><a href="javascript:;"><span>万象天引</span></a></li>
                                    <li><a href="javascript:;"><span>尸鬼封尽</span></a></li>
                                    <li><a href="javascript:;"><span>飞雷神之术</span></a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon icon_3"></i><span>退出系统</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="javascript:;"><span>火之国2</span></a></li>
                            <li><a href="javascript:;"><span>沙之国3</span></a></li>
                            <li><a href="javascript:;"><span>火影忍者3</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon icon-iconset0308"></i><span>购物车管理</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="javascript:;"><span>火之国4</span></a></li>
                            <li><a href="javascript:;"><span>沙之国4</span></a></li>
                            <li><a href="javascript:;"><span>火影忍者4</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon icon-chongzhi1"></i><span>支付中心</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="javascript:;"><span>火之国5</span></a></li>
                            <li><a href="javascript:;"><span>沙之国5</span></a></li>
                            <li><a href="javascript:;"><span>火影忍者5</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon icon-chongzhi"></i><span>充值中心</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="javascript:;"><span>火之国6</span></a></li>
                            <li><a href="javascript:;"><span>沙之国6</span></a></li>
                            <li><a href="javascript:;"><span>火影忍者6</span></a></li>
                        </ul>
                    </li>
                    <li class="lsm-sidebar-item">
                        <a class="parent" href="javascript:void(0);"><i class="my-icon lsm-sidebar-icon icon-chongzhi-copy"></i><span>充值中心</span><i class="my-icon lsm-sidebar-more"></i></a>
                        <ul>
                            <li><a href="javascript:;"><span>火之国7</span></a></li>
                            <li><a href="javascript:;"><span>沙之国7</span></a></li>
                            <li><a href="javascript:;"><span>火影忍者7</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>