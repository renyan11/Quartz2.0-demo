package com.quartz.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTask {
	
	/**
	 * jdk1.5特性，没有使用定时调度框架
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
		//只有一个线程，用来调度执行将来的任务
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间 ，第四个是单位时间
		executorService.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);
	}

}
