package com.flowable.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.flowable.springboot.dao")//配置mapper扫描
public class FlowableSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableSpringBootApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(final RepositoryService repositoryService,
//                                  final RuntimeService runtimeService,
//                                  final TaskService taskService) {
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                System.out.println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
//                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//                //runtimeService.startProcessInstanceByKey("ExpenseProcess");
//                System.out.println("Number of tasks after process start: "
//                        + taskService.createTaskQuery().count());
//            }
//        };
//    }
}

