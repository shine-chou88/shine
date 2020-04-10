package com.gwideal.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.CertificateOperateLog;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;
import com.gwideal.swj.certificate.manager.CertificateOperateLogMng;

@Aspect
@Component(value = "archivesLogAspect")
public class ArchivesLogAspect {
	
	private static final Logger log=LoggerFactory.getLogger(ArchivesLogAspect.class);  

    private User user = null;
    private HttpServletRequest request = null;
    
    @Autowired
    private CertificateOperateLogMng certificateOperateLogMng;
    @Autowired
    private CertificateInfoMng certificateInfoMng;
    
   
    @Pointcut("execution(public * com.gwideal.swj.certificate.controller.*.*(..)) && @annotation(com.gwideal.common.aspect.ArchivesLog)")
    public void log(){
    } 
    
    @AfterReturning(value = "log()", returning = "retVal")
    public void log(JoinPoint joinPoint, Object retVal) {
    	request = getHttpServletRequest(); 
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  
        Method[] methods = targetClass.getMethods();
        String operationType="";
        String operationName = "";
        String certificateId="";
        Map<String, String> param  = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        certificateId=param.get("id");
        user=(User) request.getSession().getAttribute("currentUser");
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();  
                if (clazzs!=null&&clazzs.length == arguments.length&&method.getAnnotation(ArchivesLog.class)!=null) { 
                	operationType = method.getAnnotation(ArchivesLog.class).operationType();
                    operationName = method.getAnnotation(ArchivesLog.class).operationName();
                    break;  
                }  
            }  
        }
        if(!StringUtil.isEmpty(certificateId)){
        	CertificateInfo info = certificateInfoMng.findById(certificateId);
        	if(info!=null){
        		CertificateOperateLog operateLog=new CertificateOperateLog(operationType,operationName,user,new Date(),info);
            	certificateOperateLogMng.save(operateLog);
        	}
        	
        }
    }
   
    /**
     * 
     * 获取request
     * @return
     */
    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;  
        HttpServletRequest request = sra.getRequest();
        return request;
    }
  
   

    
    
}
