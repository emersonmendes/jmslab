package br.com.emersonmendes.schedule;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
public class ScheduleExample {

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void doWork() {
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        System.out.println("################################################################################");
        System.out.println("Executing Schedule: " + simpleDateFormat.format(currentTime));
        System.out.println("################################################################################");
    }

}