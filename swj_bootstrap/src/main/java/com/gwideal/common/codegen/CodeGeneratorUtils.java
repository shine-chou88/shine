package com.gwideal.common.codegen;


import com.gwideal.common.util.DBHelper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 代码生成工具类
 * zhou_liang
 */
public class CodeGeneratorUtils {

	private static final ResourceBundle genRes=ResourceBundle.getBundle("generator");
	
	/**
	 * 生成代码
	 * @param tableName 数据库表名
	 * @param filterPropertys 实体类不需要生成的字段
	 * @param dataBaseType 数据库类型，如mysql、oracle、sqlserver
	 * @throws Exception
	 */
    public static void generatorCode(String tableName,String[] filterPropertys,String dataBaseType) throws Exception{
    	tableName=tableName.toUpperCase();
    	Map<String, String> table=getTableInfo(tableName, dataBaseType);
    	List<ColumnEntity> columns=getColumns(tableName, dataBaseType);
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(),genRes.getString("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        
        List<ColumnEntity> listColumns=new ArrayList<ColumnEntity>();
        //设置主键和过滤字段
        for(ColumnEntity column:columns){
        	if(column.isPk() && tableEntity.getPk()==null){
        		tableEntity.setPk(column);
        	}
        	String dataType=column.getDataType().toLowerCase();
        	if("decimal".equals(dataType)){
        		tableEntity.setHasBigDecimal(true);
        	}else if("date".equals(dataType) || "datetime".equals(dataType) || "timestamp".equals(dataType)
        			|| "timestamp(6)".equals(dataType)){
        		tableEntity.setHasDate(true);
        	}
        	if (contains(filterPropertys,column.getColumnName())) {
				continue;
			}
        	listColumns.add(column);
        }
        tableEntity.setColumns(listColumns);
        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
        	tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put(VelocityEngine.FILE_RESOURCE_LOADER_PATH,genRes.getString("projectDir")+genRes.getString("templatesDir"));
        Velocity.init(prop);

        //封装模板数据
        Map<String, Object> map = new HashMap<String, Object>(16);
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", tableEntity.isHasBigDecimal());
        map.put("hasDate", tableEntity.isHasDate());
        map.put("packageName", genRes.getString("packageName"));
        map.put("entityDirName", genRes.getString("entityDirName"));
        map.put("serviceDirName", genRes.getString("serviceDirName"));
        map.put("serviceImplDirName", genRes.getString("serviceImplDirName"));
        map.put("controllerDirName", genRes.getString("controllerDirName"));
        map.put("serviceNameSuffix", genRes.getString("serviceNameSuffix"));
        map.put("serviceImplNameSuffix", genRes.getString("serviceImplNameSuffix"));
        map.put("controllerNameSuffix", genRes.getString("controllerNameSuffix"));
        map.put("controllerReturnPagePrefix",genRes.getString("controllerReturnPagePrefix"));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templateNames = getTemplateNames();
        for (String templateName : templateNames) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(templateName, "UTF-8");
            tpl.merge(context, sw);
            try {
                String fileName=getFileName(templateName, tableEntity.getClassname(), tableEntity.getClassName());
                File file = new File(genRes.getString("projectDir")+fileName);
                file.getParentFile().mkdirs();
                OutputStream output=new FileOutputStream(file);
                IOUtils.write(sw.toString(), output, "UTF-8");
            } catch (Exception e) {
                throw e;
            }finally{
            	IOUtils.closeQuietly(sw);
            }
        }
    }
	
    /**
     * 加载模板
     * @return
     */
	public static List<String> getTemplateNames() {
        List<String> templates = new ArrayList<String>();
        templates.add("entity.java.vm");
        templates.add("service.java.vm");
        templates.add("serviceImpl.java.vm");
        templates.add("controller.java.vm");
        templates.add("list.jsp.vm");
        templates.add("edit.jsp.vm");
        templates.add("view.jsp.vm");
        return templates;
    }

