<%@ page language="java" pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="${base}/resource/plugins/My97DatePicker/skin/WdatePicker.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/css/main.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/css/layout.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/css/style.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/css/reset.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/css/screen.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/street/css/layout.css">
<link rel="stylesheet" id="easyuiTheme" type="text/css" href="${base}/resource/ui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/ui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/css/viewer.min.css">
<script type="text/javascript" src="${base}/resource/ui/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resource/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resource/js/custom.js"></script>
<script type="text/javascript" src="${base}/resource/ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/resource/ui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${base}/resource/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resource/js/jsencrypt.min.js"></script>
<script type="text/javascript" src="${base}/resource/js/watermark.js"></script>
<script type="text/javascript" src="${base}/resource/js/viewer-jquery.min.js"></script>
<script type="text/javascript">
	var base='${base}';
	$(function() {
		//绑定tabs的右键菜单
		$("#tt").tabs({
			onContextMenu : function(e, title) {
				e.preventDefault();
				if (title != '首页') {
					$('#tabsMenu').menu('show', {
						left : e.pageX,
						top : e.pageY
					}).data("tabTitle", title);
				}
			}
		});

		//实例化menu的onClick事件
		$("#tabsMenu").menu({
			onClick : function(item) {
				CloseTab(this, item.name);
			}
		});

		//几个关闭事件的实现
		function CloseTab(menu, type) {
			var curTabTitle = $(menu).data("tabTitle");
			var tabs = $("#tt");

			if (type === "close") {
				tabs.tabs("close", curTabTitle);
				return;
			}

			var allTabs = tabs.tabs("tabs");
			var closeTabsTitle = [];

			$.each(allTabs, function() {
				var opt = $(this).panel("options");
				if (opt.closable && opt.title != curTabTitle && type === "Other") {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === "All") {
					closeTabsTitle.push(opt.title);
				}
			});

			for (var i = 0; i < closeTabsTitle.length; i++) {
				tabs.tabs("close", closeTabsTitle[i]);
			}
		}
		
	});
	$(function () {	
		$(document).ready(function(){
		  $(".cont-ul li").hover(function(){
		    $(this).children(".cont-ul li div").toggle();
		  });
		  $('.side-bar_wrapper>.side-bar_item').each(function (i, e) {
	            var current$ = $(e);
	            var currentEvent$ = $(e).find('> p');
	            var eventTarget$ = $(e).children('p');
	            currentEvent$.on('click', function (event) {
	                event.stopPropagation();
	                if (current$.hasClass('active')) {
	                    if (!isAminated(current$.find('>ul'))) {
	                        current$.find('>ul').slideToggle(300);
	                    }
	                    return
	                } else {
	                    current$.siblings('.active').find('>ul').slideUp(300, function () {
	                        current$.siblings('.active').removeClass('active');
	                    })

	                    if (current$.hasClass('url')) {
	                        current$.find('.active').removeClass('active');
	                    }
	                    current$.find('>ul').slideDown(300);
	                    current$.addClass('active');

	                }
	            })
	        });
	        $('.side-bar_wrapper>.side-bar_item .side-bar_item').each(function (i, e) {
	            var current$ = $(e);
	            var currentEvent$ = $(e).find('> p')
	            currentEvent$.on('click', function () {
	                if ((current$).hasClass('current')) {
	                	current$.toggleClass('active');
	                    $('.side-bar_wrapper').find('.current.active').removeClass('active');
	                    current$.addClass('active');
	                    return
	                }
	                var content$ = currentEvent$.siblings('ul');
	                current$.toggleClass('active');
	                content$.toggle();
	            })
	        });
		});
	});
	function addTabs(title, url) {
		if ($('#tt').tabs('exists', title)) {
			$('#tt').tabs('select', title);
			var tab = $('#tt').tabs('getSelected');
			tab.panel('refresh', encodeURI(url));
		} else {
			$('#tt').tabs('add', {
				title : title,
				href : encodeURI(url),
				closable : true,
				loadingMessage : '',
				border : false,
				tools : [ {
					iconCls : 'icon-mini-refresh',
					handler : function() {
						$('#tt').tabs('select', title);
						var tab = $('#tt').tabs('getSelected');
						tab.panel('refresh', url);
					}
				} ]
			});
		}
	}
	
	function isAminated(el) {
	    return $(el).is(':animated')
	}
	//时间格式化
	  function ChangeDateFormat(val) {
        var t, y, m, d, h, i, s;
        if(val==null){
      	  return "";
        }
        t = new Date(val)
        y = t.getFullYear();
        m = t.getMonth() + 1;
        d = t.getDate();
        h = t.getHours();
        i = t.getMinutes();
        s = t.getSeconds();
        // 可根据需要在这里定义时间格式  
        return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
    }
	//时间格式化
	  function ChangeDateFormat2(val) {
        var t, y, m, d, h, i, s;
        if(val==null){
      	  return "";
        }
        t = new Date(val)
        y = t.getFullYear();
        m = t.getMonth() + 1;
        d = t.getDate();
        h = t.getHours();
        i = t.getMinutes();
        s = t.getSeconds();
        // 可根据需要在这里定义时间格式  
        return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
    }
	
	//时间格式化
	  function dateFormatYYYYMMDDHHmm(val) {
        var t, y, m, d, h, i, s;
        if(val==null){
      	  return "";
        }
        t = new Date(val)
        y = t.getFullYear();
        m = t.getMonth() + 1;
        d = t.getDate();
        h = t.getHours();
        i = t.getMinutes();
        // 可根据需要在这里定义时间格式  
        return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i);
    }
	
	//根据身份证号码设置出生日期
	  function setCsrq(sfzh,id){
	  	var csrq = sfzh.substring(6,10)+"-"+ sfzh.substring(10,12)+"-"+ sfzh.substring(12,14);
	  	$('#'+id).datebox('setValue',csrq);
	  }
	  function validateIdCard(idCard) {
	  	//15位和18位身份证号码的正则表达式
	  	var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	  	//如果通过该验证，说明身份证格式正确，但准确性还需计算
	  	if (regIdCard.test(idCard)) {
	  		if (idCard.length == 18) {
// 	  			var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7,
// 	  					9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里
// 	  			var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
// 	  			var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和
// 	  			for (var i = 0; i < 17; i++) {
// 	  				idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
// 	  			}
// 	  			var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置
// 	  			var idCardLast = idCard.substring(17);//得到最后一位身份证号码
// 	  			//如果等于2，则说明校验码是10，身份证号码最后一位应该是X
// 	  			if (idCardMod == 2) {
// 	  				if (idCardLast == "X" || idCardLast == "x") {
// 	  					return true;
// 	  				} else {
// 	  					return false;
// 	  				}
// 	  			} else {
// 	  				//用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
// 	  				if (idCardLast == idCardY[idCardMod]) {
// 	  					return true;
// 	  				} else {
// 	  					return false;
// 	  				}
// 	  			}
				return true;
	  		}
	  	} else {
	  		return false;
	  	}
	  }
	  function showPublicBusiness(val){
		  if(val!=null&&val!=''){
			  if(val.indexOf("草稿")>-1){
				  return '草稿';
			  }
		  }
		  return val;
	  }
	  function selectCertificateOwner(businessType){
			var win=creatFreeWindow("third_window",'添加-用户信息',830,450,'icon-add',"/user/refCertificateOwnerList?businessType="+businessType);
		    win.window('open');
		}
	  function printQrCode(id){
			 var win=creatFreeWindow("four_window",'打印-二维码预览',420,280,'icon-search','/certificateInfo/printQrcode/'+id);
			 win.window('open');
		}
</script>
<!-- plugins -->
<script type="text/javascript" src="${base}/resource/plugins/jquery.cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/resource/ui/plugins/extension/jquery.extension.validatebox.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/resource/ui/jquery.easyui.extension.js" charset="utf-8"></script>

<div id="custom_window"></div>
<div id="search_data_window"></div>
<div id="child_window_1"></div>
<div id="first_window"></div>
<div id="second_window"></div>
<div id="third_window"></div>
<div id="four_window"></div>