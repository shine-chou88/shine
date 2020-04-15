package com.gwideal.activiti.entity;

import java.io.InputStream;
import java.util.ArrayList;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;

/**
 * 更新流程时重新生成流程图
 * @author zhou_liang
 *
 */
public class ProcessInstanceDiagramPng implements Command<InputStream>{
	
	private ProcessEngine processEngine;
	
    private String processDefinitionId;

    public ProcessInstanceDiagramPng(ProcessEngine processEngine,String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
        this.processEngine = processEngine;
    }

    public InputStream execute(CommandContext commandContext) {
        GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(processDefinitionId);
        BpmnModel bpmnModel = getBpmnModelCmd.execute(commandContext);
        InputStream is = new DefaultProcessDiagramGenerator().generateDiagram(
                bpmnModel, "png",null,new ArrayList<String>(),
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(), 
                processEngine.getProcessEngineConfiguration().getClassLoader(),1.0);
        return is;
    }
}
