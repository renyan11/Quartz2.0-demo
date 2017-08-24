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
 * ʹ�õ��ȿ��ʵ�ֶ�ʱ���� Quartz
 * @author Ryan
 *
 */
public class SchedulerTest {
	public static void main(String[] args) {
		//ͨ��schedulerFactory��ȡһ��������
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = null;
		
		try {
			//ͨ��schedulerFactory��ȡһ��������
			scheduler = schedulerFactory.getScheduler();
			//����jobDetailʵ������Jobʵ����
			//ָ��job�����ƣ�����������ƣ��Լ���job��
			JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "jgroup1").build();
			//������ȴ�������
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))//�����н��ͺ���
					.startNow().build();
			//����ҵ�ʹ�����ע�ᵽ���������
			scheduler.scheduleJob(job, trigger);
			//��������
			scheduler.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}


/*
 	"0 0/1 * * * ?"   1���Ӵ���һ��
 	"0 0 12 * * ?"    ÿ������12�㴥��    
	"0 15 10 ? * *"    ÿ������10:15����    
	"0 15 10 * * ?"    ÿ������10:15����    
	"0 15 10 * * ? *"    ÿ������10:15����    
	"0 15 10 * * ? 2005"    2005���ÿ������10:15����    
	"0 * 14 * * ?"    ��ÿ������2�㵽����2:59�ڼ��ÿ1���Ӵ���    
	"0 0/5 14 * * ?"    ��ÿ������2�㵽����2:55�ڼ��ÿ5���Ӵ���     
	"0 0/5 14,18 * * ?"    ��ÿ������2�㵽2:55�ڼ������6�㵽6:55�ڼ��ÿ5���Ӵ���     
	"0 0-5 14 * * ?"    ��ÿ������2�㵽����2:05�ڼ��ÿ1���Ӵ���    
	"0 10,44 14 ? 3 WED"    ÿ�����µ�������������2:10��2:44����    
	"0 15 10 ? * MON-FRI"    ��һ�����������10:15����    
	"0 15 10 15 * ?"    ÿ��15������10:15����    
	"0 15 10 L * ?"    ÿ�����һ�յ�����10:15����    
	"0 15 10 ? * 6L"    ÿ�µ����һ������������10:15����      
	"0 15 10 ? * 6L 2002-2005"    2002����2005���ÿ�µ����һ������������10:15����    
	"0 15 10 ? * 6#3"    ÿ�µĵ���������������10:15����
 * */
