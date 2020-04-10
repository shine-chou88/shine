package com.gwideal.activiti.entity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;

/**
 * 显示执行到具体节点的流程图
 * @author zhou_liang
 *
 */
public class ProcessInstanceDiagram implements Command<InputStream>{
	
	private ProcessEngine processEngine;
	
    private String processInstanceId;

    public ProcessInstanceDiagram(ProcessEngine processEngine,String processInstanceId) {
        this.processInstanceId = processInstanceId;
        this.processEngine = processEngine;
    }

    public InputStream execute(CommandContext commandContext) {
        ExecutionEntityManager executionEntityManager = commandContext.getExecutionEntityManager();
        ExecutionEntity executionEntity = executionEntityManager.findExecutionById(processInstanceId);
        List<String> activityIds = executionEntity.findActiveActivityIds();
        String processDefinitionId = executionEntity.getProcessDefinitionId();
        GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(processDefinitionId);
        BpmnModel bpmnModel = getBpmnModelCmd.execute(commandContext);
        InputStream is = new DefaultProcessDiagramGenerator().generateDiagram(
                bpmnModel, "png",activityIds,new ArrayList<String>(),
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(), 
                processEngine.getProcessEngineConfiguration().getClassLoader(),1.0);
        return is;
    }
}
