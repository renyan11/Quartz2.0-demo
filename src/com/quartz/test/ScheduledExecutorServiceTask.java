package com.quartz.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTask {
	
	/**
	 * jdk1.5���ԣ�û��ʹ�ö�ʱ���ȿ��
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable runnable = new Runnable() {
			int count = 1;
			public void run() {
				System.out.println("Hello"+(count++));
			}
		};
		//ֻ��һ���̣߳���������ִ�н���������
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		// �ڶ�������Ϊ�״�ִ�е���ʱʱ�䣬����������Ϊ��ʱִ�еļ��ʱ�� �����ĸ��ǵ�λʱ��
		executorService.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);
	}

}
