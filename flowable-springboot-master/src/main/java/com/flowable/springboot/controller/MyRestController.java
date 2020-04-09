package com.flowable.springboot.controller;

import com.flowable.springboot.service.MyService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *测试flowable 使用，可以删除
 * */
@Controller
@RequestMapping("/myrest")
public class MyRestController {

    @Autowired
    private MyService myService;

    @RequestMapping(value="/process", method= RequestMethod.POST)
    public void startProcessInstance(String assignee) {
        myService.startProcess(assignee);
    }

    @RequestMapping(value="/tasks/{assignee}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@PathVariable(value ="assignee" ) String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();

        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }
}
