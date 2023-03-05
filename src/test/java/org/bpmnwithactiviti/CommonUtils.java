package org.bpmnwithactiviti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

public class CommonUtils {

    public static ProcessEngine getProcessEngineFromResource(String resource) {
        return ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource(resource)
                .buildProcessEngine();
    }

    public static ProcessEngine getDefaultInMemoryProcessEngine() {
        return ProcessEngineConfiguration
            .createStandaloneInMemProcessEngineConfiguration()
             .buildProcessEngine();
    }
    
}
