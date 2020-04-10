/*Copyright (c) 2008 GWIdeal. All rights reserved.
 *@Author:Kang_Shuigen
 *@Since:2009-4-29
 */
package com.gwideal.common.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ControllerGen {

	/**
	 * Action类生成器
	 * 
	 * @param actionClassName
	 * @param managerClassName
	 * @param entityClassName
	 * @throws Exception
	 */
	public static void gen(String actionClassName, String managerClassName,
			String entityClassName) throws Exception {

		String[] type = actionClassName.split("\\.");
		if (type.length < 2) {
			throw new Exception("param 'className' is a class's full name!");
		}

		String simpleClassName = type[type.length - 1];

		String filePath = System.getProperty("user.dir") + "\\src\\"
				+ actionClassName.replaceAll("\\.", "\\\\") + ".java";
		File file = new File(filePath);
		// 不用手工创建包名，会自动生成一个包名  --qin 20100720 
		file.getParentFile().mkdirs();
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(
				file), "utf-8");
		fw.write(packageCode(actionClassName));
		fw
				.write(importsCode(actionClassName, managerClassName,
						entityClassName));
		fw.write(classCode(simpleClassName));
		fw.flush();
		fw.close();
	}

	private static String packageCode(String actionClassName) {

		if (actionClassName.indexOf(".") == -1) {
			return "package " + actionClassName + ";\n\n";
		}
		return "package "
				+ actionClassName
						.substring(0, actionClassName.lastIndexOf("."))
				+ ";\n\n";
	}

	private static String importsCode(String actionClassName,
			String managerClassName, String entityClassName) {
		StringBuffer code = new StringBuffer();
		code.append("import gwideal.core.model.Entity;\n");
		code.append("import gwideal.core.service.GenericEntityManager;\n");
		code.append("import gwideal.core.web.action.BaseAction;\n");
		code.append("import " + entityClassName + ";\n");
		code.append("import " + managerClassName + ";\n");
		code.append("import gwideal.widgets.table.TableModelParameters;\n\n");
		code.append("import com.opensymphony.xwork2.Preparable;\n\n");
		return code.toString();
	}

	private static String classCode(String actionSimpleClassName) {

		String entitySimpleClass = actionSimpleClassName.replaceAll("Action",
				"");
		String managerSimpleClass = actionSimpleClassName.replaceAll("Action",
				"Manager");

		StringBuffer code = new StringBuffer();
		code.append("public class " + actionSimpleClassName
				+ " extends BaseAction implements Preparable{\n\n");
		code.append("	private static final long serialVersionUID = 1L;\n\n");
		code.append("	private String method;\n\n");
		code.append("	private " + entitySimpleClass + " "
				+ EntityGenUtil.toLowerIndexChar(entitySimpleClass) + " = new "
				+ entitySimpleClass + "();\n\n");
		code.append("	private " + managerSimpleClass + " "
				+ EntityGenUtil.toLowerIndexChar(managerSimpleClass) + ";\n\n");

		code.append("	/**\n");
		code.append("	 * 初始化信息\n");
		code.append("	 */\n");
		code.append("	public void prepare() throws Exception{\n");
		code.append("		// TODO Auto-generated method stub\n");
		code.append("	}\n");

		code.append("	public Entity getEntity() {\n");
		code.append("		return "
				+ EntityGenUtil.toLowerIndexChar(entitySimpleClass) + ";\n");
		code.append("	}\n");

		code.append("	public GenericEntityManager getManager() {\n");
		code.append("		return "
				+ EntityGenUtil.toLowerIndexChar(managerSimpleClass) + ";\n");
		code.append("	}\n");

		code.append("	public void setEntity(Entity entity) {\n");
		code.append("		this."
				+ EntityGenUtil.toLowerIndexChar(entitySimpleClass) + " = ("
				+ entitySimpleClass + ")entity;\n");
		code.append("	}\n");
		code.append("\n");

		code.append("	public String getMethod() {\n");
		code.append("		return method;\n");
		code.append("	}\n");

		code.append("	public void setMethod(String method) {\n");
		code.append("		this.method = method;\n");
		code.append("	}\n");
		code.append("\n");

		code.append("	public " + entitySimpleClass + " get" + entitySimpleClass
				+ "() {\n");
		code.append("		return "
				+ EntityGenUtil.toLowerIndexChar(entitySimpleClass) + ";\n");
		code.append("	}\n");
		code.append("\n");

		code.append("	public void set" + entitySimpleClass + "("
				+ entitySimpleClass + " "
				+ EntityGenUtil.toLowerIndexChar(entitySimpleClass) + ") {\n");
		code.append("		this."
				+ EntityGenUtil.toLowerIndexChar(entitySimpleClass) + " = "
				+ EntityGenUtil.toLowerIndexChar(entitySimpleClass) + ";\n");
		code.append("	}\n");
		code.append("\n");

		code.append("	public " + managerSimpleClass + " get"
				+ managerSimpleClass + "() {\n");
		code.append("		return "
				+ EntityGenUtil.toLowerIndexChar(managerSimpleClass) + ";\n");
		code.append("	}\n");
		code.append("\n");

		code.append("	public void set" + managerSimpleClass + "("
				+ managerSimpleClass + " "
				+ EntityGenUtil.toLowerIndexChar(managerSimpleClass) + ") {\n");
		code.append("		this."
				+ EntityGenUtil.toLowerIndexChar(managerSimpleClass) + " = "
				+ EntityGenUtil.toLowerIndexChar(managerSimpleClass) + ";\n");
		code.append("	}\n");
		code.append("\n");

		code.append("}\n");
		return code.toString();
	}

}
