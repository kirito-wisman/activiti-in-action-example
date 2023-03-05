package org.bpmnwithactiviti.chapter1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookOrderTest {

    @Test
    public void startBookOrder() {
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
//        IdentityService identityService = processEngine.getIdentityService();
        TaskService taskService = processEngine.getTaskService();
        repositoryService.createDeployment()
                .addClasspathResource("chapter1/bookorder.bpmn20.xml")
                .deploy();

        // remove tasks already present
        List<Task> availableTaskList = taskService.createTaskQuery().taskName("Work on order").list();
        for (Task task : availableTaskList) {
            taskService.complete(task.getId());
        }

        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("isbn", "123456");
//        identityService.setAuthenticatedUserId("kermit");
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("bookorder", variableMap);
        assertNotNull(processInstance.getId());
        List<Task> taskList = taskService.createTaskQuery().taskName("Work on order").list();
        assertEquals(1, taskList.size());
        System.out.println("found task " + taskList.get(0).getName());
    }
}
