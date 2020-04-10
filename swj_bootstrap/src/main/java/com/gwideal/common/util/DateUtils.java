package com.gwideal.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
    private static int DAY = 24 * 60 * 60 * 1000;

    private static int WEEK=DAY*7;
    
    /**
     * 计算两个日期之间相差几天
     * 例如：DateUtils.getDaySpan(Date 2008-9-11, Date  2008-9-15)=4
     * **/
    public static int getDays(Date r_MinDate, Date r_MaxDate)
    {
        Calendar t_Calendar = Calendar.getInstance();

        long t_Span = r_MaxDate.getTime() - r_MinDate.getTime();
        t_Calendar.setTime(new Date(t_Span));

        return  Math.abs(Integer.parseInt(Long.toString(t_Span / DAY)));
    }

    /**
     * 计算两个日期之间相差几个星期
     * 例如：DateUtils.getDaySpan(Date 2008-9-11, Date  2008-9-15)=0
     * DateUtils.getDaySpan(Date 2008-9-11, Date  2008-9-18)=1
     * **/
    public static int getWeekSpan(Date r_MinDate, Date r_MaxDate)
    {
        long t_Span = r_MaxDate.getTime() - r_MinDate.getTime();
        return  Math.abs(Integer.parseInt(Long.toString(t_Span / WEEK)));
    }
    /**
     * 将一个日期转换成yyyy-MM-dd形式的字符串
     **/
    public static String formatDate(Date r_Date)
    {
        if (null == r_Date)
        {
            return null;
        }
        else
        {
            DateFormat t_DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
            return t_DateFormat.format(r_Date);
        }
    }
    /**
     * 将一个yyyy-MM-dd或yyyy-MM形式的字符串转换成日期
     **/
    public static Date parseDate(String r_Value)
    {
        if( r_Value == null ) return null;
        try
        {
            if( r_Value.lastIndexOf( '-' ) == 4 )
            {
                r_Value += "-01";
            }
            Date t_Date = DateFormat.getDateInstance(DateFormat.MEDIUM).parse(r_Value);
            return t_Date;
        }
        catch (Exception e)
        {

        }

        return null;
    }
    /**
     * 将一个制指定格式的字符串转化成日期
     **/
    public static Date parseDate(String r_Value,String r_format)
    {
        if( r_Value == null ) 
        	return null;
        try
        {
            if( r_Value.lastIndexOf( '-' ) == 4 )
            {
                r_Value += "-01";
            }
            SimpleDateFormat sd=new SimpleDateFormat(r_format);
            Date t_Date = sd.parse(r_Value);
            return t_Date;
        }
        catch (Exception e)
        {
        	return null;
        }
    }
    /**
     * 得到日期指定field的值
     **/
    public static int getValue(Date r_Date, int r_Field)
    {
        Calendar t_Calendar = Calendar.getInstance();
        t_Calendar.setTime(r_Date);
        return t_Calendar.get(r_Field);
    }
    /**
     * 得到当前年
     **/
    public static int getThisYear()
    {
        Calendar t_Calendar = Calendar.getInstance();
        return t_Calendar.get(Calendar.YEAR);
    }
    /**
     * 得到当前月
     **/
    public static int getMonth()
    {
        Calendar t_Calendar = Calendar.getInstance();

        return  t_Calendar.get(Calendar.MONTH)+1;
    }
    /**
     * 得到当前日
     **/
    public static int getDay()
    {
        Calendar t_Calendar = Calendar.getInstance();

        return  t_Calendar.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * 根据指定的格式，将日期转化成字符串
     **/
    public static String formatDate(Date r_Date, String r_Style)
    {
        if (null == r_Date)
        {
            return "";
        }

        SimpleDateFormat t_DateFormat = new SimpleDateFormat();
        t_DateFormat.applyPattern(r_Style);

        return t_DateFormat.format(r_Date);
    }
    /**
     * 将日期转化成带时间的字符串
     **/
    public static String formatDateTime(Date r_Date)
    {
        return formatDate( r_Date, "yyyy-MM-dd hh:mm:ss" );
    }
    /**
     * 将日期转化成只有年月的字符串
     **/
    public static String formatYearMonth(Date r_Date)
    {
        return formatDate( r_Date, "yyyy-MM" );
    }
    /**
     * 得到当前日期
     **/
    public static Date today()
    {
        Calendar t_Calendar = Calendar.getInstance();
        t_Calendar.setTime(new Date());

        t_Calendar.set(Calendar.HOUR_OF_DAY, 0);
        t_Calendar.set(Calendar.MINUTE, 0);
        t_Calendar.set(Calendar.SECOND, 0);
        t_Calendar.set(Calendar.MILLISECOND, 0);

        return t_Calendar.getTime();
    }
    /**
     * 将日期转化成当前年月日的字符串
     **/
    public static String getYMD()
    {
    	return formatDate(today(),"yyyy-MM-dd");
    }
    /**
     * 得到当前月的一号的字符串
     **/
    public static String getFirstDayAndCurrMonth()
    {
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
    	return formatDate(cal.getTime(),"yyyy-MM-dd");
    }
    
    /*
     *  如add(int 1,int 2,Date 2009-10-1) 指在年上加2 结果是2011-10-1
     * */
    public static Date add(int field,int amount,Date date){
    	if(date==null)
    		return date;
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(field, amount);
    	return new Date(calendar.getTimeInMillis());
    }
    /*
     *  如add(int 1,int 2,Date 2009-10-1) 指在年上加2 结果是2011-10-1
     * */
    public static String add(int field,int amount,String dateStr){
    	if(dateStr==null || dateStr.length()==0)
    		return dateStr;
    	Date date=add(field,amount,parseDate(dateStr));
    	return formatDate(date, "yyyy-MM-dd");
    }
    /*
     *  得到年月日和星期 格式的当前时间
     * 
     * */
    public static String getFullDate(boolean hasWeek)
    {
    	Calendar t_Calendar = Calendar.getInstance();
    	String fullDate = new SimpleDateFormat("yyyy-MM-dd").format(t_Calendar.getTime());
    	String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    	if(hasWeek)
    	{
    		fullDate += " " + weeks[t_Calendar.get(Calendar.DAY_OF_WEEK) - 1];;
    	}
    	return fullDate;
    }
    /*
     *  可以得到指定日期是星期几
     * 
     * */
    public static String getWeekDate(Date date)
    {
    	Calendar t_Calendar = Calendar.getInstance();
    	t_Calendar.setTime(date);
    	String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};  	
    	
    	return weeks[t_Calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }
    public static String getWeekDate(int year,int month,int day)
    {
    	Calendar t_Calendar = Calendar.getInstance();
    	t_Calendar.set(Calendar.YEAR,year);
    	 t_Calendar.set(Calendar.MONTH, month-1);
         t_Calendar.set(Calendar.DAY_OF_MONTH, day);
         	
    	
    	return getWeekDate(t_Calendar.getTime());
    }
    
    public static String getWeekDate(String year,String month,String day)
    {
    	int _year=new Integer(year).intValue();
    	int _month=new Integer(month).intValue();
    	int _day=new Integer(day).intValue();
         	
    	
    	return getWeekDate(_year,_month,_day);
    }
    //判断指定的年月日是否是工作日
    public static boolean isWorkDay(int year,int month,int day)
    {
    	boolean flag=true;
    	Calendar t_Calendar = Calendar.getInstance();
    	t_Calendar.set(Calendar.YEAR,year);
    	 t_Calendar.set(Calendar.MONTH, month-1);
         t_Calendar.set(Calendar.DAY_OF_MONTH, day);
       //  System.out.println(year+"-"+month+"-"+day+"-"+"-DAY_OF_WEEK--"+t_Calendar.get(Calendar.DAY_OF_WEEK));
         if(t_Calendar.get(Calendar.DAY_OF_WEEK)==1 || t_Calendar.get(Calendar.DAY_OF_WEEK)==7)
         {
        	 flag=false;
         }
    	return flag;
    }
    //判断指定的年月日是否是工作日
    public static boolean isWorkDay(String year,String month,String day)
    {
    	int _year=new Integer(year).intValue();
    	int _month=new Integer(month).intValue();
    	int _day=new Integer(day).intValue();
    	return isWorkDay(_year,_month,_day);
    }
//    根据年月得到本月的总天数

    public static int getTotalDays(String year,String month){
       
    	int year_=0;
    	int month_=0;
    	if(year==null || year.equals(""))
    	{
    		return 0;
    	}else
    	{
    		try{
    			year_=new Integer(year).intValue();
    		}catch(Exception e)
    		{
    			//e.printStackTrace();
    			//System.out.println("数字转化错误year：==="+year);
    			return 0;
    		}
    	}
    	if(month==null || month.equals(""))
    	{
    		return 0;
    	}else
    	{
    		try{
    			month_=new Integer(month).intValue();
    		}catch(Exception e)
    		{
    			//e.printStackTrace();
    			//System.out.println("数字转化错误month：==="+month);
    			return 0;
    		}
    	} 
    	return getTotalDays(year_,month_);
    	
    }
    /*根据年月得到本月的总天数*/
    public static int getTotalDays(int year,int month){
    	int total_days=0;
     	try{
            Calendar t_Calendar = Calendar.getInstance();
            t_Calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(year+"-"+month+"-01"));
            total_days=t_Calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
           
            }catch(Exception e){
            	//System.out.println("getTotalDays 日期转化错误：==="+month);
            }

        return total_days;
    }
    
    /*根据日期时间判断现在是上午还是下午 
     * 0:表示上午0:00-11:59，
     * 1:表示下午12:00-23:59 
     * 3:异常
     * */
    public static int getPmorAm(Date date){
         	try{
            Calendar t_Calendar = Calendar.getInstance();
            t_Calendar.setTime(date);
           // System.out.println(date+" "+t_Calendar.get(Calendar.AM));
            return t_Calendar.get(Calendar.AM_PM);
           
            }catch(Exception e){
            	//System.out.println("getTotalDays 日期转化错误：=date=="+date);
            
            }

        return 3;
    }
    
    /*根据日期字符串判断现在是上午还是下午 
     * 0:表示上午0:00-11:59，
     * 1:表示下午12:00-23:59
     * 3:异常
     * */
    public static int getPmorAm(String date){
     	
    	Date t_date = parseDate(date, "yyyy-MM-dd HH:mm");
    	return getPmorAm(t_date);
    
     }
    
    /**
	 * 获取一个固定时间
	 * @return
	 */
	public static Date getOpRuleTaskDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.parse("2013-01-01 09:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取当前七天后的时间
	 * @param 
	 * @return
	 */
	public static Date getAfterSevenDaysTime(){
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			c.add(Calendar.DATE, 7);
			date=sdf.parse(sf.format(c.getTime())+" 20:00:00");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return date;
	}
    
    public static void main(String[] args)
    {
    	System.out.println(DateUtils.getWeekDate(2008,11,11));
    	 
    }
    
   
}