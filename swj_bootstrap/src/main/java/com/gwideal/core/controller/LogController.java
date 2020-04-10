package com.gwideal.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;

/**
 * 获取日志文件
 * @author li_chong
 *
 */
@SuppressWarnings("serial")
@Controller
public class LogController extends BaseController{
	
	private static final ResourceBundle res=ResourceBundle.getBundle("upload");
	
	public static String getLogPath(){
		return res.getString("log");
	}
	@RequestMapping("/getLogFile")
	public String getLogFile(String f,HttpServletResponse response) throws IOException {
		if (StringUtil.isEmpty(f)) {
			return null;
		}
		ServletOutputStream out = null;  
        FileInputStream ips = null;  
        try {  
			File file = new File(getLogPath()+File.separator+f);
            ips = new FileInputStream(file);  
            response.setContentType("APPLICATION/OCTET-STREAM");
			response.setContentLength(ips.available());
			response.setHeader("Content-Disposition","attachment; filename=\""+getFileName(f)+"\"");
            out = response.getOutputStream();  
            //读取文件流  
            int len = 0;  
            byte[] buffer = new byte[1024 * 10];  
            while ((len = ips.read(buffer)) != -1){  
                out.write(buffer,0,len);  
            }  
            out.flush();  
        }catch (Exception e){  
            e.printStackTrace();  
        }finally {  
            out.close();  
            ips.close();  
        }  
		return null;
	}
	
}