   /**
    * 获取表信息
    * @param tableName 数据库表名
	* @param dataBaseType 数据库类型
    * @return
    * @throws Exception
    */
	public static Map<String, String> getTableInfo(String tableName,String dataBaseType)throws Exception{
		Map<String, String> map=new HashMap<String, String>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql=null;
		dataBaseType=dataBaseType.toLowerCase();
		if("mysql".equals(dataBaseType)){
			sql="select table_name tableName, table_comment tableComment from information_schema.tables Where table_name = '"+tableName+"'";
		}else if("oracle".equals(dataBaseType)){
			sql="select table_name tableName,comments tableComment from user_tab_comments Where table_name= '"+tableName+"'";
		}else if("sqlserver".equals(dataBaseType)){
			sql="select st.name tableName,Convert(nvarchar(50),sep.value) tableComment from sys.tables st left join sys.extended_properties sep";
			sql+=" on sep.major_id = st.object_id and sep.minor_id=0 Where st.name='"+tableName+"' group by st.name ,sep.value";
		}
		conn=DBHelper.getConn();
		pstmt=conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()){
			map.put("tableName",rs.getString("tableName"));
			map.put("tableComment",rs.getString("tableComment"));
		}
		DBHelper.closeAll(conn, pstmt, rs);
		return map;
	}
	
	/**
    * 获取表的所有列信息
    * @param tableName 数据库表名
	* @param dataBaseType 数据库类型
    * @return
    * @throws Exception
    */
	public static List<ColumnEntity> getColumns(String tableName,String dataBaseType) throws Exception {
		List<ColumnEntity> list = new ArrayList<ColumnEntity>();
		Connection conn=DBHelper.getConn();
		dataBaseType=dataBaseType.toLowerCase();
		String sql=null;
		if("mysql".equals(dataBaseType)){
			sql = "Select column_name,data_type,column_comment,column_key from information_schema.Columns Where table_name='"+tableName+"'";
		}else if("oracle".equals(dataBaseType)){
			sql="Select ut.COLUMN_NAME as column_name,uc.comments as column_comment,ut.DATA_TYPE as data_type from user_tab_columns  ut ";
			sql=sql+"inner JOIN user_col_comments uc on ut.TABLE_NAME  = uc.table_name and ut.COLUMN_NAME = uc.column_name where ut.Table_Name='"+tableName+"'";
		}else if("sqlserver".equals(dataBaseType)){
			sql="Select sc.name column_name,st.name data_type,Convert(nvarchar(50),sep.value) column_comment from syscolumns sc";
			sql=sql+" left join systypes st on sc.xusertype=st.xusertype";
			sql=sql+" inner join sysobjects so on sc.id=so.id and so.xtype='U' and so.name<>'dtproperties'";
			sql=sql+" left join sys.extended_properties sep on sc.id=sep.major_id and sc.colid=sep.minor_id";
			sql=sql+" Where so.name='"+tableName+"'";
		}
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			ColumnEntity bean = new ColumnEntity();
			bean.setColumnName(rs.getString("column_name"));
			bean.setDataType(rs.getString("data_type"));
			bean.setComments(rs.getString("column_comment"));
			//列名转换成Java属性名
            String attrName = columnToJava(bean.getColumnName());
            bean.setAttrName(attrName);
            bean.setAttrname(StringUtils.uncapitalize(attrName));
            //列的数据类型，转换成Java类型
            String dataType=bean.getDataType().toLowerCase();
            String attrType = genRes.getString(dataType);
            bean.setAttrType(attrType);
			list.add(bean);
		}
		DBHelper.closeAll(conn, pstmt, rs);
		return list;
	}
	
    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
    	if(!StringUtils.isBlank(tableName) && !StringUtils.isBlank(tablePrefix)){
    		if (Boolean.valueOf(genRes.getString("removeTablePrefix")).booleanValue()) {
                tableName = tableName.substring(tableName.indexOf(tablePrefix)+tablePrefix.length());
            }
    	}
        return columnToJava(tableName);
    }

    /**
	 * 判断字符串数组是否包含某个字符串
	 * @param arr 数组
	 * @param str 字符串
	 * @return
	 */
	public static boolean contains(String[] arr,String str){
		if(arr==null || arr.length<1){
			return false;
		}else{
			for(int i=0;i<arr.length;i++){
				if(str.equalsIgnoreCase(arr[i])){
					return true;
				}
			}
		}
		return false;
	}

    /**
     * 生成文件名
     * @param templateName 模板名称
     * @param classname 类名(第一个字母小写)
     * @param className 类名(第一个字母大写)
     * @param packageName 包名
     * @return
     */
    public static String getFileName(String templateName, String classname, String className) {
        String srcDir = genRes.getString("srcDir");
        String pageDir = genRes.getString("pageFileDir");
        String packageName=genRes.getString("packageName");
        if (StringUtils.isNotBlank(packageName)) {
        	srcDir += packageName.replace(".", File.separator) + File.separator;
        }
        if (templateName.contains("entity.java.vm")) {
            return srcDir + genRes.getString("entityDirName") + File.separator + className + ".java";
        }
        if (templateName.contains("service.java.vm")) {
            return srcDir + genRes.getString("serviceDirName") + File.separator + className + genRes.getString("serviceNameSuffix") +".java";
        }
        if (templateName.contains("serviceImpl.java.vm")) {
            return srcDir+genRes.getString("serviceDirName")+File.separator+genRes.getString("serviceImplDirName")+File.separator+className+genRes.getString("serviceImplNameSuffix")+".java";
        }
        if (templateName.contains("controller.java.vm")) {
            return srcDir + genRes.getString("controllerDirName") + File.separator + className +genRes.getString("controllerNameSuffix")+".java";
        }
        if (templateName.contains("list.jsp.vm")) {
            return pageDir + File.separator + classname + File.separator + "list.jsp";
        }
        if (templateName.contains("edit.jsp.vm")) {
            return pageDir + File.separator + classname + File.separator + "edit.jsp";
        }
        if (templateName.contains("view.jsp.vm")) {
            return pageDir + File.separator + classname + File.separator + "view.jsp";
        }
        return null;
    }
}
