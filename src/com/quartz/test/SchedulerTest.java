package com.quartz.test;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 使用调度框架实现定时任务 Quartz
 * @author Ryan
 *
 */
public class SchedulerTest {
	public static void main(String[] args) {
		//通过schedulerFactory获取一个调度器
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = null;
		
		try {
			//通过schedulerFactory获取一个调度器
			scheduler = schedulerFactory.getScheduler();
			//创建jobDetail实例，绑定Job实现类
			//指明job的名称，所在组的名称，以及绑定job类
			JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "jgroup1").build();
			//定义调度触发规则
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))//下面有解释含义
					.startNow().build();
			//把作业和触发器注册到任务调度中
			scheduler.scheduleJob(job, trigger);
			//启动调度
			scheduler.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}


/*
 	"0 0/1 * * * ?"   1分钟触发一次
 	"0 0 12 * * ?"    每天中午12点触发    
	"0 15 10 ? * *"    每天上午10:15触发    
	"0 15 10 * * ?"    每天上午10:15触发    
	"0 15 10 * * ? *"    每天上午10:15触发    
	"0 15 10 * * ? 2005"    2005年的每天上午10:15触发    
	"0 * 14 * * ?"    在每天下午2点到下午2:59期间的每1分钟触发    
	"0 0/5 14 * * ?"    在每天下午2点到下午2:55期间的每5分钟触发     
	"0 0/5 14,18 * * ?"    在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发     
	"0 0-5 14 * * ?"    在每天下午2点到下午2:05期间的每1分钟触发    
	"0 10,44 14 ? 3 WED"    每年三月的星期三的下午2:10和2:44触发    
	"0 15 10 ? * MON-FRI"    周一至周五的上午10:15触发    
	"0 15 10 15 * ?"    每月15日上午10:15触发    
	"0 15 10 L * ?"    每月最后一日的上午10:15触发    
	"0 15 10 ? * 6L"    每月的最后一个星期五上午10:15触发      
	"0 15 10 ? * 6L 2002-2005"    2002年至2005年的每月的最后一个星期五上午10:15触发    
	"0 15 10 ? * 6#3"    每月的第三个星期五上午10:15触发
 * */
