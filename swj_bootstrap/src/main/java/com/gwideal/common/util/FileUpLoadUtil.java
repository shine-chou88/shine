package com.gwideal.common.util;

import java.util.ResourceBundle;

public class FileUpLoadUtil {
	private final static ResourceBundle res = ResourceBundle.getBundle("upload");
	
	public static String getSavePath(){
		return res.getString("upload.save_path");
	}
	
	public static String getJyywSavePath(){
		return res.getString("upload.jyyw.save_path");
	}
	
	public static String getFxpgSavePath(){
		return res.getString("upload.fxpg.save_path");
	}
	
	public static String getXstbSavePath(){
		return res.getString("upload.xstb.save_path");
	}
	
	public static String getGrwwSavePath(){
		return res.getString("upload.grww.save_path");
	}
	
	public static String getJcxxSavePath(){
		return res.getString("upload.jcxx.save_path");
	}
	
	public static String getReceptionSavePath(){
		return res.getString("upload.reception.save_path");
	}
	
	public static String getInspectSavePath(){
		return res.getString("upload.inspect.save_path");
	}
	
	public static String getLetterReceptionSavePath(){
		return res.getString("upload.letterReception.save_path");
	}
	
	public static String getItemNoteSavePath() {
        return res.getString("upload.itemNote.save_path");
    }
	public static String getLeaderInstructionsSavePath() {
        return res.getString("upload.leaderInstructions.save_path");
    }
	public static String getSupervisionListSavePath(){
		return res.getString("upload.supervisionList.save_path");
	}
	public static String getHistoryStatisticsDataSavePath() {
        return res.getString("upload.historyStatisticsData.save_path");
    }
	public static String getActionInformationSavePath() {
        return res.getString("upload.actionInformation.save_path");
    }
	public static String getDrillsSavePath() {
        return res.getString("upload.drills.save_path");
    }
	
	public static String getContingencyPlanSavePath() {
        return res.getString("upload.contingencyPlan.save_path");
    }
	public static String getEmpiricalDataSavePath() {
        return res.getString("upload.empiricalData.save_path");
    }
	public static String getLawsRulesSavePath() {
        return res.getString("upload.lawsRules.save_path");
    }
	public static String getWorkProgrammeSavePath() {
        return res.getString("upload.workProgramme.save_path");
    }
	public static String getPaptSavePath() {
        return res.getString("upload.papt.save_path");
    }
	
	public static String getPopuDrugSavePath() {
        return res.getString("upload.popuDrug.save_path");
    }
	public static String getTaskDistributeSavePath() {
        return res.getString("upload.taskDistribute.save_path");
    }
	public static String getUserSavePath() {
        return res.getString("upload.user.save_path");
    }
}
