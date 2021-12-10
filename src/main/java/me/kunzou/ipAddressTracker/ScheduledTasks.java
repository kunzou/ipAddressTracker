package me.kunzou.ipAddressTracker;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import me.kunzou.ipAddressTracker.service.EmailService;
import me.kunzou.ipAddressTracker.service.IpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
  private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
  private static String ip;

  private IpService ipService;
  private EmailService emailService;

  @Autowired
  public void setIpService(IpService ipService) {
    this.ipService = ipService;
  }

  @Autowired
  public void setEmailService(EmailService emailService) {
    this.emailService = emailService;
  }

  @Scheduled(fixedRate = 600000)
  public void reportIp() throws MessagingException {
    String currentIp = ipService.getIp();

    if(currentIp != null) {
      if(ip == null) {
        ip = currentIp;
        log(currentIp);
        emailService.sendEmail(currentIp);
      }
      else if(!ip.equals(currentIp)) {
        log(currentIp);
        emailService.sendEmail(currentIp);
      }
    }
  }

  void log(String ip) {
    log.info("{} {}", dateFormat.format(new Date()), ip);
  }
}
