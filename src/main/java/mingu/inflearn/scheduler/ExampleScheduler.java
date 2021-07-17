package mingu.inflearn.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@Slf4j
public class ExampleScheduler {

    @Scheduled(cron = "#{@schedulerCron}")
    public void schedule1() {
        log.info("schedule1 동작하고 있음 : {}", Calendar.getInstance().getTime());
    }
}
