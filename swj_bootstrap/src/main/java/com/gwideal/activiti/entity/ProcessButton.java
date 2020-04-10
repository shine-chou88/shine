package com.gwideal.activiti.entity;

import java.io.Serializable;

/**
 * 控制流程审批过程中“通过”、“不通过”按钮的显示
 * @author zhou_liang
 *
 */
public class ProcessButton implements Serializable{
	private static final long serialVersionUID = 1751168892271139302L;
	private boolean needNoPassButton=false;//“不通过”按钮
	public boolean isNeedNoPassButton() {
		return needNoPassButton;
	}
	public void setNeedNoPassButton(boolean needNoPassButton) {
		this.needNoPassButton = needNoPassButton;
	}
}
