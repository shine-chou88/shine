package com.gwideal.common.codegen;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class EntityGenUtil {
	
	public static String packageCode(String className){
		
		if(className.indexOf(".")==-1){
			return "package "+className;
		}
		return "package "+className.substring(0, className.lastIndexOf("."));
	}
	
	public static String simpleClassName(String className){
		if(className.indexOf(".")==-1){
			return className;
		}else{
			String[] temp = className.split("\\.");
			return temp[temp.length-1];
		}
	}
	
	public static Set<String> importsCode(List<ColumnBean> list){
		
		Set<String>  set = new LinkedHashSet<String>();
		int n = list.size();
		for(int i = 0;i<n;i++){
			ColumnBean cb = list.get(i);
			if("java.sql.Timestamp".equals(cb.getType())){
				set.add("import java.util.Date;\n");
			}else if(cb.getType().indexOf("lang")==-1){
				set.add("import "+cb.getType()+";\n");
			}
		}
		set.add("\n");
		set.add("import javax.persistence.Column;\n");
		set.add("import javax.persistence.Entity;\n");
		set.add("import javax.persistence.Table;\n");
		return set;
	}
	
	
	public static String propertyCode(ColumnBean cb){
		
		String code = "";
		String type=null;
		if(cb.getType().indexOf("datetime")>=0){
			type="Date";
		}else if(cb.getType().indexOf("int")>=0){
			type="Integer";
		}else if(cb.getType().indexOf("varchar")>=0){
			type="String";
		}
		
		String name = cb.getName();
		name = name.toLowerCase();
		
		if(name.indexOf("_")!=-1){
			StringBuffer sb = new StringBuffer();
			String[] temp = name.split("_");
			sb.append(temp[0]);
			for(int i=1;i<temp.length;i++){
				sb.append(toUpperIndexChar(temp[i]));
			}
			
			code = code +"	@Column(name=\""+cb.getName()+"\")\n";
			
			name = sb.toString();
		}
		
		code = code+"	private "+type+" "+name+";"+"//"+cb.getComment();
		
		return code;
	}
	
	
	
	public static String getterCode(ColumnBean cb){
		cb = init(cb);
		StringBuffer code = new StringBuffer();
		code.append("	public ").append(cb.getType()).append(" get").append(toUpperIndexChar(cb.getName())).append("(){\n");
		code.append("		return ").append(cb.getName()).append(";\n");
		code.append("	}");
		return code.toString();
	}
	
	public static String setterCode(ColumnBean cb){
		cb = init(cb);
		StringBuffer code = new StringBuffer();
		code.append("	public void ").append("set").append(toUpperIndexChar(cb.getName())).append("(").append(cb.getType()).append(" "+cb.getName()).append("){\n");
		code.append("		this.").append(cb.getName()).append(" = ").append(cb.getName()).append(";\n");
		code.append("	}");
		return code.toString();
	}
	
	public static ColumnBean init(ColumnBean cb){
		
		ColumnBean tempBean = new ColumnBean();
		
		if(cb.getType().indexOf("datetime")>=0){
			tempBean.setType("Date");
		}else if(cb.getType().indexOf("int")>=0){
			tempBean.setType("Integer");
		}else if(cb.getType().indexOf("varchar")>=0){
			tempBean.setType("String");
		}
		String name = cb.getName();
		name = name.toLowerCase();
		if(name.indexOf("_")!=-1){
			StringBuffer sb = new StringBuffer();
			String[] temp = name.split("_");
			sb.append(temp[0]);
			for(int i=1;i<temp.length;i++){
				sb.append(toUpperIndexChar(temp[i]));
			}
			name = sb.toString();
		}
		tempBean.setName(name);
		
		tempBean.setComment(cb.getComment());
		
		return tempBean;
	}
	
	public static String toUpperIndexChar(String str){
		
		String temp = String.valueOf(str.charAt(0)).toUpperCase()+str.substring(1, str.length());
		System.out.println("temp="+str);
		return temp;
	}
	
	public static String toLowerIndexChar(String str){
		
		String temp = String.valueOf(str.charAt(0)).toLowerCase()+str.substring(1, str.length());
		
		return temp;
	}
	
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
	
}
