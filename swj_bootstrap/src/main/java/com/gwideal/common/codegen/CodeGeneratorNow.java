package com.gwideal.common.codegen;

/**
 * 
 * 代码生成器入口
 * 目前支持mysql、oracle、sqlserver数据库，数据库表需要加注释、每个字段需要加注释（需要过滤的字段不需要加）
 * 需要在generator.properties修改生成路径信息
 * @author zhou_liang
 */
public class CodeGeneratorNow {
	
	/**
	 * 需要过滤的字段（不需要生成到实体类，父类里面已存在）
	 */
	private static final String[] filterPropertys = new String[]{"pid","pflag", "creator", "createtime","pupdater","pupdatetime"};
	
	public static void main(String[] args) {
		try {
			CodeGeneratorUtils.generatorCode("T_PUBLIC_BUSINESS_LOG",filterPropertys,"oracle");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
