package com.shenjy.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 【 定时任务 】
 *
 * @author shenjy 2018/07/01
 */
//@Component
//@EnableScheduling
public class ScheduleTask {

    @Value("#{ '${multi.value}'.split(',') }")
    private List<String> values;

    @Value("${simple.value: 3600}")
    private int fruit;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "${jobs.cron}")
    public void startTask() {
        System.out.println(fruit);
        System.out.println(values.size());
        System.out.println("定时任务，当前时间：" + DATE_FORMAT.format(new Date()));
    }
}
