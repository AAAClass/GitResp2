package com.example;



import org.activiti.engine.*;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;

import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;


public class test {
    @Test
    public void test01() {
        // 使用classpath下的activiti.cfg.xml中的配置来创建 ProcessEngine对象
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(engine);

    }
    @Test
    public void test02(){
        ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService=engine.getRepositoryService();
       Deployment deployment=  repositoryService.createDeployment()
               .addClasspathResource("bpmn/evection.bpmn")
               .addClasspathResource("bpmn/evection.png")
               .name("出差申请流程")
               .deploy();
        System.out.println("流程部署的id:"+deployment.getId());
        System.out.println("流程部署的名称"+deployment.getName());
    }
    //开启一个流程实例
    @Test
    public void test03(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService=processEngine.getRuntimeService();
        String key="evection";
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(key);
        System.out.println("流程定义的ID:"+processInstance.getProcessDefinitionId());
        System.out.println("流程实例的ID"+processInstance.getId());
        System.out.println("当前活动的ID"+processInstance.getActivityId());
    }
    //查询任务
    @Test
    public void test04(){
        String assignee="wangwu";
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService=processEngine.getTaskService();
        List<Task> list=taskService.createTaskQuery()
                .processDefinitionKey("evection")
                .list();

        for (Task task:list){
            System.out.println("流程实例id："+task.getProcessDefinitionId());
            System.out.println("任务id"+task.getId());
            System.out.println("任务名称："+task.getName());
        }
    }
    //完成实例任务
    @Test
    public void test05(){
        ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService=engine.getTaskService();
        Task task=  taskService.createTaskQuery()
                .processDefinitionKey("evection")
                .taskAssignee("zhangsan")
                .singleResult();
        taskService.complete(task.getId());
    }

   @Test
    public void test06(){
    ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService=engine.getRepositoryService();
       ProcessDefinitionQuery processDefinitionQuery=repositoryService.createProcessDefinitionQuery();
       List<ProcessDefinition> list=processDefinitionQuery.processDefinitionKey("evection")
               .orderByProcessDefinitionVersion()
               .desc()
               .list();
       for(ProcessDefinition processDefinition:list){
           System.out.println("流程定义的ID："+processDefinition.getId());
           System.out.println("流程定义的name:"+processDefinition.getName());
           System.out.println("流程定义的version："+ processDefinition.getVersion());
           System.out.println("流程部署的id："+processDefinition.getDeploymentId());
       }
   }
   //删除部署的流程
   @Test
    public void test07(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService= processEngine.getRepositoryService();
        repositoryService.deleteDeployment("30001",true);

       //17501为流程部署的id
       //evection 为流程定义的id
       //25001 为流程实例的id
       //27505 为任务id
   }
   //定义businesskey
    @Test
    public void test08(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance instance =  runtimeService.startProcessInstanceByKey("evection","1001");
        System.out.println("businessKey"+instance);
    }

}
