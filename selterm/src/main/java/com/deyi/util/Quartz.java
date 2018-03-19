package com.deyi.util;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import cn.saobei.entity.utils.SaoSign;

public class Quartz {

	public static void run(String orderno) {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobDetail job = null;//new JobDetail(SaoSign.getRandomString(6), Scheduler.DEFAULT_GROUP, SimpleJob.class);
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("orderno", orderno);
			job.setJobDataMap(jobDataMap);
			SimpleTrigger simpleTrigger = new SimpleTrigger(SaoSign.getRandomString(6), Scheduler.DEFAULT_MANUAL_TRIGGERS);
			Date date = new Date();
			simpleTrigger.setStartTime(date);
			simpleTrigger.setRepeatCount(3);
			simpleTrigger.setRepeatInterval(10000);
			simpleTrigger.setEndTime(DateUtils.addSecond(date, 30));

			scheduler.scheduleJob(job, simpleTrigger);

			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
