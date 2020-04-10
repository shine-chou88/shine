/*Copyright (c) 2008 GWIdeal. All rights reserved.
 *@Author:Kang_Shuigen
 *@Since:2009-4-28
 */
package com.gwideal.common.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import com.gwideal.common.util.DBHelper;

public class EntityGen {

	/**
	 * Entity类生成器
	 * 
	 * @param entityClassName
	 *            entity类的全称
	 * @param tableName
	 *            对应的数据库表名
	 * @param filterProperty
	 *            过滤字段、即不需要生成的字段
	 * @param dataBaseType 数据库类型 oracle、sqlserver、mysql
	 * @throws Exception
	 */
	public static void gen(String entityClassName, String tableName,
			String[] filterProperty,String dataBaseType) throws Exception {

		StringBuffer code = new StringBuffer();
		code.append(EntityGenUtil.packageCode(entityClassName)).append(";\n\n");
		code.append("import com.gwideal.common.entity.GenericEntityNew;\n\n");
		List<ColumnBean> list = getColumns(tableName, filterProperty,dataBaseType);

		Set<String> set = EntityGenUtil.importsCode(list);
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			code.append(iter.next());
		}
		code.append("\n");
		code.append("@SuppressWarnings(\"serial\")\n");
		code.append("@Entity\n");
		code.append("@Table(name=\"" + tableName + "\")\n");
		code.append("public class ").append(
				EntityGenUtil.simpleClassName(entityClassName)).append(
				" extends GenericEntity{\n\n");

		int n = list.size();
		for (int i = 0; i < n; i++) {
			ColumnBean cb = list.get(i);
			code.append(EntityGenUtil.propertyCode(cb)).append("\n\n");
		}

		for (int i = 0; i < n; i++) {
			ColumnBean cb = list.get(i);
			code.append(EntityGenUtil.setterCode(cb)).append("\n\n");
			code.append(EntityGenUtil.getterCode(cb)).append("\n\n");
		}

		code.append("}");

		String filePath = System.getProperty("user.dir") + "\\src\\"
				+ entityClassName.replaceAll("\\.", "\\\\") + ".java";
		File file = new File(filePath);
		// 不用手工创建包名，会自动生成一个包名  --qin 20100720 
		file.getParentFile().mkdirs();
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(
				file), "utf-8");
		fw.write(code.toString());
		fw.flush();
		fw.close();

	}

	public static List<ColumnBean> getColumns(String tableName,
			String[] filterProperty,String dataBaseType) throws HibernateException, SQLException {
		List<ColumnBean> list = new ArrayList<ColumnBean>();
		Connection conn=DBHelper.getConn();
		dataBaseType=dataBaseType.toLowerCase();
		String sql=null;
		if("mysql".equals(dataBaseType)){
			sql = "Select column_name,data_type,column_comment from information_schema.Columns Where table_name='"+tableName+"'";
		}
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			String name = rs.getString("column_name");
			if (EntityGenUtil.contains(filterProperty, name)) {
				continue;
			}
			ColumnBean bean = new ColumnBean();
			bean.setName(name);
			bean.setType(rs.getString("data_type"));
			bean.setComment(rs.getString("column_comment"));
			list.add(bean);
		}
		DBHelper.closeAll(conn, pstmt, rs);
		return list;
	}
}
