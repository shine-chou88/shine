
//对Date的扩展，将 Date 转化为指定格式的String   
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
//例子：   
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  

/**
 * yyyy-MM-dd HH:mm
 * @param {} date
 * @return {}
 * 以下两个方法必须同时使用，如：data-option='formatter:nyrsfFormatter,parser:nyrsfParser'
 */
function nyrsfFormatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    var h = date.getHours();  
    var min = date.getMinutes();
    var str = y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(min<10?('0'+min):min);  
    return str;  
}  
function nyrsfParser(s){  
	if (!s) return new Date();  
    var y = s.substring(0,4);  
    var m =s.substring(5,7);  
    var d = s.substring(8,10);  
    var h = s.substring(11,13);  
    var min = s.substring(14,16);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min)){  
        return new Date(y,m-1,d,h,min);  
    } else {  
        var time1 = new Date().format("yyyy-MM-dd HH:mm");
        return new Date(time1);  
    }  
}   
/**
 * yyyy年MM月dd日
 * @param {} date
 * @return {}
 */
function nyrCNFormatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    var str = y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日';  
    return str;  
}  
function nyrCNParser(s){  
	if (!s) return new Date();  
    var y = s.substring(0,4);  
    var m =s.substring(5,7);  
    var d = s.substring(8,10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min)){  
        return new Date(y,m-1,d);  
    } else {  
        var time1 = new Date().format("yyyy年MM月dd日");
        return new Date(time1);  
    }  
}   

/**
 * 创建窗口
 * @param title		窗口标题
 * @param width		宽度
 * @param height	高度
 * @param icon		图标
 * @param href		地址
 * @returns
 */
function creatWin(title,width,height,icon,href){
	var win=$("#custom_window").window({
        title: title,
	    width: width,
	    height: height,
	    shadow: true,
	    modal: true,
	    iconCls: icon,
	    closed: true,
	    minimizable: false,
	    maximizable: false,
	    collapsible: false,
	    resizable:false,
	    draggable:true,
	    cache : false,
	    loadingMessage:'正在加载，请稍等...',
	    href:base+href,
	    onBeforeClose: function() {$('#custom_window').empty();}
    });
    return win;
}

function creatStreetWin(title,width,height,icon,href){
	var win=$("#custom_window").window({
        title: title,
	    width: width,
	    height: height,
	    top:$(document).scrollTop()+150,  
	    shadow: true,
	    modal: true,
	    iconCls: icon,
	    closed: true,
	    minimizable: false,
	    maximizable: false,
	    collapsible: false,
	    resizable:false,
	    draggable:true,
	    cache : false,
	    loadingMessage:'正在加载，请稍等...',
	    href:href,
	    onBeforeClose: function() {$('#custom_window').empty();}
    });
    return win;
}

function closeWindow(){
	$('#custom_window').empty();
	$("#custom_window").window('close');
}

function creatFirstWin(title,width,height,icon,href){
	var win=$("#first_window").window({
        title: title,
	    width: width,
	    height: height,
	    shadow: true,
	    modal: true,
	    iconCls: icon,
	    closed: true,
	    minimizable: false,
	    maximizable: false,
	    collapsible: false,
	    resizable:false,
	    draggable:true,
	    cache : false,
	    loadingMessage:'正在加载，请稍等...',
	    href:base+href,
	    onBeforeClose: function() {$('#first_window').empty();}
	    
    });
    return win;
}

function closeFirstWindow(){
	$('#first_window').empty();
	$("#first_window").window('close');
}
//填写表单时可能需要弹出的表单
function creatSearchDataWin(title,width,height,icon,href){
	var win=$("#search_data_window").window({
        title: title,
	    width: width,
	    height: height,
	    shadow: true,
	    modal: true,
	    iconCls: icon,
	    closed: true,
	    minimizable: false,
	    maximizable: false,
	    collapsible: false,
	    resizable:false,
	    draggable:true,
	    cache : false,
	    loadingMessage:'正在加载，请稍等...',
	    href:base+href,
	    onBeforeClose: function() {$('#search_data_window').empty();}
    });
    return win;
}

function closeSearchDateWindow(){
	$('#search_data_window').empty();
	$("#search_data_window").window('close');
}




//自由创建窗口
function creatFreeWindow(id,title,width,height,icon,href){
	var win=$("#"+id).window({
        title: title,
	    width: width,
	    height: height,
	    shadow: true,
	    modal: true,
	    iconCls: icon,
	    closed: true,
	    minimizable: false,
	    maximizable: false,
	    collapsible: false,
	    resizable:false,
	    draggable:true,
	    cache : false,
	    loadingMessage:'正在加载，请稍等...',
	    href:base+href,
	    onBeforeClose: function() {$("#"+id).empty();}
    });
    return win;
}

function closeFreeWindow(id){
	$("#"+id).empty();
	$("#"+id).window('close');
}
//时间格式化
function changeDateFormat(val) {
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