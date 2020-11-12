package com.example.xb.config;

import com.example.xb.quartz.OrderStatusJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail orderStatusJobDetail(){
        return JobBuilder.newJob(OrderStatusJob.class).storeDurably().build();
    }

    @Bean
    public Trigger orderStatustrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60) //每一秒执行一次
                .repeatForever(); //永久重复，一直执行下去
        return TriggerBuilder.newTrigger()
                .forJob(orderStatusJobDetail())
                .withSchedule(scheduleBuilder)
                .build();
    }

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
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?").withMisfireHandlingInstructionDoNothing()).build();
//    }

    //Error:EmbeddedDatabaseType class not found，Druid数据源初始化需要引入spring-jdbc依赖，JPA或mybatis依赖已经包含该依赖
//    @Bean
//    @QuartzDataSource
//    @ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
//    DataSource quartzDataSource(){
//        return DruidDataSourceBuilder.create().build();
//    }

}