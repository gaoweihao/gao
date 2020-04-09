package com.flowable.springboot.controller;

import com.flowable.springboot.requestBean.*;
import com.flowable.springboot.responseBean.BaseResponse;
import com.flowable.springboot.service.FbpmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fbpm")
@Api(value = "fbpm-Controller",tags="fbpm 操作接口")
public class FbpmController {

    @Value("${task_url}")
    private String taskUrl;

    @Autowired
    private FbpmService fbpmService;

    @ApiOperation(value="fbpm批量启动流程")
    @PutMapping("/fbpm-batch-process-instances")
    public BaseResponse batchProcessInstances(){
        ProcessStartQuery processStart = new ProcessStartQuery();
        //批量启动远程接口
        String url = taskUrl+"/api/runtime/fbpm-process-instances-batchCreateInstance";
        BaseResponse response = fbpmService.batchProcessInstance(url,processStart);
        return response;

    }

    @ApiOperation(value="fbpm批量审核流程")
    @PutMapping("/fbpm-batch-process-approve")
    public BaseResponse batchProcessApprove(){
        ProcessApproveQuery processApprove =new ProcessApproveQuery();
        //批量启动远程接口
        String url = taskUrl+"/api/runtime/approve-batch-tasks-new";
        BaseResponse response = fbpmService.batchProcessApprove(url,processApprove);
        return response;

    }

    @ApiOperation(value="fbpm批量审核流程")
    @PutMapping("/fbpm-batch-process-back")
    public BaseResponse batchProcessBack(){
        ProcessBackQuery processBack =new ProcessBackQuery();
        //批量启动远程接口
        String url = taskUrl+"/api/runtime/approve-batch-tasks-new";
        BaseResponse response = fbpmService.batchProcessBack(url,processBack);
        return response;

    }

    @ApiOperation(value="fbpm批量审核流程")
    @PutMapping("/fbpm-batch-process-cancel")
    public BaseResponse batchProcessCancel(){
        ProcessCancelQuery processCancel =new ProcessCancelQuery();
        //批量启动远程接口
        String url = taskUrl+"/api/runtime/approve-batch-tasks-new";
        BaseResponse response = fbpmService.batchProcessCancel(url,processCancel);
        return response;

    }

    @ApiOperation(value="fbpm查询代办任务接口")
    @GetMapping("/fbpm-select-task-list-state/{state}")
    public BaseResponse selectTaskListByState(@PathVariable String state){
        TaskStateQuery taskStat =new TaskStateQuery();
        //批量启动远程接口
        String url = taskUrl+"/api/runtime/fbpm-user-state-task/taskList/"+state;
        BaseResponse response = fbpmService.selectTaskListByState(url,taskStat);
        return response;

    }
}
