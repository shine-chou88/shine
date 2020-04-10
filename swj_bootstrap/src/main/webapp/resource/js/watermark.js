function watermark(settings) {
    //默认设置
    var defaultSettings={
    	watermark_id:"text",
        watermark_txt:"text",
        watermark_x:100,//水印起始位置x轴坐标
        watermark_y:100,//水印起始位置Y轴坐标
        watermark_rows:2,//水印行数
        watermark_cols:3,//水印列数
        watermark_x_space:100,//水印x轴间隔
        watermark_y_space:230,//水印y轴间隔
        watermark_color:'#000000',//水印字体颜色
        watermark_alpha:0.2,//水印透明度
        watermark_fontsize:'18px',//水印字体大小
        watermark_font:'微软雅黑',//水印字体
        watermark_width:350,//水印宽度
        watermark_height:105,//水印长度
        watermark_angle:30//水印倾斜度数
    };
    //采用配置项替换默认值，作用类似jquery.extend
    if(arguments.length===1&&typeof arguments[0] ==="object" )
    {
        var src=arguments[0]||{};
        for(key in src)
        {
            if(src[key]&&defaultSettings[key]&&src[key]===defaultSettings[key])
                continue;
            else if(src[key])
                defaultSettings[key]=src[key];
        }
    }
    if(defaultSettings.watermark_id!=undefined&&defaultSettings.watermark_id!=null&&document.getElementById(defaultSettings.watermark_id)!=null){
	    var oTemp = document.createDocumentFragment();
	    //获取div最大宽度
	    var page_width = document.getElementById(defaultSettings.watermark_id).offsetWidth;
	    //获取div最大长度
	    var page_height = document.getElementById(defaultSettings.watermark_id).offsetHeight+400;
	    if(defaultSettings.watermark_id.indexOf("waterDetailShow")>-1){
	    	defaultSettings.watermark_y_space=150;
	    	defaultSettings.watermark_height=130;
	    }
	    if(defaultSettings.watermark_id.indexOf("waterDetailShow_")>-1){
	    	defaultSettings.watermark_y_space=140;
	    	defaultSettings.watermark_height=125;
	    }
	    if(defaultSettings.watermark_id.indexOf("waterDetailShow_1")>-1){
	    	defaultSettings.watermark_y_space=130;
	    	defaultSettings.watermark_height=105;
	    }
	    //如果将水印列数设置为0，或水印列数设置过大，超过页面最大宽度，则重新计算水印列数和水印x轴间隔
	    if (defaultSettings.watermark_cols == 0 ||
	 　　　　(parseInt(defaultSettings.watermark_x 
	　　　　+ defaultSettings.watermark_width *defaultSettings.watermark_cols 
	　　　　+ defaultSettings.watermark_x_space * (defaultSettings.watermark_cols - 1)) 
	　　　　> page_width)) {
	        defaultSettings.watermark_cols = 
	　　　　　　parseInt((page_width
	　　　　　　　　　　-defaultSettings.watermark_x
	　　　　　　　　　　+defaultSettings.watermark_x_space) 
	　　　　　　　　　　/ (defaultSettings.watermark_width 
	　　　　　　　　　　+ defaultSettings.watermark_x_space));
	        defaultSettings.watermark_x_space = 
	　　　　　　parseInt((page_width 
	　　　　　　　　　　- defaultSettings.watermark_x 
	　　　　　　　　　　- defaultSettings.watermark_width 
	　　　　　　　　　　* defaultSettings.watermark_cols) 
	　　　　　　　　　　/ (defaultSettings.watermark_cols - 1));
	    }
	    //如果将水印行数设置为0，或水印行数设置过大，超过页面最大长度，则重新计算水印行数和水印y轴间隔
	    if (defaultSettings.watermark_rows == 0 ||
	 　　　　(parseInt(defaultSettings.watermark_y 
	　　　　+ defaultSettings.watermark_height * defaultSettings.watermark_rows 
	　　　　+ defaultSettings.watermark_y_space * (defaultSettings.watermark_rows - 1)) 
	　　　　> page_height)) {
	        defaultSettings.watermark_rows = 
	　　　　　　parseInt((defaultSettings.watermark_y_space 
	　　　　　　　　　　　+ page_height - defaultSettings.watermark_y) 
	　　　　　　　　　　　/ (defaultSettings.watermark_height + defaultSettings.watermark_y_space));
	        defaultSettings.watermark_y_space = 
	　　　　　　parseInt((page_height 
	　　　　　　　　　　- defaultSettings.watermark_y 
	　　　　　　　　　　- defaultSettings.watermark_height 
	　　　　　　　　　　* defaultSettings.watermark_rows) 
	　　　　　　　　　/ (defaultSettings.watermark_rows - 1));
	    }
	    var x;
	    var y;
	    for (var i = 0; i < defaultSettings.watermark_rows; i++) {
	        y = defaultSettings.watermark_y + (defaultSettings.watermark_y_space + defaultSettings.watermark_height) * i;
	        for (var j = 0; j < defaultSettings.watermark_cols; j++) {
	            x = defaultSettings.watermark_x + (defaultSettings.watermark_width + defaultSettings.watermark_x_space) * j;
	
	            var mask_div = document.createElement('div');
	            mask_div.id = 'mask_div' + i + j;
	            mask_div.appendChild(document.createTextNode(defaultSettings.watermark_txt));
	            //设置水印div倾斜显示
	            mask_div.style.webkitTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
	            mask_div.style.MozTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
	            mask_div.style.msTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
	            mask_div.style.OTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
	            mask_div.style.transform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
	            mask_div.style.visibility = "";
	            mask_div.style.position = "absolute";
	            mask_div.style.left = x + 'px';
	            mask_div.style.top = y + 'px';
	            mask_div.style.overflow = "hidden";
	            mask_div.style.pointerEvents='none';
	            mask_div.style.zIndex = "9999";
	            //mask_div.style.border="solid #eee 1px";
	            mask_div.style.opacity = defaultSettings.watermark_alpha;
	            mask_div.style.fontSize = defaultSettings.watermark_fontsize;
	            mask_div.style.fontFamily = defaultSettings.watermark_font;
	            mask_div.style.color = defaultSettings.watermark_color;
	            mask_div.style.textAlign = "center";
	            mask_div.style.width = defaultSettings.watermark_width + 'px';
	            mask_div.style.height = defaultSettings.watermark_height + 'px';
	            mask_div.style.display = "block";
	            oTemp.appendChild(mask_div);
	        };
	    };
	    document.getElementById(defaultSettings.watermark_id).appendChild(oTemp);
    }
}