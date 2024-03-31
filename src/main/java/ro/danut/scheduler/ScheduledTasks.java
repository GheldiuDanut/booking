package ro.danut.scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        log.info("The time is now {}", now.format(dateFormat));
    }
}

