/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-04-29 02:29:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("/includes/links.jsp", Long.valueOf(1556069375867L));
    _jspx_dependants.put("/includes/taglibs.jsp", Long.valueOf(1554972940464L));
    _jspx_dependants.put("/WEB-INF/view/top.jsp", Long.valueOf(1555318102579L));
    _jspx_dependants.put("/WEB-INF/s.tld", Long.valueOf(1554972952274L));
    _jspx_dependants.put("/WEB-INF/gwideal.tld", Long.valueOf(1554972953163L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"/>\r\n");
      out.write("<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\"/>\r\n");
      out.write("<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\"/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fset_005f2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/plugins/My97DatePicker/skin/WdatePicker.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/css/main.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/css/layout.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/css/style.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/css/reset.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/css/screen.css\">\r\n");
      out.write("<link rel=\"stylesheet\" id=\"easyuiTheme\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/ui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/ui/themes/icon.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/css/viewer.min.css\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/ui/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/js/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/js/custom.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/ui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/ui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/plugins/My97DatePicker/WdatePicker.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/js/jsencrypt.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/js/watermark.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/js/viewer-jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar base='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t//绑定tabs的右键菜单\r\n");
      out.write("\t\t$(\"#tt\").tabs({\r\n");
      out.write("\t\t\tonContextMenu : function(e, title) {\r\n");
      out.write("\t\t\t\te.preventDefault();\r\n");
      out.write("\t\t\t\tif (title != '首页') {\r\n");
      out.write("\t\t\t\t\t$('#tabsMenu').menu('show', {\r\n");
      out.write("\t\t\t\t\t\tleft : e.pageX,\r\n");
      out.write("\t\t\t\t\t\ttop : e.pageY\r\n");
      out.write("\t\t\t\t\t}).data(\"tabTitle\", title);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t//实例化menu的onClick事件\r\n");
      out.write("\t\t$(\"#tabsMenu\").menu({\r\n");
      out.write("\t\t\tonClick : function(item) {\r\n");
      out.write("\t\t\t\tCloseTab(this, item.name);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t//几个关闭事件的实现\r\n");
      out.write("\t\tfunction CloseTab(menu, type) {\r\n");
      out.write("\t\t\tvar curTabTitle = $(menu).data(\"tabTitle\");\r\n");
      out.write("\t\t\tvar tabs = $(\"#tt\");\r\n");
      out.write("\r\n");
      out.write("\t\t\tif (type === \"close\") {\r\n");
      out.write("\t\t\t\ttabs.tabs(\"close\", curTabTitle);\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tvar allTabs = tabs.tabs(\"tabs\");\r\n");
      out.write("\t\t\tvar closeTabsTitle = [];\r\n");
      out.write("\r\n");
      out.write("\t\t\t$.each(allTabs, function() {\r\n");
      out.write("\t\t\t\tvar opt = $(this).panel(\"options\");\r\n");
      out.write("\t\t\t\tif (opt.closable && opt.title != curTabTitle && type === \"Other\") {\r\n");
      out.write("\t\t\t\t\tcloseTabsTitle.push(opt.title);\r\n");
      out.write("\t\t\t\t} else if (opt.closable && type === \"All\") {\r\n");
      out.write("\t\t\t\t\tcloseTabsTitle.push(opt.title);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\tfor (var i = 0; i < closeTabsTitle.length; i++) {\r\n");
      out.write("\t\t\t\ttabs.tabs(\"close\", closeTabsTitle[i]);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t$(function () {\t\r\n");
      out.write("\t\t$(document).ready(function(){\r\n");
      out.write("\t\t  $(\".cont-ul li\").hover(function(){\r\n");
      out.write("\t\t    $(this).children(\".cont-ul li div\").toggle();\r\n");
      out.write("\t\t  });\r\n");
      out.write("\t\t  $('.side-bar_wrapper>.side-bar_item').each(function (i, e) {\r\n");
      out.write("\t            var current$ = $(e);\r\n");
      out.write("\t            var currentEvent$ = $(e).find('> p');\r\n");
      out.write("\t            var eventTarget$ = $(e).children('p');\r\n");
      out.write("\t            currentEvent$.on('click', function (event) {\r\n");
      out.write("\t                event.stopPropagation();\r\n");
      out.write("\t                if (current$.hasClass('active')) {\r\n");
      out.write("\t                    if (!isAminated(current$.find('>ul'))) {\r\n");
      out.write("\t                        current$.find('>ul').slideToggle(300);\r\n");
      out.write("\t                    }\r\n");
      out.write("\t                    return\r\n");
      out.write("\t                } else {\r\n");
      out.write("\t                    current$.siblings('.active').find('>ul').slideUp(300, function () {\r\n");
      out.write("\t                        current$.siblings('.active').removeClass('active');\r\n");
      out.write("\t                    })\r\n");
      out.write("\r\n");
      out.write("\t                    if (current$.hasClass('url')) {\r\n");
      out.write("\t                        current$.find('.active').removeClass('active');\r\n");
      out.write("\t                    }\r\n");
      out.write("\t                    current$.find('>ul').slideDown(300);\r\n");
      out.write("\t                    current$.addClass('active');\r\n");
      out.write("\r\n");
      out.write("\t                }\r\n");
      out.write("\t            })\r\n");
      out.write("\t        });\r\n");
      out.write("\t        $('.side-bar_wrapper>.side-bar_item .side-bar_item').each(function (i, e) {\r\n");
      out.write("\t            var current$ = $(e);\r\n");
      out.write("\t            var currentEvent$ = $(e).find('> p')\r\n");
      out.write("\t            currentEvent$.on('click', function () {\r\n");
      out.write("\t                if ((current$).hasClass('current')) {\r\n");
      out.write("\t                \tcurrent$.toggleClass('active');\r\n");
      out.write("\t                    $('.side-bar_wrapper').find('.current.active').removeClass('active');\r\n");
      out.write("\t                    current$.addClass('active');\r\n");
      out.write("\t                    return\r\n");
      out.write("\t                }\r\n");
      out.write("\t                var content$ = currentEvent$.siblings('ul');\r\n");
      out.write("\t                current$.toggleClass('active');\r\n");
      out.write("\t                content$.toggle();\r\n");
      out.write("\t            })\r\n");
      out.write("\t        });\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction addTabs(title, url) {\r\n");
      out.write("\t\tif ($('#tt').tabs('exists', title)) {\r\n");
      out.write("\t\t\t$('#tt').tabs('select', title);\r\n");
      out.write("\t\t\tvar tab = $('#tt').tabs('getSelected');\r\n");
      out.write("\t\t\ttab.panel('refresh', encodeURI(url));\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\t$('#tt').tabs('add', {\r\n");
      out.write("\t\t\t\ttitle : title,\r\n");
      out.write("\t\t\t\thref : encodeURI(url),\r\n");
      out.write("\t\t\t\tclosable : true,\r\n");
      out.write("\t\t\t\tloadingMessage : '',\r\n");
      out.write("\t\t\t\tborder : false,\r\n");
      out.write("\t\t\t\ttools : [ {\r\n");
      out.write("\t\t\t\t\ticonCls : 'icon-mini-refresh',\r\n");
      out.write("\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t$('#tt').tabs('select', title);\r\n");
      out.write("\t\t\t\t\t\tvar tab = $('#tt').tabs('getSelected');\r\n");
      out.write("\t\t\t\t\t\ttab.panel('refresh', url);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t} ]\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction isAminated(el) {\r\n");
      out.write("\t    return $(el).is(':animated')\r\n");
      out.write("\t}\r\n");
      out.write("\t//时间格式化\r\n");
      out.write("\t  function ChangeDateFormat(val) {\r\n");
      out.write("        var t, y, m, d, h, i, s;\r\n");
      out.write("        if(val==null){\r\n");
      out.write("      \t  return \"\";\r\n");
      out.write("        }\r\n");
      out.write("        t = new Date(val)\r\n");
      out.write("        y = t.getFullYear();\r\n");
      out.write("        m = t.getMonth() + 1;\r\n");
      out.write("        d = t.getDate();\r\n");
      out.write("        h = t.getHours();\r\n");
      out.write("        i = t.getMinutes();\r\n");
      out.write("        s = t.getSeconds();\r\n");
      out.write("        // 可根据需要在这里定义时间格式  \r\n");
      out.write("        return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);\r\n");
      out.write("    }\r\n");
      out.write("\t//时间格式化\r\n");
      out.write("\t  function ChangeDateFormat2(val) {\r\n");
      out.write("        var t, y, m, d, h, i, s;\r\n");
      out.write("        if(val==null){\r\n");
      out.write("      \t  return \"\";\r\n");
      out.write("        }\r\n");
      out.write("        t = new Date(val)\r\n");
      out.write("        y = t.getFullYear();\r\n");
      out.write("        m = t.getMonth() + 1;\r\n");
      out.write("        d = t.getDate();\r\n");
      out.write("        h = t.getHours();\r\n");
      out.write("        i = t.getMinutes();\r\n");
      out.write("        s = t.getSeconds();\r\n");
      out.write("        // 可根据需要在这里定义时间格式  \r\n");
      out.write("        return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);\r\n");
      out.write("    }\r\n");
      out.write("\t//根据身份证号码设置出生日期\r\n");
      out.write("\t  function setCsrq(sfzh,id){\r\n");
      out.write("\t  \tvar csrq = sfzh.substring(6,10)+\"-\"+ sfzh.substring(10,12)+\"-\"+ sfzh.substring(12,14);\r\n");
      out.write("\t  \t$('#'+id).datebox('setValue',csrq);\r\n");
      out.write("\t  }\r\n");
      out.write("\t  function validateIdCard(idCard) {\r\n");
      out.write("\t  \t//15位和18位身份证号码的正则表达式\r\n");
      out.write("\t  \tvar regIdCard = /^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$/;\r\n");
      out.write("\t  \t//如果通过该验证，说明身份证格式正确，但准确性还需计算\r\n");
      out.write("\t  \tif (regIdCard.test(idCard)) {\r\n");
      out.write("\t  \t\tif (idCard.length == 18) {\r\n");
      out.write("// \t  \t\t\tvar idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7,\r\n");
      out.write("// \t  \t\t\t\t\t9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里\r\n");
      out.write("// \t  \t\t\tvar idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组\r\n");
      out.write("// \t  \t\t\tvar idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和\r\n");
      out.write("// \t  \t\t\tfor (var i = 0; i < 17; i++) {\r\n");
      out.write("// \t  \t\t\t\tidCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];\r\n");
      out.write("// \t  \t\t\t}\r\n");
      out.write("// \t  \t\t\tvar idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置\r\n");
      out.write("// \t  \t\t\tvar idCardLast = idCard.substring(17);//得到最后一位身份证号码\r\n");
      out.write("// \t  \t\t\t//如果等于2，则说明校验码是10，身份证号码最后一位应该是X\r\n");
      out.write("// \t  \t\t\tif (idCardMod == 2) {\r\n");
      out.write("// \t  \t\t\t\tif (idCardLast == \"X\" || idCardLast == \"x\") {\r\n");
      out.write("// \t  \t\t\t\t\treturn true;\r\n");
      out.write("// \t  \t\t\t\t} else {\r\n");
      out.write("// \t  \t\t\t\t\treturn false;\r\n");
      out.write("// \t  \t\t\t\t}\r\n");
      out.write("// \t  \t\t\t} else {\r\n");
      out.write("// \t  \t\t\t\t//用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码\r\n");
      out.write("// \t  \t\t\t\tif (idCardLast == idCardY[idCardMod]) {\r\n");
      out.write("// \t  \t\t\t\t\treturn true;\r\n");
      out.write("// \t  \t\t\t\t} else {\r\n");
      out.write("// \t  \t\t\t\t\treturn false;\r\n");
      out.write("// \t  \t\t\t\t}\r\n");
      out.write("// \t  \t\t\t}\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t  \t\t}\r\n");
      out.write("\t  \t} else {\r\n");
      out.write("\t  \t\treturn false;\r\n");
      out.write("\t  \t}\r\n");
      out.write("\t  }\r\n");
      out.write("</script>\r\n");
      out.write("<!-- plugins -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/plugins/jquery.cookie.js\" charset=\"utf-8\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/ui/plugins/extension/jquery.extension.validatebox.js\" charset=\"utf-8\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/ui/jquery.easyui.extension.js\" charset=\"utf-8\"></script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"custom_window\"></div>\r\n");
      out.write("<div id=\"search_data_window\"></div>\r\n");
      out.write("<div id=\"child_window_1\"></div>\r\n");
      out.write("<div id=\"first_window\"></div>\r\n");
      out.write("<div id=\"second_window\"></div>\r\n");
      out.write("<div id=\"third_window\"></div>\r\n");
      out.write("<div id=\"four_window\"></div>");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" style=\"text-align: left;\" id=\"content\">\r\n");
      out.write("\t<div data-options=\"region:'north'\" style=\"height: 84px;\">\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\tfunction updatePwd(){\r\n");
      out.write("\t\t\tvar win=creatWin('修改-用户密码',450,235,'icon-add','/user/editPwd');\r\n");
      out.write("\t\t\twin.window('open');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction exitSystem(){\r\n");
      out.write("\t\t\t$.messager.confirm('系统提示', '确认退出?', function(r){\r\n");
      out.write("\t\t\t\tif (r){\r\n");
      out.write("\t\t\t\t\tlocation.href='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/logout.do';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction winFullScreen(){\r\n");
      out.write("\t\t\tif($.support.fullscreen){\r\n");
      out.write("\t\t\t\t$('#content').fullScreen();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction helpOnline(){\r\n");
      out.write("\t\t\twindow.open(\"./resource/onlinehelp/index.html\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"header_new\">\r\n");
      out.write("        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/images/logo.png\" alt=\"\" class=\"logo\"/>\r\n");
      out.write("        <div class=\"user-control_wrapper\">\r\n");
      out.write("        \t<div class=\"next user-control_item\" style=\"cursor: default;\">\r\n");
      out.write("               \t 待办任务\r\n");
      out.write("                <span class=\"count\">\r\n");
      out.write("                \t<a href=\"javascript:addTabs('未读信息列表','");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/index/listUnReadMsg');\" style=\"color:#f59519;\">\r\n");
      out.write("\t                    <span class=\"text\" id=\"unReadTaskSpan\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${unReadMsgCount}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </span>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div style=\"cursor:default\" class=\"user-control_item\">欢迎您：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentUser.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</div>\r\n");
      out.write("            <div class=\"user-control_item\">\r\n");
      out.write("            \t<a href=\"javascript:void(0);\" onclick=\"updatePwd()\" style=\"color: #fff;\">修改密码</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"user-control_item\">\r\n");
      out.write("            \t<a href=\"javascript:void(0);\" onclick=\"exitSystem()\" style=\"color: #fff;\">退出系统</a>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${base}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resource/images/head-right-bg.png\" alt=\"\" class=\"head-right_bg\"/>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'west',split:true,title:'系统菜单',minWidth:230,maxWidth:230\">\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/view/left.jsp", out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'center'\">\r\n");
      out.write("\t\t<div id=\"tt\" class=\"easyui-tabs\" data-options=\"tools:'#tab-tools',fit:true,border:false\" style=\"position: absolute; overflow-y: auto;\">\r\n");
      out.write("\t\t\t<div id=\"index_tabs\" title=\"首页\" style=\"overflow:hidden;\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- tabs上的右键菜单 -->\r\n");
      out.write("\t\t\t<div id=\"tabsMenu\" class=\"easyui-menu\" style=\"width: 120px;\">\r\n");
      out.write("\t\t\t\t<div name=\"close\">关闭</div>\r\n");
      out.write("\t\t\t\t<div name=\"Other\">关闭其他</div>\r\n");
      out.write("\t\t\t\t<div name=\"All\">关闭所有</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'south'\" style=\"height: 20px; text-align: center; line-height: 18px;font-size: 14px;\">技术支持：上海长城电子信息网络有限公司</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /includes/taglibs.jsp(10,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath==\"/\"}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_c_005fset_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /includes/taglibs.jsp(11,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("base");
    // /includes/taglibs.jsp(11,1) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue("");
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /includes/taglibs.jsp(13,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath!=\"/\"}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_c_005fset_005f1(_jspx_th_c_005fif_005f1, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /includes/taglibs.jsp(14,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("base");
    // /includes/taglibs.jsp(14,1) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f2.setParent(null);
    // /includes/taglibs.jsp(16,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("title");
    // /includes/taglibs.jsp(16,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue("上海市水务信息中心-处级干部因私 出国（境）管理系统项目");
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
    return false;
  }
}
