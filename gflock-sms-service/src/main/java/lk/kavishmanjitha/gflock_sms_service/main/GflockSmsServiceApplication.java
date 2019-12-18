package lk.kavishmanjitha.gflock_sms_service.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class GflockSmsServiceApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(GflockSmsServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GflockSmsServiceApplication.class, args);
        LOGGER.info("START GFLOCK SMS SERVER");
	}
}
