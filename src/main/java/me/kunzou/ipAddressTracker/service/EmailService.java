package me.kunzou.ipAddressTracker.service;

import javax.mail.MessagingException;

public interface EmailService {
  void sendEmail(String ip) throws MessagingException;
}
