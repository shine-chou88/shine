package com.gwideal.common.page;

public class Result {
	
	public static final String saveSuccessMessage="保存成功！";
	public static final String saveFailureMessage="保存失败，请联系管理员！";
	public static final String deleteSuccessMessage="删除成功！";
	public static final String deleteSuccessMessagecz="专业分类已使用！";
	public static final String deleteFailureMessage="删除失败，请联系管理员！";
	public static final String commitSuccessMessage="提交成功！";
	public static final String commitFailureMessage="提交失败，请联系管理员！";
	public static final String saveSuccessMessagecz = "专业分类已存在";
	public static final String cancelSuccessMessage="注销成功！";
	public static final String cancelFailureMessage="注销失败，请联系管理员！";
	public static final String ERRORMSG="发生错误，请联系管理员！";
	public static final String addSuccessHistoryHistory="操作成功！";
	public static final String addFailuresHistoryHistory="操作失败,请联系管理员！";
	public static final String recoverSuccessHistoryHistory="还原成功！";
	public static final String recoverFailuresHistoryHistory="还原失败,请联系管理员！";
	public static final String SUCCESSMSG="操作成功！";
	
	private boolean success;//是否成功
	private String info;//提示信息
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public Result(boolean success,String info){
		this.success=success;
		this.info=info;
	}
}
