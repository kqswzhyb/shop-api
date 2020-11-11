package com.example.xb.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.xb.quartz.QuartzJob1;
import org.quartz.*;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class QuartzConfig {
//    @Bean
//    public JobDetail orderStatusJob(){
//        return JobBuilder.newJob(QuartzJob1.class).storeDurably().build();
//    }
//
//    @Bean
//    public Trigger orderStatusTrigger(){
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(60); //每一秒执行一次
////                .repeatForever(); //永久重复，一直执行下去
//        return TriggerBuilder.newTrigger()
//                .forJob(orderStatusJob())
//                .withSchedule(scheduleBuilder)
//                .build();
//    }

//    @Bean
//    public JobDetail jobDetail2(){
//        QuartzJobBean quartzJob2 = new QuartzJobBean() {
//            @Override
//            protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                System.out.println("内部类quartzJob2----" + sdf.format(new Date()));
//            }
//        };
//        return JobBuilder.newJob(quartzJob2.getClass()).storeDurably().build();
//    }
//
//    @Bean
//    public Trigger trigger2(){
//        //JobDetail的bean注入不能省略
//        //JobDetail jobDetail3 = JobBuilder.newJob(QuartzJob2.class).storeDurably().build();
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(2) //每2秒执行一次
//                .repeatForever(); //永久重复，一直执行下去
//        return TriggerBuilder.newTrigger()
//                .forJob(jobDetail2())
//                .withSchedule(scheduleBuilder).build();
//    }
    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
    DataSource quartzDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
}
