package org.bpmnwithactiviti.chapter1;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.bpmnwithactiviti.CommonUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleProcessTest {
	
	@Test
	public void startBookOrder() {
		ProcessEngine processEngine = CommonUtils.getDefaultInMemoryProcessEngine();

		RuntimeService runtimeService = processEngine.getRuntimeService();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
			.addClasspathResource("chapter1/bookorder.simple.bpmn20.xml")
			.deploy();
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
				"simplebookorder");
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " " 
				+ processInstance.getProcessDefinitionId());
	}

}
