package com.gwideal.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期公共类
 * @author zhou_liang
 *
 */
public class DateUtil {
	private static final Logger log=LoggerFactory.getLogger(DateUtil.class);
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 根据年、月参数推算前5个月的年月数据
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<String> getYm(int year,int month){
		List<String> listYm=new ArrayList<String>();
		try {
			for (int i = 5; i >= 0; i--) {
				Calendar cl = Calendar.getInstance();
				cl.set(Calendar.YEAR,year);
				cl.set(Calendar.MONTH,month-1);
				cl.set(Calendar.DAY_OF_MONTH,1);
				cl.add(Calendar.MONTH,-i);
				listYm.add(sdf.format(cl.getTime()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("",e);
		}
		return listYm;
	}
	
	/**
	 * 根据年、月参数推算前11个月的年月数据
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<String> getYm12(int year,int month){
		List<String> listYm=new ArrayList<String>();
		try {
			for (int i = 11; i >= 0; i--) {
				Calendar cl = Calendar.getInstance();
				cl.set(Calendar.YEAR,year);
				cl.set(Calendar.MONTH,month-1);
				cl.set(Calendar.DAY_OF_MONTH,1);
				cl.add(Calendar.MONTH,-i);
				listYm.add(sdf.format(cl.getTime()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("",e);
		}
		return listYm;
	}
	
	/**
	 * 获取n个月之前的最近几个月的年月数据
	 * @param n n个月前的
	 * @param t 最近几个月
	 * @return
	 */
	public static List<String> getYearMonth(int n,int t){
		List<String> listYm=new ArrayList<String>();
		try{
			for (int i = t; i >=0; i--) {
				Calendar cl=Calendar.getInstance();
				cl.add(Calendar.MONTH, -(i+n));
				listYm.add(sdf.format(cl.getTime()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("",e);
		}
		return listYm;
	} 
	
	/**
	 * 获取当前年月格式为yyyy-MM
	 * @return
	 */
	public static String getCurrentYearMonth(){
		return sdf.format(new Date());
	}

	/**
	 * 判断当前月份属于哪个季度
	 * @param month
	 * @return
	 */
	public static int judgeSeasonByMonth(int month) {
		if(month==1 || month==2 || month==3){
			return 1;
		}
		else if(month==4 || month==5 || month==6){
			return 2;
		}
		else if(month==7 || month==8 || month==9){
			return 3;
		}
		else if(month==10 || month==11 || month==12){
			return 4;
		}
		return -1;
	}
	/**
	 * 通过月份数字返回月份中文
	 */
	public static String getMonthCN(int month){
		String str = "";
		if(month==0){
			str = "一月";
		}else if(month==1){
			str = "二月";
		}else if(month==2){
			str = "三月";
		}else if(month==3){
			str = "四月";
		}else if(month==4){
			str = "五月";
		}else if(month==5){
			str = "六月";
		}else if(month==6){
			str = "七月";
		}else if(month==7){
			str = "八月";
		}else if(month==8){
			str = "九月";
		}else if(month==9){
			str = "十月";
		}else if(month==10){
			str = "十一月";
		}else if(month==11){
			str = "十二月";
		}
		return str;
	}
	
	/**
	 * 输入年月，返回前n个月(包括当月)的yyyy-MM格式字符集合
	 * @param year
	 * @param month
	 * @param n
	 */
	public static List<String> getYearMonthsStr(Integer year, Integer month, int n){
		if (!(year>1900) || !(-1<month && month<12) || !(n>-1))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		List<String> res = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		while (n>0) {
			cal.add(Calendar.MONTH, -1);
			res.add(sdf.format(cal.getTime()));
			--n;
		}
		return res;
	}
	
	/**
	 * 输入年月，返回前第n个月的字符串
	 * @param year
	 * @param month
	 * @param n
	 */
	public static String getAgoDateStr(Integer year, Integer month, int n){
		if (!(year>1900) || !(-1<month && month<12) || !(n>-1))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, -n);
		String str = sdf.format(cal.getTime());
		return str;
	}
	
	public static int getDayOfWeek(Date date){
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int a=cal.get(Calendar.DAY_OF_WEEK)-1;
		if(a<0){
			a=0;
		}
		return a;
	}
}
