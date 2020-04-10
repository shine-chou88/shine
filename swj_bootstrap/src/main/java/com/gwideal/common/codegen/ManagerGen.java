/*Copyright (c) 2008 GWIdeal. All rights reserved.
 *@Author:Kang_Shuigen
 *@Since:2009-4-29
*/
package com.gwideal.common.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ManagerGen {
	
	/**
	 * manager类生成器
	 * @param managerClassName
	 * @param entityclassName
	 * @throws Exception
	 */
	public static void gen(String managerClassName,String entityclassName) throws Exception{
		
		String[] type = managerClassName.split("\\.");
		if(type.length<2){
			throw new Exception("param 'className' is a class's full name!");
		}
		
		String simpleClassName = type[type.length-1];
		
		String filePath=System.getProperty("user.dir")+"\\src\\"+managerClassName.replaceAll("\\.", "\\\\")+".java";
		File file = new File(filePath);
		// 不用手工创建包名，会自动生成一个包名  --qin 20100720 
		file.getParentFile().mkdirs();
		OutputStreamWriter  fw = new OutputStreamWriter (new FileOutputStream(file),"utf-8");
		fw.write(packageCode(managerClassName));
		fw.write(importsCode(entityclassName));
		fw.write(classCode(simpleClassName));
		fw.flush();
		fw.close();
	}
	
	
	private static String packageCode(String managerClassName){
		
		if(managerClassName.indexOf(".")==-1){
			return "package "+managerClassName+";\n\n";
		}
		return "package "+managerClassName.substring(0, managerClassName.lastIndexOf("."))+";\n\n";
	}
	
	private static String importsCode(String entityClassName){
		StringBuffer code = new StringBuffer();
		code.append("import gwideal.core.service.BaseEntityManager;\n");
		code.append("import ").append(entityClassName.replaceAll("Manager", "")).append(";\n\n");
		return code.toString();
	}
	
	private static String classCode(String simpleClassName){
		StringBuffer code = new StringBuffer();
		code.append("public class "+simpleClassName+" extends BaseEntityManager<"+simpleClassName.replaceAll("Manager", "")+">{\n\n");
		code.append("}\n");
		return code.toString();
	}
	
}
