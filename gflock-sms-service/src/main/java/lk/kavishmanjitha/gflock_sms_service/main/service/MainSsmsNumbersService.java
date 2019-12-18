package lk.kavishmanjitha.gflock_sms_service.main.service;

import lk.kavishmanjitha.gflock_sms_service.main.entity.MainSsmsNumbers;
import lk.kavishmanjitha.gflock_sms_service.main.repository.MainSsmsNumbersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MainSsmsNumbersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainSsmsNumbersService.class);

    @Value("${branch_name}")
    private String branchName;
    
    @Autowired
    private MainSsmsNumbersRepository mainSsmsNumbersRepository;

    public List<MainSsmsNumbers> findAllSmsSentNumbersDetails() {
        return mainSsmsNumbersRepository.findBySmsStatus(Boolean.TRUE);
    }

    public void sentSmsTotalIncomeAndTotalQty() {
        List<Object[]> totalIncomeAndTotalQty = mainSsmsNumbersRepository.getTotalIncomeAndTotalQty();
        if (!totalIncomeAndTotalQty.get(0)[0].equals("0.00")) {
            List<MainSsmsNumbers> byStatus = mainSsmsNumbersRepository.findBySmsStatus(Boolean.TRUE);
            LOGGER.info("--------------------------------------------------------------------------");
            for (MainSsmsNumbers mainSsmsNumbers : byStatus) {
                String message = "";
                message = message + " Date" + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\n";
                message = message + " Time" + " " + new SimpleDateFormat("hh:mm:ss a").format(new Date()) + "\n";
                message = message + " Sale - " + " " + totalIncomeAndTotalQty.get(0)[0] + "\n";
                message = message + " Item Qty - " + " " + totalIncomeAndTotalQty.get(0)[1] + "\n";
                message = message + " No Of Bills - " + " " + totalIncomeAndTotalQty.get(0)[2] + "\n";
                message = message + " Shoes Pair - " + " " + totalIncomeAndTotalQty.get(0)[3] + "\n";
                message = message + " Basket Value - " + " " + totalIncomeAndTotalQty.get(0)[4];
                LOGGER.info("\n" + message);
                LOGGER.info("--------------------------------------------------------------------------");
                String uri = "http://gflock-sms.supervisionglobal.com/send_sms.php?api_key=5494031051&number=" + mainSsmsNumbers.getNumber() + "&message=" + message;
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);
                if ("0".equals(result)) {
                    LOGGER.info("SENT GFLOCK SMS " + mainSsmsNumbers.getName() + " - " + mainSsmsNumbers.getNumber());
                }
            }
            LOGGER.info("--------------------------------------------------------------------------");
        }
    }
}
