package com.example.spring.scheduler.demoscheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    public static final int POOL_SIZE = 10;

    /**
     * 기본적으로 스프링 스케줄러는 1개의 기본 스레드로 수행이 된다.
     * 이런경우 스케줄러 작업이 많아지면 밀리게 되는데, 이때 풀을 이용하여 태스크를 수행할 수 있다.
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(POOL_SIZE);
        taskScheduler.setThreadNamePrefix("task-pool-");
        taskScheduler.initialize();

        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
    }
}
