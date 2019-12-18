package lk.kavishmanjitha.gflock_sms_service.main.controller;

import lk.kavishmanjitha.gflock_sms_service.main.entity.MainSsmsNumbers;
import lk.kavishmanjitha.gflock_sms_service.main.service.MainSsmsNumbersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/gflock-sms-service")
@CrossOrigin
public class MainSsmsNumbersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainSsmsNumbersController.class);

    @Autowired
    private MainSsmsNumbersService mainSsmsNumbersService;

    @GetMapping("/find-sms-sent-users")
    public List<MainSsmsNumbers> findAllSmsSentNumbersDetails() {
        return mainSsmsNumbersService.findAllSmsSentNumbersDetails();
    }

    @GetMapping("/manual-sent-sms")
    public Integer manualSentSms() {
        LOGGER.info("MANUAL GFLOCK SMS SENT");
        mainSsmsNumbersService.sentSmsTotalIncomeAndTotalQty();
        return 1;
    }

    @GetMapping("/log-download")
    public void downloadImage(HttpServletResponse response) throws FileNotFoundException, IOException {
        InputStream inputStream = new FileInputStream("./log/message-log.txt");
        OutputStream outputStream = response.getOutputStream();

        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) > 0) {
            outputStream.write(bytes);
        }
        outputStream.flush();
        LOGGER.info("DOWNLOAD LOGFILE");
    }
}
