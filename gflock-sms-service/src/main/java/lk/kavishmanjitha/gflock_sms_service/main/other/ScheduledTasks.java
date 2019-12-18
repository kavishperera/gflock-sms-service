package lk.kavishmanjitha.gflock_sms_service.main.other;

import lk.kavishmanjitha.gflock_sms_service.main.service.MainSsmsNumbersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private MainSsmsNumbersService mainSsmsNumbersService;

    //@Scheduled(cron = "0 0 9-21 * * *")
    @Scheduled(cron = "0 0 13 * * *")
    public void scheduleTask10Clock() {
        LOGGER.info("13:00 OM CLOCK SENT SMS" + dateTimeFormatter.format(LocalDateTime.now()));
        mainSsmsNumbersService.sentSmsTotalIncomeAndTotalQty();
    }

    @Scheduled(cron = "0 0 15 * * *")
    public void scheduleTask12Clock() {
        LOGGER.info("15:00 PM CLOCK SENT SMS" + dateTimeFormatter.format(LocalDateTime.now()));
        mainSsmsNumbersService.sentSmsTotalIncomeAndTotalQty();
    }

    @Scheduled(cron = "0 0 17 * * *")
    public void scheduleTask2Clock() {
        LOGGER.info("17.00 PM CLOCK SENT SMS" + dateTimeFormatter.format(LocalDateTime.now()));
        mainSsmsNumbersService.sentSmsTotalIncomeAndTotalQty();
    }

    @Scheduled(cron = "0 0 19 * * *")
    public void scheduleTask4Clock() {
        LOGGER.info("19.00 PM CLOCK SENT SMS" + dateTimeFormatter.format(LocalDateTime.now()));
        mainSsmsNumbersService.sentSmsTotalIncomeAndTotalQty();
    }

    @Scheduled(cron = "0 0 21 * * *")
    public void scheduleTask9Clock() {
        LOGGER.info("21:00 PM CLOCK SENT FINAL SMS" + dateTimeFormatter.format(LocalDateTime.now()));
        mainSsmsNumbersService.sentSmsTotalIncomeAndTotalQty();
    }
}
