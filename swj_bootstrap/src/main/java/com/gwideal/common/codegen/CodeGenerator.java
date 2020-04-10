/*Copyright (c) 2008 GWIdeal. All rights reserved.
 *@Author:Kang_Shuigen
 *@Since:2009-4-28
 */
package com.gwideal.common.codegen;

/**
 * 代码生成使用范例
 * 
 * 
 * 在使用过程中如有疑问或建议请与我联系 不足之处敬请见谅
 * 
 * @author kang_shuigen
 * 
 */
public class CodeGenerator {

	/**
	 * 在entity类中不需要生成的数据表字段
	 * 
	 * 
	 * 因为在它的父类中已经存在该属性
	 */
	@SuppressWarnings("unused")
	private static final String[] filterPropertys = new String[] { "pid",
			"pflag", "pupdater", "pupdatetime" };

	private static final String model = "com.gwideal.elog.personparticular.correct.entity.PopuCorrectJudicialMonthReport";

	private static final String manage = "gwideal.grid.service.GridCaseManager";

	private static final String action = "gwideal.grid.web.action.GridCaseAction";

	private static final String table = "popu_community_correction_judicial_month_report";

	/**
	 * 在表单编辑页面中需要隐藏的字段
	 */
	private static final String[] hiddenPropertys = new String[] {"pid",
		"pflag", "creator", "createTime","pupdater","op_date"};

	public static void main(String[] args) throws Exception {

		 EntityGen.gen(model,table,hiddenPropertys,"mysql");

		 //ManagerGen.gen(manage,model);

		 //ControllerGen.gen(action,manage,model);

		//ListPageGen.gen(model, table, "/company", filterPropertys);

		//EditPageGen.gen(model, table, "/company", filterPropertys,
		//		hiddenPropertys);

		//ViewPageGen.gen(model, table, "/company", filterPropertys,
			//	hiddenPropertys);
//
		// ExtPanelGen.gen(entity, table, "pm", filterPropertys,
		// hiddenPropertys);
	}

}
