package com.gwideal.common.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;

/**
 * poi工具类
 * @author zhou_liang
 *
 */
public class POIUtil implements Serializable{

	private static final long serialVersionUID = -6110177130264264497L;
	
	/**
	 * 设置cell类型为String类型，获取cell里面的值
	 * @param cell
	 * @return
	 */
	public static String getCellValue(HSSFCell cell){
		String cellValue="";
		if(null!=cell){
			cell.setCellType(CellType.STRING);
			cellValue=cell.getStringCellValue();
		}
		if(!StringUtil.isEmpty(cellValue)){
			cellValue=cellValue.replace(" ", "");
		}
		return cellValue;
	}
	
	/**
	 * 取 int cell值
	 * @param cell
	 * @return
	 */
	public static int getIntCellValue(HSSFCell cell){
		String val=getCellValue(cell);
		if(!StringUtil.isEmpty(val)){
			return Integer.valueOf(val);
		}else{
			return 0;
		}
	}
	
	/**
	 * 获取日期cell里面的值
	 * @param cell
	 * @return
	 */
	public static String getDateCellValue(HSSFCell cell){
		String cellValue="";
		if(null!=cell){
			if(cell.getCellTypeEnum()==CellType.NUMERIC){
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					SimpleDateFormat dateCellSdf=new SimpleDateFormat("yyyy-MM-dd");
					Date d=HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					cellValue=dateCellSdf.format(d);
				}else{
					cell.setCellType(CellType.STRING);
					cellValue=cell.getStringCellValue();
				}
			}else{
				cell.setCellType(CellType.STRING);
				cellValue=cell.getStringCellValue();
			}
		}
		if(!StringUtil.isEmpty(cellValue)){
			cellValue=cellValue.trim();
		}
		return cellValue;
	}

	/**
	 * 矫正月报获取统计年月
	 * @param title
	 * @return
	 */
	public static String getCorrectMonthReportYearMonth(String title){
		String yearMonth=null;
		if(!StringUtil.isEmpty(title) && title.indexOf("年")>0 && title.indexOf("月")>0){
			int year=Integer.valueOf(title.substring(title.indexOf("年")-4, title.indexOf("年")));
			int month=Integer.valueOf(title.substring(title.indexOf("年")+1, title.indexOf("月")));
			if(year>0 && month>0){
				if(month<10){
					yearMonth=year+"-0"+month;
				}else{
					yearMonth=year+"-"+month;
				}
			}
		}
		return yearMonth;
	}
}
